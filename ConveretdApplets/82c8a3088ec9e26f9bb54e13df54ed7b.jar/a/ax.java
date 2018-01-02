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

public final class ax extends cL implements aA
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
        return ((T)this.q).q;
    }
    
    public final boolean w() {
        if (cs.w()) {
            return false;
        }
        if (this.w instanceof Checkbox) {
            return ((Checkbox)this.w).getState();
        }
        return ((T)this.w).q;
    }
    
    static void q(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof M) {
            ((M)canvas).e();
            return;
        }
        ((w)canvas).setEnabled(false);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof M) {
            ((M)canvas).q();
            return;
        }
        ((w)canvas).setEnabled(true);
    }
    
    private Canvas q(final String s, final String s2, final String s3) {
        return aD.q(s, s2, s3, super.q);
    }
    
    public final String q(final Object o) {
        if (o == ax.q) {
            return ak.q("Type text here to filter users list.");
        }
        if (o instanceof M || o instanceof w) {
            final l l;
            if ((l = (l)super.q.q()) == null) {
                return ak.q("This button is disabled because no user is selected.");
            }
            if (o == this.e) {
                return s.q(ak.q("Click here to enter a private conversation with %1."), new String[] { l.o });
            }
            if (o == this.y) {
                if (!l.o.equals(super.q.o)) {
                    return s.q(ak.q("Click here to add %1 to your Buddy List."), new String[] { l.o });
                }
                return s.q(ak.q("This button is disabled because you can not add yourself to your Buddy List."), new String[] { l.o });
            }
            else if (o == this.r) {
                if (l.w) {
                    return s.q(ak.q("Click here to stop flagging messages from %1."), new String[] { l.o });
                }
                return s.q(ak.q("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { l.o });
            }
            else {
                if (o == this.u) {
                    return s.q(ak.q("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { l.o });
                }
                if (o == this.q) {
                    if (!this.q.q(61) && l.q(61)) {
                        return "You can't complain master.";
                    }
                    if (l.r) {
                        return s.q(ak.q("You've already complain of %1."), new String[] { l.o });
                    }
                    return s.q(ak.q("Click here to complain to %1. Complained users will appear in the complained panel. Check show complained users checkbox to see them."), new String[] { l.o });
                }
                else {
                    if (o == this.w) {
                        final Canvas w;
                        if ((w = this.w) != null && ((w instanceof M) ? ((M)w).q() : ((w)w).isEnabled())) {
                            if (l.e) {
                                return s.q(ak.q("Click here to stop ignoring messages from %1."), new String[] { l.o });
                            }
                            return s.q(ak.q("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { l.o });
                        }
                        else if (l.q(33)) {
                            return s.q(ak.q("This button is disabled because %1 cannot be ignored."), new String[] { l.o });
                        }
                    }
                    if (o == this.t) {
                        return s.q(ak.q("Click here to kick %1."), new String[] { l.o });
                    }
                }
            }
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        final ar ar = (ar)super.q.q();
        switch (event.id) {
            case 402: {
                if (event.target == ax.q) {
                    super.q.q(this.q(), cs.q() && this.w());
                    break;
                }
                break;
            }
            case 1001: {
                if (ar != null) {
                    final String o = ar.o;
                    final S s = new S(this.q, ar.s);
                    if ((super.q.q(43) && event.target == super.q) || event.target == this.e) {
                        super.q.q(null, ar);
                        return true;
                    }
                    if (event.target == this.u) {
                        s.r(ar.s);
                        return true;
                    }
                    if (event.target == this.q) {
                        q(this.q);
                        s.q(ar);
                        return true;
                    }
                    if (event.target == this.w) {
                        this.q((l)ar);
                        return true;
                    }
                    if (event.target == this.y) {
                        final cG cg;
                        (cg = new cG(ar.s, ar.o)).t = true;
                        cg.e = ar.e;
                        cg.q = ar.q;
                        cg.q = ar.q;
                        cg.r = ar.r;
                        cg.q(ar.q());
                        if (this.q.q != null) {
                            this.q.q.q(cg, true);
                        }
                        this.q.e(this.q.c);
                    }
                    else {
                        if (event.target == this.r) {
                            ar.w = !ar.w;
                            ar.e = false;
                            this.q.q.removeElement(o);
                            if (ar.w) {
                                if (!this.q.q.contains(o)) {
                                    this.q.q.addElement(o);
                                }
                            }
                            else {
                                this.q.q.removeElement(o);
                            }
                            this.postEvent(new Event(this.r, 7689, this.q((Object)this.r)));
                            this.q(ar);
                            return true;
                        }
                        if (event.target == this.t) {
                            new y(this.q, ar);
                        }
                    }
                }
                if (event.target == this.q || event.target == this.w) {
                    if (this.q()) {
                        this.q.r(new cp(17237506, 1));
                    }
                    this.q.q(this.q(), this.w());
                }
                this.q();
                break;
            }
            case 701: {
                final l l = (l)event.arg;
                w(this.r);
                w(this.e);
                w(this.u);
                if (!l.o.equals(this.q.o)) {
                    w(this.y);
                }
                else {
                    q(this.y);
                }
                if (l.q(33)) {
                    q(this.w);
                }
                else {
                    w(this.w);
                }
                if (cs.q()) {
                    if (l.q(34) || l.s == super.q.s) {
                        if (this.t != null) {
                            q(this.t);
                        }
                    }
                    else if (super.q.q(44) && this.t != null) {
                        w(this.t);
                    }
                }
                if (l.r || (!this.q.q(61) && l.q(61))) {
                    q(this.q);
                }
                else {
                    w(this.q);
                }
                if (l.q(34) || ar.s == this.q.s) {
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
                if (ar == null) {
                    break;
                }
                if (ar.q(33)) {
                    q(this.w);
                }
                else {
                    w(this.w);
                }
                if (ar.q(34) || ar.s == this.q.s) {
                    if (this.t != null) {
                        q(this.t);
                        break;
                    }
                    break;
                }
                else {
                    if (ar.q(44) && this.t != null) {
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
    
    public final void q(final l l) {
        l.e = !l.e;
        l.w = false;
        if (l.e) {
            if (!this.q.q.contains(l.o)) {
                this.q.q.addElement(l.o);
            }
        }
        else {
            this.q.q.removeElement(l.o);
        }
        this.postEvent(new Event(this.w, 7689, this.q((Object)this.w)));
        this.q((ar)l);
    }
    
    public final void q() {
        for (int i = 0; i < this.q.q(); ++i) {
            final ar ar = (ar)this.q.q(i);
            if (this.q.y.w(ar.r) == null || this.q.e.w(ar.s) == null) {
                this.q.e(i);
            }
        }
    }
    
    public ax(final W w) {
        super(w);
        this.q = null;
        ax.q = new TextField(20);
        final be q = this.q;
        final be q2 = this.q;
        final W q3 = this.q;
        final be be = q2;
        cC cc;
        if (bL.q()) {
            cc = new ct(be, q3);
        }
        else {
            cc = new cy(be, q3);
        }
        q.q(cc);
        this.setBackground(aS.w.i);
        this.setForeground(aS.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final String e = aS.w.e();
        if (this.w == null) {
            this.w = this.q(e, "mute", "muteUserIcon.GIF");
        }
        if (w.q(43) && this.e == null) {
            this.e = this.q(e, "private", "whisperIcon.GIF");
        }
        if (this.r == null) {
            this.r = this.q(e, "flag", "flagUserIcon.GIF");
        }
        if (w.q(44) && this.t == null) {
            this.t = this.q(e, "kick", "kickUserIcon.GIF");
        }
        if (w.w() && this.y == null) {
            this.y = this.q(e, "addbuddy", "addBuddyIcon.GIF");
        }
        if (this.u == null) {
            this.u = this.q(e, "profile", "userInfoIcon.GIF");
        }
        if (this.q == null) {
            this.q = this.q(e, "complain", "complainIcon.gif");
        }
        if (aS.w.j() && w.u()) {
            final Image r = w.r(e + "allusers_unchecked.gif", true);
            final Image r2 = w.r(e + "allusers_checked.gif", true);
            if (r != null && r2 != null) {
                this.q = new T(r, r2);
            }
        }
        if (cs.q() && aS.w.j()) {
            final Image w2 = w.w(e + "allusers_unchecked.gif", true);
            final Image w3 = w.w(e + "allusers_checked.gif", true);
            if (w2 != null && w3 != null) {
                this.w = new T(w2, w3);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(ak.q("Show users in all rooms"));
        }
        if (cs.q() && this.w == null) {
            this.w = new Checkbox(ak.q("Show reported users"));
        }
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.u, gridBagConstraints);
        this.add(this.u);
        this.add(this.q, gridBagConstraints);
        if (w.q(43)) {
            layout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        layout.setConstraints(this.r, gridBagConstraints);
        this.add(this.r);
        if (!w.w() && !w.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
        }
        else {
            layout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
        }
        if (w.w() && !w.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.y, gridBagConstraints);
            this.add(this.y);
        }
        else if (w.w()) {
            layout.setConstraints(this.y, gridBagConstraints);
            this.add(this.y);
        }
        if (w.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.t, gridBagConstraints);
            this.add(this.t);
        }
        if (w.u() && this.q instanceof T) {
            final Container container;
            (container = new Panel()).setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(ak.q("Show users in all rooms"));
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
        else if (w.u()) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        if (cs.q()) {
            if (w.u() && this.w instanceof T) {
                final Container container2;
                (container2 = new Panel()).setLayout(layout);
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.gridwidth = -1;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.insets = new Insets(0, 5, 0, 0);
                layout.setConstraints(this.w, gridBagConstraints);
                container2.add(this.w);
                final Label label2 = new Label(ak.q("Show reported users"));
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
            else if (w.u()) {
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
        ax.q.setForeground(aS.w.t);
        if ((aS.w.m.getRGB() & 0xFF) != 0x0) {
            ax.q.setBackground(aS.w.m);
        }
        else {
            ax.q.setBackground(aS.w.r);
        }
        layout.setConstraints(ax.q, gridBagConstraints);
        this.add(ax.q);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final bb bb = new bb(super.q);
        layout.setConstraints(bb, gridBagConstraints);
        this.add(bb);
    }
    
    public final void w() {
        final au au = new au(null, "icon");
        final au au2 = new au(null, "star");
        final au au3 = new au(null, "countryFlag");
        final au au4 = new au(ak.q("Nickname"), "name");
        final int n = this.q.q(41) ? 20 : 0;
        final Image r = this.q.r(aS.w.e() + "settings_button_up.gif", true);
        final Image r2 = this.q.r(aS.w.e() + "logout_button_up.gif", true);
        final int max = Math.max(this.getSize().width, (r != null && r2 != null) ? (r.getWidth(null) + r2.getWidth(null)) : 140);
        au.e(28);
        au.w(0);
        au2.e(17);
        au2.w(-30);
        au4.q(true);
        au4.e(max - (n + 45 + 30));
        au4.w(max);
        au3.e(n);
        au3.w(0);
        au3.w(true);
        super.q.q(true);
        super.q.w(au);
        super.q.w(au2);
        super.q.w(au4);
        if (this.q.q(41)) {
            super.q.w(au3);
        }
        super.q.q(au4);
        super.q.w(26);
    }
}
