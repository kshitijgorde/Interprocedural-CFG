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
    int speed;
    
    point3d() {
        this.set(0.0, 0.0, 0.0);
    }
    
    point3d(final double n, final double n2, final double n3) {
        this.set(n, n2, n3);
    }
    
    point3d(final double n, final double n2, final double n3, final int n4) {
        this.set(n, n2, n3, n4);
    }
    
    public void set(final double lx, final double ly, final double lz) {
        this.lx = lx;
        this.ly = ly;
        this.lz = lz;
    }
    
    public void set(final double lx, final double ly, final double lz, final int color) {
        this.lx = lx;
        this.ly = ly;
        this.lz = lz;
        this.color = color;
    }
    
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
}
