// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.Frame;

public final class dr extends E
{
    private cV q;
    
    public final void q() {
        this.q.q(new es(67840, 0));
    }
    
    public dr(final Frame frame, final cV q) {
        super(frame, ec.q(eb.q("%1 Server Options"), new String[] { a.e }), q);
        this.q = q;
        if (q.q(51)) {
            this.q(new ds(q));
        }
        if (q.q(53)) {
            this.q(new bC(q, true, null));
        }
        if (super.q.size() > 5) {
            this.q(2);
        }
        this.pack();
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(400, 434);
    }
    
    public final Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
}
