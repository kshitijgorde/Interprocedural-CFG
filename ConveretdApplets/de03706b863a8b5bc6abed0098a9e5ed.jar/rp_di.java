import java.util.Vector;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.Component;
import javax.swing.tree.TreePath;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_di extends rp_ae implements ActionListener
{
    rp_dl a;
    private rp_aH a;
    
    public rp_di(final rp_aJ rp_aJ) {
        super(rp_aJ);
        this.a = null;
        this.setLayout(new BorderLayout());
    }
    
    public final void setVisible(final boolean visible) {
        if (visible && this.a == null) {
            this.a = this.a();
            final rp_f a = rp_au.a().a();
            this.a = rp_ap.a(this.a, a, "CM", "tooltip", this);
            this.a.a = true;
            this.a.setSelectedItem(new TreePath(a.a().getPath()));
            this.add(this.a, "North");
            this.a.addComponentListener(new rp_ej(this));
            this.add(this.a, "Center");
        }
        super.setVisible(visible);
    }
    
    public final void a(final rp_dl rp_dl, final int n, final int n2) {
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final rp_v rp_v = (rp_v)((TreePath)((rp_ef)actionEvent.getSource()).getSelectedItem()).getLastPathComponent();
        final rp_di rp_di = this;
        final rp_v rp_v2 = rp_v;
        this = rp_di;
        if (rp_v2.a(2)) {
            final rp_ek rp_ek = new rp_ek(this);
            final rp_dl a = this.a;
            final Vector a2 = rp_v2.a;
            final rp_dl rp_dl = a;
            final rp_ek rp_ek2 = rp_ek;
            Dimension dimension = null;
            for (int i = 0; i < a2.size(); ++i) {
                final rp_dv rp_dv = a2.elementAt(i);
                if (dimension == null) {
                    dimension = new Dimension(rp_dv.a(), rp_dv.b());
                }
                else {
                    dimension.width = Math.max(dimension.width, rp_dv.a());
                    dimension.height = Math.max(dimension.height, rp_dv.b());
                }
            }
            rp_ek2.a(rp_dl, a2, dimension);
            return;
        }
        rp_ae.a(this.a, new rp_em(this, rp_v2), null);
    }
}
