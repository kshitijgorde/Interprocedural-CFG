// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Frame;
import java.io.IOException;

public class ah extends r
{
    private u b;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public void a() {
        try {
            this.b.d();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public ah(final Frame frame, final u b) {
        super(frame, am.a(ao.e("%1 Settings"), new String[] { z.G }), b);
        this.b = b;
        if (!b.E) {
            this.a(new K(b));
        }
        this.a(new H(b));
        this.a(new F(b));
        if (b.a != null) {
            this.a(new C(b));
        }
        this.a(new A(b));
    }
}
