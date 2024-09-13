package employeeManagement.employee;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;  

import employeeManagement.branch.Branch;
import employeeManagement.course.Course;
import employeeManagement.exception.DataBaseException;
import employeeManagement.task.TaskAssignment;
import employeeManagement.util.DateUtil;


/**
 * <p>
 * Implementation to manage Employee Details
 * </p>
 */
public class EmployeeController {
	
  private static Scanner scanner = new Scanner(System.in);
  private static EmployeeService employeeService = new EmployeeService();
  static final Logger logger = Logger.getLogger(EmployeeController.class);
	//DOMConfigurator.configure("../logger/log4j2.xml");  

	/**
   * <p>
   * It get the Employee if exist.
   * </p>
   * @param employeeId is used for searching the employee from employee Data by employeeId.
   * @return The Employee object if exist or null.
   */
  public Employee getEmployeeIfExist(int employeeId) {
		Employee employee = employeeService.getEmployeeIfExist(employeeId);
		try {
		  return employee;
	  } catch (DataBaseException e) {
			System.out.println(e.getMessage());
			return employee;
		}
		logger.trace("We've just greeted the user!");
		logger.debug("We've just greeted the user!");
		logger.info("We've just greeted the user!");
		logger.warn("We've just greeted the user!");
		logger.error("We've just greeted the user!");
		logger.fatal("We've just greeted the user!");
	}
	
	/**
   * <p>
   * Its for getting input from user for creating the new Employee.
   * </p>
   * @return The Employee object.
   */
  public Employee addEmployeeDetails() {
    System.out.println("Enter Employee name: ");
    String name = scanner.nextLine();
    System.out.println("Enter Employee Date of Birth: (yyyy-mm-dd)");
		Date dob = null;
		while(dob == null) {
			try {
				dob = DateUtil.strToDate(scanner.nextLine());
			} catch(ParseException e) {
				System.out.println(e.getMessage() + " enter the valid Date");
			}
		}
    System.out.println("Enter Employee Mobile Number: ");
    String mobileNumber = scanner.nextLine();
    System.out.println("Enter Employee role: ");
    String role = scanner.nextLine();
    System.out.println("Enter Employee address: ");
    String address = scanner.nextLine();
		int branchId = -1;
		do{
		  try {
		    System.out.println("Enter Employee Branch Id: ");
				branchId = scanner.nextInt();
		  } catch(InputMismatchException e) {
		    scanner.nextLine();
			  System.out.println("\nYou entered not a integer");
				
		  }
		} while(branchId == -1);
		
		Employee employee = null;
		try {
      employee = employeeService.addEmployee(name, dob, mobileNumber, role, address, branchId);
		} catch (DataBaseException e) {
			System.out.println(e.getMessage());
		}
    if(employee == null) {
			System.out.println("\nBranch id is not exist\n");
		  return null;
		} else {
			return employee;
		}
  }
  
  public void displayAnEmplyee() {
    System.out.println("Enter Employee id to get details: ");
		Employee employee = null;
		try {
		  employee = employeeService.getEmployeeIfExist(scanner.nextInt());
	  } catch (DataBaseException e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		} catch(InputMismatchException e) {
		  scanner.nextLine();
			System.out.println("\nYou entered not a integer");	
		}
		
    if (employee != null) {
      System.out.println(employee);
      System.out.println("Passport: " + employee.getPassport());
      System.out.println("Branch: " + employee.getBranch());
      System.out.println("Tasks: ");
			for(TaskAssignment task : employee.getTask()) {
				System.out.println(task);
			}
      System.out.println("Courses: ");
			for(Course course : employee.getCourse()) {
				System.out.println(course);
			}	
    } else {
      System.out.println("\nEmployee not found\n");
    }
	} 
  
	/**
   * <p>
   * Its for update the primary Employee details.
   * </p>
	 * @param choice is used for select which field the user want to update in Employee.
   * @param employeeId is used for get that employee data and update that.
  */
  public void updateEmployeeDetails(int choice, Employee employee) {
	  switch(choice) {
	    case 1:
        System.out.println("Enter name you want to update: ");
        String updateName = scanner.nextLine();
        employeeService.updateEmployeeName(employee, updateName);
		    break;
      case 2:
        System.out.println("Enter Date of Birth you want to update: ");
        String updateDobStr = scanner.nextLine();
				Date updateDob = null;
        try {
          updateDob = DateUtil.strToDate(updateDobStr);
		    } catch(ParseException e) {
			    System.out.println(e.getMessage());
		    }    
        employeeService.updateEmployeeDob(employee, updateDob);
        break;
      case 3:
        System.out.println("Enter Mobile Number you want to update: ");
        String updateMobileNumber = scanner.nextLine();
        employeeService.updateEmployeeMobileNumber(employee, updateMobileNumber);
        break;
      case 4:
        System.out.println("Enter role you want to update: ");
        String updateRole = scanner.nextLine();
        employeeService.updateEmployeeRole(employee, updateRole);
        break;
      case 5:
        System.out.println("Enter address you want to update: ");
        String updateAddress = scanner.nextLine();
        employeeService.updateEmployeeAddress(employee, updateAddress);
        break;
	  }
  }
  
	/**
	 * It updates the employee data in database.
	 * @param employee is used for get employee data.
	 * @return the updated Employee Object.
	 */
	public Employee updateEmployeeInDB(Employee employee) {
		try {
	    return employeeService.updateEmployeeInDB(employee);
		} catch (DataBaseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * <p>
   * Deletes the entire Employee and that references also.
	 * </p>
	 * @param employeeId is used for find the employee.
	 */
  public void removeEmployee(int employeeId) {
		try {
	    employeeService.removeEmployee(employeeId);
    } catch(DataBaseException e) {
			System.out.println(e.getMessage());
		}
	
	}
  
  public void displayAllEmployee() {
		List<Employee> employees = null;
    try {
		  employees = employeeService.getAllEmployee();
		  if(employees.size() > 0) {
        System.out.println("\t\t All Employee Data");
		    for(Employee employee : employees) {
			    System.out.println(employee);
		    }
		  } else {
        System.out.println("\t\tThere is no Employee in database");
		  }
		} catch(DataBaseException e) {
			System.out.println(e);
		}
  }
  
	/**
	 * <p>
	 * It simply add employee reference to Branch and viceversa
	 * </p>
	 * @param employeeId is used to specify the employee.
	 * @param branch is used to specify the Branch.
	 * @return The Employee object if employee exist or null.
	 */
  public Employee bindEmployeeWithBranch(Employee employee, Branch branch) {
		try {
		 return employeeService.bindBranch(employee, branch);
	  } catch(DataBaseException e) {
			System.out.println(e);
		}
		return null;
	} 
}