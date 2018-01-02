// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import ji.io.h;
import ji.util.i;
import ji.awt.bb;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.Component;
import ji.util.e;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import ji.util.d;
import java.awt.Dialog;
import java.awt.Frame;
import ji.document.ad;
import ji.secure.dh;
import ji.secure.ei;
import ji.v1base.gr;
import ji.awt.c;
import ji.io.p;
import ji.v1base.bn;
import java.awt.Choice;
import ji.awt.eb;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import ji.v1base.jiPanel;
import ji.v1base.bl;

public class ea extends bl
{
    jiPanel a;
    CheckboxGroup b;
    Checkbox c;
    Checkbox d;
    Checkbox e;
    eb f;
    Choice g;
    bn h;
    bn i;
    ac0 j;
    ac1 k;
    ac2 l;
    ac3 m;
    boolean n;
    String o;
    p p;
    c q;
    c r;
    boolean s;
    String[] t;
    String[] u;
    gr v;
    dg w;
    ei x;
    int y;
    int z;
    eg aa;
    dh ab;
    c ac;
    ad ad;
    
    public ea(final Frame frame, final ad ad, final dg dg, final dh dh, final ei ei, final eg eg, final String s, final String s2, final int n, final boolean b) {
        super(frame, s2, true);
        this.a = null;
        this.b = new CheckboxGroup();
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = new Choice();
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = 0;
        this.z = 92;
        this.aa = null;
        this.ab = null;
        this.ac = new c("jiAnnSecurityFrameItem1");
        this.a(ad, dg, dh, ei, eg, s, s2, n, b);
    }
    
    public ea(final Dialog dialog, final ad ad, final dg dg, final dh dh, final ei ei, final eg eg, final String s, final String s2, final int n, final boolean b) {
        super(dialog, s2, true);
        this.a = null;
        this.b = new CheckboxGroup();
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = new Choice();
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = 0;
        this.z = 92;
        this.aa = null;
        this.ab = null;
        this.ac = new c("jiAnnSecurityFrameItem1");
        this.a(ad, dg, dh, ei, eg, s, s2, n, b);
    }
    
