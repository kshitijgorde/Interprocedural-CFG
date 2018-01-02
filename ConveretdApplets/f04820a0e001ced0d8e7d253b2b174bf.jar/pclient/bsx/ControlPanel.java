// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.util.StringTokenizer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Component;
import pclient.bsc.ChatPrevRenderer;
import java.awt.Font;
import com.pchat.sc.WindowUtil;
import java.awt.event.WindowEvent;
import java.awt.Event;
import java.awt.Color;
import pclient.bsc.BaseChat;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import pclient.bsc.CommonInter;
import java.awt.Frame;

public class ControlPanel extends Frame implements CommonInter
{
    public static final String DEF_MSG_ROAM_NO_ROOM = "No room name in text input field.";
    public static final String DEF_AU = "bark.au,boing.au,drip.au,quack.au,splat.au,tone.au";
    private TabbedPanel mainPanel;
    private Choice pFontSize;
    private Choice pFontName;
    private Choice pFontColor;
    private Choice pWindowColor;
    private Checkbox pDoubleSpace;
    private Checkbox pBeepEnter;
    private Checkbox pBeepExit;
    private Checkbox pJoinMsg;
    private Checkbox pTimestamp;
    private Checkbox pBlockPrivate;
    private Checkbox pNoProfile;
    private Checkbox pTextTone;
    private Checkbox pRoomAudio;
    private Button pPageAdmin;
    private Button bcastButton;
    private Button approvalButton;
    private Choice pToneChoice;
    private Panel pTonePanel;
    private Choice pEnterChoice;
    private Panel pEnterPanel;
    private Choice pExitChoice;
    private Panel pExitPanel;
    private String TAB_VIEW;
    private String TAB_CREATE;
    private String TAB_USER;
    private String TAB_COLOR;
    private String TAB_ICON;
    private ViewRooms viewRooms;
    private UserSearch userSearch;
    protected BaseChat pChat;
    private boolean initDone;
    private int lastCode;
    private String[] lastCommand;
    
    public ControlPanel() {
        this.viewRooms = null;
        this.userSearch = null;
        this.initDone = false;
        this.lastCode = -1;
        this.lastCommand = null;
    }
    
    public void process(final int lastCode, final String[] lastCommand) {
        if (!this.initDone) {
            this.lastCode = lastCode;
            this.lastCommand = lastCommand;
            return;
        }
        switch (lastCode) {
            case 10: {
                this.mainPanel.showTab(this.TAB_COLOR);
            }
            case 12: {
                this.mainPanel.showTab(this.TAB_ICON);
            }
            case 14: {
                this.mainPanel.showTab(this.TAB_VIEW);
                this.viewRooms.process(300, null);
            }
            default: {
                if (this.viewRooms != null) {
                    this.viewRooms.process(lastCode, lastCommand);
                }
                if (this.userSearch != null) {
                    this.userSearch.process(lastCode, lastCommand);
                }
            }
        }
    }
    
    public void setPara(final BaseChat pChat) {
        this.pChat = pChat;
        this.TAB_VIEW = "Rooms";
        this.TAB_CREATE = "Create";
        this.TAB_USER = "Users";
        this.TAB_COLOR = "Color";
        this.TAB_ICON = "Icons";
        this.setTitle(this.pChat.paraConf.title());
        this.setSize(520, 380);
        this.setForeground(Color.black);
        this.setBackground(Color.lightGray);
        this.buildGUI();
        this.enableEvents(64L);
        this.setVisible(true);
        this.mainPanel.showTab("About");
        if (this.pChat.paraConf.isRoam()) {
            this.mainPanel.showTab(this.TAB_VIEW);
            this.mainPanel.showTab(this.TAB_CREATE);
            this.mainPanel.showTab(this.TAB_USER);
        }
        if (this.pChat.isMegaText()) {
            this.mainPanel.showTab(this.TAB_COLOR);
            this.mainPanel.showTab(this.TAB_ICON);
        }
        if (this.pChat.paraConf.isRoam()) {
            this.mainPanel.showTab(this.TAB_VIEW);
        }
        else {
            this.mainPanel.showTab("Font");
        }
        this.initDone = true;
    }
    
    public boolean isConnected() {
        return this.pChat.isConnected();
    }
    
