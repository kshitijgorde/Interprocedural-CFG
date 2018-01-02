// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

final class i extends ComponentAdapter
{
    private /* synthetic */ WordleApplet a;
    
    i(final WordleApplet a) {
        this.a = a;
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.a.validate();
    }
}
