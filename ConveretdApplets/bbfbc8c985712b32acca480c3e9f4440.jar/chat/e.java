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

public final class e extends cu implements aB
{
    private q b;
    f a;
    private cx a;
    public int a;
    private TextArea a;
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Panel a;
    private Canvas d;
    private aR a;
    private Image a;
    private int b;
    private int c;
    private int d;
    private int e;
    private Dimension a;
    private Insets a;
    private Image b;
    private i a;
    private i b;
    
    private final Canvas a(final String s, final String s2, final String s3, final String a) {
        aB a2 = null;
        if (this.a.a.d() && s != null) {
            final Image a3 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a4 = this.a.a(s + s2 + "_button_dn.gif", true);
            if (a3 != null && a4 != null) {
                ((bG)(a2 = bG.a(a3, a4, null))).a = a;
            }
        }
        if (a2 == null) {
            if (s3 == null) {
                a2 = new cr(70, 20);
            }
            else {
                a2 = new cr(s3, 70, 20);
            }
            ((cr)a2).a(a, null);
        }
        return (cr)a2;
    }
    
    private final Panel a(final String s) {
        try {
            final bz bz;
            (bz = new bz(25, 25, this.a, this.a)).a(this.a.a(s, false, 20));
            return bz;
        }
        catch (Exception ex) {
            return new Panel();
        }
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        try {
            aB a;
            if (!this.a.a.d() || s == null) {
                ((cr)(a = new cr(25, 25))).a(this.a.a(s3, false, 20));
            }
            else {
                final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
                final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
                final Image a4 = this.a.a(s + s2 + "_button_disabled.gif", true);
                if (a2 == null || a3 == null || a4 == null) {
                    ((cr)(a = new cr(25, 25))).a(this.a.a(s3, false, 20));
                }
                else {
                    a = bG.a(a2, a3, a4);
                }
            }
            return (cr)a;
        }
        catch (Exception ex) {
            return new Canvas();
        }
    }
    
    public final void a(final aU au) {
        this.a.a(au);
    }
    
