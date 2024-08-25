import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeeController {
	
  private static Scanner scanner = new Scanner(System.in);
  private static EmployeeService service = new EmployeeService();
  
  public Employee isEmployeeFound(String id) {
    return service.isEmployeeFound(id);
  }
  public Employee addEmployeeDetails() {
    System.out.println("Enter Employee name: ");
    String name = scanner.nextLine();
    System.out.println("Enter Employee Date of Birth: (yyyy-mm-dd)");
    String dobStr = scanner.nextLine();
    Date dob = Util.strToDate(dobStr);
    System.out.println("Enter Employee Mobile Number: ");
    String mobileNumber = scanner.nextLine();
    System.out.println("Enter Employee role: ");
    String role = scanner.nextLine();
    System.out.println("Enter Employee address: ");
    String address = scanner.nextLine();
    return service.addEmployee(name, dob, mobileNumber, role, address);
    
  }
  
  public void printAnEmployee(Map<String, Object> employee) {
    employee.forEach((field, value) -> {
      System.out.println(field + ": " + value);
    });
    System.out.println();
  }
  
  public void renderAnEmplyee() {
    System.out.println("Enter Employee id to get details: ");
    String searchId = scanner.nextLine();
    if (service.isEmployeeFound(searchId) != null) {
      Map<String, Object> employee = service.getEmployee(searchId);
      printAnEmployee(employee);
    } else
      System.out.println("\nEmployee not found\n");
  } 
  
  public void updateEmployeeDetails(int choice, String employeeId) {
    
	switch(choice) {
	  case 1:
        System.out.println("Enter name you want to update: ");
        String updateName = scanner.nextLine();
        service.updateEmployeeName(employeeId, updateName);
		break;
      case 2:
        System.out.println("Enter Date of Birth you want to update: ");
        String updateDobStr = scanner.nextLine();
        Date updateDob = Util.strToDate(updateDobStr);
        service.updateEmployeeDob(employeeId, updateDob);
        break;
      case 3:
        System.out.println("Enter Mobile Number you want to update: ");
        String updateMobileNumber = scanner.nextLine();
        service.updateEmployeeMobileNumber(employeeId, updateMobileNumber);
        break;
      case 4:
        System.out.println("Enter role you want to update: ");
        String updateRole = scanner.nextLine();
        service.updateEmployeeRole(employeeId, updateRole);
        break;
      case 5:
        System.out.println("Enter address you want to update: ");
        String updateAddress = scanner.nextLine();
        service.updateEmployeeAddress(employeeId, updateAddress);
        break;
	}
  }
  
 public Employee removeEmployee(String employeeId) {
	 return service.removeEmployee(employeeId);
 }
  
  public void renderAllEmployee() {
    System.out.println("\t\t All Employee Data");
    List<Map<String, Object>> employeeData = service.getAllEmployee();
    for (Map<String, Object> employee : employeeData) {
      printAnEmployee(employee);
    }
  }
  
  public Employee bindEmployeeWithBranch(String employeeId, Branch branch) {
		return service.bindBranch(employeeId, branch);
	}
  
}