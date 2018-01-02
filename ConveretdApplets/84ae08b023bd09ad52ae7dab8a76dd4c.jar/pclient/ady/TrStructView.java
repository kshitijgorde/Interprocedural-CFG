// 
// Decompiled by Procyon v0.5.30
// 

package pclient.ady;

import pclient.adv.RoomNames;
import pclient.shd.RoomItem;
import com.pchat.sc.StringUtil;
import javax.swing.tree.TreePath;
import java.util.Enumeration;
import javax.swing.tree.MutableTreeNode;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import java.awt.event.MouseListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeCellRenderer;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.SwingUtilities;
import pclient.adv.SimpleQueueItem;
import java.util.Hashtable;
import pclient.adv.SimpleBankQueue;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import pclient.adv.AppletSpice;
import pclient.adv.ComInter;
import javax.swing.JPanel;

public class TrStructView extends JPanel implements ComInter, Runnable
{
    protected AppletSpice appletChat;
    private DefaultTreeModel treeModel;
    protected JTree treeView;
    private DefaultMutableTreeNode treeRoot;
    private SimpleBankQueue nameQueue;
    protected Hashtable iconCache;
    protected Hashtable imageCache;
    private TrCallBack mouseListener;
    
    public TrStructView() {
        this.nameQueue = new SimpleBankQueue();
        this.iconCache = new Hashtable();
        this.imageCache = new Hashtable();
    }
    
    public Hashtable getAvaCache() {
        return this.iconCache;
    }
    
    public Hashtable getImgCache() {
        return this.imageCache;
    }
    
