import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class xp implements ItemListener
{
    private final var n;
    
    xp(final var n) {
        this.n = n;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == var.b(this.n)) {
            var._(this.n).a(true);
            new Dp(this).start();
        }
    }
    
    static var a(final xp xp) {
        return xp.n;
    }
}
