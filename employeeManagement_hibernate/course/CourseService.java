package employeeManagement.course;

import java.util.Set;

import employeeManagement.employee.Employee;
import employeeManagement.exception.DataBaseException;

/**
 * <p>
 * Implementation to business logic for Course.
 * </p>
 */
public class CourseService {
  private CourseDAO courseDao = new CourseDAO();
	
	/**
   * <p>
   * It get the Course if exist.
   * </p>
   * @param courseId is used for searching the course from Course Data by courseId.
   * @return The Course object if exist or null.
   */
  public Course getCourseIfExist(int courseId) throws DataBaseException {
    return courseDao.getCourseIfExist(courseId);
  }
  
	/**
   * <p>
   * It get the course list along with the specific employee.
   * </p>
   * @param employee is used for searching the course from course Data by id.
   * @return The list of courses if exist or null.
   */
	public Set<Course> getCourseOfEmployee(Employee employee) throws DataBaseException {
		return courseDao.getCourseOfEmployee(employee);
	}
	
	/**
   * <p>
   * It creates the new course object.
   * </p>
	 * @param name It specify the course name.
	 * @param decription It specify the course description.
   * @return The course object if added or null.
   */
  public Course addCourse(String name, String description) throws DataBaseException {
    Course course = new Course(0, name, description);
    Course courseData = courseDao.insertCourse(course);
    return courseData;
    
  }
  
	/**
   * <p>
   * It updates the course name on reference.
   * </p>
   * @param course is used for find the course.
	 * @param name is used for update new name for the course.
   * @return The course object or null if course doesn't exist.
   */
  public Course updateName(Course course, String name) {
    if (course != null) {
      course.setName(name);
      return course;
    }
    return course;  
  }
  
	/**
   * <p>
   * It updates the course description on reference.
   * </p>
   * @param course is used for find the course.
	 * @param description is used for update new description for the course.
   * @return The course object or null if course doesn't exist.
   */
  public Course updateDescription(Course course, String description) {
    if (course != null) {
      course.setDescription(description);
      return course;
    }
    return course;
  }
	
	/**
   * <p>
   * It updates the course data in database.
   * </p>
   * @param course is used for find the course.
   */
	public void updateCourseInDb(Course course) throws DataBaseException {
		courseDao.updateCourseInDb(course);
	}
  
}