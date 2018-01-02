// 
// Decompiled by Procyon v0.5.30
// 

package ji.adjustment;

import ji.v1event.ar;
import ji.v1base.ek;
import java.awt.event.KeyAdapter;
import java.awt.event.TextEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import ji.v1event.af;
import ji.awt.bb;
import java.awt.event.ItemEvent;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Component;
import ji.util.e;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import ji.v1event.c9;
import ji.v1event.bo;
import ji.io.h;
import ji.util.d;
import java.awt.Frame;
import java.awt.Dimension;
import ji.graphic.c8;
import ji.document.ad;
import ji.v1base.bn;
import ji.awt.eb;
import java.awt.Label;
import ji.awt.fs;
import java.awt.CheckboxGroup;
import ji.graphic.jiImageButton;
import ji.v1base.jiPanel;
import java.awt.event.TextListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import ji.v1base.bl;

public class fr extends bl implements ItemListener, ActionListener, KeyListener, TextListener
{
    private jiPanel a;
    private jiPanel b;
    private jiPanel c;
    private jiPanel d;
    private jiPanel e;
    private jiPanel f;
    private jiPanel g;
    private jiPanel h;
    private jiPanel i;
    private jiPanel j;
    private jiPanel k;
    private jiImageButton l;
    private jiImageButton m;
    private jiImageButton n;
    private jiImageButton o;
    private jiImageButton p;
    private jiImageButton q;
    private jiImageButton r;
    private jiImageButton s;
    private jiImageButton t;
    private CheckboxGroup u;
    private fs v;
    private fs w;
    private fs x;
    private fs y;
    private CheckboxGroup z;
    private boolean aa;
    private Label ab;
    private wh ac;
    private eb ad;
    private eb ae;
    private Label af;
    private Label ag;
    private Label ah;
    private wh ai;
    private wh aj;
    private wh ak;
    private bn al;
    private bn am;
    private ad an;
    private eh ao;
    private boolean ap;
    private String aq;
    private c8 ar;
    private boolean as;
    private boolean at;
    private boolean au;
    private Dimension av;
    private boolean aw;
    
