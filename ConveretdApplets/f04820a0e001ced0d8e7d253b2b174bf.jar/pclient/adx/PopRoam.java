// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Color;
import pclient.adv.TextFieldCopy;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.Border;
import pclient.adv.CompUtil;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;
import com.pchat.sc.StringUtil;
import pclient.shd.RoomItem;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import pclient.adv.SimpleQueueItem;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import pclient.adv.SimpleBankQueue;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import pclient.adv.RegRoomList;
import javax.swing.JLabel;
import pclient.adv.AppletSpice;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import pclient.adv.ComInter;
import javax.swing.JFrame;

public class PopRoam extends JFrame implements ComInter, ActionListener, MouseListener, Runnable
{
    private static final String A_RM_REF = "Rre";
    private static final String A_RM_JN = "Rjn";
    private static final String A_RM_CH = "Rch";
    private static final String A_CR = "CR";
    private static final String A_SCH = "Usc";
    private static final String A_U_CH = "Uch";
    private static final String A_U_JN = "Ujn";
    private AppletSpice paraApplet;
    private JLabel infoText;
    private RegRoomList roamingList;
    private DefaultListModel userModel;
    private JList userList;
    private JTextField roomInput;
    private JCheckBox passwdCheck;
    private JTextField passInput;
    private JTextField searchInput;
    private DefaultListModel resultModel;
    private JList resultList;
    private SimpleBankQueue roomQueue;
    private SimpleBankQueue userQueue;
    private SimpleBankQueue searchQueue;
    
    public PopRoam() {
        this.roomQueue = new SimpleBankQueue();
        this.userQueue = new SimpleBankQueue();
        this.searchQueue = new SimpleBankQueue();
    }
    
