// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

import java.awt.event.ActionEvent;
import java.awt.Component;
import prefuse.util.ui.JForcePanel;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.AbstractAction;

public class ForceConfigAction extends AbstractAction
{
    private JDialog dialog;
    
    public ForceConfigAction(final JFrame frame, final ForceSimulator forceSimulator) {
        (this.dialog = new JDialog(frame, false)).setTitle("Configure Force Simulator");
        this.dialog.getContentPane().add(new JForcePanel(forceSimulator));
        this.dialog.pack();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dialog.setVisible(true);
    }
}
