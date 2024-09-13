package employeeManagement.task;

import java.util.Date;
import java.util.List;

import employeeManagement.employee.Employee;
import employeeManagement.exception.DataBaseException;

/**
 * <p>
 * Implementation to business logic for TaskAssignment.
 * </p>
 */
 
public class TaskAssignmentService {
  private TaskAssignmentDAO taskDao = new TaskAssignmentDAO();
	
	/**
   * <p>
   * It get the Task if exist.
   * </p>
   * @param employee is used for searching the task from task Data by employee.
   * @return The Task object if exist or null.
   */
  public List<TaskAssignment> getTaskIfExist(Employee employee) throws DataBaseException {
    return taskDao.getTaskIfExist(employee);
  }
  
	
	/**
   * <p>
   * It get the Task if exist by task id.
   * </p>
   * @param taskId is used for searching the task from task Data by taskId.
	 * @param employee is used for search task that along with the employee
   * @return The Task object if exist or null.
   */
	public TaskAssignment getTask(int taskId, Employee employee) throws DataBaseException {
		return taskDao.fetchTask(taskId, employee);
	}
	
	/**
   * <p>
   * It creates the new TaskAssignment object.
   * </p>
	 * @param givenDate It specify the task assignment Date.
	 * @param durationInDays It specify the durationInDays of task.
	 * @param status It specify the status of task.
	 * @param employee It specify the employee.
	 * @return The TaskAssignment object.
   */
  public TaskAssignment addTask(Date givenDate, int durationInDays, 
                  String status, Employee employee) throws DataBaseException {
    if(employee != null) {
      TaskAssignment task = new TaskAssignment(0, givenDate, durationInDays, status, employee);
      TaskAssignment taskData = taskDao.insertTask(task);
      bindTask(employee, taskData);
      return taskData;
    } else {
      return null;
    } 
  }
  
	/**
   * <p>
   * It updates the task given date on reference.
   * </p>
   * @param task is used for find the task.
	 * @param givenDate is used for update new givenDate for the Task.
   * @return The task object.
   */
  public TaskAssignment updateGivenDate(TaskAssignment task, Date givenDate) {
    task.setGivenDate(givenDate);
		return task;
      
  }
  
	/**
   * <p>
   * It updates the Task name on reference.
   * </p>
   * @param task is used for find the Task.
	 * @param duration is used for update new duration for the Task.
   * @return The Task object.
   */
  public TaskAssignment updateDuration(TaskAssignment task, int duration) {
    task.setDuration(duration);
    return task;
  }
  
	/**
   * <p>
   * It updates the Task name on reference.
   * </p>
   * @param task is used for find the Task.
	 * @param status is used for update new status for the Task.
   * @return The Task object.
   */
  public TaskAssignment updateStatus(TaskAssignment task, String status) {
    task.setStatus(status);
		return task;
  }
  
	/**
   * <p>
   * It remove the employee from old Task and add to new Task.
   * </p>
   * @param branchId is used for find the Task.
	 * @param newEmployeeId is used for get new employee details.
	 * @param oldEmployeeId is used for get old employee details.
   * @return The Task object or null if Task doesn't exist.
   */
  public TaskAssignment updateEmployee(TaskAssignment task, Employee newEmployee) {
    if (task != null) {
      task.setEmployee(newEmployee);
      return task;
    } else {
      return null;
    }
    
    
  }
	
	/**
   * <p>
   * It updates the Task details in database.
   * </p>
   * @param task is used for find the Task in database.
   */
	public void updateTask(TaskAssignment task) throws DataBaseException {
		taskDao.updateTask(task);
	}
  
	/**
	 * <p>
   * Deletes the entire Task.
	 * </p>
	 * @param task is used for find the specific task.
	 * @return The true if task deleted otherwise false.
   */
  public Boolean removeTask(TaskAssignment task) throws DataBaseException {
    return taskDao.deleteTask(task);
  }
  
  /**
	 * <p>
   * It bind the task with employee on reference.
	 * </p>
	 * @param employee is used for find the specific employee.
	 * @param task is used for set that to employee.
	 * @return Employee object if doesn't exist.
   */
	public Employee bindTask(Employee employee, TaskAssignment task) {
    if (employee != null) {
      employee.setTask(task);
      return employee;
    }
    return null;
  }
}