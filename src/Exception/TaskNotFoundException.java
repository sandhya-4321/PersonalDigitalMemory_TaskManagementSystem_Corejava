package Exception;

public class TaskNotFoundException extends Exception {
public TaskNotFoundException(int id) { 
	super("Task with ID " + id + " not found.");
	}
}
