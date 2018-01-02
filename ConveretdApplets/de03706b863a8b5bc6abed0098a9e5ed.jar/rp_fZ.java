import java.awt.Component;
import java.awt.event.ComponentListener;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.BorderLayout;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fZ extends rp_ae
{
    public rp_dl a;
    
    public rp_fZ(final rp_aJ rp_aJ) {
        super(rp_aJ);
        this.a = null;
        this.setLayout(new BorderLayout());
    }
    
    final void a() {
        (this.a = this.a()).setCursor(Cursor.getPredefinedCursor(0));
        this.a.addComponentListener(new rp_cM(this));
        this.add(this.a, "Center");
        this.validate();
        this.a(0, 20);
    }
    
    public final void a(final rp_dl rp_dl, final int n, final int n2) {
        this.a(n, n2);
    }
    
    public final void setVisible(final boolean visible) {
        if (visible && this.a == null) {
            this.a();
        }
        super.setVisible(visible);
    }
    
    private void a(final int n, final int n2) {
        rp_ae.b(this.a);
        rp_ae.b(this.a, new rp_y(2, 0, null), new rp_ea(n, n2));
    }
}
