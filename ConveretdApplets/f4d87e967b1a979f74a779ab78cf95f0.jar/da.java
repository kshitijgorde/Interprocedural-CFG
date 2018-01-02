import java.applet.Applet;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class da extends dn implements Serializable
{
    public b p;
    public i d;
    
    public da() {
        this.p = new b("file name", this, 1, true);
        this.d = new i("image32", this, 2, false);
    }
    
    public da(final Applet applet) {
        this.p = new b("file name", this, 1, true);
        this.d = new i("image32", this, 2, false);
        this.p(applet);
    }
    
    public da(final int p3, final int d, final int[] a) {
        this.p = new b("file name", this, 1, true);
        this.d = new i("image32", this, 2, false);
        this.d.p = p3;
        this.d.d = d;
        this.d.a = a;
    }
    
    public final void connectorChanged(final v v) throws Exception {
        if (v == this.p) {
            this.p(this.p.p());
        }
    }
    
    public final boolean execute() throws Exception {
        return true;
    }
}
