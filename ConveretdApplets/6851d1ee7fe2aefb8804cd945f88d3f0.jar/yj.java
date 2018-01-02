import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class yj implements ItemListener
{
    private final n ta;
    
    yj(final n ta) {
        this.ta = ta;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == n.b(this.ta)) {
            this.ta.b(false);
            new Ej(this).start();
        }
        if (itemEvent.getSource() == n.a(this.ta)) {
            this.ta.b(false);
            new Fj(this).start();
        }
    }
    
    static n _(final yj yj) {
        return yj.ta;
    }
}
