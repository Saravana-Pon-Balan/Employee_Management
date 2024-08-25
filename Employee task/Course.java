import java.util.ArrayList;
import java.util.List;

class Course {
	// many to many
  private String courseId;
  private String name;
  private String description;
  private List<Employee> employees;
  
  public Course(String id, String name, String description) {
    this.courseId = id;
    this.name = name;
    this.description = description;
    this.employees = new ArrayList<>();
  }
  
  public String getId() {
    return this.courseId;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setEmployee(Employee employee) {
    this.employees.add(employee);
  }
  
  public List<Employee> getEmployee() {
    return this.employees;
  }
  
  public String toString() {
    return "{\n\t\tId: " + this.courseId + "\n\t\tName: " + this.name + 
        "\n\t\tDescription: " + this.description + 
        "\n\t\tEmployee: " + this.employees + "\n}";
  }
  
  
  
  
  
}

