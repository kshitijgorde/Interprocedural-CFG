// 
// Decompiled by Procyon v0.5.30
// 

class viewPoint
{
    int nrOflights;
    point3d location;
    point3d[] lights;
    point3d view;
    
    public viewPoint(final int nrOflights) {
        this.location = new point3d(0, 0, 0);
        this.nrOflights = nrOflights;
        this.lights = new point3d[this.nrOflights];
        this.view = new point3d(0, 0, 1);
    }
    
    public void setLight(final int n, final int n2, final int n3, final int n4) {
        if (n < this.nrOflights) {
            this.lights[n] = new point3d(n2, n3, n4);
        }
    }
}
