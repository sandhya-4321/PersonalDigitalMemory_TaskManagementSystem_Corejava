package Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class MemoryEntry implements Serializable {
private int id;
private String title, content;
private List<String> tags;
private LocalDate createdDate;

public MemoryEntry(int id, String title, String content, List<String> tags) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.createdDate = LocalDate.now();
}

public int getId() { 
	return id; 
	}
public String getTitle() {
	return title;
	}
public String getContent() {
	return content;
}
public List<String> getTags() { 
	return tags; 
	}

public String toString() {
return "[" + id + "] " + title + " | Tags: " + tags + " | Created: " + createdDate + "\n " + content;
}
}