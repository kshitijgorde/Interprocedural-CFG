// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Vector;

public final class cq
{
    public Vector a;
    public boolean a;
    public r a;
    
    public cq() {
        this.a = new Vector();
        this.a = false;
        this.a = new r();
    }
    
    public final synchronized void a(final bo bo, final int n, final Object o) {
        this.a.addElement(cx.a(bo, n, o));
    }
    
    public final synchronized void a() {
        this.a = true;
    }
}
