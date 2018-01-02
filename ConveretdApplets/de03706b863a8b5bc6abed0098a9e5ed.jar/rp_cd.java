import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_cd extends ComponentAdapter
{
    private /* synthetic */ rp_bA a;
    
    rp_cd(final rp_bA a) {
        this.a = a;
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.a.a(this.a.b());
        this.a.a.a(this.a.a.a().height);
    }
}
