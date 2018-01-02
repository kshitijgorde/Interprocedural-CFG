// 
// Decompiled by Procyon v0.5.30
// 

package doook;

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

public class bj extends z implements aO, e
{
    public N a;
    public aC b;
    private M c;
    private bh a;
    private be a;
    private bc a;
    public at g;
    public TextArea d;
    private Canvas o;
    private Canvas p;
    private Canvas q;
    private x a;
    private T a;
    private Image u;
    private int r;
    private int s;
    private int an;
    private int ao;
    private Dimension c;
    private Insets c;
    private Image v;
    private ba a;
    private aw a;
    private Hashtable h;
    private int y;
    private Canvas d;
    private Canvas e;
    private Canvas f;
    private Canvas g;
    private Canvas h;
    private bp c;
    public static aH a;
    public static aJ b;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        aO a = null;
        if (this.g.b.f() && s != null) {
            final Image a2 = this.g.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.g.a(s + s2 + "_button_dn.gif", true);
            if (a2 != null && a3 != null) {
                a = aB.a(a2, a3, null);
                ((aB)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new ax(70, 20);
            }
            else {
                a = new ax(s3, 70, 20);
            }
            ((ax)a).a(s4, null);
        }
        return (ax)a;
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        return this.a(s, s2, s3, 25, 25);
    }
    
    private final Canvas a(final String s, final String s2, final String s3, final int n, final int n2) {
        try {
            aO a;
            if (!this.g.b.f() || s == null) {
                a = new ax(n, n2);
                ((ax)a).a(this.g.a(s3, false, 20));
            }
            else {
                final Image a2 = this.g.a(s + s2 + "_button_up.gif", true);
                final Image a3 = this.g.a(s + s2 + "_button_dn.gif", true);
                final Image a4 = this.g.a(s + s2 + "_button_disabled.gif", true);
                if (a2 == null || a3 == null || a4 == null) {
                    a = new ax(25, 25);
                    ((ax)a).a(this.g.a(s3, false, 20));
                }
                else {
                    a = aB.a(a2, a3, a4);
                }
            }
            return (ax)a;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Canvas();
        }
    }
    
    public void a(final aj aj) {
        this.a.a(aj);
    }
    
    public void b(final bv bv) {
        this.a.b(bv);
    }
    
    public void f() {
        this.a.f();
        if (bi.ae) {
            super.setVisible(false);
            bi.b.remove(this);
            bi.a.setVisible(true);
            bi.a.validate();
        }
    }
    
