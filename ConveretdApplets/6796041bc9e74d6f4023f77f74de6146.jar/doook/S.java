// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.TextComponent;
import java.applet.Applet;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.TextArea;

public class S extends cs implements aB, au
{
    public D a;
    public cA b;
    private B b;
    private R a;
    private P a;
    private N a;
    public u a;
    public TextArea a;
    private Canvas k;
    private Canvas l;
    private Canvas m;
    private cr a;
    private W a;
    private Image m;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private Dimension c;
    private Insets a;
    private Image n;
    private an a;
    private aR a;
    private Hashtable e;
    private int y;
    private Canvas n;
    private Canvas o;
    private Canvas p;
    private Canvas q;
    private Canvas r;
    private aX c;
    public static cv a;
    public static cD a;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        aB a = null;
        if (this.a.a.p() && s != null) {
            final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
            if (a2 != null && a3 != null) {
                a = cz.a(a2, a3, null);
                ((cz)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new aS(70, 20);
            }
            else {
                a = new aS(s3, 70, 20);
            }
            ((aS)a).a(s4, null);
        }
        return (aS)a;
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        return this.a(s, s2, s3, 25, 25);
    }
    
    private final Canvas a(final String s, final String s2, final String s3, final int n, final int n2) {
        try {
            aB a;
            if (!this.a.a.p() || s == null) {
                a = new aS(n, n2);
                ((aS)a).a(this.a.a(s3, false, 20));
            }
            else {
                final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
                final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
                final Image a4 = this.a.a(s + s2 + "_button_disabled.gif", true);
                if (a2 == null || a3 == null || a4 == null) {
                    a = new aS(n, n2);
                    ((aS)a).a(this.a.a(s3, false, 20));
                }
                else {
                    a = cz.a(a2, a3, a4);
                }
            }
            return (aS)a;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Canvas();
        }
    }
    
    public void a(final at at) {
        this.a.a(at);
    }
    
    public void a(final Z z) {
        this.a.a(z);
    }
    
    public void a() {
        this.a.a();
        if (z.W) {
            super.setVisible(false);
            z.a.remove(this);
            z.a.setVisible(true);
            z.a.validate();
        }
    }
    
    public D a() {
        return this.a;
    }
    
    public Frame a() {
        return this.a;
    }
    
    public Container a() {
        return this;
    }
    
