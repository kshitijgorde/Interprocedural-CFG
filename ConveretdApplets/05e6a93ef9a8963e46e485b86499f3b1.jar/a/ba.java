// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MediaTracker;
import java.awt.Checkbox;
import java.awt.Insets;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

public final class ba extends M implements aB, an
{
    private ca q;
    public cv q;
    private aH q;
    public ay q;
    private ak q;
    private am q;
    public W q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private aS q;
    private bY q;
    private Dimension q;
    private Image q;
    private ar q;
    private bc q;
    cg q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        aB q = null;
        if (aT.w.g() && s != null) {
            final Image r = this.q.r(s + s2 + "_button_up.gif", true);
            final Image r2 = this.q.r(s + s2 + "_button_dn.gif", true);
            if (r != null && r2 != null) {
                ((x)(q = a.x.q(r, r2, null))).q(s4, null);
            }
        }
        if (q == null) {
            if (s3 == null) {
                q = new N(70, 20);
            }
            else {
                q = new N(s3, 70, 20);
            }
            ((N)q).q(s4, null);
        }
        return (N)q;
    }
    
    public final void q(final u u) {
        this.q.q(u);
    }
    
    public final void q(final s s) {
        this.q.q(s);
    }
    
    public final void q() {
        this.q.q = true;
        if (h.q().q) {
            super.setVisible(false);
            h.q().remove(this);
            h.q().q.setVisible(true);
            h.q().q.validate();
        }
    }
    
    public final ca q() {
        return this.q;
    }
    
    public final Frame q() {
        return this.q;
    }
    
    public final Container q() {
        return this;
    }
    
    public final String q(final Object o) {
        try {
            if (o == this.q) {
                return al.q("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.q) {
                return t.q(al.q("Click here to visit %1."), new String[] { this.q.q().toString() });
            }
            if (o == null) {
                return al.q("Click here to begin conferention");
            }
            if (o == this.q) {
                return al.q("Chat messages are displayed here.  Single-click on a private message to reply.");
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (this.e instanceof N) {
                    ((N)this.e).r();
                }
                else {
                    ((x)this.e).q();
                }
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == bE.q) {
                    if (this.w instanceof N) {
                        ((N)this.w).r();
                    }
                    else {
                        ((x)this.w).q();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == null) {
                    this.q.q("", -1, 4);
                    return true;
                }
                if (event.target == this.w) {
                    final String q;
                    if ((q = this.q.q(true)).length() > 0) {
                        this.q.q(q, this.q.q.getForeground().getRGB());
                        this.q.e();
                    }
                    if (bE.q) {
                        this.q.q.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.e) {
                    this.q.y();
                    return true;
                }
                if (event.target == this.q) {
                    this.q.w(0);
                    return true;
                }
                if (event.arg instanceof s) {
                    final s s = (s)event.arg;
                    as as;
                    if ((as = (as)this.q.e.w(s.t)) == null && s.q instanceof as) {
                        as = (as)s.q;
                    }
                    if (s.t && s.t != this.q.a) {
                        this.q.q(s, as);
                        break;
                    }
                    if (this.q.q(43) && as != null && s.e && s.y == -1) {
                        if (!s.f) {
                            synchronized (this.q.f) {
                                this.q.q(s, as);
                            }
                        }
                        return s.f = true;
                    }
                    if (this.q.q(43) && as != null && s.r && s.y == -1) {
                        if (!s.f) {
                            synchronized (this.q.f) {
                                this.q.f.q(new aP(this.q, s.f, new bY(this.q, false), s.d));
                            }
                        }
                        return s.f = true;
                    }
                    if (s.y != -1) {
                        this.q.e(s.y);
                        return true;
                    }
                    if (event.modifiers == 2 && cs.q() && this.q.q(61)) {
                        if ((!this.q.q(23) || !s.q.q(23)) && (!this.q.q(25) || (!s.q.q(25) && !s.q.q(23)))) {
                            if (s.q.q(23) || s.q.q(25)) {
                                break;
                            }
                        }
                        try {
                            if (!this.q.o.equalsIgnoreCase(s.q.o)) {
                                this.q.q(null, s.q);
                            }
                        }
                        catch (Exception ex) {}
                        break;
                    }
                    break;
                }
                else {
                    if (event.arg instanceof URL) {
                        this.q.q((URL)event.arg, "_blank");
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Image q;
        if ((q = aT.w.q()) != null) {
            if (this.q == null) {
                this.q = new Dimension(0, 0);
            }
            final Insets insets = this.insets();
            final int n = this.getSize().width - insets.right - insets.left + 30;
            final int n2 = this.getSize().height - insets.bottom - insets.top + 30;
            final int width = q.getWidth(this);
            final int height = q.getHeight(this);
            if (this.q == null || this.q.width != this.getSize().width || this.q.height != this.getSize().height) {
                this.q = cs.q(this, n, n2, "mwp.p");
            }
            this.q = this.getSize();
            final Graphics graphics2;
            (graphics2 = this.q.getGraphics()).drawImage(q, 0, 0, null);
            for (int i = 0; i <= n / width; ++i) {
                for (int j = 0; j <= n2 / height; ++j) {
                    if (i + j > 0) {
                        graphics2.copyArea(0, 0, width, height, i * width, j * height);
                    }
                }
            }
            graphics2.dispose();
            graphics.drawImage(this.q, 0, 0, this);
            this.q.q(this.q);
        }
    }
    
    public final void w() {
        this.q.w();
        this.q.q.setFont(aT.w.w());
    }
    
    public final boolean q() {
        return this.q.q();
    }
    
    public final boolean w() {
        return this.q.w();
    }
    
    public final void q(final l l) {
        this.q.q(l);
    }
    
    public final void e() {
        ay.q(this.q.q);
    }
    
    public final boolean q(final bm bm) {
        return this.q.q(bm);
    }
    
    public final boolean q(final as as) {
        return this.q.q(as);
    }
    
    public final void q(final bm bm) {
        if (this.q != null) {
            final ar q;
            String s;
            final int lastIndex;
            if ((lastIndex = (s = (q = this.q).q.q.o).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
                s = s.substring(0, lastIndex);
            }
            q.setTitle(cs.e + ": " + bm.o + ": " + s);
        }
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public final void q(final boolean b) {
        this.q.q(0);
        final ay q = this.q;
        final boolean state = true;
        final ay ay = q;
        if (q.q instanceof Checkbox) {
            ((Checkbox)ay.q).setState(state);
            return;
        }
        final U u = (U)ay.q;
        final boolean q2 = state;
        final U u2 = u;
        if (u.q != q2) {
            u2.q = q2;
            u2.e = (u2.q ? u2.w : u2.q);
            u2.repaint();
            u2.postEvent(new Event(u2, 1001, null));
        }
    }
    
    public final void q(final cF cf, final boolean b) {
        if (this.q.w()) {
            this.q.q(cf, b, false);
        }
    }
    
    public final void w(final bm bm) {
        this.q.q(bm);
    }
    
    public final void q(final as as, final boolean b) {
        this.q.q(as, b);
    }
    
    public ba(final W q, final ar q2) {
        super(0);
        this.q = q2;
        this.setBackground(aT.w.q);
        this.q = q;
        q.q = new MediaTracker(this);
        if (bO.q == null) {
            bO.q = q.q("newNotes.gif", false);
        }
        if (bO.e == null) {
            bO.e = q.q("newOffline.gif", false);
        }
        if (cs.w == null) {
            cs.w = q.q("lockIcon.gif", false);
        }
        if (cs.e == null) {
            cs.e = q.q("defaultIcon.gif", false);
        }
        if (aT.w.e() == null && aT.w.d()) {
            aT.w.q(q.q(aT.w.e() + "helpLogo.gif", true));
        }
        if (aT.w.r() == null && aT.w.s()) {
            aT.w.w(q.q(aT.w.e() + "chatLogo.gif", true));
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final cv cv = new cv(aT.w.e);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(gridBagLayout);
        panel2.setLayout(gridBagLayout);
        cv.setBackground(aT.w.w);
        this.setLayout(gridBagLayout);
        cv.setLayout(gridBagLayout);
        (this.q = new bY(q, true)).setFont(aT.w.w());
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        cs.q = new af(this.q);
        gridBagConstraints.fill = 2;
        cv.add(cs.q, gridBagConstraints);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final boolean b;
        if (!(b = (q.u() || q.i() || q.w())) || aT.w.g()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagLayout.setConstraints(this.q = new bc(this.q), gridBagConstraints);
        if (aT.w.g()) {
            panel.add(this.q);
        }
        else {
            cv.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        Image r = null;
        Image r2 = null;
        Image r3 = null;
        Image r4 = null;
        Image r5 = null;
        Image r6 = null;
        if (aT.w.h()) {
            final String e = aT.w.e();
            r = q.r(e + "users_tab_up.gif", true);
            r2 = q.r(e + "users_tab_down.gif", true);
            r3 = q.r(e + "rooms_tab_up.gif", true);
            r4 = q.r(e + "rooms_tab_down.gif", true);
            if (q.w()) {
                r5 = q.r(e + "buddies_tab_up.gif", true);
                r6 = q.r(e + "buddies_tab_down.gif", true);
            }
            if (r == null || r2 == null || r3 == null || r4 == null || ((r5 == null || r6 == null) && q.w())) {
                aT.w.q(false);
            }
        }
        this.q = new aS(q, aT.w.h());
        this.q = new ay(q);
        this.q = new ak(q);
        if (q.i()) {
            this.q.q(al.q("Users"), this.q, r, r2);
        }
        if (q.u()) {
            this.q.q(al.q("Rooms"), this.q, r3, r4);
        }
        if (q.w()) {
            this.q = new am(q);
            if (aT.w.g()) {
                this.q.setSize(175, 100);
            }
            this.q.q(al.q("Buddies"), this.q, r5, r6);
        }
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (b) {
            if (aT.w.g()) {
                panel2.add(this.q);
            }
            else {
                cv.add(this.q);
            }
        }
        this.q = new cg(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (aT.w.g()) {
            panel.add(this.q);
        }
        else {
            cv.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (aT.w.g() ? 0.0 : 1.0E-5);
        final String e2 = aT.w.e();
        if (this.q == null) {
            this.q = this.q(e2, "settings", al.q("Settings"), al.q("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        }
        if (this.w == null) {
            this.w = this.q(e2, "send", null, al.q("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        }
        if (this.e == null) {
            this.e = this.q(e2, "logout", al.q("Logout"), t.q(al.q("Click here to logout and end your %1 session."), new String[] { cs.e }));
        }
        final Container container;
        (container = new Panel()).setLayout(gridBagLayout);
        gridBagConstraints.insets = (aT.w.g() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
        gridBagConstraints.gridwidth = (q.y ? 0 : -1);
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        container.add(this.q);
        if (!q.y) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            container.add(this.e);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        if (aT.w.g()) {
            container.add(this.w, gridBagConstraints);
        }
        else {
            container.add(new ab(this.w), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0E-5;
        gridBagLayout.setConstraints(container, gridBagConstraints);
        if (!aT.w.g()) {
            cv.add(container);
        }
        else {
            if (b) {
                panel2.add(container);
            }
            else {
                panel.add(container);
            }
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(panel, gridBagConstraints);
            cv.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(panel2, gridBagConstraints);
            cv.add(panel2);
        }
        if (this.w instanceof N) {
            ((N)this.w).q(al.q("Send"));
        }
        this.q = new cv(2, 2, 2, 2, 0);
        this.q = new ca();
        this.q.q = ((q.m <= 0) ? 7 : q.m);
        this.q.setLayout(gridBagLayout);
        gridBagLayout.setConstraints(this.q, new GridBagConstraints());
        this.q.add(this.q);
        (this.q = new aH()).w(aT.w.r());
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final Container container2;
        (container2 = new Panel()).setLayout(layout);
        gridBagConstraints2.gridwidth = -1;
        layout.setConstraints(this.q, gridBagConstraints2);
        gridBagConstraints.anchor = 10;
        container2.add(this.q);
        gridBagConstraints2.gridwidth = 0;
        layout.setConstraints(this.q, gridBagConstraints2);
        container2.add(this.q);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(container2, gridBagConstraints);
        this.add(container2);
        this.q.setVisible(false);
        new cJ(this, q);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(cv, gridBagConstraints);
        this.add(cv);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q3;
        (q3 = this.q(aT.w.e)).setBackground(aT.w.r);
        q3.setForeground(aT.w.e);
        gridBagLayout.setConstraints(q3, gridBagConstraints);
        this.add(q3);
        this.q(aT.w.e());
        this.q(q.w);
        final Dimension dimension;
        (dimension = new Dimension()).height = h.q().q;
        this.resize(dimension.width = h.q().w, dimension.height);
        new au(this.q).q();
        new aZ(this.q).q();
        this.q.e("postbox.gif", false);
        final String s = "_2";
        aE.q(aT.w.e(), "profile_request" + s, al.q("Profile"), this.q);
        aE.q(aT.w.e(), "send_small" + s, al.q("Send"), this.q);
        final String q4 = ce.q("9>F9D5");
        final String q5 = ce.q("t>F9D5");
        final String q6 = ce.q("C5>4");
        final String q7 = ce.q("3<?C5");
        final String q8 = ce.q("n<?C5");
        aE.q(aT.w.e(), q4, al.q(q5), this.q);
        aE.q(aT.w.e(), q6, al.q(ce.q("#5>4")), this.q);
        aE.q(aT.w.e(), q7, al.q(q8), this.q);
    }
}
