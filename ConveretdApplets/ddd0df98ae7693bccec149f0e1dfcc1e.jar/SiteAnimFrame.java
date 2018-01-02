import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class SiteAnimFrame extends Frame
{
    public SiteAnimFrame(final String str) {
        super(str);
    }
    
    public boolean handleEvent(final Event evt) {
        switch (evt.id) {
            case 201: {
                this.dispose();
                System.exit(0);
                return true;
            }
            default: {
                return super.handleEvent(evt);
            }
        }
    }
}
