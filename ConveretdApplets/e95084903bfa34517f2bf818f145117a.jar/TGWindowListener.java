import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class TGWindowListener extends WindowAdapter
{
    public void windowClosing(final WindowEvent windowEvent) {
        TG.done();
    }
}
