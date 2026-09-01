package Exception;

public class MemoryNotFoundException extends Exception {
public MemoryNotFoundException(int id) { 
	super("Memory with ID " + id + " not found.");
	}
}