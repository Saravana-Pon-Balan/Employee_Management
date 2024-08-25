import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

class TaskAssignmentDAO {
	
  private static Map<String, TaskAssignment> tasks = new HashMap<>();
  
  public TaskAssignment isTaskFound(String id) {
    if (tasks.containsKey(id)) {
      return tasks.get(id);
    } else {
      this.TaskId = id;
      return tasks.get(id);
    }
  }
  
  public TaskAssignment insertTask(TaskAssignment taskData) {
		String taskId = taskData.getId();
		tasks.put(taskId, taskData);
		return tasks.get(taskId);
  }
  
  public TaskAssignment fetchTask(String id) {
    return tasks.get(id);
  }
  
  public TaskAssignment deleteTask(String id) {
    if (tasks.containsKey(id)) {
      return tasks.remove(id);
    } else {
      return null;
    }
  }
  public List<TaskAssignment> fetchAllTask() {
    return new ArrayList<>(tasks.values());
  }
  
}  