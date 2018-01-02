// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

public final class br extends em
{
    private ek a;
    private int a;
    private af a;
    private ff a;
    private bj a;
    private boolean a;
    
    public br(final ek a, final af a2, final String s, final boolean a3) {
        super(s, !a3, a);
        this.a = new ff(100);
        this.a = a3;
        this.a = a2;
        this.a = a;
        super.a.a(new es(a.a(1716519103)), 1, 1, 0, 0);
        super.a.a(this.a, 1, 1, 1, 0);
        super.a.a(new es(a.a(1716519105)), 2, 1, 0, 1);
        super.a.a(new es(a.a(1716519104)), 2, 1, 0, 2);
        super.a.a(a2, 2, 1, 0, 3, true);
        final av av = new av(1);
        super.a.a(av, 2, 1, 0, 4);
        av.a(this.a = new bj(s), 0, 0, 2);
        av.a(super.a = new bj(a.a(1716519272)), 1, 0, 2);
        this.pack();
    }
    
    public final void a(final int a) {
        this.a = a;
        this.show();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.a.c(this.a.a().trim(), this.a);
            this.hide();
            if (!this.a) {
                this.dispose();
            }
            return true;
        }
        return false;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.target == this.a && event.id == 701) {
            final ax a;
            if ((a = this.a.a) != null) {
                this.a.a(((eu)a.a).b);
            }
            return true;
        }
        return super.handleEvent(event);
    }
}
