package Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageService {

//Deserialization
public <T> List<T> load(String file) {
     File f = new File(file);
     if (!f.exists()) return new ArrayList<>();
     try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
          return (List<T>) in.readObject();
     } catch (Exception e) {
              System.out.println("Could not load " + file + ": " + e.getMessage());
              return new ArrayList<>();
     }
}

//serialization
public <T> void save(String file, List<T> list) {
       try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(list);
       } catch (IOException e) {
                System.out.println("Could not save " + file + ": " + e.getMessage());
       }
    }
}