import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ModeBox extends Applet
{
    ModeBoxFrame oc;
    
    void destroyFrame() {
        if (this.oc != null) {
            this.oc.dispose();
        }
        this.oc = null;
    }
    
    public void init() {
        (this.oc = new ModeBoxFrame(this)).init();
    }
    
    public void destroy() {
        if (this.oc != null) {
            this.oc.dispose();
        }
        this.oc = null;
    }
}
