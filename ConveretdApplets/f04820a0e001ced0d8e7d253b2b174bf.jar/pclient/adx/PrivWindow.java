// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JMenuBar;
import javax.swing.Box;
import pclient.adv.DisplayArea;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import pclient.shd.UserAttr;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import pclient.shd.Config;
import com.pchat.sc.StringUtil;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import java.util.Date;
import com.pchat.sc.MsgOptions;
import java.awt.event.KeyEvent;
import java.awt.Component;
import javax.swing.JColorChooser;
import java.awt.Color;
import com.pchat.sc.WindowUtil;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import pclient.adv.StageBox;
import pclient.adv.DualTextArea;
import pclient.shd.UserChoice;
import java.awt.event.WindowListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class PrivWindow extends JFrame implements ActionListener, KeyListener, WindowListener
{
    private static final String ACT_SEND = "send";
    private static final String ACT_BOLD = "BD";
    private static final String ACT_ITA = "ITA";
    private static final String ACT_SMILE = "SM";
    private static final String ACT_COLOR = "CL";
    private static final String ACT_SOUND = "sd";
    private static final String ACT_AV = "av";
    protected PrivManager privateMan;
    protected String thirdParty;
    private String thisUser;
    protected UserChoice userChoice;
    protected PrivMenu topMenu;
    protected PrivSmile popSmile;
    protected PopSound popSound;
    protected DualTextArea textInput;
    protected StageBox chatRender;
    private JToggleButton boldToggle;
    private JToggleButton italicToggle;
    private JLabel topLabel;
    private JLabel typingLabel;
    private JLabel clockLabel;
    private JLabel infoLabel;
    private String partyAvatar;
    private boolean isActive;
    private boolean isIconed;
    private String Non_Empty;
    private long partyTyping;
    private boolean typingErased;
    private long typingGap;
    private long myTyping;
    private long creationTime;
    private PrivEventTasks eventTasks;
    private boolean avPopupShowing;
    private long lastAvClick;
    private boolean warnedOnce;
    
    public PrivWindow(final PrivManager privateMan, final String thisUser, final String s) {
        this.popSmile = null;
        this.popSound = null;
        this.topLabel = null;
        this.partyAvatar = null;
        this.isActive = false;
        this.isIconed = false;
        this.Non_Empty = " ";
        this.partyTyping = 0L;
        this.typingErased = true;
        this.typingGap = 5000L;
        this.myTyping = 0L;
        this.avPopupShowing = false;
        this.lastAvClick = 0L;
        this.warnedOnce = false;
        this.privateMan = privateMan;
        this.thirdParty = s;
        this.thisUser = thisUser;
        this.partyTyping = 0L;
        this.typingErased = true;
        this.myTyping = 0L;
        this.creationTime = System.currentTimeMillis();
        this.userChoice = new UserChoice();
        this.eventTasks = new PrivEventTasks(this);
        this.buildUI();
        this.setSize(560, 370);
        this.setTitle(s);
        this.addWindowListener(this);
        this.setDefaultCloseOperation(0);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        this.isActive = true;
        this.textInput.requestFocus();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        System.out.println("private window closed: " + this.thirdParty);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
        this.isActive = false;
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
        this.isIconed = false;
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
        this.isIconed = true;
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if ("send".equals(actionCommand)) {
            this.actSend();
        }
        else if ("BD".equals(actionCommand)) {
            this.userChoice.bold = this.boldToggle.isSelected();
            WindowUtil.changeStyle(this.textInput.getBox(), this.userChoice.bold, this.userChoice.italic);
        }
        else if ("ITA".equals(actionCommand)) {
            this.userChoice.italic = this.italicToggle.isSelected();
            WindowUtil.changeStyle(this.textInput.getBox(), this.userChoice.bold, this.userChoice.italic);
        }
        else if ("SM".equals(actionCommand)) {
            if (this.popSmile == null) {
                this.popSmile = new PrivSmile(this);
            }
            this.popSmile.restart();
        }
        else if ("CL".equals(actionCommand)) {
            final Color showDialog = JColorChooser.showDialog(this, this.getConf().title(), Color.BLACK);
            this.getConf().printer().print("color=" + showDialog);
            if (showDialog != null) {
                this.textInput.setForeground(showDialog);
                this.userChoice.inputColor = showDialog;
            }
        }
        else if ("sd".equals(actionCommand)) {
            if (this.popSound == null) {
                (this.popSound = new PopSound()).setPara(this.privateMan.paraApplet);
                this.popSound.setPrivate(this.thirdParty);
            }
            this.popSound.restart();
        }
        else if ("av".equals(actionCommand)) {
            this.actAv();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getSource() == this.textInput.getBox()) {
            if (this.textInput.getRows() > 4) {
                this.textInput.setRows(5);
                this.textInput.invalidate();
            }
            if (keyCode == 10) {
                if (true) {
                    this.actSend();
                }
                keyEvent.consume();
                return;
            }
            this.sendTyping();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void receivedPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
        this.chatRender.showOthers(s2, s3, msgOptions);
        this.eventTasks.postReceived(this.getInfo(s2, s, s4));
    }
    
    public void receivedSound(final String s, final String s2, final String s3) {
        final String string = "<" + s2 + "> " + this.getConf().get("Msg.SentSound", "sent sound:") + " " + s3;
        this.getConf().play(s3);
        this.displayText(string, null);
        this.eventTasks.postReceived(this.getInfo(s2, s, null));
    }
    
    public void receivedAvReq(final String s, final String s2, final String s3) {
        this.eventTasks.receivedAvReq(s, s2, s3);
    }
    
    protected void handlePostReceived(final String text) {
        this.topLabel.setText(text);
        this.typingLabel.setText(this.Non_Empty);
        this.infoLabel.setText("  " + this.getConf().get("Msg.Last", "Last message received at") + " " + new Date());
        this.pickFocus();
    }
    
    public void receivedSelfPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
        this.chatRender.showSelf(s2, s3, msgOptions);
        this.pickFocus();
    }
    
    public void joinLeave(final String s, final boolean b) {
        String s2;
        if (b) {
            s2 = this.getConf().get("Msg.Left", "has left.");
        }
        else {
            s2 = this.getConf().get("Msg.Joined", "has joined.");
        }
        this.displayText(this.thirdParty + " " + s2, null);
    }
    
    protected void showTyping() {
        this.eventTasks.showTyping();
    }
    
    protected void handleShowTyping() {
        this.partyTyping = System.currentTimeMillis();
        this.typingLabel.setText(this.thirdParty + " " + this.getConf().get("Lb.PubTyping", "is typing."));
        this.typingErased = false;
    }
    
    protected void eraseTyping() {
        this.eventTasks.eraseTyping();
    }
    
    protected void handleEraseTyping() {
        this.checkAvatarIgnore();
        this.updateClock();
        if (this.typingErased) {
            return;
        }
        if (System.currentTimeMillis() - this.partyTyping > 4L * 1000L) {
            this.typingLabel.setText(this.Non_Empty);
            this.typingErased = true;
        }
    }
    
    protected void handleAvReq(final String s, final String s2, final String s3) {
        if (this.avPopupShowing) {
            this.getConf().printer().print("avReq. popup ignored");
            return;
        }
        this.avPopupShowing = true;
        this.getConf().printer().print("from=" + s2 + " this=" + s + "r=" + s3);
        this.setVisible(true);
        final String s4 = "Audio/Video Chat Request";
        String s5;
        if (this.getConf().isAudioOnly()) {
            s5 = this.getConf().get("Lb.Av.Req", "A User Requests Private Voice Chat");
        }
        else {
            s5 = this.getConf().get("Lb.Av.Req.Vid", "A User Requests Private Video Chat");
        }
        final String string = s5 + " " + this.getInfo(s2, s, s3);
        final Object[] array = { this.getConf().get("Lb.Vq.Acc", "Accept"), this.getConf().get("Lb.Vq.Rej", "Reject"), this.getConf().get("Lb.Vq.Ign", "Ignore") };
        final int showOptionDialog = JOptionPane.showOptionDialog(this, string, s4, 1, 3, null, array, array[2]);
        if (showOptionDialog == 0) {
            this.getConf().printer().print("av accept");
            this.privateMan.paraApplet.chatModel.cmAvAccept(s2);
        }
        else if (showOptionDialog == 1) {
            this.privateMan.paraApplet.chatModel.cmAvDeny(s2);
            this.getConf().printer().print("av deny");
        }
        else if (showOptionDialog == 2) {
            this.getConf().printer().print("av ignore");
            this.privateMan.paraApplet.chatModel.cmAddIgnore(s2);
        }
        else if (showOptionDialog == -1) {
            this.getConf().printer().print("av cancel");
        }
        else {
            System.out.println("av:" + showOptionDialog);
        }
        this.avPopupShowing = false;
    }
    
    protected void displayText(final String s, final MsgOptions msgOptions) {
        this.chatRender.showSysInfo(s, msgOptions);
    }
    
    protected void warnOnce(final String s, final MsgOptions msgOptions) {
        if (this.warnedOnce) {
            return;
        }
        this.chatRender.showSysInfo(s, msgOptions);
        this.warnedOnce = true;
    }
    
    private void actSend() {
        if (!this.privateMan.paraApplet.chatModel.cmIsConnected()) {
            this.privateMan.paraApplet.vwError(this.getConf().get("Msg.NotConnected", "Not connected."), true);
            return;
        }
        if (this.privateMan.paraApplet.chatModel.cmIsIgnored(this.thirdParty)) {
            this.displayText(this.getConf().get("Msg.WarnIgnore", "You have ignored this user. You will not be able to see any reply from this user."), null);
        }
        if (this.privateMan.paraApplet.globalChoice.block) {}
        final String text = this.textInput.getText();
        this.textInput.setText("");
        this.chatRender.scrollBottom();
        if (StringUtil.isTrimmedEmpty(text)) {
            return;
        }
        this.myTyping = System.currentTimeMillis() - this.typingGap;
        this.privateMan.paraApplet.chatModel.cmPrivate(text, this.thirdParty, this.genOptions(this.textInput.getBox()));
    }
    
    private void actAv() {
        if (System.currentTimeMillis() - this.lastAvClick < 5000L) {
            this.displayText("Please wait.", null);
            return;
        }
        this.lastAvClick = System.currentTimeMillis();
        this.avStart();
    }
    
    protected void avStart() {
        if (!this.privateMan.paraApplet.chatModel.cmIsConnected()) {
            this.privateMan.paraApplet.vwError(this.getConf().get("Msg.NotConnected", "Not connected."), true);
            return;
        }
        if (this.privateMan.paraApplet.chatModel.cmIsIgnored(this.thirdParty)) {
            this.displayText(this.getConf().get("Msg.WarnIgnore", "You have ignored this user. You will not be able to see any reply from this user."), null);
        }
        if (this.privateMan.paraApplet.globalChoice.block) {}
        this.privateMan.paraApplet.chatModel.cmAvReq(this.thirdParty);
    }
    
    private MsgOptions genOptions(final Component component) {
        final MsgOptions msgOptions = new MsgOptions();
        msgOptions.fontname = this.userChoice.fontName;
        msgOptions.fontBold = this.userChoice.bold;
        msgOptions.fontItalic = this.userChoice.italic;
        msgOptions.color = this.userChoice.inputColor;
        return msgOptions;
    }
    
    private void sendTyping() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.myTyping < this.typingGap) {
            return;
        }
        this.privateMan.paraApplet.chatModel.cmTypingPriv(this.thirdParty);
        this.myTyping = currentTimeMillis;
    }
    
    protected Config getConf() {
        return this.privateMan.paraApplet.paraConf;
    }
    
    public void pickFocus() {
        if (this.isActive) {
            this.textInput.requestFocus();
            return;
        }
        if (!this.isShowing()) {
            this.setVisible(true);
            return;
        }
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        if (this.privateMan.isBeep) {
            defaultToolkit.beep();
        }
    }
    
    private String getInfo(final String s, final String s2, final String s3) {
        String s4 = "  " + this.getConf().get("Lb.Prv.To", "To:") + " " + s;
        if (!StringUtil.isTrimmedEmpty(s3)) {
            s4 = s4 + " <" + this.getConf().get("Lb.Prv.Room", "Room") + " " + s3 + ">";
        }
        return s4 + "    " + this.getConf().get("Lb.Prv.From", "From:") + " " + this.thisUser;
    }
    
    private void updateClock() {
        this.clockLabel.setText(this.getMinutes(System.currentTimeMillis() - this.creationTime) + " ");
    }
    
    private String getMinutes(final long n) {
        final long n2 = n / 1000L;
        final long n3 = n2 / 3600L;
        final long n4 = (n2 - n3 * 3600L) / 60L;
        String s = "" + n3;
        String s2 = "" + n4;
        if (n3 < 10L) {
            s = "0" + s;
        }
        if (n4 < 10L) {
            s2 = "0" + s2;
        }
        return s + ":" + s2 + " (hh:mm)";
    }
    
    private void checkAvatarIgnore() {
        final boolean enabled = !this.privateMan.paraApplet.chatModel.cmIsIgnored(this.thirdParty);
        if (enabled != this.topLabel.isEnabled()) {
            this.topLabel.setEnabled(enabled);
        }
        if (enabled == this.topMenu.checkIgnore.getState()) {
            this.topMenu.checkIgnore.setState(!enabled);
        }
        final UserAttr cmUserAttr = this.privateMan.paraApplet.chatModel.cmUserAttr(this.thirdParty);
        if (cmUserAttr == null) {
            return;
        }
        if (cmUserAttr.avatar == null) {
            return;
        }
        if (cmUserAttr.avatar.equals(this.partyAvatar)) {
            return;
        }
        this.partyAvatar = cmUserAttr.avatar;
        final Image avatar = this.getConf().getAvatar(this.partyAvatar);
        if (avatar != null) {
            this.topLabel.setIcon(new ImageIcon(avatar));
        }
    }
    
    private void buildUI() {
        this.setJMenuBar(this.createMenu());
        final JComponent chatBox = this.getChatBox();
        final JComponent inputAndBar = this.getInputAndBar();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.getTop(), "North");
        this.getContentPane().add(chatBox, "Center");
        this.getContentPane().add(inputAndBar, "South");
    }
    
    private JComponent getTop() {
        (this.topLabel = new JLabel(this.getInfo(this.thirdParty, this.thisUser, null))).setHorizontalTextPosition(11);
        this.topLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
        this.topLabel.setForeground(Color.BLUE);
        this.clockLabel = new JLabel(this.Non_Empty);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.topLabel, "West");
        panel.add(this.clockLabel, "East");
        return panel;
    }
    
    private JComponent getChatBox() {
        (this.chatRender = new StageBox(this.privateMan.paraApplet)).setChoice(this.userChoice);
        this.chatRender.clickPrivate(false);
        return (JComponent)this.chatRender.getComp();
    }
    
    private JComponent getInputAndBar() {
        final JComponent input = this.getInput();
        final JToolBar toolbar = this.getToolbar(this.getConf());
        this.infoLabel = new JLabel(this.Non_Empty);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(toolbar, "North");
        panel.add(input, "Center");
        panel.add(this.infoLabel, "South");
        return panel;
    }
    
    private JComponent getInput() {
        final DualTextArea textInput = new DualTextArea(this.getConf(), false);
        textInput.setRows(2);
        if (this.getConf().getBool("Op.Ori.RL", false)) {
            textInput.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        final JScrollPane scrollPane = new JScrollPane(textInput.getBox(), 20, 31);
        scrollPane.setPreferredSize(new Dimension(400, 160));
        scrollPane.setMaximumSize(new Dimension(64000, 440));
        textInput.setBorder(BorderFactory.createLoweredBevelBorder());
        textInput.setEditable(true);
        textInput.setLineWrap(true);
        textInput.setWrapStyleWord(true);
        textInput.setMaximumSize(new Dimension(64000, 360));
        textInput.addKeyListener(this);
        final JButton button = new JButton(this.getConf().get("Bt.Send", "Send"));
        button.setVerticalTextPosition(0);
        button.setHorizontalTextPosition(10);
        button.setActionCommand("send");
        button.addActionListener(this);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(2, 1));
        panel.add(textInput.getBox(), "Center");
        panel.add(button, "East");
        this.textInput = textInput;
        return panel;
    }
    
    private JToolBar getToolbar(final Config config) {
        final JToolBar toolBar = new JToolBar("");
        this.boldToggle = DisplayArea.getToggle("BD", config, config.getDefOrBgImg("b16.png", "b16-bg.png"), config.getDefOrBgImg("b16p.png", "b16p-bg.png"), this, "Add.Bold", toolBar, "Tip.Bold", "Bold text");
        this.italicToggle = DisplayArea.getToggle("ITA", config, config.getDefOrBgImg("i16.png", "i16-bg.png"), config.getDefOrBgImg("i16p.png", "i16p-bg.png"), this, "Add.Italic", toolBar, "Tip.Italic", "Italic text");
        DisplayArea.getBtn("CL", config, config.getDefOrBgImg("select-color.png", "select-color-bg.png"), this, "Add.Color", toolBar, "Tip.Color", "Select color for text");
        DisplayArea.getBtn("SM", config, config.getDefOrBgImg("smiley.png", "smiley-bg.png"), this, "Add.Smiley", toolBar, "Tip.Smiley", "Select a smiley face");
        JButton button = DisplayArea.getBtn("sd", config, config.getDefOrBgImg("sound.png", "sound-bg.png"), this, "Add.Sound", toolBar, "Tip.Sound", "Send a sound to this user.");
        String s = config.getDefOrBgImg("showvid.png", "showvid-bg.png");
        if (config.isAudioOnly()) {
            s = config.getDefOrBgImg("showaud.png", "showaud-bg.png");
        }
        if (this.getConf().getBool("Ctrl.Av", false)) {
            if (config.isAudioOnly()) {
                button = DisplayArea.getBtn("av", config, s, this, "Add.Av", toolBar, "Tip.Av", "Start Private Voice Chat With This User");
            }
            else {
                button = DisplayArea.getBtn("av", config, s, this, "Add.Av", toolBar, "Tip.Av.Vid", "Start Private Voice/Video Chat With This User");
            }
        }
        toolBar.add(Box.createRigidArea(new Dimension(6, 0)));
        toolBar.add(this.typingLabel = new JLabel(this.Non_Empty));
        this.getConf().printer().print("toolbar margin," + toolBar.getMargin());
        this.getConf().printer().print("button margin," + button.getMargin());
        toolBar.setRollover(true);
        return toolBar;
    }
    
    private JMenuBar createMenu() {
        this.topMenu = new PrivMenu(this);
        final JMenuBar bar = this.topMenu.getBar();
        bar.setAlignmentX(0.5f);
        return bar;
    }
}
