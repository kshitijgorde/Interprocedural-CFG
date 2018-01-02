// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import ji.io.h;
import ji.util.i;
import ji.io.ac;

class hv implements ht
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    
    public hv(final int n, final double n2) {
        this.a = 1;
        this.b = 1000;
        this.e = 1;
        this.f = 1;
        this.c = this.e;
        this.d = this.f;
    }
    
    public hv(final ac ac, final int n, final String s) throws Exception {
        this.a = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        this.b = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        this.c = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        this.d = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        this.e = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        this.f = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        if (i.c(5)) {
            h.d(s, "WANG: Processing annotation rotation: angle: ".concat(String.valueOf(String.valueOf(this.a))));
            h.d(s, "WANG: Processing annotation rotation: scale: ".concat(String.valueOf(String.valueOf(this.b))));
            h.d(s, "WANG: Processing annotation rotation: nHRes: ".concat(String.valueOf(String.valueOf(this.c))));
            h.d(s, "WANG: Processing annotation rotation: nVRes: ".concat(String.valueOf(String.valueOf(this.d))));
            h.d(s, "WANG: Processing annotation rotation: nOrigHRes: ".concat(String.valueOf(String.valueOf(this.e))));
        }
    }
    
    public int a(final ac ac, final String s) throws Exception {
        final byte[] array = new byte[52];
        int n = 0;
        final byte[] a = hq.a(array, n, this.a);
        n += 4;
        final byte[] a2 = hq.a(a, n, this.b);
        n += 4;
        final byte[] a3 = hq.a(a2, n, this.c);
        n += 4;
        final byte[] a4 = hq.a(a3, n, this.d);
        n += 4;
        final byte[] a5 = hq.a(a4, n, this.e);
        n += 4;
        final byte[] a6 = hq.a(a5, n, this.f);
        ac.b(a6);
        return a6.length;
    }
    
    public void a() {
    }
    
    public int b() {
        return 52;
    }
    
    public String c() {
        return "OiAnoDat";
    }
    
    public int d() {
        if (this.a == 5 || this.a == 6 || this.a == 8 || this.a == 7) {
            return 2;
        }
        return 0;
    }
    
    public int e() {
        int n = 0;
        switch (this.a) {
            case 2:
            case 6: {
                n = 90;
                break;
            }
            case 3:
            case 7: {
                n = 180;
                break;
            }
            case 4:
            case 8: {
                n = 270;
                break;
            }
        }
        return n;
    }
}
