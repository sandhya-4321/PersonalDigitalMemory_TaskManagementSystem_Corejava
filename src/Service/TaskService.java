package Service;

import Entity.Task;
import Exception.TaskNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskService {
private List<Task> tasks = new ArrayList<>();
private int nextId = 1;

public Task addTask(String title, LocalDate dueDate, String priority, Integer linkedMemoryId) {
         Task task = new Task(nextId++, title, dueDate, priority, linkedMemoryId);
         tasks.add(task);
         return task;
}

public List<Task> getAllTasks() { 
	return tasks; 
}

public List<Task> getTodayTasks() {
         List<Task> result = new ArrayList<>();
         for (Task t : tasks)
         if (t.getDueDate().isEqual(LocalDate.now()) && t.getStatus().equals("PENDING")) result.add(t);
         return result;
}

public List<Task> getUpcomingTasksSorted() {
       List<Task> pending = filterByStatus("PENDING");
       Collections.sort(pending);
       return pending;
}

public List<Task> getOverdueTasks() {
       List<Task> result = new ArrayList<>();
       for (Task t : tasks)
       if (t.getStatus().equals("PENDING") && t.getDueDate().isBefore(LocalDate.now())) result.add(t);
       Collections.sort(result);
       return result;
}

private List<Task> filterByStatus(String status) {
       List<Task> result = new ArrayList<>();
       for (Task t : tasks)
       if (t.getStatus().equals(status)) result.add(t);
       return result;
}

public void markComplete(int id) throws TaskNotFoundException {
       for (Task t : tasks)
       if (t.getId() == id) { 
    	   t.markCompleted(); 
    	   return; 
    	   }
       throw new TaskNotFoundException(id);
}

public void setTasks(List<Task> loaded) {
        tasks = loaded;
        for (Task t : loaded)
        if (t.getId() >= nextId) nextId = t.getId() + 1;
   }
}
