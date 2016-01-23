package github.dmi3coder.syncodoro;

import com.google.common.annotations.Beta;
import com.google.common.eventbus.EventBus;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.kinvey.nativejava.Client;
import github.dmi3coder.syncodoro.event.LoginUserEvent;
import github.dmi3coder.syncodoro.ui.ErrorTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Created by dmi3coder on 1/16/16;6:37 PM.
 */
public class Login extends Application implements EventHandler<ActionEvent>{
    private Scene rootScene;
    private JFXTextField usernameField;
    private JFXPasswordField passwordField,repeatPasswordField;
    private JFXCheckBox isSignUpCheckBox;
    private JFXButton processButton;
    private Label errorText;
    private Client backendClient;
    private Stage loginStage;


    public Login(Client backendClient){
        this.backendClient = backendClient;
    }

    @Override
    public void start(final Stage loginStage)  throws IOException {
        this.loginStage = loginStage;
        defineElements();
        setActions();
        loginStage.setTitle("User process panel");
        loginStage.setScene(rootScene);
        loginStage.show();
        loginStage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You can loose your sync!");
            alert.setHeaderText(null);
            alert.setContentText("Use [Syncodoro -> Login] to login in future");
            alert.show();
        });

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
        if (isFieldsEmpty()) {
            errorText.setVisible(true);
            errorText.setText(Main.bundle.getString("loginErrorFillField"));
        } else if (isSignUpCheckBox.isSelected()) {
            handleRegistration();
        } else {
            handleLogin();

        }
    }



    private void handleLogin() {
        try {
            if(!isFieldsHaveInvalidChars()) backendClient.user().loginBlocking(usernameField.getText(), passwordField.getText()).execute();
        }catch (Exception e){
            errorText.setText(Main.bundle.getString("loginErrorWrongPassword"));
            errorText.setVisible(true);
        }

    }


    //FIXME make check for illegal chars
    private boolean isFieldsHaveInvalidChars() {
        if(usernameField.getText().matches("^.*[~#@*+%{}<>\\[\\]|\"\\_].*$")|passwordField.getText().contains("!@#$%^&*()\";/.,+_\\/*-+")){
            System.out.println("consist!");
            return true;
        }
        else return false;
    }

    private void handleRegistration() {
        try {
            backendClient.user().createBlocking(usernameField.getText(), passwordField.getText()).execute();
        }catch (Exception e){

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
