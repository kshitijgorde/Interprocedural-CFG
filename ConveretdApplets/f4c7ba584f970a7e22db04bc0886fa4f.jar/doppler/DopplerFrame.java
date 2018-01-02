// 
// Decompiled by Procyon v0.5.30
// 

package doppler;

import java.awt.Event;
import java.awt.Frame;

class DopplerFrame extends Frame
{
    public DopplerFrame(final String s) {
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
