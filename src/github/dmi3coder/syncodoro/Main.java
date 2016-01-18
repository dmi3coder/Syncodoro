package github.dmi3coder.syncodoro;

import com.kinvey.nativejava.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.util.StringTokenizer;
import java.util.prefs.Preferences;

/**
 * Created by dmi3coder on 1/16/16;6:16 PM.
 */
public class Main extends Application {
    public static final int VERSION = 1;
    public static final String VERSION_STRING="0.0.1";
    static final Client mKinveyClient = new Client.Builder("kid_-kHgALiOhe", "0952a33bb5864a49a0f7ebf63cecf5d2").build();
    private Label progressTimer;
    private Arc progressArc;
    private Scene rootScene;
    public static Preferences preferences = Preferences.userNodeForPackage(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        rootScene = new Scene(root,640, 420);
        progressTimer = (Label) rootScene.lookup("#progressTimer");
        progressTimer.setText(preferences.get("minutes","25")+":" +preferences.get("seconds","00"));
        progressArc = (Arc)rootScene.lookup("#progressCircle");
        primaryStage.setTitle("Syncodoro");
        primaryStage.setScene(rootScene);
        primaryStage.show();

        if(preferences.getBoolean("isFirstLogin",true)){
            //preferences.putBoolean("isFirstLogin",false);
            showLoginForm();
            defineStandardValues();
            new Login().start(new Stage());
        }
    }


    private void showLoginForm() {

    }
    private void defineStandardValues() {
        preferences.putInt("session_minutes",25);
        preferences.putInt("session_seconds",0);
        preferences.putInt("break_minutes",5);
        preferences.putInt("break_seconds",5);
        preferences.putInt("longbreak_minutes",15);
        preferences.putInt("longbreak_seconds",0);
        preferences.putInt("longbreak_after",4);
        preferences.putBoolean("ticking_sound",false);
        preferences.putBoolean("alarm_sound",true);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
