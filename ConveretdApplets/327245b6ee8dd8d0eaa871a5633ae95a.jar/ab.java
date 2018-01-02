import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class ab extends c
{
    blaze3d a;
    z b;
    Vector c;
    int d;
    public int e;
    
    ab() {
        this.d = -1;
        this.e = -1;
    }
    
    ab(final blaze3d a) {
        this.d = -1;
        this.e = -1;
        this.a = a;
        this.c = new Vector();
    }
    
    public void a(int d, int e) {
        final boolean l = c.l;
        if (this.b == null) {
            return;
        }
        if (d > e) {
            final int n = e;
            e = d;
            d = n;
        }
        final int h = this.b.h();
        Label_0050: {
            if (d < 0) {
                d = 0;
                if (!l) {
                    break Label_0050;
                }
            }
            if (d > h) {
                d = h;
            }
        }
        Label_0068: {
            if (e < 0) {
                e = 0;
                if (!l) {
                    break Label_0068;
                }
            }
            if (e > h) {
                e = h;
            }
        }
        this.d = d;
        this.e = e;
        if (this.b != null) {
            this.b.k();
        }
    }
    
    void a(final z b) {
        if (this.b != b) {
            final z b2 = this.b;
            this.b = b;
            if (b2 != null) {
                b2.a(false, b);
            }
            if (b != null) {
                b.a(true, b2);
            }
            final au au = new au();
            au.a((b2 == null) ? d.d : b2.a());
            au.a((b == null) ? d.d : b.a());
            int n = 0;
            while (true) {
                Label_0121: {
                    if (!c.l) {
                        break Label_0121;
                    }
                    ((c)this.c.elementAt(n)).c(z.g, au, false);
                    ++n;
                }
                if (n < this.c.size()) {
                    continue;
                }
                break;
            }
        }
    }
    
    void a(final int n, final String s, final String s2) {
        if (this.b != null) {
            this.b.a(n, s, s2);
        }
    }
}
