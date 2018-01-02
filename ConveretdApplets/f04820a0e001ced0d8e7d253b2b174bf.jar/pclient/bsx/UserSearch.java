// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import pclient.shd.ClientUtil;
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import pclient.bsc.AppletBase;
import java.awt.Label;
import java.awt.TextField;
import java.awt.List;
import java.awt.Button;
import pclient.bsc.BaseChat;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class UserSearch extends Panel implements ActionListener
{
    private BaseChat pChat;
    private ControlPanel pControls;
    private Button pChatButton;
    private Button pRoomButton;
    private List pUserList;
    private Button pSearchButton;
    private TextField pInputFind;
    private Label pInfoLabel;
    private long lastAction;
    private String MSG_NOT_IN;
    
    public UserSearch(final BaseChat pChat, final ControlPanel pControls) {
        this.lastAction = 0L;
        this.MSG_NOT_IN = "Not Found";
        this.pChat = pChat;
        this.pControls = pControls;
        this.buildGUI();
    }
    
    public void process(final int n, final String[] array) {
        this.pChat.paraConf.printer().print("UserSearch," + n + " " + array);
        switch (n) {
            case 206: {
                if (AppletBase.hasOne(array)) {
                    this.roamServerSearch(array[0]);
                    break;
                }
                break;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.pInfoLabel.setText("");
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastAction < 2800L) {
            return;
        }
        this.lastAction = currentTimeMillis;
        if (actionEvent.getSource() == this.pSearchButton) {
            this.clickedSearch();
            return;
        }
        if (actionEvent.getSource() == this.pInputFind) {
            this.clickedSearch();
            return;
        }
        if (actionEvent.getSource() == this.pChatButton) {
            this.clickedChat();
            return;
        }
        if (actionEvent.getSource() == this.pRoomButton) {
            this.clickedRoom();
            return;
        }
        if (actionEvent.getSource() == this.pUserList) {
            this.clickedChat();
        }
    }
    
    private void roamServerSearch(final String s) {
        if (s == null) {
            return;
        }
        this.pInfoLabel.setText("");
        if (StringUtil.isEmpty(s)) {
            this.pInfoLabel.setText(this.MSG_NOT_IN);
            return;
        }
        this.addSearchUsers(s);
    }
    
    private void addSearchUsers(final String s) {
        this.pUserList.removeAll();
        final String[] splitString = StringUtil.splitString(s, "|", false);
        for (int i = 0, length = splitString.length; i < length; ++i) {
            final String s2 = splitString[i];
            String s3;
            if (++i < length) {
                s3 = splitString[i];
            }
            else {
                s3 = "";
            }
            this.pUserList.add(ClientUtil.encodeSearch(s2, s3));
        }
    }
    
    private void clickedSearch() {
        this.pUserList.removeAll();
        final String text = this.pInputFind.getText();
        this.pChat.paraConf.printer().print("search:" + text);
        if (StringUtil.isTrimmedEmpty(text)) {
            return;
        }
        this.pChat.chatModel.cmQuerySearch(text.trim());
    }
    
    private void clickedChat() {
        this.pChat.paraConf.printer().print("UserSearch, private chat");
        final String userFromSearchList = this.getUserFromSearchList();
        if (StringUtil.isEmpty(userFromSearchList)) {
            return;
        }
        this.pChat.paraConf.printer().print("US: to PC," + userFromSearchList);
        this.pChat.parentComp.startPrivate(userFromSearchList);
    }
    
    private void clickedRoom() {
        this.pChat.paraConf.printer().print("UserSearch, room");
        final String roomFromSearchList = this.getRoomFromSearchList();
        if (StringUtil.isEmpty(roomFromSearchList)) {
            return;
        }
        this.pChat.paraConf.printer().print("U S, roam," + roomFromSearchList);
        this.pControls.setVisible(false);
        this.pChat.parentComp.connRoamSwitch(roomFromSearchList);
    }
    
    private String getUserFromSearchList() {
        final String preferedLine = this.getPreferedLine();
        if (StringUtil.isEmpty(preferedLine)) {
            return null;
        }
        return ClientUtil.decodeUser(preferedLine);
    }
    
    private String getRoomFromSearchList() {
        final String preferedLine = this.getPreferedLine();
        if (StringUtil.isEmpty(preferedLine)) {
            return null;
        }
        return ClientUtil.decodeRoom(preferedLine);
    }
    
    private String getPreferedLine() {
        final int itemCount = this.pUserList.getItemCount();
        if (itemCount == 0) {
            return null;
        }
        if (itemCount == 1) {
            this.pUserList.select(0);
        }
        return this.pUserList.getSelectedItem();
    }
    
    private void buildGUI() {
        final Panel buildWest = this.buildWest();
        final Panel buildEast = this.buildEast();
        this.setLayout(new GridLayout(1, 2));
        this.add(buildWest);
        this.add(buildEast);
    }
    
    private Panel buildEast() {
        final List pUserList = new List(10);
        pUserList.addActionListener(this);
        final Font font = new Font("Dialog", 1, 11);
        final Button pChatButton = new Button("Private");
        pChatButton.setFont(font);
        pChatButton.addActionListener(this);
        final Button pRoomButton = new Button("Enter Room");
        pRoomButton.setFont(font);
        pRoomButton.addActionListener(this);
        final Panel panel = new Panel(new FlowLayout(0));
        panel.add(pChatButton);
        panel.add(pRoomButton);
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout());
        panel2.add(pUserList, "Center");
        panel2.add(panel, "North");
        this.pChatButton = pChatButton;
        this.pRoomButton = pRoomButton;
        this.pUserList = pUserList;
        return panel2;
    }
    
    private Panel buildWest() {
        final Label label = new Label("Find User:", 0);
        final Label pInfoLabel = new Label(" ", 0);
        pInfoLabel.setForeground(Color.blue);
        final TextField pInputFind = new TextField("", 16);
        pInputFind.addActionListener(this);
        final Button pSearchButton = new Button("Search");
        pSearchButton.setFont(new Font("Dialog", 1, 11));
        pSearchButton.addActionListener(this);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0));
        panel.add(pInputFind);
        panel.add(pSearchButton);
        final Panel panel2 = new Panel(new FlowLayout(0, 2, 2));
        panel2.add(label);
        panel2.add(panel);
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout());
        panel3.add(panel2, "Center");
        panel3.add(pInfoLabel, "North");
        this.pSearchButton = pSearchButton;
        this.pInputFind = pInputFind;
        this.pInfoLabel = pInfoLabel;
        return panel3;
    }
}
