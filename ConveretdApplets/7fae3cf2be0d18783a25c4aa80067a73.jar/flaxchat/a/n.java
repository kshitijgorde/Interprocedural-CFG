// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.awt.event.ActionListener;

final class n implements ActionListener
{
    private final Dialog a;
    private final k b;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        k.a(this.b, 1);
        this.a.dispose();
    }
    
    n(final Dialog a, final k b) {
        this.a = a;
        this.b = b;
    }
}
