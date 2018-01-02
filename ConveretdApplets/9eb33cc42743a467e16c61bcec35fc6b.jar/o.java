import java.awt.event.ItemEvent;
import mindbright.terminal.TerminalMenuHandlerFull;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class o implements ItemListener
{
    public final TerminalMenuHandlerFull cv;
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        int n;
        for (n = 0; n < this.cv.d3.length && this.cv.d3[n] != itemEvent.getItemSelectable(); ++n) {}
        if (n >= this.cv.d3.length) {
            return;
        }
        this.cv.d4.ci(r.e4[n][0], String.valueOf(!this.cv.d4.an[n]));
    }
    
    public o(final TerminalMenuHandlerFull cv) {
        this.cv = cv;
    }
}
