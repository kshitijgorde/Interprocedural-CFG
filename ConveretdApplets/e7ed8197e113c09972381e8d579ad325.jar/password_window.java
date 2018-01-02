import java.awt.Event;
import java.awt.Point;
import java.awt.IllegalComponentStateException;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class password_window extends Frame implements groupboard_consts
{
    groupboard parent;
    String password_string;
    TextField password;
    TextField username;
    Button ok_but;
    Button cancel_but;
    boolean need_username;
    
    password_window(final groupboard parent, final boolean need_username) {
        this.parent = parent;
        this.need_username = need_username;
        parent.asked_for_password = true;
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        if (need_username) {
            this.setTitle("Login");
            final Label label;
            this.add(label = new Label("Username"));
            layout.setConstraints(label, gridBagConstraints);
            this.add(this.username = new TextField(20));
            gridBagConstraints.gridx = 1;
            layout.setConstraints(this.username, gridBagConstraints);
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
        }
        else {
            this.setTitle("Enter Password");
        }
        final Label label2;
        this.add(label2 = new Label("Password"));
        layout.setConstraints(label2, gridBagConstraints);
        this.add(this.password = new TextField(20));
        this.password.setEchoCharacter('*');
        gridBagConstraints.gridx = 1;
        layout.setConstraints(this.password, gridBagConstraints);
        final Panel panel = new Panel();
        panel.add(this.ok_but = new Button("OK"));
        panel.add(this.cancel_but = new Button("Cancel"));
        this.add(panel);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        layout.setConstraints(panel, gridBagConstraints);
        try {
            final Point locationOnScreen = parent.getLocationOnScreen();
            if (parent.new_jdk) {
                this.setLocation(Math.max(locationOnScreen.x + 10, 10), Math.max(locationOnScreen.y + 10, 10));
            }
        }
        catch (IllegalComponentStateException ex) {}
        this.pack();
        this.show();
        if (need_username) {
            this.username.requestFocus();
        }
        else {
            this.password.requestFocus();
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cancel_but) {
            this.dispose();
            this.parent.really_stop();
            return true;
        }
        if (event.target == this.ok_but) {
            this.parent.password = this.password.getText();
            if (this.need_username) {
                this.parent.username = this.username.getText();
            }
            this.dispose();
            this.parent.start2();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            return true;
        }
        if (event.id == 401 && 10 == event.key) {
            this.parent.password = this.password.getText();
            if (this.need_username) {
                this.parent.username = this.username.getText();
            }
            this.dispose();
            this.parent.start2();
            return true;
        }
        return super.handleEvent(event);
    }
}
