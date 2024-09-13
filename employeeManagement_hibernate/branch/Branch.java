package employeeManagement.branch;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import employeeManagement.employee.Employee;

/**
 * It is represents an Branch.
 * one branch is associate with multiple employees.
 */
@Entity
@Table(name = "branch")
public class Branch {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
	@Column(name = "name")
  private String name;
	@Column(name = "location")
  private String location;
	@OneToMany(mappedBy = "branch")
  private Set<Employee> employees;

  public Branch() {}
  public Branch(int id, String name, String location) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.employees = new HashSet<>();
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

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return this.location;
  }
  
  public void setEmployee(Employee employee) {
    this.employees.add(employee);
  }
  
  public Set<Employee> getEmployee() {
    return this.employees;
  }
  
  public String toString() {
    return "\n\t\t{\n\t\tId: " + this.id + "\n\t\tName: " + this.name + 
              "\n\t\tLocation: " + this.location + "\n\t\t}";
  }

}
