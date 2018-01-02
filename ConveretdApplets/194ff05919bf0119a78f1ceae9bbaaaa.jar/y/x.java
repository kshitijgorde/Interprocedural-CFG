// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class x extends bx
{
    private bj a;
    
    x(final av av, final dm dm, final u u) {
        super(av, dm.a(1716518962));
        this.a = new bj(dm.a(1716519232));
        this.a(u, 1, 1, 0, 0);
        this.a(this.a, 1, 1, 0, 2);
        this.a();
    }
    
    public final boolean a(final Event event, final Object o) {
        if (event.target == this.a) {
            this.l();
            return true;
        }
        return super.a(event, o);
    }
}
