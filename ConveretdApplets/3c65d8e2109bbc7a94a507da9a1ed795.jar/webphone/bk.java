// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bk implements ActionListener
{
    private webphone a;
    
    bk(final webphone a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.a != null && this.a.common.bC) {
            this.a.common.bC = false;
            this.a.checkdisplayname();
        }
    }
}
