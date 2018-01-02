import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class b4 implements ItemListener
{
    private bm a;
    
    public b4(final bm a) {
        this.a = a;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        h.f().a(this.a.ae.isSelected());
        this.a.b();
    }
}
