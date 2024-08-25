import java.util.Scanner;

/**
* <p>
* Implementation to manage Emloyee Branch Details
* </p>
**/

public class BranchController {
	
  private Scanner scanner = new Scanner(System.in);
  private BranchService branchService = new BranchService();
  private EmployeeService employeeService = new EmployeeService();
  
	/**
  * <p>
  * It get the branch if exist.
  * </p>
  * @param branchId its used for searching the branch from Branch Data by branchId.
  * @return The Branch object.
  */
	public Branch getBranchIfExists(String branchId) {
		return branchService.getBranchIfExists(branchId);
	}
	
	/**
  * <p>
  * It creates the new branch.
  * </p>
  * @param employeeId its used for searching the branch from Branch Data by branchId.
  * @return The Branch object.
  */
  public Branch addBranchDetails(String employeeId) {
    System.out.println("Enter Name of Branch: ");
    String name = scanner.nextLine();
    System.out.println("Enter Branch Location: ");
    String location = scanner.nextLine();
    Branch branch = branchService.addBranch(name, location, employeeId);
    if (branch != null) {
      System.out.println("\nBranch created successfuly\n");
			return branch;
    } else {
      System.out.println("\nSomething went wrong\n");
			return null;
    }
  }
  
  public Branch updateBranchDetails(String employeeId) {
    String branchId;
    Branch branch = employeeService.getEmployeeIfExists(employeeId).getBranch();
    if(branch != null) {
      branchId = branch.getId();
			branchService.addBranchToEmployee(employeeId, branchId);
      System.out.println("\nwhat are the fields you want to update\n");
      System.out.println("1) Name\n2) Location\n"
                          + "3) Move to another branch\n4) Exit\n"
                          + "\n(give field numbers separated by single space)");
      String[] fieldNumbers = scanner.nextLine().split(" ");
      for (String s : fieldNumbers) {
        switch(Integer.parseInt(s)) {
          case 1:
						System.out.println("Enter Name you want to update: ");
					 	String name = scanner.nextLine();
            branchService.updateName(branchId, name);
            break;
				  case 2:
            System.out.println("Enter Location you want to update: ");
            String location = scanner.nextLine();
            branchService.updateLocation(branchId, location);
            break;
          case 3:
					  System.out.println("Enter Existing Branch ID: ");
						String newBranchId = scanner.nextLine();
            if(branchService.updateEmployee(newBranchId, employeeId) == null) {
							System.out.println("\nSpecified branch doesnt't exist\n");
						}
            break;
          case 4:
            break;
          default:
            System.out.println("Choose correct field");
        }
      }
      System.out.println("\nBranch data updated successfully\n");
      return branch;
    } else {
      System.out.println("Enter Branch id to update: ");
      branchId = scanner.nextLine();
			System.out.println("New Branch creating");
      return addBranchDetails(employeeId); 
	  }
    
  }
  
  public void removeBranch() {
    System.out.println("Enter Branch id to delete details: ");
    String branchId = scanner.nextLine();
    if (branchService.getBranchIfExists(branchId)!= null) {
      if (branchService.removeBranch(branchId) != null) {
        System.out.println("\nSuccessfuly deleted the Branch details\n");
      } else {
        System.out.println("Something went wrong");
      }
    }
    else
      System.out.println("\nGiven id not found\n");
  }
  
  public void removeEmployeeFromBranch(String branchId, String employeeId) {
    Branch branch = branchService.removeEmployeeFromBranch(branchId, employeeId);
    if(branch != null) {
      System.out.println("Employee removed from branch");
    } else {
      System.out.println("Employee is not removed from branch");
    }
  }
	
  
}