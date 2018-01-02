// 
// Decompiled by Procyon v0.5.30
// 

package z;

public abstract class ar
{
    private g a;
    
    public abstract Object a();
    
    public ar() {
        this.a = new g(new Thread(new aJ(this, new aK(this))));
    }
    
    public final void b() {
        final Thread a;
        if ((a = this.a.a()) != null) {
            a.start();
        }
    }
}
