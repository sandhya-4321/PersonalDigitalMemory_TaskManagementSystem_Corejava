package Entity;

import java.io.Serializable;

public class User implements Serializable {

private String username, password;
  public User(String username, String password) 
  {
            this.username = username;
            this.password = password;
  }
public String getUsername() {
	return username;
   }
public String getPassword() { 
	return password;
	}
public String toString() {
	return "User: " + username;
	}
}
