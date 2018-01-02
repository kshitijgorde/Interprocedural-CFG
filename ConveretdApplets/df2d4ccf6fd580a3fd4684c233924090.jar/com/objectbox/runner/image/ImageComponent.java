// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.image;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.image.ImageFilter;
import java.awt.Image;
import java.awt.Canvas;

public class ImageComponent extends Canvas implements ImageListener
{
    private boolean scalable;
    private Image image;
    private transient ImageFilter filter;
    private PropertyChangeSupport changes;
    
    public ImageComponent() {
        this.changes = new PropertyChangeSupport(this);
        this.setScalable(false);
    }
    
    public ImageComponent(final SerializableImage serializableImage) {
        this(serializableImage, false);
    }
    
    public ImageComponent(final SerializableImage image, final boolean scalable) {
        this.changes = new PropertyChangeSupport(this);
        this.setImage(image);
        this.setScalable(scalable);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.changes.addPropertyChangeListener(propertyChangeListener);
    }
    
    public ImageFilter getFilter() {
        return this.filter;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public Dimension getMinimumSize() {
        if (this.image != null) {
            return new Dimension(this.image.getWidth(this), this.image.getHeight(this));
        }
        return new Dimension(75, 35);
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public void imageUpdated(final ImageEvent imageEvent) {
        if (imageEvent.getImage() != null) {
            this.setImage(imageEvent.getImage());
            this.repaint();
        }
    }
    
    public boolean isScalable() {
        return this.scalable;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (this.image != null) {
            if (this.scalable) {
                graphics.drawImage(this.image, 0, 0, width, height, this);
            }
            else {
                graphics.drawImage(this.image, 0, 0, this);
            }
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        try {
            this.filter = (ImageFilter)objectInputStream.readObject();
        }
        catch (Exception ex) {}
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.changes.removePropertyChangeListener(propertyChangeListener);
    }
    
    public void setBackground(final Color background) {
        final Color background2 = this.getBackground();
        super.setBackground(background);
        this.changes.firePropertyChange("background", background2, this.getBackground());
    }
    
    public void setEnabled(final boolean enabled) {
        final Boolean b = new Boolean(this.isEnabled());
        super.setEnabled(enabled);
        this.changes.firePropertyChange("enabled", b, new Boolean(this.isEnabled()));
    }
    
    public void setFilter(final ImageFilter filter) {
        this.filter = filter;
    }
    
    public synchronized void setImage(final Image image) {
        final Image image2 = this.getImage();
        this.image = image;
        this.changes.firePropertyChange("image", image2, this.getImage());
    }
    
    public synchronized void setScalable(final boolean scalable) {
        final Boolean b = new Boolean(this.isScalable());
        this.scalable = scalable;
        this.changes.firePropertyChange("scalable", b, new Boolean(this.isScalable()));
    }
    
    public void setVisible(final boolean visible) {
        final Boolean b = new Boolean(this.isVisible());
        super.setVisible(visible);
        this.changes.firePropertyChange("visible", b, new Boolean(this.isVisible()));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (this.filter instanceof Serializable) {
            objectOutputStream.writeObject(this.filter);
        }
    }
}
