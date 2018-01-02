// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Frame;

public final class l extends N
{
    private cx a;
    
    public final void a() {
        this.a.o(new r(67840, 0));
    }
    
    public l(final Frame frame, final cx a) {
        super(frame, bm.a(aS.a(168), new String[] { a.b() }), a);
        this.a = a;
        if (a.a(51)) {
            this.a(new R(a));
        }
        if (a.a(53)) {
            this.a(new Q(a, true));
        }
        if (super.a.size() > 5) {
            this.b();
        }
    }
}
