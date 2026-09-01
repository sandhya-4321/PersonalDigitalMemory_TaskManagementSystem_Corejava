package Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

// Records one action taken on a task, e.g. "TASK_ADDED", "TASK_COMPLETED"
public class ActivityLog implements Serializable {
private String action;
private String description;
private LocalDateTime timestamp;

public ActivityLog(String action, String description) {
     this.action = action;
     this.description = description;
     this.timestamp = LocalDateTime.now();
}

public String getAction() { 
	return action; 
	}
public String getDescription() { 
	return description; 
	}
public LocalDateTime getTimestamp() {
	return timestamp; 
	}

public String toString() {
return "[" + timestamp + "] " + action + " - " + description;
  }
}