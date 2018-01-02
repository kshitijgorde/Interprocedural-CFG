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

public class N extends Panel implements aB
{
    public static final Color c;
    public static final Color h;
    public static final Color a;
    public static final Color b;
    private l d;
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private u h;
    private Hashtable d;
    private Component b;
    
    private final void a(final Canvas canvas) {
        if (canvas instanceof aS) {
            ((aS)canvas).d();
        }
        else {
            ((cz)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private final void b(final Canvas canvas) {
        if (canvas instanceof aS) {
            ((aS)canvas).c();
        }
        else {
            ((cz)canvas).setEnabled(true);
        }
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        aB a;
        if (!this.h.a.p() || s == null) {
            a = new aS(28, 28);
            ((aS)a).a(this.h.a(s3, false, 20));
        }
        else {
            final Image a2 = this.h.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.h.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = this.h.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new aS(28, 28);
                ((aS)a).a(this.h.a(s3, false, 20));
            }
            else {
                a = cz.a(a2, a3, a4);
            }
        }
        return (Canvas)a;
    }
    
    public String a(final Object o) {
        if (o instanceof aS || o instanceof cz) {
            final ac ac = (ac)this.d.a();
            if (o == this.c) {
                return ao.e("Click here to type in the name of the Buddy you want to add to the Buddy List.");
            }
            if (ac == null) {
                return ao.e("This button is disabled because no buddy is selected.");
            }
            if (o == this.b) {
                return am.a(ao.e("Click here to remove %1 from your Buddy List."), new String[] { ac.f() });
            }
            if (o == this.a && ac.a) {
                return am.a(ao.e("Click here to enter a private conversation with %1."), new String[] { ac.f() });
            }
            if (o == this.a && !ac.a) {
                return am.a(ao.e("%1 is not available for private messages now."), new String[] { ac.f() });
            }
        }
        return null;
    }
    
    private final boolean b() {
        if (this.b instanceof Checkbox) {
            return ((Checkbox)this.b).getState();
        }
        return ((cm)this.b).a();
    }
    
    public void show() {
        super.show();
        this.d.requestFocus();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final ac ac = (ac)this.d.a();
                if (((this.h.d(43) && event.target == this.d) || event.target == this.a) && ac != null && ac.a) {
                    this.h.a((Z)null, (cG)ac);
                    return true;
                }
                if (event.target == this.b && ac != null) {
                    this.a(ac);
                    return true;
                }
                if (event.target == this.b) {
                    this.a(this.b());
                }
                if (event.target == this.c) {
                    new af(this.h.a.a(), this.h).setVisible(true);
                }
                break;
            }
            case 701: {
                this.b(this.b);
                if (((ac)this.d.a()).a) {
                    this.b(this.a);
                }
                else {
                    this.a(this.a);
                }
                return true;
            }
            case 702: {
                this.a(this.b);
                this.a(this.a);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean a(final ac ac) {
        this.d.remove(ac.f());
        final cD cd = new cD(33621774, 1);
        cd.a(0, 0, this.h.f());
        cd.a(0, 1, ac.f());
        cd.a(0, 2, "remove");
        this.h.o(cd);
        this.h.q = this.d();
        return this.d.a((aU)ac);
    }
    
    public void a(final ac ac, final boolean b, final boolean b2) {
        if (this.h.f().equals(ac.f())) {
            return;
        }
        cF cf = null;
        if (ac.f() != null) {
            cf = this.d.get(ac.f());
        }
        if (cf == null && b) {
            this.d.put(ac.f(), ac);
            if (!b2) {
                final cD cd = new cD(33621774, 1);
                cd.a(0, 0, this.h.f());
                cd.a(0, 1, ac.f());
                cd.a(0, 2, "add");
                this.h.o(cd);
            }
            if (ac.a || !this.b()) {
                this.d.c(ac);
                this.a(ac);
            }
            this.h.q = this.d();
        }
        else if (cf != null) {
            this.d.remove(cf.f());
            this.d.put(ac.f(), ac);
            this.d.a((aU)cf);
            if (ac.a || !this.b()) {
                this.d.c(ac);
            }
            this.a(ac);
        }
    }
    
    private final void a(final ac ac) {
        if (ac.b) {
            this.d.a(ac, Color.red, Color.pink);
        }
        else if (ac.d(59)) {
            this.d.a(ac, N.a, N.b);
        }
        else if (ac.d(61) || ac.d(62)) {
            this.d.a(ac, N.c, N.h);
        }
        else {
            this.d.a(ac, Color.black, Color.white);
        }
        if (ac.a) {
            this.d.b(ac, false);
        }
        else {
            this.d.b(ac, true);
        }
        this.d.c();
    }
    
    protected void a() {
        final String q = this.h.q;
        if (q != null && !q.equals("")) {
            final int n = 44;
            int n2 = 0;
            for (int i = q.indexOf(n, n2); i != -1; i = q.indexOf(n, n2)) {
                final ac ac = new ac(-999, q.substring(n2, i).trim());
                ac.a = false;
                this.a(ac, true, true);
                n2 = i + 1;
            }
            final ac ac2 = new ac(-999, q.substring(n2, q.length()).trim());
            ac2.a = false;
            this.a(ac2, true, true);
        }
    }
    
    public String d() {
        String s = "";
        final Enumeration<ac> elements = this.d.elements();
        if (elements.hasMoreElements()) {
            s = elements.nextElement().f();
        }
        while (elements.hasMoreElements()) {
            s = s + "," + elements.nextElement().f();
        }
        return s;
    }
    
    public void a(final boolean b) {
        final Enumeration<ac> elements = this.d.elements();
        while (elements.hasMoreElements()) {
            final ac ac = elements.nextElement();
            if (b) {
                if (ac.a) {
                    continue;
                }
                this.d.a((aU)ac);
            }
            else {
                if (this.d.a((aU)ac) != -1) {
                    continue;
                }
                this.d.c(ac);
                this.a(ac);
            }
        }
    }
    
    public N(final u h) {
        this.d = new l();
        this.b = new Checkbox(ao.e("Show only online buddies"));
        this.h = h;
        this.d = new Hashtable(30);
        this.setBackground(h.a.g);
        this.setForeground(h.a.f);
        final String e = h.a.e();
        this.c = this.a(e, "addbuddy", "addBuddyIcon.GIF");
        this.b = this.a(e, "removebuddy", "removeBuddyIcon.GIF");
        this.a = this.a(e, "private", "whisperIcon.GIF");
        if (h.a.p()) {
            final Image a = h.a(e + "buddies_checked.gif", true);
            final Image a2 = h.a(e + "buddies_unchecked.gif", true);
            if (a != null && a2 != null) {
                this.b = new cm(a, a2);
            }
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        if (!h.d(43)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        else {
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        if (this.b instanceof cm) {
            final Panel panel = new Panel();
            panel.setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            panel.add(this.b);
            final Label label = new Label(ao.e("Show only online buddies"));
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
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            ((Checkbox)this.b).setState(true);
            this.add(this.b);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final aR ar = new aR(this.d);
        gridBagLayout.setConstraints(ar, gridBagConstraints);
        this.add(ar);
        final j j = new j(null, "icon");
        final j i = new j(ao.e("Nickname"), "name");
        j.d(28);
        j.c(0);
        i.c(true);
        this.d.a(true);
        this.d.b(j);
        this.d.b(i);
        this.d.a(i);
        this.d.i(26);
        this.a();
    }
    
    static {
        c = new Color(153);
        h = new Color(10079487);
        a = new Color(16711680);
        b = new Color(10079487);
    }
}
