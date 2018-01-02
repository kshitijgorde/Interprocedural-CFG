// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import com.bullionvault.chart.b.d;
import java.awt.Component;
import java.awt.Container;
import com.bullionvault.chart.c.e;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JMenu;
import java.awt.event.MouseMotionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import com.bullionvault.chart.g.b;
import com.bullionvault.chart.g.a;
import com.bullionvault.chart.c.h;
import com.bullionvault.chart.resources.Resources;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import com.bullionvault.chart.c.j;
import com.bullionvault.chart.run.ChartApp;
import javax.swing.JPanel;

public final class i extends JPanel implements t
{
    ChartApp a;
    String b;
    private j[] h;
    j c;
    private JPanel i;
    z d;
    private JPanel j;
    private JPanel k;
    private JButton l;
    q e;
    private n m;
    private JLabel n;
    private Font o;
    private Font p;
    private JMenuItem[] q;
    private JMenuItem[] r;
    private JMenuItem[] s;
    private JMenuItem[] t;
    private JMenuItem[] u;
    private JMenuItem[] v;
    public JMenuBar f;
    private String w;
    private final JCheckBoxMenuItem x;
    static Class g;
    private static Class y;
    
    public i(final ChartApp a, String b, final String s, String s2, final String s3, final j j, final boolean b2, final String w) {
        this.a = null;
        this.h = com.bullionvault.chart.c.j.n;
        this.c = new j(com.bullionvault.chart.c.j.i, com.bullionvault.chart.c.j.j, com.bullionvault.chart.c.j.k, com.bullionvault.chart.c.j.l);
        this.o = new Font("SansSerif", 0, 10);
        this.p = this.o;
        this.q = new JMenuItem[0];
        this.r = new JMenuItem[0];
        this.s = new JMenuItem[0];
        this.t = new JMenuItem[0];
        this.u = new JMenuItem[] { null };
        this.v = new JMenuItem[0];
        this.x = new JCheckBoxMenuItem(Resources.b("menu.realtime.start"));
        com.bullionvault.chart.c.h.f("ChartAppPanel - instantiating.");
        this.a = a;
        this.b = b;
        this.w = w;
        if (b == null) {
            b = "Galmarley";
        }
        if (j != null) {
            final j b3;
            if ((b3 = com.bullionvault.chart.c.j.b(j)) != null) {
                com.bullionvault.chart.c.h.e("Setting param: " + b3);
                this.c = b3;
            }
            else {
                com.bullionvault.chart.c.h.d("Unable to find parameters: " + j);
            }
        }
        if (b.equals("BullionVault")) {
            s2 = "chart@bullionvault.com";
        }
        final String string = "http://" + s + "/prices";
        (com.bullionvault.chart.g.b.a = new a()).a(string, s3);
        this.e();
        this.x.setEnabled(false);
        this.u[0] = this.x;
        this.x.addActionListener(new com.bullionvault.chart.a.j(this));
        this.setLayout(new BorderLayout());
        (this.f = new JMenuBar()).addMouseMotionListener(new com.bullionvault.chart.a.h(this));
        this.f.setFont(this.f.getFont().deriveFont(2));
        this.f.add(com.bullionvault.chart.a.r.a(Resources.b("menu.series"), this.q, this));
        this.f.add(com.bullionvault.chart.a.r.a(Resources.b("menu.currency"), this.r, this));
        this.f.add(com.bullionvault.chart.a.r.a(Resources.b("menu.style"), this.s, this));
        this.f.add(com.bullionvault.chart.a.r.a(Resources.b("menu.period"), this.t, this));
        this.f.add(com.bullionvault.chart.a.r.a(Resources.b("menu.realtime"), this.u, this));
        if (this.w != null) {
            final JMenu menu;
            (menu = new JMenu()).setText(Resources.b("menu.commentary"));
            final JCheckBoxMenuItem checkBoxMenuItem;
            (checkBoxMenuItem = new JCheckBoxMenuItem()).setText(Resources.b("menu.commentary.toggle"));
            checkBoxMenuItem.setState(true);
            checkBoxMenuItem.addActionListener(new l(this, checkBoxMenuItem));
            menu.add(checkBoxMenuItem);
            menu.setFont(menu.getFont().deriveFont(0));
            this.f.add(menu);
        }
        final JMenuItem menuItem;
        (menuItem = new JMenuItem()).setText(Resources.b("menu.export.csv"));
        menuItem.addActionListener(new k(this, string));
        this.f.add(com.bullionvault.chart.a.r.a(Resources.b("menu.export"), new JMenuItem[] { menuItem }, this));
        this.f.add(com.bullionvault.chart.a.r.a(Resources.b("menu.help"), this.v, this));
        (this.j = new JPanel()).setLayout(new GridBagLayout());
        (this.l = new JButton(Resources.b("chart_panel.click_here"))).setBackground(Color.red);
        this.l.setForeground(Color.white);
        this.l.addActionListener(new f(this));
        com.bullionvault.chart.c.e.a(this.j, this.l, 0, 0, 1, 1, 0, 17, 0.0, 0.0, 0, 0, 0, 0);
        com.bullionvault.chart.c.e.a(this.j, new JLabel(Resources.b("chart_panel.to_enable")), 1, 0, 1, 1, 1, 17, 1.0, 0.0, 0, 0, 0, 0);
        com.bullionvault.chart.c.e.a(this.j, new m(), 0, 1, 0, 1, 1, 10, 1.0, 0.0, 0, 0, 0, 0);
        (this.i = new JPanel()).setLayout(new GridBagLayout());
        com.bullionvault.chart.c.e.a(this.i, this.j, 0, 0, 1, 1, 1, 17, 1.0, 0.0, 1, 1, 1, 1);
        (this.k = new JPanel()).setLayout(new GridBagLayout());
        (this.n = new JLabel(Resources.b("chart_panel.new_information"))).setFont(this.o);
        com.bullionvault.chart.c.e.a(this.k, this.n, 0, 0, 1, 1, 2, 17, 1.0, 0.0, 0, 0, 0, 0);
        com.bullionvault.chart.b.b b4;
        if (b.equals("BullionVault")) {
            (b4 = new com.bullionvault.chart.b.b(" BullionVault ", "SansSerif", 1)).a(new Color(254, 239, 198));
        }
        else {
            b4 = new com.bullionvault.chart.b.b("Galmarley", "Serif", 0);
        }
        this.d = new z(this, s2, 2, 800, 200, b4, this.w);
        if (b.equals("BullionVault")) {
            final d a2;
            (a2 = this.d.a).b = new Color(254, 239, 198);
            a2.c = new Color(252, 230, 191);
            a2.d = new Color(254, 245, 222);
            a2.e = new Color(192, 192, 192);
            new Color(192, 192, 192);
            a2.f = new Color(140, 162, 99).darker();
            a2.g = a2.b;
            a2.h = new Color(233, 237, 226);
            a2.i = new Color(237, 233, 226);
            a2.j = Color.blue.darker();
            a2.k = new Color(0.1f, 0.5f, 1.0f);
            a2.l = Color.red.darker();
        }
        this.a(this.c);
        this.add(this.i, "North");
        this.add(this.k, "South");
        this.add(this.d, "Center");
        this.validate();
        com.bullionvault.chart.c.h.f("ChartAppPanel - instantiated.");
        if (b2) {
            this.d.b(j.b);
        }
    }
    
