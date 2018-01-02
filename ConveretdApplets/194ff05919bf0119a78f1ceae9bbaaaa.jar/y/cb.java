// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Container;

public class cb
{
    public cq a;
    public eg a;
    public Object a;
    public ac a;
    public ac b;
    public ar a;
    boolean a;
    
    public cb(final Container container, final byte b) {
        this(container);
    }
    
    public cb(final Container container) {
        this.a = new cq();
        this.a = new Object();
        this.b = this.a;
        this.a = new ar();
        this.a = new eg(15);
        this.a.a = this;
        this.a.start();
        this.a = new ac(container, this);
    }
    
    public final ac a() {
        ac ac;
        if ((ac = this.b) == null) {
            ac = this.a;
        }
        return ac;
    }
    
    public final void a(final bo bo, final int n, final Object o) {
        this.a.a(bo, n, o);
        this.a().l();
    }
    
    public boolean a() {
        return true;
    }
    
    public void a(final Throwable t) {
        t.printStackTrace();
    }
}
