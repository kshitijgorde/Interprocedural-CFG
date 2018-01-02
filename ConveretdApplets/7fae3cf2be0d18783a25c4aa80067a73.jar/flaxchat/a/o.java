// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.awt.event.ActionListener;

final class o implements ActionListener
{
    private final Dialog a;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.dispose();
    }
    
    o(final Dialog a) {
        this.a = a;
    }
}
