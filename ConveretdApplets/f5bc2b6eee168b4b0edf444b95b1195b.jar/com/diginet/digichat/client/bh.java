// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;
import com.diginet.digichat.awt.b0;
import com.diginet.digichat.awt.s;
import com.diginet.digichat.util.bv;
import java.io.File;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import com.diginet.digichat.common.bg;
import java.net.MalformedURLException;
import java.awt.Graphics;
import java.awt.Point;
import java.net.URL;
import com.diginet.digichat.common.User;
import java.awt.MenuItem;
import com.diginet.digichat.util.q;
import com.diginet.digichat.util.c3;
import java.awt.Event;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import java.awt.Frame;
import com.diginet.digichat.common.be;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.awt.ba;
import java.awt.image.ImageObserver;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.awt.r;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.cr;
import com.diginet.digichat.awt.b2;
import com.diginet.digichat.awt.b3;
import java.awt.Container;
import java.awt.Panel;
import java.awt.Component;
import java.awt.TextArea;
import com.diginet.digichat.awt.aa;
import com.diginet.digichat.awt.a9;
import com.diginet.digichat.awt.aw;
import com.diginet.digichat.util.p;
import com.diginet.digichat.awt.bi;

public class bh extends bi implements p, av
{
    public aw a;
    public a9 b;
    private aa c;
    private bk d;
    private br e;
    private ct f;
    private bt g;
    public g h;
    public TextArea i;
    public Component j;
    public Component k;
    private Panel l;
    private Panel m;
    private bx n;
    private Component o;
    private Component p;
    private Component q;
    private Container r;
    private Panel s;
    private b3 t;
    private Panel u;
    private b2 v;
    private cr w;
    protected bj x;
    private a8 y;
    private Image z;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private Dimension ae;
    private Insets af;
    private Image ag;
    private b4 ah;
    private r ai;
    private GridBagLayout aj;
    private GridBagConstraints ak;
    
    public final void a(final long n) {
        this.t.a(n);
    }
    
    public final void a(final String s) {
        this.t.a(s);
    }
    
    public final void b(final User2 user2) {
        if (this.n != null) {
            this.n.a(user2);
        }
    }
    
    public final void d(final boolean ai) {
        super.d(this.h.ai = ai);
    }
    
    public final void a(final int n, final int n2) {
        if (this.v != null) {
            this.v.setText(n + "/" + n2);
        }
    }
    
    public final void c(final boolean b) {
        if (b) {
            this.ak.insets = new Insets(2, 3, 2, 3);
            this.ak.fill = 1;
            this.ak.gridwidth = 1;
            this.aj.setConstraints(this.u, this.ak);
            this.r.add(this.u, 1);
        }
        else {
            this.r.remove(this.u);
        }
    }
    
    private final void c(final String s) {
        final Image a = this.h.a(s + "top_right_border.gif", true);
        if (a != null) {
            this.prepareImage(a, this);
            (this.h.df.borderImgs = new Image[8])[0] = this.h.a(s + "top_border.gif", true, -1, false);
            this.h.df.borderImgs[1] = this.h.a(s + "left_border.gif", true, -1, false);
            this.h.df.borderImgs[2] = this.h.a(s + "bottom_border.gif", true, -1, false);
            this.h.df.borderImgs[3] = this.h.a(s + "right_border.gif", true, -1, false);
            this.h.df.borderImgs[4] = this.h.a(s + "top_left_border.gif", true, -1, false);
            this.h.df.borderImgs[5] = this.h.a(s + "bottom_left_border.gif", true, -1, false);
            this.h.df.borderImgs[6] = this.h.a(s + "bottom_right_border.gif", true, -1, false);
            this.h.df.borderImgs[7] = a;
        }
    }
    
