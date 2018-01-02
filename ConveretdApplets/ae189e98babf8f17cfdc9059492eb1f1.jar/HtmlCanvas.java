import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class HtmlCanvas extends Canvas
{
    private HtmlDocument document;
    private int start;
    private int width;
    private int[] heights;
    private Href[] hrefs;
    private Hashtable names;
    private Image image;
    private HtmlImage[] imgs;
    
    public HtmlCanvas() {
        this.width = 300;
        this.document = null;
    }
    
    public HtmlCanvas(final HtmlDocument htmlDocument) {
        this.width = 300;
        this.changeDocument(htmlDocument);
    }
    
    public void changeDocument(final HtmlDocument document) {
        document.draw(new HtmlPager(this, this.width));
        this.document = document;
        this.start = 0;
    }
    
    public URL getHref(final int n, final int n2) {
        if (this.hrefs == null) {
            return null;
        }
        final int line = this.lineAt(n2 + this.start);
        for (int i = 0; i < this.hrefs.length; ++i) {
            final Href href = this.hrefs[i];
            if ((line != href.startLine || n >= href.startOffset) && (line != href.endLine || n < href.endOffset) && line >= href.startLine && line <= href.endLine) {
                return href.url;
            }
        }
        return null;
    }
    
    public int setStart(final String s) {
        if (this.names == null || s == null) {
            this.start = 0;
        }
        else {
            final Integer n = this.names.get(s);
            if (n == null) {
                this.start = 0;
            }
            else {
                this.start = this.heights[n - 1];
            }
        }
        this.repaint();
        return this.start;
    }
    
    public void setStart(final int start) {
        this.start = start;
        this.repaint();
    }
    
    public int setWidth(final int n) {
        if (this.document == null) {
            this.width = n;
            return 0;
        }
        if (n != this.width) {
            this.width = n;
            this.document.draw(new HtmlPager(this, n));
        }
        return this.heights[this.heights.length - 1];
    }
    
    private String statusString(final int n) {
        String s = "";
        if ((n & 0x80) != 0x0) {
            s = String.valueOf(s) + "ABORT ";
        }
        if ((n & 0x20) != 0x0) {
            s = String.valueOf(s) + "ALLBITS ";
        }
        if ((n & 0x40) != 0x0) {
            s = String.valueOf(s) + "ERROR ";
        }
        if ((n & 0x10) != 0x0) {
            s = String.valueOf(s) + "FRAMEBITS ";
        }
        if ((n & 0x2) != 0x0) {
            s = String.valueOf(s) + "HEIGHT ";
        }
        if ((n & 0x4) != 0x0) {
            s = String.valueOf(s) + "PROPERTIES ";
        }
        if ((n & 0x8) != 0x0) {
            s = String.valueOf(s) + "SOMEBITS ";
        }
        if ((n & 0x1) != 0x0) {
            s = String.valueOf(s) + "WIDTH ";
        }
        return String.valueOf(s) + n;
    }
    
    public synchronized void paint(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        if (this.document == null) {
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            return;
        }
        if (this.image == null) {
            this.document.draw(new HtmlPager(this, this.width));
        }
        final Graphics graphics2 = this.image.getGraphics();
        for (int i = 0; i < this.imgs.length; ++i) {
            graphics2.drawImage(this.imgs[i].img, this.imgs[i].x, this.imgs[i].y, this.imgs[i].w, this.imgs[i].h, this);
        }
        graphics.drawImage(this.image, 0, -this.start, this);
        final int width = this.image.getWidth(null);
        if (width < this.size().width) {
            graphics.fillRect(width, 0, this.size().width - width, this.size().height);
        }
        final int n = this.image.getHeight(null) - this.start;
        if (n < this.size().height) {
            graphics.fillRect(0, n, this.size().width, this.size().height - n);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    protected synchronized void setData(final int[] heights, final Href[] hrefs, final Hashtable names, final HtmlImage[] imgs, final Image image) {
        this.heights = heights;
        this.hrefs = hrefs;
        this.names = names;
        this.imgs = imgs;
        this.image = image;
    }
    
    private int lineAt(final int n) {
        for (int i = 0; i < this.heights.length - 1; ++i) {
            if (n >= this.heights[i] && n < this.heights[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}
