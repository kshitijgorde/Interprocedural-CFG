import javax.swing.SwingUtilities;
import java.awt.event.ComponentListener;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.Icon;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_ge implements Runnable
{
    private /* synthetic */ rp_aD a;
    
    rp_ge(final rp_aD a) {
        this.a = a;
    }
    
    public final void run() {
        if (this.a.a() != null) {
            return;
        }
        final rp_fZ rp_fZ;
        (rp_fZ = new rp_fZ(this.a.a)).a();
        if (rp_fZ.a.b() > 0) {
            this.a.a.insertTab(rp_au.b("M_CI"), null, rp_fZ, rp_aJ.a(rp_au.a("tt_cus")), 1);
        }
    }
}
