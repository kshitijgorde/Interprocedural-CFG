// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import java.awt.event.ActionListener;

public final class HidingDialogListener implements ActionListener
{
    private JDialog vDialog;
    
    public HidingDialogListener(final JDialog vDialog) {
        this.vDialog = vDialog;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.vDialog.setVisible(true);
    }
}
