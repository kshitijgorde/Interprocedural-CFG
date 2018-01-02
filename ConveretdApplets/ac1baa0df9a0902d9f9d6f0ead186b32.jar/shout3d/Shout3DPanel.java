// 
// Decompiled by Procyon v0.5.30
// 

package shout3d;

import shout3d.core.CoreShout3DViewer;
import shout3d.core.f;
import shout3d.core.Picker;
import shout3d.core.ImageTexture;
import shout3d.core.h;
import java.net.MalformedURLException;
import shout3d.core.DeviceInput;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.Date;
import shout3d.core.Renderer;
import java.awt.Color;
import shout3d.core.NavigationInfo;
import shout3d.core.Viewpoint;
import shout3d.core.ResourceListener;
import shout3d.core.Shout3DException;
import java.net.URL;
import shout3d.core.Group;
import shout3d.core.DeviceObserver;
import shout3d.core.Bindable;
import shout3d.core.Node;
import shout3d.core.Field;
import java.awt.Component;
import shout3d.core.d;
import java.util.Vector;
import shout3d.core.WindowInput;
import shout3d.core.KeyboardInput;
import shout3d.core.MouseInput;
import shout3d.core.TimeSensor;
import shout3d.core.Transform;
import shout3d.core.g;
import shout3d.core.Searcher;
import shout3d.core.Background;
import shout3d.core.Mmm;
import java.awt.Graphics;
import java.awt.Image;
import shout3d.core.z;
import shout3d.core.RenderObserver;
import shout3d.core.DeviceListener;
import shout3d.core.Clock;
import shout3d.core.Shout3DViewer;
import java.awt.Panel;

public class Shout3DPanel extends Panel implements Shout3DViewer, Runnable, Clock, DeviceListener, RenderObserver
{
    static final String a = "STOP IMMEDIATELY!";
    static final String b = "Copyright (c) 1997-1999 Shout Interactive, Inc.";
    static final String c = "Contents of this file are property of Shout Interactive, Inc.";
    static final String d = "If you are reading this text, then you are in direct violation";
    static final String e = "of the terms of use and the terms of the license agreement.";
    static final String f = "YOU MUST CEASE YOUR EXAMINATION OF THIS FILE AND DESTROY THIS FILE IMMEDIATELY.";
    boolean g;
    private boolean h;
    private z i;
    private boolean j;
    private boolean k;
    private boolean l;
    public Shout3DApplet applet;
    private Image m;
    private Graphics n;
    private Image o;
    private Graphics p;
    private final Mmm q;
    protected boolean r;
    protected boolean s;
    private Background t;
    private long u;
    private boolean v;
    private int w;
    private int x;
    protected c y;
    private Searcher z;
    private g A;
    private Transform B;
    private boolean C;
    protected boolean D;
    protected Thread E;
    private boolean F;
    private boolean G;
    private String H;
    private String I;
    private int J;
    private int K;
    protected boolean L;
    protected boolean M;
    private boolean N;
    protected long O;
    protected TimeSensor[] P;
    private long Q;
    boolean R;
    float S;
    int T;
    float[] U;
    long V;
    private MouseInput W;
    private KeyboardInput X;
    private WindowInput Y;
    private boolean Z;
    private boolean ba;
    protected Vector bb;
    protected Vector bc;
    protected Vector bd;
    protected Vector be;
    protected Vector bf;
    protected Vector bg;
    protected Vector bh;
    protected Vector bi;
    protected Vector bj;
    protected Vector bk;
    protected Vector bl;
    protected Vector bm;
    private double bn;
    private d bo;
    
    public final DeviceListener getDeviceListener() {
        return this;
    }
    
    private final void c() {
        if (this.g) {
            final String parameter = this.applet.getParameter("bilinearFiltering");
            if (parameter != null && parameter.equals("true")) {
                this.setBilinearFiltering(true);
                return;
            }
            this.setBilinearFiltering(false);
        }
    }
    
    public final String getVersion() {
        return "Shout3D Version 1.0.3 -- final build 2/21/2000";
    }
    
