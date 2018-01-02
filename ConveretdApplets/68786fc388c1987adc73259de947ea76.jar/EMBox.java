import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EMBox extends Applet
{
    EMBoxFrame oc;
    
    void destroyFrame() {
        if (this.oc != null) {
            this.oc.dispose();
        }
        this.oc = null;
    }
    
    public void init() {
        (this.oc = new EMBoxFrame(this)).init();
    }
    
    public void destroy() {
        if (this.oc != null) {
            this.oc.dispose();
        }
        this.oc = null;
    }
}
