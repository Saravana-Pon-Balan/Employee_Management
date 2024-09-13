package employeeManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

import employeeManagement.branch.Branch;
import employeeManagement.branch.BranchController;
import employeeManagement.course.Course;
import employeeManagement.course.CourseController;
import employeeManagement.employee.Employee;
import employeeManagement.employee.EmployeeController;
import employeeManagement.passport.Passport;
import employeeManagement.passport.PassportController;
import employeeManagement.task.TaskAssignment;
import employeeManagement.task.TaskAssignmentController;

/**
 * <p>
 * This is te entry point of the application.
 * In this class only get choice from user and call the appropriate controller
 * </p>
 */
public class Main {
  private static Scanner scanner = new Scanner(System.in);
	private static EmployeeController employeeController = new EmployeeController();
	private static PassportController passportController = new PassportController();
  private static TaskAssignmentController taskController = new TaskAssignmentController();
  private static BranchController branchController = new BranchController();
  private static CourseController courseController = new CourseController();
	
	
	/**
   * <p>
   * It is the base method for get choice from user to add employee's secondary details such as passport, task, etc.
   * </p>
   * <p> 
	 * @param employee is user for pass employee details to specific methods.
  */
  private static void addOtherDetails(Employee employee) {
	if (employee != null) {
      System.out.println("\nEmployee added successfuly\n");
      int choice = -1;
      do {
      System.out.println("\nDo you want to add\n");
      System.out.println("1) Passport Details\n"
                            + "2) Task Assignment\n3) Branch Details\n"
                            + "4) Course Details\n0) Exit");
		  do{
		    try {
				  choice = scanner.nextInt();				
		    } catch(InputMismatchException e) {
		      scanner.nextLine();
			    System.out.println("\nYou entered not a integer re enter valid integer");
		    }
		  } while(choice == -1);
      scanner.nextLine();
      switch(choice) {
        case 1:
          passportController.addPassportDetails(employee);
          break;
        case 2:
          taskController.addTaskDetails(employee);
          break;
        case 3:
					System.out.println("\n1) create new branch \n2) add employee to existing branch\n");
					int branchChoice = scanner.nextInt();
					scanner.nextLine();
					if(branchChoice == 1) {
						 branchController.addBranchDetails(employee);
					} else {
						System.out.println("Enter the BranchId: ");
						int branchId = scanner.nextInt();
						Branch branch = branchController.getBranchIfExist(branchId);
						if(branch != null) {
					    employeeController.bindEmployeeWithBranch(employee, branch);
						} else {
							System.out.println("\nThere is no Branch Found\n");
						}
					}
          break;
        case 4:
				  System.out.println("Enter Course id to add emplyee details if course already exist: ");
          int courseId = -1;
					do {
		        try {
				      courseId = scanner.nextInt();		
		        } catch(InputMismatchException e) {
		          scanner.nextLine();
			        System.out.println("\nYou entered not a integer");	
		        }
		      } while(courseId == -1);
          courseController.addCourseDetails(employee, courseId);
          break;
        default:
          System.out.println("\nchoose correct choice\n");
      }
    } while(choice != 0);
    } else {
      System.out.println("\nSomething went wrong\n");
    }
  }

  /**
   * <p>
   * Its for update the Employee details.
   * </p>
   */
  private static void updateEmployeeDetails() {
    System.out.println("Enter Employee id to update details: ");
    int employeeId = -1;
		do{
			try {
				employeeId = scanner.nextInt();				
			} catch(InputMismatchException e) {
				scanner.nextLine();
				System.out.println("\nYou entered not a integer re enter valid integer");
			
			}
		} while(employeeId == -1);
		scanner.nextLine();
		
		
		Employee employee = employeeController.getEmployeeIfExist(employeeId);
    if (employee != null) {
      System.out.println("\nwhat are the fields you want to update\n");
      System.out.println("1) Name\n2) Date of Birth\n"
                + "3) Mobile Number\n4) Role\n5) Address\n"
                + "6) Passport Details\n"
                + "7) Task Assignment\n8) Branch Details\n"
                + "9) Course Details\n"
                + "\n(give field numbers with single space)");
      String[] fieldNumbers = scanner.nextLine().split(" ");
      for (String s : fieldNumbers) {
        switch(Integer.parseInt(s)) {
          case 1, 2, 3, 4, 5:
		        employeeController.updateEmployeeDetails(Integer.parseInt(s), employee);
						break;
          case 6:
            passportController.updatePassportDetails(employee);
            break;
          case 7:
            taskController.updateTaskDetails(employee);
            break;
          case 8:
            branchController.updateBranchDetails(employee);
            break;
          case 9:
            courseController.updateCourseDetails(employee);
            break;
          default:
            System.out.println("Choose correct field");  
        }
      }
			employeeController.updateEmployeeInDB(employee);
    } else {
	  System.out.println("\nEmployee not found\n");
	  }
  }
	
	/**
   * <p>
   * It remove the whole Employee.
   * </p>
   * @return The Employee object.
   */
	public static void removeEmployee() {
    System.out.println("Enter Employee id to delete details: ");
    int employeeId = -1;
		do{
			try {
				employeeId = scanner.nextInt();				
			} catch(InputMismatchException e) {
				scanner.nextLine();
				System.out.println("\nYou entered not a integer re enter valid integer");
			
			}
		} while(employeeId == -1);
		
    Employee employee = employeeController.getEmployeeIfExist(employeeId);
		if (employee != null) {
			Passport passport = employee.getPassport();
			if(passport != null) {
				passportController.removePassport(employee);
			}
			if(employee.getTask() != null) {
				for(TaskAssignment task : employee.getTask()) {
					taskController.removeTask(task, employee);
				}    
			}
			if(employee.getCourse() != null) {
				courseController.removeEmployeeFromCourse(employee);
			}
			employeeController.removeEmployee(employee.getId());
			System.out.println("\nSuccessfully deleted the Employee details\n");
		} else {
			System.out.println("Something went wrong");
    }
  }
	
	/**
   * <p>
   * In this method only get choice for insert/modify/delete the employee details.
   * </p>
   */
  public static void main(String args[]) {
	int choice = -1;
    do {
      System.out.println("\nChoose first\n");
      System.out.println("1) Add Employee\n2) get Employee Details\n"
                          + "3) Update Employee\n4) Delete Employee\n"
                          + "5) Show all Employee Details\n0) Exit");
			do{
				try {
					choice = scanner.nextInt();				
				} catch(InputMismatchException e) {
					scanner.nextLine();
					System.out.println("\nYou entered not a integer re enter valid integer");
				}
			} while(choice == -1);
      switch(choice) {
        case 1:
          Employee employee = employeeController.addEmployeeDetails();
					addOtherDetails(employee);
          break;
        case 2:
          employeeController.displayAnEmplyee();
          break;
        case 3:
          updateEmployeeDetails();
          break;
        case 4:
          removeEmployee();
          break;
        case 5:
          employeeController.displayAllEmployee();
          break;
        default:
          System.out.println("\nchoose correct choice\n");
      }
    }while(choice != 0); 
		scanner.close();
  }
}