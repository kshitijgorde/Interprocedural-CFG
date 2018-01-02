// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.util.Iterator;

public class c implements M
{
    public static final int c = 1;
    public static final int X = 32773;
    public static final int h = 2;
    public static final int n = 3;
    public static final int g = 4;
    public static final int Z = 5;
    public static final int k = 7;
    public static final int Y = 32946;
    private int o;
    private boolean a;
    private boolean _;
    private boolean b;
    private boolean l;
    private int W;
    private int d;
    private Iterator f;
    private p[] i;
    private boolean m;
    private R j;
    private int e;
    
    public c() {
        this.o = 1;
        this.a = false;
        this._ = true;
        this.b = false;
        this.l = false;
        this.m = true;
        this.j = null;
        this.e = -1;
    }
    
    public int N() {
        return this.o;
    }
    
    public void K(final int o) {
        switch (o) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 32773:
            case 32946: {
                this.o = o;
            }
            default: {
                throw new Error(z.B.m.A("TIFFEncodeParam0"));
            }
        }
    }
    
    public boolean S() {
        return this.a;
    }
    
    public void K(final boolean a) {
        this.a = a;
    }
    
    public boolean U() {
        return this._;
    }
    
    public void I(final boolean _) {
        this._ = _;
    }
    
    public boolean V() {
        return this.b;
    }
    
    public void J(final boolean b) {
        this.b = b;
    }
    
    public boolean O() {
        return this.l;
    }
    
    public void L(final boolean l) {
        this.l = l;
    }
    
    public void C(final int w, final int d) {
        this.W = w;
        this.d = d;
    }
    
    public int P() {
        return this.W;
    }
    
    public int X() {
        return this.d;
    }
    
    public synchronized void A(final Iterator f) {
        this.f = f;
    }
    
    public synchronized Iterator R() {
        return this.f;
    }
    
    public void J(final int e) {
        if (e < 1 && e > 9 && e != -1) {
            throw new Error(z.B.m.A("TIFFEncodeParam1"));
        }
        this.e = e;
    }
    
    public int T() {
        return this.e;
    }
    
    public void H(final boolean m) {
        this.m = m;
    }
    
    public boolean Q() {
        return this.m;
    }
    
    public void A(R j) {
        if (j != null) {
            j = (R)j.clone();
            j.F(false);
            j.E(false);
        }
        this.j = j;
    }
    
    public R M() {
        if (this.j == null) {
            (this.j = new R()).F(false);
            this.j.G(true);
            this.j.E(false);
        }
        return this.j;
    }
    
    public void A(final p[] i) {
        this.i = i;
    }
    
    public p[] W() {
        return this.i;
    }
}
