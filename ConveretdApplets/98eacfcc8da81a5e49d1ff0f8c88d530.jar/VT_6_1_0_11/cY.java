// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.a;
import java.io.InputStream;
import javax.swing.JComponent;
import java.awt.Font;
import javax.swing.Icon;
import com.hw.client.util.c;
import java.awt.Cursor;
import javax.swing.JMenu;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.Box;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.util.ResourceBundle;
import javax.swing.JSeparator;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.JPanel;

public class cY extends JPanel
{
    private int e;
    private M f;
    protected boolean a;
    protected boolean b;
    protected boolean c;
    protected boolean d;
    private dy g;
    private du h;
    private AbstractButton i;
    private AbstractButton j;
    private AbstractButton k;
    private AbstractButton l;
    private JButton m;
    private JButton n;
    private ImageIcon o;
    private ImageIcon p;
    private ImageIcon q;
    private ImageIcon r;
    private ImageIcon s;
    private ImageIcon t;
    private ImageIcon u;
    private ImageIcon v;
    private bs w;
    private F z;
    private bq A;
    private JLabel B;
    private JLabel C;
    private JLabel D;
    private JPopupMenu E;
    private JMenuItem F;
    private JMenuItem G;
    private JMenuItem H;
    private JMenuItem I;
    private JMenuItem J;
    private JCheckBoxMenuItem K;
    private JCheckBoxMenuItem L;
    private JSeparator M;
    private ResourceBundle N;
    private JPanel O;
    
