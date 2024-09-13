package employeeManagement.task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import employeeManagement.employee.EmployeeService;
import employeeManagement.employee.Employee;
import employeeManagement.exception.DataBaseException;
import employeeManagement.util.DateUtil;

public class TaskAssignmentController {
	
  private Scanner scanner = new Scanner(System.in);
  private TaskAssignmentService taskService = new TaskAssignmentService();
  private EmployeeService employeeService = new EmployeeService();
	
	/**
   * <p>
   * It creates the new Task.
   * </p>
   * @param employee is used for initially bind specified employee to Task.
   * @return The TaskAssignment object if added or null.
   */
  public TaskAssignment addTaskDetails(Employee employee) {
    Date givenDate = new Date();
    System.out.println("Enter total time to complete this task (in days):  ");
    int duration = -1;
		do{
		  try {
				duration = scanner.nextInt();
		  } catch(InputMismatchException e) {
		    scanner.nextLine();
			  System.out.println("\nYou entered not a integer");	
		  }
		} while(duration == -1);
		scanner.nextLine();
		
    System.out.println("Enter Task Status: ");
    String status = scanner.nextLine();
		try {
		  TaskAssignment task = taskService.addTask(givenDate, duration, status, employee);
      if (task != null) {
        System.out.println("\nTask added successfuly\n");
			  return task;
      } else {
        System.out.println("\nSomething went wrong\n");
			  return null;
      }
		} catch(DataBaseException e) {
			System.out.println(e.getMessage());
			return null;
		}
  }
  
  /**
   * <p>
   * Its for update the Task details if exist on that employee 
	   or create new task and assign a task from one employee to another employee if exist.
   * </p>
   * @param employee is used for get that employee's Tasks and edit that or assign to other employee.
   */
  public void updateTaskDetails(Employee employee) {
    int taskId = -1;
		List<Integer> taskIds = new ArrayList<>();
		try {
		  for(TaskAssignment task : taskService.getTaskIfExist(employee)) {
			  taskIds.add(task.getId());
			  System.out.println(task);
	  	}
      System.out.println("Enter Task id from above to update details: ");
			do{
				try {
					taskId = scanner.nextInt();
				} catch(InputMismatchException e) {
					scanner.nextLine();
					System.out.println("\nYou entered not a integer");	
				}
			} while(taskId == -1);
		  scanner.nextLine();
			
		  if(!taskIds.contains(taskId)) {
			  System.out.println("\nCreating new task\n");
			  addTaskDetails(employee);
		  } else {
			  TaskAssignment task = taskService.getTask(taskId, employee);
        System.out.println("\nwhat are the fields you want to update\n");
        System.out.println("1) Given Task Date\n2) Duration of Task\n"
                            + "3) Status \n4) Change task to other Employee\n"
                            + "\n(give field numbers separated by single space)");
        String[] fieldNumbers = scanner.nextLine().split(" ");
        for (String s : fieldNumbers) {
          switch(Integer.parseInt(s)) {
            case 1:
              System.out.println("Enter Task Date you want to update: ");
							Date givenDate = null;
							while(givenDate == null) {
							  try {
                  givenDate = DateUtil.strToDate(scanner.nextLine());
                } catch(ParseException e) {
								  System.out.println(e.getMessage() + " enter the valid Date");
							  }
							}
							taskService.updateGivenDate(task, givenDate);
              break;
            case 2:
              System.out.println("Enter Duration you want to update: ");
              int duration = -1;
							do{
								try {
									duration = scanner.nextInt();
								} catch(InputMismatchException e) {
									scanner.nextLine();
									System.out.println("\nYou entered not a integer");	
								}
							} while(duration == -1);
              taskService.updateDuration(task, duration);
              break;
            case 3:
              System.out.println("Enter Status you want to update: ");
              String status = scanner.nextLine();
              taskService.updateStatus(task, status);
              break;
            case 4:
              System.out.println("Enter Employee id to move task: ");
              int newEmployeeId = -1;
							do{
								try {
									newEmployeeId = scanner.nextInt();
								} catch(InputMismatchException e) {
									scanner.nextLine();
									System.out.println("\nYou entered not a integer");	
								}
							} while(newEmployeeId == -1);
		          scanner.nextLine();
							
						  Employee newEmployee = employeeService.getEmployeeIfExist(newEmployeeId);
              taskService.updateEmployee(task, newEmployee);
              break;
            default:
              System.out.println("Choose correct field");    
          }
        }
			  taskService.updateTask(task);
        System.out.println("\nTask data updated successfully\n");
      }
		} catch (DataBaseException e) {
			System.out.println(e.getMessage());
		}
  }
	
	/**
	 * <p>
   * Get input for delete operation.
	 * </p>
	 * @param employee is used for find the employee.
   */
  public void removeTask(TaskAssignment task, Employee employee) {
		try {
      if (taskService.removeTask(task)) {
        System.out.println("\nSuccessfuly deleted the Task details\n");
      } else {
        System.out.println("Something went wrong");
      }
		} catch(DataBaseException e) {
			System.out.println(e.getMessage());
		}
  }
  
  
  
}