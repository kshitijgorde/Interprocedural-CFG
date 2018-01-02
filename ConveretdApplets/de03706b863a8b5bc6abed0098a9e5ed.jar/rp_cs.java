import javax.swing.Box;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Window;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_cs extends rp_bd
{
    private String a;
    private /* synthetic */ rp_es a;
    
    rp_cs(final rp_es a, final Window window, final String s, final String a2) {
        this.a = a;
        super(window, s);
        this.a = a2;
    }
    
    public final void a(final JPanel panel) {
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        panel.add(new JLabel(this.a.a.a().a(0, this.a)));
        panel.add(Box.createVerticalStrut(10));
        final String a = this.a.a.a().a(0, "MDC");
        panel.add(new rp_fw(a, a));
        panel.add(new JLabel(this.a.a.a().a(0, "or")));
        final String a2 = this.a.a.a().a(0, "MDM");
        panel.add(new rp_fw(a2, a2));
    }
}
