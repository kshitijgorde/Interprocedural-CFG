// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Panel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class UserDialog extends Frame implements ActionListener
{
    private BaseChat pChat;
    private Label infoLabel;
    private Button okButton;
    private Button changeButton;
    private Button cancelButton;
    private TextField answerField;
    private String errorCondition;
    private long lastAnswerTime;
    
    public UserDialog(final BaseChat pChat) {
        this.lastAnswerTime = 0L;
        this.pChat = pChat;
        this.buildGUI();
        this.setSize(360, 160);
    }
    
    public void display(final String s) {
        this.infoLabel.setText("This user name is password-protected.  Please enter the password:");
        this.okButton.setVisible(true);
        this.answerField.setVisible(true);
        this.validate();
        try {
            WindowUtil.center(this.pChat, this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.show();
    }
    
    public void shutdown() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.answerField) {
            this.clickOK();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.okButton) {
                this.clickOK();
            }
            else if (event.target == this.cancelButton) {
                this.clickCancel();
            }
            else if (event.target == this.changeButton) {
                this.clickChange();
            }
            return super.handleEvent(event);
        }
        if (event.id == 201) {
            this.clickCancel();
        }
        else if (event.id == 205) {
            this.answerField.requestFocus();
        }
        return super.handleEvent(event);
    }
    
    private void buildGUI() {
        this.setLayout(new BorderLayout());
        (this.answerField = new TextField(24)).setEchoChar('*');
        this.add("Center", this.answerField);
        this.add("South", this.buildButtons());
        (this.infoLabel = new Label("This user name is password-protected.  Please enter the password:")).setForeground(Color.blue);
        this.add("North", this.infoLabel);
        this.answerField.addActionListener(this);
    }
    
    private Panel buildButtons() {
        final Panel panel = new Panel();
        this.okButton = new Button("Submit & Enter Room");
        this.changeButton = new Button("Enter Without Password");
        this.cancelButton = new Button("Cancel");
        panel.add(this.okButton);
        panel.add(this.cancelButton);
        return panel;
    }
    
    private void clickOK() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastAnswerTime < 1500L) {
            return;
        }
        this.lastAnswerTime = currentTimeMillis;
        this.pChat.finishUserPass(this.answerField.getText());
        this.shutdown();
    }
    
    private void clickChange() {
        this.shutdown();
        this.pChat.finishUserChange();
    }
    
    private void clickCancel() {
        this.shutdown();
        this.pChat.chatModel.cmExit();
    }
}
