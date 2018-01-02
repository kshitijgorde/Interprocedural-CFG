// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component.swing;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import com.masystem.beergame.ui.scene.PaintProperties;
import javax.swing.JComponent;

public class ImageComponent extends JComponent implements HasPaintProperties
{
    private static final long serialVersionUID = 1L;
    protected PaintProperties paintProperties;
    protected BufferedImage image;
    
    public ImageComponent() {
        this.paintProperties = new PaintProperties();
    }
    
    public ImageComponent(final BufferedImage image) {
        this.paintProperties = new PaintProperties();
        this.setImage(image);
    }
    
    public final BufferedImage getImage() {
        return this.image;
    }
    
    public void setImage(final BufferedImage image) {
        this.image = image;
        this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        this.setOpaque(image.getTransparency() == 1);
    }
    
    @Override
    public final void setPaintProperties(final PaintProperties paintProperties) {
        this.paintProperties = paintProperties;
    }
    
    @Override
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2 = this.paintProperties.createGraphics(graphics);
        super.paint(graphics2);
        graphics2.dispose();
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        ((Graphics2D)graphics).drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
