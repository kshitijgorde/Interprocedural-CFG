import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class j
{
    public static final int sb = 11;
    public static final int tb = 500;
    public static final int _ = 100;
    private int width;
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private o za;
    private p rb;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private boolean n;
    private Color o;
    private static String w = "\uf507\uf517\uf51b\uf506\uf511\uf518\uf516\uf52b\uf518\uf515\uf516";
    private static String x = "\uf51c\uf51d\uf507\uf517\uf51b\uf506\uf511\uf52b\uf518\uf515\uf516";
    private static String y = "\uf518\uf51d\uf502\uf511\uf507\uf518\uf516\uf52b\uf518\uf515\uf516";
    private static String z = "\uf554\uf554";
    
    public j(final int width, final p rb, final o o) {
        this.f = -1;
        this.o = new Color(3381759);
        this.k = o.m(j.w);
        this.l = o.m(j.x);
        this.m = o.m(j.y);
        this.rb = rb;
        this.width = width;
        this.reset();
    }
    
    public void reset() {
        if (this.c > this.e) {
            this.e = this.c;
        }
        this.a = 0;
        this.b = 7;
        this.c = 0;
        l.db = 1;
        this.a();
        this.b();
        this._();
        this.n();
        this.n = true;
    }
    
    public void a() {
        this.d = 0;
    }
    
    public void _(final boolean n) {
        this.n = n;
    }
    
    public Point b() {
        if (this.n) {
            if (this.d < 100) {
                return new Point(3, 5);
            }
            if (this.d < 200) {
                return new Point(4, 6);
            }
            if (this.d < 300) {
                return new Point(5, 7);
            }
            if (this.d < 400) {
                return new Point(6, 8);
            }
            return new Point(7, 9);
        }
        else {
            if (this.d < 100) {
                return new Point(4, 4);
            }
            if (this.d < 200) {
                return new Point(5, 5);
            }
            if (this.d < 300) {
                return new Point(6, 6);
            }
            if (this.d < 400) {
                return new Point(7, 7);
            }
            return new Point(8, 8);
        }
    }
    
    public void a(final int n) {
        this.b += n;
        this.n();
    }
    
    public int i() {
        return this.b;
    }
    
    public void c() {
        this.c += 10 * l.db;
        this.d += 10;
        this.b();
    }
    
    public void l(final int n) {
        this.c += n;
        this.b();
    }
    
    public void m(final int n) {
        this.c += n * l.db;
        this.d += 10;
        this.b();
    }
    
    public void d() {
        ++this.a;
        this.a(1);
    }
    
    public int j() {
        return this.a;
    }
    
    public int k() {
        if (this.c > this.e) {
            this.e = this.c;
        }
        return this.e;
    }
    
    private void b() {
        this.h = String.valueOf(this.k) + j.z + this.c;
    }
    
    private void _() {
        this.i = String.valueOf(this.l) + j.z + this.e;
    }
    
    private void n() {
        this.j = String.valueOf(this.m) + j.z + this.b;
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(2, 357, this.width - 4, 16);
        graphics.setColor(this.o);
        graphics.fillRect(0, 355, this.width, 2);
        graphics.fillRect(0, 373, this.width, 2);
        graphics.setColor(Color.blue);
        graphics.drawString(this.h, 40, 370);
        if (this.f != this.e) {
            this.g = this.rb.a(this.i, true, graphics);
            this.f = this.e;
        }
        graphics.drawString(this.i, this.g, 370);
        graphics.drawString(this.j, this.width - 95, 370);
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEF574);
        }
        return new String(array);
    }
    
    static {
        j.w = b(j.w);
        j.x = b(j.x);
        j.y = b(j.y);
        j.z = b(j.z);
    }
}
