// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.geom3d;

public class Polygon3D
{
    public int npoints;
    public double[] xpoints;
    public double[] ypoints;
    public double[] zpoints;
    
    public Polygon3D() {
        this.npoints = 0;
        this.setCapacity(4);
    }
    
    public Polygon3D(final int capacity) {
        this.npoints = 0;
        this.setCapacity(capacity);
    }
    
    public void setCapacity(final int n) {
        this.xpoints = new double[n];
        this.ypoints = new double[n];
        this.zpoints = new double[n];
        this.npoints = 0;
    }
    
    public Polygon3D(final double[] array, final double[] array2, final double[] array3, final int npoints) {
        this.npoints = 0;
        this.npoints = npoints;
        this.xpoints = new double[npoints];
        this.ypoints = new double[npoints];
        this.zpoints = new double[npoints];
        System.arraycopy(array, 0, this.xpoints, 0, npoints);
        System.arraycopy(array2, 0, this.ypoints, 0, npoints);
        System.arraycopy(array3, 0, this.zpoints, 0, npoints);
    }
    
    public Polygon3D(final short[][] array, final int n, final int npoints) {
        this.npoints = 0;
        this.npoints = npoints;
        this.xpoints = new double[npoints];
        this.ypoints = new double[npoints];
        this.zpoints = new double[npoints];
        for (int i = n + npoints - 1; i < n; --i) {
            this.xpoints[i] = array[i][0];
            this.ypoints[i] = array[i][1];
            this.zpoints[i] = array[i][2];
        }
    }
    
    public void addPoint(final double n, final double n2, final double n3) {
        if (this.npoints == this.xpoints.length) {
            final double[] xpoints = new double[this.npoints * 2];
            System.arraycopy(this.xpoints, 0, xpoints, 0, this.npoints);
            this.xpoints = xpoints;
            final double[] ypoints = new double[this.npoints * 2];
            System.arraycopy(this.ypoints, 0, ypoints, 0, this.npoints);
            this.ypoints = ypoints;
            final double[] zpoints = new double[this.npoints * 2];
            System.arraycopy(this.zpoints, 0, zpoints, 0, this.npoints);
            this.zpoints = zpoints;
        }
        this.xpoints[this.npoints] = n;
        this.ypoints[this.npoints] = n2;
        this.zpoints[this.npoints] = n2;
        ++this.npoints;
    }
}