    private final Component a(final String s, final String s2, final String s3, final String s4) {
        p a = null;
        if (this.h.df.getImageButtons() && s != null) {
            final Image a2 = this.h.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.h.a(s + s2 + "_button_dn.gif", true);
            if (a2 != null && a3 != null) {
                this.prepareImage(a2, this);
                this.prepareImage(a3, this);
                a = ba.a(a2, a3, null);
                ((ba)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new o(70, 20);
            }
            else {
                a = new o(s3, 70, 20);
            }
            ((o)a).a(s4, null);
        }
        return (Component)a;
    }
    
    public final void a(final be be) {
        if (this.a != null) {
            this.a.a(be);
        }
    }
    
    public final void a(final ChatMessage chatMessage) {
        if (this.y != null) {
            this.y.a(chatMessage);
        }
    }
    
    public final void a() {
        if (this.a != null) {
            this.a.c();
        }
        if (DigiChatAppletAbstract.embedded) {
            super.setVisible(false);
            DigiChatAppletAbstract.applet.remove(this);
            DigiChatAppletAbstract.login.setVisible(true);
            DigiChatAppletAbstract.login.validate();
        }
    }
    
    public final aw f() {
        return this.a;
    }
    
    public final Frame b() {
        return this.ah;
    }
    
    public final Container c() {
        return this;
    }
    
    public final void b(final boolean editable) {
        if (!this.h.u(15) && this.i != null) {
            if (!editable) {
                this.i.setText("");
            }
            this.i.setEditable(editable);
        }
    }
    
    public final String a(final Object o) {
        try {
            if (o == this.ai) {
                return LanguageSupport.translate("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.c) {
                return StringSubst.Substitute(LanguageSupport.translate("Click here to visit %1."), new String[] { this.c.b().toString() });
            }
            if (o == this.y) {
                return LanguageSupport.translate("Chat messages are displayed here.  Single-click on a private message to reply.");
            }
            if (o == this.s) {
                final String text = this.t.getText();
                if (text.equalsIgnoreCase("Open")) {
                    return StringSubst.Substitute(LanguageSupport.translate("This room is %1Open%1 and messages can be posted normally."), new String[] { "\"" });
                }
                if (text.equalsIgnoreCase("Closed")) {
                    return StringSubst.Substitute(LanguageSupport.translate("This room is %1Closed%1 and messages can not be posted anymore."), new String[] { "\"" });
                }
                return StringSubst.Substitute(LanguageSupport.translate("This room will be %1Closed%1 and messages can not be posted anymore when time runs out."), new String[] { "\"" });
            }
            else {
                if (o == this.u) {
                    return LanguageSupport.translate("The ratio of answered questions to total questions for the guest speakers in this moderated room.");
                }
                return null;
            }
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (this.q instanceof o) {
                    ((o)this.q).e();
                }
                else {
                    ((ba)this.q).b();
                }
                return true;
            }
            case 401: {
                if (event.key == 27) {
                    this.i.appendText("\n");
                }
                else if (event.key == 10 || event.key == c3.a) {
                    if (this.h.bq) {
                        this.i.appendText("\n");
                    }
                    else if (this.p instanceof o) {
                        ((o)this.p).e();
                    }
                    else {
                        ((ba)this.p).b();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.k) {
                    this.h.a(com.diginet.digichat.util.q.a(this.k), -1, this.h.b);
                }
                if (event.target == this.p) {
                    String s = "";
                    if (this.j != null) {
                        s = com.diginet.digichat.util.q.a(this.j).trim();
                        if (s.length() > 0) {
                            com.diginet.digichat.util.q.a(this.j, 0);
                        }
                    }
                    if (s.length() == 0) {
                        if (!this.h.e && this.h.c6) {
                            this.h.u().a(this.i);
                        }
                        s = this.i.getText().trim();
                        this.i.setText("");
                    }
                    if (s.length() > 0) {
                        if (s.length() > 1024) {
                            s = s.substring(0, 1024);
                        }
                        if (System.currentTimeMillis() - this.h.o() < this.h.p()) {
                            try {
                                Thread.sleep(this.h.p() - (System.currentTimeMillis() - this.h.o()));
                            }
                            catch (InterruptedException ex) {}
                        }
                        this.h.f(s);
                        this.h.e(s);
                        this.y.e();
                    }
                    if (c3.d) {
                        this.i.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.q) {
                    this.h.h();
                    return true;
                }
                if (event.target == this.o) {
                    this.h.c(0);
                    return true;
                }
                if (event.target instanceof MenuItem) {
                    return this.h.a(event);
                }
                if (event.arg instanceof ChatMessage) {
                    final ChatMessage chatMessage = (ChatMessage)event.arg;
                    final User2 user2 = (User2)this.h.aj.d(chatMessage.f);
                    if (chatMessage.q && chatMessage.f != this.h.x()) {
                        this.h.a(chatMessage, user2);
                    }
                    else {
                        if (this.h.u(43) && user2 != null && chatMessage.p && chatMessage.g == -1) {
                            this.h.a(chatMessage, (User)user2);
                            return true;
                        }
                        if (chatMessage.g != -1) {
                            this.h.d(chatMessage.g);
                            return true;
                        }
                    }
                    break;
                }
                if (event.arg instanceof URL) {
                    this.h.a((URL)event.arg, "_blank");
                    return true;
                }
            }
            case 6301: {
                if (this.h.cb != null) {
                    if (this.w != null) {
                        this.w.dispose();
                        this.w = null;
                    }
                    if (event.metaDown() && this.x.getComponentAt(0, 0) instanceof bk) {
                        (this.w = new cr(this.b(), this.getFont(), this.h, this.d.b())).a("Initiate " + DigiChatAppletAbstract.OEM_DigiChat + " Session");
                        final Point location = this.b().location();
                        this.w.a(location.x + event.x, location.y + event.y + (this.b().size().height - this.size().height));
                    }
                }
                break;
            }
            case 501:
            case 702: {
                if (this.w != null) {
                    this.w.dispose();
                    this.w = null;
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.z != null) {
            this.ae = this.size();
            this.af = this.insets();
            this.aa = this.ae.width - this.af.right - this.af.left + 30;
            this.ab = this.ae.height - this.af.bottom - this.af.top + 30;
            this.ac = this.z.getWidth(this);
            this.ad = this.z.getHeight(this);
            this.ag = this.createImage(this.aa, this.ab);
            final Graphics graphics2 = this.ag.getGraphics();
            graphics2.drawImage(this.z, 0, 0, null);
            for (int i = 0; i <= this.aa / this.ac; ++i) {
                for (int j = 0; j <= this.ab / this.ad; ++j) {
                    if (i + j > 0) {
                        graphics2.copyArea(0, 0, this.ac, this.ad, i * this.ac, j * this.ad);
                    }
                }
            }
            graphics2.dispose();
            graphics.drawImage(this.ag, 0, 0, this);
            this.c.a(this.ag);
        }
    }
    
    public final void d() {
        if (this.y != null) {
            this.y.d();
        }
        if (this.i != null) {
            this.i.setFont(this.h.df.getFont());
        }
    }
    
    public final boolean a(final bf bf) {
        if (this.e != null) {
            return this.e.a(bf);
        }
        return this.f != null && this.f.b(bf);
    }
    
    public final boolean a(final User2 user2) {
        return this.d.a(user2);
    }
    
    public final void b(final String s) {
        try {
            this.c.a(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public final void b(final bf bf) {
        if (this.ah != null) {
            this.ah.a(bf);
        }
        if (this.h.c3 && bf.a()) {
            this.d.b(false);
        }
        else {
            this.d.b(true);
        }
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public final void a(final boolean b) {
        this.x.a(0);
        this.d.a(b);
    }
    
    public final boolean a(final az az, final boolean b, final boolean b2) {
        boolean a = false;
        if (this.h.e()) {
            a = this.g.a(az, b, false);
        }
        return a;
    }
    
    public final void a(final bg bg) {
        if (this.f != null) {
            this.f.a(bg);
        }
    }
    
    public final void e() {
        if (this.b() != null) {
            this.b().setCursor(3);
        }
        if (this.f == null) {
            this.f = new ct(this.h, this.b());
        }
        if (this.f.isVisible()) {
            this.f.toFront();
        }
        else {
            this.f.show();
        }
        if (this.b() != null) {
            this.b().setCursor(0);
        }
    }
    
    public final void c(final bf bf) {
        if (this.e != null) {
            this.e.b(bf);
        }
        else if (this.f != null) {
            this.f.a(bf);
        }
    }
    
    public final void a(final User2 user2, final boolean b) {
        this.d.a(user2, b);
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.doLayout();
            this.resize(n4, n5);
            this.repaint(n2, n3, n4, n5);
            try {
                this.getParent().doLayout();
                this.getParent().validate();
            }
            catch (Throwable t) {
                return true;
            }
            return false;
        }
        this.repaint(n2, n3, n4, n5);
        return true;
    }
    
    public bh(final g h, final b4 ah, final boolean b) {
        super(0);
        this.ah = ah;
        this.h = h;
        final boolean b2 = h.k && h.j && c3.c > 66048;
        h.bd = new MediaTracker(this);
        com.diginet.digichat.client.h.c = h.a("lockIcon.gif", false, 20);
        com.diginet.digichat.client.h.d = h.a("dbLockIcon.gif", false, 20);
        h.df.fileTransferLogo = h.a(h.df.getFullDirectory() + "file_transfer_logo.gif", true, -1, false);
        if (b) {
            h.df.IMLogo = h.a(h.df.getFullDirectory() + "logo_IM.gif", true, -1, false);
            h.df.avEnterpriseIM = h.a(h.df.getFullDirectory() + "dcav_enterprise_IM.gif", true, -1, false);
            h.df.helpLogoIM = h.a(h.df.getFullDirectory() + "help_IM.gif", true, -1, false);
            h.df.closeHelp = h.a(h.df.getFullDirectory() + "close_help_x.gif", true, -1, false);
            h.df.available = h.a(h.df.getFullDirectory() + "available.gif", true, -1, false);
            h.df.unavailable = h.a(h.df.getFullDirectory() + "unavailable.gif", true, -1, false);
        }
        else {
            h.df.chatLogo = h.a(h.df.getFullDirectory() + "chatLogo.gif", true, 10);
            h.df.helpLogo = h.a(h.df.getFullDirectory() + "helpLogo.gif", true, 80);
        }
        this.c(h.df.getFullDirectory());
        if (h.df.borderImgs != null) {
            this.a(h.df.borderImgs);
        }
        this.setBackground(h.df.outerBackground);
        this.aj = new GridBagLayout();
        this.ak = new GridBagConstraints();
        this.ak.insets = new Insets(2, 3, 2, 3);
        final a9 a9 = new a9(h.df.roundedCorners);
        a9.setBackground(h.df.innerBackground);
        a9.setLayout(this.aj);
        this.setLayout(this.aj);
        final boolean b3 = h.q() || h.r() || h.e();
        Container container = null;
        Container container2 = null;
        if (!b) {
            container = new Panel();
            container2 = new Panel();
            container.setLayout(this.aj);
            container2.setLayout(this.aj);
            try {
                this.i = new TextArea("", 2, 10, 1);
            }
            catch (Throwable t) {
                this.i = new TextArea(2, 10);
            }
            (this.y = new a8(h, true)).setFont(h.df.getFont());
            if (!b3 || h.df.getImageButtons()) {
                this.ak.gridwidth = 0;
            }
            else {
                this.ak.gridwidth = 1;
            }
            this.ak.fill = 1;
            this.ak.weightx = 1.0;
            this.ak.weighty = 1.0;
            this.ai = new r(this.y);
            this.aj.setConstraints(this.ai, this.ak);
            if (h.df.getImageButtons()) {
                container.add(this.ai);
            }
            else {
                a9.add(this.ai);
            }
        }
        this.ak.gridwidth = 0;
        if (b) {
            this.ak.weightx = 1.0;
        }
        else {
            this.ak.weightx = 0.0;
        }
        this.ak.weighty = 1.0;
        this.ak.fill = 1;
        Image a10 = null;
        Image a11 = null;
        Image image = null;
        Image image2 = null;
        Image a12 = null;
        Image a13 = null;
        if (h.df.getImageTabs()) {
            final String fullDirectory = h.df.getFullDirectory();
            a10 = h.a(fullDirectory + "users_tab_up.gif", true);
            a11 = h.a(fullDirectory + "users_tab_down.gif", true);
            this.prepareImage(a10, this);
            this.prepareImage(a11, this);
            if (b) {
                image = h.a(fullDirectory + "channels_tab_up.gif", true);
                image2 = h.a(fullDirectory + "channels_tab_down.gif", true);
                this.prepareImage(image, this);
                this.prepareImage(image2, this);
            }
            else {
                image = h.a(fullDirectory + "rooms_tab_up.gif", true);
                image2 = h.a(fullDirectory + "rooms_tab_down.gif", true);
                this.prepareImage(image, this);
                this.prepareImage(image2, this);
            }
            if (h.e()) {
                a12 = h.a(fullDirectory + "buddies_tab_up.gif", true);
                a13 = h.a(fullDirectory + "buddies_tab_down.gif", true);
                this.prepareImage(a12, this);
                this.prepareImage(a13, this);
            }
            if (a10 == null || a11 == null || image == null || image2 == null || ((a12 == null || a13 == null) && h.e())) {
                h.df.setImageTabs(false);
            }
        }
        this.x = new bj(h, h.df.getImageTabs());
        this.d = new bk(h, b);
        if (!h.c2) {
            this.e = new br(h, b, this.getFont());
        }
        if (h.e()) {
            this.g = new bt(h, b);
        }
        if (!b) {
            if (h.r()) {
                this.x.a(LanguageSupport.translate("Users"), this.d, a10, a11);
            }
            if (h.q() && this.e != null) {
                this.x.a(LanguageSupport.translate("Rooms"), this.e, image, image2);
            }
            if (this.g != null) {
                this.x.a(LanguageSupport.translate("Buddies"), this.g, a12, a13);
            }
        }
        else {
            if (this.g != null) {
                this.x.a(LanguageSupport.translate("Buddies"), this.g, a12, a13);
            }
            if (h.q() && this.e != null) {
                this.x.a(LanguageSupport.translate("Rooms"), this.e, image, image2);
            }
            if (h.r()) {
                this.x.a(LanguageSupport.translate("Users"), this.d, a10, a11);
            }
        }
        this.x.layout();
        this.aj.setConstraints(this.x, this.ak);
        if (b3) {
            if (h.df.getImageButtons() && container2 != null) {
                container2.add(this.x);
            }
            else {
                a9.add(this.x);
            }
        }
        if (!h.e && !b && h.c5) {
            com.diginet.digichat.util.q.b(this.j = com.diginet.digichat.util.q.b(), "");
            this.j.setBackground((c3.b != 2) ? h.df.tabsBackground : Color.white);
            this.j.setForeground(h.df.inputText);
            (this.l = new Panel()).setLayout(this.aj);
            final Insets insets = this.ak.insets;
            this.ak.insets = new Insets(0, 0, 0, 0);
            this.ak.gridheight = 0;
            this.ak.gridwidth = 0;
            this.ak.weightx = 1.0;
            this.ak.weighty = 0.0;
            this.aj.setConstraints(this.j, this.ak);
            this.l.add(this.j);
            this.ak.insets = insets;
            this.ak.gridheight = -1;
            this.ak.gridwidth = 0;
            this.ak.weightx = 1.0;
            this.ak.weighty = 0.0;
            this.aj.setConstraints(this.l, this.ak);
            if (h.df.getImageButtons()) {
                container.add(this.l);
            }
            else {
                a9.add(this.l);
            }
        }
        if (!b && h.u(76) && h.u(60) && h.j && new File(new File(System.getProperty("user.home") + File.separator + "." + DigiChatAppletAbstract.OEM_DigiChat), "PushContent.txt").exists()) {
            com.diginet.digichat.util.q.b(this.k = com.diginet.digichat.util.q.b(), "");
            this.k.setBackground((c3.b != 2) ? h.df.tabsBackground : Color.white);
            this.k.setForeground(h.df.inputText);
            (this.m = new Panel()).setLayout(this.aj);
            final Insets insets2 = this.ak.insets;
            this.ak.insets = new Insets(0, 0, 0, 0);
            this.ak.gridheight = 0;
            this.ak.gridwidth = 0;
            this.ak.weightx = 0.0;
            this.ak.weighty = 0.0;
            this.aj.setConstraints(this.k, this.ak);
            this.m.add(this.k);
            this.ak.insets = insets2;
            this.ak.gridheight = -1;
            this.ak.gridwidth = 0;
            this.ak.weightx = 0.0;
            this.ak.weighty = 0.0;
            this.aj.setConstraints(this.m, this.ak);
            if (h.df.getImageButtons()) {
                container.add(this.m);
            }
            else {
                a9.add(this.m);
            }
        }
        if (!b) {
            this.i.setFont(h.df.getFont());
            this.ai = new r(this.i);
            this.i.setBackground((c3.g && c3.b != 4) ? h.df.inputBackground.darker() : h.df.inputBackground);
            this.i.setForeground(h.df.inputText);
            this.ak.gridheight = 0;
            if (h.df.getImageButtons() && b3) {
                this.ak.gridwidth = 0;
            }
            else {
                this.ak.gridwidth = 1;
            }
            this.ak.weightx = 1.0;
            this.ak.weighty = 1.0E-5;
            this.aj.setConstraints(this.ai, this.ak);
            if (h.df.getImageButtons()) {
                container.add(this.ai);
            }
            else {
                a9.add(this.ai);
            }
            this.ak.gridheight = -1;
            this.ak.weightx = (h.df.getImageButtons() ? 0.0 : 1.0E-5);
            final String fullDirectory2 = h.df.getFullDirectory();
            this.o = this.a(fullDirectory2, "settings", LanguageSupport.translate("Settings"), LanguageSupport.translate("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
            this.p = this.a(fullDirectory2, "send", null, LanguageSupport.translate("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
            this.q = this.a(fullDirectory2, "logout", LanguageSupport.translate("Logout"), StringSubst.Substitute(LanguageSupport.translate("Click here to logout and end your %1 session."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }));
            bv.a(this.o);
            bv.a(this.p);
            bv.a(this.q);
            final Panel panel = h.df.getImageButtons() ? new Panel() : null;
            if (h.df.getImageButtons()) {
                panel.setLayout(this.aj);
                this.ak.insets = new Insets(0, 0, 0, 0);
            }
            this.ak.gridwidth = (h.f ? 0 : -1);
            this.aj.setConstraints(this.o, this.ak);
            if (h.df.getImageButtons()) {
                panel.add(this.o);
            }
            else {
                a9.add(this.o);
            }
            if (!h.f) {
                this.ak.gridwidth = 0;
                this.aj.setConstraints(this.q, this.ak);
                if (h.df.getImageButtons()) {
                    panel.add(this.q);
                }
                else {
                    a9.add(this.q);
                }
            }
            if (!h.df.getImageButtons()) {
                this.ak.insets = new Insets(2, 3, 2, 3);
            }
            this.ak.weightx = 0.0;
            this.ak.fill = 2;
            this.ak.gridheight = 0;
            this.ak.gridwidth = 0;
            if (!h.df.getImageButtons()) {
                final s s = new s(this.p);
                this.aj.setConstraints(s, this.ak);
                a9.add(s);
            }
            else {
                this.aj.setConstraints(this.p, this.ak);
                panel.add(this.p);
                this.ak.gridheight = -1;
                this.ak.insets = new Insets(2, 3, 2, 3);
                this.ak.weightx = 1.0E-5;
                this.ak.weighty = 1.0E-5;
                this.aj.setConstraints(panel, this.ak);
                if (b3) {
                    container2.add(panel);
                }
                else {
                    container.add(panel);
                }
                this.ak.gridwidth = 1;
                this.ak.fill = 1;
                this.ak.weightx = 1.0;
                this.ak.weighty = 1.0;
                this.aj.setConstraints(container, this.ak);
                a9.add(container);
                if (b3) {
                    this.ak.gridwidth = 0;
                    this.ak.fill = 3;
                    this.ak.weightx = 0.0;
                    this.ak.weighty = 0.0;
                    this.aj.setConstraints(container2, this.ak);
                    a9.add(container2);
                }
            }
            if (this.p instanceof o) {
                ((o)this.p).a(LanguageSupport.translate("Send"));
            }
            this.b = new a9(0, 0, 0, 0, 0);
            (this.a = new aw()).b((h.cp > 0) ? h.cp : 7);
            this.b.setLayout(this.aj);
            this.aj.setConstraints(this.a, new GridBagConstraints());
            this.b.add(this.a);
        }
        this.c = new aa();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel2 = new Panel();
        panel2.setLayout(layout);
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.c, gridBagConstraints);
        panel2.add(this.c);
        gridBagConstraints.gridwidth = 0;
        if (b) {
            this.c.b(h.df.IMLogo);
            gridBagConstraints.anchor = 15;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            final aa aa = new aa();
            aa.b(h.df.avEnterpriseIM);
            layout.setConstraints(aa, gridBagConstraints);
            panel2.add(aa);
            this.n = new bx(h, this.getFont());
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(4, 3, 2, 3);
            gridBagConstraints.fill = 2;
            layout.setConstraints(this.n, gridBagConstraints);
            panel2.add(this.n);
        }
        else {
            this.c.b(h.df.chatLogo);
            layout.setConstraints(this.b, gridBagConstraints);
            panel2.add(this.b);
            this.b.setVisible(false);
            final bz bz = new bz(this, h);
        }
        this.ak.insets = new Insets(2, 3, 2, 3);
        this.ak.fill = 0;
        if (h.t() || h.da) {
            this.ak.anchor = 17;
        }
        this.ak.gridheight = 1;
        this.ak.gridwidth = 0;
        this.ak.weightx = 0.0;
        this.ak.weighty = 0.0;
        this.aj.setConstraints(panel2, this.ak);
        this.add(panel2);
        this.ak.gridwidth = 0;
        this.ak.gridheight = -1;
        this.ak.fill = 1;
        this.ak.weightx = 1.0;
        this.ak.weighty = 1.0;
        this.aj.setConstraints(a9, this.ak);
        this.add(a9);
        (this.r = new Panel()).setLayout(this.aj);
        this.ak.weighty = 0.0;
        this.ak.fill = 2;
        this.ak.gridwidth = 0;
        this.ak.insets = new Insets(0, 0, 0, 0);
        this.aj.setConstraints(this.r, this.ak);
        if (b) {
            final Panel panel3 = new Panel();
            panel3.setLayout(this.aj);
            this.ak.weightx = 0.0;
            this.ak.anchor = 13;
            this.ak.fill = 0;
            this.ak.gridwidth = 1;
            this.o = new b0("SETTINGS", this.getFont());
            ((b0)this.o).a(LanguageSupport.translate("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."), null);
            this.aj.setConstraints(this.o, this.ak);
            panel3.add(this.o);
            this.ak.gridwidth = 0;
            this.ak.insets = new Insets(0, 20, 0, 5);
            this.q = new b0("LOGOUT", this.getFont());
            ((b0)this.q).a(StringSubst.Substitute(LanguageSupport.translate("Click here to logout and end your %1 session."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), null);
            this.aj.setConstraints(this.q, this.ak);
            panel3.add(this.q);
            this.aj.setConstraints(panel3, this.ak);
            this.r.add(panel3);
        }
        this.ak.weightx = 1.0;
        this.ak.gridwidth = 1;
        this.ak.fill = 2;
        this.ak.insets = new Insets(2, 3, 2, 3);
        Component component;
        if (b) {
            component = this.a(h.df.roundedCorners, 3, false, h.df.helpText, h.df.helpBackground);
            this.a(h.df.helpLogoIM);
            this.b(h.df.closeHelp);
        }
        else {
            component = this.a(h.df.roundedCorners);
            this.a(h.df.helpLogo);
            component.setBackground(h.df.helpBackground);
            component.setForeground(h.df.helpText);
        }
        this.aj.setConstraints(component, this.ak);
        this.r.add(component);
        this.a(h.b7);
        if (h.c7) {
            (this.u = new a9(5, 7, 5, 7, h.df.roundedCorners)).setBackground(h.df.helpBackground);
            this.u.setForeground(h.df.helpText);
            (this.v = new b2("0000/0000")).setAlignment(1);
            this.u.add(this.v);
            this.v.setFont(h.df.getFont());
            this.ak.weightx = 0.0;
            this.ak.fill = 1;
            this.ak.gridwidth = 1;
            this.aj.setConstraints(this.u, this.ak);
        }
        if (h.c4) {
            (this.s = new a9(5, 7, 5, 7, h.df.roundedCorners)).setBackground(h.df.helpBackground);
            this.s.setForeground(h.df.helpText);
            (this.t = new b3(new String[] { "00:00:00", LanguageSupport.translate("Open"), LanguageSupport.translate("Closed") })).setAlignment(1);
            this.s.add(this.t);
            this.t.setFont(h.df.getFont());
            this.ak.weightx = 0.0;
            this.ak.fill = 1;
            this.ak.gridwidth = 0;
            this.aj.setConstraints(this.s, this.ak);
            this.r.add(this.s);
        }
        this.add(this.r);
        boolean b4 = false;
        if (b) {
            if (h.e && h.j) {
                File file = null;
                try {
                    file = new File(System.getProperty("user.home") + File.separator + "." + DigiChatAppletAbstract.OEM_DigiChat);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    if (file.exists()) {
                        final FileInputStream fileInputStream = new FileInputStream(new File(file, h.getName()));
                        final Properties properties = new Properties();
                        properties.load(fileInputStream);
                        if (properties.getProperty("buddylist.width") != null && properties.getProperty("buddylist.width") != null) {
                            try {
                                h.ak = Integer.parseInt(properties.getProperty("buddylist.width"));
                                h.al = Integer.parseInt(properties.getProperty("buddylist.height"));
                                b4 = true;
                            }
                            catch (Exception ex2) {}
                        }
                        if (properties.getProperty("buddylist.displayHelp") != null) {
                            h.ai = Boolean.valueOf(properties.getProperty("buddylist.displayHelp"));
                        }
                    }
                }
                catch (Exception ex3) {}
            }
            else if (!h.e) {
                b4 = true;
            }
        }
        if (!b4) {
            final Dimension dimension = new Dimension();
            dimension.height = DigiChatAppletAbstract.initialWindowHeight;
            dimension.width = DigiChatAppletAbstract.initialWindowWidth;
            if (!b) {
                this.resize(dimension.width, dimension.height);
            }
        }
        if (ah != null) {
            h.a(ah);
        }
        if (!b) {
            this.z = h.df.getBackgroundImage();
        }
    }
}
