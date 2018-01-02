// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.awt.event.ActionListener;

final class k implements ActionListener
{
    private final Dialog a;
    private final StringBuffer b;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.b.append(actionEvent.getActionCommand());
        this.a.dispose();
    }
    
    k(final Dialog a, final StringBuffer b) {
        this.a = a;
        this.b = b;
    }
}
