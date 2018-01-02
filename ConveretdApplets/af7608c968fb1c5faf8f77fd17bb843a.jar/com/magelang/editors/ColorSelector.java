// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.editors;

import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.awt.Component;
import java.beans.PropertyChangeSupport;
import java.awt.Color;
import java.beans.PropertyEditor;
import java.awt.Panel;

public class ColorSelector extends Panel implements PropertyEditor
{
    Color fieldSelectedColor;
    protected transient PropertyChangeSupport propertyChange;
    private String initString;
    
    public Component getCustomEditor() {
        return this;
    }
    
    public ColorSelector() {
        this.fieldSelectedColor = null;
        this.propertyChange = new PropertyChangeSupport(this);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.propertyChange.firePropertyChange(s, o, o2);
    }
    
    public void setAsText(final String s) throws IllegalArgumentException {
        throw new IllegalArgumentException("bogus setAsText call, dude");
    }
    
    public boolean isPaintable() {
        return true;
    }
    
    public String getAsText() {
        return null;
    }
    
    public Color getSelectedColor() {
        return this.fieldSelectedColor;
    }
    
    public void setSelectedColor(final Color fieldSelectedColor) {
        this.firePropertyChange("selectedColor", this.fieldSelectedColor, this.fieldSelectedColor = fieldSelectedColor);
    }
    
    public void setValue(final Object o) {
        if (o == null) {
            this.setSelectedColor(null);
        }
        else {
            if (!(o instanceof Color)) {
                throw new IllegalArgumentException(String.valueOf("Not a color: ") + o);
            }
            this.setSelectedColor((Color)o);
        }
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.propertyChange.removePropertyChangeListener(propertyChangeListener);
    }
    
    public String getJavaInitializationString() {
        return this.initString;
    }
    
    public void paintValue(final Graphics graphics, final Rectangle rectangle) {
        if (this.getSelectedColor() != null) {
            graphics.setColor(this.getSelectedColor());
            graphics.fill3DRect(rectangle.x + 1, rectangle.y + 1, rectangle.width - 3, rectangle.height - 3, true);
        }
        else {
            final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
            graphics.setColor(Color.lightGray);
            graphics.fill3DRect(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, true);
            graphics.setColor(Color.black);
            graphics.drawString("<default>", rectangle.x + 3, rectangle.y + (fontMetrics.getAscent() + rectangle.height) / 2 - 1);
        }
    }
    
    public Object getValue() {
        return this.getSelectedColor();
    }
    
    public String[] getTags() {
        return null;
    }
    
    protected void setInitString(final String initString) {
        this.initString = initString;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.propertyChange.addPropertyChangeListener(propertyChangeListener);
    }
    
    public boolean supportsCustomEditor() {
        return true;
    }
}
