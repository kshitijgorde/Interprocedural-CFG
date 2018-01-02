// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bp implements ActionListener
{
    private webphone a;
    
    bp(final webphone a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.jButton23_actionPerformed(actionEvent);
    }
}
