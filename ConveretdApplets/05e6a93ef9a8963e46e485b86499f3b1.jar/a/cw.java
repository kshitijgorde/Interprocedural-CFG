// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class cw extends c
{
    private W q;
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public cw(final Frame frame, final W q) {
        super(frame, t.q(al.q("%1 Settings"), new String[] { cs.e }), q);
        this.q = q;
        this.q(new cD(q));
        this.q(new aD(q));
        this.q(new bB(q));
        this.q(new aM(q));
    }
}
