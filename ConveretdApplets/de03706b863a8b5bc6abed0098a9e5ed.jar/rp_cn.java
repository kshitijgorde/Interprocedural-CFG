import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_cn extends ComponentAdapter
{
    private /* synthetic */ rp_ck a;
    private /* synthetic */ rp_fY a;
    
    rp_cn(final rp_fY a, final rp_ck a2) {
        this.a = a;
        this.a = a2;
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.a.a(this.a);
    }
}
