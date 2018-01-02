// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.cb;

public abstract class a implements r, cb
{
    protected abstract f a();
    
    protected abstract void b();
    
    protected abstract void a(final int p0);
    
    protected abstract void b(final int p0);
    
    protected abstract void c();
    
    public void g() {
    }
    
    public void h() {
        final f a = this.a();
        if (a != null) {
            a.b(this);
        }
    }
    
    public abstract boolean a();
    
    public abstract Object b();
    
    public abstract boolean c();
    
    public abstract int d();
    
    public abstract void e();
    
    public abstract void f();
}
