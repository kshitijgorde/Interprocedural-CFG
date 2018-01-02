// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.io.IOException;

public final class dm extends E
{
    private cV q;
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public final void q() {
        try {
            this.q.u();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public dm(final Frame frame, final cV q) {
        super(frame, ec.q(eb.q("%1 Settings"), new String[] { a.e }), q);
        this.q = q;
        this.q(new dg(q));
        this.q(new de(q));
        this.q(new df(q));
        this.q(new cN(q));
    }
}
