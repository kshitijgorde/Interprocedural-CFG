// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ad implements ActionListener
{
    private webphone a;
    
    ad(final webphone a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.jButton22_actionPerformed(actionEvent);
    }
}
