import java.awt.Event;
import java.applet.Applet;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class ClientFrame extends Frame
{
    Applet app;
    
    public ClientFrame(final String s, final Applet app) {
        super(s);
        this.app = app;
    }
    
    public boolean handleEvent(final Event event) {
        return this.app.handleEvent(event);
    }
}
