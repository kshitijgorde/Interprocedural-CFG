// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;
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

public final class al extends Panel implements aA
{
    private static Color q;
    private static Color w;
    private static Color e;
    private static Color r;
    private bg q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private W q;
    private Hashtable q;
    private Component q;
    
    private static void q(final Canvas canvas) {
        if (canvas instanceof M) {
            ((M)canvas).e();
        }
        else {
            ((w)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas instanceof M) {
            ((M)canvas).q();
            return;
        }
        ((w)canvas).setEnabled(true);
    }
    
    private final Canvas q(final String s, final String s2) {
        aA q;
        if (!aU.w.j() || s == null) {
            q = new M(28, 28);
        }
        else {
            final Image r = this.q.r(s + s2 + "_button_up.gif", true);
            final Image r2 = this.q.r(s + s2 + "_button_dn.gif", true);
            final Image r3 = this.q.r(s + s2 + "_button_disabled.gif", true);
            if (r == null || r2 == null || r3 == null) {
                q = new M(28, 28);
            }
            else {
                q = a.w.q(r, r2, r3);
            }
        }
        return (M)q;
    }
    
    public final String q(final Object o) {
        if (o instanceof M || o instanceof w) {
            final cI ci = (cI)this.q.q();
            if (o == this.e) {
                return ak.q("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (ci == null) {
                return ak.q("This button is disabled because no buddy is selected.");
            }
            if (o == this.w) {
                return s.q(ak.q("Click here to remove %1 from your Buddy List."), new String[] { ci.o });
            }
            if (o == this.q && ci.t) {
                return s.q(ak.q("Click here to enter a private conversation with %1."), new String[] { ci.o });
            }
            if (o == this.q && !ci.t) {
                return s.q(ak.q("%1 is not available for private messages now."), new String[] { ci.o });
            }
        }
        return null;
    }
    
    private final boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((T)this.q).q;
    }
    
    public final void show() {
        super.show();
        this.q.requestFocus();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1002: {
                final cI ci = (cI)this.q.q();
                if (((this.q.q(43) && event.target == this.q) || event.target == this.q) && ci != null && ci.t) {
                    this.q.q(null, (Object)ci);
                    return true;
                }
                if (event.target == this.w && ci != null) {
                    final cI ci2 = ci;
                    this.q.remove(ci2.o);
                    final cr cr;
                    (cr = new cr(33621774, 1)).q(0, 0, this.q.o);
                    cr.q(0, 1, ci2.o);
                    cr.q(0, 2, "remove");
                    this.q.r(cr);
                    this.q.a = this.q();
                    this.q.q((aY)ci2);
                    return true;
                }
                if (event.target == this.q) {
                    final boolean q = this.q();
                    final Enumeration<cI> elements = this.q.elements();
                    while (elements.hasMoreElements()) {
                        final cI ci3 = elements.nextElement();
                        if (q) {
                            if (ci3.t) {
                                continue;
                            }
                            this.q.q((aY)ci3);
                        }
                        else {
                            if (this.q.q((aY)ci3) != -1) {
                                continue;
                            }
                            this.q.q((aY)ci3);
                            this.q(ci3);
                        }
                    }
                }
                if (event.target == this.e) {
                    new aQ(this.q.q.q(), this.q).setVisible(true);
                    break;
                }
                break;
            }
            case 701: {
                w(this.w);
                if (((cI)this.q.q()).t) {
                    w(this.q);
                }
                else {
                    q(this.q);
                }
                return true;
            }
            case 702: {
                q(this.w);
                q(this.q);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void q(final cI ci, final boolean b, final boolean b2) {
        if (this.q.o.equals(ci.o)) {
            return;
        }
        aJ aj = null;
        if (ci.o != null) {
            aj = this.q.get(ci.o);
        }
        if (aj == null && b) {
            this.q.put(ci.o, ci);
            if (!b2) {
                final cr cr;
                (cr = new cr(33621774, 1)).q(0, 0, this.q.o);
                cr.q(0, 1, ci.o);
                cr.q(0, 2, "add");
                this.q.r(cr);
            }
            if (ci.t || !this.q()) {
                this.q.q((aY)ci);
                this.q(ci);
            }
            this.q.a = this.q();
            return;
        }
        if (aj != null) {
            this.q.remove(aj.o);
            this.q.put(ci.o, ci);
            this.q.q((aY)aj);
            if (ci.t || !this.q()) {
                this.q.q((aY)ci);
            }
            this.q(ci);
        }
    }
    
    private final void q(final cI ci) {
        if (ci.w) {
            this.q.q(ci, Color.red, Color.pink);
        }
        else if (ci.q(59)) {
            this.q.q(ci, al.e, al.r);
        }
        else if (ci.q(61) || ci.q(62)) {
            this.q.q(ci, al.q, al.w);
        }
        else {
            this.q.q(ci, Color.black, Color.white);
        }
        if (ci.t) {
            this.q.q(ci, false);
        }
        else {
            this.q.q(ci, true);
        }
        this.q.w();
    }
    
    private void q() {
        final String a;
        if ((a = this.q.a) != null && !a.equals("")) {
            int n = 0;
            for (int i = a.indexOf(44, 0); i != -1; i = a.indexOf(44, n)) {
                final cI ci;
                (ci = new cI(-999, a.substring(n, i).trim())).t = false;
                this.q(ci, true, true);
                n = i + 1;
            }
            final cI ci2;
            (ci2 = new cI(-999, a.substring(n, a.length()).trim())).t = false;
            this.q(ci2, true, true);
        }
    }
    
    private String q() {
        String s = "";
        final Enumeration<cI> elements;
        if ((elements = this.q.elements()).hasMoreElements()) {
            s = elements.nextElement().o;
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().o;
        }
        return s;
    }
    
    public al(final W q) {
        this.q = new bg();
        this.q = new Checkbox(ak.q("Show only online buddies"));
        this.q = q;
        this.q = new Hashtable(30);
        this.setBackground(aU.w.i);
        this.setForeground(aU.w.u);
        final String e = aU.w.e();
        this.e = this.q(e, "addbuddy");
        this.w = this.q(e, "removebuddy");
        this.q = this.q(e, "private");
        if (aU.w.j()) {
            final Image r = q.r(e + "buddies_checked.gif", true);
            final Image r2 = q.r(e + "buddies_unchecked.gif", true);
            if (r != null && r2 != null) {
                this.q = new T(r, r2);
            }
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        if (!q.q(43)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
        }
        else {
            gridBagLayout.setConstraints(this.w, gridBagConstraints);
            this.add(this.w);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        if (this.q instanceof T) {
            final Container container;
            (container = new Panel()).setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(ak.q("Show only online buddies"));
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagLayout.setConstraints(label, gridBagConstraints);
            container.add(label);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(container, gridBagConstraints);
            this.add(container);
        }
        else {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            ((Checkbox)this.q).setState(true);
            this.add(this.q);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final bd bd = new bd(this.q);
        gridBagLayout.setConstraints(bd, gridBagConstraints);
        this.add(bd);
        final au au = new au(null, "icon");
        final au au2 = new au(ak.q("Nickname"), "name");
        au.e(28);
        au.w(0);
        au2.q(true);
        this.q.q(true);
        this.q.w(au);
        this.q.w(au2);
        this.q.q(au2);
        this.q.w(26);
        this.q();
    }
    
    static {
        al.q = new Color(153);
        al.w = new Color(10079487);
        al.e = new Color(16711680);
        al.r = new Color(10079487);
        new Color(255, 255, 0);
        new Color(0, 255, 255);
        new Color(255, 0, 255);
        new Color(255, 200, 0);
        new Color(255, 175, 175);
        new Color(192, 192, 192);
    }
}