    public void run() {
        try {
            this.doListChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.setTitle(this.paraApplet.paraConf.title());
        this.setSize(440, 420);
        this.setDefaultCloseOperation(1);
        this.buildGUI();
        WindowUtil.center(this);
    }
    
    public void process(final int n, final String[] array) {
        switch (n) {
            case 4: {
                if (array.length > 1) {
                    this.roamUserList(array[0], array[1]);
                    break;
                }
                break;
            }
            case 2: {
                this.roamingList.replaceRooms();
                break;
            }
            case 8: {
                this.roamingList.addRoom(array[0]);
                break;
            }
            case 10: {
                this.roamingList.deleteRoom(array[0]);
                break;
            }
            case 12: {
                this.roamingList.refreshList();
                break;
            }
            case 6: {
                if (array.length > 0) {
                    this.roamSearchResult(array[0]);
                    break;
                }
                break;
            }
        }
    }
    
    public void restart() {
        this.userClearTask();
        this.setVisible(true);
        this.paraApplet.chatModel.cmQueryList();
    }
    
    private void userClearTask() {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 6;
        this.userQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1 && this.roamingList.getSelectedIndex() < 0) {
            return;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.paraApplet.paraConf.printer().print("command=" + actionCommand);
        if (actionCommand == null) {
            return;
        }
        if (actionCommand.equals("Rre")) {
            this.paraApplet.chatModel.cmQueryList();
        }
        else if (actionCommand.equals("Rjn")) {
            this.roamRoom();
        }
        else if (actionCommand.equals("Rch")) {
            final int selectedIndex = this.userList.getSelectedIndex();
            if (selectedIndex < 0) {
                return;
            }
            this.paraApplet.startPrivate((String)this.userModel.get(selectedIndex));
        }
        else if (actionCommand.equals("CR")) {
            this.createRoom();
        }
        else if (actionCommand.equals("Usc")) {
            this.userSearch();
        }
        else if (actionCommand.equals("Uch")) {
            this.searchChat();
        }
        else if (actionCommand.equals("Ujn")) {
            this.searchRoam();
        }
    }
    
    protected void printInfo(String string) {
        string = "<html><p>&nbsp " + string + "</p></html>";
        this.infoText.setText(string);
    }
    
    private void roamUserList(final String s, final String s2) {
        final int selectedIndex = this.roamingList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        if (!RoomItem.sameRoom(RoomItem.simpleForm(this.roamingList.get(selectedIndex)), s)) {
            return;
        }
        this.userClearTask();
        final String[] splitString = StringUtil.splitString(s2, "|", false);
        for (int i = 0; i < splitString.length; ++i) {
            final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
            simpleQueueItem.type = 2;
            simpleQueueItem.obj = splitString[i];
            this.userQueue.add(simpleQueueItem);
            SwingUtilities.invokeLater(this);
        }
    }
    
    private void roomClearTask() {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 6;
        this.roomQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    private void searchClearTask() {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 6;
        this.searchQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    private void roamSearchResult(final String s) {
        this.searchClearTask();
        if (StringUtil.isEmpty(s)) {
            JOptionPane.showMessageDialog(this, this.paraApplet.paraConf.get("Msg.NotFound", "User not Found."));
            return;
        }
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
            final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
            simpleQueueItem.type = 2;
            simpleQueueItem.obj = this.encodeSearch(s2, s3);
            this.searchQueue.add(simpleQueueItem);
            SwingUtilities.invokeLater(this);
        }
    }
    
    private String encodeSearch(final String s, final String s2) {
        return s + " (" + s2 + ")";
    }
    
    private String decodeUser(final String s) {
        final int index = s.indexOf(" (");
        if (index < 0) {
            return null;
        }
        return s.substring(0, index);
    }
    
    private String decodeRoom(String substring) {
        final int index = substring.indexOf(" (");
        if (index < 0) {
            return null;
        }
        substring = substring.substring(index + 2);
        if (substring.length() < 2) {
            return null;
        }
        return substring.substring(0, substring.length() - 1);
    }
    
    private void roamRoom() {
        final int selectedIndex = this.roamingList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        final String value = this.roamingList.get(selectedIndex);
        if (StringUtil.isEmpty(value)) {
            return;
        }
        final String simpleForm = RoomItem.simpleForm(value);
        this.setVisible(false);
        this.paraApplet.connRoamSwitch(simpleForm);
    }
    
    private void createRoom() {
        final String text = this.roomInput.getText();
        if (StringUtil.isTrimmedEmpty(text)) {
            return;
        }
        final String replace = text.trim().replace(' ', '_');
        String trim = null;
        if (this.passwdCheck.isSelected()) {
            final String text2 = this.passInput.getText();
            if (StringUtil.isTrimmedEmpty(text2)) {
                trim = null;
            }
            else {
                trim = text2.trim();
            }
        }
        this.paraApplet.connRoamCreate(replace, trim);
        this.setVisible(false);
    }
    
    private void userSearch() {
        final String text = this.searchInput.getText();
        if (StringUtil.isTrimmedEmpty(text)) {
            return;
        }
        this.paraApplet.chatModel.cmQuerySearch(text.trim());
    }
    
    private void searchChat() {
        final int selectedIndex = this.resultList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        this.paraApplet.startPrivate(this.decodeUser((String)this.resultModel.get(selectedIndex)));
    }
    
    private void searchRoam() {
        final int selectedIndex = this.resultList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        this.paraApplet.connRoamSwitch(this.decodeRoom((String)this.resultModel.get(selectedIndex)));
    }
    
    private void buildGUI() {
        final JTabbedPane tabbedPane = new JTabbedPane();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(tabbedPane, "Center");
        this.getContentPane().add(this.getBottomStatus(), "South");
        final String value = this.paraApplet.paraConf.get("Tab.Rooms", "Rooms");
        final JComponent rooms = this.createRooms();
        if (this.paraApplet.paraConf.getBool("Op.Tb.Rooms", true)) {
            tabbedPane.addTab(value, null, rooms, null);
        }
        final String value2 = this.paraApplet.paraConf.get("Tab.Create", "Create Room");
        final JComponent create = this.createCreate();
        if (this.paraApplet.paraConf.getBool("Op.Tb.Cr", true)) {
            tabbedPane.addTab(value2, null, create, null);
        }
        final String value3 = this.paraApplet.paraConf.get("Tab.Users", "User Search");
        final JComponent search = this.createSearch();
        if (this.paraApplet.paraConf.getBool("Op.Tb.Users", true)) {
            tabbedPane.addTab(value3, null, search, null);
        }
    }
    
    private JComponent createRooms() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(CompUtil.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getLists());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        return panel;
    }
    
    private JComponent getLists() {
        final JLabel label = new JLabel("<html><p>" + this.paraApplet.paraConf.get("Rm.Txt.Rooms", "Click refresh to update room list") + " </p></html>");
        final JSplitPane splitPane = new JSplitPane(1, this.getRoomList(), this.getUsers());
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(180);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "", 1, 2), CompUtil.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(splitPane);
        return verticalPanel;
    }
    
    private JComponent getRoomList() {
        final JLabel label = new JLabel(this.paraApplet.paraConf.get("Rm.Lb.Rooms", "Rooms"));
        label.setAlignmentX(0.5f);
        this.roamingList = new RegRoomList(this.paraApplet);
        this.roamingList.clickForUsers = true;
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(this.roamingList.getBox());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(this.getRoomButtons());
        return verticalPanel;
    }
    
