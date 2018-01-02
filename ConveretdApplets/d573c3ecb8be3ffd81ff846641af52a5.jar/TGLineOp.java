import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class TGLineOp implements ImageObserver, TGGraphicsOp
{
    private Color color;
    private int width;
    int[] xPoints;
    int[] yPoints;
    private TGPoint p1;
    private TGPoint p2;
    
    public TGLineOp(final TGPoint p4, final TGPoint p5, final Color color, final int width) {
        this.p1 = p4;
        this.p2 = p5;
        this.color = color;
        this.width = width;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        System.out.println("TGLineOp.imageUpdate: got here!");
        return true;
    }
    
    private Rectangle drawHorizontalFatLine(final Graphics graphics, final int n, final int n2) {
        final float n3 = this.width / 2.0f;
        this.xPoints[0] = (this.xPoints[3] = this.p1.canvasX(n2));
        this.xPoints[1] = (this.xPoints[2] = this.p2.canvasX(n2));
        this.yPoints[0] = this.p1.canvasY(n3, n);
        this.yPoints[1] = this.p2.canvasY(n3, n);
        this.yPoints[2] = this.p2.canvasY(-n3, n);
        this.yPoints[3] = this.p1.canvasY(-n3, n);
        final int min = this.min(this.xPoints);
        final int n4 = Math.abs(min - this.max(this.xPoints)) + 1;
        final int min2 = this.min(this.yPoints);
        final int n5 = Math.abs(min2 - this.max(this.yPoints)) + 1;
        graphics.setClip(min, min2, n4, n5);
        graphics.fillPolygon(this.xPoints, this.yPoints, 4);
        return new Rectangle(min, min2, n4, n5);
    }
    
    private Rectangle drawFatLine(final Graphics graphics, final int n, final int n2) {
        if (this.xPoints == null) {
            this.xPoints = new int[4];
            this.yPoints = new int[4];
        }
        final double n3 = this.p2.xFloatValue() - this.p1.xFloatValue();
        if (Math.abs(n3) < 0.5 / n2) {
            return this.drawVerticalFatLine(graphics, n, n2);
        }
        final double n4 = this.p2.yFloatValue() - this.p1.yFloatValue();
        if (Math.abs(n4) < 0.5 / n) {
            return this.drawHorizontalFatLine(graphics, n, n2);
        }
        final double atan = Math.atan(n4 / n3);
        final double n5 = this.width / 2.0;
        double n6 = atan + 1.5707963267948966;
        if (n6 > 6.283185307179586) {
            n6 -= 6.283185307179586;
        }
        final TGPoint otherEndPoint = this.p1.otherEndPoint(n6, n5);
        this.xPoints[0] = otherEndPoint.canvasX(n2);
        this.yPoints[0] = otherEndPoint.canvasY(n);
        final TGPoint otherEndPoint2 = this.p2.otherEndPoint(n6, n5);
        this.xPoints[1] = otherEndPoint2.canvasX(n2);
        this.yPoints[1] = otherEndPoint2.canvasY(n);
        double n7 = atan - 1.5707963267948966;
        if (n7 < 0.0) {
            n7 += 6.283185307179586;
        }
        final TGPoint otherEndPoint3 = this.p2.otherEndPoint(n7, n5);
        this.xPoints[2] = otherEndPoint3.canvasX(n2);
        this.yPoints[2] = otherEndPoint3.canvasY(n);
        final TGPoint otherEndPoint4 = this.p1.otherEndPoint(n7, n5);
        this.xPoints[3] = otherEndPoint4.canvasX(n2);
        this.yPoints[3] = otherEndPoint4.canvasY(n);
        final int min = this.min(this.xPoints);
        final int n8 = Math.abs(min - this.max(this.xPoints)) + 1;
        final int min2 = this.min(this.yPoints);
        final int n9 = Math.abs(min2 - this.max(this.yPoints)) + 1;
        graphics.setClip(min, min2, n8, n9);
        graphics.fillPolygon(this.xPoints, this.yPoints, 4);
        return new Rectangle(min, min2, n8, n9);
    }
    
    private Rectangle drawVerticalFatLine(final Graphics graphics, final int n, final int n2) {
        final float n3 = this.width / 2.0f;
        this.yPoints[0] = (this.yPoints[3] = this.p1.canvasY(n));
        this.yPoints[1] = (this.yPoints[2] = this.p2.canvasY(n));
        this.xPoints[0] = this.p1.canvasX(-n3, n2);
        this.xPoints[1] = this.p2.canvasX(-n3, n2);
        this.xPoints[2] = this.p2.canvasX(n3, n2);
        this.xPoints[3] = this.p1.canvasX(n3, n2);
        final int min = this.min(this.xPoints);
        final int n4 = Math.abs(min - this.max(this.xPoints)) + 1;
        final int min2 = this.min(this.yPoints);
        final int n5 = Math.abs(min2 - this.max(this.yPoints)) + 1;
        graphics.setClip(min, min2, n4, n5);
        graphics.fillPolygon(this.xPoints, this.yPoints, 4);
        return new Rectangle(min, min2, n4, n5);
    }
    
    private int max(final int[] array) {
        int n = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > n) {
                n = array[i];
            }
        }
        return n;
    }
    
    private int min(final int[] array) {
        int n = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] < n) {
                n = array[i];
            }
        }
        return n;
    }
    
    public Rectangle doIt(final Image image) {
        final int width = image.getWidth(this);
        if (width < 0) {
            return null;
        }
        final int height = image.getHeight(this);
        if (height < 0) {
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.color);
        Rectangle drawFatLine;
        if (this.width == 1) {
            final int canvasX = this.p1.canvasX(width);
            final int canvasY = this.p1.canvasY(height);
            final int canvasX2 = this.p2.canvasX(width);
            final int canvasY2 = this.p2.canvasY(height);
            final int n = (canvasX < canvasX2) ? canvasX : canvasX2;
            final int n2 = Math.abs(canvasX - canvasX2) + 1;
            final int n3 = (canvasY < canvasY2) ? canvasY : canvasY2;
            final int n4 = Math.abs(canvasY - canvasY2) + 1;
            graphics.setClip(n, n3, n2, n4);
            graphics.drawLine(canvasX, canvasY, canvasX2, canvasY2);
            drawFatLine = new Rectangle(n, n3, n2, n4);
        }
        else {
            drawFatLine = this.drawFatLine(graphics, height, width);
        }
        graphics.dispose();
        return drawFatLine;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public String toString() {
        return "TGLineOp[width=" + this.width + ",p1=" + this.p1 + ",p2=" + this.p2 + "]";
    }
}
