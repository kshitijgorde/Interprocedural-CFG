// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MediaTracker;
import java.awt.Insets;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import com.spilka.client.muc.AppletAbstract;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

public final class bG extends H implements ce, cn
{
    private t q;
    public q q;
    private c q;
    private bX q;
    private bP q;
    private bu q;
    public bI q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private z q;
    private bv q;
    private Dimension q;
    private Image q;
    private bB q;
    private r q;
    private Q q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        cn q = null;
        if (be.w.h() && s != null) {
            final Image q2 = this.q.q(s + s2 + "_button_up.gif", true);
            final Image q3 = this.q.q(s + s2 + "_button_dn.gif", true);
            if (q2 != null && q3 != null) {
                ((n)(q = n.q(q2, q3, null))).q(s4, null);
            }
        }
        if (q == null) {
            if (s3 == null) {
                q = new e(70, 20);
            }
            else {
                q = new e(s3, 70, 20);
            }
            ((e)q).q(s4, null);
        }
        return (e)q;
    }
    
    public final void q(final aZ az) {
        this.q.q(az);
    }
    
    public final void q(final bF bf) {
        this.q.q(bf);
    }
    
    public final void q() {
        this.q.q();
        if (AppletAbstract.q().q) {
            super.setVisible(false);
            AppletAbstract.q().remove(this);
            AppletAbstract.q().q.setVisible(true);
            AppletAbstract.q().q.validate();
        }
    }
    
    public final t q() {
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
                return cv.q("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.q) {
                return cv.q(cv.q("Click here to visit %1."), new String[] { this.q.q().toString() });
            }
            if (o == null) {
                return cv.q("Click here to begin conferention");
            }
            if (o == this.q) {
                return cv.q("Chat messages are displayed here.  Single-click on a private message to reply.");
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
                if (this.e instanceof e) {
                    ((e)this.e).r();
                }
                else {
                    ((n)this.e).q();
                }
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == cx.q) {
                    if (this.w instanceof e) {
                        ((e)this.w).r();
                    }
                    else {
                        ((n)this.w).q();
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
                    if (cx.q) {
                        this.q.q.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.e) {
                    this.q.w();
                    return true;
                }
                if (event.target == this.q) {
                    this.q.o(0);
                    return true;
                }
                if (event.arg instanceof bF) {
                    final bF bf = (bF)event.arg;
                    bV bv;
                    if ((bv = (bV)this.q.e.w(bf.t)) == null && bf.q instanceof bV) {
                        bv = (bV)bf.q;
                    }
                    if (bf.t && bf.t != this.q.q()) {
                        this.q.q(bf, bv);
                        break;
                    }
                    if (this.q.q(43) && bv != null && bf.e && bf.y == -1) {
                        if (!bf.f) {
                            synchronized (this.q.f) {
                                this.q.q(bf, bv);
                            }
                        }
                        return bf.f = true;
                    }
                    if (this.q.q(43) && bv != null && bf.r && bf.y == -1) {
                        if (!bf.f) {
                            synchronized (this.q.f) {
                                this.q.f.q(new J(this.q, bf.f, new bv(this.q, false), bf.d));
                            }
                        }
                        return bf.f = true;
                    }
                    if (bf.y != -1) {
                        this.q.p(bf.y);
                        return true;
                    }
                    if (event.modifiers == 2 && a.q() && this.q.a_()) {
                        if ((!this.q.q(23) || !bf.q.q(23)) && (!this.q.q(25) || (!bf.q.q(25) && !bf.q.q(23)))) {
                            if (bf.q.q(23) || bf.q.q(25)) {
                                break;
                            }
                        }
                        try {
                            if (!this.q.getName().equalsIgnoreCase(bf.q.getName())) {
                                this.q.q(null, bf.q);
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
        if ((q = be.w.q()) != null) {
            if (this.q == null) {
                this.q = new Dimension(0, 0);
            }
            final Insets insets = this.insets();
            final int n = this.getSize().width - insets.right - insets.left + 30;
            final int n2 = this.getSize().height - insets.bottom - insets.top + 30;
            final int width = q.getWidth(this);
            final int height = q.getHeight(this);
            if (this.q == null || (this.getSize().width != 0 && this.q.width != this.getSize().width) || (this.getSize().height != 0 && this.q.height != this.getSize().height)) {
                this.q = co.q(this, n, n2, "mwp.p");
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
        this.q.q.setFont(be.w.w());
    }
    
    public final boolean q() {
        return this.q.q();
    }
    
    public final boolean w() {
        return this.q.w();
    }
    
    public final void q(final bp bp) {
        this.q.q(bp);
    }
    
    public final void e() {
        this.q.q();
    }
    
    public final boolean q(final bO bo) {
        return this.q.q(bo);
    }
    
    public final boolean q(final bV bv) {
        return this.q.q(bv);
    }
    
    public final void q(final bO bo) {
        if (this.q != null) {
            this.q.q(bo);
        }
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.q.r();
        }
    }
    
    public final void q(final boolean b) {
        this.q.q(0);
        this.q.q(true);
    }
    
    public final void q(final bZ bz, final boolean b) {
        if (this.q.p()) {
            this.q.q(bz, b, false);
        }
    }
    
    public final void w(final bO bo) {
        this.q.q(bo);
    }
    
    public final void q(final bV bv, final boolean b) {
        this.q.q(bv, b);
    }
    
    public bG(final bI q, final bB q2) {
        super(0);
        this.q = q2;
        this.setBackground(be.w.q);
        this.q = q;
        q.q = new MediaTracker(this);
        if (aA.q == null) {
            aA.q = q.w("newNotes.gif", false);
        }
        if (aA.e == null) {
            aA.e = q.w("newOffline.gif", false);
        }
        if (a.w == null) {
            a.w = q.w("lockIcon.gif", false);
        }
        if (a.e == null) {
            a.e = q.w("defaultIcon.gif", false);
        }
        if (be.w.e() == null && be.w.f()) {
            be.w.q(q.w(be.w.w() + "helpLogo.gif", true));
        }
        if (be.w.r() == null && be.w.d()) {
            be.w.w(q.w(be.w.w() + "chatLogo.gif", true));
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final q q3 = new q(be.w.u);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(gridBagLayout);
        panel2.setLayout(gridBagLayout);
        q3.setBackground(be.w.w);
        this.setLayout(gridBagLayout);
        q3.setLayout(gridBagLayout);
        (this.q = new bv(q, true)).setFont(be.w.w());
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        a.q = new aX(this.q);
        gridBagConstraints.fill = 2;
        q3.add(a.q, gridBagConstraints);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final boolean b;
        if (!(b = (q.y() || q.u() || q.p())) || be.w.h()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagLayout.setConstraints(this.q = new r(this.q), gridBagConstraints);
        if (be.w.h()) {
            panel.add(this.q);
        }
        else {
            q3.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        Image q4 = null;
        Image q5 = null;
        Image q6 = null;
        Image q7 = null;
        Image q8 = null;
        Image q9 = null;
        if (be.w.j()) {
            final String w = be.w.w();
            q4 = q.q(w + "users_tab_up.gif", true);
            q5 = q.q(w + "users_tab_down.gif", true);
            q6 = q.q(w + "rooms_tab_up.gif", true);
            q7 = q.q(w + "rooms_tab_down.gif", true);
            if (q.p()) {
                q8 = q.q(w + "buddies_tab_up.gif", true);
                q9 = q.q(w + "buddies_tab_down.gif", true);
            }
            if (q4 == null || q5 == null || q6 == null || q7 == null || ((q8 == null || q9 == null) && q.p())) {
                be.w.q(false);
            }
        }
        this.q = new z(q, be.w.j());
        this.q = new bX(q);
        this.q = new bP(q);
        if (q.u()) {
            this.q.q(cv.q("Users"), this.q, q4, q5);
        }
        if (q.y()) {
            this.q.q(cv.q("Rooms"), this.q, q6, q7);
        }
        if (q.p()) {
            this.q = new bu(q);
            if (be.w.h()) {
                this.q.setSize(175, 100);
            }
            this.q.q(cv.q("Buddies"), this.q, q8, q9);
        }
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (b) {
            if (be.w.h()) {
                panel2.add(this.q);
            }
            else {
                q3.add(this.q);
            }
        }
        this.q = new Q(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (be.w.h()) {
            panel.add(this.q);
        }
        else {
            q3.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (be.w.h() ? 0.0 : 1.0E-5);
        final String w2 = be.w.w();
        if (this.q == null) {
            this.q = this.q(w2, "settings", cv.q("Settings"), cv.q("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        }
        if (this.w == null) {
            this.w = this.q(w2, "send", null, cv.q("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        }
        if (this.e == null) {
            this.e = this.q(w2, "logout", cv.q("Logout"), cv.q(cv.q("Click here to logout and end your %1 session."), new String[] { a.e }));
        }
        final Container container;
        (container = new Panel()).setLayout(gridBagLayout);
        gridBagConstraints.insets = (be.w.h() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
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
        if (be.w.h()) {
            container.add(this.w, gridBagConstraints);
        }
        else {
            container.add(new d(this.w), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0E-5;
        gridBagLayout.setConstraints(container, gridBagConstraints);
        if (!be.w.h()) {
            q3.add(container);
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
            q3.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(panel2, gridBagConstraints);
            q3.add(panel2);
        }
        if (this.w instanceof e) {
            ((e)this.w).q(cv.q("Send"));
        }
        this.q = new q(2, 2, 2, 2, 0);
        (this.q = new t()).q((q.W <= 0) ? 7 : q.W);
        this.q.setLayout(gridBagLayout);
        gridBagLayout.setConstraints(this.q, new GridBagConstraints());
        this.q.add(this.q);
        (this.q = new c()).w(be.w.r());
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
        new ca(this, q);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(q3, gridBagConstraints);
        this.add(q3);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q10;
        (q10 = this.q(be.w.u)).setBackground(be.w.r);
        q10.setForeground(be.w.e);
        gridBagLayout.setConstraints(q10, gridBagConstraints);
        this.add(q10);
        this.q(be.w.e());
        this.q(q.w);
        final Dimension dimension;
        (dimension = new Dimension()).height = AppletAbstract.q().q;
        this.resize(dimension.width = AppletAbstract.q().w, dimension.height);
        new cf(this.q).q();
        new cg(this.q).q();
        this.q.r("postbox.gif", false);
        final String s = "_2";
        cc.q(be.w.w(), "profile_request" + s, cv.q("Profile"), this.q);
        cc.q(be.w.w(), "send_small" + s, cv.q("Send"), this.q);
        final String q11 = cl.q("9>F9D5");
        final String q12 = cl.q("t>F9D5");
        final String q13 = cl.q("C5>4");
        final String q14 = cl.q("3<?C5");
        final String q15 = cl.q("n<?C5");
        cc.q(be.w.w(), q11, cv.q(q12), this.q);
        cc.q(be.w.w(), q13, cv.q(cl.q("#5>4")), this.q);
        cc.q(be.w.w(), q14, cv.q(q15), this.q);
    }
    
    public final void r() {
        this.q.e();
    }
    
    public final TextArea q() {
        return this.q.q;
    }
}
