// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class cx extends c
{
    private W q;
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public cx(final Frame frame, final W q) {
        super(frame, s.q(ak.q("%1 Settings"), new String[] { cs.e }), q);
        this.q = q;
        this.q(new cE(q));
        this.q(new aC(q));
        this.q(new bA(q));
        this.q(new aL(q));
    }
}
