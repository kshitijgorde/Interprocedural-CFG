import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class r extends WindowAdapter
{
    private final t a;
    
    r(final t a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.dispose();
    }
}
