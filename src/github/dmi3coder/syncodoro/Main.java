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
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root,640, 420);
        Label processTimer = (Label) scene.lookup("#progressTimer");
        Arc processCircle = (Arc)scene.lookup("#progressCircle");
        processCircle.setLength(250);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
