// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Frame;
import java.io.IOException;

public class b extends w
{
    private aW a;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public void a() {
        try {
            this.a.j();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public b(final Frame frame, final aW a) {
        super(frame, aC.a(aG.a("%1 Settings"), new String[] { t.a }), a);
        this.a = a;
        if (!a.N) {
            this.a(new bs(a));
        }
        this.a(new l(a));
        this.a(new br(a));
        if (a.a != null) {
            this.a(new aO(a));
        }
        this.a(new aw(a));
    }
}
