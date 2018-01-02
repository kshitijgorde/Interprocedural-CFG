// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.awt.event.ActionListener;

final class o implements ActionListener
{
    private final Dialog a;
    private final j b;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            j.a(this.b, Integer.parseInt(actionEvent.getActionCommand()));
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        this.a.dispose();
    }
    
    o(final Dialog a, final j b) {
        this.a = a;
        this.b = b;
    }
}
