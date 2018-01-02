import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DiffEq extends Applet
{
    DiffEqFrame mf;
    
    void destroyFrame() {
        if (this.mf != null) {
            this.mf.dispose();
        }
        this.mf = null;
    }
    
    public void init() {
        (this.mf = new DiffEqFrame(this)).init();
    }
    
    public void destroy() {
        if (this.mf != null) {
            this.mf.dispose();
        }
        this.mf = null;
    }
}
