// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.net.URL;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.Choice;
import java.awt.TextField;

public final class aT extends bC
{
    private bi a;
    private cr a;
    private cr b;
    private A a;
    private ai a;
    private cx a;
    private TextField a;
    private cr c;
    private cr d;
    private cr e;
    private cr f;
    private cr g;
    private Choice a;
    private cr h;
    private av a;
    private cr i;
    private cr j;
    private cr k;
    private Frame a;
    private boolean a;
    private boolean b;
    private Checkbox a;
    private Checkbox b;
    private v a;
    private v b;
    private TextField b;
    private cr l;
    private cr m;
    private cm a;
    private ck a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == 27) {
                    this.a.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    this.a.d(this.a.b);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.a.a((URL)event.arg, "_blank");
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    this.a.a(null, this.a);
                    return true;
                }
                if (event.target == this.a) {
                    if (this.a != null && this.a.a(12) && this.a.getState() != this.a) {
                        final r r;
                        (r = new r(66305, 1)).d = -1;
                        r.e = this.a.i;
                        r.a(0, 0, this.a.i);
                        r.a(0, 0, this.a.getState() ? "*enable" : "*disable");
                        this.a.o(r);
                    }
                    if (this.b != null && this.a.a(72) && this.b.getState() != this.b) {
                        final r r2;
                        (r2 = new r(66305, 1)).d = -1;
                        r2.e = this.a.i;
                        r2.a(0, 0, this.a.i);
                        r2.a(0, 0, this.b.getState() ? "*up" : "*noup");
                        this.a.o(r2);
                    }
                    this.dispose();
                    return true;
                }
                if (event.target == this.m) {
                    final r r3;
                    (r3 = new r(66305, 1)).d = -1;
                    r3.e = this.a.i;
                    r3.a(0, 0, this.a.i);
                    r3.a(0, 0, "*KP");
                    this.a.o(r3);
                    return true;
                }
                if (event.target == this.h) {
                    final r r4;
                    (r4 = new r(66305, 1)).d = -1;
                    r4.e = this.a.i;
                    r4.a(0, 0, this.a.i);
                    r4.a(0, 0, "color=" + this.a.a + "," + this.b.a);
                    this.a.o(r4);
                    this.h.repaint();
                    return true;
                }
                if (event.target == this.l) {
                    final r r5;
                    (r5 = new r(66305, 1)).d = -1;
                    r5.e = this.a.i;
                    r5.a(0, 0, this.a.i);
                    r5.a(0, 0, "W=" + this.b.getText());
                    this.a.o(r5);
                    return true;
                }
                if (event.target == this.i) {
                    if (((aP)this.a.a(this.a.getSelectedIndex())).a != null) {
                        new bb(this.a, this.a, ((U)this.a.a(this.a.getSelectedIndex())).i, this.a.i).setVisible(true);
                        return true;
                    }
                    final r r6;
                    (r6 = new r(66305, 1)).d = -1;
                    r6.e = this.a.i;
                    r6.a(0, 0, this.a.i);
                    r6.a(0, 0, "move=" + String.valueOf(((U)this.a.a(this.a.getSelectedIndex())).i));
                    this.a.o(r6);
                    return true;
                }
                else {
                    if (event.target == this.c) {
                        final r r7;
                        (r7 = new r(66305, 1)).d = -1;
                        r7.e = this.a.i;
                        r7.a(0, 0, this.a.i);
                        r7.a(0, 0, "CN=" + this.a.getText());
                        this.a.o(r7);
                        return true;
                    }
                    if (event.target == this.k && this.a.a() != -1) {
                        final r r8;
                        (r8 = new r(66305, 1)).d = -1;
                        r8.e = this.a.i;
                        r8.a(0, 0, this.a.i);
                        r8.a(0, 0, "CI=" + this.a.a().i);
                        this.a.o(r8);
                        return true;
                    }
                    if (event.target == this.d) {
                        final r r9;
                        (r9 = new r(66305, 1)).d = -1;
                        r9.e = this.a.i;
                        r9.a(0, 0, this.a.i);
                        r9.a(0, 0, "*IP");
                        this.a.o(r9);
                        return true;
                    }
                    if (event.target == this.e) {
                        final r r10;
                        (r10 = new r(66305, 1)).d = -1;
                        r10.e = this.a.i;
                        r10.a(0, 0, this.a.i);
                        r10.a(0, 0, "*IPX");
                        this.a.o(r10);
                        return true;
                    }
                    if (event.target == this.f) {
                        final r r11;
                        (r11 = new r(66305, 1)).d = -1;
                        r11.e = this.a.i;
                        r11.a(0, 0, this.a.i);
                        r11.a(0, 0, "kick=");
                        this.a.o(r11);
                        return true;
                    }
                    if (event.target == this.g) {
                        final r r12;
                        (r12 = new r(66305, 1)).d = -1;
                        r12.e = this.a.i;
                        r12.a(0, 0, this.a.i);
                        r12.a(0, 0, "bkick=");
                        this.a.o(r12);
                        return true;
                    }
                    if (event.target == this.j) {
                        final r r13;
                        (r13 = new r(66305, 1)).d = -1;
                        r13.e = this.a.i;
                        r13.a(0, 0, this.a.i);
                        r13.a(0, 0, "star=" + this.a.a());
                        this.a.o(r13);
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public aT(Frame a, final cx a2, final ai a3, final r r, int n) {
        super(a, false);
        this.a = a;
        this.a = new cr(70, 20);
        this.b = new cr(115, 20);
        this.h = new cr(70, 20);
        this.i = new cr(70, 20);
        this.setBackground(a2.a.a);
        this.a = a3;
        this.a = a2;
        (a = (Frame)new bF()).setBackground(a2.a.h);
        a.setForeground(a2.a.g);
        final k k = new k();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String a5 = a2.a(a3.d);
        final String a6 = a2.a(r.a(a4, 0));
        final String a7 = r.a(a4, 1);
        String a8;
        if ((a8 = r.a(a4, 2)) == null) {
            a8 = a6;
        }
        final String a9 = r.a(a4, 3);
        String value = null;
        String s = null;
        String a10 = null;
        String a11 = null;
        String d = null;
        final String a12 = r.a(a4, 4);
        boolean b = false;
        final ak ak;
        if ((ak = (ak)a2.d.b(a3.b)) != null) {
            d = ak.d;
        }
        final int a13;
        if ((a13 = r.a(a4, 1)) != -999) {
            value = String.valueOf(a13);
        }
        if (r.a(a4, 1)) {
            s = aS.a(367);
        }
        else if (r.a(a4, 0)) {
            s = aS.a(368);
        }
        if (a2.a(41)) {
            a10 = a3.a;
            if ((a11 = a3.a) == null) {
                a11 = a10;
            }
        }
        final b b2 = (b)a2.b.b(a3.a);
        this.setResizable(false);
        this.setTitle(bm.a(aS.a(369), new String[] { a5 }));
        this.setLayout(gridBagLayout);
        a.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 1;
        if (b2 != null) {
            final Component component;
            ((q)(component = new q())).b(b2.a);
            gridBagLayout.setConstraints(component, gridBagConstraints);
            a.add(component);
        }
        gridBagConstraints.gridwidth = 0;
        final bi bi;
        (bi = new bi(a5, (byte)0)).setFont(bk.c);
        gridBagLayout.setConstraints(bi, gridBagConstraints);
        a.add(bi);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(k, gridBagConstraints);
        a.add(k);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        if (a8 != null) {
            this.a = new bi(a8, (byte)0);
            final bi bi2;
            (bi2 = new bi(aS.a(370), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi2, gridBagConstraints);
            a.add(bi2);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            a.add(this.a);
            b = true;
        }
        if (a6 != null) {
            if ((this.a.a(6) && this.a.i != this.a.i) || (this.a.a(31) && this.a.i == this.a.i)) {
                final bi bi3;
                (bi3 = new bi(aS.a(371), (byte)0)).setFont(bk.d);
                gridBagConstraints.gridwidth = 3;
                gridBagLayout.setConstraints(bi3, gridBagConstraints);
                a.add(bi3);
                (this.a = new TextField(a6, 15)).setForeground(Color.black);
                this.a.setBackground(Color.white);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 2;
                gridBagLayout.setConstraints(this.a, gridBagConstraints);
                a.add(this.a);
                (this.c = new cr(70, 20)).a(aS.a(372));
                this.c.resize(this.c.getFontMetrics(this.c.getFont()).stringWidth(aS.a(372)) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.c, gridBagConstraints);
                a.add(this.c);
            }
            else {
                this.a = new bi(a6, (byte)0);
                final bi bi4;
                (bi4 = new bi(aS.a(371), (byte)0)).setFont(bk.d);
                gridBagConstraints.gridwidth = 3;
                gridBagLayout.setConstraints(bi4, gridBagConstraints);
                a.add(bi4);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.a, gridBagConstraints);
                a.add(this.a);
            }
            b = true;
        }
        if (this.a.a(15)) {
            final bi bi5;
            (bi5 = new bi(aS.a(373), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi5, gridBagConstraints);
            a.add(bi5);
            (this.b = new TextField(15)).setForeground(Color.black);
            this.b.setBackground(Color.white);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 2;
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            a.add(this.b);
            (this.l = new cr(70, 20)).a(aS.a(374));
            this.l.resize(this.l.getFontMetrics(this.l.getFont()).stringWidth(aS.a(374)) + 20, 20);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.l, gridBagConstraints);
            a.add(this.l);
            b = true;
        }
        if (a12 != null) {
            final bi bi6 = new bi(a12, (byte)0);
            final bi bi7;
            (bi7 = new bi(aS.a(557), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi7, gridBagConstraints);
            a.add(bi7);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(bi6, gridBagConstraints);
            a.add(bi6);
            b = true;
        }
        if (value != null) {
            final bi bi8 = new bi(value, (byte)0);
            final bi bi9;
            (bi9 = new bi(aS.a(375), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi9, gridBagConstraints);
            a.add(bi9);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(bi8, gridBagConstraints);
            a.add(bi8);
            b = true;
        }
        if (s != null) {
            final bi bi10 = new bi(s, (byte)0);
            final bi bi11;
            (bi11 = new bi(aS.a(376), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi11, gridBagConstraints);
            a.add(bi11);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(bi10, gridBagConstraints);
            a.add(bi10);
            b = true;
        }
        if (a9 != null) {
            n = (int)new A(a9);
            final bi bi12;
            (bi12 = new bi(aS.a(377), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi12, gridBagConstraints);
            a.add(bi12);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints((Component)n, gridBagConstraints);
            try {
                ((A)n).a(new URL(a9));
            }
            catch (MalformedURLException ex2) {}
            a.add((Component)n);
            b = true;
        }
        if (a7 != null) {
            final A a14 = new A(a7);
            final bi bi13;
            (bi13 = new bi(aS.a(378), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi13, gridBagConstraints);
            a.add(bi13);
            gridBagConstraints.gridwidth = (this.a.a(9) ? 2 : 0);
            gridBagLayout.setConstraints(a14, gridBagConstraints);
            a.add(a14);
            if (this.a.a(9)) {
                (this.d = new cr(70, 20)).a(aS.a(379));
                this.d.resize(this.d.getFontMetrics(this.d.getFont()).stringWidth(aS.a(379)) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.d, gridBagConstraints);
                a.add(this.d);
            }
            b = true;
        }
        if (d != null) {
            this.a = new A(d);
            n = (int)new bi(aS.a(84), (byte)0);
            try {
                this.a.a(new URL("file:room"));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            ((bi)n).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints((Component)n, gridBagConstraints);
            a.add((Component)n);
            gridBagConstraints.gridwidth = (this.a.a(11) ? 2 : 0);
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            a.add(this.a);
            if (this.a.a(11)) {
                (this.e = new cr(70, 20)).a(aS.a(380));
                this.e.resize(this.e.getFontMetrics(this.e.getFont()).stringWidth(aS.a(380)) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.e, gridBagConstraints);
                a.add(this.e);
            }
            b = true;
        }
        if (a10 != null) {
            final bi bi14 = new bi(a10, (byte)0);
            final bi bi15;
            (bi15 = new bi(aS.a(381), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi15, gridBagConstraints);
            a.add(bi15);
            gridBagConstraints.gridwidth = (this.a.a(44) ? 2 : 0);
            gridBagLayout.setConstraints(bi14, gridBagConstraints);
            a.add(bi14);
            if (this.a.a(44)) {
                (this.f = new cr(70, 20)).a(aS.a(171));
                this.f.resize(this.f.getFontMetrics(this.f.getFont()).stringWidth(aS.a(171)) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.f, gridBagConstraints);
                a.add(this.f);
                if (this.a.a(62)) {
                    this.f.b();
                }
            }
            b = true;
        }
        if (a11 != null) {
            final bi bi16 = new bi(a11, (byte)0);
            final bi bi17;
            (bi17 = new bi(aS.a(382), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi17, gridBagConstraints);
            a.add(bi17);
            gridBagConstraints.gridwidth = (this.a.a(49) ? 2 : 0);
            gridBagLayout.setConstraints(bi16, gridBagConstraints);
            a.add(bi16);
            if (this.a.a(49)) {
                (this.g = new cr(70, 20)).a(aS.a(383));
                this.g.resize(this.g.getFontMetrics(this.g.getFont()).stringWidth(aS.a(383)) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.g, gridBagConstraints);
                a.add(this.g);
            }
            b = true;
        }
        if ((this.a.a(5) && (!this.a.a(41) || this.a.a(52))) || (this.a.a(20) && this.a.i == this.a.i)) {
            this.a = new v(this.a);
            this.a.a = this.a.a(4);
            this.a.b = true;
            this.a.a(this.a.a(41) ? 4 : 3);
            this.b = new v(this.a);
            final bi bi18;
            (bi18 = new bi(aS.a(384), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi18, gridBagConstraints);
            a.add(bi18);
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 2;
            final Panel panel;
            (panel = new Panel()).setLayout(new FlowLayout(1, 10, 0));
            panel.add(this.a);
            final bi bi19;
            (bi19 = new bi(aS.a(572), (byte)0)).setFont(bk.d);
            if (this.a.a(32)) {
                this.b.b = true;
                this.b.a(this.a.a(41) ? 4 : 3);
                panel.add(bi19);
                panel.add(this.b);
                if (a3.f != 0) {
                    this.b.a(new Color(a3.f));
                }
            }
            gridBagLayout.setConstraints(panel, gridBagConstraints);
            a.add(panel);
            this.a.a(new Color(a3.c));
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 0;
            this.h.a(aS.a(385));
            this.h.resize(this.h.getFontMetrics(this.h.getFont()).stringWidth(aS.a(385)) + 20, 20);
            gridBagLayout.setConstraints(this.h, gridBagConstraints);
            a.add(this.h);
        }
        if (this.a.a(7)) {
            gridBagConstraints.weightx = 0.0;
            this.a = new Choice();
            this.a = (av)a2.d.clone();
            a4 = 0;
            for (int i = 0; i < this.a.a(); ++i) {
                String s2;
                if ((s2 = ((U)this.a.a(i)).d).length() > 35) {
                    s2 = s2.substring(0, 35);
                }
                this.a.addItem(s2);
                if (((U)this.a.a(i)).i == a3.b) {
                    a4 = i;
                }
            }
            this.a.setForeground(Color.black);
            final bi bi20;
            (bi20 = new bi(aS.a(84), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi20, gridBagConstraints);
            a.add(bi20);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 2;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            a.add(this.a);
            this.a.select(a4);
            gridBagConstraints.gridwidth = 0;
            this.i.a(aS.a(386));
            this.i.resize(this.i.getFontMetrics(this.i.getFont()).stringWidth(aS.a(386)) + 20, 20);
            gridBagLayout.setConstraints(this.i, gridBagConstraints);
            a.add(this.i);
        }
        if (this.a.a(52) || (this.a.a(1) && !this.a.a(41)) || (this.a.a(39) && this.a.i == this.a.i)) {
            gridBagConstraints.weightx = 0.0;
            this.a = new cm();
            this.a.a.a();
            a4 = (this.a.a(41) ? 1 : 0);
            try {
                for (int j = 0; j < a2.q.a(); ++j) {
                    final Y y;
                    if ((y = (Y)a2.q.a(j)).i != 1 && (y.a(1) ? 1 : 0) == a4) {
                        this.a.a(y);
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
            final bi bi21;
            (bi21 = new bi(aS.a(563), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi21, gridBagConstraints);
            a.add(bi21);
            this.a.resize(200, 30);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 2;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            a.add(this.a);
            this.a.a(-1);
            if (this.a.b() < 0) {
                this.a.a(a3.e);
            }
            gridBagConstraints.gridwidth = 0;
            (this.j = new cr(70, 10)).a(aS.a(372));
            this.j.resize(this.j.getFontMetrics(this.j.getFont()).stringWidth(aS.a(372)) + 20, 10);
            gridBagLayout.setConstraints(this.j, gridBagConstraints);
            a.add(this.j);
        }
        final aT at;
        if (this.a.a(73) && !this.a.a(41)) {
            gridBagConstraints.weightx = 0.0;
            this.a = new ck();
            this.a.a.a();
            try {
                synchronized (this.a.b) {
                    for (int l = 0; l < this.a.b.a(); ++l) {
                        final b b3;
                        if ((b3 = (b)this.a.b.a(l)).a != null && b3.a.length() > 0) {
                            if (!b3.a.equalsIgnoreCase(this.a.g)) {
                                continue;
                            }
                        }
                        else if (b3.a(36) && !this.a.a(36)) {
                            continue;
                        }
                        this.a.a(b3);
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
            at.a.a(a3.a);
            final bi bi22;
            (bi22 = new bi(aS.a("Icon"), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi22, gridBagConstraints);
            a.add(bi22);
            at.a.resize(200, 50);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 2;
            gridBagLayout.setConstraints(at.a, gridBagConstraints);
            a.add(at.a);
            gridBagConstraints.gridwidth = 0;
            (at.k = new cr(70, 10)).a(aS.a(372));
            final FontMetrics fontMetrics = at.k.getFontMetrics(at.k.getFont());
            gridBagLayout.setConstraints(at.k, gridBagConstraints);
            a.add(at.k);
            at.k.resize(fontMetrics.stringWidth(aS.a(372)) + 20, 10);
        }
        if (at.a.a(12)) {
            final bi bi23 = new bi("", (byte)0);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(bi23, gridBagConstraints);
            a.add(bi23);
            gridBagConstraints.gridwidth = ((at.a.a(16) && !at.a.a(41)) ? 2 : 0);
            at.a = r.a(0, 40);
            gridBagLayout.setConstraints(at.a = new Checkbox(aS.a(387), at.a), gridBagConstraints);
            a.add(at.a);
            b = true;
        }
        if (at.a.a(16) && !at.a.a(41)) {
            if (!at.a.a(12)) {
                gridBagConstraints.fill = 4;
                gridBagConstraints.anchor = 13;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
            }
            gridBagConstraints.gridwidth = 0;
            (at.m = new cr(70, 20)).a(aS.a(388));
            at.m.resize(at.i.getFontMetrics(at.m.getFont()).stringWidth(aS.a(153)) + 20, 20);
            gridBagLayout.setConstraints(at.m, gridBagConstraints);
            a.add(at.m);
            b = true;
        }
        if (at.a.a(72) && !at.a.a(41)) {
            gridBagConstraints.gridwidth = 0;
            at.b = r.a(0, 12);
            gridBagLayout.setConstraints(at.b = new Checkbox(aS.a(676), at.b), gridBagConstraints);
            a.add(at.b);
            b = true;
        }
        if (!b) {
            final bi bi24;
            (bi24 = new bi(aS.a(389), (byte)0)).setFont(bk.d);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(bi24, gridBagConstraints);
            a.add(bi24);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagLayout.setConstraints(a, gridBagConstraints);
        at.add(a);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        if (a2.a(43)) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            at.b.a(aS.a(390));
            at.b.resize(at.b.getFontMetrics(at.b.getFont()).stringWidth(aS.a(390)) + 20, 20);
            gridBagLayout.setConstraints(at.b, gridBagConstraints);
            at.add(at.b);
            gridBagConstraints.weightx = 0.0;
        }
        gridBagConstraints.gridwidth = 0;
        at.a.a(aS.a(2));
        at.a.resize(at.a.getFontMetrics(at.a.getFont()).stringWidth(aS.a(2)) + 20, 20);
        final g g = new g(at.a);
        gridBagLayout.setConstraints(g, gridBagConstraints);
        at.add(g);
        at.pack();
        at.setVisible(true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = at.size();
        if (screenSize.width < size.width + 50) {
            at.resize(screenSize.width - 50, size.height);
        }
    }
}