    public final Clock getClock() {
        return this;
    }
    
    public final Component b() {
        if (this.applet != null) {
            return this.applet;
        }
        return this;
    }
    
    public boolean isRouted(final Field field, final Field field2) {
        return field.isRouted(field2);
    }
    
    protected final synchronized void d() {
        this.G = true;
        this.notifyAll();
    }
    
    private final void e() {
        this.a((Node)this.B);
    }
    
    protected final void a(final Node node) {
        this.P = null;
        final Node[] a = node.a((Node[])null);
        if (a == null) {
            return;
        }
        this.P = new TimeSensor[a.length];
        for (int i = 0; i < a.length; ++i) {
            this.P[i] = (TimeSensor)a[i];
        }
    }
    
    private final Bindable a(final String type) {
        Bindable bindable = null;
        if (this.z == null) {
            this.z = this.getNewSearcher();
        }
        this.z.setNode(null);
        this.z.setDefName(null);
        this.z.setType(type);
        final Node[][] searchAll = this.z.searchAll(this.B);
        if (searchAll != null && searchAll.length >= 1) {
            bindable = (Bindable)searchAll[0][searchAll[0].length - 1];
            for (int i = 0; i < searchAll.length; ++i) {
                final Bindable bindable2 = (Bindable)searchAll[i][searchAll[i].length - 1];
                if (bindable2.isBound.getValue()) {
                    return bindable2;
                }
            }
        }
        return bindable;
    }
    
    public final void removeDeviceObserver(final DeviceObserver deviceObserver, final String s) {
        if (s.equals("MouseInput") || s.equals("DeviceInput")) {
            if (deviceObserver instanceof Node || deviceObserver instanceof z) {
                this.a(this.bb, this.bc, deviceObserver);
            }
            else {
                this.a(this.bh, this.bi, deviceObserver);
            }
        }
        if (s.equals("KeyboardInput") || s.equals("DeviceInput")) {
            if (deviceObserver instanceof Node || deviceObserver instanceof z) {
                this.a(this.bd, this.be, deviceObserver);
            }
            else {
                this.a(this.bj, this.bk, deviceObserver);
            }
        }
        if (s.equals("WindowInput") || s.equals("DeviceInput")) {
            if (deviceObserver instanceof Node || deviceObserver instanceof z) {
                this.a(this.bf, this.bg, deviceObserver);
                return;
            }
            this.a(this.bl, this.bm, deviceObserver);
        }
    }
    
