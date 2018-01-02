// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Image;
import java.awt.Color;

public final class fc extends av
{
    public bj a;
    public co a;
    public av b;
    public bh a;
    private ap a;
    private int a;
    
    fc(final Color color, final ap a, final int a2) {
        super((byte)0);
        this.b = new av(2);
        this.a = a;
        this.a = a2;
        this.a = new bj(a.a().a(1716518935));
        (this.a = new co(80)).a(color);
        this.a.b(ap.c());
        this.a = new bh(null, 34, 23);
        if (a.a().f) {
            this.a(this.b, 1, 1, 0, 0);
            this.b.a(this.a, 0);
            this.b.a(this.a, 1);
        }
        this.a(this.a, 1, 1, 1, 0);
    }
    
    final void a(final String s) {
        final co a;
        (a = this.a).a[0] = s;
        a.h();
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        if (event.target == this.a) {
            this.a.j(this.a);
            return true;
        }
        return super.a(event, n, n2);
    }
    
    public final boolean a(final Event event) {
        if (event.id == 1001 && event.target == this.a) {
            this.a.h(this.a);
            return true;
        }
        return super.a(event);
    }
}