    public N a() {
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
                return ar.b("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.c) {
                return H.a(ar.b("Click here to visit %1."), new String[] { this.c.a().toString() });
            }
            if (o == this.a) {
                return ar.b("Chat messages are displayed here.  Single-click on a private message to reply.");
            }
            if (o == this.d) {
                return ar.b("Click here to insert emoticons into your message.");
            }
            if (o == this.e) {
                return ar.b("Click here to insert shortcut words into your message.");
            }
            if (o == this.f) {
                return ar.b("Select text then click here to make it Bold.");
            }
            if (o == this.g) {
                return ar.b("Select text then click here to make it Italic.");
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
                if (this.q instanceof ax) {
                    ((ax)this.q).l();
                }
                else {
                    ((aB)this.q).f();
                }
                return true;
            }
            case 401: {
                if (event.key == 27) {
                    this.d.append("\n");
                    break;
                }
                if (event.modifiers == 2) {
                    if (event.key == 18) {
                        this.m();
                        return true;
                    }
                    if (event.key == 5) {
                        this.t();
                        return true;
                    }
                    if (event.key == 9) {
                        ai.a("i", this.d);
                        return true;
                    }
                    if (event.key == 2) {
                        ai.a("b", this.d);
                        return true;
                    }
                    if (event.key == 1) {
                        ai.a(this.d);
                        return true;
                    }
                    if (event.key == 10) {
                        this.d.append("\n");
                        this.d.setCaretPosition(this.d.getText().length());
                        return true;
                    }
                }
                if (event.key != 10 && event.key != bs.c) {
                    break;
                }
                if (this.p instanceof ax) {
                    ((ax)this.p).l();
                }
                else {
                    ((aB)this.p).f();
                }
                return true;
            }
            case 1001: {
                if (event.target == this.d) {
                    this.t();
                    return true;
                }
                if (event.target == this.e) {
                    this.m();
                    return true;
                }
                if (event.target == this.f) {
                    ai.a("b", this.d);
                    return true;
                }
                if (event.target == this.g) {
                    ai.a("i", this.d);
                    return true;
                }
                if (event.target == this.h) {
                    this.c.c();
                    return true;
                }
                if (event.target == this.p) {
                    String s = this.d.getText().trim();
                    this.d.setText("");
                    if (s.length() > 0) {
                        if (s.length() > 1024) {
                            s = s.substring(0, 1024);
                        }
                        if (System.currentTimeMillis() - this.g.b() < this.g.h()) {
                            try {
                                Thread.sleep(this.g.h() - (System.currentTimeMillis() - this.g.b()));
                            }
                            catch (InterruptedException ex) {}
                        }
                        this.d(s);
                        this.g.a(s, this.c.aw, this.d.getFont().isBold(), this.d.getFont().isItalic());
                        this.a.l();
                    }
                    if (bs.h) {
                        this.d.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.q) {
                    this.g.n();
                    return true;
                }
                if (event.target == this.o) {
                    this.g.c(0);
                    return true;
                }
                if (event.target instanceof MenuItem) {
                    return this.g.a(event);
                }
                if (event.arg instanceof bv) {
                    final bv bv = (bv)event.arg;
                    final a a = (a)this.g.e.b(bv.B);
                    if (bv.al && bv.B != this.g.e()) {
                        this.g.a(bv, a);
                        break;
                    }
                    if (this.g.a(43) && a != null && bv.z && bv.o == -1) {
                        this.g.a(bv, a);
                        return true;
                    }
                    if (bv.o != -1) {
                        return true;
                    }
                    break;
                }
                else {
                    if (event.arg instanceof URL) {
                        this.g.a((URL)event.arg, "_blank");
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
        if (this.u != null) {
            this.c = this.size();
            this.c = this.insets();
            this.r = this.c.width - this.c.right - this.c.left + 30;
            this.s = this.c.height - this.c.bottom - this.c.top + 30;
            this.an = this.u.getWidth(this);
            this.ao = this.u.getHeight(this);
            this.v = this.createImage(this.r, this.s);
            final Graphics graphics2 = this.v.getGraphics();
            graphics2.drawImage(this.u, 0, 0, null);
            for (int i = 0; i <= this.r / this.an; ++i) {
                for (int j = 0; j <= this.s / this.ao; ++j) {
                    if (i + j > 0) {
                        graphics2.copyArea(0, 0, this.an, this.ao, i * this.an, j * this.ao);
                    }
                }
            }
            graphics2.dispose();
            graphics.drawImage(this.v, 0, 0, this);
            this.c.a(this.v);
        }
        final int width = this.g.b.f() ? this.a.getSize().width : (this.getSize().width - 37);
        if (bj.a.width != width) {
            bj.a.width = width;
            bj.a.ac = true;
            if (bj.a.K != "") {
                bj.a.start();
            }
            else {
                bj.a.stop();
            }
        }
    }
    
    public void k() {
        this.a.k();
        this.d.setFont(this.g.b.b());
    }
    
    public boolean a(final bn bn) {
        return this.a.a(bn);
    }
    
    public boolean a(final a a) {
        return this.a.a(a);
    }
    
    public void a(final String s) {
        try {
            this.c.a(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void a(final bn bn) {
        if (this.a != null) {
            this.a.c(bn);
        }
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void a(final boolean b) {
        this.a.a(0);
        this.a.a(b);
    }
    
    public void a(final b b, final boolean b2, final boolean b3) {
        if (this.g.i()) {
            this.a.a(b, b2, false);
        }
    }
    
    public void b(final bn bn) {
        this.a.a(bn);
    }
    
    public void a(final a a, final boolean b) {
        this.a.a(a, b);
    }
    
    private final void d(final String s) {
        this.h.put(new Integer(this.y), new Object[] { new Long(System.currentTimeMillis()), s });
        if (this.g.X != 0 && this.g.Y != 0) {
            final Object[] array = this.h.get(new Integer(this.y - this.g.X + 1));
            if (array != null && System.currentTimeMillis() - (long)array[0] < this.g.Y * 1000) {
                this.g.o();
            }
        }
        if (this.g.W != 0) {
            int n = 1;
            for (int i = this.y - 1; i > this.y - this.g.W; --i) {
                final Object[] array2 = this.h.get(new Integer(i));
                if (array2 == null) {
                    break;
                }
                (long)array2[0];
                if (((String)array2[1]).equals(s)) {
                    ++n;
                }
            }
            if (n >= this.g.W) {
                this.g.o();
            }
        }
        ++this.y;
    }
    
    private void t() {
        if (as.S) {
            final k k = new k(this.g.a.a(), this.g, this.d);
            k.f();
            k.setVisible(true);
        }
    }
    
    private void m() {
        if (as.T) {
            final w w = new w(this.g.a.a(), this.g, this.d);
            w.f();
            w.setVisible(true);
        }
    }
    
    public bj(final at at, final ba a) {
        super(0);
        this.h = new Hashtable();
        this.y = 0;
        this.a = a;
        this.setBackground(at.b.a);
        this.g = at;
        at.b = new MediaTracker(this);
        as.c = at.a("lockIcon.gif", false, 20);
        as.j = at.a("arrowIcon.gif", false, 20);
        at.b.p = at.a(at.b.e() + "helpLogo.gif", true, 80);
        at.b.q = at.a(at.b.e() + "chatLogo.gif", true, 10);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final aC ac = new aC(at.b.ap);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        ac.setBackground(at.b.b);
        this.setLayout(layout);
        ac.setLayout(layout);
        try {
            this.d = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.d = new TextArea(2, 10);
        }
        (this.a = new T(at, true)).setFont(at.b.b());
        final boolean b = at.f() || at.g() || at.i();
        bj.a = new aH(av.a);
        bj.a.K = "";
        bj.a.al = 13;
        bj.a.aa = true;
        bj.a.width = 20;
        bj.a.height = 20;
        gridBagConstraints.insets = new Insets(2, 5, 12, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        layout.setConstraints(bj.a, gridBagConstraints);
        if (this.g.b.f()) {
            panel.add(bj.a);
        }
        else {
            ac.add(bj.a);
        }
        bj.a.setVisible(false);
        doook.h.a();
        if (!b || at.b.f()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.a = new aw(this.a), gridBagConstraints);
        if (at.b.f()) {
            panel.add(this.a);
        }
        else {
            ac.add(this.a);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        Image a2 = null;
        Image a3 = null;
        Image a4 = null;
        Image a5 = null;
        Image a6 = null;
        Image a7 = null;
        if (at.b.g()) {
            final String e = at.b.e();
            a2 = at.a(e + "users_tab_up.gif", true);
            a3 = at.a(e + "users_tab_down.gif", true);
            a4 = at.a(e + "rooms_tab_up.gif", true);
            a5 = at.a(e + "rooms_tab_down.gif", true);
            if (at.i()) {
                a6 = at.a(e + "buddies_tab_up.gif", true);
                a7 = at.a(e + "buddies_tab_down.gif", true);
            }
            if (a2 == null || a3 == null || a4 == null || a5 == null || ((a6 == null || a7 == null) && at.i())) {
                at.b.a(false);
            }
        }
        this.a = new x(at, at.b.g());
        this.a = new bh(at);
        this.a = new be(at);
        if (at.g()) {
            this.a.a(ar.b("Users"), this.a, a2, a3);
        }
        if (at.f()) {
            this.a.a(ar.b("Rooms"), this.a, a4, a5);
        }
        if (at.i()) {
            this.a = new bc(at);
            if (at.b.f()) {
                this.a.setSize(175, 100);
            }
            this.a.a(ar.b("Buddies"), this.a, a6, a7);
        }
        layout.setConstraints(this.a, gridBagConstraints);
        if (b) {
            if (at.b.f()) {
                panel2.add(this.a);
            }
            else {
                ac.add(this.a);
            }
        }
        this.d.setFont(at.b.b());
        this.a = new aw(this.d);
        this.d.setBackground((!bs.e || bs.t == 4) ? at.b.e : at.b.e.darker());
        this.d.setForeground(at.b.d);
        final Panel panel3 = new Panel();
        panel3.setLayout(layout);
        final Panel panel4 = new Panel(new GridBagLayout());
        if (as.Q || as.O || as.S || as.T) {
            final String e2 = at.b.e();
            this.d = this.a(e2, "emoticons", "emoticonsIcon.gif");
            this.e = this.a(e2, "shortcuts", "shortcutsIcon.gif");
            this.f = this.a(e2, "bold", "boldIcon.gif");
            this.g = this.a(e2, "italic", "italicIcon.gif");
            this.h = this.a(e2, "colors", "colorsIcon.gif", 13, 50);
            (this.c = new bp(at, this.d, true)).setSize(1, 1);
            this.c.b(this.g.b.r);
            this.c.a(this.g.s, true);
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (as.Q) {
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 2;
                gridBagConstraints2.weighty = 1.0;
                panel4.add(this.h, gridBagConstraints2);
                panel4.add(this.c, gridBagConstraints2);
            }
            gridBagConstraints2.weighty = 0.0;
            gridBagConstraints2.gridheight = 1;
            if (as.O) {
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 0;
                panel4.add(this.f, gridBagConstraints2);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 1;
                panel4.add(this.g, gridBagConstraints2);
            }
            if (as.S) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 0;
                panel4.add(this.d, gridBagConstraints2);
            }
            if (as.T) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 1;
                panel4.add(this.e, gridBagConstraints2);
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
        if (at.b.f()) {
            panel.add(panel3);
        }
        else {
            ac.add(panel3);
        }
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (at.b.f() ? 0.0 : 1.0E-5);
        final String e3 = at.b.e();
        this.o = this.a(e3, "settings", ar.b("Settings"), ar.b("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        this.p = this.a(e3, "send", null, ar.b("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        this.q = this.a(e3, "logout", ar.b("Logout"), H.a(ar.b("Click here to logout and end your %1 session."), new String[] { bi.Q }));
        final Panel panel5 = at.b.f() ? new Panel() : null;
        if (at.b.f()) {
            panel5.setLayout(layout);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        }
        gridBagConstraints.gridwidth = (at.h ? 0 : -1);
        layout.setConstraints(this.o, gridBagConstraints);
        if (at.b.f()) {
            panel5.add(this.o);
        }
        else {
            ac.add(this.o);
        }
        if (!at.h) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.q, gridBagConstraints);
            if (at.b.f()) {
                panel5.add(this.q);
            }
            else {
                ac.add(this.q);
            }
        }
        if (!at.b.f()) {
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        if (!at.b.f()) {
            final au au = new au(this.p);
            layout.setConstraints(au, gridBagConstraints);
            ac.add(au);
        }
        else {
            layout.setConstraints(this.p, gridBagConstraints);
            panel5.add(this.p);
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
            ac.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            ac.add(panel2);
        }
        if (this.p instanceof ax) {
            ((ax)this.p).a(ar.b("Send"));
        }
        this.b = new aC(2, 2, 2, 2, 5);
        this.a = new N();
        this.a.f = at;
        this.a.b((at.U <= 0) ? 7 : at.U);
        this.b.setLayout(layout);
        layout.setConstraints(this.a, new GridBagConstraints());
        this.b.add(this.a);
        (this.c = new M()).b(at.b.q);
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final Panel panel6 = new Panel();
        panel6.setLayout(layout2);
        gridBagConstraints3.gridwidth = -1;
        layout2.setConstraints(this.c, gridBagConstraints3);
        panel6.add(this.c);
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
        final bb bb = new bb(this, at);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(ac, gridBagConstraints);
        this.add(ac);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component a8 = this.a(at.b.ap);
        a8.setBackground(at.b.h);
        a8.setForeground(at.b.l);
        layout.setConstraints(a8, gridBagConstraints);
        this.add(a8);
        this.a(at.b.p);
        this.a(at.d);
        final Dimension dimension = new Dimension();
        dimension.height = bi.ar;
        this.resize(dimension.width = bi.as, dimension.height);
        if (a != null) {
            at.a(a);
        }
        this.u = at.b.a();
    }
    
    static {
        bj.a = null;
        bj.b = null;
    }
}
