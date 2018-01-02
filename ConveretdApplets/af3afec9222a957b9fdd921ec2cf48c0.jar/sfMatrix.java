// 
// Decompiled by Procyon v0.5.30
// 

class sfMatrix
{
    public double m_dXx;
    public double m_dXy;
    public double m_dXz;
    public double m_dYx;
    public double m_dYy;
    public double m_dYz;
    public double m_dZx;
    public double m_dZy;
    public double m_dZz;
    
    public void rotateSelf(final double n, final double n2, final double n3) {
        if (n != 0.0) {
            final double cos = Math.cos(n);
            final double sin = Math.sin(n);
            final double dYx = this.m_dYx * cos + this.m_dZx * sin;
            final double dYy = this.m_dYy * cos + this.m_dZy * sin;
            final double dYz = this.m_dYz * cos + this.m_dZz * sin;
            final double dZx = this.m_dZx * cos - this.m_dYx * sin;
            final double dZy = this.m_dZy * cos - this.m_dYy * sin;
            final double dZz = this.m_dZz * cos - this.m_dYz * sin;
            this.m_dYx = dYx;
            this.m_dYy = dYy;
            this.m_dYz = dYz;
            this.m_dZx = dZx;
            this.m_dZy = dZy;
            this.m_dZz = dZz;
        }
        if (n2 != 0.0) {
            final double cos2 = Math.cos(n2);
            final double sin2 = Math.sin(n2);
            final double dXx = this.m_dXx * cos2 + this.m_dZx * sin2;
            final double dXy = this.m_dXy * cos2 + this.m_dZy * sin2;
            final double dXz = this.m_dXz * cos2 + this.m_dZz * sin2;
            final double dZx2 = this.m_dZx * cos2 - this.m_dXx * sin2;
            final double dZy2 = this.m_dZy * cos2 - this.m_dXy * sin2;
            final double dZz2 = this.m_dZz * cos2 - this.m_dXz * sin2;
            this.m_dXx = dXx;
            this.m_dXy = dXy;
            this.m_dXz = dXz;
            this.m_dZx = dZx2;
            this.m_dZy = dZy2;
            this.m_dZz = dZz2;
        }
        if (n3 != 0.0) {
            final double cos3 = Math.cos(n3);
            final double sin3 = Math.sin(n3);
            final double dXx2 = this.m_dXx * cos3 + this.m_dYx * sin3;
            final double dXy2 = this.m_dXy * cos3 + this.m_dYy * sin3;
            final double dXz2 = this.m_dXz * cos3 + this.m_dYz * sin3;
            final double dYx2 = this.m_dYx * cos3 - this.m_dXx * sin3;
            final double dYy2 = this.m_dYy * cos3 - this.m_dXy * sin3;
            final double dYz2 = this.m_dYz * cos3 - this.m_dXz * sin3;
            this.m_dXx = dXx2;
            this.m_dXy = dXy2;
            this.m_dXz = dXz2;
            this.m_dYx = dYx2;
            this.m_dYy = dYy2;
            this.m_dYz = dYz2;
        }
    }
    
