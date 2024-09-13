package employeeManagement.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import employeeManagement.employee.Employee; 

/**
 * It is represents an TaskAssignment.
 * It associate with single employee object.
 */
@Entity
@Table(name = "task")
public class TaskAssignment {
	// one to many
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
	@Temporal(TemporalType.DATE)
	@Column(name = "given_date")
  private Date givenDate;
	@Column(name = "duration")
  private int durationInDays;
	// enum type in DB (inprogress, notstarted, completed, cancelled)
	@Column(name = "status")
  private String status;
	@ManyToOne
	@JoinColumn(name = "employee_id")
  private Employee employee;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
  public TaskAssignment() {}
  public TaskAssignment(int id, Date givenDate, int durationInDays,
                          String status, Employee employee) {
    this.id = id;
    this.givenDate = givenDate;
    this.durationInDays = durationInDays;
    this.status = status;
    this.employee = employee;
		this.isDeleted = false;
  }
  
  public int getId() {
    return this.id;
  }
	
	public void setId(int id) {
		this.id = id;
	}
  
  public void setGivenDate(Date date) {
    this.givenDate = date;
  }
  
  public Date getGivenDate() {
    return this.givenDate;
  }
  public void setDuration(int days) {
    this.durationInDays = days;
  }
  
  public int getDuration() {
    return this.durationInDays;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
  
  public Employee getEmployee() {
    return this.employee;
  }
  
  public void setIsDeleted(boolean isDeleted) {
	  this.isDeleted = isDeleted;
	}
	
	public boolean getIsDeleted() {
    return this.isDeleted;
	}
  public String toString() {
    return "\n\t\t{\n\t\tId: " + this.id + "\n\t\tGiven Date: " + this.givenDate + 
        "\n\t\tDuration: " + this.durationInDays + 
        "\n\t\tStatus: " + this.status + "\n\t\t}\n";
  }
}