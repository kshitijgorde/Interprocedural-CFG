// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Enumeration;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.Image;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Panel;

public class an extends Panel implements aj
{
    public static final Color a;
    public static final Color b;
    public static final Color c;
    public static final Color d;
    private am a;
    private Canvas m;
    private Canvas n;
    private Canvas o;
    private aW d;
    private Hashtable c;
    private Component f;
    
    private final void c(final Canvas canvas) {
        if (canvas instanceof al) {
            ((al)canvas).g();
        }
        else {
            ((o)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private final void a(final Canvas canvas) {
        if (canvas instanceof al) {
            ((al)canvas).b();
        }
        else {
            ((o)canvas).setEnabled(true);
        }
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        aj a;
        if (!this.d.c.h() || s == null) {
            a = new al(28, 28);
            ((al)a).a(this.d.a(s3, false, 20));
        }
        else {
            final Image a2 = this.d.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.d.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = this.d.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new al(28, 28);
                ((al)a).a(this.d.a(s3, false, 20));
            }
            else {
                a = doook.o.a(a2, a3, a4);
            }
        }
        return (Canvas)a;
    }
    
    public String a(final Object o) {
        if (o instanceof al || o instanceof o) {
            final at at = (at)this.a.a();
            if (o == this.o) {
                return aG.a("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (at == null) {
                return aG.a("This button is disabled because no buddy is selected.");
            }
            if (o == this.n) {
                return aC.a(aG.a("Click here to remove %1 from your Buddy List."), new String[] { at.g() });
            }
            if (o == this.m && at.h) {
                return aC.a(aG.a("Click here to enter a private conversation with %1."), new String[] { at.g() });
            }
            if (o == this.m && !at.h) {
                return aC.a(aG.a("%1 is not available for private messages now."), new String[] { at.g() });
            }
        }
        return null;
    }
    
    private final boolean d() {
        if (this.f instanceof Checkbox) {
            return ((Checkbox)this.f).getState();
        }
        return ((W)this.f).a();
    }
    
    public void show() {
        super.show();
        this.a.requestFocus();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final at at = (at)this.a.a();
                if (((this.d.c(43) && event.target == this.a) || event.target == this.m) && at != null && at.h) {
                    this.d.a((aN)null, at);
                    return true;
                }
                if (event.target == this.n && at != null) {
                    this.a(at);
                    return true;
                }
                if (event.target == this.f) {
                    this.a(this.d());
                }
                if (event.target == this.o) {
                    new ak(this.d.a.a(), this.d).setVisible(true);
                }
                break;
            }
            case 701: {
                this.a(this.n);
                if (((at)this.a.a()).h) {
                    this.a(this.m);
                }
                else {
                    this.c(this.m);
                }
                return true;
            }
            case 702: {
                this.c(this.n);
                this.c(this.m);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean a(final at at) {
        this.c.remove(at.g());
        final V v = new V(33621774, 1);
        v.a(0, 0, this.d.g());
        v.a(0, 1, at.g());
        v.a(0, 2, "remove");
        this.d.F(v);
        this.d.C = this.e();
        return this.a.a((bk)at);
    }
    
    public void a(final at at, final boolean b, final boolean b2) {
        if (this.d.g().equals(at.g())) {
            return;
        }
        bn bn = null;
        if (at.g() != null) {
            bn = this.c.get(at.g());
        }
        if (bn == null && b) {
            this.c.put(at.g(), at);
            if (!b2) {
                final V v = new V(33621774, 1);
                v.a(0, 0, this.d.g());
                v.a(0, 1, at.g());
                v.a(0, 2, "add");
                this.d.F(v);
            }
            if (at.h || !this.d()) {
                this.a.a((bk)at);
                this.a(at);
            }
            this.d.C = this.e();
        }
        else if (bn != null) {
            this.c.remove(bn.g());
            this.c.put(at.g(), at);
            this.a.a((bk)bn);
            if (at.h || !this.d()) {
                this.a.a((bk)at);
            }
            this.a(at);
        }
    }
    
    private final void a(final at at) {
        if (at.u) {
            this.a.a(at, Color.red, Color.pink);
        }
        else if (at.c(59)) {
            this.a.a(at, an.c, an.d);
        }
        else if (at.c(61) || at.c(62)) {
            this.a.a(at, an.a, an.b);
        }
        else {
            this.a.a(at, Color.black, Color.white);
        }
        if (at.h) {
            this.a.a(at, false);
        }
        else {
            this.a.a(at, true);
        }
        this.a.a();
    }
    
    protected void c() {
        final String c = this.d.C;
        if (c != null && !c.equals("")) {
            final int n = 44;
            int n2 = 0;
            for (int i = c.indexOf(n, n2); i != -1; i = c.indexOf(n, n2)) {
                final at at = new at(-999, c.substring(n2, i).trim());
                at.h = false;
                this.a(at, true, true);
                n2 = i + 1;
            }
            final at at2 = new at(-999, c.substring(n2, c.length()).trim());
            at2.h = false;
            this.a(at2, true, true);
        }
    }
    
    public String e() {
        String s = "";
        final Enumeration<at> elements = this.c.elements();
        if (elements.hasMoreElements()) {
            s = elements.nextElement().g();
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().g();
        }
        return s;
    }
    
    public void a(final boolean b) {
        final Enumeration<at> elements = this.c.elements();
        while (elements.hasMoreElements()) {
            final at at = elements.nextElement();
            if (b) {
                if (at.h) {
                    continue;
                }
                this.a.a((bk)at);
            }
            else {
                if (this.a.a((bk)at) != -1) {
                    continue;
                }
                this.a.a((bk)at);
                this.a(at);
            }
        }
    }
    
    public an(final aW d) {
        this.a = new am();
        this.f = new Checkbox(aG.a("Show only online buddies"));
        this.d = d;
        this.c = new Hashtable(30);
        this.setBackground(d.c.k);
        this.setForeground(d.c.j);
        final String f = d.c.f();
        this.o = this.a(f, "addbuddy", "addBuddyIcon.GIF");
        this.n = this.a(f, "removebuddy", "removeBuddyIcon.GIF");
        this.m = this.a(f, "private", "whisperIcon.GIF");
        if (d.c.h()) {
            final Image a = d.a(f + "buddies_checked.gif", true);
            final Image a2 = d.a(f + "buddies_unchecked.gif", true);
            if (a != null && a2 != null) {
                this.f = new W(a, a2);
            }
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.o, gridBagConstraints);
        this.add(this.o);
        if (!d.c(43)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.n, gridBagConstraints);
            this.add(this.n);
        }
        else {
            gridBagLayout.setConstraints(this.n, gridBagConstraints);
            this.add(this.n);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.m, gridBagConstraints);
            this.add(this.m);
        }
        if (this.f instanceof W) {
            final Panel panel = new Panel();
            panel.setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.f, gridBagConstraints);
            panel.add(this.f);
            final Label label = new Label(aG.a("Show only online buddies"));
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
        else {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.f, gridBagConstraints);
            ((Checkbox)this.f).setState(true);
            this.add(this.f);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final aX ax = new aX(this.a);
        gridBagLayout.setConstraints(ax, gridBagConstraints);
        this.add(ax);
        final aB ab = new aB(null, "icon");
        final aB ab2 = new aB(aG.a("Nickname"), "name");
        ab.e(28);
        ab.c(0);
        ab2.b(true);
        this.a.a(true);
        this.a.b(ab);
        this.a.b(ab2);
        this.a.a(ab2);
        this.a.j(26);
        this.c();
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
        c = new Color(16711680);
        d = new Color(10079487);
    }
}
