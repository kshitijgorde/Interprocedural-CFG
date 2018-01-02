// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class G implements ActionListener
{
    private /* synthetic */ j a;
    
    G(final j a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.setVisible(false);
    }
}
