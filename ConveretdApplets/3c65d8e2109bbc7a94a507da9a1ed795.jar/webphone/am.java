// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class am implements ActionListener
{
    private webphone a;
    
    am(final webphone a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.button1_actionPerformed(actionEvent);
    }
}
