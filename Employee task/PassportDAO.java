import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

class PassportDAO {
	
  private static Map<String, Passport> passports = new HashMap<>();
  private String passportId;
  
  public Passport isPassportFound(String id) {
    if (passports.containsKey(id)) {
      return passports.get(id);
    } else {
      this.passportId = id;
      return passports.get(id);
    }
  }
  
  public Passport insertPassport(Passport passportData) {
		String passportId = passportData.getId();
		passports.put(passportId,passportData);
		return passports.get(passportId);
  }
  
  public Passport fetchPassport(String id) {
    return passports.get(id);
  }
  
  public Passport deletePassport(String id) {
    if (passports.containsKey(id)) {
      return passports.remove(id);
    } else {
      return null;
    }
  }
  
  public List<Passport> fetchAllPassport() {
    return new ArrayList<>(passports.values());
  }
  
}  