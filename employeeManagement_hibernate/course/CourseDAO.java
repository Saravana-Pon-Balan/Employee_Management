package employeeManagement.course;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import employeeManagement.DBConnection.HibernateConn;
import employeeManagement.employee.Employee;
import employeeManagement.exception.DataBaseException;

/**
 * <p>
 * Implementation to access/insert/delete course in database.
 * </p>
 */
class CourseDAO {
	
	/**
   * <p>
   * It get the course if exist.
   * </p>
   * @param id is used for searching the course from course Data by id.
   * @return The course object if exist or null.
   */
  public Course getCourseIfExist(int id) {
		try(Session session = HibernateConn.getInstance()) {
			Course course = (Course) session.get(Course.class, id);
			return course;
		} catch(HibernateException e) {
			throw new DataBaseException("Issue in Server: cannot get course right now", e);
		}
  }
  
	/**
   * <p>
   * It fetch the course list along with the specific employee.
   * </p>
   * @param employee is used for search the courses from mapper table by employee id.
   * @return The list of courses if exist or null.
   */
	public Set<Course> getCourseOfEmployee(Employee employee) {
		Set<Course> courses = new HashSet<>();
		try (Session session = HibernateConn.getInstance()) {
			String hql = "SELECT c FROM Course c JOIN c.employees e WHERE e.id = :employeeId";
			TypedQuery<Course> query = session.createQuery(hql, Course.class);
			query.setParameter("employeeId", employee.getId());
			courses = new HashSet<>(query.getResultList());
			for(Course course : courses) {
				if(!employee.getCourse().contains(course)) {
				  employee.setCourse(course);
				}
			}
        return courses;
    } catch (HibernateException e) {
        throw new DataBaseException("Issue in Server: cannot get course of employee", e);
    }
	}
	
	/**
   * <p>
   * It insert the course into database.
   * </p>
   * @param coureData is used for insert course object into database.
   * @return The inseted course object.
   */
  public Course insertCourse(Course courseData) {
		try (Session session = HibernateConn.getInstance()) {
			int courseId = (int) session.save(courseData);
			courseData.setId(courseId);
			return courseData;
		} catch(HibernateException e) {
			throw new DataBaseException("Issue in Server: cannot insert right now", e);
		}
  }
	
	/**
   * <p>
   * It updates the course data in database.
   * </p>
   * @param course is used for get the field data of course object.
   */
	public void updateCourseInDb(Course course) {
		try (Session session = HibernateConn.getInstance()) {
			Transaction transaction = session.beginTransaction();
			session.update(course);
			transaction.commit();
		} catch(HibernateException e) {
			throw new DataBaseException("Issue in Server: cannot update course", e);
		}
	}
	
	

}  