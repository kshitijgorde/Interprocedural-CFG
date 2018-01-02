// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import KJEgui.GetGraphics;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public abstract class Axis
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;
    public static final int TICK_LENGTH = 3;
    public static final int LABEL_SPACE_LENGTH = 5;
    public static final int LINE_WIDTH = 1;
    protected Graph _gGraph;
    protected boolean _bShow;
    public int _iOrientation;
    protected Color _cColor;
    public Color _cLine;
    protected Font _fFont;
    protected int _iXOffset;
    protected int _iYOffset;
    protected int _iLabelCount;
    protected int _iMaxLabelLength;
    protected int _iMaxLength;
    protected int _iWidth;
    protected int _iHeight;
    protected int _iPixelsPerIncrement;
    protected int _iDataLength;
    protected boolean _bUseTextImages;
    protected Image[] _iTextImages;
    protected int _iFontHeight;
    protected int _iFontDescent;
    protected int _iFontOffset;
    
    public Axis(final int iOrientation) {
        this._iOrientation = iOrientation;
        this._fFont = new Font("helvetica", 1, 11);
        this._cColor = Color.black;
        this._cLine = Color.gray;
        this._bShow = true;
    }
    
    public Color getColor() {
        return this._cColor;
    }
    
    public int getDataLength() {
        return this._iDataLength;
    }
    
    public Font getFont() {
        return this._fFont;
    }
    
    public int getHeight() {
        return this._iHeight;
    }
    
    public int getMaxLabelLength() {
        return this._iMaxLabelLength;
    }
    
    public int getMaxLength() {
        return this._iMaxLength;
    }
    
    public static Image getVerticalText(final String s, final Graphics graphics, final Component component, final Color color) {
        final int height = graphics.getFontMetrics().getHeight();
        final int stringWidth = graphics.getFontMetrics().stringWidth(s);
        final int n = graphics.getFontMetrics().getHeight() - graphics.getFontMetrics().getDescent();
        final int n2 = 0;
        final int[] array = new int[stringWidth * height];
        final Image image = component.createImage(stringWidth, height);
        final Graphics graphics2 = image.getGraphics();
        GetGraphics.setText(graphics2);
        graphics2.setColor(color);
        graphics2.fillRect(0, 0, stringWidth, height);
        graphics2.setColor(graphics.getColor());
        graphics2.setFont(graphics.getFont());
        graphics2.drawString(s, n2, n);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, stringWidth, height, array, 0, stringWidth);
        pixelGrabber.setColorModel(ColorModel.getRGBdefault());
        boolean grabPixels;
        do {
            try {
                grabPixels = pixelGrabber.grabPixels(500L);
            }
            catch (InterruptedException ex) {
                return image;
            }
        } while (!grabPixels);
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            return image;
        }
        final boolean startsWith = System.getProperty("java.vendor").toLowerCase().startsWith("netscape");
        int n3 = 0;
        final int rgb = color.getRGB();
        final int[] array2 = new int[stringWidth * height];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < stringWidth; ++j) {
                if (array[n3] == array[0] && startsWith) {
                    array2[(stringWidth - j - 1) * height + i] = rgb;
                }
                else {
                    array2[(stringWidth - j - 1) * height + i] = array[n3];
                }
                ++n3;
            }
        }
        return component.createImage(new MemoryImageSource(height, stringWidth, array2, 0, height));
    }
    
    public int getWidth() {
        return this._iWidth;
    }
    
    public int getXOffset() {
        return this._iXOffset;
    }
    
    public int getYOffset() {
        return this._iYOffset;
    }
    
    public abstract void paint(final Graphics p0, final int p1, final int p2, final int p3, final int p4);
    
    public abstract void setINF(final Graphics p0, final Graph p1);
    
    public void setOrientation(final int iOrientation) {
        this._iOrientation = iOrientation;
    }
    
    public abstract void setSize(final Graphics p0, final int p1);
    
    public void setVisible(final boolean bShow) {
        this._bShow = bShow;
    }
}
