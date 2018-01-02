// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Component;
import javax.swing.Icon;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import pclient.adv.DualTextArea;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyListener;

public class MdEdit implements KeyListener, PropertyChangeListener
{
    private JDialog dialogWin;
    private ModWindow parentFrame;
    private JOptionPane optionPane;
    private DualTextArea textInput;
    private JLabel itemLabel;
    private String enterString;
    private String cancelString;
    private boolean enterClicked;
    private boolean enterSubmit;
    protected String questionID;
    
    public MdEdit(final ModWindow parentFrame) {
        this.dialogWin = null;
        this.enterString = "OK";
        this.cancelString = "Cancel";
        this.enterClicked = false;
        this.enterSubmit = false;
        this.questionID = null;
        this.parentFrame = parentFrame;
        this.buildUI();
    }
    
    public void showWin() {
        this.enterClicked = false;
        if (this.dialogWin == null) {
            this.buildUI();
        }
        this.dialogWin.setVisible(true);
    }
    
    public String getInput() {
        if (this.dialogWin == null) {
            return null;
        }
        return this.textInput.getText();
    }
    
    public void setInput(final String questionID, final String s, final String text) {
        if (this.dialogWin != null) {
            this.questionID = questionID;
            this.itemLabel.setText("Question ID: " + questionID + "  From: " + s);
            this.textInput.setText(text);
        }
    }
    
    public void clearInput() {
        if (this.dialogWin != null) {
            this.textInput.setText("");
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
                this.parentFrame.responseEdit();
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
            this.parentFrame.responseEdit();
            this.dialogWin.setVisible(false);
        }
        else {
            this.dialogWin.setVisible(false);
        }
    }
    
    private void buildUI() {
        this.itemLabel = new JLabel(" ");
        final Object[] array = { this.itemLabel, this.getCenter() };
        final Object[] array2 = { this.enterString, this.cancelString };
        this.optionPane = new JOptionPane(array, -1, 2, null, array2, array2[0]);
        (this.dialogWin = this.optionPane.createDialog(this.parentFrame, "Moderation Console: Edit")).setLocationRelativeTo(this.parentFrame);
        this.dialogWin.setResizable(true);
        this.dialogWin.setModal(false);
        this.dialogWin.addComponentListener(new ComponentAdapter() {
            public void componentShown(final ComponentEvent componentEvent) {
                MdEdit.this.textInput.requestFocusInWindow();
            }
        });
        this.optionPane.addPropertyChangeListener(this);
    }
    
    private JComponent getCenter() {
        (this.textInput = new DualTextArea(this.parentFrame.paraConf, false)).setRows(4);
        this.textInput.setColumns(42);
        this.textInput.setEditable(true);
        this.textInput.setLineWrap(true);
        this.textInput.setWrapStyleWord(true);
        this.textInput.addKeyListener(this);
        return new JScrollPane(this.textInput.getBox(), 20, 31);
    }
}