    public void restart() {
        this.show();
        this.toFront();
        if (this.lastCode > 0) {
            this.process(this.lastCode, this.lastCommand);
            this.lastCode = -1;
        }
        this.initDone = true;
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.pFontSize) {
                this.changeFontSize((String)event.arg);
            }
            else if (event.target == this.pFontColor) {
                this.changeFontColor((String)event.arg);
            }
            else if (event.target == this.pFontName) {
                this.changeFontName((String)event.arg);
            }
            else if (event.target == this.pWindowColor) {
                this.changeBgColor((String)event.arg);
            }
            else if (event.target == this.pDoubleSpace) {
                this.useDoubleSpace(this.pDoubleSpace.getState());
            }
            else if (event.target == this.pBeepEnter || event.target == this.pEnterChoice) {
                this.useBeepEnter(this.pBeepEnter.getState());
            }
            else if (event.target == this.pBeepExit || event.target == this.pExitChoice) {
                this.useBeepExit(this.pBeepExit.getState());
            }
            else if (event.target == this.pTextTone || event.target == this.pToneChoice) {
                this.useTextTone(this.pTextTone.getState());
            }
            else if (event.target == this.pJoinMsg) {
                this.showJoinMessage(this.pJoinMsg.getState());
            }
            else if (event.target == this.pTimestamp) {
                this.pChat.parentComp.globalChoice.timestamp = this.pTimestamp.getState();
            }
            else if (event.target == this.pBlockPrivate) {
                this.blockPrivate(this.pBlockPrivate.getState());
            }
            else if (event.target == this.pNoProfile) {
                this.pChat.pClickInfo = !this.pNoProfile.getState();
            }
            else if (event.target == this.pPageAdmin) {
                this.pChat.chatModel.cmPage();
            }
            else if (event.target == this.pRoomAudio) {
                this.pChat.parentComp.globalChoice.audio = this.pRoomAudio.getState();
            }
            else if (event.target == this.bcastButton) {
                this.pChat.chatModel.cmAvBcast();
            }
            else if (event.target == this.approvalButton) {
                this.pChat.parentComp.vwAvViewPermit(null, null);
            }
        }
        return super.handleEvent(event);
    }
    
    public void processWindowEvent(final WindowEvent windowEvent) {
        final int id = windowEvent.getID();
        if (id != 205) {
            if (id != 206) {
                if (id == 201) {
                    this.setVisible(false);
                }
            }
        }
    }
    
    private void changeFontSize(final String s) {
        final int int1 = WindowUtil.parseInt(s, 12);
        if (int1 < 0) {
            return;
        }
        final ChatPrevRenderer renderer = this.pChat.getRenderer();
        final Font font = renderer.getFont();
        final Font font2 = new Font(font.getName(), font.getStyle(), int1);
        renderer.setFont(font2);
        this.pChat.getTextInput().setFont(font2);
        this.pChat.getInputx().setFont(font2);
    }
    
    private void changeFontColor(final String s) {
        final Color color = this.getColor(s);
        if (color == null) {
            return;
        }
        this.pChat.parentComp.userChoice.inputColor = color;
        this.pChat.getRenderer().setForeground(color);
    }
    
    private void changeFontName(final String fontName) {
        final Font font = this.pChat.getTextInput().getFont();
        final Font font2 = new Font(fontName, font.getStyle(), font.getSize());
        this.pChat.paraConf.printer().print("new font is," + font2);
        if (font2 != null) {
            this.pChat.getTextInput().setFont(font2);
            this.pChat.getInputx().setFont(font2);
            this.pChat.parentComp.userChoice.fontName = fontName;
        }
    }
    
    private void changeBgColor(final String s) {
        final Color color = this.getColor(s);
        if (color == null) {
            return;
        }
        this.pChat.getRenderer().setBackground(color);
    }
    
    private Color getColor(final String s) {
        final ColorTable colorTable = new ColorTable();
        return ColorTable.getColor(s);
    }
    
    private void useDoubleSpace(final boolean doubleSpace) {
        this.pChat.parentComp.userChoice.doubleSpace = doubleSpace;
        this.pChat.getRenderer().setnl(doubleSpace);
    }
    
    private void useBeepEnter(final boolean soundEnterOn) {
        if (!(this.pChat.parentComp.userChoice.soundEnterOn = soundEnterOn)) {
            return;
        }
        this.pChat.parentComp.userChoice.soundEnter = this.pEnterChoice.getSelectedItem();
    }
    
    private void useBeepExit(final boolean soundExitOn) {
        if (!(this.pChat.parentComp.userChoice.soundExitOn = soundExitOn)) {
            return;
        }
        this.pChat.parentComp.userChoice.soundExit = this.pExitChoice.getSelectedItem();
    }
    
    private void useTextTone(final boolean soundNewOn) {
        if (!(this.pChat.parentComp.userChoice.soundNewOn = soundNewOn)) {
            return;
        }
        this.pChat.parentComp.userChoice.soundNew = this.pToneChoice.getSelectedItem();
    }
    
    private void showJoinMessage(final boolean showJoin) {
        this.pChat.parentComp.userChoice.showJoin = showJoin;
    }
    
    private void blockPrivate(final boolean block) {
        this.pChat.parentComp.globalChoice.block = block;
        this.pChat.chatModel.cmBlock(block);
    }
    
    private void buildGUI() {
        final Panel fontPanel = this.getFontPanel();
        final Panel settingPanel = this.getSettingPanel();
        final Panel videoPanel = this.getVideoPanel();
        final Panel aboutPanel = this.getAboutPanel();
        this.add("Center", this.mainPanel = new TabbedPanel());
        if (this.pChat.paraConf.isRoam()) {
            this.viewRooms = new ViewRooms(this.pChat, this);
            this.mainPanel.addTabPanel(this.viewRooms, this.TAB_VIEW);
            final CreateRoom createRoom = new CreateRoom(this.pChat, this);
            if (this.pChat.paraConf.getBool("Add.Mn.Roam", true)) {
                this.mainPanel.addTabPanel(createRoom, this.TAB_CREATE);
            }
            this.userSearch = new UserSearch(this.pChat, this);
            if (this.pChat.paraConf.getBool("Add.Mn.Roam", true)) {
                this.mainPanel.addTabPanel(this.userSearch, this.TAB_USER);
            }
        }
        if (this.pChat.isMegaText()) {
            final TextColor textColor = new TextColor(this.pChat);
            if (this.pChat.paraConf.getBool("Add.Color", true)) {
                this.mainPanel.addTabPanel(textColor, this.TAB_COLOR);
            }
            final IconPanel iconPanel = new IconPanel(this.pChat);
            if (this.pChat.paraConf.getBool("Add.Smiley", true)) {
                this.mainPanel.addTabPanel(iconPanel, this.TAB_ICON);
            }
        }
        this.mainPanel.addTabPanel(fontPanel, "Font");
        this.mainPanel.addTabPanel(settingPanel, "Settings");
        if (this.pChat.paraConf.getBool("Ctrl.Av", false)) {
            this.mainPanel.addTabPanel(videoPanel, "Audio/Video");
        }
        if (this.pChat.paraConf.getBool("Add.Mn.Help", true)) {
            this.mainPanel.addTabPanel(aboutPanel, "About");
        }
        aboutPanel.invalidate();
    }
    
    private Panel getFontPanel() {
        final Label label = new Label("Local Font Size", 0);
        final Label label2 = new Label("Font Color", 0);
        final Label label3 = new Label("My User Font", 0);
        final Label label4 = new Label("Window Color", 0);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 1, 3, 7));
        panel.add(label);
        if (!this.pChat.isMegaText()) {
            panel.add(label2);
        }
        else {
            panel.add(label3);
        }
        panel.add(label4);
        this.initFontChoices();
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(4, 1, 3, 7));
        panel2.add(this.pFontSize);
        if (!this.pChat.isMegaText()) {
            panel2.add(this.pFontColor);
        }
        else {
            panel2.add(this.pFontName);
        }
        panel2.add(this.pWindowColor);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(1));
        panel3.add(panel);
        panel3.add(panel2);
        return panel3;
    }
    
    private void initFontChoices() {
        (this.pFontSize = new Choice()).addItem("10");
        this.pFontSize.addItem("12");
        this.pFontSize.addItem("14");
        this.pFontSize.addItem("16");
        this.pFontSize.addItem("18");
        this.pFontSize.addItem("20");
        this.pFontSize.select(2);
        this.addColors(this.pFontColor = new Choice());
        this.setChoice(this.pFontName = new Choice(), "Mn.FontName", "Dialog,TimesRoman,Courier,Helvetica");
        this.addColors(this.pWindowColor = new Choice());
    }
    
    private void addColors(final Choice choice) {
        choice.addItem("black");
        choice.addItem("white");
        choice.addItem("red");
        choice.addItem("green");
        choice.addItem("blue");
        choice.addItem("gray");
        choice.addItem("yellow");
        choice.addItem("magenta");
        choice.addItem("cyan");
        choice.addItem("violet");
    }
    
    private Panel getSettingPanel() {
        this.initSettingBoxes();
        this.initTextTone();
        this.initBeepEnter();
        this.initBeepExit();
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1, 14, 1));
        panel.add(this.getLeftSetting());
        panel.add(this.getRightSetting());
        return panel;
    }
    
    private Panel getVideoPanel() {
        final Panel panel = new Panel();
        this.bcastButton = new Button("Start Broadcasting Video");
        this.approvalButton = new Button("View Video Request Queue");
        panel.add(this.bcastButton);
        panel.add(this.approvalButton);
        return panel;
    }
    
    private Panel getLeftSetting() {
        final int n = 5;
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(n, 1, 1, 2));
        panel.add(this.pJoinMsg);
        panel.add(this.pTimestamp);
        panel.add(this.pDoubleSpace);
        panel.add(this.pBlockPrivate);
        panel.add(this.pNoProfile);
        return panel;
    }
    
    private Panel getRightSetting() {
        final int n = 5;
        int n2 = 1;
        final Panel panel = new Panel();
        final GridLayout layout = new GridLayout(n, 1, 1, 2);
        panel.setLayout(layout);
        panel.add(this.pRoomAudio);
        panel.add(this.pEnterPanel);
        panel.add(this.pExitPanel);
        panel.add(this.pTonePanel);
        if (this.pChat.paraConf.getBool("Add.Mn.Page", true)) {
            panel.add(this.pPageAdmin);
            --n2;
        }
        layout.setRows(n - n2);
        return panel;
    }
    
    private void initSettingBoxes() {
        (this.pDoubleSpace = new Checkbox("Double Space")).setState(false);
        (this.pBeepEnter = new Checkbox("Beep on Enter")).setState(false);
        (this.pBeepExit = new Checkbox("Beep on Exit")).setState(false);
        (this.pJoinMsg = new Checkbox("Join/Leave Message")).setState(true);
        (this.pTimestamp = new Checkbox("Timestamp Messages")).setState(false);
        (this.pNoProfile = new Checkbox("Turn off Profile")).setState(!this.pChat.pClickInfo);
        (this.pRoomAudio = new Checkbox("Room Audio")).setState(true);
        (this.pBlockPrivate = new Checkbox("Block All Private")).setState(false);
        (this.pTextTone = new Checkbox("Tone on New Text")).setState(false);
        this.pPageAdmin = new Button("Page Admin");
    }
    
    private void initTextTone() {
        this.setChoice(this.pToneChoice = new Choice(), "Cf.Alert", "bark.au,boing.au,drip.au,quack.au,splat.au,tone.au");
        final Panel pTonePanel = new Panel();
        pTonePanel.setLayout(new BorderLayout(1, 1));
        pTonePanel.add("West", this.pTextTone);
        pTonePanel.add("East", this.pToneChoice);
        this.pTonePanel = pTonePanel;
    }
    
    private void initBeepEnter() {
        this.setChoice(this.pEnterChoice = new Choice(), "Cf.Alert", "bark.au,boing.au,drip.au,quack.au,splat.au,tone.au");
        final Panel pEnterPanel = new Panel();
        pEnterPanel.setLayout(new BorderLayout(1, 1));
        pEnterPanel.add("West", this.pBeepEnter);
        pEnterPanel.add("East", this.pEnterChoice);
        this.pEnterPanel = pEnterPanel;
    }
    
    private void initBeepExit() {
        this.setChoice(this.pExitChoice = new Choice(), "Cf.Alert", "bark.au,boing.au,drip.au,quack.au,splat.au,tone.au");
        final Panel pExitPanel = new Panel();
        pExitPanel.setLayout(new BorderLayout(1, 1));
        pExitPanel.add("West", this.pBeepExit);
        pExitPanel.add("East", this.pExitChoice);
        this.pExitPanel = pExitPanel;
    }
    
    private void setChoice(final Choice choice, final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.pChat.paraConf.get(s, s2), ", ");
        while (stringTokenizer.hasMoreTokens()) {
            choice.addItem(stringTokenizer.nextToken().trim());
        }
    }
    
    private Panel getAboutPanel() {
        return new AboutPanel(this.pChat, false);
    }
}
