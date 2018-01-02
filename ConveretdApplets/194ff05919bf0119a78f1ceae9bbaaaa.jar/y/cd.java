// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;

final class cd extends bx
{
    private bj a;
    private bj b;
    private bj c;
    private aj a;
    
    public cd(final aj a) {
        super(a.b(), a.a().a(1716518986));
        this.c = new bj(a.a().a(1716518985));
        this.a = new bj(a.a().a(1716518989));
        this.b = new bj(a.a().a(1716518988));
        this.a = a;
        this.a(new es(a.a().a(1716518994)), 2, 1, 0, 0);
        this.a(new es(a.a().a(1716518987), 2), 12, 2, 1, 1, 1, 0, 1);
        this.a(new es(a.a().a(1716518990)), 18, 2, 2, 1, 1, 1, 1);
        this.a(new es(a.a().a(1716518992), 2), 12, 2, 1, 1, 1, 0, 2);
        this.a(new es(a.a().a(1716518984)), 18, 1, 2, 1, 1, 1, 2);
        final av av = new av(1);
        this.a(av, 3, 1, 0, 4);
        av.a(this.a, 0, 0, 2);
        av.a(this.b, 1, 0, 2);
        av.a(this.c, 2, 0, 2);
        this.a();
    }
    
    public final boolean a(final Event event, final Object o) {
        if (event.target == this.a) {
            this.a.h();
            this.a.f();
            this.l();
        }
        else if (event.target == this.b) {
            this.a.a('!', (byte)0);
            this.l();
        }
        else if (event.target == null) {
            this.a.a('!', (byte)1);
            this.l();
        }
        else {
            if (event.target != this.c) {
                return false;
            }
            this.l();
        }
        return true;
    }
}
