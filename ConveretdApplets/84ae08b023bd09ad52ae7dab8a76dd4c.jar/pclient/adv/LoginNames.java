// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.JMenuItem;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import com.pchat.sc.WindowUtil;
import java.awt.Color;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import java.awt.event.ActionListener;
import pclient.shd.UserAttr;
import javax.swing.SwingUtilities;
import java.util.Hashtable;
import javax.swing.JPopupMenu;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JComponent;

public class LoginNames implements Runnable
{
    protected int listHeight;
    private JComponent mainComp;
    protected JList nameList;
    private DefaultListModel listModel;
    protected JPopupMenu ignorePop;
    protected JPopupMenu unignorePop;
    private NamesMenu mouseListener;
    protected AppletSpice appletChat;
    private SimpleBankQueue nameQueue;
    protected Hashtable iconCache;
    
    public LoginNames(final AppletSpice appletChat) {
        this.listHeight = 12;
        this.appletChat = appletChat;
        this.nameQueue = new SimpleBankQueue();
        this.iconCache = new Hashtable();
        this.buildUI();
    }
    
    public void run() {
        try {
            this.doNameChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected JComponent getBox() {
        return this.mainComp;
    }
    
    protected String[] userNames() {
        final String[] array;
        synchronized (this.listModel) {
            final int size = this.listModel.size();
            array = new String[size];
            for (int i = 0; i < size; ++i) {
                array[i] = ((CellData)this.listModel.get(i)).username;
            }
        }
        return array;
    }
    
    protected String selectedUser() {
        final int selectedIndex = this.nameList.getSelectedIndex();
        if (selectedIndex < 0) {
            return null;
        }
        final CellData index = this.getIndex(selectedIndex);
        if (index == null) {
            return null;
        }
        return index.username;
    }
    
    private void queue(final int type, final Object obj) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = type;
        simpleQueueItem.obj = obj;
        this.nameQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    public void clearAll() {
        this.queue(6, null);
    }
    
    private void handleClear() {
        this.listModel.clear();
    }
    
    public void addName(final String s) {
        this.queue(2, s);
    }
    
    public void addNames(final String[] array) {
        this.queue(12, array);
    }
    
    private void handleAddUser(final String username) {
        synchronized (this.listModel) {
            if (this.nameExists(username)) {
                this.appletChat.paraConf.printer().print("name found already, " + username);
                return;
            }
            this.appletChat.paraConf.printer().print("add " + username);
            final CellData props = new CellData();
            props.username = username;
            this.setProps(props);
            this.addUserName(props);
        }
    }
    
    private void handleAddUsers(final String[] array) {
        synchronized (this.listModel) {
            for (int i = 0; i < array.length; ++i) {
                final String username = array[i];
                if (this.nameExists(username)) {
                    this.appletChat.paraConf.printer().print("name found, " + username);
                }
                else {
                    this.appletChat.paraConf.printer().print("add:" + username);
                    final CellData props = new CellData();
                    props.username = username;
                    this.setProps(props);
                    this.addUserName(props);
                }
            }
        }
    }
    
    private void addUserName(final CellData cellData) {
        final String username = cellData.username;
        final int size = this.listModel.size();
        int i = 0;
        while (i < size) {
            final int cmCompare = this.appletChat.chatModel.cmCompare(username, this.listModel.get(i).username);
            if (cmCompare > 0) {
                ++i;
            }
            else {
                if (cmCompare == 0) {
                    return;
                }
                this.listModel.add(i, cellData);
                this.appletChat.paraConf.printer().print("add: " + username + " at " + i);
                return;
            }
        }
        this.appletChat.paraConf.printer().print("add: " + username + " at end " + i);
        this.listModel.addElement(cellData);
    }
    
    private void setProps(final CellData cellData) {
        final String username = cellData.username;
        final UserAttr cmUserAttr = this.appletChat.chatModel.cmUserAttr(username);
        if (cmUserAttr == null) {
            System.out.println("Err87920." + username);
            return;
        }
        cellData.dirty = true;
        if (this.appletChat.chatModel.cmLocalIsBcaster(username)) {
            cmUserAttr.foreground = this.appletChat.paraConf.getPref().spkColor;
        }
        else if (this.appletChat.chatModel.cmLocalIsSpk(username)) {
            cmUserAttr.foreground = this.appletChat.paraConf.getPref().spkColor;
        }
        else if (this.appletChat.chatModel.cmLocalIsMod(username)) {
            cmUserAttr.foreground = this.appletChat.paraConf.getPref().modColor;
        }
        else if (this.appletChat.chatModel.cmLocalIsAdmin(username)) {
            cmUserAttr.foreground = this.appletChat.paraConf.getPref().adminColor;
        }
        else if (this.appletChat.chatModel.cmIsSelf(username)) {
            cmUserAttr.foreground = this.appletChat.paraConf.getPref().selfColor;
        }
        else {
            cmUserAttr.foreground = this.appletChat.paraConf.getPref().userColor;
        }
    }
    
    protected void refreshProps() {
        this.queue(10, null);
    }
    
    protected void refreshUser(final String s) {
        final CellData find = this.find(s);
        if (find != null) {
            this.setProps(find);
        }
    }
    
    private void handleRefreshProps() {
        synchronized (this.listModel) {
            for (int size = this.listModel.size(), i = 0; i < size; ++i) {
                this.setProps((CellData)this.listModel.get(i));
            }
            this.sortModel();
        }
    }
    
    private void sortModel() {
        final Object[] array = this.listModel.toArray();
        this.listModel.removeAllElements();
        for (int i = 0; i < array.length; ++i) {
            this.addUserName((CellData)array[i]);
        }
    }
    
    public void refresh() {
        final Object[] array = this.listModel.toArray();
        for (int i = 0; i < array.length; ++i) {
            ((CellData)array[i]).dirty = true;
        }
        this.queue(8, null);
    }
    
    private void handleRefresh() {
        this.nameList.repaint();
        this.nameList.updateUI();
    }
    
    public CellData getIndex(final int n) {
        final CellData cellData;
        synchronized (this.listModel) {
            if (n < 0 || n >= this.listModel.size()) {
                return null;
            }
            cellData = this.listModel.get(n);
        }
        return cellData;
    }
    
    public void deleteName(final String s) {
        this.queue(4, s);
    }
    
    private void handleDeleteUser(final String s) {
        synchronized (this.listModel) {
            final int index = this.findIndex(s);
            if (index < 0) {
                return;
            }
            this.listModel.remove(index);
        }
    }
    
    private void buildUI() {
        this.mouseListener = new NamesMenu(this);
        this.ignorePop = createIgnore(this.mouseListener, this.appletChat);
        this.unignorePop = createUnIgnore(this.mouseListener, this.appletChat);
        this.listModel = new DefaultListModel();
        this.nameList = new JList(this.listModel);
        final JScrollPane mainComp = new JScrollPane(this.nameList);
        mainComp.getViewport().setScrollMode(2);
        final UserNameCell cellRenderer = new UserNameCell(this);
        this.nameList.setCellRenderer(cellRenderer);
        this.nameList.setSelectionMode(0);
        final String value = this.appletChat.paraConf.get("Col.UList.Bg");
        if (value != null) {
            final Color color = WindowUtil.parseColor(value, Color.WHITE);
            this.nameList.setBackground(color);
            cellRenderer.setBackground(color);
        }
        this.listHeight = this.appletChat.paraConf.getInt("Sz.UserH", 0);
        if (this.listHeight <= 0) {
            this.listHeight = 12;
        }
        cellRenderer.setMinimumSize(new Dimension(20, this.listHeight));
        cellRenderer.setPreferredSize(new Dimension(20, this.listHeight));
        this.appletChat.paraConf.printer().print("#### cell prefer, " + cellRenderer.getPreferredSize());
        this.nameList.addMouseListener(this.mouseListener);
        this.mainComp = mainComp;
    }
    
    public static JPopupMenu createIgnore(final ActionListener actionListener, final AppletSpice appletSpice) {
        final JPopupMenu popupMenu = new JPopupMenu();
        addFirstFew(popupMenu, actionListener, appletSpice);
        if (appletSpice.paraConf.getBool("Add.Bt.Ig", true)) {
            genItem("Pm.Ignore", "Ignore", actionListener, popupMenu, "IG", appletSpice);
        }
        addLastFew(popupMenu, actionListener, appletSpice);
        return popupMenu;
    }
    
    public static JPopupMenu createUnIgnore(final ActionListener actionListener, final AppletSpice appletSpice) {
        final JPopupMenu popupMenu = new JPopupMenu();
        addFirstFew(popupMenu, actionListener, appletSpice);
        if (appletSpice.paraConf.getBool("Add.Bt.Ig", true)) {
            genItem("Pm.Unignore", "Unignore", actionListener, popupMenu, "IG", appletSpice);
        }
        addLastFew(popupMenu, actionListener, appletSpice);
        return popupMenu;
    }
    
    private static void addFirstFew(final JPopupMenu popupMenu, final ActionListener actionListener, final AppletSpice appletSpice) {
        genItem("Pm.Private", "Private Chat", actionListener, popupMenu, "PV", appletSpice);
        if (appletSpice.paraConf.getBool("Add.Bt.Prof", true)) {
            genItem("Pm.Profile", "See Profile", actionListener, popupMenu, "ST", appletSpice);
        }
    }
    
    private static void addLastFew(final JPopupMenu popupMenu, final ActionListener actionListener, final AppletSpice appletSpice) {
        if (appletSpice.paraConf.getBool("Ctrl.Av", false)) {
            if (appletSpice.paraConf.isAudioOnly()) {
                genItem("Tip.Av", "Start Private Voice Chat With This User", actionListener, popupMenu, "Tip.Av", appletSpice);
            }
            else {
                genItem("Tip.Av.Vid", "Start Private Voice/Video Chat With This User", actionListener, popupMenu, "Tip.Av", appletSpice);
            }
        }
        boolean bool = appletSpice.paraConf.getBool("Add.Bt.Vw", true);
        if (!appletSpice.paraConf.getBool("Ctrl.Av", false)) {
            bool = false;
        }
        if (bool) {
            if (appletSpice.paraConf.isAudioOnly()) {
                genItem("Mn.VwVid", "Listen To This User's Voice Chat Broadcast", actionListener, popupMenu, "VB", appletSpice);
            }
            else {
                genItem("Mn.VwVid.Vid", "View This User's Audio/Video Broadcast", actionListener, popupMenu, "VB", appletSpice);
            }
        }
    }
    
    private static JMenuItem genItem(final String s, final String s2, final ActionListener actionListener, final JPopupMenu popupMenu, final String actionCommand, final AppletSpice appletSpice) {
        final JMenuItem menuItem = new JMenuItem(appletSpice.paraConf.get(s, s2));
        menuItem.addActionListener(actionListener);
        menuItem.setActionCommand(actionCommand);
        popupMenu.add(menuItem);
        return menuItem;
    }
    
    protected void singleClick(final int n) {
    }
    
    protected void doubleClick(final int n) {
        final CellData index = this.getIndex(n);
        if (index == null) {
            return;
        }
        this.appletChat.startPrivate(index.username);
    }
    
    private boolean nameExists(final String s) {
        return this.find(s) != null;
    }
    
    private CellData find(final String s) {
        synchronized (this.listModel) {
            for (int i = 0; i < this.listModel.size(); ++i) {
                final CellData cellData = this.listModel.get(i);
                if (cellData.username.equals(s)) {
                    return cellData;
                }
            }
        }
        return null;
    }
    
    private int findIndex(final String s) {
        synchronized (this.listModel) {
            for (int i = 0; i < this.listModel.size(); ++i) {
                if (((CellData)this.listModel.get(i)).username.equals(s)) {
                    return i;
                }
            }
        }
        return -1;
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
                    this.handleAddUser((String)simpleQueueItem.obj);
                    break;
                }
                case 12: {
                    this.handleAddUsers((String[])simpleQueueItem.obj);
                    break;
                }
                case 4: {
                    this.handleDeleteUser((String)simpleQueueItem.obj);
                    break;
                }
                case 8: {
                    this.handleRefresh();
                    break;
                }
                case 10: {
                    this.handleRefreshProps();
                    break;
                }
                case 6: {
                    this.handleClear();
                    break;
                }
                default: {
                    System.err.println("Err94249," + simpleQueueItem);
                    break;
                }
            }
        }
    }
}
