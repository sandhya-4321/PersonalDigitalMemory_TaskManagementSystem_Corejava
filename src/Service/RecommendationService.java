package Service;

import Entity.Task;
import java.util.List;
// Suggests which task the user should do next.
// Logic: HIGH priority tasks first, then whichever has the earliest due date.
public class RecommendationService {
	
public Task recommendNextTask(TaskService taskService) {
     List<Task> pending = taskService.getUpcomingTasksSorted(); // already sorted by due date
     if (pending.isEmpty()) return null;
     Task best = null;
     for (Task t : pending) {
       if (best == null) {
          best = t;
       } else if (isHigherPriority(t.getPriority(), best.getPriority())) {
       best = t;
       }
     }
      return best;
}

// Simple priority ranking: HIGH > MEDIUM > LOW
private boolean isHigherPriority(String a, String b) {
        return rank(a) > rank(b);
}

private int rank(String priority) {
      if (priority.equals("HIGH")) return 3;
      if (priority.equals("MEDIUM")) return 2;
      return 1;
   }
}