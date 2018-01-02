// 
// Decompiled by Procyon v0.5.30
// 

public class Location
{
    int m_ix;
    int m_iy;
    int m_iz;
    
    public int getZ() {
        return this.m_iz;
    }
    
    public void setZ(final int iz) {
        this.m_iz = iz;
    }
    
    public int getX() {
        return this.m_ix;
    }
    
    public void setX(final int ix) {
        this.m_ix = ix;
    }
    
    public Location() {
        final int ix = -1;
        this.m_iz = ix;
        this.m_iy = ix;
        this.m_ix = ix;
    }
    
    public Location(final int ix, final int iy, final int iz) {
        this.m_ix = ix;
        this.m_iy = iy;
        this.m_iz = iz;
    }
    
    public int getY() {
        return this.m_iy;
    }
    
    public void setY(final int iy) {
        this.m_iy = iy;
    }
    
    public void setLocation(final int ix, final int iy, final int iz) {
        this.m_ix = ix;
        this.m_iy = iy;
        this.m_iz = iz;
    }
}
