// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Box;
import java.awt.Dimension;
import pclient.adv.CompUtil;
import javax.swing.JComponent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Component;
import javax.swing.Icon;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import pclient.adv.DualTextArea;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyListener;

public class MdReply implements KeyListener, PropertyChangeListener
{
    private static final String READ_ONLY = "<html><p>This is a read-only question. Click Edit button in the main window to edit. An answered question will be deleted unless you have selected not to.</p><BR></html>";
    private JDialog dialogWin;
    private ModWindow parentFrame;
    private JOptionPane optionPane;
    private DualTextArea answerInput;
    private JTextArea questionText;
    private JLabel itemLabel;
    private boolean isPostOnly;
    private boolean enterClicked;
    private String enterString;
    private String postString;
    private String cancelString;
    private boolean enterSubmit;
    protected String questionID;
    
    public MdReply(final ModWindow parentFrame) {
        this.dialogWin = null;
        this.isPostOnly = false;
        this.enterClicked = false;
        this.enterString = "Submit Text to Room";
        this.postString = "Just Post the Question";
        this.cancelString = "Cancel";
        this.enterSubmit = false;
        this.questionID = null;
        this.parentFrame = parentFrame;
    }
    
    public void showWin() {
        this.enterClicked = false;
        if (this.dialogWin == null) {
            this.buildUI();
        }
        this.dialogWin.setVisible(true);
    }
    
    public boolean isPostQuestion() {
        return this.isPostOnly;
    }
    
    public String getInput() {
        if (this.dialogWin == null) {
            return null;
        }
        if (this.isPostOnly) {
            return null;
        }
        return this.answerInput.getText();
    }
    
    public void setQuestion(final String questionID, final String s, final String text) {
        this.questionID = questionID;
        if (this.dialogWin == null) {
            this.buildUI();
        }
        this.questionText.setText(text);
        this.itemLabel.setText("Question ID: " + questionID + "  From: " + s);
        this.clearInput();
    }
    
    public void clearInput() {
        if (this.dialogWin != null) {
            this.answerInput.setText("");
        }
    }
    
    public void setEnter(final boolean enterSubmit) {
        this.enterSubmit = enterSubmit;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            if (this.enterSubmit) {
                this.enterClicked = true;
                keyEvent.consume();
                this.dialogWin.setVisible(false);
                this.isPostOnly = false;
                this.parentFrame.responseAnswer();
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.parentFrame.paraConf.printer().print("mod edit, change=" + propertyChangeEvent.getPropertyName());
        if (propertyChangeEvent.getSource() != this.optionPane) {
            return;
        }
        final Object value = this.optionPane.getValue();
        this.parentFrame.paraConf.printer().print("mod edit, value=" + value);
        if (value == JOptionPane.UNINITIALIZED_VALUE) {
            return;
        }
        this.optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
        if (this.enterString.equals(value)) {
            this.isPostOnly = false;
            this.parentFrame.responseAnswer();
            this.dialogWin.setVisible(false);
        }
        else if (this.postString.equals(value)) {
            this.isPostOnly = true;
            this.parentFrame.responseAnswer();
            this.dialogWin.setVisible(false);
        }
        else {
            this.dialogWin.setVisible(false);
        }
    }
    
    private void buildUI() {
        final Object[] array = { "<html><p>This is a read-only question. Click Edit button in the main window to edit. An answered question will be deleted unless you have selected not to.</p><BR></html>", this.getCenter() };
        final Object[] array2 = { this.enterString, this.postString, this.cancelString };
        this.optionPane = new JOptionPane(array, -1, 1, null, array2, array2[0]);
        (this.dialogWin = this.optionPane.createDialog(this.parentFrame, "Moderation Console: Answer")).setResizable(true);
        this.dialogWin.setLocationRelativeTo(this.parentFrame);
        this.dialogWin.setSize(540, 360);
        this.dialogWin.setModal(false);
        this.dialogWin.addComponentListener(new ComponentAdapter() {
            public void componentShown(final ComponentEvent componentEvent) {
                MdReply.this.answerInput.requestFocusInWindow();
            }
        });
        this.optionPane.addPropertyChangeListener(this);
    }
    
    private JComponent getCenter() {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.setAlignmentX(0.0f);
        verticalPanel.add(this.getAnswerLabel());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 2)));
        verticalPanel.add(this.getAnswer());
        final JSplitPane splitPane = new JSplitPane(0, this.getQuestion(), verticalPanel);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(0.2);
        final JPanel verticalPanel2 = CompUtil.createVerticalPanel(false);
        verticalPanel2.setAlignmentY(0.0f);
        verticalPanel2.setAlignmentX(0.0f);
        verticalPanel2.add(this.getItemLabel());
        verticalPanel2.add(Box.createRigidArea(new Dimension(1, 2)));
        verticalPanel2.add(splitPane);
        return verticalPanel2;
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
    
    private JComponent getAnswerLabel() {
        return new JLabel("Type your answer here: ");
    }
    
    private JComponent getAnswer() {
        final DualTextArea answerInput = new DualTextArea(this.parentFrame.paraConf, false);
        answerInput.setRows(4);
        answerInput.setColumns(36);
        answerInput.setEditable(true);
        answerInput.setLineWrap(true);
        answerInput.setWrapStyleWord(true);
        answerInput.addKeyListener(this);
        final JScrollPane scrollPane = new JScrollPane(answerInput.getBox(), 20, 31);
        this.answerInput = answerInput;
        return scrollPane;
    }
}
