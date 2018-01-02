import java.awt.Point;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class nsbutton extends Canvas implements MouseListener
{
    protected int border;
    protected int spacing;
    protected String label;
    protected Image image;
    protected Image greyimage;
    protected boolean vertical;
    protected boolean left;
    protected boolean bottom;
    protected boolean mouseover;
    protected boolean mouseup;
    protected boolean autosize;
    private String url;
    private String target;
    PopupMenu popup;
    Image offImage;
    Graphics offGraphics;
    Dimension offDim;
    navwinbutton AppParent;
    
    protected void drawButtonImage(final Graphics graphics, final FontMetrics fontMetrics, final Image image) {
        if (image != null && !this.AppParent.ndmode) {
            if (this.vertical) {
                if (this.bottom) {
                    graphics.drawImage(image, (this.size().width - image.getWidth(this)) / 2, this.border, this);
                }
                else {
                    graphics.drawImage(image, (this.size().width - image.getWidth(this)) / 2, this.size().height - this.border - image.getHeight(this), this);
                }
            }
            else if (this.left) {
                graphics.drawImage(image, this.size().width - image.getWidth(null) - this.border, (this.size().height - image.getHeight(null)) / 2, this);
            }
            else {
                graphics.drawImage(image, this.border, (this.size().height - image.getHeight(null)) / 2, this);
            }
        }
        if (image != null && this.AppParent.ndmode) {
            if (this.left) {
                graphics.drawImage(image, this.size().width - image.getWidth(null) - this.border, (this.size().height - image.getHeight(null) + 1) / 2, this);
            }
            else {
                graphics.drawImage(image, this.border, (this.size().height - image.getHeight(null) + 1) / 2, this);
            }
        }
    }
    
    public void setHorizontalLayout(final boolean left) {
        this.vertical = false;
        this.left = left;
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(12));
        if (this.url != null) {
            this.AppParent.showStatus(this.url);
        }
        this.mouseover = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mouseover = false;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.mouseover = false;
        this.repaint();
    }
    
    public synchronized void mousePressed(final MouseEvent mouseEvent) {
        if (this.url != null && !this.AppParent.callJavascript(this.url)) {
            try {
                this.AppParent.getAppletContext().showDocument(new URL(this.AppParent.getCodeBase(), this.url), this.target);
            }
            catch (MalformedURLException ex) {}
        }
        else {
            this.mouseup = false;
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    nsbutton() {
        this.border = 0;
        this.spacing = 0;
        this.label = null;
        this.image = null;
        this.greyimage = null;
        this.vertical = false;
        this.left = false;
        this.bottom = false;
        this.mouseover = false;
        this.mouseup = true;
        this.autosize = false;
        this.url = null;
        this.target = null;
        this.popup = null;
        this.offImage = null;
        this.offGraphics = null;
        this.offDim = null;
        this.AppParent = null;
        this.border = 4;
        this.spacing = 6;
        this.label = new String();
        this.image = null;
        this.greyimage = null;
        this.vertical = true;
        this.left = true;
        this.bottom = true;
        this.mouseover = false;
        this.mouseup = true;
        this.autosize = true;
        this.url = null;
        this.target = null;
        this.popup = null;
    }
    
    nsbutton(final String label, final Image image, final String url, final String target, final PopupMenu popup, final navwinbutton appParent) {
        this.border = 0;
        this.spacing = 0;
        this.label = null;
        this.image = null;
        this.greyimage = null;
        this.vertical = false;
        this.left = false;
        this.bottom = false;
        this.mouseover = false;
        this.mouseup = true;
        this.autosize = false;
        this.url = null;
        this.target = null;
        this.popup = null;
        this.offImage = null;
        this.offGraphics = null;
        this.offDim = null;
        this.AppParent = null;
        this.border = 4;
        this.spacing = 6;
        this.label = new String();
        this.image = null;
        this.greyimage = null;
        this.vertical = true;
        this.left = true;
        this.bottom = true;
        this.mouseover = false;
        this.mouseup = true;
        this.autosize = true;
        this.url = null;
        this.target = null;
        this.popup = null;
        this.AppParent = appParent;
        this.label = label;
        if (image != null) {
            this.prepareImage(this.image = image, this);
            if (this.image != null) {
                this.AppParent.tracker.addImage(this.image, 0);
            }
        }
        this.url = url;
        this.target = target;
        this.popup = popup;
    }
    
    public void paint(final Graphics graphics) {
        if (this.offGraphics != null) {
            this.offGraphics.setColor(this.getBackground());
            this.offGraphics.fillRect(0, 0, this.size().width, this.size().height);
            if (this.AppParent.bgimage != null) {
                final int min = Math.min(this.location().x + this.size().width, this.AppParent.bgimage.getWidth(this));
                final int min2 = Math.min(this.location().y + this.size().height, this.AppParent.bgimage.getHeight(this));
                final int max = Math.max(min, 0);
                final int max2 = Math.max(min2, 0);
                this.offGraphics.drawImage(this.AppParent.bgimage, 0, 0, max - this.location().x, max2 - this.location().y, this.location().x, this.location().y, max, max2, this);
            }
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            if (this.mouseover) {
                this.offGraphics.draw3DRect(0, 0, this.size().width - 1, this.size().height - 1, this.mouseup);
            }
            if (!this.mouseover && this.AppParent.grayscale) {
                this.drawButtonImage(this.offGraphics, fontMetrics, this.greyimage);
            }
            else {
                this.drawButtonImage(this.offGraphics, fontMetrics, this.image);
            }
            this.drawButtonText(this.offGraphics, fontMetrics);
            if (this.offImage != null) {
                graphics.drawImage(this.offImage, 0, 0, this);
            }
        }
        else {
            this.repaint();
        }
        if (this.popup != null && !this.mouseup) {
            if (this.AppParent.popupright) {
                this.popup.show(this, this.size().width, 0);
            }
            else {
                this.popup.show(this, 0, this.size().height);
            }
            this.mouseup = true;
        }
    }
    
    public void setBorder(final int border) {
        this.border = border;
        this.repaint();
    }
    
    public void setAutosize(final boolean autosize) {
        this.autosize = autosize;
        this.repaint();
    }
    
    protected Image createGreyScaleImage(final Image image) {
        final int width = this.image.getWidth(null);
        final int height = this.image.getHeight(null);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                final int n = array[i * width + j];
                final int n2 = (n & 0xFF000000) >> 24;
                final int n3 = (int)(((n & 0xFF0000) >> 16) * 0.299) + (int)(((n & 0xFF00) >> 8) * 0.587) + (int)((n & 0xFF) * 0.114);
                array[i * width + j] = (n2 << 24 | n3 << 16 | n3 << 8 | n3);
            }
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
    }
    
    public void update(final Graphics graphics) {
        if (this.offGraphics == null || this.size().width != this.offDim.width || this.size().height != this.offDim.height) {
            this.offDim = this.size();
            this.offImage = this.createImage(this.offDim.width, this.offDim.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.paint(graphics);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    protected void setOptimumSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final StringTokenizer stringTokenizer = new StringTokenizer(this.label, "'");
        final int countTokens = stringTokenizer.countTokens();
        int n = 0;
        int width = 0;
        if (this.image != null) {
            n = this.image.getHeight(null) + this.spacing;
            width = this.image.getWidth(null);
        }
        final int n2 = fontMetrics.getHeight() * countTokens + 2 * this.border;
        int stringWidth = 0;
        int n3;
        if (this.vertical) {
            n3 = n + n2;
        }
        else {
            n3 = n + (this.border - this.spacing);
            if (n3 < n2) {
                n3 = n2;
            }
        }
        while (stringTokenizer.hasMoreTokens()) {
            final String string = "  " + stringTokenizer.nextToken() + "  ";
            if (stringWidth < fontMetrics.stringWidth(string)) {
                stringWidth = fontMetrics.stringWidth(string);
            }
        }
        if (this.vertical) {
            if (width < stringWidth) {
                width = stringWidth;
            }
        }
        else {
            width += stringWidth + this.spacing + this.border;
        }
        this.resize(width, n3);
    }
    
    public void setVerticalLayout(final boolean bottom) {
        this.vertical = true;
        this.bottom = bottom;
        this.repaint();
    }
    
    public void setSpacing(final int spacing) {
        this.spacing = spacing;
        this.repaint();
    }
    
    public void setLabel(final String label) {
        this.label = label;
        this.repaint();
    }
    
    protected void drawButtonText(final Graphics graphics, final FontMetrics fontMetrics) {
        final Point point = new Point();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.label, "'");
        final int countTokens = stringTokenizer.countTokens();
        graphics.setColor(this.AppParent.fontcolor);
        if (this.vertical) {
            if (this.image != null) {
                if (this.bottom) {
                    point.y = (this.size().height + this.image.getHeight(null)) / 2 - (fontMetrics.getAscent() + fontMetrics.getDescent()) * countTokens / 2 + fontMetrics.getAscent();
                }
                else {
                    point.y = (this.size().height - this.image.getHeight(null)) / 2 - (fontMetrics.getAscent() + fontMetrics.getDescent()) * countTokens / 2 + fontMetrics.getAscent();
                }
            }
        }
        else {
            if (this.left) {
                point.x = 0;
            }
            else if (this.image != null) {
                point.x = this.border + this.image.getWidth(null);
            }
            point.y = this.size().height / 2 - (fontMetrics.getAscent() + fontMetrics.getDescent()) * countTokens / 2 + fontMetrics.getAscent();
        }
        if (this.image == null) {
            point.y = this.size().height / 2 - (fontMetrics.getAscent() + fontMetrics.getDescent()) * countTokens / 2 + fontMetrics.getAscent();
        }
        while (stringTokenizer.hasMoreTokens()) {
            final String string = "  " + stringTokenizer.nextToken() + "  ";
            if (this.vertical || this.image == null) {
                if (this.AppParent.textalign == 0) {
                    point.x = (this.size().width - fontMetrics.stringWidth(string)) / 2;
                }
                if (this.AppParent.textalign == 1) {
                    point.x = 0;
                }
                if (this.AppParent.textalign == 2) {
                    point.x = this.size().width - fontMetrics.stringWidth(string);
                }
            }
            if (!this.vertical && this.image != null) {
                if (this.AppParent.textalign == 0) {
                    point.x = (this.size().width - fontMetrics.stringWidth(string) - this.image.getWidth(null)) / 2;
                }
                if (this.AppParent.textalign == 1) {
                    point.x = 0;
                }
                if (this.AppParent.textalign == 2) {
                    point.x = this.size().width - fontMetrics.stringWidth(string) - this.image.getWidth(null);
                }
                if (!this.left) {
                    final Point point2 = point;
                    point2.x += this.image.getWidth(null);
                }
            }
            graphics.drawString(string, point.x, point.y);
            final Point point3 = point;
            point3.y += fontMetrics.getHeight();
        }
    }
}
