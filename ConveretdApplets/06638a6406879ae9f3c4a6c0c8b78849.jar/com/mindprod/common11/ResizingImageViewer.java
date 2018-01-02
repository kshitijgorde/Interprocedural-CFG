// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common11;

import java.awt.MediaTracker;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.Component;

public final class ResizingImageViewer extends Component
{
    private AffineTransform transform;
    private Image image;
    
    public ResizingImageViewer() {
        this.image = null;
    }
    
    public ResizingImageViewer(final AffineTransform transform) {
        this.transform = transform;
    }
    
    public ResizingImageViewer(final Image image) {
        this();
        this.setImage(image);
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        if (this.image != null) {
            return new Dimension(this.image.getWidth(this), this.image.getHeight(this));
        }
        return new Dimension(10, 10);
    }
    
    public void paint(final Graphics g) {
        final Dimension dim = this.getSize();
        if (this.image != null) {
            AffineTransform saveTransform = null;
            final Graphics2D g2 = (Graphics2D)g;
            if (this.transform != null) {
                saveTransform = g2.getTransform();
                g2.setTransform(this.transform);
            }
            g.drawImage(this.image, 0, 0, dim.width, dim.height, this);
            if (this.transform != null) {
                g2.setTransform(saveTransform);
            }
        }
        else {
            g.setColor(this.getBackground());
            g.clearRect(0, 0, dim.width, dim.height);
        }
    }
    
    public void setImage(final Image image) {
        if (image == null) {
            this.image = null;
            this.repaint();
        }
        else {
            this.image = image;
            try {
                final MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(image, 0);
                tracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
