import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class AuthPanel extends Panel implements ActionListener
{
    Label title;
    Label retry;
    Label prompt;
    TextField password;
    Button ok;
    
    public AuthPanel() {
        (this.title = new Label("VNC Authentication", 1)).setFont(new Font("Helvetica", 1, 18));
        this.prompt = new Label("Password:", 1);
        (this.password = new TextField(10)).setForeground(Color.black);
        this.password.setBackground(Color.white);
        this.password.setEchoChar('*');
        this.ok = new Button("OK");
        (this.retry = new Label("", 1)).setFont(new Font("Courier", 1, 16));
        final GridBagLayout gridbag = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gridbag);
        gbc.gridwidth = 0;
        gridbag.setConstraints(this.title, gbc);
        this.add(this.title);
        gbc.fill = 2;
        gridbag.setConstraints(this.retry, gbc);
        this.add(this.retry);
        gbc.fill = 0;
        gbc.gridwidth = 1;
        gridbag.setConstraints(this.prompt, gbc);
        this.add(this.prompt);
        gridbag.setConstraints(this.password, gbc);
        this.add(this.password);
        this.password.addActionListener(this);
        gbc.ipady = 10;
        gbc.gridwidth = 0;
        gbc.fill = 1;
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.ipadx = 40;
        gridbag.setConstraints(this.ok, gbc);
        this.add(this.ok);
        this.ok.addActionListener(this);
    }
    
    public void moveFocusToPasswordField() {
        this.password.requestFocus();
    }
    
    public synchronized void actionPerformed(final ActionEvent evt) {
        if (evt.getSource() == this.password || evt.getSource() == this.ok) {
            this.password.setEnabled(false);
            this.notify();
        }
    }
    
    public void retry() {
        this.retry.setText("Sorry. Try again.");
        this.password.setEnabled(true);
        this.password.setText("");
        this.moveFocusToPasswordField();
    }
}
