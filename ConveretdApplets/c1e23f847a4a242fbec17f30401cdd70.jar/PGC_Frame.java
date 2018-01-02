import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class PGC_Frame extends Frame
{
    private PGC owner;
    
    public PGC_Frame(final PGC owner, final String s) {
        super(s);
        this.owner = owner;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            if (this.owner != null) {
                this.owner.MainFrameClose();
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.owner != null) {
            return this.owner.action(event, o);
        }
        return super.action(event, o);
    }
}
