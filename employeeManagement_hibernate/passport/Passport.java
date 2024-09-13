package employeeManagement.passport;

import java.util.Date;

import employeeManagement.employee.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * It is represents an passport.
 * It associate with one employee.
 */
 
@Entity
@Table(name = "passport")
public class Passport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
	@OneToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;
	@Column(name = "place_of_birth")
  private String placeOfBirth;
	@Column(name = "passport_number")
  private String passportNumber;
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_expiry")
  private Date dateOfExpiry;

	
	public Passport() {}
  public Passport(int id, Employee employee, String placeOfBirth, 
	                  String passportNumber, Date dateOfExpiry) {
    this.id = id;
    this.employee = employee;
    this.placeOfBirth = placeOfBirth;
    this.passportNumber = passportNumber;
    this.dateOfExpiry = dateOfExpiry;
  }  
  
  public int getId() {
    return this.id;
  }
	
	public void setId(int id) {
		this.id = id;
	}
  
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
  
  public Employee getEmployee() {
    return this.employee;
  }
  
  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }
  
  public String getPlaceOfBirth() {
    return this.placeOfBirth;
  }
  
  public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
  }
  
  public String getPassportNumber() {
    return this.passportNumber;
  }
  
  public void setDateOfExpiry(Date dateOfExpiry) {
    this.dateOfExpiry = dateOfExpiry;
  }
  
  public Date getDateOfExpiry() {
    return this.dateOfExpiry;
  }
	
  public String toString() {
    return "\n\t\t{\n\t\tId: " + this.id + "\n\t\tPlace of Birth: " + this.placeOfBirth + 
        "\n\t\tPassport Number: " + this.passportNumber + 
        "\n\t\tDate of Expiry: " + this.dateOfExpiry + "\n\t\t}\n";
  }
}
