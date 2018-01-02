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

public class x extends K implements aj
{
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Canvas d;
    private Canvas e;
    private Canvas f;
    private Component b;
    public static TextField a;
    private Canvas g;
    private aW e;
    private Vector d;
    
    public boolean a() {
        if (this.b instanceof Checkbox) {
            return ((Checkbox)this.b).getState();
        }
        return ((W)this.b).a();
    }
    
    public void a(final boolean state) {
        if (this.b instanceof Checkbox) {
            ((Checkbox)this.b).setState(state);
        }
        else {
            ((W)this.b).a(state);
        }
    }
    
    private final void a(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof al) {
            ((al)canvas).g();
        }
        else {
            ((o)canvas).setEnabled(false);
        }
    }
    
    private final void b(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof al) {
            ((al)canvas).b();
        }
        else {
            ((o)canvas).setEnabled(true);
        }
    }
    
    public boolean a(final Canvas canvas) {
        if (canvas == null) {
            return false;
        }
        if (canvas instanceof al) {
            return ((al)canvas).c();
        }
        return ((o)canvas).isEnabled();
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        aj a;
        if (!super.f.c.h() || s == null) {
            a = new al(28, 28);
            ((al)a).a(super.f.a(s3, false, 20));
        }
        else {
            final Image a2 = super.f.a(s + s2 + "_button_up.gif", true);
            final Image a3 = super.f.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = super.f.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new al(28, 28);
                ((al)a).a(super.f.a(s3, false, 20));
            }
            else {
                a = o.a(a2, a3, a4);
            }
        }
        return (al)a;
    }
    
    public String a(final Object o) {
        if (o == x.a) {
            return aG.a("Type text here to filter users list.");
        }
        if (o instanceof al || o instanceof o) {
            final aI ai = (aI)super.b.a();
            if (ai == null) {
                return aG.a("This button is disabled because no user is selected.");
            }
            if (o == this.b) {
                return aC.a(aG.a("Click here to enter a private conversation with %1."), new String[] { ai.g() });
            }
            if (o == this.g) {
                if (!this.d.contains(String.valueOf(ai.b()))) {
                    return aC.a(aG.a("Click here to report %1."), new String[] { ai.g() });
                }
                return aC.a(aG.a("You already reported %1."), new String[] { ai.g() });
            }
            else if (o == this.e) {
                if (!ai.g().equals(super.f.g())) {
                    return aC.a(aG.a("Click here to add %1 to your Buddy List."), new String[] { ai.g() });
                }
                return aC.a(aG.a("This button is disabled because you can not add yourself to your Buddy List."), new String[] { ai.g() });
            }
            else {
                if (o == this.c) {
                    return ai.u ? aC.a(aG.a("Click here to stop flagging messages from %1."), new String[] { ai.g() }) : aC.a(aG.a("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { ai.g() });
                }
                if (o == this.f) {
                    return aC.a(aG.a("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { ai.g() });
                }
                if (o == this.a) {
                    if (this.a(this.a)) {
                        return ai.v ? aC.a(aG.a("Click here to stop ignoring messages from %1."), new String[] { ai.g() }) : aC.a(aG.a("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { ai.g() });
                    }
                    if (ai.c(33)) {
                        return aC.a(aG.a("This button is disabled because %1 cannot be ignored."), new String[] { ai.g() });
                    }
                }
                if (o == this.d) {
                    return aC.a(aG.a("Click here to kick %1."), new String[] { ai.g() });
                }
            }
        }
        return null;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 402 && event.modifiers == 2 && event.key == 4 && be.a != null) {
            be.a(super.f);
            return true;
        }
        final aq aq = (aq)super.b.a();
        switch (event.id) {
            case 402: {
                if (event.target == x.a) {
                    super.f.a(this.a());
                    break;
                }
                break;
            }
            case 1001: {
                if (aq != null) {
                    final String g = aq.g();
                    if ((super.f.c(43) && event.target == super.b) || event.target == this.b) {
                        super.f.a((aN)null, aq);
                        return true;
                    }
                    if (event.target == this.f) {
                        final V v = new V(67074, 1);
                        v.a(0, 0, super.f.b());
                        v.j = aq.b();
                        super.f.F(v);
                        return true;
                    }
                    if (event.target == this.a) {
                        aq.v = !aq.v;
                        aq.u = false;
                        if (aq.v) {
                            if (!super.f.i.contains(g)) {
                                super.f.i.addElement(g);
                            }
                        }
                        else {
                            super.f.i.removeElement(g);
                        }
                        this.postEvent(new Event(this.a, 7689, this.a((Object)this.a)));
                        this.a(aq);
                        return true;
                    }
                    if (event.target == this.g && !aq.c(33)) {
                        final V v2 = new V(264448, 1);
                        v2.j = aq.b();
                        super.f.F(v2);
                        this.d.addElement(String.valueOf(aq.b()));
                        this.a(this.g);
                        return true;
                    }
                    if (event.target == this.e) {
                        final at at = new at(aq.b(), aq.g());
                        at.h = true;
                        at.w = aq.w;
                        at.a(aq.d());
                        if (super.f.a != null) {
                            ((aV)super.f.a).a(at, true, false);
                        }
                        super.f.e(super.f.N);
                    }
                    else {
                        if (event.target == this.c) {
                            aq.u = !aq.u;
                            aq.v = false;
                            super.f.i.removeElement(g);
                            if (aq.u) {
                                if (!super.f.j.contains(g)) {
                                    super.f.j.addElement(g);
                                }
                            }
                            else {
                                super.f.j.removeElement(g);
                            }
                            this.postEvent(new Event(this.c, 7689, this.a((Object)this.c)));
                            this.a(aq);
                            return true;
                        }
                        if (event.target == this.d) {
                            new ay(super.f, aq);
                        }
                    }
                }
                if (event.target == this.b) {
                    super.f.a(this.a());
                    break;
                }
                break;
            }
            case 701: {
                final aI ai = (aI)event.arg;
                this.b(this.c);
                this.b(this.b);
                this.b(this.f);
                if (!this.d.contains(String.valueOf(ai.b())) && !ai.c(33)) {
                    this.b(this.g);
                }
                else {
                    this.a(this.g);
                }
                if (!ai.g().equals(super.f.g())) {
                    this.b(this.e);
                }
                else {
                    this.a(this.e);
                }
                if (ai.c(33)) {
                    this.a(this.a);
                }
                else {
                    this.b(this.a);
                }
                if (ai.c(34) || aq.b() == super.f.b()) {
                    if (this.d != null) {
                        this.a(this.d);
                    }
                }
                else if (super.f.c(44) && this.d != null) {
                    this.b(this.d);
                }
                return true;
            }
            case 702: {
                this.a(this.c);
                this.a(this.b);
                this.a(this.f);
                this.a(this.a);
                this.a(this.e);
                this.a(this.g);
                if (this.d != null) {
                    this.a(this.d);
                }
                return true;
            }
            default: {
                if (aq == null) {
                    break;
                }
                if (aq.c(33)) {
                    this.a(this.a);
                }
                else {
                    this.b(this.a);
                }
                if (aq.c(34) || aq.b() == super.f.b()) {
                    if (this.d != null) {
                        this.a(this.d);
                        break;
                    }
                    break;
                }
                else {
                    if (aq.c(44) && this.d != null) {
                        this.b(this.d);
                        break;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public x(final aW g) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.b = null;
        x.a = null;
        super.f = g;
        this.e = g;
        this.g = null;
        this.d = new Vector();
        this.setBackground(g.c.k);
        this.setForeground(g.c.j);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String f = g.c.f();
        this.a = this.a(f, "mute", "muteUserIcon.GIF");
        if (g.c(43)) {
            this.b = this.a(f, "private", "whisperIcon.GIF");
        }
        this.c = this.a(f, "flag", "flagUserIcon.GIF");
        if (g.c(44)) {
            this.d = this.a(f, "kick", "kickUserIcon.GIF");
        }
        this.g = this.a(f, "report", "reportUserIcon.GIF");
        if (g.i()) {
            this.e = this.a(f, "addbuddy", "addBuddyIcon.GIF");
        }
        this.f = this.a(f, "profile", "userInfoIcon.GIF");
        if (g.c.h() && g.k()) {
            final Image a = g.a(f + "allusers_unchecked.gif", true);
            final Image a2 = g.a(f + "allusers_checked.gif", true);
            if (a != null && a2 != null) {
                this.b = new W(a, a2);
            }
        }
        if (this.b == null) {
            this.b = new Checkbox(aG.a("Show users in all rooms"));
        }
        x.a = new TextField(20);
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        gridBagLayout.setConstraints(this.g, gridBagConstraints);
        this.add(this.g);
        if (g.c(43)) {
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        if (!g.i() && !g.c(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        else {
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        if (g.i() && !g.c(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        else if (g.i()) {
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        if (g.c(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            this.add(this.d);
        }
        if (g.k() && this.b instanceof W) {
            final Panel panel = new Panel();
            panel.setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            panel.add(this.b);
            final Label label = new Label(aG.a("Show users in all rooms"));
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
        else if (g.k()) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        x.a.setForeground(Color.blue);
        x.a.setBackground(Color.gray);
        gridBagLayout.setConstraints(x.a, gridBagConstraints);
        this.add(x.a);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final aX ax = new aX(super.b);
        gridBagLayout.setConstraints(ax, gridBagConstraints);
        this.add(ax);
        final aB ab = new aB(null, "icon");
        final aB ab2 = new aB(aG.a("Nickname"), "name");
        ab.e(28);
        ab.c(0);
        ab2.b(true);
        super.b.a(true);
        super.b.b(ab);
        super.b.b(ab2);
        super.b.a(ab2);
        super.b.j(26);
        super.b.g = g;
    }
}
