// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class BlankPanel extends JPanel
{
    BorderLayout borderLayout1;
    
    public BlankPanel() {
        this.borderLayout1 = new BorderLayout();
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setLayout(this.borderLayout1);
        this.setOpaque(false);
    }
}
