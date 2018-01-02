import java.applet.AppletContext;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Font;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Item
{
    static final int ACT_DBCLICK = 1;
    static final int ACT_CLICK = 2;
    static final int ACT_HIGHLIGHT = 3;
    static final int DRAW_FULL = 1;
    static final int DRAW_HIGHLIGHT = 2;
    static final int DRAW_CLEAR = 4;
    static final int DRAW_MOUSEOVER = 8;
    String m_title;
    public Image m_icon;
    public Image m_highlightIcon;
    public Rectangle m_rect;
    public String m_status;
    public URL m_doc;
    public URL m_rightdoc;
    public String m_docTarget;
    public String m_rightdocTarget;
    public Font m_font;
    public static boolean m_showURL;
    
    public void initalise(final Applet applet) {
        int n = 4;
        int height = 10;
        if (this.m_icon != null) {
            int n2 = this.m_icon.getWidth(applet);
            int n3 = this.m_icon.getHeight(applet);
            if (this.m_highlightIcon != null) {
                if (n2 < this.m_highlightIcon.getWidth(applet)) {
                    n2 = this.m_highlightIcon.getWidth(applet);
                }
                if (n3 < this.m_highlightIcon.getHeight(applet)) {
                    n3 = this.m_highlightIcon.getHeight(applet);
                }
            }
            n += n2;
            height = n3;
        }
        FontMetrics fontMetrics;
        if (this.m_font != null) {
            fontMetrics = applet.getFontMetrics(this.m_font);
        }
        else {
            fontMetrics = applet.getFontMetrics(applet.getFont());
        }
        final int width = n + fontMetrics.stringWidth(this.m_title);
        if (height < fontMetrics.getHeight()) {
            height = fontMetrics.getHeight();
        }
        if (width > this.m_rect.width) {
            this.m_rect.width = width;
        }
        if (height > this.m_rect.height) {
            this.m_rect.height = height;
        }
    }
    
    public void draw(final Graphics graphics, final TreeApp treeApp, final int n) {
        final boolean b = (n & 0x2) > 0;
        final boolean b2 = (n & 0x8) > 0;
        int x = this.m_rect.x;
        final int y = this.m_rect.y;
        if (this.m_font != null && graphics.getFont() != this.m_font) {
            graphics.setFont(this.m_font);
        }
        if (b || (n & 0x4) > 0) {
            treeApp.clearRect(graphics, this.m_rect);
        }
        if (this.m_icon != null) {
            Image image;
            if (this.m_highlightIcon != null && b) {
                image = this.m_highlightIcon;
            }
            else {
                image = this.m_icon;
            }
            final int width = image.getWidth((ImageObserver)treeApp);
            final int height = image.getHeight((ImageObserver)treeApp);
            if (width > 0 && height > 0) {
                graphics.drawImage(image, x, y + (this.m_rect.height - height) / 2, (ImageObserver)treeApp);
                x += width;
            }
        }
        Color textColour = treeApp.m_textColour;
        if (b && this.m_title.length() != 0) {
            if (treeApp.m_highColour != null && !b2) {
                graphics.setColor(treeApp.m_highColour);
                graphics.fillRect(x, this.m_rect.y, this.m_rect.width - (x - this.m_rect.x), this.m_rect.height);
            }
            textColour = (b2 ? treeApp.m_mouseOverColour : treeApp.m_highTextColour);
        }
        graphics.setColor(textColour);
        final int y2 = this.m_rect.y;
        x += 2;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n2 = y2 + (this.m_rect.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        graphics.drawString(this.m_title, x, n2);
        if (b2) {
            n2 += 2;
            if (n2 >= this.m_rect.y + this.m_rect.height) {
                n2 = this.m_rect.y + this.m_rect.height - 1;
            }
            graphics.setColor(treeApp.m_underColour);
            graphics.drawLine(x, n2, x + fontMetrics.stringWidth(this.m_title), n2);
        }
    }
    
    public Item hitTest(final int n, final int n2) {
        if (this.m_rect.inside(n, n2)) {
            return this;
        }
        return null;
    }
    
    public boolean activate(final int n, final Event event, final Applet applet) {
        if (this.m_status != null && applet != null) {
            applet.showStatus(this.m_status);
        }
        if (n == 2 || n == 1) {
            URL url = this.m_doc;
            String s = this.m_docTarget;
            final boolean b = event != null && event.metaDown();
            if (b && this.m_rightdoc != null) {
                url = this.m_rightdoc;
            }
            if (b && this.m_rightdocTarget != null) {
                s = this.m_rightdocTarget;
            }
            if (url != null && applet != null) {
                final AppletContext appletContext = applet.getAppletContext();
                if (s != null) {
                    appletContext.showDocument(url, s);
                }
                else {
                    appletContext.showDocument(url);
                }
            }
        }
        return false;
    }
    
    static {
        Item.m_showURL = true;
    }
    
    public void copy(final Item item) {
        this.m_title = new String(item.getTitle());
        this.m_icon = item.m_icon;
        this.m_status = item.m_status;
        this.m_doc = item.m_doc;
        this.m_rightdoc = item.m_rightdoc;
        this.m_docTarget = item.m_docTarget;
        this.m_rightdocTarget = item.m_rightdocTarget;
        final Rectangle rect = item.getRect();
        this.m_rect = new Rectangle();
        this.m_rect.x = rect.x;
        this.m_rect.x = rect.y;
        this.m_rect.width = rect.width;
        this.m_rect.height = rect.height;
        this.m_highlightIcon = item.m_highlightIcon;
        this.m_font = item.m_font;
    }
    
    public String getTitle() {
        return this.m_title;
    }
    
    public void setTitle(final String title) {
        this.m_title = title;
    }
    
    public final void setStatus(final String s) {
        if (s != null) {
            this.m_status = new String(s);
        }
        if (this.m_doc != null && Item.m_showURL) {
            if (this.m_status == null) {
                this.m_status = new String();
            }
            else {
                this.m_status = this.m_status.concat(" ");
            }
            this.m_status = this.m_status.concat("(" + this.m_doc.getFile() + ")");
        }
    }
    
    public Rectangle layout(final int x, final int y) {
        this.m_rect.x = x;
        this.m_rect.y = y;
        return this.m_rect;
    }
    
    public Item(final String title) {
        this.m_title = title;
        this.m_rect = new Rectangle();
    }
    
    public Item(final Item item) {
        this.copy(item);
    }
    
    public Rectangle getRect() {
        return this.m_rect;
    }
}
