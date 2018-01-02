import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class g extends WindowAdapter
{
    private final k a;
    
    g(final k a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.setVisible(false);
        this.a.dispose();
    }
}
