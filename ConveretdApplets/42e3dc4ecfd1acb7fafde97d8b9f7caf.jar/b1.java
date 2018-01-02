import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class b1 implements ItemListener
{
    private bm a;
    
    public b1(final bm a) {
        this.a = a;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.a.g();
    }
}
