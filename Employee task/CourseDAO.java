import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

class CourseDAO {
	
  private static Map<String, Course> courses = new HashMap<>();
  private String courseId;
  
  public Course isCourseFound(String id) {
    if (courses.containsKey(id)) {
      return courses.get(id);
    } else {
      this.courseId = id;
      return courses.get(id);
    }
  }
  
  public Course insertCourse(Course courseData) {
		String courseId = courseData.getId();
		courses.put(courseId, courseData);
		return courses.get(courseId);
  }
  
  public Course fetchCourse(String id) {
    return courses.get(id);
  }
  
  public Course deleteCourse(String id) {
    if (courses.containsKey(id)) {
      return courses.remove(id);
    } else {
      return null;
    }
  }
  
  public List<Course> fetchAllCourse() {
    return new ArrayList<>(courses.values());
  }
  
}  