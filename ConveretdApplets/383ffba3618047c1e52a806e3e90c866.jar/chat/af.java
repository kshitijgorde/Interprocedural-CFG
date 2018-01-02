// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.TextComponent;
import java.awt.FontMetrics;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.awt.Graphics;
import java.net.URL;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.util.Hashtable;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.TextArea;

public final class af extends aQ implements ab, ar
{
    private ag a;
    public aM a;
    private aM c;
    private l b;
    ah a;
    private Z a;
    public bl a;
    public TextArea a;
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Canvas d;
    private Panel a;
    private Canvas e;
    private aR a;
    private al a;
    private Image a;
    private int a;
    private int b;
    private int c;
    private int d;
    private Dimension a;
    private Insets a;
    private Image b;
    aJ a;
    private g a;
    private g b;
    private Hashtable a;
    private int e;
    private az a;
    
    private final Canvas a(final String s, final String s2, final String s3, final String a) {
        ab a2 = null;
        if (this.a.a.d() && s != null) {
            final Image a3 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a4 = this.a.a(s + s2 + "_button_dn.gif", true);
            if (a3 != null && a4 != null) {
                this.prepareImage(a3, this);
                this.prepareImage(a4, this);
                ((aN)(a2 = aN.a(a3, a4, null))).a = a;
            }
        }
        if (a2 == null) {
            if (s3 == null) {
                a2 = new i(70, 20);
            }
            else {
                a2 = new i(s3, 70, 20);
            }
            ((i)a2).a(a, null);
        }
        return (i)a2;
    }
    