    public final String a(final Object o) {
        try {
            if (o == this.a) {
                return aS.a(438);
            }
            if (o == this.b) {
                return aS.a(442);
            }
            if (o == this.c) {
                return aS.a(443);
            }
            if (o == this.b) {
                return bm.a(aS.a(444), new String[] { this.b.a().toString() });
            }
            if (o == this.a) {
                return aS.a(445);
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
                        this.a.f();
                        return true;
                    }
                    if (event.key == 5) {
                        final ah ah;
                        (ah = new ah(this, this.a, this.a)).e();
                        ah.setVisible(true);
                        return true;
                    }
                    if (event.key == 18) {
                        final aj aj;
                        (aj = new aj(this, this.a, this.a)).e();
                        aj.setVisible(true);
                        return true;
                    }
                    if (event.key == 1 && ce.c <= 66048) {
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
                if (event.key == 10 || event.key == ce.a) {
                    if (this.a instanceof cr) {
                        ((cr)this.a).c();
                    }
                    else {
                        ((bG)this.a).a();
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
                        if (s.length() > this.a.E) {
                            s = s.substring(0, this.a.E);
                        }
                        if (System.currentTimeMillis() - this.a.a() < this.a.b()) {
                            try {
                                Thread.sleep(this.a.b() - (System.currentTimeMillis() - this.a.a()));
                            }
                            catch (InterruptedException ex) {}
                        }
                        if (this.a.a(14)) {
                            this.a.a(s, ((bz)this.a).a.a, ((bz)this.a).a, this.a);
                        }
                        else {
                            this.a.a(s, 0, 0, this.a);
                        }
                        this.a.d();
                    }
                    if (ce.a) {
                        this.a.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.d) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.b) {
                    final aj aj2;
                    (aj2 = new aj(this, this.a, this.a)).e();
                    aj2.setVisible(true);
                    return true;
                }
                if (event.target == this.c) {
                    final ah ah2;
                    (ah2 = new ah(this, this.a, this.a)).e();
                    ah2.setVisible(true);
                    return true;
                }
                if (event.target instanceof MenuItem) {
                    return this.a.a(event);
                }
                final aZ az;
                if (event.arg instanceof aU && (az = (aZ)this.a.c.b(((aU)event.arg).e)) != null) {
                    if (event.modifiers == 2) {
                        final r r;
                        (r = new r(67074, 1)).e = az.i;
                        this.a.o(r);
                    }
                    else {
                        if (event.modifiers == 12 && event.metaDown() && this.a.a(44) && !az.a(34)) {
                            final r r2;
                            (r2 = new r(66305, 1)).d = -1;
                            r2.e = az.i;
                            r2.a(0, 0, this.a.i);
                            r2.a(0, 0, "bkick=");
                            this.a.o(r2);
                            return true;
                        }
                        if (event.modifiers == 6 && event.metaDown() && this.a.a(44) && !az.a(34)) {
                            final r r3;
                            (r3 = new r(66305, 1)).d = -1;
                            r3.e = az.i;
                            r3.a(0, 0, this.a.i);
                            r3.a(0, 0, "kick=");
                            this.a.o(r3);
                            return true;
                        }
                        if (event.modifiers == 5 && event.metaDown() && this.a.a(44) && !az.a(34)) {
                            final r r4;
                            (r4 = new r(66305, 1)).d = -1;
                            r4.e = az.i;
                            r4.a(0, 0, this.a.i);
                            r4.a(0, 0, "*IPX");
                            this.a.o(r4);
                            return true;
                        }
                    }
                }
                if (event.arg instanceof aU) {
                    final aU au = (aU)event.arg;
                    final aZ az2 = (aZ)this.a.c.b(au.e);
                    if (au.c && au.e != this.a.i) {
                        this.a.a(au, az2);
                        break;
                    }
                    if (this.a.a(43) && az2 != null && au.b && au.f == -1) {
                        this.a.a(au, (ai)az2);
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
            this.a.e(this.a);
        }
        this.a.t.b(this.a);
    }
    
    public e(final cx a, int layout) {
        new Hashtable();
        this.setBackground(a.a.a);
        this.setTitle(a.b() + ": " + aS.a(655));
        this.a = a;
        this.a = a2;
        layout = (int)new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final bF bf = new bF(a.a.c);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout((LayoutManager)layout);
        panel2.setLayout((LayoutManager)layout);
        bf.setBackground(a.a.b);
        this.setLayout((LayoutManager)layout);
        bf.setLayout((LayoutManager)layout);
        try {
            this.a = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.a = new TextArea(2, 10);
        }
        (this.a = new aR(a, true)).setFont(a.a.b());
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
        this.a = new i(this.a);
        ((GridBagLayout)layout).setConstraints(this.b = new i(this.a), gridBagConstraints);
        if (a.a.d()) {
            panel.add(this.b);
        }
        else {
            bf.add(this.b);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        if (a.a.e()) {
            final String c = a.a.c();
            final Image a3 = a.a(c + "users_tab_up.gif", true);
            final Image a4 = a.a(c + "users_tab_down.gif", true);
            final Image a5 = a.a(c + "rooms_tab_up.gif", true);
            final Image a6 = a.a(c + "rooms_tab_down.gif", true);
            if (a3 == null || a4 == null || a5 == null || a6 == null) {
                a.a.e(false);
            }
        }
        ((GridBagLayout)layout).setConstraints(this.a = new f(this, a), gridBagConstraints);
        if (b) {
            if (a.a.d()) {
                panel2.add(this.a);
            }
            else {
                bf.add(this.a);
            }
        }
        this.a.setFont(a.a.b());
        ((TextComponent)(this.a = new i(this.a))).setBackground((!ce.c || ce.b == 4) ? a.a.f : a.a.f.darker());
        this.a.setForeground(a.a.e);
        final String c2 = a.a.c();
        this.b = this.a(c2, "chatwich", "wtc.gif");
        this.c = this.a(c2, "emoticons", "em.gif");
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
            ((bz)this.a).c();
            ((bz)this.a).a();
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
            bf.add(panel4);
        }
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (a.a.d() ? 0.0 : 1.0E-5);
        final String c3 = a.a.c();
        this.a = this.a(c3, "send", null, aS.a(656));
        this.d = this.a(c3, "logout", aS.a(12), aS.a(657));
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
            final g g = new g(this.a);
            ((GridBagLayout)layout).setConstraints(g, gridBagConstraints);
            bf.add(g);
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
            bf.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            ((GridBagLayout)layout).setConstraints(panel2, gridBagConstraints);
            bf.add(panel2);
        }
        if (this.a instanceof cr) {
            ((cr)this.a).a(aS.a(13));
        }
        (this.b = new q()).b(a.a.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ((GridBagLayout)layout).setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final int c4 = a.a.c;
        if (super.a == null) {
            super.a = new bF(5, 7, 5, 7, c4);
            super.a = new A();
            ((Container)(super.a = new q())).setLayout(new BorderLayout(10, 3));
            super.a.add("West", super.a);
            super.a.add("Center", super.a);
            super.a.setFont(bk.c);
            super.a.a = false;
            final FontMetrics fontMetrics = this.getFontMetrics(bk.c);
            super.a.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        final bF a7;
        (a7 = super.a).setBackground(a.a.d);
        a7.setForeground(a.a.c);
        ((GridBagLayout)layout).setConstraints(a7, gridBagConstraints);
        this.add(a7);
        super.a.b(a.a.a);
        super.a.a(a.b);
        final Dimension dimension;
        (dimension = new Dimension()).height = bU.a;
        this.resize(dimension.width = bU.b, dimension.height);
        this.a = a.a.a();
    }
}
