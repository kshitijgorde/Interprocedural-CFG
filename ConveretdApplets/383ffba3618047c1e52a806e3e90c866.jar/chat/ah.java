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

public final class ah extends ac implements ab, MouseListener, ImageObserver
{
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Canvas d;
    private Canvas e;
    private Canvas f;
    Component a;
    public TextField a;
    private Vector a;
    
    private boolean a() {
        if (this.a instanceof aO) {
            return ((aO)this.a).a;
        }
        return s.a(this.a);
    }
    
    private static void a(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof i) {
            ((i)canvas).b();
            return;
        }
        ((aN)canvas).setEnabled(false);
    }
    
    private static void b(final Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (canvas instanceof i) {
            ((i)canvas).a();
            return;
        }
        ((aN)canvas).setEnabled(true);
    }
    
    public final void a(final boolean b) {
        super.a.a(b);
        super.a.a(this.a());
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        ab a;
        if (!super.a.a.d() || s == null) {
            ((i)(a = new i(28, 28))).a(super.a.a(s3, false, 20));
        }
        else {
            final Image a2 = super.a.a(s + s2 + "_button_up.gif", true);
            final Image a3 = super.a.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = super.a.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                ((i)(a = new i(28, 28))).a(super.a.a(s3, false, 20));
            }
            else {
                this.prepareImage(a2, this);
                this.prepareImage(a3, this);
                this.prepareImage(a4, this);
                a = aN.a(a2, a3, a4);
            }
        }
        return (i)a;
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
    
    public final String a(final Object o) {
        if (o instanceof i || o instanceof aN) {
            final aj aj;
            if ((aj = (aj)super.a.a()) == null) {
                return ak.a(283);
            }
            if (o == this.b) {
                return ak.a(ak.a(282), new String[] { aj.c });
            }
            if (o == this.e) {
                if (!this.a.contains(String.valueOf(aj.g))) {
                    return ak.a(ak.a(576), new String[] { aj.c });
                }
                return ak.a(ak.a(577), new String[] { aj.c });
            }
            else if (o == this.c) {
                if (aj.a) {
                    return ak.a(ak.a(281), new String[] { aj.c });
                }
                return ak.a(ak.a(280), new String[] { aj.c });
            }
            else {
                if (o == this.f) {
                    return ak.a(ak.a(279), new String[] { aj.c });
                }
                if (o == this.a) {
                    final Canvas a;
                    if ((a = this.a) != null && ((a instanceof i) ? ((i)a).a() : ((aN)a).isEnabled())) {
                        if (aj.b) {
                            return ak.a(ak.a(278), new String[] { aj.c });
                        }
                        return ak.a(ak.a(277), new String[] { aj.c });
                    }
                    else if (aj.a(33)) {
                        return ak.a(ak.a(276), new String[] { aj.c });
                    }
                }
                if (o == this.d) {
                    return ak.a(ak.a(275), new String[] { aj.c });
                }
            }
        }
        return null;
    }
    
    public final boolean handleEvent(final Event event) {
        final au au = (au)super.a.a();
        switch (event.id) {
            case 402: {
                if (event.target == this.a) {
                    if (this.a.getText().length() == 0 && super.a.j) {
                        super.a.j = false;
                    }
                    else if (!super.a.j) {
                        super.a.j = true;
                    }
                    super.a.a(this.a());
                    break;
                }
                break;
            }
            case 1001: {
                if (au != null) {
                    if ((super.a.a(43) && event.target == super.a) || event.target == this.b) {
                        super.a.a(null, au);
                        return true;
                    }
                    if (event.target == this.f) {
                        final m m;
                        (m = new m(67074, 1)).a(0, 0, super.a.g);
                        m.e = au.g;
                        super.a.m(m);
                        return true;
                    }
                    if (event.target == this.a) {
                        this.c(au);
                        return true;
                    }
                    if (event.target == this.e) {
                        this.a.addElement(String.valueOf(au.g));
                        a(this.e);
                        final m i;
                        (i = new m(67349, 1)).e = au.g;
                        super.a.m(i);
                        return true;
                    }
                    if (event.target instanceof CheckboxMenuItem) {
                        final CheckboxMenuItem checkboxMenuItem;
                        if ((checkboxMenuItem = (CheckboxMenuItem)event.target).getLabel().equals("flag")) {
                            this.b(au);
                            return true;
                        }
                        if (checkboxMenuItem.getLabel().equals("Ignore")) {
                            this.c(au);
                            return true;
                        }
                        return true;
                    }
                    else if (event.target instanceof MenuItem) {
                        final MenuItem menuItem;
                        if ((menuItem = (MenuItem)event.target).getLabel().equals("private chat")) {
                            if (super.a.a(43)) {
                                super.a.a(null, au);
                            }
                            return true;
                        }
                        if (menuItem.getLabel().equals("Profile")) {
                            final m j;
                            (j = new m(67074, 1)).a(0, 0, super.a.g);
                            j.e = au.g;
                            super.a.m(j);
                            return true;
                        }
                        return true;
                    }
                    else if (event.target == this.c) {
                        this.b(au);
                        return true;
                    }
                }
                if (event.target == this.a) {
                    super.a.a(this.a());
                    break;
                }
                break;
            }
            case 701: {
                final aj aj = (aj)event.arg;
                b(this.c);
                b(this.b);
                b(this.f);
                if (!this.a.contains(String.valueOf(aj.g))) {
                    b(this.e);
                }
                else {
                    a(this.e);
                }
                if (aj.a(33)) {
                    a(this.a);
                }
                else {
                    b(this.a);
                }
                if (aj.a(34) || au.g == super.a.g) {
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
                if (au == null) {
                    break;
                }
                if (au.a(33)) {
                    a(this.a);
                }
                else {
                    b(this.a);
                }
                if (au.a(34) || au.g == super.a.g) {
                    if (this.d != null) {
                        a(this.d);
                        break;
                    }
                    break;
                }
                else {
                    if (au.a(44) && this.d != null) {
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
    
    private void b(final au au) {
        final String c = au.c;
        au.a = !au.a;
        au.b = false;
        super.a.a.removeElement(c);
        if (au.a) {
            if (!super.a.b.contains(c)) {
                super.a.b.addElement(c);
            }
        }
        else {
            super.a.b.removeElement(c);
        }
        this.postEvent(new Event(this.c, 7689, this.a((Object)this.c)));
        this.a(au);
    }
    
    private void c(final au au) {
        final String c = au.c;
        au.b = !au.b;
        au.a = false;
        if (au.b) {
            if (!super.a.a.contains(c)) {
                super.a.a.addElement(c);
            }
        }
        else {
            super.a.a.removeElement(c);
        }
        this.postEvent(new Event(this.a, 7689, this.a((Object)this.a)));
        this.a(au);
    }
    
    public final void a(final bl a) {
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
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String b = a.a.b();
        this.a = this.a(b, "mute", "muteUserIcon.GIF");
        if (a.a(43)) {
            this.b = this.a(b, "private", "whisperIcon.GIF");
        }
        this.c = this.a(b, "flag", "flagUserIcon.GIF");
        if (a.a(44)) {
            this.d = this.a(b, "kick", "kickUserIcon.GIF");
        }
        this.e = this.a(b, "report", "reportIcon.GIF");
        this.f = this.a(b, "profile", "userInfoIcon.GIF");
        if (a.a.d() && a.b()) {
            final Image a2 = a.a(b + "allusers_unchecked.gif", true);
            final Image a3 = a.a(b + "allusers_checked.gif", true);
            if (a2 != null && a3 != null) {
                this.a = new aO(a2, a3);
            }
        }
        if (this.a == null) {
            this.a = s.b(ak.a(274));
            if (aZ.d) {
                this.a.addMouseListener(this);
            }
        }
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        gridBagLayout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        if (a.a(43)) {
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        if (!a.a(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        else {
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        if (a.a(44)) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            this.add(this.d);
        }
        if (a.b() && this.a instanceof aO) {
            final Container container;
            (container = new Panel()).setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            container.add(this.a);
            final aw aw = new aw(ak.a(274), (byte)0);
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagLayout.setConstraints(aw, gridBagConstraints);
            container.add(aw);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(container, gridBagConstraints);
            this.add(container);
        }
        else if (a.b()) {
            this.a.setForeground(a.a.g);
            this.a.setFont(this.getFont());
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        this.a = new TextField(20);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        this.a.setVisible(m.a(super.a.d, 32));
        final g g = new g(this.a);
        this.a.setBackground(a.a.h.darker());
        this.a.setForeground(a.a.g.brighter());
        gridBagLayout.setConstraints(g, gridBagConstraints);
        this.add(g);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final g g2 = new g(super.a);
        gridBagLayout.setConstraints(g2, gridBagConstraints);
        this.add(g2);
    }
    
    public ah(final bl bl) {
        this.a(bl);
        final w w = new w(null, "icon");
        final w w2 = new w(ak.a(5), "name");
        w.c(28);
        w.b(0);
        w2.a = true;
        super.a.a();
        super.a.b(w);
        super.a.b(w2);
        super.a.a(w2);
        super.a.b(26);
        super.a.b(bl.a(21));
    }
}
