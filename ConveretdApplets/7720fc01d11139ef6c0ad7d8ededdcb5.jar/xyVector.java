import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class xyVector
{
    double m_x;
    double m_y;
    static final double one_over_root_two;
    
    public xyVector(final xyVector xyVector) {
        this.m_x = xyVector.m_x;
        this.m_y = xyVector.m_y;
    }
    
    public xyVector(final Point point) {
        this.m_x = point.x;
        this.m_y = point.y;
    }
    
    public xyVector(final double x, final double y) {
        this.m_x = x;
        this.m_y = y;
    }
    
    public boolean isEqualTo(final xyVector xyVector) {
        return this.m_x == xyVector.m_x && this.m_y == xyVector.m_y;
    }
    
    public double x() {
        return this.m_x;
    }
    
    public double y() {
        return this.m_y;
    }
    
    public void scale(final double n, final double n2) {
        this.m_x *= n;
        this.m_y *= n2;
    }
    
    public void scale(final double n) {
        this.m_x *= n;
        this.m_y *= n;
    }
    
    public void rotate45() {
        final double n = this.m_y + this.m_x;
        final double n2 = this.m_y - this.m_x;
        this.m_x = n * xyVector.one_over_root_two;
        this.m_y = n2 * xyVector.one_over_root_two;
    }
    
    public void rotate90() {
        final double x = this.m_x;
        this.m_x = -this.m_y;
        this.m_y = x;
    }
    
    public void mirror45() {
        final double x = this.m_x;
        this.m_x = this.m_y;
        this.m_y = x;
    }
    
    public void add(final xyVector xyVector) {
        this.m_x += xyVector.m_x;
        this.m_y += xyVector.m_y;
    }
    
    public void add(final double n, final double n2) {
        this.m_x += n;
        this.m_y += n2;
    }
    
    public void subtract(final xyVector xyVector) {
        this.m_x -= xyVector.m_x;
        this.m_y -= xyVector.m_y;
    }
    
    public void subtract(final double n, final double n2) {
        this.m_x -= n;
        this.m_y -= n2;
    }
    
    public double magnitude() {
        return Math.sqrt(this.m_x * this.m_x + this.m_y * this.m_y);
    }
    
    public double angle() {
        return Math.atan2(this.m_x, this.m_y);
    }
    
    public xyVector unit() {
        if (this.magnitude() == 0.0) {
            return new xyVector(0.0, 0.0);
        }
        final xyVector xyVector = new xyVector(this);
        xyVector.scale(1.0 / this.magnitude());
        return xyVector;
    }
    
    public xyVector resolve(final xyVector xyVector) {
        final xyVector unit = xyVector.unit();
        unit.scale(this.magnitude() * Math.cos(this.angle() - xyVector.angle()));
        return unit;
    }
    
    static {
        one_over_root_two = Math.sqrt(0.5);
    }
}
