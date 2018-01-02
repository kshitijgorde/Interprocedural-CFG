// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyEvent;
import pclient.adv.DualTextArea;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import java.beans.PropertyChangeListener;
import java.awt.event.KeyListener;

public class MdPost implements KeyListener, PropertyChangeListener
{
    private JDialog dialogWin;
    private ModWindow parentFrame;
    private JOptionPane optionPane;
    private DualTextArea textInput;
    private String enterString;
    private String cancelString;
    private boolean enterClicked;
    private boolean enterSubmit;
    
    public MdPost(final ModWindow parentFrame) {
        this.dialogWin = null;
        this.enterString = "OK";
        this.cancelString = "Cancel";
        this.enterClicked = false;
        this.enterSubmit = false;
        this.parentFrame = parentFrame;
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
                this.parentFrame.responsePost();
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.parentFrame.paraConf.printer().print("mod post, change=" + propertyChangeEvent.getPropertyName());
        if (propertyChangeEvent.getSource() != this.optionPane) {
            return;
        }
        final Object value = this.optionPane.getValue();
        this.parentFrame.paraConf.printer().print("mod post, value=" + value);
        if (value == JOptionPane.UNINITIALIZED_VALUE) {
            return;
        }
        this.optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
        if (this.enterString.equals(value)) {
            this.parentFrame.responsePost();
            this.dialogWin.setVisible(false);
        }
        else {
            this.dialogWin.setVisible(false);
        }
    }
    
    private void buildUI() {
        (this.textInput = new DualTextArea(this.parentFrame.paraConf, false)).setRows(4);
        this.textInput.setColumns(42);
        this.textInput.setEditable(true);
        this.textInput.setLineWrap(true);
        this.textInput.setWrapStyleWord(true);
        this.textInput.addKeyListener(this);
        final Object[] array = { "Post a message to this chat room", new JScrollPane(this.textInput.getBox(), 20, 31) };
        final Object[] array2 = { this.enterString, this.cancelString };
        this.optionPane = new JOptionPane(array, -1, 2, null, array2, array2[0]);
        (this.dialogWin = this.optionPane.createDialog(this.parentFrame, "Moderation Console: Post")).setResizable(true);
        this.dialogWin.setLocationRelativeTo(this.parentFrame);
        this.dialogWin.setModal(false);
        this.dialogWin.addComponentListener(new ComponentAdapter() {
            public void componentShown(final ComponentEvent componentEvent) {
                MdPost.this.textInput.requestFocusInWindow();
            }
        });
        this.optionPane.addPropertyChangeListener(this);
    }
}
