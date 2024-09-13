package employeeManagement.course;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import employeeManagement.employee.Employee;

/**
 * It is represents an course.
 * One course is associate with multiple employee.
 */
@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
	
	@Column(name = "name")
  private String name;
	
	@Column(name = "description")
  private String description;
	
	@ManyToMany(mappedBy = "courses")
  private List<Employee> employees;
  
	public Course() {}
	
  public Course(int id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.employees = new ArrayList<>();
  }
  
  public int getId() {
    return this.id;
  }
  
	public void setId(int id) {
		this.id = id;
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
    return "\n\t\t{\n\t\tId: " + this.id + "\n\t\tName: " + this.name + 
        "\n\t\tDescription: " + this.description + "\n";
  }
  
  
  
  
  
}

