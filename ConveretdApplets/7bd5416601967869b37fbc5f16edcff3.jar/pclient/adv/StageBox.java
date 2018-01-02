// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import javax.swing.text.StyleContext;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import javax.swing.JSeparator;
import pclient.shd.ClientUtil;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;
import javax.swing.Icon;
import com.pchat.sc.StringUtil;
import javax.swing.SwingUtilities;
import com.pchat.sc.MsgOptions;
import java.awt.Point;
import javax.swing.text.AttributeSet;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.event.DocumentEvent;
import java.awt.Component;
import javax.swing.BorderFactory;
import pclient.shd.UserChoice;
import javax.swing.JPopupMenu;
import java.awt.Cursor;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import pclient.shd.PrefDef;
import pclient.shd.Config;
import javax.swing.JViewport;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import pclient.shd.ChatRender;

public class StageBox implements ChatRender, MouseListener, MouseMotionListener, ActionListener, Runnable, DocumentListener
{
    private static final String CR = "\n";
    private static final String NON_EMPTY = " ";
    private static final String ACT_SALL = "sall";
    private static final String ACT_CP = "cp";
    private static final String AT_LINK = "PC_link";
    private static final String AT_CONN = "PC_conn";
    private static final String AT_USER = "PC_user";
    private AppletSpice paraApplet;
    public JTextPane textPane;
    private JScrollPane textScroll;
    private JViewport oldViewport;
    private JViewport waterViewport;
    private Config paraConf;
    private PrefDef prefDefault;
    private Style defaultStyle;
    private SimpleAttributeSet userStyle;
    private SimpleAttributeSet selfStyle;
    protected SimpleAttributeSet textStyle;
    protected SimpleAttributeSet systemStyle;
    private SimpleAttributeSet localStyle;
    private SimpleAttributeSet reverseStyle;
    protected SimpleAttributeSet linkStyle;
    private SimpleAttributeSet imageStyle;
    private SimpleAttributeSet connectStyle;
    protected SimpleAttributeSet questionStyle;
    protected SimpleAttributeSet answerStyle;
    private SimpleAttributeSet sepStyle;
    private Cursor defaultCursor;
    private Cursor handCursor;
    private float scrollPercent;
    private boolean privTrigger;
    private JPopupMenu rightPop;
    private UserChoice userChoice;
    private boolean enableTimestamp;
    private SimpleBankQueue textQueue;
    private float roundValue;
    
    public StageBox(final AppletSpice paraApplet) {
        this.oldViewport = null;
        this.waterViewport = null;
        this.scrollPercent = 0.0f;
        this.privTrigger = false;
        this.userChoice = null;
        this.enableTimestamp = true;
        this.roundValue = 0.9f;
        this.paraApplet = paraApplet;
        this.textQueue = new SimpleBankQueue();
        this.setConf(this.paraApplet.paraConf);
        this.roundValue = this.paraConf.getInt("Sb.Round", 90) / 100.0f;
    }
    
