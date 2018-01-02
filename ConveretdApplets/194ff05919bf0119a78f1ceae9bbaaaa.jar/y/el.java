// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Color;
import java.awt.Event;

public final class el extends bv
{
    private es a;
    
    public el() {
        this("", false);
    }
    
    private el(final String s, final boolean b) {
        final au au = new au();
        this.a(au, 1, 1, 0, 0);
        this.c();
        au.c();
        (this.a = new es("", 0)).b(this.a());
        au.a(this.a, 1, 1, 0, 0, true);
        this.a(s);
    }
    
    public final void a(final String s) {
        if (this.a.a(s)) {
            this.a.d();
        }
    }
    
    public final boolean a(final Event event) {
        event.target = this;
        return false;
    }
    
    public final void a(final ei ei) {
        if (!this.a.a().equals("")) {
            ei.a(this.a());
            ei.b(1, 1, this.c() - 2, this.d() - 2);
            ei.a(this.b());
            final int c = this.c();
            final int d = this.d();
            final int n = c;
            ei.a(2, 0, n - 2 - 1, 0);
            ei.a(n - 2 - 1, 0, n - 1, 2);
            ei.a(n - 1, 2, n - 1, d - 1 - 2);
            ei.a(n - 1, d - 1 - 2, n - 1 - 2, d - 1);
            ei.a(n - 1 - 2, d - 1, 2, d - 1);
            ei.a(2, d - 1, 0, d - 1 - 2);
            ei.a(0, d - 1 - 2, 0, 2);
            ei.a(0, 2, 2, 0);
            super.a(ei);
        }
    }
    
    static {
        new Color(11580601);
        new Color(5661038);
        new Color(9742270);
    }
}
