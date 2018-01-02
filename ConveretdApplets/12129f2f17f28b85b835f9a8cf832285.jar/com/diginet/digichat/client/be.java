// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.u;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import com.diginet.digichat.common.j;
import java.awt.MenuItem;
import com.diginet.digichat.util.ch;
import java.awt.Event;
import com.diginet.digichat.util.ap;
import com.esial.util.d;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import com.diginet.digichat.common.bb;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.a8;
import java.util.Hashtable;
import com.diginet.digichat.awt.t;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;
import com.diginet.digichat.awt.bg;
import java.awt.Canvas;
import java.awt.TextArea;
import com.diginet.digichat.awt.as;
import com.diginet.digichat.awt.a7;
import com.diginet.digichat.awt.ar;
import com.diginet.digichat.util.s;
import com.diginet.digichat.awt.bf;

public class be extends bf implements s, aq
{
    public ar a;
    public a7 b;
    private as c;
    private bh d;
    private bn e;
    private bp f;
    public h g;
    public TextArea h;
    private Canvas i;
    private Canvas j;
    private Canvas k;
    private bg l;
    private a6 m;
    private Image n;
    private int o;
    private int p;
    private int q;
    private int r;
    private Dimension s;
    private Insets t;
    private Image u;
    private bs v;
    private t w;
    private Hashtable x;
    private int y;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        s a = null;
        if (this.g.ca.l() && s != null) {
            final Image a2 = this.g.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.g.a(s + s2 + "_button_dn.gif", true);
            if (a2 != null && a3 != null) {
                a = a8.a(a2, a3, null);
                ((a8)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new r(70, 20);
            }
            else {
                a = new r(s3, 70, 20);
            }
            ((r)a).a(s4, null);
        }
        return (Canvas)a;
    }
    
    public void a(final bb bb) {
        this.a.a(bb);
    }
    
    public void a(final ay ay) {
        this.m.a(ay);
    }
    
    public void a() {
        this.a.a();
        if (DigiChatAppletAbstract.embedded) {
            super.setVisible(false);
            DigiChatAppletAbstract.applet.remove(this);
            DigiChatAppletAbstract.login.setVisible(true);
            DigiChatAppletAbstract.login.validate();
        }
    }
    
    public ar e() {
        return this.a;
    }
    
    public Frame b() {
        return this.v;
    }
    
    public Container c() {
        return this;
    }
    
