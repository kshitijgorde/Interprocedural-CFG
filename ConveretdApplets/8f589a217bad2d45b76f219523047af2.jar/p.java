import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class p
{
    public int p;
    public a[] p;
    public Vector p;
    
    public p() {
        this.p = new a[4];
        int n = 0;
        do {
            this.p[n] = new a();
        } while (++n < 4);
        this.p = new Vector();
    }
    
    public final void p(final l l, final l i) {
        if (Math.ceil(l.d) == Math.ceil(i.d)) {
            return;
        }
        if (this.p > 3) {}
        final a a = this.p[this.p++];
        final double n = i.d - l.d;
        final double n2 = i.p - l.p;
        a.d = l.p;
        a.a = n2 / n;
        a.p = i.d;
        if (a.p < 0.0) {}
        a.n = l.l;
        a.v = l.b;
        a.i = l.c;
        final double j = (i.l - l.l) / n;
        final double b = (i.b - l.b) / n;
        final double c = (i.c - l.c) / n;
        if (n2 > 1.0E-4) {
            final double n3 = (i.l - l.l) / n2;
            final double n4 = (i.b - l.b) / n2;
            final double n5 = (i.c - l.c) / n2;
        }
        a.l = j;
        a.b = b;
        a.c = c;
        final double n6 = Math.ceil(l.d) - l.d;
        final double n7 = n6 * a.a;
        if (n6 > 0.0) {
            final a a2 = a;
            a2.n += n6 * j;
            final a a3 = a;
            a3.v += n6 * b;
            final a a4 = a;
            a4.i += n6 * c;
            final a a5 = a;
            a5.d += n7;
        }
        int n8;
        for (n8 = 0; n8 < this.p.size() && ((a)this.p.elementAt(n8)).d < a.d; ++n8) {}
        if (n8 != this.p.size()) {
            if (Math.ceil(((a)this.p.elementAt(n8)).d) == Math.ceil(a.d) && a.a > ((a)this.p.elementAt(n8)).a) {
                ++n8;
            }
            this.p.insertElementAt(a, n8);
            return;
        }
        this.p.addElement(a);
    }
}
