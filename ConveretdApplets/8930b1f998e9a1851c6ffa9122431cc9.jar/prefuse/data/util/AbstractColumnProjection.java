// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.event.ProjectionListener;
import prefuse.util.collections.CopyOnWriteArrayList;

public abstract class AbstractColumnProjection implements ColumnProjection
{
    private CopyOnWriteArrayList m_listeners;
    
    public void addProjectionListener(final ProjectionListener projectionListener) {
        if (this.m_listeners == null) {
            this.m_listeners = new CopyOnWriteArrayList();
        }
        if (!this.m_listeners.contains(projectionListener)) {
            this.m_listeners.add(projectionListener);
        }
    }
    
    public void removeProjectionListener(final ProjectionListener projectionListener) {
        if (this.m_listeners != null) {
            this.m_listeners.remove(projectionListener);
        }
        if (this.m_listeners.size() == 0) {
            this.m_listeners = null;
        }
    }
    
    public void fireUpdate() {
        if (this.m_listeners == null) {
            return;
        }
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ProjectionListener)array[i]).projectionChanged(this);
        }
    }
}
