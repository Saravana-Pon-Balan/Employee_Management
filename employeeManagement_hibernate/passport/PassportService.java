package employeeManagement.passport;

import java.util.Date;

import employeeManagement.employee.Employee;
import employeeManagement.exception.DataBaseException;

/**
 * <p>
 * Implementation to business logic for Passport.
 * </p>
 */

public class PassportService {
  private PassportDAO passportDao = new PassportDAO();
	
	/**
   * <p>
   * It get the Passport if exist.
   * </p>
   * @param id is used for searching the passport from passport Data by passportId.
   * @return The Passport object if exist or null.
   */
  public Passport getPassportIfExist(Employee employee) throws DataBaseException {
    return passportDao.getPassportIfExist(employee);
  }
  
	/**
   * <p>
   * It creates the new Passport object.
   * </p>
	 * @param employee It specify the employee.
	 * @param placeOfBirth It specify the placeOfBirth of employee.
	 * @param passportNumber It specify the passportNumber of employee.
	 * @param dateOfExpiry It specify the employee Date of Expiry.
   * @return The   object if employee exist or null.
   */
  public Passport addPassport(Employee employee, String placeOfBirth, 
                                String passportNumber, Date dateOfExpiry) throws DataBaseException {

    if(employee != null) {
      Passport passport = new Passport(0, employee, placeOfBirth, passportNumber, dateOfExpiry);
      Passport passportData =  passportDao.insertPassport(passport);  
      bindPassport(employee, passportData);
      return passportData;
    } else {
      return null;
    }
  }
  
	/**
   * <p>
   * It updates the employee place of birth on Passport reference.
   * </p>
   * @param passport is used for find the passport.
	 * @param placeOfBirth is used for update new place of birth for the employee.
   * @return The Passport object or null if passport doesn't exist.
   */
  public Passport updatePlaceOfBirth(Passport passport, String placeOfBirth) {
    passport.setPlaceOfBirth(placeOfBirth);
    return passport;
  }
  
	/**
   * <p>
   * It updates the employee passportNumber on Passport reference.
   * </p>
   * @param passport is used for find the passport.
	 * @param passportNumber is used for update new passportNumber for the employee.
   * @return The Passport object or null if passport doesn't exist.
   */
  public Passport updatePassportNumber(Passport passport, String passportNumber) {
    passport.setPassportNumber(passportNumber);
    return passport;
  }
  
	/**
   * <p>
   * It updates the passport Date of Expiry on Passport reference.
   * </p>
   * @param passport is used for find the passport.
	 * @param dateOfExpiry is used for update new dateOfExpiry for the passport.
   * @return The Passport object or null if passport doesn't exist.
   */
  public Passport updateDateOfExpiry(Passport passport, Date dateOfExpiry) {
    passport.setDateOfExpiry(dateOfExpiry);
    return passport;
  }
	
	/**
   * <p>
   * It updates the passport data in database.
   * </p>
   * @param passport is used for find the passport.
   */
	public Passport updatePassport(Passport passport) throws DataBaseException {
		return passportDao.updatePassport(passport);
	}
  
	/**
	 * <p>
   * Deletes the entire passport.
	 * </p>
	 * @param employee is used for find the specific passport of employee.
   */
  public void removePassport(Employee employee) throws DataBaseException {
    passportDao.deletePassport(employee.getPassport());
  }

  /**
	 * <p>
   * This method is for binding passport with employee.
	 * </p>
	 * @param employee is used for find the employee.
	 * @param passportId is used for find the specific passport.
	 * @return The updated passport data.
   */
  public Passport addPassportToEmployee(Employee employee, int passportId) throws DataBaseException {
    Passport passport = passportDao.getPassportIfExist(employee);
    bindPassport(employee, passport);
    return passport;
  }
  
	 public Employee bindPassport(Employee employee, Passport passport) {
    if (employee != null) {
      employee.setPassport(passport);
      return employee;
    }
    return null;
  }
}