import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class PassportController {
	
  private static Scanner scanner = new Scanner(System.in);
  private static PassportService service = new PassportService();
  private EmployeeService employeeService = new EmployeeService();

  public void addPassportDetails(String employeeId) {
      
    
    System.out.println("Enter Place of Birth: ");
    String placeOfBirth = scanner.nextLine();
    System.out.println("Enter Passport Number: ");
    String passportNumber = scanner.nextLine();
    System.out.println("Enter Passport expiry date: (yyyy-mm-dd)");
    Date expiryDate = Util.strToDate(scanner.nextLine());
    
    if (service.addPassport(employeeId, placeOfBirth, passportNumber, expiryDate) != null) {
      System.out.println("\nPassport added successfuly\n");
    } else {
      System.out.println("\nSomething went wrong\n");
    }
  }
  
  public void updatePassportDetails(String employeeId) {
    String passportId;
    Passport passport = employeeService.isEmployeeFound(employeeId).getPassport();
    if(passport != null) {
      passportId = passport.getId();
			
      System.out.println("\nwhat are the fields you want to update\n");
      System.out.println("1) Delete Passport\n2) placeOfBirth\n"
                + "3) passport Number\n4) Expiry Date\n"
                + "\n(give field numbers separated by single space)");
      String[] fieldNumbers = scanner.nextLine().split(" ");
      for (String s : fieldNumbers) {
        switch(Integer.parseInt(s)) {
          case 1:
            service.removePassport(passportId, employeeId);
            return;
          case 2:
            System.out.println("Enter place of Birth you want to update: ");
            String placeOfBirth = scanner.nextLine();
            service.updatePlaceOfBirth(passportId, placeOfBirth);
            break;
          case 3:
            System.out.println("Enter passport Number you want to update: ");
            String updatePassportNumber = scanner.nextLine();
            service.updatePassportNumber(passportId, updatePassportNumber);
            break;
          case 4:
            System.out.println("Enter Expiry Date you want to update: ");
            Date parsedExpiryDate = service.parseDate(scanner.nextLine());
            service.updateDateOfExpiry(passportId, parsedExpiryDate);
            break;
          
          default:
            System.out.println("Choose correct field");
          
        }
      }
      System.out.println("\nPassport data updated successfully\n");
    } else {
      System.out.println("Adding new passport");
      addPassportDetails(employeeId);
    }
      
  }
  public void removePassport(String employeeId) {
    String deleteId = null;
    Passport passport = employeeService.isEmployeeFound(employeeId).getPassport();
    if(passport != null) {
      deleteId = passport.getId();
    } else {
      System.out.println("\nThis employee don't have passport\n");
    }
    if (service.isPassportFound(deleteId)!= null) {
      if (service.removePassport(deleteId, employeeId) != null) {
        System.out.println("\nSuccessfuly deleted the Passport details\n");
      } else {
        System.out.println("Something went wrong");
      }
    }
    else
      System.out.println("\nGiven id not found\n");
  }
  
  
  
}