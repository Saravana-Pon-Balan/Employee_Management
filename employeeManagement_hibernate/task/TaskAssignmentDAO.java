package employeeManagement.task;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;  
import org.hibernate.HibernateException;    
import org.hibernate.Session;
import org.hibernate.Transaction;

import employeeManagement.DBConnection.DbConnection;
import employeeManagement.DBConnection.HibernateConn;
import employeeManagement.exception.DataBaseException;

import employeeManagement.employee.Employee;
/**
 * <p>
 * Implementation to access/insert/delete task in database.
 * </p>
 */
class TaskAssignmentDAO {
	  
	/**
   * <p>
   * It get the task if exist.
   * </p>
   * @param employee is used for searching the task associate with employee from database.
   * @return The list of TaskAssignment object if exist or null.
   */
  public List<TaskAssignment> getTaskIfExist(Employee employee) {
    try (Session session = HibernateConn.getInstance()) {
        String hql = "from TaskAssignment where employee_id = :employeeId and is_deleted = false";
        TypedQuery<TaskAssignment> query = session.createQuery(hql, TaskAssignment.class);
        query.setParameter("employeeId", employee.getId());
				List<TaskAssignment> tasks = query.getResultList();
				for(TaskAssignment task : tasks) {
				  employee.setTask(task);
				}
        return tasks;
    } catch (HibernateException e) {
      throw new DataBaseException("Issue in server: cannot get the tasks of employee", e);
    }
  }
  
	/**
   * <p>
   * It is insert the task object into database.
   * </p>
   * @param taskData is used for insert task object into database.
   * @return The TaskAssignment object.
   */                   
  public TaskAssignment insertTask(TaskAssignment taskData) {
		try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();  
			int id = (int)session.save(taskData);  
			t.commit();  
			System.out.println("successfully saved");    
			taskData.setId(id);
			return taskData;
		} catch (HibernateException e) {
			throw new DataBaseException("Issue in server: cannot insert the task", e);
		}
  }
  
	/**
   * <p>
   * It get the task if exist.
   * </p>
   * @param taskId is used for searching the task from task Data by id.
   * @param employee is used add that in task object.
   * @return The TaskAssignment object if exist or null.
   */
  public TaskAssignment fetchTask(int taskId, Employee employee) {
    try (Session session = HibernateConn.getInstance()) {
			String hql = "from TaskAssignment where id = :id and is_deleted = false";
      TypedQuery<TaskAssignment> query = session.createQuery(hql, TaskAssignment.class);
			query.setParameter("id", taskId);
			TaskAssignment task = query.getSingleResult();
			task.setEmployee(employee);
			return task;		
		} catch (HibernateException e) {
			throw new DataBaseException("Issue in server: cannot get the task", e);
		}	
  }
	
	/** <p>
   * It updates the task if exist.
   * </p>
   * @param task is used for searching the task from task Data by id.
   */
	public void updateTask(TaskAssignment task) {
		try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();  
			session.update(task);  
			t.commit();
		} catch (HibernateException e) {
			throw new DataBaseException("Issue in server: cannot update the task", e);
		}
	}
	/**
   * <p>
   * It delete the task from database.
   * </p>
   * @param task is used for find the specific task from database.
   * @return true if deleted otherwise false.
   */
  public Boolean deleteTask(TaskAssignment task) {
    try (Session session = HibernateConn.getInstance()) {
			Transaction t = session.beginTransaction();
      task.setIsDeleted(true);			
			session.update(task);  
			t.commit();  
			return true;
		} catch (HibernateException e) {
			throw new DataBaseException("Issue in server: cannot delete the task", e);
		}
  }
}  