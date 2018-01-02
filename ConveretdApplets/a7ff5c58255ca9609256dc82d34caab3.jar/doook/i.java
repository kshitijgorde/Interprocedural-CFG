// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Frame;
import java.io.IOException;

public class i extends aa
{
    private at a;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public void c() {
        try {
            this.a.f();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public i(final Frame frame, final at a) {
        super(frame, H.a(ar.b("%1 Settings"), new String[] { bi.Q }), a);
        this.a = a;
        if (!a.H) {
            this.a(new B(a));
        }
        this.a(new y(a));
        this.a(new v(a));
        if (a.a != null) {
            this.a(new q(a));
        }
        this.a(new aQ(a));
    }
}
