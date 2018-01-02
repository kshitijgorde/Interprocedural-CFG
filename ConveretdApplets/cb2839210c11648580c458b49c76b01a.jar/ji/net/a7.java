// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import ji.io.h;
import ji.util.d;
import ji.io.ac;

public class a7
{
    public long a;
    public long b;
    public long c;
    public long d;
    public long e;
    public long f;
    public long g;
    public long h;
    public String i;
    public String j;
    public String k;
    String l;
    
    public a7(final String l) {
        this.a = 0L;
        this.b = 0L;
        this.c = 0L;
        this.d = 0L;
        this.e = 0L;
        this.f = 0L;
        this.g = 0L;
        this.h = 0L;
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = null;
        this.l = l;
    }
    
    public String toString() {
        return this.a();
    }
    
    public void a(final ac ac) throws Exception {
        final long r = ac.r();
        ac.a(ac.w());
        this.a(ac, this.i);
        this.a(ac, this.j);
        this.a(ac, this.k);
        ac.b(this.a);
        ac.b(this.b);
        ac.b(this.c);
        ac.b(this.d);
        ac.b(this.e);
        ac.b(this.f);
        ac.b(this.g);
        ac.b(this.h);
        ac.b((int)(ac.r() - r));
        ac.a(r);
    }
    
    public void b(final ac ac) throws Exception {
        final long r = ac.r();
        ac.u();
        this.i = this.c(ac);
        this.j = this.c(ac);
        this.k = this.c(ac);
        this.a = ac.o();
        this.b = ac.o();
        this.c = ac.o();
        this.d = ac.o();
        this.e = ac.o();
        this.f = ac.o();
        this.g = ac.o();
        this.h = ac.o();
        ac.a(r);
    }
    
    public void a(final a7 a7) {
        if (a7 != null) {
            this.b += a7.b - a7.a;
            this.d += a7.d - a7.c;
            this.e += a7.e;
            this.f += a7.f;
            this.g += a7.g;
        }
    }
    
    public final String a() {
        final double n = (this.b - this.a) / 1000.0;
        final double n2 = (this.d - this.c) / 1000.0;
        double n3 = 0.0;
        if (n2 > 0.0) {
            n3 = this.e / n2;
        }
        String s = "".concat(String.valueOf(String.valueOf(n)));
        final int n4 = 4;
        String value = "";
        if (this.g > 0) {
            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(1176)))).append(" = ").append(ji.util.d.a(this.g, this.l)).append(": ")));
        }
        String s2;
        if (n == 0) {
            final long n5 = this.b - this.a;
            if (n5 == 0) {
                s2 = this.a(660);
            }
            else {
                final String concat = "".concat(String.valueOf(String.valueOf(n5)));
                if (n5 == 1) {
                    s2 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(this.a(661)))))));
                }
                else {
                    s2 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(this.a(662)))))));
                }
            }
        }
        else {
            final int index = s.indexOf(".");
            if (index > 0) {
                s = s.substring(0, Math.min(index + n4, s.length()));
            }
            if (n == 1.0) {
                s2 = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(this.a(663)))))));
            }
            else {
                s2 = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(this.a(664)))))));
            }
        }
        String s3;
        if (n2 == 0.0) {
            final long n6 = this.d - this.c;
            if (n6 == 0) {
                s3 = this.a(660);
            }
            else {
                final String concat2 = "".concat(String.valueOf(String.valueOf(n6)));
                if (n6 == 1) {
                    s3 = String.valueOf(String.valueOf(concat2)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(this.a(661)))))));
                }
                else {
                    s3 = String.valueOf(String.valueOf(concat2)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(this.a(662)))))));
                }
            }
        }
        else {
            String s4 = "".concat(String.valueOf(String.valueOf(n2)));
            final int index2 = s4.indexOf(".");
            if (index2 > 0) {
                s4 = s4.substring(0, Math.min(index2 + n4, s4.length()));
            }
            if (n2 == 1) {
                s3 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(this.a(663)))))));
            }
            else {
                s3 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(this.a(664)))))));
            }
        }
        String value2 = null;
        if (n3 > 0.0) {
            value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.a((long)(int)n3, this.l)))).append("/").append(this.a(663))));
        }
        String value3 = "";
        if (this.f > 0) {
            value3 = String.valueOf(String.valueOf(new StringBuffer(": ").append(this.a(1177)).append(" = ").append(this.a(this.f))));
        }
        String s5;
        if (value2 != null) {
            s5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(this.a(666)).append(" = ").append(s2).append(": ").append(this.a(667)).append(" = ").append(s3).append(value3).append(": ").append(this.a(668)).append(" = ").append(value2)));
        }
        else {
            s5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(this.a(666)).append(" = ").append(s2).append(": ").append(this.a(667)).append(" = ").append(s3).append(value3)));
        }
        return s5;
    }
    
    private String a(final int n) {
        return ji.util.d.b(n, this.l);
    }
    
    private final String a(final long n) {
        String s = "0";
        if (n > 0) {
            final double n2 = n / 1000.0;
            String s2 = "".concat(String.valueOf(String.valueOf(n2)));
            final int index = s2.indexOf(".");
            if (index > 0) {
                s2 = s2.substring(0, Math.min(index + 4, s2.length()));
            }
            if (n2 == 1.0) {
                s = String.valueOf(String.valueOf(s2)).concat(" second");
            }
            else {
                s = String.valueOf(String.valueOf(s2)).concat(" seconds");
            }
        }
        return s;
    }
    
    private final void a(final ac ac, final String s) throws Exception {
        final byte[] bytes = this.a(s).getBytes("UTF8");
        ac.b(bytes.length);
        ac.b(bytes);
    }
    
    private final String c(final ac ac) throws Exception {
        final int p = ac.p();
        if (p < 102400) {
            final byte[] array = new byte[p];
            ac.a(array);
            return new String(array, "UTF8");
        }
        ji.io.h.d(this.l, String.valueOf(String.valueOf(new StringBuffer("*** Probable corrupted file, string too long (").append(p).append(") ***"))));
        return "";
    }
    
    private final String a(final String s) {
        if (s == null) {
            return "";
        }
        return s;
    }
}
