import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetFrame extends Frame
{
    private IRCQNetMainPanel mParent;
    
    public IRCQNetFrame(final String s, final IRCQNetMainPanel mParent) {
        super(s);
        this.mParent = mParent;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.mParent.postEvent(new Event(this, 10015, null));
                break;
            }
        }
        return super.handleEvent(event);
    }
}
