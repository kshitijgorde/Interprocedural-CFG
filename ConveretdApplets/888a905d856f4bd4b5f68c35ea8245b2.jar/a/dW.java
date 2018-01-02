// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.io.IOException;

public final class dW extends f
{
    private ap q;
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public final void q() {
        try {
            this.q.i();
        }
        catch (IOException ex) {}
    }
    
    public dW(final Frame frame, final ap q) {
        super(frame, B.q(be.w("%1 Settings"), new String[] { dN.e }), q);
        this.q = q;
        this.q(new ed(q));
        this.q(new bh(q));
        this.q(new cH(q));
        this.q(new bv(q));
    }
}
