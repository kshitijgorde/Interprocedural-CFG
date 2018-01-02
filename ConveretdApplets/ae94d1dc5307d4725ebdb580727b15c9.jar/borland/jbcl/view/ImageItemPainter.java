// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.io.IOException;
import borland.jbcl.util.ImageLoader;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import borland.jbcl.model.ItemPaintSite;
import java.awt.Graphics;
import java.awt.Component;
import borland.jbcl.model.ItemPainter;

public class ImageItemPainter implements ItemPainter
{
    protected Component component;
    protected int alignment;
    protected boolean streamResetError;
    protected boolean paintBackground;
    protected boolean genDisabledImage;
    
    public ImageItemPainter() {
        this.paintBackground = true;
        this.genDisabledImage = true;
    }
    
    public ImageItemPainter(final Component component) {
        this(component, 68);
    }
    
    public ImageItemPainter(final Component component, final int alignment) {
        this.paintBackground = true;
        this.genDisabledImage = true;
        this.component = component;
        this.alignment = alignment;
    }
    
    public ImageItemPainter(final Component component, final int alignment, final boolean paintBackground) {
        this.paintBackground = true;
        this.genDisabledImage = true;
        this.component = component;
        this.alignment = alignment;
        this.paintBackground = paintBackground;
    }
    
    public void setAlignment(final int alignment) {
        if ((alignment & 0xF0) < 0 || (alignment & 0xF0) > 64) {
            throw new IllegalArgumentException(Res.format(1, new Integer[] { new Integer(alignment & 0xF0) }));
        }
        if ((alignment & 0xF) < 0 || (alignment & 0xF) > 4) {
            throw new IllegalArgumentException(Res.format(2, new Integer[] { new Integer(alignment & 0xF) }));
        }
        this.alignment = alignment;
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    public void setPaintBackground(final boolean paintBackground) {
        this.paintBackground = paintBackground;
    }
    
    public boolean isPaintBackground() {
        return this.paintBackground;
    }
    
    public void setGenDisabledImage(final boolean genDisabledImage) {
        this.genDisabledImage = genDisabledImage;
    }
    
    public boolean isGenDisabledImage() {
        return this.genDisabledImage;
    }
    
    public Dimension getPreferredSize(final Object data, final Graphics g, final int state, final ItemPaintSite site) {
        if (site != null && site.getSiteComponent() != null) {
            this.component = site.getSiteComponent();
        }
        final Image image = this.getImage(data, state);
        return (image == null) ? new Dimension() : new Dimension(image.getWidth(this.component), image.getHeight(this.component));
    }
    
    public void paint(final Object data, final Graphics g, final Rectangle rect, final int state, final ItemPaintSite site) {
        if (site != null && site.getSiteComponent() != null) {
            this.component = site.getSiteComponent();
        }
        final Image image = this.getImage(data, state);
        final Color oc = g.getColor();
        Color bg = (site != null) ? site.getBackground() : g.getColor();
        if (bg == null) {
            bg = g.getColor();
        }
        g.setColor(bg);
        if (this.paintBackground) {
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
        if (this.streamResetError) {
            g.setColor(SystemColor.windowText);
            g.drawString(Res.getString(9), rect.x, rect.y);
        }
        else if (image != null) {
            final int imageWidth = image.getWidth(this.component);
            final int imageHeight = image.getHeight(this.component);
            int a = (site != null) ? site.getAlignment() : this.alignment;
            if (a == 0) {
                a = this.alignment;
            }
            int x = 0;
            int width = 0;
            switch (a & 0xF) {
                default: {
                    x = rect.x;
                    width = imageWidth;
                    break;
                }
                case 2: {
                    x = rect.x + (rect.width - imageWidth) / 2;
                    width = imageWidth;
                    break;
                }
                case 3: {
                    x = rect.x + rect.width - imageWidth;
                    width = imageWidth;
                    break;
                }
                case 4: {
                    x = rect.x;
                    width = rect.width;
                    break;
                }
            }
            int y = 0;
            int height = 0;
            switch (a & 0xF0) {
                default: {
                    y = rect.y;
                    height = imageHeight;
                    break;
                }
                case 32: {
                    y = rect.y + (rect.height - imageHeight) / 2;
                    height = imageHeight;
                    break;
                }
                case 48: {
                    y = rect.y + rect.height - imageHeight;
                    height = imageHeight;
                    break;
                }
                case 64: {
                    y = rect.y;
                    height = rect.height;
                    break;
                }
            }
            g.drawImage(image, x, y, width, height, this.component);
        }
        g.setColor(oc);
    }
    
    protected Image getImage(final Object data, final int state) {
        if (data == null) {
            return null;
        }
        this.streamResetError = false;
        Image image;
        if (data instanceof Image) {
            image = (Image)data;
        }
        else {
            try {
                image = ImageLoader.loadFromBlob(data, this.component, true);
            }
            catch (IOException ex) {
                this.streamResetError = true;
                image = null;
            }
        }
        if (image == null) {
            return null;
        }
        if (this.genDisabledImage && (state & 0x1) != 0x0) {
            return ImageLoader.getDisabledImage(this.component, image);
        }
        return image;
    }
}
