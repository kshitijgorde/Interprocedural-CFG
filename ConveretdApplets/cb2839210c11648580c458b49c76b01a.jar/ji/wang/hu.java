// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import ji.io.h;
import ji.util.i;
import ji.io.ac;

class hu implements ht
{
    public int a;
    public String b;
    public int c;
    public boolean d;
    
    public hu(final int a, final String b, final boolean d) {
        this.a = a;
        this.b = b;
        this.c = b.length();
        this.d = d;
    }
    
    public hu(final ac ac, final int n, final String s) throws Exception {
        this.a = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        this.c = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        this.d = (ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24) == 1);
        final StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < this.c; ++i) {
                final char c = (char)ac.h();
                if (c != '\0') {
                    sb.append(c);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.b = sb.toString();
        if (i.c(5)) {
            h.d(s, "WANG: Processing annotation Line tooltip: ".concat(String.valueOf(String.valueOf(this.b))));
        }
    }
    
    public void a() {
        this.b = null;
    }
    
    public int a(final ac ac, final String s) throws Exception {
        final byte[] array = new byte[12 + this.c];
        int n = 0;
        final byte[] a = hq.a(array, n, this.a);
        n += 4;
        final byte[] a2 = hq.a(a, n, this.c);
        n += 4;
        final byte[] a3 = hq.a(a2, n, this.d ? 1 : 0);
        n += 4;
        final byte[] bytes = this.b.getBytes();
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] == 10) {
                a3[n] = 13;
                a3[n + 1] = bytes[i];
                n += 2;
            }
            else {
                a3[n] = bytes[i];
                ++n;
            }
        }
        ac.b(a3);
        return a3.length;
    }
    
    public String c() {
        return "v1line";
    }
    
    public int b() {
        return 12 + this.c;
    }
}
