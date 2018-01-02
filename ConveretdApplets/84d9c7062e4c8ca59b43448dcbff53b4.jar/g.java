import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class g
{
    private static final String a;
    public d d;
    public String c;
    private String b;
    private int e;
    
    public g(final String c) {
        this.d = new d();
        this.c = c;
    }
    
    public boolean e(final String s) {
        d d = this.d;
        this.b(s);
        int n = 0;
        while (this.d()) {
            final d b = d.b(this.a());
            if (b == null) {
                return d != this.d && d.b == null;
            }
            d = b;
            ++n;
        }
        return d != this.d && d.b == null;
    }
    
    public boolean d(final String s) {
        if (s.equals("/") && this.d.b != null) {
            return true;
        }
        d d = this.d;
        this.b(s);
        int n = 0;
        while (this.d()) {
            final d b = d.b(this.a());
            if (b == null) {
                return false;
            }
            d = b;
            ++n;
        }
        return true;
    }
    
    public void c(final String s) {
        d d = this.d;
        this.b(s);
        while (this.d()) {
            d = d.c(this.a());
        }
        d.b = null;
    }
    
    public void a(final String s) {
        d d = this.d;
        d d2 = null;
        final int[] array = new int[30];
        final d[] array2 = new d[30];
        int n = 0;
        this.b(s);
        while (this.d()) {
            final float a = d.a(this.a());
            if ((int)(a * 10.0f) % 10 != 0) {
                return;
            }
            array[n] = (int)a;
            array2[n] = d;
            d2 = d;
            d = (d)d2.b.elementAt(array[n]);
            ++n;
        }
        if (d2 != null) {
            for (int i = n - 1; i >= 0; --i) {
                array2[i].a(array[i]);
                if (((array2[i].b != null) ? array2[i].b.size() : 0) > 0) {
                    break;
                }
            }
        }
    }
    
    public String[] c() {
        final Vector vector = new Vector();
        this.a(vector, this.d, "");
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public boolean b() {
        return this.d.b == null || this.d.b.size() == 0;
    }
    
    private void a(final Vector vector, final d d, final String s) {
        if (d.b == null) {
            vector.addElement(s);
        }
        else {
            for (int size = d.b.size(), i = 0; i < size; ++i) {
                final d d2 = d.b.elementAt(i);
                final StringBuffer sb = new StringBuffer();
                sb.append(s);
                sb.append(d2.a);
                this.a(vector, d2, new String(sb));
            }
        }
    }
    
    private void b(final String b) {
        this.b = b;
        this.e = 0;
    }
    
    private String a() {
        final int length = this.b.length();
        if (this.e >= length) {
            return g.a;
        }
        int e;
        int n;
        for (n = (e = this.e); e < length && this.b.charAt(e) != '/'; ++e) {}
        if (n == e) {
            this.e = e + 1;
            return "/";
        }
        this.e = e;
        return this.b.substring(n, e);
    }
    
    private boolean d() {
        return this.e < this.b.length();
    }
    
    static {
        a = new String();
    }
}
