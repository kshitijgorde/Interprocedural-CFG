// 
// Decompiled by Procyon v0.5.30
// 

class matrix3d
{
    static final double DISTANCE = 150.0;
    static final int NUMBER = 14;
    double[][] mat;
    double tx;
    double ty;
    double tz;
    double sc;
    int mx;
    int my;
    
    matrix3d() {
        this.mat = new double[3][3];
    }
    
    double radian(final double deg) {
        return deg / 180.0 * 3.141592653589793;
    }
    
    void scale(final double scale) {
        this.sc = scale;
    }
    
    void center(final int x, final int y) {
        this.mx = x;
        this.my = y;
    }
    
    void translate(final double x, final double y, final double z) {
        this.tx = x;
        this.ty = y;
        this.tz = z;
    }
    
    void rotate(final double x, final double y, final double z) {
        double rad = this.radian(x);
        final double xc = Math.cos(rad);
        final double xs = Math.sin(rad);
        rad = this.radian(y);
        final double yc = Math.cos(rad);
        final double ys = Math.sin(rad);
        rad = this.radian(z);
        final double zc = Math.cos(rad);
        final double zs = Math.sin(rad);
        final double sc2 = this.sc * this.sc;
        this.mat[0][0] = yc * zc * this.sc;
        this.mat[0][1] = -yc * zs * this.sc;
        this.mat[0][2] = -ys * sc2;
        this.mat[1][0] = -xs * ys * zc * this.sc + xc * zs * sc2;
        this.mat[1][1] = xs * ys * zs * this.sc + xc * zc * sc2;
        this.mat[1][2] = -xs * yc * sc2;
        this.mat[2][0] = xc * ys * zc * this.sc + xs * zs * sc2;
        this.mat[2][1] = -xc * ys * zs * this.sc + xs * zc * sc2;
        this.mat[2][2] = xc * yc * sc2;
    }
    
    void move(final double x, final double y, final double z) {
        for (int i = 0; i < 3; ++i) {
            final double[] array = this.mat[i];
            final int n = 0;
            array[n] += x * this.mat[i][0];
            final double[] array2 = this.mat[i];
            final int n2 = 1;
            array2[n2] += y * this.mat[i][1];
            final double[] array3 = this.mat[i];
            final int n3 = 2;
            array3[n3] += z * this.mat[i][2];
        }
    }
    
    void form(final point3d pt) {
        pt.wx = pt.lx * this.mat[0][0] + pt.ly * this.mat[0][1] + pt.lz * this.mat[0][2] + this.tx;
        pt.wy = pt.lx * this.mat[1][0] + pt.ly * this.mat[1][1] + pt.lz * this.mat[1][2] + this.ty;
        pt.wz = pt.lx * this.mat[2][0] + pt.ly * this.mat[2][1] + pt.lz * this.mat[2][2] + this.tz;
    }
    
    void project(final point3d pt) {
        double z;
        if ((z = 150.0 - pt.wz) == 0.0) {
            z = 1.0;
        }
        pt.xp = (int)(150.0 * pt.wx / z) + this.mx;
        pt.yp = (int)(150.0 * pt.wy / z) + this.my;
        pt.zp = (int)pt.wz;
    }
    
    void transform(final point3d[] pt) {
        for (int i = 0; i < 14; ++i) {
            this.form(pt[i]);
            this.project(pt[i]);
        }
    }
    
    void sortIt(final point3d[] p, final int[] st) {
        for (int i = 0; i < 14; ++i) {
            st[i] = i;
        }
        for (int i = 0; i < 14; ++i) {
            int t = i;
            for (int j = i; j < 14; ++j) {
                if (p[st[t]].wz > p[st[j]].wz) {
                    t = j;
                }
            }
            if (t != i) {
                final int c = st[t];
                st[t] = st[i];
                st[i] = c;
            }
        }
    }
}
