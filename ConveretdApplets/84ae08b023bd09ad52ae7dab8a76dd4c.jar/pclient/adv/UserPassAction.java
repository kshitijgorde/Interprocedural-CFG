// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;

public class UserPassAction implements ActionListener
{
    private AppletSpice appletChat;
    private JDialog passDialog;
    private JButton enterWithout;
    public boolean enterWithoutClicked;
    
    public UserPassAction(final AppletSpice appletChat, final JDialog passDialog, final JButton enterWithout) {
        this.enterWithoutClicked = false;
        this.appletChat = appletChat;
        this.passDialog = passDialog;
        this.enterWithout = enterWithout;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.enterWithout) {
            this.enterWithoutClicked = true;
        }
        this.passDialog.setVisible(false);
    }
}
