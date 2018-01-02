// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Rectangle;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Panel;

public class ColorList extends Panel
{
    private Image colorList;
    private int clWidth;
    private int clHeight;
    private Component listener;
    private int[] pixels;
    private Color selectedColor;
    private boolean bValidSelection;
    private static final Color BORDER_COLOR;
    private boolean isLinux;
    
    public ColorList(final Image colorList) {
        this.colorList = null;
        this.clWidth = 0;
        this.clHeight = 0;
        this.listener = null;
        this.bValidSelection = true;
        this.isLinux = false;
        this.colorList = colorList;
        this.clWidth = colorList.getWidth(this);
        this.clHeight = colorList.getHeight(this);
        this.pixels = new int[this.clWidth * this.clHeight];
        try {
            this.isLinux = (System.getProperty("os.name").indexOf("Linux") != -1);
            final PixelGrabber pixelGrabber = new PixelGrabber(colorList, 0, 0, this.clWidth, this.clHeight, this.pixels, 0, this.clWidth);
            pixelGrabber.grabPixels();
            if ((pixelGrabber.status() & 0x80) != 0x0) {
                System.out.println("Error Grabbing pixels in ColorList");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.colorList != null) {
            graphics.drawImage(this.colorList, 0, 0, this);
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.isVisible()) {
            if (this.isLinux) {
                final Rectangle bounds = this.getBounds();
                if (n > 0 && n < bounds.width && n2 > 0 && n2 < bounds.height) {
                    return false;
                }
            }
            this.hide();
            if (this.listener != null) {
                this.listener.postEvent(new Event(this, 502, null));
            }
            this.listener = null;
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        boolean b = false;
        this.bValidSelection = true;
        if (this.listener != null && this.pixels != null && n < this.clWidth && n2 < this.clHeight) {
            this.selectedColor = new Color(this.pixels[n2 * this.clWidth + n]);
            if (this.selectedColor.equals(Color.white) && !this.checkIsWhite(n, n2)) {
                this.bValidSelection = false;
            }
            else {
                this.listener.postEvent(new Event(this, 1001, null));
            }
            b = true;
        }
        return b;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        boolean b = false;
        if (this.isVisible() && this.bValidSelection) {
            this.hide();
            if (this.listener != null) {
                this.listener.postEvent(new Event(this, 502, null));
            }
            this.listener = null;
            b = true;
        }
        return b;
    }
    
    public void showList(final int n, final int n2) {
        this.reshape(n, n2, this.clWidth, this.clHeight);
        this.show();
    }
    
    public void setColor(final Color selectedColor) {
        this.selectedColor = selectedColor;
    }
    
    public Color getColor() {
        return this.selectedColor;
    }
    
    private boolean checkIsWhite(final int n, final int n2) {
        boolean b = true;
        final int rgb = ColorList.BORDER_COLOR.getRGB();
        final int rgb2 = Color.white.getRGB();
        for (int n3 = -2; b && n3 < 3; ++n3) {
            int i = 0;
            int n4 = n3;
            while (i < 2) {
                final int n5 = this.pixels[(n2 + n3) * this.clWidth + n + n4];
                if (n5 != rgb2 && n5 != rgb) {
                    b = false;
                    break;
                }
                ++i;
                n4 = -n3;
            }
            if (++n3 == 0) {}
        }
        return b;
    }
    
    public void setListener(final Component listener) {
        this.listener = listener;
    }
    
    static {
        BORDER_COLOR = new Color(204, 204, 204);
    }
}
