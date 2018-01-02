// 
// Decompiled by Procyon v0.5.30
// 

class DoublePoint
{
    public double x;
    public double y;
    
    public DoublePoint() {
        this(0.0, 0.0);
    }
    
    public DoublePoint(final DoublePoint doublePoint) {
        this(doublePoint.x, doublePoint.y);
    }
    
    public DoublePoint(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public DoublePoint getLocation() {
        return new DoublePoint(this.x, this.y);
    }
    
    public void setLocation(final DoublePoint doublePoint) {
        this.setLocation(doublePoint.x, doublePoint.y);
    }
    
    public void setLocation(final double n, final double n2) {
        this.move(n, n2);
    }
    
    public void move(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public void translate(final double n, final double n2) {
        this.x += n;
        this.y += n2;
    }
    
    public boolean equals(final Object o) {
        if (o instanceof DoublePoint) {
            final DoublePoint doublePoint = (DoublePoint)o;
            return this.x == doublePoint.x && this.y == doublePoint.y;
        }
        return false;
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + "[x=" + this.x + ",y=" + this.y + "]";
    }
}
