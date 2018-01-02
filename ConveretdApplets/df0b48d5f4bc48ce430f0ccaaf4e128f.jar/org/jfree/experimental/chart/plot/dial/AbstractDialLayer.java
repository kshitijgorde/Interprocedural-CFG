// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.plot.dial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Arrays;
import java.util.EventListener;
import javax.swing.event.EventListenerList;

public abstract class AbstractDialLayer implements DialLayer
{
    private boolean visible;
    private transient EventListenerList listenerList;
    static /* synthetic */ Class class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener;
    
    protected AbstractDialLayer() {
        this.visible = true;
        this.listenerList = new EventListenerList();
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
        this.notifyListeners(new DialLayerChangeEvent(this));
    }
    
    public void addChangeListener(final DialLayerChangeListener listener) {
        this.listenerList.add((AbstractDialLayer.class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener == null) ? (AbstractDialLayer.class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener = class$("org.jfree.experimental.chart.plot.dial.DialLayerChangeListener")) : AbstractDialLayer.class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener, listener);
    }
    
    public void removeChangeListener(final DialLayerChangeListener listener) {
        this.listenerList.remove((AbstractDialLayer.class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener == null) ? (AbstractDialLayer.class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener = class$("org.jfree.experimental.chart.plot.dial.DialLayerChangeListener")) : AbstractDialLayer.class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener, listener);
    }
    
    public boolean hasListener(final EventListener listener) {
        final List list = Arrays.asList(this.listenerList.getListenerList());
        return list.contains(listener);
    }
    
    protected void notifyListeners(final DialLayerChangeEvent event) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ((AbstractDialLayer.class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener == null) ? (AbstractDialLayer.class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener = class$("org.jfree.experimental.chart.plot.dial.DialLayerChangeListener")) : AbstractDialLayer.class$org$jfree$experimental$chart$plot$dial$DialLayerChangeListener)) {
                ((DialLayerChangeListener)listeners[i + 1]).dialLayerChanged(event);
            }
        }
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.listenerList = new EventListenerList();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
