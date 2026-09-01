package Service;

import Entity.User;
import Exception.InvalidLoginException;

import java.util.ArrayList;
import java.util.List;

public class UserService {
	
private List<User> users = new ArrayList<>();
public void register(String username, String password) throws InvalidLoginException {
    for (User u : users)
         if (u.getUsername().equalsIgnoreCase(username))
             throw new InvalidLoginException("Username already exists.");
             users.add(new User(username, password));
}

public User login(String username, String password) throws InvalidLoginException {
    for (User u : users)
        if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password))
        return u;
        throw new InvalidLoginException("Invalid username or password.");
}

public void setUsers(List<User> loaded) { 
	users = loaded;
	}

public List<User> getAllUsers() { 
	return users; 
	}
}
