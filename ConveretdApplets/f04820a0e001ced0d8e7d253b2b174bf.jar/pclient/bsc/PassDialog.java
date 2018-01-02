// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Panel;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class PassDialog extends Frame implements ActionListener
{
    private BaseChat pChat;
    private Button okButton;
    private Button cancelButton;
    private TextField answerField;
    private long lastAnswerTime;
    
    public PassDialog(final BaseChat pChat) {
        super(pChat.paraConf.title());
        this.lastAnswerTime = 0L;
        this.pChat = pChat;
        this.buildGUI();
        this.setSize(360, 160);
        this.pack();
        try {
            WindowUtil.center(pChat, this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
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
    
    private void clickOK() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastAnswerTime < 1500L) {
            System.out.println("multiple click roompass.");
            return;
        }
        this.lastAnswerTime = currentTimeMillis;
        this.pChat.finishPass(this.answerField.getText());
        this.shutdown();
    }
    
    private void clickCancel() {
        this.shutdown();
        this.pChat.finishCancelPass();
    }
    
    private void buildGUI() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.answerField = new TextField(24));
        this.add("South", this.buildButtons());
        this.add("North", new Label("This room is password-protected.  Please enter the password:"));
        this.answerField.addActionListener(this);
    }
    
    private Panel buildButtons() {
        final Panel panel = new Panel();
        this.okButton = new Button("Submit & Enter Room");
        this.cancelButton = new Button("Cancel");
        panel.add(this.okButton);
        panel.add(this.cancelButton);
        return panel;
    }
}
