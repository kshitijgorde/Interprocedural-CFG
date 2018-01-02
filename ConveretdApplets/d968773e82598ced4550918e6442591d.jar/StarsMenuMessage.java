import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.net.URL;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class StarsMenuMessage
{
    public String message;
    public String status;
    public Color color;
    public URL url;
    public int sizes;
    public int maxSize;
    public int[] x;
    public int[] y;
    public Font defaultFont;
    public Font[] font;
    public boolean bound;
    
    public StarsMenuMessage(final String message, final Color color, final URL url) {
        this.status = "";
        this.sizes = 60;
        this.maxSize = 600;
        this.defaultFont = new Font("Helvetica", 0, 10);
        this.bound = false;
        this.message = message;
        this.color = color;
        this.url = url;
    }
    
    public StarsMenuMessage(final String s, final Color color) {
        this(s, color, null);
    }
    
    public StarsMenuMessage(final String s) {
        this(s, Color.white, null);
    }
    
    public void init(final Point point, final Graphics graphics) {
        final String name = this.defaultFont.getName();
        final int style = this.defaultFont.getStyle();
        int n = this.maxSize / this.sizes;
        final int n2 = this.sizes / 2;
        int maxSize = 0;
        this.x = new int[this.sizes];
        this.y = new int[this.sizes];
        this.font = new Font[this.sizes];
        for (int i = 0; i < this.sizes; ++i) {
            if (!this.bound) {
                int n3 = (i < n2) ? (n / 2) : (n * 2);
                if (n3 < 1) {
                    n3 = 1;
                }
                maxSize += n3;
            }
            if (this.bound) {
                if (n < 1) {
                    n = 1;
                }
                maxSize += n * (i + 1);
                if (maxSize > this.maxSize) {
                    maxSize = this.maxSize;
                    this.x[i] = this.x[i - 1];
                    this.y[i] = this.y[i - 1];
                }
            }
            graphics.setFont(this.font[i] = new Font(name, style, maxSize));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int stringWidth = fontMetrics.stringWidth(this.message);
            final int maxAscent = fontMetrics.getMaxAscent();
            this.x[i] = point.x - stringWidth / 2;
            this.y[i] = point.y + maxAscent / 2;
        }
    }
    
    public void paint(final Graphics graphics, final int n) {
        graphics.setFont(this.font[n]);
        graphics.setColor(this.color);
        graphics.drawString(this.message, this.x[n], this.y[n]);
    }
    
    public void setSizes(final int sizes) {
        this.sizes = sizes;
    }
    
    public void setMaxSize(final int maxSize) {
        this.maxSize = maxSize;
    }
    
    public void setFont(final Font defaultFont) {
        this.defaultFont = defaultFont;
    }
    
    public void setBound(final Dimension dimension, final Graphics graphics) {
        final String name = this.defaultFont.getName();
        final int style = this.defaultFont.getStyle();
        this.bound = true;
        int n = (dimension.width < dimension.height) ? dimension.width : dimension.height;
        n += 0;
        for (int i = 1; i < n; ++i) {
            graphics.setFont(new Font(name, style, i));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int stringWidth = fontMetrics.stringWidth(this.message);
            final int height = fontMetrics.getHeight();
            if (stringWidth >= n || height >= n) {
                this.maxSize = i;
                return;
            }
        }
    }
}
