import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class AuthPanel extends Panel implements ActionListener
{
    TextField passwordField;
    Button okButton;
    
    public AuthPanel(final VncViewer vncViewer) {
        final Label label = new Label("VNC Authentication", 1);
        label.setFont(new Font("Helvetica", 1, 18));
        final Label label2 = new Label("Password:", 1);
        (this.passwordField = new TextField(10)).setForeground(Color.black);
        this.passwordField.setBackground(Color.white);
        this.passwordField.setEchoChar('*');
        this.okButton = new Button("OK");
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        layout.setConstraints(this.passwordField, gridBagConstraints);
        this.add(this.passwordField);
        this.passwordField.addActionListener(this);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 20, 0, 0);
        gridBagConstraints.ipadx = 30;
        layout.setConstraints(this.okButton, gridBagConstraints);
        this.add(this.okButton);
        this.okButton.addActionListener(this);
    }
    
    public void moveFocusToDefaultField() {
        this.passwordField.requestFocus();
    }
    
    public synchronized void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.passwordField || actionEvent.getSource() == this.okButton) {
            this.passwordField.setEnabled(false);
            this.notify();
        }
    }
    
    public synchronized String getPassword() throws Exception {
        try {
            this.wait();
        }
        catch (InterruptedException ex) {}
        return this.passwordField.getText();
    }
}
