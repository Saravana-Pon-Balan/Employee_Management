import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BranchService {
  private BranchDAO branchDao = new BranchDAO();
  private EmployeeService employeeService = new EmployeeService();
  private static int autoId = 0;

  public Branch isBranchFound(String id) {
    return branchDao.isBranchFound(id);
  }
  
  public Branch addBranch(String name, String location, String employeeId) {
    Employee employee = employeeService.isEmployeeFound(employeeId);
    if(employee != null) {
			String branchId = Integer.toString(++autoId);
      Branch branch = new Branch(branchId, name, location);
			System.out.println(branch);
      Branch branchData =  branchDao.insertBranch(branch);  
      Employee bindedEmployee = employeeService.bindBranch(employeeId, branchData);
      return branchData;
    } else {
      return null;
    }
  }
  
  public Branch updateName(String id, String name) {
    Branch updateBranchData = branchDao.fetchBranch(id);
    if (updateBranchData != null) {
      updateBranchData.setName(name);
      return updateBranchData;
    }
    return updateBranchData;  
  }
  
  public Branch updateLocation(String id, String location) {
    Branch updateBranchData = branchDao.fetchBranch(id);
    if (updateBranchData != null) {
      updateBranchData.setLocation(location);
      return updateBranchData;
    }
    return updateBranchData;
  }

  public Branch updateEmployee(String id, String employeeId) {
    Branch updateBranchData = branchDao.fetchBranch(id);
    Employee employee = employeeService.isEmployeeFound(employeeId);
    List<Employee> oldBranchEmployees = employee.getBranch() != null ?
                                          employee.getBranch().getEmployee() : new ArrayList<>();
    
    if (!oldBranchEmployees.isEmpty()) {
        Iterator<Employee> iterator = oldBranchEmployees.iterator();
        while (iterator.hasNext()) {
            Employee currentEmployee = iterator.next();
            if (currentEmployee.equals(employee)) { 
                iterator.remove();
            }
        }
    }
    if (updateBranchData != null) {
        employeeService.bindBranch(employeeId, updateBranchData);
        return updateBranchData;
    }
    return null;
  }
  
  public Branch removeBranch(String id) {
    return branchDao.deleteBranch(id);
  }
  
  public Branch addBranchToEmployee(String employeeId, String branchId) {
    Branch branch = isBranchFound(branchId);
    Employee employee = employeeService.bindBranch(employeeId, branch);
    branch.setEmployee(employee);
    return branch;
  }
  
  public Branch removeEmployeeFromBranch(String branchId, String employeeId) {
    Branch branch = isBranchFound(branchId);
    List<Employee> employees = branch.getEmployee();
    for(int i = 0; i < employees.size(); i++) {
      if(employees.get(i).getId().equals(employeeId)) {
        employees.remove(i);
        return branch;
      } 
    }
    return null;
  }
  
}