    public cY(final int e, final du h, final ResourceBundle n) {
        this.a = true;
        this.b = true;
        this.M = new JSeparator();
        this.e = e;
        this.h = h;
        this.N = n;
        final cS cs;
        (cs = new cS("/images/recorder/bg_right.png")).setLayout(new GridBagLayout());
        cs.add(this.u(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 11, 0, new Insets(3, 1, 0, 0), 0, 0));
        cs.add(Box.createVerticalGlue(), new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0, 10, 3, new Insets(0, 0, 0, 0), 0, 0));
        cs.add(this.t(), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 15, 0, new Insets(0, 1, 3, 0), 0, 0));
        final JPanel m = this.m();
        final cS cs2;
        (cs2 = new cS("/images/recorder/bg_middle.png")).setOpaque(false);
        cs2.setLayout(new BorderLayout());
        cs2.add(m, "Center");
        cs2.add(cs, "East");
        this.setBorder(new LineBorder(ca.a("#BFBFBF", Color.darkGray), 1, false));
        this.setLayout(new BorderLayout());
        this.add(cs2, "Center");
        this.setMinimumSize(new Dimension(100, 48));
        this.setPreferredSize(new Dimension(200, 48));
        this.setMaximumSize(new Dimension(600, 48));
        this.n().c();
        if (this.t() != null) {
            this.t().addActionListener(this.n());
        }
        if (this.f() != null) {
            this.f().addActionListener(this.n());
        }
        if (this.g() != null) {
            this.g().addActionListener(this.n());
        }
        if (this.h() != null) {
            this.h().addActionListener(this.n());
        }
        if (this.i() != null) {
            this.i().addActionListener(this.n());
        }
        if (this.j() != null) {
            this.j().b().addChangeListener(this.n());
        }
        if (this.u() != null) {
            this.u().addActionListener(this.n());
        }
    }
    
    public final du e() {
        return this.h;
    }
    
    public final void b(final boolean b) {
        this.a = true;
    }
    
    public final void c(final boolean b) {
        this.b = false;
    }
    
    public final void d(final boolean c) {
        this.c = c;
    }
    
    public final void a(final double n) {
        this.n().c(0.0);
    }
    
    private JPopupMenu b() {
        if (this.E == null) {
            this.G = new JMenu(this.b("options_audio_input"));
            (this.I = new JMenuItem(this.b("options_audio_device_loading"))).setEnabled(false);
            this.H = new JMenu(this.b("options_audio_output"));
            (this.J = new JMenuItem(this.b("options_audio_device_loading"))).setEnabled(false);
            (this.K = new JCheckBoxMenuItem(this.b("options_filter_auto_gain_control"))).setActionCommand("ACTION_FILTER_AGC");
            this.K.addActionListener(this.n());
            (this.L = new JCheckBoxMenuItem(this.b("options_filter_noise_reduction"))).setActionCommand("ACTION_FILTER_NOISE_REDUCTION");
            this.L.addActionListener(this.n());
            final JMenu menu;
            (menu = new JMenu(this.b("options_audio_filters"))).add(this.K);
            menu.add(this.L);
            final JPopupMenu e;
            (e = new JPopupMenu()).setBorderPainted(true);
            e.add(this.w());
            e.add(this.M);
            if (this.e == 0) {
                e.add(this.G);
            }
            e.add(this.H);
            if (this.e == 0) {
                e.add(menu);
            }
            this.E = e;
        }
        return this.E;
    }
    
    public final void b(final int n) {
        if (this.getCursor().getType() != n) {
            if (n == 0) {
                this.setCursor(null);
                return;
            }
            this.setCursor(new Cursor(n));
        }
    }
    
    public final AbstractButton f() {
        if (this.i == null) {
            this.o = com.hw.client.util.c.a("/images/recorder/btn_play.png");
            this.q = com.hw.client.util.c.a("/images/recorder/btn_play_disabled.png");
            final ImageIcon a = com.hw.client.util.c.a("/images/recorder/btn_play_rollover.png");
            this.p = com.hw.client.util.c.a("/images/recorder/btn_play_pressed.png");
            final AbstractButton a2;
            (a2 = a(this.o, "ACTION_PLAY", this.b("audiocomponent.play"))).setDisabledIcon(this.q);
            a2.setRolloverIcon(a);
            a2.setPressedIcon(this.p);
            this.i = a2;
        }
        return this.i;
    }
    
    public final AbstractButton g() {
        if (this.j == null) {
            this.r = com.hw.client.util.c.a("/images/recorder/btn_pause.png");
            final ImageIcon a = com.hw.client.util.c.a("/images/recorder/btn_pause_disabled.png");
            final ImageIcon a2 = com.hw.client.util.c.a("/images/recorder/btn_pause_rollover.png");
            this.s = com.hw.client.util.c.a("/images/recorder/btn_pause_pressed.png");
            final AbstractButton a3;
            (a3 = a(this.r, "ACTION_PAUSE", this.b("audiocomponent.pause"))).setDisabledIcon(a);
            a3.setRolloverIcon(a2);
            a3.setPressedIcon(this.s);
            this.j = a3;
        }
        return this.j;
    }
    
    public final AbstractButton h() {
        if (this.k == null) {
            final ImageIcon a = com.hw.client.util.c.a("/images/recorder/btn_stop.png");
            final ImageIcon a2 = com.hw.client.util.c.a("/images/recorder/btn_stop_disabled.png");
            final ImageIcon a3 = com.hw.client.util.c.a("/images/recorder/btn_stop_rollover.png");
            final ImageIcon a4 = com.hw.client.util.c.a("/images/recorder/btn_stop_pressed.png");
            final AbstractButton a5;
            (a5 = a(a, "ACTION_STOP", this.b("audiocomponent.stop"))).setDisabledIcon(a2);
            a5.setRolloverIcon(a3);
            a5.setPressedIcon(a4);
            this.k = a5;
        }
        return this.k;
    }
    
    public final AbstractButton i() {
        if (this.l == null) {
            this.t = com.hw.client.util.c.a("/images/recorder/btn_record.png");
            this.v = com.hw.client.util.c.a("/images/recorder/btn_record_disabled.png");
            final ImageIcon a = com.hw.client.util.c.a("/images/recorder/btn_record_rollover.png");
            this.u = com.hw.client.util.c.a("/images/recorder/btn_record_pressed.png");
            final AbstractButton a2;
            (a2 = a(this.t, "ACTION_RECORD", this.b("audiocomponent.record"))).setDisabledIcon(this.v);
            a2.setRolloverIcon(a);
            a2.setPressedIcon(this.u);
            this.l = a2;
        }
        return this.l;
    }
    
    private JLabel c() {
        if (this.B == null) {
            final Font font = new Font(VT_6_1_0_11.h.a(), 0, 10);
            final aT b;
            com.hw.client.util.c.a(b = new aT(" 0:00", 4), new Dimension(32, 10));
            b.setFont(font);
            this.B = b;
        }
        return this.B;
    }
    
    private JLabel d() {
        if (this.C == null) {
            final Font font = new Font(VT_6_1_0_11.h.a(), 0, 10);
            final aT c;
            com.hw.client.util.c.a(c = new aT(" -0:00", 2), new Dimension(38, 10));
            c.setFont(font);
            this.C = c;
        }
        return this.C;
    }
    
    private JLabel s() {
        if (this.D == null) {
            final aT d;
            (d = new aT(" 0:00", 4)).setFont(new Font("Dialog", 0, 12));
            this.D = d;
        }
        return this.D;
    }
    
    public final bs j() {
        if (this.w == null) {
            final bs w;
            (w = new bs(0, 1000, 0)).setForeground(Color.red);
            w.setFont(new Font("Dialog", 0, 10));
            w.a(com.hw.client.util.c.a("/images/recorder/cursor_progress.png"));
            w.b(com.hw.client.util.c.a("/images/recorder/cursor_progress_pressed.png"));
            w.a(6);
            w.b(true);
            w.a(ca.a("#EFF4DB", Color.red));
            w.c(ca.a("#C8CDB7", Color.red));
            w.a(new Insets(0, 2, 0, 3));
            this.w = w;
        }
        return this.w;
    }
    
    private JButton t() {
        if (this.n == null) {
            (this.n = new cP()).setActionCommand(null);
            VT_6_1_0_11.h.a(this.n);
            this.n.setIcon(com.hw.client.util.c.a("/images/recorder/btn_wimba.png"));
            this.n.setDisabledSelectedIcon(null);
            com.hw.client.util.c.a(this.n);
        }
        return this.n;
    }
    
    private JButton u() {
        if (this.m == null) {
            (this.m = new cP()).setActionCommand("ACTION_MENU");
            VT_6_1_0_11.h.a(this.m);
            this.m.setIcon(com.hw.client.util.c.a("/images/recorder/btn_menu.png"));
            this.m.setPressedIcon(com.hw.client.util.c.a("/images/recorder/btn_menu_pressed.png"));
            this.m.setRolloverIcon(com.hw.client.util.c.a("/images/recorder/btn_menu_rollover.png"));
            this.m.setDisabledSelectedIcon(null);
            com.hw.client.util.c.a(this.m);
        }
        return this.m;
    }
    
    public final F k() {
        if (this.z == null) {
            (this.z = new F("/images/recorder/power_0.png", "/images/recorder/power_5.png", 0, 100)).b(1);
        }
        return this.z;
    }
    
    public final bq l() {
        if (this.A == null) {
            this.A = new bq(VT_6_1_0_11.C.a().b());
        }
        return this.A;
    }
    
    private static AbstractButton a(final ImageIcon imageIcon, final String actionCommand, final String toolTipText) {
        final JButton button;
        c.a(button = new JButton(imageIcon));
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setRolloverEnabled(true);
        return button;
    }
    
    public final JPanel m() {
        if (this.O == null) {
            final JPanel panel;
            (panel = new JPanel()).setOpaque(false);
            panel.setLayout(new GridBagLayout());
            int n = -1;
            final GridBagConstraints gridBagConstraints2;
            final GridBagConstraints gridBagConstraints = gridBagConstraints2 = new GridBagConstraints();
            ++n;
            gridBagConstraints.gridx = 0;
            gridBagConstraints2.gridy = 0;
            gridBagConstraints2.gridwidth = 1;
            gridBagConstraints2.gridheight = 1;
            gridBagConstraints2.weightx = 0.0;
            gridBagConstraints2.weighty = 0.0;
            gridBagConstraints2.anchor = 15;
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.insets = new Insets(0, 5, 4, 3);
            gridBagConstraints2.ipadx = 0;
            gridBagConstraints2.ipady = 0;
            panel.add(this.l(), gridBagConstraints2);
            ++n;
            panel.add(v(), new GridBagConstraints(1, 0, 1, 1, 0.5, 0.5, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
            if (this.e == 0) {
                ++n;
                panel.add(this.i(), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 15, 0, new Insets(0, 1, 2, 1), 0, 0));
            }
            panel.add(this.f(), new GridBagConstraints(++n, 0, 1, 1, 0.0, 0.0, 15, 0, new Insets(0, 1, 2, 1), 0, 0));
            panel.add(this.g(), new GridBagConstraints(++n, 0, 1, 1, 0.0, 0.0, 15, 0, new Insets(0, 1, 2, 1), 0, 0));
            panel.add(this.h(), new GridBagConstraints(++n, 0, 1, 1, 0.0, 0.0, 15, 0, new Insets(0, 1, 2, 1), 0, 0));
            panel.add(v(), new GridBagConstraints(++n, 0, 1, 1, 0.5, 0.5, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
            panel.add(this.k(), new GridBagConstraints(++n, 0, 1, 1, 0.0, 0.0, 15, 0, new Insets(0, 21, 4, 6), 0, 0));
            final JPanel o;
            (o = new JPanel()).setOpaque(false);
            o.setLayout(new BorderLayout());
            final JPanel panel2 = o;
            final JPanel panel3;
            (panel3 = new JPanel()).setOpaque(false);
            panel3.setLayout(new GridBagLayout());
            panel3.add(this.c(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 12, 0, new Insets(3, 0, 0, 0), 0, 0));
            panel3.add(this.j(), new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, 11, 2, new Insets(3, 5, 0, 5), 0, 10));
            panel3.add(this.d(), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 18, 0, new Insets(3, 0, 0, 0), 0, 0));
            panel3.add(v(), new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0, 10, 3, new Insets(0, 0, 0, 0), 0, 0));
            panel2.add(panel3, "North");
            o.add(panel, "South");
            this.O = o;
        }
        return this.O;
    }
    
    private static JPanel v() {
        final JPanel panel;
        (panel = new JPanel()).setOpaque(false);
        return panel;
    }
    
    public final void e(final boolean enabled) {
        this.f().setEnabled(enabled);
    }
    
    public final dy n() {
        if (this.g == null) {
            this.g = this.a();
        }
        return this.g;
    }
    
    public final void a(String text) {
        if (text == null) {
            text = "";
        }
        this.j().setText(text);
    }
    
    public final void f(final boolean b) {
        this.o();
        this.g = new cb(this, null);
    }
    
    public final void o() {
        this.f().setEnabled(false);
        this.g().setEnabled(false);
        this.h().setEnabled(false);
        this.i().setEnabled(false);
        this.t().setEnabled(false);
        this.u().setEnabled(false);
        this.l().setEnabled(false);
    }
    
    public final void a(final dv dv, final InputStream j) {
        if (dv != null && j != null) {
            this.n().b(0.0);
            this.n().j = j;
            this.n().d = new B(dv);
            if (this.n().q() == 3) {
                this.f().setEnabled(true);
            }
        }
        else {
            this.n().j = null;
            this.n().d = null;
            this.f().setEnabled(false);
            this.d().setText("0:00");
        }
        if (dv != null && dv.g() != 0) {
            this.b(0.0);
        }
    }
    
    private void b(final double n) {
        final dv a = this.n().a();
        this.c().setText(a((int)(n * a.i() / a.g())));
        this.d().setText("-" + a((int)((1.0 - n) * a.i() / a.g())));
        this.s().setText(a(a.i() / a.g()));
    }
    
    public final void p() {
        this.a("");
        this.n().g = false;
        this.n().s();
    }
    
    protected dy a() {
        return new dy(this, this.h);
    }
    
    private static String a(int n) {
        final StringBuffer sb = new StringBuffer();
        if (n < 600) {
            sb.append(" ");
        }
        sb.append(n / 60);
        sb.append(":");
        if ((n %= 60) < 10) {
            sb.append("0");
        }
        sb.append(n % 60);
        return sb.toString();
    }
    
    public final void g(final boolean enabled) {
        this.w().setEnabled(enabled);
    }
    
    private JMenuItem w() {
        if (this.F == null) {
            final JMenuItem f;
            (f = new JMenuItem(this.b("options_save_as"))).setActionCommand("ACTION_SAVE_AS");
            f.addActionListener(this.n());
            f.setEnabled(false);
            this.F = f;
        }
        return this.F;
    }
    
    public final void q() {
        this.b().remove(this.w());
        this.b().remove(this.M);
    }
    
    private String b(final String s) {
        return this.N.getString(s);
    }
    
    public final M r() {
        return this.f;
    }
    
    public final void a(final M f) {
        this.f = f;
    }
    
    static ImageIcon a(final cY cy) {
        return cy.o;
    }
    
    static ImageIcon b(final cY cy) {
        return cy.q;
    }
    
    static ImageIcon c(final cY cy) {
        return cy.t;
    }
    
    static ImageIcon d(final cY cy) {
        return cy.v;
    }
    
    static ImageIcon e(final cY cy) {
        return cy.r;
    }
    
    static ImageIcon f(final cY cy) {
        return cy.p;
    }
    
    static ImageIcon g(final cY cy) {
        return cy.s;
    }
    
    static ImageIcon h(final cY cy) {
        return cy.u;
    }
    
    static JPopupMenu i(final cY cy) {
        return cy.E;
    }
    
    static JMenuItem j(final cY cy) {
        return cy.G;
    }
    
    static JMenuItem k(final cY cy) {
        return cy.H;
    }
    
    static String a(final cY cy, final String s) {
        return cy.b(s);
    }
    
    static void l(cY cy) {
        (cy = cy).b();
        if (a.a()) {
            a.b("AudioComponent.openOptionsPopup(): isAutoGainControlEnabled()" + C.a().i());
        }
        cy.K.setState(C.a().i());
        if (a.a()) {
            a.b("AudioComponent.openOptionsPopup(): isNoiseReductionEnabled()" + C.a().j());
        }
        cy.L.setState(C.a().j());
        cy.G.removeAll();
        cy.G.add(cy.I);
        cy.H.removeAll();
        cy.H.add(cy.J);
        cy.h.a(cy.n());
        cy.h.d();
        cy.b().show(cy.m, 0, cy.m.getHeight());
    }
    
    static JCheckBoxMenuItem m(final cY cy) {
        return cy.L;
    }
    
    static JCheckBoxMenuItem n(final cY cy) {
        return cy.K;
    }
    
    static JMenuItem o(final cY cy) {
        return cy.w();
    }
    
    static M p(final cY cy) {
        return cy.f;
    }
    
    static void a(final cY cy, final double n) {
        cy.b(n);
    }
    
    static void b(cY cy, final double n) {
        final dv b;
        if ((b = (cy = cy).n().b()) != null) {
            cy.c().setText(a((int)(n * cy.n().p() / b.g())));
            cy.d().setText("-" + a((int)((1.0 - n) * cy.n().p() / b.g())));
            cy.s().setText(a((int)(cy.n().p() / b.g())));
        }
    }
}
