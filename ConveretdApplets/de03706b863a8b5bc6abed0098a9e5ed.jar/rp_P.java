import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_P extends AbstractAction
{
    private /* synthetic */ rp_bW a;
    
    rp_P(final rp_bW a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.c();
    }
}
