import java.awt.event.ItemListener;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import java.awt.event.ComponentListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import net.eprevue.easyplan.selection.SelectionTreeView;

// 
// Decompiled by Procyon v0.5.30
// 

class rp_bA extends rp_ae
{
    rp_dl a;
    SelectionTreeView a;
    
    public rp_bA(final rp_aJ rp_aJ) {
        super(rp_aJ);
        this.a = null;
        this.a = null;
        this.setLayout(new BorderLayout());
    }
    
    rp_dl b() {
        return this.a;
    }
    
    SelectionTreeView a() {
        return this.a;
    }
    
    void a() {
        this.a = this.a();
        (this.a = new SelectionTreeView(100)).setBackground(rp_aJ.v);
        this.add(this.a(this.a), "North");
        this.a.addComponentListener(new rp_cd(this));
        this.add(this.a, "Center");
        this.a.a(rp_ae.a(100));
        if (null == this.a.a()) {
            this.a.a(this.a.b());
        }
    }
    
    protected final rp_r a(final SelectionTreeView selectionTreeView) {
        final JScrollPane scrollPane;
        (scrollPane = new JScrollPane(selectionTreeView)).setBorder(null);
        final rp_r rp_r;
        (rp_r = new rp_r(scrollPane, selectionTreeView)).setBackground(rp_aJ.o);
        rp_r.addItemListener(new rp_cb(this));
        return rp_r;
    }
    
    public void setVisible(final boolean visible) {
        if (visible && this.b() == null) {
            this.a();
        }
        super.setVisible(visible);
    }
    
    public void requestFocus() {
        if (this.a() != null) {
            this.a().setVisible(false);
            this.a().setVisible(true);
            this.a().requestFocus();
        }
    }
    
    public final void a(final rp_dl rp_dl, int n, int n2) {
        final rp_bA rp_bA = this;
        final rp_ao a = this.a().a();
        final int n3 = n;
        n2 = n2;
        n = n3;
        final rp_ao rp_ao = a;
        this = rp_bA;
        if (rp_ao != null && null != rp_ao.a) {
            rp_ae.b(this.b());
        }
        rp_ae.a(this.b(), (rp_cI)rp_ao.a, new rp_ea(n, n2));
    }
}
