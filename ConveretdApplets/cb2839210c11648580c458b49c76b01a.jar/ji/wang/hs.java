// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import ji.io.h;
import ji.util.i;
import ji.io.ac;
import ji.awt.d5;

class hs implements ht
{
    public int a;
    public d5[] b;
    
    public hs() {
    }
    
    public hs(final ac ac, final int n, final String s) throws Exception {
        this.a = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        final int a = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        this.b = new d5[a];
        for (int i = 0; i < a; ++i) {
            this.b[i] = new d5(ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24), ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24));
            if (i.c(5)) {
                h.d(s, "WANG: Processing annotation point: ".concat(String.valueOf(String.valueOf(this.b[i]))));
            }
        }
    }
    
    public void a() {
        try {
            if (this.b != null) {
                for (int i = 0; i < this.b.length; ++i) {
                    this.b[i] = null;
                }
                this.b = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public int b() {
        return 8 + this.b.length * 8;
    }
    
    public String c() {
        return "OiAnoDat";
    }
    
    public int a(final ac ac, final String s) throws Exception {
        final int length = this.b.length;
        final byte[] array = new byte[8 + length * 8];
        int n = 0;
        final byte[] a = hq.a(array, n, this.a);
        n += 4;
        byte[] array2 = hq.a(a, n, length);
        n += 4;
        for (int i = 0; i < length; ++i) {
            final byte[] a2 = hq.a(array2, n, (int)this.b[i].a);
            n += 4;
            array2 = hq.a(a2, n, (int)this.b[i].b);
            n += 4;
        }
        ac.b(array2);
        return array2.length;
    }
}
