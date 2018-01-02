import java.io.IOException;
import java.awt.Window;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.Box;
import javax.swing.tree.TreePath;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JTextField;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_gz extends rp_fG
{
    private JTextField a;
    private JTextField b;
    private rp_aH a;
    private Frame a;
    private rp_fK a;
    private rp_f a;
    private rp_fx a;
    public boolean a;
    
    public rp_gz(final Frame a, final rp_fK a2, final rp_f a3, final rp_fx a4) {
        super(rp_au.a("sd1"));
        this.a = false;
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.a = a4;
        this.a(rp_au.a.a());
        this.a();
        this.b();
        this.a.requestFocus();
    }
    
    public final void a(final JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(12, 10, 14, 10));
        final rp_v a = this.a.a;
        final JPanel panel2;
        (panel2 = new JPanel()).setLayout(new BoxLayout(panel2, 1));
        final JPanel panel3;
        (panel3 = new JPanel(new BorderLayout())).add(new JLabel(rp_au.a("sd9")), "West");
        this.a = rp_ap.a(rp_au.a, this.a, this.title, this.title, null);
        if (a != null && a.getParent() != null) {
            this.a.setSelectedItem(new TreePath(((rp_v)a.getParent()).getPath()));
        }
        else {
            this.a.setSelectedItem(new TreePath(this.a.a().getPath()));
        }
        panel3.add(this.a, "Center");
        panel2.add(panel3, "North");
        panel2.add(Box.createVerticalStrut(12));
        final JPanel panel4;
        (panel4 = new JPanel(new BorderLayout())).add(new JLabel(rp_au.a("sd2")), "West");
        String a2 = "";
        if (a != null) {
            a2 = a.a;
        }
        (this.a = new JTextField(a2, 32)).setBackground(rp_aJ.l);
        this.a.setBorder(BorderFactory.createBevelBorder(1));
        this.a.setColumns(32);
        panel4.add(this.a, "Center");
        panel2.add(panel4, "North");
        panel2.add(Box.createVerticalStrut(12));
        final JPanel panel5;
        (panel5 = new JPanel(new BorderLayout())).add(new JLabel(rp_au.a("sd3")), "North");
        String b = "";
        if (a != null) {
            b = a.b;
        }
        (this.b = new JTextField(b)).setBackground(rp_aJ.l);
        this.b.setBorder(BorderFactory.createBevelBorder(1));
        panel5.add(this.b, "South");
        panel2.add(panel5, "Center");
        panel.add(panel2, "Center");
    }
    
    public final boolean a() {
        final String replaceAll = this.a.getText().replaceAll(" +$", "");
        final String text = this.b.getText();
        final TreePath treePath;
        final DefaultMutableTreeNode defaultMutableTreeNode;
        if ((treePath = (TreePath)this.a.getSelectedItem()) != null) {
            defaultMutableTreeNode = (DefaultMutableTreeNode)treePath.getLastPathComponent();
        }
        final rp_v rp_v;
        (rp_v = (rp_v)defaultMutableTreeNode).a(this.a);
        DefaultMutableTreeNode defaultMutableTreeNode2 = null;
        if (!rp_C.a(replaceAll, true, this.a)) {
            return false;
        }
        final rp_au a = rp_au.a();
        for (int i = 0; i < rp_v.getChildCount(); ++i) {
            final rp_v rp_v2 = (rp_v)rp_v.getChildAt(i);
            if (replaceAll.equalsIgnoreCase(rp_v2.a)) {
                final rp_fb a2 = rp_au.a.a();
                if (rp_v2.a()) {
                    rp_bd.a(this.a, a2, "err", "sd6");
                    return false;
                }
                if (this.a.a() && !rp_v2.b(2)) {
                    rp_bd.a(this.a, a2, "err", "sd4");
                    return false;
                }
                String s = "<html>" + a2.a(0, "s02") + replaceAll + a2.a(0, "s03") + replaceAll + a2.a(0, "s04") + "</html>";
                if (rp_v2.b != null && rp_v2.b.length() > 0) {
                    s += rp_v2.b;
                }
                if (1 != rp_bd.a(this.a, a2, "sd5", s, false)) {
                    return false;
                }
                defaultMutableTreeNode2 = rp_v2;
            }
        }
        final rp_v rp_v3;
        (rp_v3 = new rp_v(replaceAll, text)).b(2, true);
        rp_v.a(rp_v3);
        final rp_v[] array = { rp_v3 };
        try {
            final rp_bg rp_bg;
            if (!(rp_bg = (rp_bg)this.a.a(rp_aw.a, array, this.a.a(null, rp_fl.b))).a()) {
                rp_v3.removeFromParent();
                a.a().reload();
                rp_bg.a(this.a);
                return false;
            }
            if (defaultMutableTreeNode2 != null) {
                defaultMutableTreeNode2.removeFromParent();
            }
            a.a().reload();
            this.a.a.a(false);
            this.a.a(rp_v3);
            this.a = true;
        }
        catch (IOException ex) {
            rp_v3.removeFromParent();
            a.a().reload();
        }
        return super.a();
    }
}
