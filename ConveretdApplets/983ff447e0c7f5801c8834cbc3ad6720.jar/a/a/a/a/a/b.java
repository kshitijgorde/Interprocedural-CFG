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
import a.a.a.a.b.g;
import java.awt.event.ItemEvent;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.MediaTracker;
import java.awt.Component;
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
    protected a X;
    protected String aJ;
    public boolean H;
    protected volatile int ac;
    private Thread ak;
    private volatile boolean an;
    private int ad;
    private boolean at;
    private Object ai;
    private Thread af;
    private volatile int am;
    private int aj;
    protected String aE;
    protected String G;
    protected volatile boolean Q;
    protected volatile boolean U;
    protected boolean as;
    protected boolean R;
    protected boolean aI;
    protected boolean T;
    protected int aw;
    protected int av;
    protected int L;
    protected int K;
    protected int aH;
    protected int aG;
    protected Color ao;
    protected Color ah;
    protected Color Y;
    protected Color aB;
    protected static String au;
    protected Dimension al;
    protected Image az;
    protected boolean ar;
    protected Vector aC;
    protected a.a.a.a.c.a.a aA;
    protected AppletContext appContext;
    private boolean aa;
    public String[] M;
    public String[] J;
    public Image[] V;
    public Image[] I;
    public Image[] P;
    public a.a.a.a.b.a[] ab;
    private ImageFilter O;
    private int aF;
    private int W;
    private int Z;
    private int aD;
    private int ay;
    private int ax;
    private int aq;
    private int N;
    private boolean ae;
    protected String S;
    protected String ap;
    protected boolean ag;
    
    public b(final a x, final String aj) {
        this.X = null;
        this.aJ = null;
        this.H = false;
        this.ac = 0;
        this.ai = new Object();
        this.aj = 15;
        this.Q = false;
        this.aI = false;
        this.T = false;
        this.az = null;
        this.ar = false;
        this.aa = false;
        this.Z = 0;
        this.S = null;
        this.ap = "splashscreen.gif";
        this.ag = true;
        this.setLayout(null);
        this.X = x;
        this.aJ = aj;
        (this.aA = (a.a.a.a.c.a.a)this.X.byte("statemachine")).if(this);
        this.appContext = ((Applet)this.X.byte("applet")).getAppletContext();
        this.enableEvents(56L);
    }
    
    public void case(final String s) {
        if (s != null) {
            if (s.equalsIgnoreCase("on")) {
                this.ar = true;
            }
            else {
                this.ar = false;
            }
        }
    }
    
    private boolean a(final Image image, final Component component) {
        if (image == null) {
            return false;
        }
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(image, 1);
        try {
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex) {
            return false;
        }
        return !mediaTracker.isErrorAny();
    }
    
    static /* synthetic */ void access$4(final b b, final int ad) {
        b.ad = ad;
    }
    
    static /* synthetic */ void access$6(final b b, final int am) {
        b.am = am;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        a.a.a.a.b.a a = null;
        if (actionCommand.equals(this.X.a("resource/menuitem.zoomin"))) {
            a = (a.a.a.a.b.a)this.X.byte("command/action/zoomin");
        }
        else if (actionCommand.equals(this.X.a("resource/menuitem.zoomout"))) {
            a = (a.a.a.a.b.a)this.X.byte("command/action/zoomout");
        }
        else if (actionCommand.equals(this.X.a("resource/menuitem.reset"))) {
            a = (a.a.a.a.b.a)this.X.byte("command/action/reset");
        }
        else if (actionCommand.equals(this.X.a("resource/menuitem.rotateleft"))) {
            a = (a.a.a.a.b.a)this.X.byte("command/action/rotateleft");
        }
        else if (actionCommand.equals(this.X.a("resource/menuitem.pause"))) {
            a = (a.a.a.a.b.a)this.X.byte("command/action/pause");
        }
        else if (actionCommand.equals(this.X.a("resource/menuitem.rotateright"))) {
            a = (a.a.a.a.b.a)this.X.byte("command/action/rotateright");
        }
        else if (actionCommand.equals(this.X.a("resource/menuitem.info"))) {
            a = (a.a.a.a.b.a)this.X.byte("command/action/info");
        }
        if (a != null) {
            a.a();
        }
    }
    
    public void new(final String s) {
        if (this.aa) {
            this.aA.for(1);
        }
        else {
            this.aA.for(0);
        }
    }
    
    protected void k() {
        this.appContext.showStatus("                                                                                                   ");
    }
    
    private void a(final Graphics graphics) {
        final int width = this.getSize().width;
        final int n = this.getSize().height - this.aF;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, n);
        if (this.S != null && this.S.length() != 0) {
            try {
                final Image image = Toolkit.getDefaultToolkit().getImage(new URL((URL)this.X.byte("param/documentbase"), this.S));
                if (!this.a(image, this)) {
                    return;
                }
                final int height = image.getHeight(this);
                final int width2 = image.getWidth(this);
                if (width2 > 0 && height > 0) {
                    graphics.drawImage(image, (width >> 1) - (width2 >> 1), (n >> 1) - (height >> 1), this);
                }
                return;
            }
            catch (MalformedURLException ex) {
                return;
            }
        }
        this.a(graphics, width, n);
    }
    
    public void j() {
        if (this.aC != null) {
            this.aC.removeAllElements();
            this.aC = null;
        }
        this.t();
        this.removeAll();
    }
    
    protected abstract void i();
    
    private void a(final Graphics graphics, final int n, final int n2) {
        Image image = null;
        try {
            final InputStream a = a.a(this.X.a("resource/image.splash"));
            final byte[] array = new byte[32768];
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = a.read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            image = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
        }
        catch (Throwable t) {}
        if (this.a(image, this)) {
            final int height = image.getHeight(this);
            final int width = image.getWidth(this);
            if (width > 0 && height > 0) {
                graphics.drawImage(image, (n >> 1) - (width >> 1), (n2 >> 1) - (height >> 1), this);
            }
        }
    }
    
    protected String int(final String s) {
        return this.X.a("resource/error." + s);
    }
    
    public void s() {
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final a.a.a.a.b.a byte1 = ((g)itemEvent.getItemSelectable()).byte();
        if (byte1 != null) {
            byte1.a();
        }
    }
    
    protected abstract void m();
    
    public void paint(final Graphics graphics) {
        if (this.Q) {
            this.t();
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.al.width, this.al.height);
            graphics.setColor(Color.red);
            graphics.drawString(this.aE, (this.al.width >> 1) - (fontMetrics.stringWidth(this.aE) >> 1), this.al.height >> 1);
            if (this.G != null) {
                graphics.drawString(this.G, (this.al.width >> 1) - (fontMetrics.stringWidth(this.G) >> 1), (this.al.height >> 1) + fontMetrics.getHeight());
            }
        }
        else if (this.az == null && this.al != null) {
            if (this.ag && this.S != null) {
                this.a(graphics);
            }
            else {
                graphics.setColor(this.aB);
                graphics.fillRect(0, 0, this.al.width, this.al.height);
                graphics.setColor(this.Y);
                final String s = "loading ...";
                final FontMetrics fontMetrics2 = graphics.getFontMetrics();
                graphics.drawString(s, (this.al.width >> 1) - (fontMetrics2.stringWidth(s) >> 1), (this.al.height >> 1) - this.aj - (fontMetrics2.getHeight() + 2));
                graphics.drawOval((this.al.width >> 1) - this.aj, (this.al.height >> 1) - this.aj, this.aj << 1, this.aj << 1);
                graphics.fillArc((this.al.width >> 1) - this.aj, (this.al.height >> 1) - this.aj, this.aj << 1, this.aj << 1, 90, -this.am);
            }
        }
        else {
            if (this.az != null) {
                synchronized (this.az) {
                    graphics.drawImage(this.az, 0, 0, this);
                    if (this.H && !this.aA.goto) {
                        this.if(graphics, this.ay, this.ax);
                    }
                    else {
                        this.aA.for();
                        this.H = false;
                    }
                }
                // monitorexit(this.az)
            }
            super.paint(graphics);
        }
    }
    
    public void l() {
        if (this.ak != null) {
            this.a(true);
        }
    }
    
    protected abstract void f();
    
    public void r() {
        this.al = this.getSize();
        this.g();
        this.as = false;
        final boolean b = false;
        this.aG = (b ? 1 : 0);
        this.aH = (b ? 1 : 0);
        this.K = (b ? 1 : 0);
        this.L = (b ? 1 : 0);
        this.av = (b ? 1 : 0);
        this.aw = (b ? 1 : 0);
        this.ao = (Color)this.X.byte("param/backgroundColor");
        this.ar = (boolean)this.X.byte("param/enableZoomPastMax");
        this.aa = (boolean)this.X.byte("param/showHotspots");
        this.ah = (Color)this.X.byte("param/hotspotColor");
        this.S = (String)this.X.byte("param/altsplash");
        this.Y = (Color)this.X.byte("param/loadingBarColor");
        this.aB = (Color)this.X.byte("param/loadingBkgdColor");
        final Vector vector = new Vector<String>();
        final Vector vector2 = new Vector<String>();
        final Vector vector3 = new Vector<Image>();
        final Vector vector4 = new Vector<Image>();
        final Vector vector5 = new Vector<Image>();
        final Vector vector6 = new Vector<Object>();
        this.aF = this.X.case("resource/button.height");
        this.W = this.X.case("resource/button.width");
        this.O = new CropImageFilter(0, 0, this.W, this.aF);
        final StringTokenizer stringTokenizer = new StringTokenizer(this.X.a("resource/" + this.aJ + ".toolbar.buttons"), ":");
        final Graphics graphics = this.getGraphics();
        this.aA = (a.a.a.a.c.a.a)this.X.byte("statemachine");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (!trim.equals("separator")) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(trim, ",");
                final String trim2 = stringTokenizer2.nextToken().trim();
                final String trim3 = stringTokenizer2.nextToken().trim();
                stringTokenizer2.nextToken().trim();
                vector2.addElement(trim2);
                int z;
                if (trim3.equals("state")) {
                    vector.addElement(this.X.a("resource/menuitem.checkbox." + trim2));
                    z = graphics.getFontMetrics().stringWidth(this.X.a("resource/menuitem.checkbox." + trim2));
                    vector6.addElement(this.X.byte("command/state/" + trim2));
                    if (!trim2.equals("magnifier")) {
                        vector5.addElement(this.createImage(new FilteredImageSource(this.X.do("images/button/" + trim2 + ".gray").getSource(), this.O)));
                    }
                }
                else {
                    vector.addElement(this.X.a("resource/menuitem." + trim2));
                    z = graphics.getFontMetrics().stringWidth(this.X.a("resource/menuitem." + trim2));
                    vector6.addElement(this.X.byte("command/action/" + trim2));
                    vector5.addElement(this.createImage(new FilteredImageSource(this.X.do("images/button/" + trim2 + ".up").getSource(), this.O)));
                }
                Image image;
                if (trim2.equals("hotspots") && this.X.a("param/hotspot1") == null) {
                    image = this.X.do("images/button/" + trim2 + ".gray");
                }
                else {
                    image = this.X.do("images/button/" + trim2 + ".up");
                }
                vector3.addElement(this.createImage(new FilteredImageSource(image.getSource(), this.O)));
                vector4.addElement(this.createImage(new FilteredImageSource(this.X.do("images/button/" + trim2 + ".down").getSource(), this.O)));
                if (z <= this.Z) {
                    continue;
                }
                this.Z = z;
            }
        }
        vector2.copyInto(this.M = new String[vector2.size()]);
        vector.copyInto(this.J = new String[vector.size()]);
        vector3.copyInto(this.V = new Image[vector3.size()]);
        vector4.copyInto(this.I = new Image[vector4.size()]);
        vector5.copyInto(this.P = new Image[vector5.size()]);
        vector6.copyInto(this.ab = new a.a.a.a.b.a[vector6.size()]);
        final Point point = (Point)this.X.byte("param/logopos");
        if (point == null) {
            this.ay = 0;
            this.aq = this.W + 10;
            this.N = this.ay;
            this.ax = this.size().height - this.J.length * this.aF;
        }
        else {
            if (point.x < Math.round(this.size().width / 2)) {
                this.ay = 0;
                this.aq = this.W + 10;
                this.N = this.ay;
            }
            else {
                this.ay = this.size().width - this.Z - this.W - 20;
                this.aq = this.ay + 10;
                this.N = this.size().width - this.W;
            }
            if (point.y < Math.round(this.size().height / 2)) {
                this.ax = this.aF;
            }
            else {
                this.ax = this.size().height - (this.J.length + 1) * this.aF;
            }
        }
        this.aA.for(-1);
        int n = 1;
        while (true) {
            final String a = this.X.a("param/hotspot" + n);
            if (a == null) {
                break;
            }
            if (this.aC == null) {
                this.aC = new Vector();
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
                            ((a.a.a.a.b.a)this.X.byte("command/action/zoomin")).a();
                            break Label_0288;
                        }
                        case 34:
                        case 90: {
                            ((a.a.a.a.b.a)this.X.byte("command/action/zoomout")).a();
                            break Label_0288;
                        }
                        case 86: {
                            ((a.a.a.a.b.a)this.X.byte("command/action/reset")).a();
                            break Label_0288;
                        }
                        case 73:
                        case 112: {
                            ((a.a.a.a.b.a)this.X.byte("command/action/info")).a();
                            break Label_0288;
                        }
                        case 77: {
                            ((a.a.a.a.b.a)this.X.byte("command/state/magnifier")).a();
                            break Label_0288;
                        }
                        case 84: {
                            ((a.a.a.a.b.a)this.X.byte("command/state/toolbar")).a();
                            break Label_0288;
                        }
                        case 72: {
                            ((a.a.a.a.b.a)this.X.byte("command/state/hotspots")).a();
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
                    this.g();
                    break;
                }
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger() && !this.H) {
            this.as = true;
            this.H = true;
            this.aw = mouseEvent.getX();
            this.av = mouseEvent.getY();
            this.setCursor((Cursor)this.X.byte("cursor/default"));
        }
        else {
            switch (mouseEvent.getID()) {
                case 501: {
                    if (!this.H) {
                        this.as = true;
                        final int x = mouseEvent.getX();
                        this.aw = x;
                        this.aH = x;
                        this.L = x;
                        final int y = mouseEvent.getY();
                        this.av = y;
                        this.aG = y;
                        this.K = y;
                        break;
                    }
                    if (this.aD == -1) {
                        this.as = true;
                        break;
                    }
                    break;
                }
                case 502: {
                    this.as = false;
                    this.aH = mouseEvent.getX();
                    this.aG = mouseEvent.getY();
                    this.g();
                    this.setCursor((Cursor)this.X.byte("cursor/default"));
                    if (this.H) {
                        if (this.aD >= 0) {
                            this.ab[this.aD].a();
                        }
                        this.H = false;
                        break;
                    }
                    break;
                }
                case 505: {
                    this.g();
                    break;
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                this.aH = mouseEvent.getX();
                this.aG = mouseEvent.getY();
                this.g();
                break;
            }
            case 503: {
                final int x = mouseEvent.getX();
                this.aH = x;
                this.L = x;
                final int y = mouseEvent.getY();
                this.aG = y;
                this.K = y;
                if (this.H && this.K > this.ax && this.K < this.ax + this.J.length * this.aF && this.L > this.ay && this.L < this.ay + this.Z + this.W + 20 && Math.round((this.K - this.ax) / this.aF) != this.aD) {
                    this.repaint();
                    break;
                }
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    protected abstract void d();
    
    public void p() {
        this.g();
        this.requestFocus();
    }
    
    public void v() {
        this.a(false);
    }
    
    public abstract void x();
    
    public abstract void w();
    
    public abstract void c();
    
    public abstract boolean h();
    
    public void g() {
        this.U = true;
    }
    
    private void a(final boolean at) {
        synchronized (this.ai) {
            if (this.at != at) {
                this.at = at;
                this.ai.notifyAll();
            }
        }
        // monitorexit(this.ai)
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
        this.ao = new Color(array[0], array[1], array[2]);
        this.g();
    }
    
    protected void try(final String s) {
        this.a(s, null);
    }
    
    protected void a(final String ae, final String g) {
        if (this.Q) {
            return;
        }
        this.aE = ae;
        this.G = g;
        this.Q = true;
        this.repaint();
    }
    
    protected void if(final Graphics graphics, final int n, final int n2) {
        boolean b = false;
        final int round = Math.round((this.K - n2) / this.aF);
        final int n3 = round * this.aF + n2 + 1;
        graphics.setColor(new Color(204, 204, 204));
        graphics.fill3DRect(n, n2 - 1, this.Z + this.W + 20, this.J.length * this.aF + 3, true);
        if (this.K > n2 && this.K < n2 + this.J.length * this.aF && this.L > n && this.L < n + this.Z + this.W + 20) {
            graphics.setColor(new Color(10, 36, 102));
            graphics.fill3DRect(n, n3, this.Z + this.W + 20, this.aF, true);
            b = true;
            this.aD = round;
        }
        else {
            this.aD = -1;
        }
        for (int i = 0; i < this.J.length; ++i) {
            if (i == round && b) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            if (this.aC == null && this.M[i].equals("hotspots")) {
                if (i != round || this.aD == -1) {
                    graphics.setColor(Color.white);
                    graphics.drawString(this.J[i], this.aq + 1, 20 + this.aF * i + n2);
                }
                graphics.setColor(new Color(128, 128, 128));
                graphics.drawString(this.J[i], this.aq, 19 + this.aF * i + n2);
            }
            else {
                graphics.drawString(this.J[i], this.aq, 19 + this.aF * i + n2);
            }
            if (this.aA.a(this.M[i]) == 1) {
                graphics.drawImage(this.I[i], this.N, this.aF * i + n2, this);
            }
            else {
                graphics.drawImage(this.V[i], this.N, this.aF * i + n2, this);
            }
        }
    }
    
    public void u() {
        final a.a.a.a.d.a a = (a.a.a.a.d.a)this.X.byte("applet");
        a.if("player start()");
        if (this.ak == null) {
            this.e();
            a.for("player start2()");
            this.an = false;
            this.ad = 10;
            (this.ak = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (!b.this.an) {
                            synchronized (b.this.ai) {
                                while (b.this.at) {
                                    b.this.ai.wait();
                                }
                            }
                            // monitorexit(b.access$1(this.this$0))
                            if (!b.this.Q && b.this.h()) {
                                b.access$4(b.this, 10);
                                Thread.yield();
                            }
                            else {
                                Thread.sleep(b.this.ad);
                                if (b.this.ad >= 75) {
                                    continue;
                                }
                                final b this$0 = b.this;
                                b.access$4(this$0, this$0.ad + 5);
                            }
                        }
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Player")).start();
            if (this.S == null) {
                (this.af = new Thread(new Runnable() {
                    public void run() {
                        try {
                            while (b.this.az == null) {
                                b.access$6(b.this, (b.this.am + 12) % 360);
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
        }
        else {
            this.repaint();
            this.requestFocus();
        }
        a.for("launch player thread");
    }
    
    public void e() {
        final a.a.a.a.d.a a = (a.a.a.a.d.a)this.X.byte("applet");
        this.r();
        if (this.Q) {
            return;
        }
        this.s();
        if (this.Q) {
            return;
        }
        a.for("initialize player");
        this.f();
        if (this.Q) {
            return;
        }
        a.for("player preload image");
        this.p();
        this.requestFocus();
        a.for("reset player");
    }
    
    public void a(final a.a.a.a.c.a.b b) {
        this.g();
        this.repaint();
    }
    
    public void t() {
        if (this.ak != null) {
            this.an = true;
            this.ak.interrupt();
            this.ak = null;
            this.n();
        }
    }
    
    public void n() {
        this.i();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public abstract void q();
    
    public abstract void o();
}
