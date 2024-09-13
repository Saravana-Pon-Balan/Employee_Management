package employeeManagement.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import employeeManagement.branch.Branch;
import employeeManagement.branch.BranchService;
import employeeManagement.course.Course;
import employeeManagement.course.CourseService;
import employeeManagement.passport.Passport;
import employeeManagement.passport.PassportService;
import employeeManagement.task.TaskAssignment;
import employeeManagement.task.TaskAssignmentService;
import employeeManagement.util.DateUtil;

import employeeManagement.exception.DataBaseException;

/**
 * <p>
 * Implementation to business logic for Employee.
 * </p>
 */
public class EmployeeService {
    private EmployeeDAO employeeDao = new EmployeeDAO();
    private BranchService branchService = new BranchService();
		private TaskAssignmentService taskService = new TaskAssignmentService();
		private PassportService passportService = new PassportService();
		private CourseService courseService = new CourseService();

	/**
   * <p>
   * It get the employee if exist.
   * </p>
   * @param employeeId is used for searching the employee from employee Data by employeeId.
   * @return The employee object if exist or null.
   */
  public Employee getEmployeeIfExist(int employeeId) throws DataBaseException {
    List<Object> employeeData = employeeDao.getEmployeeIfExist(employeeId);
		if(employeeData != null && employeeData.size() > 0  ) {
			Employee employee = (Employee) employeeData.get(0);
			try {
			  passportService.getPassportIfExist(employee);
			  taskService.getTaskIfExist(employee);
			  employee.setBranch(branchService.getBranchIfExist((int) employeeData.get(1)));
				
			} catch(DataBaseException | IndexOutOfBoundsException e) {
				return employee;
			}
		  return employee;
		} else {
			return null;
		}
  }
  
	/**
   * <p>
   * It creates the new employee object.
   * </p>
	 * @param name It specify the employee name.
	 * @param dob It specify the employee Date of Birth.
	 * @param mobileNumber It specify the employee mobileNumber.
	 * @param role It specify the employee role.
   * @param address It specify the employee address.
   * @return The Employee object.
   */
  public Employee addEmployee(String name, Date dob, String mobileNumber,
                                String role, String address, int branchId) throws DataBaseException {
    Employee employee = new Employee(0, name, dob, mobileNumber, role, address);
		Branch branch = branchService.getBranchIfExist(branchId);
		if(branch == null) {
			return null;
		}
		employee.setBranch(branch);
    return employeeDao.insertEmployee(employee);
  }
  
  public Employee getEmployee(int id) {
    return getEmployeeIfExist(id);
    
  }
  
	/**
   * <p>
   * It updates the employee name on reference.
   * </p>
   * @param employeeId is used for find the employee.
	 * @param name is used for update new name for the employee.
   * @return The employee object or null if employee doesn't exist.
   */
  public Employee updateEmployeeName(Employee employee, String name) {
    if (employee != null) {
      employee.setName(name);
      return employee;
    }
    return employee;
    
  }
  
	/**
   * <p>
   * It updates the employee date of birth on reference.
   * </p>
   * @param employeeId is used for find the employee.
	 * @param dob is used for update new date of birth for the employee.
   * @return The employee object or null if employee doesn't exist.
   */
  public Employee updateEmployeeDob(Employee employee, Date dob) {
    if (employee != null) {
      employee.setDob(dob);
      return employee;
    }
    return employee;  
  }
  
	/**
   * <p>
   * It updates the employee mobileNumber on reference.
   * </p>
   * @param employeeId is used for find the employee.
	 * @param mobileNumber is used for update new mobileNumber for the employee.
   * @return The employee object or null if employee doesn't exist.
   */
  public Employee updateEmployeeMobileNumber(Employee employee, String mobileNumber) {
    if (employee != null) {
      employee.setMobileNumber(mobileNumber);
      return employee;
    }
    return employee;
  }
  
	/**
   * <p>
   * It updates the employee role on reference.
   * </p>
   * @param employeeId is used for find the employee.
	 * @param role is used for update new role for the employee.
   * @return The employee object or null if employee doesn't exist.
   */
  public Employee updateEmployeeRole(Employee employee, String role) {
    if (employee != null) {
      employee.setRole(role);
      return employee;
    }
    return employee;
  }
  
	/**
   * <p>
   * It updates the employee address on reference.
   * </p>
   * @param employeeId is used for find the employee.
	 * @param address is used for update new address for the employee.
   * @return The employee object or null if employee doesn't exist.
   */
  public Employee updateEmployeeAddress(Employee employee, String address) {
    if (employee != null) {
      employee.setAddress(address);
      return employee;
    }
    return employee;  
  }
	
	public Employee updateEmployeeInDB(Employee employee) {
		return employeeDao.updateEmployee(employee);
		
	}
	
	public Employee updateEmployeeBranch(int employeeId, int branchId) {
		Employee employee = getEmployeeIfExist(employeeId);
		Branch branch = branchService.getBranchIfExist(branchId);
		if(employee != null && branch!= null) {
			employee.setBranch(branch);
		} else {
			return null;
		}
		return employeeDao.updateEmployeeBranch(employee);
	}
  
	/**
	 * <p>
   * Deletes the entire Employee.
	 * </p>
	 * @param id is used for find the specific employee.
   */
  public void removeEmployee(int employeeId) throws DataBaseException {
    employeeDao.deleteEmployee(employeeId);
  }
  
	/**
	 * <p>
   * In this method parsing the all employee primary data and return that
	 * </p>
	 * @return It return the all employee primary data as List of Maps.
	 */
  public List<Employee> getAllEmployee() throws DataBaseException {
    return employeeDao.fetchAllEmployee();
    
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
		  return branchService.bindBranch(employee, branch); 
    }
    return null;
  }
  
  /**
	* <p>
  * It handle the employee reference from the course.
	* </p>
	* @param employeeId is used for find the employee.
	* @return It return the updated course data if employee in course else null.
  */
  public Boolean removeEmployeeFromCourse(Employee employee) throws DataBaseException {
		employee.setCourseForDelete();
    return employeeDao.saveOrUpdate(employee);		
  }
	
	/**
	 * <p>
   * This method is for binding course with employee.
	 * </p>
	 * @param employee is used for find the employee.
	 * @param course is used for find the specific course.
	 * @return The number of column updated.
   */
  public int addCourseToEmployee(Employee employee, Course course) throws DataBaseException {
		
		employee.setCourse(course);
    return employeeDao.mapEmployeeWithCourse(employee);
  }
}