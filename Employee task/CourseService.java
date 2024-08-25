import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
  private CourseDAO courseDao = new CourseDAO();
  private EmployeeService employeeService = new EmployeeService();
	private static int autoId = 0;
	
  public Course isCourseFound(String id) {
    return courseDao.isCourseFound(id);
  }
  
  public Course addCourse(String name, String description, String employeeId) {
    Employee employee = employeeService.isEmployeeFound(employeeId);
    if(employee != null) {
			String courseId = Integer.toString(++autoId);
      Course course = new Course(name, description);
      Course courseData =  courseDao.insertCourse(course);  
      Employee bindedEmployee= employeeService.bindCourse(employeeId, courseData);
      courseData.setEmployee(bindedEmployee);
      return courseData;
    } else {
      return null;
    }
  }
  
  public Map<String, Object> getCourse(String id) {
    Course courseData = courseDao.fetchCourse(id);
    Map<String, Object> branchDataMap = new LinkedHashMap<>();
    branchDataMap.put("Id", courseData.getId());
    branchDataMap.put("Name", courseData.getName());
    branchDataMap.put("Description", courseData.getDescription());
    branchDataMap.put("Employee", courseData.getEmployee());
    return branchDataMap;
  }
  
  
  
  public Course updateName(String id, String name) {
    Course updateCourseData = courseDao.fetchCourse(id);
    if (updateCourseData != null) {
      updateCourseData.setName(name);
      return updateCourseData;
    }
    return updateCourseData;  
  }
  
  public Course updateDescription(String id, String description) {
    Course updateCourseData = courseDao.fetchCourse(id);
    if (updateCourseData != null) {
      updateCourseData.setDescription(description);
      return updateCourseData;
    }
    return updateCourseData;
  }
  
  public Course updateEmployee(String id, String employeeId) {
    Course updateCourseData = courseDao.fetchCourse(id);
    Employee employee = employeeService.isEmployeeFound(employeeId);
    
    if (updateCourseData != null) {
      updateCourseData.setEmployee(employee);
      employeeService.bindCourse(employeeId, updateCourseData);
      return updateCourseData;
    }
    return null;
  }
  
  
  
  public Course removeCourse(String id) {
    return courseDao.deleteCourse(id);
  }
  
  
  public List<Map<String, Object>> getAllCourse() {
    
    List<Course> courses = new ArrayList<>(courseDao.fetchAllCourse());
    List<Map<String, Object>> courseData = new ArrayList<>();
    Map<String, Object> singleCourse = new LinkedHashMap<>();
        for (Course course : courses) {
            singleCourse.put("Id: ", course.getId());
      singleCourse.put("Name", course.getName());
      singleCourse.put("Description", course.getDescription());
      singleCourse.put("Employee", course.getEmployee());
      courseData.add(new LinkedHashMap<String, Object>(singleCourse));
      singleCourse.clear();
        }
    return courseData;
  }
  
  public Course addCourseToEmployee(String employeeId, String courseId) {
    Course course = courseDao.isCourseFound(courseId);
    Employee employee = employeeService.bindCourse(employeeId, course);
    course.setEmployee(employee);
    return course;
  }
  
  public Course removeEmployeeFromCourse(String courseId, String employeeId) {
    Course course = isCourseFound(courseId);
    System.out.println(course);
    if(course != null) {
      List<Employee> employees = course.getEmployee();
      System.out.println("emp"+employees);
      
      for(int i = 0; i < employees.size(); i++) {
        System.out.println(employees.get(i).getId().equals(employeeId));
        if(employees.get(i).getId().equals(employeeId)) {
          System.out.println("id"+employees.get(i).getId());
          employees.remove(i);
          return course;
        }
      }
    }
    return null;
  }
}