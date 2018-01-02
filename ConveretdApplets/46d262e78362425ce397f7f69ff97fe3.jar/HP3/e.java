// 
// Decompiled by Procyon v0.5.30
// 

package HP3;

import java.awt.Event;
import java.applet.Applet;
import java.awt.Frame;

public class e extends Frame
{
    Applet a;
    
    public e(final String s) {
        super(s);
        this.a = null;
        this.pack();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.a.destroy();
            this.dispose();
        }
        return true;
    }
}
