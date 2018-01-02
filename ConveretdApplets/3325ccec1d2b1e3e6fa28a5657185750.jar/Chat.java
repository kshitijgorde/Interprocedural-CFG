import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
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
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Chat extends Applet implements Runnable
{
    public boolean gs;
    public e gr;
    public Ident gq;
    public f gp;
    public String bv;
    public boolean go;
    public boolean gn;
    public Thread gm;
    public n gl;
    public boolean gk;
    public Panel gj;
    public Panel gi;
    public Panel gh;
    public Font gg;
    public m gf;
    public a ge;
    public List gd;
    public b gc;
    public TextField l;
    public Button gb;
    public Button ga;
    public Button f9;
    public Button f8;
    public Button f7;
    public Checkbox f6;
    public Checkbox f5;
    public Checkbox f4;
    public Panel f3;
    public Panel f2;
    public Panel f1;
    public Panel f0;
    public Button f_;
    public Panel fz;
    public Panel fy;
    public Choice fx;
    public Choice fw;
    public TextField fv;
    public TextField fu;
    public TextField ft;
    public TextField fs;
    public TextField fr;
    public TextField fq;
    public TextField fp;
    public Panel fo;
    public GridBagLayout fn;
    public GridBagConstraints fm;
    public Label fl;
    public Label fk;
    public Label fj;
    public Label fi;
    public Label fh;
    public Label fg;
    public Label ff;
    public Canvas fe;
    public Canvas fc;
    public Canvas fb;
    public Canvas fa;
    public Canvas e9;
    public Canvas e8;
    public Canvas e7;
    public Canvas e6;
    public Panel e5;
    public h e4;
    public Panel e3;
    public Panel e2;
    public Panel e1;
    public Label e0;
    public Label e_;
    public Label ez;
    public CardLayout ey;
    public int ex;
    public Font ew;
    public Font ev;
    public String eu;
    public int et;
    public boolean aj;
    
    public Chat() {
        this.gs = false;
        this.bv = "\u0003";
        this.go = false;
        this.gn = true;
        this.gl = new n();
        this.gk = true;
        this.fv = new TextField(30);
        this.fu = new TextField(10);
        this.ft = new TextField(10);
        this.fs = new TextField(10);
        this.fr = new TextField(30);
        this.fq = new TextField(10);
        this.fp = new TextField(10);
        this.ex = -11;
        this.aj = false;
        if (!this.gk) {
            this.init();
            this.start();
        }
    }
    
    public final void init() {
        this.cb();
        this.fl = new Label(this.gl.cy);
        this.fk = new Label(this.gl.cw);
        this.fj = new Label(this.gl.c1);
        this.fi = new Label(this.gl.c0);
        this.fh = new Label(this.gl.cz);
        this.fg = new Label(this.gl.cx);
        this.ff = new Label(this.gl.c_);
        new Vector();
        this.gr = new e(this.gl.v);
        this.e_ = new Label();
        if (this.gr.p()) {
            final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
            if (lowerCase.endsWith(this.gr.w)) {
                this.gl.ac = this.gr.ac;
                this.gl.ab = this.gr.ab;
                this.gl.aa = this.gr.aa;
                this.gs = true;
            }
            else {
                System.out.println("License failed: Register to host " + this.gr.w + ", but document host is " + lowerCase);
                this.gs = false;
            }
        }
        else {
            this.gs = false;
        }
        this.f8 = new Button(this.gl.cr);
        this.bm();
        this.setFont(this.gg);
        this.ca();
        this.b9();
        this.ey = new CardLayout();
        (this.gj = new Panel()).setLayout(this.ey);
        this.gj.add("Config", this.gi);
        this.gj.add("Chat", this.gh);
        if (this.gl.dh) {
            this.ey.show(this.gj, "Chat");
        }
        else {
            this.ey.show(this.gj, "Config");
        }
        this.setLayout(new BorderLayout(this.gl.er, this.gl.eq));
        this.add("North", new Canvas());
        this.add("South", new Canvas());
        this.add("West", new Canvas());
        this.add("East", new Canvas());
        this.add("Center", this.gj);
    }
    
    public final void cb() {
        this.gl.bi(this);
    }
    
    public final void ca() {
        int n = 0;
        this.fn = new GridBagLayout();
        this.fm = new GridBagConstraints();
        this.fm.insets = new Insets(this.gl.eq, this.gl.er, 0, this.gl.er);
        this.fx = new Choice();
        for (int size = this.gl.el.size(), i = 0; i < size; ++i) {
            this.fx.addItem((String)this.gl.el.elementAt(i));
        }
        if (this.fx.countItems() <= 0) {
            this.fx.addItem(this.gl.eh);
        }
        this.fw = new Choice();
        for (int size2 = this.gl.en.size(), j = 0; j < size2; ++j) {
            this.fw.addItem((String)this.gl.en.elementAt(j));
        }
        if (this.fw.countItems() <= 0) {
            this.fw.addItem(this.gl.ei);
        }
        (this.f6 = new Checkbox(this.gl.cv)).setState(this.gl.de);
        (this.f4 = new Checkbox(this.gl.cu)).setState(this.gl.c9);
        (this.f0 = new Panel()).setLayout(new GridLayout(1, 2));
        this.f0.add(this.f6);
        this.f0.add(this.f4);
        (this.fy = new Panel()).setLayout(this.fn);
        this.fm.weightx = 0.0;
        this.fm.weighty = 0.0;
        this.fm.fill = 0;
        this.ft.setText(this.gl.ej);
        this.fs.setText(this.fw.getItem(0));
        this.fr.setText(this.gl.ee);
        this.fv.setText(this.fx.getItem(0));
        this.fu.setText(Integer.toString(this.gl.ek));
        this.fp.setEchoCharacter('*');
        this.fq.setEchoCharacter('*');
        this.fp.setText(this.gl.ed);
        this.fq.setText(this.gl.ec);
        if (this.gl.dq) {
            this.fm.anchor = 13;
            this.bu(this.fy, this.fj, this.fn, this.fm, 0, n, 1, 1);
            this.fm.anchor = 17;
            this.bu(this.fy, this.ft, this.fn, this.fm, 1, n, 1, 1);
            ++n;
        }
        if (this.gl.dp) {
            this.fm.anchor = 13;
            this.bu(this.fy, this.fi, this.fn, this.fm, 0, n, 1, 1);
            if (!this.gl.db) {
                this.fm.anchor = 17;
                this.bu(this.fy, this.fs, this.fn, this.fm, 1, n, 1, 1);
                ++n;
            }
            this.fm.anchor = 17;
            this.bu(this.fy, this.fw, this.fn, this.fm, 1, n, 2, 1);
            ++n;
        }
        if (this.gl.do) {
            this.fm.anchor = 13;
            this.bu(this.fy, this.ff, this.fn, this.fm, 0, n, 1, 1);
            this.fm.anchor = 17;
            this.bu(this.fy, this.fq, this.fn, this.fm, 1, n, 2, 1);
            ++n;
        }
        if (this.gl.dn) {
            this.fm.anchor = 13;
            this.bu(this.fy, this.fh, this.fn, this.fm, 0, n, 1, 1);
            this.fm.anchor = 17;
            this.bu(this.fy, this.fr, this.fn, this.fm, 1, n, 2, 1);
            ++n;
        }
        if (this.gl.dm) {
            this.fm.anchor = 13;
            this.bu(this.fy, this.fl, this.fn, this.fm, 0, n, 1, 1);
            if (!this.gl.da) {
                this.fm.anchor = 17;
                this.bu(this.fy, this.fv, this.fn, this.fm, 1, n, 2, 1);
                ++n;
            }
            this.fm.anchor = 17;
            this.bu(this.fy, this.fx, this.fn, this.fm, 1, n, 2, 1);
            ++n;
        }
        if (this.gl.dl) {
            this.fm.anchor = 13;
            this.bu(this.fy, this.fg, this.fn, this.fm, 0, n, 1, 1);
            this.fm.anchor = 17;
            this.bu(this.fy, this.fp, this.fn, this.fm, 1, n, 2, 1);
            ++n;
        }
        if (this.gl.dk) {
            this.fm.anchor = 13;
            this.bu(this.fy, this.fk, this.fn, this.fm, 0, n, 1, 1);
            this.fm.anchor = 17;
            this.fm.insets = new Insets(this.gl.eq, this.gl.er, this.gl.eq, this.gl.er);
            this.bu(this.fy, this.fu, this.fn, this.fm, 1, n, 2, 1);
            ++n;
        }
        if (this.gl.dj) {
            this.fm.anchor = 10;
            this.bu(this.fy, this.f0, this.fn, this.fm, 0, n, 4, 1);
        }
        (this.fe = new Canvas()).resize(10, 1);
        this.fe.setBackground(Color.white);
        (this.fc = new Canvas()).resize(10, 1);
        this.fc.setBackground(Color.white);
        (this.e9 = new Canvas()).resize(1, 10);
        this.e9.setBackground(Color.white);
        (this.e8 = new Canvas()).resize(1, 10);
        this.e8.setBackground(Color.white);
        (this.e5 = new Panel()).setLayout(new BorderLayout());
        this.e5.add("North", this.fe);
        this.e5.add("South", this.fc);
        this.e5.add("West", this.e9);
        this.e5.add("East", this.e8);
        this.e5.add("Center", this.fy);
        (this.fo = new Panel()).setLayout(new FlowLayout(1, this.gl.er, this.gl.eq));
        this.f9 = new Button(this.gl.ct);
        this.f_ = new Button(this.gl.cs);
        this.fo.add(this.f9);
        this.fo.add(this.f_);
        if (!this.gl.c5 && this.gl.c3) {
            this.fo.add(this.f8);
        }
        (this.fz = new Panel()).setLayout(new BorderLayout(this.gl.er, 0));
        this.fz.setBackground(Color.white);
        if (this.gk) {
            this.e4 = new h(this.getCodeBase());
        }
        else {
            this.e4 = new h();
        }
        this.e4.resize(this.gl.ea, this.gl.d9);
        (this.e1 = new Panel()).add(this.e4);
        final Font font = new Font("TimesRoman", 0, 10);
        (this.e0 = new Label("JPilot jIRC applet V2.42.1")).setForeground(Color.red);
        this.e0.setFont(font);
        this.e3 = new Panel();
        if (this.gs) {
            this.e_.setForeground(Color.red);
            this.e_.setFont(font);
            (this.ez = new Label("http://www.jpilot.com")).setForeground(Color.red);
            this.ez.setFont(font);
        }
        else {
            (this.e_ = new Label("Unregistered Copy")).setForeground(Color.red);
            this.e_.setFont(font);
            (this.ez = new Label("http://www.jpilot.com")).setForeground(Color.red);
            this.ez.setFont(font);
            this.e3.setLayout(new GridLayout(3, 1, 0, 0));
            this.e3.add(this.e0);
            this.e3.add(this.ez);
            this.e3.add(this.e_);
        }
        (this.e2 = new Panel()).setLayout(new BorderLayout(this.gl.er, this.gl.eq));
        this.e2.add("North", this.e1);
        this.e2.add("South", this.e3);
        (this.e7 = new Canvas()).setBackground(Color.black);
        this.e7.resize(2, 10);
        (this.e6 = new Canvas()).setBackground(Color.black);
        this.e6.resize(2, 10);
        (this.fb = new Canvas()).setBackground(Color.black);
        this.fb.resize(10, 2);
        (this.fa = new Canvas()).setBackground(Color.black);
        this.fa.resize(10, 2);
        this.fz.add("Center", this.e2);
        this.fz.add("East", this.e7);
        this.fz.add("West", this.e6);
        this.fz.add("North", this.fb);
        this.fz.add("South", this.fa);
        this.fz.setBackground(this.gl.d3);
        (this.gi = new Panel()).setFont(this.gg);
        this.gi.setForeground(this.gl.d2);
        this.gi.setBackground(this.gl.d6);
        this.gi.setLayout(new BorderLayout(this.gl.er, this.gl.eq));
        this.gi.add("West", this.fz);
        this.gi.add("Center", this.e5);
        this.gi.add("South", this.fo);
    }
    
    public final void b9() {
        this.f2 = new Panel();
        (this.l = new TextField("", this.gl.es)).setBackground(this.gl.d0);
        this.l.setForeground(this.gl.d1);
        this.l.setFont(this.gg);
        this.gb = new Button(this.gl.cp);
        this.ga = new Button(this.gl.cq);
        (this.f7 = new Button("B")).setFont(this.ev);
        (this.ge = new a("Channel", "0", "", this.gl.du, this.gl.dt, this.gl.dr, this.gl.ds, this.gl.b, this.gl.a)).e(this.gl.bq);
        this.gf = new m(this, this.gl.d1);
        (this.f1 = new Panel()).setLayout(new FlowLayout(1, 4, 0));
        if (this.gl.dg) {
            this.f1.add(this.f7);
            this.f1.add(this.gf);
        }
        if (!this.gl.c5) {
            this.f1.add(this.ga);
        }
        else if (this.gl.c3) {
            this.f1.add(this.f8);
        }
        this.f1.add(this.gb);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", this.l);
        this.f2.setLayout(new BorderLayout());
        this.f2.add("Center", panel);
        this.f2.add("East", this.f1);
        (this.f3 = new Panel()).setFont(this.gg);
        this.f3.setLayout(new BorderLayout());
        (this.gd = new List(1, false)).setForeground(this.gl.d5);
        this.gd.setBackground(this.gl.d4);
        this.f3.add("West", this.gd);
        (this.f5 = new Checkbox(this.gl.cn)).setState(this.gl.dd);
        if (this.gl.df) {
            this.f3.add("South", this.f5);
        }
        (this.gh = new Panel()).setForeground(this.gl.d2);
        this.gh.setBackground(this.gl.d6);
        this.gh.setFont(this.gg);
        this.gh.setLayout(new BorderLayout(this.gl.er, this.gl.eq));
        (this.gc = new b()).i(this);
        this.gc.setBackground(this.gl.d7);
        this.gc.setForeground(this.gl.d8);
        this.gc.setFont(this.gg);
        this.gc.e(this.gl.bq);
        this.at(this.gl.c2);
        this.gh.add("South", this.f2);
        this.gh.add("East", this.f3);
        this.gh.add("Center", this.gc);
        this.gh.add("North", this.ge);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.ga) {
                    this.bz();
                    return true;
                }
                if (event.target == this.f6) {
                    this.gl.de = this.f6.getState();
                    return true;
                }
                if (event.target == this.f5) {
                    this.gl.dd = this.f5.getState();
                    return true;
                }
                if (event.target == this.f4) {
                    this.gl.c9 = this.f4.getState();
                    return true;
                }
                if (event.target == this.fx) {
                    this.b5(event);
                    return true;
                }
                if (event.target == this.fw) {
                    this.b4(event);
                    return true;
                }
                if (event.target == this.f7) {
                    this.go = !this.go;
                    if (this.go) {
                        this.f7.setFont(this.ew);
                    }
                    else {
                        this.f7.setFont(this.ev);
                    }
                    this.l.requestFocus();
                }
                else {
                    if (event.target == this.f9) {
                        this.b7();
                        this.et = 0;
                        this.b1(event);
                        return true;
                    }
                    if (event.target == this.f_) {
                        this.b6();
                        return true;
                    }
                    if (event.target == this.gb) {
                        if (this.gb.getLabel().equals(this.gl.cp)) {
                            this.et = 0;
                            this.b1(event);
                        }
                        else {
                            this.b0();
                        }
                        return true;
                    }
                    if (event.target == this.f8) {
                        this.b8();
                        return true;
                    }
                    if (event.target == this.gd) {
                        this.b3(event);
                        return true;
                    }
                    if (event.target == this.l) {
                        this.au(this.l.getText(), true);
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
    
    public final void b8() {
        new j(this.gs, this.gl.ac, this.gl.ab, this.gl.aa, "2.42.1").show();
    }
    
    public final void b7() {
        this.gl.ej = this.ft.getText();
        this.gl.ee = this.fr.getText();
        if (this.gl.db) {
            this.gl.ei = this.fw.getSelectedItem();
        }
        else {
            this.gl.ei = this.fs.getText();
        }
        if (this.gl.da) {
            this.gl.eh = this.fx.getSelectedItem();
        }
        else {
            this.gl.eh = this.fv.getText();
        }
        try {
            this.gl.ek = new Integer(this.fu.getText());
        }
        catch (Exception ex) {
            this.gl.ek = 6667;
            this.fu.setText("6667");
        }
        this.gl.ej = this.gl.ej.replace(' ', '_');
        if (this.gl.ei.startsWith("#") || this.gl.ei.startsWith("&")) {
            this.gl.ei = this.gl.ei.substring(1);
        }
        this.gl.eh = this.gl.eh.replace(' ', '_');
        this.gl.ed = this.fp.getText();
        this.gl.ec = this.fq.getText();
        this.ft.setText(this.gl.ej);
        this.fs.setText(this.gl.ei);
        this.fr.setText(this.gl.ee);
        this.fv.setText(this.gl.eh);
        this.fu.setText(Integer.toString(this.gl.ek));
        this.by();
        if (this.gp == null || !this.aj || !this.gp.isAlive()) {
            return;
        }
        if (!this.gl.ej.equals(this.gl.eg)) {
            this.gp.aj("/nick " + this.gl.ej);
            return;
        }
        if (!this.gl.ei.equals(this.gl.ef)) {
            this.gp.aj("/join #" + this.gl.ei);
        }
    }
    
    public final void b6() {
        this.ft.setText(this.gl.ej);
        this.fr.setText(this.gl.ee);
        if (this.gl.db) {
            this.gl.ei = this.fw.getSelectedItem();
        }
        else {
            this.gl.ei = this.fs.getText();
        }
        if (this.gl.da) {
            this.gl.eh = this.fx.getSelectedItem();
        }
        else {
            this.gl.eh = this.fv.getText();
        }
        this.fs.setText(this.gl.ei);
        this.fv.setText(this.gl.eh);
        this.fu.setText(Integer.toString(this.gl.ek));
        this.by();
    }
    
    public final void b5(final Event event) {
        this.fv.setText((String)event.arg);
    }
    
    public final void b4(final Event event) {
        this.fs.setText((String)event.arg);
    }
    
    public final void b3(final Event event) {
        final String s = (String)event.arg;
        if (this.gp.isAlive()) {
            if (s.startsWith("@")) {
                this.gp.u(s.substring(1));
                return;
            }
            if (s.startsWith("+")) {
                this.gp.u(s.substring(1));
                return;
            }
            this.gp.u(s);
        }
    }
    
    public final void b2(final int ex, final Color foreground) {
        this.ex = ex;
        this.l.setForeground(foreground);
    }
    
    public final void processJInput(final String s) {
        this.au(s, false);
    }
    
    public final void au(final String eu, final boolean b) {
        this.eu = eu;
        if (b) {
            this.l.setText("");
            this.l.setForeground(this.gl.d1);
        }
        if (this.eu == null || this.eu.equals("")) {
            if (b) {
                this.ex = -1;
            }
            return;
        }
        if (this.aj) {
            if (!this.eu.startsWith("/")) {
                if (this.ex >= 0 && this.ex <= 15) {
                    this.eu = String.valueOf(this.bv) + this.ex + this.eu;
                }
                if (this.go) {
                    this.eu = "\u0002" + this.eu;
                }
                this.gp.aj(this.eu);
            }
            else if (this.gl.c4) {
                this.gp.aj(this.eu);
            }
            else if (this.eu.startsWith("/quit") || this.eu.startsWith("/s ") || this.eu.startsWith("/msg ")) {
                this.gp.aj(this.eu);
            }
        }
        if (b) {
            this.ex = -1;
            this.gf.be();
            this.go = false;
            this.f7.setFont(this.ev);
        }
    }
    
    public final synchronized void b1(final Event event) {
        this.b_();
        this.at("Connecting to " + this.gl.eh + " " + this.gl.ek + " ......");
        this.gb.setLabel(this.gl.co);
        this.f9.disable();
        if (this.gm != null) {
            this.gm.isAlive();
        }
        (this.gm = new Thread(this)).start();
    }
    
    public final void b0() {
        if (this.gp != null && this.gp.isAlive()) {
            this.gp.ak();
            this.gp.y();
            this.gp = null;
        }
        this.ab();
        this.gb.setLabel(this.gl.cp);
        this.f9.enable();
    }
    
    public final void b_() {
        if (!this.gl.c9) {
            return;
        }
        if (this.gq != null && this.gq.isAlive()) {
            return;
        }
        if (!this.gl.bi.equals("")) {
            this.gq = new Ident(113, this.bk(), this.gl.bi);
        }
        else {
            this.gq = new Ident(113, this.bk(), this.gl.ej);
        }
        this.gq.a_(this.gl.ek);
        this.gq.start();
        if (!this.gq.bj) {
            System.out.println("Failed to start Ident daemon or it has already running.");
            this.gq.a0();
            this.gq = null;
        }
    }
    
    public final void bz() {
        this.ey.show(this.gj, "Config");
    }
    
    public final void by() {
        this.ey.show(this.gj, "Chat");
    }
    
    public final void at(final String s) {
        this.gc.h(s, this.gl.d8);
    }
    
    public final void y() {
        this.y(false);
    }
    
    public final void y(final boolean b) {
        this.aj = false;
        this.gl.eg = "";
        this.gl.ef = "";
        this.at("Connection closed.");
        this.gb.setLabel(this.gl.cp);
        this.f9.enable();
        if (b && this.gl.c7) {
            if (this.et == this.fx.countItems()) {
                return;
            }
            if (this.fx.getSelectedIndex() == this.fx.countItems() - 1) {
                try {
                    this.fx.select(0);
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    this.fx.select(this.fx.getSelectedIndex() + 1);
                }
                catch (Exception ex2) {}
            }
            this.gl.eh = this.fx.getSelectedItem();
            this.fv.setText(this.gl.eh);
            ++this.et;
            this.b1(null);
        }
    }
    
    public final void run() {
        try {
            final f gp = new f();
            gp.as(this);
            gp.start();
            this.gp = gp;
        }
        catch (Exception ex) {
            this.at("Create connection failed." + ex.toString());
        }
    }
    
    public final void start() {
        if (this.bk()) {
            AccessRequester.cc();
        }
        if (this.gl.dh) {
            this.b7();
            this.b1(null);
        }
    }
    
    public final void stop() {
        if (this.gp != null && this.gp.isAlive()) {
            this.gp.ak();
            this.gp.y();
            this.gp = null;
        }
        this.gb.setLabel(this.gl.cp);
        this.f9.enable();
        this.gl.eg = "";
        this.gl.ef = "";
        this.aj = false;
    }
    
    public final void destroy() {
        if (this.isActive()) {
            this.stop();
        }
        super.destroy();
    }
    
    public final void ab() {
        this.gl.eg = "";
        this.gl.ef = "";
        this.aj = false;
        if (this.gm != null && this.gm.isAlive()) {
            this.gm.stop();
        }
        this.gb.setLabel(this.gl.cp);
    }
    
    public final boolean bx(final String s) {
        final String trim = s.trim();
        if (!this.gk || !this.gl.de) {
            return false;
        }
        try {
            this.getAppletContext().showDocument(new URL(trim), "JAVACHAT_WIN");
        }
        catch (Exception ex) {
            this.at("*** Displaying URL error: " + trim);
            return false;
        }
        return true;
    }
    
    public final void bw() {
        this.gc.g();
    }
    
    public final void bv(final boolean aj) {
        this.aj = aj;
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
        this.gd.clear();
        this.ge.c("0");
        this.ge.d("Channel");
    }
    
    public final void bs() {
        this.gd.clear();
        this.ge.c("0");
        this.ge.d(this.gl.ef);
        this.ge.b("");
    }
    
    public final void br(String trim) {
        trim = trim.trim();
        final int n = this.gd.countItems() - 1;
        for (int i = 0; i <= n; ++i) {
            if (trim.compareTo(this.gd.getItem(i)) < 0) {
                this.gd.addItem(trim, i);
                break;
            }
        }
        if (this.gd.countItems() - 1 == n) {
            this.gd.addItem(trim, n);
        }
        this.ge.c(Integer.toString(this.gd.countItems()));
    }
    
    public final boolean bq(String trim) {
        trim = trim.trim();
        for (int i = 0; i <= this.gd.countItems() - 1; ++i) {
            if (this.gd.getItem(i).equalsIgnoreCase(trim) || this.gd.getItem(i).equalsIgnoreCase("@" + trim) || this.gd.getItem(i).equalsIgnoreCase("+" + trim)) {
                this.gd.delItem(i);
                this.ge.c(Integer.toString(this.gd.countItems()));
                return true;
            }
        }
        return false;
    }
    
    public final boolean bp(String trim) {
        trim = trim.trim();
        for (int countItems = this.gd.countItems(), i = 0; i <= countItems - 1; ++i) {
            if (this.gd.getItem(i).equals(trim) || this.gd.getItem(i).equalsIgnoreCase("+" + trim) || this.gd.getItem(i).equalsIgnoreCase("@" + trim)) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean bo(String trim, String s) {
        trim = trim.trim();
        s = s.trim();
        final int countItems = this.gd.countItems();
        int n = -1;
        for (int i = 0; i <= countItems - 1; ++i) {
            if (this.gd.getItem(i).equalsIgnoreCase(trim)) {
                n = i;
                break;
            }
            if (this.gd.getItem(i).equalsIgnoreCase("+" + trim)) {
                s = "+" + s;
                n = i;
                break;
            }
            if (this.gd.getItem(i).equalsIgnoreCase("@" + trim)) {
                s = "@" + s;
                n = i;
                break;
            }
            if (this.gd.getItem(i).equalsIgnoreCase("+@" + trim)) {
                s = "+@" + s;
                n = i;
                break;
            }
        }
        if (n != -1) {
            this.gd.replaceItem(s, n);
            return true;
        }
        return false;
    }
    
    public final boolean bn(String trim, final String s, final boolean b) {
        trim = trim.trim();
        for (int countItems = this.gd.countItems(), i = 0; i <= countItems - 1; ++i) {
            if (this.gd.getItem(i).equalsIgnoreCase(trim)) {
                if (b) {
                    this.gd.replaceItem(String.valueOf(s) + "" + trim, i);
                }
                return true;
            }
            if (this.gd.getItem(i).equalsIgnoreCase("+" + trim)) {
                if (!b && s.equals("+")) {
                    this.gd.replaceItem(trim, i);
                    return true;
                }
                if (b && s.equals("@")) {
                    this.gd.replaceItem("+@" + trim, i);
                    return true;
                }
            }
            else if (this.gd.getItem(i).equalsIgnoreCase("@" + trim)) {
                if (!b && s.equals("@")) {
                    this.gd.replaceItem(trim, i);
                    return true;
                }
                if (b && s.equals("+")) {
                    this.gd.replaceItem("+@" + trim, i);
                    return true;
                }
            }
            else if (this.gd.getItem(i).equalsIgnoreCase("+@" + trim) && !b) {
                if (s.equals("@")) {
                    this.gd.replaceItem("+" + trim, i);
                    return true;
                }
                if (s.equals("+")) {
                    this.gd.replaceItem("@" + trim, i);
                    return true;
                }
            }
        }
        return false;
    }
    
    public final void bm() {
        this.gg = new Font(this.gl.dr, 0, this.gl.ds);
        this.ew = new Font(this.gl.dr, 1, 8);
        this.ev = new Font(this.gl.dr, 0, 8);
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
        return this.bl() && this.gk && !this.gl.dc && this.gl.di;
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
        if (!this.gl.dd || s == null || s.equals("")) {
            return;
        }
        new c(this, s, false).start();
    }
}
