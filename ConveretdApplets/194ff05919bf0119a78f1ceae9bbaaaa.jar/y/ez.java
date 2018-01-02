// 
// Decompiled by Procyon v0.5.30
// 

package y;

public class ez extends av
{
    av b;
    int a;
    int b;
    boolean a;
    public boolean b;
    
    public ez(final av b) {
        super('\0');
        this.b = false;
        this.b = b;
    }
    
    public final void a() {
        this.b.a(this, 0, 0);
    }
    
    public final void l() {
        this.b.a(this);
    }
    
    public final void e() {
        super.e();
        if (!this.b) {
            final int n = (this.b.c() - this.c()) / 2;
            final int n2 = (this.b.d() - this.d()) / 2;
            if (n != super.c || n2 != super.d) {
                super.a(n, n2, this.c(), this.d());
            }
        }
    }
}
