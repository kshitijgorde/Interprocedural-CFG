import java.awt.event.WindowEvent;
import mindbright.application.MindTerm;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class cp extends WindowAdapter
{
    public final MindTerm cv;
    
    public final void windowClosing(final WindowEvent windowEvent) {
        this.cv.n7(windowEvent);
    }
    
    public final void windowDeiconified(final WindowEvent windowEvent) {
        this.cv.d4.requestFocus();
    }
    
    public cp(final MindTerm cv) {
        this.cv = cv;
    }
}
