// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.Box;
import pclient.adv.CompUtil;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JOptionPane;
import com.pchat.sc.StringUtil;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import pclient.shd.Config;
import pclient.adv.AppletSpice;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ModWindow extends JFrame implements ActionListener, WindowListener, MouseListener
{
    private static final String INFO_NO_SELECT = "Please make a selection in the list first.";
    private static final String ALERT_NO_SPEAKER = "There is no speaker available to receive forwarded question. \nThis question will be lost unless a new speaker logs in later. \nDo you want to continue?";
    private static final String ERR_TO_ALL = "Sorry, there is no speaker available to receive forwarded question.";
    private static final String ALERT_DEL = "Are you sure you want to delete all the questions in the window?";
    private static final String TIP_FETCH = "Retrieve more questions";
    private static final String TIP_PRE = "Display previous questions";
    private static final String TIP_NEXT = "Display more questions";
    private static final String TIP_ANSWER = "Answer or post a question";
    private static final String TIP_EDIT = "Edit a question";
    private static final String TIP_FORWARD = "Forward for any speaker to retrieve";
    private static final String TIP_FORWARD_TO = "Forward to a specific speaker or moderator";
    private static final String TIP_FORWARD_ALL = "Forward to all speakers";
    private static final String TIP_POST = "Compose and send a message directly to the room";
    private static final String TIP_GAG = "Ignore the user who submitted the selected question";
    private static final String TIP_DELETE = "Delete a question";
    private static final String ACT_FETCH = "fet";
    private static final String ACT_PRE = "pre";
    private static final String ACT_NEXT = "next";
    private static final String ACT_ANSWER = "ans";
    private static final String ACT_EDIT = "edit";
    private static final String ACT_FORWARD = "fwd";
    private static final String ACT_FORWARD_TO = "fwdto";
    private static final String ACT_FORWARD_ALL = "fwdall";
    private static final String ACT_POST = "post";
    private static final String ACT_GAG = "gag";
    private static final String ACT_DELETE = "del";
    private static final String ACT_DEL_ALL = "delall";
    private static final String ACT_VIEW_IGNORED = "ignore";
    private static final String ACT_VIEW_STORED = "store";
    private static final String ACT_MOD_HELP = "modH";
    private static final String ACT_CHAT_HELP = "chatH";
    private static final String ACT_ENTER_CHECK = "enterGo";
    private JLabel infoLabel;
    protected JLabel countLabel;
    private JCheckBoxMenuItem enterCheckbox;
    private JCheckBoxMenuItem autoCheckbox;
    private DefaultListModel modListModel;
    private JList modList;
    private JTextArea questionDisplay;
    private MdPost modPost;
    private MdEdit modEdit;
    private MdReply modAnswer;
    protected MdIgnore modIgnore;
    private MdDirectForwd modDirect;
    private MdAskMe modDelete;
    protected MdMsg modMsg;
    protected AppletSpice paraApplet;
    protected ModHandler modHandler;
    protected Config paraConf;
    protected ModManager modMan;
    
    public ModWindow(final ModManager modMan) {
        this.modMan = modMan;
    }
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.paraConf = paraApplet.paraConf;
        this.modHandler = new ModHandler(paraApplet, this);
        this.setSize(640, 460);
        this.addWindowListener(this);
        this.setDefaultCloseOperation(0);
        this.buildGUI();
    }
    
    public void startUp() {
        String cmUserSelf = this.modMan.paraApplet.chatModel.cmUserSelf();
        if (cmUserSelf == null) {
            cmUserSelf = "";
        }
        this.setTitle("Moderation Console - " + cmUserSelf);
        this.setVisible(true);
    }
    
    public void clearAll() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void addItem(final String id, final String sender, final String question) {
        final QueueData queueData = new QueueData();
        queueData.id = id;
        queueData.sender = sender;
        queueData.question = question;
        this.modListModel.addElement(queueData);
    }
    
    public void removeAllQuestions() {
        this.modListModel.clear();
    }
    
    public void forwardSpeaker(final String s, final String s2) {
        if (s2 == null) {
            return;
        }
        final QueueData question = this.findQuestion(s);
        if (question == null) {
            return;
        }
        this.modHandler.mdForwardTo(s, s2);
        question.forwarded = true;
        this.modList.repaint();
        this.printInfo("Forwarded to: " + s2);
    }
    
    public void forwardModerator(final String s, final String s2) {
        if (s2 == null) {
            return;
        }
        final QueueData question = this.findQuestion(s);
        if (question == null) {
            return;
        }
        this.modHandler.mdForwardTo(s, s2);
        question.forwarded = true;
        this.modList.repaint();
        this.printInfo("Forwarded to: " + s2);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        this.paraConf.printer().print("mod console closed ");
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.clearInfo();
        final String actionCommand = actionEvent.getActionCommand();
        this.paraConf.printer().print("com=" + actionCommand);
        if ("fet".equals(actionCommand)) {
            this.questionDisplay.setText("");
            this.removeAllQuestions();
            this.modHandler.mdFetch();
        }
        else if ("pre".equals(actionCommand)) {
            this.questionDisplay.setText("");
            this.removeAllQuestions();
            this.modHandler.mdPrevious();
        }
        else if ("next".equals(actionCommand)) {
            this.questionDisplay.setText("");
            this.removeAllQuestions();
            this.modHandler.mdNext();
        }
        else if ("ans".equals(actionCommand)) {
            this.actAnswer();
        }
        else if ("edit".equals(actionCommand)) {
            this.actEdit();
        }
        else if ("fwd".equals(actionCommand)) {
            this.actForward();
        }
        else if ("fwdto".equals(actionCommand)) {
            this.actForwardTo();
        }
        else if ("fwdall".equals(actionCommand)) {
            this.actForwardAll();
        }
        else if ("post".equals(actionCommand)) {
            this.actPost();
        }
        else if ("gag".equals(actionCommand)) {
            this.actGag();
        }
        else if ("del".equals(actionCommand)) {
            this.actDelete();
        }
        else if ("delall".equals(actionCommand)) {
            this.actDeleteAll();
        }
        else if ("ignore".equals(actionCommand)) {
            this.modIgnore.showWin();
        }
        else if ("store".equals(actionCommand)) {
            this.modMsg.setVisible(true);
        }
        else if ("modH".equals(actionCommand)) {
            final String value = this.paraConf.get("Mod.Menu.ModHelpURL", "http://parachat.com/documentation/570/moderation.php");
            this.paraApplet.vwInfo(value);
            this.paraConf.loadPage(value);
        }
        else if ("chatH".equals(actionCommand)) {
            final String value2 = this.paraConf.get("Mod.Menu.ChatHelpURL", "http://www.parachat.com/helpdesk/");
            this.paraApplet.vwInfo(value2);
            this.paraConf.loadPage(value2);
        }
        else if ("enterGo".equals(actionCommand)) {
            final boolean state = this.enterCheckbox.getState();
            this.modPost.setEnter(state);
            this.modEdit.setEnter(state);
            this.modAnswer.setEnter(state);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() != 1) {
            return;
        }
        final int selectedIndex = this.modList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        this.questionDisplay.setText(((QueueData)this.modListModel.get(selectedIndex)).question);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    protected void printInfo(String string) {
        string = " " + string;
        this.infoLabel.setText(string);
    }
    
    protected void clearInfo() {
        this.infoLabel.setText(" ");
    }
    
    protected void printLog(final String s) {
    }
    
    private void actAnswer() {
        final QueueData selectedItem = this.selectedItem();
        if (selectedItem == null) {
            this.printInfo("Please make a selection in the list first.");
            return;
        }
        this.modAnswer.setQuestion(selectedItem.id, selectedItem.sender, selectedItem.question);
        this.modAnswer.showWin();
    }
    
    protected void responseAnswer() {
        final String questionID = this.modAnswer.questionID;
        if (this.modAnswer.isPostQuestion()) {
            this.paraConf.printer().print("post:" + questionID);
            this.modHandler.mdShowQuestion(questionID);
            this.checkAutoAnswerDelete(questionID);
            return;
        }
        final String input = this.modAnswer.getInput();
        if (StringUtil.isEmpty(input)) {
            this.printInfo("Empty answer. Ignored. You may try to answer again.");
            return;
        }
        this.paraConf.printer().print("input=" + input);
        this.modHandler.mdAnswer(questionID, input);
        this.checkAutoAnswerDelete(questionID);
    }
    
    private void checkAutoAnswerDelete(final String s) {
        if (this.autoCheckbox.getState()) {
            this.modHandler.mdDelete(s);
            this.deleteSelected();
        }
    }
    
    private void actEdit() {
        final QueueData selectedItem = this.selectedItem();
        if (selectedItem == null) {
            this.printInfo("Please make a selection in the list first.");
            return;
        }
        this.modEdit.setInput(selectedItem.id, selectedItem.sender, selectedItem.question);
        this.modEdit.showWin();
    }
    
    protected void responseEdit() {
        final String input = this.modEdit.getInput();
        if (input == null) {
            this.paraConf.printer().print("edit canceled");
            return;
        }
        this.paraConf.printer().print("input=" + input);
        final QueueData selectedItem = this.selectedItem();
        if (selectedItem != null) {
            if (input.equals(selectedItem.question)) {
                return;
            }
            selectedItem.question = input;
            this.questionDisplay.setText(selectedItem.question);
        }
        this.modEdit.clearInput();
        this.modHandler.mdEdit(this.modEdit.questionID, input);
        this.modList.repaint();
    }
    
    private void actForward() {
        final QueueData selectedItem = this.selectedItem();
        if (selectedItem == null) {
            this.printInfo("Please make a selection in the list first.");
            return;
        }
        if (this.noSpeaker() && JOptionPane.showConfirmDialog(this, "There is no speaker available to receive forwarded question. \nThis question will be lost unless a new speaker logs in later. \nDo you want to continue?", "Moderation", 0) != 0) {
            return;
        }
        this.modHandler.mdForward(selectedItem.id);
        selectedItem.forwarded = true;
        this.deleteSelected();
    }
    
    private void actPost() {
        this.modPost.showWin();
    }
    
    protected void responsePost() {
        final String input = this.modPost.getInput();
        if (input == null) {
            this.paraConf.printer().print("post canceled");
            return;
        }
        this.paraConf.printer().print("input=" + input);
        this.modHandler.mdPost(input);
        this.modPost.clearInput();
    }
    
    private void actGag() {
        final QueueData selectedItem = this.selectedItem();
        if (selectedItem == null) {
            this.printInfo("Please make a selection in the list first.");
            return;
        }
        final String sender = selectedItem.sender;
        this.modIgnore.add(sender);
        this.printInfo("User is gagged: " + sender);
    }
    
    private void actForwardTo() {
        final QueueData selectedItem = this.selectedItem();
        if (selectedItem == null) {
            this.printInfo("Please make a selection in the list first.");
            return;
        }
        this.modDirect.setQuestion(selectedItem.id, selectedItem.sender, selectedItem.question, this.modMan.paraApplet.chatModel.cmModerators(), this.modMan.paraApplet.chatModel.cmSpeakers());
        this.modDirect.showWin();
    }
    
    private void actForwardAll() {
        final QueueData selectedItem = this.selectedItem();
        if (selectedItem == null) {
            this.printInfo("Please make a selection in the list first.");
            return;
        }
        if (this.noSpeaker()) {
            JOptionPane.showMessageDialog(this, "Sorry, there is no speaker available to receive forwarded question.");
            return;
        }
        this.modHandler.mdForwardToAll(selectedItem.id);
        selectedItem.forwarded = true;
        this.deleteSelected();
    }
    
    private void actDelete() {
        final QueueData selectedItem = this.selectedItem();
        if (selectedItem == null) {
            this.printInfo("Please make a selection in the list first.");
            return;
        }
        if (!this.modDelete.noNeedToAsk()) {
            this.modDelete.showWin();
        }
        if (this.modDelete.isCanceled()) {
            this.paraConf.printer().print("delete canceled");
            return;
        }
        if (this.modList.getSelectedIndex() < 0) {
            this.printInfo("Please make a selection in the list first.");
            return;
        }
        this.modHandler.mdDelete(selectedItem.id);
        this.deleteSelected();
    }
    
    private void actDeleteAll() {
        final int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete all the questions in the window?", "Clear", 0);
        if (showConfirmDialog == 0) {
            this.deleteAll();
            this.modListModel.clear();
        }
        else if (showConfirmDialog == 1) {}
    }
    
    private void deleteAll() {
        for (int i = 0; i < this.modListModel.size(); ++i) {
            this.modHandler.mdDelete(((QueueData)this.modListModel.get(i)).id);
        }
    }
    
    private QueueData selectedItem() {
        final int selectedIndex = this.modList.getSelectedIndex();
        if (selectedIndex < 0) {
            return null;
        }
        return (QueueData)this.modListModel.get(selectedIndex);
    }
    
    private QueueData findQuestion(final String s) {
        for (int i = 0; i < this.modListModel.size(); ++i) {
            final QueueData queueData = this.modListModel.get(i);
            if (queueData.id.equals(s)) {
                return queueData;
            }
        }
        return null;
    }
    
    private boolean noSpeaker() {
        final String[] cmSpeakers = this.modMan.paraApplet.chatModel.cmSpeakers();
        return cmSpeakers == null || cmSpeakers.length == 0;
    }
    
    protected void deleteSelected() {
        final int selectedIndex = this.modList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        this.modListModel.remove(selectedIndex);
        this.modList.repaint();
        this.questionDisplay.setText("");
    }
    
    private void buildGUI() {
        this.modPost = new MdPost(this);
        this.modEdit = new MdEdit(this);
        this.modAnswer = new MdReply(this);
        this.modIgnore = new MdIgnore(this);
        this.modDirect = new MdDirectForwd(this);
        this.modDelete = new MdAskMe(this);
        this.modMsg = new MdMsg(this);
        this.setJMenuBar(this.createMenu());
        final JSplitPane splitPane = new JSplitPane(0, this.getCenter(), this.getSouth());
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(0.85);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.getNorth(), "North");
        this.getContentPane().add(splitPane, "Center");
        this.getContentPane().add(this.getBottom(), "South");
    }
    
    private JMenuBar createMenu() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu("Action");
        menuBar.add(menu);
        menu.setToolTipText("Moderation action");
        final JMenuItem menuItem = new JMenuItem("Delete all questions in current Window");
        menu.add(menuItem);
        menuItem.setActionCommand("delall");
        menuItem.addActionListener(this);
        menu.addSeparator();
        final JMenuItem menuItem2 = new JMenuItem("View Gagged Users");
        menu.add(menuItem2);
        menuItem2.setActionCommand("ignore");
        menuItem2.addActionListener(this);
        final JMenuItem menuItem3 = new JMenuItem("View Stored Messages");
        menu.add(menuItem3);
        menuItem3.setActionCommand("store");
        menuItem3.addActionListener(this);
        final JMenu menu2 = new JMenu("Options");
        menuBar.add(menu2);
        menu2.setToolTipText("Options of Moderaation");
        this.enterCheckbox = new JCheckBoxMenuItem("Allow Enter key to submit in Post", false);
        final JCheckBoxMenuItem enterCheckbox = this.enterCheckbox;
        enterCheckbox.setActionCommand("enterGo");
        enterCheckbox.addActionListener(this);
        enterCheckbox.setToolTipText("Pressing Enter in a message window sends the message");
        menu2.add(enterCheckbox);
        this.autoCheckbox = new JCheckBoxMenuItem("Delete question after it is answered", true);
        final JCheckBoxMenuItem autoCheckbox = this.autoCheckbox;
        autoCheckbox.addActionListener(this);
        autoCheckbox.setToolTipText("Delete question after it is answered or posted");
        menu2.add(autoCheckbox);
        final JMenu menu3 = new JMenu("Help");
        menuBar.add(menu3);
        menu3.setToolTipText("Help Info");
        final JMenuItem menuItem4 = new JMenuItem("Moderation Online Help");
        menu3.add(menuItem4);
        menuItem4.setActionCommand("modH");
        menuItem4.addActionListener(this);
        final JMenuItem menuItem5 = new JMenuItem("Chat FAQ");
        menu3.add(menuItem5);
        menuItem5.setActionCommand("chatH");
        menuItem5.addActionListener(this);
        return menuBar;
    }
    
    private JComponent getNorth() {
        final JLabel countLabel = new JLabel(" ");
        countLabel.setForeground(Color.BLUE);
        return this.countLabel = countLabel;
    }
    
    private JComponent getCenter() {
        final DefaultListModel<Object> modListModel = new DefaultListModel<Object>();
        final JList modList = new JList(modListModel);
        modList.setVisibleRowCount(32);
        modList.setSelectionMode(0);
        modList.setCellRenderer(new QueueCell(this, modListModel, modList));
        modList.addMouseListener(this);
        final JScrollPane scrollPane = new JScrollPane(modList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(100, 240));
        scrollPane.setPreferredSize(new Dimension(120, 260));
        this.modListModel = modListModel;
        this.modList = modList;
        return scrollPane;
    }
    
    private JComponent getSouth() {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.setAlignmentX(0.0f);
        verticalPanel.add(this.getQuickView());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 2)));
        verticalPanel.add(this.getButtons());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 2)));
        return verticalPanel;
    }
    
    private JComponent getQuickView() {
        final JTextArea questionDisplay = new JTextArea();
        questionDisplay.setRows(3);
        questionDisplay.setColumns(36);
        questionDisplay.setEditable(false);
        questionDisplay.setLineWrap(true);
        questionDisplay.setWrapStyleWord(true);
        final JScrollPane scrollPane = new JScrollPane(questionDisplay, 20, 31);
        scrollPane.setPreferredSize(new Dimension(120, 48));
        this.questionDisplay = questionDisplay;
        return scrollPane;
    }
    
    private JComponent getButtons() {
        final JButton genButton = this.genButton("Fetch", "fet", "Retrieve more questions");
        final JButton genButton2 = this.genButton("Previous", "pre", "Display previous questions");
        final JButton genButton3 = this.genButton("Next", "next", "Display more questions");
        final JButton genButton4 = this.genButton("Answer", "ans", "Answer or post a question");
        final JButton genButton5 = this.genButton("Edit", "edit", "Edit a question");
        final JButton genButton6 = this.genButton("Forward", "fwd", "Forward for any speaker to retrieve");
        final JButton genButton7 = this.genButton("Forward To", "fwdto", "Forward to a specific speaker or moderator");
        final JButton genButton8 = this.genButton("Post", "post", "Compose and send a message directly to the room");
        final JButton genButton9 = this.genButton("Gag", "gag", "Ignore the user who submitted the selected question");
        final JButton genButton10 = this.genButton("Delete", "del", "Delete a question");
        final JButton genButton11 = this.genButton("Forward To All", "fwdall", "Forward to all speakers");
        if (this.paraConf.getBool("Mod.Cf.Toolbar", false)) {
            final JToolBar toolBar = new JToolBar("Moderation Tools");
            toolBar.add(genButton);
            toolBar.add(genButton2);
            toolBar.add(genButton3);
            toolBar.add(genButton4);
            toolBar.add(genButton5);
            toolBar.add(genButton6);
            toolBar.add(genButton7);
            toolBar.add(genButton8);
            toolBar.add(genButton9);
            toolBar.add(genButton10);
            toolBar.add(genButton11);
            return toolBar;
        }
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        final Dimension dimension = new Dimension(3, 3);
        horizontalPanel.add(genButton);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton2);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton3);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton4);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton5);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton6);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton7);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton8);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton9);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton10);
        horizontalPanel.add(Box.createRigidArea(dimension));
        horizontalPanel.add(genButton11);
        return horizontalPanel;
    }
    
    private JButton genButton(final String s, final String actionCommand, final String toolTipText) {
        final JButton button = new JButton(s);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setToolTipText(toolTipText);
        button.setMargin(new Insets(1, 2, 1, 2));
        return button;
    }
    
    private JComponent getBottom() {
        final JLabel infoLabel = new JLabel(" ");
        infoLabel.setForeground(Color.BLUE);
        infoLabel.setAlignmentX(0.0f);
        return this.infoLabel = infoLabel;
    }
}
