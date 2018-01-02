// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.image.ImageObserver;
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

public final class ay extends cK implements aB
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
        return ((U)this.q).q;
    }
    
    public final boolean w() {
        if (cs.w()) {
            return false;
        }
        if (this.w instanceof Checkbox) {
            return ((Checkbox)this.w).getState();
        }
        return ((U)this.w).q;
    }
    
    static void q(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof N) {
            ((N)canvas).e();
            return;
        }
        ((x)canvas).setEnabled(false);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof N) {
            ((N)canvas).q();
            return;
        }
        ((x)canvas).setEnabled(true);
    }
    
    private Canvas q(final String s, final String s2, final String s3) {
        return aE.q(s, s2, s3, super.q);
    }
    
    public final String q(final Object o) {
        if (o == ay.q) {
            return al.q("Type text here to filter users list.");
        }
        if (o instanceof N || o instanceof x) {
            final l l;
            if ((l = (l)super.q.q()) == null) {
                return al.q("This button is disabled because no user is selected.");
            }
            if (o == this.e) {
                return a.t.q(al.q("Click here to enter a private conversation with %1."), new String[] { l.o });
            }
            if (o == this.y) {
                if (!l.o.equals(super.q.o)) {
                    return a.t.q(al.q("Click here to add %1 to your Buddy List."), new String[] { l.o });
                }
                return a.t.q(al.q("This button is disabled because you can not add yourself to your Buddy List."), new String[] { l.o });
            }
            else if (o == this.r) {
                if (l.w) {
                    return a.t.q(al.q("Click here to stop flagging messages from %1."), new String[] { l.o });
                }
                return a.t.q(al.q("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { l.o });
            }
            else {
                if (o == this.u) {
                    return a.t.q(al.q("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { l.o });
                }
                if (o == this.q) {
                    if (!this.q.q(61) && l.q(61)) {
                        return "You can't complain master.";
                    }
                    if (l.r) {
                        return a.t.q(al.q("You've already complain of %1."), new String[] { l.o });
                    }
                    return a.t.q(al.q("Click here to complain to %1. Complained users will appear in the complained panel. Check show complained users checkbox to see them."), new String[] { l.o });
                }
                else {
                    if (o == this.w) {
                        final Canvas w;
                        if ((w = this.w) != null && ((w instanceof N) ? ((N)w).q() : ((x)w).isEnabled())) {
                            if (l.e) {
                                return a.t.q(al.q("Click here to stop ignoring messages from %1."), new String[] { l.o });
                            }
                            return a.t.q(al.q("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { l.o });
                        }
                        else if (l.q(33)) {
                            return a.t.q(al.q("This button is disabled because %1 cannot be ignored."), new String[] { l.o });
                        }
                    }
                    if (o == this.t) {
                        return a.t.q(al.q("Click here to kick %1."), new String[] { l.o });
                    }
                }
            }
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        final as as = (as)super.q.q();
        switch (event.id) {
            case 402: {
                if (event.target == ay.q) {
                    super.q.q(this.q(), cs.q() && this.w());
                    break;
                }
                break;
            }
            case 1001: {
                if (as != null) {
                    final String o = as.o;
                    final T t = new T(this.q, as.a);
                    if ((super.q.q(43) && event.target == super.q) || event.target == this.e) {
                        super.q.q(null, as);
                        return true;
                    }
                    if (event.target == this.u) {
                        t.r(as.a);
                        return true;
                    }
                    if (event.target == this.q) {
                        q(this.q);
                        t.q(as);
                        return true;
                    }
                    if (event.target == this.w) {
                        this.q((l)as);
                        return true;
                    }
                    if (event.target == this.y) {
                        final cF cf;
                        (cf = new cF(as.a, as.o)).t = true;
                        cf.e = as.e;
                        cf.q = as.q;
                        cf.q = as.q;
                        cf.r = as.r;
                        cf.q(as.q());
                        if (this.q.q != null) {
                            this.q.q.q(cf, true);
                        }
                        this.q.q(this.q.x);
                    }
                    else {
                        if (event.target == this.r) {
                            as.w = !as.w;
                            as.e = false;
                            this.q.q.removeElement(o);
                            if (as.w) {
                                if (!this.q.q.contains(o)) {
                                    this.q.q.addElement(o);
                                }
                            }
                            else {
                                this.q.q.removeElement(o);
                            }
                            this.postEvent(new Event(this.r, 7689, this.q((Object)this.r)));
                            this.q(as);
                            return true;
                        }
                        if (event.target == this.t) {
                            new z(this.q, as);
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
                    if (l.q(34) || l.a == super.q.a) {
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
                if (l.q(34) || as.a == this.q.a) {
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
                if (as == null) {
                    break;
                }
                if (as.q(33)) {
                    q(this.w);
                }
                else {
                    w(this.w);
                }
                if (as.q(34) || as.a == this.q.a) {
                    if (this.t != null) {
                        q(this.t);
                        break;
                    }
                    break;
                }
                else {
                    if (as.q(44) && this.t != null) {
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
        this.q((as)l);
    }
    
    public final void q() {
        for (int i = 0; i < this.q.q(); ++i) {
            final as as = (as)this.q.q(i);
            if (this.q.y.w(as.r) == null || this.q.e.w(as.a) == null) {
                this.q.e(i);
            }
        }
    }
    
    public ay(final W w) {
        super(w);
        this.q = null;
        ay.q = new TextField(20);
        final bf q = this.q;
        final bf q2 = this.q;
        final W q3 = this.q;
        final bf bf = q2;
        cB cb;
        if (bM.q()) {
            cb = new ct(bf, q3);
        }
        else {
            cb = new cx(bf, q3);
        }
        q.q(cb);
        this.setBackground(aT.w.i);
        this.setForeground(aT.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final String e = aT.w.e();
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
        if (aT.w.g() && w.u()) {
            final Image r = w.r(e + "allusers_unchecked.gif", true);
            final Image r2 = w.r(e + "allusers_checked.gif", true);
            if (r != null && r2 != null) {
                this.q = new U(r, r2);
            }
        }
        if (cs.q() && aT.w.g()) {
            final Image w2 = w.w(e + "allusers_unchecked.gif", true);
            final Image w3 = w.w(e + "allusers_checked.gif", true);
            if (w2 != null && w3 != null) {
                this.w = new U(w2, w3);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(al.q("Show users in all rooms"));
        }
        if (cs.q() && this.w == null) {
            this.w = new Checkbox(al.q("Show reported users"));
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
        if (w.u() && this.q instanceof U) {
            final Container container;
            (container = new Panel()).setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(al.q("Show users in all rooms"));
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
            if (w.u() && this.w instanceof U) {
                final Container container2;
                (container2 = new Panel()).setLayout(layout);
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.gridwidth = -1;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.insets = new Insets(0, 5, 0, 0);
                layout.setConstraints(this.w, gridBagConstraints);
                container2.add(this.w);
                final Label label2 = new Label(al.q("Show reported users"));
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
        ay.q.setForeground(aT.w.t);
        if ((aT.w.m.getRGB() & 0xFF) != 0x0) {
            ay.q.setBackground(aT.w.m);
        }
        else {
            ay.q.setBackground(aT.w.r);
        }
        layout.setConstraints(ay.q, gridBagConstraints);
        this.add(ay.q);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final bc bc = new bc(super.q);
        layout.setConstraints(bc, gridBagConstraints);
        this.add(bc);
        final av av = new av(null, "icon");
        final av av2 = new av(null, "star");
        final av av3 = new av(null, "countryFlag");
        final av av4 = new av(al.q("Nickname"), "name");
        final int n = this.q.q(41) ? 20 : 0;
        final Image r3 = this.q.r(aT.w.e() + "settings_button_up.gif", true);
        final Image r4 = this.q.r(aT.w.e() + "logout_button_up.gif", true);
        final int max = Math.max(aT.w.y, (r3 != null && r4 != null) ? (r3.getWidth(null) + r4.getWidth(null)) : 140);
        av.e(28);
        av.w(0);
        av2.e(17);
        av2.w(0);
        av4.q(true);
        av4.e(max - (n + 45 + ((max - 140) / 3 + 5)));
        av4.w(max);
        av2.w(-30);
        av3.e(n);
        av3.w(0);
        av3.w(true);
        super.q.q(true);
        super.q.w(av);
        super.q.w(av2);
        super.q.w(av4);
        if (this.q.q(41)) {
            super.q.w(av3);
        }
        super.q.q(av4);
        super.q.w(26);
    }
}
