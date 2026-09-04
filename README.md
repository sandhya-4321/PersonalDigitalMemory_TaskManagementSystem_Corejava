# Personal Digital Memory and Task Management System

## 📌 Project Overview

**Personal Digital Memory and Task Management System** is a **Core Java-based console application** developed as a group project.

It helps users organize personal memories and manage daily tasks efficiently. The application provides task priorities, deadlines, reminders, recommendations, and productivity tracking in one place.

---

## 🎯 Objectives

- Organize personal memories and daily tasks.
- Set task priorities and deadlines.
- Track today's, upcoming, overdue, completed, and pending tasks.
- Provide reminders and smart task recommendations.
- Monitor and improve productivity.

---

## ✨ Key Features

- User Registration and Login
- Memory Management and Keyword Search
- Task Creation and Management
- Priority and Deadline Management
- Today's, Upcoming, and Overdue Tasks
- Task Completion Tracking
- Reminders and Smart Recommendations
- Productivity Score and Dashboard
- Activity History
- Data Storage using File Handling

---

## 🛠️ Technologies Used

| Category | Technology |
|---|---|
| Programming Language | Java |
| Application Type | Console-Based Application |
| Programming Approach | Core Java & OOP |
| Collections | ArrayList, HashMap |
| Error Handling | Exception Handling |
| Data Storage | File Handling & Serialization |
| Date Management | Java Date-Time API |

---

## 💡 Core Java Concepts

- **Classes & Objects** – Used for `Task`, `MemoryEntry`, `User`, and `ActivityLog`.
- **Encapsulation** – Used private variables with getters and setters.
- **Inheritance** – Used in custom exception classes.
- **Polymorphism** – Used for common operations with different implementations.
- **Abstraction** – Used through service classes for business logic.
- **Collections** – `ArrayList` and `HashMap` are used for data management.
- **Exception Handling** – Used to handle invalid inputs and errors.
- **File Handling & Serialization** – Used to save and load application data.
- **Date-Time API** – Used to manage task deadlines and dates.

---

## 📂 Project Structure

```text
📦 Personal Digital Memory and Task Management System
│
├── 📁 Entity
│   ├── 📄 Task.java
│   ├── 📄 MemoryEntry.java
│   ├── 📄 User.java
│   └── 📄 ActivityLog.java
│
├── 📁 Service
│   ├── 📄 TaskService.java
│   ├── 📄 MemoryService.java
│   ├── 📄 UserService.java
│   ├── 📄 StorageService.java
│   ├── 📄 RecommendationService.java
│   ├── 📄 DashboardService.java
│   ├── 📄 ActivityService.java
│   └── 📄 ReminderService.java
│
├── 📁 Exception
│   ├── 📄 InvalidLoginException.java
│   ├── 📄 InvalidDateException.java
│   ├── 📄 TaskNotFoundException.java
│   └── 📄 MemoryNotFoundException.java
│
├── 📁 Main
│   └── 📄 Main.java
│
└── 📄 README.md
