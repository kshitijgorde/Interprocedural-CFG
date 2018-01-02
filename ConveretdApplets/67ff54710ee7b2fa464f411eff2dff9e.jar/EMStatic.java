import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EMStatic extends Applet
{
    EMStaticFrame ogf;
    
    void destroyFrame() {
        if (this.ogf != null) {
            this.ogf.dispose();
        }
        this.ogf = null;
    }
    
    public void init() {
        (this.ogf = new EMStaticFrame(this)).init();
    }
    
    public void destroy() {
        if (this.ogf != null) {
            this.ogf.dispose();
        }
        this.ogf = null;
    }
}
