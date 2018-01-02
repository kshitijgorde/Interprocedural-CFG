import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.util.EventListener;
import java.beans.PropertyChangeListener;
import java.awt.AWTEventMulticaster;

// 
// Decompiled by Procyon v0.5.30
// 

public class EventMulticaster extends AWTEventMulticaster implements PropertyChangeListener, PaintListener
{
    protected EventMulticaster(final EventListener eventListener, final EventListener eventListener2) {
        super(eventListener, eventListener2);
    }
    
    protected static final EventListener addInternal(final EventListener eventListener, final EventListener eventListener2) {
        if (eventListener == null) {
            return eventListener2;
        }
        if (eventListener2 == null) {
            return eventListener;
        }
        return new EventMulticaster(eventListener, eventListener2);
    }
    
    protected final EventListener remove(final EventListener eventListener) {
        if (eventListener == super.a) {
            return super.b;
        }
        if (eventListener == super.b) {
            return super.a;
        }
        final EventListener removeInternal = AWTEventMulticaster.removeInternal(super.a, eventListener);
        final EventListener removeInternal2 = AWTEventMulticaster.removeInternal(super.b, eventListener);
        if (removeInternal == super.a && removeInternal2 == super.b) {
            return this;
        }
        return addInternal(removeInternal, removeInternal2);
    }
    
    public static final PaintListener add(final PaintListener paintListener, final PaintListener paintListener2) {
        return (PaintListener)addInternal(paintListener, paintListener2);
    }
    
    public final void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        ((PropertyChangeListener)super.a).propertyChange(propertyChangeEvent);
        ((PropertyChangeListener)super.b).propertyChange(propertyChangeEvent);
    }
    
    public final void onPaint(final Object o, final Graphics graphics, final boolean b) {
        ((PaintListener)super.a).onPaint(o, graphics, b);
        ((PaintListener)super.b).onPaint(o, graphics, b);
    }
}
