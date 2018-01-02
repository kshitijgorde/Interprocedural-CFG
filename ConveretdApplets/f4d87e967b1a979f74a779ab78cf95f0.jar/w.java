import java.applet.Applet;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class w extends dn implements Serializable
{
    public b p;
    public g d;
    
    public w() {
        this.p = new b("file name", this, 1, true);
        this.d = new g("image8", this, 2, false);
    }
    
    public w(final Applet applet) {
        this.p = new b("file name", this, 1, true);
        this.d = new g("image8", this, 2, false);
        this.p(applet);
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
