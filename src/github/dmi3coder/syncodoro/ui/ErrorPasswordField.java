package github.dmi3coder.syncodoro.ui;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

/**
 * Created by dmi3coder on 1/22/16;11:20 PM.
 */
public class ErrorPasswordField extends JFXPasswordField{

    public ErrorPasswordField(){
        super();
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setUnFocusColor(Paint.valueOf("#212121"));
            }
        });
    }
}
