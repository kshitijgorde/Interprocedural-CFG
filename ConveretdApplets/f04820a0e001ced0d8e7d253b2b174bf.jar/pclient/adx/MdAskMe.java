// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JComponent;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.Frame;
import javax.swing.JDialog;

public class MdAskMe
{
    private boolean actionCanceled;
    private JDialog dialogWin;
    private Frame parentFrame;
    private JCheckBox donotCheck;
    private JOptionPane optionPane;
    private String yesString;
    private String noString;
    
    public MdAskMe(final Frame parentFrame) {
        this.dialogWin = null;
        this.yesString = "Yes";
        this.noString = "No";
        this.parentFrame = parentFrame;
        this.buildUI();
    }
    
    public boolean noNeedToAsk() {
        return this.donotCheck.isSelected();
    }
    
    public boolean isCanceled() {
        return this.actionCanceled;
    }
    
    public void showWin() {
        if (this.dialogWin == null) {
            this.buildUI();
        }
        this.dialogWin.setVisible(true);
        final Object value = this.optionPane.getValue();
        this.actionCanceled = true;
        if (value == null) {
            return;
        }
        if (this.yesString.equals(value)) {
            this.actionCanceled = false;
        }
    }
    
    private void buildUI() {
        final Object[] array = { "Are you sure to delete it?", this.getCenter() };
        final Object[] array2 = { this.yesString, this.noString };
        this.optionPane = new JOptionPane(array, 2, 0, null, array2, array2[0]);
        (this.dialogWin = this.optionPane.createDialog(this.parentFrame, "Confirmation")).setLocationRelativeTo(this.parentFrame);
        this.dialogWin.setResizable(false);
        this.dialogWin.pack();
    }
    
    private JComponent getCenter() {
        return this.donotCheck = new JCheckBox("Do not ask me again", false);
    }
}
