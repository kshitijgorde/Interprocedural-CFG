// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class ek extends f
{
    private ap q;
    
    public final void q() {
        this.q.o(new dI(67840, 0));
    }
    
    public ek(final Frame frame, final ap q) {
        super(frame, B.q(be.w("%1 Server Options"), new String[] { dN.e }), q);
        this.q = q;
        if (q.q(51)) {
            this.q(new bX(q));
        }
        if (q.q(53)) {
            this.q(new ci(q, true));
        }
        if (super.q.size() > 5) {
            this.q(2);
        }
    }
}
