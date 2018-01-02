// 
// Decompiled by Procyon v0.5.30
// 

public class ad
{
    private av[] a;
    private int b;
    private int c;
    
    public ad() {
        this.a = new av[0];
        this.b = 0;
        this.c = 0;
    }
    
    public synchronized void a() {
        if (this.b < this.a.length) {
            System.arraycopy(this.a, 0, this.a = new av[this.b], 0, this.b);
        }
    }
    
    public av[] b() {
        this.a();
        return this.a;
    }
    
    public synchronized void c() {
        final av[] b = this.b();
        for (int i = 0; i < b.length; ++i) {
            b[i].m();
        }
        this.b = 0;
        this.a = new av[0];
        System.gc();
    }
    
    private void a(final int n) {
        final int length = this.a.length;
        if (n > length) {
            final av[] a = this.a;
            int n2 = (this.c > 0) ? (length + this.c) : (length * 2);
            if (n2 < n) {
                n2 = n;
            }
            System.arraycopy(a, 0, this.a = new av[n2], 0, this.b);
        }
    }
    
    public boolean a(final av av) {
        if (this.c(av.s()) < 0) {
            synchronized (this.a) {
                this.a(this.b + 1);
                this.a[this.b++] = av;
            }
            return true;
        }
        return false;
    }
    
    public av a(final String s) {
        final int c = this.c(s);
        if (c >= 0) {
            return this.b(c);
        }
        return null;
    }
    
    public synchronized av b(final String s) {
        final int c = this.c(s);
        if (c >= 0) {
            return this.a[c];
        }
        return null;
    }
    
    public av b(final av av) {
        return this.a(av.s());
    }
    
    public int d() {
        return this.b;
    }
    
    public av[] a(final z z) {
        synchronized (this.a) {
            this.a();
            if (z != null) {
                bi.a(this.a, z);
            }
        }
        return this.a;
    }
    
    private av b(final int n) {
        synchronized (this.a) {
            final av av = this.a[n];
            final int n2 = this.b - n - 1;
            if (n2 > 0) {
                System.arraycopy(this.a, n + 1, this.a, n, n2);
            }
            --this.b;
            if (av != null) {
                av.m();
            }
            return av;
        }
    }
    
    private int c(final String s) {
        if (s == null) {
            for (int i = 0; i < this.b; ++i) {
                if (this.a[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = 0; j < this.b; ++j) {
                if (s.equalsIgnoreCase(this.a[j].s())) {
                    return j;
                }
            }
        }
        return -1;
    }
}
