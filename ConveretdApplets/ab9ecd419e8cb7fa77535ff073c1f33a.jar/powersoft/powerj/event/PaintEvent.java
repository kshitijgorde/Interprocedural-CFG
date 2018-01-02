// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.event;

import java.awt.Graphics;

public class PaintEvent extends EventData
{
    protected Graphics _graphics;
    protected static PaintEvent _cachedEvent;
    
    public PaintEvent(final Object source, final Graphics g) {
        super(source, null);
        this._graphics = g;
    }
    
    public Graphics getGraphics() {
        return this._graphics;
    }
    
    public void reinitialize(final Object source, final Graphics g) {
        this.setSource(source);
        this._graphics = g;
    }
    
    public static synchronized PaintEvent allocate(final Object source, final Graphics g) {
        PaintEvent p;
        if (PaintEvent._cachedEvent != null) {
            p = PaintEvent._cachedEvent;
            PaintEvent._cachedEvent = null;
            p.reinitialize(source, g);
        }
        else {
            p = new PaintEvent(source, g);
        }
        return p;
    }
    
    public static synchronized void deallocate(final PaintEvent p) {
        p.reinitialize(null, null);
        if (PaintEvent._cachedEvent == null) {
            PaintEvent._cachedEvent = p;
        }
    }
}
