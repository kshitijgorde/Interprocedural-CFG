import java.applet.Applet;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class ptvjapp$1 extends WindowAdapter
{
    private final ptvjapp f1;
    
    ptvjapp$1(final ptvjapp f1) {
        this.f1 = f1;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        final Applet applet;
        (applet = (Applet)windowEvent.getWindow().getComponent(0)).stop();
        applet.destroy();
        System.exit(0);
    }
}
