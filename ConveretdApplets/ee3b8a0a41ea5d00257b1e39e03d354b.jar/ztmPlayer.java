import java.text.DecimalFormat;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.awt.Menu;
import java.awt.event.ActionListener;
import java.awt.MenuComponent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.io.DataInputStream;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import netscape.javascript.JSObject;
import java.awt.Color;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ztmPlayer extends Applet implements Runnable
{
    public static String legalPlayerName;
    static URL I;
    static boolean Z;
    static volatile int C;
    private ztmPlayer add;
    volatile int B;
    private int addActionListener;
    volatile URL D;
    Color F;
    Color J;
    private Thread addImage;
    volatile boolean S;
    volatile boolean A;
    private boolean addMouseListener;
    volatile boolean E;
    boolean G;
    volatile boolean H;
    volatile boolean K;
    private boolean addMouseMotionListener;
    int L;
    String M;
    volatile int N;
    volatile int O;
    int addSeparator;
    int append;
    double P;
    CI Q;
    volatile K R;
    Q T;
    Q booleanValue;
    O charAt;
    private int clearRect;
    private int close;
    I U;
    ZI V;
    Z W;
    private BI createImage;
    private volatile boolean currentThread;
    private volatile boolean decode;
    private volatile boolean dispose;
    volatile boolean X;
    private volatile String drawImage;
    private volatile String equals;
    private volatile String equalsIgnoreCase;
    private JSObject eval;
    private byte[] exp;
    byte[] Y;
    H i;
    U z;
    private PopupMenu fillRect;
    private MenuItem forName;
    private MenuItem format;
    private MenuItem gc;
    private MenuItem getCodeBase;
    volatile boolean c;
    boolean b;
    boolean getComponent;
    private long getDocumentBase;
    private String getGraphics;
    private String getHeight;
    private URL getImage;
    private String getInstance;
    private Image getParameter;
    volatile Image getProperty;
    volatile Image d;
    volatile Image getSize;
    volatile Image f;
    volatile Image j;
    volatile int[] s;
    volatile boolean a;
    volatile boolean e;
    volatile long g;
    volatile long h;
    volatile long k;
    volatile DataInputStream l;
    volatile long m;
    volatile double n;
    volatile double o;
    volatile double p;
    URL q;
    byte[] r;
    byte[] t;
    int u;
    long v;
    private boolean getWidth;
    private volatile long getWindow;
    private boolean getX;
    boolean w;
    static boolean II;
    double ZI;
    static double getY;
    
    public ztmPlayer() {
        this.ZI = 1.0;
        this.add = this;
        ztmPlayer.legalPlayerName = I.I.I(1402);
        this.F = Color.white;
        this.J = Color.green;
        ztmPlayer.I = new URL(I.I.I(1422));
        this.getDocumentBase = -1L;
        this.L = 255;
        this.M = ztmPlayer.legalPlayerName + I.I.I(1444);
        this.exp = new byte[1];
        this.Y = new byte[1];
        final boolean b = true;
        this.X = b;
        this.dispose = b;
        this.K = b;
        this.addMouseListener = b;
    }
    
    public final void init() {
        System.out.print(ztmPlayer.legalPlayerName + I.I.I(917));
        this.getParameter = this.currentThread(this.getParameter(I.I.I(937)));
        this.getProperty = this.currentThread(this.getParameter(I.I.I(956)));
        this.getSize = this.currentThread(this.getParameter(I.I.I(975)));
        this.set_callback_on_playback_start(this.getParameter(I.I.I(992)));
        this.set_callback_on_playback_pause(this.getParameter(I.I.I(1010)));
        this.set_callback_on_playback_stop(this.getParameter(I.I.I(1028)));
        if (Boolean.valueOf(this.getParameter(I.I.I(1045)))) {
            this.j = this.currentThread(this.getParameter(I.I.I(1057)));
            if (null != this.j) {
                final int width = this.j.getWidth(null);
                final int height = this.j.getHeight(null);
                this.s = new int[width * height];
                final PixelGrabber pixelGrabber = new PixelGrabber(this.j, 0, 0, width, height, this.s, 0, width);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex) {
                    this.s = null;
                }
            }
        }
        if (!this.close(this.getParameter(I.I.I(1072)))) {
            this.addMouseMotionListener = true;
            System.out.println(I.I.I(1080));
            return;
        }
        this.getGraphics = this.getParameter(I.I.I(1087));
        this.getHeight = this.getParameter(I.I.I(1098));
        this.getComponent = Boolean.valueOf(this.getParameter(I.I.I(1114)));
        this.b = Boolean.valueOf(this.getParameter(I.I.I(1133)));
        this.J = createImage(this.getParameter(I.I.I(1151)), this.J);
        this.E = Boolean.valueOf(this.getParameter(I.I.I(1171)));
        this.G = Boolean.valueOf(this.getParameter(I.I.I(1181)));
        final String parameter = this.getParameter(I.I.I(1203));
        if (null != parameter) {
            if (parameter.equalsIgnoreCase(I.I.I(1212))) {
                this.B = 2;
            }
            else {
                this.B = (((boolean)Boolean.valueOf(parameter)) ? 1 : 0);
            }
        }
        try {
            final String parameter2 = this.getParameter(I.I.I(1221));
            if (null != parameter2) {
                this.getImage = this.addImage(parameter2);
            }
        }
        catch (Exception ex2) {}
        this.getInstance = this.getParameter(I.I.I(1232));
        this.A = Boolean.valueOf(this.getParameter(I.I.I(1249)));
        try {
            this.addActionListener = Integer.parseInt(this.getParameter(I.I.I(1263)));
        }
        catch (Exception ex3) {}
        finally {
            if (0 >= this.addActionListener) {
                this.addActionListener = -1;
            }
        }
        try {
            final int int1 = Integer.parseInt(this.getParameter(I.I.I(1278)));
            if (int1 >= 0 || int1 <= 255) {
                this.L = int1;
            }
        }
        catch (Exception ex4) {}
        System.out.println(I.I.I(1294));
    }
    
    public final synchronized void start() {
        if (this.addMouseMotionListener) {
            return;
        }
        super.start();
        (this.addImage = new Thread(this, ztmPlayer.legalPlayerName)).start();
    }
    
    private void add(final String s) {
        if (null != this.eval && null != s) {
            try {
                this.eval.eval(s);
            }
            catch (Exception ex) {}
        }
    }
    
    public final void run() {
        try {
            this.eval = JSObject.getWindow((Applet)this);
            ztmPlayer.II = (0 > ((String)this.eval.eval(I.I.I(1))).indexOf(I.I.I(21)));
        }
        catch (Exception ex) {}
        if (0 == ztmPlayer.C) {
            try {
                Class.forName(I.I.I(28));
                ztmPlayer.C = 1;
            }
            catch (Exception ex2) {
                try {
                    Class.forName(I.I.I(60));
                    ztmPlayer.C = 2;
                }
                catch (Exception ex3) {}
            }
        }
        this.setLayout(new GridLayout());
        try {
            while (!this.S) {
                this.equals();
                if (this.dispose) {
                    this.setBackground(this.F = createImage(this.getHeight, this.F));
                    if (null != this.z) {
                        this.remove(this.z);
                    }
                    if (null == this.Q) {
                        (this.Q = new CI(this)).setSize(this.getSize());
                        this.add(this.Q);
                    }
                }
                this.charAt = new O(this);
                while (!this.S) {
                    this.getWidth = false;
                    if (-1 == this.charAt.C) {
                        this.addMouseMotionListener = true;
                        this.charAt.I();
                        return;
                    }
                    if (3 <= this.charAt.C) {
                        break;
                    }
                    Thread.currentThread();
                    Thread.sleep(20L);
                }
                this.P = 1000.0 / this.n;
                if (null != this.getGraphics) {
                    this.M = ztmPlayer.legalPlayerName + I.I.I(82) + this.getGraphics;
                }
                if (this.dispose) {
                    this.d = this.decode(this.getProperty);
                    this.f = this.decode(this.getSize);
                    if (null == this.i) {
                        this.i = new H(this);
                    }
                    else {
                        this.i.I();
                    }
                    if (1 == this.B) {
                        final H i = this.i;
                        final H j = this.i;
                        i.Z(1);
                        final H k = this.i;
                        final H l = this.i;
                        k.I(2);
                    }
                    else {
                        final H m = this.i;
                        final H i2 = this.i;
                        m.I(1);
                        final H i3 = this.i;
                        final H i4 = this.i;
                        i3.Z(2);
                    }
                    if (this.E) {
                        final H i5 = this.i;
                        final H i6 = this.i;
                        i5.Z(3);
                    }
                    else {
                        final H i7 = this.i;
                        final H i8 = this.i;
                        i7.I(3);
                    }
                    final FI fi = new FI(this);
                    if (null != this.fillRect) {
                        this.Q.remove(this.fillRect);
                    }
                    this.fillRect = new PopupMenu();
                    this.forName = new MenuItem(I.I.I(86));
                    this.format = new MenuItem(I.I.I(91));
                    this.gc = new MenuItem(this.E ? I.I.I(96) : I.I.I(103));
                    this.getCodeBase = new MenuItem(I.I.I(108));
                    this.forName.setActionCommand(I.I.I(118));
                    this.forName.addActionListener(fi);
                    this.format.setActionCommand(I.I.I(120));
                    this.format.setEnabled(false);
                    this.format.addActionListener(fi);
                    this.gc.setActionCommand(I.I.I(122));
                    this.gc.addActionListener(fi);
                    this.getCodeBase.setActionCommand(I.I.I(124));
                    this.getCodeBase.addActionListener(fi);
                    final Menu menu = new Menu(I.I.I(126));
                    menu.add(I.I.I(137) + this.N + I.I.I(146) + this.O + I.I.I(148) + NumberFormat.getInstance().format(Math.round(100.0 * this.n) / 100.0) + I.I.I(152));
                    menu.add(I.I.I(157) + dispose(this.m));
                    menu.addSeparator();
                    menu.add(I.I.I(168) + System.getProperty(I.I.I(183)) + I.I.I(196) + System.getProperty(I.I.I(199)) + I.I.I(211));
                    final MenuItem menuItem = new MenuItem(I.I.I(213));
                    menuItem.setActionCommand(I.I.I(240));
                    menuItem.addActionListener(fi);
                    if (!Boolean.valueOf(this.getParameter(I.I.I(242)))) {
                        this.fillRect.add(this.forName);
                        this.fillRect.add(this.format);
                        if (0 != ztmPlayer.C) {
                            this.fillRect.add(this.gc);
                        }
                        this.fillRect.add(this.getCodeBase);
                        this.fillRect.addSeparator();
                        this.fillRect.add(menu);
                        this.fillRect.addSeparator();
                    }
                    this.fillRect.add(menuItem);
                    this.Q.add(this.fillRect);
                    this.Q.addMouseListener(new JI(this));
                    this.Q.addMouseMotionListener(new SI(this));
                    this.z = new U(this.Q, this.i);
                    this.Q.setSize(this.N, this.O);
                    this.add(this.z);
                    this.z.setBounds(0, 0, this.getSize().width, this.getSize().height);
                    this.repaint();
                    this.dispose = false;
                }
                synchronized (this.charAt) {
                    this.charAt.notifyAll();
                    while (!this.S) {
                        if (-1 == this.charAt.C) {
                            throw new InterruptedException();
                        }
                        if (5 == this.charAt.C) {
                            if (0 != ztmPlayer.C) {
                                this.close = (int)(15.0 * this.p);
                                this.booleanValue = new Q(this.charAt.F, this.close, 512, I.I.I(255));
                            }
                            this.clearRect = (int)((15.0 - 0.001 * this.charAt.I) * this.o);
                            this.T = new Q(this.charAt.B, this.clearRect, 2048, I.I.I(268));
                            break;
                        }
                        this.charAt.wait(20L);
                    }
                }
                if (0 != ztmPlayer.C) {
                    this.U = new I(this.booleanValue, 0.5, 2 == ztmPlayer.C, this.charAt.I);
                }
                this.V = new ZI(this, this.n, this.charAt.Z);
                if (0 != ztmPlayer.C) {
                    try {
                        if (2 == ztmPlayer.C) {
                            this.W = new Y();
                        }
                        else if (1 == ztmPlayer.C) {
                            this.W = M.S();
                        }
                        this.W.I(this);
                        this.i.I(this.get_volume());
                    }
                    catch (Exception ex4) {
                        ztmPlayer.C = 0;
                    }
                }
                this.createImage = new BI(this);
                this.getWidth = true;
                this.currentThread = true;
                if (!this.a) {
                    this.i.I(0.0f);
                }
                int n = 0;
                do {
                    if (!this.a && 0 == n % 25) {
                        this.I((0 == n % 50) ? I.I.I(281) : null);
                    }
                    else {
                        Thread.currentThread();
                        Thread.sleep(20L);
                    }
                    ++n;
                } while (!this.T.I && !this.T.Z && (!this.createImage.Z || this.V.C() < 0.8) && 0 != ztmPlayer.C && !this.booleanValue.I && !this.booleanValue.Z && (0 != this.U.D || this.U.C() < 0.8) && !this.S);
                this.I((String)null);
                synchronized (this.exp) {
                    if (1 == this.B || (!this.addMouseListener && this.A) || this.a) {
                        if (!this.S) {
                            this.addMouseMotionListener();
                        }
                        this.a = false;
                        if (1 == this.B) {
                            this.B = 0;
                        }
                    }
                    else {
                        while (this.K) {
                            this.exp.wait(50L);
                        }
                    }
                }
                while (!this.S) {
                    synchronized (this.exp) {
                        if (this.a || this.K) {
                            if (this.a) {
                                this.addSeparator();
                            }
                            this.addMouseListener = !this.a;
                            break;
                        }
                        this.exp.wait(500L);
                        if (!this.K && this.createImage.F) {
                            this.i.I(1.0f);
                            this.addSeparator();
                            this.addMouseListener = false;
                            break;
                        }
                    }
                    if (!this.a) {
                        this.i.I((this.createImage.I + this.charAt.J) / this.m);
                        if (!this.decode) {
                            continue;
                        }
                        if (!this.T.I && !this.T.Z && this.V.C() < 0.5f && (0 == ztmPlayer.C || (!this.booleanValue.I && !this.booleanValue.Z && this.U.C() < 0.5f))) {
                            synchronized (this.exp) {
                                this.createImage.D();
                                if (0 != ztmPlayer.C) {
                                    this.W.D();
                                }
                                this.H = true;
                            }
                            int n2 = 0;
                            while (!this.T.I && !this.T.Z && this.V.C() < 0.8f && (0 == ztmPlayer.C || (!this.booleanValue.I && !this.booleanValue.Z && this.U.C() < 0.8f))) {
                                if (0 == n2 % 5) {
                                    this.I((0 == n2 % 10) ? I.I.I(304) : null);
                                }
                                ++n2;
                                Thread.currentThread();
                                Thread.sleep(100L);
                            }
                            this.I((String)null);
                            this.addMouseMotionListener();
                        }
                        this.decode = false;
                    }
                }
                this.currentThread = false;
            }
        }
        catch (Exception ex5) {}
        synchronized (this) {
            this.addSeparator();
            this.I((String)null);
            this.Q = null;
            System.out.println(ztmPlayer.legalPlayerName + I.I.I(319));
            final boolean b = false;
            this.S = b;
            this.currentThread = b;
            this.notifyAll();
        }
    }
    
    private void addActionListener(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger() && !this.a && (null != this.R || this.N - mouseEvent.getX() > 30 || this.O - mouseEvent.getY() > 30)) {
            this.getX = true;
            this.fillRect.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public final synchronized void stop() {
        if (this.addMouseMotionListener) {
            return;
        }
        super.stop();
        this.hide_fscreen();
        if (null != this.addImage && this.addImage.isAlive()) {
            this.K = false;
            synchronized (this.exp) {
                this.exp.notifyAll();
            }
            this.drawImage();
            this.S = true;
            while (this.S) {
                try {
                    this.wait(100L);
                }
                catch (InterruptedException ex) {}
            }
            try {
                this.addImage.join();
            }
            catch (InterruptedException ex2) {}
        }
        try {
            this.l.close();
        }
        catch (Exception ex3) {}
        this.addImage = null;
        this.add = null;
        this.equals();
    }
    
    private final URL addImage(final String s) {
        try {
            if (-1 == s.indexOf(I.I.I(1299)) && !s.startsWith(I.I.I(1303)) && !s.startsWith(I.I.I(1310)) && !s.startsWith(I.I.I(1312)) && (s.charAt(1) != ':' || !Character.isLetter(s.charAt(0)))) {
                final String parameter = this.getParameter(I.I.I(1314));
                String substring = (null != parameter && parameter.equalsIgnoreCase(I.I.I(1322))) ? this.getCodeBase().toExternalForm() : this.getDocumentBase().toExternalForm();
                final int lastIndex = substring.lastIndexOf(I.I.I(1310));
                if (-1 != lastIndex) {
                    substring = substring.substring(0, 1 + lastIndex);
                }
                return new URL(substring + s);
            }
            return new URL(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    final void I() {
        this.decode = true;
    }
    
    public final void set_callback_on_playback_start(final String equals) {
        this.equals = equals;
    }
    
    public final void set_callback_on_playback_stop(final String drawImage) {
        this.drawImage = drawImage;
    }
    
    public final void set_callback_on_playback_pause(final String equalsIgnoreCase) {
        this.equalsIgnoreCase = equalsIgnoreCase;
    }
    
    public final void toggle_repeat_forever() {
        synchronized (this.exp) {
            this.A = !this.A;
        }
    }
    
    public final void set_repeat_forever() {
        synchronized (this.exp) {
            this.A = true;
        }
    }
    
    public final void unset_repeat_forever() {
        synchronized (this.exp) {
            this.A = false;
        }
    }
    
    public final synchronized void play(final String s, final String getHeight, final String getGraphics) {
        if (this.dispose || !this.close(s)) {
            return;
        }
        this.getHeight = getHeight;
        this.getGraphics = getGraphics;
        synchronized (this.exp) {
            this.K = false;
            this.exp.notifyAll();
            try {
                this.exp.wait(50L);
            }
            catch (InterruptedException ex) {}
            final boolean b = true;
            this.dispose = b;
            this.X = b;
            this.B = 1;
            this.addSeparator();
        }
    }
    
    public final void toggle_playback() {
        if (this.currentThread) {
            this.addMouseListener();
        }
    }
    
    private void addMouseListener() {
        synchronized (this.exp) {
            if (this.H || this.K) {
                this.start_playback();
            }
            else {
                this.pause_playback();
            }
        }
    }
    
    public final void start_playback() {
        if (this.currentThread) {
            try {
                this.addMouseMotionListener();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void addMouseMotionListener() {
        synchronized (this.exp) {
            if (this.H || this.K || this.a) {
                if (0 == this.addActionListener) {
                    return;
                }
                --this.addActionListener;
                this.forName.setLabel(I.I.I(340));
                this.format.setEnabled(true);
                if (!this.a) {
                    final H i = this.i;
                    final H j = this.i;
                    i.Z(1);
                    final H k = this.i;
                    final H l = this.i;
                    k.I(2);
                }
                this.decode = false;
                if (this.H) {
                    this.createImage.J();
                    if (0 == ztmPlayer.C) {
                        synchronized (this.Y) {
                            this.Y.notifyAll();
                        }
                    }
                    else {
                        this.W.J();
                    }
                }
                else {
                    this.createImage.F();
                    if (0 == ztmPlayer.C) {
                        synchronized (this.Y) {
                            this.Y.notifyAll();
                        }
                    }
                    else {
                        this.W.F();
                    }
                }
                final boolean b = false;
                this.K = b;
                this.H = b;
                this.add(this.equals);
                this.exp.notifyAll();
            }
        }
    }
    
    public final void stop_playback() {
        if (this.currentThread || this.a) {
            this.addSeparator();
        }
    }
    
    private void addSeparator() {
        this.charAt.I();
        this.drawImage();
        if (0 != ztmPlayer.C) {
            synchronized (this.add.W) {
                this.add.W.notifyAll();
            }
        }
        synchronized (this.exp) {
            if (!this.a && null != this.i) {
                final H i = this.i;
                final H j = this.i;
                i.I(1);
                final H k = this.i;
                final H l = this.i;
                k.Z(2);
            }
            if (!this.K) {
                this.forName.setLabel(I.I.I(86));
                this.format.setEnabled(false);
                if (0 != ztmPlayer.C) {
                    this.W.C();
                }
                this.createImage.C();
                if (!this.a) {
                    final long n = 0L;
                    this.k = n;
                    this.g = n;
                    this.e = false;
                    this.a = false;
                }
                this.T.I();
                if (0 != ztmPlayer.C) {
                    this.booleanValue.I();
                }
                this.V.B();
                if (0 != ztmPlayer.C) {
                    this.U.B();
                }
                this.K = !this.a;
                final boolean b = false;
                this.H = b;
                this.currentThread = b;
                this.add(this.drawImage);
                this.exp.notifyAll();
            }
        }
    }
    
    public final void pause_playback() {
        if (this.currentThread) {
            try {
                this.append();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void append() {
        synchronized (this.exp) {
            if (!this.H && !this.K) {
                if (-1 != this.addActionListener) {
                    ++this.addActionListener;
                }
                this.forName.setLabel(I.I.I(86));
                final H i = this.i;
                final H j = this.i;
                i.I(1);
                this.createImage.D();
                if (0 != ztmPlayer.C) {
                    this.W.D();
                }
                this.H = true;
                this.add(this.equalsIgnoreCase);
                this.exp.notifyAll();
            }
        }
    }
    
    public final void toggle_mute() {
        if (this.currentThread) {
            this.booleanValue();
        }
    }
    
    private void booleanValue() {
        synchronized (this.exp) {
            if (this.E) {
                this.unmute();
            }
            else {
                this.mute();
            }
        }
    }
    
    public final void mute() {
        if (this.currentThread) {
            this.charAt();
        }
    }
    
    private void charAt() {
        if (ztmPlayer.C != 1 || ((M)this.W).I.isRunning()) {
            synchronized (this.exp) {
                if (!this.E) {
                    this.gc.setLabel(I.I.I(96));
                    final H i = this.i;
                    final H j = this.i;
                    i.Z(3);
                    if (0 != ztmPlayer.C) {
                        this.W.I();
                    }
                    this.E = true;
                }
            }
        }
    }
    
    public final void unmute() {
        if (this.currentThread) {
            this.clearRect();
        }
    }
    
    private void clearRect() {
        if (ztmPlayer.C != 1 || ((M)this.W).I.isRunning()) {
            synchronized (this.exp) {
                if (this.E) {
                    this.gc.setLabel(I.I.I(103));
                    final H i = this.i;
                    final H j = this.i;
                    i.I(3);
                    if (0 != ztmPlayer.C) {
                        this.W.Z();
                    }
                    this.E = false;
                }
            }
        }
    }
    
    public final synchronized void toggle_fscreen() {
        if (null == this.R) {
            this.show_fscreen();
        }
        else {
            this.hide_fscreen();
        }
    }
    
    public final synchronized void show_fscreen() {
        if (null == this.R) {
            this.getCodeBase.setLabel(I.I.I(1456));
            this.c = false;
            this.R = new K(this);
        }
    }
    
    public final synchronized void hide_fscreen() {
        if (null != K.Z) {
            this.getCodeBase.setLabel(I.I.I(1466) + K.Z.width * 100 / this.N + I.I.I(1472));
        }
        if (null != this.R) {
            this.R.dispose();
        }
    }
    
    public final synchronized int get_status() {
        int n = 0;
        if (this.H) {
            n |= 0x1;
        }
        if (this.K) {
            n |= 0x2;
        }
        if (this.E) {
            n |= 0x4;
        }
        if (null != this.R) {
            n |= 0x8;
        }
        return n;
    }
    
    private boolean close(String externalForm) {
        if (null == externalForm) {
            T.I(I.I.I(1331));
            return false;
        }
        try {
            final URL addImage = this.addImage(externalForm);
            this.D = addImage;
            externalForm = addImage.toExternalForm();
            if (!externalForm.substring(externalForm.length() - 6).equalsIgnoreCase(I.I.I(759))) {
                throw new NullPointerException();
            }
        }
        catch (Exception ex) {
            T.I(I.I.I(1363));
            return false;
        }
        return true;
    }
    
    private static Color createImage(String substring, final Color color) {
        try {
            if (I.I.I(908).equals(substring.substring(0, 1))) {
                substring = substring.substring(1);
            }
            return Color.decode(I.I.I(910) + substring);
        }
        catch (Exception ex) {
            return color;
        }
    }
    
    final void I(final String s) {
        if (this.getComponent) {
            this.Q.I(s);
        }
    }
    
    private Image currentThread(final String s) {
        if (null == s) {
            return null;
        }
        Image image = this.getImage(this.addImage(s));
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (mediaTracker.isErrorID(0) || 0 >= image.getWidth(null)) {
            image = null;
        }
        System.gc();
        return image;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (null != this.getParameter) {
            graphics.drawImage(this.getParameter, 0, 0, null);
        }
        else {
            graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        }
        try {
            this.paintComponents(graphics);
        }
        catch (Exception ex) {}
    }
    
    private Image decode(final Image image) {
        Image image2 = null;
        if (null != image) {
            image2 = this.createImage(this.N, this.O);
            final Graphics graphics = image2.getGraphics();
            graphics.setColor(this.F);
            graphics.fillRect(0, 0, this.N, this.O);
            if (null != this.getParameter) {
                graphics.drawImage(this.getParameter, 0, 0, this.N, this.O, this.addSeparator, this.append, this.addSeparator + this.N, this.append + this.O, this.F, null);
            }
            int n = this.N;
            int o = this.O;
            if (n / image.getWidth(null) < o / image.getHeight(null)) {
                o = image.getHeight(null) * n / image.getWidth(null);
            }
            else {
                n = image.getWidth(null) * o / image.getHeight(null);
            }
            graphics.drawImage(image, (this.N - n) / 2, (this.O - o) / 2, n, o, null);
        }
        return image2;
    }
    
    private static final String dispose(long n) {
        final long n2 = n / 3600000000L;
        n %= 3600000000L;
        final long n3 = n / 60000000L;
        n %= 60000000L;
        return new DecimalFormat(I.I.I(448)).format(n2) + I.I.I(450) + new DecimalFormat(I.I.I(452)).format(n3) + I.I.I(450) + new DecimalFormat(I.I.I(455)).format(1.0E-6 * n);
    }
    
    final void I(double n) {
        if (!this.a) {
            this.a = true;
            if (n > 1.0) {
                n = 1.0;
            }
            else if (n < 0.0) {
                n = 0.0;
            }
            else {
                n = (long)(n * (this.m / 1000000.0) * this.n) / this.n / (this.m / 1000000.0);
            }
            this.g = (long)(n * this.h);
            synchronized (this.exp) {
                this.exp.notifyAll();
            }
            new AI(this).start();
        }
    }
    
    private void drawImage() {
        while (!this.getWidth && !this.S) {
            try {
                Thread.currentThread();
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void equals() {
        final Q q = null;
        this.booleanValue = q;
        this.T = q;
        this.charAt = null;
        this.U = null;
        this.V = null;
        this.W = null;
        this.createImage = null;
        System.gc();
    }
    
    public final void set_volume(final double n) {
        if (1.0 <= n) {
            this.ZI = 1.0;
        }
        else if (0.0 >= n) {
            this.ZI = 0.0;
        }
        else {
            this.ZI = Math.exp((1.0 - n) * ztmPlayer.getY);
        }
    }
    
    public final double get_volume() {
        return 1.0 - Math.log(this.ZI) / ztmPlayer.getY;
    }
    
    static final void I(final ztmPlayer ztmPlayer, final MouseEvent mouseEvent) {
        ztmPlayer.addActionListener(mouseEvent);
    }
    
    static final ztmPlayer I(final ztmPlayer ztmPlayer) {
        return ztmPlayer.add;
    }
    
    static final boolean Z(final ztmPlayer ztmPlayer) {
        return ztmPlayer.currentThread;
    }
    
    static final URL C(final ztmPlayer ztmPlayer) {
        return ztmPlayer.getImage;
    }
    
    static final boolean B(final ztmPlayer ztmPlayer) {
        return ztmPlayer.getX;
    }
    
    static final long D(final ztmPlayer ztmPlayer) {
        return ztmPlayer.getWindow;
    }
    
    static final String F(final ztmPlayer ztmPlayer) {
        return ztmPlayer.getInstance;
    }
    
    static final long I(final ztmPlayer ztmPlayer, final long getWindow) {
        return ztmPlayer.getWindow = getWindow;
    }
    
    static final boolean I(final ztmPlayer ztmPlayer, final boolean getX) {
        return ztmPlayer.getX = getX;
    }
    
    static {
        ztmPlayer.Z = (0 <= System.getProperty(I.I.I(183)).indexOf(I.I.I(913)));
        ztmPlayer.getY = Math.log(0.01);
    }
}
