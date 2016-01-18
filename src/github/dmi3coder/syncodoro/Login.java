package github.dmi3coder.syncodoro;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

/**
 * Created by dmi3coder on 1/16/16;6:37 PM.
 */
public class Login{
    private JPanel panel1;
    private JLabel helloLabel;
    private JLabel loginLabel;
    private ResourceBundle bundle;
    static JFrame frame;

    public Login(JFrame frame) {
        frame = new JFrame("Login");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(670,530);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((int) (screenSize.getWidth()/2-frame.getHeight()/2), (int) (screenSize.getHeight()/2-frame.getHeight()/2)));
        bundle = ResourceBundle.getBundle("github.dmi3coder.Syncodoro.login");
        helloLabel.setText(bundle.getString("welcome"));
        loginLabel.setText(bundle.getString("loginplease"));
    }

    private void createUIComponents() {

    }
}
