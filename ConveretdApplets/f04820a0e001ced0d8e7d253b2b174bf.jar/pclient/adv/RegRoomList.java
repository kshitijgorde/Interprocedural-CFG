// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import com.pchat.sc.WindowUtil;
import java.awt.Color;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import com.pchat.sc.StringUtil;
import pclient.shd.RoomItem;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JComponent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

public class RegRoomList implements Runnable, ActionListener, MouseListener
{
    private JComponent mainComp;
    private JList roomList;
    private DefaultListModel listModel;
    private JPopupMenu rightMenu;
    protected AppletSpice appletChat;
    private SimpleBankQueue nameQueue;
    public boolean clickForUsers;
    
    public RegRoomList(final AppletSpice appletChat) {
        this.clickForUsers = false;
        this.appletChat = appletChat;
        this.nameQueue = new SimpleBankQueue();
        this.buildUI();
    }
    
    public int getSelectedIndex() {
        return this.roomList.getSelectedIndex();
    }
    
    public String get(final int n) {
        return this.listModel.get(n);
    }
    
    public JComponent getBox() {
        return this.mainComp;
    }
    
    public void clearAll() {
        this.queue(6, null);
    }
    
    public void addRoom(final String s) {
        this.queue(2, s);
    }
    
    public void deleteRoom(final String s) {
        this.queue(4, s);
    }
    
    public void replaceRooms() {
        this.queue(10, null);
    }
    
    public void refreshList() {
        this.queue(8, null);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if ("jn".equals(actionCommand)) {
            this.roamRoom();
        }
        else if ("Rf".equals(actionCommand)) {
            this.appletChat.chatModel.cmQueryList();
        }
    }
    
