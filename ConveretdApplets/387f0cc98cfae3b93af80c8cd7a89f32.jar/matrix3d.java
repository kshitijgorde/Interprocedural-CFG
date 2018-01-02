// 
// Decompiled by Procyon v0.5.30
// 

class matrix3d
{
    double distance;
    double[][] mat;
    double tx;
    double ty;
    double tz;
    double sc;
    int mx;
    int my;
    
    matrix3d(final int n) {
        this.mat = new double[3][3];
        this.distance = n;
    }
    
    void center(final int mx, final int my) {
        this.mx = mx;
        this.my = my;
    }
    
    void form(final point3d point3d) {
        final double[] array = this.mat[0];
        point3d.wx = point3d.lx * array[0] + point3d.ly * array[1] + point3d.lz * array[2] + this.tx;
        final double[] array2 = this.mat[1];
        point3d.wy = point3d.lx * array2[0] + point3d.ly * array2[1] + point3d.lz * array2[2] + this.ty;
        final double[] array3 = this.mat[2];
        point3d.wz = point3d.lx * array3[0] + point3d.ly * array3[1] + point3d.lz * array3[2] + this.tz;
    }
    
    void move(final double n, final double n2, final double n3) {
        for (int i = 0; i < 3; ++i) {
            final double[] array = this.mat[i];
            final int n4 = 0;
            array[n4] += n * this.mat[i][0];
            final double[] array2 = this.mat[i];
            final int n5 = 1;
            array2[n5] += n2 * this.mat[i][1];
            final double[] array3 = this.mat[i];
            final int n6 = 2;
            array3[n6] += n3 * this.mat[i][2];
        }
    }
    
    void project(final point3d point3d) {
        double n;
        if ((n = this.distance - point3d.wz) == 0.0) {
            n = 1.0;
        }
        point3d.xp = (int)(this.distance * point3d.wx / n) + this.mx;
        point3d.yp = (int)(this.distance * point3d.wy / n) + this.my;
        point3d.zp = (int)point3d.wz;
    }
    
    double radian(final double n) {
        return n / 180.0 * 3.141592653589793;
    }
    
    void rotate(final double n, final double n2, final double n3) {
        final double radian = this.radian(n);
        final double cos = Math.cos(radian);
        final double sin = Math.sin(radian);
        final double radian2 = this.radian(n2);
        final double cos2 = Math.cos(radian2);
        final double sin2 = Math.sin(radian2);
        final double radian3 = this.radian(n3);
        final double cos3 = Math.cos(radian3);
        final double sin3 = Math.sin(radian3);
        final double n4 = this.sc * this.sc;
        this.mat[0][0] = cos2 * cos3 * this.sc;
        this.mat[0][1] = -cos2 * sin3 * this.sc;
        this.mat[0][2] = -sin2 * n4;
        this.mat[1][0] = -sin * sin2 * cos3 * this.sc + cos * sin3 * n4;
        this.mat[1][1] = sin * sin2 * sin3 * this.sc + cos * cos3 * n4;
        this.mat[1][2] = -sin * cos2 * n4;
        this.mat[2][0] = cos * sin2 * cos3 * this.sc + sin * sin3 * n4;
        this.mat[2][1] = -cos * sin2 * sin3 * this.sc + sin * cos3 * n4;
        this.mat[2][2] = cos * cos2 * n4;
    }
    
    void scale(final double sc) {
        this.sc = sc;
    }
    
    void sortIt(final int n, final point3d[] array, final int[] array2) {
        for (int i = 0; i < n; ++i) {
            array2[i] = i;
        }
        for (int j = 0; j < n; ++j) {
            int n2 = j;
            for (int k = j; k < n; ++k) {
                if (array[array2[n2]].wz > array[array2[k]].wz) {
                    n2 = k;
                }
            }
            if (n2 != j) {
                final int n3 = array2[n2];
                array2[n2] = array2[j];
                array2[j] = n3;
            }
        }
    }
    
    void transform(final int n, final point3d[] array) {
        for (int i = 0; i < n; ++i) {
            this.form(array[i]);
            this.project(array[i]);
        }
    }
    
    void translate(final double tx, final double ty, final double tz) {
        this.tx = tx;
        this.ty = ty;
        this.tz = tz;
    }
}
