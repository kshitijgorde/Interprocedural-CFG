// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bd implements ActionListener
{
    private webphone a;
    
    bd(final webphone a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.jButton11_actionPerformed(actionEvent);
    }
}