    private JComponent getRoomButtons() {
        final JButton button = new JButton(this.paraApplet.paraConf.get("Rm.Bt.Refresh", "Refresh"));
        button.setActionCommand("Rre");
        button.addActionListener(this);
        final JButton button2 = new JButton(this.paraApplet.paraConf.get("Rm.Bt.Join", "Join"));
        button2.setActionCommand("Rjn");
        button2.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(button);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button2);
        return horizontalPanel;
    }
    
    private JComponent getUsers() {
        final JLabel label = new JLabel(this.paraApplet.paraConf.get("Rm.Lb.Users", "User Names"));
        label.setAlignmentX(0.5f);
        final DefaultListModel<Object> userModel = new DefaultListModel<Object>();
        final JList userList = new JList(userModel);
        userList.setSelectionMode(0);
        userList.setVisibleRowCount(32);
        final JScrollPane scrollPane = new JScrollPane(userList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(60, 180));
        scrollPane.setPreferredSize(new Dimension(90, 190));
        final JButton button = new JButton(this.paraApplet.paraConf.get("Rm.Bt.Chat", "Chat"));
        button.setActionCommand("Rch");
        button.addActionListener(this);
        button.setAlignmentX(0.5f);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(scrollPane);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(button);
        this.userList = userList;
        this.userModel = userModel;
        return verticalPanel;
    }
    
    private JComponent createCreate() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(CompUtil.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getCreate());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        return panel;
    }
    
    private JComponent getCreate() {
        final JLabel label = new JLabel(this.paraApplet.paraConf.get("Rm.Lb.Create", "Create a New Chat Room"));
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JTextField roomInput = new JTextField(16);
        roomInput.setPreferredSize(new Dimension(480, 24));
        roomInput.setMaximumSize(new Dimension(1200, 24));
        horizontalPanel.add(roomInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 1)));
        final JButton button = new JButton(this.paraApplet.paraConf.get("Rm.Bt.Create", "Create"));
        horizontalPanel.add(button);
        button.setActionCommand("CR");
        button.addActionListener(this);
        final JCheckBox passwdCheck = new JCheckBox(this.paraApplet.paraConf.get("Rm.Lb.Pass", "Add a Password"), false);
        final JTextField passInput = new JTextField(16);
        passInput.setPreferredSize(new Dimension(180, 24));
        passInput.setMaximumSize(new Dimension(200, 24));
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        horizontalPanel2.add(passwdCheck);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(12, 1)));
        horizontalPanel2.add(passInput);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "", 1, 2), CompUtil.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(4, 4)));
        verticalPanel.add(horizontalPanel2);
        this.roomInput = roomInput;
        this.passwdCheck = passwdCheck;
        this.passInput = passInput;
        return verticalPanel;
    }
    
    private JComponent createSearch() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(CompUtil.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getSearch());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        verticalPanel.add(this.getResult());
        return panel;
    }
    
    private JComponent getSearch() {
        final JLabel label = new JLabel("<html><p> " + this.paraApplet.paraConf.get("Rm.Txt.Find", "Find User") + " </p></html>");
        final TextFieldCopy searchInput = new TextFieldCopy(this.paraApplet.paraConf);
        searchInput.setColumns(32);
        searchInput.setPreferredSize(new Dimension(620, 24));
        searchInput.setMaximumSize(new Dimension(1200, 24));
        final JButton button = new JButton(this.paraApplet.paraConf.get("Rm.Bt.Find", "Find"));
        button.setActionCommand("Usc");
        button.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(searchInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(6, 1)));
        horizontalPanel.add(button);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "", 1, 2), CompUtil.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel);
        this.searchInput = searchInput;
        return verticalPanel;
    }
    
    private JComponent getResult() {
        final JLabel label = new JLabel(this.paraApplet.paraConf.get("Rm.Lb.Result", "Search Result"));
        label.setAlignmentX(0.5f);
        final DefaultListModel<Object> resultModel = new DefaultListModel<Object>();
        final JList resultList = new JList(resultModel);
        resultList.setVisibleRowCount(8);
        resultList.setSelectionMode(0);
        final JScrollPane scrollPane = new JScrollPane(resultList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(120, 60));
        scrollPane.setPreferredSize(new Dimension(160, 120));
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(scrollPane);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(this.getResultButtons());
        this.resultList = resultList;
        this.resultModel = resultModel;
        return verticalPanel;
    }
    
    private JComponent getResultButtons() {
        final JButton button = new JButton(this.paraApplet.paraConf.get("Rm.Bt.Chat", "Chat"));
        button.setActionCommand("Uch");
        button.addActionListener(this);
        final JButton button2 = new JButton(this.paraApplet.paraConf.get("Rm.Bt.Join", "Join"));
        button2.setActionCommand("Ujn");
        button2.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(button);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button2);
        return horizontalPanel;
    }
    
    private JComponent getBottomStatus() {
        final JLabel infoText = new JLabel(" ");
        infoText.setForeground(Color.BLUE);
        return this.infoText = infoText;
    }
    
    private void doListChanges() {
        final SimpleQueueItem[] dequeueAll = this.userQueue.dequeueAll();
        for (int i = 0; i < dequeueAll.length; ++i) {
            final SimpleQueueItem simpleQueueItem = dequeueAll[i];
            switch (simpleQueueItem.type) {
                case 2: {
                    this.userModel.addElement(simpleQueueItem.obj);
                    break;
                }
                case 6: {
                    this.userModel.clear();
                    break;
                }
                default: {
                    System.err.println("Err7323," + simpleQueueItem);
                    break;
                }
            }
        }
        final SimpleQueueItem[] dequeueAll2 = this.searchQueue.dequeueAll();
        for (int j = 0; j < dequeueAll2.length; ++j) {
            final SimpleQueueItem simpleQueueItem2 = dequeueAll2[j];
            switch (simpleQueueItem2.type) {
                case 2: {
                    this.resultModel.addElement(simpleQueueItem2.obj);
                    break;
                }
                case 6: {
                    this.resultModel.clear();
                    break;
                }
                default: {
                    System.err.println("Err7539," + simpleQueueItem2);
                    break;
                }
            }
        }
    }
}
