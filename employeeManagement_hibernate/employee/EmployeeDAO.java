package employeeManagement.employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;    
import org.hibernate.Transaction;  
import org.hibernate.HibernateException;    
import javax.persistence.TypedQuery;

import employeeManagement.DBConnection.DbConnection;
import employeeManagement.DBConnection.HibernateConn;
import employeeManagement.exception.DataBaseException;

/**
 * <p>
 * Implementation to access/modify/delete employee in database.
 * </p>
 */
public class EmployeeDAO {	
	/**
   * <p>
   * It get the employee if exist.
   * </p>
   * @param id is used for searching the employee from employee Data by id.
   * @return The list of employee object if exist or null.
   */
  public List<Object> getEmployeeIfExist(int id) throws DataBaseException {
		List<Object> employeeData = new ArrayList<>();
		try(Session session = HibernateConn.getInstance()) {
			
			String hql = "SELECT e, e.branch.id FROM Employee e WHERE e.id = :id AND e.isDeleted = false";
			TypedQuery<Object[]> query = session.createQuery(hql, Object[].class);
			query.setParameter("id", id);
			List<Object[]> results = query.getResultList();
			if (!results.isEmpty()) {
				Object[] result = results.get(0);		
				Employee employee = (Employee) result[0];
				Integer branchId = (Integer) result[1];
				employeeData.add(employee);
				employeeData.add(branchId);
			}
			return employeeData;
		} catch (HibernateException e) {
		  throw new DataBaseException("Issue in server: cannot get the employee", e);
		}
  }
  
	/**
   * <p>
   * It insert the employee into database.
   * </p>
   * @param employeeData is used for insert employee object into database.
   * @return The employee object.
   */
  public Employee insertEmployee(Employee employeeData) throws DataBaseException {
	  try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();  
			int id = (int)session.save(employeeData);  
			t.commit();  
			System.out.println("successfully saved");    
			employeeData.setId(id);
			return employeeData;
		} catch (HibernateException e) {
		  throw new DataBaseException("You cannot create employee right now", e);
		}
  }
	
	/**
   * <p>
   * It insert if not exist otherwise update the employee into database.
   * </p>
   * @param employee is used for insert employee object into database.
   * @return The true if saved or updated in database otherwise throw exception.
   */
	public Boolean saveOrUpdate(Employee employee) throws DataBaseException {
		try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();  
			session.saveOrUpdate(employee);  
			t.commit();  
			System.out.println("successfully saved or updated");    
			return true;
		} catch (HibernateException e) {
			throw new DataBaseException("You cannot create or update employee right now", e);
		}
	}
	
	/**
   * <p>
   * It update the employee if exist.
   * </p>
   * @param employee is used for searching the employee from database by id.
   * @return The employee object if exist or null.
   */
	public Employee updateEmployee(Employee employee) throws DataBaseException {		
		try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();  
			session.update(employee);  
			t.commit();  
			return employee;
		} catch (HibernateException e) {
			throw new DataBaseException("You cannot update employee right now", e);
		}
	}
  
	/**
   * <p>
   * It update the employee branch if exist.
   * </p>
   * @param employee is used for searching the employee from database by id.
   * @return The employee object if exist or null.
   */
	public Employee updateEmployeeBranch(Employee employee) {
		try(Connection conn = DbConnection.getInstance()) {
			int branchId = 1;
			String sql = "update employee set branch_id = ? where id = " + employee.getId();
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, branchId);
			prepareStatement.executeUpdate();
			return employee;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
   * <p>
   * It is delete the employee from database if exist.
   * </p>
   * @param id is used for searching the employee from employee Data by id.
   */
  public void deleteEmployee(int id) throws DataBaseException {
		
		List<Object> employeeData = getEmployeeIfExist(id);
		if(employeeData != null) {
		  Employee employee = (Employee) employeeData.get(0);
	    employee.setIsDeleted(true);
			updateEmployee(employee);
		} else {
			System.out.println("There is no employee found");
		}
  }
	
	/**
   * <p>
   * It gets the all employee data from database.
   * </p>
   * @return The List of employee objects if exist or empty list.
   */
  public List<Employee> fetchAllEmployee() throws DataBaseException {
		try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();  
			String hql = "FROM Employee WHERE isDeleted = false";
      TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
      List<Employee> employees = query.getResultList();

			t.commit();  
			return employees;
			 
		} catch (HibernateException e) {
		  throw new DataBaseException("Issue in server: cannot get the all employee", e);
		}
		
  }
  
  /**
   * <p>
   * It map the specific employee with specific course in mapper table.
   * </p>
   * @param employee is used to get employee Id.
   * @return 1 if saved or updated otherwise 0.
   */
	public int mapEmployeeWithCourse(Employee employee) throws DataBaseException {
    Transaction transaction = null;
    try (Session session = HibernateConn.getInstance()) {
      transaction = session.beginTransaction();
      session.saveOrUpdate(employee);
			transaction.commit();
			return 1;
    } catch (Exception e) {
		  throw new DataBaseException("Issue in server: cannot enroll the employee with course", e);
    }
  }
}