// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.Enumeration;
import java.awt.Event;
import java.awt.Rectangle;
import lotus.notes.util.Bidi;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

public class huLabel extends Canvas
{
    static final boolean DEBUG = false;
    public static final int LEFT = 0;
    public static final int CENTER = 2;
    public static final int RIGHT = 1;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;
    private static final int MIN_BULLET_GAP = 0;
    static final int BRIGHTEN = 1;
    private boolean i_peerLess;
    private String i_text;
    private Image i_icon;
    private Image i_bullet;
    private int i_bulletSide;
    private Color i_fgColor;
    private Color i_bgColor;
    private Font i_font;
    private Image i_bgImage;
    private boolean i_mouseOver;
    private Color i_rollOverColor;
    private Image i_rollOverImg;
    private Font i_rollOverFont;
    private int i_rollOverImgEffect;
    private boolean i_autoRollOverImg;
    private boolean i_selected;
    private Color i_selectedColor;
    private Image i_selectedImg;
    private Font i_selectedFont;
    private int i_readingOrder;
    private int i_direction;
    private int i_hAlign;
    private int i_vAlign;
    private Insets i_insets;
    private boolean i_textWrapping;
    private boolean i_rollOverEnabled;
    private boolean i_ellipsisEnabled;
    private Object i_argContext;
    private Vector listeners;
    private Dimension dim;
    int mouseDownX;
    int mouseDownY;
    
    public huLabel() {
        this((String)null);
    }
    
    public huLabel(final String text) {
        this.i_peerLess = false;
        this.i_bulletSide = 1;
        this.i_readingOrder = 0;
        this.i_direction = 0;
        this.i_hAlign = 0;
        this.i_vAlign = 2;
        this.listeners = new Vector();
        this.dim = new Dimension(0, 0);
        this.mouseDownX = -1;
        this.mouseDownY = -1;
        this.setForeground(Color.black);
        this.i_rollOverColor = Color.blue;
        this.i_selectedColor = Color.red;
        this.i_fgColor = Color.black;
        this.i_font = new Font("Helvetica", 0, 14);
        this.i_rollOverFont = this.i_font;
        this.i_selectedFont = new Font(this.i_font.getName(), 1, 14);
        this.i_rollOverEnabled = false;
        this.i_ellipsisEnabled = false;
        this.i_insets = new Insets(4, 4, 4, 4);
        this.setText(text);
    }
    
    public synchronized void addNotify() {
        if (!this.i_peerLess) {
            super.addNotify();
        }
    }
    
    public void repaint() {
        if (!this.i_peerLess) {
            super.repaint();
        }
        else {
            this.getParent().repaint();
        }
    }
    
    public void setPeerLess(final boolean i_peerLess) {
        this.i_peerLess = i_peerLess;
    }
    
    public void setText(final String i_text) {
        this.i_text = i_text;
    }
    
    public String getText() {
        return this.i_text;
    }
    
    public void setTextWrapping(final boolean i_textWrapping) {
        this.i_textWrapping = i_textWrapping;
    }
    
    public void setAutoRollOverImg(final boolean i_autoRollOverImg) {
        this.i_autoRollOverImg = i_autoRollOverImg;
    }
    
    public void setRollOverEnabled(final boolean i_rollOverEnabled) {
        this.i_rollOverEnabled = i_rollOverEnabled;
    }
    
    public boolean isRollOverEnabled() {
        return this.i_rollOverEnabled;
    }
    
    public void setIcon(final Image i_icon) {
        this.i_icon = i_icon;
        if (this.i_autoRollOverImg && this.i_rollOverImg == null) {
            if (this.i_rollOverImgEffect == 1) {
                this.i_rollOverImg = this.brightnessFilter(this.i_icon, 120);
            }
            else {
                this.i_rollOverImg = this.i_icon;
            }
        }
    }
    
    public Image getIcon() {
        return this.i_icon;
    }
    
    public void setBullet(final Image i_bullet) {
        this.i_bullet = i_bullet;
    }
    
