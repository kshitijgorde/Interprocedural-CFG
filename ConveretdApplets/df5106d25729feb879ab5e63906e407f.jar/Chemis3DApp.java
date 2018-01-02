import java.util.StringTokenizer;
import java.awt.MenuItem;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MediaTracker;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Chemis3DApp extends Applet implements Runnable
{
    private static final String k = " http://members.xoom.com/Chemis/Chemis3D";
    private static final String cc = "© 1999,2000 Didier Collomb";
    private static final String d = "Chemis3DApp Ver 1.93";
    private static final String gc;
    private static final String hc;
    private static final Font rc;
    private MediaTracker jb;
    private l ac;
    private l uc;
    private l zb;
    private l v;
    private l ob;
    private l j;
    private l wb;
    private k rb;
    private k q;
    private k ab;
    private k r;
    private k fc;
    private k sb;
    private k bc;
    private k n;
    private k lc;
    private k kc;
    private k g;
    private k a;
    private k qb;
    private Menu yc;
    private Menu bd;
    private Menu cd;
    private Menu ad;
    private Menu zc;
    private MenuBar kb;
    private Frame ub;
    private String pb;
    private String bb;
    private String vb;
    private String hb;
    protected boolean cb;
    protected boolean tb;
    protected boolean mc;
    private int db;
    private int eb;
    private int gb;
    private int fb;
    private int dc;
    private int lb;
    private int mb;
    private int nb;
    private int nc;
    private int oc;
    private int pc;
    private int qc;
    private int ec;
    private int u;
    private int yb;
    private int xb;
    private int z;
    private int sc;
    private int tc;
    private int s;
    private int t;
    private int xc;
    private int wc;
    private int c;
    private int b;
    private float h;
    private float i;
    private float p;
    private float o;
    private Color jc;
    private Image ic;
    private Image l;
    private Graphics x;
    private Thread f;
    protected boolean w;
    protected float y;
    protected float m;
    protected f e;
    protected f vc;
    m ib;
    
    static {
        rc = new Font("Helvetica", 0, 12);
        hc = System.getProperty("java.vendor");
        gc = System.getProperty("java.version");
    }
    
    public Chemis3DApp() {
        this.vc = new f();
        this.e = new f();
        this.m = 1.0f;
        this.w = true;
        this.jc = Color.black;
        this.cb = true;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "model", "URL", "The path to the model to be displayed." }, { "format", "string", "Molecular datafile format (x-mol, x-xyz, x-pdb)" }, { "style", "string", "Molecular display (ball, ballstick, stick, wireframe)" }, { "fastmove", "string", "Fast drawing on move (on or off)" }, { "color", "string", "Display style (atom, group or chain)" }, { "back", "string", "Color (#hex format) or Image (*.jpeg or *.gif)" }, { "scale", "float", "Scale of the molecule (0-2)" }, { "light", "int", "Light intensity (0-255) and direction (x/y -16/+16)" }, { "spin", "string", "Spin orientation (X,Y,Z)" }, { "setmenu", "string", " Menu position (x,y,width)" } };
    }
    
    public String getAppletInfo() {
        return "Chemis3DApp Ver 1.93" + a();
    }
    
    private void a(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(this.getDocumentBase(), s), "Chemis3D Help");
        }
        catch (Exception ex) {
            System.out.println("Unable to load " + s + " !");
        }
    }
    
    private static String a() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n                  ");
        sb.append("© 1999,2000 Didier Collomb");
        sb.append("\n                    d.collomb@mail.dotcom.fr\n   ");
        sb.append(" http://members.xoom.com/Chemis/Chemis3D");
        sb.append("\n\nThis version of Chemis3D may be  freely  distributed\n");
        sb.append("for non-commercial purpose providing credit is given\n\n");
        sb.append("Disassembling, Decompiling or Reverse-engineering\n");
        sb.append("is strictly forbidden.");
        return sb.toString();
    }
    
    public void paint(final Graphics graphics) {
        if (this.ib == null) {
            graphics.setFont(Chemis3DApp.rc);
            graphics.drawString("Chemis3DApp Ver 1.93", 5, 20);
            graphics.drawLine(5, 26, 130, 26);
            graphics.drawString("© 1999,2000 Didier Collomb", 5, 50);
            graphics.drawString(" http://members.xoom.com/Chemis/Chemis3D", 5, 64);
            graphics.drawString("Please Wait while loading data ...", 5, 100);
            return;
        }
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        if (this.ib != null) {
            this.ib.p.a();
            this.ib.p.a(-this.ib.e, -this.ib.c, -this.ib.a);
            this.ib.p.a(this.vc);
            this.ib.p.b(this.y, -this.y, 16.0f * this.y / this.b);
            this.ib.p.a(this.wc, this.xc, 8.0f);
            this.ib.i = false;
            if (this.x != null) {
                this.x.setColor(this.jc);
                this.x.fillRect(0, 0, this.b, this.c);
                if (this.ic != null) {
                    this.x.drawImage(this.ic, 0, 0, this.b, this.c, this);
                }
                this.ib.a(this.x, this.xb, this.yb, this.y);
                if (this.mc) {
                    if (this.ob.getState()) {
                        int e = this.ib.l.e;
                        while (--e >= 0) {
                            this.x.setColor(this.ib.b(this.ib.a(e)));
                            if (this.dc == 0) {
                                if (this.ib.a(e) == 0 && this.yb != 0) {
                                    continue;
                                }
                                this.x.drawString(String.valueOf(this.ib.l.l[e]) + (e + 1), this.ib.h[e], this.ib.g[e]);
                            }
                            else {
                                if (e <= 0 || this.ib.l.m[e] == this.ib.l.m[e - 1]) {
                                    continue;
                                }
                                this.x.drawString(String.valueOf(this.ib.l.n[e]) + this.ib.l.m[e] + "  ", this.ib.h[e + 1], this.ib.g[e + 1]);
                            }
                        }
                    }
                    else if (this.ec > 0) {
                        this.x.setColor(Color.green);
                        if (this.v.getState()) {
                            if (this.ib.l.g) {
                                this.x.drawString(String.valueOf(this.ib.l.l[this.qc]) + " " + (this.qc + 1) + " " + this.ib.l.n[this.qc] + this.ib.l.m[this.qc] + this.ib.l.o[this.qc], this.ib.h[this.qc], this.ib.g[this.qc]);
                            }
                            else {
                                this.x.drawString(String.valueOf(this.ib.l.l[this.qc]) + (this.qc + 1), this.ib.h[this.qc], this.ib.g[this.qc]);
                            }
                        }
                        else {
                            this.x.drawString(String.valueOf(this.ib.l.l[this.qc]) + (this.qc + 1), this.ib.h[this.qc], this.ib.g[this.qc]);
                            if (this.ec > 1) {
                                this.x.drawString(String.valueOf(this.ib.l.l[this.pc]) + (this.pc + 1), this.ib.h[this.pc], this.ib.g[this.pc]);
                                this.x.drawLine(this.ib.h[this.qc], this.ib.g[this.qc], this.ib.h[this.pc], this.ib.g[this.pc]);
                                if (this.zb.getState()) {
                                    this.x.drawString("Distance " + this.ib.l.l[this.qc] + (this.qc + 1) + "-" + this.ib.l.l[this.pc] + (this.pc + 1) + "=" + this.ib.a(this.qc, this.pc) + "\u00c5", 5, this.c - 5);
                                }
                                else if (this.ec > 2) {
                                    this.x.drawString(String.valueOf(this.ib.l.l[this.oc]) + (this.oc + 1), this.ib.h[this.oc], this.ib.g[this.oc]);
                                    this.x.drawLine(this.ib.h[this.pc], this.ib.g[this.pc], this.ib.h[this.oc], this.ib.g[this.oc]);
                                    if (this.uc.getState()) {
                                        this.x.drawString("Angle(" + this.ib.l.l[this.qc] + (this.qc + 1) + "," + this.ib.l.l[this.pc] + (this.pc + 1) + "," + this.ib.l.l[this.oc] + (this.oc + 1) + ") = " + this.ib.a(this.qc, this.pc, this.oc) + "°", 5, this.c - 5);
                                    }
                                    else if (this.ec > 3) {
                                        this.x.drawString(String.valueOf(this.ib.l.l[this.nc]) + (this.nc + 1), this.ib.h[this.nc], this.ib.g[this.nc]);
                                        this.x.drawLine(this.ib.h[this.oc], this.ib.g[this.oc], this.ib.h[this.nc], this.ib.g[this.nc]);
                                        this.x.drawString("Dihedre(" + this.ib.l.l[this.qc] + (this.qc + 1) + "," + this.ib.l.l[this.pc] + (this.pc + 1) + "," + this.ib.l.l[this.oc] + (this.oc + 1) + "," + this.ib.l.l[this.nc] + (this.nc + 1) + ") = " + this.ib.a(this.qc, this.pc, this.oc, this.nc) + "°", 5, this.c - 5);
                                    }
                                }
                            }
                        }
                    }
                }
                graphics.drawImage(this.l, 0, 0, this);
                this.w = true;
            }
        }
        else if (this.bb != null) {
            graphics.setFont(Chemis3DApp.rc);
            graphics.drawString("Error in model:", 3, 120);
            graphics.drawString(this.bb, 3, 135);
        }
    }
    
    private synchronized void b() {
        this.e.a();
        this.e.a(this.i);
        this.e.b(this.h);
        this.vc.a(this.e);
        if (this.w) {
            this.w = false;
            this.repaint();
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 82) {
            this.a(0);
            this.z = 0;
        }
        else if (n == 84) {
            this.a(0);
            this.z = 2;
        }
        else if (n == 90) {
            this.a(0);
            this.z = 1;
        }
        else if (n == 88) {
            this.a(0);
            this.z = 3;
        }
        else if (n == 77) {
            this.ub.toFront();
        }
        else if (n == 66) {
            final boolean b = true;
            this.u = (b ? 1 : 0);
            this.xb = (b ? 1 : 0);
        }
        else if (n == 75) {
            final int n2 = 3;
            this.u = n2;
            this.xb = n2;
        }
        else if (n == 83) {
            final int n3 = 2;
            this.u = n3;
            this.xb = n3;
        }
        else if (n == 87) {
            final boolean b2 = false;
            this.u = (b2 ? 1 : 0);
            this.xb = (b2 ? 1 : 0);
        }
        else if (n == 72) {
            if (this.yb == 0) {
                this.yb = 1;
            }
            else {
                this.yb = 0;
            }
        }
        else if (n == 65) {
            this.dc = 0;
            this.ib.b();
        }
        else if (n == 71) {
            this.ob.setState(false);
            this.ib.d();
            this.dc = 1;
        }
        else if (n == 67) {
            this.ob.setState(false);
            this.ib.c();
            this.dc = 2;
        }
        else if (n == 70) {
            this.wb.setState(true);
        }
        else if (n == 73) {
            this.j.setState(true);
            this.a(1);
        }
        else if (n == 69) {
            this.y = this.ib.d;
            this.vc.a();
        }
        else if (n == 76) {
            this.ob.setState(true);
            this.a(2);
        }
        else if (n == 80) {
            this.v.setState(true);
            this.a(3);
        }
        else if (n == 68) {
            this.zb.setState(true);
            this.a(4);
        }
        else if (n == 78) {
            this.uc.setState(true);
            this.a(5);
        }
        else if (n == 87) {
            this.ac.setState(true);
            this.a(6);
        }
        else if (n == 85) {
            j.createInfo("Molecular Infos", this.ib.a());
        }
        else if (n == 33) {
            j.createInfo("Chemis3DApp Ver 1.93", a());
        }
        else if (n == 81) {
            this.a("QuickHelp.htm");
        }
        else if (n == 63) {
            this.a(" http://members.xoom.com/Chemis/Chemis3D");
        }
        if (this.w) {
            this.w = false;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int t, final int s) {
        if (this.wb.getState()) {
            this.xb = 0;
        }
        if (event.modifiers == 4 || this.z == 1) {
            this.y = ((this.s > s) ? (this.y + (this.s - s >> 3)) : ((this.y > s - this.s >> 3) ? (this.y - (s - this.s >> 3)) : this.y));
        }
        else if (event.modifiers == 1 || this.z == 3) {
            this.e.a();
            this.e.c((s - this.s) * this.o + (t - this.t) * this.p);
            this.vc.a(this.e);
        }
        else if (event.modifiers == 2 || this.z == 2) {
            this.e.a();
            this.e.a(t - this.t >> 3, this.s - s >> 3, 0.0f);
            this.vc.a(this.e);
        }
        else {
            this.e.a();
            final float n = (this.s - s) * 0.5f * this.o;
            final float n2 = (t - this.t) * 0.5f * this.p;
            this.e.a(n);
            this.e.b(n2);
            this.vc.a(this.e);
        }
        if (this.w) {
            this.w = false;
            this.repaint();
        }
        this.tc = this.t;
        this.sc = this.s;
        this.t = t;
        this.s = s;
        return true;
    }
    
    public boolean mouseDown(final Event event, final int t, final int s) {
        this.t = t;
        this.s = s;
        if (event.clickCount > 1) {
            this.ub.toFront();
        }
        if (this.j.getState()) {
            this.h = 0.0f;
            this.i = 0.0f;
        }
        else if (this.v.getState()) {
            this.qc = this.ib.b(t, s);
            this.ec = 1;
        }
        else if (this.zb.getState()) {
            if (this.ec == 1) {
                this.pc = this.ib.b(t, s);
                this.ec = 2;
            }
            else {
                this.qc = this.ib.b(t, s);
                this.ec = 1;
            }
        }
        else if (this.uc.getState()) {
            if (this.ec == 1) {
                this.pc = this.ib.b(t, s);
                this.ec = 2;
            }
            else if (this.ec == 2) {
                this.oc = this.ib.b(t, s);
                this.ec = 3;
            }
            else {
                this.qc = this.ib.b(t, s);
                this.ec = 1;
            }
        }
        else if (this.ac.getState()) {
            if (this.ec == 1) {
                this.pc = this.ib.b(t, s);
                this.ec = 2;
            }
            else if (this.ec == 2) {
                this.oc = this.ib.b(t, s);
                this.ec = 3;
            }
            else if (this.ec == 3) {
                this.nc = this.ib.b(t, s);
                this.ec = 4;
            }
            else {
                this.qc = this.ib.b(t, s);
                this.ec = 1;
            }
        }
        if (this.w) {
            this.w = false;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.j.getState()) {
            this.h = ((n - this.tc == 0) ? 0.0f : ((n - this.tc >> 3) * this.o));
            this.i = ((n2 - this.sc == 0) ? 0.0f : ((this.sc - n2 >> 3) * this.p));
        }
        if (this.wb.getState()) {
            this.xb = this.u;
            if (this.w) {
                this.w = false;
                this.repaint();
            }
        }
        return true;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.g) {
            final int n = 2;
            this.u = n;
            this.xb = n;
        }
        else if (event.target == this.lc) {
            final boolean b = true;
            this.u = (b ? 1 : 0);
            this.xb = (b ? 1 : 0);
        }
        else if (event.target == this.a) {
            final boolean b2 = false;
            this.u = (b2 ? 1 : 0);
            this.xb = (b2 ? 1 : 0);
            this.ob.setState(false);
        }
        else if (event.target == this.kc) {
            final int n2 = 3;
            this.u = n2;
            this.xb = n2;
            this.ob.setState(false);
        }
        else if (event.target == this.qb) {
            if (this.yb == 0) {
                this.yb = 1;
            }
            else {
                this.yb = 0;
            }
        }
        else if (event.target == this.bc) {
            this.dc = 0;
            this.ib.b();
        }
        else if (event.target == this.sb) {
            this.ob.setState(false);
            this.ib.d();
            this.dc = -1;
        }
        else if (event.target == this.fc) {
            this.ob.setState(false);
            this.ib.c();
            this.dc = -2;
        }
        else if (event.target != this.wb) {
            if (event.target == this.j) {
                this.a(1);
            }
            else if (event.target == this.n) {
                this.y = this.ib.d;
                this.vc.a();
            }
            else if (event.target == this.ob) {
                this.a(2);
            }
            else if (event.target == this.v) {
                this.a(3);
            }
            else if (event.target == this.zb) {
                this.a(4);
            }
            else if (event.target == this.uc) {
                this.a(5);
            }
            else if (event.target == this.ac) {
                this.a(6);
            }
            else if (event.target == this.ab) {
                j.createInfo("Molecular Infos", this.ib.a());
            }
            else if (event.target == this.r) {
                j.createInfo("Chemis3DApp Ver 1.93", a());
            }
            else if (event.target == this.q) {
                this.a("QuickHelp.htm");
            }
            else if (event.target == this.rb) {
                this.a(" http://members.xoom.com/Chemis/Chemis3D");
            }
        }
        if (this.w) {
            this.w = false;
            this.repaint();
        }
        return super.action(event, o);
    }
    
    private void a(final int n) {
        if (this.j.getState() && n != 1) {
            this.j.setState(false);
        }
        if (this.ob.getState() && n != 2) {
            this.ob.setState(false);
        }
        if (this.v.getState() && n != 3) {
            this.v.setState(false);
        }
        if (this.zb.getState() && n != 4) {
            this.zb.setState(false);
        }
        if (this.uc.getState() && n != 5) {
            this.uc.setState(false);
        }
        if (this.ac.getState() && n != 6) {
            this.ac.setState(false);
        }
        this.ec = 0;
        if (n <= 1) {
            this.mc = false;
            return;
        }
        this.mc = true;
    }
    
    public void run() {
        InputStream openStream = null;
        try {
            openStream = new URL(this.getDocumentBase(), this.hb).openStream();
            this.getAppletContext().showStatus("Thinking... Please Wait");
            e e;
            if (this.vb.equals("x-pdb")) {
                e = new d(openStream).a();
            }
            else if (this.vb.equals("x-mol")) {
                e = new h(openStream).a();
            }
            else if (this.vb.equals("x-xyz")) {
                e = new b(openStream).a();
            }
            else {
                e = null;
                this.bb = "Unknown Molecular Data Format";
            }
            (this.ib = new m((Applet)this, e, this.dc, this.nb, this.mb, this.lb)).c(this.b, this.c);
            this.y = this.ib.d * this.m;
            if (e.e > 250) {
                this.wb.setState(true);
            }
            if (Chemis3DApp.hc.toLowerCase().indexOf("microsoft") != -1 && Chemis3DApp.gc.compareTo("1.02") > 0 && Chemis3DApp.gc.compareTo("1.1.4") <= 0 && this.cb) {
                this.getAppletContext().showStatus("Use the ScrollBar to show the Applet");
            }
            else {
                this.getAppletContext().showStatus("Chemis3DApp Ver 1.93");
            }
        }
        catch (Exception ex) {
            this.ib = null;
            this.bb = ex.toString();
        }
        try {
            if (openStream != null) {
                openStream.close();
            }
            System.gc();
        }
        catch (Exception ex2) {}
        this.repaint();
        while (this.f != null) {
            if (this.j.getState()) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.b();
                final int n = (int)(50L - (System.currentTimeMillis() - currentTimeMillis >> 3));
                try {
                    Thread.sleep((n < 0) ? 5 : n);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
    
    public void NewModel(final String s) {
        this.c();
        this.b(s);
        this.backBuffer();
        this.start();
    }
    
    public void destroy() {
        this.stop();
    }
    
    private void c() {
        if (this.f != null) {
            this.f.stop();
            this.f = null;
            this.bb = null;
            System.gc();
        }
    }
    
    public void stop() {
        this.c();
        this.ub.hide();
        this.ub.dispose();
    }
    
    public void start() {
        System.gc();
        if (this.hb != null && this.f == null && this.bb == null) {
            (this.f = new Thread(this)).setPriority(1);
            this.f.start();
        }
    }
    
    public void backBuffer() {
        this.l = this.createImage(this.b, this.c);
        (this.x = this.l.getGraphics()).setFont(Chemis3DApp.rc);
    }
    
    private void d() {
        this.setLayout(new BorderLayout());
        (this.kb = new MenuBar()).add(this.ad = new Menu("Display"));
        this.ad.add(this.a = new k(this, "Wireframe"));
        this.ad.add(this.g = new k(this, "Stick"));
        this.ad.add(this.kc = new k(this, "Ball & Stick"));
        this.ad.add(this.lc = new k(this, "Ball"));
        this.ad.addSeparator();
        this.ad.add(this.qb = new k(this, "Hydrogens"));
        this.kb.add(this.zc = new Menu("Options"));
        this.zc.add(this.bd = new Menu("Color"));
        this.bd.add(this.bc = new k(this, "Atoms"));
        this.bd.add(this.sb = new k(this, "Groups"));
        this.bd.add(this.fc = new k(this, "Chains"));
        this.zc.add(this.yc = new Menu("Tools"));
        this.yc.add((MenuItem)(this.ob = new l((Component)this, "Labels")));
        this.ob.setState(false);
        this.yc.add((MenuItem)(this.v = new l((Component)this, "Pick atom")));
        this.v.setState(false);
        this.yc.add((MenuItem)(this.zb = new l((Component)this, "Distance")));
        this.zb.setState(false);
        this.yc.add((MenuItem)(this.uc = new l((Component)this, "Angle")));
        this.uc.setState(false);
        this.yc.add((MenuItem)(this.ac = new l((Component)this, "Torsion")));
        this.ac.setState(false);
        this.zc.addSeparator();
        this.zc.add((MenuItem)(this.wb = new l((Component)this, "Fast Move")));
        this.zc.add((MenuItem)(this.j = new l((Component)this, "Spin")));
        this.zc.addSeparator();
        this.zc.add(this.n = new k(this, "Reset"));
        this.kb.add(this.cd = new Menu("About"));
        this.cd.add(this.ab = new k(this, "Molecule"));
        this.cd.add(this.r = new k(this, "Chemis3D"));
        this.cd.add(this.q = new k(this, "Quick Help"));
        this.cd.add(this.rb = new k(this, "Full Help ?"));
        if (Chemis3DApp.hc.toLowerCase().indexOf("microsoft") != -1 && Chemis3DApp.gc.compareTo("1.02") > 0 && Chemis3DApp.gc.compareTo("1.1.4") <= 0 && this.cb) {
            (this.ub = a(this)).setMenuBar(this.kb);
            this.ub.pack();
        }
        else {
            if (Chemis3DApp.hc.toLowerCase().indexOf("netscape") != -1) {
                this.gb = 49;
            }
            else if (Chemis3DApp.hc.toLowerCase().indexOf("microsoft") != -1 && Chemis3DApp.gc.compareTo("1.1.4") >= 0) {
                this.gb = 48;
            }
            else {
                this.gb = 68;
            }
            (this.ub = new Frame("Chemis3D")).setMenuBar(this.kb);
            this.ub.pack();
            this.ub.reshape(this.eb, this.db, this.fb, this.gb);
        }
        this.ub.show();
    }
    
    private static Frame a(Component parent) {
        Frame frame = null;
        while (parent != null && frame == null) {
            if (parent instanceof Frame) {
                frame = (Frame)parent;
            }
            else {
                parent = parent.getParent();
            }
        }
        return frame;
    }
    
    private void c(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            if (stringTokenizer.nextToken().equals("in")) {
                this.cb = true;
            }
            else {
                this.cb = false;
            }
            this.eb = Integer.valueOf(stringTokenizer.nextToken());
            this.eb = ((this.eb < 0) ? 0 : this.eb);
            this.db = Integer.valueOf(stringTokenizer.nextToken());
            this.db = ((this.db < 0) ? 0 : this.db);
            this.fb = Integer.valueOf(stringTokenizer.nextToken());
            this.fb = ((this.fb < 200) ? 200 : ((this.fb > 500) ? 500 : this.fb));
        }
        catch (Exception ex) {
            this.cb = false;
            this.eb = 0;
            this.db = 100;
            this.fb = 200;
        }
        if (!this.tb) {
            this.tb = true;
        }
        else {
            this.ub.dispose();
        }
        this.d();
    }
    
    private void d(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("off")) {
                this.j.setState(false);
                return;
            }
            float floatValue = 1.0f;
            this.j.setState(true);
            try {
                floatValue = Float.valueOf(stringTokenizer.nextToken());
            }
            catch (Exception ex) {}
            if (nextToken.equals("y")) {
                this.i = floatValue;
                this.h = 0.0f;
                return;
            }
            if (nextToken.equals("z")) {
                final float n = floatValue;
                this.h = n;
                this.i = n;
                return;
            }
            this.i = 0.0f;
            this.h = floatValue;
        }
        catch (Exception ex2) {
            this.j.setState(false);
            this.i = 0.0f;
            this.h = 1.0f;
        }
    }
    
    private void e(final String s) {
        if (s == null || s.equals("on")) {
            this.wb.setState(true);
            return;
        }
        this.wb.setState(false);
    }
    
    private int e() {
        return (this.jc.getRed() + this.jc.getGreen() + this.jc.getBlue()) / 3;
    }
    
    private void f(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            this.nb = Integer.valueOf(stringTokenizer.nextToken());
            this.nb = ((this.nb > 255) ? 255 : this.nb);
            try {
                this.mb = Integer.valueOf(stringTokenizer.nextToken());
                this.mb = ((this.mb < -15) ? -16 : ((this.mb > 15) ? 16 : this.mb));
                this.lb = Integer.valueOf(stringTokenizer.nextToken());
                this.lb = ((this.lb < -15) ? -16 : ((this.lb > 15) ? 16 : this.lb));
            }
            catch (Exception ex) {
                this.mb = 8;
                this.lb = 8;
            }
        }
        catch (Exception ex2) {
            this.nb = (this.jc.getRed() + this.jc.getGreen() + this.jc.getBlue()) / 3;
            this.mb = 8;
            this.lb = 8;
        }
    }
    
    private void g(final String s) {
        try {
            Label_0154: {
                if (s.startsWith("#")) {
                    this.jc = new Color(Integer.valueOf(s.substring(1), 16));
                    this.ic = null;
                }
                else {
                    if (!s.endsWith(".jpeg") && !s.endsWith(".jpg")) {
                        if (!s.endsWith(".gif")) {
                            this.ic = null;
                            this.jc = Color.black;
                            break Label_0154;
                        }
                    }
                    try {
                        this.ic = this.getImage(this.getDocumentBase(), s);
                        (this.jb = new MediaTracker(this)).addImage(this.ic, 0);
                        try {
                            this.jb.waitForID(0);
                        }
                        catch (InterruptedException ex) {
                            this.showStatus("Interrupted");
                        }
                    }
                    catch (Exception ex2) {
                        this.ic = null;
                    }
                    this.jc = Color.black;
                }
            }
            this.nb = (this.jc.getRed() + this.jc.getGreen() + this.jc.getBlue()) / 3;
        }
        catch (Exception ex3) {
            this.ic = null;
            this.jc = Color.black;
            this.nb = 0;
        }
    }
    
    private void h(final String s) {
        if (s == null) {
            this.dc = 0;
            return;
        }
        if (s.startsWith("group")) {
            this.dc = 1;
            return;
        }
        if (s.startsWith("chain")) {
            this.dc = 2;
            return;
        }
        this.dc = 0;
    }
    
    private void i(final String s) {
        if (s != null) {
            this.m = Float.valueOf(s);
            return;
        }
        this.m = 1.0f;
    }
    
    private void j(final String s) {
        if (s == null || s.equals("ball")) {
            this.xb = 1;
        }
        else if (s.equals("stick")) {
            this.xb = 2;
        }
        else if (s.equals("ball-stick")) {
            this.xb = 3;
        }
        else {
            this.xb = 0;
        }
        this.u = this.xb;
    }
    
    private void k(final String vb) {
        if (vb != null) {
            this.vb = vb;
            return;
        }
        if (this.hb == null) {
            this.vb = "x-mol";
            return;
        }
        if (this.hb.endsWith(".pdb") || this.hb.endsWith(".ent")) {
            this.vb = "x-pdb";
            return;
        }
        if (this.hb.endsWith(".xyz")) {
            this.vb = "x-xyz";
            return;
        }
        this.vb = "x-mol";
    }
    
    private void b(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
            while (stringTokenizer.hasMoreTokens()) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), "=");
                final String nextToken = stringTokenizer2.nextToken();
                if (nextToken.equals("model")) {
                    this.hb = stringTokenizer2.nextToken();
                }
                else if (nextToken.equals("format")) {
                    this.k(stringTokenizer2.nextToken());
                }
                else if (nextToken.equals("style")) {
                    this.j(stringTokenizer2.nextToken());
                }
                else if (nextToken.equals("scale")) {
                    final String nextToken2 = stringTokenizer2.nextToken();
                    if (nextToken2 != null) {
                        this.m = Float.valueOf(nextToken2);
                    }
                    else {
                        this.m = 1.0f;
                    }
                }
                else if (nextToken.equals("color")) {
                    this.h(stringTokenizer2.nextToken());
                }
                else if (nextToken.equals("back")) {
                    this.g(stringTokenizer2.nextToken());
                }
                else if (nextToken.equals("light")) {
                    this.f(stringTokenizer2.nextToken());
                }
                else if (nextToken.equals("fastmove")) {
                    final String nextToken3 = stringTokenizer2.nextToken();
                    if (nextToken3 == null || nextToken3.equals("on")) {
                        this.wb.setState(true);
                    }
                    else {
                        this.wb.setState(false);
                    }
                }
                else if (nextToken.equals("spin")) {
                    this.d(stringTokenizer2.nextToken());
                }
                else {
                    if (!nextToken.equals("setmenu")) {
                        continue;
                    }
                    this.c(stringTokenizer2.nextToken());
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void initialize() {
        this.wc = this.b >> 1;
        this.xc = this.c >> 1;
        this.o = 360.0f / this.b;
        this.p = 360.0f / this.c;
        this.resize((this.b <= 100) ? 250 : this.b, (this.c <= 100) ? 250 : this.c);
        if (this.pb != null) {
            this.b(this.pb);
            if (this.vb == null) {
                this.k(this.vb);
            }
            if (!this.tb) {
                this.d();
            }
        }
        else {
            this.hb = this.getParameter("model");
            this.vb = this.getParameter("format");
            if (this.vb == null) {
                this.vb = this.getParameter("filetype");
            }
            this.k(this.vb);
            String s = this.getParameter("style");
            if (s == null) {
                s = this.getParameter("display");
            }
            this.j(s);
            final String parameter = this.getParameter("scale");
            if (parameter != null) {
                this.m = Float.valueOf(parameter);
            }
            else {
                this.m = 1.0f;
            }
            this.g(this.getParameter("back"));
            this.h(this.getParameter("color"));
            this.f(this.getParameter("light"));
            this.c(this.getParameter("setmenu"));
            final String parameter2 = this.getParameter("fastmove");
            if (parameter2 == null || parameter2.equals("on")) {
                this.wb.setState(true);
            }
            else {
                this.wb.setState(false);
            }
            this.d(this.getParameter("spin"));
        }
        this.backBuffer();
    }
    
    public void initBean(final String pb, final int b, final int c) {
        this.pb = pb;
        this.b = b;
        this.c = c;
        this.initialize();
    }
    
    public void init() {
        this.pb = this.getParameter("inline");
        this.b = this.size().width;
        this.c = this.size().height;
        this.initialize();
    }
}
