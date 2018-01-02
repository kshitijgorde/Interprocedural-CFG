// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.util.Vector;

public class PropertyChangeEventMediator
{
    private transient Vector propertyChangeListeners;
    private transient Vector vetoableChangeListeners;
    
    public PropertyChangeEventMediator() {
        this.propertyChangeListeners = new Vector();
        this.vetoableChangeListeners = new Vector();
    }
    
    public synchronized void addVetoableChangeListener(final VetoableChangeListener vetoableChangeListener) {
        final Vector vetoableChangeListeners = (Vector)((this.vetoableChangeListeners == null) ? new Vector<VetoableChangeListener>(2) : this.vetoableChangeListeners.clone());
        if (!vetoableChangeListeners.contains(vetoableChangeListener)) {
            vetoableChangeListeners.addElement(vetoableChangeListener);
            this.vetoableChangeListeners = vetoableChangeListeners;
        }
    }
    
    public synchronized void removeVetoableChangeListener(final VetoableChangeListener vetoableChangeListener) {
        if (this.vetoableChangeListeners != null && this.vetoableChangeListeners.contains(vetoableChangeListener)) {
            final Vector vetoableChangeListeners = (Vector)this.vetoableChangeListeners.clone();
            vetoableChangeListeners.removeElement(vetoableChangeListener);
            this.vetoableChangeListeners = vetoableChangeListeners;
        }
    }
    
    public void fireVetoableChange(final PropertyChangeEvent propertyChangeEvent) throws PropertyVetoException {
        if (this.vetoableChangeListeners != null) {
            final Vector vetoableChangeListeners = this.vetoableChangeListeners;
            for (int size = vetoableChangeListeners.size(), i = 0; i < size; ++i) {
                vetoableChangeListeners.elementAt(i).vetoableChange(propertyChangeEvent);
            }
        }
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (this.propertyChangeListeners != null && this.propertyChangeListeners.contains(propertyChangeListener)) {
            final Vector propertyChangeListeners = (Vector)this.propertyChangeListeners.clone();
            propertyChangeListeners.removeElement(propertyChangeListener);
            this.propertyChangeListeners = propertyChangeListeners;
        }
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        final Vector propertyChangeListeners = (Vector)((this.propertyChangeListeners == null) ? new Vector<PropertyChangeListener>(2) : this.propertyChangeListeners.clone());
        if (!propertyChangeListeners.contains(propertyChangeListener)) {
            propertyChangeListeners.addElement(propertyChangeListener);
            this.propertyChangeListeners = propertyChangeListeners;
        }
    }
    
    public void firePropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (this.propertyChangeListeners != null) {
            final Vector propertyChangeListeners = this.propertyChangeListeners;
            for (int size = propertyChangeListeners.size(), i = 0; i < size; ++i) {
                propertyChangeListeners.elementAt(i).propertyChange(propertyChangeEvent);
            }
        }
    }
    
    public static PropertyChangeEvent createPropertyChangeEvent(final Object o, final String s, final Object o2, final Object o3) {
        return new PropertyChangeEvent(o, s, o2, o3);
    }
}
