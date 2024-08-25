import java.text.SimpleDateFormat;  
import java.time.Period; 
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {
  private EmployeeDAO employeeDao = new EmployeeDAO();
  private static int autoId = 0;
  
  public Employee isEmployeeFound(String id) {
    return employeeDao.isEmployeeFound(id);
  }
  
  public Employee addEmployee(String name, Date dob, String mobileNumber,
                                String role, String address) {
	  String id = Integer.toString(++autoId);
    Employee employee = new Employee(id, name, dob, mobileNumber, role, address);
    return employeeDao.insertEmployee(employee);
  }
  
  public Map<String, Object> getEmployee(String id) {
    Employee employeeData = employeeDao.fetchEmployee(id);
    Map<String, Object> employeeDataAsMap = new LinkedHashMap<>();
    employeeDataAsMap.put("Id", employeeData.getId());
    employeeDataAsMap.put("Name", employeeData.getName());
    employeeDataAsMap.put("Dob", employeeData.getDob());
    employeeDataAsMap.put("Age:", Util.findDifferenceOfDate(employeeData.getDob(), new Date()));
    employeeDataAsMap.put("MobileNumber", employeeData.getMobileNumber());
    employeeDataAsMap.put("Role", employeeData.getRole());
    employeeDataAsMap.put("Address", employeeData.getAddress());
    employeeDataAsMap.put("Passport", employeeData.getPassport());
    employeeDataAsMap.put("Task", employeeData.getTask());
    employeeDataAsMap.put("Branch", employeeData.getBranch());
    employeeDataAsMap.put("Course", employeeData.getCourse());
    return employeeDataAsMap;
  }
  
  public Employee updateEmployeeName(String id, String name) {
    Employee updateEmployeeData = employeeDao.fetchEmployee(id);
    if (updateEmployeeData != null) {
      updateEmployeeData.setName(name);
      return updateEmployeeData;
    }
    return updateEmployeeData;
    
  }
  
  public Employee updateEmployeeDob(String id, Date dob) {
    Employee updateEmployeeData = employeeDao.fetchEmployee(id);
    if (updateEmployeeData != null) {
      updateEmployeeData.setDob(dob);
      return updateEmployeeData;
    }
    return updateEmployeeData;  
  }
  
  public Employee updateEmployeeMobileNumber(String id, String mobileNumber) {
    Employee updateEmployeeData = employeeDao.fetchEmployee(id);
    if (updateEmployeeData != null) {
      updateEmployeeData.setMobileNumber(mobileNumber);
      return updateEmployeeData;
    }
    return updateEmployeeData;
  }
  
  public Employee updateEmployeeRole(String id, String role) {
    Employee updateEmployeeData = employeeDao.fetchEmployee(id);
    if (updateEmployeeData != null) {
      updateEmployeeData.setRole(role);
      return updateEmployeeData;
    }
    return updateEmployeeData;
  }
  
  public Employee updateEmployeeAddress(String id, String address) {
    Employee updateEmployeeData = employeeDao.fetchEmployee(id);
    if (updateEmployeeData != null) {
      updateEmployeeData.setAddress(address);
      return updateEmployeeData;
    }
    return updateEmployeeData;  
  }
  
  public Employee removeEmployee(String id) {
    Employee employee = employeeDao.deleteEmployee(id);
    return employee;
    
  }
  
  public List<Map<String, Object>> getAllEmployee() {
    
    List<Employee> employeeList = new ArrayList<>(employeeDao.fetchAllEmployee());
    List<Map<String, Object>> employeeData = new ArrayList<>();
    Map<String, Object> singleEmployee = new LinkedHashMap<>();
        for (Employee employee : employeeList) {
            singleEmployee.put("Id: ", employee.getId());
      singleEmployee.put("Name: ", employee.getName());
      singleEmployee.put("DOB: ", employee.getDob());
      singleEmployee.put("Age: ", Util.findDifferenceOfDate(employee.getDob()));
      singleEmployee.put("MobileNumber", employee.getMobileNumber());
      singleEmployee.put("Role", employee.getRole());
      singleEmployee.put("Address", employee.getAddress());
      singleEmployee.put("passport", employee.getPassport());
      singleEmployee.put("Task", employee.getTask());
      singleEmployee.put("Branch", employee.getBranch());
      singleEmployee.put("Course", employee.getCourse());
      employeeData.add(new LinkedHashMap<String, Object>(singleEmployee));
      singleEmployee.clear();
        }
    return employeeData;
  }
  
  public Employee bindPassport(String employeeId, Passport passport) {
    Employee employee = employeeDao.fetchEmployee(employeeId);
    if (employee != null) {
      employee.setPassport(passport);
      return employee;
    }
    return null;
  }
  
  public Employee bindTask(String employeeId, TaskAssignment task) {
    Employee employee = employeeDao.fetchEmployee(employeeId);
    if (employee != null) {
      employee.setTask(task);
      return employee;
    }
    return null;
  }
  
  public Employee bindBranch(String employeeId, Branch branch) {
    Employee employee = employeeDao.fetchEmployee(employeeId);
    if (employee != null) {
      employee.setBranch(branch);
			branch.setEmployee(employee);
      return employee;
    }
    return null;
  }
  
  public Employee bindCourse(String employeeId, Course course) {
    Employee employee = employeeDao.fetchEmployee(employeeId);
    if (employee != null) {
      employee.setCourse(course);
      return employee;
    }
    return null;
  }
}