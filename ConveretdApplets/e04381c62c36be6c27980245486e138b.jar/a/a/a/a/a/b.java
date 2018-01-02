// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.a;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.util.StringTokenizer;
import java.awt.image.CropImageFilter;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import a.a.a.a.b.g;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.applet.Applet;
import java.awt.LayoutManager;
import java.awt.image.ImageFilter;
import java.applet.AppletContext;
import java.util.Vector;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Color;
import a.a.a.a.e.a;
import a.a.a.a.c.a.c;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Container;

public abstract class b extends Container implements ActionListener, ItemListener, c
{
    protected a W;
    protected String aE;
    public boolean H;
    protected volatile int aa;
    private Thread ah;
    private volatile boolean ak;
    private int ab;
    private boolean ap;
    private Object af;
    private Thread ad;
    private volatile int aj;
    private int ag;
    protected String az;
    protected String G;
    protected volatile boolean Q;
    protected volatile boolean T;
    protected boolean ao;
    protected boolean R;
    protected boolean aD;
    protected boolean S;
    protected int as;
    protected int ar;
    protected int L;
    protected int K;
    protected int aC;
    protected int aB;
    protected Color al;
    protected Color ae;
    protected static String aq;
    protected Dimension ai;
    protected Image av;
    protected boolean an;
    protected Vector ax;
    protected a.a.a.a.c.a.a aw;
    protected AppletContext appContext;
    private boolean Y;
    public String[] M;
    public String[] J;
    public Image[] U;
    public Image[] I;
    public Image[] P;
    public a.a.a.a.b.a[] Z;
    private ImageFilter O;
    private int aA;
    private int V;
    private int X;
    private int ay;
    private int au;
    private int at;
    private int am;
    private int N;
    private boolean ac;
    
    public b(final a w, final String ae) {
        this.W = null;
        this.aE = null;
        this.H = false;
        this.aa = 0;
        this.af = new Object();
        this.ag = 15;
        this.Q = false;
        this.aD = false;
        this.S = false;
        this.av = null;
        this.an = false;
        this.Y = false;
        this.X = 0;
        this.setLayout(null);
        this.W = w;
        this.aE = ae;
        (this.aw = (a.a.a.a.c.a.a)this.W.byte("statemachine")).if(this);
        this.appContext = ((Applet)this.W.byte("applet")).getAppletContext();
        this.enableEvents(56L);
    }
    
    public void case(final String s) {
        if (s != null) {
            if (s.equalsIgnoreCase("on")) {
                this.an = true;
            }
            else {
                this.an = false;
            }
        }
    }
    
    static /* synthetic */ void access$4(final b b, final int ab) {
        b.ab = ab;
    }
    
    static /* synthetic */ void access$6(final b b, final int aj) {
        b.aj = aj;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        a.a.a.a.b.a a = null;
        if (actionCommand.equals(this.W.a("resource/menuitem.zoomin"))) {
            a = (a.a.a.a.b.a)this.W.byte("command/action/zoomin");
        }
        else if (actionCommand.equals(this.W.a("resource/menuitem.zoomout"))) {
            a = (a.a.a.a.b.a)this.W.byte("command/action/zoomout");
        }
        else if (actionCommand.equals(this.W.a("resource/menuitem.reset"))) {
            a = (a.a.a.a.b.a)this.W.byte("command/action/reset");
        }
        else if (actionCommand.equals(this.W.a("resource/menuitem.rotateleft"))) {
            a = (a.a.a.a.b.a)this.W.byte("command/action/rotateleft");
        }
        else if (actionCommand.equals(this.W.a("resource/menuitem.pause"))) {
            a = (a.a.a.a.b.a)this.W.byte("command/action/pause");
        }
        else if (actionCommand.equals(this.W.a("resource/menuitem.rotateright"))) {
            a = (a.a.a.a.b.a)this.W.byte("command/action/rotateright");
        }
        else if (actionCommand.equals(this.W.a("resource/menuitem.info"))) {
            a = (a.a.a.a.b.a)this.W.byte("command/action/info");
        }
        if (a != null) {
            a.a();
        }
    }
    
    public void new(final String s) {
        if (this.Y) {
            this.aw.for(1);
        }
        else {
            this.aw.for(0);
        }
    }
    
    protected void j() {
        this.appContext.showStatus("                                                                                                   ");
    }
    
    public void i() {
        if (this.ax != null) {
            this.ax.removeAllElements();
            this.ax = null;
        }
        this.s();
        this.removeAll();
    }
    
    protected abstract void h();
    
    protected String int(final String s) {
        return this.W.a("resource/error." + s);
    }
    
    public void r() {
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final a.a.a.a.b.a byte1 = ((g)itemEvent.getItemSelectable()).byte();
        if (byte1 != null) {
            byte1.a();
        }
    }
    
