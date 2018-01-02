import java.awt.Image;
import java.awt.Component;
import java.awt.Event;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class tabnav01a extends Thread
{
    private tabnav01 z0;
    private tabnav01b z1;
    private Rectangle r;
    
    tabnav01a(final tabnav01 z0, final Rectangle r) {
        this.z0 = z0;
        this.r = r;
    }
    
    public void run() {
        this.z1 = new tabnav01b(this.z0, this.r);
        this.z0.postEvent(new Event(this, (int)(this.z1.q33() ? 1 : 0), "TN"));
    }
    
    void q27() {
        this.z0.add(this.z1);
        this.z1.q34();
    }
    
    void q28() {
        this.z1.q28();
    }
    
    Image q29() {
        return this.z1.q47[1];
    }
}
