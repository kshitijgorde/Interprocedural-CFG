// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class b2 implements ActionListener
{
    private bu a;
    
    b2(final bu a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.a(actionEvent);
    }
}
