// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.Border;
import pclient.adv.CompUtil;
import javax.swing.JComponent;
import java.awt.Component;
import javax.swing.Icon;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.awt.event.ActionListener;

public class MdDirectForwd implements ActionListener
{
    private static final String ACT_MOD = "MOD";
    private static final String ACT_SPK = "SPK";
    private ModWindow parentFrame;
    private JDialog dialogWin;
    private JOptionPane optionPane;
    private JComboBox speakerChoice;
    private JComboBox moderatorChoice;
    private JButton speakerButton;
    private JButton moderatorButton;
    private JTextArea questionText;
    private JLabel itemLabel;
    private String okString;
    private EmptyBorder border5;
    private String lastID;
    
    public MdDirectForwd(final ModWindow parentFrame) {
        this.dialogWin = null;
        this.okString = "Cancel";
        this.border5 = new EmptyBorder(5, 5, 5, 5);
        this.lastID = null;
        this.parentFrame = parentFrame;
        this.buildUI();
    }
    
    public void showWin() {
        if (this.dialogWin == null) {
            this.buildUI();
        }
        this.dialogWin.setVisible(true);
    }
    
    public void setQuestion(final String lastID, final String s, final String text, String[] array, String[] array2) {
        this.lastID = lastID;
        if (this.dialogWin == null) {
            this.buildUI();
        }
        if (array == null) {
            array = new String[0];
        }
        if (array2 == null) {
            array2 = new String[0];
        }
        this.questionText.setText(text);
        this.itemLabel.setText("Question ID: " + lastID + "  From: " + s);
        this.setState(array, array2);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.parentFrame.paraConf.printer().print("dirct com=" + actionCommand);
        if ("SPK".equals(actionCommand)) {
            final Object selectedItem = this.speakerChoice.getSelectedItem();
            this.dialogWin.setVisible(false);
            if (selectedItem != null) {
                this.parentFrame.forwardSpeaker(this.lastID, (String)selectedItem);
                this.parentFrame.deleteSelected();
            }
        }
        else if ("MOD".equals(actionCommand)) {
            final Object selectedItem2 = this.moderatorChoice.getSelectedItem();
            this.dialogWin.setVisible(false);
            if (selectedItem2 != null) {
                this.parentFrame.forwardModerator(this.lastID, (String)selectedItem2);
                this.parentFrame.deleteSelected();
            }
        }
    }
    
    private void setState(final String[] array, final String[] array2) {
        this.speakerChoice.removeAllItems();
        this.moderatorChoice.removeAllItems();
        if (array2.length == 0) {
            this.speakerButton.setEnabled(false);
        }
        else {
            this.speakerButton.setEnabled(true);
            this.setChoice(this.speakerChoice, array2);
        }
        if (array.length == 0) {
            this.moderatorButton.setEnabled(false);
        }
        else {
            this.moderatorButton.setEnabled(true);
            this.setChoice(this.moderatorChoice, array);
        }
    }
    
    private void setChoice(final JComboBox comboBox, final String[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            comboBox.addItem(array[i]);
        }
    }
    
    private void buildUI() {
        final JLabel itemLabel = new JLabel(" ");
        this.itemLabel = itemLabel;
        final Object[] array = { itemLabel, this.getCenter() };
        final Object[] array2 = { this.okString };
        this.optionPane = new JOptionPane(array, -1, 2, null, array2, array2[0]);
        (this.dialogWin = this.optionPane.createDialog(this.parentFrame, "Moderation Console: Direct Forward")).setResizable(true);
        this.dialogWin.setLocationRelativeTo(this.parentFrame);
    }
    
    private JComponent getCenter() {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(this.border5);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.setAlignmentX(0.0f);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        verticalPanel.add(this.getSpeaker());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 32)));
        verticalPanel.add(this.getModerator());
        final JSplitPane splitPane = new JSplitPane(0, this.getQuestion(), verticalPanel);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(0.2);
        final JPanel verticalPanel2 = CompUtil.createVerticalPanel(false);
        verticalPanel2.setAlignmentY(0.0f);
        verticalPanel2.setAlignmentX(0.0f);
        verticalPanel2.add(Box.createRigidArea(new Dimension(1, 2)));
        verticalPanel2.add(splitPane);
        return verticalPanel2;
    }
    
    private JComponent getSpeaker() {
        final JComboBox speakerChoice = new JComboBox();
        final JButton speakerButton = new JButton("To a Speaker");
        speakerButton.setActionCommand("SPK");
        speakerButton.setToolTipText("Forward the question to a speaker.");
        final JLabel label = new JLabel("select a name and forward the question to the speaker.");
        this.speakerButton = speakerButton;
        this.speakerChoice = speakerChoice;
        return this.getCommon(label, speakerChoice, speakerButton);
    }
    
    private JComponent getModerator() {
        final JComboBox moderatorChoice = new JComboBox();
        final JButton moderatorButton = new JButton("To a Moderator");
        moderatorButton.setActionCommand("MOD");
        moderatorButton.setToolTipText("Forward the question to a Moderator.");
        final JLabel label = new JLabel("select a name and forward the question to the moderator.");
        this.moderatorButton = moderatorButton;
        this.moderatorChoice = moderatorChoice;
        return this.getCommon(label, moderatorChoice, moderatorButton);
    }
    
    private JComponent getCommon(final JLabel label, final JComboBox comboBox, final JButton button) {
        comboBox.setEditable(false);
        comboBox.setPreferredSize(new Dimension(60, 20));
        comboBox.setMaximumSize(new Dimension(240, 32));
        button.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentY(0.0f);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(Box.createRigidArea(new Dimension(20, 2)));
        horizontalPanel.add(comboBox);
        horizontalPanel.add(Box.createRigidArea(new Dimension(6, 2)));
        horizontalPanel.add(button);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.setAlignmentX(0.5f);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        return verticalPanel;
    }
    
    private JComponent getQuestion() {
        final JTextArea questionText = new JTextArea();
        questionText.setRows(3);
        questionText.setColumns(36);
        questionText.setEditable(false);
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        final JScrollPane scrollPane = new JScrollPane(questionText, 20, 31);
        this.questionText = questionText;
        return scrollPane;
    }
    
    private JComponent getItemLabel() {
        return this.itemLabel = new JLabel(" ");
    }
}
