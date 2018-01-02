import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Image;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Toolkit;
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
    public Hashtable hk;
    public boolean hj;
    public e hi;
    public k hh;
    public f hg;
    public String a3;
    public boolean hf;
    public boolean he;
    public Thread hd;
    public p hc;
    public boolean hb;
    public Panel ha;
    public Panel g9;
    public Panel g8;
    public Font g7;
    public o g6;
    public a g5;
    public List g4;
    public b g3;
    public TextField l;
    public Button g2;
    public Button g1;
    public Button g0;
    public Button g_;
    public Button gz;
    public Checkbox gy;
    public Checkbox gx;
    public Checkbox gw;
    public Panel gv;
    public Panel gu;
    public Panel gt;
    public Panel gs;
    public Button gr;
    public Panel gq;
    public Panel gp;
    public Choice go;
    public Choice gn;
    public TextField gm;
    public TextField gl;
    public TextField gk;
    public TextField gj;
    public TextField gi;
    public TextField gh;
    public TextField gg;
    public TextField gf;
    public Panel ge;
    public GridBagLayout gd;
    public GridBagConstraints gc;
    public Label gb;
    public Label ga;
    public Label f9;
    public Label f8;
    public Label f7;
    public Label f6;
    public Label f5;
    public Label f4;
    public Canvas f3;
    public Canvas f2;
    public Canvas f1;
    public Canvas f0;
    public Canvas f_;
    public Canvas fz;
    public Canvas fy;
    public Canvas fx;
    public Panel fw;
    public h fv;
    public Panel fu;
    public Panel ft;
    public Panel fs;
    public Label fr;
    public Label fq;
    public Label fp;
    public CardLayout fo;
    public int fn;
    public Font fm;
    public Font fl;
    public String fk;
    public int fj;
    public boolean al;
    
    public Chat() {
        this.hk = new Hashtable();
        this.hj = false;
        this.a3 = "\u0003";
        this.hf = false;
        this.he = true;
        this.hc = new p();
        this.hb = true;
        this.gm = new TextField(30);
        this.gl = new TextField(10);
        this.gk = new TextField(10);
        this.gj = new TextField(10);
        this.gi = new TextField(30);
        this.gh = new TextField(10);
        this.gg = new TextField(10);
        this.gf = new TextField(10);
        this.fn = -11;
        this.al = false;
        if (!this.hb) {
            this.init();
            this.start();
        }
    }
    
    public final void init() {
        this.cc();
        this.cd();
        this.gb = new Label(this.hc.c8);
        this.ga = new Label(this.hc.c6);
        this.f9 = new Label(this.hc.dd);
        this.f8 = new Label(this.hc.db);
        this.f7 = new Label(this.hc.c9);
        this.f6 = new Label(this.hc.c7);
        this.f5 = new Label(this.hc.da);
        this.f4 = new Label(this.hc.dc);
        new Vector();
        this.fq = new Label();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.hc.z, ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.hi = new e(stringTokenizer.nextToken());
            if (this.hi.q()) {
                final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
                System.out.println("docbase=" + lowerCase + ", " + "Licensehost=" + this.hi.aa);
                if (!lowerCase.endsWith(this.hi.aa)) {
                    continue;
                }
                this.hc.ae = this.hi.ae;
                this.hc.ad = this.hi.ad;
                this.hc.ac = this.hi.ac;
                this.hj = true;
            }
        }
        this.g_ = new Button(this.hc.c1);
        this.bm();
        this.setFont(this.g7);
        this.cb();
        this.ca();
        this.fo = new CardLayout();
        (this.ha = new Panel()).setLayout(this.fo);
        this.ha.add("Config", this.g9);
        this.ha.add("Chat", this.g8);
        if (this.hc.d0) {
            this.fo.show(this.ha, "Chat");
        }
        else {
            this.fo.show(this.ha, "Config");
        }
        this.setLayout(new BorderLayout(this.hc.fb, this.hc.fb));
        this.add("North", new Canvas());
        this.add("South", new Canvas());
        this.add("West", new Canvas());
        this.add("East", new Canvas());
        this.add("Center", this.ha);
    }
    
    public final void cd() {
        this.hk.clear();
        final String string = this.getCodeBase().toString();
        final Enumeration<String> keys = this.hc.fh.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = this.hc.fh.get(s);
            try {
                final Image image = Toolkit.getDefaultToolkit().getImage(new URL(String.valueOf(string) + s2));
                Toolkit.getDefaultToolkit().prepareImage(image, -1, -1, this);
                this.hk.put(s, image);
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public final void cc() {
        this.hc.bi(this);
    }
    
    public final void cb() {
        int n = 0;
        this.gd = new GridBagLayout();
        this.gc = new GridBagConstraints();
        this.gc.insets = new Insets(this.hc.fc, this.hc.fe, 0, this.hc.fe);
        this.go = new Choice();
        for (int size = this.hc.e5.size(), i = 0; i < size; ++i) {
            this.go.addItem((String)this.hc.e5.elementAt(i));
        }
        if (this.go.countItems() <= 0) {
            this.go.addItem(this.hc.e1);
        }
        this.gn = new Choice();
        for (int size2 = this.hc.e7.size(), j = 0; j < size2; ++j) {
            this.gn.addItem((String)this.hc.e7.elementAt(j));
        }
        if (this.gn.countItems() <= 0) {
            this.gn.addItem(this.hc.e2);
        }
        (this.gy = new Checkbox(this.hc.c5)).setState(this.hc.dv);
        (this.gw = new Checkbox(this.hc.c4)).setState(this.hc.dq);
        (this.gs = new Panel()).setLayout(new GridLayout(1, 2));
        this.gs.add(this.gy);
        this.gs.add(this.gw);
        (this.gp = new Panel()).setLayout(this.gd);
        this.gc.weightx = 0.0;
        this.gc.weighty = 0.0;
        this.gc.fill = 0;
        this.gk.setText(this.hc.e3);
        this.gf.setEchoCharacter('*');
        this.gf.setText(this.hc.ew);
        this.gj.setText(this.gn.getItem(0));
        this.gi.setText(this.hc.ez);
        this.gm.setText(this.go.getItem(0));
        this.gl.setText(Integer.toString(this.hc.e4));
        this.gg.setEchoCharacter('*');
        this.gh.setEchoCharacter('*');
        this.gg.setText(this.hc.ey);
        this.gh.setText(this.hc.ex);
        if (this.hc.ea) {
            this.gc.anchor = 13;
            this.bu(this.gp, this.f9, this.gd, this.gc, 0, n, 1, 1);
            this.gc.anchor = 17;
            this.bu(this.gp, this.gk, this.gd, this.gc, 1, n, 1, 1);
            ++n;
        }
        if (this.hc.d9) {
            this.gc.anchor = 13;
            this.bu(this.gp, this.f4, this.gd, this.gc, 0, n, 1, 1);
            this.gc.anchor = 17;
            this.bu(this.gp, this.gf, this.gd, this.gc, 1, n, 2, 1);
            ++n;
        }
        if (this.hc.d8) {
            this.gc.anchor = 13;
            this.bu(this.gp, this.f8, this.gd, this.gc, 0, n, 1, 1);
            if (!this.hc.ds) {
                this.gc.anchor = 17;
                this.bu(this.gp, this.gj, this.gd, this.gc, 1, n, 1, 1);
                ++n;
            }
            this.gc.anchor = 17;
            this.bu(this.gp, this.gn, this.gd, this.gc, 1, n, 2, 1);
            ++n;
        }
        if (this.hc.d7) {
            this.gc.anchor = 13;
            this.bu(this.gp, this.f5, this.gd, this.gc, 0, n, 1, 1);
            this.gc.anchor = 17;
            this.bu(this.gp, this.gh, this.gd, this.gc, 1, n, 2, 1);
            ++n;
        }
        if (this.hc.d6) {
            this.gc.anchor = 13;
            this.bu(this.gp, this.f7, this.gd, this.gc, 0, n, 1, 1);
            this.gc.anchor = 17;
            this.bu(this.gp, this.gi, this.gd, this.gc, 1, n, 2, 1);
            ++n;
        }
        if (this.hc.d5) {
            this.gc.anchor = 13;
            this.bu(this.gp, this.gb, this.gd, this.gc, 0, n, 1, 1);
            if (!this.hc.dr) {
                this.gc.anchor = 17;
                this.bu(this.gp, this.gm, this.gd, this.gc, 1, n, 2, 1);
                ++n;
            }
            this.gc.anchor = 17;
            this.bu(this.gp, this.go, this.gd, this.gc, 1, n, 2, 1);
            ++n;
        }
        if (this.hc.d4) {
            this.gc.anchor = 13;
            this.bu(this.gp, this.f6, this.gd, this.gc, 0, n, 1, 1);
            this.gc.anchor = 17;
            this.bu(this.gp, this.gg, this.gd, this.gc, 1, n, 2, 1);
            ++n;
        }
        if (this.hc.d3) {
            this.gc.anchor = 13;
            this.bu(this.gp, this.ga, this.gd, this.gc, 0, n, 1, 1);
            this.gc.anchor = 17;
            this.gc.insets = new Insets(this.hc.fc, this.hc.fe, this.hc.fc, this.hc.fe);
            this.bu(this.gp, this.gl, this.gd, this.gc, 1, n, 2, 1);
            ++n;
        }
        if (this.hc.d2) {
            this.gc.anchor = 10;
            this.bu(this.gp, this.gs, this.gd, this.gc, 0, n, 4, 1);
        }
        (this.f3 = new Canvas()).resize(10, 1);
        this.f3.setBackground(Color.white);
        (this.f2 = new Canvas()).resize(10, 1);
        this.f2.setBackground(Color.white);
        (this.f_ = new Canvas()).resize(1, 10);
        this.f_.setBackground(Color.white);
        (this.fz = new Canvas()).resize(1, 10);
        this.fz.setBackground(Color.white);
        (this.fw = new Panel()).setLayout(new BorderLayout());
        this.fw.add("North", this.f3);
        this.fw.add("South", this.f2);
        this.fw.add("West", this.f_);
        this.fw.add("East", this.fz);
        this.fw.add("Center", this.gp);
        (this.ge = new Panel()).setLayout(new FlowLayout(1, this.hc.fe, this.hc.fc));
        this.g0 = new Button(this.hc.c3);
        this.gr = new Button(this.hc.c2);
        this.ge.add(this.g0);
        this.ge.add(this.gr);
        if (!this.hj || (!this.hc.di && this.hc.dg)) {
            this.ge.add(this.g_);
        }
        (this.gq = new Panel()).setLayout(new BorderLayout(this.hc.fe, 0));
        this.gq.setBackground(Color.white);
        if (this.hb) {
            this.fv = new h(this.getCodeBase());
        }
        else {
            this.fv = new h();
        }
        this.fv.resize(this.hc.eu, this.hc.et);
        (this.fs = new Panel()).add(this.fv);
        final Font font = new Font("TimesRoman", 0, 10);
        (this.fr = new Label("JPilot jIRC applet V2.5.1")).setForeground(Color.red);
        this.fr.setFont(font);
        this.fu = new Panel();
        if (this.hj) {
            this.fq.setForeground(Color.red);
            this.fq.setFont(font);
            (this.fp = new Label("http://www.jpilot.com")).setForeground(Color.red);
            this.fp.setFont(font);
        }
        else {
            (this.fq = new Label("Unregistered Copy")).setForeground(Color.red);
            this.fq.setFont(font);
            (this.fp = new Label("http://www.jpilot.com")).setForeground(Color.red);
            this.fp.setFont(font);
            this.fu.setLayout(new GridLayout(3, 1, 0, 0));
            this.fu.add(this.fr);
            this.fu.add(this.fp);
            this.fu.add(this.fq);
        }
        (this.ft = new Panel()).setLayout(new BorderLayout(this.hc.fe, this.hc.fc));
        this.ft.add("North", this.fs);
        this.ft.add("South", this.fu);
        (this.fy = new Canvas()).setBackground(Color.black);
        this.fy.resize(2, 10);
        (this.fx = new Canvas()).setBackground(Color.black);
        this.fx.resize(2, 10);
        (this.f1 = new Canvas()).setBackground(Color.black);
        this.f1.resize(10, 2);
        (this.f0 = new Canvas()).setBackground(Color.black);
        this.f0.resize(10, 2);
        this.gq.add("Center", this.ft);
        this.gq.add("East", this.fy);
        this.gq.add("West", this.fx);
        this.gq.add("North", this.f1);
        this.gq.add("South", this.f0);
        this.gq.setBackground(this.hc.en);
        (this.g9 = new Panel()).setFont(this.g7);
        this.g9.setForeground(this.hc.em);
        this.g9.setBackground(this.hc.eq);
        this.g9.setLayout(new BorderLayout(this.hc.fe, this.hc.fc));
        this.g9.add("West", this.gq);
        this.g9.add("Center", this.fw);
        this.g9.add("South", this.ge);
    }
    
    public final void ca() {
        this.gu = new Panel();
        (this.l = new TextField("", this.hc.ff)).setBackground(this.hc.ek);
        this.l.setForeground(this.hc.el);
        this.l.setFont(this.g7);
        this.g2 = new Button(this.hc.c_);
        this.g1 = new Button(this.hc.c0);
        (this.gz = new Button("B")).setFont(this.fl);
        (this.g5 = new a("Channel", "0", "", this.hc.ef, this.hc.ee, this.hc.eb, this.hc.ec, this.hc.b, this.hc.a)).f(this.hc.bv);
        this.g5.j(this);
        this.g6 = new o(this, this.hc.el);
        (this.gt = new Panel()).setLayout(new FlowLayout(1, 4, 0));
        if (this.hc.d_) {
            this.gt.add(this.gz);
            this.gt.add(this.g6);
        }
        if (!this.hc.di) {
            this.gt.add(this.g1);
        }
        else if (!this.hj || this.hc.dg) {
            this.gt.add(this.g_);
        }
        this.gt.add(this.g2);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", this.l);
        this.gu.setLayout(new BorderLayout());
        this.gu.add("Center", panel);
        this.gu.add("East", this.gt);
        (this.gv = new Panel()).setFont(this.g7);
        this.gv.setLayout(new BorderLayout(this.hc.fe / 2, this.hc.fc / 2));
        final Canvas canvas = new Canvas();
        canvas.resize(1, 10);
        this.gv.add("East", canvas);
        (this.g4 = new List(1, false)).setForeground(this.hc.ep);
        this.g4.setBackground(this.hc.eo);
        if (this.hc.dw) {
            if (this.hc.fa > 0) {
                final Canvas canvas2 = new Canvas();
                canvas2.resize(this.hc.fa, 1);
                this.gv.add("North", canvas2);
            }
            this.gv.add("Center", this.g4);
        }
        (this.gx = new Checkbox(this.hc.cy)).setState(this.hc.du);
        if (this.hc.dz) {
            this.gv.add("South", this.gx);
        }
        (this.g8 = new Panel()).setForeground(this.hc.em);
        this.g8.setBackground(this.hc.eq);
        this.g8.setFont(this.g7);
        this.g8.setLayout(new BorderLayout(this.hc.fe, this.hc.fc));
        (this.g3 = new b(200, 400)).j(this);
        this.g3.setBackground(this.hc.er);
        this.g3.setForeground(this.hc.es);
        this.g3.setFont(this.g7);
        this.g3.f(this.hc.bv);
        this.ar(this.hc.de);
        this.g8.add("South", this.gu);
        this.g8.add("East", this.gv);
        this.g8.add("Center", this.g3);
        this.g8.add("North", this.g5);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.g1) {
                    this.bz();
                    return true;
                }
                if (event.target == this.gy) {
                    this.hc.dv = this.gy.getState();
                    return true;
                }
                if (event.target == this.gx) {
                    this.hc.du = this.gx.getState();
                    return true;
                }
                if (event.target == this.gw) {
                    this.hc.dq = this.gw.getState();
                    return true;
                }
                if (event.target == this.go) {
                    this.b6(event);
                    return true;
                }
                if (event.target == this.gn) {
                    this.b5(event);
                    return true;
                }
                if (event.target == this.gz) {
                    this.hf = !this.hf;
                    if (this.hf) {
                        this.gz.setFont(this.fm);
                    }
                    else {
                        this.gz.setFont(this.fl);
                    }
                    this.l.requestFocus();
                }
                else if (event.target == this.g0) {
                    if (!this.g0.isEnabled()) {
                        return true;
                    }
                    this.g0.disable();
                    this.b8();
                    this.by();
                    this.fj = 0;
                    this.b2(event);
                    return true;
                }
                else {
                    if (event.target == this.gr) {
                        this.b7();
                        return true;
                    }
                    if (event.target == this.g2) {
                        if (this.g2.getLabel().equals(this.hc.c_)) {
                            this.fj = 0;
                            this.b2(event);
                        }
                        else {
                            this.b1();
                        }
                        return true;
                    }
                    if (event.target == this.g_) {
                        this.b9();
                        return true;
                    }
                    if (event.target == this.g4) {
                        this.b4(event);
                        return true;
                    }
                    if (event.target == this.l) {
                        this.as(this.l.getText(), this.hc.df, true);
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
    
    public final void b9() {
        new j(this.hj, this.hc.ae, this.hc.ad, this.hc.ac, "2.5.1").show();
    }
    
    public final void b8() {
        this.hc.e3 = this.gk.getText();
        this.hc.ez = this.gi.getText();
        if (this.hc.ds) {
            this.hc.e2 = this.gn.getSelectedItem();
        }
        else {
            this.hc.e2 = this.gj.getText();
        }
        if (this.hc.dr) {
            this.hc.e1 = this.go.getSelectedItem();
        }
        else {
            this.hc.e1 = this.gm.getText();
        }
        try {
            this.hc.e4 = new Integer(this.gl.getText());
        }
        catch (Exception ex) {
            this.hc.e4 = 6667;
            this.gl.setText("6667");
        }
        this.hc.e3 = this.hc.e3.replace(' ', '_');
        if (this.hc.e2.startsWith("#") || this.hc.e2.startsWith("&")) {
            this.hc.e2 = this.hc.e2.substring(1);
        }
        this.hc.e1 = this.hc.e1.replace(' ', '_');
        this.hc.ey = this.gg.getText();
        this.hc.ex = this.gh.getText();
        this.hc.ew = this.gf.getText();
        this.gk.setText(this.hc.e3);
        this.gj.setText(this.hc.e2);
        this.gi.setText(this.hc.ez);
        this.gm.setText(this.hc.e1);
        this.gl.setText(Integer.toString(this.hc.e4));
    }
    
    public final void b7() {
        this.gk.setText(this.hc.e3);
        this.gi.setText(this.hc.ez);
        if (this.hc.ds) {
            this.hc.e2 = this.gn.getSelectedItem();
        }
        else {
            this.hc.e2 = this.gj.getText();
        }
        if (this.hc.dr) {
            this.hc.e1 = this.go.getSelectedItem();
        }
        else {
            this.hc.e1 = this.gm.getText();
        }
        this.gj.setText(this.hc.e2);
        this.gm.setText(this.hc.e1);
        this.gl.setText(Integer.toString(this.hc.e4));
        this.by();
    }
    
    public final void b6(final Event event) {
        this.gm.setText((String)event.arg);
    }
    
    public final void b5(final Event event) {
        this.gj.setText((String)event.arg);
    }
    
    public final void b4(final Event event) {
        final String s = (String)event.arg;
        if (this.hg.isAlive()) {
            if (s.startsWith("@")) {
                this.hg.s(s.substring(1));
                return;
            }
            if (s.startsWith("+")) {
                this.hg.s(s.substring(1));
                return;
            }
            this.hg.s(s);
        }
    }
    
    public final void b3(final int fn, final Color foreground) {
        this.fn = fn;
        this.l.setForeground(foreground);
        this.gu.repaint();
        this.l.requestFocus();
    }
    
    public final void processJInput(final String s) {
        this.as(s, false, false);
    }
    
    public final void as(final String fk, final boolean b, final boolean b2) {
        this.fk = fk;
        if (b2) {
            this.l.setText("");
        }
        if (this.fk == null || this.fk.equals("")) {
            if (b) {
                this.fn = -1;
            }
            return;
        }
        if (this.al) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.fk);
            String nextToken;
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            else {
                nextToken = "";
            }
            int index;
            while ((index = this.fk.indexOf(" %$N ")) >= 0) {
                this.fk = String.valueOf(this.fk.substring(0, index)) + " " + this.hc.e0 + " " + this.fk.substring(index + 5);
            }
            int index2;
            while ((index2 = this.fk.indexOf(" %$C ")) >= 0) {
                this.fk = String.valueOf(this.fk.substring(0, index2)) + " " + this.hc.e_ + " " + this.fk.substring(index2 + 5);
            }
            int index3;
            while ((index3 = this.fk.indexOf(" #%$C ")) >= 0) {
                this.fk = String.valueOf(this.fk.substring(0, index3)) + " #" + this.hc.e_ + " " + this.fk.substring(index3 + 6);
            }
            int index4;
            while ((index4 = this.fk.indexOf(" %$HN ")) >= 0) {
                String s = this.g4.getSelectedItem();
                if (s == null) {
                    s = "NULL";
                }
                else if (s.startsWith("+@") || s.startsWith("@+")) {
                    s = s.substring(2);
                }
                else if (s.startsWith("@") || s.startsWith("+")) {
                    s = s.substring(1);
                }
                this.fk = String.valueOf(this.fk.substring(0, index4)) + " " + s + " " + this.fk.substring(index4 + 6);
            }
            if (!this.fk.startsWith("/")) {
                String s2;
                if (this.hc.fh.get(nextToken) != null) {
                    s2 = " ";
                }
                else {
                    s2 = "";
                }
                if (this.fn >= 0 && this.fn <= 15) {
                    this.fk = String.valueOf(this.a3) + this.fn + s2 + this.fk;
                }
                if (this.hf) {
                    this.fk = "\u0002" + s2 + this.fk;
                }
                this.hg.ah(this.fk);
            }
            else {
                final String s3 = this.hc.fg.get(nextToken);
                if (s3 != null) {
                    this.fk = String.valueOf(s3) + this.fk.substring(nextToken.length());
                    this.hg.ah(this.fk);
                    return;
                }
                if (this.hc.dh) {
                    this.hg.ah(this.fk);
                }
                else if (this.fk.startsWith("/quit") || this.fk.startsWith("/s ") || this.fk.startsWith("/msg ")) {
                    this.hg.ah(this.fk);
                }
            }
        }
        if (b) {
            this.fn = -1;
            this.g6.be();
            this.hf = false;
            this.gz.setFont(this.fl);
            this.l.setForeground(this.hc.el);
        }
        this.gu.repaint();
    }
    
    public final synchronized void b2(final Event event) {
        this.b0();
        if (this.hc.cv.equals("")) {
            this.ar("Connecting to " + this.hc.e1 + " " + this.hc.e4 + " ......");
        }
        else {
            this.ar(this.hc.cv);
        }
        this.g2.setLabel(this.hc.cz);
        this.g0.disable();
        if (this.hd != null && this.hd.isAlive()) {
            this.hd.stop();
        }
        (this.hd = new Thread(this)).start();
    }
    
    public final void b1() {
        if (this.hg != null && this.hg.isAlive()) {
            if (this.hg.al) {
                this.hg.ai();
            }
            this.hg.w();
            this.hg = null;
        }
        this.z();
        this.g2.setLabel(this.hc.c_);
        this.g0.enable();
    }
    
    public final void b0() {
        if (!this.hc.dq) {
            return;
        }
        if (this.hh != null && this.hh.isAlive()) {
            return;
        }
        if (!this.hc.bm.equals("")) {
            this.hh = new k(113, this.bk(), this.hc.bm);
        }
        else {
            this.hh = new k(113, this.bk(), this.hc.e3);
        }
        this.hh.ay(this.hc.e4);
        this.hh.start();
        if (!this.hh.bn) {
            System.out.println("Failed to start Ident daemon or it has already running.");
            this.hh.az();
            this.hh = null;
        }
    }
    
    public final void b_() {
        if (this.hh != null && this.hh.isAlive() && this.hc.dn) {
            this.hh.az();
            this.hh = null;
        }
    }
    
    public final void bz() {
        this.fo.show(this.ha, "Config");
    }
    
    public final void by() {
        this.fo.show(this.ha, "Chat");
        this.l.setBackground(this.hc.ek);
        this.l.requestFocus();
        this.gu.repaint();
    }
    
    public final void ar(final String s) {
        this.g3.i(s, this.hc.es);
    }
    
    public final void w() {
        this.w(false);
    }
    
    public final void w(final boolean b) {
        this.al = false;
        this.hc.e0 = "";
        this.hc.e_ = "";
        this.ar(this.hc.ct);
        this.g2.setLabel(this.hc.c_);
        this.g0.enable();
        if (b && this.hc.do) {
            if (this.fj == this.go.countItems()) {
                return;
            }
            if (this.go.getSelectedIndex() == this.go.countItems() - 1) {
                try {
                    this.go.select(0);
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    this.go.select(this.go.getSelectedIndex() + 1);
                }
                catch (Exception ex2) {}
            }
            this.hc.e1 = this.go.getSelectedItem();
            this.gm.setText(this.hc.e1);
            ++this.fj;
            this.b2(null);
        }
    }
    
    public final void run() {
        try {
            final f hg = new f();
            hg.aq(this);
            hg.start();
            this.hg = hg;
        }
        catch (Exception ex) {
            this.ar("Create connection failed." + ex.toString());
        }
    }
    
    public final void start() {
        if (this.bk()) {
            a.a();
        }
        if (this.hc.d0) {
            this.g0.disable();
            this.b8();
            this.b2(null);
        }
    }
    
    public final void stop() {
        if (this.hg != null && this.hg.isAlive()) {
            if (this.hg.al) {
                this.hg.ai();
            }
            this.hg.w();
            this.hg = null;
        }
        this.b_();
        this.g0.enable();
        this.hc.e0 = "";
        this.hc.e_ = "";
        this.al = false;
        super.stop();
    }
    
    public final void destroy() {
        if (this.isActive()) {
            this.stop();
        }
        super.destroy();
    }
    
    public final void z() {
        this.hc.e0 = "";
        this.hc.e_ = "";
        this.al = false;
        if (this.hd != null && this.hd.isAlive()) {
            this.hd.stop();
        }
        this.g2.setLabel(this.hc.c_);
    }
    
    public final boolean bx(final String s) {
        final String trim = s.trim();
        if (!this.hb || !this.hc.dv) {
            return false;
        }
        try {
            this.getAppletContext().showDocument(new URL(trim), "JAVACHAT_WIN");
        }
        catch (Exception ex) {
            this.ar("*** Displaying URL error: " + trim);
            return false;
        }
        return true;
    }
    
    public final void bw() {
        this.g3.h();
    }
    
    public final void bv(final boolean al) {
        this.al = al;
    }
    
    private final void bu(final Panel panel, final Component component, final GridBagLayout gridBagLayout, final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagLayout.setConstraints(component, gridBagConstraints);
        panel.add(component);
    }
    
    public final void bt() {
        this.g4.clear();
        this.g5.d("0");
        this.g5.e("Channel");
    }
    
    public final void bs() {
        this.g4.clear();
        this.g5.d("0");
        this.g5.e(this.hc.e_);
        this.g5.c("");
    }
    
    public final void br(String trim) {
        trim = trim.trim();
        final int n = this.g4.countItems() - 1;
        for (int i = 0; i <= n; ++i) {
            if (trim.compareTo(this.g4.getItem(i)) < 0) {
                this.g4.addItem(trim, i);
                break;
            }
        }
        if (this.g4.countItems() - 1 == n) {
            this.g4.addItem(trim, n);
        }
        this.g5.d(Integer.toString(this.g4.countItems()));
    }
    
    public final boolean bq(String trim) {
        trim = trim.trim();
        for (int i = 0; i <= this.g4.countItems() - 1; ++i) {
            if (this.g4.getItem(i).equalsIgnoreCase(trim) || this.g4.getItem(i).equalsIgnoreCase("@" + trim) || this.g4.getItem(i).equalsIgnoreCase("+" + trim)) {
                this.g4.delItem(i);
                this.g5.d(Integer.toString(this.g4.countItems()));
                return true;
            }
        }
        return false;
    }
    
    public final boolean bp(String trim) {
        trim = trim.trim();
        for (int countItems = this.g4.countItems(), i = 0; i <= countItems - 1; ++i) {
            if (this.g4.getItem(i).equals(trim) || this.g4.getItem(i).equalsIgnoreCase("+" + trim) || this.g4.getItem(i).equalsIgnoreCase("@" + trim)) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean bo(String trim, String s) {
        trim = trim.trim();
        s = s.trim();
        final int countItems = this.g4.countItems();
        int n = -1;
        for (int i = 0; i <= countItems - 1; ++i) {
            if (this.g4.getItem(i).equalsIgnoreCase(trim)) {
                n = i;
                break;
            }
            if (this.g4.getItem(i).equalsIgnoreCase("+" + trim)) {
                s = "+" + s;
                n = i;
                break;
            }
            if (this.g4.getItem(i).equalsIgnoreCase("@" + trim)) {
                s = "@" + s;
                n = i;
                break;
            }
            if (this.g4.getItem(i).equalsIgnoreCase("+@" + trim)) {
                s = "+@" + s;
                n = i;
                break;
            }
        }
        if (n != -1) {
            this.g4.replaceItem(s, n);
            return true;
        }
        return false;
    }
    
    public final boolean bn(String trim, final String s, final boolean b) {
        trim = trim.trim();
        for (int countItems = this.g4.countItems(), i = 0; i <= countItems - 1; ++i) {
            if (this.g4.getItem(i).equalsIgnoreCase(trim)) {
                if (b) {
                    this.g4.replaceItem(String.valueOf(s) + "" + trim, i);
                }
                return true;
            }
            if (this.g4.getItem(i).equalsIgnoreCase("+" + trim)) {
                if (!b && s.equals("+")) {
                    this.g4.replaceItem(trim, i);
                    return true;
                }
                if (b && s.equals("@")) {
                    this.g4.replaceItem("+@" + trim, i);
                    return true;
                }
            }
            else if (this.g4.getItem(i).equalsIgnoreCase("@" + trim)) {
                if (!b && s.equals("@")) {
                    this.g4.replaceItem(trim, i);
                    return true;
                }
                if (b && s.equals("+")) {
                    this.g4.replaceItem("+@" + trim, i);
                    return true;
                }
            }
            else if (this.g4.getItem(i).equalsIgnoreCase("+@" + trim) && !b) {
                if (s.equals("@")) {
                    this.g4.replaceItem("+" + trim, i);
                    return true;
                }
                if (s.equals("+")) {
                    this.g4.replaceItem("@" + trim, i);
                    return true;
                }
            }
        }
        return false;
    }
    
    public final void bm() {
        this.g7 = new Font(this.hc.eb, 0, this.hc.ec);
        this.fm = new Font(this.hc.eb, 1, 8);
        this.fl = new Font(this.hc.eb, 0, 8);
    }
    
    public final boolean bl() {
        final String property = System.getProperty("java.vendor");
        try {
            return property.indexOf("Netscape") != -1;
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public final boolean bk() {
        return this.bl() && this.hb && !this.hc.dt && this.hc.d1;
    }
    
    public final void bj(String s) {
        s = s.trim();
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        try {
            s = stringTokenizer.nextToken();
        }
        catch (Exception ex) {
            s = "";
        }
        if (!this.hc.du || s == null || s.equals("")) {
            return;
        }
        new c(this, s, false).start();
    }
}
