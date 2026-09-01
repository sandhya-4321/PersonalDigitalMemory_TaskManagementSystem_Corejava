package Service;

import Entity.ActivityLog;

import java.util.ArrayList;
import java.util.List;

// Keeps a simple history of what the user did: task added, completed, etc.
public class ActivityService {
    private List<ActivityLog> logs = new ArrayList<>();
      public void log(String action, String description) {
          logs.add(new ActivityLog(action, description));
      }
public List<ActivityLog> getAllLogs() { 
	return logs; 
}
public void setLogs(List<ActivityLog> loaded) { 
	logs = loaded; 
	}
}