// 
// Decompiled by Procyon v0.5.30
// 

class point3d
{
    double lx;
    double ly;
    double lz;
    double wx;
    double wy;
    double wz;
    int xp;
    int yp;
    int zp;
    int color;
    
    point3d(final double x, final double y, final double z, final int c) {
        this(x, y, z);
        this.color = c;
    }
    
    point3d(final double x, final double y, final double z) {
        this.lx = x;
        this.ly = y;
        this.lz = z;
    }
    
    point3d() {
    }
}
