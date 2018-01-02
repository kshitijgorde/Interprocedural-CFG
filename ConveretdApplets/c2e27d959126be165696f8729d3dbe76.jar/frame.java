import java.awt.Component;
import java.applet.Applet;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class frame extends Frame
{
    public frame(final String title) {
        super(title);
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target == this && evt.id == 201) {
            final Component[] comp = this.getComponents();
            for (int i = comp.length - 1; i >= 0; --i) {
                if (comp[i] instanceof Applet) {
                    ((Applet)comp[i]).stop();
                    this.dispose();
                    return true;
                }
            }
        }
        return false;
    }
}
