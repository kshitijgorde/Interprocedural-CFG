import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Line
{
    int x1;
    int y1;
    int x2;
    int y2;
    Color color;
    
    Line(final int x1, final int y1, final int x2, final int y2) {
        this.color = new Color(0, 0, 255);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public int getX1() {
        return this.x1;
    }
    
    public int getY1() {
        return this.y1;
    }
    
    public int getX2() {
        return this.x2;
    }
    
    public int getY2() {
        return this.y2;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setX1(final int x1) {
        this.x1 = x1;
    }
    
    public void setY1(final int y1) {
        this.y1 = y1;
    }
    
    public void setX2(final int x2) {
        this.x2 = x2;
    }
    
    public void setY2(final int y2) {
        this.y2 = y2;
    }
    
    public double getSlope() {
        double n = 0.0;
        final double n2 = this.x1;
        final double n3 = this.y1;
        final double n4 = this.x2;
        final double n5 = this.y2;
        try {
            n = (n5 - n3) / (n4 - n2);
        }
        catch (ArithmeticException ex) {}
        return n;
    }
    
    public double getLength() {
        return Math.abs(Math.sqrt(Math.pow(this.y2 - this.y1, 2.0) + Math.pow(this.x2 - this.x1, 2.0)));
    }
}
