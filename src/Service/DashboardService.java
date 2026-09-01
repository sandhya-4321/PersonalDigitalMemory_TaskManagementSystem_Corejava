package Service;

import Entity.Task;

import java.time.LocalDate;
import java.util.List;

// Computes summary status about tasks: counts and a productivity score
public class DashboardService {
	
public void printDashboard(TaskService taskService) {
      List<Task> all = taskService.getAllTasks();
      int total = all.size();
      int completed = 0, overdue = 0;
      for (Task t : all) {
        if (t.getStatus().equals("COMPLETED")) {
            completed++;
        } else if (t.getDueDate().isBefore(LocalDate.now())) {
        overdue++;
      }
}
      
int pending = total - completed;
System.out.println("===== TASK DASHBOARD =====");
System.out.println("Total Tasks : " + total);
System.out.println("Completed Tasks : " + completed);
System.out.println("Pending Tasks : " + pending);
System.out.println("Overdue Tasks : " + overdue);
}

// Productivity score = percentage of tasks completed out of all tasks
public double calculateProductivityScore(TaskService taskService) {
      List<Task> all = taskService.getAllTasks();
      if (all.isEmpty()) return 0;
      int completed = 0;
      for (Task t : all) {
        if (t.getStatus().equals("COMPLETED")) completed++;
      }
      return (completed * 100.0) / all.size();
   }
}
