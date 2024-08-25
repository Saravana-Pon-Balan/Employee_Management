import java.text.SimpleDateFormat;  
import java.time.Period; 
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class TaskAssignmentService {
  private TaskAssignmentDAO TaskDao = new TaskAssignmentDAO();
  private EmployeeService employeeService = new EmployeeService();
	private int autoId = 0;
	
  public TaskAssignment isTaskFound(String id) {
    return TaskDao.isTaskFound(id);
  }
  
  public TaskAssignment addTask(Date givenDate, int durationInDays, 
                  String status, String employeeId) {
    Employee employee = employeeService.isEmployeeFound(employeeId);
    if(employee != null) {
			String taskId = Integer.toString(++autoId);
      TaskAssignment task = new TaskAssignment(taskId, givenDate, durationInDays, status, employee);
      TaskAssignment taskData = TaskDao.insertTask(task);
      employeeService.bindTask(employeeId, taskData);
      return taskData;
    } else {
      return null;
    }
    
  }
  
  public TaskAssignment updateGivenDate(String id, Date givenDate) {
    TaskAssignment updateTaskData = TaskDao.fetchTask(id);
    if (updateTaskData != null) {
      updateTaskData.setGivenDate(givenDate);
      return updateTaskData;
    }
    return updateTaskData;  
  }
  
  public TaskAssignment updateDuration(String id, int duration) {
    TaskAssignment updateTaskData = TaskDao.fetchTask(id);
    if (updateTaskData != null) {
      updateTaskData.setDuration(duration);
      return updateTaskData;
    }
    return updateTaskData;
  }
  
  public TaskAssignment updateStatus(String id, String status) {
    TaskAssignment updateTaskData = TaskDao.fetchTask(id);
    if (updateTaskData != null) {
      updateTaskData.setStatus(status);
      return updateTaskData;
    }
    return updateTaskData;
  }
  
  public TaskAssignment updateEmployee(String id, String newEmployeeId, String oldEmployeeId) {
    TaskAssignment updateTaskData = TaskDao.fetchTask(id);
    Employee newEmployee = employeeService.isEmployeeFound(newEmployeeId);
    Employee oldEmployee = employeeService.isEmployeeFound(oldEmployeeId);
    if(!oldEmployee.getTask().contains(updateTaskData)) {
      return null;
    }
    List<TaskAssignment> oldEmployeeTasks = oldEmployee.getTask();
    for(int i = 0; i < oldEmployeeTasks.size(); i++) {
      if(oldEmployeeTasks.get(i).equals(updateTaskData)) {
        System.out.println("task Found");
        oldEmployeeTasks.remove(i);
      }
    }
    if (updateTaskData != null) {
      updateTaskData.setEmployee(newEmployee);
      employeeService.bindTask(newEmployeeId, updateTaskData);
      return updateTaskData;
    } else {
      return null;
    }
    
    
  }
  
  public TaskAssignment removeTask(String id) {
    return TaskDao.deleteTask(id);
  }
  
  
}