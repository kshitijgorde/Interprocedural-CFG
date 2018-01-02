// 
// Decompiled by Procyon v0.5.30
// 

package com.kaon.meson;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import java.util.Locale;
import java.lang.reflect.Method;
import java.awt.Container;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

public class MesonApplet extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener, FocusListener, ActionListener, WindowListener
{
    public static boolean a;
    public static String b;
    public static boolean c;
    public static boolean d;
    public static int e;
    public static int f;
    public static int g;
    public static String h;
    public static boolean i;
    public static boolean j;
    public static boolean k;
    public static boolean l;
    public static boolean m;
    public boolean n;
    public float o;
    public Dimension p;
    public int q;
    public int r;
    public int s;
    public int t;
    public Blackboard u;
    private Object v;
    public String w;
    private Thread x;
    public volatile boolean y;
    private volatile boolean z;
    public volatile boolean aa;
    private PopupMenu ab;
    private MenuItem ac;
    private int ad;
    private Vector ae;
    public boolean af;
    private boolean ag;
    private static int ah;
    private static boolean ai;
    private static boolean aj;
    private boolean ak;
    private String al;
    private Cursor am;
    public boolean an;
    private boolean ao;
    private boolean ap;
    public boolean aq;
    private Vector ar;
    public boolean as;
    private Container at;
    public static Cursor au;
    public double av;
    public int aw;
    public boolean ax;
    public static int ay;
    public static int az;
    public static boolean a0;
    private static String a1;
    public static boolean a2;
    public static long a3;
    public boolean a4;
    public long a5;
    private boolean a6;
    public Method a7;
    private boolean a8;
    private boolean a9;
    public static /* synthetic */ Class ba;
    public static /* synthetic */ Class bb;
    public static /* synthetic */ Class bc;
    public static /* synthetic */ Class bd;
    public static /* synthetic */ Class be;
    public static /* synthetic */ Class bf;
    public static /* synthetic */ Class bg;
    public static /* synthetic */ Class bh;
    
    public MesonApplet() {
        this.n = false;
        this.o = 1.0f;
        this.p = new Dimension(1920, 1080);
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.aw = 64;
        this.a5 = 0L;
        this.a7 = null;
    }
    
    public static synchronized String a() {
        return "BBx" + Long.toHexString(++MesonApplet.a3);
    }
    
    public static boolean HEADLESS_MODE() {
        return MesonApplet.c;
    }
    
    public static boolean CLIENT_IS_iPAD() {
        return MesonApplet.j;
    }
    
    public static boolean CLIENT_IS_ANDROID() {
        return MesonApplet.k;
    }
    
