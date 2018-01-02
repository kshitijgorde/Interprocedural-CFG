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

public final class bb extends L implements aA, al
{
    private cc q;
    public cy q;
    private aG q;
    public ax q;
    private aj q;
    private am q;
    public W q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private aT q;
    private ca q;
    private Dimension q;
    private Image q;
    private aq q;
    private bd q;
    ci q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        aA q = null;
        if (aU.w.j() && s != null) {
            final Image r = this.q.r(s + s2 + "_button_up.gif", true);
            final Image r2 = this.q.r(s + s2 + "_button_dn.gif", true);
            if (r != null && r2 != null) {
                ((w)(q = a.w.q(r, r2, null))).q(s4, null);
            }
        }
        if (q == null) {
            if (s3 == null) {
                q = new M(70, 20);
            }
            else {
                q = new M(s3, 70, 20);
            }
            ((M)q).q(s4, null);
        }
        return (M)q;
    }
    
    public final void q(final t t) {
        this.q.q(t);
    }
    
    public final void q(final r r) {
        this.q.q(r);
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
    
    public final cc q() {
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
                return ak.q("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.q) {
                return s.q(ak.q("Click here to visit %1."), new String[] { this.q.q().toString() });
            }
            if (o == null) {
                return ak.q("Click here to begin conferention");
            }
            if (o == this.q) {
                return ak.q("Chat messages are displayed here.  Single-click on a private message to reply.");
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
                if (this.e instanceof M) {
                    ((M)this.e).r();
                }
                else {
                    ((w)this.e).q();
                }
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == bF.q) {
                    if (this.w instanceof M) {
                        ((M)this.w).r();
                    }
                    else {
                        ((w)this.w).q();
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
                    if (bF.q) {
                        this.q.q.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.e) {
                    this.q.y();
                    return true;
                }
                if (event.target == this.q) {
                    this.q.r(0);
                    return true;
                }
                if (event.arg instanceof r) {
                    final r r = (r)event.arg;
                    ar ar;
                    if ((ar = (ar)this.q.e.w(r.t)) == null && r.q instanceof ar) {
                        ar = (ar)r.q;
                    }
                    if (r.t && r.t != this.q.s) {
                        this.q.q(r, ar);
                        break;
                    }
                    if (this.q.q(43) && ar != null && r.e && r.y == -1) {
                        if (!r.f) {
                            synchronized (this.q.f) {
                                this.q.q(r, ar);
                            }
                        }
                        return r.f = true;
                    }
                    if (this.q.q(43) && ar != null && r.r && r.y == -1) {
                        if (!r.f) {
                            synchronized (this.q.f) {
                                this.q.f.q(new aP(this.q, r.f, new ca(this.q, false), r.d));
                            }
                        }
                        return r.f = true;
                    }
                    if (r.y != -1) {
                        this.q.t(r.y);
                        return true;
                    }
                    if (event.modifiers == 2 && cu.q() && this.q.q(61)) {
                        if ((!this.q.q(23) || !r.q.q(23)) && (!this.q.q(25) || (!r.q.q(25) && !r.q.q(23)))) {
                            if (r.q.q(23) || r.q.q(25)) {
                                break;
                            }
                        }
                        try {
                            if (!this.q.o.equalsIgnoreCase(r.q.o)) {
                                this.q.q(null, r.q);
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
        if ((q = aU.w.q()) != null) {
            if (this.q == null) {
                this.q = new Dimension(0, 0);
            }
            final Insets insets = this.insets();
            final int n = this.getSize().width - insets.right - insets.left + 30;
            final int n2 = this.getSize().height - insets.bottom - insets.top + 30;
            final int width = q.getWidth(this);
            final int height = q.getHeight(this);
            if (this.q == null || (this.getSize().width != 0 && this.q.width != this.getSize().width) || (this.getSize().height != 0 && this.q.height != this.getSize().height)) {
                this.q = cu.q(this, n, n2, "mwp.p");
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
        this.q.q.setFont(aU.w.w());
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
        ax.q(this.q.q);
    }
    
    public final boolean q(final bn bn) {
        return this.q.q(bn);
    }
    
    public final boolean q(final ar ar) {
        return this.q.q(ar);
    }
    
    public final void q(final bn bn) {
        if (this.q != null) {
            final aq q;
            String s;
            final int lastIndex;
            if ((lastIndex = (s = (q = this.q).q.q.o).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
                s = s.substring(0, lastIndex);
            }
            q.setTitle(cu.e + ": " + bn.o + ": " + s);
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
        final ax q = this.q;
        final boolean state = true;
        final ax ax = q;
        if (q.q instanceof Checkbox) {
            ((Checkbox)ax.q).setState(state);
            return;
        }
        final T t = (T)ax.q;
        final boolean q2 = state;
        final T t2 = t;
        if (t.q != q2) {
            t2.q = q2;
            t2.e = (t2.q ? t2.w : t2.q);
            t2.repaint();
            t2.postEvent(new Event(t2, 1001, null));
        }
    }
    
    public final void q(final cI ci, final boolean b) {
        if (this.q.w()) {
            this.q.q(ci, b, false);
        }
    }
    
    public final void w(final bn bn) {
        this.q.q(bn);
    }
    
    public final void q(final ar ar, final boolean b) {
        this.q.q(ar, b);
    }
    
    public bb(final W q, final aq q2) {
        super(0);
        this.q = q2;
        this.setBackground(aU.w.q);
        this.q = q;
        q.q = new MediaTracker(this);
        if (bP.q == null) {
            bP.q = q.q("newNotes.gif", false);
        }
        if (bP.e == null) {
            bP.e = q.q("newOffline.gif", false);
        }
        if (cu.w == null) {
            cu.w = q.q("lockIcon.gif", false);
        }
        if (cu.e == null) {
            cu.e = q.q("defaultIcon.gif", false);
        }
        if (aU.w.e() == null && aU.w.g()) {
            aU.w.q(q.q(aU.w.e() + "helpLogo.gif", true));
        }
        if (aU.w.r() == null && aU.w.f()) {
            aU.w.w(q.q(aU.w.e() + "chatLogo.gif", true));
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final cy cy = new cy(aU.w.e);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(gridBagLayout);
        panel2.setLayout(gridBagLayout);
        cy.setBackground(aU.w.w);
        this.setLayout(gridBagLayout);
        cy.setLayout(gridBagLayout);
        (this.q = new ca(q, true)).setFont(aU.w.w());
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        cu.q = new ae(this.q);
        gridBagConstraints.fill = 2;
        cy.add(cu.q, gridBagConstraints);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final boolean b;
        if (!(b = (q.u() || q.i() || q.w())) || aU.w.j()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagLayout.setConstraints(this.q = new bd(this.q), gridBagConstraints);
        if (aU.w.j()) {
            panel.add(this.q);
        }
        else {
            cy.add(this.q);
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
        if (aU.w.k()) {
            final String e = aU.w.e();
            r = q.r(e + "users_tab_up.gif", true);
            r2 = q.r(e + "users_tab_down.gif", true);
            r3 = q.r(e + "rooms_tab_up.gif", true);
            r4 = q.r(e + "rooms_tab_down.gif", true);
            if (q.w()) {
                r5 = q.r(e + "buddies_tab_up.gif", true);
                r6 = q.r(e + "buddies_tab_down.gif", true);
            }
            if (r == null || r2 == null || r3 == null || r4 == null || ((r5 == null || r6 == null) && q.w())) {
                aU.w.q(false);
            }
        }
        this.q = new aT(q, aU.w.k());
        this.q = new ax(q);
        this.q = new aj(q);
        if (q.i()) {
            this.q.q(ak.q("Users"), this.q, r, r2);
        }
        if (q.u()) {
            this.q.q(ak.q("Rooms"), this.q, r3, r4);
        }
        if (q.w()) {
            this.q = new am(q);
            if (aU.w.j()) {
                this.q.setSize(175, 100);
            }
            this.q.q(ak.q("Buddies"), this.q, r5, r6);
        }
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (b) {
            if (aU.w.j()) {
                panel2.add(this.q);
            }
            else {
                cy.add(this.q);
            }
        }
        this.q = new ci(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (aU.w.j()) {
            panel.add(this.q);
        }
        else {
            cy.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (aU.w.j() ? 0.0 : 1.0E-5);
        final String e2 = aU.w.e();
        if (this.q == null) {
            this.q = this.q(e2, "settings", ak.q("Settings"), ak.q("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        }
        if (this.w == null) {
            this.w = this.q(e2, "send", null, ak.q("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        }
        if (this.e == null) {
            this.e = this.q(e2, "logout", ak.q("Logout"), s.q(ak.q("Click here to logout and end your %1 session."), new String[] { cu.e }));
        }
        final Container container;
        (container = new Panel()).setLayout(gridBagLayout);
        gridBagConstraints.insets = (aU.w.j() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
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
        if (aU.w.j()) {
            container.add(this.w, gridBagConstraints);
        }
        else {
            container.add(new Z(this.w), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0E-5;
        gridBagLayout.setConstraints(container, gridBagConstraints);
        if (!aU.w.j()) {
            cy.add(container);
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
            cy.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(panel2, gridBagConstraints);
            cy.add(panel2);
        }
        if (this.w instanceof M) {
            ((M)this.w).q(ak.q("Send"));
        }
        this.q = new cy(2, 2, 2, 2, 0);
        this.q = new cc();
        this.q.q = ((q.Q <= 0) ? 7 : q.Q);
        this.q.setLayout(gridBagLayout);
        gridBagLayout.setConstraints(this.q, new GridBagConstraints());
        this.q.add(this.q);
        (this.q = new aG()).w(aU.w.r());
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
        new cM(this, q);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(cy, gridBagConstraints);
        this.add(cy);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q3;
        (q3 = this.q(aU.w.e)).setBackground(aU.w.r);
        q3.setForeground(aU.w.e);
        gridBagLayout.setConstraints(q3, gridBagConstraints);
        this.add(q3);
        this.q(aU.w.e());
        this.q(q.w);
        final Dimension dimension;
        (dimension = new Dimension()).height = h.q().q;
        this.resize(dimension.width = h.q().w, dimension.height);
        new at(this.q).q();
        new ba(this.q).q();
        this.q.e("postbox.gif", false);
        final String s = "_2";
        aD.q(aU.w.e(), "profile_request" + s, ak.q("Profile"), this.q);
        aD.q(aU.w.e(), "send_small" + s, ak.q("Send"), this.q);
        final String q4 = cg.q("9>F9D5");
        final String q5 = cg.q("t>F9D5");
        final String q6 = cg.q("C5>4");
        final String q7 = cg.q("3<?C5");
        final String q8 = cg.q("n<?C5");
        aD.q(aU.w.e(), q4, ak.q(q5), this.q);
        aD.q(aU.w.e(), q6, ak.q(cg.q("#5>4")), this.q);
        aD.q(aU.w.e(), q7, ak.q(q8), this.q);
    }
}
