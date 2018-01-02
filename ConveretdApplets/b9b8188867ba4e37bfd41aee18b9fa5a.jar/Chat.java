import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Image;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.CardLayout;
import java.awt.Canvas;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.TextField;
import java.awt.List;
import java.awt.Font;
import java.awt.Panel;
import java.util.Hashtable;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Chat extends Applet implements Runnable
{
    public Hashtable h0;
    public boolean h_;
    public e hz;
    public k hy;
    public f hx;
    public String a2;
    public boolean hw;
    public Hashtable hv;
    public boolean hu;
    public Thread ht;
    public p hs;
    public boolean hr;
    public Panel hq;
    public Panel hp;
    public Panel ho;
    public Font hn;
    public o hm;
    public a hl;
    public List hk;
    public b hj;
    public TextField l;
    public Button hi;
    public Button hh;
    public Button hg;
    public Button hf;
    public Button he;
    public Checkbox hd;
    public Checkbox hc;
    public Checkbox hb;
    public Panel ha;
    public Panel g9;
    public Panel g8;
    public Panel g7;
    public Button g6;
    public Button bh;
    public Panel g5;
    public Panel g4;
    public Choice g3;
    public Choice g2;
    public TextField g1;
    public TextField g0;
    public TextField g_;
    public TextField gz;
    public TextField gy;
    public TextField gx;
    public TextField gw;
    public TextField gv;
    public TextField gu;
    public Panel gt;
    public GridBagLayout gs;
    public GridBagConstraints gr;
    public Label gq;
    public Label gp;
    public Label go;
    public Label gn;
    public Label gm;
    public Label gl;
    public Label gk;
    public Label gj;
    public Label gi;
    public Canvas gh;
    public Canvas gg;
    public Canvas gf;
    public Canvas ge;
    public Canvas gd;
    public Canvas gc;
    public Canvas gb;
    public Canvas ga;
    public Panel f9;
    public h f8;
    public Panel f7;
    public Panel f6;
    public Panel f5;
    public Label f4;
    public Label f3;
    public Label f2;
    public CardLayout f1;
    public int f0;
    public Font f_;
    public Font fz;
    public String fy;
    public int fx;
    public boolean al;
    
    public Chat() {
        this.h0 = new Hashtable();
        this.h_ = false;
        this.a2 = "\u0003";
        this.hw = false;
        this.hv = new Hashtable();
        this.hu = true;
        this.hs = new p();
        this.hr = true;
        this.g1 = new TextField(30);
        this.g0 = new TextField(10);
        this.g_ = new TextField(10);
        this.gz = new TextField(10);
        this.gy = new TextField(30);
        this.gx = new TextField(10);
        this.gw = new TextField(10);
        this.gv = new TextField(10);
        this.gu = new TextField(30);
        this.f0 = -11;
        this.al = false;
        if (!this.hr) {
            this.init();
            this.start();
        }
    }
    
    public final void init() {
        this.ci();
        this.cj();
        this.gq = new Label(this.hs.dg);
        this.gp = new Label(this.hs.de);
        this.go = new Label(this.hs.dl);
        this.gn = new Label(this.hs.dj);
        this.gm = new Label(this.hs.dh);
        this.gl = new Label(this.hs.df);
        this.gk = new Label(this.hs.di);
        this.gj = new Label(this.hs.dk);
        this.gi = new Label(this.hs.cy);
        new Vector();
        this.f3 = new Label();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.hs.z, ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.hz = new e(stringTokenizer.nextToken());
            if (this.hz.q()) {
                final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
                System.out.println("docbase=" + lowerCase + ", " + "Licensehost=" + this.hz.aa);
                if (!lowerCase.endsWith(this.hz.aa)) {
                    continue;
                }
                this.hs.ae = this.hz.ae;
                this.hs.ad = this.hz.ad;
                this.hs.ac = this.hz.ac;
                this.h_ = true;
            }
        }
        this.hf = new Button(this.hs.c9);
        this.br();
        this.setFont(this.hn);
        this.ch();
        this.cg();
        this.f1 = new CardLayout();
        (this.hq = new Panel()).setLayout(this.f1);
        this.hq.add("Config", this.hp);
        this.hq.add("Chat", this.ho);
        if (this.hs.d9) {
            this.f1.show(this.hq, "Chat");
        }
        else {
            this.f1.show(this.hq, "Config");
        }
        this.setLayout(new BorderLayout(this.hs.fp, this.hs.fp));
        this.add("North", new Canvas());
        this.add("South", new Canvas());
        this.add("West", new Canvas());
        this.add("East", new Canvas());
        this.add("Center", this.hq);
    }
    
    public final void cj() {
        this.h0.clear();
        final String string = this.getCodeBase().toString();
        final Enumeration<String> keys = this.hs.fv.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = this.hs.fv.get(s);
            try {
                final Image image = this.getImage(new URL(String.valueOf(string) + s2));
                this.prepareImage(image, null);
                this.h0.put(s, image);
            }
            catch (Exception ex) {}
        }
    }
    
    public final void ci() {
        this.hs.bl(this);
    }
    
    public final void ch() {
        int n = 0;
        this.gs = new GridBagLayout();
        this.gr = new GridBagConstraints();
        this.gr.insets = new Insets(this.hs.fq, this.hs.fr, 0, this.hs.fr);
        this.g3 = new Choice();
        for (int size = this.hs.fh.size(), i = 0; i < size; ++i) {
            this.g3.addItem((String)this.hs.fh.elementAt(i));
        }
        if (this.g3.countItems() <= 0) {
            this.g3.addItem(this.hs.fc);
        }
        this.g2 = new Choice();
        for (int size2 = this.hs.fj.size(), j = 0; j < size2; ++j) {
            this.g2.addItem((String)this.hs.fj.elementAt(j));
        }
        if (this.g2.countItems() <= 0) {
            this.g2.addItem(this.hs.fe);
        }
        (this.hd = new Checkbox(this.hs.dd)).setBackground(this.hs.e_);
        this.hd.setState(this.hs.d4);
        (this.hb = new Checkbox(this.hs.dc)).setState(this.hs.d_);
        this.hb.setBackground(this.hs.e_);
        (this.g7 = new Panel()).setBackground(this.hs.e_);
        this.g7.setLayout(new GridLayout(1, 2));
        this.g7.add(this.hd);
        this.g7.add(this.hb);
        (this.g4 = new Panel()).setLayout(this.gs);
        this.gr.weightx = 0.0;
        this.gr.weighty = 0.0;
        this.gr.fill = 0;
        this.g_.setText(this.hs.ff);
        this.gv.setEchoCharacter('*');
        this.gv.setText(this.hs.e6);
        this.gz.setText(this.g2.getItem(0));
        this.gy.setText(this.hs.e9);
        this.g1.setText(this.g3.getItem(0));
        this.g0.setText(Integer.toString(this.hs.fg));
        this.gw.setEchoCharacter('*');
        this.gx.setEchoCharacter('*');
        this.gw.setText(this.hs.e8);
        this.gx.setText(this.hs.e7);
        this.gu.setText(this.hs.e4);
        if (this.hs.ek) {
            this.gr.anchor = 13;
            this.b_(this.g4, this.go, this.gs, this.gr, 0, n, 1, 1);
            this.gr.anchor = 17;
            this.b_(this.g4, this.g_, this.gs, this.gr, 1, n, 1, 1);
            ++n;
        }
        if (this.hs.ej) {
            this.gr.anchor = 13;
            this.b_(this.g4, this.gj, this.gs, this.gr, 0, n, 1, 1);
            this.gr.anchor = 17;
            this.b_(this.g4, this.gv, this.gs, this.gr, 1, n, 2, 1);
            ++n;
        }
        if (this.hs.ei) {
            this.gr.anchor = 13;
            this.b_(this.g4, this.gn, this.gs, this.gr, 0, n, 1, 1);
            if (!this.hs.d1) {
                this.gr.anchor = 17;
                this.b_(this.g4, this.gz, this.gs, this.gr, 1, n, 1, 1);
                ++n;
            }
            this.gr.anchor = 17;
            this.b_(this.g4, this.g2, this.gs, this.gr, 1, n, 2, 1);
            ++n;
        }
        if (this.hs.eh) {
            this.gr.anchor = 13;
            this.b_(this.g4, this.gk, this.gs, this.gr, 0, n, 1, 1);
            this.gr.anchor = 17;
            this.b_(this.g4, this.gx, this.gs, this.gr, 1, n, 2, 1);
            ++n;
        }
        if (this.hs.eg) {
            this.gr.anchor = 13;
            this.b_(this.g4, this.gm, this.gs, this.gr, 0, n, 1, 1);
            this.gr.anchor = 17;
            this.b_(this.g4, this.gy, this.gs, this.gr, 1, n, 2, 1);
            ++n;
        }
        if (this.hs.ef) {
            this.gr.anchor = 13;
            this.b_(this.g4, this.gq, this.gs, this.gr, 0, n, 1, 1);
            if (!this.hs.d0) {
                this.gr.anchor = 17;
                this.b_(this.g4, this.g1, this.gs, this.gr, 1, n, 2, 1);
                ++n;
            }
            this.gr.anchor = 17;
            this.b_(this.g4, this.g3, this.gs, this.gr, 1, n, 2, 1);
            ++n;
        }
        if (this.hs.ee) {
            this.gr.anchor = 13;
            this.b_(this.g4, this.gl, this.gs, this.gr, 0, n, 1, 1);
            this.gr.anchor = 17;
            this.b_(this.g4, this.gw, this.gs, this.gr, 1, n, 2, 1);
            ++n;
        }
        if (this.hs.ed) {
            this.gr.anchor = 13;
            this.b_(this.g4, this.gp, this.gs, this.gr, 0, n, 1, 1);
            this.gr.anchor = 17;
            this.gr.insets = new Insets(this.hs.fq, this.hs.fr, this.hs.fq, this.hs.fr);
            this.b_(this.g4, this.g0, this.gs, this.gr, 1, n, 2, 1);
            ++n;
        }
        if (this.hs.eb) {
            this.gr.anchor = 13;
            this.b_(this.g4, this.gi, this.gs, this.gr, 0, n, 1, 1);
            this.gr.anchor = 17;
            this.gr.insets = new Insets(this.hs.fq, this.hs.fr, this.hs.fq, this.hs.fr);
            this.b_(this.g4, this.gu, this.gs, this.gr, 1, n, 2, 1);
            ++n;
        }
        if (this.hs.ec) {
            this.gr.anchor = 10;
            this.b_(this.g4, this.g7, this.gs, this.gr, 0, n, 4, 1);
        }
        (this.gh = new Canvas()).resize(10, 1);
        this.gh.setBackground(Color.white);
        (this.gg = new Canvas()).resize(10, 1);
        this.gg.setBackground(Color.white);
        (this.gd = new Canvas()).resize(1, 10);
        this.gd.setBackground(Color.white);
        (this.gc = new Canvas()).resize(1, 10);
        this.gc.setBackground(Color.white);
        (this.f9 = new Panel()).setLayout(new BorderLayout());
        this.f9.add("North", this.gh);
        this.f9.add("South", this.gg);
        this.f9.add("West", this.gd);
        this.f9.add("East", this.gc);
        this.f9.add("Center", this.g4);
        (this.gt = new Panel()).setLayout(new FlowLayout(1, this.hs.fr, this.hs.fq));
        this.hg = new Button(this.hs.db);
        this.g6 = new Button(this.hs.da);
        this.bh = new Button(this.hs.cx);
        this.gt.add(this.hg);
        this.gt.add(this.g6);
        if (this.hs.ea) {
            this.gt.add(this.bh);
        }
        if (!this.h_ || (!this.hs.dq && this.hs.do)) {
            this.gt.add(this.hf);
        }
        (this.g5 = new Panel()).setLayout(new BorderLayout(this.hs.fr, 0));
        this.g5.setBackground(Color.white);
        try {
            this.f8 = new h(this.getImage(new URL(String.valueOf(this.getCodeBase()) + "/IRClogo.gif")));
        }
        catch (Exception ex) {
            this.f8 = new h();
        }
        this.f8.resize(this.hs.e3, this.hs.e2);
        (this.f5 = new Panel()).add(this.f8);
        final Font font = new Font("TimesRoman", 0, 10);
        (this.f4 = new Label("JPilot jIRC applet V2.6.0")).setForeground(Color.red);
        this.f4.setFont(font);
        this.f7 = new Panel();
        if (this.h_) {
            this.f3.setForeground(Color.red);
            this.f3.setFont(font);
            (this.f2 = new Label("http://www.jpilot.com")).setForeground(Color.red);
            this.f2.setFont(font);
        }
        else {
            (this.f3 = new Label("Unregistered Copy")).setForeground(Color.red);
            this.f3.setFont(font);
            (this.f2 = new Label("http://www.jpilot.com")).setForeground(Color.red);
            this.f2.setFont(font);
            this.f7.setLayout(new GridLayout(3, 1, 0, 0));
            this.f7.add(this.f4);
            this.f7.add(this.f2);
            this.f7.add(this.f3);
        }
        (this.f6 = new Panel()).setLayout(new BorderLayout(this.hs.fr, this.hs.fq));
        this.f6.add("North", this.f5);
        this.f6.add("South", this.f7);
        (this.gb = new Canvas()).setBackground(Color.black);
        this.gb.resize(2, 10);
        (this.ga = new Canvas()).setBackground(Color.black);
        this.ga.resize(2, 10);
        (this.gf = new Canvas()).setBackground(Color.black);
        this.gf.resize(10, 2);
        (this.ge = new Canvas()).setBackground(Color.black);
        this.ge.resize(10, 2);
        this.g5.add("Center", this.f6);
        this.g5.add("East", this.gb);
        this.g5.add("West", this.ga);
        this.g5.add("North", this.gf);
        this.g5.add("South", this.ge);
        this.g5.setBackground(this.hs.ex);
        (this.hp = new Panel()).setFont(this.hn);
        this.hp.setForeground(this.hs.ew);
        this.hp.setBackground(this.hs.e_);
        this.hp.setLayout(new BorderLayout(this.hs.fr, this.hs.fq));
        this.hp.add("West", this.g5);
        this.hp.add("Center", this.f9);
        this.hp.add("South", this.gt);
    }
    
    public final void cg() {
        this.g9 = new Panel();
        (this.l = new TextField("", this.hs.fs)).setBackground(this.hs.eu);
        this.l.setForeground(this.hs.ev);
        this.l.setFont(this.hn);
        this.hi = new Button(this.hs.c7);
        this.hh = new Button(this.hs.c8);
        (this.he = new Button("B")).setFont(this.fz);
        (this.hl = new a("Channel", "0", "", this.hs.ep, this.hs.eo, this.hs.el, this.hs.em, this.hs.b, this.hs.a)).f(this.hs.bu);
        this.hl.j(this);
        this.hm = new o(this, this.hs.ev);
        (this.g8 = new Panel()).setLayout(new FlowLayout(1, 4, 0));
        if (this.hs.d8) {
            this.g8.add(this.he);
            this.g8.add(this.hm);
        }
        if (!this.hs.dq) {
            this.g8.add(this.hh);
        }
        else if (!this.h_ || this.hs.do) {
            this.g8.add(this.hf);
        }
        if (this.hs.d5) {
            this.g8.add(this.hi);
        }
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", this.l);
        this.g9.setLayout(new BorderLayout());
        this.g9.add("Center", panel);
        this.g9.add("East", this.g8);
        (this.ha = new Panel()).setFont(this.hn);
        this.ha.setLayout(new BorderLayout(this.hs.fr / 2, this.hs.fq / 2));
        this.ha.setBackground(this.hs.e_);
        final Canvas canvas = new Canvas();
        canvas.resize(1, 10);
        this.ha.add("East", canvas);
        (this.hk = new List(1, false)).setForeground(this.hs.ez);
        this.hk.setBackground(this.hs.ey);
        if (this.hs.d6) {
            if (this.hs.fo > 0) {
                final Canvas canvas2 = new Canvas();
                canvas2.resize(this.hs.fo, 1);
                this.ha.add("North", canvas2);
            }
            this.ha.add("Center", this.hk);
        }
        (this.hc = new Checkbox(this.hs.c5)).setState(this.hs.d3);
        this.hc.setBackground(this.hs.e_);
        if (this.hs.d7) {
            this.ha.add("South", this.hc);
        }
        (this.ho = new Panel()).setForeground(this.hs.ew);
        this.ho.setBackground(this.hs.e_);
        this.ho.setFont(this.hn);
        this.ho.setLayout(new BorderLayout(this.hs.fr, this.hs.fq));
        (this.hj = new b(200, 400)).j(this);
        this.hj.setBackground(this.hs.e0);
        this.hj.setForeground(this.hs.e1);
        this.hj.setFont(this.hn);
        this.hj.f(this.hs.bu);
        this.au(this.hs.dm);
        this.ho.add("South", this.g9);
        this.ho.add("East", this.ha);
        this.ho.add("Center", this.hj);
        this.ho.add("North", this.hl);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.hh) {
                    this.b4();
                    return true;
                }
                if (event.target == this.hd) {
                    this.hs.d4 = this.hd.getState();
                    return true;
                }
                if (event.target == this.hc) {
                    this.hs.d3 = this.hc.getState();
                    return true;
                }
                if (event.target == this.hb) {
                    this.hs.d_ = this.hb.getState();
                    return true;
                }
                if (event.target == this.g3) {
                    this.cc(event);
                    return true;
                }
                if (event.target == this.g2) {
                    this.cb(event);
                    return true;
                }
                if (event.target == this.he) {
                    this.hw = !this.hw;
                    if (this.hw) {
                        this.he.setFont(this.f_);
                    }
                    else {
                        this.he.setFont(this.fz);
                    }
                    this.l.requestFocus();
                }
                else if (event.target == this.hg) {
                    if (!this.hg.isEnabled()) {
                        return true;
                    }
                    this.hg.disable();
                    this.ce();
                    this.b3();
                    this.fx = 0;
                    this.b8(event);
                    return true;
                }
                else {
                    if (event.target == this.g6) {
                        this.cd();
                        return true;
                    }
                    if (event.target == this.bh) {
                        this.ce();
                        this.b3();
                        return true;
                    }
                    if (event.target == this.hi) {
                        if (this.hi.getLabel().equals(this.hs.c7)) {
                            this.fx = 0;
                            this.b8(event);
                        }
                        else {
                            this.b7();
                        }
                        return true;
                    }
                    if (event.target == this.hf) {
                        this.cf();
                        return true;
                    }
                    if (event.target == this.hk) {
                        this.ca(event);
                        return true;
                    }
                    if (event.target == this.l) {
                        this.av(this.l.getText(), this.hs.dn, true);
                        return true;
                    }
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final void cf() {
        new j(this.h_, this.hs.ae, this.hs.ad, this.hs.ac, "2.6.0").show();
    }
    
    public final void ce() {
        this.hs.ff = this.g_.getText();
        this.hs.e9 = this.gy.getText();
        if (this.hs.d1) {
            this.hs.fe = this.g2.getSelectedItem();
        }
        else {
            this.hs.fe = this.gz.getText();
        }
        if (!this.hs.fe.startsWith("#") && !this.hs.fe.startsWith("&") && !this.hs.fe.startsWith("!")) {
            this.hs.fe = "#" + this.hs.fe;
        }
        if (this.hs.d0) {
            this.hs.fc = this.g3.getSelectedItem();
        }
        else {
            this.hs.fc = this.g1.getText();
        }
        try {
            this.hs.fg = new Integer(this.g0.getText());
        }
        catch (Exception ex) {
            this.hs.fg = 6667;
            this.g0.setText("6667");
        }
        this.hs.e8 = this.gw.getText();
        this.hs.e7 = this.gx.getText();
        this.hs.e6 = this.gv.getText();
        this.hs.e4 = this.gu.getText();
        this.g_.setText(this.hs.ff);
        this.gz.setText(this.hs.fe);
        this.gy.setText(this.hs.e9);
        this.g1.setText(this.hs.fc);
        this.g0.setText(Integer.toString(this.hs.fg));
        if (this.hx == null || !this.al || !this.hx.isAlive()) {
            return;
        }
        if (!this.hs.ff.equals(this.hs.fb)) {
            this.hx.aj("/nick " + this.hs.ff);
            return;
        }
        if (!this.hs.fe.equals(this.hs.fa)) {
            this.hx.aj("/join " + this.hs.fe);
        }
    }
    
    public final void cd() {
        this.g_.setText(this.hs.ff);
        this.gy.setText(this.hs.e9);
        if (this.hs.d1) {
            this.hs.fe = this.g2.getSelectedItem();
        }
        else {
            this.hs.fe = this.gz.getText();
        }
        if (this.hs.d0) {
            this.hs.fc = this.g3.getSelectedItem();
        }
        else {
            this.hs.fc = this.g1.getText();
        }
        this.gz.setText(this.hs.fe);
        this.g1.setText(this.hs.fc);
        this.g0.setText(Integer.toString(this.hs.fg));
        this.b3();
    }
    
    public final void cc(final Event event) {
        this.g1.setText((String)event.arg);
    }
    
    public final void cb(final Event event) {
        this.gz.setText((String)event.arg);
    }
    
    public final void ca(final Event event) {
        if (this.hx.isAlive()) {
            this.hx.u(this.bx(this.hk.getSelectedIndex()));
        }
    }
    
    public final void b9(final int f0, final Color foreground) {
        this.f0 = f0;
        this.l.setForeground(foreground);
        this.g9.repaint();
        this.l.requestFocus();
    }
    
    public final void processJInput(final String s) {
        this.av(s, false, false);
    }
    
    public final void av(final String fy, final boolean b, final boolean b2) {
        this.fy = fy;
        if (b2) {
            this.l.setText("");
        }
        if (this.fy == null || this.fy.equals("")) {
            if (b) {
                this.f0 = -1;
            }
            return;
        }
        if (this.al) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.fy);
            String nextToken;
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            else {
                nextToken = "";
            }
            if (nextToken.startsWith("/")) {
                final String s = this.hs.fu.get(nextToken);
                if (s != null) {
                    this.fy = String.valueOf(s) + this.fy.substring(nextToken.length());
                }
            }
            int index;
            while ((index = this.fy.indexOf(" %$N ")) >= 0) {
                this.fy = String.valueOf(this.fy.substring(0, index)) + " " + this.hs.fb + " " + this.fy.substring(index + 5);
            }
            int index2;
            while ((index2 = this.fy.indexOf(" %$C ")) >= 0) {
                this.fy = String.valueOf(this.fy.substring(0, index2)) + " " + this.hs.fa + " " + this.fy.substring(index2 + 5);
            }
            int index3;
            while ((index3 = this.fy.indexOf(" %$C ")) >= 0) {
                this.fy = String.valueOf(this.fy.substring(0, index3)) + " " + this.hs.fa + " " + this.fy.substring(index3 + 5);
            }
            int index4;
            while ((index4 = this.fy.indexOf(" %$HN ")) >= 0) {
                String bx = this.bx(this.hk.getSelectedIndex());
                if (bx == null) {
                    bx = "NULL";
                }
                this.fy = String.valueOf(this.fy.substring(0, index4)) + " " + bx + " " + this.fy.substring(index4 + 6);
            }
            if (!this.fy.startsWith("/")) {
                String s2;
                if (this.hs.fv.get(nextToken) != null) {
                    s2 = " ";
                }
                else {
                    s2 = "";
                }
                if (this.f0 >= 0 && this.f0 <= 15) {
                    this.fy = String.valueOf(this.a2) + this.f0 + s2 + this.fy;
                }
                if (this.hw) {
                    this.fy = "\u0002" + s2 + this.fy;
                }
                this.hx.aj(this.fy);
            }
            else if (this.hs.dp) {
                this.hx.aj(this.fy);
            }
            else if (this.fy.startsWith("/quit") || this.fy.startsWith("/s ") || this.fy.startsWith("/msg ")) {
                this.hx.aj(this.fy);
            }
        }
        if (b) {
            this.f0 = -1;
            this.hm.bh();
            this.hw = false;
            this.he.setFont(this.fz);
            this.l.setForeground(this.hs.ev);
        }
        this.g9.repaint();
    }
    
    public final synchronized void b8(final Event event) {
        this.b6();
        if (this.hs.c4.equals("")) {
            this.au("Connecting to " + this.hs.fc + " " + this.hs.fg + " ......");
        }
        else {
            this.au(this.hs.c4);
        }
        this.hi.setLabel(this.hs.c6);
        this.hg.disable();
        if (this.ht != null && this.ht.isAlive()) {
            this.ht.stop();
        }
        (this.ht = new Thread(this)).start();
    }
    
    public final void b7() {
        if (this.hx != null && this.hx.isAlive()) {
            if (this.hx.al) {
                this.hx.ak();
            }
            this.hx.y();
            this.hx = null;
        }
        this.ab();
    }
    
    public final void b6() {
        if (!this.hs.d_) {
            return;
        }
        if (this.hy != null && this.hy.isAlive()) {
            return;
        }
        if (!this.hs.bl.equals("")) {
            this.hy = new k(113, this.bp(), this.hs.bl);
        }
        else {
            this.hy = new k(113, this.bp(), this.hs.ff);
        }
        this.hy.a0(this.hs.fg);
        this.hy.start();
        if (!this.hy.bm) {
            System.out.println("Failed to start Ident daemon or it has already running.");
            this.hy.a1();
            this.hy = null;
        }
    }
    
    public final void b5() {
        if (this.hy != null) {
            this.hy.isAlive();
        }
    }
    
    public final void b4() {
        this.f1.show(this.hq, "Config");
    }
    
    public final void b3() {
        this.f1.show(this.hq, "Chat");
        this.l.setBackground(this.hs.eu);
        this.l.requestFocus();
        this.g9.repaint();
    }
    
    public final void au(final String s) {
        this.hj.i(s, this.hs.e1);
    }
    
    public final void y() {
        this.y(false);
    }
    
    public final void y(final boolean b) {
        this.al = false;
        this.hs.fb = "";
        this.hs.fa = "";
        this.au(this.hs.c2);
        this.hi.setLabel(this.hs.c7);
        this.hg.enable();
        if (b && this.hs.dw) {
            if (this.fx == this.g3.countItems()) {
                return;
            }
            if (this.g3.getSelectedIndex() == this.g3.countItems() - 1) {
                try {
                    this.g3.select(0);
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    this.g3.select(this.g3.getSelectedIndex() + 1);
                }
                catch (Exception ex2) {}
            }
            this.hs.fc = this.g3.getSelectedItem();
            this.g1.setText(this.hs.fc);
            ++this.fx;
            this.b8(null);
        }
    }
    
    public final void run() {
        try {
            final f hx = new f();
            hx.as(this);
            hx.start();
            this.hx = hx;
        }
        catch (Exception ex) {
            this.au("Create connection failed." + ex.toString());
            ex.printStackTrace();
        }
    }
    
    public final void start() {
        if (this.bp()) {
            a.a();
        }
        if (this.hs.d9) {
            this.hg.disable();
            this.ce();
            this.b8(null);
        }
    }
    
    public final void stop() {
        this.b7();
    }
    
    public final void destroy() {
        if (this.isActive()) {
            this.stop();
        }
        super.destroy();
    }
    
    public final void ab() {
        this.hs.fb = "";
        this.hs.fa = "";
        this.al = false;
        this.b5();
        if (this.ht != null && this.ht.isAlive()) {
            this.ht.stop();
        }
        this.hg.enable();
        this.hi.setLabel(this.hs.c7);
    }
    
    public final boolean b2(final String s) {
        final String trim = s.trim();
        if (!this.hr || !this.hs.d4) {
            return false;
        }
        try {
            this.getAppletContext().showDocument(new URL(trim), "JAVACHAT_WIN");
        }
        catch (Exception ex) {
            this.au("*** Displaying URL error: " + trim);
            return false;
        }
        return true;
    }
    
    public final void b1() {
        this.hj.h();
    }
    
    public final void b0(final boolean al) {
        this.al = al;
    }
    
    private final void b_(final Panel panel, final Component component, final GridBagLayout gridBagLayout, final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagLayout.setConstraints(component, gridBagConstraints);
        panel.add(component);
    }
    
    public final void bz() {
        this.hk.clear();
        this.hl.d("0");
        this.hl.e("Channel");
    }
    
    public final void by() {
        this.hk.clear();
        this.hl.d("0");
        this.hl.e(this.hs.fa);
        this.hl.c("");
    }
    
    public final String bx(final int n) {
        final String item = this.hk.getItem(n);
        final int lastIndex = item.lastIndexOf(" ");
        String substring;
        try {
            substring = item.substring(lastIndex + 1);
        }
        catch (Exception ex) {
            substring = "";
        }
        return substring;
    }
    
    public final void bw(String s) {
        String s2 = "";
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        s = s.trim();
        final int index = s.indexOf("@");
        if (index >= 0 && index <= 2) {
            b3 = true;
            s = String.valueOf(s.substring(0, index)) + s.substring(index + 1);
        }
        final int index2 = s.indexOf("%");
        if (index2 >= 0 && index2 <= 2) {
            b = true;
            s = String.valueOf(s.substring(0, index2)) + s.substring(index2 + 1);
        }
        final int index3 = s.indexOf("+");
        if (index3 >= 0 && index3 <= 2) {
            b2 = true;
            s = String.valueOf(s.substring(0, index3)) + s.substring(index3 + 1);
        }
        if (b3) {
            s2 = String.valueOf(s2) + "@";
        }
        if (b) {
            s2 = String.valueOf(s2) + "%";
        }
        if (b2) {
            s2 = String.valueOf(s2) + "+";
        }
        String s3;
        if (s2.equals("")) {
            s3 = " " + s;
        }
        else {
            s3 = " " + s2 + " " + s;
        }
        final int n = this.hk.countItems() - 1;
        for (int i = 0; i <= n; ++i) {
            if (s3.toLowerCase().compareTo(this.hk.getItem(i).toLowerCase()) < 0) {
                this.hk.addItem(s3, i);
                break;
            }
        }
        if (this.hk.countItems() - 1 == n) {
            this.hk.addItem(s3);
        }
        this.hl.d(Integer.toString(this.hk.countItems()));
    }
    
    public final boolean bv(String trim) {
        trim = trim.trim();
        for (int n = this.hk.countItems() - 1, i = 0; i <= n; ++i) {
            if (this.bx(i).equalsIgnoreCase(trim)) {
                this.hk.delItem(i);
                this.hl.d(Integer.toString(this.hk.countItems()));
                return true;
            }
        }
        return false;
    }
    
    public final boolean bu(String trim) {
        trim = trim.trim();
        for (int countItems = this.hk.countItems(), i = 0; i <= countItems - 1; ++i) {
            if (this.bx(i).equalsIgnoreCase(trim)) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean bt(String trim, String trim2) {
        trim = trim.trim();
        trim2 = trim2.trim();
        for (int countItems = this.hk.countItems(), i = 0; i <= countItems - 1; ++i) {
            final String bx = this.bx(i);
            if (trim.equals(bx)) {
                final String item = this.hk.getItem(i);
                final int lastIndex = item.lastIndexOf(" ");
                String trim3;
                if (lastIndex > 0) {
                    trim3 = item.substring(0, lastIndex).trim();
                }
                else {
                    trim3 = "";
                }
                this.bv(bx);
                this.bw(String.valueOf(trim3) + " " + trim2);
                return true;
            }
        }
        return false;
    }
    
    public final boolean bs(String trim, final String s, final boolean b) {
        trim = trim.trim();
        final int countItems = this.hk.countItems();
        String s2 = "";
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        for (int i = 0; i <= countItems - 1; ++i) {
            final String bx = this.bx(i);
            if (trim.equals(bx)) {
                final String item = this.hk.getItem(i);
                final int lastIndex = item.lastIndexOf(" ");
                String substring;
                if (lastIndex > 0) {
                    substring = item.substring(0, lastIndex);
                }
                else {
                    substring = "";
                }
                if (substring.indexOf("@") > 0) {
                    b2 = true;
                }
                if (substring.indexOf("%") > 0) {
                    b3 = true;
                }
                if (substring.indexOf("+") > 0) {
                    b4 = true;
                }
                if (s.equals("@")) {
                    b2 = b;
                }
                if (s.equals("%")) {
                    b3 = b;
                }
                if (s.equals("+")) {
                    b4 = b;
                }
                if (b2) {
                    s2 = String.valueOf(s2) + "@";
                }
                if (b3) {
                    s2 = String.valueOf(s2) + "%";
                }
                if (b4) {
                    s2 = String.valueOf(s2) + "+";
                }
                this.bv(bx);
                this.bw(String.valueOf(s2) + " " + bx);
                return true;
            }
        }
        return false;
    }
    
    public final void br() {
        this.hn = new Font(this.hs.el, 0, this.hs.em);
        this.f_ = new Font(this.hs.el, 1, 8);
        this.fz = new Font(this.hs.el, 0, 8);
    }
    
    public final boolean bq() {
        final String property = System.getProperty("java.vendor");
        try {
            return property.indexOf("Netscape") != -1;
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public final boolean bp() {
        return this.bq() && this.hr && !this.hs.d2;
    }
    
    public final void bo(String s) {
        s = s.trim();
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        try {
            s = stringTokenizer.nextToken();
        }
        catch (Exception ex) {
            s = "";
        }
        if (!this.hs.d3 || s == null || s.equals("")) {
            return;
        }
        new c(this, s, false).start();
    }
    
    public final void bn(String lowerCase) {
        if (lowerCase == null) {
            return;
        }
        lowerCase = lowerCase.toLowerCase();
        String s;
        if (this.hv.get(lowerCase) == null) {
            this.hv.put(lowerCase, "1");
            s = String.valueOf(this.hs.cw) + "" + lowerCase;
        }
        else {
            this.hv.remove(lowerCase);
            s = String.valueOf(this.hs.cv) + "" + lowerCase;
        }
        for (int size = this.hx.am.size(), i = 0; i < size; ++i) {
            final g g = this.hx.am.elementAt(i);
            if (g.getName().equals(lowerCase)) {
                g.at("***" + s, this.hs.er);
                return;
            }
        }
        this.hx.af("*** " + s);
    }
    
    public final boolean bm(final String s) {
        return this.hv.get(s.toLowerCase()) != null;
    }
}