    public void setBulletSide(final int i_bulletSide) {
        this.i_bulletSide = i_bulletSide;
    }
    
    public void setInsets(final Insets i_insets) {
        this.i_insets = i_insets;
    }
    
    public void setSelected(final boolean i_selected) {
        this.i_selected = i_selected;
    }
    
    public void setFont(final Font i_font) {
        this.i_font = null;
        this.i_font = i_font;
        this.i_rollOverFont = this.i_font;
        this.i_selectedFont = new Font(this.i_font.getName(), 1, 14);
    }
    
    public void setFgColor(final Color i_fgColor) {
        this.i_fgColor = i_fgColor;
    }
    
    public Color getFgColor() {
        return this.i_fgColor;
    }
    
    public void setBgColor(final Color i_bgColor) {
        this.i_bgColor = i_bgColor;
    }
    
    public Color getBgColor() {
        return this.i_bgColor;
    }
    
    public void setRollOverFont(final Font i_rollOverFont) {
        this.i_rollOverFont = null;
        this.i_rollOverFont = i_rollOverFont;
    }
    
    public void setSelectedFont(final Font i_selectedFont) {
        this.i_selectedFont = null;
        this.i_selectedFont = i_selectedFont;
    }
    
    public void setSelectedImage(final Image i_selectedImg) {
        this.i_selectedImg = i_selectedImg;
    }
    
    public void setValign(final int i_vAlign) {
        this.i_vAlign = i_vAlign;
    }
    
    public void setHalign(final int i_hAlign) {
        this.i_hAlign = i_hAlign;
    }
    
    public void setSelectedColor(final Color i_selectedColor) {
        this.i_selectedColor = i_selectedColor;
    }
    
    public void setRollOverColor(final Color i_rollOverColor) {
        this.i_rollOverColor = i_rollOverColor;
    }
    
    public void setRollOverImage(final Image i_rollOverImg) {
        this.i_rollOverImg = i_rollOverImg;
    }
    
    public void setRollOverImgEffect(final int i_rollOverImgEffect) {
        this.i_rollOverImgEffect = i_rollOverImgEffect;
    }
    
    public void setArgContext(final Object i_argContext) {
        this.i_argContext = i_argContext;
    }
    
    public Object getArgContext() {
        return this.i_argContext;
    }
    
    public void setBgImage(final Image i_bgImage) {
        this.i_bgImage = i_bgImage;
    }
    
    public void seEllipsisEnabled(final boolean i_ellipsisEnabled) {
        this.i_ellipsisEnabled = i_ellipsisEnabled;
    }
    
    public boolean isEllipsisEnabled() {
        return this.i_ellipsisEnabled;
    }
    
    public void setReadingOrder(final int i_readingOrder) {
        this.i_readingOrder = i_readingOrder;
    }
    
    public void setDirection(final int i_direction) {
        this.i_direction = i_direction;
    }
    