    public void run() {
        try {
            this.doTreeChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void setPara(final AppletSpice appletChat) {
        this.appletChat = appletChat;
        this.buildGUI();
    }
    
    public void process(final int n, final String[] array) {
        switch (n) {
            case 21: {
                this.clearUserList();
                break;
            }
            case 23: {
                this.refreshUserList();
                break;
            }
            case 25: {
                this.refreshUserProps();
                break;
            }
            case 27: {
                if (array.length > 0) {
                    this.addUser(array[0]);
                    break;
                }
                break;
            }
            case 29: {
                if (array.length > 0) {
                    this.deleteUser(array[0]);
                    break;
                }
                break;
            }
            case 31: {
                this.clearRoomList();
                break;
            }
            case 33: {
                if (array.length > 0) {
                    this.addRooms(array);
                    break;
                }
                break;
            }
            case 35: {
                if (array.length > 0) {
                    this.addUsersOfRoom(array);
                    break;
                }
                break;
            }
            case 37: {
                if (array.length > 1) {
                    this.userJoins(array);
                    break;
                }
                break;
            }
            case 39: {
                if (array.length > 1) {
                    this.userExits(array);
                    break;
                }
                break;
            }
            case 41: {
                if (array.length > 0) {
                    this.userCount(array);
                    break;
                }
                break;
            }
            case 43: {
                if (array.length > 0) {
                    this.deleteRoom(array);
                    break;
                }
                break;
            }
            default: {
                System.out.println("Err948." + n);
                break;
            }
        }
    }
    
    public void restart() {
    }
    
    public void destroy() {
    }
    
    public void clearUserList() {
        this.queue(6, null);
    }
    
    public void refreshUserList() {
        this.queue(8, null);
    }
    
    public void refreshUserProps() {
        this.queue(10, null);
    }
    
    public void addUser(final String s) {
        this.queue(2, s);
    }
    
    public void deleteUser(final String s) {
        this.queue(4, s);
    }
    
    private void clearRoomList() {
        this.queue(14, null);
    }
    
    private void addRooms(final String[] array) {
        this.queue(12, array);
    }
    
    private void addUsersOfRoom(final String[] array) {
        this.queue(16, array);
    }
    
    private void userJoins(final String[] array) {
        this.queue(18, array);
    }
    
    private void userExits(final String[] array) {
        this.queue(20, array);
    }
    
    private void userCount(final String[] array) {
        this.queue(22, array);
    }
    
    private void deleteRoom(final String[] array) {
        this.queue(24, array);
    }
    
    protected void checkFixTree() {
        this.queue(26, null);
    }
    
    private void queue(final int type, final Object obj) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = type;
        simpleQueueItem.obj = obj;
        this.nameQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    private void buildGUI() {
        this.mouseListener = new TrCallBack(this);
        this.treeRoot = TrCellData.createRoot("Chat");
        this.treeModel = new DefaultTreeModel(this.treeRoot);
        this.treeView = new JTree(this.treeModel);
        final JScrollPane scrollPane = new JScrollPane(this.treeView);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, "Center");
        this.treeView.setRootVisible(false);
        this.treeView.setCellRenderer(new TrCellRend(this.appletChat, this));
        this.treeView.addTreeSelectionListener(this.mouseListener);
        this.treeView.getSelectionModel().setSelectionMode(1);
        this.treeView.addMouseListener(this.mouseListener);
        this.treeView.setShowsRootHandles(true);
        ToolTipManager.sharedInstance().registerComponent(this.treeView);
        final String s = "JTree.lineStyle";
        final String value = this.appletChat.paraConf.get(s);
        if (value != null) {
            this.treeView.putClientProperty(s, value);
        }
        this.appletChat.paraConf.printer().print("fixedH=" + this.treeView.isFixedRowHeight());
    }
    
    private void doListChanges() {
    }
    
    private void doTreeChanges() {
        final SimpleQueueItem[] dequeueAll = this.nameQueue.dequeueAll();
        if (dequeueAll == null) {
            return;
        }
        for (int i = 0; i < dequeueAll.length; ++i) {
            final SimpleQueueItem simpleQueueItem = dequeueAll[i];
            switch (simpleQueueItem.type) {
                case 2: {
                    this.handleAddUserToCurrentRoom((String)simpleQueueItem.obj);
                    break;
                }
                case 4: {
                    this.handleDeleteUserFromCurrentRoom((String)simpleQueueItem.obj);
                    break;
                }
                case 8: {
                    this.handleRefreshUserList();
                    break;
                }
                case 10: {
                    this.handleRefreshUserProps();
                    break;
                }
                case 6: {
                    this.handleClearUserList();
                    break;
                }
                case 12: {
                    this.handleAddRooms((String[])simpleQueueItem.obj);
                    break;
                }
                case 14: {
                    this.handleClearRoomList();
                    break;
                }
                case 16: {
                    this.handleAddUsersOfRoom((String[])simpleQueueItem.obj);
                    break;
                }
                case 18: {
                    this.handleUserJoins((String[])simpleQueueItem.obj);
                    break;
                }
                case 20: {
                    this.handleUserExits((String[])simpleQueueItem.obj);
                    break;
                }
                case 22: {
                    this.handleUserCount((String[])simpleQueueItem.obj);
                    break;
                }
                case 24: {
                    this.handleDeleteRoom((String[])simpleQueueItem.obj);
                    break;
                }
                case 26: {
                    this.handleFixTree();
                    break;
                }
                default: {
                    System.err.println("Err79323." + simpleQueueItem);
                    break;
                }
            }
        }
    }
    
    private void handleFixTree() {
        this.deleteFromRoot(this.appletChat.roamRooms.getRoomNames(), this.appletChat.chatModel.cmRoomName(), false);
    }
    
    private void handleClearUserList() {
        final Enumeration allNodes = this.allNodes();
        while (allNodes.hasMoreElements()) {
            final DefaultMutableTreeNode defaultMutableTreeNode = allNodes.nextElement();
            if (((TrCellData)defaultMutableTreeNode.getUserObject()).type == TrCellData.T_USER) {
                this.treeModel.removeNodeFromParent(defaultMutableTreeNode);
            }
        }
    }
    
    private void handleRefreshUserList() {
        this.makeCurrentDirty();
        this.treeView.repaint();
    }
    
    private void handleRefreshUserProps() {
        this.handleRefreshUserList();
    }
    
    private void handleAddRooms(final String[] array) {
        this.deleteFromRoot(array, this.appletChat.chatModel.cmRoomName(), false);
        for (int i = 0; i < array.length; ++i) {
            if (!roomExists(array[i], this.treeRoot)) {
                this.createInsertRoom(array[i], this.treeRoot);
            }
        }
        this.appletChat.paraConf.printer().print("len=" + array.length + " h=" + this.treeRoot.getChildCount());
        this.treeView.expandPath(new TreePath(this.treeRoot));
    }
    
    private void handleClearRoomList() {
        this.removeAllFromNode(this.treeRoot, this.treeModel);
        this.appletChat.paraConf.printer().print("tree, all rooms removed");
    }
    
    private void handleAddUsersOfRoom(final String[] array) {
        this.appletChat.paraConf.printer().print("add users for room " + array);
        if (array.length != 2) {
            System.out.println("Err TR793");
            return;
        }
        final String s = array[0];
        final String[] splitString = StringUtil.splitString(array[1], "|", false);
        if (this.isCurrentRoom(s)) {
            for (int i = 0; i < splitString.length; ++i) {
                this.handleAddUserToCurrentRoom(splitString[i]);
            }
            return;
        }
        this.appletChat.paraConf.printer().print("room " + s + " users," + array[1]);
        final DefaultMutableTreeNode room = findRoom(s, this.treeRoot);
        if (room == null) {
            System.out.println("warn TR634=" + s);
            return;
        }
        this.replacingUsersOfRoom(splitString, room);
    }
    
    private void handleAddUserToCurrentRoom(final String s) {
        if (findRoom(this.appletChat.chatModel.cmRoomName(), this.treeRoot) == null) {
            this.appendRoom(this.appletChat.chatModel.cmRoomName());
        }
        final DefaultMutableTreeNode room = findRoom(this.appletChat.chatModel.cmRoomName(), this.treeRoot);
        if (room == null) {
            System.out.println("err4732." + s + "," + this.appletChat.chatModel.cmRoomName());
            return;
        }
        this.appletChat.paraConf.printer().print("adding " + s + " to room " + room);
        synchronized (this.treeModel) {
            if (userExists(s, room)) {
                this.appletChat.paraConf.printer().print("name found already, " + s);
                return;
            }
            this.appletChat.paraConf.printer().print("add " + s);
            addUserNameToCurrentRoom(s, room, this.appletChat, this.treeModel);
        }
    }
    
    private void handleDeleteUserFromCurrentRoom(final String s) {
        this.appletChat.paraConf.printer().print("delete user " + s);
        final DefaultMutableTreeNode room = findRoom(this.appletChat.chatModel.cmRoomName(), this.treeRoot);
        if (room == null) {
            System.out.println("Alert782." + s + "," + this.appletChat.chatModel.cmRoomName());
            return;
        }
        this.removeUser(s, room);
    }
    
    private void handleUserJoins(final String[] array) {
        this.appletChat.paraConf.printer().print("user joins room " + array);
        if (array.length != 2) {
            System.out.println("Err TR721");
            return;
        }
        final String s = array[0];
        final String s2 = array[1];
        this.appletChat.paraConf.printer().print("add user " + s2 + "." + s);
        if (this.isCurrentRoom(s)) {
            this.handleAddUserToCurrentRoom(s2);
            return;
        }
        this.appletChat.paraConf.printer().print("join room " + s + " user," + s2);
        DefaultMutableTreeNode defaultMutableTreeNode = findRoom(s, this.treeRoot);
        if (defaultMutableTreeNode == null) {
            defaultMutableTreeNode = this.createInsertRoom(s, this.treeRoot);
        }
        this.addUserToRoom(s2, defaultMutableTreeNode, this.treeModel);
    }
    
    private void handleUserExits(final String[] array) {
        this.appletChat.paraConf.printer().print("user leaves room " + array);
        if (array.length != 2) {
            System.out.println("Err TR723");
            return;
        }
        final String s = array[0];
        final String s2 = array[1];
        this.appletChat.paraConf.printer().print("exit, room " + s + " user," + s2);
        if (this.isCurrentRoom(s)) {
            this.handleDeleteUserFromCurrentRoom(s2);
            return;
        }
        final DefaultMutableTreeNode room = findRoom(s, this.treeRoot);
        if (room == null) {
            System.out.println("alert8945=" + s2 + "," + s);
            return;
        }
        this.removeUser(s2, room);
    }
    
    private void handleUserCount(final String[] array) {
        this.appletChat.paraConf.printer().print("user count " + array);
        if (array.length != 1) {
            System.out.println("Err TR725");
            return;
        }
        final String s = array[0];
        this.appletChat.paraConf.printer().print("user count, room " + s);
        if (this.isCurrentRoom(s)) {
            return;
        }
        if (findRoom(s, this.treeRoot) == null) {
            System.out.println("alert8932=" + s);
            this.createInsertRoom(s, this.treeRoot);
        }
    }
    
    private void handleDeleteRoom(final String[] array) {
        this.appletChat.paraConf.printer().print("delete room " + array);
        if (array.length != 1) {
            System.out.println("Err TR727");
            return;
        }
        final String s = array[0];
        this.appletChat.paraConf.printer().print("del, room " + s);
        if (this.isCurrentRoom(s)) {
            return;
        }
        this.removeRoom(s);
    }
    
    private void removeRoom(final String s) {
        this.appletChat.paraConf.printer().print("removing room " + s);
        final DefaultMutableTreeNode room = findRoom(s, this.treeRoot);
        if (room == null) {
            System.out.println("warn823=" + s);
            return;
        }
        this.treeModel.removeNodeFromParent(room);
        this.appletChat.paraConf.printer().print("room removed " + s);
    }
    
    private void removeUser(final String s, final DefaultMutableTreeNode defaultMutableTreeNode) {
        this.appletChat.paraConf.printer().print("removing user " + s + " from " + defaultMutableTreeNode);
        final DefaultMutableTreeNode user = findUser(s, defaultMutableTreeNode);
        if (user == null) {
            System.out.println("Warn823." + s);
            return;
        }
        this.treeModel.removeNodeFromParent(user);
        this.appletChat.paraConf.printer().print("user removed " + s);
    }
    
    private boolean userExists(final String s, final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (s.equals(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    private void deleteFromRoot(final String[] array, final String s, final boolean b) {
        this.appletChat.paraConf.printer().print("check room nodes.");
        for (int i = this.treeRoot.getChildCount() - 1; i >= 0; --i) {
            final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.treeRoot.getChildAt(i);
            final TrCellData trCellData = (TrCellData)defaultMutableTreeNode.getUserObject();
            if (trCellData.type != TrCellData.T_ROOM) {
                System.out.println("error792=" + trCellData.name);
            }
            else {
                this.appletChat.paraConf.printer().print("check to delete=" + trCellData.name);
                if (!RoomItem.sameRoom(trCellData.name, s)) {
                    if (!RoomNames.roomOnList(trCellData.name, array)) {
                        this.treeModel.removeNodeFromParent(defaultMutableTreeNode);
                        if (b) {
                            System.out.println("deleting rnode=" + trCellData.name);
                        }
                    }
                }
            }
        }
    }
    
    private DefaultMutableTreeNode createInsertRoom(final String s, final DefaultMutableTreeNode defaultMutableTreeNode) {
        RoomItem lookupRoom = this.appletChat.roamRooms.lookupRoom(s);
        if (lookupRoom == null) {
            System.out.println("WARN872=" + s);
            lookupRoom = new RoomItem(s, 0);
            lookupRoom.secondary = true;
        }
        for (int childCount = defaultMutableTreeNode.getChildCount(), i = 0; i < childCount; ++i) {
            final DefaultMutableTreeNode defaultMutableTreeNode2 = (DefaultMutableTreeNode)defaultMutableTreeNode.getChildAt(i);
            final TrCellData trCellData = (TrCellData)defaultMutableTreeNode2.getUserObject();
            final RoomItem lookupRoom2 = this.appletChat.roamRooms.lookupRoom(trCellData.name);
            if (lookupRoom2 == null) {
                System.out.println("WARN879=" + s + "," + trCellData.name);
            }
            else {
                final int compareInTreeView = lookupRoom2.compareInTreeView(lookupRoom);
                if (compareInTreeView >= 0) {
                    if (compareInTreeView == 0) {
                        System.out.println("Err8823=" + s);
                        return defaultMutableTreeNode2;
                    }
                    return insertRoom(s, i, this.treeModel, defaultMutableTreeNode);
                }
            }
        }
        return this.appendRoom(s);
    }
    
    private static DefaultMutableTreeNode insertRoom(final String s, final int n, final DefaultTreeModel defaultTreeModel, final DefaultMutableTreeNode defaultMutableTreeNode) {
        final DefaultMutableTreeNode room = TrCellData.createRoom(s);
        defaultTreeModel.insertNodeInto(room, defaultMutableTreeNode, n);
        return room;
    }
    
    private static void insertUser(final DefaultTreeModel defaultTreeModel, final String s, final int n, final DefaultMutableTreeNode defaultMutableTreeNode) {
        defaultTreeModel.insertNodeInto(TrCellData.createUser(s), defaultMutableTreeNode, n);
    }
    
    private void replacingUsersOfRoom(final String[] array, final DefaultMutableTreeNode defaultMutableTreeNode) {
        this.deleteFromRoom(array, defaultMutableTreeNode);
        int n = -1;
        for (int i = 0; i < array.length; ++i) {
            ++n;
            if (n >= defaultMutableTreeNode.getChildCount()) {
                appendUser(array[i], defaultMutableTreeNode, this.treeModel);
                this.appletChat.paraConf.printer().print("append " + array[i]);
            }
            else if (array[i].equals(((TrCellData)((DefaultMutableTreeNode)defaultMutableTreeNode.getChildAt(n)).getUserObject()).name)) {
                this.appletChat.paraConf.printer().print("User exists " + array[i]);
            }
            else {
                insertUser(this.treeModel, array[i], n, defaultMutableTreeNode);
                this.appletChat.paraConf.printer().print("ins " + array[i]);
            }
        }
        this.appletChat.paraConf.printer().print("len=" + array.length + " h=" + defaultMutableTreeNode.getChildCount());
    }
    
    private void deleteFromRoom(final String[] array, final DefaultMutableTreeNode defaultMutableTreeNode) {
        for (int i = defaultMutableTreeNode.getChildCount() - 1; i >= 0; --i) {
            final DefaultMutableTreeNode defaultMutableTreeNode2 = (DefaultMutableTreeNode)defaultMutableTreeNode.getChildAt(i);
            if (!this.userExists(((TrCellData)defaultMutableTreeNode2.getUserObject()).name, array)) {
                this.treeModel.removeNodeFromParent(defaultMutableTreeNode2);
            }
        }
    }
    
    private void makeCurrentDirty() {
        final String cmRoomName = this.appletChat.chatModel.cmRoomName();
        if (cmRoomName == null) {
            System.out.println("alert 7892.");
            return;
        }
        final DefaultMutableTreeNode room = findRoom(cmRoomName, this.treeRoot);
        if (room == null) {
            return;
        }
        for (int childCount = room.getChildCount(), i = 0; i < childCount; ++i) {
            ((TrCellData)((DefaultMutableTreeNode)room.getChildAt(i)).getUserObject()).dirty = true;
        }
    }
    
    public boolean isUserInCurrentRoom(final DefaultMutableTreeNode defaultMutableTreeNode) {
        final DefaultMutableTreeNode defaultMutableTreeNode2 = (DefaultMutableTreeNode)defaultMutableTreeNode.getParent();
        if (defaultMutableTreeNode2 == null) {
            System.out.println("err6838");
            return false;
        }
        final TrCellData trCellData = (TrCellData)defaultMutableTreeNode2.getUserObject();
        if (trCellData.type != TrCellData.T_ROOM) {
            System.out.println("err7835");
            return false;
        }
        return RoomItem.sameRoom(trCellData.name, this.appletChat.chatModel.cmRoomName());
    }
    
    private void removeAllFromNode(final DefaultMutableTreeNode defaultMutableTreeNode, final DefaultTreeModel defaultTreeModel) {
        for (int i = defaultMutableTreeNode.getChildCount() - 1; i >= 0; --i) {
            defaultTreeModel.removeNodeFromParent((MutableTreeNode)defaultMutableTreeNode.getChildAt(i));
        }
    }
    
    private void addUsers(final String[] array, final DefaultMutableTreeNode defaultMutableTreeNode) {
        for (int i = 0; i < array.length; ++i) {
            appendUser(array[i], defaultMutableTreeNode, this.treeModel);
        }
    }
    
    private void addUserToRoom(final String s, final DefaultMutableTreeNode defaultMutableTreeNode, final DefaultTreeModel defaultTreeModel) {
        final int childCount = defaultMutableTreeNode.getChildCount();
        int i = 0;
        while (i < childCount) {
            final int compareUserName = ((TrCellData)((DefaultMutableTreeNode)defaultMutableTreeNode.getChildAt(i)).getUserObject()).compareUserName(s);
            if (compareUserName < 0) {
                ++i;
            }
            else {
                if (compareUserName == 0) {
                    return;
                }
                insertUser(defaultTreeModel, s, i, defaultMutableTreeNode);
                return;
            }
        }
        appendUser(s, defaultMutableTreeNode, defaultTreeModel);
    }
    
    private static void appendUser(final String s, final DefaultMutableTreeNode defaultMutableTreeNode, final DefaultTreeModel defaultTreeModel) {
        defaultTreeModel.insertNodeInto(TrCellData.createUser(s), defaultMutableTreeNode, defaultMutableTreeNode.getChildCount());
    }
    
    private boolean isCurrentRoom(final String s) {
        return s.equals(this.appletChat.chatModel.cmRoomName());
    }
    
    private static void addUserNameToCurrentRoom(final String s, final DefaultMutableTreeNode defaultMutableTreeNode, final AppletSpice appletSpice, final DefaultTreeModel defaultTreeModel) {
        final int childCount = defaultMutableTreeNode.getChildCount();
        int i = 0;
        while (i < childCount) {
            final int cmCompare = appletSpice.chatModel.cmCompare(s, ((TrCellData)((DefaultMutableTreeNode)defaultMutableTreeNode.getChildAt(i)).getUserObject()).name);
            if (cmCompare > 0) {
                ++i;
            }
            else {
                if (cmCompare == 0) {
                    return;
                }
                insertUser(defaultTreeModel, s, i, defaultMutableTreeNode);
                return;
            }
        }
        appendUser(s, defaultMutableTreeNode, defaultTreeModel);
    }
    
    private static boolean userExists(final String s, final DefaultMutableTreeNode defaultMutableTreeNode) {
        return findUser(s, defaultMutableTreeNode) != null;
    }
    
    private static DefaultMutableTreeNode findUser(final String s, final DefaultMutableTreeNode defaultMutableTreeNode) {
        final Enumeration depthFirstEnumeration = defaultMutableTreeNode.depthFirstEnumeration();
        while (depthFirstEnumeration.hasMoreElements()) {
            final DefaultMutableTreeNode defaultMutableTreeNode2 = depthFirstEnumeration.nextElement();
            final TrCellData trCellData = (TrCellData)defaultMutableTreeNode2.getUserObject();
            if (trCellData.type == TrCellData.T_USER && s.equals(trCellData.name)) {
                return defaultMutableTreeNode2;
            }
        }
        return null;
    }
    
    private static boolean roomExists(final String s, final DefaultMutableTreeNode defaultMutableTreeNode) {
        return findRoom(s, defaultMutableTreeNode) != null;
    }
    
    private static DefaultMutableTreeNode findRoom(final String s, final DefaultMutableTreeNode defaultMutableTreeNode) {
        for (int childCount = defaultMutableTreeNode.getChildCount(), i = 0; i < childCount; ++i) {
            final DefaultMutableTreeNode defaultMutableTreeNode2 = (DefaultMutableTreeNode)defaultMutableTreeNode.getChildAt(i);
            final TrCellData trCellData = (TrCellData)defaultMutableTreeNode2.getUserObject();
            if (trCellData.type != TrCellData.T_ROOM) {
                System.out.println("error792=" + trCellData.name);
            }
            else if (RoomItem.sameRoom(s, trCellData.name)) {
                return defaultMutableTreeNode2;
            }
        }
        return null;
    }
    
    private Enumeration allNodes() {
        return this.treeRoot.depthFirstEnumeration();
    }
    
    private DefaultMutableTreeNode appendRoom(final String s) {
        final DefaultMutableTreeNode room = TrCellData.createRoom(RoomItem.simpleForm(s));
        this.treeModel.insertNodeInto(room, this.treeRoot, this.treeRoot.getChildCount());
        return room;
    }
}
