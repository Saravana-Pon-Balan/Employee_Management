package employeeManagement.course;

import java.util.InputMismatchException;
import java.util.Scanner;

import employeeManagement.employee.Employee;
import employeeManagement.employee.EmployeeService;
import employeeManagement.exception.DataBaseException;

/**
 * <p>
 * Implementation to manage Course Details.
 * </p>
 */
public class CourseController {
	
  private Scanner scanner = new Scanner(System.in);
  private CourseService courseService = new CourseService();
	private EmployeeService employeeService = new EmployeeService();
  
	/**
   * <p>
   * It getting input for creating new course.
   * </p>
   * @param employee is used for initially bind specified employee to new course .
	 * @param courseId is used for bind employee with existing course. 
   */
  public void addCourseDetails(Employee employee, int courseId) {
    Course course = null;
		try {
			course = courseService.getCourseIfExist(courseId);
		} catch (DataBaseException e) {
			System.out.println(e.getMessage());
		}
    if (!employee.getRole().equals("Admin") && course != null) {
      if(employeeService.addCourseToEmployee(employee, course) == 1) {
				System.out.println("\nEmployee binded with that course Exist\n");
			} else {
				System.out.println("\nYou are already Enrolled \n");
			} 
    }  
		
    else if(employee.getRole().equals("Admin")) {
      System.out.println("Enter Name of Course: ");
      String name = scanner.nextLine();
      System.out.println("Enter Course Description: ");
      String description = scanner.nextLine();
		  try {
			  course = courseService.addCourse(name, description);
		  } catch (DataBaseException e) {
			  System.out.println(e.getMessage());
		  }
      if (course != null) {
        System.out.println("\nCourse added successfuly\n");
      } else {
        System.out.println("\nSomething went wrong\n");
      }
		}
		else {
			System.out.println("You cannot create course");
		}
  }	
  
	/**
   * <p>
   * This method is used for update course data such as Name, Description.
   * </p>
   * @param employee is used for bind the employee to Specific course or enroll with existing course .
   */
  public void updateCourseDetails(Employee employee) {
    System.out.println("Enter Course id to update details: ");
    int courseId = -1;
		do{
			try {
				courseId = scanner.nextInt();
			} catch(InputMismatchException e) {
				scanner.nextLine();
				System.out.println("\nYou entered not a integer");	
			}
		} while(courseId == -1);
		scanner.nextLine();
		Course course = null;
		try {
			course = courseService.getCourseIfExist(courseId);
		
      if (course != null && employee.getRole().equals("Admin")) {
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
              courseService.updateName(course, name);
              break;
            case 2:
              System.out.println("Enter Description you want to update: ");
              String description = scanner.nextLine();
              courseService.updateDescription(course, description);
              break;
            case 3:
              if(employeeService.addCourseToEmployee(employee, course) == 0) {
							  System.out.println("\nyou are already enrolled\n");
					   	}
              break;
          
            default:
              System.out.println("Choose correct field");
          }
        }
			  courseService.updateCourseInDb(course);
        System.out.println("\nCourse data updated successfully\n");
		  
      } else if(employee.getRole().equals("Admin")) {
			  System.out.println("\nCreating new course\n");
        addCourseDetails(employee, courseId);
      } else {
			  System.out.println("\nYou cannot update the course details\n");
			  System.out.println("\nYou can enroll this course press 1 for Enroll\n");
			  int Duration = -1;
				do{
					try {
						if(scanner.nextInt() == 1) {
				      addCourseDetails(employee, courseId);	
			      }	
					} catch(InputMismatchException e) {
						scanner.nextLine();
						System.out.println("\nYou entered not a integer");	
					}
				} while(Duration == -1);
		    scanner.nextLine();
				
		  }
		} catch (DataBaseException e) {
			System.out.println(e.getMessage());
    }
  }
	/**
   * <p>
   * It is simply remove employee from a Course in Database.
   * </p>
   * @param employee - is used for find the employee in specific Course enrollment list.
   */
  public void removeEmployeeFromCourse(Employee employee) {
    try {
		  if(employeeService.removeEmployeeFromCourse(employee)) {
        System.out.println("Employee removed from course");
      } else {
        System.out.println("Employee is not removed from course");
      }
		} catch (DataBaseException e) {
		  System.out.println(e.getMessage());
		}
    
    
  
  }
  
  
}