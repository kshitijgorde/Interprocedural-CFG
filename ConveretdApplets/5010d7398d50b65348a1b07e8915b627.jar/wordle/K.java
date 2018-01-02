// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.event.ComponentEvent;
import java.awt.Container;
import java.awt.event.ComponentAdapter;

final class K extends ComponentAdapter
{
    private final /* synthetic */ Container a;
    
    K(final WordleApplet wordleApplet, final Container a) {
        this.a = a;
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.a.validate();
    }
}
