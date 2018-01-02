import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class MyLogin extends Dialog implements ActionListener
{
    boolean id;
    Button ok;
    Button can;
    TextField username;
    TextField password;
    
    MyLogin(final Frame frame) {
        super(frame, "Welcome", true);
        this.id = false;
        this.setLayout(new FlowLayout());
        this.username = new TextField(15);
        (this.password = new TextField(15)).setEchoChar('*');
        this.add(new Label("User :"));
        this.add(this.username);
        this.add(new Label("Password :"));
        this.add(this.password);
        this.addOKCancelPanel();
        this.createFrame();
        this.pack();
        this.setVisible(true);
    }
    
    void addOKCancelPanel() {
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        this.createButtons(panel);
        this.add(panel);
    }
    
    void createButtons(final Panel panel) {
        panel.add(this.ok = new Button("OK"));
        this.ok.addActionListener(this);
    }
    
    void createFrame() {
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation(screenSize.width / 4, screenSize.height / 3);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.ok) {
            this.id = true;
            this.setVisible(false);
            return;
        }
        if (actionEvent.getSource() == this.can) {
            this.setVisible(false);
        }
    }
}
