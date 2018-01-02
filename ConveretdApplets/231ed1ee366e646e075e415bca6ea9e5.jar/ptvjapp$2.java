import java.awt.Frame;
import java.applet.Applet;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class ptvjapp$2 extends ComponentAdapter
{
    private final ptvjapp f1;
    
    ptvjapp$2(final ptvjapp f1) {
        this.f1 = f1;
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        final Applet applet;
        (applet = (Applet)((Frame)componentEvent.getComponent()).getComponent(0)).stop();
        applet.start();
    }
}
