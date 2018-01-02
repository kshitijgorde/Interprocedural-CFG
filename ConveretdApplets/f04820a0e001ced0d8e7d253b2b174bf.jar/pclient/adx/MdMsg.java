// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import pclient.shd.Config;
import pclient.adv.TextFieldCopy;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.Box;
import java.awt.Dimension;
import pclient.adv.CompUtil;
import javax.swing.border.Border;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class MdMsg extends JFrame implements ActionListener, MouseListener
{
    private static final String INFO_INTRO = "<html><p>Frequently used messages that you want to use during a moderated event are displayed below.  Hightlight an existing message, and click the \"Send\" button to submit it to the room.  Use the input field at the bottom of the field to add new messages, and then click the \"Save to Server\" button to save your messages. This list should be maintained by one user at a time.</p></html>";
    private static final String INFO_SEND = "<html><p>Highlight a saved message, or input a new message, to Post to the room.</p></html>";
    private static final String ACT_ADD = "ADD";
    private static final String ACT_DEL = "DEL";
    private static final String ACT_SAVE = "SAVE";
    private static final String ACT_LOAD = "LOAD";
    private static final String ACT_SEND = "SEND";
    private JTextField textInput;
    private JList msgList;
    private DefaultListModel msgModel;
    private JLabel infoLabel;
    private ModWindow modWindow;
    private EmptyBorder border5;
    
    public MdMsg(final ModWindow modWindow) {
        this.border5 = new EmptyBorder(5, 5, 5, 5);
        this.modWindow = modWindow;
        this.buildUI();
        this.setTitle("Chat Moderation: Stored Messages");
        this.setSize(520, 560);
    }
    
    public void refresh() {
    }
    
    protected void populateList(final String[] array) {
        if (array == null) {
            return;
        }
        this.msgModel.clear();
        for (int i = 0; i < array.length; ++i) {
            this.msgModel.addElement(array[i]);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.clearInfo();
        if (mouseEvent.getClickCount() != 1) {
            if (mouseEvent.getClickCount() == 2) {}
            return;
        }
        final int selectedIndex = this.msgList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        this.textInput.setText((String)this.msgModel.get(selectedIndex));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.modWindow.paraConf.printer().print("com=" + actionCommand);
        this.clearInfo();
        if ("ADD".equals(actionCommand)) {
            final String text = this.textInput.getText();
            if (StringUtil.isTrimmedEmpty(text)) {
                this.printInfo("Please enter some text you would like to add in the text field.");
                return;
            }
            this.msgModel.addElement(text);
        }
        else if ("DEL".equals(actionCommand)) {
            this.deleteMessage();
        }
        else if ("SAVE".equals(actionCommand)) {
            if (!this.modWindow.modHandler.chatModel.cmIsConnected()) {
                this.printInfo(this.modWindow.paraConf.get("Console.Msg.NotConnected", "Error: Not Connected"));
                return;
            }
            final String[] array = new String[this.msgModel.getSize()];
            this.msgModel.copyInto(array);
            this.modWindow.modHandler.mdSaveMessages(array);
        }
        else if ("LOAD".equals(actionCommand)) {
            if (!this.modWindow.modHandler.chatModel.cmIsConnected()) {
                this.printInfo(this.modWindow.paraConf.get("Console.Msg.NotConnected", "Error: Not Connected"));
                return;
            }
            this.msgModel.clear();
            this.modWindow.modHandler.mdLoadMessages();
        }
        else if ("SEND".equals(actionCommand)) {
            if (!this.modWindow.modHandler.chatModel.cmIsConnected()) {
                this.printInfo(this.modWindow.paraConf.get("Console.Msg.NotConnected", "Error: Not Connected"));
                return;
            }
            this.sendMsg();
        }
    }
    
    private void deleteMessage() {
        final int selectedIndex = this.msgList.getSelectedIndex();
        if (selectedIndex < 0) {
            this.printInfo("Please select a message to delete.");
            return;
        }
        this.msgModel.remove(selectedIndex);
    }
    
    private void sendMsg() {
        final String text = this.textInput.getText();
        if (StringUtil.isTrimmedEmpty(text)) {
            this.printInfo("Please select a message or enter some text in the text field.");
            return;
        }
        this.modWindow.paraConf.printer().print("input=" + text);
        this.modWindow.modHandler.mdPost(text);
        this.textInput.setText("");
    }
    
    private void printInfo(final String s) {
        this.infoLabel.setText(" " + s);
    }
    
    private void clearInfo() {
        this.infoLabel.setText(" ");
    }
    
    private void buildUI() {
        this.getContentPane().setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(this.createMsg());
        (this.infoLabel = new JLabel(" ")).setForeground(Color.BLUE);
        this.getContentPane().add(panel, "Center");
        this.getContentPane().add(this.infoLabel, "South");
    }
    
    private JComponent createMsg() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getList());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        verticalPanel.add(this.getBottom());
        return panel;
    }
    
    private JComponent getList() {
        final JLabel label = new JLabel("<html><p>Frequently used messages that you want to use during a moderated event are displayed below.  Hightlight an existing message, and click the \"Send\" button to submit it to the room.  Use the input field at the bottom of the field to add new messages, and then click the \"Save to Server\" button to save your messages. This list should be maintained by one user at a time.</p></html>");
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        verticalPanel.add(this.getMsgs());
        return verticalPanel;
    }
    
    private JComponent getMsgs() {
        final DefaultListModel<Object> msgModel = new DefaultListModel<Object>();
        final JList msgList = new JList(msgModel);
        msgList.setVisibleRowCount(20);
        msgList.setSelectionMode(0);
        msgList.addMouseListener(this);
        final JScrollPane scrollPane = new JScrollPane(msgList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(120, 64));
        scrollPane.setPreferredSize(new Dimension(160, 120));
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(scrollPane);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(this.getButtons());
        this.msgList = msgList;
        this.msgModel = msgModel;
        return verticalPanel;
    }
    
    private JComponent getButtons() {
        final JButton button = this.createButton("Add", "ADD");
        final JButton button2 = this.createButton("Delete", "DEL");
        final JButton button3 = this.createButton("Save to Server", "SAVE");
        final JButton button4 = this.createButton("Load from Server", "LOAD");
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(button);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button2);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button3);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button4);
        return horizontalPanel;
    }
    
    private JButton createButton(final String s, final String actionCommand) {
        final JButton button = new JButton(s);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setRolloverEnabled(true);
        return button;
    }
    
    private JComponent getBottom() {
        final JLabel label = new JLabel("<html><p>Highlight a saved message, or input a new message, to Post to the room.</p></html>");
        final TextFieldCopy textInput = new TextFieldCopy((Config)null);
        textInput.setColumns(32);
        textInput.setMaximumSize(new Dimension(1200, 24));
        final JButton button = new JButton("Send");
        button.setActionCommand("SEND");
        button.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(textInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 1)));
        horizontalPanel.add(button);
        horizontalPanel.add(Box.createRigidArea(new Dimension(6, 2)));
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        this.textInput = textInput;
        return verticalPanel;
    }
}
