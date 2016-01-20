package github.dmi3coder.syncodoro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by dmi3coder on 1/16/16;6:37 PM.
 */
public class Login extends Application{
    private Scene rootScene;
    JFXTextField usernameField;
    JFXPasswordField passwordField,repeatPasswordField;
    CheckBox isSignUpCheckBox;
    JFXButton processButton;

    @Override
    public void start(Stage loginStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        rootScene = new Scene(root,640, 420);
        usernameField = (JFXTextField) root.lookup("#usernameField");
        usernameField.setText("test");
        passwordField = (JFXPasswordField) root.lookup("#passwordField");
        repeatPasswordField = (JFXPasswordField)root.lookup("#repeatPasswordField");
        loginStage.setTitle("User process panel");
        loginStage.setScene(rootScene);
        loginStage.show();

    }

    private void createUIComponents() {

    }
}
