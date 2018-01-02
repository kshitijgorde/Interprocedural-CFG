// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.util.GregorianCalendar;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import COM.NextBus.AdminMap.Toolbar.AgencyToolbarPanel;
import java.awt.Label;
import java.awt.Choice;
import COM.NextBus.Applets.a;
import java.awt.Container;
import java.awt.Image;
import java.awt.Dialog;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;

public final class N implements ActionListener
{
    Button a;
    Button b;
    Button c;
    Button d;
    Button e;
    Panel f;
    private Dialog g;
    private Button h;
    private Button i;
    private Button j;
    private Dialog k;
    private Dialog l;
    private Dialog m;
    private ImageButton n;
    private ImageButton o;
    private static Image p;
    private static Image q;
    private static Image r;
    private static Image s;
    private static Image t;
    private Container u;
    private O v;
    private MapCanvas w;
    private a x;
    private static String[] y;
    private Choice z;
    private Choice A;
    private Choice B;
    private Choice C;
    private Choice D;
    private Choice E;
    private Label F;
    private static int G;
    private static int[] H;
    private int I;
    private long J;
    private long K;
    
    public N(final Container u, final O v) {
        this.I = 0;
        this.J = 0L;
        this.K = 0L;
        this.u = u;
        this.v = v;
    }
    
    public final PropertiesDialog a() {
        this.i();
        return (PropertiesDialog)this.m;
    }
    
    public final AgencyToolbarPanel b() {
        return (AgencyToolbarPanel)this.f;
    }
    
    public final void c() {
        this.g.setVisible(false);
        this.h.setVisible(true);
    }
    
    public final void d() {
        final KeyAdapter keyAdapter = this.v.o() ? new J(this) : new C(this);
        this.w = this.v.f;
        this.u.setBackground(new Color(COM.NextBus.a.a.b));
        this.u.setLayout(new BorderLayout());
        this.h();
        Panel f;
        if (this.v.o()) {
            final Panel panel = new Panel(new GridLayout(1, 3));
            final Panel panel2;
            (panel2 = new Panel(new FlowLayout(0, 1, 1))).add(this.b);
            panel2.add(this.a);
            final Panel panel3;
            (panel3 = new Panel(new FlowLayout(1, 1, 1))).add(this.c);
            final Panel panel4;
            (panel4 = new Panel(new FlowLayout(2, 1, 1))).add(this.j);
            panel.add(panel2);
            panel.add(panel3);
            panel.add(panel4);
            f = panel;
        }
        else {
            f = new AgencyToolbarPanel(this.v.K(), this.v, this.j, this.c, this.e, this.d, this.i, this.b, this.a);
        }
        this.f = f;
        if (this.v.o()) {
            if (!this.v.h()) {
                this.w.add(this.f, "North");
            }
        }
        else {
            (this.h = new Button(COM.NextBus.AdminMap.a.b("Show Toolbar") + "...")).setVisible(true);
            this.v.a(this.h);
            this.w.add(this.h);
            this.h.addActionListener(new b(this));
            (this.g = new Dialog(COM.NextBus.AdminMap.r.a(this.u), COM.NextBus.AdminMap.a.b("Map Toolbar for") + " " + this.v.v())).addWindowListener(new d(this));
            this.g.toFront();
            this.g.setLocation(90, 40);
            this.g.setBackground(this.v.x());
            final Dialog g;
            (g = this.g).add(this.f, "North");
            if (this.v.O()) {
                final ClassLoader classLoader = this.getClass().getClassLoader();
                final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                try {
                    N.p = defaultToolkit.getImage(classLoader.getResource("COM/NextBus/AdminMap/pause_on.jpg"));
                    N.q = defaultToolkit.getImage(classLoader.getResource("COM/NextBus/AdminMap/play_pause_off.jpg"));
                    N.r = defaultToolkit.getImage(classLoader.getResource("COM/NextBus/AdminMap/play_on.jpg"));
                    N.s = defaultToolkit.getImage(classLoader.getResource("COM/NextBus/AdminMap/stop_on.jpg"));
                    N.t = defaultToolkit.getImage(classLoader.getResource("COM/NextBus/AdminMap/stop_off.jpg"));
                }
                catch (NullPointerException ex) {
                    N.p = null;
                    N.q = null;
                    N.r = null;
                    N.s = null;
                    N.t = null;
                }
                final Panel panel5 = new Panel(new BorderLayout(0, 0));
                g.add(panel5, "Center");
                final Panel panel6 = new Panel(new BorderLayout(1, 1));
                panel5.add(panel6, "North");
                final Panel panel7 = new Panel(new BorderLayout(1, 1));
                panel5.add(panel7, "South");
                this.a(panel6);
                final Panel panel8 = panel7;
                final Panel panel9 = new Panel(new FlowLayout(1, 1, 1));
                final Panel panel10 = new Panel(new FlowLayout(1, 1, 1));
                final Label label;
                (label = new Label(COM.NextBus.AdminMap.a.b("Status") + ":")).setFont(O.k);
                label.setAlignment(2);
                panel10.add(label);
                (this.F = new Label(COM.NextBus.AdminMap.a.b("Stopped") + "                                                                                  ")).setFont(O.k);
                panel10.add(this.F);
                if (N.s != null) {
                    this.n = new ImageButton(N.s, N.s, 30, 25);
                }
                else {
                    this.n = new ImageButton(30, 25, "Stop");
                }
                if (N.q != null) {
                    this.o = new ImageButton(N.q, N.q, 50, 25);
                }
                else {
                    this.o = new ImageButton(65, 25, "Play/Pause");
                }
                this.n.a(new Color(COM.NextBus.a.a.e));
                this.o.a(new Color(COM.NextBus.a.a.e));
                this.o.a(new ab(this));
                this.n.a(new Y(this));
                this.v.a(this.n);
                this.v.a(this.o);
                panel9.add(this.n);
                panel9.add(this.o);
                panel8.add(panel9, "East");
                panel8.add(panel10, "West");
            }
            COM.NextBus.AdminMap.r.a(g, keyAdapter);
            if (this.g != null) {
                this.g.setVisible(true);
                this.g.validate();
                this.g.pack();
            }
        }
        this.u.setVisible(true);
        this.w.setBackground(new Color(COM.NextBus.a.a.b));
        this.u.add(this.w, "Center");
        this.u.invalidate();
        this.u.validate();
        COM.NextBus.AdminMap.r.a(this.u, keyAdapter);
        if (this.h != null) {
            this.h.setVisible(false);
        }
    }
    
