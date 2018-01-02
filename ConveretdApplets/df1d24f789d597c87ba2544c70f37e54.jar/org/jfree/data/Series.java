// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.beans.PropertyChangeListener;
import org.jfree.util.ObjectUtils;
import java.beans.PropertyChangeSupport;
import javax.swing.event.EventListenerList;
import java.io.Serializable;

public class Series implements Cloneable, Serializable
{
    private String name;
    private String description;
    private EventListenerList listeners;
    private PropertyChangeSupport propertyChangeSupport;
    private boolean notify;
    static /* synthetic */ Class class$org$jfree$data$SeriesChangeListener;
    
    protected Series(final String name) {
        this(name, null);
    }
    
    protected Series(final String name, final String description) {
        if (name == null) {
            throw new IllegalArgumentException("Null 'name' argument.");
        }
        this.name = name;
        this.description = description;
        this.listeners = new EventListenerList();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.notify = true;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Null 'name' argument.");
        }
        final String old = this.name;
        this.name = name;
        this.propertyChangeSupport.firePropertyChange("Name", old, name);
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        final String old = this.description;
        this.description = description;
        this.propertyChangeSupport.firePropertyChange("Description", old, description);
    }
    
    public boolean getNotify() {
        return this.notify;
    }
    
    public void setNotify(final boolean notify) {
        if (this.notify != notify) {
            this.notify = notify;
            this.fireSeriesChanged();
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        try {
            final Series clone = (Series)super.clone();
            clone.listeners = new EventListenerList();
            clone.propertyChangeSupport = new PropertyChangeSupport(clone);
            return clone;
        }
        catch (CloneNotSupportedException e) {
            throw new CloneNotSupportedException("Series.clone(): unexpected exception.");
        }
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Series)) {
            return false;
        }
        final Series s = (Series)object;
        return this.getName().equals(s.getName()) && ObjectUtils.equal(this.getDescription(), s.getDescription());
    }
    
    public int hashCode() {
        int result = this.name.hashCode();
        result = 29 * result + ((this.description != null) ? this.description.hashCode() : 0);
        return result;
    }
    
    public void addChangeListener(final SeriesChangeListener listener) {
        this.listeners.add((Series.class$org$jfree$data$SeriesChangeListener == null) ? (Series.class$org$jfree$data$SeriesChangeListener = class$("org.jfree.data.SeriesChangeListener")) : Series.class$org$jfree$data$SeriesChangeListener, listener);
    }
    
    public void removeChangeListener(final SeriesChangeListener listener) {
        this.listeners.remove((Series.class$org$jfree$data$SeriesChangeListener == null) ? (Series.class$org$jfree$data$SeriesChangeListener = class$("org.jfree.data.SeriesChangeListener")) : Series.class$org$jfree$data$SeriesChangeListener, listener);
    }
    
    public void fireSeriesChanged() {
        if (this.notify) {
            this.notifyListeners(new SeriesChangeEvent(this));
        }
    }
    
    protected void notifyListeners(final SeriesChangeEvent event) {
        final Object[] listenerList = this.listeners.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((Series.class$org$jfree$data$SeriesChangeListener == null) ? (Series.class$org$jfree$data$SeriesChangeListener = class$("org.jfree.data.SeriesChangeListener")) : Series.class$org$jfree$data$SeriesChangeListener)) {
                ((SeriesChangeListener)listenerList[i + 1]).seriesChanged(event);
            }
        }
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    protected void firePropertyChange(final String property, final Object oldValue, final Object newValue) {
        this.propertyChangeSupport.firePropertyChange(property, oldValue, newValue);
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
