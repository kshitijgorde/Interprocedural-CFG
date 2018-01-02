// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;

final class cc implements bk
{
    public final int a(final q q) {
        return 0;
    }
    
    public final void a(final bd cp) {
        try {
            ((bd)cp).a();
        }
        catch (cp cp) {
            boolean b = false;
            try {
                synchronized (cp.c) {
                    b = true;
                    final cN cn = new cN();
                    for (cp cp2 = cp.c; cp2 != null; cp2 = cp2.d) {
                        cn.a(cp2.a);
                    }
                    for (cp cp3 = cp.c; cp3 != null; cp3 = cp3.d) {
                        aF.a(32, "RtryM: handling exception ", cp3);
                        final q a2;
                        final aj a = (a2 = cp3.a).a();
                        if (!cn.b(a2) || (a.b && a.a >= 65537 && a2.b > 0) || ((!a.b || a.a <= 65536) && a2.b > 4) || cp3.b.l) {
                            cp3.c = null;
                        }
                        else if (a2.f() != null) {
                            if (aj.d) {
                                a2.f().c();
                                cp3.b.a(true);
                            }
                            cp3.c = null;
                        }
                        else {
                            if (a2.e() != null && cp3.f) {
                                if (a.b && a.a >= 65537) {
                                    a(a2, "Expect", "100-continue");
                                }
                                else {
                                    a2.a = 5000L << a2.b;
                                }
                            }
                            if (cp3.d != null && cp3.d.a.e() != null && (!a.b || a.a < 65537) && cp3.f) {
                                a(a2, "Connection", "close");
                            }
                            if (a.b && a.a >= 65537 && cp3.f) {
                                a2.c = true;
                            }
                            a2.c = true;
                            new StringBuffer().append("RtryM: Retrying request '").append(a2.b()).append(" ").append(a2.c()).append("'").toString();
                            if (cp3.f) {
                                final q q = a2;
                                ++q.b;
                            }
                            cp3.b.a.a(a2, a.a(a2, cp3.b.b));
                            cp3.e = null;
                            cp3.c = null;
                        }
                    }
                }
            }
            catch (NullPointerException ex) {
                if (b) {
                    throw ex;
                }
            }
            catch (dh dh) {
                throw new IOException(dh.getMessage());
            }
            if (cp.e != null) {
                throw cp.e;
            }
            cp.g = true;
            throw cp;
        }
    }
    
    public final int a(final bd bd, final q q) {
        q.a = 0L;
        q.c = false;
        q.b = 0;
        return 10;
    }
    
    private static void a(final q q, final String s, final String s2) {
        cU[] d;
        int n;
        for (d = q.d(), n = 0; n < d.length && !d[n].a().equalsIgnoreCase(s); ++n) {}
        if (n == d.length) {
            final cU[] a;
            (a = bz.a(d, n + 1))[n] = new cU(s, s2);
            q.a(a);
            return;
        }
        if (!bz.b(d[n].b(), s2)) {
            d[n] = new cU(s, d[n].b() + ", " + s2);
        }
    }
}
