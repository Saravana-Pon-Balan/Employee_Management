import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static Scanner scanner = new Scanner(System.in);
	private static EmployeeController employeeController = new EmployeeController();
	private static PassportController passportController = new PassportController();
  private static TaskAssignmentController taskController = new TaskAssignmentController();
  private static BranchController branchController = new BranchController();
  private static CourseController courseController = new CourseController();
	
  private static void addOtherDetails(Employee employee){
	if (employee != null) {
      System.out.println("\nEmployee added successfuly\n");
      int choice;
      do {
      System.out.println("\nDo you want to add\n");
      System.out.println("1) Passport Details\n"
                + "2) Task Assignment\n3) Branch Details\n"
                + "4) Course Details\n0) Exit");
      choice = scanner.nextInt();
      scanner.nextLine();
			System.out.println(employee.getId());
      switch(choice) {
        case 1:
          passportController.addPassportDetails(employee.getId());
          break;
        case 2:
          taskController.addTaskDetails(employee.getId());
          break;
        case 3:
					System.out.println("\n1) create new branch \n2) add employee to existing branch\n");
					int branchChoice = scanner.nextInt();
					scanner.nextLine();
					if(branchChoice == 1) {
						 branchController.addBranchDetails(employee.getId());
					} else {
						System.out.println("Enter the BranchId: ");
						String branchId = scanner.nextLine();
						Branch branch = branchController.isBranchFound(branchId);
						if(branch != null) {
					    employeeController.bindEmployeeWithBranch(employee.getId(), branch);
						} else {
							System.out.println("\nThere is no Branch Found\n");
						}
						
					}
         
          break;
        case 4:
				  System.out.println("Enter Course id to update details: ");
          String courseId = scanner.nextLine();
          courseController.addCourseDetails(employee.getId(), courseId);
          break;
        default:
          System.out.println("\nchoose correct choice\n");
      }
    } while(choice != 0);
      
    } else {
      System.out.println("\nSomething went wrong\n");
    }
  }

  private static void updateEmployeeDetails() {
    System.out.println("Enter Employee id to update details: ");
    String employeeId = scanner.nextLine();
    if (employeeController.isEmployeeFound(employeeId)!= null) {
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
		        employeeController.updateEmployeeDetails(Integer.parseInt(s), employeeId);
						
						break;
          case 6:
            passportController.updatePassportDetails(employeeId);
            break;
          case 7:
            taskController.updateTaskDetails(employeeId);
            break;
          case 8:
            branchController.updateBranchDetails(employeeId);
            break;
          case 9:
            courseController.updateCourseDetails(employeeId);
            break;
          default:
            System.out.println("Choose correct field");  
        }
      }
      System.out.println("\nEmployee data updated successfully\n");
    } else {
	  System.out.println("\nEmployee not found\n");
	  }
  }
	 public static void removeEmployee() {
    System.out.println("Enter Employee id to delete details: ");
    String employeeId = scanner.nextLine();
    
    if (employeeController.isEmployeeFound(employeeId)!= null) {
      Employee employee = employeeController.isEmployeeFound(employeeId);
      if (employeeController.removeEmployee(employeeId) != null) {
        Passport passport = employee.getPassport();
        if(passport != null) {
          passportController.removePassport(employeeId);

        }
        for(TaskAssignment task : employee.getTask()) {
          taskController.removeTask(task.getId());
        }
        Branch branch = employee.getBranch();
        if(branch != null) {
          branchController.removeEmployeeFromBranch(branch.getId(), employeeId);

        }
        for(Course course : employee.getCourse()) {
          courseController.removeEmployeeFromCourse(course.getId(), employeeId);
        }
        System.out.println("\nSuccessfully deleted the Employee details\n");
      } else {
        System.out.println("Something went wrong");
      }
    }
    else
      System.out.println("\nGiven id not found\n");
  }
  public static void main(String args[]) {
	int choice;
    do {
      System.out.println("\nChoose first\n");
      System.out.println("1) Add Employee\n2) get Employee Details\n"
                  + "3) Update Employee\n4) Delete Employee\n"
                  + "5) Show all Employee Details\n0) Exit");
      choice = scanner.nextInt();
      scanner.nextLine();
      switch(choice) {
        case 1:
          Employee employee = employeeController.addEmployeeDetails();
					addOtherDetails(employee);
          break;
        case 2:
          employeeController.renderAnEmplyee();
          break;
        case 3:
          updateEmployeeDetails();
          break;
        case 4:
          removeEmployee();
          break;
        case 5:
          employeeController.renderAllEmployee();
          break;
        default:
          System.out.println("\nchoose correct choice\n");
      }
    }while(choice != 0);
    
    
  }
}