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

public final class dl extends dk implements dT
{
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private Canvas r;
    private Canvas t;
    private Canvas y;
    private Canvas u;
    private Component q;
    private Component w;
    public static TextField q;
    
    public final boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((L)this.q).q();
    }
    
    public final void q(final boolean state) {
        if (this.q instanceof Checkbox) {
            ((Checkbox)this.q).setState(state);
            return;
        }
        ((L)this.q).q(state);
    }
    
    public final boolean w() {
        if (a.w()) {
            return false;
        }
        if (this.w instanceof Checkbox) {
            return ((Checkbox)this.w).getState();
        }
        return ((L)this.w).q();
    }
    
    private static void q(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof g) {
            ((g)canvas).e();
            return;
        }
        ((p)canvas).setEnabled(false);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof g) {
            ((g)canvas).q();
            return;
        }
        ((p)canvas).setEnabled(true);
    }
    
    private Canvas q(final String s, final String s2, final String s3) {
        return dI.q(s, s2, s3, super.q);
    }
    
    public final String q(final Object o) {
        if (o == dl.q) {
            return eb.q("Type text here to filter users list.");
        }
        if (o instanceof g || o instanceof p) {
            final cz cz;
            if ((cz = (cz)super.q.q()) == null) {
                return eb.q("This button is disabled because no user is selected.");
            }
            if (o == this.w) {
                return ec.q(eb.q("Click here to enter a private conversation with %1."), new String[] { cz.getName() });
            }
            if (o == this.t) {
                if (!cz.getName().equals(super.q.getName())) {
                    return ec.q(eb.q("Click here to add %1 to your Buddy List."), new String[] { cz.getName() });
                }
                return ec.q(eb.q("This button is disabled because you can not add yourself to your Buddy List."), new String[] { cz.getName() });
            }
            else if (o == this.e) {
                if (cz.e) {
                    return ec.q(eb.q("Click here to stop flagging messages from %1."), new String[] { cz.getName() });
                }
                return ec.q(eb.q("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { cz.getName() });
            }
            else {
                if (o == this.y) {
                    return ec.q(eb.q("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { cz.getName() });
                }
                if (o == this.u) {
                    if (!this.q.a_() && cz.a_()) {
                        return "You can't complain master.";
                    }
                    if (cz.t) {
                        return ec.q(eb.q("You've already complain of %1."), new String[] { cz.getName() });
                    }
                    return ec.q(eb.q("Click here to complain to %1. Complained users will appear in the complained panel. Check show complained users checkbox to see them."), new String[] { cz.getName() });
                }
                else {
                    if (o == this.q) {
                        final Canvas q;
                        if ((q = this.q) != null && ((q instanceof g) ? ((g)q).q() : ((p)q).isEnabled())) {
                            if (cz.r) {
                                return ec.q(eb.q("Click here to stop ignoring messages from %1."), new String[] { cz.getName() });
                            }
                            return ec.q(eb.q("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { cz.getName() });
                        }
                        else if (cz.q(33)) {
                            return ec.q(eb.q("This button is disabled because %1 cannot be ignored."), new String[] { cz.getName() });
                        }
                    }
                    if (o == this.r) {
                        return ec.q(eb.q("Click here to kick %1."), new String[] { cz.getName() });
                    }
                }
            }
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        final dj dj = (dj)super.q.q();
        switch (event.id) {
            case 402: {
                if (event.target == dl.q) {
                    super.q.q(this.q(), a.q() && this.w());
                    break;
                }
                break;
            }
            case 1001: {
                if (dj != null) {
                    final String name = dj.getName();
                    final dP dp = new dP(this.q, dj.q());
                    if ((super.q.q(43) && event.target == super.q) || event.target == this.w) {
                        super.q.q(null, (cz)dj);
                        return true;
                    }
                    if (event.target == this.y) {
                        dp.e(dj.q());
                        return true;
                    }
                    if (event.target == this.u) {
                        q(this.u);
                        dp.q(dj);
                        return true;
                    }
                    if (event.target == this.q) {
                        this.q((cz)dj);
                        return true;
                    }
                    if (event.target == this.t) {
                        final dt dt;
                        (dt = new dt(dj.q(), dj.getName())).y = true;
                        dt.e = dj.e;
                        dt.q = dj.q;
                        dt.q(dj.q());
                        dt.o = dj.o;
                        dt.q(dj.q());
                        if (this.q.q != null) {
                            this.q.q.q(dt, true);
                        }
                        this.q.p(this.q.b);
                    }
                    else {
                        if (event.target == this.e) {
                            dj.e = !dj.e;
                            dj.r = false;
                            this.q.q.removeElement(name);
                            if (dj.e) {
                                if (!this.q.q.contains(name)) {
                                    this.q.q.addElement(name);
                                }
                            }
                            else {
                                this.q.q.removeElement(name);
                            }
                            this.postEvent(new Event(this.e, 7689, this.q((Object)this.e)));
                            this.q(dj);
                            return true;
                        }
                        if (event.target == this.r) {
                            new af(this.q, dj);
                        }
                    }
                }
                if (event.target == this.q || event.target == this.w) {
                    if (this.q()) {
                        this.q.q(new es(17237506, 1));
                    }
                    this.q.q(this.q(), this.w());
                }
                this.e();
                break;
            }
            case 701: {
                final cz cz = (cz)event.arg;
                w(this.e);
                w(this.w);
                w(this.y);
                if (!cz.getName().equals(this.q.getName())) {
                    w(this.t);
                }
                else {
                    q(this.t);
                }
                if (cz.q(33)) {
                    q(this.q);
                }
                else {
                    w(this.q);
                }
                if (a.q()) {
                    if (cz.q(34) || cz.q() == super.q.q()) {
                        if (this.r != null) {
                            q(this.r);
                        }
                    }
                    else if (super.q.q(44) && this.r != null) {
                        w(this.r);
                    }
                }
                if (cz.t || (!this.q.a_() && cz.a_())) {
                    q(this.u);
                }
                else {
                    w(this.u);
                }
                if (cz.q(34) || dj.q() == this.q.q()) {
                    if (this.r != null) {
                        q(this.r);
                    }
                }
                else if (this.q.q(44) && this.r != null) {
                    w(this.r);
                }
                return true;
            }
            case 702: {
                q(this.e);
                q(this.w);
                q(this.y);
                q(this.u);
                q(this.q);
                q(this.t);
                if (this.r != null) {
                    q(this.r);
                }
                return true;
            }
            default: {
                if (dj == null) {
                    break;
                }
                if (dj.q(33)) {
                    q(this.q);
                }
                else {
                    w(this.q);
                }
                if (dj.q(34) || dj.q() == this.q.q()) {
                    if (this.r != null) {
                        q(this.r);
                        break;
                    }
                    break;
                }
                else {
                    if (dj.q(44) && this.r != null) {
                        w(this.r);
                        break;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void q(final cz cz) {
        cz.r = !cz.r;
        cz.e = false;
        if (cz.r) {
            if (!this.q.q.contains(cz.getName())) {
                this.q.q.addElement(cz.getName());
            }
        }
        else {
            this.q.q.removeElement(cz.getName());
        }
        this.postEvent(new Event(this.q, 7689, this.q((Object)this.q)));
        this.q((dj)cz);
    }
    
    public final void q() {
        q(this.u);
    }
    
    public final void e() {
        for (int i = 0; i < this.q.q(); ++i) {
            final dj dj = (dj)this.q.q(i);
            if (this.q.y.w(dj.o) == null || this.q.e.w(dj.q()) == null) {
                this.q.e(i);
            }
        }
    }
    
    public dl(final cV cv) {
        super(cv);
        this.q = null;
        dl.q = new TextField(20);
        final w q = this.q;
        final w q2 = this.q;
        final cV q3 = this.q;
        final w w = q2;
        bh bh;
        if (aG.q()) {
            bh = new bo(w, q3);
        }
        else {
            bh = new bd(w, q3);
        }
        q.q(bh);
        this.setBackground(cf.w.i);
        this.setForeground(cf.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final String e = cf.w.e();
        if (this.q == null) {
            this.q = this.q(e, "mute", "muteUserIcon.GIF");
        }
        if (cv.q(43) && this.w == null) {
            this.w = this.q(e, "private", "whisperIcon.GIF");
        }
        if (this.e == null) {
            this.e = this.q(e, "flag", "flagUserIcon.GIF");
        }
        if (cv.q(44) && this.r == null) {
            this.r = this.q(e, "kick", "kickUserIcon.GIF");
        }
        if (cv.a() && this.t == null) {
            this.t = this.q(e, "addbuddy", "addBuddyIcon.GIF");
        }
        if (this.y == null) {
            this.y = this.q(e, "profile", "userInfoIcon.GIF");
        }
        if (this.u == null) {
            this.u = this.q(e, "complain", "complainIcon.gif");
        }
        if (cf.w.k() && cv.i()) {
            final Image q4 = cv.q(e + "allusers_unchecked.gif", true);
            final Image q5 = cv.q(e + "allusers_checked.gif", true);
            if (q4 != null && q5 != null) {
                this.q = new L(q4, q5);
            }
        }
        if (a.q() && cf.w.k()) {
            final Image e2 = cv.e(e + "allusers_unchecked.gif", true);
            final Image e3 = cv.e(e + "allusers_checked.gif", true);
            if (e2 != null && e3 != null) {
                this.w = new L(e2, e3);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(eb.q("Show users in all rooms"));
        }
        if (a.q() && this.w == null) {
            this.w = new Checkbox(eb.q("Show reported users"));
        }
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.y, gridBagConstraints);
        this.add(this.y);
        this.add(this.u, gridBagConstraints);
        if (cv.q(43)) {
            layout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
        }
        layout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        if (!cv.a() && !cv.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        else {
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        if (cv.a() && !cv.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.t, gridBagConstraints);
            this.add(this.t);
        }
        else if (cv.a()) {
            layout.setConstraints(this.t, gridBagConstraints);
            this.add(this.t);
        }
        if (cv.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.r, gridBagConstraints);
            this.add(this.r);
        }
        if (cv.i() && this.q instanceof L) {
            final Container container;
            (container = new Panel()).setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(eb.q("Show users in all rooms"));
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
        else if (cv.i()) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        if (a.q()) {
            if (cv.i() && this.w instanceof L) {
                final Container container2;
                (container2 = new Panel()).setLayout(layout);
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.gridwidth = -1;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.insets = new Insets(0, 5, 0, 0);
                layout.setConstraints(this.w, gridBagConstraints);
                container2.add(this.w);
                final Label label2 = new Label(eb.q("Show reported users"));
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
            else if (cv.i()) {
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
        dl.q.setForeground(cf.w.t);
        if ((cf.w.m.getRGB() & 0xFF) != 0x0) {
            dl.q.setBackground(cf.w.m);
        }
        else {
            dl.q.setBackground(cf.w.r);
        }
        layout.setConstraints(dl.q, gridBagConstraints);
        if (this.q.q(67)) {
            this.add(dl.q);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final t t = new t(super.q);
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
    }
    
    public final void r() {
        final y y = new y(null, "icon");
        final y y2 = new y(null, "star");
        final y y3 = new y(null, "countryFlag");
        final y y4 = new y(eb.q("Nickname"), "name");
        final int n = this.q.q(41) ? 20 : 0;
        final Image q = this.q.q(cf.w.e() + "settings_button_up.gif", true);
        final Image q2 = this.q.q(cf.w.e() + "logout_button_up.gif", true);
        final int max = Math.max(this.getSize().width, (q != null && q2 != null) ? (q.getWidth(null) + q2.getWidth(null)) : 140);
        y.r(28);
        y.e(0);
        y2.r(17);
        y2.e(-30);
        y4.w(true);
        y4.r(max - (n + 45 + 30));
        y4.e(max);
        y3.r(n);
        y3.e(0);
        y3.e(true);
        super.q.q(true);
        super.q.w(y);
        super.q.w(y2);
        super.q.w(y4);
        if (this.q.q(41)) {
            super.q.w(y3);
        }
        super.q.q(y4);
        super.q.w(26);
    }
}
