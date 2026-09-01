package Service;

import Entity.Task; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReminderService {
	
public List<String> getReminders(TaskService taskService) {
      List<String> reminders = new ArrayList<>();
      LocalDate today = LocalDate.now();
      for (Task t : taskService.getAllTasks()) {
         if (t.getStatus().equals("PENDING")) {
           if (t.getDueDate().isEqual(today)) {
                reminders.add("DUE TODAY: " + t);
           } else if (t.getDueDate().isBefore(today)) {
                reminders.add("OVERDUE: " + t);
           }
         }
     }
     return reminders;
   }
}
