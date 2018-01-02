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
import java.awt.Dimension;
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
import java.awt.Rectangle;
import java.awt.Color;
import a.a.a.a.e.a;
import a.a.a.a.c.a.c;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Container;

public abstract class b extends Container implements ActionListener, ItemListener, c
{
    protected a ad;
    protected String ai;
    public boolean P;
    protected volatile int aa;
    private Thread ag;
    private Thread K;
    private volatile boolean aH;
    private int O;
    private boolean at;
    private Object aS;
    private Thread aR;
    private volatile int ao;
    private int N;
    protected String aN;
    protected String ac;
    protected volatile boolean Z;
    protected volatile boolean aT;
    protected boolean X;
    protected boolean am;
    protected boolean H;
    protected boolean U;
    protected int aG;
    protected int aE;
    protected int an;
    protected int ak;
    protected int aK;
    protected int aI;
    protected Color ab;
    protected Color aA;
    protected Color aw;
    protected Color az;
    protected static String af;
    protected Rectangle aP;
    protected Image ae;
    protected boolean aL;
    protected boolean ax;
    protected Vector aW;
    protected a.a.a.a.c.a.a I;
    protected AppletContext appContext;
    private boolean W;
    public String[] aC;
    public String[] au;
    public Image[] J;
    public Image[] Q;
    public Image[] as;
    public a.a.a.a.b.a[] Y;
    private ImageFilter aQ;
    private int aB;
    private int V;
    private int L;
    private int al;
    private int S;
    private int R;
    private int aO;
    private int aZ;
    private boolean av;
    protected String ap;
    protected String ay;
    protected boolean aX;
    protected int ar;
    protected int aD;
    protected int aF;
    protected int G;
    protected boolean aq;
    public a.a.b.b M;
    private Image aV;
    private Image T;
    private Image aj;
    private Image aJ;
    protected Image aU;
    public String ah;
    protected long aM;
    public boolean aY;
    
    public b(final a ad, final String ai) {
        this.ad = null;
        this.ai = null;
        this.P = false;
        this.aa = 0;
        this.aS = new Object();
        this.N = 15;
        this.Z = false;
        this.H = false;
        this.U = false;
        this.aP = null;
        this.ae = null;
        this.aL = false;
        this.ax = false;
        this.W = false;
        this.L = 0;
        this.ap = null;
        this.ay = "splashscreen.gif";
        this.aX = true;
        this.ar = 0;
        this.aD = 0;
        this.aF = this.size().width;
        this.G = this.size().height;
        this.aq = false;
        this.M = null;
        this.aV = null;
        this.T = null;
        this.aj = null;
        this.aJ = null;
        this.aU = null;
        this.ah = null;
        this.aM = 350L;
        this.aY = true;
        this.setLayout(null);
        this.ad = ad;
        this.ai = ai;
        (this.I = (a.a.a.a.c.a.a)this.ad.case("statemachine")).if(this);
        this.appContext = ((Applet)this.ad.case("applet")).getAppletContext();
        this.enableEvents(56L);
    }
    
    public void void(final String s) {
        if (s != null) {
            if (s.equalsIgnoreCase("on")) {
                this.aL = true;
            }
            else {
                this.aL = false;
            }
        }
    }
    
