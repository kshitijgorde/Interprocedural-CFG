// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.awt.event.ActionListener;

final class n implements ActionListener
{
    private final Dialog a;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.dispose();
    }
    
    n(final Dialog a) {
        this.a = a;
    }
}
