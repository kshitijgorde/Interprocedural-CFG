import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class b8 extends WindowAdapter implements Runnable
{
    private be a;
    
    public b8(final be a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        new Thread(this).start();
    }
    
    public void run() {
        this.a.g();
    }
}
