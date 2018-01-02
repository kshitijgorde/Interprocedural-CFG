import java.awt.Graphics;
import java.awt.FontMetrics;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class HtmlPagerLine
{
    private Vector offsets;
    private Vector items;
    private int count;
    private int height;
    private int ascent;
    private int descent;
    
    protected HtmlPagerLine() {
        this.offsets = new Vector();
        this.items = new Vector();
    }
    
    protected int getHeight() {
        if (this.ascent + this.descent < this.height) {
            return this.height;
        }
        return this.ascent + this.descent;
    }
    
    protected void addItem(final FontMetrics fontMetrics, final int n, final HtmlPagerItem htmlPagerItem) {
        this.offsets.addElement(new Integer(n));
        this.items.addElement(htmlPagerItem);
        ++this.count;
        final int height = htmlPagerItem.getHeight(fontMetrics);
        if (height > this.height) {
            this.height = height;
        }
        final int ascent = htmlPagerItem.getAscent(fontMetrics);
        if (ascent >= 0) {
            if (ascent > this.ascent) {
                this.ascent = ascent;
            }
            if (height - ascent > this.descent) {
                this.descent = height - ascent;
            }
        }
    }
    
    protected void translate(final int n) {
        for (int i = 0; i < this.count; ++i) {
            this.offsets.setElementAt(new Integer((int)this.offsets.elementAt(i) + n), i);
        }
    }
    
    protected void draw(final Graphics graphics, final int n, final Vector vector) {
        for (int i = 0; i < this.count; ++i) {
            ((HtmlPagerItem)this.items.elementAt(i)).draw(graphics, (int)this.offsets.elementAt(i), n + this.ascent, this.ascent, vector);
        }
    }
}
