// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Container;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.util.Vector;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;

public final class aI extends aA implements aB, MouseListener, ImageObserver
{
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Canvas d;
    private Canvas e;
    private Canvas f;
    Component a;
    private Component b;
    public TextField a;
    private Vector a;
    
    private boolean a() {
        if (this.a instanceof bI) {
            return ((bI)this.a).a;
        }
        return n.a(this.a);
    }
    
    private static void a(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof cr) {
            ((cr)canvas).b();
            return;
        }
        ((bG)canvas).setEnabled(false);
    }
    
    private static void b(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof cr) {
            ((cr)canvas).a();
            return;
        }
        ((bG)canvas).setEnabled(true);
    }
    
    public final void a(final boolean b) {
        super.a.b(b);
        super.a.b(this.a());
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        aB a;
        if (!super.a.a.d() || s == null) {
            ((cr)(a = new cr(28, 28))).a(super.a.a(s3, false, 20));
        }
        else {
            final Image a2 = super.a.a(s + s2 + "_button_up.gif", true);
            final Image a3 = super.a.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = super.a.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                ((cr)(a = new cr(28, 28))).a(super.a.a(s3, false, 20));
            }
            else {
                this.prepareImage(a2, this);
                this.prepareImage(a3, this);
                this.prepareImage(a4, this);
                a = bG.a(a2, a3, a4);
            }
        }
        return (cr)a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.handleEvent(new Event(mouseEvent.getSource(), 1001, null));
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private boolean b() {
        if (this.b == null) {
            return false;
        }
        if (this.b instanceof bI) {
            return ((bI)this.b).a;
        }
        return n.a(this.b);
    }
    
    public final String a(final Object o) {
        if (o instanceof cr || o instanceof bG) {
            final ai ai;
            if ((ai = (ai)super.a.a()) == null) {
                return aS.a(283);
            }
            if (o == this.b) {
                return bm.a(aS.a(282), new String[] { ai.d });
            }
            if (o == this.e) {
                if (!this.a.contains(String.valueOf(ai.i))) {
                    return bm.a(aS.a(576), new String[] { ai.d });
                }
                return bm.a(aS.a(577), new String[] { ai.d });
            }
            else if (o == this.c) {
                if (ai.c) {
                    return bm.a(aS.a(281), new String[] { ai.d });
                }
                return bm.a(aS.a(280), new String[] { ai.d });
            }
            else {
                if (o == this.f) {
                    return bm.a(aS.a(279), new String[] { ai.d });
                }
                if (o == this.a) {
                    final Canvas a;
                    if ((a = this.a) != null && ((a instanceof cr) ? ((cr)a).a() : ((bG)a).isEnabled())) {
                        if (ai.d) {
                            return bm.a(aS.a(278), new String[] { ai.d });
                        }
                        return bm.a(aS.a(277), new String[] { ai.d });
                    }
                    else if (ai.a(33)) {
                        return bm.a(aS.a(276), new String[] { ai.d });
                    }
                }
                if (o == this.d) {
                    return bm.a(aS.a(275), new String[] { ai.d });
                }
            }
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        final aZ az = (aZ)super.a.a();
        switch (event.id) {
            case 402: {
                if (event.target == this.a) {
                    if (this.a.getText().length() == 0 && super.a.n) {
                        super.a.n = false;
                    }
                    else if (!super.a.n) {
                        super.a.n = true;
                    }
                    super.a.b(this.a());
                    break;
                }
                break;
            }
            case 1001: {
                if (az != null) {
                    if ((super.a.a(43) && event.target == super.a) || event.target == this.b) {
                        super.a.a(null, (ai)az);
                        return true;
                    }
                    if (event.target == this.f) {
                        final r r;
                        (r = new r(67074, 1)).a(0, 0, super.a.i);
                        r.e = az.i;
                        super.a.o(r);
                        return true;
                    }
                    if (event.target == this.a) {
                        this.c(az);
                        return true;
                    }
                    if (event.target == this.e) {
                        this.a.addElement(String.valueOf(az.i));
                        a(this.e);
                        final r r2;
                        (r2 = new r(67349, 1)).e = az.i;
                        super.a.o(r2);
                        return true;
                    }
                    if (event.target instanceof CheckboxMenuItem) {
                        final CheckboxMenuItem checkboxMenuItem;
                        if ((checkboxMenuItem = (CheckboxMenuItem)event.target).getLabel().equals("flag")) {
                            this.b(az);
                            return true;
                        }
                        if (checkboxMenuItem.getLabel().equals("Ignore")) {
                            this.c(az);
                            return true;
                        }
                        return true;
                    }
                    else if (event.target instanceof MenuItem) {
                        final MenuItem menuItem;
                        if ((menuItem = (MenuItem)event.target).getLabel().equals("private chat")) {
                            if (super.a.a(43)) {
                                super.a.a(null, (ai)az);
                            }
                            return true;
                        }
                        if (menuItem.getLabel().equals("Profile")) {
                            final r r3;
                            (r3 = new r(67074, 1)).a(0, 0, super.a.i);
                            r3.e = az.i;
                            super.a.o(r3);
                            return true;
                        }
                        return true;
                    }
                    else {
                        if (event.target == this.c) {
                            this.b(az);
                            return true;
                        }
                        if (event.target == this.d) {
                            new bf(super.a, az);
                        }
                    }
                }
                if (event.target == this.a || event.target == this.b) {
                    super.a.u = this.b();
                    super.a.a(this.b());
                    super.a.b(this.a());
                    break;
                }
                break;
            }
            case 701: {
                final ai ai = (ai)event.arg;
                b(this.c);
                b(this.b);
                b(this.f);
                if (!this.a.contains(String.valueOf(ai.i))) {
                    b(this.e);
                }
                else {
                    a(this.e);
                }
                if (ai.a(33)) {
                    a(this.a);
                }
                else {
                    b(this.a);
                }
                if (ai.a(34) || az.i == super.a.i) {
                    if (this.d != null) {
                        a(this.d);
                    }
                }
                else if (super.a.a(44) && this.d != null) {
                    b(this.d);
                }
                return true;
            }
            case 702: {
                a(this.c);
                a(this.b);
                a(this.f);
                a(this.a);
                a(this.e);
                if (this.d != null) {
                    a(this.d);
                }
                return true;
            }
            default: {
                if (az == null) {
                    break;
                }
                if (az.a(33)) {
                    a(this.a);
                }
                else {
                    b(this.a);
                }
                if (az.a(34) || az.i == super.a.i) {
                    if (this.d != null) {
                        a(this.d);
                        break;
                    }
                    break;
                }
                else {
                    if (az.a(44) && this.d != null) {
                        b(this.d);
                        break;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private void b(final aZ az) {
        final String d = az.d;
        az.c = !az.c;
        az.d = false;
        super.a.a.removeElement(d);
        if (az.c) {
            if (!super.a.b.contains(d)) {
                super.a.b.addElement(d);
            }
        }
        else {
            super.a.b.removeElement(d);
        }
        this.postEvent(new Event(this.c, 7689, this.a((Object)this.c)));
        this.a(az);
    }
    
    private void c(final aZ az) {
        final String d = az.d;
        az.d = !az.d;
        az.c = false;
        if (az.d) {
            if (!super.a.a.contains(d)) {
                super.a.a.addElement(d);
            }
        }
        else {
            super.a.a.removeElement(d);
        }
        this.postEvent(new Event(this.a, 7689, this.a((Object)this.a)));
        this.a(az);
    }
    
    public final void a(final cx a) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f = null;
        this.a = null;
        this.e = null;
        this.a = new Vector();
        super.a = a;
        this.setBackground(a.a.h);
        this.setForeground(a.a.g);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final String c = a.a.c();
        this.a = this.a(c, "mute", "muteUserIcon.GIF");
        if (a.a(43)) {
            this.b = this.a(c, "private", "whisperIcon.GIF");
        }
        this.c = this.a(c, "flag", "flagUserIcon.GIF");
        if (a.a(44)) {
            this.d = this.a(c, "kick", "kickUserIcon.GIF");
        }
        this.e = this.a(c, "report", "reportIcon.GIF");
        this.f = this.a(c, "profile", "userInfoIcon.GIF");
        if (a.a.d() && a.b()) {
            final Image a2 = a.a(c + "allusers_unchecked.gif", true);
            final Image a3 = a.a(c + "allusers_checked.gif", true);
            if (a2 != null && a3 != null) {
                this.a = new bI(a2, a3);
            }
        }
        if (a.a.d() && a.b() && (a.a(52) || a.a(68))) {
            final Image a4 = a.a(c + "report_unchecked.gif", true);
            final Image a5 = a.a(c + "report_checked.gif", true);
            if (a4 != null && a5 != null) {
                this.b = new bI(a4, a5);
            }
        }
        if ((a.a(52) || a.a(68)) && this.b == null) {
            (this.b = n.b(aS.a(575))).addMouseListener(this);
        }
        if (this.a == null) {
            this.a = n.b(aS.a(274));
            if (ce.d) {
                this.a.addMouseListener(this);
            }
        }
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        layout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        layout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        if (a.a(43)) {
            layout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        layout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        if (!a.a(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        else {
            layout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        if (a.a(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.d, gridBagConstraints);
            this.add(this.d);
        }
        if (a.b() && this.a instanceof bI) {
            final Container container;
            (container = new Panel()).setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.a, gridBagConstraints);
            container.add(this.a);
            final bi bi = new bi(aS.a(274), (byte)0);
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            layout.setConstraints(bi, gridBagConstraints);
            container.add(bi);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(container, gridBagConstraints);
            this.add(container);
        }
        else if (a.b()) {
            this.a.setForeground(a.a.g);
            this.a.setFont(this.getFont());
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            layout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        if ((a.a(52) || a.a(68)) && a.b() && this.b instanceof bI) {
            final Container container2;
            (container2 = new Panel()).setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.b, gridBagConstraints);
            container2.add(this.b);
            final bi bi2 = new bi(aS.a(575), (byte)0);
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            layout.setConstraints(bi2, gridBagConstraints);
            container2.add(bi2);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(container2, gridBagConstraints);
            this.add(container2);
        }
        else if ((a.a(52) || a.a(68)) && a.b()) {
            this.b.setForeground(a.a.g);
            this.b.setFont(this.getFont());
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            layout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        this.a = new TextField(20);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        this.a.setVisible(r.a(super.a.h, 32));
        final i i = new i(this.a);
        this.a.setBackground(a.a.h.darker());
        this.a.setForeground(a.a.g.brighter());
        layout.setConstraints(i, gridBagConstraints);
        this.add(i);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final i j = new i(super.a);
        layout.setConstraints(j, gridBagConstraints);
        this.add(j);
    }
    
    public aI(final cx cx) {
        this.a(cx);
        final I i = new I(null, "icon");
        final I j = new I(aS.a(5), "name");
        i.c(28);
        i.b(0);
        j.b = true;
        super.a.a();
        super.a.b(i);
        super.a.b(j);
        super.a.a(j);
        super.a.b(26);
        super.a.c(cx.a(21));
    }
}