    public Dimension minimumSize() {
        this.dim.width = 0;
        this.dim.height = 0;
        if (this.i_text != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.i_font);
            this.dim.height = fontMetrics.getHeight();
            this.dim.width = fontMetrics.stringWidth(this.i_text);
            if (this.i_rollOverFont != null && this.i_rollOverEnabled) {
                final FontMetrics fontMetrics2 = this.getFontMetrics(this.i_rollOverFont);
                this.dim.height = Math.max(this.dim.height, fontMetrics2.getHeight());
                this.dim.width = Math.max(this.dim.width, fontMetrics2.stringWidth(this.i_text));
            }
            if (this.i_selectedFont != null) {
                final FontMetrics fontMetrics3 = this.getFontMetrics(this.i_selectedFont);
                this.dim.height = Math.max(this.dim.height, fontMetrics3.getHeight());
                this.dim.width = Math.max(this.dim.width, fontMetrics3.stringWidth(this.i_text));
            }
        }
        if (this.i_icon != null) {
            final Dimension dim = this.dim;
            dim.width += this.i_icon.getWidth(null);
            final Dimension dim2 = this.dim;
            dim2.height += this.i_icon.getHeight(null);
        }
        if (this.i_bullet != null) {
            this.dim.height = Math.max(this.dim.height, this.i_bullet.getHeight(null));
            final Dimension dim3 = this.dim;
            dim3.width += this.i_bullet.getWidth(null) + 0;
        }
        final Dimension dim4 = this.dim;
        dim4.width += this.i_insets.top + this.i_insets.bottom;
        final Dimension dim5 = this.dim;
        dim5.height += this.i_insets.left + this.i_insets.right;
        return this.dim;
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, bounds.width, bounds.height);
        Image image = this.i_bgImage;
        if (this.i_mouseOver && this.i_rollOverImg != null) {
            image = this.i_rollOverImg;
        }
        if (this.i_selected && this.i_selectedImg != null) {
            image = this.i_selectedImg;
        }
        if (image != null) {
            graphics.drawImage(image, (bounds.width - image.getWidth(null)) / 2, (bounds.height - image.getHeight(null)) / 2, null);
        }
        int width = 0;
        if (this.i_bullet != null) {
            int left;
            if (this.i_bulletSide == 0) {
                left = this.i_insets.left;
            }
            else {
                left = bounds.width - this.i_bullet.getWidth(null) - this.i_insets.right;
            }
            graphics.drawImage(this.i_bullet, left, bounds.height / 2 - this.i_bullet.getHeight(null) / 2, null);
            bounds.width -= this.i_bullet.getWidth(null);
            if (this.i_bulletSide == 0) {
                width = this.i_bullet.getWidth(null);
            }
        }
        if (this.i_icon != null) {
            final Image i_icon = this.i_icon;
            final int n = bounds.height - this.i_insets.bottom - i_icon.getHeight(null);
            final int n2 = bounds.height / 2 - i_icon.getHeight(null) / 2;
            final int top = this.i_insets.top;
            int n3 = n2;
            if (this.i_vAlign == 2) {
                n3 = n2;
            }
            else if (this.i_vAlign == 3) {
                n3 = top;
            }
            else if (this.i_vAlign == 4) {
                n3 = n;
            }
            final int n4 = bounds.width - this.i_insets.right - i_icon.getWidth(null);
            final int left2 = this.i_insets.left;
            int n5 = bounds.width / 2 - i_icon.getWidth(null) / 2;
            if (this.i_hAlign == 2) {
                n5 = n5;
            }
            else if ((this.i_hAlign == 0 && this.i_readingOrder == 0) || (this.i_hAlign == 1 && this.i_readingOrder == 1)) {
                n5 = left2;
            }
            else if ((this.i_hAlign == 1 && this.i_readingOrder == 0) || (this.i_hAlign == 0 && this.i_readingOrder == 1)) {
                n5 = n4;
            }
            final int n6 = n5 + width;
            if (this.i_readingOrder == 0) {
                graphics.drawImage(i_icon, n6, n3, null);
            }
            else {
                graphics.drawImage(i_icon, Bidi.toggleHorzPos(n6, i_icon.getWidth(null), this.size().width), n3, null);
            }
        }
        if (this.i_text != null) {
            final String i_text = new String(this.i_text);
            this.i_text = ((this.i_readingOrder == 0) ? this.i_text : Bidi.BidiString(this.i_text));
            graphics.setFont(this.i_font);
            final FontMetrics fontMetrics = graphics.getFontMetrics(this.i_font);
            int n7 = this.i_insets.top;
            if (this.i_vAlign == 2) {
                n7 = bounds.height / 2 + fontMetrics.getAscent() / 2;
            }
            else if (this.i_vAlign == 3) {
                n7 = fontMetrics.getAscent() + this.i_insets.top;
            }
            else if (this.i_vAlign == 4) {
                n7 = bounds.height - this.i_insets.bottom - fontMetrics.getDescent();
            }
            if (n7 < this.i_insets.top) {
                n7 = this.i_insets.top;
            }
            final int stringWidth = fontMetrics.stringWidth(this.i_text);
            int n8 = this.i_insets.left;
            if (this.i_hAlign == 2) {
                n8 = bounds.width / 2 - stringWidth / 2;
            }
            else if (this.i_hAlign == 0) {
                n8 = this.i_insets.left;
            }
            else if (this.i_hAlign == 1) {
                n8 = bounds.width - this.i_insets.right - stringWidth;
            }
            if (n8 < this.i_insets.left) {
                n8 = this.i_insets.left;
            }
            final int n9 = n8 + width;
            if (this.i_selected) {
                graphics.setColor(this.i_selectedColor);
                graphics.setFont(this.i_selectedFont);
            }
            else if (this.i_mouseOver) {
                graphics.setColor(this.i_rollOverColor);
                graphics.setFont(this.i_rollOverFont);
            }
            else {
                graphics.setColor(this.i_fgColor);
            }
            if (stringWidth > bounds.width - this.i_insets.right - this.i_insets.left) {
                Graphics graphics2 = null;
                final Rectangle rectangle = new Rectangle(width + this.i_insets.left, 0, bounds.width - this.i_insets.right - this.i_insets.left, bounds.height);
                if (!this.i_textWrapping) {
                    if (this.i_ellipsisEnabled) {
                        final Rectangle rectangle2 = rectangle;
                        rectangle2.width -= fontMetrics.stringWidth("...") + 4;
                    }
                    if (this.i_readingOrder == 0) {
                        graphics2 = graphics.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                        graphics2.drawString(this.i_text, 0, n7);
                    }
                    else {
                        graphics2 = graphics.create(Bidi.toggleHorzPos(0, rectangle.width, rectangle.x + bounds.width - this.i_insets.right - this.i_insets.left), rectangle.y, rectangle.width, rectangle.height);
                        graphics2.drawString(this.i_text, Bidi.toggleHorzPos(0, stringWidth, rectangle.width), n7);
                    }
                    if (this.i_ellipsisEnabled) {
                        if (this.i_readingOrder == 0) {
                            graphics.drawString("...", rectangle.x + rectangle.width, n7);
                        }
                        else {
                            graphics.drawString("...", rectangle.x, n7);
                        }
                    }
                }
                graphics2.dispose();
            }
            else {
                graphics.drawString(this.i_text, n9, n7);
            }
            this.i_text = i_text;
        }
    }
    
    public boolean mouseDown(final Event event, final int mouseDownX, final int mouseDownY) {
        this.mouseDownX = mouseDownX;
        this.mouseDownY = mouseDownY;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.mouseDownX == n && this.mouseDownY == n2) {
            this.fireStateChangeEvent();
            this.mouseDownX = -1;
            this.mouseDownY = -1;
            return true;
        }
        return false;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.i_rollOverEnabled) {
            this.i_mouseOver = true;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.i_mouseOver = false;
        this.repaint();
        return true;
    }
    
    public void addStateChangeListener(final StateChangeListener stateChangeListener) {
        if (!this.listeners.contains(stateChangeListener)) {
            this.listeners.addElement(stateChangeListener);
        }
    }
    
    public void removeStateChangeListener(final StateChangeListener stateChangeListener) {
        this.listeners.removeElement(stateChangeListener);
    }
    
    private void fireStateChangeEvent() {
        final StateChangeEvent stateChangeEvent = new StateChangeEvent(this);
        final Enumeration<StateChangeListener> elements = (Enumeration<StateChangeListener>)this.listeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().stateChanged(stateChangeEvent);
        }
    }
    
    public synchronized boolean inside(final int n, final int n2) {
        return n >= 0 && n < this.bounds().width && n2 >= 0 && n2 < this.bounds().height;
    }
    
    public Image brightnessFilter(final Image image, final int n) {
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < array.length; ++i) {
            final int n2 = array[i];
            array[i] = (n2 & 0xFF000000) + (Math.min(255, ((n2 & 0xFF0000) >> 16) * n / 100) << 16) + (Math.min(255, ((n2 & 0xFF00) >> 8) * n / 100) << 8) + Math.min(255, (n2 & 0xFF) * n / 100);
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
}
