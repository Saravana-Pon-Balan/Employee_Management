package employeeManagement.branch;

import java.util.List;
import java.sql.*;

import employeeManagement.employee.Employee;

/**
 * <p>
 * Implementation to business logic for Branch details.
 * </p>
 */
public class BranchService {
  private BranchDAO branchDao = new BranchDAO();
		
  /**
   * <p>
   * It get the branch if exist.
   * </p>
   * @param branchId is used for searching the branch from Branch Data by branchId.
   * @return The Branch object if exist or null.
   */
  public Branch getBranchIfExist(int branchId) {
    return branchDao.getBranchIfExist(branchId);
  }
  
	/**
   * <p>
   * It creates the new branch object.
   * </p>
	 * @param name is used for add branch name.
	 * @param location is used for add branch location.
   * @param employee is used for initially bind specified employee to Branch.
   * @return The Branch object.
   */
  public Branch addBranch(String name, String location, Employee employee) {
    if(employee != null && employee.getRole().equals("Admin")) {
      Branch branch = new Branch(0, name, location);
      Branch branchData =  branchDao.insertBranch(branch);  
      Employee bindedEmployee = bindBranch(employee, branchData);
      return branchData;
    } else {
      return null;
    }
  }
  
	/**
   * <p>
   * It updates the branch name on reference.
   * </p>
   * @param branch is used for find the Branch.
	 * @param name is used for update new name for the branch.
   * @return The Branch object or null if branch doesn't exist.
   */
  public Branch updateName(Branch branch, String name) {
      branch.setName(name);
      return branch; 
  }
  
	/**
   * <p>
   * It updates the branch location on reference.
   * </p>
   * @param branch is used for find the Branch.
	 * @param location is used for update new location for the branch
   * @return The Branch object or null if branch doesn't exist.
   */
  public Branch updateLocation(Branch branch, String location) {
      branch.setLocation(location);
      return branch;
  }

  /**
   * <p>
   * It updates the branch details in Database.
   * </p>
   * @param branch is used for find the specific Branch.
   */
  public void updateBranchInDb(Branch branch) {
		branchDao.updateBranchInDb(branch);
  }
 
  
	
	/**
	 * <p>
   * It is bind the employee with specific Branch.
	 * </p>
	 * @param employee is used for find the specific employee.
	 * @param branch is used for binding the branch to employee.
	 * @return The employee data if employee exist or null.
   */
	public Employee bindBranch(Employee employee, Branch branch) {
    if (employee != null) {
      employee.setBranch(branch);
			branch.setEmployee(employee);
      return employee;
    }
    return null;
  }
}