    private void e() {
        com.bullionvault.chart.c.h.f("ChartAppPanel - parseMenuOptions");
        final Vector vector = new Vector();
        final Vector vector2 = new Vector();
        final Vector vector3 = new Vector();
        final Vector vector4 = new Vector();
        com.bullionvault.chart.c.h.e("Generating option vectors...");
        for (int i = 0; i < this.h.length; ++i) {
            final j j = this.h[i];
            this.a(vector, j.a, j.h);
            this.a(vector2, j.b, j.h);
            this.a(vector3, j.d, j.h);
            this.a(vector4, j.e, j.h);
        }
        com.bullionvault.chart.c.h.f("Converting parameters to array options...");
        com.bullionvault.chart.c.h.f("seriesMenuItems [" + vector.size() + "]");
        com.bullionvault.chart.c.h.f("currencyMenuItems [" + vector2.size() + "]");
        com.bullionvault.chart.c.h.f("styleMenuItems [" + vector3.size() + "]");
        com.bullionvault.chart.c.h.f("periodMenuItems [" + vector4.size() + "]");
        this.r = a(vector2);
        this.q = a(vector);
        this.s = a(vector3);
        this.t = a(vector4);
        this.v = new JMenuItem[] { new o(this, Resources.b("menu.help.topics")), null, new o(this, Resources.b("menu.help.bullionvault")), new o(this, Resources.b("menu.help.galmarley")), null, new o(this, Resources.b("menu.help.about")) };
        com.bullionvault.chart.c.h.f("ChartAppPanel - parseMenuOptions - done");
    }
    
    private void a(final Vector vector, final String s, final boolean b) {
        JMenuItem a;
        if (a(vector, s) == null) {
            (a = new JMenuItem(s)).addActionListener(new com.bullionvault.chart.a.e(this));
            vector.addElement(a);
            a.setEnabled(false);
        }
        else {
            a = a(vector, s);
        }
        if (a == null) {
            throw new RuntimeException("Unable to set JMenuItem [" + s + "]");
        }
        if (!b) {
            a.setEnabled(true);
        }
    }
    
    private static JMenuItem a(final Vector vector, final Object o) {
        if (o == null) {
            return null;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final JMenuItem menuItem;
            if (vector.elementAt(i) != null && (menuItem = vector.elementAt(i)).getText().equals(o)) {
                return menuItem;
            }
        }
        return null;
    }
    
    public final void a(final j c) {
        com.bullionvault.chart.c.h.f("ChartAppPanel - setParams - " + c);
        this.c = c;
        this.d.e = this.c;
        this.d.a.a(Resources.b(c.a) + ": " + Resources.b(c.b) + " " + Resources.b(c.e));
    }
    
