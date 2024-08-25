import java.util.List;
import java.util.ArrayList;

/**

* This is Model class for Branch
* It have branchId, name, location and list of employees as attributes.

**/

class Branch {
  // many to one 
  private String branchId;
  private String name;
  private String location;
  private List<Employee> employees;

  public Branch(String id, String name, String location) {
    this.branchId = id;
    this.name = name;
    this.location = location;
    this.employees = new ArrayList<>();
  }

  public String getId() {
    return this.branchId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return this.location;
  }
  
  public void setEmployee(Employee employee) {
    this.employees.add(employee);
  }
  
  public List<Employee> getEmployee() {
    return this.employees;
  }
  
  public String toString() {
    return "{\n\t\tId: " + this.branchId + "\n\t\tName: " + this.name + 
        "\n\t\tLocation: " + this.location + 
        "\n\t\tEmployee: " + this.employees + "\n}";
  }

}
