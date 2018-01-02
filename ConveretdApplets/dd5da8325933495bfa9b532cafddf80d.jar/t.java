// 
// Decompiled by Procyon v0.5.30
// 

public class t
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public v f;
    
    public t() {
        this.f = new v();
        this.a();
    }
    
    public void a() {
        this.a = 0;
        this.b = -1;
        this.c = 0;
        this.f.a();
        this.a(-1);
    }
    
    public void a(final int b, final int a, final int c) {
        this.b = b;
        this.a = a;
        this.c = c;
    }
    
    public void a(final int n) {
        this.e &= ~n;
    }
    
    public void b(final int n) {
        this.e |= n;
    }
    
    public boolean c(final int n) {
        return (this.e & n) == n;
    }
}
