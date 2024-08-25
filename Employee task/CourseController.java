import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CourseController {
	
  private Scanner scanner = new Scanner(System.in);
  private CourseService service = new CourseService();
  
  public void addCourseDetails(String employeeId, String id) {
    
    
    if (service.isCourseFound(id) != null) {
      service.addCourseToEmployee(employeeId, id);
      System.out.println("\nEmployee binded with that course Exist\n");
      return; 
    }  
    
    System.out.println("Enter Name of Course: ");
    String name = scanner.nextLine();
    System.out.println("Enter Course Description: ");
    String description = scanner.nextLine();
    
    if (service.addCourse(name, description, employeeId) != null) {
      System.out.println("\nCourse added successfuly\n");
    } else {
      System.out.println("\nSomething went wrong\n");
    }
  }
  
  public void printAnCourse(Map<String, Object> course) {
    course.forEach((field, value) -> {
      System.out.println(field + ": " + value);
    });
    System.out.println();
  }
  
  public void renderAnCourse() {
    System.out.println("Enter Course id to get details: ");
    String searchId = scanner.nextLine();
    if (service.isCourseFound(searchId) != null) {
      Map<String, Object> course = service.getCourse(searchId);
      printAnCourse(course);
    } else
      System.out.println("\nCourse not found\n");
  } 
  
  public void updateCourseDetails(String employeeId) {
    System.out.println("Enter Course id to update details: ");
    String courseId = scanner.nextLine();
    if (service.isCourseFound(courseId)!= null) {
      System.out.println("\nwhat are the fields you want to update\n");
      System.out.println("1) Name\n2) Description\n"
                + "3) Add Employee\n"
                + "\n(give field numbers separated by single space)");
      String[] fieldNumbers = scanner.nextLine().split(" ");
      for (String s : fieldNumbers) {
        switch(Integer.parseInt(s)) {
          case 1:
            System.out.println("Enter Name you want to update: ");
            String name = scanner.nextLine();
            service.updateName(courseId, name);
            break;
          case 2:
            System.out.println("Enter Description you want to update: ");
            String description = scanner.nextLine();
            service.updateDescription(courseId, description);
            break;
          case 3:
            service.updateEmployee(courseId, employeeId);
            break;
          
          default:
            System.out.println("Choose correct field");
        }
      }
      System.out.println("\nCourse data updated successfully\n");
    } else {
      addCourseDetails(employeeId, courseId);
    }
  }
  public void removeCourse() {
    System.out.println("Enter Course id to delete details: ");
    String deleteId = scanner.nextLine();
    if (service.isCourseFound(deleteId)!= null) {
      if (service.removeCourse(deleteId) != null) {
        System.out.println("\nSuccessfuly deleted the Course details\n");
      } else {
        System.out.println("Something went wrong");
      }
    }
    else
      System.out.println("\nGiven id not found\n");
  }
  public void renderAllCourse() {
    System.out.println("\t\t All Course Data");
    List<Map<String, Object>> courseData = service.getAllCourse();
    for (Map<String, Object> course : courseData) {
      printAnCourse(course);
    }
  }
  
  public void removeEmployeeFromCourse(String courseId, String employeeId) {
    Course course = service.removeEmployeeFromCourse(courseId, employeeId);
    if(course != null) {
      System.out.println("Employee removed from course");
    } else {
      System.out.println("Employee is not removed from course");
    }
    
  
  }
  
  
}