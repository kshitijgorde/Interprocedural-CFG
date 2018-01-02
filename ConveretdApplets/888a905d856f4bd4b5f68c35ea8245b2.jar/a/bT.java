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
import java.net.MalformedURLException;
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

public final class bT extends ac implements aH, bf
{
    private do q;
    public dT q;
    private bl q;
    public bb q;
    private aF q;
    private aI q;
    public ap q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private bB q;
    private dl q;
    private Dimension q;
    private Image q;
    private aP q;
    private bZ q;
    dw q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        bf q = null;
        if (bC.w.z() && s != null) {
            final Image r = this.q.r(s + s2 + "_button_up.gif", true);
            final Image r2 = this.q.r(s + s2 + "_button_dn.gif", true);
            if (r != null && r2 != null) {
                ((H)(q = H.q(r, r2, null))).q(s4, null);
            }
        }
        if (q == null) {
            if (s3 == null) {
                q = new ad(70, 20);
            }
            else {
                q = new ad(s3, 70, 20);
            }
            ((ad)q).q(s4, null);
        }
        return (ad)q;
    }
    
    public final void q(final C c) {
        this.q.q(c);
    }
    
    public final void q(final A a) {
        this.q.q(a);
    }
    
    public final void q() {
        this.q.q = true;
        if (m.q().q) {
            super.setVisible(false);
            m.q().remove(this);
            m.q().q.setVisible(true);
            m.q().q.validate();
        }
    }
    
    public final do q() {
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
                return be.w("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.q) {
                return B.q(be.w("Click here to visit %1."), new String[] { this.q.q().toString() });
            }
            if (o == null) {
                return be.w("Click here to begin conferention");
            }
            if (o == this.q) {
                return be.w("Chat messages are displayed here.  Single-click on a private message to reply.");
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
                if (this.e instanceof ad) {
                    ((ad)this.e).r();
                }
                else {
                    ((H)this.e).q();
                }
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == cK.q) {
                    if (this.w instanceof ad) {
                        ((ad)this.w).r();
                    }
                    else {
                        ((H)this.w).q();
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
                        this.q.r();
                    }
                    if (cK.q) {
                        this.q.q.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.e) {
                    this.q.u();
                    return true;
                }
                if (event.target == this.q) {
                    this.q.r(0);
                    return true;
                }
                if (event.arg instanceof A) {
                    final A a = (A)event.arg;
                    aO ao;
                    if ((ao = (aO)this.q.a.w(a.y)) == null && a.q instanceof aO) {
                        ao = (aO)a.q;
                    }
                    if (a.y && a.y != this.q.s) {
                        this.q.q(a, (p)ao);
                        break;
                    }
                    if (this.q.q(43) && ao != null && a.r && a.u == -1) {
                        if (!a.h) {
                            synchronized (this.q.c) {
                                this.q.q(a, (p)ao);
                            }
                        }
                        return a.h = true;
                    }
                    if (this.q.q(43) && ao != null && a.t && a.u == -1) {
                        if (!a.h) {
                            synchronized (this.q.c) {
                                this.q.c.q(new bz(this.q, a.h, new dl(this.q, false), a.g));
                            }
                        }
                        return a.h = true;
                    }
                    if (a.u != -1) {
                        this.q.t(a.u);
                        return true;
                    }
                    if (event.modifiers == 2 && dN.q() && this.q.q(61)) {
                        if ((!this.q.q(23) || !a.q.q(23)) && (!this.q.q(25) || (!a.q.q(25) && !a.q.q(23)))) {
                            if (a.q.q(23) || a.q.q(25)) {
                                break;
                            }
                        }
                        try {
                            if (!this.q.a.equalsIgnoreCase(a.q.a)) {
                                this.q.q(null, a.q);
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
        if ((q = bC.w.q()) != null) {
            if (this.q == null) {
                this.q = new Dimension(0, 0);
            }
            final Insets insets = this.insets();
            final int n = this.getSize().width - insets.right - insets.left + 30;
            final int n2 = this.getSize().height - insets.bottom - insets.top + 30;
            final int width = q.getWidth(this);
            final int height = q.getHeight(this);
            if (this.q == null || (this.getSize().width != 0 && this.q.width != this.getSize().width) || (this.getSize().height != 0 && this.q.height != this.getSize().height)) {
                this.q = dN.q(this, n, n2, "mwp.p");
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
        this.q.q.setFont(bC.w.w());
    }
    
    public final boolean q() {
        return this.q.q();
    }
    
    public final boolean w() {
        return this.q.w();
    }
    
    public final void q(final p p) {
        this.q.q(p);
    }
    
    public final void e() {
        bb.q(this.q.q);
    }
    
    public final boolean q(final cj cj) {
        return this.q.q(cj);
    }
    
    public final boolean q(final aO ao) {
        return this.q.q(ao);
    }
    
    public final void q(final String s) {
        try {
            this.q.q(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public final void q(final cj cj) {
        if (this.q != null) {
            final aP q;
            String s;
            final int lastIndex;
            if ((lastIndex = (s = (q = this.q).q.q.a).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
                s = s.substring(0, lastIndex);
            }
            q.setTitle(dN.e + ": " + cj.a + ": " + s);
        }
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.q.w();
        }
    }
    
    public final void q(final boolean b) {
        this.q.q(0);
        final bb q = this.q;
        final boolean state = true;
        final bb bb = q;
        if (q.q instanceof Checkbox) {
            ((Checkbox)bb.q).setState(state);
            return;
        }
        final am am = (am)bb.q;
        final boolean q2 = state;
        final am am2 = am;
        if (am.q != q2) {
            am2.q = q2;
            am2.e = (am2.q ? am2.w : am2.q);
            am2.repaint();
            am2.postEvent(new Event(am2, 1001, null));
        }
    }
    
    public final void q(final eh eh, final boolean b) {
        if (this.q.r()) {
            this.q.q(eh, b, false);
        }
    }
    
    public final void w(final cj cj) {
        this.q.q(cj);
    }
    
    public final void q(final aO ao, final boolean b) {
        this.q.q(ao, b);
    }
    
    public bT(final ap q, final aP q2) {
        super(0);
        this.q = q2;
        this.setBackground(bC.w.q);
        this.q = q;
        q.q = new MediaTracker(this);
        if (cW.q == null) {
            cW.q = q.q("newNotes.gif", false);
        }
        if (cW.e == null) {
            cW.e = q.q("newOffline.gif", false);
        }
        if (dN.w == null) {
            dN.w = q.q("lockIcon.gif", false);
        }
        if (dN.e == null) {
            dN.e = q.q("defaultIcon.gif", false);
        }
        if (bC.w.e() == null && bC.w.k()) {
            bC.w.q(q.q(bC.w.r() + "helpLogo.gif", true));
        }
        if (bC.w.r() == null && bC.w.j()) {
            bC.w.w(q.q(bC.w.r() + "chatLogo.gif", true));
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final dT dt = new dT(bC.w.e);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(gridBagLayout);
        panel2.setLayout(gridBagLayout);
        dt.setBackground(bC.w.w);
        this.setLayout(gridBagLayout);
        dt.setLayout(gridBagLayout);
        (this.q = new dl(q, true)).setFont(bC.w.w());
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        dN.q = new ax(this.q);
        gridBagConstraints.fill = 2;
        dt.add(dN.q, gridBagConstraints);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final boolean b;
        if (!(b = (q.i() || q.o() || q.r())) || bC.w.z()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagLayout.setConstraints(this.q = new bZ(this.q), gridBagConstraints);
        if (bC.w.z()) {
            panel.add(this.q);
        }
        else {
            dt.add(this.q);
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
        if (bC.w.x()) {
            final String r7 = bC.w.r();
            r = q.r(r7 + "users_tab_up.gif", true);
            r2 = q.r(r7 + "users_tab_down.gif", true);
            r3 = q.r(r7 + "rooms_tab_up.gif", true);
            r4 = q.r(r7 + "rooms_tab_down.gif", true);
            if (q.r()) {
                r5 = q.r(r7 + "buddies_tab_up.gif", true);
                r6 = q.r(r7 + "buddies_tab_down.gif", true);
            }
            if (r == null || r2 == null || r3 == null || r4 == null || ((r5 == null || r6 == null) && q.r())) {
                bC.w.u(false);
            }
        }
        this.q = new bB(q, bC.w.x());
        this.q = new bb(q);
        this.q = new aF(q);
        if (q.o()) {
            this.q.q(be.w("Users"), this.q, r, r2);
        }
        if (q.i()) {
            this.q.q(be.w("Rooms"), this.q, r3, r4);
        }
        if (q.r()) {
            this.q = new aI(q);
            if (bC.w.z()) {
                this.q.setSize(175, 100);
            }
            this.q.q(be.w("Buddies"), this.q, r5, r6);
        }
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (b) {
            if (bC.w.z()) {
                panel2.add(this.q);
            }
            else {
                dt.add(this.q);
            }
        }
        this.q = new dw(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (bC.w.z()) {
            panel.add(this.q);
        }
        else {
            dt.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (bC.w.z() ? 0.0 : 1.0E-5);
        final String r8 = bC.w.r();
        if (this.q == null) {
            this.q = this.q(r8, "settings", be.w("Settings"), be.w("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        }
        if (this.w == null) {
            this.w = this.q(r8, "send", null, be.w("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        }
        if (this.e == null) {
            this.e = this.q(r8, "logout", be.w("Logout"), B.q(be.w("Click here to logout and end your %1 session."), new String[] { dN.e }));
        }
        final Container container;
        (container = new Panel()).setLayout(gridBagLayout);
        gridBagConstraints.insets = (bC.w.z() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
        gridBagConstraints.gridwidth = (q.u ? 0 : -1);
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        container.add(this.q);
        if (!q.u) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            container.add(this.e);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        if (bC.w.z()) {
            container.add(this.w, gridBagConstraints);
        }
        else {
            container.add(new as(this.w), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0E-5;
        gridBagLayout.setConstraints(container, gridBagConstraints);
        if (!bC.w.z()) {
            dt.add(container);
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
            dt.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(panel2, gridBagConstraints);
            dt.add(panel2);
        }
        if (this.w instanceof ad) {
            ((ad)this.w).q(be.w("Send"));
        }
        this.q = new dT(2, 2, 2, 2, 0);
        this.q = new do();
        this.q.q = ((q.K <= 0) ? 7 : q.K);
        this.q.setLayout(gridBagLayout);
        gridBagLayout.setConstraints(this.q, new GridBagConstraints());
        this.q.add(this.q);
        (this.q = new bl()).w(bC.w.r());
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
        new eo(this, q);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(dt, gridBagConstraints);
        this.add(dt);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q3;
        (q3 = this.q(bC.w.e)).setBackground(bC.w.r);
        q3.setForeground(bC.w.e);
        gridBagLayout.setConstraints(q3, gridBagConstraints);
        this.add(q3);
        this.q(bC.w.e());
        this.q(q.w);
        final Dimension dimension;
        (dimension = new Dimension()).height = m.q().q;
        this.resize(dimension.width = m.q().w, dimension.height);
        new aV(this.q).q();
        new bQ(this.q).q();
        this.q.e("postbox.gif", false);
        final String s = "_2";
        bi.q(bC.w.r(), "profile_request" + s, be.w("Profile"), this.q);
        bi.q(bC.w.r(), "send_small" + s, be.w("Send"), this.q);
        final String q4 = ds.q("9>F9D5");
        final String q5 = ds.q("t>F9D5");
        final String q6 = ds.q("C5>4");
        final String q7 = ds.q("3<?C5");
        final String q8 = ds.q("n<?C5");
        bi.q(bC.w.r(), q4, be.w(q5), this.q);
        bi.q(bC.w.r(), q6, be.w(ds.q("#5>4")), this.q);
        bi.q(bC.w.r(), q7, be.w(q8), this.q);
    }
}
