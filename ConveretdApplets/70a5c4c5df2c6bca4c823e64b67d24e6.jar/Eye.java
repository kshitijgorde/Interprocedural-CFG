import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Eye
{
    public int centerx;
    public int centery;
    public double magnification;
    public Point[] m;
    Point[] d;
    Point[] rslm;
    Point p;
    
    public Eye(final String s, final int n, final int n2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " p(,)");
        this.p = new Point(0.0, 0.0, 0.0);
        this.p.x = 0.0;
        this.p.y = 0.0;
        this.p.z = -Point.s2d(stringTokenizer.nextToken(), 10.0);
        this.setEye(this.p, Point.s2d(stringTokenizer.nextToken(), 0.0), Point.s2d(stringTokenizer.nextToken(), 0.0), Point.s2d(stringTokenizer.nextToken(), 0.0), n, n2, Point.s2d(stringTokenizer.nextToken(), 1.0));
    }
    
    public Eye(final Point point, final double n, final double n2, final double n3, final int n4, final int n5, final double n6) {
        this.setEye(point, n, n2, n3, n4, n5, n6);
    }
    
    void setEye(final Point point, final double n, final double n2, final double n3, final int centerx, final int centery, final double magnification) {
        this.centerx = centerx;
        this.centery = centery;
        this.magnification = magnification;
        (this.m = new Point[5])[0] = new Point(1.0, 0.0, 0.0);
        this.m[1] = new Point(0.0, 1.0, 0.0);
        this.m[2] = new Point(0.0, 0.0, 1.0);
        this.m[3] = new Point(point.x, point.y, point.z);
        this.m[4] = new Point(0.0, 0.0, 0.0);
        (this.d = new Point[3])[0] = new Point(0.0, 0.0, 0.0);
        this.d[1] = new Point(0.0, 0.0, 0.0);
        this.d[2] = new Point(0.0, 0.0, 0.0);
        (this.rslm = new Point[3])[0] = new Point(0.0, 0.0, 0.0);
        this.rslm[1] = new Point(0.0, 0.0, 0.0);
        this.rslm[2] = new Point(0.0, 0.0, 0.0);
        this.left(n);
        this.up(n2);
        this.clockwise(n3);
    }
    
    public final void left(final double n) {
        this.d[0].x = Math.cos(n);
        this.d[0].y = 0.0;
        this.d[0].z = Math.sin(n);
        this.d[1].x = 0.0;
        this.d[1].y = 1.0;
        this.d[1].z = 0.0;
        this.d[2].x = -Math.sin(n);
        this.d[2].y = 0.0;
        this.d[2].z = Math.cos(n);
        this.mdt();
    }
    
    public final void up(final double n) {
        this.d[0].x = 1.0;
        this.d[0].y = 0.0;
        this.d[0].z = 0.0;
        this.d[1].x = 0.0;
        this.d[1].y = Math.cos(n);
        this.d[1].z = Math.sin(n);
        this.d[2].x = 0.0;
        this.d[2].y = -Math.sin(n);
        this.d[2].z = Math.cos(n);
        this.mdt();
    }
    
    public final void clockwise(final double n) {
        this.d[0].x = Math.cos(n);
        this.d[0].y = Math.sin(n);
        this.d[0].z = 0.0;
        this.d[1].x = -Math.sin(n);
        this.d[1].y = Math.cos(n);
        this.d[1].z = 0.0;
        this.d[2].x = 0.0;
        this.d[2].y = 0.0;
        this.d[2].z = 1.0;
        this.mdt();
    }
    
    public final void center(final Point point) {
        this.m[4].copy(point);
    }
    
    public final void move(final Point point) {
        this.m[3].plusa(this.m[3], 1.0, point);
    }
    
    public final void zoom(final double n) {
        this.magnification *= n;
    }
    
    final void mdt() {
        this.rslm[0].x = this.m[0].x * this.d[0].x + this.m[1].x * this.d[0].y + this.m[2].x * this.d[0].z;
        this.rslm[0].y = this.m[0].y * this.d[0].x + this.m[1].y * this.d[0].y + this.m[2].y * this.d[0].z;
        this.rslm[0].z = this.m[0].z * this.d[0].x + this.m[1].z * this.d[0].y + this.m[2].z * this.d[0].z;
        this.rslm[1].x = this.m[0].x * this.d[1].x + this.m[1].x * this.d[1].y + this.m[2].x * this.d[1].z;
        this.rslm[1].y = this.m[0].y * this.d[1].x + this.m[1].y * this.d[1].y + this.m[2].y * this.d[1].z;
        this.rslm[1].z = this.m[0].z * this.d[1].x + this.m[1].z * this.d[1].y + this.m[2].z * this.d[1].z;
        this.rslm[2].x = this.m[0].x * this.d[2].x + this.m[1].x * this.d[2].y + this.m[2].x * this.d[2].z;
        this.rslm[2].y = this.m[0].y * this.d[2].x + this.m[1].y * this.d[2].y + this.m[2].y * this.d[2].z;
        this.rslm[2].z = this.m[0].z * this.d[2].x + this.m[1].z * this.d[2].y + this.m[2].z * this.d[2].z;
        final Point point = this.rslm[0];
        this.rslm[0] = this.m[0];
        this.m[0] = point;
        final Point point2 = this.rslm[1];
        this.rslm[1] = this.m[1];
        this.m[1] = point2;
        final Point point3 = this.rslm[2];
        this.rslm[2] = this.m[2];
        this.m[2] = point3;
    }
    
    public final void map(final Point point, final Point point2) {
        this.p.minus(point2, this.m[4]);
        point.x = this.p.dot(this.m[0]);
        point.y = this.p.dot(this.m[1]);
        point.z = this.p.dot(this.m[2]);
        point.minus(point, this.m[3]);
    }
    
    public final int mapx(final Point point) {
        return this.centerx + (int)(this.magnification * point.x / point.z);
    }
    
    public final int mapy(final Point point) {
        return this.centery + (int)(this.magnification * point.y / point.z);
    }
}
