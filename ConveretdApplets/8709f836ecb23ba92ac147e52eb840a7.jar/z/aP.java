// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

final class aP implements ComponentListener
{
    private /* synthetic */ aT a;
    
    aP(final aT at, final aT a) {
        this.a = a;
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
        try {
            Thread.sleep(250L);
        }
        catch (InterruptedException ex) {}
        this.a.getContentPane().repaint();
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
    }
}
