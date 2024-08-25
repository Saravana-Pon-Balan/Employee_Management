import java.util.Date;

class Passport {
	// one to one
  private String passportId;
  private Employee employee;
  private String placeOfBirth;
  private String passportNumber;
  private Date dateOfExpiry;
  
  public Passport(String id, Employee employee, String placeOfBirth, 
	                  String passportNumber, Date dateOfExpiry) {
    this.passportId = id;
    this.employee = employee;
    this.placeOfBirth = placeOfBirth;
    this.passportNumber = passportNumber;
    this.dateOfExpiry = dateOfExpiry;
  }  
  
  public String getId() {
    return this.passportId;
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
    return "{\n\t\tId: " + this.passportId + "\n\t\tEmployee: " + this.employee + 
        "\n\t\tPlace of Birth: " + this.placeOfBirth + 
        "\n\t\tPassport Number: " + this.passportNumber + 
        "\n\t\tDate of Expiry: " + this.dateOfExpiry + "\n}";
  }
  
}
