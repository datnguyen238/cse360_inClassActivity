package asuHelloWorldJavaFX;

//UserCredentialsStorage.java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UserCredentialsStorage {
 private Properties properties;
 private String propertiesFilePath;

 public UserCredentialsStorage(String propertiesFilePath) {
     this.propertiesFilePath = propertiesFilePath;
     properties = new Properties();
     // Load the properties file
     try (FileInputStream in = new FileInputStream(propertiesFilePath)) {
         properties.load(in);
     } catch (IOException e) {
         // Handle the case where the properties file does not exist yet
     }
 }

 public boolean checkCredentials(String userId, String password) {
     // Load the stored password for the user
     String storedPassword = properties.getProperty(userId);
     return storedPassword != null && storedPassword.equals(password);
 }

 public void storeCredentials(String userId, String password) throws IOException {
     // Store the user credentials (This is insecure as it's plain text)
     properties.setProperty(userId, password);

     // Save the properties file
     try (FileOutputStream out = new FileOutputStream(propertiesFilePath)) {
         properties.store(out, "User Credentials");
     }
 }
}

