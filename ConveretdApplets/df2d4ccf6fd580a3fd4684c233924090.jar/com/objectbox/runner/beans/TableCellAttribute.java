// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Color;

public class TableCellAttribute
{
    private Color textcolor;
    private int[] symbolarray;
    private boolean isselectable;
    private Color backgroundcolor;
    protected transient PropertyChangeSupport propertyChange;
    private boolean fieldIsEditable;
    
    public TableCellAttribute() {
        this.textcolor = Color.black;
        this.symbolarray = null;
        this.isselectable = true;
        this.backgroundcolor = Color.white;
        this.fieldIsEditable = false;
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().addPropertyChangeListener(propertyChangeListener);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.getPropertyChange().firePropertyChange(s, o, o2);
    }
    
    public Color getBackgroundcolor() {
        return this.backgroundcolor;
    }
    
    public boolean getIsEditable() {
        return this.fieldIsEditable;
    }
    
    public boolean getIsselectable() {
        return this.isselectable;
    }
    
    protected PropertyChangeSupport getPropertyChange() {
        if (this.propertyChange == null) {
            this.propertyChange = new PropertyChangeSupport(this);
        }
        return this.propertyChange;
    }
    
    public int[] getSymbolarray() {
        return this.symbolarray;
    }
    
    public Color getTextcolor() {
        return this.textcolor;
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().removePropertyChangeListener(propertyChangeListener);
    }
    
    public void setBackgroundcolor(final Color backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }
    
    public void setIsEditable(final boolean fieldIsEditable) {
        final boolean fieldIsEditable2 = this.fieldIsEditable;
        this.fieldIsEditable = fieldIsEditable;
        this.firePropertyChange("isEditable", new Boolean(fieldIsEditable2), new Boolean(fieldIsEditable));
    }
    
    public void setIsselectable(final boolean isselectable) {
        this.isselectable = isselectable;
    }
    
    public void setSymbolarray(final int[] symbolarray) {
        this.symbolarray = symbolarray;
    }
    
    public void setTextcolor(final Color textcolor) {
        this.textcolor = textcolor;
    }
}
