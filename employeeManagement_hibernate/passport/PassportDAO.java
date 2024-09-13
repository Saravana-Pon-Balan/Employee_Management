package employeeManagement.passport;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import org.hibernate.HibernateException;    
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.TypedQuery;

import employeeManagement.DBConnection.DbConnection;
import employeeManagement.DBConnection.HibernateConn;
import employeeManagement.employee.Employee;
import employeeManagement.exception.DataBaseException;

/**
 * <p>
 * Implementation to access/insert/delete passport in database.
 * </p>
 */
class PassportDAO {
		
	/**
   * <p>
   * It get the passport if exist.
   * </p>
   * @param employee is used for searching the passport along with that employee.
   * @return The passport object if exist or null.
   */
  public Passport getPassportIfExist(Employee employee) {
    try (Session session = HibernateConn.getInstance()) {
      String hql = "FROM Passport where employee_id = :employeeId";
      TypedQuery<Passport> query = session.createQuery(hql, Passport.class);
			query.setParameter("employeeId", employee.getId());
			Passport passport = query.getSingleResult();
      employee.setPassport(passport);
			return passport;		
		} catch (Exception e) {
			throw new DataBaseException("Issue in server: cannot get the passport", e);
		}
	}
  
	/**
   * <p>
   * It is insert the passport object into database.
   * </p>
   * @param passportData is used for insert passport object into database.
   * @return The passport object.
   */
  public Passport insertPassport(Passport passportData) {
    try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();  
			int id = (int)session.save(passportData);  
			t.commit();  
			System.out.println("successfully saved");    
			passportData.setId(id);
			return passportData;
		} catch (HibernateException e) {
			throw new DataBaseException("Issue in server: cannot insert the passport", e);
		}
  }
	
	/**
   * <p>
   * It is updates the passport data into database.
   * </p>
   * @param passport is used for update passport into database.
   */
	public Passport updatePassport(Passport passport) {
		try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();  
			session.update(passport);  
			t.commit();
      return passport;	
		} catch (HibernateException e) {
			throw new DataBaseException("Issue in server: cannot update the passport", e);
		}
	}
	/**
   * <p>
   * It delete the passport from database.
   * </p>
   * @param passport it is used for find the specific passport.
   */
  public void deletePassport(Passport passport) {
    try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();  
			session.delete(passport);  
			t.commit();  
		} catch (HibernateException e) {
			throw new DataBaseException("Issue in server: cannot delete the passport", e);
		}
  }
  
  
}  