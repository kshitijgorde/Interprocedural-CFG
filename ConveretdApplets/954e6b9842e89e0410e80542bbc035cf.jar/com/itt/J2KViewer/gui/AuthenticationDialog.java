// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import com.itt.J2KViewer.controller.PropertyManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import javax.swing.JDialog;

public class AuthenticationDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private ViewCentral viewCentral;
    private JPanel authenticationPanel;
    private JPanel btnPanel;
    private JLabel instructionsLabel1;
    private JLabel instructionsLabel2;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton okBtn;
    private JButton cancelBtn;
    private boolean isNewAuthentication;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$AuthenticationDialog;
    
    public AuthenticationDialog(final Frame frame, final boolean b, final ViewCentral viewCentral) {
        super(frame, b);
        this.viewCentral = null;
        this.isNewAuthentication = false;
        this.viewCentral = viewCentral;
        this.initComponents();
        this.getRootPane().setDefaultButton(this.okBtn);
        this.loadUsername();
    }
    
    public boolean isNewAuthentication() {
        return this.isNewAuthentication;
    }
    
    private void loadUsername() {
    }
    
    private void initComponents() {
        this.setDefaultCloseOperation(2);
        this.setTitle("Authentication");
        (this.authenticationPanel = new JPanel()).setLayout(new GridBagLayout());
        this.authenticationPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.instructionsLabel1 = new JLabel("Please enter a valid username and password.");
        this.instructionsLabel2 = new JLabel("to access this resource.");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        this.authenticationPanel.add(this.instructionsLabel1, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.anchor = 17;
        this.authenticationPanel.add(this.instructionsLabel2, gridBagConstraints2);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridwidth = 0;
        gridBagConstraints3.anchor = 17;
        this.authenticationPanel.add(new JLabel(" "), gridBagConstraints3);
        this.usernameLabel = new JLabel("Username:");
        this.passwordLabel = new JLabel("Password:");
        this.username = new JTextField(13);
        this.password = new JPasswordField(13);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.anchor = 13;
        gridBagConstraints4.insets = new Insets(5, 0, 5, 5);
        this.authenticationPanel.add(this.usernameLabel, gridBagConstraints4);
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridwidth = 0;
        gridBagConstraints5.anchor = 17;
        this.authenticationPanel.add(this.username, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.anchor = 13;
        gridBagConstraints6.insets = new Insets(5, 0, 5, 5);
        this.authenticationPanel.add(this.passwordLabel, gridBagConstraints6);
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridwidth = 0;
        gridBagConstraints7.anchor = 17;
        this.authenticationPanel.add(this.password, gridBagConstraints7);
        this.getContentPane().add(this.authenticationPanel, "Center");
        (this.btnPanel = new JPanel()).setLayout(new FlowLayout(2));
        this.btnPanel.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));
        (this.okBtn = new JButton()).setMnemonic('O');
        this.okBtn.setText("OK");
        this.okBtn.setToolTipText("Close dialog and save changes");
        this.okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                AuthenticationDialog.this.okBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.okBtn);
        (this.cancelBtn = new JButton()).setMnemonic('C');
        this.cancelBtn.setText("Cancel");
        this.cancelBtn.setToolTipText("Close dialog and cancel changes");
        this.cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                AuthenticationDialog.this.cancelBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.cancelBtn);
        this.getContentPane().add(this.btnPanel, "South");
        this.pack();
    }
    
    private void cancelBtnActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(false);
    }
    
    private void okBtnActionPerformed(final ActionEvent actionEvent) {
        this.closeDialog(true);
    }
    
    private void closeDialog(final boolean b) {
        if (b) {
            final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
            propertyManager.setLoginId(this.username.getText());
            final char[] password = this.password.getPassword();
            propertyManager.setLoginPwd(new String(password));
            for (int i = 0; i < password.length; ++i) {
                password[i] = ' ';
            }
            this.isNewAuthentication = true;
        }
        else {
            this.isNewAuthentication = false;
        }
        this.dispose();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        AuthenticationDialog.log = new Log((AuthenticationDialog.class$com$itt$J2KViewer$gui$AuthenticationDialog == null) ? (AuthenticationDialog.class$com$itt$J2KViewer$gui$AuthenticationDialog = class$("com.itt.J2KViewer.gui.AuthenticationDialog")) : AuthenticationDialog.class$com$itt$J2KViewer$gui$AuthenticationDialog);
    }
}
