// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.awt.Graphics;
import java.awt.Frame;
import java.beans.PropertyChangeListener;
import java.awt.Dimension;
import java.beans.PropertyChangeSupport;
import java.awt.Component;

public class LWSeparator extends Component
{
    protected transient PropertyChangeSupport propertyChange;
    private boolean fieldDirection;
    private Dimension fieldPrefSize;
    
    public LWSeparator() {
        this.fieldDirection = true;
        this.fieldPrefSize = null;
        this.initialize();
    }
    
    public void addNotify() {
        super.addNotify();
        final Dimension size = this.getSize();
        if (size.width == 0 || size.height == 0) {
            this.setSize(this.getPreferredSize());
        }
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().addPropertyChangeListener(propertyChangeListener);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.getPropertyChange().firePropertyChange(s, o, o2);
    }
    
    public boolean getDirection() {
        return this.fieldDirection;
    }
    
    public Dimension getMaximumSize() {
        if (this.getPrefSize() != null) {
            return this.getPrefSize();
        }
        if (this.fieldDirection) {
            return new Dimension(this.getSize().width, 10);
        }
        return new Dimension(10, this.getSize().height);
    }
    
    public Dimension getMinimumSize() {
        if (this.getPrefSize() != null) {
            return this.getPrefSize();
        }
        if (this.fieldDirection) {
            return new Dimension(this.getSize().width, 10);
        }
        return new Dimension(10, this.getSize().height);
    }
    
    public Dimension getPreferredSize() {
        if (this.getPrefSize() != null) {
            return this.getPrefSize();
        }
        if (this.fieldDirection) {
            return new Dimension(this.getSize().width, 10);
        }
        return new Dimension(10, this.getSize().height);
    }
    
    public Dimension getPrefSize() {
        return this.fieldPrefSize;
    }
    
    protected PropertyChangeSupport getPropertyChange() {
        if (this.propertyChange == null) {
            this.propertyChange = new PropertyChangeSupport(this);
        }
        return this.propertyChange;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initialize() {
        this.setName("LWSeparator");
        this.setSize(150, 143);
        this.setDirection(false);
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t2) {
                frame = new Frame();
            }
            final LWSeparator lwSeparator = new LWSeparator();
            frame.add("Center", lwSeparator);
            frame.setSize(lwSeparator.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t) {
            System.err.println("Exception occurred in main() of com.objectbox.gui.lwcomp.LWSeparator");
            t.printStackTrace(System.out);
        }
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setColor(this.getBackground().darker());
        if (this.fieldDirection) {
            graphics.drawLine(0, height / 2, width, height / 2);
            graphics.setColor(this.getBackground().brighter());
            graphics.drawLine(0, height / 2 + 1, width, height / 2 + 1);
        }
        else {
            graphics.drawLine(width / 2, 0, width / 2, height);
            graphics.setColor(this.getBackground().brighter());
            graphics.drawLine(width / 2 + 1, 0, width / 2 + 1, height);
        }
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().removePropertyChangeListener(propertyChangeListener);
    }
    
    public void setDirection(final boolean fieldDirection) {
        final boolean fieldDirection2 = this.fieldDirection;
        this.fieldDirection = fieldDirection;
        this.firePropertyChange("direction", new Boolean(fieldDirection2), new Boolean(fieldDirection));
        this.repaint();
    }
    
    public void setPrefSize(final Dimension fieldPrefSize) {
        this.firePropertyChange("prefSize", this.fieldPrefSize, this.fieldPrefSize = fieldPrefSize);
    }
}
