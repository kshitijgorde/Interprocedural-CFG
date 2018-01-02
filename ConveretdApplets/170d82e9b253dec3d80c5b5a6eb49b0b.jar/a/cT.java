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
import java.net.MalformedURLException;
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

public final class cT extends K implements dK, dT
{
    private v q;
    public s q;
    private d q;
    private dl q;
    private dc q;
    private cH q;
    public cV q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private B q;
    private cI q;
    private Dimension q;
    private Image q;
    private cO q;
    private t q;
    private U q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        dT q = null;
        if (cf.w.k() && s != null) {
            final Image q2 = this.q.q(s + s2 + "_button_up.gif", true);
            final Image q3 = this.q.q(s + s2 + "_button_dn.gif", true);
            if (q2 != null && q3 != null) {
                ((p)(q = p.q(q2, q3, null))).q(s4, null);
            }
        }
        if (q == null) {
            if (s3 == null) {
                q = new g(70, 20);
            }
            else {
                q = new g(s3, 70, 20);
            }
            ((g)q).q(s4, null);
        }
        return (g)q;
    }
    
    public final void q(final bY by) {
        this.q.q(by);
    }
    
    public final void q(final cS cs) {
        this.q.q(cs);
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
    
    public final v q() {
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
                return eb.q("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
            }
            if (o == this.q) {
                return ec.q(eb.q("Click here to visit %1."), new String[] { this.q.q().toString() });
            }
            if (o == null) {
                return eb.q("Click here to begin conferention");
            }
            if (o == this.q) {
                return eb.q("Chat messages are displayed here.  Single-click on a private message to reply.");
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
                if (this.e instanceof g) {
                    ((g)this.e).r();
                }
                else {
                    ((p)this.e).q();
                }
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == ef.q) {
                    if (this.w instanceof g) {
                        ((g)this.w).r();
                    }
                    else {
                        ((p)this.w).q();
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
                    if (ef.q) {
                        this.q.q.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.e) {
                    this.q.w();
                    return true;
                }
                if (event.target == this.q) {
                    this.q.a(0);
                    return true;
                }
                if (event.arg instanceof cS) {
                    final cS cs = (cS)event.arg;
                    dj dj;
                    if ((dj = (dj)this.q.e.w(cs.y)) == null && cs.q instanceof dj) {
                        dj = (dj)cs.q;
                    }
                    if (cs.y && cs.y != this.q.q()) {
                        this.q.q(cs, (cz)dj);
                        break;
                    }
                    if (this.q.q(43) && dj != null && cs.r && cs.u == -1) {
                        if (!cs.h) {
                            synchronized (this.q.g) {
                                this.q.q(cs, (cz)dj);
                            }
                        }
                        return cs.h = true;
                    }
                    if (this.q.q(43) && dj != null && cs.t && cs.u == -1) {
                        if (!cs.h) {
                            synchronized (this.q.g) {
                                this.q.g.q(new M(this.q, cs.h, new cI(this.q, false), cs.g));
                            }
                        }
                        return cs.h = true;
                    }
                    if (cs.u != -1) {
                        this.q.s(cs.u);
                        return true;
                    }
                    if (event.modifiers == 2 && a.q() && this.q.a_()) {
                        if ((!this.q.q(23) || !cs.q.q(23)) && (!this.q.q(25) || (!cs.q.q(25) && !cs.q.q(23)))) {
                            if (cs.q.q(23) || cs.q.q(25)) {
                                break;
                            }
                        }
                        try {
                            if (!this.q.getName().equalsIgnoreCase(cs.q.getName())) {
                                this.q.q(null, cs.q);
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
        if ((q = cf.w.q()) != null) {
            if (this.q == null) {
                this.q = new Dimension(0, 0);
            }
            final Insets insets = this.insets();
            final int n = this.getSize().width - insets.right - insets.left + 30;
            final int n2 = this.getSize().height - insets.bottom - insets.top + 30;
            final int width = q.getWidth(this);
            final int height = q.getHeight(this);
            if (this.q == null || (this.getSize().width != 0 && this.q.width != this.getSize().width) || (this.getSize().height != 0 && this.q.height != this.getSize().height)) {
                this.q = dU.q(this, n, n2, "mwp.p");
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
        this.q.q.setFont(cf.w.w());
    }
    
    public final boolean q() {
        return this.q.q();
    }
    
    public final boolean w() {
        return this.q.w();
    }
    
    public final void q(final cz cz) {
        this.q.q(cz);
    }
    
    public final void e() {
        this.q.q();
    }
    
    public final boolean q(final db db) {
        return this.q.q(db);
    }
    
    public final boolean q(final dj dj) {
        return this.q.q(dj);
    }
    
    public final void q(final String s) {
        try {
            this.q.q(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public final void q(final db db) {
        if (this.q != null) {
            this.q.q(db);
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
    
    public final void q(final dt dt, final boolean b) {
        if (this.q.a()) {
            this.q.q(dt, b, false);
        }
    }
    
    public final void w(final db db) {
        this.q.q(db);
    }
    
    public final void q(final dj dj, final boolean b) {
        this.q.q(dj, b);
    }
    
    public cT(final cV q, final cO q2) {
        super(0);
        this.q = q2;
        this.setBackground(cf.w.q);
        this.q = q;
        q.q = new MediaTracker(this);
        if (aP.q == null) {
            aP.q = q.w("newNotes.gif", false);
        }
        if (aP.e == null) {
            aP.e = q.w("newOffline.gif", false);
        }
        if (a.w == null) {
            a.w = q.w("lockIcon.gif", false);
        }
        if (a.e == null) {
            a.e = q.w("defaultIcon.gif", false);
        }
        if (cf.w.e() == null && cf.w.h()) {
            cf.w.q(q.w(cf.w.e() + "helpLogo.gif", true));
        }
        if (cf.w.r() == null && cf.w.g()) {
            cf.w.w(q.w(cf.w.e() + "chatLogo.gif", true));
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final s s = new s(cf.w.e);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(gridBagLayout);
        panel2.setLayout(gridBagLayout);
        s.setBackground(cf.w.w);
        this.setLayout(gridBagLayout);
        s.setLayout(gridBagLayout);
        (this.q = new cI(q, true)).setFont(cf.w.w());
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        a.q = new bq(this.q);
        gridBagConstraints.fill = 2;
        s.add(a.q, gridBagConstraints);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final boolean b;
        if (!(b = (q.i() || q.o() || q.a())) || cf.w.k()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagLayout.setConstraints(this.q = new t(this.q), gridBagConstraints);
        if (cf.w.k()) {
            panel.add(this.q);
        }
        else {
            s.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        Image q3 = null;
        Image q4 = null;
        Image q5 = null;
        Image q6 = null;
        Image q7 = null;
        Image q8 = null;
        if (cf.w.l()) {
            final String e = cf.w.e();
            q3 = q.q(e + "users_tab_up.gif", true);
            q4 = q.q(e + "users_tab_down.gif", true);
            q5 = q.q(e + "rooms_tab_up.gif", true);
            q6 = q.q(e + "rooms_tab_down.gif", true);
            if (q.a()) {
                q7 = q.q(e + "buddies_tab_up.gif", true);
                q8 = q.q(e + "buddies_tab_down.gif", true);
            }
            if (q3 == null || q4 == null || q5 == null || q6 == null || ((q7 == null || q8 == null) && q.a())) {
                cf.w.u(false);
            }
        }
        this.q = new B(q, cf.w.l());
        this.q = new dl(q);
        this.q = new dc(q);
        if (q.o()) {
            this.q.q(eb.q("Users"), this.q, q3, q4);
        }
        if (q.i()) {
            this.q.q(eb.q("Rooms"), this.q, q5, q6);
        }
        if (q.a()) {
            this.q = new cH(q);
            if (cf.w.k()) {
                this.q.setSize(175, 100);
            }
            this.q.q(eb.q("Buddies"), this.q, q7, q8);
        }
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (b) {
            if (cf.w.k()) {
                panel2.add(this.q);
            }
            else {
                s.add(this.q);
            }
        }
        this.q = new U(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        if (cf.w.k()) {
            panel.add(this.q);
        }
        else {
            s.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (cf.w.k() ? 0.0 : 1.0E-5);
        final String e2 = cf.w.e();
        if (this.q == null) {
            this.q = this.q(e2, "settings", eb.q("Settings"), eb.q("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        }
        if (this.w == null) {
            this.w = this.q(e2, "send", null, eb.q("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        }
        if (this.e == null) {
            this.e = this.q(e2, "logout", eb.q("Logout"), ec.q(eb.q("Click here to logout and end your %1 session."), new String[] { a.e }));
        }
        final Container container;
        (container = new Panel()).setLayout(gridBagLayout);
        gridBagConstraints.insets = (cf.w.k() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
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
        if (cf.w.k()) {
            container.add(this.w, gridBagConstraints);
        }
        else {
            container.add(new f(this.w), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0E-5;
        gridBagLayout.setConstraints(container, gridBagConstraints);
        if (!cf.w.k()) {
            s.add(container);
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
            s.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(panel2, gridBagConstraints);
            s.add(panel2);
        }
        if (this.w instanceof g) {
            ((g)this.w).q(eb.q("Send"));
        }
        this.q = new s(2, 2, 2, 2, 0);
        (this.q = new v()).q((q.E <= 0) ? 7 : q.E);
        this.q.setLayout(gridBagLayout);
        gridBagLayout.setConstraints(this.q, new GridBagConstraints());
        this.q.add(this.q);
        (this.q = new d()).w(cf.w.r());
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
        new du(this, q);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(s, gridBagConstraints);
        this.add(s);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q9;
        (q9 = this.q(cf.w.e)).setBackground(cf.w.r);
        q9.setForeground(cf.w.e);
        gridBagLayout.setConstraints(q9, gridBagConstraints);
        this.add(q9);
        this.q(cf.w.e());
        this.q(q.w);
        final Dimension dimension;
        (dimension = new Dimension()).height = AppletAbstract.q().q;
        this.resize(dimension.width = AppletAbstract.q().w, dimension.height);
        new dL(this.q).q();
        new dM(this.q).q();
        this.q.r("postbox.gif", false);
        final String s2 = "_2";
        dI.q(cf.w.e(), "profile_request" + s2, eb.q("Profile"), this.q);
        dI.q(cf.w.e(), "send_small" + s2, eb.q("Send"), this.q);
        final String q10 = dV.q("9>F9D5");
        final String q11 = dV.q("t>F9D5");
        final String q12 = dV.q("C5>4");
        final String q13 = dV.q("3<?C5");
        final String q14 = dV.q("n<?C5");
        dI.q(cf.w.e(), q10, eb.q(q11), this.q);
        dI.q(cf.w.e(), q12, eb.q(dV.q("#5>4")), this.q);
        dI.q(cf.w.e(), q13, eb.q(q14), this.q);
    }
    
    public final void r() {
        this.q.e();
    }
    
    public final TextArea q() {
        return this.q.q;
    }
}