    public void init() {
        System.gc();
        this.f();
        MesonApplet.aj = true;
        this.ao = "strata".equalsIgnoreCase(this.getParameter("brand"));
        this.ap = "long".equalsIgnoreCase(this.getParameter("patent"));
        if (!Blackboard.quietMode) {
            System.err.println("Meson Applet.  Copyright 2005-2010, Kaon Interactive Inc.");
        }
        this.ag = false;
        MesonApplet.ah = 0;
        this.ak = false;
        final String parameter = this.getParameter("blackboard");
        String parameter2 = this.getParameter("mesonName");
        if (parameter2 == null) {
            parameter2 = "Primary";
        }
        this.w = ("Applet." + parameter2).intern();
        (this.u = Blackboard.getInstance((parameter == null) ? a() : parameter)).setGlobal((this.w + ".javaApplet").intern(), this);
        this.u.setGlobal("Meson.documentBase", this.getDocumentBase());
        this.u.setGlobal("Meson.codeBase", this.getCodeBase());
        this.u.invoke("Meson.setCurrentDataSource", this.getDocumentBase());
        this.u.setGlobal("Meson.displayDepth", (this.getColorModel().getPixelSize() < 24) ? "16" : "32");
        this.u.setGlobal("Meson.isMac", System.getProperty("os.name").startsWith("Mac") ? "true" : "false");
        this.u.setGlobal("Meson.isWindows", System.getProperty("os.name").startsWith("Windows") ? "true" : "false");
        this.u.setGlobal("Meson.language", Locale.getDefault().getLanguage().intern());
        final StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.version"), "._ /-");
        double n = 0.0;
        double n2 = 1.0;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                if (int1 > 9) {
                    n2 /= Math.pow(10.0, Math.ceil(Math.log(int1) / Math.log(10.0)) - 1.0);
                }
                n += int1 * n2;
                n2 /= 10.0;
            }
            catch (Throwable t) {}
        }
        this.u.setGlobal("Meson.javaVersion", new Double(n));
        this.ar = new Vector();
        this.ae = new Vector();
        this.ab = new PopupMenu();
        if (!this.g()) {
            this.ab.addSeparator();
            if (this.ao) {
                this.ac = new MenuItem("About Strata...");
            }
            else {
                this.ac = new MenuItem("About Kaon...");
            }
            this.ac.addActionListener(this);
            this.ab.add(this.ac);
            this.ad = 2;
        }
        this.add(this.ab);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addFocusListener(this);
        try {
            this.getClass().getMethod("setFocusTraversalKeysEnabled", Boolean.TYPE).invoke(this, Boolean.FALSE);
        }
        catch (Exception ex) {}
    }
    
    private void f() {
        try {
            final byte[] array = new byte[54525952];
        }
        catch (Throwable t) {}
        System.gc();
        Long n = null;
        final String property = System.getProperty("java.version");
        this.ax = property.startsWith("1.1");
        if (!property.startsWith("1.1") && !property.startsWith("1.2") && !property.startsWith("1.3")) {
            try {
                n = (Long)((MesonApplet.ba == null) ? (MesonApplet.ba = class$("java.lang.Runtime")) : MesonApplet.ba).getMethod("maxMemory", (Class[])new Class[0]).invoke(Runtime.getRuntime(), new Object[0]);
                this.aw = (int)(n / 1024L / 1024L);
                if (!Blackboard.quietMode) {
                    System.err.println("Detected maximum memory -Xmx" + this.aw + "M");
                }
            }
            catch (Exception ex) {}
        }
        if (n == null) {
            try {
                final byte[] array2 = new byte[79691776];
            }
            catch (Throwable t2) {
                MesonApplet.a2 = true;
            }
        }
        else {
            MesonApplet.a2 = (n < 79691776L);
        }
        if (MesonApplet.a2 && "false".equalsIgnoreCase(this.getParameter("lowMemoryCheck"))) {
            System.err.println("Not using low-memory footprint mode because lowMemoryCheck applet parameter is false");
            MesonApplet.a2 = false;
        }
        if (MesonApplet.a2 && !Blackboard.quietMode) {
            if (System.getProperty("java.vendor").startsWith("IBM") || System.getProperty("java.vendor").startsWith("Microsoft")) {
                System.err.println("Using low-memory footprint mode.  Using an updated JVM will improve appearance and performance.");
            }
            else {
                System.err.println("Using low-memory footprint mode.  Use -Xmx96M JVM argument to improve appearance and performance.");
            }
        }
        System.gc();
    }
    
    private boolean g() {
        try {
            if (!System.getProperty("java.version").startsWith("1.1") && !"".equals(System.getProperty("meson.sysid", ""))) {
                return true;
            }
        }
        catch (Exception ex) {}
        try {
            return this.getCodeBase().getHost().endsWith(".webcollage.net");
        }
        catch (Exception ex2) {
            return false;
        }
    }
    
    public String getParameter(String substring) {
        if (substring.startsWith("?")) {
            substring = substring.substring(1);
            if (!Blackboard.quietMode) {
                System.out.println("meson> " + substring);
            }
            this.u.loadParameter(substring);
            return "";
        }
        return super.getParameter(substring);
    }
    
    private void h() {
        try {
            this.u = (Blackboard)Class.forName("java.lang.ref.SoftReference").getMethod("get", (Class<?>[])new Class[0]).invoke(this.v, new Object[0]);
        }
        catch (Exception ex) {}
        this.v = null;
    }
    
    private void i() {
        String s = this.getParameter("meson");
        if (s != null && !"".equals(s)) {
            this.ak = true;
            int index;
            while ((index = s.indexOf("&quot;")) != -1) {
                s = s.substring(0, index) + '\"' + s.substring(index + 6);
            }
            int index2;
            while ((index2 = s.indexOf("&gt;")) != -1) {
                s = s.substring(0, index2) + '>' + s.substring(index2 + 4);
            }
            int index3;
            while ((index3 = s.indexOf("&lt;")) != -1) {
                s = s.substring(0, index3) + '<' + s.substring(index3 + 4);
            }
            int index4;
            while ((index4 = s.indexOf("&amp;")) != -1) {
                s = s.substring(0, index4) + '&' + s.substring(index4 + 5);
            }
            this.u.load("HTML Page", s.replace('\n', ' ').replace('\r', ' '));
        }
        if (!this.ak && "".equals(System.getProperty("browser"))) {
            this.ak = true;
        }
    }
    
    public void start() {
        if (this.v != null) {
            this.h();
            if (this.u == null) {
                System.err.println("(BB lost while stopped)");
                this.init();
            }
        }
        this.u.referenceCount(1);
        if (!this.ak) {
            this.i();
            if (!this.ak) {
                System.err.println("Warning: Applet started with without 'meson' parameter.");
            }
        }
        if (this.x == null) {
            this.af = true;
            this.x = new Thread(this, "Meson Applet");
            if (!this.ag) {
                this.ag = true;
                final String s = (String)this.u.getGlobal(this.w + ".image", (MesonApplet.bb == null) ? (MesonApplet.bb = class$("java.lang.String")) : MesonApplet.bb);
                if (s != "") {
                    try {
                        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                        final Image image = defaultToolkit.getImage(new URL(this.getDocumentBase(), s));
                        this.u.setGlobal(this.w + ".javaImage", image);
                        defaultToolkit.prepareImage(image, -1, -1, this);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            this.x.start();
        }
    }
    
    public void stop() {
        this.x = null;
        this.u.referenceCount(-1);
        if (!System.getProperty("java.version").startsWith("1.1")) {
            try {
                this.v = Class.forName("java.lang.ref.SoftReference").getConstructor((MesonApplet.bc == null) ? (MesonApplet.bc = class$("java.lang.Object")) : MesonApplet.bc).newInstance(this.u);
                this.u = null;
            }
            catch (Exception ex) {}
        }
    }
    
    public void destroy() {
        if (this.v != null) {
            this.h();
        }
        if (this.u == null) {
            return;
        }
        if (!Blackboard.quietMode) {
            System.err.println("Dropping reference to " + this.u);
        }
        this.u.setGlobal((this.w + ".javaApplet").intern(), "");
        this.u = null;
        System.gc();
    }
    
    public Dimension b() {
        if (this.n) {
            return new Dimension(this.p);
        }
        return this.getSize();
    }
    
    public void run() {
        try {
            while (Thread.currentThread() == this.x && this.u != null) {
                if (!this.ak) {
                    this.i();
                }
                final Dimension b = this.b();
                if (this.as) {
                    final Dimension dimension = b;
                    --dimension.width;
                }
                this.av = (double)this.u.getGlobal(this.w + ".twist", (MesonApplet.bd == null) ? (MesonApplet.bd = class$("java.lang.Double")) : MesonApplet.bd);
                if (this.av != 0.0 && !this.n) {
                    final int width = b.width;
                    b.width = b.height;
                    b.height = width;
                }
                if (b.width != (int)(0.5 + (double)this.u.getGlobal(this.w + ".width", (MesonApplet.bd == null) ? (MesonApplet.bd = class$("java.lang.Double")) : MesonApplet.bd)) || b.height != (int)(0.5 + (double)this.u.getGlobal(this.w + ".height", (MesonApplet.bd == null) ? (MesonApplet.bd = class$("java.lang.Double")) : MesonApplet.bd))) {
                    this.u.queueAssignments(new Object[] { this.w + ".width", new Double(b.width), this.w + ".height", new Double(b.height) });
                }
                this.u.a();
                if (this.u == null) {
                    return;
                }
                this.j();
                if (this.u == null) {
                    return;
                }
                if (this.u.getGlobal(this.w + ".pleaseRepaint", (MesonApplet.bb == null) ? (MesonApplet.bb = class$("java.lang.String")) : MesonApplet.bb) == "true" || this.aa) {
                    this.u.setGlobal(this.w + ".pleaseRepaint", "false");
                    final boolean aa = false;
                    this.z = aa;
                    this.y = aa;
                    this.aa = aa;
                    this.repaint();
                    Thread.yield();
                    int n = 0;
                    while (!this.y && Thread.currentThread() == this.x) {
                        MesonApplet.ai = true;
                        final int n2 = appletHasFocus() ? 3 : 33;
                        this.a(n2);
                        if (!this.z) {
                            n += n2;
                            if (n <= 3000) {
                                continue;
                            }
                            this.d();
                            this.repaint();
                            n = 0;
                        }
                    }
                }
                else {
                    MesonApplet.ai = true;
                    Thread.sleep(33L);
                }
            }
        }
        catch (InterruptedException ex) {}
        catch (Error error) {
            if (this.u != null) {
                error.printStackTrace();
            }
        }
        catch (Throwable t) {
            if (this.u != null) {
                t.printStackTrace();
            }
        }
    }
    
    public void a(final String s, final int n, final int n2) {
        try {
            this.a(this.getClass().getMethod("doMoveToDialog", (MesonApplet.bb == null) ? (MesonApplet.bb = class$("java.lang.String")) : MesonApplet.bb, Integer.TYPE, Integer.TYPE), this, new Object[] { s, new Integer(n), new Integer(n2) });
        }
        catch (Exception ex) {}
    }
    
    public void doMoveToDialog(final String s, final int n, final int n2) {
        if (this.a4) {
            return;
        }
        if (this.at == null) {
            this.at = this.getParent();
        }
        final Frame frame = new Frame(s);
        frame.setSize(n, n2);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - n) / 2, (screenSize.height - n2) / 2);
        frame.add(this);
        frame.addWindowListener(this);
        frame.show();
        this.a4 = true;
    }
    
    public void c() {
        try {
            this.a(this.getClass().getMethod("doCloseDialog", (Class<?>[])new Class[0]), this, new Object[0]);
        }
        catch (Exception ex) {}
    }
    
    public void doCloseDialog() {
        if (!this.a4) {
            return;
        }
        final Container parent = this.getParent();
        if (this.at != null) {
            this.at.add(this);
            this.at.invalidate();
            this.at.validate();
        }
        if (parent instanceof Frame) {
            ((Frame)parent).dispose();
        }
        this.a4 = false;
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.doCloseDialog();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    private synchronized void a(final int n) {
        try {
            this.wait(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public synchronized void a(final boolean y) {
        this.y = y;
        this.notify();
    }
    
    public void d() {
    }
    
    private void j() {
        final String al = (String)this.u.getGlobal(this.w + ".useCursor", (MesonApplet.bb == null) ? (MesonApplet.bb = class$("java.lang.String")) : MesonApplet.bb);
        if (al == this.al) {
            return;
        }
        if ((this.al = al) == "_HIDE") {
            this.am = MesonApplet.au;
        }
        else if (al == "_HAND") {
            this.am = Cursor.getPredefinedCursor(12);
        }
        else if (al == "_MOVE") {
            this.am = Cursor.getPredefinedCursor(13);
        }
        else if (al == "_CROSSHAIR") {
            this.am = Cursor.getPredefinedCursor(1);
        }
        else if (al == "_WAIT") {
            this.am = Cursor.getPredefinedCursor(3);
        }
        else {
            final int digit;
            if (al.length() > 0 && (digit = Character.digit(al.charAt(0), 36)) != -1) {
                this.am = Cursor.getPredefinedCursor(digit);
            }
            else {
                this.am = Cursor.getDefaultCursor();
            }
        }
        this.aa = true;
    }
    
    public void paint(final Graphics graphics) {
        this.u.setGlobal(this.w + ".dirtyState", "_SELF");
        this.aa = true;
        if (!this.z) {
            this.update(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.am != null) {
            this.setCursor(this.am);
            this.am = null;
        }
        while (!this.ar.isEmpty()) {
            final Object[] array = this.ar.firstElement();
            this.ar.removeElementAt(0);
            try {
                ((Method)array[0]).invoke(array[1], (Object[])array[2]);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.z = true;
        if (this.u != null) {
            while (!this.ae.isEmpty()) {
                final String s = this.ae.firstElement();
                this.ae.removeElementAt(0);
                if (s == MesonApplet.a1) {
                    while (this.ab.getItemCount() != this.ad) {
                        this.ab.remove(0);
                    }
                }
                else if (s == "-") {
                    this.ab.insertSeparator(this.ab.getItemCount() - this.ad);
                }
                else {
                    final MenuItem menuItem = new MenuItem(s);
                    menuItem.addActionListener(this);
                    this.ab.insert(menuItem, this.ab.getItemCount() - this.ad);
                }
            }
            if (this.af) {
                this.requestFocus();
                this.af = false;
            }
            this.u.invoke(this.w + ".paintInternal", "");
            if (graphics != null) {
                final Image image = (Image)this.u.getGlobal(this.w + ".javaImage", (MesonApplet.be == null) ? (MesonApplet.be = class$("java.awt.Image")) : MesonApplet.be);
                if (image != null) {
                    if (this.av != 0.0 && !this.ax) {
                        try {
                            graphics.getClass().getMethod("rotate", Double.TYPE, Double.TYPE, Double.TYPE).invoke(graphics, new Double(this.av * -0.017453292519943295), new Double(0.0), new Double(0.0));
                            if (this.av > 0.0) {
                                graphics.drawImage(image, -this.size().height, 0, null);
                            }
                            else {
                                graphics.drawImage(image, 0, -this.size().width, null);
                            }
                        }
                        catch (Exception ex2) {}
                    }
                    else {
                        final FastVector fastVector = (FastVector)this.u.getGlobal(this.w + ".dirtyRects", (MesonApplet.bf == null) ? (MesonApplet.bf = class$("com.kaon.meson.FastVector")) : MesonApplet.bf);
                        for (int i = 0; i < fastVector.size(); ++i) {
                            final Rectangle rectangle = (Rectangle)fastVector.get(i);
                            graphics.setClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                            graphics.drawImage(image, 0, 0, null);
                        }
                    }
                }
                else {
                    final Integer n = (Integer)this.u.getGlobal(this.w + ".fill", (MesonApplet.bg == null) ? (MesonApplet.bg = class$("java.lang.Integer")) : MesonApplet.bg);
                    if (n != null) {
                        graphics.setColor(new Color(n));
                        graphics.fillRect(0, 0, this.size().width, this.size().height);
                    }
                }
            }
        }
        if (this.a5 >= 0L && graphics != null) {
            if (this.a5 == 0L) {
                this.a5 = System.currentTimeMillis();
            }
            else if (System.currentTimeMillis() - this.a5 > 5000L) {
                this.a5 = -1L;
                if (this.u != null) {
                    graphics.setClip(0, 0, this.size().width, this.size().height);
                    final Image image2 = (Image)this.u.getGlobal(this.w + ".javaImage", (MesonApplet.be == null) ? (MesonApplet.be = class$("java.awt.Image")) : MesonApplet.be);
                    if (image2 != null) {
                        graphics.drawImage(image2, 0, 0, null);
                    }
                }
            }
            else {
                graphics.setClip(0, 0, this.size().width, this.size().height);
                graphics.setColor(Color.gray);
                graphics.setFont(this.getFont());
                graphics.drawString(this.ap ? "3D Viewer Protected by US Patent 7,079,139" : "Patent 7,079,139", 0, this.size().height - 2);
            }
        }
        if (!this.aq) {
            this.a(true);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.u == null) {
            return false;
        }
        if (image != this.u.getGlobal(this.w + ".javaImage", null)) {
            return false;
        }
        this.aa = true;
        return n != 32;
    }
    
    public void a(final Method method, final Object o, final Object[] array) {
        this.ar.addElement(new Object[] { method, o, array });
        this.aa = true;
    }
    
    private boolean k() {
        if (this.a8) {
            return this.a9;
        }
        this.a8 = true;
        this.a9 = false;
        try {
            if (!System.getProperty("java.version").startsWith("1.1") && !System.getProperty("java.version").startsWith("1.2")) {
                if (this.a7 == null) {
                    this.a7 = ((MesonApplet.bh == null) ? (MesonApplet.bh = class$("java.awt.Toolkit")) : MesonApplet.bh).getMethod("getLockingKeyState", Integer.TYPE);
                }
                this.a7.invoke(Toolkit.getDefaultToolkit(), new Integer(20));
                return this.a9 = true;
            }
        }
        catch (Throwable t) {}
        return false;
    }
    
    private boolean l() {
        try {
            if (this.k()) {
                return (boolean)this.a7.invoke(Toolkit.getDefaultToolkit(), new Integer(20));
            }
        }
        catch (Throwable t) {}
        return this.a6;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        String s = null;
        switch (keyEvent.getKeyCode()) {
            case 17: {
                this.u.queueAssignment(this.w + ".ctrl", "true");
                return;
            }
            case 16: {
                this.u.queueAssignment(this.w + ".shift", "true");
                return;
            }
            case 157: {
                this.u.queueAssignment(this.w + ".meta", "true");
                return;
            }
            case 20: {
                this.a6 = true;
                this.u.queueAssignment(this.w + ".shift", this.l() ? "true" : "false");
                return;
            }
            case 10: {
                s = "_ENTER";
                break;
            }
            case 37: {
                s = "_LEFT";
                break;
            }
            case 39: {
                s = "_RIGHT";
                break;
            }
            case 38: {
                s = "_UP";
                break;
            }
            case 40: {
                s = "_DOWN";
                break;
            }
            case 9: {
                s = "_TAB";
                break;
            }
            case 127: {
                s = "_DELETE";
                break;
            }
            case 8: {
                s = "_BACK";
                break;
            }
            case 155: {
                s = "_INSERT";
                break;
            }
            case 36: {
                s = "_HOME";
                break;
            }
            case 35: {
                s = "_END";
                break;
            }
            case 33: {
                s = "_PAGE_UP";
                break;
            }
            case 34: {
                s = "_PAGE_DOWN";
                break;
            }
            case 27: {
                s = "_ESCAPE";
                break;
            }
            default: {
                return;
            }
        }
        this.u.queueAssignment(this.w + ".key", s);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (keyCode == 17) {
            this.u.queueAssignment(this.w + ".ctrl", "false");
        }
        else if (keyCode == 157) {
            this.u.queueAssignment(this.w + ".meta", "false");
        }
        else if (keyCode == 16) {
            this.u.queueAssignment(this.w + ".shift", this.l() ? "true" : "false");
        }
        else if (keyCode == 20) {
            this.a6 = false;
            this.u.queueAssignment(this.w + ".shift", this.l() ? "true" : "false");
        }
        else {
            this.u.queueAssignment(this.w + ".key", "");
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        if (keyChar == '\b' || keyChar == '\n' || keyChar == '\t' || keyChar == '\u007f') {
            return;
        }
        if (keyChar != '\0') {
            this.u.queueAssignment(this.w + ".key", new Character(keyChar).toString().intern());
        }
    }
    
    private String a(final MouseEvent mouseEvent) {
        if (mouseEvent.isShiftDown() || this.l()) {
            return "true";
        }
        return "false";
    }
    
    private void a(final MouseEvent mouseEvent, final boolean b) {
        MesonApplet.ai = false;
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (this.av > 0.0) {
            final int n = y;
            y = x;
            x = this.getSize().height - n;
        }
        else if (this.av < 0.0) {
            final int n2 = x;
            x = y;
            y = this.getSize().width - n2;
        }
        if (this.n) {
            if (this.av == 0.0) {
                x = (int)((x - this.q) / this.o);
                y = (int)((y - this.r) / this.o);
            }
            else {
                x = (int)((x - this.r) / this.o);
                y = (int)((y - this.q) / this.o);
            }
        }
        if (MesonApplet.a0) {
            this.u.queueAssignments(new Object[] { this.w + ".mouseX", new Double(x), this.w + ".mouseY", new Double(y), this.w + ".press", b ? "true" : "false", this.w + ".shift", this.a(mouseEvent), this.w + ".ctrl", mouseEvent.isControlDown() ? "true" : "false", this.w + ".meta", (b && mouseEvent.isMetaDown()) ? "true" : "false" });
        }
        else {
            this.u.queueAssignments(new Object[] { this.w + ".mouseX", new Double(x), this.w + ".mouseY", new Double(y), this.w + ".press", b ? "true" : "false", this.w + ".shift", this.a(mouseEvent), this.w + ".ctrl", mouseEvent.isControlDown() ? "true" : "false" });
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.an && (mouseEvent.getModifiers() & 0x10) == 0x0) {
            this.requestFocus();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.a(mouseEvent, false);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.a(mouseEvent, true);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a(mouseEvent, false);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (MesonApplet.a0) {
            this.a(mouseEvent, true);
        }
        else if (mouseEvent.isMetaDown()) {
            this.ab.show(this, MesonApplet.ay = mouseEvent.getX(), MesonApplet.az = mouseEvent.getY());
        }
        else {
            this.a(mouseEvent, true);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.u != null) {
            this.u.queueAssignments(new Object[] { this.w + ".mouseX", new Double(-1.0), this.w + ".mouseY", new Double(-1.0) });
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        b(1);
        if (this.u != null) {
            this.u.queueAssignment(this.w + ".focus", "true");
            this.u.queueAssignment(this.w + ".shift", this.l() ? "true" : "false");
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        b(-1);
        if (this.u != null) {
            this.u.queueAssignments(new Object[] { this.w + ".ctrl", "false", this.w + ".shift", "false", this.w + ".key", "", this.w + ".focus", "false" });
        }
    }
    
    private static synchronized void b(final int n) {
        MesonApplet.ah += n;
    }
    
    public static boolean appletHasFocus() {
        return MesonApplet.ah != 0;
    }
    
    public static boolean appletIsIdle() {
        return !MesonApplet.aj || MesonApplet.ai;
    }
    
    public static void appletSetBusy() {
        MesonApplet.ai = false;
    }
    
    public static boolean lowMemory() {
        return MesonApplet.a2;
    }
    
    public void a(final String s) {
        this.ae.addElement(s);
    }
    
    public void e() {
        this.ae.addElement(MesonApplet.a1);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == this.ac) {
                this.getAppletContext().showDocument(new URL(this.ao ? "http://www.strata.com/rd/live3dmenu.html" : "http://www.kaon.com/about"), "_blank");
            }
            else {
                this.u.queueAssignment(this.w + ".menuItem", actionEvent.getActionCommand().intern());
            }
        }
        catch (Exception ex) {}
    }
    
    public Dimension getSize() {
        if (MesonApplet.c) {
            return new Dimension(MesonApplet.e, MesonApplet.f);
        }
        return super.getSize();
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MesonApplet.a = true;
        MesonApplet.b = null;
        MesonApplet.c = false;
        MesonApplet.d = false;
        MesonApplet.e = 1920;
        MesonApplet.f = 1080;
        MesonApplet.g = Integer.MAX_VALUE;
        MesonApplet.i = false;
        MesonApplet.j = false;
        MesonApplet.k = false;
        MesonApplet.l = false;
        MesonApplet.m = true;
        MesonApplet.au = Cursor.getPredefinedCursor(1);
        MesonApplet.a0 = false;
        MesonApplet.a1 = "___FLUSH___";
        MesonApplet.a3 = System.currentTimeMillis();
    }
}
