import java.util.StringTokenizer;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Moon
{
    Point p;
    Point v;
    Point a;
    double m;
    Color c;
    Point[] ov;
    Point[] oa;
    Point[] nov;
    Point[] noa;
    int head;
    static final int POINTS = 9;
    static final int PERIOD = 8;
    static final int history = 20;
    static final BInterpolate dejit;
    double r;
    int id;
    int screenx;
    int screeny;
    int screenr;
    Point peye;
    
    public Moon(final String s, final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " pv(,)");
        final Point point = new Point(0.0, 0.0, 0.0);
        final Point point2 = new Point(0.0, 0.0, 0.0);
        point.x = Point.s2d(stringTokenizer.nextToken(), 0.0);
        point.y = Point.s2d(stringTokenizer.nextToken(), 0.0);
        point.z = Point.s2d(stringTokenizer.nextToken(), 0.0);
        point2.x = Point.s2d(stringTokenizer.nextToken(), 0.0);
        point2.y = Point.s2d(stringTokenizer.nextToken(), 0.0);
        point2.z = Point.s2d(stringTokenizer.nextToken(), 0.0);
        this.setMoon(point, point2, Point.s2d(stringTokenizer.nextToken(), 0.0), Point.s2c(stringTokenizer.nextToken(), Color.white), Point.s2d(stringTokenizer.nextToken(), 0.0), n);
    }
    
    public Moon(final Point point, final Point point2, final double n, final Color color, final double n2, final int n3) {
        this.setMoon(point, point2, n, color, n2, n3);
    }
    
    void setMoon(final Point point, final Point point2, final double m, final Color c, final double r, final int id) {
        this.ov = new Point[40];
        this.oa = new Point[40];
        this.nov = new Point[40];
        this.noa = new Point[40];
        for (int i = 0; i < 20; ++i) {
            this.ov[i] = (this.ov[i + 20] = new Point(0.0, 0.0, 0.0));
            this.oa[i] = (this.oa[i + 20] = new Point(0.0, 0.0, 0.0));
            this.nov[i] = (this.nov[i + 20] = new Point(0.0, 0.0, 0.0));
            this.noa[i] = (this.noa[i + 20] = new Point(0.0, 0.0, 0.0));
        }
        this.id = id;
        this.p = new Point(point.x, point.y, point.z);
        this.v = new Point(point2.x, point2.y, point2.z);
        this.a = new Point(0.0, 0.0, 0.0);
        this.m = m;
        this.r = r;
        this.c = c;
        this.peye = new Point(0.0, 0.0, 0.0);
        this.head = 20;
    }
    
    final void step(final double n, final Point point) {
        this.v.zero();
        point.plus(this.oa[this.head], this.oa[this.head + 7]);
        this.v.plusa(this.v, 1.460383597883598, point);
        point.plus(this.oa[this.head + 1], this.oa[this.head + 6]);
        this.v.plusa(this.v, -0.48525132275132277, point);
        point.plus(this.oa[this.head + 2], this.oa[this.head + 5]);
        this.v.plusa(this.v, 3.0267857142857144, point);
        point.plus(this.oa[this.head + 3], this.oa[this.head + 4]);
        this.v.plusa(this.v, -0.001917989417989418, point);
        this.v.scale(n * n);
        this.v.plus(this.v, this.ov[this.head + 7]);
        this.p.plus(this.p, this.v);
    }
    
    final void velocity(final double n, final Point point) {
        this.v.zero();
        point.plus(this.ov[this.head + 3], this.ov[this.head + 4]);
        this.v.plusa(this.v, 0.8 / n, point);
        point.plus(point, this.ov[this.head + 2]);
        point.plus(point, this.ov[this.head + 5]);
        this.v.plusa(this.v, -0.2 / n, point);
        point.plus(point, this.ov[this.head + 1]);
        point.plus(point, this.ov[this.head + 6]);
        this.v.plusa(this.v, 0.0380952380952381 / n, point);
        point.plus(point, this.ov[this.head + 0]);
        point.plus(point, this.ov[this.head + 7]);
        this.v.plusa(this.v, -0.0035714285714285713 / n, point);
    }
    
    final void dejitter(final double n, final double n2, final BVector bVector, final BVector bVector2, final Point[] array, final Point[] array2) {
        for (int i = 0; i < Moon.dejit.length; ++i) {
            bVector.set(i, this.oa[this.head + i].x);
        }
        Moon.dejit.makeInterpolator(bVector2, bVector);
        for (int j = 0; j < Moon.dejit.length; ++j) {
            array[j].x = bVector2.get(j);
        }
        for (int k = 0; k < Moon.dejit.length; ++k) {
            bVector.set(k, this.oa[this.head + k].y);
        }
        Moon.dejit.makeInterpolator(bVector2, bVector);
        for (int l = 0; l < Moon.dejit.length; ++l) {
            array[l].y = bVector2.get(l);
        }
        for (int n3 = 0; n3 < Moon.dejit.length; ++n3) {
            bVector.set(n3, this.oa[this.head + n3].z);
        }
        Moon.dejit.makeInterpolator(bVector2, bVector);
        for (int n4 = 0; n4 < Moon.dejit.length; ++n4) {
            array[n4].z = bVector2.get(n4);
        }
        array2[0].zero();
        array2[1].zero();
        for (int n5 = 0; n5 < Moon.dejit.length; ++n5) {
            array2[n5 + 2].copy(array[n5]);
            array2[n5 + 2].scale(n2 * n2 / ((n5 + 1) * (n5 + 2)));
        }
        this.nov[this.head].eval(array2, Moon.dejit.offset);
        this.nov[this.head + 8].eval(array2, 8.0 + Moon.dejit.offset);
        array2[1].plus(array2[1], this.nov[this.head]);
        array2[1].minus(array2[1], this.nov[this.head + 8]);
        this.v.zero();
        for (int n6 = 0; n6 < 8; ++n6) {
            this.v.plus(this.v, this.ov[this.head + n6]);
        }
        array2[1].minus(array2[1], this.v);
        array2[1].scale(0.125);
        this.nov[this.head].eval(array2, Moon.dejit.offset);
        for (int n7 = 1; n7 < 9; ++n7) {
            this.nov[this.head + n7].eval(array2, n7 + Moon.dejit.offset);
            this.nov[this.head + n7 - 1].minus(this.nov[this.head + n7 - 1], this.nov[this.head + n7]);
        }
        array2[0].zero();
        this.v.zero();
        for (int n8 = 0; n8 < 7; ++n8) {
            this.v.plus(this.v, this.nov[this.head + n8]);
            array2[0].plus(array2[0], this.v);
        }
        this.v.zero();
        for (int n9 = 0; n9 < 7; ++n9) {
            this.v.plus(this.v, this.ov[this.head + n9]);
            array2[0].minus(array2[0], this.v);
        }
        array2[0].scale(0.125);
        if (n != 1.0) {
            System.out.println("Dejitter rescale: Not implemented!!!");
        }
        else {
            this.p.plus(this.p, array2[0]);
            final Point[] ov = this.ov;
            this.ov = this.nov;
            this.nov = ov;
        }
    }
    
    static {
        dejit = new BInterpolate(9);
    }
}