    public String a(final Object o) {
        try {
            if (o == this.a) {
                return ao.e("Type your message here, then press ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.b) {
                return am.a(ao.e("Click here to visit %1."), new String[] { this.b.a().toString() });
            }
            if (o == this.a) {
                return ao.e("Chat messages are displayed here.  Single-click on a private message to reply.");
            }
            if (o == this.n) {
                return ao.e("Click here to insert emoticons into your message.");
            }
            if (o == this.o) {
                return ao.e("Click here to insert a shortcut word into your message.");
            }
            if (o == this.r) {
                return ao.e("Click here and select your message color.");
            }
            if (o == this.p) {
                return ao.e("Click here to make text Bold.");
            }
            if (o == this.q) {
                return ao.e("Click here to make text Italic.");
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (this.m instanceof aS) {
                    ((aS)this.m).s();
                }
                else {
                    ((cz)this.m).a();
                }
                return true;
            }
            case 401: {
                if (event.key == 27) {
                    this.a.append("\n");
                    break;
                }
                if (event.modifiers == 2) {
                    if (event.key == 18) {
                        this.p();
                        return true;
                    }
                    if (event.key == 5) {
                        this.o();
                        return true;
                    }
                    if (event.key == 9) {
                        aj.a("i", this.a);
                        return true;
                    }
                    if (event.key == 2) {
                        aj.a("b", this.a);
                        return true;
                    }
                    if (event.key == 1) {
                        aj.a(this.a);
                        return true;
                    }
                    if (event.key == 10) {
                        this.a.append("\n");
                        this.a.setCaretPosition(this.a.getText().length());
                        return true;
                    }
                }
                if (event.key != 10 && event.key != f.g) {
                    break;
                }
                if (this.l instanceof aS) {
                    ((aS)this.l).s();
                }
                else {
                    ((cz)this.l).a();
                }
                return true;
            }
            case 1001: {
                if (event.target == this.n) {
                    this.o();
                    return true;
                }
                if (event.target == this.o) {
                    this.p();
                    return true;
                }
                if (event.target == this.p) {
                    aj.a("b", this.a);
                    return true;
                }
                if (event.target == this.q) {
                    aj.a("i", this.a);
                    return true;
                }
                if (event.target == this.r) {
                    this.c.c();
                    return true;
                }
                if (event.target == this.l) {
                    String s = this.a.getText().trim();
                    this.a.setText("");
                    if (s.length() > 0) {
                        if (s.length() > 1024) {
                            s = s.substring(0, 1024);
                        }
                        if (System.currentTimeMillis() - this.a.a() < this.a.f()) {
                            try {
                                Thread.sleep(this.a.f() - (System.currentTimeMillis() - this.a.a()));
                            }
                            catch (InterruptedException ex) {}
                        }
                        this.b(s);
                        this.a.a(s, this.c.aA, this.a.getFont().isBold(), this.a.getFont().isItalic());
                        this.a.s();
                    }
                    if (f.h) {
                        this.a.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.m) {
                    this.a.i();
                    return true;
                }
                if (event.target == this.k) {
                    this.a.c(0);
                    return true;
                }
                if (event.target instanceof MenuItem) {
                    return this.a.a(event);
                }
                if (event.arg instanceof Z) {
                    final ab ab = (ab)this.a.c.b(((Z)event.arg).Z);
                    if (ab != null) {
                        if (event.modifiers == 2) {
                            final cD cd = new cD(67074, 1);
                            cd.j = ab.h();
                            this.a.o(cd);
                        }
                        else {
                            if (event.modifiers == 8 && this.a.d(44) && !ab.d(34)) {
                                final cD cd2 = new cD(66305, 1);
                                cd2.o = -1;
                                cd2.j = ab.h();
                                cd2.a(0, 0, -1);
                                cd2.a(0, 0, "bkick=");
                                this.a.o(cd2);
                                return true;
                            }
                            if (event.modifiers == 10 && this.a.d(49) && !ab.d(34)) {
                                final cD cd3 = new cD(66305, 1);
                                cd3.o = -1;
                                cd3.j = ab.h();
                                cd3.a(0, 0, -1);
                                cd3.a(0, 0, "kick=");
                                this.a.o(cd3);
                                return true;
                            }
                            if (event.modifiers == 11 && this.a.d(49) && !ab.d(34)) {
                                final cD cd4 = new cD(66305, 1);
                                cd4.o = -1;
                                cd4.j = ab.h();
                                cd4.a(0, 0, -1);
                                cd4.a(0, 0, "IPX");
                                this.a.o(cd4);
                                return true;
                            }
                        }
                    }
                }
                if (event.arg instanceof Z) {
                    final Z z = (Z)event.arg;
                    final ab ab2 = (ab)this.a.c.b(z.Z);
                    if (z.w && z.Z != this.a.h()) {
                        this.a.a(z, (cG)ab2);
                        break;
                    }
                    if (this.a.d(43) && ab2 != null && z.v && z.ai == -1) {
                        this.a.a(z, (cG)ab2);
                        return true;
                    }
                    if (z.ai != -1) {
                        return true;
                    }
                    break;
                }
                else {
                    if (event.arg instanceof URL) {
                        this.a.a((URL)event.arg, "_blank");
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.m != null) {
            this.c = this.getSize();
            this.a = this.insets();
            this.ac = this.c.width - this.a.right - this.a.left + 30;
            this.ad = this.c.height - this.a.bottom - this.a.top + 30;
            this.ae = this.m.getWidth(this);
            this.af = this.m.getHeight(this);
            this.n = this.createImage(this.ac, this.ad);
            final Graphics graphics2 = this.n.getGraphics();
            graphics2.drawImage(this.m, 0, 0, null);
            for (int i = 0; i <= this.ac / this.ae; ++i) {
                for (int j = 0; j <= this.ad / this.af; ++j) {
                    if (i + j > 0) {
                        graphics2.copyArea(0, 0, this.ae, this.af, i * this.ae, j * this.af);
                    }
                }
            }
            graphics2.dispose();
            graphics.drawImage(this.n, 0, 0, this);
            this.b.a(this.n);
        }
        final int width = this.a.a.p() ? this.a.getSize().width : (this.getSize().width - 37);
        if (S.a.width != width) {
            S.a.width = width;
            S.a.au = true;
            if (S.a.ag != "") {
                S.a.start();
            }
            else {
                S.a.stop();
            }
        }
    }
    
    public void b() {
        this.a.b();
        this.a.setFont(this.a.a.b());
    }
    
    public boolean a(final T t) {
        return this.a.a(t);
    }
    
    public boolean a(final ab ab) {
        return this.a.a(ab);
    }
    
    public void a(final String s) {
        try {
            this.b.a(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void a(final T t) {
        if (this.a != null) {
            this.a.c(t);
        }
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void a(final boolean b) {
        this.a.b(0);
        this.a.a(b);
    }
    
    public void a(final ac ac, final boolean b, final boolean b2) {
        if (this.a.h()) {
            this.a.a(ac, b, false);
        }
    }
    
    public void b(final T t) {
        this.a.a(t);
    }
    
    public void a(final ab ab, final boolean b) {
        this.a.a(ab, b);
    }
    
    private final void b(final String s) {
        this.e.put(new Integer(this.y), new Object[] { new Long(System.currentTimeMillis()), s });
        if (this.a.K != 0 && this.a.L != 0) {
            final Object[] array = this.e.get(new Integer(this.y - this.a.K + 1));
            if (array != null && System.currentTimeMillis() - (long)array[0] < this.a.L * 1000) {
                this.a.k();
            }
        }
        if (this.a.J != 0) {
            int n = 1;
            for (int i = this.y - 1; i > this.y - this.a.J; --i) {
                final Object[] array2 = this.e.get(new Integer(i));
                if (array2 == null) {
                    break;
                }
                (long)array2[0];
                if (((String)array2[1]).equals(s)) {
                    ++n;
                }
            }
            if (n >= this.a.J) {
                this.a.k();
            }
        }
        ++this.y;
    }
    
    private void o() {
        if (t.Q) {
            final ck ck = new ck(this.a.a.a(), this.a, this.a);
            ck.d();
            ck.setVisible(true);
        }
    }
    
    private void p() {
        if (t.R) {
            final cq cq = new cq(this.a.a.a(), this.a, this.a);
            cq.d();
            cq.setVisible(true);
        }
    }
    
    public S(final u a, final an a2) {
        super(0);
        this.e = new Hashtable();
        this.y = 0;
        this.a = a2;
        this.setBackground(a.a.a);
        this.a = a;
        a.a = new MediaTracker(this);
        t.a = a.a("lockIcon.gif", false, 20);
        t.b = a.a("arrowIcon.gif", false, 20);
        a.a.t = a.a(a.a.e() + "helpLogo.gif", true, 80);
        a.a.u = a.a(a.a.e() + "chatLogo.gif", true, 10);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final cA ca = new cA(a.a.aE);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        ca.setBackground(a.a.b);
        this.setLayout(layout);
        ca.setLayout(layout);
        try {
            this.a = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.a = new TextArea(2, 10);
        }
        (this.a = new W(a, true)).setFont(a.a.b());
        final boolean b = a.e() || a.f() || a.h();
        S.a = new cv(cI.b);
        S.a.ag = "";
        S.a.aJ = 13;
        S.a.as = true;
        S.a.width = 20;
        S.a.height = 20;
        gridBagConstraints.insets = new Insets(2, 5, 12, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        layout.setConstraints(S.a, gridBagConstraints);
        if (this.a.a.p()) {
            panel.add(S.a);
        }
        else {
            ca.add(S.a);
        }
        S.a.setVisible(false);
        ai.q();
        if (!b || a.a.p()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.a = new aR(this.a), gridBagConstraints);
        if (a.a.p()) {
            panel.add(this.a);
        }
        else {
            ca.add(this.a);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        Image a3 = null;
        Image a4 = null;
        Image a5 = null;
        Image a6 = null;
        Image a7 = null;
        Image a8 = null;
        if (a.a.q()) {
            final String e = a.a.e();
            a3 = a.a(e + "users_tab_up.gif", true);
            a4 = a.a(e + "users_tab_down.gif", true);
            a5 = a.a(e + "rooms_tab_up.gif", true);
            a6 = a.a(e + "rooms_tab_down.gif", true);
            if (a.h()) {
                a7 = a.a(e + "buddies_tab_up.gif", true);
                a8 = a.a(e + "buddies_tab_down.gif", true);
            }
            if (a3 == null || a4 == null || a5 == null || a6 == null || ((a7 == null || a8 == null) && a.h())) {
                a.a.e(false);
            }
        }
        this.a = new cr(a, a.a.q());
        this.a = new R(a);
        this.a = new P(a);
        if (a.f()) {
            this.a.a(ao.e("Users"), this.a, a3, a4);
        }
        if (a.e()) {
            this.a.a(ao.e("Rooms"), this.a, a5, a6);
        }
        if (a.h()) {
            this.a = new N(a);
            if (a.a.p()) {
                this.a.setSize(175, 100);
            }
            this.a.a(ao.e("Buddies"), this.a, a7, a8);
        }
        layout.setConstraints(this.a, gridBagConstraints);
        if (b) {
            if (a.a.p()) {
                panel2.add(this.a);
            }
            else {
                ca.add(this.a);
            }
        }
        this.a.setFont(a.a.b());
        ((TextComponent)(this.a = new aR(this.a))).setBackground((f.e && f.h != 4) ? a.a.e.darker() : a.a.e);
        this.a.setForeground(a.a.d);
        final Panel panel3 = new Panel();
        panel3.setLayout(layout);
        final Panel panel4 = new Panel(new GridBagLayout());
        if (t.P || t.N || t.Q || t.R) {
            final String e2 = a.a.e();
            this.n = this.a(e2, "emoticons", "emoticonsIcon.gif");
            this.o = this.a(e2, "shortcuts", "shortcutsIcon.gif");
            this.p = this.a(e2, "bold", "boldIcon.gif");
            this.q = this.a(e2, "italic", "italicIcon.gif");
            this.r = this.a(e2, "colors", "colorsIcon.gif", 13, 50);
            (this.c = new aX(a, this.a, true)).setSize(1, 1);
            this.c.a(this.a.a.q);
            this.c.aB = 13;
            this.c.a(this.a.k, true);
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (t.P) {
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 2;
                gridBagConstraints2.weighty = 1.0;
                panel4.add(this.r, gridBagConstraints2);
                panel4.add(this.c, gridBagConstraints2);
            }
            gridBagConstraints2.weighty = 0.0;
            gridBagConstraints2.gridheight = 1;
            if (t.N) {
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 0;
                panel4.add(this.p, gridBagConstraints2);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 1;
                panel4.add(this.q, gridBagConstraints2);
            }
            if (t.Q) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 0;
                panel4.add(this.n, gridBagConstraints2);
            }
            if (t.R) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 1;
                panel4.add(this.o, gridBagConstraints2);
            }
        }
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.a, gridBagConstraints);
        panel3.add(this.a);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        panel3.add(panel4, gridBagConstraints);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(panel3, gridBagConstraints);
        if (a.a.p()) {
            panel.add(panel3);
        }
        else {
            ca.add(panel3);
        }
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (a.a.p() ? 0.0 : 1.0E-5);
        final String e3 = a.a.e();
        this.k = this.a(e3, "settings", ao.e("Settings"), ao.e("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        this.m = this.a(e3, "logout", ao.e("Logout"), am.a(ao.e("Click here to logout and end your %1 session."), new String[] { z.G }));
        this.l = this.a(e3, "send", null, ao.e("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final Panel panel5 = a.a.p() ? new Panel() : null;
        if (a.a.p()) {
            panel5.setLayout(layout);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        }
        gridBagConstraints.gridwidth = (a.d ? 0 : -1);
        layout.setConstraints(this.k, gridBagConstraints);
        if (a.a.p()) {
            panel5.add(this.k);
        }
        else {
            ca.add(this.k);
        }
        if (!a.d) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.m, gridBagConstraints);
            if (a.a.p()) {
                panel5.add(this.m);
            }
            else {
                ca.add(this.m);
            }
        }
        if (!a.a.p()) {
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        if (!a.a.p()) {
            final aQ aq = new aQ(this.l);
            layout.setConstraints(aq, gridBagConstraints);
            ca.add(aq);
        }
        else {
            layout.setConstraints(this.l, gridBagConstraints);
            panel5.add(this.l);
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
            gridBagConstraints.gridheight = -1;
            gridBagConstraints.weightx = 1.0E-5;
            layout.setConstraints(panel5, gridBagConstraints);
            if (b) {
                panel2.add(panel5);
            }
            else {
                panel.add(panel5);
            }
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            layout.setConstraints(panel, gridBagConstraints);
            ca.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            ca.add(panel2);
        }
        if (this.l instanceof aS) {
            ((aS)this.l).a(ao.e("Send"));
        }
        this.b = new cA(2, 2, 2, 2, 0);
        (this.a = new D()).b((a.H > 0) ? a.H : 7);
        this.b.setLayout(layout);
        layout.setConstraints(this.a, new GridBagConstraints());
        this.b.add(this.a);
        (this.b = new B()).b(a.a.u);
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final Panel panel6 = new Panel();
        panel6.setLayout(layout2);
        gridBagConstraints3.gridwidth = -1;
        layout2.setConstraints(this.b, gridBagConstraints3);
        panel6.add(this.b);
        gridBagConstraints3.gridwidth = 0;
        layout2.setConstraints(this.b, gridBagConstraints3);
        panel6.add(this.b);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(panel6, gridBagConstraints);
        this.add(panel6);
        this.b.setVisible(false);
        final M m = new M(this, a);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(ca, gridBagConstraints);
        this.add(ca);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component b2 = this.b(a.a.aE);
        b2.setBackground(a.a.k);
        b2.setForeground(a.a.i);
        layout.setConstraints(b2, gridBagConstraints);
        this.add(b2);
        super.a(a.a.t);
        super.a(a.c);
        final Dimension dimension = new Dimension();
        dimension.height = z.V;
        this.resize(dimension.width = z.W, dimension.height);
        if (a2 != null) {
            a.a(a2);
        }
        this.m = a.a.a();
    }
    
    static {
        S.a = null;
        S.a = null;
    }
}
