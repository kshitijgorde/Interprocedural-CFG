// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.Image;
import java.awt.Checkbox;
import java.util.Vector;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Canvas;

public class bh extends bg implements aO
{
    private Canvas l;
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Canvas m;
    private Canvas i;
    private Component e;
    public static TextField h;
    private Canvas n;
    private at i;
    private Vector k;
    
    public boolean b() {
        if (this.e instanceof Checkbox) {
            return ((Checkbox)this.e).getState();
        }
        return ((n)this.e).b();
    }
    
    public void a(final boolean state) {
        if (this.e instanceof Checkbox) {
            ((Checkbox)this.e).setState(state);
        }
        else {
            ((n)this.e).a(state);
        }
    }
    
    private final void b(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof ax) {
            ((ax)canvas).g();
        }
        else {
            ((aB)canvas).setEnabled(false);
        }
    }
    
    private final void c(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof ax) {
            ((ax)canvas).c();
        }
        else {
            ((aB)canvas).setEnabled(true);
        }
    }
    
    public boolean a(final Canvas canvas) {
        if (canvas == null) {
            return false;
        }
        if (canvas instanceof ax) {
            return ((ax)canvas).b();
        }
        return ((aB)canvas).isEnabled();
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        aO a;
        if (!super.f.b.f() || s == null) {
            a = new ax(28, 28);
            ((ax)a).a(super.f.a(s3, false, 20));
        }
        else {
            final Image a2 = super.f.a(s + s2 + "_button_up.gif", true);
            final Image a3 = super.f.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = super.f.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new ax(28, 28);
                ((ax)a).a(super.f.a(s3, false, 20));
            }
            else {
                a = aB.a(a2, a3, a4);
            }
        }
        return (ax)a;
    }
    
    public String a(final Object o) {
        if (o == bh.h) {
            return ar.b("Type text here to filter users list.");
        }
        if (o instanceof ax || o instanceof aB) {
            final F f = (F)super.c.a();
            if (f == null) {
                return ar.b("This button is disabled because no user is selected.");
            }
            if (o == this.a) {
                return H.a(ar.b("Click here to enter a private conversation with %1."), new String[] { f.d() });
            }
            if (o == this.n) {
                if (!this.k.contains(String.valueOf(f.e()))) {
                    return H.a(ar.b("Click here to report %1."), new String[] { f.d() });
                }
                return H.a(ar.b("You already reported %1."), new String[] { f.d() });
            }
            else if (o == this.m) {
                if (!f.d().equals(super.f.d())) {
                    return H.a(ar.b("Click here to add %1 to your Buddy List."), new String[] { f.d() });
                }
                return H.a(ar.b("This button is disabled because you can not add yourself to your Buddy List."), new String[] { f.d() });
            }
            else {
                if (o == this.b) {
                    return f.j ? H.a(ar.b("Click here to stop flagging messages from %1."), new String[] { f.d() }) : H.a(ar.b("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { f.d() });
                }
                if (o == this.i) {
                    return H.a(ar.b("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { f.d() });
                }
                if (o == this.l) {
                    if (this.a(this.l)) {
                        return f.h ? H.a(ar.b("Click here to stop ignoring messages from %1."), new String[] { f.d() }) : H.a(ar.b("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { f.d() });
                    }
                    if (f.a(33)) {
                        return H.a(ar.b("This button is disabled because %1 cannot be ignored."), new String[] { f.d() });
                    }
                }
                if (o == this.c) {
                    return H.a(ar.b("Click here to kick %1."), new String[] { f.d() });
                }
            }
        }
        return null;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 402 && event.modifiers == 2 && event.key == 4 && as.a != null) {
            as.a(super.f);
            return true;
        }
        final a a = (a)super.c.a();
        switch (event.id) {
            case 402: {
                if (event.target == bh.h) {
                    super.f.b(this.b());
                    break;
                }
                break;
            }
            case 1001: {
                if (a != null) {
                    final String d = a.d();
                    if ((super.f.a(43) && event.target == super.c) || event.target == this.a) {
                        super.f.a((bv)null, a);
                        return true;
                    }
                    if (event.target == this.i) {
                        final aJ aj = new aJ(67074, 1);
                        aj.a(0, 0, super.f.e());
                        aj.j = a.e();
                        super.f.q(aj);
                        return true;
                    }
                    if (event.target == this.l) {
                        a.h = !a.h;
                        a.j = false;
                        if (a.h) {
                            if (!super.f.e.contains(d)) {
                                super.f.e.addElement(d);
                            }
                        }
                        else {
                            super.f.e.removeElement(d);
                        }
                        this.postEvent(new Event(this.l, 7689, this.a((Object)this.l)));
                        this.a(a);
                        return true;
                    }
                    if (event.target == this.n && !a.a(33)) {
                        final aJ aj2 = new aJ(264448, 1);
                        aj2.j = a.e();
                        super.f.q(aj2);
                        this.k.addElement(String.valueOf(a.e()));
                        this.b(this.n);
                        return true;
                    }
                    if (event.target == this.m) {
                        final b b = new b(a.e(), a.d());
                        b.a = true;
                        b.t = a.t;
                        b.a(a.a());
                        if (super.f.a != null) {
                            ((e)super.f.a).a(b, true, false);
                        }
                        super.f.a(super.f.P);
                    }
                    else {
                        if (event.target == this.b) {
                            a.j = !a.j;
                            a.h = false;
                            super.f.e.removeElement(d);
                            if (a.j) {
                                if (!super.f.f.contains(d)) {
                                    super.f.f.addElement(d);
                                }
                            }
                            else {
                                super.f.f.removeElement(d);
                            }
                            this.postEvent(new Event(this.b, 7689, this.a((Object)this.b)));
                            this.a(a);
                            return true;
                        }
                        if (event.target == this.c) {
                            new aG(super.f, a);
                        }
                    }
                }
                if (event.target == this.e) {
                    super.f.b(this.b());
                    break;
                }
                break;
            }
            case 701: {
                final F f = (F)event.arg;
                this.c(this.b);
                this.c(this.a);
                this.c(this.i);
                if (!this.k.contains(String.valueOf(f.e())) && !f.a(33)) {
                    this.c(this.n);
                }
                else {
                    this.b(this.n);
                }
                if (!f.d().equals(super.f.d())) {
                    this.c(this.m);
                }
                else {
                    this.b(this.m);
                }
                if (f.a(33)) {
                    this.b(this.l);
                }
                else {
                    this.c(this.l);
                }
                if (f.a(34) || a.e() == super.f.e()) {
                    if (this.c != null) {
                        this.b(this.c);
                    }
                }
                else if (super.f.a(44) && this.c != null) {
                    this.c(this.c);
                }
                return true;
            }
            case 702: {
                this.b(this.b);
                this.b(this.a);
                this.b(this.i);
                this.b(this.l);
                this.b(this.m);
                this.b(this.n);
                if (this.c != null) {
                    this.b(this.c);
                }
                return true;
            }
            default: {
                if (a == null) {
                    break;
                }
                if (a.a(33)) {
                    this.b(this.l);
                }
                else {
                    this.c(this.l);
                }
                if (a.a(34) || a.e() == super.f.e()) {
                    if (this.c != null) {
                        this.b(this.c);
                        break;
                    }
                    break;
                }
                else {
                    if (a.a(44) && this.c != null) {
                        this.c(this.c);
                        break;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bh(final at c) {
        this.l = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.m = null;
        this.i = null;
        this.e = null;
        bh.h = null;
        super.f = c;
        this.i = c;
        this.n = null;
        this.k = new Vector();
        this.setBackground(c.b.g);
        this.setForeground(c.b.f);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String e = c.b.e();
        this.l = this.a(e, "mute", "muteUserIcon.GIF");
        if (c.a(43)) {
            this.a = this.a(e, "private", "whisperIcon.GIF");
        }
        this.b = this.a(e, "flag", "flagUserIcon.GIF");
        if (c.a(44)) {
            this.c = this.a(e, "kick", "kickUserIcon.GIF");
        }
        this.n = this.a(e, "report", "reportUserIcon.GIF");
        if (c.i()) {
            this.m = this.a(e, "addbuddy", "addBuddyIcon.GIF");
        }
        this.i = this.a(e, "profile", "userInfoIcon.GIF");
        if (c.b.f() && c.f()) {
            final Image a = c.a(e + "allusers_unchecked.gif", true);
            final Image a2 = c.a(e + "allusers_checked.gif", true);
            if (a != null && a2 != null) {
                this.e = new n(a, a2);
            }
        }
        if (this.e == null) {
            this.e = new Checkbox(ar.b("Show users in all rooms"));
        }
        bh.h = new TextField(20);
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.i, gridBagConstraints);
        this.add(this.i);
        gridBagLayout.setConstraints(this.n, gridBagConstraints);
        this.add(this.n);
        if (c.a(43)) {
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        if (!c.i() && !c.a(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.l, gridBagConstraints);
            this.add(this.l);
        }
        else {
            gridBagLayout.setConstraints(this.l, gridBagConstraints);
            this.add(this.l);
        }
        if (c.i() && !c.a(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.m, gridBagConstraints);
            this.add(this.m);
        }
        else if (c.i()) {
            gridBagLayout.setConstraints(this.m, gridBagConstraints);
            this.add(this.m);
        }
        if (c.a(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            this.add(this.c);
        }
        if (c.f() && this.e instanceof n) {
            final Panel panel = new Panel();
            panel.setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            panel.add(this.e);
            final Label label = new Label(ar.b("Show users in all rooms"));
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagLayout.setConstraints(label, gridBagConstraints);
            panel.add(label);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(panel, gridBagConstraints);
            this.add(panel);
        }
        else if (c.f()) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        bh.h.setForeground(Color.blue);
        bh.h.setBackground(Color.gray);
        gridBagLayout.setConstraints(bh.h, gridBagConstraints);
        this.add(bh.h);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final aw aw = new aw(super.c);
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        this.add(aw);
        final p p = new p(null, "icon");
        final p p2 = new p(ar.b("Nickname"), "name");
        p.c(28);
        p.b(0);
        p2.b(true);
        super.c.a(true);
        super.c.b(p);
        super.c.b(p2);
        super.c.a(p2);
        super.c.h(26);
        super.c.c = c;
    }
}
