import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class HelpWindowListener extends WindowAdapter
{
    public void windowClosing(final WindowEvent windowEvent) {
        windowEvent.getWindow().dispose();
    }
}
