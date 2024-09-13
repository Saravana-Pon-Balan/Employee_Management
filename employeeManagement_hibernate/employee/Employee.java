package employeeManagement.employee;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import employeeManagement.passport.Passport;
import employeeManagement.branch.Branch;
import employeeManagement.task.TaskAssignment;
import employeeManagement.course.Course;
import employeeManagement.util.DateUtil;

/**
 * It is represents an employee.
 */
@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
  private int id;
	
	@Column(name = "name")
  private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "mobile_number")
  private String mobileNumber;
	
	@Column(name = "role")
  private String role;
	
	@Column(name = "address")	
  private String address;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
  @OneToOne(mappedBy = "employee")
  private Passport passport;
	
  @ManyToOne
	@JoinColumn(name = "branch_id")
  private Branch branch;
	
	@OneToMany(mappedBy = "employee")
  private Set<TaskAssignment> tasks;  
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "employee_course_mapper", 
    joinColumns = { @JoinColumn(name = "employee_id") }, 
    inverseJoinColumns = { @JoinColumn(name = "course_id") }
  )
  private Set<Course> courses;
	
	public Employee() {}
 
	public Employee(int id, String name, Date dob, String mobileNumber, String role, String address) {
    this.id = id;
    this.name = name;
    this.dob = dob;
    this.mobileNumber = mobileNumber;
    this.role = role;
    this.address = address;
		this.isDeleted = false;
    this.tasks = new HashSet<>();
    this.courses = new HashSet<>();
  }
  
  public int getId() {
    return this.id;
  }
	
	public void setId(int id) {
		this.id = id;
	}
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Date getDob() {
    return this.dob;
  }
  
  public void setDob(Date dob) {
    this.dob = dob;
  }
  
  public String getMobileNumber() {
    return this.mobileNumber;
  }
  
  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }
  
  public String getRole() {
    return this.role;
  }
  
  public void setRole(String role) {
    this.role = role;
  }
  
  public String getAddress() {
    return this.address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
	
  public void setPassport(Passport passport) {
    this.passport = passport;
  }
  
  public Passport getPassport() {
    return this.passport;
  }
	
  public void setBranch(Branch branch) {
    this.branch = branch;
  }
  
  public Branch getBranch() {
    return this.branch;
  }

  public void setTask(TaskAssignment task) {
    this.tasks.add(task);
  }
  
  public Set<TaskAssignment> getTask() {
    return this.tasks;
  }
  
  public void setCourse(Course courses) {
    this.courses.add(courses);
  }
  
  public Set<Course> getCourse() {
    return this.courses;
  }
	
	public void setCourseForDelete() {
		this.courses = null;
	}
	
	public String toString() {
    return "\nId: " + this.id + "\nName: " + this.name + 
        "\ndob: " + this.dob + 
				"\nAge: " + DateUtil.findDifferenceOfDate(this.dob, new Date()) +
				"\nMobile Number " + this.mobileNumber +
				"\nRole: " + this.role + 
				"\nAddress: " + this.address + "\n";
  }
}