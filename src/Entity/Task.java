package Entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable, Comparable<Task> {
	
private int id;
private String title, priority, status;
private LocalDate dueDate;
private Integer linkedMemoryId;


public Task(int id, String title, LocalDate dueDate, String priority, Integer linkedMemoryId) {
          this.id = id;
          this.title = title;
          this.dueDate = dueDate;
          this.priority = priority;
          this.status = "PENDING";
          this.linkedMemoryId = linkedMemoryId;
}

public int getId() { 
	return id; 
	}
public String getTitle() { 
	return title; 
	}
public LocalDate getDueDate() { 
	return dueDate; 
	}
public String getPriority() { 
	return priority; 
	}
public String getStatus() { 
	return status; 
	}
public void markCompleted() { 
	status = "COMPLETED"; 
	}
public int compareTo(Task other) { 
	return this.dueDate.compareTo(other.dueDate);
	}

public String toString() {

    String link = "";

    if (linkedMemoryId != null) {
        link = " (from Memory #" + linkedMemoryId + ")";
    }

    return "[" + id + "] " + title
            + " | Due: " + dueDate
            + " | Priority: " + priority
            + " | Status: " + status
            + link;
}
}