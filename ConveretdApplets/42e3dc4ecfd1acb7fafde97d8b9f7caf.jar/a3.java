import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class a3 implements ItemListener
{
    private a2 a;
    private int b;
    
    public a3(final a2 a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.a.a(itemEvent, this.b);
    }
}
