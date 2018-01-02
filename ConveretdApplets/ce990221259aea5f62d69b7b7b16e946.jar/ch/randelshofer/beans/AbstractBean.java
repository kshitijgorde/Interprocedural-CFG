// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class AbstractBean implements Serializable
{
    protected PropertyChangeSupport propertySupport;
    
    public AbstractBean() {
        this.propertySupport = new PropertyChangeSupport(this);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.propertySupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.propertySupport.removePropertyChangeListener(propertyChangeListener);
    }
    
    protected void firePropertyChange(final String s, final boolean b, final boolean b2) {
        this.propertySupport.firePropertyChange(s, b ? Boolean.TRUE : Boolean.FALSE, b2 ? Boolean.TRUE : Boolean.FALSE);
    }
    
    protected void firePropertyChange(final String s, final int n, final int n2) {
        this.propertySupport.firePropertyChange(s, new Integer(n), new Integer(n2));
    }
    
    protected void firePropertyChange(final String s, final Object o, final Object o2) {
        this.propertySupport.firePropertyChange(s, o, o2);
    }
}
