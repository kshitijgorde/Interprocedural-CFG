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

public class bc extends Panel implements aO
{
    public static final Color c;
    public static final Color i;
    public static final Color a;
    public static final Color b;
    private u e;
    private Canvas i;
    private Canvas j;
    private Canvas k;
    private at k;
    private Hashtable g;
    private Component d;
    
    private final void a(final Canvas canvas) {
        if (canvas instanceof ax) {
            ((ax)canvas).g();
        }
        else {
            ((aB)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private final void b(final Canvas canvas) {
        if (canvas instanceof ax) {
            ((ax)canvas).c();
        }
        else {
            ((aB)canvas).setEnabled(true);
        }
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        aO a;
        if (!this.k.b.f() || s == null) {
            a = new ax(28, 28);
            ((ax)a).a(this.k.a(s3, false, 20));
        }
        else {
            final Image a2 = this.k.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.k.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = this.k.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new ax(28, 28);
                ((ax)a).a(this.k.a(s3, false, 20));
            }
            else {
                a = aB.a(a2, a3, a4);
            }
        }
        return (Canvas)a;
    }
    
    public String a(final Object o) {
        if (o instanceof ax || o instanceof aB) {
            final b b = (b)this.e.a();
            if (o == this.k) {
                return ar.b("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (b == null) {
                return ar.b("This button is disabled because no buddy is selected.");
            }
            if (o == this.j) {
                return H.a(ar.b("Click here to remove %1 from your Buddy List."), new String[] { b.d() });
            }
            if (o == this.i && b.a) {
                return H.a(ar.b("Click here to enter a private conversation with %1."), new String[] { b.d() });
            }
            if (o == this.i && !b.a) {
                return H.a(ar.b("%1 is not available for private messages now."), new String[] { b.d() });
            }
        }
        return null;
    }
    
    private final boolean c() {
        if (this.d instanceof Checkbox) {
            return ((Checkbox)this.d).getState();
        }
        return ((n)this.d).b();
    }
    
    public void show() {
        super.show();
        this.e.requestFocus();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final b b = (b)this.e.a();
                if (((this.k.a(43) && event.target == this.e) || event.target == this.i) && b != null && b.a) {
                    this.k.a((bv)null, b);
                    return true;
                }
                if (event.target == this.j && b != null) {
                    this.a(b);
                    return true;
                }
                if (event.target == this.d) {
                    this.a(this.c());
                }
                if (event.target == this.k) {
                    new aM(this.k.a.a(), this.k).setVisible(true);
                }
                break;
            }
            case 701: {
                this.b(this.j);
                if (((b)this.e.a()).a) {
                    this.b(this.i);
                }
                else {
                    this.a(this.i);
                }
                return true;
            }
            case 702: {
                this.a(this.j);
                this.a(this.i);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean a(final b b) {
        this.g.remove(b.d());
        final aJ aj = new aJ(33621774, 1);
        aj.a(0, 0, this.k.d());
        aj.a(0, 1, b.d());
        aj.a(0, 2, "remove");
        this.k.q(aj);
        this.k.u = this.f();
        return this.e.a((az)b);
    }
    
    public void a(final b b, final boolean b2, final boolean b3) {
        if (this.k.d().equals(b.d())) {
            return;
        }
        D d = null;
        if (b.d() != null) {
            d = this.g.get(b.d());
        }
        if (d == null && b2) {
            this.g.put(b.d(), b);
            if (!b3) {
                final aJ aj = new aJ(33621774, 1);
                aj.a(0, 0, this.k.d());
                aj.a(0, 1, b.d());
                aj.a(0, 2, "add");
                this.k.q(aj);
            }
            if (b.a || !this.c()) {
                this.e.a((az)b);
                this.a(b);
            }
            this.k.u = this.f();
        }
        else if (d != null) {
            this.g.remove(d.d());
            this.g.put(b.d(), b);
            this.e.a((az)d);
            if (b.a || !this.c()) {
                this.e.a((az)b);
            }
            this.a(b);
        }
    }
    
    private final void a(final b b) {
        if (b.j) {
            this.e.a(b, Color.red, Color.pink);
        }
        else if (b.a(59)) {
            this.e.a(b, bc.a, bc.b);
        }
        else if (b.a(61) || b.a(62)) {
            this.e.a(b, bc.c, bc.i);
        }
        else {
            this.e.a(b, Color.black, Color.white);
        }
        if (b.a) {
            this.e.a(b, false);
        }
        else {
            this.e.a(b, true);
        }
        this.e.c();
    }
    
    protected void f() {
        final String u = this.k.u;
        if (u != null && !u.equals("")) {
            final int n = 44;
            int n2 = 0;
            for (int i = u.indexOf(n, n2); i != -1; i = u.indexOf(n, n2)) {
                final b b = new b(-999, u.substring(n2, i).trim());
                b.a = false;
                this.a(b, true, true);
                n2 = i + 1;
            }
            final b b2 = new b(-999, u.substring(n2, u.length()).trim());
            b2.a = false;
            this.a(b2, true, true);
        }
    }
    
    public String f() {
        String s = "";
        final Enumeration<b> elements = this.g.elements();
        if (elements.hasMoreElements()) {
            s = elements.nextElement().d();
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().d();
        }
        return s;
    }
    
    public void a(final boolean b) {
        final Enumeration<b> elements = this.g.elements();
        while (elements.hasMoreElements()) {
            final b b2 = elements.nextElement();
            if (b) {
                if (b2.a) {
                    continue;
                }
                this.e.a((az)b2);
            }
            else {
                if (this.e.a((az)b2) != -1) {
                    continue;
                }
                this.e.a((az)b2);
                this.a(b2);
            }
        }
    }
    
    public bc(final at k) {
        this.e = new u();
        this.d = new Checkbox(ar.b("Show only online buddies"));
        this.k = k;
        this.g = new Hashtable(30);
        this.setBackground(k.b.g);
        this.setForeground(k.b.f);
        final String e = k.b.e();
        this.k = this.a(e, "addbuddy", "addBuddyIcon.GIF");
        this.j = this.a(e, "removebuddy", "removeBuddyIcon.GIF");
        this.i = this.a(e, "private", "whisperIcon.GIF");
        if (k.b.f()) {
            final Image a = k.a(e + "buddies_checked.gif", true);
            final Image a2 = k.a(e + "buddies_unchecked.gif", true);
            if (a != null && a2 != null) {
                this.d = new n(a, a2);
            }
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.k, gridBagConstraints);
        this.add(this.k);
        if (!k.a(43)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.j, gridBagConstraints);
            this.add(this.j);
        }
        else {
            gridBagLayout.setConstraints(this.j, gridBagConstraints);
            this.add(this.j);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.i, gridBagConstraints);
            this.add(this.i);
        }
        if (this.d instanceof n) {
            final Panel panel = new Panel();
            panel.setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            panel.add(this.d);
            final Label label = new Label(ar.b("Show only online buddies"));
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
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            ((Checkbox)this.d).setState(true);
            this.add(this.d);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final aw aw = new aw(this.e);
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        this.add(aw);
        final p p = new p(null, "icon");
        final p p2 = new p(ar.b("Nickname"), "name");
        p.c(28);
        p.b(0);
        p2.b(true);
        this.e.a(true);
        this.e.b(p);
        this.e.b(p2);
        this.e.a(p2);
        this.e.h(26);
        this.f();
    }
    
    static {
        c = new Color(153);
        i = new Color(10079487);
        a = new Color(16711680);
        b = new Color(10079487);
    }
}
