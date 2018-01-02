// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Frame;

public final class aB extends A
{
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public aB(final Frame frame, final bl bl) {
        super(frame, ak.a(ak.a(468), new String[] { bl.a.a }), bl);
        this.a(new L(bl));
        this.a(new J(bl));
        this.a(new I(bl));
        this.a(new E(bl));
    }
}
