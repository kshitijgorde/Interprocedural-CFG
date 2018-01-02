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
    private double heading;
    private int penWidth;
    private int[] xPoints;
    private int[] yPoints;
    private TGPoint p1;
    private TGPoint p2;
    
    public TGLineOp(final TGPoint tgPoint, final TGPoint tgPoint2, final double heading, final Color color, final int penWidth) {
        if (tgPoint.xFloatValue() <= tgPoint2.xFloatValue()) {
            this.p1 = tgPoint;
            this.p2 = tgPoint2;
            this.heading = heading;
        }
        else {
            this.p1 = tgPoint2;
            this.p2 = tgPoint;
            this.heading = heading - 3.141592653589793;
            if (this.heading < 0.0) {
                this.heading += 6.283185307179586;
            }
        }
        this.color = color;
        this.penWidth = penWidth;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        System.err.println("TGLineOp.imageUpdate: got here!");
        return true;
    }
    
    private Rectangle drawHorizontalFatLine(final Graphics graphics, final int n, final int n2) {
        final int imageY = this.p1.imageY(this.penWidth / 2.0f, n);
        final int imageX = this.p1.imageX(n2);
        final int imageX2 = this.p2.imageX(n2);
        final int n3 = (imageX < imageX2) ? imageX : imageX2;
        final int abs = Math.abs(imageX - imageX2);
        graphics.setClip(n3, imageY, abs, this.penWidth);
        graphics.fillRect(n3, imageY, abs, this.penWidth);
        return new Rectangle(n3, imageY, abs, this.penWidth);
    }
    
    private Rectangle drawFatLine(final Graphics graphics, final int n, final int n2) {
        final double n3 = 0.004363323129985824;
        if (this.heading < n3 || this.heading > 6.283185307179586 - n3) {
            return this.drawHorizontalFatLine(graphics, n, n2);
        }
        if (this.heading > 1.5707963267948966 - n3 && this.heading < 4.71238898038469 + n3) {
            return this.drawVerticalFatLine(graphics, n, n2);
        }
        final float abs = Math.abs(this.p1.xFloatValue() - this.p2.xFloatValue());
        final float abs2 = Math.abs(this.p1.yFloatValue() - this.p2.yFloatValue());
        if (abs < 1.5f && abs2 < 1.5f) {
            return this.drawOnePixelFatLine(graphics, n, n2);
        }
        final int imageX = this.p1.imageX(n2);
        final int imageY = this.p1.imageY(n);
        final int imageX2 = this.p2.imageX(n2);
        final int imageY2 = this.p2.imageY(n);
        final double n4 = this.penWidth / 2.0;
        double n5 = this.heading + 1.5707963267948966;
        if (n5 > 6.283185307179586) {
            n5 -= 6.283185307179586;
        }
        final TGPoint otherEndPoint = this.p1.otherEndPoint(n5, n4);
        final int imageX3 = otherEndPoint.imageX(n2);
        final int n6 = imageX3 - imageX;
        final int imageY3 = otherEndPoint.imageY(n);
        final int n7 = imageY3 - imageY;
        double n8 = this.heading - 1.5707963267948966;
        if (n8 < 0.0) {
            n8 += 6.283185307179586;
        }
        final TGPoint otherEndPoint2 = this.p1.otherEndPoint(n8, n4);
        final int imageX4 = otherEndPoint2.imageX(n2);
        final int n9 = imageX4 - imageX;
        final int imageY4 = otherEndPoint2.imageY(n);
        final int n10 = imageY4 - imageY;
        if (this.xPoints == null) {
            this.xPoints = new int[4];
            this.yPoints = new int[4];
        }
        this.xPoints[0] = imageX3;
        this.yPoints[0] = imageY3;
        this.xPoints[1] = imageX4;
        this.yPoints[1] = imageY4;
        this.xPoints[2] = imageX2 + n9;
        this.yPoints[2] = imageY2 + n10;
        this.xPoints[3] = imageX2 + n6;
        this.yPoints[3] = imageY2 + n7;
        final int min = this.min(this.xPoints);
        final int n11 = Math.abs(min - this.max(this.xPoints)) + 1;
        final int min2 = this.min(this.yPoints);
        final int n12 = Math.abs(min2 - this.max(this.yPoints)) + 1;
        graphics.setClip(min, min2, n11, n12);
        graphics.fillPolygon(this.xPoints, this.yPoints, 4);
        return new Rectangle(min, min2, n11, n12);
    }
    
    private Rectangle drawOnePixelFatLine(final Graphics graphics, final int n, final int n2) {
        final double n3 = this.penWidth / 2.0;
        double n4 = this.heading + 1.5707963267948966;
        if (n4 > 6.283185307179586) {
            n4 -= 6.283185307179586;
        }
        final TGPoint otherEndPoint = this.p1.otherEndPoint(n4, n3);
        final int imageX = otherEndPoint.imageX(n2);
        final int imageY = otherEndPoint.imageY(n);
        double n5 = this.heading - 1.5707963267948966;
        if (n5 < 0.0) {
            n5 += 6.283185307179586;
        }
        final TGPoint otherEndPoint2 = otherEndPoint.otherEndPoint(n5, this.penWidth);
        final int imageX2 = otherEndPoint2.imageX(n2);
        final int imageY2 = otherEndPoint2.imageY(n);
        final int n6 = (imageX < imageX2) ? imageX : imageX2;
        final int n7 = Math.abs(imageX - imageX2) + 1;
        final int n8 = (imageY < imageY2) ? imageY : imageY2;
        final int n9 = Math.abs(imageY - imageY2) + 1;
        graphics.setClip(n6, n8, n7, n9);
        graphics.drawLine(imageX, imageY, imageX2, imageY2);
        return new Rectangle(n6, n8, n7, n9);
    }
    
    private Rectangle drawVerticalFatLine(final Graphics graphics, final int n, final int n2) {
        final int imageX = this.p1.imageX(-(this.penWidth / 2.0f), n2);
        final int imageY = this.p1.imageY(n);
        final int imageY2 = this.p2.imageY(n);
        final int n3 = (imageY < imageY2) ? imageY : imageY2;
        final int abs = Math.abs(imageY - imageY2);
        graphics.setClip(imageX, n3, this.penWidth, abs);
        graphics.fillRect(imageX, n3, this.penWidth, abs);
        return new Rectangle(imageX, n3, this.penWidth, abs);
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
        if (this.penWidth == 1) {
            final int imageX = this.p1.imageX(width);
            final int imageY = this.p1.imageY(height);
            final int imageX2 = this.p2.imageX(width);
            final int imageY2 = this.p2.imageY(height);
            final int n = (imageX < imageX2) ? imageX : imageX2;
            final int n2 = Math.abs(imageX - imageX2) + 1;
            final int n3 = (imageY < imageY2) ? imageY : imageY2;
            final int n4 = Math.abs(imageY - imageY2) + 1;
            graphics.setClip(n, n3, n2, n4);
            graphics.drawLine(imageX, imageY, imageX2, imageY2);
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
        return "TGLineOp[color=" + this.color + ",width=" + this.penWidth + ",p1=" + this.p1 + ",p2=" + this.p2 + "]";
    }
}
