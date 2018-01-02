// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

import ji.util.d;
import ji.util.i;
import ji.awt.c;

public class de
{
    private at a;
    private c b;
    private q c;
    private long d;
    private long e;
    private String f;
    
    public de(final Object o, final String f) {
        this.a = null;
        this.b = new c("jiCacheManaged1");
        this.c = null;
        this.d = 0L;
        this.e = 10485760L;
        this.f = null;
        this.f = f;
        if (this.a == null) {
            this.a = new at(f);
        }
        if (this.c == null) {
            this.c = q.a(o, f);
        }
    }
    
    public final void a(final long e) {
        this.e = e;
    }
    
    public final String a(final String s, final Object o) {
        String s2 = null;
        try {
            s2 = (String)this.a.c(s);
            if (s2 != null && !this.c.e(s2)) {
                final long c = this.c.c(s2, o);
                if (this.c.d(s2)) {
                    this.d -= c;
                    this.a.b(s);
                }
                this.b(s);
                s2 = null;
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public final String b(final String s, final Object o) {
        String c = null;
        try {
            v3 v3 = null;
            Block_4: {
                for (int i = 0; i < this.b.b(); ++i) {
                    v3 = (v3)this.b.b(i);
                    if (v3.a.equals(s)) {
                        break Block_4;
                    }
                }
                return c;
            }
            c = v3.c;
        }
        catch (Exception ex) {}
        return c;
    }
    
    public final byte[] c(final String s, final Object o) throws Exception {
        byte[] a = null;
        if (s != null) {
            a = this.c.a(s, o);
        }
        return a;
    }
    
    public final int[] d(final String s, final Object o) throws Exception {
        int[] b = null;
        if (s != null) {
            b = this.c.b(s, o);
        }
        return b;
    }
    
    public final void a(final String s, final String s2, final Object o, final String s3) {
        try {
            if (s != null) {
                this.a.a(s2, s);
                this.b.c(new v3(s2, s, s3));
                this.d += this.c.c(s, o);
                this.f(s2, o);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void f(final String s, final Object o) throws Exception {
        String s2 = null;
        while (this.d > this.e) {
            final String e = this.a.e();
            if (e == null) {
                break;
            }
            if (s2 != null && s2.equals(e)) {
                break;
            }
            s2 = e;
            if (e.toLowerCase().equals(s.toLowerCase())) {
                break;
            }
            final String s3 = (String)this.a.c(e);
            if (s3 == null) {
                break;
            }
            final long c = this.c.c(s3, o);
            if (!this.c.d(s3)) {
                continue;
            }
            this.d -= c;
            this.b(e);
            this.a.b(e);
        }
    }
    
    public final void a(final Object o) {
        try {
            int i = 0;
            while (i == 0) {
                i = 1;
                for (int b = this.b.b(), n = 0; n < b && i != 0; ++n) {
                    final v3 v3 = (v3)this.b.b(n);
                    final String b2 = v3.b;
                    if (b2 != null) {
                        final long c = this.c.c(b2, o);
                        if (c > 0 && this.a.d(v3.a) <= 0 && this.c.d(b2)) {
                            this.d -= c;
                            this.a.b(v3.a);
                            this.b.d(n);
                            i = 0;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b(final Object o) {
        try {
            if (i.c(83)) {
                h.d(this.f, "emptyCache---");
            }
            long c = 0L;
            while (this.b.b() > 0) {
                final v3 v3 = (v3)this.b.b(0);
                final String b = v3.b;
                if (b != null) {
                    if (this.c.e(b)) {
                        c = this.c.c(b, o);
                    }
                    if (c > 0) {
                        if (i.c(83)) {
                            h.d(this.f, "emptying ".concat(String.valueOf(String.valueOf(b))));
                        }
                        if (this.c.d(b)) {
                            this.d -= c;
                            this.a.b(v3.a);
                        }
                    }
                }
                this.b.d(0);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void b(final String s) {
        try {
            Block_3: {
                for (int i = 0; i < this.b.b(); ++i) {
                    if (((v3)this.b.b(i)).a.equals(s)) {
                        break Block_3;
                    }
                }
                return;
            }
            int i = 0;
            this.b.d(i);
        }
        catch (Exception ex) {}
    }
    
    public final boolean e(final String s, final Object o) {
        boolean b = false;
        try {
            if (!this.a(s)) {
                final String s2 = (String)this.a.c(s);
                if (s2 != null) {
                    final long c = this.c.c(s2, o);
                    if (this.c.d(s2)) {
                        this.d -= c;
                        this.a.b(s);
                        this.b(s);
                        b = true;
                    }
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.b(ex);
            b = false;
        }
        return b;
    }
    
    public final boolean a(final String s) {
        boolean b = false;
        try {
            if (this.a.d(s) > 0) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    class v3
    {
        public String a;
        public String b;
        public String c;
        
        public v3(final de de, final String a, final String b, final String c) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
