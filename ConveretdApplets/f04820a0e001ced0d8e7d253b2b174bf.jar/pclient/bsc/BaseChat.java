// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import com.pchat.sc.WindowUtil;
import pclient.shd.ClientUtil;
import java.awt.event.ActionEvent;
import com.pchat.sc.MsgOptions;
import java.awt.Container;
import com.pchat.sc.StringUtil;
import java.awt.TextComponent;
import pclient.shd.ChatModel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextArea;
import pclient.shd.Config;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class BaseChat extends Panel implements SelectAction, ActionListener
{
    public static final int CP_COLOR = 10;
    public static final int CP_CLIP = 12;
    public static final int CP_ROOMS = 14;
    public static final int SVR_ROOMS = 200;
    public static final int SVR_USERS = 204;
    public static final int SVR_SEEK = 206;
    public static final int UI_REFRESH = 300;
    public Config paraConf;
    public CommonInter controlPanel;
    protected TextArea pUserMultiLineInput;
    private MultiHandler multiHandler;
    protected Button pSendBtn;
    private Button pSecondBtn;
    public TextArea pSecondMulti;
    protected Button pWhoisBtn;
    protected Button privateButton;
    protected Button pIgnoreUser;
    private Button pUserButton;
    private Button pRoomButton;
    public ChatPrevRenderer pChatText;
    public UserList pUsersBox;
    public Label countLabel;
    private Label infoLabel;
    protected OptionBar optionBar;
    private GridLayout openPanelLayout;
    private Component openInputBox;
    private Panel openMainPanel;
    public Color bgColor;
    public Color fgColor;
    public boolean pClickInfo;
    private String pRenderer;
    public CommonInter pRoomList;
    private CardLayout pRoamCard;
    private Panel pRoamPanel;
    private static final String ROAM_USER = "US";
    private static final String ROAM_ROOM = "RM";
    private PassDialog promptDialog;
    private UserDialog userDialog;
    private long lastClick;
    protected long typingGap;
    protected long myTyping;
    public ChatModel chatModel;
    public AppletBase parentComp;
    protected Panel toobarPanel;
    
    public BaseChat(final AppletBase parentComp, final Config paraConf) {
        this.paraConf = null;
        this.controlPanel = null;
        this.multiHandler = null;
        this.pUserButton = null;
        this.pRoomButton = null;
        this.optionBar = null;
        this.pRoomList = null;
        this.pRoamCard = null;
        this.promptDialog = null;
        this.userDialog = null;
        this.lastClick = 0L;
        this.typingGap = 6000L;
        this.myTyping = 0L;
        this.parentComp = parentComp;
        this.chatModel = parentComp.chatModel;
        this.paraConf = paraConf;
        this.initParams();
        this.buildGUI();
    }
    
    public void showStatus(final String s) {
        this.infoLabel.setText("  " + s);
        this.infoLabel.invalidate();
        this.validate();
        this.repaint();
    }
    
    public TextComponent getTextInput() {
        return this.pUserMultiLineInput;
    }
    
    public TextComponent getInputx() {
        return this.pSecondMulti;
    }
    
    public boolean isMegaText() {
        return this.pRenderer.indexOf("MegaText") >= 0;
    }
    
    public void insertImage(final String s) {
        TextComponent textComponent;
        if (this.chatModel.cmIsModPublic()) {
            textComponent = this.getInputx();
        }
        else {
            textComponent = this.getTextInput();
        }
        final int caretPosition = textComponent.getCaretPosition();
        final String text = textComponent.getText();
        textComponent.setText(text.substring(0, caretPosition) + "::" + s + " " + text.substring(caretPosition));
        textComponent.setCaretPosition(caretPosition + "::".length() + s.length() + 1);
    }
    
    public void promptRoomPass() {
        if (this.promptDialog == null) {
            this.promptDialog = new PassDialog(this);
        }
        this.promptDialog.show();
    }
    
    public void finishPass(final String s) {
        this.promptDialog = null;
        this.chatModel.cmSendRoomPass(s);
    }
    
    public void finishCancelPass() {
        this.promptDialog = null;
        this.chatModel.cmCancelRoomPass();
    }
    
    public void promptUserPass(final String s) {
        if (this.userDialog == null) {
            this.userDialog = new UserDialog(this);
        }
        this.userDialog.display(s);
    }
    
    public void finishUserPass(final String s) {
        this.userDialog = null;
        this.chatModel.cmSendUserPass(s);
    }
    
    public void finishUserChange() {
        this.userDialog = null;
    }
    
    public void changeUserColor(final Color foreground) {
        this.parentComp.userChoice.inputColor = foreground;
        this.getTextInput().setForeground(foreground);
        this.getInputx().setForeground(foreground);
    }
    
    private void initParams() {
        this.pClickInfo = true;
    }
    
    public void clickAction() {
        final String selectedItem = this.pUsersBox.getSelectedItem();
        if (StringUtil.isEmpty(selectedItem)) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.lastClick != 0L && currentTimeMillis - this.lastClick < 900L) {
            this.parentComp.startPrivate(selectedItem);
            this.lastClick = currentTimeMillis;
            return;
        }
        this.lastClick = currentTimeMillis;
        this.ignoreState(this.chatModel.cmIsIgnored(selectedItem));
        if (this.isConnected() && this.pClickInfo) {
            this.whois(selectedItem);
        }
    }
    
    private void switchRoam(final boolean b) {
        if (b) {
            this.pRoamCard.show(this.pRoamPanel, "RM");
            this.pUserButton.setEnabled(true);
            this.pRoomButton.setEnabled(false);
        }
        else {
            this.pRoamCard.show(this.pRoamPanel, "US");
            this.pUserButton.setEnabled(false);
            this.pRoomButton.setEnabled(true);
        }
    }
    
    private void ignoreState(final boolean b) {
        if (b) {
            this.pIgnoreUser.setLabel("Accept");
        }
        else {
            this.pIgnoreUser.setLabel("Ignore");
        }
    }
    
    public void doMultiInput() {
        final String text = this.pUserMultiLineInput.getText();
        this.pUserMultiLineInput.setText("");
        if (StringUtil.isTrimmedEmpty(text)) {
            return;
        }
        this.myTyping = System.currentTimeMillis() - this.typingGap;
        if (this.chatModel.cmIsModerated()) {
            this.chatModel.cmModSubmit(text);
        }
        else {
            this.chatModel.cmPublic(text, this.genOptions(this.pUserMultiLineInput));
        }
    }
    
    public void inputxMulti() {
        final String text = this.pSecondMulti.getText();
        this.pSecondMulti.setText("");
        if (StringUtil.isTrimmedEmpty(text)) {
            return;
        }
        this.myTyping = System.currentTimeMillis() - this.typingGap;
        this.chatModel.cmPublic(text, this.genOptions(this.pSecondMulti));
    }
    
    private MsgOptions genOptions(final Component component) {
        final MsgOptions msgOptions = new MsgOptions();
        msgOptions.fontname = this.parentComp.userChoice.fontName;
        msgOptions.fontBold = this.parentComp.userChoice.bold;
        msgOptions.fontItalic = this.parentComp.userChoice.italic;
        msgOptions.color = this.parentComp.userChoice.inputColor;
        return msgOptions;
    }
    
    protected void removeOpenMod() {
        this.openMainPanel.remove(this.openInputBox);
        this.openPanelLayout.setRows(1);
        this.openMainPanel.invalidate();
        this.validate();
        this.repaint();
    }
    
    protected void addOpenMod() {
        this.openPanelLayout.setRows(2);
        this.openMainPanel.remove(this.openInputBox);
        this.openMainPanel.add(this.openInputBox);
        this.openMainPanel.invalidate();
        this.validate();
        this.repaint();
    }
    
    private void toggleIgnore(final String s) {
        if (null == s) {
            return;
        }
        if (this.chatModel.cmIsIgnored(s)) {
            this.setIgnore(s, false);
            this.ignoreState(false);
        }
        else {
            this.setIgnore(s, true);
            this.ignoreState(true);
        }
    }
    
    public ChatPrevRenderer getRenderer() {
        return this.pChatText;
    }
    
    public boolean isConnected() {
        return this.chatModel.cmIsConnected();
    }
    
    public void setConn(final boolean connect) {
        if (this.optionBar != null) {
            this.optionBar.setConnect(connect);
        }
    }
    
    protected void showUsers() {
        if (this.pRoamCard != null) {
            this.pRoamCard.show(this.pRoamPanel, "US");
        }
        if (this.pUserButton != null) {
            this.pUserButton.setEnabled(false);
        }
        if (this.pRoomButton != null) {
            this.pRoomButton.setEnabled(true);
        }
    }
    
    public String selectedUser() {
        return this.pUsersBox.getSelectedItem();
    }
    
    public void clearUserNames() {
        this.pUsersBox.delItems(0, this.pUsersBox.countItems() - 1);
        while (this.pUsersBox.countItems() > 0) {
            this.pUsersBox.delItem(0);
        }
    }
    
    public synchronized void addUser(final String s) {
        this.paraConf.printer().print("####add " + s);
        final int countItems = this.pUsersBox.countItems();
        int i = 0;
        while (i < countItems) {
            final int cmCompare = this.chatModel.cmCompare(s, this.pUsersBox.getItem(i));
            if (cmCompare > 0) {
                ++i;
            }
            else {
                if (cmCompare == 0) {
                    this.paraConf.printer().print("already on list, " + s);
                    return;
                }
                this.insertUser(s, this.pUsersBox, i);
                return;
            }
        }
        this.insertUser(s, this.pUsersBox, -1);
    }
    
    private void insertUser(final String s, final UserList list, final int n) {
        if (this.chatModel.cmLocalIsAdmin(s)) {
            list.addAdmin(s, n);
        }
        else if (this.chatModel.cmLocalIsMod(s)) {
            list.addModerator(s, n);
        }
        else if (this.chatModel.cmLocalIsSpk(s)) {
            list.addSpeaker(s, n);
        }
        else if (this.chatModel.cmIsSelf(s)) {
            list.addDominantItem(s, n);
        }
        else {
            list.addItem(s, n);
        }
        final boolean cmIsIgnored = this.chatModel.cmIsIgnored(s);
        final int countItems = this.pUsersBox.countItems();
        if (cmIsIgnored) {
            if (n < 0) {
                this.pUsersBox.ignore(countItems - 1);
            }
            else {
                this.pUsersBox.ignore(n);
            }
        }
    }
    
    public void deleteUser(final String s) {
        for (int countItems = this.pUsersBox.countItems(), i = 0; i < countItems; ++i) {
            if (this.chatModel.cmCompare(s, this.pUsersBox.getItem(i)) == 0) {
                this.pUsersBox.delItem(i);
                break;
            }
        }
    }
    
    public synchronized void setIgnore(final String s, final boolean b) {
        if (s == null) {
            return;
        }
        if (b) {
            this.chatModel.cmAddIgnore(s);
        }
        else {
            this.chatModel.cmDeleteIgnore(s);
        }
        for (int i = 0; i < this.pUsersBox.countItems(); ++i) {
            if (this.pUsersBox.getItem(i).equals(s)) {
                if (b) {
                    this.pUsersBox.ignore(i);
                }
                else {
                    this.pUsersBox.stopIgnoring(i);
                }
                return;
            }
        }
    }
    
    public void doze(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    private void doPrivateButton() {
        final String selectedUser = this.selectedUser();
        if (StringUtil.isEmpty(selectedUser)) {
            return;
        }
        this.parentComp.startPrivate(selectedUser);
    }
    
    public void fireControlPanel(final int n, final String s) {
        final String[] array = { s };
        if (this.controlPanel != null) {
            this.controlPanel.restart();
            this.controlPanel.process(n, array);
            return;
        }
        this.parentComp.showLocalInfo("Loading Control Panel. Please wait...");
        new LoadCP(this, n, array).start();
        this.doze(1000);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pSendBtn) {
            this.doMultiInput();
        }
        else if (actionEvent.getSource() == this.pSecondBtn) {
            this.inputxMulti();
        }
        else if (actionEvent.getSource() == this.privateButton) {
            this.doPrivateButton();
        }
        else if (actionEvent.getSource() == this.pIgnoreUser) {
            this.toggleIgnore(this.selectedUser());
        }
        else if (actionEvent.getSource() == this.pUserButton) {
            this.switchRoam(false);
        }
        else if (actionEvent.getSource() == this.pRoomButton) {
            this.switchRoam(true);
            this.pRoomList.process(300, null);
        }
        else if (actionEvent.getSource() == this.pWhoisBtn) {
            if (!this.isConnected()) {
                return;
            }
            final String selectedUser = this.selectedUser();
            if (selectedUser != null) {
                this.whois(selectedUser);
            }
        }
    }
    
    private void whois(final String s) {
        if (this.paraConf.getBool("Add.Bt.Prof", true)) {
            this.chatModel.cmQueryProfile(s);
        }
    }
    
    public String getStamp() {
        if (!this.parentComp.globalChoice.timestamp) {
            return "";
        }
        return ClientUtil.getStamp(this.paraConf, null);
    }
    
    private void buildGUI() {
        final String s = "CCCC99";
        final String s2 = "000000";
        this.bgColor = WindowUtil.parseColor(s, Color.white);
        this.fgColor = WindowUtil.parseColor(s2, Color.black);
        this.setBackground(this.bgColor);
        this.setForeground(this.fgColor);
        this.setFont(new Font("Dialog", 0, 12));
        if (this.paraConf.getBool("Render.Plain", false)) {
            this.pRenderer = "pclient.bsx.ChatPanel";
        }
        else {
            this.pRenderer = "pclient.bsc.MegaText";
        }
        try {
            this.pChatText = (ChatPrevRenderer)Class.forName(this.pRenderer).newInstance();
        }
        catch (Exception ex) {
            System.out.println("Error T3214 :" + ex + "loading " + this.pRenderer);
        }
        if (this.pChatText != null) {
            this.pChatText.setApplet(this);
        }
        this.pUsersBox = new ExUserList(this.paraConf);
        if (false) {
            this.pUsersBox.setVisible(false);
        }
        this.pUsersBox.addSelectAction(this);
        final int n = 10;
        final String s3 = "Dialog";
        final Font font = new Font(s3, 0, n);
        if (font != null) {
            this.pUsersBox.setFont(font);
        }
        else {
            System.out.println("Err #672. font," + s3 + " " + n);
        }
        final Color black = Color.black;
        final Color white = Color.white;
        this.pUsersBox.setForeground(black);
        this.pUsersBox.setBackground(white);
        this.buildLayout();
    }
    
    private void buildLayout() {
        final Panel buildNorthPanel = this.buildNorthPanel();
        final Panel buildSouthPanel = this.buildSouthPanel();
        final Panel buildCenterPanel = this.buildCenterPanel();
        this.setLayout(new BorderLayout(2, 1));
        this.add("North", buildNorthPanel);
        this.add("Center", buildCenterPanel);
        this.add("South", buildSouthPanel);
    }
    
    private Panel buildNorthPanel() {
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(2, 2));
        return panel;
    }
    
    private Panel buildUsersRooms() {
        final Font font = new Font("Dialog", 1, 10);
        final String s = "Users";
        final String s2 = "Rooms";
        final Button pUserButton = new Button(s);
        final Button pRoomButton = new Button(s2);
        pUserButton.setFont(font);
        pRoomButton.setFont(font);
        final Panel panel = new Panel();
        if (s.length() + s2.length() > 18) {
            panel.setLayout(new GridLayout(2, 1, 1, 1));
        }
        else {
            panel.setLayout(new FlowLayout(1, 1, 1));
        }
        panel.add(pUserButton);
        panel.add(pRoomButton);
        try {
            (this.pRoomList = (CommonInter)Class.forName("pclient.bsx.RoomPanel").newInstance()).setPara(this);
        }
        catch (Exception ex) {
            System.out.println("Err624," + ex);
            this.pRoomList = null;
        }
        final Panel buildUserPanel = this.buildUserPanel();
        final CardLayout cardLayout = new CardLayout();
        final Panel pRoamPanel = new Panel();
        pRoamPanel.setLayout(cardLayout);
        pRoamPanel.add("US", buildUserPanel);
        if (this.pRoomList != null && this.paraConf.getBool("Add.RoomList", true)) {
            pRoamPanel.add("RM", (Component)this.pRoomList);
        }
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout());
        panel2.add("North", panel);
        panel2.add("Center", pRoamPanel);
        cardLayout.show(pRoamPanel, "US");
        this.pRoamCard = cardLayout;
        this.pRoamPanel = pRoamPanel;
        this.pUserButton = pUserButton;
        this.pRoomButton = pRoomButton;
        this.pUserButton.setEnabled(false);
        this.pRoomButton.setEnabled(false);
        this.pUserButton.addActionListener(this);
        this.pRoomButton.addActionListener(this);
        return panel2;
    }
    
    private Panel buildMainWest() {
        if (this.paraConf.isRoam()) {
            return this.buildUsersRooms();
        }
        return this.buildUserPanel();
    }
    
    private Panel buildUserPanel() {
        final Font font = new Font("Dialog", 1, 10);
        (this.pWhoisBtn = new Button("User Info")).setFont(font);
        this.pWhoisBtn.setEnabled(false);
        this.pWhoisBtn.addActionListener(this);
        if (this.paraConf.isRoam()) {
            this.pWhoisBtn.setVisible(false);
        }
        final String s = "Private";
        (this.privateButton = new Button(s)).setFont(font);
        this.privateButton.setEnabled(false);
        this.privateButton.addActionListener(this);
        final String s2 = "Ignore";
        (this.pIgnoreUser = new Button(s2)).setFont(font);
        this.pIgnoreUser.setEnabled(false);
        this.pIgnoreUser.addActionListener(this);
        final Panel panel = new Panel();
        int n = 0;
        ++n;
        ++n;
        final int n2 = 18;
        if (n == 2 && s.length() + s2.length() > n2) {
            panel.setLayout(new GridLayout(2, 1));
        }
        else {
            panel.setLayout(new FlowLayout(1, 1, 1));
        }
        panel.add(this.privateButton);
        panel.add(this.pIgnoreUser);
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout(1, 1));
        if (this.paraConf.getBool("Add.UserList", true)) {
            panel2.add("Center", this.pUsersBox);
            panel2.add("North", this.pWhoisBtn);
            panel2.add("South", panel);
        }
        return panel2;
    }
    
    private Panel buildSouthPanel() {
        final Component component = null;
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(2, 2));
        if (component != null) {
            panel.add("Center", component);
        }
        return panel;
    }
    
    private Panel buildCenterPanel() {
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(1, 1));
        panel.add("Center", this.buildMainCenter());
        final boolean bool = this.paraConf.getBool("Pos.UserList.West", true);
        final Panel buildMainWest = this.buildMainWest();
        if (this.paraConf.isSimpleCSR()) {
            return panel;
        }
        if (!this.paraConf.getBool("Op.Add.Lists", true)) {
            return panel;
        }
        if (bool) {
            panel.add("West", buildMainWest);
        }
        else {
            panel.add("East", buildMainWest);
        }
        return panel;
    }
    
    private Panel buildMainCenter() {
        final Panel buildUserInput = this.buildUserInput();
        final Panel buildBar = this.buildBar();
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(1, 1));
        panel.add("Center", buildUserInput);
        panel.add("South", buildBar);
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout(1, 1));
        panel2.add("Center", (Component)this.pChatText);
        if (!this.paraConf.isSimpleCSR()) {
            panel2.add("South", panel);
        }
        this.toobarPanel = buildBar;
        return panel2;
    }
    
    private Panel buildUserInput() {
        this.multiHandler = new MultiHandler(this);
        final Panel buildOpenInput = this.buildOpenInput();
        final Panel buildDoubleInput = this.buildDoubleInput();
        final Panel openMainPanel = new Panel();
        final GridLayout gridLayout = new GridLayout(1, 1);
        openMainPanel.setLayout(gridLayout);
        openMainPanel.add(buildDoubleInput);
        this.openPanelLayout = gridLayout;
        this.openInputBox = buildOpenInput;
        return this.openMainPanel = openMainPanel;
    }
    
    private Panel buildDoubleInput() {
        (this.pUserMultiLineInput = new TextArea(" ", 1, 48, 3)).setFont(new Font("Dialog", 0, 11));
        this.pUserMultiLineInput.setForeground(Color.black);
        this.pUserMultiLineInput.setBackground(Color.white);
        (this.pSendBtn = new Button("Send")).setFont(new Font("Dialog", 1, 11));
        this.pSendBtn.addActionListener(this);
        final boolean bool = this.paraConf.getBool("Add.Input", true);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        if (!bool) {
            return panel;
        }
        panel.add("East", this.pSendBtn);
        panel.add("Center", this.pUserMultiLineInput);
        this.pUserMultiLineInput.addKeyListener(this.multiHandler);
        return panel;
    }
    
    private Panel buildOpenInput() {
        (this.pSecondMulti = new TextArea(" ", 1, 48, 3)).setFont(new Font("Dialog", 0, 11));
        this.pSecondMulti.setForeground(Color.black);
        this.pSecondMulti.setBackground(Color.white);
        this.pSecondMulti.addKeyListener(this.multiHandler);
        (this.pSecondBtn = new Button("Send")).setFont(new Font("Dialog", 1, 11));
        this.pSecondBtn.addActionListener(this);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("East", this.pSecondBtn);
        panel.add("Center", this.pSecondMulti);
        return panel;
    }
    
    private Panel buildBar() {
        this.optionBar = new OptionBar(this);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0, 1, 1));
        panel.add(this.optionBar);
        final Component buildCount = this.buildCount();
        if (this.paraConf.getBool("Add.Count", true)) {
            panel.add(buildCount);
        }
        panel.add(this.buildInfo());
        return panel;
    }
    
    private Component buildCount() {
        (this.countLabel = new Label("    0", 1)).setForeground(Color.blue);
        return this.countLabel;
    }
    
    private Component buildInfo() {
        return this.infoLabel = new Label("                 ", 0);
    }
}
