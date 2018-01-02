import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import netscape.javascript.JSObject;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URLConnection;
import netscape.security.PrivilegeManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.applet.Applet;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Properties;
import java.awt.Frame;
import java.util.Hashtable;
import java.net.URL;
import java.awt.List;
import java.awt.Label;
import java.util.Observer;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class C45 extends Panel implements C06, C51, C48, Observer
{
    Panel t;
    Label u;
    long w;
    C01 x;
    List y;
    List A;
    C49 B;
    C33 C;
    double D;
    URL E;
    boolean F;
    C33 H;
    C17 I;
    C44 K;
    String L;
    Panel M;
    boolean N;
    Hashtable O;
    ViewerApplet P;
    Hashtable Q;
    long R;
    boolean S;
    C05 U;
    Frame V;
    C18 W;
    String X;
    Properties Y;
    double Z;
    boolean ba;
    
    private void a(final C44 c44) {
        if (this.P == null) {
            return;
        }
        final Vector vector = new Vector<String>();
        final Vector<String> vector2 = new Vector<String>();
        final Vector<String> vector3 = new Vector<String>();
        int n = 0;
        while (true) {
            final String parameter = this.P.getParameter("URL_BLINK" + n);
            if (parameter == null) {
                break;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "=<>", true);
            if (stringTokenizer.countTokens() == 3) {
                vector.addElement(stringTokenizer.nextToken());
                vector2.addElement(stringTokenizer.nextToken());
                vector3.addElement(stringTokenizer.nextToken());
            }
            ++n;
        }
        if (vector.size() > 0) {}
    }
    
    public void b() {
    }
    
    public void R() {
        final Vector f = this.W.f();
        this.B.cf.P.widgets = this.W.f();
        this.W.a();
        this.Q = this.W.e();
        this.I = this.W.k();
        if (this.H != null) {
            this.H.dispose();
        }
        this.H = new C33(this.B, this.Q);
        final Vector d = this.W.d();
        (this.K = new C44(this.P, this.u, f, this.B)).n(this);
        this.f();
        this.layout();
        this.a(this.K);
        this.B.s(this.K);
        if (this.U != null) {
            this.U.e();
        }
        if (this.C != null) {
            this.C.dispose();
        }
        this.C = new C33(this.B, d);
        if (this.P.getParameter("Initial_Zoom_Booth") != null) {
            this.K.m(this.P.getParameter("Initial_Zoom_Booth"));
        }
        this.W = null;
        System.gc();
        try {
            if (this.P != null) {
                this.P.showStatus("Drawing loaded ");
                ViewerApplet.DoneLoading = true;
            }
        }
        catch (Throwable t) {
            System.out.println("%%%");
        }
    }
    
    public void c(final int n, final Object o) {
        if (o == this) {
            return;
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable != null) {
            if (observable instanceof C05) {
                this.j();
            }
        }
    }
    
    public void a(final InputStream inputStream, final String s) {
        try {
            this.ba = false;
            this.B.u();
            this.B.a();
            System.gc();
            if (this.V != null) {
                this.V.setTitle(" - " + s);
            }
            this.k(inputStream, s);
        }
        catch (IOException ex) {
            System.out.println("Exception while trying to load drawing " + ex + "");
        }
    }
    
    public void a(final C28 c28) {
        final C25 e = c28.e();
        if (e == null) {
            return;
        }
        final URL g = e.g();
        if (g == null) {
            final String f = e.f();
            if (f == null) {
                System.out.println("ERROR 38289 null");
            }
            else {
                this.n(f);
            }
            return;
        }
        String s = "" + System.currentTimeMillis();
        if (e.e() != null) {
            s = e.e();
        }
        if (!g.getFile().toLowerCase().trim().endsWith("dwf") && !g.getFile().toLowerCase().trim().endsWith("svf") && !g.getFile().toLowerCase().trim().endsWith("vr2")) {
            if (this.P == null) {
                System.out.println("ERROR selected  " + g + " but applet=null");
            }
            else {
                if (this.P.getParameter("target") != null) {
                    s = this.P.getParameter("target");
                }
                this.P.getAppletContext().showDocument(g, s);
            }
            return;
        }
        try {
            if (g.getProtocol().startsWith("file") && System.getProperty("java.vendor").toLowerCase().indexOf("netscape") != -1) {
                try {
                    PrivilegeManager.enablePrivilege("UniversalFileRead");
                }
                catch (Throwable t) {}
            }
            final URLConnection openConnection = g.openConnection();
            openConnection.setAllowUserInteraction(true);
            this.a(openConnection.getInputStream(), g.getFile());
        }
        catch (IOException ex) {
            System.out.println("Could not load URL, applets will only connect to the host they came from. " + ex);
            System.out.println("ERROR loading  " + g + " " + ex);
        }
    }
    
    private URL e() throws MalformedURLException {
        if (this.P == null) {
            System.out.println("Error 88334");
            throw new MalformedURLException("Error #232344");
        }
        String s = this.P.getParameter("origDrawingURL");
        if (this.x != null && this.x.c() != null) {
            s = this.x.c().toString();
        }
        String s2;
        if (s.indexOf("?") == -1) {
            s2 = s + "?t=" + System.currentTimeMillis();
        }
        else {
            s2 = s + "&t=" + System.currentTimeMillis();
        }
        return new URL(s2);
    }
    
    private void f() {
        this.Y = new Properties();
        this.A = new List();
        (this.y = new List()).setBackground(new Color(206, 206, 206));
        this.A.setBackground(new Color(206, 206, 206));
        if (this.K == null) {
            System.out.println("NO URL THREAD");
            return;
        }
        final Vector l = this.K.l();
        this.P.BoothCount = 0;
        for (int i = 0; i < l.size(); ++i) {
            final C25 e = l.elementAt(i).e();
            final String h = e.h();
            final String c = e.c();
            final String a = e.a();
            if (c != null) {
                if (!c.equals("")) {
                    this.y.addItem(c + " (#" + h + ")");
                }
            }
            ViewerApplet.BoothNumbers = ViewerApplet.BoothNumbers + a + "|";
            final ViewerApplet p = this.P;
            ++p.BoothCount;
            ((Hashtable<String, String>)this.Y).put(h, c);
        }
        this.B.cf.P.version = Double.valueOf(System.getProperty("java.version").trim().substring(0, 3));
        if (!this.F) {
            if (this.B.cf.P.version >= 1.2) {
                this.B.cf.P.ZoomBoothColor = new Color(0, 255, 0, 100);
            }
            else {
                this.B.cf.P.ZoomBoothColor = new Color(0, 255, 0);
            }
        }
        if (this.Y.size() > 0) {
            this.P.LoadList(this.F);
        }
        this.F = false;
    }
    
    public void g(URL e) {
        try {
            if (e == null) {
                e = this.e();
            }
            if (e.getProtocol().startsWith("file")) {
                try {
                    PrivilegeManager.enablePrivilege("UniversalFileRead");
                }
                catch (Throwable t2) {}
            }
            if (this.P != null) {
                this.P.showStatus("Refreshing drawing ");
            }
            this.E = e;
            final URLConnection openConnection = e.openConnection();
            openConnection.setAllowUserInteraction(true);
            openConnection.setUseCaches(false);
            final InputStream inputStream = openConnection.getInputStream();
            this.R = -1L;
            this.w = openConnection.getLastModified() + 1000L;
            this.P.loadedDrawing(e, openConnection.getLastModified() + 1000L);
            this.ba = false;
            this.B.u();
            this.B.M();
            this.B.A(true);
            this.k(inputStream, this.X);
            this.F = true;
            this.B.repaint();
            System.gc();
        }
        catch (Throwable t) {
            System.out.println("Err $232321 " + t);
        }
    }
    
    public void h() {
        if (this.B != null) {
            this.B.u();
        }
        if (this.U != null) {
            this.U.d();
        }
    }
    
    public void i(final Throwable t) {
        this.m(t);
        if (!t.getMessage().equals("done")) {
            this.b();
        }
    }
    
    public void j() {
        if (this.P == null) {
            System.out.println("Error 88335");
            return;
        }
        try {
            final URL e = this.e();
            if (this.E != null) {
                String s = e.toString();
                if (s.indexOf("?") != -1) {
                    s = s.substring(0, s.indexOf("?"));
                }
                String s2 = this.E.toString();
                if (s2.indexOf("?") != -1) {
                    s2 = s2.substring(0, s2.indexOf("?"));
                }
                if (!s2.toString().equals(s)) {
                    System.out.println("LAST REFRESH URL=" + this.E + " u=" + e);
                    this.g(e);
                    return;
                }
            }
            final URLConnection openConnection = e.openConnection();
            openConnection.setAllowUserInteraction(true);
            openConnection.setUseCaches(false);
            openConnection.setIfModifiedSince(this.w);
            if (this.w <= 0L) {
                this.w = openConnection.getLastModified();
            }
            if (this.w < openConnection.getLastModified()) {
                if (this.R == openConnection.getLastModified()) {
                    this.g(e);
                    return;
                }
                this.R = openConnection.getLastModified();
            }
        }
        catch (Throwable t) {
            System.out.println("Error while refreshing: " + t);
            t.printStackTrace();
        }
    }
    
    private void k(final InputStream inputStream, final String s) throws IOException {
        this.w = -1L;
        if (this.U != null) {
            this.U.b();
        }
        (this.W = new C42(inputStream, (this.P == null) ? null : this.P.getDocumentBase())).c(this);
        this.B.Q(this.W);
    }
    
    public void l(final String s) {
        try {
            (this.U = new C05()).c(Integer.parseInt(s) * 500);
            this.U.addObserver(this);
            this.R = -1L;
            this.U.a();
        }
        catch (Throwable t) {
            System.out.println(" Error 443 " + t);
        }
    }
    
    public void m(final Throwable t) {
    }
    
    private void n(final String s) {
        if (System.getProperty("java.vendor").toLowerCase().indexOf("netscape") == -1) {
            System.out.println("The URL selected contains JavaScript. JavaScript is only supported in Netscape");
            return;
        }
        JSObject.getWindow(this.P).eval(s);
    }
    
    private void o(final InputStream inputStream, final String s) throws IOException {
        if (inputStream != null) {
            this.w = -1L;
            if (this.U != null) {
                this.U.b();
            }
            (this.W = new C42(inputStream, (this.P == null) ? null : this.P.getDocumentBase())).c(this);
            if (this.O != null) {
                this.W.l(this.O);
            }
        }
        try {
            final Vector vector = new Vector();
            if (this.B == null) {
                this.B = new C49(this);
                if (this.P != null) {
                    this.B.D(this.P.getParameter("water_mark"));
                    if (this.P.getParameter("initial_view_name") != null) {
                        this.B.v(this.P.getParameter("initial_view_name"));
                    }
                }
                if (this.W != null) {
                    this.B.Q(this.W);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Error #455443 " + ex);
            ex.printStackTrace();
        }
        if (this.ba) {
            this.add("Center", this.B);
            try {
                this.repaint();
                this.invalidate();
                this.validate();
                this.layout();
                this.B.layout();
            }
            catch (Throwable t) {
                System.out.println("First  " + t);
            }
        }
    }
    
    public C45(final InputStream inputStream, final String x, final String l, final Applet applet, final Label u) throws IOException {
        this.F = false;
        this.D = 1.0;
        this.Z = 1.0;
        this.N = false;
        this.ba = true;
        this.w = -1L;
        this.t = new Panel();
        this.M = new Panel();
        this.S = true;
        this.L = l;
        this.P = (ViewerApplet)applet;
        this.u = u;
        this.X = x;
        this.setLayout(new BorderLayout());
        if (applet != null && applet.getParameter("off_layers") != null) {
            this.q(applet.getParameter("off_layers"));
        }
        if (applet != null && applet.getParameter("refresh_sec") != null) {
            this.l(applet.getParameter("refresh_sec"));
        }
        this.o(inputStream, x);
    }
    
    private void q(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        this.O = new Hashtable();
        final Integer n = new Integer(1);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            System.out.println("LAYER OFF=" + nextToken);
            this.O.put(nextToken, n);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        return super.handleEvent(event);
    }
    
    public void r(final Frame v) {
        this.V = v;
    }
    
    public Image s() {
        try {
            if (this.P != null && this.P.getParameter("logo") != null) {
                return Toolkit.getDefaultToolkit().getImage(new URL(this.P.getDocumentBase(), this.P.getParameter("logo")));
            }
        }
        catch (MalformedURLException ex) {
            System.out.println("Err#443 " + ex);
        }
        return null;
    }
    
    public boolean action(final Event event, final Object o) {
        return true;
    }
}
