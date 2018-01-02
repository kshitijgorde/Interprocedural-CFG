import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.awt.Event;
import java.awt.MenuItem;
import java.util.StringTokenizer;
import java.util.Random;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.Choice;
import java.awt.MenuBar;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.Menu;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetMainPanel extends Panel
{
    private Menu FileMenu;
    private Menu ChannelMenu;
    private Menu PrivChatMenu;
    private GridBagLayout channelsLayout;
    private GridBagLayout locationLayout;
    private GridBagLayout ToolBarLayout;
    private IRCQNetChannelListCtrl ChannelCard;
    private IRCQNetPanel Cards;
    private CardLayout cardLayout;
    private IRCQNetPanelControl ChannelControl;
    private IRCQNetPanel Buttons;
    private IRCQNetAdsPanel adPanel;
    private IRCQNetInternetTime iTimePanel;
    private IRCQNet theApp;
    private BorderLayout borderLayout;
    private FlowLayout flowLayout;
    private IRCQNetProtocol IrcClient;
    private IRCQNetImageButtonPanel IRCQNetButtonPanel;
    private IRCQNetToolBarPanel IRCQNetToolBar;
    private Panel buttonPanel;
    private Panel channelPanel;
    private Panel topToolPanel;
    private Panel toolPanel;
    private IRCQNetTopLinePanel locationPanel;
    private boolean BisModeFrame;
    private boolean embedDisable;
    private IRCQNetSearchPanel sPanel;
    public MenuBar menuBar;
    public boolean nickOk;
    public boolean firstLogin;
    private IRCQNetTimer pTimer;
    private Thread pThread;
    private Choice lChannels;
    private Label channelsLabel;
    private Label locationLabel;
    private IRCQNetNickNamePanel nickCard;
    public int countRooms;
    
    public IRCQNetMainPanel(final String s, final IRCQNet theApp) {
        this.firstLogin = true;
        this.countRooms = 0;
        this.nickCard = null;
        this.FileMenu = new Menu("File");
        this.ChannelMenu = new Menu("Channels");
        this.PrivChatMenu = new Menu("Private Chats");
        this.channelsLayout = new GridBagLayout();
        this.locationLayout = new GridBagLayout();
        this.ToolBarLayout = new GridBagLayout();
        this.cardLayout = new CardLayout();
        this.flowLayout = new FlowLayout(0, 0, 0);
        this.buttonPanel = new Panel();
        this.channelPanel = new Panel();
        this.topToolPanel = new Panel();
        this.toolPanel = new Panel();
        this.locationPanel = new IRCQNetTopLinePanel();
        this.BisModeFrame = true;
        this.embedDisable = false;
        this.menuBar = new MenuBar();
        this.nickOk = false;
        this.channelsLabel = new Label("Channels");
        this.locationLabel = new Label("Location");
        this.theApp = theApp;
        this.setLayout(this.borderLayout = new BorderLayout());
    }
    
    public void init() {
        this.initMenu();
        this.initMembers();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        this.add("Center", this.Cards);
        this.buttonPanel.setLayout(new BorderLayout());
        this.topToolPanel.setLayout(this.ToolBarLayout);
        this.toolPanel.setLayout(new BorderLayout());
        this.locationPanel.setLayout(this.locationLayout);
        this.channelPanel.setLayout(this.channelsLayout);
        this.channelPanel.setBackground(IRCQNetColors.controlColor);
        this.toolPanel.setBackground(IRCQNetColors.controlColor);
        this.topToolPanel.setBackground(IRCQNetColors.controlColor);
        this.buttonPanel.setBackground(IRCQNetColors.controlColor);
        this.locationPanel.setBackground(IRCQNetColors.controlColor);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(0, 2, 2, 0);
        this.channelsLayout.setConstraints(this.lChannels, gridBagConstraints);
        this.channelPanel.add(this.lChannels);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(0, 5, 2, 0);
        this.channelsLayout.setConstraints(this.channelsLabel, gridBagConstraints);
        this.channelPanel.add(this.channelsLabel);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.insets = new Insets(0, 2, 0, 0);
        this.locationLayout.setConstraints(this.locationLabel, gridBagConstraints2);
        this.locationPanel.add(this.locationLabel);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.weightx = 100.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.fill = 2;
        this.locationLayout.setConstraints(this.IRCQNetButtonPanel, gridBagConstraints2);
        this.locationPanel.add(this.IRCQNetButtonPanel);
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.gridwidth = 1;
        gridBagConstraints4.gridheight = 1;
        gridBagConstraints4.anchor = 17;
        gridBagConstraints4.weightx = 100.0;
        gridBagConstraints4.weighty = 0.0;
        gridBagConstraints4.fill = 2;
        this.ToolBarLayout.setConstraints(this.IRCQNetToolBar, gridBagConstraints4);
        this.topToolPanel.add(this.IRCQNetToolBar);
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.gridwidth = 1;
        gridBagConstraints3.gridheight = 1;
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.weightx = 0.0;
        gridBagConstraints3.weighty = 0.0;
        this.ToolBarLayout.setConstraints(this.adPanel, gridBagConstraints3);
        this.topToolPanel.add(this.adPanel);
        this.toolPanel.add("North", this.topToolPanel);
        this.toolPanel.add("West", this.channelPanel);
        this.toolPanel.add("East", this.iTimePanel);
        this.buttonPanel.add("North", this.toolPanel);
        this.buttonPanel.add("South", this.locationPanel);
        this.add("North", this.buttonPanel);
        this.initToolbar();
        this.add("South", this.pTimer = new IRCQNetTimer(15));
        (this.pThread = new Thread(this.pTimer)).setPriority(1);
        this.pThread.start();
        this.paintAll(this.getGraphics());
        this.iTimePanel.start();
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
    }
    
    public void initToolbar() {
        this.IRCQNetToolBar.setButtonsWidth(60);
        this.IRCQNetToolBar.setFontSize(11);
        this.IRCQNetToolBar.NewButton("Join", 3);
        this.IRCQNetToolBar.NewButton("Nick", 8);
        this.IRCQNetToolBar.NewButton("Search", 11);
        this.IRCQNetToolBar.NewButton("Rooms", 7);
        this.IRCQNetToolBar.NewButton("Options", 2);
        if (this.BisModeFrame) {
            this.IRCQNetToolBar.NewButton("Embed", 6);
        }
        else {
            this.IRCQNetToolBar.NewButton("Float", 5);
        }
        this.IRCQNetToolBar.disableButton("Nick");
    }
    
    public void ChannelListCard() {
        try {
            this.ChannelCard = new IRCQNetChannelListCtrl(this.theApp);
        }
        catch (Exception ex) {}
        this.Cards.add("!CHANNEL!", this.ChannelCard);
        this.cardLayout.show(this.Cards, "!CHANNEL!");
        this.NewButton("!CHANNEL!", 5);
        this.ShowCard("!CHANNEL!");
    }
    
    public void DelListCard() {
        if (this.ChannelCard != null) {
            this.Cards.remove(this.ChannelCard);
            this.ChannelCard = null;
        }
        this.IRCQNetButtonPanel.DelButton("!CHANNEL!");
        this.ShowCard("!STATUS!");
    }
    
    public void PrintToCList(final String s, final String s2, final String s3) {
        if (this.ChannelCard == null) {
            this.ChannelListCard();
        }
        this.ChannelCard.Print(s, s2, s3);
    }
    
    public void CleanClist() {
        if (this.ChannelCard == null) {
            this.ChannelListCard();
        }
        this.ChannelCard.cleanUp();
    }
    
    public void Disconnected(final boolean b) {
        this.IRCQNetToolBar.disableButton("Nick");
        this.ShowCard("!STATUS!");
        this.nickOk = false;
        this.Send("QUIT http://ircqnet.icq.com/");
        this.IRCQNetButtonPanel.DelAllButtons();
        this.ChannelControl.DelAllWindows();
        this.getParams().Connected = false;
        if (b) {
            this.Print("!STATUS!", "\u00034*** Disconnected from " + this.getParams().server);
        }
        try {
            this.IrcClient.cleanUp();
        }
        catch (Exception ex) {}
        this.IrcClient = null;
        this.ShowCard("!STATUS!");
    }
    
    public boolean isEmbedDisable() {
        return this.embedDisable;
    }
    
    public boolean disableEmbed() {
        this.embedDisable = true;
        this.IRCQNetToolBar.disableButton("Embed");
        return true;
    }
    
    public boolean doJoin() {
        this.Send("JOIN " + this.getParams().firstJoin);
        return true;
    }
    
    public String procText(String s) {
        if (s.startsWith("/")) {
            return s;
        }
        String s2 = null;
        switch (this.getParams().writeMode) {
            case 1: {
                final Random random = new Random();
                s2 = "\u00031," + this.getParams().RtBG;
                for (int i = 0; i < s.length(); ++i) {
                    int j;
                    do {
                        j = Math.abs(random.nextInt()) % 15 + 1;
                    } while (j == this.getParams().RtBG);
                    if (Character.isLetter(s.charAt(i))) {
                        s2 = s2 + "\u0003" + j + s.charAt(i);
                    }
                    else {
                        s2 += s.charAt(i);
                    }
                }
                break;
            }
            case 2: {
                s = this.removeAllColors(s, true, true);
                final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
                final int countTokens = stringTokenizer.countTokens();
                s2 = "\u0003" + this.getParams().HtFG + "," + this.getParams().HtBG;
                for (int k = 0; k < countTokens; ++k) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (nextToken.length() == 0) {
                        s2 += " ";
                    }
                    else if (nextToken.length() == 1) {
                        s2 = s2 + "\u0003" + this.getParams().HtFG + nextToken + " ";
                    }
                    else if (nextToken.length() > 1) {
                        for (int l = 0; l < nextToken.length(); ++l) {
                            if (Character.isLetter(nextToken.charAt(l)) || Character.isDigit(nextToken.charAt(l))) {
                                s2 = s2 + "\u0003" + this.getParams().HtFG + nextToken.charAt(l) + "\u0003" + this.getParams().Ht2FG + nextToken.substring(l + 1) + " ";
                                break;
                            }
                            s2 += nextToken.charAt(l);
                        }
                    }
                }
                break;
            }
            case 3: {
                this.getParams().setSpecials(this.getParams().specialNum);
                final int size = this.getParams().sColorVector.size();
                s = this.removeAllColors(s);
                s2 = "\u00031," + this.getParams().GtBG;
                final int length = s.length();
                final int n = length / size;
                int n2 = 0;
                int n3 = 0;
                for (int n4 = 0; n2 < length; ++n2, ++n4) {
                    if (n4 == n && n3 < size - 1) {
                        ++n3;
                        n4 = 0;
                    }
                    if (n4 == 0) {
                        if (this.getParams().GtBG < 10) {
                            s2 = s2 + "\u0003" + (int)this.getParams().sColorVector.elementAt(n3) + ",0" + this.getParams().GtBG;
                        }
                        else {
                            s2 = s2 + "\u0003" + (int)this.getParams().sColorVector.elementAt(n3) + "," + this.getParams().GtBG;
                        }
                    }
                    s2 += s.charAt(n2);
                }
                break;
            }
            default: {
                s2 = s;
                break;
            }
        }
        return s2;
    }
    
    public String removeAllColors(final String s) {
        new StringTokenizer(s);
        String string = "";
        int i = 0;
    Label_0228_Outer:
        while (i < s.length()) {
            while (true) {
                switch (s.charAt(i)) {
                    case '\u0003': {
                        try {
                            if (i + 1 >= s.length() || !Character.isDigit(s.charAt(i + 1))) {
                                break Label_0228;
                            }
                            if (++i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                ++i;
                            }
                            if (i + 2 < s.length() && s.substring(i + 1, i + 2).indexOf(",") != -1) {
                                ++i;
                                try {
                                    if (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                        ++i;
                                        if (Character.isDigit(s.charAt(i + 1))) {
                                            ++i;
                                        }
                                    }
                                }
                                catch (NumberFormatException ex) {}
                            }
                            break Label_0228;
                        }
                        catch (NumberFormatException ex2) {
                            break Label_0228;
                        }
                        break;
                    }
                    case '\u0001':
                    case '\u0002':
                    case '\u001f': {
                        ++i;
                        continue Label_0228_Outer;
                    }
                }
                string += s.charAt(i);
                continue;
            }
        }
        return string;
    }
    
    public String removeAllColors(final String s, final boolean b, final boolean b2) {
        new StringTokenizer(s);
        String s2 = "";
        int i = 0;
        while (i < s.length()) {
            Label_0307: {
                switch (s.charAt(i)) {
                    case '\u0002': {
                        if (b) {
                            s2 += s.charAt(i);
                        }
                        break Label_0307;
                    }
                    case '\u0003': {
                        try {
                            if (i + 1 >= s.length() || !Character.isDigit(s.charAt(i + 1))) {
                                break Label_0307;
                            }
                            if (++i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                ++i;
                            }
                            if (i + 2 < s.length() && s.substring(i + 1, i + 2).indexOf(",") != -1) {
                                ++i;
                                try {
                                    if (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                        ++i;
                                        if (Character.isDigit(s.charAt(i + 1))) {
                                            ++i;
                                        }
                                    }
                                }
                                catch (NumberFormatException ex) {}
                            }
                            break Label_0307;
                        }
                        catch (NumberFormatException ex2) {
                            break Label_0307;
                        }
                    }
                    case '\u001f': {
                        if (b2) {
                            s2 += s.charAt(i);
                        }
                        break Label_0307;
                    }
                    default: {
                        s2 += s.charAt(i);
                    }
                    case '\u0001': {
                        ++i;
                        continue;
                    }
                }
            }
        }
        return s2;
    }
    
    public boolean isModeFrame() {
        return this.BisModeFrame;
    }
    
    public void setModeFrame(final boolean bisModeFrame) {
        this.displayFrame(this.BisModeFrame = bisModeFrame);
    }
    
    public boolean displayFrame(final boolean bisModeFrame) {
        if (bisModeFrame) {
            this.IRCQNetToolBar.changeButton("Float", "Embed", 6);
        }
        else {
            this.IRCQNetToolBar.changeButton("Embed", "Float", 5);
        }
        this.BisModeFrame = bisModeFrame;
        return true;
    }
    
    public boolean NewChannelCard(final String s) {
        final IRCQNetChannelWnd newChannelWnd = this.ChannelControl.NewChannelWnd(s);
        if (newChannelWnd != null) {
            newChannelWnd.show();
            this.Cards.add(s, newChannelWnd);
            this.cardLayout.show(this.Cards, s);
            this.NewButton(s, 1);
            this.ShowCard(s);
            this.ChannelMenu.add(new MenuItem(s));
            this.repaint();
            newChannelWnd.hide();
            newChannelWnd.show();
            return true;
        }
        this.ShowCard(s);
        return false;
    }
    
    public boolean NewPrivChatCard(final String s, final boolean b) {
        final IRCQNetPrivChatWnd newPrivChatWnd = this.ChannelControl.NewPrivChatWnd(s);
        if (newPrivChatWnd != null) {
            newPrivChatWnd.show();
            this.NewButton(s, 2);
            this.Cards.add(s, newPrivChatWnd);
            if (!s.equals("!STATUS!")) {
                this.PrivChatMenu.add(new MenuItem(s));
            }
            if (b) {
                this.cardLayout.show(this.Cards, s);
                newPrivChatWnd.hide();
                newPrivChatWnd.show();
                this.ShowCard(s);
            }
            this.repaint();
            return true;
        }
        this.ShowCard(s);
        return false;
    }
    
    public boolean DelChannelCard() {
        if (this.ChannelCard == null) {
            return false;
        }
        this.Cards.remove(this.ChannelCard);
        this.IRCQNetButtonPanel.DelButton("!CHANNEL!");
        this.ChannelCard = null;
        return true;
    }
    
    public boolean delCard(final String s) {
        if (s.startsWith("#")) {
            this.Cards.remove(this.ChannelControl.GetChannelWnd(s));
            this.IRCQNetButtonPanel.DelButton(s);
            for (int countItems = this.ChannelMenu.countItems(), i = 0; i < countItems; ++i) {
                if (this.ChannelMenu.getItem(i).getLabel().equals(s)) {
                    this.ChannelMenu.remove(i);
                    break;
                }
            }
        }
        else {
            if (this.ChannelControl.GetPrivChatWnd(s) != null) {
                this.Cards.remove(this.ChannelControl.GetPrivChatWnd(s));
            }
            this.IRCQNetButtonPanel.DelButton(s);
            for (int countItems2 = this.PrivChatMenu.countItems(), j = 0; j < countItems2; ++j) {
                if (this.PrivChatMenu.getItem(j).getLabel().equals(s)) {
                    this.PrivChatMenu.remove(j);
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean DelPrivChatCard(final String s) {
        if (this.ChannelControl.GetPrivChatWnd(s) != null) {
            this.Cards.remove(this.ChannelControl.GetPrivChatWnd(s));
        }
        this.IRCQNetButtonPanel.DelButton(s);
        this.ChannelControl.DelPrivChatWnd(s);
        for (int countItems = this.PrivChatMenu.countItems(), i = 0; i < countItems; ++i) {
            if (this.PrivChatMenu.getItem(i).getLabel().equals(s)) {
                this.PrivChatMenu.remove(i);
                break;
            }
        }
        this.ShowCard("!STATUS!");
        this.repaint();
        return true;
    }
    
    public boolean DelChannelCard(final String s) {
        this.Cards.remove(this.ChannelControl.GetChannelWnd(s));
        this.IRCQNetButtonPanel.DelButton(s);
        this.ChannelControl.DelChannelWnd(s);
        for (int countItems = this.ChannelMenu.countItems(), i = 0; i < countItems; ++i) {
            if (this.ChannelMenu.getItem(i).getLabel().equals(s)) {
                this.ChannelMenu.remove(i);
                break;
            }
        }
        this.ShowCard("!STATUS!");
        this.repaint();
        return true;
    }
    
    public void StartStautsWnd() {
        this.NewPrivChatCard("!STATUS!", true);
    }
    
    public void initMenu() {
        this.FileMenu.add(new MenuItem("Connect"));
        this.FileMenu.add(new MenuItem("Options"));
        this.menuBar.add(this.FileMenu);
        this.menuBar.add(this.ChannelMenu);
        this.menuBar.add(this.PrivChatMenu);
    }
    
    public void initChannels() {
        final String channelList = this.theApp.MPanel.getParams().channelList;
        final StringTokenizer stringTokenizer = new StringTokenizer(channelList, ";");
        if (stringTokenizer.countTokens() <= 0) {
            this.lChannels.addItem(channelList);
            return;
        }
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            this.lChannels.addItem(stringTokenizer.nextToken());
        }
    }
    
    public void initMembers() {
        this.Buttons = new IRCQNetPanel(this.theApp);
        this.Cards = new IRCQNetPanel(this.theApp);
        this.ChannelControl = new IRCQNetPanelControl(this.Cards, this.theApp);
        this.IRCQNetButtonPanel = new IRCQNetImageButtonPanel(this.theApp);
        this.IRCQNetToolBar = new IRCQNetToolBarPanel(this.theApp, true);
        this.adPanel = new IRCQNetAdsPanel(this.theApp, 1, "Test");
        this.iTimePanel = new IRCQNetInternetTime(this.theApp);
        this.IRCQNetButtonPanel.show();
        this.IRCQNetToolBar.show();
        this.IRCQNetButtonPanel.resize(90, 28);
        this.IRCQNetToolBar.resize(90, 50);
        this.IRCQNetButtonPanel.setFontSize(11);
        this.Cards.setLayout(this.cardLayout);
        this.StartStautsWnd();
        this.lChannels = new Choice();
        this.initChannels();
    }
    
    public void Print(final String s, final String s2) {
        this.ChannelControl.Print(s, s2);
    }
    
    public void PrintToCurrent(final String s) {
        this.Print("!STATUS!", s);
    }
    
    public void ShowCard(final String s) {
        this.cardLayout.show(this.Cards, s);
        this.IRCQNetButtonPanel.Press(s);
    }
    
    public boolean NewButton(final String s, final int n) {
        this.IRCQNetButtonPanel.NewButton(s, n);
        return false;
    }
    
    public IRCQNetParam getParams() {
        return this.theApp.getParams();
    }
    
    public void runSearchDlg() {
        if (this.sPanel == null) {
            this.sPanel = new IRCQNetSearchPanel(this.theApp);
            this.add("South", this.sPanel.ePost);
            this.sPanel.ePost.hide();
            this.sPanel.getFocus();
            return;
        }
        this.sPanel.getFocus();
    }
    
    public IRCQNetProtocol getClient() {
        return this.IrcClient;
    }
    
    public boolean ConnectToServer() {
        this.Send("QUIT http://ircqnet.icq.com/");
        this.Disconnected(false);
        this.Print("!STATUS!", "\u00032*** Connecting to \u0002\u000312IrCQNet \u0002\u00032Please wait...");
        this.add("South", this.IrcClient = new IRCQNetProtocol(this.theApp));
        this.IrcClient.hide();
        this.IrcClient.Connect();
        return true;
    }
    
    public IRCQNetPanelControl GetChanControl() {
        return this.ChannelControl;
    }
    
    public void runStartUpDlg() {
        new IRCQNetStartupDlg(this, "IRCQNet", true, this.theApp).displayControl();
        System.gc();
    }
    
    public void runOptionsDlg() {
        final IRCQNetOptionsDlg ircqNetOptionsDlg = new IRCQNetOptionsDlg(true, this.theApp);
        System.gc();
    }
    
    public void runJoinDlg() {
        final IRCQNetChannelJoinDlg ircqNetChannelJoinDlg = new IRCQNetChannelJoinDlg(this.theApp);
        this.add("South", ircqNetChannelJoinDlg.ePost);
        ircqNetChannelJoinDlg.ePost.hide();
        ircqNetChannelJoinDlg.startDlg();
        System.gc();
    }
    
    public IRCQNet getTheApp() {
        return this.theApp;
    }
    
    public void closeAllWindows() {
        this.theApp.closeWindow();
        if (this.sPanel != null) {
            this.sPanel.closeWindow();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 10015) {
            if (!this.embedDisable) {
                this.theApp.initNoneFrame();
                return true;
            }
            if (this.getParams().Connected) {
                this.Send("QUIT http://ircqnet.icq.com/");
                this.Disconnected(false);
                this.doQuit();
            }
            return true;
        }
        else {
            if (event.target == this.lChannels && event.arg != null) {
                this.Send("JOIN " + event.arg);
                return true;
            }
            if (event.id == 1001) {
                if (event.arg == null) {
                    return false;
                }
                if (event.target == this.IRCQNetToolBar) {
                    final String s = (String)event.arg;
                    if (s.equalsIgnoreCase("Options")) {
                        this.runOptionsDlg();
                        return true;
                    }
                    if (s.equalsIgnoreCase("Search")) {
                        this.runSearchDlg();
                        return true;
                    }
                    if (s.equalsIgnoreCase("Connect")) {
                        this.ConnectToServer();
                        return true;
                    }
                    if (s.equalsIgnoreCase("Float")) {
                        this.theApp.initFrame();
                        return true;
                    }
                    if (s.equalsIgnoreCase("Embed")) {
                        this.theApp.initNoneFrame();
                        return true;
                    }
                    if (s.equalsIgnoreCase("Rooms")) {
                        this.Send("LIST");
                        return true;
                    }
                    if (s.equalsIgnoreCase("Join")) {
                        this.runJoinDlg();
                        return true;
                    }
                    if (s.equalsIgnoreCase("Nick")) {
                        this.OpenNickNameTab("Change Nickname", this.getParams().nick, false);
                        return true;
                    }
                    return true;
                }
                else {
                    for (int i = 0; i < this.ChannelMenu.countItems(); ++i) {
                        if (this.ChannelMenu.getItem(i) == event.target) {
                            this.ShowCard((String)event.arg);
                            return true;
                        }
                    }
                    for (int j = 0; j < this.PrivChatMenu.countItems(); ++j) {
                        if (this.PrivChatMenu.getItem(j) == event.target) {
                            this.ShowCard((String)event.arg);
                            return true;
                        }
                    }
                }
            }
            switch (event.id) {
                case 10000: {
                    this.Print("!STATUS!", "\u00032*** Connected To " + this.getParams().server);
                    this.IrcClient.Login();
                    return true;
                }
                case 10021: {
                    if (event.target == this.pTimer) {
                        this.getParams().pingTimer();
                    }
                    return true;
                }
                case 10001: {
                    if (this.countRooms == 100) {
                        this.ChannelCard.modeWait(false);
                        this.countRooms = 0;
                    }
                    ++this.countRooms;
                    this.PrintToCList(((Vector)event.arg).elementAt(0), ((Vector)event.arg).elementAt(1), ((Vector)event.arg).elementAt(2));
                    return true;
                }
                case 10002: {
                    if (this.ChannelCard == null) {
                        this.ChannelListCard();
                    }
                    this.ChannelCard.cleanUp();
                    return true;
                }
                case 10003: {
                    if (this.ChannelCard == null) {
                        this.ChannelListCard();
                    }
                    this.ChannelCard.modeWait(false);
                    this.ChannelCard.SetHeaderTopic();
                    return true;
                }
                case 10016: {
                    this.Nickused(this.getParams().nick, (String)event.arg, false);
                    return true;
                }
                case 10004: {
                    this.nickOk = true;
                    this.IRCQNetToolBar.enableButton("Nick");
                    this.doJoin();
                    return true;
                }
                case 10005: {
                    this.Disconnected(true);
                    this.Print("!STATUS!", "\u00034*** Trying again...");
                    this.ConnectToServer();
                    return true;
                }
                case 10008: {
                    this.getClient().parseToSend((String)event.arg);
                    return true;
                }
                case 10006: {
                    this.Print("!STATUS!", "\u00034*** Error Connecting To " + this.getParams().server);
                    this.Disconnected(false);
                    this.Print("!STATUS!", "\u00034*** Trying again...");
                    this.ConnectToServer();
                    return true;
                }
                case 10007: {
                    this.getClient().postEvent(event);
                    return true;
                }
                case 10017: {
                    this.getParams().iRoomSearch = (String)event.arg;
                    if (this.ChannelCard == null) {
                        this.ChannelListCard();
                    }
                    this.ChannelCard.setSearchMode(true);
                    this.Send("LIST");
                    return true;
                }
                case 10019: {
                    this.sPanel = null;
                    System.gc();
                    return true;
                }
                default: {
                    return super.handleEvent(event);
                }
            }
        }
    }
    
    public void Send(final String s) {
        if (this.IrcClient != null && this.getParams().Connected) {
            this.IrcClient.Send(s);
        }
    }
    
    public void Nickused(final String s, final String s2, final boolean b) {
        this.getClient().parseToSend("!STATUS!", "/nick " + this.getParams().nick + "_" + (Math.abs(new Random().nextInt()) % 9999 + 1));
    }
    
    public void OpenNickNameTab(final String s, final String s2, final boolean b) {
        if (this.nickCard != null) {
            this.CloseNickNameTab();
        }
        this.nickCard = new IRCQNetNickNamePanel(this.theApp, s, s2, b);
        this.Cards.add("!NICKNAME!", this.nickCard);
        this.cardLayout.show(this.Cards, "!NICKNAME!");
        this.nickCard.show();
        this.nickCard.repaint();
        this.repaint();
        this.nickCard.hide();
        this.nickCard.show();
    }
    
    public void CloseNickNameTab() {
        if (this.nickCard != null) {
            this.Cards.remove(this.nickCard);
            this.nickCard = null;
        }
        this.ShowCard("!STATUS!");
    }
    
    private void doQuit() {
        this.Send("QUIT http://ircqnet.icq.com");
        try {
            this.theApp.getAppletContext().showDocument(new URL("http://www.icq.com/"));
        }
        catch (MalformedURLException ex) {
            this.closeAllWindows();
            System.exit(0);
        }
        catch (Exception ex2) {
            this.closeAllWindows();
            System.exit(0);
        }
        this.closeAllWindows();
        System.exit(0);
    }
    
    public void PrintJoins(final String s) {
        if (this.theApp.MPanel.getParams().showJoins || !this.theApp.MPanel.getParams().showOnlyChat) {
            this.Print("!STATUS!", "\u00032*** " + s);
        }
    }
    
    public void PrintNickChange(final String s) {
        if (this.theApp.MPanel.getParams().showNickChange || !this.theApp.MPanel.getParams().showOnlyChat) {
            this.Print("!STATUS!", "\u00032*** " + s);
        }
    }
    
    public void PrintQuits(final String s) {
        if (this.theApp.MPanel.getParams().showQuits || !this.theApp.MPanel.getParams().showOnlyChat) {
            this.Print("!STATUS!", "\u00032*** " + s);
        }
    }
}
