// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

import ji.util.d;
import ji.awt.c;

public class at
{
    public boolean a;
    private c b;
    protected String c;
    
    public at(final String c) {
        this.a = true;
        this.b = new c("jiCacheObject1");
        this.c = null;
        this.c = c;
    }
    
    public final void a(final String s, final Object o) throws Exception {
        if (this.a) {
            if (o == null) {
                throw new Exception(d.b(353, this.c));
            }
            if (s == null) {
                throw new Exception(d.b(354, this.c));
            }
            final u0 u0 = new u0(s, o);
            for (int i = 0; i < this.b.b(); ++i) {
                if (u0.a((u0)this.b.b(i)) && s == null) {
                    throw new Exception(d.b(355, this.c));
                }
            }
            u0.a(System.currentTimeMillis());
            this.b.c(u0);
        }
    }
    
    public final void b(final String s) throws Exception {
        if (this.a) {
            if (s == null) {
                throw new Exception(d.b(354, this.c));
            }
            final String lowerCase = s.toLowerCase();
            u0 u0 = null;
            for (int i = 0; i < this.b.b(); ++i) {
                final u0 u2 = (u0)this.b.b(i);
                if (u2.b().equals(lowerCase)) {
                    u0 = u2;
                    break;
                }
            }
            if (u0 != null) {
                u0.g();
                this.b.b(u0);
            }
        }
    }
    
    public final Object c(final String s) throws Exception {
        Object a = null;
        if (this.a) {
            if (s == null) {
                throw new Exception(d.b(354, this.c));
            }
            final String lowerCase = s.toLowerCase();
            for (int i = 0; i < this.b.b(); ++i) {
                final u0 u0 = (u0)this.b.b(i);
                if (u0.b().equals(lowerCase)) {
                    a = u0.a();
                    u0.a(System.currentTimeMillis());
                    break;
                }
            }
        }
        return a;
    }
    
    public final String e() throws Exception {
        String b = null;
        if (this.a) {
            long n = -1L;
            for (int i = 0; i < this.b.b(); ++i) {
                final u0 u0 = (u0)this.b.b(i);
                final long d = u0.d();
                if (u0.c() == 0 && (n < 0 || d < n)) {
                    n = d;
                    b = u0.b();
                }
            }
        }
        return b;
    }
    
    public final int d(final String s) throws Exception {
        int c = 0;
        if (this.a) {
            if (s == null) {
                throw new Exception(d.b(354, this.c));
            }
            final String lowerCase = s.toLowerCase();
            for (int i = 0; i < this.b.b(); ++i) {
                final u0 u0 = (u0)this.b.b(i);
                if (u0.b().equals(lowerCase)) {
                    c = u0.c();
                    break;
                }
            }
        }
        return c;
    }
    
    public final void e(final String s) throws Exception {
        if (this.a) {
            if (s == null) {
                throw new Exception(d.b(354, this.c));
            }
            final String lowerCase = s.toLowerCase();
            for (int i = 0; i < this.b.b(); ++i) {
                final u0 u0 = (u0)this.b.b(i);
                if (u0.b().equals(lowerCase)) {
                    u0.e();
                    u0.a(System.currentTimeMillis());
                    break;
                }
            }
        }
    }
    
    public final void f(final String s) throws Exception {
        if (this.a) {
            if (s == null) {
                throw new Exception(d.b(354, this.c));
            }
            final String lowerCase = s.toLowerCase();
            for (int i = 0; i < this.b.b(); ++i) {
                final u0 u0 = (u0)this.b.b(i);
                if (u0.b().equals(lowerCase)) {
                    u0.f();
                    u0.a(System.currentTimeMillis());
                    break;
                }
            }
        }
    }
    
    public final void f() {
        if (this.a) {
            this.a();
        }
    }
    
    private final void a() {
        try {
            while (this.b.b() > 0) {
                final u0 u0 = (u0)this.b.b(0);
                u0.g();
                this.b.b(u0);
            }
        }
        catch (Exception ex) {}
    }
    
    public void finalize() {
        this.a();
    }
    
    class u0
    {
        private Object a;
        private String b;
        private int c;
        private long d;
        
        public u0(final at at, final String s, final Object a) throws Exception {
            this.a = null;
            this.b = null;
            this.c = 0;
            this.d = 0L;
            if (a == null) {
                throw new Exception(ji.util.d.b(353, at.c));
            }
            if (s == null) {
                throw new Exception(ji.util.d.b(354, at.c));
            }
            this.a = a;
            this.b = s.toLowerCase();
        }
        
        public final Object a() {
            return this.a;
        }
        
        public final String b() {
            return this.b;
        }
        
        public final int c() {
            return this.c;
        }
        
        public final long d() {
            return this.d;
        }
        
        public final void a(final long d) {
            this.d = d;
        }
        
        public final void e() {
            ++this.c;
        }
        
        public final void f() {
            if (this.c > 0) {
                --this.c;
            }
        }
        
        public final boolean a(final u0 u0) {
            try {
                return this.b.equals(u0.b());
            }
            catch (Exception ex) {
                return false;
            }
        }
        
        public final void g() {
            this.a = null;
            this.b = null;
        }
    }
}
