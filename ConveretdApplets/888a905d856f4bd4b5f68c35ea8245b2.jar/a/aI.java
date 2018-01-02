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

public final class aI extends Panel implements bf
{
    private static Color q;
    private static Color w;
    private static Color e;
    private static Color r;
    private cc q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private ap q;
    private Hashtable q;
    private Component q;
    
    private static void q(final Canvas canvas) {
        if (canvas instanceof ad) {
            ((ad)canvas).e();
        }
        else {
            ((H)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas instanceof ad) {
            ((ad)canvas).q();
            return;
        }
        ((H)canvas).setEnabled(true);
    }
    
    private final Canvas q(final String s, final String s2) {
        bf q;
        if (!bC.w.z() || s == null) {
            q = new ad(28, 28);
        }
        else {
            final Image r = this.q.r(s + s2 + "_button_up.gif", true);
            final Image r2 = this.q.r(s + s2 + "_button_dn.gif", true);
            final Image r3 = this.q.r(s + s2 + "_button_disabled.gif", true);
            if (r == null || r2 == null || r3 == null) {
                q = new ad(28, 28);
            }
            else {
                q = H.q(r, r2, r3);
            }
        }
        return (ad)q;
    }
    
    public final String q(final Object o) {
        if (o instanceof ad || o instanceof H) {
            final eh eh = (eh)this.q.q();
            if (o == this.e) {
                return be.w("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (eh == null) {
                return be.w("This button is disabled because no buddy is selected.");
            }
            if (o == this.w) {
                return B.q(be.w("Click here to remove %1 from your Buddy List."), new String[] { eh.a });
            }
            if (o == this.q && eh.y) {
                return B.q(be.w("Click here to enter a private conversation with %1."), new String[] { eh.a });
            }
            if (o == this.q && !eh.y) {
                return B.q(be.w("%1 is not available for private messages now."), new String[] { eh.a });
            }
        }
        return null;
    }
    
    private final boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((am)this.q).q;
    }
    
    public final void show() {
        super.show();
        this.q.requestFocus();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1002: {
                final eh eh = (eh)this.q.q();
                if (((this.q.q(43) && event.target == this.q) || event.target == this.q) && eh != null && eh.y) {
                    this.q.q(null, (Object)eh);
                    return true;
                }
                if (event.target == this.w && eh != null) {
                    final eh eh2 = eh;
                    this.q.remove(eh2.a);
                    final dI di;
                    (di = new dI(33621774, 1)).q(0, 0, this.q.a);
                    di.q(0, 1, eh2.a);
                    di.q(0, 2, "remove");
                    this.q.o(di);
                    this.q.g = this.q();
                    this.q.q((bJ)eh2);
                    return true;
                }
                if (event.target == this.q) {
                    final boolean q = this.q();
                    final Enumeration<eh> elements = this.q.elements();
                    while (elements.hasMoreElements()) {
                        final eh eh3 = elements.nextElement();
                        if (q) {
                            if (eh3.y) {
                                continue;
                            }
                            this.q.q((bJ)eh3);
                        }
                        else {
                            if (this.q.q((bJ)eh3) != -1) {
                                continue;
                            }
                            this.q.e(eh3);
                            this.q(eh3);
                        }
                    }
                }
                if (event.target == this.e) {
                    new by(this.q.q.q(), this.q).setVisible(true);
                    break;
                }
                break;
            }
            case 701: {
                w(this.w);
                if (((eh)this.q.q()).y) {
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
    
    public final void q(final eh eh, final boolean b, final boolean b2) {
        if (this.q.a.equals(eh.a)) {
            return;
        }
        bp bp = null;
        if (eh.a != null) {
            bp = this.q.get(eh.a);
        }
        if (bp == null && b) {
            this.q.put(eh.a, eh);
            if (!b2) {
                final dI di;
                (di = new dI(33621774, 1)).q(0, 0, this.q.a);
                di.q(0, 1, eh.a);
                di.q(0, 2, "add");
                this.q.o(di);
            }
            if (eh.y || !this.q()) {
                this.q.e(eh);
                this.q(eh);
            }
            this.q.g = this.q();
            return;
        }
        if (bp != null) {
            this.q.remove(bp.a);
            this.q.put(eh.a, eh);
            this.q.q((bJ)bp);
            if (eh.y || !this.q()) {
                this.q.e(eh);
            }
            this.q(eh);
        }
    }
    
    private final void q(final eh eh) {
        if (eh.w) {
            this.q.q(eh, Color.red, Color.pink);
        }
        else if (eh.q(59)) {
            this.q.q(eh, aI.e, aI.r);
        }
        else if (eh.q(61) || eh.q(62)) {
            this.q.q(eh, aI.q, aI.w);
        }
        else {
            this.q.q(eh, Color.black, Color.white);
        }
        if (eh.y) {
            this.q.w(eh, false);
        }
        else {
            this.q.w(eh, true);
        }
        this.q.w();
    }
    
    private void q() {
        final String g;
        if ((g = this.q.g) != null && !g.equals("")) {
            int n = 0;
            for (int i = g.indexOf(44, 0); i != -1; i = g.indexOf(44, n)) {
                final eh eh;
                (eh = new eh(-999, g.substring(n, i).trim())).y = false;
                this.q(eh, true, true);
                n = i + 1;
            }
            final eh eh2;
            (eh2 = new eh(-999, g.substring(n, g.length()).trim())).y = false;
            this.q(eh2, true, true);
        }
    }
    
    private String q() {
        String s = "";
        final Enumeration<eh> elements;
        if ((elements = this.q.elements()).hasMoreElements()) {
            s = elements.nextElement().a;
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().a;
        }
        return s;
    }
    
    public aI(final ap q) {
        this.q = new cc();
        this.q = new Checkbox(be.w("Show only online buddies"));
        this.q = q;
        this.q = new Hashtable(30);
        this.setBackground(bC.w.i);
        this.setForeground(bC.w.u);
        final String r = bC.w.r();
        this.e = this.q(r, "addbuddy");
        this.w = this.q(r, "removebuddy");
        this.q = this.q(r, "private");
        if (bC.w.z()) {
            final Image r2 = q.r(r + "buddies_checked.gif", true);
            final Image r3 = q.r(r + "buddies_unchecked.gif", true);
            if (r2 != null && r3 != null) {
                this.q = new am(r2, r3);
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
        if (this.q instanceof am) {
            final Container container;
            (container = new Panel()).setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(be.w("Show only online buddies"));
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
        final bZ bz = new bZ(this.q);
        gridBagLayout.setConstraints(bz, gridBagConstraints);
        this.add(bz);
        final aX ax = new aX(null, "icon");
        final aX ax2 = new aX(be.w("Nickname"), "name");
        ax.r(28);
        ax.e(0);
        ax2.w(true);
        this.q.q(true);
        this.q.w(ax);
        this.q.w(ax2);
        this.q.q(ax2);
        this.q.w(26);
        this.q();
    }
    
    static {
        aI.q = new Color(153);
        aI.w = new Color(10079487);
        aI.e = new Color(16711680);
        aI.r = new Color(10079487);
        new Color(255, 255, 0);
        new Color(0, 255, 255);
        new Color(255, 0, 255);
        new Color(255, 200, 0);
        new Color(255, 175, 175);
        new Color(192, 192, 192);
    }
}
