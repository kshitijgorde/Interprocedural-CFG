// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.ColorModel;
import java.awt.Image;
import java.awt.image.ImageObserver;

public final class MyPixelGrabber implements ImageObserver
{
    private Image m_image;
    private Object m_pixels;
    private int m_iNumOfColors;
    private int m_iWidth;
    private int m_iHeight;
    private ColorModel m_colorModel;
    
    public MyPixelGrabber(final Image img) {
        this.m_image = null;
        this.m_pixels = null;
        this.m_iNumOfColors = 0;
        this.m_colorModel = null;
        this.m_image = img;
    }
    
    public void grabPixels() {
        this.m_iWidth = this.m_image.getWidth(this);
        this.m_iHeight = this.m_image.getHeight(this);
        final PixelGrabber pixelGrabber = new PixelGrabber(this.m_image, 0, 0, this.m_iWidth, this.m_iHeight, true);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception e) {
            System.out.println("PixelGrabber exception");
        }
        this.m_pixels = pixelGrabber.getPixels();
        this.m_colorModel = pixelGrabber.getColorModel();
        if (this.m_colorModel instanceof IndexColorModel) {
            this.m_iNumOfColors = ((IndexColorModel)this.m_colorModel).getMapSize();
        }
    }
    
    public Object getPixels() {
        return this.m_pixels;
    }
    
    public int getWidth() {
        return this.m_iWidth;
    }
    
    public int getHeight() {
        return this.m_iHeight;
    }
    
    public int getNumOfColors() {
        return this.m_iNumOfColors;
    }
    
    public int getRed(final int pixel) {
        if (this.m_colorModel instanceof IndexColorModel) {
            return ((IndexColorModel)this.m_colorModel).getRed(pixel);
        }
        return ((DirectColorModel)this.m_colorModel).getRed(pixel);
    }
    
    public int getGreen(final int pixel) {
        if (this.m_colorModel instanceof IndexColorModel) {
            return ((IndexColorModel)this.m_colorModel).getGreen(pixel);
        }
        return ((DirectColorModel)this.m_colorModel).getGreen(pixel);
    }
    
    public int getBlue(final int pixel) {
        if (this.m_colorModel instanceof IndexColorModel) {
            return ((IndexColorModel)this.m_colorModel).getBlue(pixel);
        }
        return ((DirectColorModel)this.m_colorModel).getBlue(pixel);
    }
    
    public int getAlpha(final int pixel) {
        if (this.m_colorModel instanceof IndexColorModel) {
            return ((IndexColorModel)this.m_colorModel).getAlpha(pixel);
        }
        return ((DirectColorModel)this.m_colorModel).getAlpha(pixel);
    }
    
    public ColorModel getColorModel() {
        return this.m_colorModel;
    }
    
    public void destroy() {
        this.m_image = null;
        this.m_pixels = null;
    }
    
    public boolean imageUpdate(final Image img, final int infoflags, final int x, final int y, final int width, final int height) {
        return true;
    }
}
