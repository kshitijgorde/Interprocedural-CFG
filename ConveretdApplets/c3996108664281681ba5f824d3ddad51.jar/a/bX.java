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

public final class bX extends bW implements cn
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
        return ((I)this.q).q();
    }
    
    public final void q(final boolean state) {
        if (this.q instanceof Checkbox) {
            ((Checkbox)this.q).setState(state);
            return;
        }
        ((I)this.q).q(state);
    }
    
    public final boolean w() {
        if (a.w()) {
            return false;
        }
        if (this.w instanceof Checkbox) {
            return ((Checkbox)this.w).getState();
        }
        return ((I)this.w).q();
    }
    
    private static void q(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof e) {
            ((e)canvas).e();
            return;
        }
        ((n)canvas).setEnabled(false);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof e) {
            ((e)canvas).q();
            return;
        }
        ((n)canvas).setEnabled(true);
    }
    
    private Canvas q(final String s, final String s2, final String s3) {
        return cc.q(s, s2, s3, super.q);
    }
    
    public final String q(final Object o) {
        if (o == bX.q) {
            return cv.q("Type text here to filter users list.");
        }
        if (o instanceof e || o instanceof n) {
            final bp bp;
            if ((bp = (bp)super.q.q()) == null) {
                return cv.q("This button is disabled because no user is selected.");
            }
            if (o == this.w) {
                return cv.q(cv.q("Click here to enter a private conversation with %1."), new String[] { bp.getName() });
            }
            if (o == this.t) {
                if (!bp.getName().equals(super.q.getName())) {
                    return cv.q(cv.q("Click here to add %1 to your Buddy List."), new String[] { bp.getName() });
                }
                return cv.q(cv.q("This button is disabled because you can not add yourself to your Buddy List."), new String[] { bp.getName() });
            }
            else if (o == this.e) {
                if (bp.w) {
                    return cv.q(cv.q("Click here to stop flagging messages from %1."), new String[] { bp.getName() });
                }
                return cv.q(cv.q("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { bp.getName() });
            }
            else {
                if (o == this.y) {
                    return cv.q(cv.q("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { bp.getName() });
                }
                if (o == this.u) {
                    if (!this.q.a_() && bp.a_()) {
                        return "You can't complain master.";
                    }
                    if (bp.r) {
                        return cv.q(cv.q("You've already complain of %1."), new String[] { bp.getName() });
                    }
                    return cv.q(cv.q("Click here to complain to %1. Complained users will appear in the complained panel. Check show complained users checkbox to see them."), new String[] { bp.getName() });
                }
                else {
                    if (o == this.q) {
                        final Canvas q;
                        if ((q = this.q) != null && ((q instanceof e) ? ((e)q).q() : ((n)q).isEnabled())) {
                            if (bp.e) {
                                return cv.q(cv.q("Click here to stop ignoring messages from %1."), new String[] { bp.getName() });
                            }
                            return cv.q(cv.q("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { bp.getName() });
                        }
                        else if (bp.q(33)) {
                            return cv.q(cv.q("This button is disabled because %1 cannot be ignored."), new String[] { bp.getName() });
                        }
                    }
                    if (o == this.r) {
                        return cv.q(cv.q("Click here to kick %1."), new String[] { bp.getName() });
                    }
                }
            }
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        final bV bv = (bV)super.q.q();
        switch (event.id) {
            case 402: {
                if (event.target == bX.q) {
                    super.q.q(this.q(), a.q() && this.w());
                    break;
                }
                break;
            }
            case 1001: {
                if (bv != null) {
                    final String name = bv.getName();
                    final cj cj = new cj(this.q, bv.q());
                    if ((super.q.q(43) && event.target == super.q) || event.target == this.w) {
                        super.q.q(null, bv);
                        return true;
                    }
                    if (event.target == this.y) {
                        cj.e(bv.q());
                        return true;
                    }
                    if (event.target == this.u) {
                        q(this.u);
                        cj.q(bv);
                        return true;
                    }
                    if (event.target == this.q) {
                        this.q((bp)bv);
                        return true;
                    }
                    if (event.target == this.t) {
                        final bZ bz;
                        (bz = new bZ(bv.q(), bv.getName())).t = true;
                        bz.u = bv.u;
                        bz.q = bv.q;
                        bz.q(bv.q());
                        bz.i = bv.i;
                        bz.q(bv.q());
                        if (this.q.q != null) {
                            this.q.q.q(bz, true);
                        }
                        this.q.i(this.q.v);
                    }
                    else {
                        if (event.target == this.e) {
                            bv.w = !bv.w;
                            bv.e = false;
                            this.q.q.removeElement(name);
                            if (bv.w) {
                                if (!this.q.q.contains(name)) {
                                    this.q.q.addElement(name);
                                }
                            }
                            else {
                                this.q.q.removeElement(name);
                            }
                            this.postEvent(new Event(this.e, 7689, this.q((Object)this.e)));
                            this.q(bv);
                            return true;
                        }
                        if (event.target == this.r) {
                            new U(this.q, bv);
                        }
                    }
                }
                if (event.target == this.q || event.target == this.w) {
                    if (this.q()) {
                        this.q.q(new cJ(17237506, 1));
                    }
                    this.q.q(this.q(), this.w());
                }
                this.e();
                break;
            }
            case 701: {
                final bp bp = (bp)event.arg;
                w(this.e);
                w(this.w);
                w(this.y);
                if (!bp.getName().equals(this.q.getName())) {
                    w(this.t);
                }
                else {
                    q(this.t);
                }
                if (bp.q(33)) {
                    q(this.q);
                }
                else {
                    w(this.q);
                }
                if (a.q()) {
                    if (bp.q(34) || bp.q() == super.q.q()) {
                        if (this.r != null) {
                            q(this.r);
                        }
                    }
                    else if (super.q.q(44) && this.r != null) {
                        w(this.r);
                    }
                }
                if (bp.r || (!this.q.a_() && bp.a_())) {
                    q(this.u);
                }
                else {
                    w(this.u);
                }
                if (bp.q(34) || bv.q() == this.q.q()) {
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
                if (bv == null) {
                    break;
                }
                if (bv.q(33)) {
                    q(this.q);
                }
                else {
                    w(this.q);
                }
                if (bv.q(34) || bv.q() == this.q.q()) {
                    if (this.r != null) {
                        q(this.r);
                        break;
                    }
                    break;
                }
                else {
                    if (bv.q(44) && this.r != null) {
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
    
    public final void q(final bp bp) {
        bp.e = !bp.e;
        bp.w = false;
        if (bp.e) {
            if (!this.q.q.contains(bp.getName())) {
                this.q.q.addElement(bp.getName());
            }
        }
        else {
            this.q.q.removeElement(bp.getName());
        }
        this.postEvent(new Event(this.q, 7689, this.q((Object)this.q)));
        this.q((bV)bp);
    }
    
    public final void q() {
        q(this.u);
    }
    
    public final void e() {
        for (int i = 0; i < this.q.q(); ++i) {
            final bV bv = (bV)this.q.q(i);
            if (this.q.y.w(bv.i) == null || this.q.e.w(bv.q()) == null) {
                this.q.e(i);
            }
        }
    }
    
    public bX(final bI bi) {
        super(bi);
        this.q = null;
        bX.q = new TextField(20);
        final u q = this.q;
        final u q2 = this.q;
        final bI q3 = this.q;
        final u u = q2;
        aQ aq;
        if (ar.q()) {
            aq = new aW(u, q3);
        }
        else {
            aq = new aN(u, q3);
        }
        q.q(aq);
        this.setBackground(be.w.i);
        this.setForeground(be.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final String w = be.w.w();
        if (this.q == null) {
            this.q = this.q(w, "mute", "muteUserIcon.GIF");
        }
        if (bi.q(43) && this.w == null) {
            this.w = this.q(w, "private", "whisperIcon.GIF");
        }
        if (this.e == null) {
            this.e = this.q(w, "flag", "flagUserIcon.GIF");
        }
        if (bi.q(44) && this.r == null) {
            this.r = this.q(w, "kick", "kickUserIcon.GIF");
        }
        if (bi.p() && this.t == null) {
            this.t = this.q(w, "addbuddy", "addBuddyIcon.GIF");
        }
        if (this.y == null) {
            this.y = this.q(w, "profile", "userInfoIcon.GIF");
        }
        if (this.u == null) {
            this.u = this.q(w, "complain", "complainIcon.gif");
        }
        if (be.w.h() && bi.y()) {
            final Image q4 = bi.q(w + "allusers_unchecked.gif", true);
            final Image q5 = bi.q(w + "allusers_checked.gif", true);
            if (q4 != null && q5 != null) {
                this.q = new I(q4, q5);
            }
        }
        if (a.q() && be.w.h()) {
            final Image e = bi.e(w + "allusers_unchecked.gif", true);
            final Image e2 = bi.e(w + "allusers_checked.gif", true);
            if (e != null && e2 != null) {
                this.w = new I(e, e2);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(cv.q("Show users in all rooms"));
        }
        if (a.q() && this.w == null) {
            this.w = new Checkbox(cv.q("Show reported users"));
        }
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.y, gridBagConstraints);
        this.add(this.y);
        this.add(this.u, gridBagConstraints);
        if (bi.q(43)) {
            layout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
        }
        layout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        if (!bi.p() && !bi.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        else {
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        if (bi.p() && !bi.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.t, gridBagConstraints);
            this.add(this.t);
        }
        else if (bi.p()) {
            layout.setConstraints(this.t, gridBagConstraints);
            this.add(this.t);
        }
        if (bi.q(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.r, gridBagConstraints);
            this.add(this.r);
        }
        if (bi.y() && this.q instanceof I) {
            final Container container;
            (container = new Panel()).setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(cv.q("Show users in all rooms"));
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
        else if (bi.y()) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        if (a.q()) {
            if (bi.y() && this.w instanceof I) {
                final Container container2;
                (container2 = new Panel()).setLayout(layout);
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.gridwidth = -1;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.insets = new Insets(0, 5, 0, 0);
                layout.setConstraints(this.w, gridBagConstraints);
                container2.add(this.w);
                final Label label2 = new Label(cv.q("Show reported users"));
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
            else if (bi.y()) {
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
        bX.q.setForeground(be.w.t);
        if ((be.w.m.getRGB() & 0xFF) != 0x0) {
            bX.q.setBackground(be.w.m);
        }
        else {
            bX.q.setBackground(be.w.r);
        }
        layout.setConstraints(bX.q, gridBagConstraints);
        if (this.q.q(67)) {
            this.add(bX.q);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final r r = new r(super.q);
        layout.setConstraints(r, gridBagConstraints);
        this.add(r);
    }
    
    public final void r() {
        final w w = new w(null, "icon");
        final w w2 = new w(null, "star");
        final w w3 = new w(null, "countryFlag");
        final w w4 = new w(cv.q("Nickname"), "name");
        final int n = this.q.q(41) ? 20 : 0;
        final Image q = this.q.q(be.w.w() + "settings_button_up.gif", true);
        final Image q2 = this.q.q(be.w.w() + "logout_button_up.gif", true);
        final int max = Math.max(this.getSize().width, (q != null && q2 != null) ? (q.getWidth(null) + q2.getWidth(null)) : 140);
        w.e(28);
        w.w(0);
        w2.e(17);
        w2.w(-30);
        w4.q(true);
        w4.e(max - (n + 45 + 30));
        w4.w(max);
        w3.e(n);
        w3.w(0);
        w3.w(true);
        super.q.q(true);
        super.q.w(w);
        super.q.w(w2);
        super.q.w(w4);
        if (this.q.q(41)) {
            super.q.w(w3);
        }
        super.q.q(w4);
        super.q.w(26);
    }
}
