import java.awt.event.ItemEvent;
import mindbright.terminal.TerminalMenuHandlerFull;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class n implements ItemListener
{
    public final TerminalMenuHandlerFull cv;
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        this.cv.cx();
    }
    
    public n(final TerminalMenuHandlerFull cv) {
        this.cv = cv;
    }
}
