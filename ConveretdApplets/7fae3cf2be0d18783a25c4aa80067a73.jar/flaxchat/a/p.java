// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.awt.event.ActionListener;

final class p implements ActionListener
{
    private final Dialog a;
    private final k b;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            k.a(this.b, Integer.parseInt(actionEvent.getActionCommand()));
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        this.a.dispose();
    }
    
    p(final Dialog a, final k b) {
        this.a = a;
        this.b = b;
    }
}
