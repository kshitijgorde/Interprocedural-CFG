// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Canvas;

public final class bb extends ep implements bf
{
    private Canvas w;
    private Canvas e;
    private Canvas r;
    private Canvas t;
    private Canvas y;
    private Canvas u;
    Canvas q;
    Component q;
    private Component w;
    public static TextField q;
    
    public final boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((am)this.q).q;
    }
    
    public final boolean w() {
        if (dN.w()) {
            return false;
        }
        if (this.w instanceof Checkbox) {
            return ((Checkbox)this.w).getState();
        }
        return ((am)this.w).q;
    }
    
    static void q(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof ad) {
            ((ad)canvas).e();
            return;
        }
        ((H)canvas).setEnabled(false);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof ad) {
            ((ad)canvas).q();
            return;
        }
        ((H)canvas).setEnabled(true);
    }
    
    private Canvas q(final String s, final String s2, final String s3) {
        return bi.q(s, s2, s3, super.q);
    }
    
    public final String q(final Object o) {
        if (o == bb.q) {
            return be.w("Type text here to filter users list.");
        }
        if (o instanceof ad || o instanceof H) {
            final p p;
            if ((p = (p)super.q.q()) == null) {
                return be.w("This button is disabled because no user is selected.");
            }
            if (o == this.e) {
                return B.q(be.w("Click here to enter a private conversation with %1."), new String[] { p.a });
            }
            if (o == this.y) {
                if (!p.a.equals(super.q.a)) {
                    return B.q(be.w("Click here to add %1 to your Buddy List."), new String[] { p.a });
                }
                return B.q(be.w("This button is disabled because you can not add yourself to your Buddy List."), new String[] { p.a });
            }
            else if (o == this.r) {
                if (p.w) {
                    return B.q(be.w("Click here to stop flagging messages from %1."), new String[] { p.a });
                }
                return B.q(be.w("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { p.a });
            }
            else {
                if (o == this.u) {
                    return B.q(be.w("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { p.a });
                }
                if (o == this.q) {
                    if (!this.q.q(61) && p.q(61)) {
                        return "You can't complain master.";
                    }
                    if (p.r) {
                        return B.q(be.w("You've already complain of %1."), new String[] { p.a });
                    }
                    return B.q(be.w("Click here to complain to %1. Complained users will appear in the complained panel. Check show complained users checkbox to see them."), new String[] { p.a });
                }
                else {
                    if (o == this.w) {
                        final Canvas w;
                        if ((w = this.w) != null && ((w instanceof ad) ? ((ad)w).q() : ((H)w).isEnabled())) {
                            if (p.e) {
                                return B.q(be.w("Click here to stop ignoring messages from %1."), new String[] { p.a });
                            }
                            return B.q(be.w("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { p.a });
                        }
                        else if (p.q(33)) {
                            return B.q(be.w("This button is disabled because %1 cannot be ignored."), new String[] { p.a });
                        }
                    }
                    if (o == this.t) {
                        return B.q(be.w("Click here to kick %1."), new String[] { p.a });
                    }
                }
            }
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        final aO ao = (aO)super.q.q();
        switch (event.id) {
            case 402: {
                if (event.target == bb.q) {
                    super.q.q(this.q(), dN.q() && this.w());
                    break;
                }
                break;
            }
            case 1001: {
                if (ao != null) {
                    final String a = ao.a;
                    final aG ag = new aG(this.q, ao.s);
                    if ((super.q.q(43) && event.target == super.q) || event.target == this.e) {
                        super.q.q(null, (p)ao);
                        return true;
                    }
                    if (event.target == this.u) {
                        ag.y(ao.s);
                        return true;
                    }
                    if (event.target == this.q) {
                        q(this.q);
                        ag.q(ao);
                        return true;
                    }
                    if (event.target == this.w) {
                        this.q((p)ao);
                        return true;
                    }
                    if (event.target == this.y) {
                        final eh eh;
                        (eh = new eh(ao.s, ao.a)).y = true;
                        eh.e = ao.e;
                        eh.q = ao.q;
                        eh.q = ao.q;
                        eh.r = ao.r;
                        eh.q(ao.w());
                        if (this.q.q != null) {
                            this.q.q.q(eh, true);
                        }
                        this.q.e(this.q.D);
                    }
                    else {
                        if (event.target == this.r) {
                            ao.w = !ao.w;
                            ao.e = false;
                            this.q.q.removeElement(a);
                            if (ao.w) {
                                if (!this.q.q.contains(a)) {
                                    this.q.q.addElement(a);
                                }
                            }
                            else {
                                this.q.q.removeElement(a);
                            }
                            this.postEvent(new Event(this.r, 7689, this.q((Object)this.r)));
                            this.q(ao);
                            return true;
                        }
                        if (event.target == this.t) {
                            new J(this.q, ao);
                        }
                    }
                }
                if (event.target == this.q || event.target == this.w) {
                    if (this.q()) {
                        this.q.o(new dI(17237506, 1));
                    }
                    this.q.q(this.q(), this.w());
                }
                this.q();
                break;
            }
            case 701: {
                final p p = (p)event.arg;
                w(this.r);
                w(this.e);
                w(this.u);
                if (!p.a.equals(this.q.a)) {
                    w(this.y);
                }
                else {
                    q(this.y);
                }
                if (p.q(33)) {
                    q(this.w);
                }
                else {
                    w(this.w);
                }
                if (dN.q()) {
                    if (p.q(34) || p.s == super.q.s) {
                        if (this.t != null) {
                            q(this.t);
                        }
                    }
                    else if (super.q.q(44) && this.t != null) {
                        w(this.t);
                    }
                }
                if (p.r || (!this.q.q(61) && p.q(61))) {
                    q(this.q);
                }
                else {
                    w(this.q);
                }
                if (p.q(34) || ao.s == this.q.s) {
                    if (this.t != null) {
                        q(this.t);
                    }
                }
                else if (this.q.q(44) && this.t != null) {
                    w(this.t);
                }
                return true;
            }
            case 702: {
                q(this.r);
                q(this.e);
                q(this.u);
                q(this.q);
                q(this.w);
                q(this.y);
                if (this.t != null) {
                    q(this.t);
                }
                return true;
            }
            default: {
                if (ao == null) {
                    break;
                }
                if (ao.q(33)) {
                    q(this.w);
                }
                else {
                    w(this.w);
                }
                if (ao.q(34) || ao.s == this.q.s) {
                    if (this.t != null) {
                        q(this.t);
                        break;
                    }
                    break;
                }
                else {
                    if (ao.q(44) && this.t != null) {
                        w(this.t);
                        break;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void q(final p p) {
        p.e = !p.e;
        p.w = false;
        if (p.e) {
            if (!this.q.q.contains(p.a)) {
                this.q.q.addElement(p.a);
            }
        }
        else {
            this.q.q.removeElement(p.a);
        }
        this.postEvent(new Event(this.w, 7689, this.q((Object)this.w)));
        this.q((aO)p);
    }
    
    public final void q() {
        for (int i = 0; i < this.q.q(); ++i) {
            final aO ao = (aO)this.q.q(i);
            if (this.q.f.w(ao.r) == null || this.q.a.w(ao.s) == null) {
                this.q.e(i);
            }
        }
    }
    
    public bb(final ap ap) {
        super(ap);
        this.q = null;
        bb.q = new TextField(20);
        final cc q = this.q;
        final cc q2 = this.q;
        final ap q3 = this.q;
        final cc cc = q2;
        eb eb;
        if (cU.q()) {
            eb = new dO(cc, q3);
        }
        else {
            eb = new dY(cc, q3);
        }
        q.q(eb);
        this.setBackground(bC.w.i);
        this.setForeground(bC.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final String r = bC.w.r();
        if (this.w == null) {
            this.w = this.q(r, "mute", "muteUserIcon.GIF");
        }
        if (ap.q(43) && this.e == null) {
            this.e = this.q(r, "private", "whisperIcon.GIF");
        }
        if (this.r == null) {
            this.r = this.q(r, "flag", "flagUserIcon.GIF");
        }
        if (ap.q(44) && this.t == null) {
            this.t = this.q(r, "kick", "kickUserIcon.GIF");
        }
        if (ap.r() && this.y == null) {
            this.y = this.q(r, "addbuddy", "addBuddyIcon.GIF");
        }
        if (this.u == null) {
            this.u = this.q(r, "profile", "userInfoIcon.GIF");
        }
        if (this.q == null) {
            this.q = this.q(r, "complain", "complainIcon.gif");
        }
        if (bC.w.z() && ap.i()) {
            final Image r2 = ap.r(r + "allusers_unchecked.gif", true);
            final Image r3 = ap.r(r + "allusers_checked.gif", true);
            if (r2 != null && r3 != null) {
                this.q = new am(r2, r3);
            }
        }
        if (dN.q() && bC.w.z()) {
            final Image w = ap.w(r + "allusers_unchecked.gif", true);
            final Image w2 = ap.w(r + "allusers_checked.gif", true);
            if (w != null && w2 != null) {
                this.w = new am(w, w2);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(be.w("Show users in all rooms"));
        }
        if (dN.q() && this.w == null) {
            this.w = new Checkbox(be.w("Show reported users"));
        }
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.u, gridBagConstraints);
        this.add(this.u);
        this.add(this.q, gridBagConstraints);
        if (ap.q(43)) {
            layout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        layout.setConstraints(this.r, gridBagConstraints);
        this.add(this.r);
        if (!ap.r() && !ap.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
        }
        else {
            layout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
        }
        if (ap.r() && !ap.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.y, gridBagConstraints);
            this.add(this.y);
        }
        else if (ap.r()) {
            layout.setConstraints(this.y, gridBagConstraints);
            this.add(this.y);
        }
        if (ap.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.t, gridBagConstraints);
            this.add(this.t);
        }
        if (ap.i() && this.q instanceof am) {
            final Container container;
            (container = new Panel()).setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(be.w("Show users in all rooms"));
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            layout.setConstraints(label, gridBagConstraints);
            container.add(label);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(container, gridBagConstraints);
            this.add(container);
        }
        else if (ap.i()) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        if (dN.q()) {
            if (ap.i() && this.w instanceof am) {
                final Container container2;
                (container2 = new Panel()).setLayout(layout);
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.gridwidth = -1;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.insets = new Insets(0, 5, 0, 0);
                layout.setConstraints(this.w, gridBagConstraints);
                container2.add(this.w);
                final Label label2 = new Label(be.w("Show reported users"));
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridwidth = 0;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 17;
                layout.setConstraints(label2, gridBagConstraints);
                container2.add(label2);
                gridBagConstraints.gridwidth = 0;
                layout.setConstraints(container2, gridBagConstraints);
                this.add(container2);
            }
            else if (ap.i()) {
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.insets = new Insets(0, 0, 0, 0);
                layout.setConstraints(this.w, gridBagConstraints);
                this.add(this.w);
            }
        }
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        bb.q.setForeground(bC.w.t);
        if ((bC.w.m.getRGB() & 0xFF) != 0x0) {
            bb.q.setBackground(bC.w.m);
        }
        else {
            bb.q.setBackground(bC.w.r);
        }
        layout.setConstraints(bb.q, gridBagConstraints);
        this.add(bb.q);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final bZ bz = new bZ(super.q);
        layout.setConstraints(bz, gridBagConstraints);
        this.add(bz);
    }
    
    public final void w() {
        final aX ax = new aX(null, "icon");
        final aX ax2 = new aX(null, "star");
        final aX ax3 = new aX(null, "countryFlag");
        final aX ax4 = new aX(be.w("Nickname"), "name");
        final int n = this.q.q(41) ? 20 : 0;
        final Image r = this.q.r(bC.w.r() + "settings_button_up.gif", true);
        final Image r2 = this.q.r(bC.w.r() + "logout_button_up.gif", true);
        final int max = Math.max(this.getSize().width, (r != null && r2 != null) ? (r.getWidth(null) + r2.getWidth(null)) : 140);
        ax.r(28);
        ax.e(0);
        ax2.r(17);
        ax2.e(-30);
        ax4.w(true);
        ax4.r(max - (n + 45 + 30));
        ax4.e(max);
        ax3.r(n);
        ax3.e(0);
        ax3.e(true);
        super.q.q(true);
        super.q.w(ax);
        super.q.w(ax2);
        super.q.w(ax4);
        if (this.q.q(41)) {
            super.q.w(ax3);
        }
        super.q.q(ax4);
        super.q.w(26);
    }
}
