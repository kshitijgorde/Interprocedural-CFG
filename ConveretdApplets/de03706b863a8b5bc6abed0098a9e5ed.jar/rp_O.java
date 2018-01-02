import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_O extends InternalFrameAdapter
{
    private /* synthetic */ rp_bW a;
    
    rp_O(final rp_bW a) {
        this.a = a;
    }
    
    public final void internalFrameClosing(final InternalFrameEvent internalFrameEvent) {
        rp_au.a().c();
        this.a.setVisible(false);
    }
}