    public void try(final String s) {
        if (s != null) {
            if (s.equalsIgnoreCase("on")) {
                this.ax = true;
            }
            else {
                this.ax = false;
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
    
    static /* synthetic */ void access$4(final b b, final int o) {
        b.O = o;
    }
    
    static /* synthetic */ void access$6(final b b, final int ao) {
        b.ao = ao;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        a.a.a.a.b.a a = null;
        if (actionCommand.equals(this.ad.a("resource/menuitem.zoomin"))) {
            a = (a.a.a.a.b.a)this.ad.case("command/action/zoomin");
        }
        else if (actionCommand.equals(this.ad.a("resource/menuitem.zoomout"))) {
            a = (a.a.a.a.b.a)this.ad.case("command/action/zoomout");
        }
        else if (actionCommand.equals(this.ad.a("resource/menuitem.reset"))) {
            a = (a.a.a.a.b.a)this.ad.case("command/action/reset");
        }
        else if (actionCommand.equals(this.ad.a("resource/menuitem.rotateleft"))) {
            a = (a.a.a.a.b.a)this.ad.case("command/action/rotateleft");
        }
        else if (actionCommand.equals(this.ad.a("resource/menuitem.pause"))) {
            a = (a.a.a.a.b.a)this.ad.case("command/action/pause");
        }
        else if (actionCommand.equals(this.ad.a("resource/menuitem.rotateright"))) {
            a = (a.a.a.a.b.a)this.ad.case("command/action/rotateright");
        }
        else if (actionCommand.equals(this.ad.a("resource/menuitem.info"))) {
            a = (a.a.a.a.b.a)this.ad.case("command/action/info");
        }
        if (a != null) {
            a.a();
        }
    }
    
    public abstract void w();
    
    public abstract void if(final Graphics p0);
    
    public void case(final String s) {
        if (this.W) {
            this.I.for(1);
        }
        else {
            this.I.for(0);
        }
    }
    
    private void a(final int n, final int n2, int n3, int n4) {
        final int n5 = this.bounds().height + this.aB;
        if (n2 + n4 > n5) {
            n4 = n5 - n2;
        }
        final int width = this.bounds().width;
        if (n + n3 > width) {
            n3 = width - n;
        }
        this.aP = new Rectangle(n, n2, n3, n4);
    }
    
    protected void n() {
        this.appContext.showStatus("                                                                                                   ");
    }
    
    private void a(final Graphics graphics) {
        final int width = this.getSize().width;
        final int n = this.getSize().height - this.aB;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, n);
        if (this.ap != null && this.ap.length() != 0) {
            try {
                final Image image = Toolkit.getDefaultToolkit().getImage(new URL((URL)this.ad.case("param/documentbase"), this.ap));
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
    
    public void k() {
        this.aU = null;
        this.ah = null;
        this.aX = true;
        this.aL = false;
        this.ax = false;
        this.Z = false;
        this.aa = 0;
        this.N = 15;
        this.P = false;
        this.ai = null;
        b.af = null;
        this.W = false;
        this.ap = null;
        this.ar = 0;
        this.aD = 0;
        this.aF = this.size().width;
        this.G = this.size().height;
        this.ab = new Color(255, 255, 255);
    }
    
    public void m() {
        if (this.aW != null) {
            this.aW.removeAllElements();
            this.aW = null;
        }
        this.x();
        this.removeAll();
    }
    
    protected abstract void l();
    
    protected void do(final Graphics graphics) {
        if (this.ah != null && (this.aP.width != this.size().width || this.aP.height != this.size().height + this.aB)) {
            final int width = this.bounds().width;
            final int n = this.bounds().height + this.aB + 1;
            Image image = null;
            try {
                image = Toolkit.getDefaultToolkit().getImage(new URL((URL)this.ad.case("param/documentbase"), this.ah));
            }
            catch (MalformedURLException ex) {}
            try {
                if (this.aJ == null && this.a(image, this)) {
                    this.aJ = this.createImage(width, n - (this.aP.y + this.aP.height));
                    final Graphics graphics2 = this.aJ.getGraphics();
                    graphics2.setColor(this.ab);
                    graphics2.fillRect(0, 0, width, n - (this.aP.y + this.aP.height));
                    graphics2.drawImage(image, 0, -(this.aP.y + this.aP.height), width, n, this);
                    graphics2.dispose();
                    this.T = this.createImage(width, this.aP.y);
                    final Graphics graphics3 = this.T.getGraphics();
                    graphics3.setColor(this.ab);
                    graphics3.fillRect(0, 0, width, this.aP.y);
                    graphics3.drawImage(image, 0, 0, width, n, this);
                    graphics3.dispose();
                    this.aV = this.createImage(this.aP.x, n);
                    final Graphics graphics4 = this.aV.getGraphics();
                    graphics4.setColor(this.ab);
                    graphics4.fillRect(0, 0, this.aP.x, n);
                    graphics4.drawImage(image, 0, 0, width, n, this);
                    graphics4.dispose();
                    this.aj = this.createImage(width - (this.aP.x + this.aP.width), n);
                    final Graphics graphics5 = this.aj.getGraphics();
                    graphics5.setColor(this.ab);
                    graphics5.fillRect(0, 0, width - (this.aP.x + this.aP.width), n);
                    graphics5.drawImage(image, -(this.aP.x + this.aP.width), 0, width, n, this);
                    graphics5.dispose();
                    image.flush();
                }
                graphics.drawImage(this.T, 0, 0, this);
                graphics.drawImage(this.aV, 0, 0, this);
                graphics.drawImage(this.aJ, 0, this.aP.y + this.aP.height, this);
                graphics.drawImage(this.aj, this.aP.x + this.aP.width, 0, this);
            }
            catch (Exception ex2) {}
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2) {
        Image image = null;
        try {
            final InputStream a = a.a(this.ad.a("resource/image.splash"));
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
    
    protected String byte(final String s) {
        return this.ad.a("resource/error." + s);
    }
    
    public void v() {
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final a.a.a.a.b.a byte1 = ((g)itemEvent.getItemSelectable()).byte();
        if (byte1 != null) {
            byte1.a();
        }
    }
    
    protected abstract void p();
    
    public abstract void i();
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.Z) {
            this.x();
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(Color.red);
            graphics.drawString(this.aN, (size.width >> 1) - (fontMetrics.stringWidth(this.aN) >> 1), size.height >> 1);
            if (this.ac != null) {
                graphics.drawString(this.ac, (size.width >> 1) - (fontMetrics.stringWidth(this.ac) >> 1), (size.height >> 1) + fontMetrics.getHeight());
            }
        }
        else if (this.ae == null && size != null) {
            if (this.aX && this.ap != null) {
                this.a(graphics);
            }
            else {
                graphics.setColor(this.az);
                graphics.fillRect(0, 0, size.width, size.height);
                graphics.setColor(this.aw);
                final String s = "loading ...";
                final FontMetrics fontMetrics2 = graphics.getFontMetrics();
                graphics.drawString(s, (size.width >> 1) - (fontMetrics2.stringWidth(s) >> 1), (size.height >> 1) - this.N - (fontMetrics2.getHeight() + 2));
                graphics.drawOval((size.width >> 1) - this.N, (size.height >> 1) - this.N, this.N << 1, this.N << 1);
                graphics.fillArc((size.width >> 1) - this.N, (size.height >> 1) - this.N, this.N << 1, this.N << 1, 90, -this.ao);
            }
        }
        else {
            if (this.ah != null && !this.aq) {
                this.if(graphics);
            }
            if (this.aq) {
                synchronized (this.ae) {
                    graphics.drawImage(this.ae, 0, 0, null);
                }
                // monitorexit(this.ae)
            }
            if (this.ah == null && this.ae != null) {
                synchronized (this.ae) {
                    graphics.drawImage(this.ae, 0, 0, null);
                    if (this.P && !this.I.goto) {
                        this.if(graphics, this.S, this.R);
                    }
                    else {
                        this.I.for();
                        this.P = false;
                    }
                }
                // monitorexit(this.ae)
            }
            super.paint(graphics);
        }
    }
    
    public void o() {
        if (this.aR != null) {
            this.a(true);
        }
    }
    
    protected abstract void g();
    
    public void u() {
        this.h();
        this.X = false;
        final boolean b = false;
        this.aI = (b ? 1 : 0);
        this.aK = (b ? 1 : 0);
        this.ak = (b ? 1 : 0);
        this.an = (b ? 1 : 0);
        this.aE = (b ? 1 : 0);
        this.aG = (b ? 1 : 0);
        this.ab = (Color)this.ad.case("param/backgroundColor");
        this.aL = (boolean)this.ad.case("param/enableZoomPastMax");
        this.ax = (boolean)this.ad.case("param/enableZoomPastMin");
        this.W = (boolean)this.ad.case("param/showHotspots");
        this.aA = (Color)this.ad.case("param/hotspotColor");
        this.ap = (String)this.ad.case("param/altsplash");
        this.ah = (String)this.ad.case("param/backgroundImage");
        this.ar = this.ad.do("param/leftMargin");
        this.aD = this.ad.do("param/topMargin");
        this.aF = this.ad.do("param/displayWidth");
        if (this.aF == 0) {
            this.aF = this.size().width;
        }
        this.G = this.ad.do("param/displayHeight");
        if (this.G == 0) {
            this.G = this.size().height;
        }
        this.aq = (boolean)this.ad.case("param/foregroundFrame");
        this.aB = this.ad.char("resource/button.height");
        this.V = this.ad.char("resource/button.width");
        if (this.aq) {
            try {
                this.aU = Toolkit.getDefaultToolkit().getImage(new URL((URL)this.ad.case("param/documentbase"), this.ah));
            }
            catch (MalformedURLException ex) {
                this.aU = null;
                this.aq = false;
            }
            if (this.aU != null) {
                Toolkit.getDefaultToolkit().prepareImage(this.aU, this.size().width, this.size().height, this);
                this.aP = new Rectangle(0, 0, this.size().width, this.size().height);
            }
            else {
                this.aq = false;
            }
        }
        else {
            this.a(this.ar, this.aD, this.aF, this.G);
        }
        if (this.aP == null) {
            this.aP = new Rectangle(this.ar, this.aD, this.aF, this.G);
        }
        this.aw = (Color)this.ad.case("param/loadingBarColor");
        this.az = (Color)this.ad.case("param/loadingBkgdColor");
        final Vector vector = new Vector<String>();
        final Vector vector2 = new Vector<String>();
        final Vector vector3 = new Vector<Image>();
        final Vector vector4 = new Vector<Image>();
        final Vector vector5 = new Vector<Image>();
        final Vector vector6 = new Vector<Object>();
        this.aQ = new CropImageFilter(0, 0, this.V, this.aB);
        final StringTokenizer stringTokenizer = new StringTokenizer(this.ad.a("resource/" + this.ai + ".toolbar.buttons"), ":");
        final Graphics graphics = this.getGraphics();
        this.I = (a.a.a.a.c.a.a)this.ad.case("statemachine");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (!trim.equals("separator")) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(trim, ",");
                final String trim2 = stringTokenizer2.nextToken().trim();
                final String trim3 = stringTokenizer2.nextToken().trim();
                stringTokenizer2.nextToken().trim();
                vector2.addElement(trim2);
                int l;
                if (trim3.equals("state")) {
                    vector.addElement(this.ad.a("resource/menuitem.checkbox." + trim2));
                    l = graphics.getFontMetrics().stringWidth(this.ad.a("resource/menuitem.checkbox." + trim2));
                    vector6.addElement(this.ad.case("command/state/" + trim2));
                    if (!trim2.equals("magnifier")) {
                        vector5.addElement(this.createImage(new FilteredImageSource(this.ad.for("images/button/" + trim2 + ".gray").getSource(), this.aQ)));
                    }
                }
                else {
                    vector.addElement(this.ad.a("resource/menuitem." + trim2));
                    l = graphics.getFontMetrics().stringWidth(this.ad.a("resource/menuitem." + trim2));
                    vector6.addElement(this.ad.case("command/action/" + trim2));
                    vector5.addElement(this.createImage(new FilteredImageSource(this.ad.for("images/button/" + trim2 + ".up").getSource(), this.aQ)));
                }
                Image image;
                if (trim2.equals("hotspots") && this.ad.a("param/hotspot1") == null) {
                    image = this.ad.for("images/button/" + trim2 + ".gray");
                }
                else {
                    image = this.ad.for("images/button/" + trim2 + ".up");
                }
                vector3.addElement(this.createImage(new FilteredImageSource(image.getSource(), this.aQ)));
                vector4.addElement(this.createImage(new FilteredImageSource(this.ad.for("images/button/" + trim2 + ".down").getSource(), this.aQ)));
                if (l <= this.L) {
                    continue;
                }
                this.L = l;
            }
        }
        vector2.copyInto(this.aC = new String[vector2.size()]);
        vector.copyInto(this.au = new String[vector.size()]);
        vector3.copyInto(this.J = new Image[vector3.size()]);
        vector4.copyInto(this.Q = new Image[vector4.size()]);
        vector5.copyInto(this.as = new Image[vector5.size()]);
        vector6.copyInto(this.Y = new a.a.a.a.b.a[vector6.size()]);
        final Point point = (Point)this.ad.case("param/logopos");
        if (point == null) {
            this.S = 0;
            this.aO = this.V + 10;
            this.aZ = this.S;
            this.R = this.size().height - this.au.length * this.aB;
        }
        else {
            if (point.x < Math.round(this.size().width / 2)) {
                this.S = 0;
                this.aO = this.V + 10;
                this.aZ = this.S;
            }
            else {
                this.S = this.size().width - this.L - this.V - 20;
                this.aO = this.S + 10;
                this.aZ = this.size().width - this.V;
            }
            if (point.y < Math.round(this.size().height / 2)) {
                this.R = this.aB;
            }
            else {
                this.R = this.size().height - (this.au.length + 1) * this.aB;
            }
        }
        this.I.for(-1);
        int n = 1;
        while (true) {
            final String a = this.ad.a("param/hotspot" + n);
            if (a == null) {
                break;
            }
            if (this.aW == null) {
                this.aW = new Vector();
            }
            this.case(a);
            ++n;
        }
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        Label_0304: {
            switch (keyEvent.getID()) {
                case 401: {
                    switch (keyEvent.getKeyCode()) {
                        case 16:
                        case 33:
                        case 65: {
                            ((a.a.a.a.b.a)this.ad.case("command/action/zoomin")).a();
                            break Label_0304;
                        }
                        case 17:
                        case 34:
                        case 90: {
                            ((a.a.a.a.b.a)this.ad.case("command/action/zoomout")).a();
                            break Label_0304;
                        }
                        case 86: {
                            ((a.a.a.a.b.a)this.ad.case("command/action/reset")).a();
                            break Label_0304;
                        }
                        case 73:
                        case 112: {
                            ((a.a.a.a.b.a)this.ad.case("command/action/info")).a();
                            break Label_0304;
                        }
                        case 77: {
                            ((a.a.a.a.b.a)this.ad.case("command/state/magnifier")).a();
                            break Label_0304;
                        }
                        case 84: {
                            ((a.a.a.a.b.a)this.ad.case("command/state/toolbar")).a();
                            break Label_0304;
                        }
                        case 72: {
                            ((a.a.a.a.b.a)this.ad.case("command/state/hotspots")).a();
                            break Label_0304;
                        }
                    }
                    break;
                }
                case 400: {
                    this.am = true;
                    break;
                }
                case 402: {
                    this.am = false;
                    this.h();
                    break;
                }
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger() && !this.P) {
            this.X = true;
            this.P = true;
            this.aG = mouseEvent.getX();
            this.aE = mouseEvent.getY();
            this.setCursor((Cursor)this.ad.case("cursor/default"));
        }
        else {
            switch (mouseEvent.getID()) {
                case 501: {
                    if (!this.P) {
                        this.X = true;
                        final int x = mouseEvent.getX();
                        this.aG = x;
                        this.aK = x;
                        this.an = x;
                        final int y = mouseEvent.getY();
                        this.aE = y;
                        this.aI = y;
                        this.ak = y;
                        break;
                    }
                    if (this.al == -1) {
                        this.X = true;
                        break;
                    }
                    break;
                }
                case 502: {
                    this.X = false;
                    this.aK = mouseEvent.getX();
                    this.aI = mouseEvent.getY();
                    this.h();
                    this.setCursor((Cursor)this.ad.case("cursor/default"));
                    if (this.P) {
                        if (this.al >= 0) {
                            this.Y[this.al].a();
                        }
                        this.P = false;
                        break;
                    }
                    break;
                }
                case 505: {
                    this.h();
                    break;
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                this.aK = mouseEvent.getX();
                this.aI = mouseEvent.getY();
                this.h();
                break;
            }
            case 503: {
                final int x = mouseEvent.getX();
                this.aK = x;
                this.an = x;
                final int y = mouseEvent.getY();
                this.aI = y;
                this.ak = y;
                if (this.P && this.ak > this.R && this.ak < this.R + this.au.length * this.aB && this.an > this.S && this.an < this.S + this.L + this.V + 20 && Math.round((this.ak - this.R) / this.aB) != this.al) {
                    this.repaint();
                    break;
                }
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    protected abstract void e();
    
    public void s() {
        this.h();
        this.requestFocus();
    }
    
    public void z() {
        this.a(false);
    }
    
    public abstract void B();
    
    public abstract void A();
    
    public abstract void d();
    
    public abstract boolean j();
    
    public void h() {
        this.aT = true;
    }
    
    private void a(final boolean at) {
        synchronized (this.aS) {
            if (this.at != at) {
                this.at = at;
                this.aS.notifyAll();
            }
        }
        // monitorexit(this.aS)
    }
    
    public void long(final String s) {
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
        this.ab = new Color(array[0], array[1], array[2]);
        this.h();
    }
    
    public void else(final String ah) {
        this.ah = ah;
    }
    
    public void b(final String s) {
        try {
            this.G = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.G = this.size().height;
        }
    }
    
    public void null(final String s) {
        try {
            this.aF = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.aF = this.size().width;
        }
    }
    
    public void new(final String s) {
        if (s != null && s.equalsIgnoreCase("true")) {
            this.aq = true;
        }
    }
    
    public void c(final String s) {
        try {
            this.ar = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.ar = 0;
        }
    }
    
    public void char(final String s) {
        try {
            this.aD = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.aD = 0;
        }
    }
    
    protected void goto(final String s) {
        this.a(s, null);
    }
    
    protected void a(final String an, final String ac) {
        if (this.Z) {
            return;
        }
        this.aN = an;
        this.ac = ac;
        this.Z = true;
        this.repaint();
    }
    
    protected void if(final Graphics graphics, final int n, final int n2) {
        boolean b = false;
        final int round = Math.round((this.ak - n2) / this.aB);
        final int n3 = round * this.aB + n2 + 1;
        graphics.setColor(new Color(204, 204, 204));
        graphics.fill3DRect(n, n2 - 1, this.L + this.V + 20, this.au.length * this.aB + 3, true);
        if (this.ak > n2 && this.ak < n2 + this.au.length * this.aB && this.an > n && this.an < n + this.L + this.V + 20) {
            graphics.setColor(new Color(10, 36, 102));
            graphics.fill3DRect(n, n3, this.L + this.V + 20, this.aB, true);
            b = true;
            this.al = round;
        }
        else {
            this.al = -1;
        }
        for (int i = 0; i < this.au.length; ++i) {
            if (i == round && b) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            if (this.aW == null && this.aC[i].equals("hotspots")) {
                if (i != round || this.al == -1) {
                    graphics.setColor(Color.white);
                    graphics.drawString(this.au[i], this.aO + 1, 20 + this.aB * i + n2);
                }
                graphics.setColor(new Color(128, 128, 128));
                graphics.drawString(this.au[i], this.aO, 19 + this.aB * i + n2);
            }
            else {
                graphics.drawString(this.au[i], this.aO, 19 + this.aB * i + n2);
            }
            if (this.I.a(this.aC[i]) == 1) {
                graphics.drawImage(this.Q[i], this.aZ, this.aB * i + n2, this);
            }
            else {
                graphics.drawImage(this.J[i], this.aZ, this.aB * i + n2, this);
            }
        }
    }
    
    public void y() {
        final a.a.a.a.d.a a = (a.a.a.a.d.a)this.ad.case("applet");
        a.if("player start()");
        if (this.aR == null) {
            this.f();
            a.for("player start2()");
            this.aH = false;
            this.O = 10;
            (this.aR = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (!b.this.aH) {
                            synchronized (b.this.aS) {
                                while (b.this.at) {
                                    b.this.aS.wait();
                                }
                            }
                            // monitorexit(b.access$1(this.this$0))
                            if (!b.this.Z && b.this.j()) {
                                b.access$4(b.this, 10);
                                Thread.yield();
                            }
                            else {
                                Thread.sleep(b.this.O);
                                if (b.this.O >= 75) {
                                    continue;
                                }
                                final b this$0 = b.this;
                                b.access$4(this$0, this$0.O + 5);
                            }
                        }
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Player")).start();
            if (this.ap == null) {
                (this.ag = new Thread(new Runnable() {
                    public void run() {
                        try {
                            while (b.this.ae == null) {
                                b.access$6(b.this, (b.this.ao + 12) % 360);
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
            if (this.aY) {
                (this.K = new Thread(new Runnable() {
                    public void run() {
                        try {
                            while (b.this.aY) {
                                b.this.i();
                                Thread.sleep(b.this.aM);
                            }
                        }
                        catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "proloading tracker")).start();
            }
        }
        else {
            this.repaint();
            this.requestFocus();
        }
        a.for("launch player thread");
    }
    
    public void f() {
        final a.a.a.a.d.a a = (a.a.a.a.d.a)this.ad.case("applet");
        this.u();
        if (this.Z) {
            return;
        }
        this.v();
        if (this.Z) {
            return;
        }
        a.for("initialize player");
        this.g();
        if (this.Z) {
            return;
        }
        a.for("player preload image");
        this.s();
        this.requestFocus();
        a.for("reset player");
    }
    
    public void a(final a.a.a.a.c.a.b b) {
        this.h();
        this.repaint();
    }
    
    public void x() {
        if (this.aR != null) {
            this.aH = true;
            this.aR.interrupt();
            this.aR = null;
            this.q();
        }
    }
    
    public void q() {
        this.l();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public abstract void t();
    
    public abstract void r();
}
