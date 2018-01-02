import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_dU extends MouseAdapter
{
    private /* synthetic */ rp_fE a;
    
    rp_dU(final rp_fE a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        final rp_fE a;
        if ((a = this.a).a.a()) {
            a.a.a(a.a, 1);
            return;
        }
        a.a.a(a.a);
    }
}
