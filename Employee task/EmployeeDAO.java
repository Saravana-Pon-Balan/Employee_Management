import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EmployeeDAO {
	
  private static Map<String, Employee> employees = new HashMap<>();
  public Employee isEmployeeFound(String id) {
    if (employees.containsKey(id)) {
      return employees.get(id);
    } else {
      return null;
    }
  }
  
  public Employee insertEmployee(Employee employeeData) {
 
		String employeeId = employeeData.getId();
		employees.put(employeeId,employeeData);
		return employees.get(employeeId);
  }
  
  public Employee fetchEmployee(String id) {
    return employees.get(id);
  }
  
  public Employee deleteEmployee(String id) {
    if (employees.containsKey(id)) {
      return employees.remove(id);
    } else {
      return null;
    }
  }
  
  public List<Employee> fetchAllEmployee() {
    return new ArrayList<>(employees.values());
  }
  
  public Employee insertPassport(String employeeId, Employee employeeData) {
    if (!employees.containsKey(employeeId)) {
      employees.put(employeeId,employeeData);
      return employees.get(employeeId);
    } else {
      return employees.get(employeeId);
    }
  }
  
}