    public fr(boolean ap, final String aq, final Frame frame, final ad an, final eh ao) {
        super(frame, ji.util.d.b(1026, aq), false);
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = false;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.ao = null;
        this.aq = null;
        this.ar = null;
        this.as = false;
        this.at = false;
        this.au = false;
        this.av = null;
        this.aw = true;
        this.aq = aq;
        final boolean ec = ji.util.d.ec();
        final boolean ed = ji.util.d.ed();
        String s = "10";
        String s2 = eh.c(0, aq);
        String s3 = eh.c(2, aq);
        Point point = null;
        String s4 = null;
        final Dimension dimension = new Dimension(22, 19);
        this.av = new Dimension(an.j9(), an.ka());
        this.an = an;
        this.ao = ao;
        if (!ed && !ec) {
            ji.io.h.d(aq, "Image adjustment dialog called with illegal foregroundAllowed and backgroundAllowed parameters");
        }
        if (ap && !ec) {
            ap = false;
        }
        if (!ap && !ed) {
            ap = true;
        }
        String s5;
        String s6;
        String s7;
        String s8;
        String s9;
        String s10;
        if (this.ap = ap) {
            s5 = "1012";
            s6 = "1013";
            s7 = "1014";
            s8 = "1015";
            s9 = "1022";
            s10 = "1023";
            if (ao != null) {
                s = String.valueOf(ao.m());
                s2 = eh.c(ao.o(), aq);
                point = ao.q();
                s4 = String.valueOf(ao.s());
                s3 = eh.c(ao.g(), aq);
                this.aa = ao.e();
            }
        }
        else {
            s5 = "1016";
            s6 = "1017";
            s7 = "1018";
            s8 = "1019";
            s9 = "1020";
            s10 = "1021";
            if (ao != null) {
                s = String.valueOf(ao.n());
                s2 = eh.c(ao.p(), aq);
                point = ao.r();
                s4 = String.valueOf(ao.t());
                s3 = eh.c(ao.h(), aq);
                this.aa = ao.f();
            }
        }
        this.a = new jiPanel(aq);
        this.ar = new c8(aq, this.a, an.isSwing(), an.a9(), aq);
        if (ec && ed) {
            this.u = new CheckboxGroup();
            (this.v = new fs(aq, ji.util.d.b(1024, aq), ap, this.u)).a(this);
            this.v.addKeyListener(this);
            this.v.setFocusRingId(bo.g);
            this.v.a(ap);
            (this.w = new fs(aq, ji.util.d.b(1025, aq), !ap, this.u)).a(this);
            this.w.addKeyListener(this);
            this.w.setFocusRingId(bo.g);
            this.w.a(!ap);
        }
        (this.p = new jiImageButton(s9, 5, dimension.width, dimension.height, false, true, false, true, 2, "1037", "1037", 5, false, null, null, false, null, -1, aq, false)).a(this.ar);
        this.p.a(bo.g);
        (this.n = new jiImageButton(s7, 0, dimension.width, dimension.height, false, true, false, true, 2, "1035", "1035", 5, false, null, null, false, null, -1, aq, false)).a(this.ar);
        this.n.a(bo.g);
        (this.q = new jiImageButton(s10, 4, dimension.width, dimension.height, false, true, false, true, 2, "1038", "1038", 5, false, null, null, false, null, -1, aq, false)).a(this.ar);
        this.q.a(bo.g);
        (this.l = new jiImageButton(s5, 2, dimension.width, dimension.height, false, true, false, true, 2, "1033", "1033", 5, false, null, null, false, null, -1, aq, false)).a(this.ar);
        this.l.a(bo.g);
        (this.t = new jiImageButton("1032", 8, dimension.width, dimension.height, false, true, false, true, 2, "1032", "1032", 5, false, null, null, false, null, -1, aq, false)).a(this.ar);
        this.t.a(bo.g);
        (this.m = new jiImageButton(s6, 3, dimension.width, dimension.height, false, true, false, true, 2, "1034", "1034", 5, false, null, null, false, null, -1, aq, false)).a(this.ar);
        this.m.a(bo.g);
        (this.s = new jiImageButton(s10, 6, dimension.width, dimension.height, false, true, false, true, 2, "1038", "1038", 5, false, null, null, false, null, -1, aq, false)).a(this.ar);
        this.s.a(bo.g);
        (this.o = new jiImageButton(s8, 1, dimension.width, dimension.height, false, true, false, true, 2, "1036", "1036", 5, false, null, null, false, null, -1, aq, false)).a(this.ar);
        this.o.a(bo.g);
        (this.r = new jiImageButton(s9, 7, dimension.width, dimension.height, false, true, false, true, 2, "1037", "1037", 5, false, null, null, false, null, -1, aq, false)).a(this.ar);
        this.r.a(bo.g);
        this.l.addActionListener(an);
        this.m.addActionListener(an);
        this.n.addActionListener(an);
        this.o.addActionListener(an);
        this.t.addActionListener(this);
        this.q.addActionListener(an);
        this.s.addActionListener(an);
        this.p.addActionListener(an);
        this.r.addActionListener(an);
        this.ab = new Label(ji.util.d.b(1027, aq));
        (this.ac = new wh(aq, s, false)).addTextListener(this);
        this.ac.addKeyListener(this);
        this.ac.a(bo.g);
        this.ad = new eb(aq, new FlowLayout());
        final String[] a = eh.a(aq);
        for (int i = 0; i < a.length; ++i) {
            this.ad.a(a[i]);
        }
        this.ad.b(s2);
        this.ad.a(this);
        this.ad.setBounds(0, 0, 40, 20);
        this.ad.setFocusRingId(bo.g);
        this.af = new Label(ji.util.d.b(1028, aq));
        this.ag = new Label("x");
        if (this.aa) {
            this.ai = new wh(aq, String.valueOf(this.av.width / 2), false);
            this.aj = new wh(aq, String.valueOf(this.av.height / 2), false);
            this.ai.setEnabled(false);
            this.aj.setEnabled(false);
        }
        else {
            this.ai = new wh(aq, String.valueOf(point.x), false);
            this.aj = new wh(aq, String.valueOf(point.y), false);
            this.ai.setEnabled(true);
            this.aj.setEnabled(true);
        }
        this.ah = new Label(ji.util.d.b(1029, aq));
        this.ak = new wh(aq, s4, true);
        this.ai.addTextListener(this);
        this.aj.addTextListener(this);
        this.aj.addKeyListener(this);
        this.ac.addKeyListener(this);
        this.ak.addTextListener(this);
        this.ai.a(bo.g);
        this.aj.a(bo.g);
        this.ak.a(bo.g);
        this.z = new CheckboxGroup();
        (this.x = new fs(aq, ji.util.d.b(1055, aq), !this.aa, this.z)).a(this);
        this.x.addKeyListener(this);
        this.x.setFocusRingId(bo.g);
        (this.y = new fs(aq, ji.util.d.b(1056, aq), this.aa, this.z)).a(this);
        this.y.addKeyListener(this);
        this.y.setFocusRingId(bo.g);
        this.ae = new eb(aq, new FlowLayout());
        final String[] b = eh.b(aq);
        for (int j = 0; j < b.length; ++j) {
            this.ae.a(b[j]);
        }
        this.ae.b(s3);
        this.ae.a(this);
        this.ae.setBounds(0, 0, 40, 20);
        this.ae.setFocusRingId(bo.g);
        ji.util.e.a(this.al = new bn(aq, ji.util.d.b(1043, aq)));
        this.al.addKeyListener(this);
        this.al.addActionListener(this);
        this.al.a(bo.g);
        ji.util.e.a(this.am = new bn(aq, ji.util.d.b(276, aq)));
        this.am.addKeyListener(this);
        this.am.addActionListener(this);
        this.am.a(bo.g);
        (this.b = new jiPanel(aq, new FlowLayout())).add(this.ac);
        this.b.add(this.ad);
        (this.c = new jiPanel(aq, new FlowLayout())).add(this.ak);
        this.c.add(this.ae);
        (this.f = new jiPanel(aq, new FlowLayout())).add(this.ai);
        this.f.add(this.ag);
        this.f.add(this.aj);
        (this.e = new jiPanel(aq, new FlowLayout())).add(this.x);
        this.e.add(this.y);
        (this.d = new jiPanel(aq, new GridLayout(2, 1))).add(this.f);
        this.d.add(this.e);
        (this.g = new jiPanel(aq, new FlowLayout())).add(this.al);
        this.g.add(this.am);
        if (ed && ec) {
            (this.h = new jiPanel(aq, new FlowLayout())).add(this.v);
            this.h.add(this.w);
        }
        (this.i = new jiPanel(aq, new GridLayout(3, 3, 0, 0))).add(this.p);
        this.i.add(this.n);
        this.i.add(this.q);
        this.i.add(this.l);
        this.i.add(this.t);
        this.i.add(this.m);
        this.i.add(this.s);
        this.i.add(this.o);
        this.i.add(this.r);
        this.j = new jiPanel(aq, new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.j.add(this.b, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        this.j.add(this.af, gridBagConstraints2);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 3;
        this.j.add(this.d, gridBagConstraints3);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 4;
        this.j.add(this.ah, gridBagConstraints4);
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 5;
        this.j.add(this.c, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        this.j.add(this.ab, gridBagConstraints6);
        (this.k = new jiPanel(aq, new BorderLayout())).add(this.i, "North");
        this.k.add(this.j, "South");
        this.a.addKeyListener(this);
        this.a.setLayout(new BorderLayout());
        if (ed && ec) {
            this.a.add(this.h, "North");
        }
        this.a.add(this.k, "Center");
        this.a.add(this.g, "South");
        this.add(this.a);
        this.addKeyListener(an);
        this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 4 * 3 - 62, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 170);
        this.pack();
        this.setResizable(false);
    }
    
    public void a(final eh ao) {
        try {
            this.ao = ao;
            this.d(this.b());
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public void setEnabled(final boolean b) {
        if (this.aw != b) {
            this.aw = b;
            this.l.setEnabled(b);
            this.m.setEnabled(b);
            this.n.setEnabled(b);
            this.o.setEnabled(b);
            this.p.setEnabled(b);
            this.q.setEnabled(b);
            this.r.setEnabled(b);
            this.s.setEnabled(b);
            this.t.setEnabled(b);
            if (this.v != null) {
                this.v.setEnabled(b);
            }
            if (this.w != null) {
                this.w.setEnabled(b);
            }
            this.x.setEnabled(b);
            this.y.setEnabled(b);
            this.ac.setEnabled(b);
            this.ad.setEnabled(b);
            this.ae.setEnabled(b);
            this.ai.setEnabled(b);
            this.aj.setEnabled(b);
            this.ak.setEnabled(b);
            this.al.setEnabled(b);
            this.am.setEnabled(b);
        }
    }
    
    public boolean isEnabled() {
        return this.aw;
    }
    
    public void a(final boolean b) {
        if (b) {
            this.at = true;
        }
        else {
            this.au = true;
        }
    }
    
    public void b(final boolean as) {
        this.as = as;
    }
    
    public boolean a() {
        return this.as;
    }
    
    public boolean b() {
        return this.ap;
    }
    
    private void d(final boolean b) {
        if (b) {
            this.ap = true;
            this.l.setId("1012");
            this.m.setId("1013");
            this.n.setId("1014");
            this.o.setId("1015");
            this.p.setId("1022");
            this.q.setId("1023");
            this.r.setId("1022");
            this.s.setId("1023");
            if (this.ao != null) {
                this.ac.setText(String.valueOf(this.ao.m()));
                this.ad.b(eh.c(this.ao.o(), this.aq));
                this.ae.b(eh.c(this.ao.g(), this.aq));
                this.ak.setText(String.valueOf(this.ao.s()));
                this.aa = this.ao.e();
                if (this.aa) {
                    this.y.a(true);
                    this.ai.setText(String.valueOf(this.av.width / 2));
                    this.aj.setText(String.valueOf(this.av.height / 2));
                    this.ai.setEnabled(false);
                    this.aj.setEnabled(false);
                }
                else {
                    this.x.a(true);
                    this.ai.setText(String.valueOf(this.ao.q().x));
                    this.aj.setText(String.valueOf(this.ao.q().y));
                    this.ai.setEnabled(true);
                    this.aj.setEnabled(true);
                }
            }
        }
        else {
            this.ap = false;
            this.l.setId("1016");
            this.m.setId("1017");
            this.n.setId("1018");
            this.o.setId("1019");
            this.p.setId("1020");
            this.q.setId("1021");
            this.r.setId("1020");
            this.s.setId("1021");
            if (this.ao != null) {
                this.ac.setText(String.valueOf(this.ao.n()));
                this.ad.b(eh.c(this.ao.p(), this.aq));
                this.ae.b(eh.c(this.ao.h(), this.aq));
                this.ak.setText(String.valueOf(this.ao.t()));
            }
            this.aa = this.ao.f();
            if (this.aa) {
                this.y.a(true);
                this.ai.setText(String.valueOf(this.av.width / 2));
                this.aj.setText(String.valueOf(this.av.height / 2));
                this.ai.setEnabled(false);
                this.aj.setEnabled(false);
            }
            else {
                this.x.a(true);
                this.ai.setText(String.valueOf(this.ao.r().x));
                this.aj.setText(String.valueOf(this.ao.r().y));
                this.ai.setEnabled(true);
                this.aj.setEnabled(true);
            }
        }
    }
    
    public boolean c() {
        return !this.aa;
    }
    
    public void a(final Point point) {
        this.ai.setText(String.valueOf(point.x));
        this.aj.setText(String.valueOf(point.y));
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            if (this.v != null && itemEvent.getSource().equals(this.v.a())) {
                this.d(true);
            }
            else if (this.w != null && itemEvent.getSource().equals(this.w.a())) {
                this.d(false);
            }
            else if (itemEvent.getSource().equals(this.x.a())) {
                this.aa = false;
                this.ai.setEnabled(true);
                this.aj.setEnabled(true);
                String text = this.ai.getText();
                if (text.equals("")) {
                    text = "0";
                }
                String text2 = this.aj.getText();
                if (text2.equals("")) {
                    text2 = "0";
                }
                final Point point = new Point(Integer.valueOf(text), Integer.valueOf(text2));
                if (this.ap) {
                    this.an.a(point, this.aa);
                    this.ao.a(point, this.aa);
                }
                else {
                    this.an.b(point, this.aa);
                    this.ao.b(point, this.aa);
                }
            }
            else if (itemEvent.getSource().equals(this.y.a())) {
                this.aa = true;
                final Point point2 = new Point(this.av.width / 2, this.av.height / 2);
                this.ai.setText(String.valueOf(this.av.width / 2));
                this.aj.setText(String.valueOf(this.av.height / 2));
                this.ai.setEnabled(false);
                this.aj.setEnabled(false);
                if (this.ap) {
                    this.an.a(point2, this.aa);
                    this.ao.a(point2, this.aa);
                }
                else {
                    this.an.b(point2, this.aa);
                    this.ao.b(point2, this.aa);
                }
            }
            else if (itemEvent.getSource().equals(this.ad.c())) {
                final String s = (String)itemEvent.getItem();
                if (this.ap) {
                    this.an.r(eh.c(s, this.aq));
                    this.ao.a(s, this.aq);
                }
                else {
                    this.an.s(eh.c(s, this.aq));
                    this.ao.b(s, this.aq);
                }
            }
            else if (itemEvent.getSource().equals(this.ae.c())) {
                final String s2 = (String)itemEvent.getItem();
                if (this.ap) {
                    this.an.t(eh.c(s2, this.aq));
                    this.ao.a(eh.c(s2, this.aq));
                }
                else {
                    this.an.u(eh.c(s2, this.aq));
                    this.ao.b(eh.c(s2, this.aq));
                }
            }
        }
    }
    
    public bb f() {
        boolean a = false;
        bb bb = null;
        if (this.at || this.au) {
            a = ji.util.d.a(ji.util.d.b(1048, this.aq), ji.util.d.b(1103, this.aq), null, null, this.aq);
        }
        if (a) {
            if (this.at || this.au) {
                bb = this.an.cb();
            }
            this.at = false;
            this.au = false;
        }
        else {
            bb = this.c(true);
            this.at = false;
            this.au = false;
        }
        return bb;
    }
    
    private void e(final boolean b) {
        boolean a = true;
        if ((this.at || this.au) && b) {
            a = ji.util.d.a(ji.util.d.b(1048, this.aq), ji.util.d.b(1049, this.aq), null, null, this.aq);
        }
        if (a) {
            if (this.at || this.au) {
                this.an.cb();
            }
            this.at = false;
            this.au = false;
            this.an.bf(false);
        }
    }
    
    private void f(final boolean b) {
        boolean g = true;
        if (b) {
            g = this.g();
        }
        if (g) {
            this.c(this.as = true);
            this.at = false;
            this.au = false;
            this.an.bf(false);
        }
        else {
            this.requestFocus();
        }
    }
    
    public boolean g() {
        return (!this.at && !this.au) || ji.util.d.a(ji.util.d.b(1046, this.aq), ji.util.d.b(1047, this.aq), null, null, this.aq);
    }
    
    private void i() {
        if ((this.ap && this.at) || (!this.ap && this.au)) {
            if (ji.util.d.a(ji.util.d.b(1044, this.aq), ji.util.d.b(1045, this.aq), null, null, this.aq)) {
                this.c(false);
            }
            else {
                this.requestFocus();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.al)) {
            this.f(true);
        }
        else if (actionEvent.getSource().equals(this.am)) {
            this.e(true);
        }
        else if (actionEvent.getSource().equals(this.t)) {
            this.i();
        }
    }
    
    public bb c(final boolean b) {
        if ((this.ap || b) && this.ao != null) {
            this.at = false;
        }
        if ((!this.ap || b) && this.ao != null) {
            this.au = false;
        }
        return this.an.ca();
    }
    
    public void h() {
        try {
            this.hide();
            if (this.ad != null) {
                this.ad.releaseResources();
            }
            if (this.ae != null) {
                this.ae.releaseResources();
            }
            if (this.an != null) {
                if (this.l != null) {
                    this.l.removeActionListener(this.an);
                    this.remove(this.l);
                }
                this.l = null;
                if (this.m != null) {
                    this.m.removeActionListener(this.an);
                    this.remove(this.m);
                }
                this.m = null;
                if (this.n != null) {
                    this.n.removeActionListener(this.an);
                    this.remove(this.n);
                }
                this.n = null;
                if (this.o != null) {
                    this.o.removeActionListener(this.an);
                    this.remove(this.o);
                }
                this.o = null;
                if (this.p != null) {
                    this.p.removeActionListener(this.an);
                    this.remove(this.p);
                }
                this.p = null;
                if (this.r != null) {
                    this.r.removeActionListener(this.an);
                    this.remove(this.r);
                }
                this.r = null;
                if (this.q != null) {
                    this.q.removeActionListener(this.an);
                    this.remove(this.q);
                }
                this.q = null;
                if (this.s != null) {
                    this.s.removeActionListener(this.an);
                    this.remove(this.s);
                }
                this.s = null;
                if (this.t != null) {
                    this.t.removeActionListener(this.an);
                    this.remove(this.t);
                }
                this.t = null;
            }
            this.an = null;
            if (this.ar != null) {
                this.ar.a();
            }
            this.ar = null;
            if (this.a != null) {
                this.a.releaseResources();
            }
            this.a = null;
            if (this.b != null) {
                this.b.releaseResources();
            }
            this.b = null;
            if (this.d != null) {
                this.d.releaseResources();
            }
            this.d = null;
            if (this.e != null) {
                this.e.releaseResources();
            }
            if (this.f != null) {
                this.f.releaseResources();
            }
            if (this.g != null) {
                this.g.releaseResources();
            }
            this.g = null;
            if (this.h != null) {
                this.h.releaseResources();
            }
            this.h = null;
            if (this.i != null) {
                this.i.releaseResources();
            }
            this.i = null;
            if (this.j != null) {
                this.j.releaseResources();
            }
            this.j = null;
            if (this.k != null) {
                this.k.releaseResources();
            }
            this.k = null;
            if (this.v != null) {
                this.v.releaseResources();
            }
            this.v = null;
            if (this.w != null) {
                this.w.releaseResources();
            }
            this.w = null;
            if (this.x != null) {
                this.x.releaseResources();
            }
            this.x = null;
            if (this.y != null) {
                this.y.releaseResources();
            }
            this.y = null;
            this.ab = null;
            if (this.ac != null) {
                this.ac.a();
                this.ac.removeTextListener(this);
            }
            this.ac = null;
            this.u = null;
            if (this.ad != null) {
                this.ad.releaseResources();
            }
            this.ad = null;
            if (this.ae != null) {
                this.ae.releaseResources();
            }
            this.ae = null;
            if (this.ai != null) {
                this.ai.a();
                this.ai.removeTextListener(this);
            }
            this.ai = null;
            if (this.aj != null) {
                this.aj.a();
                this.aj.removeTextListener(this);
            }
            this.aj = null;
            if (this.ak != null) {
                this.ak.removeTextListener(this);
            }
            this.ak = null;
            if (this.al != null) {
                this.al.removeKeyListener(this);
                this.al.removeActionListener(this);
                this.al.a();
            }
            this.al = null;
            if (this.am != null) {
                this.am.removeKeyListener(this);
                this.am.removeActionListener(this);
                this.am.a();
            }
            this.am = null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.dispose();
        }
        catch (Exception ex2) {}
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        try {
            if (keyEvent.getKeyCode() == 27) {
                this.an.bf(false);
            }
            else if (keyEvent.getKeyCode() == 10) {
                if (this.al != null && keyEvent.getSource().equals(this.al)) {
                    this.f(true);
                    return;
                }
                if (this.am != null && keyEvent.getSource().equals(this.am)) {
                    this.e(true);
                    return;
                }
                if (this.v != null && keyEvent.getSource().equals(this.v.a())) {
                    this.v.a(true);
                    this.d(true);
                }
                if (this.w != null && keyEvent.getSource().equals(this.w.a())) {
                    this.w.a(true);
                    this.d(false);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        if (textEvent.getSource().equals(this.ac)) {
            String s = this.ac.getText();
            if (s.equals("")) {
                s = "0";
            }
            if (s.startsWith(".")) {
                s = "0".concat(String.valueOf(String.valueOf(s)));
            }
            if (this.a(s, false)) {
                if (this.ap) {
                    this.an.p((int)Integer.valueOf(s));
                    this.ao.c(Integer.valueOf(s));
                }
                else {
                    this.an.q(Integer.valueOf(s));
                    this.ao.d(Integer.valueOf(s));
                }
            }
        }
        else if (textEvent.getSource().equals(this.ai) || textEvent.getSource().equals(this.aj)) {
            String s2 = this.ai.getText();
            if (s2.equals("")) {
                s2 = "0";
            }
            if (s2.startsWith(".")) {
                s2 = "0".concat(String.valueOf(String.valueOf(s2)));
            }
            String s3 = this.aj.getText();
            if (s3.equals("")) {
                s3 = "0";
            }
            if (s3.startsWith(".")) {
                s3 = "0".concat(String.valueOf(String.valueOf(s3)));
            }
            if (this.a(s2, false) && this.a(s3, false)) {
                final Point point = new Point(Integer.valueOf(s2), Integer.valueOf(s3));
                if (this.ap) {
                    this.an.a(point, this.aa);
                    this.ao.a(point, this.aa);
                }
                else {
                    this.an.b(point, this.aa);
                    this.ao.b(point, this.aa);
                }
            }
        }
        else if (textEvent.getSource().equals(this.ak)) {
            String s4 = this.ak.getText();
            if (s4.equals("")) {
                s4 = "0";
            }
            if (s4.startsWith(".")) {
                s4 = "0".concat(String.valueOf(String.valueOf(s4)));
            }
            if (this.a(s4, true)) {
                if (this.ap) {
                    this.an.b((double)Double.valueOf(s4));
                    this.ao.a(Double.valueOf(s4));
                }
                else {
                    this.an.c((double)Double.valueOf(s4));
                    this.ao.b(Double.valueOf(s4));
                }
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private boolean a(final String s, final boolean b) {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            final boolean b2 = b && char1 == '.';
            if (char1 != '\b' && char1 != '\u007f' && char1 != '\n' && char1 != '\t' && !b2 && !Character.isDigit(char1)) {
                return false;
            }
        }
        return true;
    }
    
    private class wj extends KeyAdapter
    {
        private boolean a;
        
        public wj(final boolean a) {
            this.a = false;
            this.a = a;
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
            if (!fr.this.a(String.valueOf(String.valueOf(keyEvent.getKeyChar())).concat(""), this.a)) {
                keyEvent.consume();
            }
        }
    }
    
    private class wh extends ek
    {
        public wh(final String s, final String s2, final boolean b) {
            super(s, s2);
            ji.util.e.a(s, this, true);
            this.addKeyListener(new wj(b));
        }
        
        public void a() {
            ji.util.e.a(fr.this.aq, this, false);
        }
    }
}
