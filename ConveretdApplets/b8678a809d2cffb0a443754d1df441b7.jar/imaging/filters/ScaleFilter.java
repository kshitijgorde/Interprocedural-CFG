// 
// Decompiled by Procyon v0.5.30
// 

package imaging.filters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.util.Hashtable;
import java.awt.image.BufferedImage;
import imaging.util.AbstractBufferedImageOp;

public class ScaleFilter extends AbstractBufferedImageOp
{
    private int width;
    private int height;
    
    public BufferedImage filter(final BufferedImage src, final float scale) {
        this.width = (int)(src.getWidth() * scale);
        this.height = (int)(src.getHeight() * scale);
        return this.filter(src);
    }
    
    public BufferedImage filter(final BufferedImage src) {
        final ColorModel dstCM = src.getColorModel();
        final BufferedImage dst = new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(this.width, this.height), dstCM.isAlphaPremultiplied(), null);
        final Image scaleImage = src.getScaledInstance(this.width, this.height, 4);
        final Graphics2D g = dst.createGraphics();
        g.drawImage(scaleImage, 0, 0, this.width, this.height, null);
        g.dispose();
        return dst;
    }
    
    @Override
    public String toString() {
        return "Distort/Scale";
    }
    
    @Override
    public BufferedImage filter(final BufferedImage arg0, final BufferedImage arg1) {
        return null;
    }
}
