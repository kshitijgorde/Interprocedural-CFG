// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import com.masystem.beergame.ui.component.swing.ImageComponent;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import com.masystem.beergame.ui.scene.Node;

public class BeerGameRotatedImage extends Node
{
    public BeerGameRotatedImage() {
        super(new RotatedImageComponent());
    }
    
    public BeerGameRotatedImage(final BufferedImage bufferedImage) {
        super(new RotatedImageComponent(bufferedImage));
    }
    
    public final void setRotation(final double rotation) {
        ((RotatedImageComponent)super.getComponent()).setRotation(rotation);
    }
    
    @Override
    public final void setCacheEnabled(final boolean cacheEnabled) {
        if (!cacheEnabled || this.getNbrChildren() != 0) {
            super.setCacheEnabled(cacheEnabled);
        }
    }
    
    public static final class RotatedImageComponent extends ImageComponent
    {
        private static final long serialVersionUID = 1L;
        private final AffineTransform transform;
        private double rotation;
        
        public RotatedImageComponent() {
            this.transform = new AffineTransform();
        }
        
        public RotatedImageComponent(final BufferedImage bufferedImage) {
            super(bufferedImage);
            this.transform = new AffineTransform();
        }
        
        @Override
        public final void setImage(final BufferedImage image) {
            super.setImage(image);
            final int width = image.getWidth();
            final int height = image.getHeight();
            final int n = (int)Math.round(Math.sqrt(width * width + height * height));
            this.setPreferredSize(new Dimension(n, n));
        }
        
        public final void setRotation(final double rotation) {
            this.rotation = rotation;
        }
        
        @Override
        protected final void paintComponent(final Graphics graphics) {
            final int width = this.image.getWidth();
            final int height = this.image.getHeight();
            final int n2;
            final int n = (n2 = (int)Math.round(Math.sqrt(width * width + height * height))) - width >> 1;
            final int n3 = n2 - height >> 1;
            if (this.rotation != 0.0) {
                this.transform.setToTranslation(n, n3);
                this.transform.rotate(-this.rotation, width * 0.5, height * 0.5);
                ((Graphics2D)graphics).drawImage(this.image, this.transform, null);
                return;
            }
            ((Graphics2D)graphics).drawImage(this.image, n, n3, null);
        }
    }
}
