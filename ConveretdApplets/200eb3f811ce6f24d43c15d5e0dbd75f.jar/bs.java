// 
// Decompiled by Procyon v0.5.30
// 

public abstract class bs
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    
    public void a(int a, final int b) {
        if (a > 0 && a < 1024) {
            a = 1024;
        }
        this.a = a;
        this.b = b;
    }
    
    public void a(final String s, final Object o, final int n, final long n2) {
        this.a(this.b(s, o, n, n2));
    }
    
    public synchronized Object a(final String s, final boolean b) {
        final bt b2 = this.b(s, b);
        if (b2 != null) {
            return b2.b();
        }
        return null;
    }
    
    public synchronized Object a(final String s) {
        return this.a(s, false);
    }
    
    public abstract bt b(final String p0, final Object p1, final int p2, final long p3);
    
    public abstract bt a(final bt p0);
    
    public abstract bt b(final String p0, final boolean p1);
    
    public abstract void a();
    
    public boolean b() {
        return (this.d > this.b && this.b > 0) || (this.c > this.a && this.a > 0);
    }
}