    private void h() {
        this.x = new a();
        final Iterator<t> iterator = this.v.c.b().iterator();
        while (iterator.hasNext()) {
            this.x.a(iterator.next());
        }
        this.a = new Button(COM.NextBus.AdminMap.a.b("Zoom In"));
        this.b = new Button(COM.NextBus.AdminMap.a.b("Zoom Out"));
        this.v.a(this.b);
        this.b.addActionListener(new e(this));
        this.v.a(this.a);
        this.a.addActionListener(new R(this));
        (this.c = new Button(COM.NextBus.AdminMap.a.b(this.v.k() + "s") + "...")).setEnabled(false);
        this.v.a(this.c);
        this.c.addActionListener(this.x);
        if (!this.v.o()) {
            this.e = new Button(COM.NextBus.AdminMap.a.b("Vehicles") + "...");
            this.v.a(this.e);
            this.e.addActionListener(this.x);
            if (!this.v.O()) {
                this.d = new Button(COM.NextBus.AdminMap.a.b("Events") + "...");
                this.v.a(this.d);
                this.d.addActionListener(this.x);
            }
            this.i = new Button(COM.NextBus.AdminMap.a.b("Properties") + "...");
            this.v.a(this.i);
            this.i.addActionListener(this);
        }
        this.j = new Button(COM.NextBus.AdminMap.a.b("Help") + "...");
        this.v.a(this.j);
        this.j.addActionListener(this);
    }
    
    private void a(int i, final int n, int n2) {
        int n3 = N.H[n];
        if (n == 1 && i % 4 == 0) {
            n3 = 29;
        }
        if (n2 > n3) {
            n2 = n3;
        }
        this.C.removeAll();
        String s;
        for (i = 1; i <= n3; ++i) {
            if ((s = Integer.toString(i)).length() < 2) {
                s = "  " + s;
            }
            this.C.addItem(s);
        }
        this.C.select(n2 - 1);
    }
    