    private final Panel a(final String s) {
        try {
            final aI ai;
            (ai = new aI(25, 25, this.a, this.a)).a(this.a.a(s, false, 20));
            return ai;
        }
        catch (Exception ex) {
            return new Panel();
        }
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        try {
            ab a;
            if (!this.a.a.d() || s == null) {
                ((i)(a = new i(25, 25))).a(this.a.a(s3, false, 20));
            }
            else {
                final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
                final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
                final Image a4 = this.a.a(s + s2 + "_button_disabled.gif", true);
                if (a2 == null || a3 == null || a4 == null) {
                    ((i)(a = new i(25, 25))).a(this.a.a(s3, false, 20));
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
        catch (Exception ex) {
            return new Canvas();
        }
    }
    
    public final void a(final c c) {
        this.a.a(c);
    }
    
    public final void a(final int n) {
        this.a.a(n);
    }
    
    public final void a(final ao ao) {
        this.a.a(ao);
    }
    
    public final void a() {
        this.a.a = true;
        if (aV.a) {
            super.setVisible(false);
            aV.a.remove(this);
            aV.a.setVisible(true);
            aV.a.validate();
        }
    }
    
    public final void b() {
        final boolean b = this.a.s.a() > 0;
        if (b && !this.c.isShowing()) {
            this.c.show();
            this.validate();
        }
        else if (!b && this.c.isShowing()) {
            this.c.hide();
            this.validate();
        }
        this.a.b = this.a.a.r;
        this.a.a = this.a.a.q;
        if (this.a.a == 0) {
            this.a.a = this.c.size().width;
        }
        this.a.b = (this.a.D != 1);
        this.a.c = 30 - this.a.E * 5;
        this.a.a = this.a.a.a;
        try {
            if (this.a.s.a() > 0) {
                if (!this.a.isVisible()) {
                    this.a.setVisible(true);
                    this.a.a(this.c.size().width, this.getFontMetrics(this.a.a.b()).getHeight());
                }
                final az a;
                (a = this.a).c = true;
                if (a.a == null) {
                    a.a = new Thread(a);
                }
                if (!a.a.isAlive()) {
                    a.a.start();
                }
                return;
            }
            this.a.setVisible(false);
            this.a.a();
        }
        catch (Exception ex) {}
    }
    
    public final ag a() {
        return this.a;
    }
    
    public final Frame a() {
        return this.a;
    }
    
    public final Container a() {
        return this;
    }
    
    public final void c() {
        if (this.a.a(14) && this.a != null) {
            ((aI)this.a).a(1);
        }
    }
    
    public final String a(final Object o) {
        try {
            if (o == this.a) {
                return ak.a(438);
            }
            if (o == this.c) {
                return ak.a(442);
            }
            if (o == this.d) {
                return ak.a(443);
            }
            if (o == this.b) {
                return ak.a(ak.a(444), new String[] { this.b.a().toString() });
            }
            if (o == this.a) {
                return ak.a(445);
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final void a(final boolean b) {
        this.a.a(b);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (this.e instanceof i) {
                    ((i)this.e).c();
                }
                else {
                    ((aN)this.e).a();
                }
                return true;
            }
            case 401: {
                if (event.key == 27) {
                    this.a.appendText("\n");
                    break;
                }
                if (event.modifiers == 2) {
                    if (event.key == 4) {
                        this.a.h();
                        return true;
                    }
                    if (event.key == 5) {
                        final M m;
                        (m = new M(this.a.a.a(), this.a, this.a)).a();
                        m.setVisible(true);
                        return true;
                    }
                    if (event.key == 18) {
                        final N n;
                        (n = new N(this.a.a.a(), this.a, this.a)).a();
                        n.setVisible(true);
                        return true;
                    }
                    if (event.key == 1 && !aZ.d) {
                        this.a.setSelectionStart(0);
                        this.a.setSelectionEnd(this.a.getText().length());
                        return true;
                    }
                    if (event.key == 10) {
                        this.a.append("\n");
                        this.a.setCaretPosition(this.a.getText().length());
                        return true;
                    }
                }
                if (event.key == 10 || event.key == aZ.a) {
                    if (this.b instanceof i) {
                        ((i)this.b).c();
                    }
                    else {
                        ((aN)this.b).a();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    String s = this.a.getText().trim();
                    this.a.setText("");
                    if (s.length() > 0) {
                        if (s.length() > this.a.v) {
                            s = s.substring(0, this.a.v);
                        }
                        if (System.currentTimeMillis() - this.a.a() < this.a.b()) {
                            try {
                                Thread.sleep(this.a.b() - (System.currentTimeMillis() - this.a.a()));
                            }
                            catch (InterruptedException ex) {}
                        }
                        final String s2 = s;
                        if (this.a.s != 0 || this.a.t != 0) {
                            this.a.put(new Integer(this.e), new Object[] { new Long(System.currentTimeMillis()), s2 });
                            final Object[] array;
                            if (this.a.t != 0 && this.a.u != 0 && (array = this.a.get(new Integer(this.e - this.a.t + 1))) != null && System.currentTimeMillis() - (long)array[0] < this.a.u * 1000) {
                                this.a.g();
                            }
                            if (this.a.s != 0) {
                                int n2 = 1;
                                Object[] array2;
                                for (int n3 = this.e - 1; n3 > this.e - this.a.s && (array2 = this.a.get(new Integer(n3))) != null; --n3) {
                                    if (((String)array2[1]).equals(s2)) {
                                        ++n2;
                                    }
                                }
                                if (n2 >= this.a.s) {
                                    this.a.g();
                                }
                            }
                            if (this.a.s > this.a.t) {
                                this.a.remove(new Integer(this.e - this.a.s));
                            }
                            else if (this.a.t > 0) {
                                this.a.remove(new Integer(this.e - this.a.t));
                            }
                            ++this.e;
                        }
                        if (this.a.a(14)) {
                            this.a.a(s, ((aI)this.a).a.a, ((aI)this.a).a);
                        }
                        else {
                            this.a.b(s);
                        }
                        this.a.c();
                    }
                    if (aZ.a) {
                        this.a.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.e) {
                    this.a.c();
                    return true;
                }
                if (event.target == this.a) {
                    this.a.d(0);
                    return true;
                }
                if (event.target == this.c) {
                    final N n4;
                    (n4 = new N(this.a.a.a(), this.a, this.a)).a();
                    n4.setVisible(true);
                    return true;
                }
                if (event.target == this.d) {
                    final M i;
                    (i = new M(this.a.a.a(), this.a, this.a)).a();
                    i.setVisible(true);
                    return true;
                }
                if (event.target instanceof MenuItem) {
                    return this.a.a(event);
                }
                if (event.arg instanceof ao) {
                    this.a.c.b(((ao)event.arg).e);
                }
                if (event.arg instanceof ao) {
                    final ao ao = (ao)event.arg;
                    final au au = (au)this.a.c.b(ao.e);
                    if ((!ao.c || ao.e == this.a.g) && this.a.a(43) && au != null && ao.b && ao.f == -1) {
                        this.a.a(ao, au);
                        return true;
                    }
                    break;
                }
                else {
                    if (event.arg instanceof URL) {
                        this.a.a((URL)event.arg, "_blank");
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.a != null) {
            if (this.b == null || this.size().height != this.a.height || this.size().width != this.a.width) {
                this.a = this.size();
                this.a = this.insets();
                this.a = this.a.width - this.a.right - this.a.left + 30;
                this.b = this.a.height - this.a.bottom - this.a.top + 30;
                this.c = this.a.getWidth(this);
                this.d = this.a.getHeight(this);
                if (this.b != null) {
                    try {
                        this.b.flush();
                    }
                    catch (SecurityException ex) {}
                }
                this.b = this.createImage(this.a, this.b);
            }
            final Graphics graphics2;
            (graphics2 = this.b.getGraphics()).drawImage(this.a, 0, 0, null);
            for (int i = 0; i <= this.a / this.c; ++i) {
                for (int j = 0; j <= this.b / this.d; ++j) {
                    if (i + j > 0) {
                        graphics2.copyArea(0, 0, this.c, this.d, i * this.c, j * this.d);
                    }
                }
            }
            graphics2.dispose();
            graphics.drawImage(this.b, 0, 0, this);
            this.b.a(this.b);
        }
    }
    
    public final void d() {
        this.a.b();
        this.a.setFont(this.a.a.b());
    }
    
    public final boolean a(final ai ai) {
        return this.a.a(ai);
    }
    
    public final boolean a(final au au) {
        return this.a.a(au);
    }
    
    public final String a() {
        if (this.a != null && this.a.a != null) {
            return this.a.a.getText();
        }
        return "";
    }
    
    public final void a(final String s) {
        try {
            this.b.a(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public final void a(final ai ai) {
        if (this.a != null) {
            final aJ a = this.a;
            a.setTitle(this.a.a.a + ": " + ai.c + ": " + a.a.a.c);
        }
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public final void e() {
        this.a.a(0);
        final ah a;
        if ((a = this.a).a instanceof aO) {
            final aO ao;
            if (!(ao = (aO)a.a).a) {
                ao.a = true;
                ao.c = (ao.a ? ao.b : ao.a);
                ao.repaint();
                ao.postEvent(new Event(ao, 1001, null));
            }
            return;
        }
        s.a(a.a, true);
    }
    
    public final void b(final ai ai) {
        this.a.a(ai);
    }
    
    public final void a(final au au, final boolean b) {
        this.a.a(au, b);
    }
    
    public final void b(final boolean b) {
        if (b) {
            this.b.b(this.a.a.b);
            return;
        }
        this.b.b(null);
        this.b.a(1, 1);
    }
    
    public af(final bl a, final aJ a2) {
        this.a = new Hashtable();
        this.e = 0;
        this.a = a2;
        this.setBackground(a.a.a);
        this.a = a;
        a.a = new MediaTracker(this);
        bh.b = a.a("lockIcon.gif", false, 20);
        bh.g = a.a("arrow.gif", false, 25);
        bh.c = a.a("em.gif", false, 21);
        bh.d = a.a("wtc.gif", false, 22);
        bh.e = a.a("bold.gif", false, 23);
        bh.f = a.a("italic.gif", false, 24);
        a.a.a = a.a(a.a.b() + "helpLogo.gif", true, 12);
        a.a.b = a.a(a.a.b() + "chatLogo.gif", true, 10);
        (bh.a = new Image[7])[1] = a.a("busy.gif", false, 49);
        bh.a[2] = a.a("away.gif", false, 49);
        bh.a[3] = a.a("call.gif", false, 49);
        bh.a[4] = a.a("vb.gif", false, 49);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final aM am = new aM(a.a.c);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        am.setBackground(a.a.b);
        this.setLayout(layout);
        am.setLayout(layout);
        try {
            this.a = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.a = new TextArea(2, 10);
        }
        this.a = new az(a);
        this.a.b = 13;
        this.a.a = true;
        (this.c = new aM(2, 2, 2, 2, 1)).setLayout(layout);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.a, gridBagConstraints);
        this.c.add(this.a);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.c, gridBagConstraints);
        if (a.a.d()) {
            panel.add(this.c);
        }
        else {
            am.add(this.c);
        }
        this.c.hide();
        (this.a = new al(a, true)).setFont(a.a.b());
        final boolean b;
        if (!(b = (a.b() || a.c())) || a.a.d()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.a = new g(this.a);
        layout.setConstraints(this.b = new g(this.a), gridBagConstraints);
        if (a.a.d()) {
            panel.add(this.b);
        }
        else {
            am.add(this.b);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        Image a3 = null;
        Image a4 = null;
        Image a5 = null;
        Image a6 = null;
        if (a.a.e()) {
            final String b2 = a.a.b();
            a3 = a.a(b2 + "users_tab_up.gif", true);
            a4 = a.a(b2 + "users_tab_down.gif", true);
            a5 = a.a(b2 + "rooms_tab_up.gif", true);
            a6 = a.a(b2 + "rooms_tab_down.gif", true);
            if (a3 == null || a4 == null || a5 == null || a6 == null) {
                a.a.b();
            }
        }
        this.a = new aR(a, a.a.e());
        this.a = new ah(a);
        this.a = new Z(a);
        if (a.c()) {
            this.a.a(ak.a(19), this.a, a3, a4);
        }
        if (a.b()) {
            this.a.a(ak.a(18), this.a, a5, a6);
        }
        layout.setConstraints(this.a, gridBagConstraints);
        if (b) {
            if (a.a.d()) {
                panel2.add(this.a);
            }
            else {
                am.add(this.a);
            }
        }
        this.a.setFont(a.a.b());
        ((TextComponent)(this.a = new g(this.a))).setBackground((!aZ.c || aZ.b == 4) ? a.a.f : a.a.f.darker());
        this.a.setForeground(a.a.e);
        final String b3 = a.a.b();
        this.c = this.a(b3, "chatwich", "wtc.gif");
        this.d = this.a(b3, "emoticons", "em.gif");
        this.a = this.a("m1.gif");
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final Panel panel3 = new Panel();
        final Panel panel4 = new Panel();
        panel3.setLayout(gridBagLayout);
        panel4.setLayout(gridBagLayout);
        gridBagConstraints2.insets = new Insets(0, 1, 1, 1);
        if (this.a.a(14)) {
            gridBagConstraints2.fill = 1;
            gridBagConstraints2.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints2);
            panel3.add(this.a);
            ((aI)this.a).b();
            ((aI)this.a).a(2);
        }
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridwidth = (this.a.a(14) ? 1 : 0);
        gridBagConstraints2.anchor = 17;
        gridBagLayout.setConstraints(this.c, gridBagConstraints2);
        panel3.add(this.c);
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.anchor = 17;
        gridBagLayout.setConstraints(this.d, gridBagConstraints2);
        panel3.add(this.d);
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.gridwidth = -1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints2);
        panel4.add(this.a);
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagLayout.setConstraints(panel3, gridBagConstraints2);
        panel4.add(panel3);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(panel4, gridBagConstraints);
        if (a.a.d()) {
            panel.add(panel4);
        }
        else {
            am.add(panel4);
        }
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (a.a.d() ? 0.0 : 1.0E-5);
        final String b4 = a.a.b();
        this.a = this.a(b4, "settings", ak.a(11), ak.a(446));
        this.b = this.a(b4, "send", null, ak.a(441));
        this.e = this.a(b4, "logout", ak.a(12), ak.a(ak.a(447), new String[] { this.a.a.a }));
        final Panel panel5 = a.a.d() ? new Panel() : null;
        if (a.a.d()) {
            panel5.setLayout(layout);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        }
        gridBagConstraints.gridwidth = (a.e ? 0 : -1);
        layout.setConstraints(this.a, gridBagConstraints);
        if (a.a.d()) {
            panel5.add(this.a);
        }
        else {
            am.add(this.a);
        }
        if (!a.e) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.e, gridBagConstraints);
            if (a.a.d()) {
                panel5.add(this.e);
            }
            else {
                am.add(this.e);
            }
        }
        if (!a.a.d()) {
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        if (!a.a.d()) {
            final f f = new f(this.b);
            layout.setConstraints(f, gridBagConstraints);
            am.add(f);
        }
        else {
            layout.setConstraints(this.b, gridBagConstraints);
            panel5.add(this.b);
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
            gridBagConstraints.gridheight = -1;
            gridBagConstraints.weightx = 1.0E-5;
            layout.setConstraints(panel5, gridBagConstraints);
            if (b) {
                panel2.add(panel5);
            }
            else {
                panel.add(panel5);
            }
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            layout.setConstraints(panel, gridBagConstraints);
            am.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            am.add(panel2);
        }
        if (this.b instanceof i) {
            ((i)this.b).a(ak.a(13));
        }
        this.a = new aM(2, 2, 2, 2, 0);
        this.a = new ag();
        this.a.a = ((a.r <= 0) ? 7 : a.r);
        this.a.setLayout(layout);
        layout.setConstraints(this.a, new GridBagConstraints());
        this.a.add(this.a);
        this.b = new l();
        if (m.a(a.b, 49)) {
            this.b.b(a.a.b);
        }
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final Container container;
        (container = new Panel()).setLayout(layout2);
        gridBagConstraints3.gridwidth = -1;
        layout2.setConstraints(this.b, gridBagConstraints3);
        container.add(this.b);
        gridBagConstraints3.gridwidth = 0;
        layout2.setConstraints(this.a, gridBagConstraints3);
        container.add(this.a);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(container, gridBagConstraints);
        this.add(container);
        this.a.setVisible(false);
        new X(this, a);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(am, gridBagConstraints);
        this.add(am);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final int c = a.a.c;
        if (super.b == null) {
            super.b = new aM(5, 7, 5, 7, c);
            super.a = new t();
            super.a = new l();
            super.b.setLayout(new BorderLayout(10, 3));
            super.b.add("West", super.a);
            super.b.add("Center", super.a);
            super.b.setFont(ay.c);
            super.a.a = false;
            final FontMetrics fontMetrics = this.getFontMetrics(ay.c);
            super.a.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        final aM b5;
        (b5 = super.b).setBackground(a.a.d);
        b5.setForeground(a.a.c);
        layout.setConstraints(b5, gridBagConstraints);
        this.add(b5);
        super.a.b(a.a.a);
        super.a.a((URL)null);
        final Dimension dimension;
        (dimension = new Dimension()).height = aV.a;
        this.resize(dimension.width = aV.b, dimension.height);
        if (a2 != null) {
            a.a(a2);
        }
        this.a = a.a.a();
    }
}
