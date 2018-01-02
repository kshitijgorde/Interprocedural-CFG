// 
// Decompiled by Procyon v0.5.30
// 

public class m
{
    private boolean p;
    private long p;
    private long d;
    private long a;
    
    public m() {
        this.p = false;
        final long currentTimeMillis = System.currentTimeMillis();
        this.p = currentTimeMillis;
        this.d = currentTimeMillis;
        this.a = currentTimeMillis;
    }
    
    final synchronized void p(final int n) {
        this.p = System.currentTimeMillis() + n * 1000;
        final long currentTimeMillis = System.currentTimeMillis();
        this.a = currentTimeMillis;
        this.d = currentTimeMillis;
        this.p = false;
    }
    
    final synchronized void d(final int n) {
        this.p += n * 1000;
    }
    
    final synchronized void a(final int n) {
        this.p += n * 1000 - this.p();
    }
    
    final synchronized void p() {
        if (!this.p) {
            du.p("Clock is not running");
            return;
        }
        this.d = System.currentTimeMillis();
        this.p = false;
    }
    
    final synchronized void d() {
        if (this.p) {
            du.p("Clock is running");
            return;
        }
        final long n = System.currentTimeMillis() - this.d;
        this.p += n;
        this.a += n;
        this.p = true;
    }
    
    final synchronized boolean p() {
        return this.p;
    }
    
    final synchronized int d() {
        return this.p() / 1000;
    }
    
    final synchronized int p() {
        if (this.p) {
            return (int)(this.p - System.currentTimeMillis());
        }
        return (int)(this.p - this.d);
    }
}
