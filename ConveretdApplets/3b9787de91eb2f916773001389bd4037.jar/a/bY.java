// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.io.IOException;

public final class bY extends B
{
    private bI q;
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public final void q() {
        try {
            final bI q = this.q;
            bI.y();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public bY(final Frame frame, final bI q) {
        super(frame, cv.q(cv.q("%1 Settings"), new String[] { a.e }), q);
        this.q = q;
        this.q(new bT(q));
        this.q(new bR(q));
        this.q(new bS(q));
        this.q(new bA(q));
    }
}
