// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class b9 implements ActionListener
{
    private webphone a;
    
    b9(final webphone a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.button4_actionPerformed(actionEvent);
    }
}
