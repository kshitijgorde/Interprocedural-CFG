// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import ji.io.h;
import ji.util.i;
import ji.io.ac;

class hy implements ht
{
    public int a;
    public int b;
    public int c;
    public String d;
    
    public hy(final String d, final int b, final int a) {
        this.d = d;
        this.c = d.length();
        for (int i = 0; i < d.length(); ++i) {
            if (d.charAt(i) == '\n') {
                ++this.c;
            }
        }
        this.b = b;
        this.a = a;
    }
    
    public hy(final ac ac, final int n, final String s) throws Exception {
        this.a = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        ac.a(ac.r() + 4);
        this.b = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        this.c = hq.a(ac.h(), ac.h(), ac.h(), ac.h(), n);
        final byte[] array = new byte[this.c];
        try {
            for (int i = 0; i < this.c; ++i) {
                array[i] = (byte)ac.h();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int j = 0; j < array.length; ++j) {
            if (array[j] == 0) {
                this.c = j + 1;
                break;
            }
        }
        this.d = new String(array, 0, this.c);
        if (i.c(5)) {
            h.d(s, "WANG: Processing annotation Text: ".concat(String.valueOf(String.valueOf(this.d))));
        }
    }
    
    public void a() {
        this.d = null;
    }
    
    public int b() {
        return 20 + (this.c + 1);
    }
    
    public String c() {
        return "OiAnText";
    }
    
    public int a(final ac ac, final String s) throws Exception {
        final byte[] array = new byte[20 + (this.c + 1)];
        int n = 0;
        final byte[] a = hq.a(array, n, this.a);
        n += 4;
        final byte[] a2 = hq.a(a, n, 1000);
        n += 4;
        final byte[] a3 = hq.a(a2, n, this.b);
        n += 4;
        final byte[] a4 = hq.a(a3, n, this.c + 1);
        n += 4;
        final byte[] bytes = this.d.getBytes();
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] == 10) {
                a4[n] = 13;
                a4[n + 1] = bytes[i];
                n += 2;
            }
            else {
                a4[n] = bytes[i];
                ++n;
            }
        }
        ac.b(a4);
        return a4.length;
    }
}