    public final void setSceneFromURL(final String[] array) throws Shout3DException {
        final URL a = this.a(array);
        final Transform scene = new Transform();
        if (this.g) {
            this.y = new c(this.applet, this, a, this.h, scene);
        }
        else {
            this.y = new c(this, this, a, this.h, scene);
        }
        if (this.i == null) {
            (this.i = new z()).a(this);
        }
        try {
            this.a().a(this, this.y, false);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.setScene(scene);
    }
    
    public final Bindable getCurrentBindableNode(final String s) throws Shout3DException {
        if (s.equals("Viewpoint")) {
            return this.A.a();
        }
        if (s.equals("Background")) {
            return this.A.z();
        }
        if (s.equals("NavigationInfo")) {
            return this.A.C();
        }
        throw new Shout3DException("panel was asked for unknown type of bindable node");
    }
    
    private final void a(final KeyboardInput keyboardInput) {
        if (keyboardInput.key == 102 || keyboardInput.key == 70) {
            this.C = !this.C;
        }
    }
    
    public boolean addRoute(final Field field, final Field field2) throws Shout3DException {
        if (!field2.isOfType(field.getTypeName())) {
            throw new Shout3DException("Shout3DPanel can not create route between two different field types " + field.getTypeName() + " and " + field2.getTypeName());
        }
        return field.addRoute(field2);
    }
    
    public boolean deleteRoute(final Field field, final Field field2) {
        return field.deleteRoute(field2);
    }
    
    private final void f() {
        this.D = true;
        System.out.println("window deiconified");
    }
    
    public final String getProfile() {
        return "Core Shout3D Profile";
    }
    
    public final ResourceListener getResourceListener() {
        return this.a();
    }
    
    private void a(final Vector vector, final Vector vector2, final DeviceObserver deviceObserver) {
        if (vector != null && vector2 != null) {
            final int index = vector.indexOf(deviceObserver);
            if (index != -1) {
                vector.removeElement(deviceObserver);
                vector2.removeElementAt(index);
            }
        }
    }
    
    protected final void g() {
        this.Q = System.currentTimeMillis();
        this.r();
        this.getClock().tick();
        this.a((Node)this.getScene());
        if (this.P != null) {
            for (int i = 0; i < this.P.length; ++i) {
                this.P[i].update();
            }
        }
    }
    
    public final void a(final Bindable node) {
        if (!node.isBound.getValue()) {
            if (this.A.a() == node && !this.j) {
                this.A.a(null, (Node[])null);
            }
            if (this.A.z() == node && !this.k) {
                this.A.a((Background)null);
            }
            if (this.A.C() == node && !this.l) {
                this.A.a(this.getScene(), null);
            }
            return;
        }
        if (this.z == null) {
            this.z = this.getNewSearcher();
        }
        if (node.isOfType("Viewpoint")) {
            this.j = true;
            this.z.setType(null);
            this.z.setDefName(null);
            this.z.setNode(node);
            this.A.a((Viewpoint)node, this.z.searchFirst(this.getScene()));
        }
        else if (node.isOfType("Background")) {
            this.k = true;
            this.A.a((Background)node);
        }
        else if (node.isOfType("NavigationInfo")) {
            this.l = true;
            this.A.a(this.getScene(), (NavigationInfo)node);
        }
        this.z.setNode(null);
        this.z.setDefName(null);
        this.z.setType(node.getTypeName());
        final Node[][] searchAll = this.z.searchAll(this.getScene());
        if (searchAll != null) {
            for (int i = 0; i < searchAll.length; ++i) {
                if (searchAll[i] != null) {
                    final Node node2 = searchAll[i][searchAll[i].length - 1];
                    if (node2 != node && ((Bindable)node2).isBound.getValue()) {
                        ((Bindable)node2).isBound.setValue(false);
                    }
                }
            }
        }
        final Viewpoint b = this.A.B();
        if (node.isOfType("Viewpoint") && b != null && b != node && b.isBound.getValue()) {
            this.A.B().isBound.setValue(false);
        }
        if (node.isOfType("Background") && this.t != null && this.t != node && this.t.isBound.getValue()) {
            this.t.isBound.setValue(false);
        }
        if (node.isOfType("Viewpoint")) {
            this.j = false;
            return;
        }
        if (node.isOfType("Background")) {
            this.k = false;
            return;
        }
        if (node.isOfType("NavigationInfo")) {
            this.l = false;
        }
    }
    
    public final void setAntiAliased(final boolean b) {
        this.A.e(b);
    }
    
    public final void setLoadResourcesInSeparateThread(final boolean b) {
        this.bo.a(b);
    }
    
    public final float getFramesPerSecond() {
        if (this.S == 0.0f) {
            return 0.0f;
        }
        return (int)(1.0f / this.S * 100.0f) / 100.0f;
    }
    
    private final void h() {
        if (this.g) {
            final String parameter = this.applet.getParameter("antiAliasingEnabled");
            if (parameter != null && parameter.equals("true")) {
                this.setAntiAliased(true);
                return;
            }
            this.setAntiAliased(false);
        }
    }
    
    protected final void a(final Graphics graphics) {
        if (this.getScene() != null) {
            if (this.D) {
                this.A.render(this.getScene());
            }
            if (this.C) {
                graphics.setColor(Color.black);
                graphics.fillRect(10, this.x - 30, 40, 15);
                graphics.setColor(Color.white);
                graphics.drawString("" + this.getFramesPerSecond(), 12, this.x - 17);
            }
        }
    }
    
    protected final void i() {
        if (this.y != null && this.y.h()) {
            this.q.m.setValue(this.y.o());
            this.q.f.setValue(this.y.i());
            if (!this.L) {
                this.A.render(this.q);
            }
        }
    }
    
    public void onPostRender(final Renderer renderer, final Object o) {
    }
    
    public final void run() {
        if (!this.L) {
            this.s = false;
            this.o();
            this.O = new Date().getTime();
        }
        if (!this.s) {
            while (this.E != null && this.getScene() != null && !this.r) {
                Thread.yield();
                if (this.v) {
                    try {
                        Thread.sleep(this.u);
                    }
                    catch (Exception ex) {}
                }
                if (this.L) {
                    if (this.y != null && this.y.h()) {
                        this.A.render(this.q);
                    }
                    else {
                        this.g();
                        this.a(this.getGraphics());
                    }
                }
                this.repaint();
                this.l();
            }
        }
    }
    
    protected final void j() {
        System.gc();
        System.gc();
        System.gc();
        this.setLayout(null);
        this.setFont(new Font("Dialog", 0, 10));
        final FontMetrics fontMetrics = this.getToolkit().getFontMetrics(this.getFont());
        final int maxAscent = fontMetrics.getMaxAscent();
        if (System.getProperty("java.version").indexOf("1.0") > -1) {
            this.N = false;
        }
        else {
            this.N = true;
        }
        this.J = fontMetrics.stringWidth(this.H) >> 1;
        this.K = maxAscent >> 1;
        this.repaint();
    }
    
    protected final synchronized void l() {
        System.currentTimeMillis();
        while (!this.G && !this.r) {
            try {
                this.wait();
            }
            catch (Exception ex) {}
        }
        this.G = false;
    }
    
    public final boolean handleEvent(final Event event) {
        final double n = 0.001 * System.currentTimeMillis();
        DeviceInput deviceInput = null;
        final int n2 = (event.modifiers == 4) ? 1 : 0;
        if (this.L) {
            switch (event.id) {
                case 501: {
                    deviceInput = this.W;
                    this.W.a(0, event.x, event.y, n2, event.modifiers, n);
                    break;
                }
                case 506: {
                    deviceInput = this.W;
                    this.W.a(3, event.x, event.y, n2, event.modifiers, n);
                    break;
                }
                case 502: {
                    deviceInput = this.W;
                    this.W.a(1, event.x, event.y, n2, event.modifiers, n);
                    break;
                }
                case 503: {
                    deviceInput = this.W;
                    this.W.a(2, event.x, event.y, n2, event.modifiers, n);
                    break;
                }
                case 401:
                case 403: {
                    deviceInput = this.X;
                    this.X.a(0, event.key, event.modifiers, n);
                    this.a((KeyboardInput)deviceInput);
                    break;
                }
                case 402:
                case 404: {
                    deviceInput = this.X;
                    this.X.a(1, event.key, event.modifiers, n);
                    break;
                }
                case 201: {
                    deviceInput = this.Y;
                    this.Y.a(0, event.modifiers, n);
                    break;
                }
                case 203: {
                    deviceInput = this.Y;
                    this.Y.a(1, event.modifiers, n);
                    this.p();
                    break;
                }
                case 204: {
                    deviceInput = this.Y;
                    this.Y.a(2, event.modifiers, n);
                    this.f();
                    break;
                }
            }
        }
        this.ba = false;
        if (deviceInput == this.W) {
            for (int i = 0; i < this.bb.size(); ++i) {
                this.Z = ((DeviceObserver)this.bb.elementAt(i)).onDeviceInput(deviceInput, this.bc.elementAt(i));
                if (this.Z) {
                    this.ba = true;
                    break;
                }
            }
            if (!this.ba) {
                for (int n3 = 0; n3 < this.bh.size() && !(this.Z = ((DeviceObserver)this.bh.elementAt(n3)).onDeviceInput(deviceInput, this.bi.elementAt(n3))); ++n3) {}
            }
        }
        if (deviceInput == this.X) {
            for (int j = 0; j < this.bd.size(); ++j) {
                this.Z = ((DeviceObserver)this.bd.elementAt(j)).onDeviceInput(deviceInput, this.be.elementAt(j));
                if (this.Z) {
                    this.ba = true;
                    break;
                }
            }
            if (!this.ba) {
                for (int n4 = 0; n4 < this.bj.size() && !(this.Z = ((DeviceObserver)this.bj.elementAt(n4)).onDeviceInput(deviceInput, this.bk.elementAt(n4))); ++n4) {}
            }
        }
        if (deviceInput == this.Y) {
            for (int k = 0; k < this.bf.size(); ++k) {
                this.Z = ((DeviceObserver)this.bf.elementAt(k)).onDeviceInput(deviceInput, this.bg.elementAt(k));
                if (this.Z) {
                    this.ba = true;
                    break;
                }
            }
            if (!this.ba) {
                for (int n5 = 0; n5 < this.bl.size() && !(this.Z = ((DeviceObserver)this.bl.elementAt(n5)).onDeviceInput(deviceInput, this.bm.elementAt(n5))); ++n5) {}
            }
        }
        return super.handleEvent(event);
    }
    
    private final void m() {
        this.H = "ERROR : No object file found";
        this.J = this.getToolkit().getFontMetrics(this.getFont()).stringWidth(this.H) >> 1;
        this.repaint();
    }
    
    public final void addDeviceObserver(final DeviceObserver deviceObserver, final String s, final Object o) {
        if (s.equals("MouseInput") || s.equals("DeviceInput")) {
            if (deviceObserver instanceof Node || deviceObserver instanceof z) {
                this.bb.addElement(deviceObserver);
                this.bc.addElement(o);
            }
            else {
                this.bh.addElement(deviceObserver);
                this.bi.addElement(o);
            }
        }
        if (s.equals("KeyboardInput") || s.equals("DeviceInput")) {
            if (deviceObserver instanceof Node || deviceObserver instanceof z) {
                this.bd.addElement(deviceObserver);
                this.be.addElement(o);
            }
            else {
                this.bj.addElement(deviceObserver);
                this.bk.addElement(o);
            }
        }
        if (s.equals("WindowInput") || s.equals("DeviceInput")) {
            if (deviceObserver instanceof Node || deviceObserver instanceof z) {
                this.bf.addElement(deviceObserver);
                this.bg.addElement(o);
                return;
            }
            this.bl.addElement(deviceObserver);
            this.bm.addElement(o);
        }
    }
    
    protected final void n() {
        if (!this.L) {
            return;
        }
        this.r = true;
        try {
            if (this.E != null) {
                this.E.stop();
                this.E = null;
            }
            this.y = null;
        }
        catch (Exception ex) {
            System.err.println("stop: " + ex);
        }
    }
    
    protected final void o() {
        this.M = true;
        this.w = this.size().width;
        this.x = this.size().height;
        this.A = new g(this, this.getGraphics(), this.w, this.x);
        if (this.g) {
            final String parameter = this.applet.getParameter("src");
            final String parameter2 = this.applet.getParameter("gzip_src");
            if (this.N && parameter2 != null) {
                this.setSceneFromURL(new String[] { parameter2, parameter });
            }
            else {
                this.setSceneFromURL(new String[] { parameter });
            }
        }
        else {
            this.setScene(new Transform());
        }
        this.customInitialize();
        this.M = false;
        this.L = true;
    }
    
    private URL a(final String[] array) {
        if (!this.g) {
            try {
                return new URL("file", "", array[0]);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        String s = null;
        String s2 = null;
        if (array.length > 1) {
            s2 = array[0];
            s = array[1];
        }
        else if (array.length == 1) {
            s = array[0];
        }
        URL url = null;
        this.h = false;
        try {
            if (this.N && s2 != null) {
                this.b(s2);
                if (s2.startsWith("http") || s2.startsWith("HTTP")) {
                    url = new URL(s2);
                }
                else {
                    url = new URL(this.applet.getCodeBase(), s2);
                }
                if (url != null) {
                    this.h = true;
                }
            }
            if (s != null && url == null) {
                this.b(s);
                if (s.startsWith("http") || s.startsWith("HTTP")) {
                    url = new URL(s);
                }
                else {
                    url = new URL(this.applet.getCodeBase(), s);
                }
            }
            if (url == null) {
                this.m();
            }
        }
        catch (MalformedURLException ex2) {
            System.out.println(ex2);
            this.m();
        }
        return url;
    }
    
    public final void setBilinearFiltering(final boolean b) {
        this.A.f(b);
        this.A.d(b);
    }
    
    private final void p() {
        this.D = false;
        System.out.println("window iconified");
    }
    
    public final d a() {
        return this.bo;
    }
    
    public final Searcher getNewSearcher() {
        return new h();
    }
    
    public final void loadURL(final String[] array, final Node node) throws Shout3DException {
        final URL a = this.a(array);
        if (a == null) {
            return;
        }
        if (node == null || !(node instanceof Transform)) {
            throw new Shout3DException("loadUrl requires non-null Transform node as input");
        }
        if (this.i == null) {
            (this.i = new z()).a(this);
        }
        c c;
        if (this.g) {
            c = new c(this.applet, this, a, this.h, (Group)node);
        }
        else {
            c = new c(this, this, a, this.h, (Group)node);
        }
        try {
            this.a().a(this, c);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public final void setScene(final Group group) throws Shout3DException {
        if (group == null || !(group instanceof Transform)) {
            throw new Shout3DException("setScene requires non-null Transform node as input");
        }
        this.B = (Transform)group;
        this.a(group);
        this.s();
        this.u();
        this.e();
        this.v();
        this.h();
        this.c();
        this.t();
    }
    
    public final Group getScene() {
        return this.B;
    }
    
    private final void a(final Group group) {
        if (this.A != null) {
            this.A.a(group);
        }
        final Searcher newSearcher = this.getNewSearcher();
        for (int i = this.bb.size() - 1; i >= 0; --i) {
            if (this.bb.elementAt(i) instanceof Node) {
                newSearcher.setNode((Node)this.bb.elementAt(i));
                if (newSearcher.searchFirst(group) == null) {
                    this.removeDeviceObserver((DeviceObserver)this.bb.elementAt(i), "DeviceInput");
                }
            }
        }
        for (int j = this.bd.size() - 1; j >= 0; --j) {
            if (this.bd.elementAt(j) instanceof Node) {
                newSearcher.setNode((Node)this.bd.elementAt(j));
                if (newSearcher.searchFirst(group) == null) {
                    this.removeDeviceObserver((DeviceObserver)this.bd.elementAt(j), "DeviceInput");
                }
            }
        }
        for (int k = this.bf.size() - 1; k >= 0; --k) {
            if (this.bf.elementAt(k) instanceof Node) {
                newSearcher.setNode((Node)this.bf.elementAt(k));
                if (newSearcher.searchFirst(group) == null) {
                    this.removeDeviceObserver((DeviceObserver)this.bf.elementAt(k), "DeviceInput");
                }
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.d();
    }
    
    protected final void q() {
        System.gc();
        this.j();
        this.r = false;
        if (this.E == null) {
            this.F = false;
            (this.E = new Thread(this)).setPriority(1);
            this.E.start();
        }
    }
    
    public final double getAbsoluteTime() {
        return this.bn;
    }
    
    private final void b(final String s) {
        System.out.println("\nShout3D 1.0.3 - " + s);
        System.out.println("Copyright (c) 1999 Shout Interactive, Inc.\n");
    }
    
    public final boolean isAntiAliased() {
        return this.A.p();
    }
    
    public final boolean isLoadResourcesInSeparateThread() {
        return this.bo.a();
    }
    
    public void onPreRender(final Renderer renderer, final Object o) {
    }
    
    private final void r() {
        final long currentTimeMillis = System.currentTimeMillis();
        ++this.T;
        if (this.V == 0L) {
            this.V = currentTimeMillis;
        }
        final float n = (currentTimeMillis - this.V) / 1000.0f;
        System.arraycopy(this.U, 0, this.U, 1, 9);
        this.U[0] = n;
        if (this.T > 10) {
            float n2 = 0.0f;
            for (int i = 0; i < this.U.length; ++i) {
                n2 += this.U[i];
            }
            this.S = n2 / this.U.length;
        }
        this.V = currentTimeMillis;
    }
    
    private final void s() {
        this.t = null;
        if (this.g) {
            final String parameter = this.applet.getParameter("background");
            final String parameter2 = this.applet.getParameter("backgroundStretchToFit");
            final String parameter3 = this.applet.getParameter("backgroundColorR");
            final String parameter4 = this.applet.getParameter("backgroundColorG");
            final String parameter5 = this.applet.getParameter("backgroundColorB");
            if (parameter != null || (parameter2 != null && parameter2.equals("true")) || parameter3 != null || parameter4 != null || parameter5 != null) {
                this.t = new Background(this);
            }
            if (parameter != null) {
                final ImageTexture value = new ImageTexture(this.applet);
                value.url.setValue(new String[] { parameter });
                this.t.texture.setValue(value);
            }
            if (parameter2 != null && parameter2.equals("true")) {
                this.t.stretchToFit.setValue(true);
            }
            if (parameter3 != null || parameter4 != null || parameter5 != null) {
                if (parameter3 != null) {
                    this.t.color.getValue()[0] = new Float(parameter3);
                }
                if (parameter4 != null) {
                    this.t.color.getValue()[1] = new Float(parameter4);
                }
                if (parameter5 != null) {
                    this.t.color.getValue()[2] = new Float(parameter5);
                }
                this.t.color.setValue(this.t.color.getValue());
            }
        }
        if (this.t == null) {
            this.t = (Background)this.a("Background");
        }
        if (this.t != null) {
            this.t.isBound.setValue(true);
            return;
        }
        this.A.a((Background)null);
    }
    
    private final void t() {
        if (this.g) {
            final String parameter = this.applet.getParameter("loadResourcesInSeparateThread");
            if (parameter != null && parameter.equals("false")) {
                this.setLoadResourcesInSeparateThread(false);
                return;
            }
            this.setLoadResourcesInSeparateThread(true);
        }
    }
    
    public Shout3DPanel(final Shout3DApplet applet) {
        this.g = true;
        this.h = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.q = new Mmm();
        this.r = false;
        this.s = false;
        this.t = null;
        this.u = 5L;
        this.v = true;
        this.w = 100;
        this.x = 100;
        this.B = new Transform();
        this.C = false;
        this.D = true;
        this.E = null;
        this.F = false;
        this.H = "Shout3D Viewer ©1999";
        this.I = "Shout Interactive, Inc.";
        this.L = false;
        this.M = false;
        this.P = null;
        this.R = false;
        this.S = 0.2f;
        this.T = 0;
        this.U = new float[10];
        this.V = 0L;
        this.W = new MouseInput();
        this.X = new KeyboardInput();
        this.Y = new WindowInput();
        this.bb = new Vector();
        this.bc = new Vector();
        this.bd = new Vector();
        this.be = new Vector();
        this.bf = new Vector();
        this.bg = new Vector();
        this.bh = new Vector();
        this.bi = new Vector();
        this.bj = new Vector();
        this.bk = new Vector();
        this.bl = new Vector();
        this.bm = new Vector();
        this.bn = 0.0;
        this.bo = new d();
        this.applet = applet;
        this.reshape(0, 0, applet.size().width, applet.size().height);
    }
    
    public Shout3DPanel(final Shout3DApplet applet, final int n, final int n2) {
        this.g = true;
        this.h = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.q = new Mmm();
        this.r = false;
        this.s = false;
        this.t = null;
        this.u = 5L;
        this.v = true;
        this.w = 100;
        this.x = 100;
        this.B = new Transform();
        this.C = false;
        this.D = true;
        this.E = null;
        this.F = false;
        this.H = "Shout3D Viewer ©1999";
        this.I = "Shout Interactive, Inc.";
        this.L = false;
        this.M = false;
        this.P = null;
        this.R = false;
        this.S = 0.2f;
        this.T = 0;
        this.U = new float[10];
        this.V = 0L;
        this.W = new MouseInput();
        this.X = new KeyboardInput();
        this.Y = new WindowInput();
        this.bb = new Vector();
        this.bc = new Vector();
        this.bd = new Vector();
        this.be = new Vector();
        this.bf = new Vector();
        this.bg = new Vector();
        this.bh = new Vector();
        this.bi = new Vector();
        this.bj = new Vector();
        this.bk = new Vector();
        this.bl = new Vector();
        this.bm = new Vector();
        this.bn = 0.0;
        this.bo = new d();
        this.applet = applet;
        this.reshape(0, 0, n, n2);
    }
    
    public Shout3DPanel(final Shout3DApplet applet, final int n, final int n2, final int n3, final int n4) {
        this.g = true;
        this.h = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.q = new Mmm();
        this.r = false;
        this.s = false;
        this.t = null;
        this.u = 5L;
        this.v = true;
        this.w = 100;
        this.x = 100;
        this.B = new Transform();
        this.C = false;
        this.D = true;
        this.E = null;
        this.F = false;
        this.H = "Shout3D Viewer ©1999";
        this.I = "Shout Interactive, Inc.";
        this.L = false;
        this.M = false;
        this.P = null;
        this.R = false;
        this.S = 0.2f;
        this.T = 0;
        this.U = new float[10];
        this.V = 0L;
        this.W = new MouseInput();
        this.X = new KeyboardInput();
        this.Y = new WindowInput();
        this.bb = new Vector();
        this.bc = new Vector();
        this.bd = new Vector();
        this.be = new Vector();
        this.bf = new Vector();
        this.bg = new Vector();
        this.bh = new Vector();
        this.bi = new Vector();
        this.bj = new Vector();
        this.bk = new Vector();
        this.bl = new Vector();
        this.bm = new Vector();
        this.bn = 0.0;
        this.bo = new d();
        this.applet = applet;
        this.reshape(n, n2, n3, n4);
    }
    
    private final void u() {
        final Viewpoint viewpoint = (Viewpoint)this.a("Viewpoint");
        if (viewpoint != null) {
            viewpoint.isBound.setValue(true);
            return;
        }
        this.A.a(null, (Node[])null);
    }
    
    private final void v() {
        NavigationInfo navigationInfo = (NavigationInfo)this.a("NavigationInfo");
        if (this.g) {
            final String parameter = this.applet.getParameter("headlightOn");
            if (parameter != null) {
                if (navigationInfo == null) {
                    navigationInfo = new NavigationInfo(this);
                    navigationInfo.setDEFName("_shout3dpanel_NavigationInfo_");
                    this.B.addChildren(new Node[] { navigationInfo });
                }
                if (parameter.equals("true")) {
                    navigationInfo.headlight.setValue(true);
                }
                else {
                    navigationInfo.headlight.setValue(false);
                }
            }
        }
        if (navigationInfo != null) {
            navigationInfo.isBound.setValue(true);
            return;
        }
        this.A.a(this.getScene(), null);
    }
    
    public final Renderer getRenderer() {
        return this.A;
    }
    
    public final void tick() {
        this.bn = System.currentTimeMillis() / 1000.0;
    }
    
    public final boolean isBilinearFiltering() {
        return this.A.s() || this.A.e();
    }
    
    public final void clearResourceCaches() {
        this.a().c();
    }
    
    public void customInitialize() {
    }
    
    public final Picker getNewPicker() {
        return new f(this);
    }
    
    public final Node getNodeByName(final String defName) {
        if (this.z == null) {
            this.z = this.getNewSearcher();
        }
        this.z.setNode(null);
        this.z.setType(null);
        this.z.setDefName(defName);
        final Node[] searchFirst = this.z.searchFirst(this.getScene());
        if (searchFirst != null) {
            return searchFirst[searchFirst.length - 1];
        }
        return null;
    }
}
