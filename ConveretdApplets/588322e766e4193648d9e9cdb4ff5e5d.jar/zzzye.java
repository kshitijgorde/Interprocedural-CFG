import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class zzzye extends Frame
{
    public zzzye(final String s) {
        super(s);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                System.exit(0);
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
}
