import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class TaskAssignmentController {
	
  private Scanner scanner = new Scanner(System.in);
  private TaskAssignmentService service = new TaskAssignmentService();
  private EmployeeService employeeService = new EmployeeService();
	
  public TaskAssignment addTaskDetails(String employeeId) {
    Date givenDate = new Date();
    System.out.println("Enter total time to complete this task (in days):  ");
    int Duration = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Enter Task Status: ");
    String status = scanner.nextLine();
		TaskAssignment task = service.addTask(givenDate, Duration, status, employeeId);
    if (task != null) {
      System.out.println("\nTask added successfuly\n");
			return task;
    } else {
      System.out.println("\nSomething went wrong\n");
			return null;
    }
  }
  
  
  public TaskAssignment updateTaskDetails(String employeeId) {
    
    String taskId;
		TaskAssignment task;
    System.out.println(employeeService.isEmployeeFound(employeeId).getTask());
    System.out.println("Enter Task id from above to update details: ");
    taskId = scanner.nextLine();
		if(!employeeService.isEmployeeFound(employeeId).getTask().contains(service.isTaskFound(taskId))) {
			System.out.println("\nCreating new task\n");
			return addTaskDetails(employeeId);
		} else {
			task = service.isTaskFound(taskId);
		}
    if(task != null) {
      taskId = service.isTaskFound(taskId).getId();
      System.out.println("\nwhat are the fields you want to update\n");
      System.out.println("1) Given Task Date\n2) Duration of Task\n"
                + "3) Status \n4) Change task to other Employee\n"
                + "\n(give field numbers separated by single space)");
      String[] fieldNumbers = scanner.nextLine().split(" ");
      for (String s : fieldNumbers) {
        switch(Integer.parseInt(s)) {
          case 1:
            System.out.println("Enter Task Date you want to update: ");
            Date givenDate = Util.strToDate(scanner.nextLine());
            service.updateGivenDate(taskId, givenDate);
            break;
          case 2:
            System.out.println("Enter Duration you want to update: ");
            int duration = scanner.nextInt();
            scanner.nextLine();
            service.updateDuration(taskId, duration);
            break;
          case 3:
            System.out.println("Enter Status you want to update: ");
            String status = scanner.nextLine();
            service.updateStatus(taskId, status);
            break;
          case 4:
            System.out.println("Enter Employee id to move task: ");
            String newEmployeeId = scanner.nextLine();
            service.updateEmployee(taskId, newEmployeeId, employeeId);
            break;
          default:
            System.out.println("Choose correct field");
          
        }
      }
      System.out.println("\nTask data updated successfully\n");
			return service.isTaskFound(taskId);
    } else {
      return addTaskDetails(employeeId);
    }
  }
  public void removeTask(String deleteId) {
    if (service.isTaskFound(deleteId)!= null) {
      if (service.removeTask(deleteId) != null) {
        System.out.println("\nSuccessfuly deleted the Task details\n");
      } else {
        System.out.println("Something went wrong");
      }
    }
    else
      System.out.println("\nGiven id not found\n");
  }
  
  
  
}