    protected abstract void l();
    
    public void paint(final Graphics graphics) {
        if (this.Q) {
            this.s();
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.ai.width, this.ai.height);
            graphics.setColor(Color.red);
            graphics.drawString(this.az, (this.ai.width >> 1) - (fontMetrics.stringWidth(this.az) >> 1), this.ai.height >> 1);
            if (this.G != null) {
                graphics.drawString(this.G, (this.ai.width >> 1) - (fontMetrics.stringWidth(this.G) >> 1), (this.ai.height >> 1) + fontMetrics.getHeight());
            }
        }
        else if (this.av == null && this.ai != null) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.ai.width, this.ai.height);
            graphics.setColor(Color.green);
            final String s = "loading ...";
            final FontMetrics fontMetrics2 = graphics.getFontMetrics();
            graphics.drawString(s, (this.ai.width >> 1) - (fontMetrics2.stringWidth(s) >> 1), (this.ai.height >> 1) - this.ag - (fontMetrics2.getHeight() + 2));
            graphics.drawOval((this.ai.width >> 1) - this.ag, (this.ai.height >> 1) - this.ag, this.ag << 1, this.ag << 1);
            graphics.fillArc((this.ai.width >> 1) - this.ag, (this.ai.height >> 1) - this.ag, this.ag << 1, this.ag << 1, 90, -this.aj);
        }
        else {
            if (this.av != null) {
                synchronized (this.av) {
                    graphics.drawImage(this.av, 0, 0, this);
                    if (this.H && !this.aw.goto) {
                        this.a(graphics, this.au, this.at);
                    }
                    else {
                        this.aw.for();
                        this.H = false;
                    }
                }
                // monitorexit(this.av)
            }
            super.paint(graphics);
        }
    }
    
    public void k() {
        if (this.ah != null) {
            this.a(true);
        }
    }
    
    protected abstract void e();
    
    public void q() {
        this.ai = this.getSize();
        this.f();
        this.ao = false;
        final boolean b = false;
        this.aB = (b ? 1 : 0);
        this.aC = (b ? 1 : 0);
        this.K = (b ? 1 : 0);
        this.L = (b ? 1 : 0);
        this.ar = (b ? 1 : 0);
        this.as = (b ? 1 : 0);
        this.al = (Color)this.W.byte("param/backgroundColor");
        this.an = (boolean)this.W.byte("param/enableZoomPastMax");
        this.Y = (boolean)this.W.byte("param/showHotspots");
        this.ae = (Color)this.W.byte("param/hotspotColor");
        final Vector vector = new Vector<String>();
        final Vector vector2 = new Vector<String>();
        final Vector vector3 = new Vector<Image>();
        final Vector vector4 = new Vector<Image>();
        final Vector vector5 = new Vector<Image>();
        final Vector vector6 = new Vector<Object>();
        this.aA = this.W.case("resource/button.height");
        this.V = this.W.case("resource/button.width");
        this.O = new CropImageFilter(0, 0, this.V, this.aA);
        final StringTokenizer stringTokenizer = new StringTokenizer(this.W.a("resource/" + this.aE + ".toolbar.buttons"), ":");
        final Graphics graphics = this.getGraphics();
        this.aw = (a.a.a.a.c.a.a)this.W.byte("statemachine");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (!trim.equals("separator")) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(trim, ",");
                final String trim2 = stringTokenizer2.nextToken().trim();
                final String trim3 = stringTokenizer2.nextToken().trim();
                stringTokenizer2.nextToken().trim();
                vector2.addElement(trim2);
                int x;
                if (trim3.equals("state")) {
                    vector.addElement(this.W.a("resource/menuitem.checkbox." + trim2));
                    x = graphics.getFontMetrics().stringWidth(this.W.a("resource/menuitem.checkbox." + trim2));
                    vector6.addElement(this.W.byte("command/state/" + trim2));
                    if (!trim2.equals("magnifier")) {
                        vector5.addElement(this.createImage(new FilteredImageSource(this.W.do("images/button/" + trim2 + ".gray").getSource(), this.O)));
                    }
                }
                else {
                    vector.addElement(this.W.a("resource/menuitem." + trim2));
                    x = graphics.getFontMetrics().stringWidth(this.W.a("resource/menuitem." + trim2));
                    vector6.addElement(this.W.byte("command/action/" + trim2));
                    vector5.addElement(this.createImage(new FilteredImageSource(this.W.do("images/button/" + trim2 + ".up").getSource(), this.O)));
                }
                Image image;
                if (trim2.equals("hotspots") && this.W.a("param/hotspot1") == null) {
                    image = this.W.do("images/button/" + trim2 + ".gray");
                }
                else {
                    image = this.W.do("images/button/" + trim2 + ".up");
                }
                vector3.addElement(this.createImage(new FilteredImageSource(image.getSource(), this.O)));
                vector4.addElement(this.createImage(new FilteredImageSource(this.W.do("images/button/" + trim2 + ".down").getSource(), this.O)));
                if (x <= this.X) {
                    continue;
                }
                this.X = x;
            }
        }
        vector2.copyInto(this.M = new String[vector2.size()]);
        vector.copyInto(this.J = new String[vector.size()]);
        vector3.copyInto(this.U = new Image[vector3.size()]);
        vector4.copyInto(this.I = new Image[vector4.size()]);
        vector5.copyInto(this.P = new Image[vector5.size()]);
        vector6.copyInto(this.Z = new a.a.a.a.b.a[vector6.size()]);
        final Point point = (Point)this.W.byte("param/logopos");
        if (point == null) {
            this.au = 0;
            this.am = this.V + 10;
            this.N = this.au;
            this.at = this.size().height - this.J.length * this.aA;
        }
        else {
            if (point.x < Math.round(this.size().width / 2)) {
                this.au = 0;
                this.am = this.V + 10;
                this.N = this.au;
            }
            else {
                this.au = this.size().width - this.X - this.V - 20;
                this.am = this.au + 10;
                this.N = this.size().width - this.V;
            }
            if (point.y < Math.round(this.size().height / 2)) {
                this.at = this.aA;
            }
            else {
                this.at = this.size().height - (this.J.length + 1) * this.aA;
            }
        }
        this.aw.for(-1);
        int n = 1;
        while (true) {
            final String a = this.W.a("param/hotspot" + n);
            if (a == null) {
                break;
            }
            if (this.ax == null) {
                this.ax = new Vector();
            }
            this.new(a);
            ++n;
        }
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        Label_0288: {
            switch (keyEvent.getID()) {
                case 401: {
                    switch (keyEvent.getKeyCode()) {
                        case 33:
                        case 65: {
                            ((a.a.a.a.b.a)this.W.byte("command/action/zoomin")).a();
                            break Label_0288;
                        }
                        case 34:
                        case 90: {
                            ((a.a.a.a.b.a)this.W.byte("command/action/zoomout")).a();
                            break Label_0288;
                        }
                        case 86: {
                            ((a.a.a.a.b.a)this.W.byte("command/action/reset")).a();
                            break Label_0288;
                        }
                        case 73:
                        case 112: {
                            ((a.a.a.a.b.a)this.W.byte("command/action/info")).a();
                            break Label_0288;
                        }
                        case 77: {
                            ((a.a.a.a.b.a)this.W.byte("command/state/magnifier")).a();
                            break Label_0288;
                        }
                        case 84: {
                            ((a.a.a.a.b.a)this.W.byte("command/state/toolbar")).a();
                            break Label_0288;
                        }
                        case 72: {
                            ((a.a.a.a.b.a)this.W.byte("command/state/hotspots")).a();
                            break Label_0288;
                        }
                    }
                    break;
                }
                case 400: {
                    this.R = true;
                    break;
                }
                case 402: {
                    this.R = false;
                    this.f();
                    break;
                }
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger() && !this.H) {
            this.ao = true;
            this.H = true;
            this.as = mouseEvent.getX();
            this.ar = mouseEvent.getY();
            this.setCursor((Cursor)this.W.byte("cursor/default"));
        }
        else {
            switch (mouseEvent.getID()) {
                case 501: {
                    if (!this.H) {
                        this.ao = true;
                        final int x = mouseEvent.getX();
                        this.as = x;
                        this.aC = x;
                        this.L = x;
                        final int y = mouseEvent.getY();
                        this.ar = y;
                        this.aB = y;
                        this.K = y;
                        break;
                    }
                    if (this.ay == -1) {
                        this.ao = true;
                        break;
                    }
                    break;
                }
                case 502: {
                    this.ao = false;
                    this.aC = mouseEvent.getX();
                    this.aB = mouseEvent.getY();
                    this.f();
                    this.setCursor((Cursor)this.W.byte("cursor/default"));
                    if (this.H) {
                        if (this.ay >= 0) {
                            this.Z[this.ay].a();
                        }
                        this.H = false;
                        break;
                    }
                    break;
                }
                case 505: {
                    this.f();
                    break;
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                this.aC = mouseEvent.getX();
                this.aB = mouseEvent.getY();
                this.f();
                break;
            }
            case 503: {
                final int x = mouseEvent.getX();
                this.aC = x;
                this.L = x;
                final int y = mouseEvent.getY();
                this.aB = y;
                this.K = y;
                if (this.H && this.K > this.at && this.K < this.at + this.J.length * this.aA && this.L > this.au && this.L < this.au + this.X + this.V + 20 && Math.round((this.K - this.at) / this.aA) != this.ay) {
                    this.repaint();
                    break;
                }
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    protected abstract void c();
    
    public void o() {
        this.f();
        this.requestFocus();
    }
    
    public void u() {
        this.a(false);
    }
    
    public abstract void w();
    
    public abstract void v();
    
    public abstract void b();
    
    public abstract boolean g();
    
    public void f() {
        this.T = true;
    }
    
    private void a(final boolean ap) {
        synchronized (this.af) {
            if (this.ap != ap) {
                this.ap = ap;
                this.af.notifyAll();
            }
        }
        // monitorexit(this.af)
    }
    
    public void byte(final String s) {
        final int[] array = { 255, 255, 255 };
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        int i;
        try {
            for (i = 0; i < 3; ++i) {
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }
                array[i] = Integer.parseInt(stringTokenizer.nextToken().trim());
            }
        }
        catch (NumberFormatException ex) {
            return;
        }
        if (i < 3) {
            return;
        }
        this.al = new Color(array[0], array[1], array[2]);
        this.f();
    }
    
    protected void try(final String s) {
        this.a(s, null);
    }
    
    protected void a(final String az, final String g) {
        if (this.Q) {
            return;
        }
        this.az = az;
        this.G = g;
        this.Q = true;
        this.repaint();
    }
    
    protected void a(final Graphics graphics, final int n, final int n2) {
        boolean b = false;
        final int round = Math.round((this.K - n2) / this.aA);
        final int n3 = round * this.aA + n2 + 1;
        graphics.setColor(new Color(204, 204, 204));
        graphics.fill3DRect(n, n2 - 1, this.X + this.V + 20, this.J.length * this.aA + 3, true);
        if (this.K > n2 && this.K < n2 + this.J.length * this.aA && this.L > n && this.L < n + this.X + this.V + 20) {
            graphics.setColor(new Color(10, 36, 102));
            graphics.fill3DRect(n, n3, this.X + this.V + 20, this.aA, true);
            b = true;
            this.ay = round;
        }
        else {
            this.ay = -1;
        }
        for (int i = 0; i < this.J.length; ++i) {
            if (i == round && b) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            if (this.ax == null && this.M[i].equals("hotspots")) {
                if (i != round || this.ay == -1) {
                    graphics.setColor(Color.white);
                    graphics.drawString(this.J[i], this.am + 1, 20 + this.aA * i + n2);
                }
                graphics.setColor(new Color(128, 128, 128));
                graphics.drawString(this.J[i], this.am, 19 + this.aA * i + n2);
            }
            else {
                graphics.drawString(this.J[i], this.am, 19 + this.aA * i + n2);
            }
            if (this.aw.a(this.M[i]) == 1) {
                graphics.drawImage(this.I[i], this.N, this.aA * i + n2, this);
            }
            else {
                graphics.drawImage(this.U[i], this.N, this.aA * i + n2, this);
            }
        }
    }
    
    public void t() {
        final a.a.a.a.d.a a = (a.a.a.a.d.a)this.W.byte("applet");
        a.if("player start()");
        if (this.ah == null) {
            this.d();
            a.for("player start2()");
            this.ak = false;
            this.ab = 10;
            (this.ah = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (!b.this.ak) {
                            synchronized (b.this.af) {
                                while (b.this.ap) {
                                    b.this.af.wait();
                                }
                            }
                            // monitorexit(b.access$1(this.this$0))
                            if (!b.this.Q && b.this.g()) {
                                b.access$4(b.this, 10);
                                Thread.yield();
                            }
                            else {
                                Thread.sleep(b.this.ab);
                                if (b.this.ab >= 75) {
                                    continue;
                                }
                                final b this$0 = b.this;
                                b.access$4(this$0, this$0.ab + 5);
                            }
                        }
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Player")).start();
            (this.ad = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (b.this.av == null) {
                            b.access$6(b.this, (b.this.aj + 12) % 360);
                            Thread.sleep(200L);
                            b.this.repaint();
                            b.this.requestFocus();
                        }
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "progress tracker")).start();
            a.for("launch progress tracking thread");
        }
        else {
            this.repaint();
            this.requestFocus();
        }
        a.for("launch player thread");
    }
    
    public void d() {
        final a.a.a.a.d.a a = (a.a.a.a.d.a)this.W.byte("applet");
        this.q();
        if (this.Q) {
            return;
        }
        this.r();
        if (this.Q) {
            return;
        }
        a.for("initialize player");
        this.e();
        if (this.Q) {
            return;
        }
        a.for("player preload image");
        this.o();
        this.requestFocus();
        a.for("reset player");
    }
    
    public void a(final a.a.a.a.c.a.b b) {
        this.f();
        this.repaint();
    }
    
    public void s() {
        if (this.ah != null) {
            this.ak = true;
            this.ah.interrupt();
            this.ah = null;
            this.m();
        }
    }
    
    public void m() {
        this.h();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public abstract void p();
    
    public abstract void n();
}
