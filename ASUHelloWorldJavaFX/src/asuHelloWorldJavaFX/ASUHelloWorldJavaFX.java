package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class ASUHelloWorldJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
    	UserCredentialsStorage storage = new UserCredentialsStorage("userCredentials.properties");
        
    	primaryStage.setTitle("Group 31 Pediatric Office Login");

        // Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User ID:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btnLogin = new Button("Login");
        Button btnSignUp = new Button("Sign Up");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnSignUp);
        hbBtn.getChildren().add(btnLogin);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btnLogin.setOnAction(e -> {
            String userID = userTextField.getText();
            String password = pwBox.getText();
            if (storage.checkCredentials(userID, password)) {
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Login successful.");
            } else {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Login failed.");
            }
        });

        btnSignUp.setOnAction(e -> {
            String userID = userTextField.getText();
            String password = pwBox.getText();
            try {
                storage.storeCredentials(userID, password);
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Signup successful. Please log in.");
            } catch (IOException ex) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Signup failed: " + ex.getMessage());
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
