import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class while extends WindowAdapter
{
    private final throws nb;
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.nb.dispose();
    }
    
    while(final throws nb) {
        this.nb = nb;
    }
}
