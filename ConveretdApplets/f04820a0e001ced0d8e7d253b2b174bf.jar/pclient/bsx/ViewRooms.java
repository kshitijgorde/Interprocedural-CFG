// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import pclient.shd.RoomItem;
import com.pchat.sc.StringUtil;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.List;
import java.awt.Label;
import pclient.bsc.BaseChat;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class ViewRooms extends Panel implements ActionListener, ItemListener
{
    private BaseChat pChat;
    private ControlPanel pControls;
    private Label pInfoLabel;
    private RoomList pRoomList;
    private List pUserList;
    private Button pJoinRoom;
    private Button pRefreshButton;
    
    public ViewRooms(final BaseChat pChat, final ControlPanel pControls) {
        this.pChat = pChat;
        this.pControls = pControls;
        this.buildGUI();
    }
    
    public void process(final int n, final String[] array) {
        this.pChat.paraConf.printer().print("View Rooms," + n + " " + array);
        if (n == 204) {
            if (array.length > 1) {
                this.roamServerUsers(array[0], array[1]);
            }
            return;
        }
        this.pRoomList.process(n, array);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pRoomList) {
            this.clickedRoom();
        }
        else if (actionEvent.getSource() == this.pUserList) {
            this.clickedUser();
        }
        else if (actionEvent.getSource() == this.pJoinRoom) {
            this.clickedRoom();
        }
        else if (actionEvent.getSource() == this.pRefreshButton) {
            this.clickedRefresh();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.pRoomList) {
            this.selectedRoom();
        }
    }
    
    private void selectedRoom() {
        final String selectedItem = this.pRoomList.getSelectedItem();
        if (StringUtil.isEmpty(selectedItem)) {
            return;
        }
        this.pChat.chatModel.cmQueryUsers(RoomItem.decodeSimple(selectedItem.trim()));
    }
    
    private void clickedRoom() {
        final String selectedItem = this.pRoomList.getSelectedItem();
        if (StringUtil.isEmpty(selectedItem)) {
            return;
        }
        final String decodeSimple = RoomItem.decodeSimple(selectedItem);
        if (StringUtil.isEmpty(decodeSimple)) {
            return;
        }
        this.pControls.setVisible(false);
        this.pChat.parentComp.connRoamSwitch(decodeSimple);
    }
    
    private void clickedUser() {
        final String selectedItem = this.pUserList.getSelectedItem();
        if (StringUtil.isEmpty(selectedItem)) {
            return;
        }
        this.pChat.parentComp.startPrivate(selectedItem);
    }
    
    private void clickedRefresh() {
        this.pChat.chatModel.cmQueryList();
    }
    
    private void roamServerUsers(final String s, final String s2) {
        final String selectedItem = this.pRoomList.getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        if (!RoomItem.decodeSimple(selectedItem).equals(s)) {
            return;
        }
        this.pUserList.removeAll();
        final String[] splitString = StringUtil.splitString(s2, "|", false);
        for (int i = 0; i < splitString.length; ++i) {
            this.pUserList.add(splitString[i]);
        }
    }
    
    private void buildGUI() {
        final Panel buildCenter = this.buildCenter();
        final Panel buildBottom = this.buildBottom();
        this.setLayout(new BorderLayout(2, 1));
        this.add(buildCenter, "Center");
        this.add(buildBottom, "South");
    }
    
    private Panel buildLabel() {
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(new Label(" "), "Center");
        return panel;
    }
    
    private Panel buildCenter() {
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2, 2, 1));
        panel.add(this.buildRooms());
        panel.add(this.buildUsers());
        return panel;
    }
    
    private Panel buildRooms() {
        final Label label = new Label();
        label.setFont(new Font("Dialog", 1, 9));
        label.setText("Room/User Count");
        final RoomList pRoomList = new RoomList(8, this.pChat);
        pRoomList.addItemListener(this);
        pRoomList.addActionListener(this);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(label, "North");
        panel.add(pRoomList, "Center");
        this.pRoomList = pRoomList;
        return panel;
    }
    
    private Panel buildUsers() {
        final List pUserList = new List(8);
        pUserList.setFont(new Font("Dialog", 0, 10));
        pUserList.addActionListener(this);
        final Label label = new Label();
        label.setFont(new Font("Dialog", 1, 9));
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(label, "North");
        panel.add(pUserList, "Center");
        this.pUserList = pUserList;
        return panel;
    }
    
    private Panel buildBottom() {
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(1, 2));
        panel.add("Center", this.buildButtons());
        panel.add("South", this.buildLabel());
        return panel;
    }
    
    private Panel buildButtons() {
        final Font font = new Font("Dialog", 1, 11);
        final Button pJoinRoom = new Button("Join");
        pJoinRoom.addActionListener(this);
        pJoinRoom.setFont(font);
        final Button pRefreshButton = new Button("Refresh");
        pRefreshButton.addActionListener(this);
        pRefreshButton.setFont(font);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0, 2, 2));
        panel.add(pRefreshButton);
        panel.add(pJoinRoom);
        this.pJoinRoom = pJoinRoom;
        this.pRefreshButton = pRefreshButton;
        return panel;
    }
}
