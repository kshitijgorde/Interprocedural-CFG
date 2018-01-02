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

public final class bu extends Panel implements cn
{
    private static Color q;
    private static Color w;
    private static Color e;
    private static Color r;
    private u q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private bI q;
    private Hashtable q;
    private Component q;
    
    private static void q(final Canvas canvas) {
        if (canvas instanceof e) {
            ((e)canvas).e();
        }
        else {
            ((n)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas instanceof e) {
            ((e)canvas).q();
            return;
        }
        ((n)canvas).setEnabled(true);
    }
    
    private final Canvas q(final String s, final String s2) {
        cn q;
        if (!be.w.h() || s == null) {
            q = new e(28, 28);
        }
        else {
            final Image q2 = this.q.q(s + s2 + "_button_up.gif", true);
            final Image q3 = this.q.q(s + s2 + "_button_dn.gif", true);
            final Image q4 = this.q.q(s + s2 + "_button_disabled.gif", true);
            if (q2 == null || q3 == null || q4 == null) {
                q = new e(28, 28);
            }
            else {
                q = n.q(q2, q3, q4);
            }
        }
        return (e)q;
    }
    
    public final String q(final Object o) {
        if (o instanceof e || o instanceof n) {
            final bZ bz = (bZ)this.q.q();
            if (o == this.e) {
                return cv.q("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (bz == null) {
                return cv.q("This button is disabled because no buddy is selected.");
            }
            if (o == this.w) {
                return cv.q(cv.q("Click here to remove %1 from your Buddy List."), new String[] { bz.getName() });
            }
            if (o == this.q && bz.t) {
                return cv.q(cv.q("Click here to enter a private conversation with %1."), new String[] { bz.getName() });
            }
            if (o == this.q && !bz.t) {
                return cv.q(cv.q("%1 is not available for private messages now."), new String[] { bz.getName() });
            }
        }
        return null;
    }
    
    private final boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((I)this.q).q();
    }
    
    public final void show() {
        super.show();
        this.q.requestFocus();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1002: {
                final bZ bz = (bZ)this.q.q();
                if (((this.q.q(43) && event.target == this.q) || event.target == this.q) && bz != null && bz.t) {
                    this.q.q(null, (Object)bz);
                    return true;
                }
                if (event.target == this.w && bz != null) {
                    final bZ bz2 = bz;
                    this.q.remove(bz2.getName());
                    final cJ cj;
                    (cj = new cJ(33621774, 1)).q(0, 0, this.q.getName());
                    cj.q(0, 1, bz2.getName());
                    cj.q(0, 2, "remove");
                    this.q.q(cj);
                    this.q.a = this.q();
                    this.q.q((aq)bz2);
                    return true;
                }
                if (event.target == this.q) {
                    final boolean q = this.q();
                    final Enumeration<bZ> elements = this.q.elements();
                    while (elements.hasMoreElements()) {
                        final bZ bz3 = elements.nextElement();
                        if (q) {
                            if (bz3.t) {
                                continue;
                            }
                            this.q.q((aq)bz3);
                        }
                        else {
                            if (this.q.q((aq)bz3) != -1) {
                                continue;
                            }
                            this.q.q((aq)bz3);
                            this.q(bz3);
                        }
                    }
                }
                if (event.target == this.e) {
                    new Z(this.q.q.q(), this.q).setVisible(true);
                    break;
                }
                break;
            }
            case 701: {
                w(this.w);
                if (((bZ)this.q.q()).t) {
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
    
    public final void q(final bZ bz, final boolean b, final boolean b2) {
        if (this.q.getName().equals(bz.getName())) {
            return;
        }
        ba ba = null;
        if (bz.getName() != null) {
            ba = this.q.get(bz.getName());
        }
        if (ba == null && b) {
            this.q.put(bz.getName(), bz);
            if (!b2) {
                final cJ cj;
                (cj = new cJ(33621774, 1)).q(0, 0, this.q.getName());
                cj.q(0, 1, bz.getName());
                cj.q(0, 2, "add");
                this.q.q(cj);
            }
            if (bz.t || !this.q()) {
                this.q.q((aq)bz);
                this.q(bz);
            }
            this.q.a = this.q();
            return;
        }
        if (ba != null) {
            this.q.remove(ba.getName());
            this.q.put(bz.getName(), bz);
            this.q.q((aq)ba);
            if (bz.t || !this.q()) {
                this.q.q((aq)bz);
            }
            this.q(bz);
        }
    }
    
    private final void q(final bZ bz) {
        if (bz.w) {
            this.q.q(bz, Color.red, Color.pink);
        }
        else if (bz.q(59)) {
            this.q.q(bz, bu.e, bu.r);
        }
        else if (bz.q(61) || bz.q(62)) {
            this.q.q(bz, bu.q, bu.w);
        }
        else {
            this.q.q(bz, Color.black, Color.white);
        }
        if (bz.t) {
            this.q.q(bz, false);
        }
        else {
            this.q.q(bz, true);
        }
        this.q.w();
    }
    
    private void q() {
        final String a;
        if ((a = this.q.a) != null && !a.equals("")) {
            int n = 0;
            for (int i = a.indexOf(44, 0); i != -1; i = a.indexOf(44, n)) {
                final bZ bz;
                (bz = new bZ(-999, a.substring(n, i).trim())).t = false;
                this.q(bz, true, true);
                n = i + 1;
            }
            final bZ bz2;
            (bz2 = new bZ(-999, a.substring(n, a.length()).trim())).t = false;
            this.q(bz2, true, true);
        }
    }
    
    private String q() {
        String s = "";
        final Enumeration<bZ> elements;
        if ((elements = this.q.elements()).hasMoreElements()) {
            s = elements.nextElement().getName();
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().getName();
        }
        return s;
    }
    
    public bu(final bI q) {
        this.q = new u();
        this.q = new Checkbox(cv.q("Show only online buddies"));
        this.q = q;
        this.q = new Hashtable(30);
        this.setBackground(be.w.i);
        this.setForeground(be.w.u);
        final String w = be.w.w();
        this.e = this.q(w, "addbuddy");
        this.w = this.q(w, "removebuddy");
        this.q = this.q(w, "private");
        if (be.w.h()) {
            final Image q2 = q.q(w + "buddies_checked.gif", true);
            final Image q3 = q.q(w + "buddies_unchecked.gif", true);
            if (q2 != null && q3 != null) {
                this.q = new I(q2, q3);
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
        if (this.q instanceof I) {
            final Container container;
            (container = new Panel()).setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(cv.q("Show only online buddies"));
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
        final r r = new r(this.q);
        gridBagLayout.setConstraints(r, gridBagConstraints);
        this.add(r);
        final w w2 = new w(null, "icon");
        final w w3 = new w(cv.q("Nickname"), "name");
        w2.e(28);
        w2.w(0);
        w3.q(true);
        this.q.q(true);
        this.q.w(w2);
        this.q.w(w3);
        this.q.q(w3);
        this.q.w(26);
        this.q();
    }
    
    static {
        bu.q = new Color(153);
        bu.w = new Color(10079487);
        bu.e = new Color(16711680);
        bu.r = new Color(10079487);
        new Color(255, 255, 0);
        new Color(0, 255, 255);
        new Color(255, 0, 255);
        new Color(255, 200, 0);
        new Color(255, 175, 175);
        new Color(192, 192, 192);
    }
}
