// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class cg extends u
{
    private fg a;
    
    cg(final fg a) {
        super(true);
        this.a = a;
    }
    
    public final void a(final ei ei) {
        ei.a -= this.a.t;
        ei.b -= this.a.a;
        this.a.a(ei, this.a.t, this.a.t + this.a.v, this.a.a, this.a.a + this.a.u);
    }
    
    public final void e() {
        super.e();
        this.a.b(super.e, super.f);
        this.a.u = super.f;
        this.a.v = super.e;
        this.a.a();
        this.a.l();
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        this.a.a(event, n, n2 + this.a.a);
        return true;
    }
    
    public final boolean c(final Event event, final int n, final int n2) {
        this.a.b(event, n, n2 + this.a.a);
        return true;
    }
    
    public final int a() {
        return this.a.g();
    }
    
    public final int b() {
        return this.a.h();
    }
}