    public void rotateWorld(final double n, final double n2, final double n3) {
        if (n != 0.0) {
            final double cos = Math.cos(n);
            final double sin = Math.sin(n);
            final double dXy = this.m_dXy * cos - this.m_dXz * sin;
            final double dXz = this.m_dXz * cos + this.m_dXy * sin;
            final double dYy = this.m_dYy * cos - this.m_dYz * sin;
            final double dYz = this.m_dYz * cos + this.m_dYy * sin;
            final double dZy = this.m_dZy * cos - this.m_dZz * sin;
            final double dZz = this.m_dZz * cos + this.m_dZy * sin;
            this.m_dXy = dXy;
            this.m_dXz = dXz;
            this.m_dYy = dYy;
            this.m_dYz = dYz;
            this.m_dZy = dZy;
            this.m_dZz = dZz;
        }
        if (n2 != 0.0) {
            final double cos2 = Math.cos(n2);
            final double sin2 = Math.sin(n2);
            final double dXx = this.m_dXx * cos2 + this.m_dXz * sin2;
            final double dXz2 = this.m_dXz * cos2 - this.m_dXx * sin2;
            final double dYx = this.m_dYx * cos2 + this.m_dYz * sin2;
            final double dYz2 = this.m_dYz * cos2 - this.m_dYx * sin2;
            final double dZx = this.m_dZx * cos2 + this.m_dZz * sin2;
            final double dZz2 = this.m_dZz * cos2 - this.m_dZx * sin2;
            this.m_dXx = dXx;
            this.m_dXz = dXz2;
            this.m_dYx = dYx;
            this.m_dYz = dYz2;
            this.m_dZx = dZx;
            this.m_dZz = dZz2;
        }
        if (n3 != 0.0) {
            final double cos3 = Math.cos(n3);
            final double sin3 = Math.sin(n3);
            final double dXx2 = this.m_dXx * cos3 - this.m_dXy * sin3;
            final double dXy2 = this.m_dXy * cos3 + this.m_dXx * sin3;
            final double dYx2 = this.m_dYx * cos3 - this.m_dYy * sin3;
            final double dYy2 = this.m_dYy * cos3 + this.m_dYx * sin3;
            final double dZx2 = this.m_dZx * cos3 - this.m_dZy * sin3;
            final double dZy2 = this.m_dZy * cos3 + this.m_dZx * sin3;
            this.m_dXx = dXx2;
            this.m_dXy = dXy2;
            this.m_dYx = dYx2;
            this.m_dYy = dYy2;
            this.m_dZx = dZx2;
            this.m_dZy = dZy2;
        }
    }
    
    public void normalize() {
        this.m_dZx = this.m_dXy * this.m_dYz - this.m_dXz * this.m_dYy;
        this.m_dZy = this.m_dXz * this.m_dYx - this.m_dXx * this.m_dYz;
        this.m_dZz = this.m_dXx * this.m_dYy - this.m_dXy * this.m_dYx;
        this.m_dYx = this.m_dZy * this.m_dXz - this.m_dZz * this.m_dXy;
        this.m_dYy = this.m_dZz * this.m_dXx - this.m_dZx * this.m_dXz;
        this.m_dYz = this.m_dZx * this.m_dXy - this.m_dZy * this.m_dXx;
        final double sqrt = Math.sqrt(this.m_dXx * this.m_dXx + this.m_dXy * this.m_dXy + this.m_dXz * this.m_dXz);
        if (sqrt == 0.0) {
            return;
        }
        this.m_dXx /= sqrt;
        this.m_dXy /= sqrt;
        this.m_dXz /= sqrt;
        final double sqrt2 = Math.sqrt(this.m_dYx * this.m_dYx + this.m_dYy * this.m_dYy + this.m_dYz * this.m_dYz);
        if (sqrt2 == 0.0) {
            return;
        }
        this.m_dYx /= sqrt2;
        this.m_dYy /= sqrt2;
        this.m_dYz /= sqrt2;
        final double sqrt3 = Math.sqrt(this.m_dZx * this.m_dZx + this.m_dZy * this.m_dZy + this.m_dZz * this.m_dZz);
        if (sqrt3 == 0.0) {
            return;
        }
        this.m_dZx /= sqrt3;
        this.m_dZy /= sqrt3;
        this.m_dZz /= sqrt3;
    }
    
    public sfMatrix() {
        this.m_dXx = 1.0;
        this.m_dXy = 0.0;
        this.m_dXz = 0.0;
        this.m_dYx = 0.0;
        this.m_dYy = 1.0;
        this.m_dYz = 0.0;
        this.m_dZx = 0.0;
        this.m_dZy = 0.0;
        this.m_dZz = 1.0;
    }
    
    public sfMatrix(final double dXx, final double dXy, final double dXz, final double dYx, final double dYy, final double dYz, final double dZx, final double dZy, final double dZz) {
        this.m_dXx = dXx;
        this.m_dXy = dXy;
        this.m_dXz = dXz;
        this.m_dYx = dYx;
        this.m_dYy = dYy;
        this.m_dYz = dYz;
        this.m_dZx = dZx;
        this.m_dZy = dZy;
        this.m_dZz = dZz;
    }
}
