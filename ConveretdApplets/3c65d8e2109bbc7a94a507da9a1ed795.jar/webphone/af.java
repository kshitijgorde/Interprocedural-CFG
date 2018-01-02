// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class af implements ActionListener
{
    private bl a;
    
    af(final bl a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.if(actionEvent);
    }
}
