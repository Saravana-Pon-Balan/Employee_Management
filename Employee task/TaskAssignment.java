import java.util.Date;

class TaskAssignment {
	// one to many
  private String taskId;
  private Date givenDate;
  private int durationInDays;
  private String status;
  private Employee employee;
  
  public TaskAssignment(String id, Date givenDate, int durationInDays,
                          String status, Employee employee) {
    this.taskId = id;
    this.givenDate = givenDate;
    this.durationInDays = durationInDays;
    this.status = status;
    this.employee = employee;
  }
  
  public String getId() {
    return this.taskId;
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
  
  public String toString() {
    return "{\n\t\tId: " + this.taskId + "\n\t\tGiven Date: " + this.givenDate + 
        "\n\t\tDuration: " + this.durationInDays + 
        "\n\t\tStatus: " + this.status + 
        "\n\t\tEmployee: " + this.employee + "\n}";
  }
}