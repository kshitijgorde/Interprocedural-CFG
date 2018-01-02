// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.awt.event.ActionListener;

final class m implements ActionListener
{
    private final Dialog a;
    private final j b;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        j.a(this.b, 1);
        this.a.dispose();
    }
    
    m(final Dialog a, final j b) {
        this.a = a;
        this.b = b;
    }
}
