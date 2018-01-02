// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class cz extends c
{
    private W q;
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public cz(final Frame frame, final W q) {
        super(frame, s.q(ak.q("%1 Settings"), new String[] { cu.e }), q);
        this.q = q;
        this.q(new cG(q));
        this.q(new aC(q));
        this.q(new bC(q));
        this.q(new aN(q));
    }
}
