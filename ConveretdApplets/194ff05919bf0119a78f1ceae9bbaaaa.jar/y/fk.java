// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class fk extends bx
{
    private k a;
    private at a;
    private bj a;
    private bj b;
    
    fk(final k a, av av) {
        super(av, a.a.a(1716524898));
        this.a = a;
        this.a(this.a = new at(a.a), 1, 1, 0, 0);
        av = new av(1);
        this.a(av, 1, 1, 0, 1);
        av.a(this.a = new bj(a.a.a(1716524899)), 0, 0, 2);
        av.a(this.b = new bj(a.a.a(1716524897)), 1, 0, 2);
        this.a();
    }
    
    public final boolean a(final Event event, final Object o) {
        if (event.target == this.a) {
            this.a.a(this.a);
            this.a.c();
            this.l();
            return true;
        }
        if (event.target == this.b) {
            this.a.b();
            return true;
        }
        return false;
    }
}
