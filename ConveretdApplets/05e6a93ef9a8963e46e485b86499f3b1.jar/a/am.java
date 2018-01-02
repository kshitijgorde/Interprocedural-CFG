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

public final class am extends Panel implements aB
{
    private static Color q;
    private static Color w;
    private static Color e;
    private static Color r;
    private bf q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private W q;
    private Hashtable q;
    private Component q;
    
    private static void q(final Canvas canvas) {
        if (canvas instanceof N) {
            ((N)canvas).e();
        }
        else {
            ((x)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas instanceof N) {
            ((N)canvas).q();
            return;
        }
        ((x)canvas).setEnabled(true);
    }
    
    private final Canvas q(final String s, final String s2) {
        aB q;
        if (!aT.w.g() || s == null) {
            q = new N(28, 28);
        }
        else {
            final Image r = this.q.r(s + s2 + "_button_up.gif", true);
            final Image r2 = this.q.r(s + s2 + "_button_dn.gif", true);
            final Image r3 = this.q.r(s + s2 + "_button_disabled.gif", true);
            if (r == null || r2 == null || r3 == null) {
                q = new N(28, 28);
            }
            else {
                q = a.x.q(r, r2, r3);
            }
        }
        return (N)q;
    }
    
    public final String q(final Object o) {
        if (o instanceof N || o instanceof x) {
            final cF cf = (cF)this.q.q();
            if (o == this.e) {
                return al.q("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (cf == null) {
                return al.q("This button is disabled because no buddy is selected.");
            }
            if (o == this.w) {
                return t.q(al.q("Click here to remove %1 from your Buddy List."), new String[] { cf.o });
            }
            if (o == this.q && cf.t) {
                return t.q(al.q("Click here to enter a private conversation with %1."), new String[] { cf.o });
            }
            if (o == this.q && !cf.t) {
                return t.q(al.q("%1 is not available for private messages now."), new String[] { cf.o });
            }
        }
        return null;
    }
    
    private final boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((U)this.q).q;
    }
    
    public final void show() {
        super.show();
        this.q.requestFocus();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1002: {
                final cF cf = (cF)this.q.q();
                if (((this.q.q(43) && event.target == this.q) || event.target == this.q) && cf != null && cf.t) {
                    this.q.q(null, (Object)cf);
                    return true;
                }
                if (event.target == this.w && cf != null) {
                    final cF cf2 = cf;
                    this.q.remove(cf2.o);
                    final cp cp;
                    (cp = new cp(33621774, 1)).q(0, 0, this.q.o);
                    cp.q(0, 1, cf2.o);
                    cp.q(0, 2, "remove");
                    this.q.r(cp);
                    this.q.a = this.q();
                    this.q.q((aX)cf2);
                    return true;
                }
                if (event.target == this.q) {
                    final boolean q = this.q();
                    final Enumeration<cF> elements = this.q.elements();
                    while (elements.hasMoreElements()) {
                        final cF cf3 = elements.nextElement();
                        if (q) {
                            if (cf3.t) {
                                continue;
                            }
                            this.q.q((aX)cf3);
                        }
                        else {
                            if (this.q.q((aX)cf3) != -1) {
                                continue;
                            }
                            this.q.q((aX)cf3);
                            this.q(cf3);
                        }
                    }
                }
                if (event.target == this.e) {
                    new aO(this.q.q.q(), this.q).setVisible(true);
                    break;
                }
                break;
            }
            case 701: {
                w(this.w);
                if (((cF)this.q.q()).t) {
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
    
    public final void q(final cF cf, final boolean b, final boolean b2) {
        if (this.q.o.equals(cf.o)) {
            return;
        }
        aK ak = null;
        if (cf.o != null) {
            ak = this.q.get(cf.o);
        }
        if (ak == null && b) {
            this.q.put(cf.o, cf);
            if (!b2) {
                final cp cp;
                (cp = new cp(33621774, 1)).q(0, 0, this.q.o);
                cp.q(0, 1, cf.o);
                cp.q(0, 2, "add");
                this.q.r(cp);
            }
            if (cf.t || !this.q()) {
                this.q.q((aX)cf);
                this.q(cf);
            }
            this.q.a = this.q();
            return;
        }
        if (ak != null) {
            this.q.remove(ak.o);
            this.q.put(cf.o, cf);
            this.q.q((aX)ak);
            if (cf.t || !this.q()) {
                this.q.q((aX)cf);
            }
            this.q(cf);
        }
    }
    
    private final void q(final cF cf) {
        if (cf.w) {
            this.q.q(cf, Color.red, Color.pink);
        }
        else if (cf.q(59)) {
            this.q.q(cf, am.e, am.r);
        }
        else if (cf.q(61) || cf.q(62)) {
            this.q.q(cf, am.q, am.w);
        }
        else {
            this.q.q(cf, Color.black, Color.white);
        }
        if (cf.t) {
            this.q.q(cf, false);
        }
        else {
            this.q.q(cf, true);
        }
        this.q.w();
    }
    
    private void q() {
        final String a;
        if ((a = this.q.a) != null && !a.equals("")) {
            int n = 0;
            for (int i = a.indexOf(44, 0); i != -1; i = a.indexOf(44, n)) {
                final cF cf;
                (cf = new cF(-999, a.substring(n, i).trim())).t = false;
                this.q(cf, true, true);
                n = i + 1;
            }
            final cF cf2;
            (cf2 = new cF(-999, a.substring(n, a.length()).trim())).t = false;
            this.q(cf2, true, true);
        }
    }
    
    private String q() {
        String s = "";
        final Enumeration<cF> elements;
        if ((elements = this.q.elements()).hasMoreElements()) {
            s = elements.nextElement().o;
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().o;
        }
        return s;
    }
    
    public am(final W q) {
        this.q = new bf();
        this.q = new Checkbox(al.q("Show only online buddies"));
        this.q = q;
        this.q = new Hashtable(30);
        this.setBackground(aT.w.i);
        this.setForeground(aT.w.u);
        final String e = aT.w.e();
        this.e = this.q(e, "addbuddy");
        this.w = this.q(e, "removebuddy");
        this.q = this.q(e, "private");
        if (aT.w.g()) {
            final Image r = q.r(e + "buddies_checked.gif", true);
            final Image r2 = q.r(e + "buddies_unchecked.gif", true);
            if (r != null && r2 != null) {
                this.q = new U(r, r2);
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
        if (this.q instanceof U) {
            final Container container;
            (container = new Panel()).setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(al.q("Show only online buddies"));
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
        final bc bc = new bc(this.q);
        gridBagLayout.setConstraints(bc, gridBagConstraints);
        this.add(bc);
        final av av = new av(null, "icon");
        final av av2 = new av(al.q("Nickname"), "name");
        av.e(28);
        av.w(0);
        av2.q(true);
        this.q.q(true);
        this.q.w(av);
        this.q.w(av2);
        this.q.q(av2);
        this.q.w(26);
        this.q();
    }
    
    static {
        am.q = new Color(153);
        am.w = new Color(10079487);
        am.e = new Color(16711680);
        am.r = new Color(10079487);
        new Color(255, 255, 0);
        new Color(0, 255, 255);
        new Color(255, 0, 255);
        new Color(255, 200, 0);
        new Color(255, 175, 175);
        new Color(192, 192, 192);
    }
}
