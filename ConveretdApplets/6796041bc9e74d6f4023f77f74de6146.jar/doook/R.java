// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Label;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.Image;
import java.awt.Checkbox;
import java.util.Vector;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Canvas;

public class R extends Q implements aB
{
    private Canvas d;
    private Canvas e;
    private Canvas f;
    private Canvas g;
    private Canvas h;
    private Canvas a;
    private Component c;
    private Canvas i;
    private Canvas j;
    private Component d;
    public static TextField i;
    private u i;
    private Vector f;
    
    public boolean a() {
        if (this.c instanceof Checkbox) {
            return ((Checkbox)this.c).getState();
        }
        return ((cm)this.c).a();
    }
    
    public void a(final boolean state) {
        if (this.c instanceof Checkbox) {
            ((Checkbox)this.c).setState(state);
        }
        else {
            ((cm)this.c).a(state);
        }
    }
    
    public boolean j() {
        if (this.d == null) {
            return false;
        }
        if (this.d instanceof Checkbox) {
            return ((Checkbox)this.d).getState();
        }
        return ((cm)this.d).a();
    }
    
    private final void b(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof aS) {
            ((aS)canvas).d();
        }
        else {
            ((cz)canvas).setEnabled(false);
        }
    }
    
    private final void c(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof aS) {
            ((aS)canvas).c();
        }
        else {
            ((cz)canvas).setEnabled(true);
        }
    }
    
    public boolean a(final Canvas canvas) {
        if (canvas == null) {
            return false;
        }
        if (canvas instanceof aS) {
            return ((aS)canvas).a();
        }
        return ((cz)canvas).isEnabled();
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        aB a;
        if (!super.d.a.p() || s == null) {
            a = new aS(28, 28);
            ((aS)a).a(super.d.a(s3, false, 20));
        }
        else {
            final Image a2 = super.d.a(s + s2 + "_button_up.gif", true);
            final Image a3 = super.d.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = super.d.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new aS(28, 28);
                ((aS)a).a(super.d.a(s3, false, 20));
            }
            else {
                a = cz.a(a2, a3, a4);
            }
        }
        return (aS)a;
    }
    
    public String a(final Object o) {
        if (o == R.i) {
            return ao.e("Type text here to filter users list.");
        }
        if (o instanceof aS || o instanceof cz) {
            final cG cg = (cG)super.b.a();
            if (cg == null) {
                return ao.e("This button is disabled because no user is selected.");
            }
            if (o == this.e) {
                return am.a(ao.e("Click here to enter a private conversation with %1."), new String[] { cg.f() });
            }
            if (o == this.i) {
                if (!this.f.contains(String.valueOf(cg.h()))) {
                    return am.a(ao.e("Click here to report %1."), new String[] { cg.f() });
                }
                return am.a(ao.e("You already reported %1."), new String[] { cg.f() });
            }
            else if (o == this.h) {
                if (!cg.f().equals(super.d.f())) {
                    return am.a(ao.e("Click here to add %1 to your Buddy List."), new String[] { cg.f() });
                }
                return am.a(ao.e("This button is disabled because you can not add yourself to your Buddy List."), new String[] { cg.f() });
            }
            else {
                if (o == this.f) {
                    return cg.b ? am.a(ao.e("Click here to stop flagging messages from %1."), new String[] { cg.f() }) : am.a(ao.e("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { cg.f() });
                }
                if (o == this.a) {
                    return am.a(ao.e("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { cg.f() });
                }
                if (o == this.d) {
                    if (this.a(this.d)) {
                        return cg.h ? am.a(ao.e("Click here to stop ignoring messages from %1."), new String[] { cg.f() }) : am.a(ao.e("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { cg.f() });
                    }
                    if (cg.d(33)) {
                        return am.a(ao.e("This button is disabled because %1 cannot be ignored."), new String[] { cg.f() });
                    }
                }
                if (o == this.g) {
                    return am.a(ao.e("Click here to kick %1."), new String[] { cg.f() });
                }
            }
        }
        return null;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 402 && event.modifiers == 2 && t.a(event.key, this.i)) {
            return true;
        }
        final ab ab = (ab)super.b.a();
        switch (event.id) {
            case 402: {
                if (event.target == R.i) {
                    super.d.c(this.a());
                    break;
                }
                break;
            }
            case 1001: {
                if (ab != null) {
                    final String f = ab.f();
                    if ((super.d.d(43) && event.target == super.b) || event.target == this.e) {
                        super.d.a((Z)null, (cG)ab);
                        return true;
                    }
                    if (event.target == this.a) {
                        final cD cd = new cD(67074, 1);
                        cd.a(0, 0, super.d.h());
                        cd.j = ab.h();
                        super.d.o(cd);
                        return true;
                    }
                    if (event.target == this.d) {
                        ab.h = !ab.h;
                        ab.b = false;
                        if (ab.h) {
                            if (!super.d.c.contains(f)) {
                                super.d.c.addElement(f);
                            }
                        }
                        else {
                            super.d.c.removeElement(f);
                        }
                        this.postEvent(new Event(this.d, 7689, this.a(this.d) ? "True" : "False"));
                        this.a(ab);
                        return true;
                    }
                    if (event.target == this.i) {
                        final cD cd2 = new cD(264448, 1);
                        cd2.j = ab.h();
                        super.d.o(cd2);
                        this.f.addElement(String.valueOf(ab.h()));
                        this.b(this.i);
                        return true;
                    }
                    if (event.target == this.h) {
                        final ac ac = new ac(ab.h(), ab.f());
                        ac.a = true;
                        ac.h = ab.h;
                        ac.a(ab.d());
                        if (super.d.a != null) {
                            ((au)super.d.a).a(ac, true, false);
                        }
                        super.d.a(super.d.B);
                    }
                    else {
                        if (event.target == this.f) {
                            ab.b = !ab.b;
                            ab.h = false;
                            super.d.c.removeElement(f);
                            if (ab.b) {
                                if (!super.d.d.contains(f)) {
                                    super.d.d.addElement(f);
                                }
                            }
                            else {
                                super.d.d.removeElement(f);
                            }
                            this.postEvent(new Event(this.f, 7689, this.a(this.f) ? "True" : "False"));
                            this.a(ab);
                            return true;
                        }
                        if (event.target == this.g) {
                            new ch(super.d, ab);
                        }
                    }
                }
                if (event.target == this.c || event.target == this.d) {
                    this.i.av = this.j();
                }
                super.d.c(this.a());
                break;
            }
            case 701: {
                final cG cg = (cG)event.arg;
                this.c(this.f);
                this.c(this.e);
                this.c(this.a);
                if (!this.f.contains(String.valueOf(cg.h()))) {
                    this.c(this.i);
                }
                else {
                    this.b(this.i);
                }
                if (!cg.f().equals(super.d.f())) {
                    this.c(this.h);
                }
                else {
                    this.b(this.h);
                }
                if (cg.d(33)) {
                    this.b(this.d);
                }
                else {
                    this.c(this.d);
                }
                if (ab.h() == super.d.h() || (cg.d(34) && !super.d.d(52))) {
                    if (this.g != null) {
                        this.b(this.g);
                    }
                }
                else if (super.d.d(44) && this.g != null) {
                    this.c(this.g);
                }
                return true;
            }
            case 702: {
                this.b(this.f);
                this.b(this.e);
                this.b(this.a);
                this.b(this.d);
                this.b(this.h);
                this.b(this.i);
                if (this.g != null) {
                    this.b(this.g);
                }
                return true;
            }
            default: {
                if (ab == null) {
                    break;
                }
                if (ab.d(33)) {
                    this.b(this.d);
                }
                else {
                    this.c(this.d);
                }
                if (ab.h() == super.d.h() || (ab.d(34) && !super.d.d(52))) {
                    if (this.g != null) {
                        this.b(this.g);
                        break;
                    }
                    break;
                }
                else {
                    if (ab.d(44) && this.g != null) {
                        this.c(this.g);
                        break;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public R(final u a) {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.a = null;
        this.c = null;
        this.i = null;
        this.d = null;
        this.f = new Vector();
        R.i = null;
        this.j = null;
        super.d = a;
        this.i = a;
        this.setBackground(a.a.g);
        this.setForeground(a.a.f);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final String e = a.a.e();
        this.d = this.a(e, "mute", "muteUserIcon.GIF");
        if (a.d(43)) {
            this.e = this.a(e, "private", "whisperIcon.GIF");
        }
        this.f = this.a(e, "flag", "flagUserIcon.GIF");
        if (a.d(44)) {
            this.g = this.a(e, "kick", "kickUserIcon.GIF");
        }
        this.i = this.a(e, "report", "reportUserIcon.GIF");
        if (a.h()) {
            this.h = this.a(e, "addbuddy", "addBuddyIcon.GIF");
        }
        this.a = this.a(e, "profile", "userInfoIcon.GIF");
        if (a.a.p() && a.e()) {
            final Image a2 = a.a(e + "allusers_unchecked.gif", true);
            final Image a3 = a.a(e + "allusers_checked.gif", true);
            if (a2 != null && a3 != null) {
                this.c = new cm(a2, a3);
            }
        }
        if (this.c == null) {
            this.c = new Checkbox(ao.e("Show users in all rooms"));
        }
        if (a.a.p() && a.d(60)) {
            final Image a4 = a.a(e + "allusers_unchecked.gif", true);
            final Image a5 = a.a(e + "allusers_checked.gif", true);
            if (a4 != null && a5 != null) {
                this.d = new cm(a4, a5);
            }
        }
        if (a.d(60) && this.d == null) {
            this.d = new Checkbox(ao.e("Show reported users"));
        }
        (R.i = new TextField(17)).setForeground(Color.blue);
        R.i.setBackground(Color.gray);
        this.j = this.a(e, "search", "searchIcon.gif");
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        layout.setConstraints(this.i, gridBagConstraints);
        this.add(this.i);
        if (a.d(43)) {
            layout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        layout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        if (!a.h() && !a.d(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.d, gridBagConstraints);
            this.add(this.d);
        }
        else {
            layout.setConstraints(this.d, gridBagConstraints);
            this.add(this.d);
        }
        if (a.h() && !a.d(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.h, gridBagConstraints);
            this.add(this.h);
        }
        else if (a.h()) {
            layout.setConstraints(this.h, gridBagConstraints);
            this.add(this.h);
        }
        if (a.d(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.g, gridBagConstraints);
            this.add(this.g);
        }
        if (a.e() && this.c instanceof cm) {
            final Panel panel = new Panel();
            panel.setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.c, gridBagConstraints);
            panel.add(this.c);
            final Label label = new Label(ao.e("Show users in all rooms"));
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            layout.setConstraints(label, gridBagConstraints);
            panel.add(label);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(panel, gridBagConstraints);
            this.add(panel);
        }
        else if (a.e()) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            layout.setConstraints(this.c, gridBagConstraints);
            this.add(this.c);
        }
        if (a.d(60) && this.d instanceof cm) {
            final Panel panel2 = new Panel();
            panel2.setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.d, gridBagConstraints);
            panel2.add(this.d);
            final Label label2 = new Label(ao.e("Show reported users"));
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            layout.setConstraints(label2, gridBagConstraints);
            panel2.add(label2);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(panel2, gridBagConstraints);
            this.add(panel2);
        }
        else if (a.d(60)) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            layout.setConstraints(this.d, gridBagConstraints);
            this.add(this.d);
        }
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(R.i, gridBagConstraints);
        this.add(R.i);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final aR ar = new aR(super.b);
        layout.setConstraints(ar, gridBagConstraints);
        this.add(ar);
        final j j = new j(null, "icon");
        final j i = new j(ao.e("Nickname"), "name");
        j.d(28);
        j.c(0);
        i.c(true);
        super.b.a(true);
        super.b.b(j);
        super.b.b(i);
        super.b.a(i);
        super.b.i(26);
        super.b.a = a;
    }
}
