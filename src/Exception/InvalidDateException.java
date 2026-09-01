package Exception;

public class InvalidDateException extends Exception {
public InvalidDateException(String badInput) { 
	super("Invalid date: '" + badInput + "'. Use dd-MM-yyyy."); 
	}
}