    private void a(final Panel panel) {
        final Panel panel2 = new Panel(new FlowLayout(1, 1, 1));
        final Panel panel3 = new Panel(new FlowLayout(1, 1, 1));
        final Panel panel4 = new Panel(new FlowLayout(1, 1, 1));
        final Label label;
        (label = new Label(COM.NextBus.AdminMap.a.b("Rate") + ":")).setFont(O.k);
        label.setAlignment(2);
        panel2.add(label);
        (this.z = new Choice()).addItem("1x");
        this.z.addItem("2x");
        this.z.addItem("5x");
        this.z.addItem("10x");
        this.z.addItem("20x");
        this.z.addItem("60x");
        this.z.addItem("120x");
        this.z.select("60x");
        this.v.a(this.z);
        panel2.add(this.z);
        this.z.addItemListener(new j(this));
        final Label label2;
        (label2 = new Label(COM.NextBus.AdminMap.a.b("Begin Date") + ":")).setFont(O.k);
        label2.setAlignment(2);
        this.A = new Choice();
        this.B = new Choice();
        this.C = new Choice();
        this.D = new Choice();
        this.E = new Choice();
        this.v.a(this.A);
        this.v.a(this.B);
        this.v.a(this.C);
        this.v.a(this.D);
        this.v.a(this.E);
        final GregorianCalendar gregorianCalendar;
        final int value = (gregorianCalendar = new GregorianCalendar()).get(1);
        final int value2 = gregorianCalendar.get(2);
        final int value3 = gregorianCalendar.get(5);
        for (int i = value - 2; i <= value; ++i) {
            this.A.add(Integer.toString(i));
        }
        this.A.select(Integer.toString(value));
        this.A.addItemListener(new p(this));
        for (int j = 0; j < 12; ++j) {
            this.B.add(COM.NextBus.AdminMap.a.b(N.y[j]));
        }
        this.B.select(COM.NextBus.AdminMap.a.b(N.y[value2]));
        this.B.addItemListener(new ad(this));
        this.a(value, value2, value3);
        final Label label3;
        (label3 = new Label(COM.NextBus.AdminMap.a.b("Hour/Minute") + ":")).setFont(O.k);
        label3.setAlignment(2);
        for (int k = 0; k <= 23; ++k) {
            int n;
            String s;
            if ((n = k) < 12) {
                if (n == 0) {
                    n = 12;
                }
                s = "" + ((n < 10) ? "  " : "") + n + (COM.NextBus.AdminMap.a.a() ? "" : "AM");
            }
            else {
                if (n != 12 && !COM.NextBus.AdminMap.a.a()) {
                    n -= 12;
                }
                s = "" + ((n < 10) ? "  " : "") + n + (COM.NextBus.AdminMap.a.a() ? "" : "PM");
            }
            this.D.add(s);
        }
        this.D.select(8);
        for (int l = 0; l < 60; l += N.G) {
            this.E.add((l < 10) ? ("0" + Integer.toString(l)) : Integer.toString(l));
        }
        panel4.add(label2);
        panel4.add(this.B);
        panel4.add(this.C);
        panel4.add(this.A);
        panel3.add(label3);
        panel3.add(this.D);
        panel3.add(this.E);
        panel.add(panel2, "East");
        panel.add(panel3, "Center");
        panel.add(panel4, "West");
    }
    
    public final GregorianCalendar e() {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(this.v.q());
        gregorianCalendar.set(Integer.parseInt(this.A.getSelectedItem()), this.B.getSelectedIndex(), this.C.getSelectedIndex() + 1, this.D.getSelectedIndex(), this.E.getSelectedIndex() * N.G, 0);
        return gregorianCalendar;
    }
    
    public final void f() {
        this.F.setText(this.v.d.b());
        this.a(this.v.d.c());
    }
    
    private void a(final int i) {
        if (i == this.I) {
            return;
        }
        if ((this.I = i) == COM.NextBus.AdminMap.F.b) {
            this.n.a(N.t, N.t);
            this.o.a(N.r, N.r);
            return;
        }
        if (i == COM.NextBus.AdminMap.F.c) {
            this.n.a(N.t, N.t);
            this.o.a(N.p, N.p);
            return;
        }
        this.n.a(N.s, N.s);
        this.o.a(N.q, N.q);
    }
    
    public final Frame g() {
        return COM.NextBus.AdminMap.r.a(this.u);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.j) {
            if (System.currentTimeMillis() - this.J < 1000L) {
                ++this.K;
                if (this.K >= 2L) {
                    if (this.l == null) {
                        this.l = new DebugDialog(COM.NextBus.AdminMap.r.a(this.u), "Debug", this.v);
                    }
                    this.l.setVisible(true);
                }
            }
            else {
                this.K = 0L;
                if (this.k == null) {
                    this.k = new HelpDialog(COM.NextBus.AdminMap.r.a(this.u), COM.NextBus.AdminMap.a.b("Help"), this.v.f.getParent(), this.v);
                }
                this.k.setVisible(true);
            }
            this.J = System.currentTimeMillis();
            return;
        }
        if (actionEvent.getSource() == this.v.b.i) {
            this.i();
            this.m.setVisible(true);
        }
    }
    
    private void i() {
        if (this.m == null) {
            this.m = new PropertiesDialog(COM.NextBus.AdminMap.r.a(this.v.b.u), COM.NextBus.AdminMap.a.b("Properties"), this.v);
        }
    }
    
    static {
        N.y = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        N.G = 5;
        N.H = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    }
}
