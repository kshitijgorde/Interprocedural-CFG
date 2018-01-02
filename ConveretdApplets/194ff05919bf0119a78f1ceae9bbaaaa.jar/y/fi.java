// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

public final class fi extends bx
{
    private u a;
    
    public fi(final av av, final String s, final ak ak) {
        super(av, ak.a(1716519268));
        this.a(new es(s), 1, 1, 0, 0);
        this.a(this.a = new bj(ak.a(1716519269)), 1, 1, 0, 1);
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
