package github.dmi3coder.syncodoro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.kinvey.nativejava.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by dmi3coder on 1/16/16;6:37 PM.
 */
public class Login extends Application implements EventHandler<ActionEvent>{
    private Scene rootScene;
    JFXTextField usernameField;
    JFXPasswordField passwordField,repeatPasswordField;
    JFXCheckBox isSignUpCheckBox;
    JFXButton processButton;
    Label errorText;
    Client client;

    public Login(Client client){
        this.client = client;
    }

    @Override
    public void start(final Stage loginStage)  throws IOException {
        defineElements();
        setActions();
        loginStage.setTitle("User process panel");
        loginStage.setScene(rootScene);
        loginStage.show();

    }

    private void defineElements() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        rootScene = new Scene(root,320, 420);
        usernameField = (JFXTextField) root.lookup("#usernameField");
        passwordField = (JFXPasswordField) root.lookup("#passwordField");
        repeatPasswordField = (JFXPasswordField)root.lookup("#repeatPasswordField");
        isSignUpCheckBox = (JFXCheckBox)root.lookup("#isSignUpCheckBox");
        processButton = (JFXButton)root.lookup("#processButton");
        errorText = (Label)root.lookup("#text_error");
    }

    private void setActions() {
        usernameField.setOnMouseClicked(event -> usernameField.setUnFocusColor(Paint.valueOf("#212121")));
        passwordField.setOnMouseClicked(event -> passwordField.setUnFocusColor(Paint.valueOf("#212121")));
        repeatPasswordField.setOnMouseClicked(event -> repeatPasswordField.setUnFocusColor(Paint.valueOf("#212121")));
        isSignUpCheckBox.setOnAction(event -> repeatPasswordField.setDisable(!isSignUpCheckBox.isSelected()));
        processButton.setOnAction(this);

    }

    private void createUIComponents() {

    }

    @Override
    public void handle(ActionEvent event) {
        try {
            if(isFieldsEmpty()){
                errorText.setVisible(true);
            }
            else if(isSignUpCheckBox.isSelected()) {
            client.user().createBlocking(usernameField.getText(),passwordField.getText()).execute();
                //TODO add client registration check
            }
            else {
                client.user().loginBlocking(usernameField.getText(),passwordField.getText()).execute();

            }
        } catch (IOException e) {

        }
    }

    public boolean isFieldsEmpty(){
        if (usernameField.getText().isEmpty()) {
            usernameField.setUnFocusColor(Paint.valueOf("#ff5252"));
            return true;
        }
        else if(passwordField.getText().isEmpty()){
            passwordField.setUnFocusColor(Paint.valueOf("#ff5252"));
            return true;
        }
        else if(isSignUpCheckBox.isSelected()&repeatPasswordField.getText().isEmpty()){
            repeatPasswordField.setUnFocusColor(Paint.valueOf("#ff5252"));
            return true;
        }

        return false;
    }
}