    public void run() {
        try {
            this.doChanges();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void setChoice(final UserChoice userChoice) {
        this.userChoice = userChoice;
    }
    
    public void removeScrcollBorder() {
        this.textScroll.setBorder(BorderFactory.createEmptyBorder());
    }
    
    public Component getComp() {
        return this.textScroll;
    }
    
    public void toggleWater(final boolean b) {
        if (this.waterViewport == null) {
            return;
        }
        if (b) {
            this.textPane.setOpaque(false);
            this.waterViewport.setView(this.textPane);
            this.textScroll.setViewport(this.waterViewport);
        }
        else {
            this.textPane.setOpaque(true);
            this.oldViewport.setView(this.textPane);
            this.textScroll.setViewport(this.oldViewport);
        }
    }
    
    public void setWatermark(final WmViewport wmViewport) {
        this.textPane.setOpaque(false);
        wmViewport.setView(this.textPane);
        this.textScroll.setViewport(wmViewport);
        wmViewport.setScrollMode(2);
        this.waterViewport = wmViewport;
    }
    
    public void clickPrivate(final boolean privTrigger) {
        this.privTrigger = privTrigger;
    }
    
    public void enableTime(final boolean enableTimestamp) {
        this.enableTimestamp = enableTimestamp;
    }
    
    public void insertUpdate(final DocumentEvent documentEvent) {
        this.paraConf.printer().print("insert event");
    }
    
    public void removeUpdate(final DocumentEvent documentEvent) {
        this.paraConf.printer().print("remove event");
    }
    
    public void changedUpdate(final DocumentEvent documentEvent) {
    }
    
    private void setConf(final Config paraConf) {
        this.paraConf = paraConf;
        this.prefDefault = paraConf.getPref();
        this.buildUI();
        this.createPop();
        this.defaultCursor = Cursor.getDefaultCursor();
        this.handCursor = Cursor.getPredefinedCursor(12);
        this.initStyles();
    }
    
    public void fontChanged(final int n) {
        StyleConstants.setFontSize(this.defaultStyle, n);
        this.textPane.updateUI();
    }
    
    public void clearText() {
        this.textPane.setText("");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if ("cp".equals(actionEvent.getActionCommand())) {
            this.textPane.copy();
            return;
        }
        if ("sall".equals(actionEvent.getActionCommand())) {
            this.textPane.selectAll();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            return;
        }
        if (mouseEvent.getClickCount() == 2) {}
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
        final AttributeSet link = this.isLink(mouseEvent);
        if (!this.containsLink(link)) {
            return;
        }
        final String s = (String)link.getAttribute("PC_link");
        if (s != null) {
            this.paraConf.printer().print("load=[" + s + "]");
            this.paraConf.loadPage(s);
            return;
        }
        final String s2 = (String)link.getAttribute("PC_user");
        if (s2 != null) {
            this.paraConf.printer().print("private chat with," + s2);
            this.paraApplet.startPrivate(s2);
            return;
        }
        if (link.getAttribute("PC_conn") != null) {
            this.paraConf.printer().print("try to reconnect");
            this.paraApplet.reconnect();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.containsLink(this.isLink(mouseEvent))) {
            if (this.textPane.getCursor() != this.handCursor) {
                this.textPane.setCursor(this.handCursor);
            }
        }
        else if (this.textPane.getCursor() != this.defaultCursor) {
            this.textPane.setCursor(this.defaultCursor);
        }
    }
    
    private boolean containsLink(final AttributeSet set) {
        return set.getAttribute("PC_link") != null || set.getAttribute("PC_user") != null || set.getAttribute("PC_conn") != null;
    }
    
    private AttributeSet isLink(final MouseEvent mouseEvent) {
        final int viewToModel = this.textPane.viewToModel(new Point(mouseEvent.getX(), mouseEvent.getY()));
        if (viewToModel < 0) {
            return null;
        }
        return this.textPane.getStyledDocument().getCharacterElement(viewToModel).getAttributes();
    }
    
    public void showSelf(final String s, final String s2, final MsgOptions msgOptions) {
        this.savePos();
        this.drawTime(this.selfStyle);
        this.showMsg(s + this.prefDefault.selfSuffix + " ", this.selfStyle);
        this.addContent(s2, this.getCustomStyle(msgOptions, this.textStyle));
        this.newLine();
        this.drawExtra();
        this.scrollBottom();
    }
    
    public void showOthers(final String s, final String s2, final MsgOptions msgOptions) {
        this.savePos();
        this.drawTime(this.userStyle);
        final String string = s + this.prefDefault.otherSuffix + " ";
        if (this.privTrigger) {
            final SimpleAttributeSet set = new SimpleAttributeSet();
            set.setResolveParent(this.userStyle);
            set.addAttribute("PC_user", s);
            this.showMsg(string, set);
        }
        else {
            this.showMsg(string, this.userStyle);
        }
        this.addContent(s2, this.getCustomStyle(msgOptions, this.textStyle));
        this.newLine();
        this.drawExtra();
        this.scrollPos();
    }
    
    public void modMsg(final String s, final String s2) {
        this.savePos();
        this.drawTime(this.userStyle);
        this.showMsg(s + this.prefDefault.otherSuffix + " ", this.userStyle);
        this.addContent(s2, this.answerStyle);
        this.newLine();
        this.newLine();
        this.drawExtra();
        this.scrollPos();
    }
    
    public void modAnswer(final String s, final String s2, final String s3, final String s4) {
        this.savePos();
        this.drawTime(this.userStyle);
        this.showMsg(s + this.prefDefault.otherSuffix + " ", this.userStyle);
        this.addContent(s2, this.questionStyle);
        this.newLine();
        if (s4 != null) {
            this.drawTime(this.userStyle);
            this.showMsg(s3 + this.prefDefault.otherSuffix + " ", this.userStyle);
            this.addContent(s4, this.answerStyle);
            this.newLine();
        }
        this.newLine();
        this.drawExtra();
        this.scrollPos();
    }
    
    public void showSysInfo(final String s, final MsgOptions msgOptions) {
        this.savePos();
        this.drawTime(this.systemStyle);
        this.addContent(s, this.systemStyle);
        this.newLine();
        this.drawExtra();
        this.scrollPos();
    }
    
    public void showSysError(final String s, final MsgOptions msgOptions) {
        this.savePos();
        this.drawTime(this.systemStyle);
        this.addContent(s, this.systemStyle);
        this.newLine();
        this.drawExtra();
        this.scrollPos();
    }
    
    public void showLocalInfo(final String s, final MsgOptions msgOptions) {
        this.savePos();
        this.drawTime(this.localStyle);
        this.addContent(s, this.localStyle);
        this.newLine();
        this.drawExtra();
        this.scrollPos();
    }
    
    public void showLocalAlert(final String s, final MsgOptions msgOptions) {
        this.savePos();
        this.drawTime(this.localStyle);
        this.addContent(s, this.reverseStyle);
        this.newLine();
        this.drawExtra();
        this.scrollPos();
    }
    
    public void showLocalError(final String s, final MsgOptions msgOptions, final boolean b) {
        this.savePos();
        this.drawTime(this.localStyle);
        this.addContent(s, this.localStyle);
        if (b) {
            this.showMsg(" " + this.paraConf.get("Msg.Reconnect", "Click here to reconnect."), this.connectStyle);
        }
        this.newLine();
        this.drawExtra();
        this.scrollPos();
    }
    
    public void prependPublic(final String s, final String s2, final MsgOptions msgOptions, final String s3) {
        this.prepend("\n", this.textStyle);
        this.prependContent(s2, this.getCustomStyle(msgOptions, this.textStyle));
        this.prepend(s + this.prefDefault.otherSuffix + " ", this.userStyle);
        this.prepend("\n", this.textStyle);
        this.prepend(s3, this.textStyle);
    }
    
    public void scrollBottom() {
        this.paraConf.printer().print("#### scroll to bottom.");
        this.scrollTask(this.scrollPercent = 1.0f);
    }
    
    private void scrollTask(final float n) {
        final SimpleQueueItem simpleQueueItem = new SimpleQueueItem();
        simpleQueueItem.type = 10;
        simpleQueueItem.obj = new Float(n);
        this.textQueue.add(simpleQueueItem);
        SwingUtilities.invokeLater(this);
    }
    
    private void savePos() {
        final JViewport viewport = this.textScroll.getViewport();
        final int n = viewport.getViewPosition().y + viewport.getExtentSize().height;
        final int height = viewport.getViewSize().height;
        this.scrollPercent = n / height;
        this.paraConf.printer().print("currentH=" + n);
        this.paraConf.printer().print("totalH=" + height);
        this.paraConf.printer().print("saved percent=" + this.scrollPercent);
        this.paraConf.printer().print("view=" + viewport.getViewPosition());
        if (this.paraConf.getBool("Op.Scroll", true) && this.scrollPercent > this.roundValue) {
            this.scrollPercent = 1.0f;
        }
        this.checkMax();
    }
    
    private void scrollPos() {
        this.scrollTask(this.scrollPercent);
    }
    
    private void handleScrollPos(final float n) {
        if (n >= 1.0f) {
            try {
                this.textPane.setCaretPosition(this.textPane.getDocument().getLength());
            }
            catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
            return;
        }
        final JViewport viewport = this.textScroll.getViewport();
        final int height = viewport.getViewSize().height;
        final int n2 = (int)(height * n);
        final Point viewPosition = new Point(0, n2 - viewport.getExtentSize().height);
        try {
            viewport.setViewPosition(viewPosition);
        }
        catch (IndexOutOfBoundsException ex2) {
            System.out.println("Err378923.");
            ex2.printStackTrace();
        }
        this.paraConf.printer().print("scrolling:");
        this.paraConf.printer().print("currentH=" + n2);
        this.paraConf.printer().print("totalH=" + height);
        this.paraConf.printer().print("scroll to percent=" + n);
        this.paraConf.printer().print("position=" + viewPosition);
    }
    
    private SimpleAttributeSet getCustomStyle(final MsgOptions msgOptions, final SimpleAttributeSet resolveParent) {
        if (msgOptions == null) {
            return resolveParent;
        }
        if (msgOptions.color == null && StringUtil.isEmpty(msgOptions.fontname) && !msgOptions.fontBold && !msgOptions.fontItalic) {
            return resolveParent;
        }
        final SimpleAttributeSet set = new SimpleAttributeSet();
        set.setResolveParent(resolveParent);
        if (msgOptions.color != null) {
            StyleConstants.setForeground(set, msgOptions.color);
        }
        if (msgOptions.fontBold) {
            StyleConstants.setBold(set, true);
        }
        if (msgOptions.fontItalic) {
            StyleConstants.setItalic(set, true);
        }
        if (!StringUtil.isEmpty(msgOptions.fontname)) {
            StyleConstants.setFontFamily(set, msgOptions.fontname);
        }
        return set;
    }
    
    private void addContent(final String s, final AttributeSet set) {
        final ContentElement[] parse = this.paraApplet.msgParser.parse(s);
        if (parse == null) {
            return;
        }
        for (int i = 0; i < parse.length; ++i) {
            final ContentElement contentElement = parse[i];
            if (contentElement.type == 2) {
                this.showMsg(contentElement.text, set);
            }
            else if (contentElement.type == 4) {
                final Icon smile = this.paraApplet.getSmile(contentElement.imageID);
                if (smile == null || !this.paraApplet.vwGlobal().smiley) {
                    this.showMsg(contentElement.text, set);
                }
                else {
                    this.addIcon(smile, true);
                }
            }
            else if (contentElement.type == 6) {
                if (this.prefDefault.link && this.paraConf.getBool("Op.Clickable", true)) {
                    this.addLink(contentElement.text, contentElement.link, true);
                }
                else {
                    this.showMsg(contentElement.text, set);
                }
            }
        }
    }
    
    private void newLine() {
        this.showMsg("\n", this.textStyle);
    }
    
    private void showMsgLine(final String s, final AttributeSet set) {
        this.showMsg(s, set);
        this.newLine();
    }
    
    private void showMsg(final String s, final AttributeSet set) {
        this.paraConf.printer().print("append=" + s);
        this.handleShowMsg(s, set);
    }
    
    private void handleShowMsg(final String s, final AttributeSet set) {
        final Document document = this.textPane.getDocument();
        try {
            document.insertString(document.getLength(), s, set);
        }
        catch (BadLocationException ex) {
            System.out.println("Err 83479," + s);
            ex.printStackTrace();
        }
    }
    
    private void prependContent(final String s, final AttributeSet set) {
        final ContentElement[] parse = this.paraApplet.msgParser.parse(s);
        if (parse == null) {
            return;
        }
        for (int i = parse.length - 1; i >= 0; --i) {
            final ContentElement contentElement = parse[i];
            if (contentElement.type == 2) {
                this.prepend(contentElement.text, set);
            }
            else if (contentElement.type == 4) {
                final Icon smile = this.paraApplet.getSmile(contentElement.imageID);
                if (smile == null || !this.paraApplet.vwGlobal().smiley) {
                    this.prepend(contentElement.text, set);
                }
                else {
                    this.addIcon(smile, false);
                }
            }
            else if (contentElement.type == 6) {
                if (this.prefDefault.link && this.paraConf.getBool("Op.Clickable", true)) {
                    this.addLink(contentElement.text, contentElement.link, false);
                }
                else {
                    this.prepend(contentElement.text, set);
                }
            }
        }
    }
    
    private void prepend(final String s, final AttributeSet set) {
        this.handlePrepend(s, set);
    }
    
    private void handlePrepend(final String s, final AttributeSet set) {
        this.paraConf.printer().print("prepend=" + s);
        final Document document = this.textPane.getDocument();
        try {
            document.insertString(0, s, set);
        }
        catch (Exception ex) {
            System.out.println("Err 88359");
            ex.printStackTrace();
        }
    }
    
    private void drawTime(final AttributeSet set) {
        if (!this.enableTimestamp) {
            return;
        }
        if (!this.paraApplet.vwGlobal().timestamp) {
            return;
        }
        this.showMsg(ClientUtil.getStamp(this.paraConf, null), set);
    }
    
    private void drawExtra() {
        this.drawSep();
        this.drawDouble();
    }
    
    private void drawSep() {
        if (this.userChoice == null) {
            return;
        }
        if (!this.userChoice.separator) {
            return;
        }
        final SimpleAttributeSet set = new SimpleAttributeSet();
        this.sepStyle.setResolveParent(this.sepStyle);
        StyleConstants.setComponent(set, new JSeparator(0));
        this.showMsg(" ", set);
        this.showMsg("\n", this.textStyle);
    }
    
    private void drawDouble() {
        if (this.userChoice == null) {
            return;
        }
        if (!this.userChoice.doubleSpace) {
            return;
        }
        this.showMsg("\n", this.textStyle);
    }
    
    private void checkMax() {
        final int int1 = this.paraConf.getInt("Max.TextLen", 8000);
        if (int1 <= 0) {
            return;
        }
        final StyledDocument styledDocument = this.textPane.getStyledDocument();
        final int length = styledDocument.getLength();
        if (length <= int1) {
            return;
        }
        int endOffset = length / 4;
        if (endOffset <= 0) {
            return;
        }
        final Element characterElement = styledDocument.getCharacterElement(endOffset);
        if (characterElement != null) {
            endOffset = characterElement.getEndOffset();
        }
        else {
            System.out.println("err,doc934," + endOffset);
        }
        try {
            styledDocument.remove(0, endOffset);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void addIcon(final Icon icon, final boolean b) {
        this.paraConf.printer().print("inserting icon," + icon);
        final SimpleAttributeSet set = new SimpleAttributeSet();
        set.setResolveParent(this.textStyle);
        StyleConstants.setIcon(set, icon);
        if (b) {
            this.showMsg(" ", set);
        }
        else {
            this.prepend(" ", set);
        }
    }
    
    private void addLink(final String s, final String s2, final boolean b) {
        this.paraConf.printer().print("inserting link," + s + "|" + s2);
        final SimpleAttributeSet set = new SimpleAttributeSet();
        set.setResolveParent(this.linkStyle);
        set.addAttribute("PC_link", s2);
        if (b) {
            this.showMsg(s, set);
        }
        else {
            this.prepend(s, set);
        }
    }
    
    private void initStyles() {
        this.textPane.getStyledDocument();
        this.defaultStyle = StyleContext.getDefaultStyleContext().getStyle("default");
        (this.userStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setForeground(this.userStyle, this.prefDefault.userName);
        StyleConstants.setBold(this.userStyle, true);
        (this.selfStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setForeground(this.selfStyle, this.prefDefault.selfName);
        StyleConstants.setBold(this.selfStyle, true);
        (this.textStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setForeground(this.textStyle, this.prefDefault.textColor);
        (this.systemStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setForeground(this.systemStyle, this.prefDefault.systemColor);
        (this.localStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setForeground(this.localStyle, this.prefDefault.localColor);
        (this.linkStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setUnderline(this.linkStyle, true);
        StyleConstants.setForeground(this.linkStyle, this.prefDefault.linkColor);
        final boolean bool = this.paraConf.getBool("Val.Mod.Bold", true);
        (this.questionStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setForeground(this.questionStyle, this.prefDefault.questionColor);
        StyleConstants.setBold(this.questionStyle, bool);
        (this.answerStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setForeground(this.answerStyle, this.prefDefault.answerColor);
        StyleConstants.setBold(this.answerStyle, bool);
        (this.imageStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        (this.connectStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        this.connectStyle.addAttribute("PC_conn", "PC_conn");
        (this.sepStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setAlignment(this.sepStyle, 1);
        (this.reverseStyle = new SimpleAttributeSet()).setResolveParent(this.defaultStyle);
        StyleConstants.setForeground(this.reverseStyle, this.textPane.getBackground());
        StyleConstants.setBackground(this.reverseStyle, this.prefDefault.systemColor);
        StyleConstants.setBold(this.reverseStyle, true);
    }
    
    private void buildUI() {
        this.textPane = new JTextPane();
        this.textPane.getDocument().addDocumentListener(this);
        this.textPane.setDoubleBuffered(true);
        this.textPane.setEditable(false);
        this.textPane.addMouseListener(this);
        this.textPane.addMouseMotionListener(this);
        if (this.paraConf.getBool("Op.Ori.RL", false)) {
            this.textPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        (this.textScroll = new JScrollPane(this.textPane, 20, 31)).setMinimumSize(new Dimension(30, 60));
        this.textScroll.setOpaque(true);
        this.textScroll.getViewport().setScrollMode(2);
        this.oldViewport = this.textScroll.getViewport();
    }
    
    private void createPop() {
        final JPopupMenu rightPop = new JPopupMenu();
        final JMenuItem menuItem = new JMenuItem(this.paraConf.get("Pm.Copy", "Copy"));
        menuItem.addActionListener(this);
        menuItem.setActionCommand("cp");
        if (this.paraConf.getBool("Op.TextCopy", false)) {
            rightPop.add(menuItem);
        }
        final JMenuItem menuItem2 = new JMenuItem(this.paraConf.get("Pm.Select", "Select All"));
        menuItem2.addActionListener(this);
        menuItem2.setActionCommand("sall");
        rightPop.add(menuItem2);
        this.rightPop = rightPop;
    }
    
    private void maybeShowPopup(final MouseEvent mouseEvent) {
        if (!this.paraConf.getBool("Op.Chat.RtCk", true)) {
            return;
        }
        if (mouseEvent.isPopupTrigger()) {
            this.rightPop.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    private void doChanges() {
        final SimpleQueueItem[] dequeueAll = this.textQueue.dequeueAll();
        if (dequeueAll == null) {
            return;
        }
        for (int i = 0; i < dequeueAll.length; ++i) {
            final SimpleQueueItem simpleQueueItem = dequeueAll[i];
            switch (simpleQueueItem.type) {
                case 2: {
                    break;
                }
                case 8: {
                    break;
                }
                case 10: {
                    this.handleScrollPos((float)simpleQueueItem.obj);
                    break;
                }
                default: {
                    System.err.println("Err78238." + simpleQueueItem);
                    break;
                }
            }
        }
    }
}