    public void run() {
        try {
            this.doNameChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.roomList.locationToIndex(mouseEvent.getPoint());
        if (mouseEvent.getClickCount() == 1) {
            this.singleClick();
            return;
        }
        if (mouseEvent.getClickCount() == 2) {
            this.roamRoom();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.maybeShowPopup(mouseEvent);
    }
    
    private void queue(final int type, final Object obj) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = type;
        simpleQueueItem.obj = obj;
        this.nameQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    private void handleClear() {
        this.listModel.clear();
    }
    
    private void handleRefresh() {
        this.roomList.repaint();
    }
    
    private void handleReplaceRooms() {
        this.handleClear();
        final String[] roomNames = this.appletChat.roamRooms.getRoomNames();
        synchronized (this.listModel) {
            for (int i = 0; i < roomNames.length; ++i) {
                this.listModel.addElement(roomNames[i]);
            }
        }
    }
    
    protected void checkFixTree() {
        this.queue(12, null);
    }
    
    private void handleAddRoom(final String s) {
        synchronized (this.listModel) {
            if (this.nameExists(s)) {
                return;
            }
            this.insertRoom(s);
        }
    }
    
    private void handleDeleteRoom(final String s) {
        synchronized (this.listModel) {
            if (!this.nameExists(s)) {
                return;
            }
            this.removeRoom(s);
        }
    }
    
    private void handleFixTree() {
        final String[] roomNames = this.appletChat.roamRooms.getRoomNames();
        synchronized (this.listModel) {
            for (int i = this.listModel.size() - 1; i >= 0; --i) {
                if (!RoomNames.roomOnList((String)this.listModel.get(i), roomNames)) {
                    this.listModel.removeElementAt(i);
                }
            }
        }
    }
    
    private void insertRoom(final String s) {
        final RoomItem lookupRoom = this.appletChat.roamRooms.lookupRoom(s);
        if (lookupRoom == null) {
            System.out.println("WARN853=" + s);
            return;
        }
        for (int size = this.listModel.size(), i = 0; i < size; ++i) {
            final String s2 = this.listModel.get(i);
            final RoomItem lookupRoom2 = this.appletChat.roamRooms.lookupRoom(s2);
            if (lookupRoom2 == null) {
                System.out.println("WARN857=" + s + "," + s2);
            }
            else {
                final int compareInTreeView = lookupRoom2.compareInTreeView(lookupRoom);
                if (compareInTreeView >= 0) {
                    if (compareInTreeView == 0) {
                        System.out.println("Err8526=" + s);
                        return;
                    }
                    this.listModel.add(i, s);
                    return;
                }
            }
        }
        this.listModel.addElement(s);
    }
    
    private boolean removeRoom(final String s) {
        for (int size = this.listModel.size(), i = 0; i < size; ++i) {
            if (RoomItem.sameRoom((String)this.listModel.get(i), s)) {
                this.listModel.remove(i);
                return true;
            }
        }
        return false;
    }
    
    private void roamRoom() {
        final int selectedIndex = this.roomList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        final String s = this.listModel.get(selectedIndex);
        if (StringUtil.isEmpty(s)) {
            return;
        }
        this.appletChat.connRoamSwitch(RoomItem.simpleForm(s));
    }
    
    private void buildUI() {
        this.rightMenu = RoomNames.createPop(this, this.appletChat);
        this.listModel = new DefaultListModel();
        this.roomList = new JList(this.listModel);
        final JScrollPane mainComp = new JScrollPane(this.roomList);
        mainComp.setOpaque(true);
        mainComp.getViewport().setScrollMode(0);
        final RoomCell cellRenderer = new RoomCell(this.appletChat, this);
        this.roomList.setCellRenderer(cellRenderer);
        this.roomList.setSelectionMode(0);
        this.roomList.addMouseListener(this);
        final String value = this.appletChat.paraConf.get("Col.RmList.Bg");
        if (value != null) {
            final Color color = WindowUtil.parseColor(value, Color.WHITE);
            this.roomList.setBackground(color);
            cellRenderer.setBackground(color);
        }
        this.mainComp = mainComp;
    }
    
    private void singleClick() {
        if (!this.clickForUsers) {
            return;
        }
        final int selectedIndex = this.roomList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        final String s = this.listModel.get(selectedIndex);
        if (StringUtil.isEmpty(s)) {
            return;
        }
        this.appletChat.chatModel.cmQueryUsers(RoomItem.simpleForm(s));
    }
    
    private boolean nameExists(final String s) {
        return this.find(s) != null;
    }
    
    private String find(final String s) {
        synchronized (this.listModel) {
            for (int i = 0; i < this.listModel.size(); ++i) {
                final String s2 = this.listModel.get(i);
                if (RoomItem.sameRoom(s2, s)) {
                    return s2;
                }
            }
        }
        return null;
    }
    
    private void maybeShowPopup(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            final int locationToIndex = this.roomList.locationToIndex(mouseEvent.getPoint());
            if (locationToIndex >= 0) {
                this.roomList.setSelectedIndex(locationToIndex);
            }
            this.rightMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            this.appletChat.paraConf.printer().print(" show popup on room item " + locationToIndex);
        }
    }
    
    private void doNameChanges() {
        final SimpleQueueItem[] dequeueAll = this.nameQueue.dequeueAll();
        if (dequeueAll == null) {
            return;
        }
        for (int i = 0; i < dequeueAll.length; ++i) {
            final SimpleQueueItem simpleQueueItem = dequeueAll[i];
            switch (simpleQueueItem.type) {
                case 2: {
                    this.handleAddRoom((String)simpleQueueItem.obj);
                    break;
                }
                case 4: {
                    this.handleDeleteRoom((String)simpleQueueItem.obj);
                    break;
                }
                case 6: {
                    this.handleClear();
                    break;
                }
                case 8: {
                    this.handleRefresh();
                    break;
                }
                case 10: {
                    this.handleReplaceRooms();
                    break;
                }
                case 12: {
                    this.handleFixTree();
                    break;
                }
                default: {
                    System.err.println("Err9523," + simpleQueueItem);
                    break;
                }
            }
        }
    }
}
