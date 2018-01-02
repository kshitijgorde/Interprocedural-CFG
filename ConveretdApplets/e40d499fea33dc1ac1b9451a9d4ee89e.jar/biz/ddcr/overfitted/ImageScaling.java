// 
// Decompiled by Procyon v0.5.30
// 

package biz.ddcr.overfitted;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.image.RenderedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageScaling
{
    BufferedImage orig;
    
    public ImageScaling(final BufferedImage image) {
        this.orig = image;
    }
    
    public BufferedImage getScaledBufferedImage(final int w, final int h) {
        double scaleWidth = w / this.orig.getWidth();
        double scaleHeight = h / this.orig.getHeight();
        if (scaleWidth == 0.0) {
            scaleWidth = 1.0;
        }
        if (scaleHeight == 0.0) {
            scaleHeight = 1.0;
        }
        final int type = 1;
        final BufferedImage out = new BufferedImage(w, h, type);
        final Graphics2D g2 = out.createGraphics();
        final AffineTransform at = AffineTransform.getScaleInstance(scaleWidth, scaleHeight);
        g2.drawRenderedImage(this.orig, at);
        g2.dispose();
        return out;
    }
    
    public ImageIcon getScaledImageIcon(final int wantedWidth, final int wantedHeight) {
        return new ImageIcon(this.getScaledBufferedImage(wantedWidth, wantedHeight));
    }
}
