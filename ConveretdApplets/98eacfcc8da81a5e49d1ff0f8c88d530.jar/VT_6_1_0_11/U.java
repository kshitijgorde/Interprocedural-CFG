// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.AbstractButton;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import com.hw.client.util.c;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.applet.Applet;
import javax.swing.JPopupMenu;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

public class U extends JPanel
{
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    private bJ a;
    private JButton b;
    private JButton k;
    private JFrame l;
    private JLabel m;
    private ImageIcon n;
    private String o;
    private String p;
    protected l h;
    private JComponent q;
    private JComponent r;
    private JComponent s;
    protected aS i;
    protected JButton j;
    private JPopupMenu t;
    
    public U(final l h) {
        this.p = "";
        this.i = new aS(this);
        this.h = h;
        this.c = h.a().getString("voice.board");
        this.d = h.a().getString("voice.presentation");
        this.e = h.a().getString("voice.podcaster");
        this.f = h.a().getString("voice.email");
        this.g = h.a().getString("voice.direct");
        h.a().getString("voice.authoring");
        final Color a = ca.a(h.t, "bg", VT_6_1_0_11.h.b());
        final int a2 = ca.a(h.t, "border", 1);
        final Color a3 = ca.a(h.t, "bordercolor", VT_6_1_0_11.h.a);
        this.setBackground(a);
        this.setOpaque(true);
        this.setBorder(new LineBorder(a3, a2));
    }
    
    public final l f() {
        return this.h;
    }
    
    public final bJ g() {
        if (this.a == null) {
            this.a = this.h();
            this.b();
        }
        return this.a;
    }
    
    protected bJ h() {
        return new bJ(false);
    }
    
    protected void b() {
    }
    
    protected JButton i() {
        final JButton a;
        (a = VT_6_1_0_11.h.a("/images/common/btn_options.png", this.h.e("btn_options"), null, "ACTION_OPTIONS")).setRolloverIcon(com.hw.client.util.c.a("/images/common/btn_options_rollover.png"));
        a.setPressedIcon(com.hw.client.util.c.a("/images/common/btn_options_pressed.png"));
        a.addActionListener(this.i);
        return a;
    }
    
    public final JButton j() {
        if (this.j == null) {
            this.j = this.i();
        }
        return this.j;
    }
    
    public final JButton k() {
        return this.g().b();
    }
    
    public final void a(final String o) {
        if (o != null && !o.equals(this.o)) {
            this.o = o;
            if (this.m == null) {
                this.m = new aT();
                if (this.o != null) {
                    this.m.setIcon(this.c());
                }
                com.hw.client.util.c.a(this.m, new Dimension(this.m.getIcon().getIconWidth(), this.m.getIcon().getIconHeight()));
            }
            this.m.setIcon(com.hw.client.util.c.a(o));
        }
    }
    
    private ImageIcon c() {
        if (this.n == null) {
            this.n = com.hw.client.util.c.a(this.o);
        }
        return this.n;
    }
    
    public final void b(final String p) {
        this.p = p;
    }
    
    public final JPopupMenu l() {
        if (this.t == null) {
            this.t = this.m();
        }
        return this.t;
    }
    
    protected JPopupMenu m() {
        final JPopupMenu popupMenu = new JPopupMenu();
        this.j().addActionListener(this.i);
        return popupMenu;
    }
    
    public final String c(final String s) {
        return this.h.e(s);
    }
    
    protected void n() {
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, 11, 2, new Insets(0, 0, 0, 0), 0, 0);
        if (this.q == null) {
            this.q = this.g();
        }
        this.add(this.q, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
        this.d().setBorder(new ad(new Insets(10, 15, 32, 15), this.c(), this.p));
        this.d().setOpaque(true);
        this.d().setBackground(VT_6_1_0_11.h.d());
        this.add(this.d(), gridBagConstraints2);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, 15, 2, new Insets(0, 0, 0, 0), 0, 0);
        if (this.s == null) {
            final String s = "/images/common/bg_poweredby.png";
            if (this.b == null) {
                final cP b;
                (b = new cP(com.hw.client.util.c.a("/images/common/btn_poweredby.png"))).setRolloverIcon(com.hw.client.util.c.a("/images/common/btn_poweredby_rollover.png"));
                VT_6_1_0_11.h.a(b);
                com.hw.client.util.c.a(b);
                b.setActionCommand("ACTION_POWERED_BY");
                b.addActionListener(this.i);
                b.setOpaque(false);
                this.b = b;
            }
            this.s = new cS(s, this.b);
        }
        this.add(this.s, gridBagConstraints3);
        this.g().b().addActionListener(this.i);
    }
    
    private JComponent d() {
        if (this.r == null) {
            this.r = this.a();
        }
        return this.r;
    }
    
    protected JComponent a() {
        return new JPanel();
    }
    
    static JFrame a(final U u, final JFrame l) {
        return u.l = l;
    }
    
    static JFrame a(final U u) {
        return u.l;
    }
    
    static JButton b(final U u) {
        return u.k;
    }
    
    static JButton a(final U u, final JButton k) {
        return u.k = k;
    }
}
