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

public class O extends h implements aV, aj
{
    public y a;
    public n b;
    private bp b;
    private x a;
    private ac a;
    private an a;
    public aW a;
    public TextArea b;
    private Canvas c;
    private Canvas d;
    private Canvas e;
    private p a;
    private ae a;
    private Image g;
    private int p;
    private int q;
    private int r;
    private int s;
    private Dimension a;
    private Insets c;
    private Image h;
    private c a;
    private aX a;
    private Hashtable b;
    private int t;
    private Canvas h;
    private Canvas i;
    private Canvas j;
    private Canvas k;
    private Canvas l;
    private aa a;
    public static aE a;
    public static V a;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        aj a = null;
        if (this.a.c.h() && s != null) {
            final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
            if (a2 != null && a3 != null) {
                a = o.a(a2, a3, null);
                ((o)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new al(70, 20);
            }
            else {
                a = new al(s3, 70, 20);
            }
            ((al)a).a(s4, null);
        }
        return (al)a;
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        return this.a(s, s2, s3, 25, 25);
    }
    
    private final Canvas a(final String s, final String s2, final String s3, final int n, final int n2) {
        try {
            aj a;
            if (!this.a.c.h() || s == null) {
                a = new al(n, n2);
                ((al)a).a(this.a.a(s3, false, 20));
            }
            else {
                final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
                final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
                final Image a4 = this.a.a(s + s2 + "_button_disabled.gif", true);
                if (a2 == null || a3 == null || a4 == null) {
                    a = new al(25, 25);
                    ((al)a).a(this.a.a(s3, false, 20));
                }
                else {
                    a = o.a(a2, a3, a4);
                }
            }
            return (al)a;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Canvas();
        }
    }
    
    public void a(final z z) {
        this.a.a(z);
    }
    
    public void a(final aN an) {
        this.a.a(an);
    }
    
    public void c() {
        this.a.a();
        if (doook.t.e) {
            super.setVisible(false);
            doook.t.a.remove(this);
            doook.t.a.setVisible(true);
            doook.t.a.validate();
        }
    }
    
    public y a() {
        return this.a;
    }
    
    public Frame a() {
        return this.a;
    }
    
    public Container b() {
        return this;
    }
    
