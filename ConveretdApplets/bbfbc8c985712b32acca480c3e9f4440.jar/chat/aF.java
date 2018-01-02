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
import java.util.Hashtable;
import java.net.MalformedURLException;
import java.awt.Graphics;
import java.net.URL;
import java.awt.MenuItem;
import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.TextArea;

public final class aF extends bL implements aB, aX
{
    private aJ a;
    public bF a;
    private bF c;
    private q b;
    aI a;
    private aw a;
    public cx a;
    public TextArea a;
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Canvas d;
    private Panel a;
    private Canvas e;
    private bM a;
    private aR a;
    private Image a;
    private int a;
    private int b;
    private int c;
    private int d;
    private Dimension a;
    private Insets a;
    private Image b;
    bA a;
    private i a;
    private i b;
    private bl a;
    
    private final Canvas a(final String s, final String s2, final String s3, final String a) {
        aB a2 = null;
        if (this.a.a.d() && s != null) {
            final Image a3 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a4 = this.a.a(s + s2 + "_button_dn.gif", true);
            if (a3 != null && a4 != null) {
                this.prepareImage(a3, this);
                this.prepareImage(a4, this);
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
                    this.prepareImage(a2, this);
                    this.prepareImage(a3, this);
                    this.prepareImage(a4, this);
                    a = bG.a(a2, a3, a4);
                }
            }
            return (cr)a;
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
    
    public final void a(final aU au) {
        this.a.a(au);
    }
    
    public final void a() {
        this.a.a = true;
        if (bU.a) {
            super.setVisible(false);
            bU.a.remove(this);
            bU.a.setVisible(true);
            bU.a.validate();
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
        this.a.b = (this.a.P != 1);
        this.a.c = 30 - this.a.Q * 5;
        this.a.a = this.a.a.a;
        try {
            if (this.a.s.a() > 0) {
                if (!this.a.isVisible()) {
                    this.a.setVisible(true);
                    this.a.a(this.c.size().width, this.getFontMetrics(this.a.a.b()).getHeight());
                }
                final bl a;
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
    
    public final aJ a() {
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
            ((bz)this.a).a();
        }
    }
    
    public final String a(final Object o) {
        try {
            if (o == this.a) {
                return aS.a(438);
            }
            if (o == this.c) {
                return aS.a(442);
            }
            if (o == this.d) {
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
    
    public final void a(final boolean b) {
        this.a.a(b);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (this.e instanceof cr) {
                    ((cr)this.e).c();
                }
                else {
                    ((bG)this.e).a();
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
                        this.a.f();
                        return true;
                    }
                    if (event.key == 5) {
                        final ah ah;
                        (ah = new ah(this.a.a.a(), this.a, this.a)).e();
                        ah.setVisible(true);
                        return true;
                    }
                    if (event.key == 18) {
                        final aj aj;
                        (aj = new aj(this.a.a.a(), this.a, this.a)).e();
                        aj.setVisible(true);
                        return true;
                    }
                    if (event.key == 1 && !ce.d) {
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
                    if (this.b instanceof cr) {
                        ((cr)this.b).c();
                    }
                    else {
                        ((bG)this.b).a();
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
                        if (s.length() > this.a.I) {
                            s = s.substring(0, this.a.I);
                        }
                        if (System.currentTimeMillis() - this.a.a() < this.a.b()) {
                            try {
                                Thread.sleep(this.a.b() - (System.currentTimeMillis() - this.a.a()));
                            }
                            catch (InterruptedException ex) {}
                        }
                        if (this.a.a(14)) {
                            this.a.a(s, ((bz)this.a).a.a, ((bz)this.a).a);
                        }
                        else {
                            this.a.a(s);
                        }
                        this.a.d();
                    }
                    if (ce.a) {
                        this.a.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.e) {
                    this.a.j();
                    return true;
                }
                if (event.target == this.a) {
                    this.a.f(0);
                    return true;
                }
                if (event.target == this.c) {
                    final aj aj2;
                    (aj2 = new aj(this.a.a.a(), this.a, this.a)).e();
                    aj2.setVisible(true);
                    return true;
                }
                if (event.target == this.d) {
                    final ah ah2;
                    (ah2 = new ah(this.a.a.a(), this.a, this.a)).e();
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
        this.a.c();
        this.a.setFont(this.a.a.b());
    }
    
    public final boolean a(final aP ap) {
        return this.a.a(ap);
    }
    
    public final boolean a(final aZ az) {
        return this.a.a(az);
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
    
    public final void a(final aP ap) {
        if (this.a != null) {
            final bA a = this.a;
            a.setTitle(this.a.a.a + ": " + ap.d + ": " + a.a.a.d);
        }
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public final void e() {
        this.a.a(0);
        final aI a;
        if ((a = this.a).a instanceof bI) {
            final bI bi;
            if (!(bi = (bI)a.a).a) {
                bi.a = true;
                bi.c = (bi.a ? bi.b : bi.a);
                bi.repaint();
                bi.postEvent(new Event(bi, 1001, null));
            }
            return;
        }
        n.a(a.a, true);
    }
    
    public final void b(final aP ap) {
        this.a.a(ap);
    }
    
    public final void a(final aZ az, final boolean b) {
        this.a.a(az, b);
    }
    
    public final void b(final boolean b) {
        if (b) {
            this.b.b(this.a.a.b);
            return;
        }
        this.b.b(null);
        this.b.a(1, 1);
    }
    
    public aF(final cx a, final bA a2) {
        new Hashtable();
        this.a = a2;
        this.setBackground(a.a.a);
        this.a = a;
        a.a = new MediaTracker(this);
        cs.b = a.a("lockIcon.gif", false, 20);
        cs.g = a.a("arrow.gif", false, 25);
        cs.c = a.a("em.gif", false, 21);
        cs.d = a.a("wtc.gif", false, 22);
        cs.e = a.a("bold.gif", false, 23);
        cs.f = a.a("italic.gif", false, 24);
        cs.h = a.a("ar-up.gif", false, 23);
        cs.i = a.a("ar-down.gif", false, 23);
        a.a.a = a.a(a.a.c() + "helpLogo.gif", true, 12);
        a.a.b = a.a(a.a.c() + "chatLogo.gif", true, 10);
        (cs.a = new Image[7])[1] = a.a("busy.gif", false, 49);
        cs.a[2] = a.a("away.gif", false, 49);
        cs.a[3] = a.a("call.gif", false, 49);
        cs.a[4] = a.a("vb.gif", false, 49);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final bF bf = new bF(a.a.c);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        bf.setBackground(a.a.b);
        this.setLayout(layout);
        bf.setLayout(layout);
        try {
            this.a = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.a = new TextArea(2, 10);
        }
        this.a = new bl(a);
        this.a.b = 13;
        this.a.a = true;
        (this.c = new bF(2, 2, 2, 2, 1)).setLayout(layout);
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
            bf.add(this.c);
        }
        this.c.hide();
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
        layout.setConstraints(this.b = new i(this.a), gridBagConstraints);
        if (a.a.d()) {
            panel.add(this.b);
        }
        else {
            bf.add(this.b);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        Image a3 = null;
        Image a4 = null;
        Image a5 = null;
        Image a6 = null;
        if (a.a.e()) {
            final String c = a.a.c();
            a3 = a.a(c + "users_tab_up.gif", true);
            a4 = a.a(c + "users_tab_down.gif", true);
            a5 = a.a(c + "rooms_tab_up.gif", true);
            a6 = a.a(c + "rooms_tab_down.gif", true);
            if (a3 == null || a4 == null || a5 == null || a6 == null) {
                a.a.e(false);
            }
        }
        this.a = new bM(a, a.a.e());
        this.a = new aI(a);
        this.a = new aw(a);
        if (a.c()) {
            this.a.a(aS.a(19), this.a, a3, a4);
        }
        if (a.b()) {
            this.a.a(aS.a(18), this.a, a5, a6);
        }
        layout.setConstraints(this.a, gridBagConstraints);
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
        this.c = this.a(c2, "chatwich", "wtc.gif");
        this.d = this.a(c2, "emoticons", "em.gif");
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
            bf.add(panel4);
        }
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (a.a.d() ? 0.0 : 1.0E-5);
        final String c3 = a.a.c();
        this.a = this.a(c3, "settings", aS.a(11), aS.a(446));
        this.b = this.a(c3, "send", null, aS.a(441));
        this.e = this.a(c3, "logout", aS.a(12), bm.a(aS.a(447), new String[] { this.a.a.a }));
        final Panel panel5 = a.a.d() ? new Panel() : null;
        if (a.a.d()) {
            panel5.setLayout(layout);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        }
        gridBagConstraints.gridwidth = (a.g ? 0 : -1);
        layout.setConstraints(this.a, gridBagConstraints);
        if (a.a.d()) {
            panel5.add(this.a);
        }
        else {
            bf.add(this.a);
        }
        if (!a.g) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.e, gridBagConstraints);
            if (a.a.d()) {
                panel5.add(this.e);
            }
            else {
                bf.add(this.e);
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
            final g g = new g(this.b);
            layout.setConstraints(g, gridBagConstraints);
            bf.add(g);
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
            bf.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            bf.add(panel2);
        }
        if (this.b instanceof cr) {
            ((cr)this.b).a(aS.a(13));
        }
        this.a = new bF(2, 2, 2, 2, 0);
        this.a = new aJ();
        this.a.a = ((a.z <= 0) ? 7 : a.z);
        this.a.setLayout(layout);
        layout.setConstraints(this.a, new GridBagConstraints());
        this.a.add(this.a);
        this.b = new q();
        if (r.a(a.d, 49)) {
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
        new at(this, a);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final int c4 = a.a.c;
        if (super.b == null) {
            super.b = new bF(5, 7, 5, 7, c4);
            super.a = new A();
            super.a = new q();
            super.b.setLayout(new BorderLayout(10, 3));
            super.b.add("West", super.a);
            super.b.add("Center", super.a);
            super.b.setFont(bk.c);
            super.a.a = false;
            final FontMetrics fontMetrics = this.getFontMetrics(bk.c);
            super.a.resize(50, 2 * fontMetrics.getHeight() + fontMetrics.getDescent());
        }
        final bF b2;
        (b2 = super.b).setBackground(a.a.d);
        b2.setForeground(a.a.c);
        layout.setConstraints(b2, gridBagConstraints);
        this.add(b2);
        super.a.b(a.a.a);
        super.a.a(a.b);
        final Dimension dimension;
        (dimension = new Dimension()).height = bU.a;
        this.resize(dimension.width = bU.b, dimension.height);
        if (a2 != null) {
            a.a(a2);
        }
        this.a = a.a.a();
    }
}
