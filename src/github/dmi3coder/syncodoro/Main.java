package github.dmi3coder.syncodoro;

import com.kinvey.nativejava.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by dmi3coder on 1/16/16;6:16 PM.
 */
public class Main extends Application {
    static final Client mKinveyClient = new Client.Builder("kid_-kHgALiOhe", "0952a33bb5864a49a0f7ebf63cecf5d2").build();
    private Label progressTimer;
    private Arc progressArc;
    private Scene rootScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        rootScene = new Scene(root,640, 420);
        progressTimer = (Label) rootScene.lookup("#progressTimer");
        progressArc = (Arc)rootScene.lookup("#progressCircle");
        primaryStage.setTitle("Syncodoro");
        primaryStage.setScene(rootScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
