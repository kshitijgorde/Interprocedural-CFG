import java.util.Vector;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class HtmlPagerItem
{
    protected static final int TOP = 1;
    protected static final int MIDDLE = 0;
    protected static final int BOTTOM = -1;
    private Color color;
    private int length;
    private HtmlImage image;
    private int align;
    private Font font;
    private String text;
    
    protected HtmlPagerItem(final Color color, final int length) {
        this.color = color;
        this.length = length;
    }
    
    protected HtmlPagerItem(final Color color, final HtmlImage image, final int align) {
        this.color = color;
        this.image = image;
        this.align = align;
    }
    
    protected HtmlPagerItem(final Color color, final Font font, final String text) {
        this.color = color;
        this.font = font;
        this.text = text;
    }
    
    protected int getAscent(final FontMetrics fontMetrics) {
        if (this.font != null) {
            return fontMetrics.getMaxAscent();
        }
        if (this.image == null) {
            return 7;
        }
        int h = this.image.h;
        if (this.color != null) {
            h += 2;
        }
        switch (this.align) {
            case 1: {
                return -1;
            }
            case 0: {
                return h / 2;
            }
            case -1: {
                return h;
            }
            default: {
                return 0;
            }
        }
    }
    
    protected int getHeight(final FontMetrics fontMetrics) {
        if (this.font != null) {
            return fontMetrics.getHeight();
        }
        if (this.image == null) {
            return 14;
        }
        if (this.color != null) {
            return this.image.h + 2;
        }
        return this.image.h;
    }
    
    protected void draw(final Graphics graphics, int x, int y, final int n, final Vector vector) {
        graphics.setColor(this.color);
        if (this.font != null) {
            graphics.setFont(this.font);
            graphics.drawString(this.text, x, y);
            return;
        }
        if (this.image != null) {
            int h = this.image.h;
            if (this.color != null) {
                h += 2;
            }
            switch (this.align) {
                case 1: {
                    y -= n;
                    break;
                }
                case 0: {
                    y -= h / 2;
                    break;
                }
                case -1: {
                    y -= h;
                    break;
                }
                default: {
                    return;
                }
            }
            if (this.color != null) {
                graphics.drawRect(x, y, this.image.w + 2, h);
                ++x;
                ++y;
            }
            this.image.x = x;
            this.image.y = y;
            vector.addElement(this.image);
            return;
        }
        graphics.drawLine(x, y, x + this.length, y);
    }
}
