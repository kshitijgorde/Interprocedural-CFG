import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class PlotCanvas extends Canvas
{
    SimplePlot containedBy;
    public int mousex;
    public int mousey;
    public boolean isMouse;
    int timeThrough;
    Color color;
    int inset;
    int fn;
    float ymax;
    float ymin;
    float xmax;
    float xmin;
    
    PlotCanvas(final SimplePlot containedBy) {
        this.isMouse = false;
        this.color = Color.green;
        this.inset = 20;
        this.containedBy = containedBy;
        this.xmin = -100.0f;
        this.xmax = 100.0f;
    }
    
    public void changeColor(final Color color) {
        this.color = color;
    }
    
    public void setFn(final int fn) {
        this.fn = fn;
    }
    
    public void setXmin(final float xmin) {
        this.xmin = xmin;
    }
    
    public float getXmin() {
        return this.xmin;
    }
    
    public void setXmax(final float xmax) {
        this.xmax = xmax;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.setColor(this.color);
        this.ymax = 0.0f;
        this.ymin = 0.0f;
        for (float n = (this.xmax - this.xmin) / 50.0f, xmin = this.xmin; xmin <= this.xmax; xmin += n) {
            final float func = this.func(xmin);
            if (func > this.ymax) {
                this.ymax = func;
            }
            if (func < this.ymin) {
                this.ymin = func;
            }
        }
        final PlotCoord plotCoord = new PlotCoord(this.xmax, this.ymax, this.xmin, this.ymin, size.width, size.height, this.inset);
        graphics.setColor(Color.black);
        final float xmax = this.xmax;
        final float xmin2 = this.xmin;
        final float ymax = this.ymax;
        final float ymin = this.ymin;
        float xmin3;
        if (this.xmin < 0.0f && this.xmax > 0.0f) {
            xmin3 = 0.0f;
        }
        else {
            xmin3 = this.xmin;
        }
        float ymin2;
        if (this.ymin < 0.0f && this.ymax > 0.0f) {
            ymin2 = 0.0f;
        }
        else {
            ymin2 = this.ymin;
        }
        graphics.setFont(new Font("Dialog", 0, 10));
        graphics.drawLine(plotCoord.xcoord(xmax), plotCoord.ycoord(ymin2), plotCoord.xcoord(xmin2), plotCoord.ycoord(ymin2));
        graphics.drawLine(plotCoord.xcoord(xmin3), plotCoord.ycoord(ymax), plotCoord.xcoord(xmin3), plotCoord.ycoord(ymin));
        final int ycoord = plotCoord.ycoord(this.ymin + (this.ymax - this.ymin) / 2.0f);
        final int xcoord = plotCoord.xcoord(xmin3);
        graphics.drawLine(xcoord + 3, ycoord, xcoord - 3, ycoord);
        graphics.drawString(new Integer(Math.round(this.ymin + (this.ymax - this.ymin) / 2.0f)).toString(), xcoord + 5, ycoord + 10);
        final int xcoord2 = plotCoord.xcoord(this.xmin + (this.xmax - this.xmin) / 2.0f);
        final int ycoord2 = plotCoord.ycoord(ymin2);
        graphics.drawLine(xcoord2, ycoord2 + 3, xcoord2, ycoord2 - 3);
        graphics.drawString(new Integer(Math.round(this.xmin + (this.xmax - this.xmin) / 2.0f)).toString(), xcoord2 - 5, ycoord2 + 15);
        if (this.xmin < 0.0f && this.xmax > 0.0f) {
            final int xcoord3 = plotCoord.xcoord(0.0f);
            final int ycoord3 = plotCoord.ycoord(ymin2);
            graphics.drawLine(xcoord3, ycoord3 + 3, xcoord3, ycoord3 - 3);
            graphics.drawString("0", xcoord3 - 5, ycoord3 + 15);
        }
        if (this.ymin < 0.0f && this.ymax > 0.0f) {
            final int ycoord4 = plotCoord.ycoord(0.0f);
            final int xcoord4 = plotCoord.xcoord(xmin3);
            graphics.drawLine(xcoord4 + 3, ycoord4, xcoord4 - 3, ycoord4);
            graphics.drawString("0", xcoord4 + 7, ycoord4 + 10);
        }
        final int ycoord5 = plotCoord.ycoord(this.ymax);
        final int xcoord5 = plotCoord.xcoord(xmin3);
        graphics.drawLine(xcoord5 + 3, ycoord5, xcoord5 - 3, ycoord5);
        graphics.drawString(new Integer(Math.round(this.ymax)).toString(), xcoord5 + 5, ycoord5 + 10);
        final int xcoord6 = plotCoord.xcoord(this.xmax);
        final int ycoord6 = plotCoord.ycoord(ymin2);
        graphics.drawLine(xcoord6, ycoord6 + 3, xcoord6, ycoord6 - 3);
        graphics.drawString(new Integer(Math.round(this.xmax)).toString(), xcoord6 - 5, ycoord6 + 15);
        graphics.setColor(this.color);
        int n2 = 0;
        int n3 = 0;
        for (float n4 = (this.xmax - this.xmin) / 50.0f, xmin4 = this.xmin; xmin4 <= this.xmax; xmin4 += n4) {
            final float func2 = this.func(xmin4);
            final int xcoord7 = plotCoord.xcoord(xmin4);
            final int ycoord7 = plotCoord.ycoord(func2);
            if (xmin4 != this.xmin) {
                graphics.drawLine(n2, n3, xcoord7, ycoord7);
            }
            n2 = xcoord7;
            n3 = ycoord7;
        }
    }
    
    float func(final float n) {
        float n2 = 0.0f;
        switch (this.fn) {
            case 0: {
                n2 = n * n;
                break;
            }
            case 1: {
                n2 = n + 30.0f;
                break;
            }
            case 2: {
                n2 = n * n * n;
                break;
            }
            case 3: {
                n2 = n * n + n - 52.0f;
                break;
            }
            default: {
                System.out.println("Bad function number");
                n2 = -1.0f;
                break;
            }
        }
        return n2;
    }
}
