// 
// Decompiled by Procyon v0.5.30
// 

class cgVector
{
    public double m_dX;
    public double m_dY;
    public double m_dZ;
    
    public cgVector() {
        this.m_dX = 0.0;
        this.m_dY = 0.0;
        this.m_dZ = 0.0;
    }
    
    public cgVector(final double dx, final double dy, final double dz) {
        this.m_dX = dx;
        this.m_dY = dy;
        this.m_dZ = dz;
    }
    
    public cgVector(final cgVector cgVector, final cgVector cgVector2) {
        this.crossProduct(cgVector, cgVector2);
    }
    
    public double normalize() {
        final double sqrt = Math.sqrt(this.m_dX * this.m_dX + this.m_dY * this.m_dY + this.m_dZ * this.m_dZ);
        if (sqrt > 0.0) {
            this.m_dX /= sqrt;
            this.m_dY /= sqrt;
            this.m_dZ /= sqrt;
        }
        return sqrt;
    }
    
    public void crossProduct(final cgVector cgVector, final cgVector cgVector2) {
        this.m_dX = cgVector.m_dY * cgVector2.m_dZ - cgVector.m_dZ * cgVector2.m_dY;
        this.m_dY = cgVector.m_dZ * cgVector2.m_dX - cgVector.m_dX * cgVector2.m_dZ;
        this.m_dZ = cgVector.m_dX * cgVector2.m_dY - cgVector.m_dY * cgVector2.m_dX;
    }
}
