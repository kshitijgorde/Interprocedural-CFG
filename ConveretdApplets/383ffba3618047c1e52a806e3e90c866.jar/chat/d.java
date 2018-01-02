// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Container;
import java.awt.TextComponent;
import java.awt.FontMetrics;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Hashtable;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.MenuItem;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.TextArea;

public final class d extends bi implements ab
{
    private l b;
    e a;
    private bl a;
    public int a;
    private TextArea a;
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Panel a;
    private Canvas d;
    private al a;
    private Image a;
    private int b;
    private int c;
    private int d;
    private int e;
    private Dimension a;
    private Insets a;
    private Image b;
    private g a;
    private g b;
    
    private final Canvas a(final String s, final String s2, final String s3, final String a) {
        ab a2 = null;
        if (this.a.a.d() && s != null) {
            final Image a3 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a4 = this.a.a(s + s2 + "_button_dn.gif", true);
            if (a3 != null && a4 != null) {
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
                    a = aN.a(a2, a3, a4);
                }
            }
            return (i)a;
        }
        catch (Exception ex) {
            return new Canvas();
        }
    }
    
    public final void a(final ao ao) {
        this.a.a(ao);
    }
    
    public final String a(final Object o) {
        try {
            if (o == this.a) {
                return ak.a(438);
            }
            if (o == this.b) {
                return ak.a(442);
            }
            if (o == this.c) {
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
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
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
                        (m = new M(this, this.a, this.a)).a();
                        m.setVisible(true);
                        return true;
                    }
                    if (event.key == 18) {
                        final N n;
                        (n = new N(this, this.a, this.a)).a();
                        n.setVisible(true);
                        return true;
                    }
                    if (event.key == 1 && aZ.c <= 66048) {
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
                    if (this.a instanceof i) {
                        ((i)this.a).c();
                    }
                    else {
                        ((aN)this.a).a();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
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
                        if (this.a.a(14)) {
                            this.a.a(s, ((aI)this.a).a.a, ((aI)this.a).a, this.a);
                        }
                        else {
                            this.a.a(s, 0, 0, this.a);
                        }
                        this.a.c();
                    }
                    if (aZ.a) {
                        this.a.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.d) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    final N n2;
                    (n2 = new N(this, this.a, this.a)).a();
                    n2.setVisible(true);
                    return true;
                }
                if (event.target == this.c) {
                    final M i;
                    (i = new M(this, this.a, this.a)).a();
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
                this.b = this.a.width - this.a.right - this.a.left + 30;
                this.c = this.a.height - this.a.bottom - this.a.top + 30;
                this.d = this.a.getWidth(this);
                this.e = this.a.getHeight(this);
                if (this.b != null) {
                    try {
                        this.b.flush();
                    }
                    catch (SecurityException ex) {}
                }
                this.b = this.createImage(this.b, this.c);
            }
            final Graphics graphics2;
            (graphics2 = this.b.getGraphics()).drawImage(this.a, 0, 0, null);
            for (int i = 0; i <= this.b / this.d; ++i) {
                for (int j = 0; j <= this.c / this.e; ++j) {
                    if (i + j > 0) {
                        graphics2.copyArea(0, 0, this.d, this.e, i * this.d, j * this.e);
                    }
                }
            }
            graphics2.dispose();
            graphics.drawImage(this.b, 0, 0, this);
            this.b.a(this.b);
        }
    }
    
    public final void dispose() {
        super.dispose();
        if (this.a != -999) {
            this.a.c(this.a);
        }
        this.a.t.b(this.a);
    }
    
    public d(final bl a, int layout) {
        new Hashtable();
        this.setBackground(a.a.a);
        this.setTitle(a.b() + ": " + ak.a(655));
        this.a = a;
        this.a = a2;
        layout = (int)new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final aM am = new aM(a.a.c);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout((LayoutManager)layout);
        panel2.setLayout((LayoutManager)layout);
        am.setBackground(a.a.b);
        this.setLayout((LayoutManager)layout);
        am.setLayout((LayoutManager)layout);
        try {
            this.a = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.a = new TextArea(2, 10);
        }
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
        ((GridBagLayout)layout).setConstraints(this.b = new g(this.a), gridBagConstraints);
        if (a.a.d()) {
            panel.add(this.b);
        }
        else {
            am.add(this.b);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        if (a.a.e()) {
            final String b2 = a.a.b();
            final Image a3 = a.a(b2 + "users_tab_up.gif", true);
            final Image a4 = a.a(b2 + "users_tab_down.gif", true);
            final Image a5 = a.a(b2 + "rooms_tab_up.gif", true);
            final Image a6 = a.a(b2 + "rooms_tab_down.gif", true);
            if (a3 == null || a4 == null || a5 == null || a6 == null) {
                a.a.b();
            }
        }
        ((GridBagLayout)layout).setConstraints(this.a = new e(this, a), gridBagConstraints);
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
        this.b = this.a(b3, "chatwich", "wtc.gif");
        this.c = this.a(b3, "emoticons", "em.gif");
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
        gridBagLayout.setConstraints(this.b, gridBagConstraints2);
        panel3.add(this.b);
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.anchor = 17;
        gridBagLayout.setConstraints(this.c, gridBagConstraints2);
        panel3.add(this.c);
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
        ((GridBagLayout)layout).setConstraints(panel4, gridBagConstraints);
        if (a.a.d()) {
            panel.add(panel4);
        }
        else {
            am.add(panel4);
        }
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (a.a.d() ? 0.0 : 1.0E-5);
        final String b4 = a.a.b();
        this.a = this.a(b4, "send", null, ak.a(656));
        this.d = this.a(b4, "logout", ak.a(12), ak.a(657));
        final Panel panel5 = a.a.d() ? new Panel() : null;
        if (a.a.d()) {
            panel5.setLayout((LayoutManager)layout);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        }
        gridBagConstraints.gridwidth = 0;
        if (!a.a.d()) {
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        if (!a.a.d()) {
            final f f = new f(this.a);
            ((GridBagLayout)layout).setConstraints(f, gridBagConstraints);
            am.add(f);
        }
        else {
            ((GridBagLayout)layout).setConstraints(this.a, gridBagConstraints);
            panel5.add(this.a);
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
            gridBagConstraints.gridheight = -1;
            gridBagConstraints.weightx = 1.0E-5;
            ((GridBagLayout)layout).setConstraints(panel5, gridBagConstraints);
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
            ((GridBagLayout)layout).setConstraints(panel, gridBagConstraints);
            am.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            ((GridBagLayout)layout).setConstraints(panel2, gridBagConstraints);
            am.add(panel2);
        }
        if (this.a instanceof i) {
            ((i)this.a).a(ak.a(13));
        }
        (this.b = new l()).b(a.a.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ((GridBagLayout)layout).setConstraints(am, gridBagConstraints);
        this.add(am);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final int c = a.a.c;
        if (super.a == null) {
            super.a = new aM(5, 7, 5, 7, c);
            super.a = new t();
            ((Container)(super.a = new l())).setLayout(new BorderLayout(10, 3));
            super.a.add("West", super.a);
            super.a.add("Center", super.a);
            super.a.setFont(ay.c);
            super.a.a = false;
            final FontMetrics fontMetrics = this.getFontMetrics(ay.c);
            super.a.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        final aM a7;
        (a7 = super.a).setBackground(a.a.d);
        a7.setForeground(a.a.c);
        ((GridBagLayout)layout).setConstraints(a7, gridBagConstraints);
        this.add(a7);
        super.a.b(a.a.a);
        super.a.a((URL)null);
        final Dimension dimension;
        (dimension = new Dimension()).height = aV.a;
        this.resize(dimension.width = aV.b, dimension.height);
        this.a = a.a.a();
    }
}
