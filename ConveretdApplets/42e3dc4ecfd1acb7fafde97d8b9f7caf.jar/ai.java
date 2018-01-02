import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class ai extends WindowAdapter
{
    private ah a;
    
    public ai(final ah a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.dispose();
    }
}