    private void a(final ad ad, final dg w, final dh ab, final ei x, final eg aa, final String o, final String s, final int y, final boolean s2) {
        this.ad = ad;
        this.w = w;
        this.x = x;
        this.o = o;
        this.y = y;
        this.aa = aa;
        this.s = s2;
        this.ab = ab;
        this.h = new bn(o);
        this.i = new bn(o);
        this.f = new eb(o);
        this.a = new jiPanel(o);
        try {
            this.a(ad);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void a(final ad ad) throws Exception {
        int n = 360;
        int n2 = 140;
        int n3 = 42;
        this.z = 92;
        if (this.s) {
            this.ac.c(ji.util.d.b(589, this.o));
        }
        this.ac.c(ji.util.d.b(588, this.o));
        this.ac.c(ji.util.d.b(587, this.o));
        this.ac.c(ji.util.d.b(586, this.o));
        this.ac.c(ji.util.d.b(585, this.o));
        final int n4 = 35;
        for (int i = 0; i < this.ac.b(); ++i) {
            final int z = ad.getGraphics().getFontMetrics().stringWidth((String)this.ac.b(i)) + n4;
            if (this.z < z) {
                n += z - this.z;
                this.z = z;
            }
        }
        this.a.setLayout(null);
        this.a.setBorderStyle(0);
        this.setResizable(false);
        this.f();
        final boolean al = this.p.al(true, this.v);
        final boolean aj = this.p.aj(true, this.v);
        this.j = new ac0();
        this.k = new ac1();
        this.l = new ac2();
        this.addWindowListener(this.j);
        this.m = new ac3();
        if (this.y == 0) {
            ji.util.e.a(this.c = new Checkbox(ji.util.d.b(582, this.o), al, this.b));
            this.c.addKeyListener(this.k);
            this.c.addItemListener(this.m);
            this.c.setBounds(new Rectangle(15, 8, 66, 25));
            ji.util.e.a(this.d = new Checkbox(ji.util.d.b(583, this.o), aj, this.b));
            this.d.addKeyListener(this.k);
            this.d.addItemListener(this.m);
            this.d.setBounds(new Rectangle(87, 8, 66, 25));
            ji.util.e.a(this.e = new Checkbox(ji.util.d.b(584, this.o), al && aj, this.b));
            this.e.addKeyListener(this.k);
            this.e.addItemListener(this.m);
            this.e.setBounds(new Rectangle(160, 8, 66, 25));
        }
        else {
            n2 = 120;
            n3 = 10;
        }
        this.f.setBounds(new Rectangle(15, n3, 231, 20));
        this.g.setBounds(new Rectangle(250, n3, this.z, 20));
        final int n5 = 10;
        final int n6 = 85;
        final int n7 = 25;
        final int n8 = (n - 2 * n6 - n5) / 2;
        final int n9 = n2 - n7 - n5 - 3 - 25;
        this.h.setLabel(ji.util.d.b(232, this.o));
        ji.util.e.a(this.h);
        this.h.addKeyListener(this.k);
        this.h.addActionListener(this.l);
        this.h.setBounds(new Rectangle(n8, n9, n6, n7));
        this.i.setLabel(ji.util.d.b(235, this.o));
        ji.util.e.a(this.i);
        this.i.addKeyListener(this.k);
        this.i.addActionListener(this.l);
        this.i.setBounds(new Rectangle(n8 + n6 + n5, n9, n6, n7));
        this.a.add(this.g, null);
        this.a.add(this.f, null);
        if (this.y == 0) {
            this.a.add(this.c, null);
            this.a.add(this.d, null);
            this.a.add(this.e, null);
        }
        this.a.add(this.h, null);
        this.a.add(this.i, null);
        this.d().add(this.a);
        final int n10 = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - n / 2;
        final int n11 = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - n2 / 2;
        this.setSize(n, n2);
        this.setLocation(n10, n11);
    }
    
    public void show() {
        new bb(this.o, new aat((aem)null)).start();
        super.show();
    }
    
    private final void b() {
        if (this.y == 0) {
            final String d = this.f.d();
            final int intValue = (int)this.q.b(this.f.e());
            final String l = (String)this.r.b(this.g.getSelectedIndex());
            boolean b = false;
            final c b2 = this.ab.b();
            for (int i = 0; i < b2.b(); ++i) {
                final ei ei = (ei)b2.b(i);
                if (ei.h.toLowerCase().equals(d.toLowerCase()) && ei.j == intValue) {
                    ei.k = 1;
                    ei.l = l;
                    b = true;
                    break;
                }
            }
            if (!b) {
                this.ab.a(new ei(d, d, intValue, l, 2));
            }
        }
        else {
            this.x.l = (String)this.r.b(this.g.getSelectedIndex());
            this.x.k = 1;
        }
        this.a();
    }
    
    private final void c() {
        this.a();
    }
    
    private final void f() {
        try {
            if (this.p == null) {
                this.p = new p(this.o);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final boolean b, final boolean b2) {
        try {
            final boolean c = ji.util.i.c(57);
            if (c) {
                ji.io.h.d(this.o, "jiAnnSecurityFrameItem1");
            }
            this.f.a();
            this.f.removeAll();
            if (this.y == 0) {
                if (c) {
                    ji.io.h.d(this.o, "jiAnnSecurityFrameItem2");
                }
                this.t = this.aa.a();
                if (c) {
                    ji.io.h.d(this.o, "jiAnnSecurityFrameItem3");
                }
                this.u = this.aa.b();
                if (c) {
                    ji.io.h.d(this.o, "jiAnnSecurityFrameItem4");
                }
                if (this.q == null) {
                    this.q = new c("jiAnnSecurityFrameItem1");
                }
                else {
                    this.q.c();
                }
                if (this.r == null) {
                    this.r = new c("jiAnnSecurityFrameItem2");
                    if (this.s) {
                        this.r.c("admin");
                    }
                    this.r.c("owner");
                    this.r.c("author");
                    this.r.c("viewer");
                    this.r.c("none");
                    for (int i = 0; i < this.ac.b(); ++i) {
                        this.g.add((String)this.ac.b(i));
                    }
                }
                if (b && this.t != null) {
                    if (c) {
                        ji.io.h.d(this.o, "jiAnnSecurityFrameItem5");
                    }
                    for (int j = 0; j < this.t.length; ++j) {
                        this.f.a(this.t[j]);
                        this.q.c(new Integer(0));
                    }
                    if (c) {
                        ji.io.h.d(this.o, "jiAnnSecurityFrameItem6");
                    }
                }
                if (b2 && this.u != null) {
                    if (c) {
                        ji.io.h.d(this.o, "jiAnnSecurityFrameItem7");
                    }
                    for (int k = 0; k < this.u.length; ++k) {
                        this.f.a(this.u[k]);
                        this.q.c(new Integer(1));
                    }
                    if (c) {
                        ji.io.h.d(this.o, "jiAnnSecurityFrameItem8");
                    }
                }
            }
            else if (this.x != null) {
                this.f.a(this.x.h);
                this.f.setEnabled(false);
                if (this.r == null) {
                    this.r = new c("jiAnnSecurityFrameItem2");
                    if (this.ad.bi(25) && this.s) {
                        this.r.c("admin");
                    }
                    this.r.c("owner");
                    this.r.c("author");
                    this.r.c("viewer");
                    this.r.c("none");
                    int n2;
                    int n = n2 = 0;
                    if (this.ad.bi(25) && this.s) {
                        this.g.add(ji.util.d.b(589, this.o));
                        if (this.x.l.toLowerCase().equals("admin".toLowerCase())) {
                            n2 = n;
                        }
                        ++n;
                    }
                    this.g.add(ji.util.d.b(588, this.o));
                    if (this.x.l.toLowerCase().equals("owner".toLowerCase())) {
                        n2 = n;
                    }
                    ++n;
                    this.g.add(ji.util.d.b(587, this.o));
                    if (this.x.l.toLowerCase().equals("author".toLowerCase())) {
                        n2 = n;
                    }
                    ++n;
                    this.g.add(ji.util.d.b(586, this.o));
                    if (this.x.l.toLowerCase().equals("viewer".toLowerCase())) {
                        n2 = n;
                    }
                    ++n;
                    this.g.add(ji.util.d.b(585, this.o));
                    if (this.x.l.toLowerCase().equals("none".toLowerCase())) {
                        n2 = n;
                    }
                    this.g.select(n2);
                }
            }
            this.f.b();
            if (c) {
                ji.io.h.d(this.o, "jiAnnSecurityFrameItem9");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void a() {
        try {
            this.hide();
            if (this.p != null) {
                this.p.a();
                this.p = null;
            }
            if (this.h != null) {
                if (this.k != null) {
                    this.h.removeKeyListener(this.k);
                }
                if (this.l != null) {
                    this.h.removeActionListener(this.l);
                }
                this.a.remove(this.h);
                this.h = null;
            }
            if (this.i != null) {
                if (this.k != null) {
                    this.i.removeKeyListener(this.k);
                }
                if (this.l != null) {
                    this.i.removeActionListener(this.l);
                }
                this.a.remove(this.i);
                this.i = null;
            }
            if (this.c != null) {
                if (this.k != null) {
                    this.c.removeKeyListener(this.k);
                }
                if (this.a != null) {
                    this.a.remove(this.c);
                }
                this.c = null;
            }
            if (this.d != null) {
                if (this.k != null) {
                    this.d.removeKeyListener(this.k);
                }
                if (this.a != null) {
                    this.a.remove(this.d);
                }
                this.d = null;
            }
            if (this.e != null) {
                if (this.k != null) {
                    this.e.removeKeyListener(this.k);
                }
                if (this.a != null) {
                    this.a.remove(this.e);
                }
                this.e = null;
            }
            this.b = null;
            if (this.f != null) {
                this.f.a();
                this.f.removeAll();
                if (this.k != null) {
                    this.f.removeKeyListener(this.k);
                }
                if (this.f.getParent() != null) {
                    this.f.getParent().removeAll();
                }
                this.f.releaseResources();
                this.f = null;
            }
            if (this.g != null) {
                this.g.removeAll();
                if (this.k != null) {
                    this.g.removeKeyListener(this.k);
                }
                if (this.a != null) {
                    this.a.remove(this.g);
                }
                this.g = null;
            }
            if (this.a != null) {
                this.remove(this.a);
                this.a.releaseResources();
                this.a = null;
            }
            if (this.q != null) {
                this.q.c();
                this.q = null;
            }
            if (this.r != null) {
                this.r.c();
                this.r = null;
            }
            if (this.ac != null) {
                while (this.ac.b() > 0) {
                    this.ac.d(0);
                }
                this.ac = null;
            }
            this.v = null;
            this.w = null;
            this.aa = null;
        }
        catch (Exception ex) {}
        try {
            this.dispose();
        }
        catch (Exception ex2) {}
    }
    
    public void dispose() {
        this.hide();
    }
    
    private class aat implements Runnable
    {
        public void run() {
            try {
                ea.this.a(ea.this.p.al(true, ea.this.v), ea.this.p.aj(true, ea.this.v));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    class ac3 implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getStateChange() == 1) {
                try {
                    boolean b;
                    boolean b2;
                    if (itemEvent.getSource().equals(ea.this.c)) {
                        b = true;
                        b2 = false;
                    }
                    else if (itemEvent.getSource().equals(ea.this.d)) {
                        b = false;
                        b2 = true;
                    }
                    else {
                        b = true;
                        b2 = true;
                    }
                    ea.this.p.ak(b, ea.this.v);
                    ea.this.p.ai(b2, ea.this.v);
                    ea.this.a(b, b2);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    class ac2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            try {
                if (actionEvent.getSource().equals(ea.this.h)) {
                    ea.this.b();
                }
                else if (actionEvent.getSource().equals(ea.this.i)) {
                    ea.this.c();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    class ac1 extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            try {
                if (keyEvent.getKeyCode() == 27) {
                    ea.this.c();
                }
                else if (keyEvent.getKeyCode() == 10 && ea.this.h != null) {
                    if (keyEvent.getSource().equals(ea.this.h)) {
                        ea.this.b();
                    }
                    else {
                        ea.this.c();
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    class ac0 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (ea.this.n) {
                ea.this.b();
            }
            else {
                ea.this.c();
            }
        }
    }
    
    interface aem
    {
    }
}