    public String a(final Object o) {
        try {
            if (o == this.w) {
                return com.esial.util.d.a("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.c) {
                return ap.a(com.esial.util.d.a("Click here to visit %1."), new String[] { this.c.c().toString() });
            }
            if (o == this.m) {
                return com.esial.util.d.a("Chat messages are displayed here.  Single-click on a private message to reply.");
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
                if (this.k instanceof r) {
                    ((r)this.k).e();
                }
                else {
                    ((a8)this.k).a();
                }
                return true;
            }
            case 401: {
                if (event.key == 27) {
                    this.h.appendText("\n");
                }
                else if (event.key == 10 || event.key == ch.a) {
                    if (this.j instanceof r) {
                        ((r)this.j).e();
                    }
                    else {
                        ((a8)this.j).a();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.j) {
                    String s = this.h.getText().trim();
                    this.h.setText("");
                    if (s.length() > 0) {
                        if (s.length() > 1024) {
                            s = s.substring(0, 1024);
                        }
                        if (System.currentTimeMillis() - this.g.j() < this.g.k()) {
                            try {
                                Thread.sleep(this.g.k() - (System.currentTimeMillis() - this.g.j()));
                            }
                            catch (InterruptedException ex) {}
                        }
                        this.b(s);
                        this.g.c(s);
                        this.m.e();
                    }
                    if (ch.d) {
                        this.h.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.k) {
                    this.g.o();
                    return true;
                }
                if (event.target == this.i) {
                    this.g.c(0);
                    return true;
                }
                if (event.target instanceof MenuItem) {
                    return this.g.a(event);
                }
                if (event.arg instanceof ay) {
                    final ay ay = (ay)event.arg;
                    final aw aw = (aw)this.g.aa.d(ay.f);
                    if (ay.q && ay.f != this.g.q()) {
                        this.g.a(ay, aw);
                    }
                    else {
                        if (this.g.i(43) && aw != null && ay.p && ay.g == -1) {
                            this.g.a(ay, (j)aw);
                            return true;
                        }
                        if (ay.g != -1) {
                            this.g.d(ay.g);
                            return true;
                        }
                    }
                    break;
                }
                if (event.arg instanceof URL) {
                    this.g.a((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.n != null) {
            this.s = this.size();
            this.t = this.insets();
            this.o = this.s.width - this.t.right - this.t.left + 30;
            this.p = this.s.height - this.t.bottom - this.t.top + 30;
            this.q = this.n.getWidth(this);
            this.r = this.n.getHeight(this);
            this.u = this.createImage(this.o, this.p);
            final Graphics graphics2 = this.u.getGraphics();
            graphics2.drawImage(this.n, 0, 0, null);
            for (int i = 0; i <= this.o / this.q; ++i) {
                for (int j = 0; j <= this.p / this.r; ++j) {
                    if (i + j > 0) {
                        graphics2.copyArea(0, 0, this.q, this.r, i * this.q, j * this.r);
                    }
                }
            }
            graphics2.dispose();
            graphics.drawImage(this.u, 0, 0, this);
            this.c.a(this.u);
        }
    }
    
    public void d() {
        this.m.d();
        this.h.setFont(this.g.ca.b());
    }
    
    public boolean a(final bc bc) {
        return this.e.a(bc);
    }
    
    public boolean a(final aw aw) {
        return this.d.a(aw);
    }
    
    public void a(final String s) {
        try {
            this.c.a(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void b(final bc bc) {
        if (this.v != null) {
            this.v.a(bc);
        }
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void a(final boolean b) {
        this.l.a(0);
        this.d.a(b);
    }
    
    public void a(final av av, final boolean b, final boolean b2) {
        if (this.g.e()) {
            this.f.a(av, b, false);
        }
    }
    
    public void c(final bc bc) {
        this.e.b(bc);
    }
    
    public void a(final aw aw, final boolean b) {
        this.d.a(aw, b);
    }
    
    private final void b(final String s) {
        this.x.put(new Integer(this.y), new Object[] { new Long(System.currentTimeMillis()), s });
        if (this.g.b2 != 0 && this.g.b3 != 0) {
            final Object[] array = this.x.get(new Integer(this.y - this.g.b2 + 1));
            if (array != null && System.currentTimeMillis() - (long)array[0] < this.g.b3 * 1000) {
                this.g.p();
            }
        }
        if (this.g.b1 != 0) {
            int n = 1;
            for (int i = this.y - 1; i > this.y - this.g.b1; --i) {
                final Object[] array2 = this.x.get(new Integer(i));
                if (array2 == null) {
                    break;
                }
                (long)array2[0];
                if (((String)array2[1]).equals(s)) {
                    ++n;
                }
            }
            if (n >= this.g.b1) {
                this.g.p();
            }
        }
        ++this.y;
    }
    
    public be(final h g, final bs v) {
        super(0);
        this.x = new Hashtable();
        this.y = 0;
        this.v = v;
        this.setBackground(g.ca.c);
        this.g = g;
        g.ay = new MediaTracker(this);
        com.diginet.digichat.client.i.c = g.a("lockIcon.gif", false, 20);
        g.ca.x = g.a(g.ca.f() + "helpLogo.gif", true, 80);
        g.ca.y = g.a(g.ca.f() + "chatLogo.gif", true, 10);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final a7 a7 = new a7(g.ca.v);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(gridBagLayout);
        panel2.setLayout(gridBagLayout);
        a7.setBackground(g.ca.d);
        this.setLayout(gridBagLayout);
        a7.setLayout(gridBagLayout);
        try {
            this.h = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.h = new TextArea(2, 10);
        }
        (this.m = new a6(g, true)).setFont(g.ca.b());
        final boolean b = g.l() || g.m() || g.e();
        if (!b || g.ca.l()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(this.w = new t(this.m), gridBagConstraints);
        if (g.ca.l()) {
            panel.add(this.w);
        }
        else {
            a7.add(this.w);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        Image a8 = null;
        Image a9 = null;
        Image a10 = null;
        Image a11 = null;
        Image a12 = null;
        Image a13 = null;
        if (g.ca.m()) {
            final String f = g.ca.f();
            a8 = g.a(f + "users_tab_up.gif", true);
            a9 = g.a(f + "users_tab_down.gif", true);
            a10 = g.a(f + "rooms_tab_up.gif", true);
            a11 = g.a(f + "rooms_tab_down.gif", true);
            if (g.e()) {
                a12 = g.a(f + "buddies_tab_up.gif", true);
                a13 = g.a(f + "buddies_tab_down.gif", true);
            }
            if (a8 == null || a9 == null || a10 == null || a11 == null || ((a12 == null || a13 == null) && g.e())) {
                g.ca.a(false);
            }
        }
        this.l = new bg(g, g.ca.m());
        this.d = new bh(g);
        this.e = new bn(g);
        if (g.m()) {
            this.l.a(com.esial.util.d.a("Users"), this.d, a8, a9);
        }
        if (g.l()) {
            this.l.a(com.esial.util.d.a("Rooms"), this.e, a10, a11);
        }
        if (g.e()) {
            this.f = new bp(g);
            if (g.ca.l()) {
                this.f.setSize(175, 100);
            }
            this.l.a(com.esial.util.d.a("Buddies"), this.f, a12, a13);
        }
        gridBagLayout.setConstraints(this.l, gridBagConstraints);
        if (b) {
            if (g.ca.l()) {
                panel2.add(this.l);
            }
            else {
                a7.add(this.l);
            }
        }
        this.h.setFont(g.ca.b());
        this.w = new t(this.h);
        this.h.setBackground((ch.f && ch.b != 4) ? g.ca.h.darker() : g.ca.h);
        this.h.setForeground(g.ca.g);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        if (g.ca.l()) {
            panel.add(this.w);
        }
        else {
            a7.add(this.w);
        }
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (g.ca.l() ? 0.0 : 1.0E-5);
        final String f2 = g.ca.f();
        this.i = this.a(f2, "settings", com.esial.util.d.a("Settings"), com.esial.util.d.a("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        this.j = this.a(f2, "send", null, com.esial.util.d.a("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        this.k = this.a(f2, "logout", com.esial.util.d.a("Logout"), ap.a(com.esial.util.d.a("Click here to logout and end your %1 session."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }));
        final Panel panel3 = g.ca.l() ? new Panel() : null;
        if (g.ca.l()) {
            panel3.setLayout(gridBagLayout);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        }
        gridBagConstraints.gridwidth = (g.d ? 0 : -1);
        gridBagLayout.setConstraints(this.i, gridBagConstraints);
        if (g.ca.l()) {
            panel3.add(this.i);
        }
        else {
            a7.add(this.i);
        }
        if (!g.d) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.k, gridBagConstraints);
            if (g.ca.l()) {
                panel3.add(this.k);
            }
            else {
                a7.add(this.k);
            }
        }
        if (!g.ca.l()) {
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        if (!g.ca.l()) {
            final u u = new u(this.j);
            gridBagLayout.setConstraints(u, gridBagConstraints);
            a7.add(u);
        }
        else {
            gridBagLayout.setConstraints(this.j, gridBagConstraints);
            panel3.add(this.j);
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
            gridBagConstraints.gridheight = -1;
            gridBagConstraints.weightx = 1.0E-5;
            gridBagLayout.setConstraints(panel3, gridBagConstraints);
            if (b) {
                panel2.add(panel3);
            }
            else {
                panel.add(panel3);
            }
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(panel, gridBagConstraints);
            a7.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(panel2, gridBagConstraints);
            a7.add(panel2);
        }
        if (this.j instanceof r) {
            ((r)this.j).a(com.esial.util.d.a("Send"));
        }
        this.b = new a7(2, 2, 2, 2, 0);
        (this.a = new ar()).b((g.bz > 0) ? g.bz : 7);
        this.b.setLayout(gridBagLayout);
        gridBagLayout.setConstraints(this.a, new GridBagConstraints());
        this.b.add(this.a);
        (this.c = new as()).b(g.ca.y);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final Panel panel4 = new Panel();
        panel4.setLayout(layout);
        gridBagConstraints2.gridwidth = -1;
        layout.setConstraints(this.c, gridBagConstraints2);
        panel4.add(this.c);
        gridBagConstraints2.gridwidth = 0;
        layout.setConstraints(this.b, gridBagConstraints2);
        panel4.add(this.b);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(panel4, gridBagConstraints);
        this.add(panel4);
        this.b.setVisible(false);
        final br br = new br(this, g);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(a7, gridBagConstraints);
        this.add(a7);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component a14 = this.a(g.ca.v);
        a14.setBackground(g.ca.f);
        a14.setForeground(g.ca.e);
        gridBagLayout.setConstraints(a14, gridBagConstraints);
        this.add(a14);
        this.a(g.ca.x);
        this.a(g.bk);
        final Dimension dimension = new Dimension();
        dimension.height = DigiChatAppletAbstract.initialWindowHeight;
        this.resize(dimension.width = DigiChatAppletAbstract.initialWindowWidth, dimension.height);
        if (v != null) {
            g.a(v);
        }
        this.n = g.ca.g();
    }
}
