// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Color;

public final class dk extends av
{
    private f a;
    public Color h;
    public Color i;
    public Color j;
    public Color k;
    public Color l;
    public Color m;
    boolean a;
    
    public dk(final f a, final u u) {
        super(0);
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.a = false;
        this.a = a;
        this.a(u, 1, 1, 0, 0);
    }
    
    public final void a() {
        if (this.h != null && this.i != null && this.j != null && this.k != null && this.l != null && this.m != null) {
            if (this.a) {
                this.a(this.i, this.h, this.m, this.l, this.k, this.j);
            }
            else {
                this.a(this.h, this.i, this.j, this.k, this.l, this.m);
            }
            this.h();
        }
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        final f a = this.a;
        a.a(a.a.indexOf(this));
        return true;
    }
}
