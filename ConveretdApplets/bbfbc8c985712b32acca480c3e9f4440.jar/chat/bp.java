// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Frame;

public final class bp extends N
{
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public final void a() {
    }
    
    public bp(final Frame frame, final cx cx) {
        super(frame, bm.a(aS.a(468), new String[] { cx.a.a }), cx);
        this.a(new ag(cx));
        this.a(new ad(cx));
        this.a(new ab(cx));
        this.a(new X(cx));
    }
}
