// 
// Decompiled by Procyon v0.5.30
// 

class sfVector
{
    public double m_dX;
    public double m_dY;
    public double m_dZ;
    
    public sfVector() {
        this.m_dX = 0.0;
        this.m_dY = 0.0;
        this.m_dZ = 0.0;
    }
    
    public sfVector(final double dx, final double dy, final double dz) {
        this.m_dX = dx;
        this.m_dY = dy;
        this.m_dZ = dz;
    }
}