// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt.barcode2;

import java.util.List;
import java.util.Arrays;

class FourColorNode implements Element
{
    int a;
    int b;
    int c;
    int d;
    byte e;
    Object f;
    int g;
    byte h;
    FourColorNode i;
    FourColorNode j;
    FourColorNode k;
    int l;
    static final /* synthetic */ boolean m;
    
    public FourColorNode(final byte e, final int g, final Object f, final FourColorNode k) {
        this.l = 0;
        if (!FourColorNode.m && (FourColorTree.b(e) < 0 || FourColorTree.b(e) >= 7)) {
            throw new AssertionError();
        }
        this.e = e;
        this.g = g;
        this.f = f;
        this.h = 1;
        this.k = k;
        if (e == 1) {
            this.a += g;
        }
        if (e == 2) {
            this.b += g;
        }
        if (e == 4) {
            this.c += g;
        }
        if (e == 8) {
            this.d += g;
        }
    }
    
    public Object a() {
        return this.f;
    }
    
    public void a(final Object f) {
        this.f = f;
    }
    
    public byte b() {
        return this.e;
    }
    
    final int a(final byte b) {
        int n = 0;
        if ((b & 0x1) != 0x0) {
            n += this.a;
        }
        if ((b & 0x2) != 0x0) {
            n += this.b;
        }
        if ((b & 0x4) != 0x0) {
            n += this.c;
        }
        if ((b & 0x8) != 0x0) {
            n += this.d;
        }
        return n;
    }
    
    final int b(final byte b) {
        return ((b & this.e) > 0) ? this.g : 0;
    }
    
    final void c() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        if (this.i != null) {
            this.a += this.i.a;
            this.b += this.i.b;
            this.c += this.i.c;
            this.d += this.i.d;
        }
        if (this.j != null) {
            this.a += this.j.a;
            this.b += this.j.b;
            this.c += this.j.c;
            this.d += this.j.d;
        }
        if (this.e == 1) {
            this.a += this.g;
        }
        if (this.e == 2) {
            this.b += this.g;
        }
        if (this.e == 4) {
            this.c += this.g;
        }
        if (this.e == 8) {
            this.d += this.g;
        }
    }
    
    public String toString() {
        return this.a(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));
    }
    
    String a(final List list) {
        final StringBuffer sb = new StringBuffer();
        this.a(0, sb, list);
        return sb.toString();
    }
    
    void a(final int n, final StringBuffer sb, final List list) {
        if (this.i != null) {
            this.i.a(n + 1, sb, list);
        }
        for (int i = 0; i < n; ++i) {
            sb.append("   ");
        }
        sb.append(list.get(FourColorTree.b(this.e)));
        sb.append(" [").append(this.g).append("]");
        if (this.f != null) {
            sb.append(": ");
            if (this.f instanceof FourColorNode) {
                sb.append("<Node>");
            }
            else {
                sb.append(this.f);
            }
        }
        sb.append("\n");
        if (this.j != null) {
            this.j.a(n + 1, sb, list);
        }
    }
    
    public void a(final int l) {
        this.l = l;
    }
    
    public int d() {
        return this.l;
    }
    
    public Element e() {
        return FourColorTree.a(this);
    }
    
    public Element f() {
        return FourColorTree.b(this);
    }
    
    static {
        m = !FourColorNode.class.desiredAssertionStatus();
    }
}
