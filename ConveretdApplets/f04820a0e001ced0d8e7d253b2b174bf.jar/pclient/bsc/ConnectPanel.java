// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import pclient.shd.ClientUtil;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class ConnectPanel extends Panel implements ActionListener
{
    private TextField pNickEdit;
    private TextField pRoomEdit;
    private TextField pEmailEdit;
    private TextField pPasswdEdit;
    private Button pDoConnectBtn;
    private AppletBase pChat;
    private long lastOKclicked;
    
    public ConnectPanel(final AppletBase pChat) {
        this.lastOKclicked = 0L;
        this.pChat = pChat;
        this.setForeground(this.pChat.mainChat.fgColor);
        this.setBackground(this.pChat.mainChat.bgColor);
        final Panel buildInput = this.buildInput();
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", buildInput);
        if (this.pChat.isMac()) {
            this.setLayout(new BorderLayout());
            this.add("Center", panel);
            this.add("South", this.buildButton());
        }
        else {
            int n = 7;
            if (this.pChat.isPopMode()) {
                n = 1;
            }
            this.setLayout(new FlowLayout(1, 499, n));
            this.add(panel);
            this.add(this.buildButton());
        }
        this.pNickEdit.addActionListener(this);
        this.pRoomEdit.addActionListener(this);
        this.pEmailEdit.addActionListener(this);
        this.pPasswdEdit.addActionListener(this);
        this.display();
    }
    
    private Panel buildInput() {
        final boolean b = !this.pChat.paraConf.getBool("Op.UserInfo", false);
        final boolean b2 = !this.pChat.paraConf.getBool("Op.UserPass", false);
        final boolean b3 = false;
        final Color black = Color.black;
        final Color white = Color.white;
        final Label label = new Label("User Name", 0);
        (this.pNickEdit = new TextField("", 18)).setForeground(black);
        this.pNickEdit.setBackground(white);
        final Label label2 = new Label("Your Password", 0);
        (this.pPasswdEdit = new TextField("", 18)).setEchoChar('*');
        this.pPasswdEdit.setForeground(black);
        this.pPasswdEdit.setBackground(white);
        final Label label3 = new Label("Email[optional]", 0);
        (this.pEmailEdit = new TextField("", 18)).setForeground(black);
        this.pEmailEdit.setBackground(white);
        final Label label4 = new Label("Room Password", 0);
        (this.pRoomEdit = new TextField("", 18)).setForeground(black);
        this.pRoomEdit.setBackground(white);
        (this.pDoConnectBtn = new Button("Connect")).setFont(new Font("Dialog", 1, 11));
        this.pDoConnectBtn.addActionListener(this);
        int n = 1;
        final Panel panel = new Panel();
        final GridLayout layout = new GridLayout(4, 1);
        panel.setLayout(layout);
        if (!b3) {
            panel.add(this.pNickEdit);
            ++n;
        }
        if (!b2) {
            panel.add(this.pPasswdEdit);
            ++n;
        }
        if (!b) {
            panel.add(this.pEmailEdit);
            ++n;
        }
        final Panel panel2 = new Panel();
        final GridLayout layout2 = new GridLayout(4, 1);
        panel2.setLayout(layout2);
        if (!b3) {
            panel2.add(label);
        }
        if (!b2) {
            panel2.add(label2);
        }
        if (!b) {
            panel2.add(label3);
        }
        layout2.setRows(n);
        layout.setRows(n);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(1));
        panel3.add(panel2);
        panel3.add(panel);
        return panel3;
    }
    
    private Panel buildButton() {
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1));
        panel.add(this.pDoConnectBtn);
        return panel;
    }
    
    public void display() {
        this.pNickEdit.requestFocus();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pNickEdit || actionEvent.getSource() == this.pRoomEdit || actionEvent.getSource() == this.pEmailEdit || actionEvent.getSource() == this.pPasswdEdit || actionEvent.getSource() == this.pDoConnectBtn) {
            this.connectRoom();
        }
    }
    
    private void connectRoom() {
        final String text = this.pNickEdit.getText();
        if (text.length() == 0) {
            this.pNickEdit.setText("Enter Name here");
            return;
        }
        this.pChat.connPanelLogin(ClientUtil.fixUser(text, this.pChat.paraConf), this.pPasswdEdit.getText(), this.pEmailEdit.getText(), this.pRoomEdit.getText());
    }
}
