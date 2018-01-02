import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class dc extends FocusAdapter
{
    private final /* synthetic */ cs a;
    
    public dc(final cs a) {
        this.a = a;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.a.ab = false;
        this.a.repaint();
    }
}
