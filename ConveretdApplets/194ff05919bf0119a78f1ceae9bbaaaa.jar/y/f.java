// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Color;
import java.util.Vector;

public final class f extends av
{
    public Vector a;
    public Vector b;
    public dr a;
    public int a;
    
    public f(final int n) {
        super(0);
        this.a = new Vector();
        this.b = new Vector();
        this.a = -1;
        this.a = new dr(n);
        final u u = new u(1, 1);
        final u u2 = new u(1, 1);
        final u u3 = new u(1, 1);
        u.b(Color.black);
        u2.b(Color.black);
        u3.b(Color.black);
        this.a(u, 2, 1, 0, 0, false);
        this.a(u2, 1, 1, 0, 1, false);
        this.a(u3, 2, 1, 0, 2, false);
        this.a(this.a, 1, 1, 1, 1, false);
    }
    
    public final void a(final int a) {
        for (int i = 0; i < this.a.size(); ++i) {
            final dk dk = this.a.elementAt(i);
            final boolean a2 = i == a;
            final dk dk2 = dk;
            dk.a = a2;
            dk2.a();
        }
        this.a = a;
    }
}
