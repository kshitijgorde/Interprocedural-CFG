import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class e
{
    public static final int ob = 0;
    public static final int pb = 1;
    public static final int qb = 2;
    public static final int rb = 3;
    public static final int sb = 4;
    public static int tb;
    private Polygon _;
    private Polygon a;
    private Polygon b;
    private Color c;
    private Color d;
    private Rectangle bounds;
    private boolean e;
    private boolean f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private double n;
    private static String o = "\u2cca";
    private static String p = "\u2c9a";
    private static String q = "\u2c92\u2c94\u2c93\u2c83";
    
    public e(final Color d, final Color c, final int i, final int l, final int m, final int n, final boolean b) {
        this.i = i;
        this.d = d;
        this.c = c;
        this.b(45.0);
        final int n2 = n - 1;
        this.g = n2;
        this.h = n2;
        final Polygon _ = _(i);
        this._ = new Polygon(_.xpoints, _.ypoints, _.npoints);
        final Point a = a(this._);
        for (int j = 0; j < this._.npoints; ++j) {
            final int[] xpoints = this._.xpoints;
            final int n3 = j;
            xpoints[n3] += l - a.x;
            final int[] ypoints = this._.ypoints;
            final int n4 = j;
            ypoints[n4] += m - a.y;
        }
        this._(this.l = l, this.m = m);
        if (i == 3) {
            this.b = new Polygon(this._.xpoints, this._.ypoints, this._.npoints);
        }
        this.a = new Polygon(this._.xpoints, this._.ypoints, this._.npoints);
        this._();
        if (b) {
            this.a();
        }
        this.bounds = this.getBounds();
    }
    
    public e(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, e.o);
        try {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), e.p);
            final int n = stringTokenizer2.countTokens() / 2;
            final int[] array = new int[n];
            final int[] array2 = new int[n];
            for (int i = 0; i < n; ++i) {
                array[i] = Integer.parseInt(stringTokenizer2.nextToken());
                array2[i] = Integer.parseInt(stringTokenizer2.nextToken());
            }
            this.b(new Polygon(array, array2, n), new Color(Integer.parseInt(stringTokenizer.nextToken())), new Color(Integer.parseInt(stringTokenizer.nextToken())), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), stringTokenizer.nextToken().equals(e.q));
        }
        catch (NumberFormatException ex) {
            System.out.println(ex);
        }
    }
    
    public e(final Polygon polygon, final Color color, final Color color2, final int n, final int n2, final boolean b) {
        this.b(polygon, color, color2, n, n2, b);
    }
    
    private void b(final Polygon polygon, final Color color, final Color color2, final int i, final int n, final boolean b) {
        this.i = i;
        this.b(color, color2);
        this._ = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
        this.a = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
        this.bounds = this.getBounds();
        this.b(45.0);
        final int n2 = n - 1;
        this.g = n2;
        this.h = n2;
        final Point a = a(this._);
        this.l = a.x;
        this.m = a.y;
        this._(this.l, this.m);
        if (this.e) {
            this.a();
        }
    }
    
    public void b(final Color d, final Color c) {
        this.d = d;
        this.c = c;
    }
    
    public void _() {
        if (++this.g >= (int)(6.283190000000001 / this.n)) {
            this.g = 0;
        }
        final Point a = a(this.a);
        final int x = a.x;
        final int y = a.y;
        final double cos = Math.cos(this.n * this.g);
        final double sin = Math.sin(this.n * this.g);
        for (int i = 0; i < this.a.npoints; ++i) {
            final double n = this._.xpoints[i] - this.l;
            final double n2 = this._.ypoints[i] - this.m;
            this.a.xpoints[i] = (int)Math.round(n * cos - n2 * sin) + x;
            this.a.ypoints[i] = (int)Math.round(n * sin + n2 * cos) + y;
        }
    }
    
    public void b(final Polygon polygon) {
        System.arraycopy(polygon.xpoints, 0, this.a.xpoints, 0, polygon.npoints);
        System.arraycopy(polygon.ypoints, 0, this.a.ypoints, 0, polygon.npoints);
    }
    
    public void a(final int n, final int n2) {
        this.a.translate(n, n2);
        this._.translate(n, n2);
        if (this.b != null) {
            this.b.translate(n, n2);
        }
        final Point b = this.b();
        this._(b.x, b.y);
        this.l = b.x;
        this.m = b.y;
    }
    
    public void move(final int n, final int n2) {
        this.a.translate(n, n2);
        final Point b = this.b();
        this._(b.x, b.y);
    }
    
    public void b(final int n, final int n2) {
        this.a.translate(-(this.j - n), -(this.k - n2));
        this._(n, n2);
    }
    
    public void b() {
        if (this.i == 3) {
            System.arraycopy(this.b.xpoints, 0, this._.xpoints, 0, this.b.npoints);
            System.arraycopy(this.b.ypoints, 0, this._.ypoints, 0, this.b.npoints);
        }
        System.arraycopy(this._.xpoints, 0, this.a.xpoints, 0, this._.npoints);
        System.arraycopy(this._.ypoints, 0, this.a.ypoints, 0, this._.npoints);
        final Point b = this.b();
        this.l = b.x;
        this.m = b.y;
        this._(b.x, b.y);
        this.g = this.h;
        this._();
        this.e = false;
        this.f = false;
    }
    
    public void a() {
        this.e = !this.e;
        final Point a = a(this.a);
        final Point a2 = a(this._);
        for (int i = 0; i < this.a.npoints; ++i) {
            final int[] xpoints = this.a.xpoints;
            final int n = i;
            xpoints[n] -= a.x;
            this.a.xpoints[i] = -this.a.xpoints[i] + a.x;
            final int[] xpoints2 = this._.xpoints;
            final int n2 = i;
            xpoints2[n2] -= a2.x;
            this._.xpoints[i] = -this._.xpoints[i] + a2.x;
        }
        switch (this.g %= 4) {
            case 1: {
                this.g = 3;
            }
            case 3: {
                this.g = 1;
            }
            default: {}
        }
    }
    
    public boolean b() {
        return this.e;
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(this.d);
        graphics.fillPolygon(this.a);
        graphics.setColor(this.c);
        graphics.drawPolygon(this.a);
    }
    
    public String a() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.a(this.a));
        sb.append(',');
        sb.append(this.d.getRGB()).append(',');
        sb.append(this.c.getRGB()).append(',');
        sb.append(this.i).append(',');
        sb.append(this.g).append(',');
        sb.append(this.e);
        return sb.toString();
    }
    
    public String b() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.a(this._));
        sb.append(',');
        sb.append(this.d.getRGB()).append(',');
        sb.append(this.c.getRGB()).append(',');
        sb.append(this.i).append(',');
        sb.append(this.h).append(',');
        sb.append(this.e);
        return sb.toString();
    }
    
    private String a(final Polygon polygon) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < polygon.npoints; ++i) {
            sb.append(polygon.xpoints[i]);
            sb.append('|');
            sb.append(polygon.ypoints[i]).append('|');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    
    public void b(final double n) {
        this.n = n * 0.017453292519943295;
    }
    
    public void _(final int j, final int k) {
        this.j = j;
        this.k = k;
    }
    
    public int getID() {
        return this.i;
    }
    
    public Point b() {
        return a(this.a);
    }
    
    public static Point a(final Polygon polygon) {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < polygon.npoints; ++i) {
            n += polygon.xpoints[i];
            n2 += polygon.ypoints[i];
        }
        return new Point(n / polygon.npoints, n2 / polygon.npoints);
    }
    
    public int a() {
        return this.g;
    }
    
    public Polygon b() {
        return new Polygon(this.a.xpoints, this.a.ypoints, this.a.npoints);
    }
    
    public boolean _() {
        return this.f;
    }
    
    public void _(final boolean f) {
        this.f = f;
    }
    
    public Rectangle getBounds() {
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < this.a.npoints; ++i) {
            final int n = this.a.xpoints[i];
            min = Math.min(min, n);
            max = Math.max(max, n);
            final int n2 = this.a.ypoints[i];
            min2 = Math.min(min2, n2);
            max2 = Math.max(max2, n2);
        }
        if (this.bounds == null) {
            this.bounds = new Rectangle();
        }
        this.bounds.x = min;
        this.bounds.y = min2;
        this.bounds.width = max - min;
        this.bounds.height = max2 - min2;
        return this.bounds;
    }
    
    public boolean inside(final int n, final int n2) {
        return this.b().contains(n, n2);
    }
    
    public static Polygon _(final int n) {
        final int tb = e.tb;
        final int n2 = tb / 2;
        final int n3 = tb / 4;
        final int n4 = n2 + n3;
        Polygon polygon = null;
        switch (n) {
            case 0: {
                polygon = new Polygon(new int[] { 0, n2, tb, 0 }, new int[] { n2, 0, n2, n2 }, 3);
                break;
            }
            case 1: {
                polygon = new Polygon(new int[] { 0, n2, n2, 0 }, new int[] { 0, 0, n2, 0 }, 3);
                break;
            }
            case 2: {
                polygon = new Polygon(new int[] { 0, n2, n3, 0 }, new int[] { 0, 0, n3, 0 }, 3);
                break;
            }
            case 3: {
                polygon = new Polygon(new int[] { 0, n2, n4, n3, 0 }, new int[] { 0, 0, n3, n3, 0 }, 4);
                break;
            }
            case 4: {
                polygon = new Polygon(new int[] { n3, n2, n3, 0, n3 }, new int[] { 0, n3, n2, n3, 0 }, 4);
                break;
            }
        }
        return polygon;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x12CE6);
        }
        return new String(array);
    }
    
    static {
        e.o = _(e.o);
        e.p = _(e.p);
        e.q = _(e.q);
    }
}
