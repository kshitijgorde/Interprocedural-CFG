import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CoordinateSystem
{
    private double mx;
    private double bx;
    private double my;
    private double by;
    protected int xO1;
    protected int yO1;
    protected int xL1;
    protected int yL1;
    protected double xO2;
    protected double yO2;
    protected double xL2;
    protected double yL2;
    
    public void setOriginalRectangularPlane(final int x1, final int y1, final int x2, final int y2) {
        this.xO1 = x1;
        this.yO1 = y1;
        this.xL1 = x2;
        this.yL1 = y2;
    }
    
    public void setNewRectangularPlane(final double x1, final double y2, final double x2, final double y1) {
        this.xO2 = x1;
        this.yO2 = y1;
        this.xL2 = x2;
        this.yL2 = y2;
        this.computeXYTranslation();
    }
    
    public void computeXYTranslation() {
        if (this.xO2 == this.xL2) {
            --this.xO2;
            ++this.xL2;
        }
        this.mx = (this.xO1 - this.xL1) / (this.xO2 - this.xL2);
        this.bx = this.xO1 - this.mx * this.xO2;
        if (this.yO2 == this.yL2) {
            --this.yO2;
            ++this.yL2;
        }
        this.my = (this.yO1 - this.yL1) / (this.yO2 - this.yL2);
        this.by = this.yO1 - this.my * this.yO2;
    }
    
    public int plotX(final double x) {
        final int dx = (int)(this.mx * x + this.bx);
        return dx;
    }
    
    public int plotY(final double y) {
        final int dy = (int)(this.my * y + this.by);
        return dy;
    }
    
    public int getWidth(final double width) {
        return (int)(width * this.mx);
    }
    
    public int getHeight(final double height) {
        return (int)(height * -this.my);
    }
    
    public void drawLine(final Graphics g, double x1, double y1, double x2, double y2) {
        x1 = this.plotX(x1);
        y1 = this.plotY(y1);
        x2 = this.plotX(x2);
        y2 = this.plotY(y2);
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }
    
    public void drawBar(final Graphics g, final double x1, final double y1, final double width1, final double height1, final Color clr1, final Color clr2) {
        int width2 = this.getWidth(width1);
        int height2 = this.getHeight(height1);
        if (width2 <= 0) {
            width2 = 1;
        }
        if (height2 <= 0) {
            height2 = 1;
        }
        final int x2 = this.plotX(x1);
        final int y2 = this.plotY(y1) - height2;
        if (clr2 != null) {
            g.setColor(clr2);
            g.fillRect(x2, y2, width2, height2);
        }
        g.setColor(clr1);
        g.drawRect(x2, y2, width2, height2);
    }
    
    public void drawRect(final Graphics g, final double x1, final double y1, final double width1, final double height1, final Color clr) {
        this.drawBar(g, x1, y1, width1, height1, clr, null);
    }
    
    public void drawDot(final Graphics g, final double x1, final double y1, final Color clr) {
        this.drawLine(g, x1, y1, x1, y1);
    }
}
