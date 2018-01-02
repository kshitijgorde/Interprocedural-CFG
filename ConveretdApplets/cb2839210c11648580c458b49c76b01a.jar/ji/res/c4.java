// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import ji.awt.c;
import ji.util.i;
import ji.util.d;

class c4 implements Runnable
{
    String a;
    
    c4(final String a) {
        this.a = a;
    }
    
    public void run() {
        try {
            int n = 0;
            final long n2 = 5000L;
            while (n == 0 && !d.c5() && !d.c3() && !d.c4()) {
                d.b(3000, 2004, this.a);
                if (i.c(100)) {
                    if (z.w > 0) {
                        if (System.currentTimeMillis() - z.w < n2) {
                            continue;
                        }
                        z.t.c();
                        z.u.c();
                        z.v.c();
                        n = 1;
                    }
                    else {
                        n = 1;
                    }
                }
                else {
                    final c c = new c("jiUtilPurgeItems");
                    for (int i = 0; i < z.m.b(); ++i) {
                        if (System.currentTimeMillis() - (long)z.m.b(i) >= n2) {
                            c.c(z.m.c(i));
                        }
                    }
                    if (c.b() > 0) {
                        while (c.b() > 0) {
                            z.m.a((String)c.b(0));
                            z.l.a((String)c.b(0));
                            c.d(0);
                        }
                    }
                    for (int j = 0; j < z.o.b(); ++j) {
                        if (System.currentTimeMillis() - (long)z.o.b(j) >= n2) {
                            c.c(z.o.c(j));
                        }
                    }
                    if (c.b() > 0) {
                        while (c.b() > 0) {
                            z.o.a((String)c.b(0));
                            z.n.a((String)c.b(0));
                            c.d(0);
                        }
                    }
                    for (int k = 0; k < z.q.b(); ++k) {
                        if (System.currentTimeMillis() - (long)z.q.b(k) >= n2) {
                            c.c(z.q.c(k));
                        }
                    }
                    if (c.b() > 0) {
                        while (c.b() > 0) {
                            z.q.a((String)c.b(0));
                            z.p.a((String)c.b(0));
                            c.d(0);
                        }
                    }
                    if (z.m.b() == 0 && z.o.b() == 0 && z.q.b() == 0) {
                        break;
                    }
                    continue;
                }
            }
        }
        catch (Exception ex) {}
        finally {
            z.r = null;
        }
    }
}
