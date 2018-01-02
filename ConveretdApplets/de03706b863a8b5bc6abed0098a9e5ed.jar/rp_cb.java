import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_cb implements ItemListener
{
    private /* synthetic */ rp_bA a;
    
    rp_cb(final rp_bA a) {
        this.a = a;
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (this.a.b() != null) {
            final rp_ao rp_ao = (rp_ao)itemEvent.getItem();
            if (rp_ao != null && null != rp_ao.a) {
                this.a.b().a(((rp_cI)rp_ao.a).a(), rp_ao.a);
            }
        }
    }
}
