import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class WindowListenerWithClose extends WindowAdapter
{
    boolean dispose;
    
    public WindowListenerWithClose() {
        this.dispose = false;
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        windowEvent.getWindow().setVisible(false);
        if (!this.dispose) {
            windowEvent.getWindow().dispose();
        }
    }
}
