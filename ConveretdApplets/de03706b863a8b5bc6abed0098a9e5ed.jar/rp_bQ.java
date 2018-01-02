import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_bQ extends ComponentAdapter
{
    private /* synthetic */ rp_bl a;
    
    rp_bQ(final rp_bl a) {
        this.a = a;
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
        if (this.a.b < 2) {
            final rp_bl a = this.a;
            ++a.b;
            this.a.setSize(300, 200);
            this.a.pack();
        }
    }
}
