package Service;

import Entity.MemoryEntry;
import Exception.MemoryNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryService {
	
private List<MemoryEntry> memories = new ArrayList<>();
private Map<String, List<MemoryEntry>> tagIndex = new HashMap<>();
private int nextId = 1;

  public MemoryEntry addMemory(String title, String content, List<String> tags) {
        MemoryEntry entry = new MemoryEntry(nextId++, title, content, tags);
        memories.add(entry);
        indexTags(entry);
        return entry;
  }
  
private void indexTags(MemoryEntry entry) {
     for (String tag : entry.getTags())
     tagIndex.computeIfAbsent(tag.toLowerCase().trim(), k -> new ArrayList<>()).add(entry);
}

public List<MemoryEntry> getAllMemories() { 
	return memories; 
}

public List<MemoryEntry> searchByTag(String tag) {
      return tagIndex.getOrDefault(tag.toLowerCase().trim(), new ArrayList<>());
}

public List<MemoryEntry> searchByKeyword(String keyword) {
      List<MemoryEntry> results = new ArrayList<>();
      String k = keyword.toLowerCase();
      for (MemoryEntry m : memories)
      if (m.getTitle().toLowerCase().contains(k) || m.getContent().toLowerCase().contains(k))
      results.add(m);
      return results;
}

public MemoryEntry getById(int id) throws MemoryNotFoundException {
    for (MemoryEntry m : memories)
    if (m.getId() == id) return m;
    throw new MemoryNotFoundException(id);
}

public void setMemories(List<MemoryEntry> loaded) {
            memories = loaded;
            tagIndex.clear();
            for (MemoryEntry m : loaded) {
            indexTags(m);
            if (m.getId() >= nextId) nextId = m.getId() + 1;
       }
    }
}
