import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class c9 extends WindowAdapter
{
    private c8 a;
    
    public c9(final c8 a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.a();
    }
}
