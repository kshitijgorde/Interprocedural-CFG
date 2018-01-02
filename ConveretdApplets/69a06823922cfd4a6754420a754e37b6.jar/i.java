import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class i extends WindowAdapter
{
    private final m a;
    
    i(final m a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        System.exit(0);
    }
}
