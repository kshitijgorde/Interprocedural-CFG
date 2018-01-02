// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

final class h extends ComponentAdapter
{
    private /* synthetic */ n a;
    
    h(final n a) {
        this.a = a;
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.a.repaint();
    }
}
