import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class bz implements ItemListener
{
    private bm a;
    
    public bz(final bm a) {
        this.a = a;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.a.d();
    }
}
