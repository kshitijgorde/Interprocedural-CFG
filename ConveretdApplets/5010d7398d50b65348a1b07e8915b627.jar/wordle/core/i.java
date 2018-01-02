// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class i extends MouseAdapter
{
    private /* synthetic */ n a;
    
    i(final n a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            this.a.a(!this.a.b());
        }
    }
}