    final void a(final String s) {
        com.bullionvault.chart.c.h.e("Opening webpage: " + s);
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        try {
            this.a.getAppletContext().showDocument(url, "_blank");
        }
        catch (NullPointerException ex2) {
            throw new RuntimeException(ex2);
        }
        com.bullionvault.chart.c.h.e("Opened webpage: " + s);
    }
    
    final j a(final String s, final String s2) {
        final j b = this.c.b();
        if (s.equals(Resources.b("menu.series"))) {
            b.a = s2;
        }
        else if (s.equals(Resources.b("menu.currency"))) {
            b.b = s2;
        }
        else if (s.equals(Resources.b("menu.style"))) {
            b.d = s2;
        }
        else {
            if (!s.equals(Resources.b("menu.period"))) {
                throw new RuntimeException("Unknown Graph option selected [" + s2 + "] from menu [" + s + "]");
            }
            b.e = s2;
        }
        com.bullionvault.chart.c.h.f("Searching for parameters: [" + b + "]");
        boolean b2 = false;
        for (int i = 0; i < this.h.length; ++i) {
            final j j = this.h[i];
            if (b.a(j)) {
                com.bullionvault.chart.c.h.f("Found matching parameters: [" + j + "]");
                b.f = j.f;
                b.g = j.g;
                b.c = j.c;
                b.e = j.e;
                b.b = j.b;
                b.a = j.a;
                b.d = j.d;
                b2 = true;
            }
        }
        if (!b2) {
            throw new RuntimeException("Unable to find matching option for parameters [" + b + "]");
        }
        com.bullionvault.chart.c.h.f("Found option URL: [" + b.f + "]");
        return b;
    }
    
    public final void addNotify() {
        com.bullionvault.chart.c.h.f("ChartAppPanel - addNotify - enter");
        super.addNotify();
        this.a();
        com.bullionvault.chart.c.h.f("ChartAppPanel - addNotify - exit");
    }
    
    public final JFrame a() {
        return (JFrame)SwingUtilities.getAncestorOfClass((com.bullionvault.chart.a.i.y == null) ? (com.bullionvault.chart.a.i.y = d("javax.swing.JFrame")) : com.bullionvault.chart.a.i.y, this);
    }
    
    public final void b() {
        com.bullionvault.chart.c.h.f("ChartAppPanel - processLoginResult - enter");
        if (com.bullionvault.chart.g.b.a.b() == 0 || com.bullionvault.chart.g.b.a.b() == 3 || com.bullionvault.chart.g.b.a.b() == 1) {
            com.bullionvault.chart.c.h.e("Login: User not fully registered [" + com.bullionvault.chart.g.b.a.b() + "]");
            this.setVisible(true);
            this.validate();
            this.repaint();
        }
        else if (com.bullionvault.chart.g.b.a.b() == 2) {
            com.bullionvault.chart.c.h.e("Login: Authorised Username");
            a(this.s);
            a(this.t);
            a(this.u);
            a(this.r);
            this.j.setVisible(false);
            this.validate();
            this.repaint();
        }
        else {
            com.bullionvault.chart.c.h.b("Unknown Access Controller Status [" + com.bullionvault.chart.g.b.a.b() + "]");
        }
        com.bullionvault.chart.c.h.f("ChartAppPanel - processLoginResult - exit");
    }
    
    private static void a(final JMenuItem[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i].setEnabled(true);
        }
    }
    
    public final void c() {
        final z d;
        if (!(d = this.d).d || d.b == null) {
            d.d = true;
            d.d();
            d.a(d.e);
            return;
        }
        final z z = d;
        z.a(z.b, true);
    }
    
    public final void d() {
        com.bullionvault.chart.c.h.e("ChartAppPanel stopThreads() - stopping chart Threads");
        final z d;
        if ((d = this.d).b != null && com.bullionvault.chart.c.k.c(d.b)) {
            com.bullionvault.chart.c.h.e("ChartPanel stopThreads() - stopping readerThread");
            d.b.b();
            d.b = null;
        }
        if (d.c != null) {
            com.bullionvault.chart.c.h.e("ChartPanel stopThreads() - stopping real-time Threads");
            d.c.b();
            d.c = null;
        }
        if (this.m != null) {
            com.bullionvault.chart.c.h.e("ChartAppPanel stopThreads() - stopping Ticker Label");
            this.m.a();
        }
        com.bullionvault.chart.c.h.e("ChartAppPanel - threads stopped");
    }
    
    public final void b(final String text) {
        this.n.setText(text);
    }
    
    public final void c(final String text) {
        if (this.m == null) {
            (this.m = new n()).setFont(this.p);
            com.bullionvault.chart.c.e.a(this.k, this.m, 0, 1, 1, 1, 2, 17, 1.0, 0.0, 0, 0, 0, 0);
        }
        this.m.setText(text);
    }
    
    private static JMenuItem[] a(final Vector vector) {
        final JMenuItem[] array = new JMenuItem[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    static JCheckBoxMenuItem a(final i i) {
        return i.x;
    }
    
    static Class d(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
