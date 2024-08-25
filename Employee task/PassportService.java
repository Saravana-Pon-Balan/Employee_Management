import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.time.Period; 
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PassportService {
  private PassportDAO passportDao = new PassportDAO();
  private EmployeeService employeeService = new EmployeeService();
	private static int autoId = 0;
	
  public Passport isPassportFound(String id) {
    return passportDao.isPassportFound(id);
  }
  
  public Passport addPassport(String employeeId, String placeOfBirth, 
                                String passportNumber, Date dateOfExpiry) {
    Employee employee = employeeService.isEmployeeFound(employeeId);

    if(employee != null) {
			String passportId = "" + ++autoId;
      Passport passport = new Passport(passportId, employee, placeOfBirth, passportNumber, dateOfExpiry);
      Passport passportData =  passportDao.insertPassport(passport);  
      employeeService.bindPassport(employeeId, passportData);
      return passportData;
    } else {
      return null;
    }
  }
  
  public Map<String, Object> getPassport(String id) {
    Passport passportData = passportDao.fetchPassport(id);
    Map<String, Object> passportDataMap = new LinkedHashMap<>();
    passportDataMap.put("Id", passportData.getId());
    passportDataMap.put("Employee", passportData.getEmployee());
    passportDataMap.put("placeOfBirth", passportData.getPlaceOfBirth());
    passportDataMap.put("passportNumber", passportData.getPassportNumber());
    passportDataMap.put("dateOfExpiry", passportData.getDateOfExpiry());
    return passportDataMap;
  }
  
  public Passport updateEmployee(String id, String employeeId) {
    Passport updatePassportData = passportDao.fetchPassport(id);
    Employee employee = employeeService.isEmployeeFound(employeeId);
    if (updatePassportData != null) {
      updatePassportData.setEmployee(employee);
      employeeService.bindPassport(employeeId, updatePassportData);
      return updatePassportData;
    }
    return updatePassportData;
    
  }
  
  public Passport updatePlaceOfBirth(String id, String placeOfBirth) {
    Passport updatePassportData = passportDao.fetchPassport(id);
    if (updatePassportData != null) {
      updatePassportData.setPlaceOfBirth(placeOfBirth);
      return updatePassportData;
    }
    return updatePassportData;  
  }
  
  public Passport updatePassportNumber(String id, String passportNumber) {
    Passport updatePassportData = passportDao.fetchPassport(id);
    if (updatePassportData != null) {
      updatePassportData.setPassportNumber(passportNumber);
      return updatePassportData;
    }
    return updatePassportData;
  }
  
  public Passport updateDateOfExpiry(String id, Date dateOfExpiry) {
    Passport updatePassportData = passportDao.fetchPassport(id);
    if (updatePassportData != null) {
      updatePassportData.setDateOfExpiry(dateOfExpiry);
      return updatePassportData;
    }
    return updatePassportData;
  }
  
  public Passport removePassport(String id, String employeeId) {
    employeeService.isEmployeeFound(employeeId).setPassport(null);
    return passportDao.deletePassport(id);
  }
  
  public Date parseDate(String date) {
    
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
      return null;
        }
  }
  
  public List<Map<String, Object>> getAllPassport() {
    
    List<Passport> passports = new ArrayList<>(passportDao.fetchAllPassport());
    List<Map<String, Object>> passportData = new ArrayList<>();
    Map<String, Object> singlePassport = new LinkedHashMap<>();
        for (Passport passport : passports) {
            singlePassport.put("Id: ", passport.getId());
      singlePassport.put("Employee", passport.getEmployee());
      singlePassport.put("placeOfBirth", passport.getPlaceOfBirth());
      singlePassport.put("passportNumber", passport.getPassportNumber());
      singlePassport.put("dateOfExpiry", passport.getDateOfExpiry());
      passportData.add(new LinkedHashMap<String, Object>(singlePassport));
      singlePassport.clear();
        }
    return passportData;
  }
  
  public Passport addPassportToEmployee(String employeeId, String passportId) {
    Passport passport = passportDao.isPassportFound(passportId);
    employeeService.bindPassport(employeeId, passport);
    return passport;
  }
  
}