import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class login extends Applet
{
    public static String codingString;
    public static String codedString;
    
    public void init() {
        if (login.codedString == null) {
            login();
        }
    }
    
    public static String login() {
        final Color foreground = new Color(0, 0, 200);
        final Font font = new Font("dialoginput", 0, 16);
        final Dialog dialog = new Dialog(new Frame(), "Username and Password Required", true);
        dialog.setLocation(250, 300);
        final BorderLayout layout = new BorderLayout();
        layout.setVgap(10);
        dialog.setLayout(layout);
        final Label label = new Label("** Authentication for DVR Server **", 1);
        label.setFont(font);
        label.setForeground(foreground);
        dialog.add(label, "North");
        dialog.add(new Label(""), "West");
        dialog.add(new Label(""), "East");
        final GridLayout layout2 = new GridLayout(2, 2);
        layout2.setVgap(15);
        final Panel panel = new Panel();
        panel.setLayout(layout2);
        final Label label2 = new Label("Enter Username:", 1);
        label2.setFont(font);
        panel.add(label2);
        final TextField textField = new TextField();
        textField.setFont(font);
        textField.addActionListener(new ActionListener() {
            private final /* synthetic */ Dialog val$jd = val$jd;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                this.val$jd.dispose();
            }
        });
        panel.add(textField);
        final Label label3 = new Label("Enter Password:", 1);
        label3.setFont(font);
        panel.add(label3);
        final TextField textField2 = new TextField();
        textField2.setEchoChar('*');
        textField2.addActionListener(new ActionListener() {
            private final /* synthetic */ Dialog val$jd = val$jd;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                this.val$jd.dispose();
            }
        });
        panel.add(textField2);
        dialog.add(panel, "Center");
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 3));
        panel2.add(new Label(""));
        final Button button = new Button("OK");
        button.addActionListener(new ActionListener() {
            private final /* synthetic */ Dialog val$jd = val$jd;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                this.val$jd.dispose();
            }
        });
        panel2.add(button);
        panel2.add(new Label(""));
        dialog.add(panel2, "South");
        dialog.pack();
        dialog.setVisible(true);
        login.codingString = String.valueOf(textField.getText()) + ":" + textField2.getText();
        return login.codedString = Base64.encode(login.codingString.getBytes());
    }
}