    public String a(final Object o) {
        try {
            if (o == this.a) {
                return aG.a("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.b) {
                return aC.a(aG.a("Click here to visit %1."), new String[] { this.b.a().toString() });
            }
            if (o == this.a) {
                return aG.a("Chat messages are displayed here.  Single-click on a private message to reply.");
            }
            if (o == this.h) {
                return aG.a("Click here to insert emoticons into your message.");
            }
            if (o == this.i) {
                return aG.a("Click here to insert shortcut words into your message.");
            }
            if (o == this.j) {
                return aG.a("Select text then click here to make it Bold.");
            }
            if (o == this.k) {
                return aG.a("Select text then click here to make it Italic.");
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
                if (this.e instanceof al) {
                    ((al)this.e).e();
                }
                else {
                    ((o)this.e).c();
                }
                return true;
            }
            case 401: {
                if (event.key == 27) {
                    this.b.append("\n");
                    break;
                }
                if (event.modifiers == 2) {
                    if (event.key == 18) {
                        this.f();
                        return true;
                    }
                    if (event.key == 5) {
                        this.e();
                        return true;
                    }
                    if (event.key == 9) {
                        bc.a("i", this.b);
                        return true;
                    }
                    if (event.key == 2) {
                        bc.a("b", this.b);
                        return true;
                    }
                    if (event.key == 1) {
                        bc.a(this.b);
                        return true;
                    }
                    if (event.key == 10) {
                        this.b.append("\n");
                        this.b.setCaretPosition(this.b.getText().length());
                        return true;
                    }
                }
                if (event.key != 10 && event.key != F.e) {
                    break;
                }
                if (this.d instanceof al) {
                    ((al)this.d).e();
                }
                else {
                    ((o)this.d).c();
                }
                return true;
            }
            case 1001: {
                if (event.target == this.h) {
                    this.e();
                    return true;
                }
                if (event.target == this.i) {
                    this.f();
                    return true;
                }
                if (event.target == this.j) {
                    bc.a("b", this.b);
                    return true;
                }
                if (event.target == this.k) {
                    bc.a("i", this.b);
                    return true;
                }
                if (event.target == this.l) {
                    this.a.a();
                    return true;
                }
                if (event.target == this.d) {
                    String s = this.b.getText().trim();
                    this.b.setText("");
                    if (s.length() > 0) {
                        if (s.length() > 1024) {
                            s = s.substring(0, 1024);
                        }
                        if (System.currentTimeMillis() - this.a.c() < this.a.e()) {
                            try {
                                Thread.sleep(this.a.e() - (System.currentTimeMillis() - this.a.c()));
                            }
                            catch (InterruptedException ex) {}
                        }
                        this.b(s);
                        this.a.a(s, this.a.e, this.b.getFont().isBold(), this.b.getFont().isItalic());
                        this.a.d();
                    }
                    if (F.a) {
                        this.b.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.e) {
                    this.a.i();
                    return true;
                }
                if (event.target == this.c) {
                    this.a.f(0);
                    return true;
                }
                if (event.target instanceof MenuItem) {
                    return this.a.a(event);
                }
                if (event.arg instanceof aN) {
                    final aN an = (aN)event.arg;
                    final aq aq = (aq)this.a.d.b(an.d);
                    if (an.n && an.d != this.a.b()) {
                        this.a.a(an, aq);
                        break;
                    }
                    if (this.a.c(43) && aq != null && an.p && an.g == -1) {
                        this.a.a(an, aq);
                        return true;
                    }
                    if (an.g != -1) {
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
        if (this.g != null) {
            this.a = this.size();
            this.c = this.insets();
            this.p = this.a.width - this.c.right - this.c.left + 30;
            this.q = this.a.height - this.c.bottom - this.c.top + 30;
            this.r = this.g.getWidth(this);
            this.s = this.g.getHeight(this);
            this.h = this.createImage(this.p, this.q);
            final Graphics graphics2 = this.h.getGraphics();
            graphics2.drawImage(this.g, 0, 0, null);
            for (int i = 0; i <= this.p / this.r; ++i) {
                for (int j = 0; j <= this.q / this.s; ++j) {
                    if (i + j > 0) {
                        graphics2.copyArea(0, 0, this.r, this.s, i * this.r, j * this.s);
                    }
                }
            }
            graphics2.dispose();
            graphics.drawImage(this.h, 0, 0, this);
            this.b.a(this.h);
        }
        final int d = this.a.c.h() ? this.a.getSize().width : (this.getSize().width - 37);
        if (O.a.d != d) {
            O.a.d = d;
            O.a.p = true;
            if (O.a.f != "") {
                O.a.a();
            }
            else {
                O.a.c();
            }
        }
    }
    
    public void d() {
        this.a.g();
        this.b.setFont(this.a.c.b());
    }
    
    public boolean a(final ax ax) {
        return this.a.a(ax);
    }
    
    public boolean a(final aq aq) {
        return this.a.a(aq);
    }
    
    public void a(final String s) {
        try {
            this.b.a(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void b(final ax ax) {
        if (this.a != null) {
            this.a.a(ax);
        }
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void a(final boolean b) {
        this.a.b(0);
        this.a.a(b);
    }
    
    public void a(final at at, final boolean b, final boolean b2) {
        if (this.a.i()) {
            this.a.a(at, b, false);
        }
    }
    
    public void c(final ax ax) {
        this.a.b(ax);
    }
    
    public void a(final aq aq, final boolean b) {
        this.a.a(aq, b);
    }
    
    private final void b(final String s) {
        this.b.put(new Integer(this.t), new Object[] { new Long(System.currentTimeMillis()), s });
        if (this.a.X != 0 && this.a.Y != 0) {
            final Object[] array = this.b.get(new Integer(this.t - this.a.X + 1));
            if (array != null && System.currentTimeMillis() - (long)array[0] < this.a.Y * 1000) {
                this.a.q();
            }
        }
        if (this.a.W != 0) {
            int n = 1;
            for (int i = this.t - 1; i > this.t - this.a.W; --i) {
                final Object[] array2 = this.b.get(new Integer(i));
                if (array2 == null) {
                    break;
                }
                (long)array2[0];
                if (((String)array2[1]).equals(s)) {
                    ++n;
                }
            }
            if (n >= this.a.W) {
                this.a.q();
            }
        }
        ++this.t;
    }
    
    private void e() {
        if (be.Y) {
            final g g = new g(this.a.a.a(), this.a, this.b);
            g.c();
            g.setVisible(true);
        }
    }
    
    private void f() {
        if (be.Z) {
            final r r = new r(this.a.a.a(), this.a, this.b);
            r.c();
            r.setVisible(true);
        }
    }
    
    public O(final aW aw, final c a) {
        super(0);
        this.b = new Hashtable();
        this.t = 0;
        this.a = a;
        this.setBackground(aw.c.c);
        this.a = aw;
        aw.b = new MediaTracker(this);
        be.c = aw.a("lockIcon.gif", false, 20);
        be.j = aw.a("arrowIcon.gif", false, 20);
        aw.c.l = aw.a(aw.c.f() + "helpLogo.gif", true, 80);
        aw.c.m = aw.a(aw.c.f() + "chatLogo.gif", true, 10);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final n n = new n(aw.c.r);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        n.setBackground(aw.c.d);
        this.setLayout(layout);
        n.setLayout(layout);
        try {
            this.b = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.b = new TextArea(2, 10);
        }
        (this.a = new ae(aw, true)).setFont(aw.c.b());
        final boolean b = aw.k() || aw.l() || aw.i();
        O.a = new aE(ar.b);
        O.a.f = "";
        O.a.i = 13;
        O.a.j = true;
        O.a.d = 20;
        O.a.g = 20;
        gridBagConstraints.insets = new Insets(2, 5, 12, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        layout.setConstraints(O.a, gridBagConstraints);
        if (this.a.c.h()) {
            panel.add(O.a);
        }
        else {
            n.add(O.a);
        }
        O.a.setVisible(false);
        bi.c();
        if (!b || aw.c.h()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.a = new aX(this.a), gridBagConstraints);
        if (aw.c.h()) {
            panel.add(this.a);
        }
        else {
            n.add(this.a);
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
        if (aw.c.i()) {
            final String f = aw.c.f();
            a2 = aw.a(f + "users_tab_up.gif", true);
            a3 = aw.a(f + "users_tab_down.gif", true);
            a4 = aw.a(f + "rooms_tab_up.gif", true);
            a5 = aw.a(f + "rooms_tab_down.gif", true);
            if (aw.i()) {
                a6 = aw.a(f + "buddies_tab_up.gif", true);
                a7 = aw.a(f + "buddies_tab_down.gif", true);
            }
            if (a2 == null || a3 == null || a4 == null || a5 == null || ((a6 == null || a7 == null) && aw.i())) {
                aw.c.a(false);
            }
        }
        this.a = new p(aw, aw.c.i());
        this.a = new x(aw);
        this.a = new ac(aw);
        if (aw.l()) {
            this.a.a(aG.a("Users"), this.a, a2, a3);
        }
        if (aw.k()) {
            this.a.a(aG.a("Rooms"), this.a, a4, a5);
        }
        if (aw.i()) {
            this.a = new an(aw);
            if (aw.c.h()) {
                this.a.setSize(175, 100);
            }
            this.a.a(aG.a("Buddies"), this.a, a6, a7);
        }
        layout.setConstraints(this.a, gridBagConstraints);
        if (b) {
            if (aw.c.h()) {
                panel2.add(this.a);
            }
            else {
                n.add(this.a);
            }
        }
        this.b.setFont(aw.c.b());
        this.a = new aX(this.b);
        this.b.setBackground((!F.c || F.f == 4) ? aw.c.i : aw.c.i.darker());
        this.b.setForeground(aw.c.h);
        final Panel panel3 = new Panel();
        panel3.setLayout(layout);
        final Panel panel4 = new Panel(new GridBagLayout());
        if (be.W || be.U || be.Y || be.Z) {
            final String f2 = aw.c.f();
            this.h = this.a(f2, "emoticons", "emoticonsIcon.gif");
            this.i = this.a(f2, "shortcuts", "shortcutsIcon.gif");
            this.j = this.a(f2, "bold", "boldIcon.gif");
            this.k = this.a(f2, "italic", "italicIcon.gif");
            this.l = this.a(f2, "colors", "colorsIcon.gif", 13, 50);
            (this.a = new aa(aw, this.b, true)).setSize(1, 1);
            this.a.a(this.a.c.p);
            this.a.a(this.a.r, true);
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (be.W) {
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 2;
                gridBagConstraints2.weighty = 1.0;
                panel4.add(this.l, gridBagConstraints2);
                panel4.add(this.a, gridBagConstraints2);
            }
            gridBagConstraints2.weighty = 0.0;
            gridBagConstraints2.gridheight = 1;
            if (be.U) {
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 0;
                panel4.add(this.j, gridBagConstraints2);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 1;
                panel4.add(this.k, gridBagConstraints2);
            }
            if (be.Y) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 0;
                panel4.add(this.h, gridBagConstraints2);
            }
            if (be.Z) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 1;
                panel4.add(this.i, gridBagConstraints2);
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
        if (aw.c.h()) {
            panel.add(panel3);
        }
        else {
            n.add(panel3);
        }
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (aw.c.h() ? 0.0 : 1.0E-5);
        final String f3 = aw.c.f();
        this.c = this.a(f3, "settings", aG.a("Settings"), aG.a("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        this.d = this.a(f3, "send", null, aG.a("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        this.e = this.a(f3, "logout", aG.a("Logout"), aC.a(aG.a("Click here to logout and end your %1 session."), new String[] { doook.t.a }));
        final Panel panel5 = aw.c.h() ? new Panel() : null;
        if (aw.c.h()) {
            panel5.setLayout(layout);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        }
        gridBagConstraints.gridwidth = (aw.b ? 0 : -1);
        layout.setConstraints(this.c, gridBagConstraints);
        if (aw.c.h()) {
            panel5.add(this.c);
        }
        else {
            n.add(this.c);
        }
        if (!aw.b) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.e, gridBagConstraints);
            if (aw.c.h()) {
                panel5.add(this.e);
            }
            else {
                n.add(this.e);
            }
        }
        if (!aw.c.h()) {
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        if (!aw.c.h()) {
            final P p2 = new P(this.d);
            layout.setConstraints(p2, gridBagConstraints);
            n.add(p2);
        }
        else {
            layout.setConstraints(this.d, gridBagConstraints);
            panel5.add(this.d);
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
            n.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            n.add(panel2);
        }
        if (this.d instanceof al) {
            ((al)this.d).a(aG.a("Send"));
        }
        this.b = new n(2, 2, 2, 2, 5);
        this.a = new y();
        this.a.b = aw;
        this.a.c((aw.U <= 0) ? 7 : aw.U);
        this.b.setLayout(layout);
        layout.setConstraints(this.a, new GridBagConstraints());
        this.b.add(this.a);
        (this.b = new bp()).b(aw.c.m);
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
        final f f4 = new f(this, aw);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(n, gridBagConstraints);
        this.add(n);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component a8 = this.a(aw.c.r);
        a8.setBackground(aw.c.g);
        a8.setForeground(aw.c.e);
        layout.setConstraints(a8, gridBagConstraints);
        this.add(a8);
        this.a(aw.c.l);
        this.a(aw.d);
        final Dimension dimension = new Dimension();
        dimension.height = doook.t.b;
        this.resize(dimension.width = doook.t.c, dimension.height);
        if (a != null) {
            aw.a(a);
        }
        this.g = aw.c.a();
    }
    
    static {
        O.a = null;
        O.a = null;
    }
}
