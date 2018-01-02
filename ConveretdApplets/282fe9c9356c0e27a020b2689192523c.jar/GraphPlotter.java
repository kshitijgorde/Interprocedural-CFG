import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class GraphPlotter
{
    public Rectangle gloc;
    public Axis axis;
    
    public GraphPlotter(final Rectangle gloc, final Axis axis) {
        this.gloc = gloc;
        this.axis = axis;
    }
    
    public void drawLine(final Graphics g, final LongPair p1, final LongPair p2, final boolean thick) {
        final LongPair rat1 = this.axis.locateRatio(p1);
        final LongPair rat2 = this.axis.locateRatio(p2);
        final int x1 = (int)(this.gloc.x + rat1.x * this.gloc.width);
        final int y1 = (int)(this.gloc.y + rat1.y * this.gloc.height);
        final int x2 = (int)(this.gloc.x + rat2.x * this.gloc.width);
        final int y2 = (int)(this.gloc.y + rat2.y * this.gloc.height);
        g.drawLine(x1, y1, x2, y2);
        if (thick) {
            g.drawLine(x1, y1 + 1, x2, y2 + 1);
        }
    }
    
    public void drawFilledRect(final Graphics g, final LongPair p1, final LongPair p2) {
        final LongPair rat1 = this.axis.locateRatio(p1);
        final LongPair rat2 = this.axis.locateRatio(p2);
        final int x1 = (int)(this.gloc.x + rat1.x * this.gloc.width);
        final int y1 = (int)(this.gloc.y + rat1.y * this.gloc.height);
        final int x2 = (int)(this.gloc.x + rat2.x * this.gloc.width);
        final int y2 = (int)(this.gloc.y + rat2.y * this.gloc.height);
        g.fillRect(x1, y1, x2 - x1, y2 - y1);
    }
    
    public Point getPoint(final LongPair p1) {
        final LongPair rat1 = this.axis.locateRatio(p1);
        final int x1 = (int)(this.gloc.x + rat1.x * this.gloc.width);
        final int y1 = (int)(this.gloc.y + rat1.y * this.gloc.height);
        return new Point(x1, y1);
    }
    
    public void drawAxis(final Graphics g, final Color xgrid, final Color ygrid, final Color fg, final Color bg, final Color gback) {
        g.setColor(gback);
        this.drawFilledRect(g, new LongPair(this.axis.x1, this.axis.y1), new LongPair(this.axis.x2, this.axis.y2));
        if (!ygrid.equals(bg)) {
            g.setColor(ygrid);
            for (int y = (int)this.axis.y1; y <= (int)this.axis.y2; ++y) {
                this.drawLine(g, new LongPair(this.axis.x1, y), new LongPair(this.axis.x2, y), false);
            }
        }
        if (!xgrid.equals(bg)) {
            g.setColor(xgrid);
            for (int x = (int)this.axis.x1; x <= (int)this.axis.x2; ++x) {
                this.drawLine(g, new LongPair(x, this.axis.y1), new LongPair(x, this.axis.y2), false);
            }
        }
        g.setColor(fg);
        this.drawLine(g, new LongPair(this.axis.x1, this.axis.y1), new LongPair(this.axis.x1, this.axis.y2), false);
        this.drawLine(g, new LongPair(this.axis.x2, this.axis.y1), new LongPair(this.axis.x2, this.axis.y2), false);
        this.drawLine(g, new LongPair(this.axis.x1, this.axis.y1), new LongPair(this.axis.x2, this.axis.y1), false);
        this.drawLine(g, new LongPair(this.axis.x1, this.axis.y2), new LongPair(this.axis.x2, this.axis.y2), false);
    }
}
