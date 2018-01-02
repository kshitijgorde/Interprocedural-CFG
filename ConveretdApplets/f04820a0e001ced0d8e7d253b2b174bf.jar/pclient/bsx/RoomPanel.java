// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import pclient.bsc.BaseChat;
import java.awt.Button;
import pclient.bsc.CommonInter;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class RoomPanel extends Panel implements ActionListener, CommonInter
{
    private RoomList roomList;
    private Button refreshButton;
    private Button moreButton;
    private BaseChat pChat;
    private long lastRefreshTime;
    
    public RoomPanel() {
        this.lastRefreshTime = 0L;
    }
    
    public void process(final int n, final String[] array) {
        this.roomList.process(n, array);
    }
    
    public void setPara(final BaseChat pChat) {
        this.pChat = pChat;
        this.buildGUI();
    }
    
    public void restart() {
    }
    
    public void destroy() {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.refreshButton) {
            this.refreshRoomList();
            return;
        }
        if (actionEvent.getSource() == this.moreButton) {
            this.pChat.fireControlPanel(14, null);
        }
    }
    
    public void refreshRoomList() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastRefreshTime < 4000L) {
            this.lastRefreshTime = currentTimeMillis;
            return;
        }
        this.lastRefreshTime = currentTimeMillis;
        this.pChat.chatModel.cmQueryList();
    }
    
    private void buildGUI() {
        (this.roomList = new RoomList(60, this.pChat)).addActionListener(this);
        final Color black = Color.black;
        final Color lightGray = Color.lightGray;
        this.roomList.setForeground(black);
        this.roomList.setBackground(lightGray);
        final int n = 10;
        final String s = "Dialog";
        final Font font = new Font(s, 0, n);
        if (font != null) {
            this.roomList.setFont(font);
        }
        else {
            System.out.println("Err #472X. font," + s + " " + n);
        }
        final Panel buildButtons = this.buildButtons();
        this.setLayout(new BorderLayout(1, 1));
        this.add("Center", this.roomList);
        this.add("South", buildButtons);
    }
    
    private Panel buildButtons() {
        final String s = "Refresh";
        final String s2 = "More";
        final Font font = new Font("Dialog", 1, 10);
        this.refreshButton = new Button(s);
        this.moreButton = new Button(s2);
        this.refreshButton.setFont(font);
        this.moreButton.setFont(font);
        final Panel panel = new Panel();
        if (s.length() + s2.length() > 18) {
            panel.setLayout(new GridLayout(2, 1, 1, 1));
        }
        else {
            panel.setLayout(new FlowLayout(1, 1, 1));
        }
        if (this.pChat.paraConf.isRoam()) {
            panel.add(this.refreshButton);
            panel.add(this.moreButton);
            this.refreshButton.addActionListener(this);
            this.moreButton.addActionListener(this);
        }
        return panel;
    }
}
