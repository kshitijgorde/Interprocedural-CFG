// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

final class g extends ComponentAdapter
{
    private /* synthetic */ WordleApplet a;
    
    g(final WordleApplet a) {
        this.a = a;
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
        this.a.requestFocusInWindow();
    }
}
