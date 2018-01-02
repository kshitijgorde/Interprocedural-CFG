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

public final class cH extends Panel implements dT
{
    private static Color q;
    private static Color w;
    private static Color e;
    private static Color r;
    private w q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private cV q;
    private Hashtable q;
    private Component q;
    
    private static void q(final Canvas canvas) {
        if (canvas instanceof g) {
            ((g)canvas).e();
        }
        else {
            ((p)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private static void w(final Canvas canvas) {
        if (canvas instanceof g) {
            ((g)canvas).q();
            return;
        }
        ((p)canvas).setEnabled(true);
    }
    
    private final Canvas q(final String s, final String s2) {
        dT q;
        if (!cf.w.k() || s == null) {
            q = new g(28, 28);
        }
        else {
            final Image q2 = this.q.q(s + s2 + "_button_up.gif", true);
            final Image q3 = this.q.q(s + s2 + "_button_dn.gif", true);
            final Image q4 = this.q.q(s + s2 + "_button_disabled.gif", true);
            if (q2 == null || q3 == null || q4 == null) {
                q = new g(28, 28);
            }
            else {
                q = p.q(q2, q3, q4);
            }
        }
        return (g)q;
    }
    
    public final String q(final Object o) {
        if (o instanceof g || o instanceof p) {
            final dt dt = (dt)this.q.q();
            if (o == this.e) {
                return eb.q("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (dt == null) {
                return eb.q("This button is disabled because no buddy is selected.");
            }
            if (o == this.w) {
                return ec.q(eb.q("Click here to remove %1 from your Buddy List."), new String[] { dt.getName() });
            }
            if (o == this.q && dt.y) {
                return ec.q(eb.q("Click here to enter a private conversation with %1."), new String[] { dt.getName() });
            }
            if (o == this.q && !dt.y) {
                return ec.q(eb.q("%1 is not available for private messages now."), new String[] { dt.getName() });
            }
        }
        return null;
    }
    
    private final boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((L)this.q).q();
    }
    
    public final void show() {
        super.show();
        this.q.requestFocus();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1002: {
                final dt dt = (dt)this.q.q();
                if (((this.q.q(43) && event.target == this.q) || event.target == this.q) && dt != null && dt.y) {
                    this.q.q(null, (Object)dt);
                    return true;
                }
                if (event.target == this.w && dt != null) {
                    final dt dt2 = dt;
                    this.q.remove(dt2.getName());
                    final es es;
                    (es = new es(33621774, 1)).q(0, 0, this.q.getName());
                    es.q(0, 1, dt2.getName());
                    es.q(0, 2, "remove");
                    this.q.q(es);
                    this.q.s = this.q();
                    this.q.q((aF)dt2);
                    return true;
                }
                if (event.target == this.q) {
                    final boolean q = this.q();
                    final Enumeration<dt> elements = this.q.elements();
                    while (elements.hasMoreElements()) {
                        final dt dt3 = elements.nextElement();
                        if (q) {
                            if (dt3.y) {
                                continue;
                            }
                            this.q.q((aF)dt3);
                        }
                        else {
                            if (this.q.q((aF)dt3) != -1) {
                                continue;
                            }
                            this.q.e(dt3);
                            this.q(dt3);
                        }
                    }
                }
                if (event.target == this.e) {
                    new ao(this.q.q.q(), this.q).setVisible(true);
                    break;
                }
                break;
            }
            case 701: {
                w(this.w);
                if (((dt)this.q.q()).y) {
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
    
    public final void q(final dt dt, final boolean b, final boolean b2) {
        if (this.q.getName().equals(dt.getName())) {
            return;
        }
        bZ bz = null;
        if (dt.getName() != null) {
            bz = this.q.get(dt.getName());
        }
        if (bz == null && b) {
            this.q.put(dt.getName(), dt);
            if (!b2) {
                final es es;
                (es = new es(33621774, 1)).q(0, 0, this.q.getName());
                es.q(0, 1, dt.getName());
                es.q(0, 2, "add");
                this.q.q(es);
            }
            if (dt.y || !this.q()) {
                this.q.e(dt);
                this.q(dt);
            }
            this.q.s = this.q();
            return;
        }
        if (bz != null) {
            this.q.remove(bz.getName());
            this.q.put(dt.getName(), dt);
            this.q.q((aF)bz);
            if (dt.y || !this.q()) {
                this.q.e(dt);
            }
            this.q(dt);
        }
    }
    
    private final void q(final dt dt) {
        if (dt.e) {
            this.q.q(dt, Color.red, Color.pink);
        }
        else if (dt.q(59)) {
            this.q.q(dt, cH.e, cH.r);
        }
        else if (dt.q(61) || dt.q(62)) {
            this.q.q(dt, cH.q, cH.w);
        }
        else {
            this.q.q(dt, Color.black, Color.white);
        }
        if (dt.y) {
            this.q.w(dt, false);
        }
        else {
            this.q.w(dt, true);
        }
        this.q.w();
    }
    
    private void q() {
        final String s;
        if ((s = this.q.s) != null && !s.equals("")) {
            int n = 0;
            for (int i = s.indexOf(44, 0); i != -1; i = s.indexOf(44, n)) {
                final dt dt;
                (dt = new dt(-999, s.substring(n, i).trim())).y = false;
                this.q(dt, true, true);
                n = i + 1;
            }
            final dt dt2;
            (dt2 = new dt(-999, s.substring(n, s.length()).trim())).y = false;
            this.q(dt2, true, true);
        }
    }
    
    private String q() {
        String s = "";
        final Enumeration<dt> elements;
        if ((elements = this.q.elements()).hasMoreElements()) {
            s = elements.nextElement().getName();
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().getName();
        }
        return s;
    }
    
    public cH(final cV q) {
        this.q = new w();
        this.q = new Checkbox(eb.q("Show only online buddies"));
        this.q = q;
        this.q = new Hashtable(30);
        this.setBackground(cf.w.i);
        this.setForeground(cf.w.u);
        final String e = cf.w.e();
        this.e = this.q(e, "addbuddy");
        this.w = this.q(e, "removebuddy");
        this.q = this.q(e, "private");
        if (cf.w.k()) {
            final Image q2 = q.q(e + "buddies_checked.gif", true);
            final Image q3 = q.q(e + "buddies_unchecked.gif", true);
            if (q2 != null && q3 != null) {
                this.q = new L(q2, q3);
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
        if (this.q instanceof L) {
            final Container container;
            (container = new Panel()).setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(eb.q("Show only online buddies"));
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
        final t t = new t(this.q);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        this.add(t);
        final y y = new y(null, "icon");
        final y y2 = new y(eb.q("Nickname"), "name");
        y.r(28);
        y.e(0);
        y2.w(true);
        this.q.q(true);
        this.q.w(y);
        this.q.w(y2);
        this.q.q(y2);
        this.q.w(26);
        this.q();
    }
    
    static {
        cH.q = new Color(153);
        cH.w = new Color(10079487);
        cH.e = new Color(16711680);
        cH.r = new Color(10079487);
        new Color(255, 255, 0);
        new Color(0, 255, 255);
        new Color(255, 0, 255);
        new Color(255, 200, 0);
        new Color(255, 175, 175);
        new Color(192, 192, 192);
    }
}
