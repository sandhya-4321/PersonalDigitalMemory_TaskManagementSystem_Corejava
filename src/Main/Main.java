package Main;

import Entity.MemoryEntry;
import Entity.Task;
import Entity.User;
import Exception.InvalidDateException;
import Exception.InvalidLoginException;
import Exception.TaskNotFoundException;
import Service.ActivityService;
import Service.DashboardService;
import Service.MemoryService;
import Service.RecommendationService;
import Service.ReminderService;
import Service.StorageService;
import Service.TaskService;
import Service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	
private static Scanner sc = new Scanner(System.in);

private static MemoryService memoryService = new MemoryService();
private static TaskService taskService = new TaskService();
private static StorageService storageService = new StorageService();
private static UserService userService = new UserService();
private static ActivityService activityService = new ActivityService();
private static DashboardService dashboardService = new DashboardService();
private static ReminderService reminderService = new ReminderService();
private static RecommendationService recommendationService = new RecommendationService();
private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
private static User currentUser;

public static void main(String[] args) {
	
memoryService.setMemories(storageService.load("memories.dat"));
taskService.setTasks(storageService.load("tasks.dat"));
userService.setUsers(storageService.load("users.dat"));
activityService.setLogs(storageService.load("activity.dat"));

while (currentUser == null) {
     System.out.println("\n===== LOGIN =====\n1. Login 2. Register 0. Exit");
     System.out.print("Choose an option: ");
     String choice = sc.nextLine().trim();
     if (choice.equals("1")) login();
     else if (choice.equals("2")) register();
     else if (choice.equals("0")) { System.out.println("Bye!"); return; }
     else System.out.println("Invalid option, try again.");
}

boolean running = true;
while (running) {
    showMenu();
    String choice = sc.nextLine().trim();
    switch (choice) {
      case "1": addMemory(); break;
      case "2": viewAllMemories(); break;
      case "3": searchMemories(); break;
      case "4": addTaskManually(); break;
      case "5": viewTodayTasks(); break;
      case "6": viewUpcomingTasks(); break;
      case "7": viewOverdueTasks(); break;
      case "8": markTaskComplete(); break;
      case "9": dashboardService.printDashboard(taskService); break;
      case "10": showProductivityScore(); break;
      case "11": printList(activityService.getAllLogs(), "No activity yet."); break;
      case "12": printList(reminderService.getReminders(taskService), "No reminders right now."); break;
      case "13": showRecommendation(); break;
      case "0": running = false; saveAndExit(); break;
      default: System.out.println("Invalid option, try again.");
    }
  }
}
private static void login() {
    System.out.print("Username: ");
    String u = sc.nextLine();
    System.out.print("Password: ");
    String p = sc.nextLine();
  try {
      currentUser = userService.login(u, p);
      System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
      List<String> reminders = reminderService.getReminders(taskService);
      if (!reminders.isEmpty()) {
         System.out.println("--- You have " + reminders.size() + " reminder(s) ---");
         reminders.forEach(System.out::println);
       }
  } catch (InvalidLoginException e) {
          System.out.println("Error: " + e.getMessage());
  }
}
private static void register() {
      System.out.print("Choose a username: ");
      String u = sc.nextLine();
      System.out.print("Choose a password: ");
      String p = sc.nextLine();
       try {
          userService.register(u, p);
          storageService.save("users.dat", userService.getAllUsers());
          System.out.println("Registered successfully! You can now login.");
       } catch (InvalidLoginException e) {
             System.out.println("Error: " + e.getMessage());
       }
}
private static void showMenu() {
   System.out.println("\n===== PERSONAL MEMORY & TASK PLANNER (" + currentUser.getUsername() + ") =====");
   System.out.println("1. Add Memory\n2. View All Memories\n3. Search Memories\n4. Add Task");
   System.out.println("5. Today's Tasks\n6. Upcoming Tasks\n7. Overdue Tasks\n8. Mark Task Complete");
   System.out.println("9. Task Dashboard\n10. Productivity Score\n11. Task Activity History");
   System.out.println("12. Reminders\n13. Recommended Task\n0. Save & Exit");
   System.out.print("Choose an option: ");
}
private static void addMemory() {
     System.out.print("Title: ");
     String title = sc.nextLine();
     System.out.print("Content: ");
     String content = sc.nextLine();
     System.out.print("Tags (comma separated): ");
     String tagLine = sc.nextLine();
     List<String> tags = tagLine.isBlank() ? new ArrayList<>() : Arrays.asList(tagLine.split(","));
     MemoryEntry entry = memoryService.addMemory(title, content, new ArrayList<>(tags));
     System.out.println("Memory saved with ID: " + entry.getId());
}
private static void viewAllMemories() {
      printList(memoryService.getAllMemories(), "No memories yet.");
}
private static void searchMemories() {
    System.out.print("Search by (1) Tag or (2) Keyword? ");
    String mode = sc.nextLine().trim();
    System.out.print("Enter search term: ");
    String term = sc.nextLine();
    List<MemoryEntry> results = mode.equals("1") ? memoryService.searchByTag(term) : memoryService.searchByKeyword(term);
    printList(results, "No matches found.");
}
private static void addTaskManually() {
     System.out.print("Task title: ");
     String title = sc.nextLine();
     try {
         LocalDate dueDate = readDate();
         String priority = readPriority();
         Task task = taskService.addTask(title, dueDate, priority, null);
         activityService.log("TASK_ADDED", task.getTitle() + " (due " + dueDate + ")");
         System.out.println("Task added with ID: " + task.getId());
     } catch (InvalidDateException e) {
         System.out.println("Error: " + e.getMessage());
     }
}
private static LocalDate readDate() throws InvalidDateException {
       System.out.print("Due date (dd-MM-yyyy): ");
       String input = sc.nextLine().trim();
       try {
         return LocalDate.parse(input, dateFormat);
       } catch (DateTimeParseException e) {
           throw new InvalidDateException(input);
       }
}
private static String readPriority() {
      System.out.print("Priority (1-LOW, 2-MEDIUM, 3-HIGH): ");
      String input = sc.nextLine().trim();
      if (input.equals("1")) return "LOW";
      if (input.equals("3")) return "HIGH";
      return "MEDIUM";
}
private static void viewTodayTasks() {
    printList(taskService.getTodayTasks(), "No tasks due today.");
}
private static void viewUpcomingTasks() {
    printList(taskService.getUpcomingTasksSorted(), "No pending tasks.");
}
private static void viewOverdueTasks() {
    printList(taskService.getOverdueTasks(), "No overdue tasks. Nice!");
}
private static void markTaskComplete() {
       System.out.print("Enter task ID to mark complete: ");
       int id = Integer.parseInt(sc.nextLine().trim());
       try {
          taskService.markComplete(id);
          activityService.log("TASK_COMPLETED", "Task ID " + id);
          System.out.println("Task marked complete.");
      } catch (TaskNotFoundException e) {
           System.out.println("Error: " + e.getMessage());
      }
}
private static void showProductivityScore() {
      double score = dashboardService.calculateProductivityScore(taskService);
      System.out.printf("Your Productivity Score: %.1f%%\n", score);
}
private static void showRecommendation() {
      Task next = recommendationService.recommendNextTask(taskService);
      if (next == null) {
          System.out.println("No pending tasks to recommend.");
      } else {
          System.out.println("Recommended next task: " + next);
      }
}
// Shared helper: prints any list, or a message if it's empty (removes repeated if/else blocks)
private static <T> void printList(List<T> list, String emptyMessage) {
      if (list.isEmpty()) System.out.println(emptyMessage);
      else list.forEach(System.out::println);
}
private static void saveAndExit() {
     storageService.save("memories.dat", memoryService.getAllMemories());
     storageService.save("tasks.dat", taskService.getAllTasks());
     storageService.save("activity.dat", activityService.getAllLogs());
     System.out.println("Data saved. Goodbye!");
   }
}
