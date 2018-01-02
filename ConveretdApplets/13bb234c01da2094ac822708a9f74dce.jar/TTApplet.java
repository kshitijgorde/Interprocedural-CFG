import java.lang.reflect.InvocationTargetException;
import com.daysofwonder.applet.af;
import com.daysofwonder.applet.aw;
import com.daysofwonder.tt.m;
import com.daysofwonder.applet.aq;
import com.daysofwonder.applet.n;
import com.daysofwonder.applet.an;
import com.daysofwonder.applet.e;
import com.daysofwonder.applet.aG;
import java.io.UnsupportedEncodingException;
import com.daysofwonder.util.w;
import com.daysofwonder.util.v;
import com.daysofwonder.applet.aA;
import com.daysofwonder.a.o;
import com.daysofwonder.applet.aE;
import com.daysofwonder.applet.ap;
import java.awt.Graphics;
import java.net.URISyntaxException;
import java.net.URI;
import com.daysofwonder.tt.p;
import javax.swing.JComponent;
import com.daysofwonder.applet.aM;
import javax.swing.SwingUtilities;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Enumeration;
import com.daysofwonder.applet.J;
import com.daysofwonder.applet.c;
import java.io.IOException;
import java.net.MalformedURLException;
import com.daysofwonder.tt.b;
import com.daysofwonder.tt.l;
import java.util.Properties;
import com.daysofwonder.util.t;
import com.daysofwonder.util.i;
import com.daysofwonder.applet.ag;
import java.util.Hashtable;
import com.daysofwonder.util.UIProperties;
import java.net.URL;
import java.awt.Image;
import com.daysofwonder.util.q;
import com.daysofwonder.a.j;
import com.daysofwonder.applet.au;

// 
// Decompiled by Procyon v0.5.30
// 

public class TTApplet extends au implements j, q, Runnable
{
    private static Image W;
    private static Image X;
    private static Image Y;
    private URL Z;
    private String aa;
    private String ab;
    private String ac;
    private String ad;
    private String ae;
    private String af;
    private boolean ag;
    private boolean ah;
    private boolean ai;
    private boolean aj;
    private boolean ak;
    private G al;
    private UIProperties am;
    protected Hashtable a;
    protected Hashtable b;
    protected int c;
    private ad an;
    private ag ao;
    private int ap;
    
    public TTApplet() {
        this.a = new Hashtable();
        this.b = new Hashtable();
        this.c = 0;
    }
    
    public void init() {
        final i i = new i();
        ((Hashtable<String, String>)i).put("log.print_date", "true");
        com.daysofwonder.tt.j.a();
        com.daysofwonder.util.t.a(i);
        super.init();
        com.daysofwonder.tt.l.c();
        com.daysofwonder.util.t.a("Game build version:" + this.l());
        com.daysofwonder.tt.b.a(this);
        if (this.getParameter("table") != null && this.getParameter("table").length() > 0) {
            this.aa = this.getParameter("table");
        }
        if (this.getParameter("tpass") != null && this.getParameter("tpass").length() > 0) {
            this.ab = this.getParameter("tpass");
        }
        if (this.getParameter("tname") != null && this.getParameter("tname").length() > 0) {
            this.ac = this.getParameter("tname");
        }
        if (this.getParameter("resurrect") != null && this.getParameter("resurrect").length() > 0) {
            this.ad = this.getParameter("resurrect");
        }
        if (this.getParameter("observe") != null && this.getParameter("observe").length() > 0) {
            this.ae = this.getParameter("observe");
        }
        if (this.getParameter("training") != null && this.getParameter("training").length() > 0) {
            this.af = this.getParameter("training");
        }
        else if (this.getParameter("practice") != null && this.getParameter("practice").length() > 0) {
            this.af = this.getParameter("practice");
        }
        if (this.getParameter("splash") != null && this.getParameter("splash").length() > 0) {
            try {
                this.Z = new URL(this.getParameter("splash"));
            }
            catch (MalformedURLException ex2) {
                com.daysofwonder.util.t.a("malformed url: " + this.getParameter("splash"));
            }
        }
        else {
            this.Z = null;
        }
        this.ag = (this.ad != null && this.aa != null && this.ab != null && this.ac != null);
        this.ah = (this.ad == null && this.aa != null && this.ab != null && this.ac != null);
        this.ai = (this.ad == null && this.aa == null && this.ab == null && this.ac == null && this.ae != null);
        this.aj = (this.ad == null && this.aa == null && this.ab == null && this.ac == null && this.ae == null && this.af != null);
        com.daysofwonder.util.t.a("Params: res=" + this.ag + " autojoin=" + this.ah + " observe=" + this.ai + " training=" + this.aj);
        this.O = this.getParameter("login");
        this.P = this.getParameter("pass");
        try {
            String string;
            if (TTApplet.L.equals("en")) {
                string = "appletmsg.properties";
            }
            else {
                string = "appletmsg-" + TTApplet.L + ".properties";
            }
            this.am = new UIProperties(this.e(string), "UTF8");
        }
        catch (IOException ex) {
            System.out.println("Can't load : " + ex.getMessage());
        }
    }
    
    public synchronized void a() {
        com.daysofwonder.util.t.a("initUI");
        if (this.h) {
            return;
        }
        com.daysofwonder.util.t.a("initUI");
        TTApplet.k = new Hashtable();
        if (this.Z != null) {
            com.daysofwonder.util.t.a("Loading: " + this.Z);
            TTApplet.W = this.a(this.Z, this.o);
            com.daysofwonder.util.t.a("logo: " + TTApplet.W);
        }
        if (TTApplet.W == null) {
            TTApplet.W = this.d("img/logo-" + TTApplet.L + ".gif");
            this.o.a(TTApplet.W);
        }
        if (!this.o.a((c)null)) {
            System.out.println("Cannot load logo");
        }
        this.h = true;
    }
    
    public synchronized void b() {
        if (this.al != null && this.m != null) {
            this.m.g();
            final J ag = this.al.ag();
            if (ag instanceof S) {
                ((S)ag).c();
            }
            this.al.a((J)null);
            this.al.e();
        }
        --this.i;
        if (this.i < 0) {
            this.i = 0;
        }
        else if (this.i == 0) {
            System.out.println("removing images");
            if (TTApplet.W != null) {
                TTApplet.W.flush();
            }
            TTApplet.W = null;
            if (TTApplet.k != null) {
                final Enumeration<String> keys = TTApplet.k.keys();
                while (keys.hasMoreElements()) {
                    final String s = keys.nextElement();
                    final Object value = TTApplet.k.get(s);
                    if (value instanceof Image) {
                        ((Image)value).flush();
                        TTApplet.k.put(s, s);
                    }
                }
            }
        }
        this.h = false;
    }
    
    public void run() {
        System.out.println("Starting Game Thread");
        while (this.g == Thread.currentThread()) {
            int a = 25536;
            try {
                if (TTApplet.W == null) {
                    this.a();
                }
                this.m = null;
                this.F();
                com.daysofwonder.util.t.a("loading imgs");
                this.a(this.o, this.an, this.an);
                final String parameter = this.getParameter("server");
                final String parameter2 = this.getParameter("port");
                final ArrayList<Integer> list = new ArrayList<Integer>();
                if (parameter2 != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, ",");
                    while (stringTokenizer.hasMoreTokens()) {
                        list.add(Integer.parseInt(stringTokenizer.nextToken().trim()));
                    }
                }
                final boolean b = false;
                try {
                    a = this.a(a, parameter, list, b);
                }
                catch (IOException ex3) {
                    if (this.D != null) {
                        this.a(this.D);
                    }
                    else {
                        this.a("error.connect.title", "error.connect1.text", "error.connect2.text", "error.connect3.text", "error.connect4.text");
                    }
                }
                list.clear();
                this.b(parameter, a);
            }
            catch (Exception ex) {
                com.daysofwonder.util.t.a("If you see this, send it to support@daysofwonder.com");
                com.daysofwonder.util.t.a(ex);
                if (ex.getMessage() == null || (ex.getMessage() != null && !ex.getMessage().equals("toto"))) {
                    this.a("general failure", ex);
                }
                if (ex.getMessage() != null && ex.getMessage().equals("toto")) {
                    this.a(this.A);
                    return;
                }
                try {
                    if (this.al != null) {
                        System.out.println("----> abort game...");
                        this.al.au();
                        this.al.e();
                    }
                    if (this.m != null) {
                        this.m.g();
                    }
                    this.j();
                    this.m();
                    this.b();
                }
                catch (Exception ex2) {
                    com.daysofwonder.util.t.a(ex2);
                }
            }
        }
        com.daysofwonder.util.t.a("Gosh: we're out of the loop...");
    }
    
    private void a(final String s, final String... array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(this.am.b(array[i])).append("\n");
        }
        try {
            SwingUtilities.invokeAndWait(new al(this, sb, s));
        }
        catch (Exception ex) {}
    }
    
    private int a(int intValue, final String s, final List list, boolean b) {
        long n = 500L;
        while (!b) {
            try {
                if (this.l != null) {
                    this.m();
                }
                if (list.size() > 0) {
                    intValue = list.remove(0);
                }
                else {
                    n *= 2L;
                    if (n > 300000L) {
                        n = 300000L;
                    }
                    if (n > 0L) {
                        try {
                            com.daysofwonder.util.t.a("sleeping...:" + n);
                            Thread.sleep(n);
                        }
                        catch (InterruptedException ex2) {
                            break;
                        }
                    }
                }
                this.a(s, intValue);
                this.m = new aM(this.l);
                b = true;
            }
            catch (IOException ex) {
                if (list.size() == 0) {
                    com.daysofwonder.util.t.a(ex);
                    throw ex;
                }
                continue;
            }
        }
        return intValue;
    }
    
    private void F() {
        if (this.an == null) {
            this.an = new ad(TTApplet.W, TTApplet.X, TTApplet.Y, this.am);
        }
        this.b(this.an);
    }
    
    private void i(final String s) {
        if (this.B != null) {
            try {
                final URI uri = this.B.toURI();
                String s2 = uri.getQuery();
                if (s2 == null) {
                    s2 = "";
                }
                if (s2.length() > 0) {
                    s2 = s2.concat("&");
                }
                this.a(new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(), s2.concat("game=TT&").concat("variant=").concat(com.daysofwonder.tt.p.b(s)), uri.getFragment()).toURL());
            }
            catch (MalformedURLException ex) {}
            catch (URISyntaxException ex2) {}
        }
    }
    
    public void update(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public aE a(final Object o, final ap ap, final int n, final int n2) {
        return new av(o, (z)ap, n, n2);
    }
    
    public synchronized void a(final o o, final int n) {
        final Integer value = n;
        this.a.put(o, value);
        this.b.put(value, o);
    }
    
    public synchronized int a(final o o) {
        return this.a.get(o);
    }
    
    public synchronized o c(final int n) {
        return com.daysofwonder.tt.i.a;
    }
    
    private int a(final y y) {
        return y.h();
    }
    
    public void c() {
        if (this.al != null) {
            com.daysofwonder.util.t.a("pinging...");
            this.al.ar();
        }
    }
    
    private boolean G() {
        final aA aa = new aA(this.z(), this.am);
        aa.a();
        this.O = aa.c();
        this.P = aa.d();
        if (this.O == null || this.P == null) {
            return false;
        }
        try {
            this.P = com.daysofwonder.util.w.b(new v().a(this.P.getBytes("ASCII")));
        }
        catch (UnsupportedEncodingException ex) {}
        return true;
    }
    
    private void b(final String s, final String... array) {
        this.a(s, array);
        this.O = null;
        this.P = null;
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        this.a(this.A);
    }
    
    private boolean H() {
        final int a = this.al.a(this.O, this.P, this.getParameter("nonce"), this.getParameter("hmac"));
        if (a == -3) {
            this.b("error.version.title", "error.version1.text", "error.version2.text", "error.version3.text", "error.version4.text", "error.version5.text");
            return false;
        }
        if (a == -1) {
            this.b("error.login.title", "error.login.text");
            return false;
        }
        if (a == -2) {
            this.b("error.serverfull.title", "error.serverfull.text");
            return false;
        }
        return true;
    }
    
    private boolean I() {
        this.ao = new ag();
        com.daysofwonder.util.t.a("create Lobby");
        final y y = new y(this.al, this.ao, this.ap, this);
        y.e();
        y.f();
        final F n = new F(this.al, y);
        this.al.a(n);
        this.al.a(this.ao);
        SwingUtilities.invokeAndWait(new ah(this, y));
        this.al.a((com.daysofwonder.applet.p)y);
        this.al.a((e)y);
        this.al.a((an)y);
        this.al.ak();
        this.al.al();
        this.al.ah();
        do {
            y.repaint();
            try {
                com.daysofwonder.util.t.a("waiting condVar");
                this.ao.a();
                com.daysofwonder.util.t.a("condVar release");
            }
            catch (InterruptedException ex) {
                System.out.println("----> interruption...");
                this.al.b((com.daysofwonder.applet.p)y);
                this.al.b((e)y);
                this.al.b((an)y);
                y.d();
                return false;
            }
        } while (this.a(y) < 0 && !this.al.aI());
        this.ao = null;
        this.ap = y.a();
        this.al.b((com.daysofwonder.applet.p)y);
        this.al.b((e)y);
        this.al.b((an)y);
        y.d();
        SwingUtilities.invokeAndWait(new ai(this, y));
        n.c();
        com.daysofwonder.util.t.a("destruction of Lobby");
        if (this.al.aI()) {
            if (this.m != null) {
                this.m.g();
            }
            return false;
        }
        if (this.m.e()) {
            throw new Exception("toto");
        }
        return true;
    }
    
    private void c(final String s, final String... array) {
        this.a(s, array);
        if (this.w != null) {
            this.a(this.w);
            return;
        }
        this.F();
    }
    
    private void b(final String s, final int n) {
        int n2 = 1;
        com.daysofwonder.util.t.a("create game");
        (this.al = new G(s, n)).a(new L(this));
        this.al.a(false);
        while (this.g == Thread.currentThread()) {
            if ((this.O == null || this.P == null) && !this.G()) {
                continue;
            }
            com.daysofwonder.util.t.a("new controller");
            final n n3 = new n();
            this.m.a(this.al);
            this.al.a(n3);
            this.al.a(this.m);
            com.daysofwonder.util.t.a("game start");
            if (this.m.f()) {
                this.b("error.serverfull.title", "error.serverfull.text");
                if (this.A == null) {
                    continue;
                }
            }
            if (n2 != 0) {
                this.H();
                n2 = 0;
            }
            else {
                this.al.a(this.O, this.P);
            }
            if (!this.al.aK().b()) {
                this.a(this.B);
            }
            this.a(this.an);
            TTApplet.W = null;
            boolean b = true;
            if (this.ak) {
                this.ak = false;
                if (this.al.am()) {
                    b = false;
                }
            }
            if (b && !this.ag && !this.ah && !this.ai && !this.aj) {
                if (!this.I()) {
                    return;
                }
            }
            else if (this.ai) {
                final boolean f = this.al.f();
                this.ai = false;
                if (!f) {
                    this.c("error.observe.title", "error.observe.text");
                    continue;
                }
            }
            else if (this.ag) {
                final boolean a = this.al.a(this.aa, this.ab, this.ac);
                this.ag = false;
                if (!a) {
                    this.c("error.resurrect.title", "error.resurrect.text");
                    continue;
                }
            }
            else if (this.ah) {
                final int b2 = this.al.b(this.aa, this.ab, this.ac);
                this.ah = false;
                if (b2 <= 0) {
                    this.c("error.join.title", "error.join.text");
                    continue;
                }
            }
            else if (this.aj) {
                final int a2 = this.al.a("Training", "", false, true, 3, 0, new m("us", 4, 0, false, false, 2, false), "us");
                this.aj = false;
                if (a2 == -1) {
                    this.c("error.join.title", "error.join.text");
                    continue;
                }
            }
            final String aa = this.al.aa();
            if (!this.a(n3)) {
                return;
            }
            if (Thread.currentThread().isInterrupted() || this.g == null) {
                if (this.w != null) {
                    this.a(this.w);
                }
                return;
            }
            if (this.al.aM() && !this.al.a(aa)) {
                this.i(aa);
            }
            this.al.e();
            if (this.m.e()) {
                throw new Exception("toto");
            }
            this.F();
        }
    }
    
    private boolean a(final J j) {
        com.daysofwonder.util.t.a("creation of Game View");
        final s s = new s(this.al, this);
        final aw aw = new aw(s);
        this.b(aw);
        s.a();
        s.b();
        final S n = new S(this.al, s);
        com.daysofwonder.util.t.a("display of Game View");
        aw.a();
        aw.repaint();
        try {
            this.al.a();
            this.al.d();
        }
        catch (af af) {
            this.al.e();
            com.daysofwonder.util.t.a("Netword Timeout: disonnected ?");
            com.daysofwonder.util.t.a("Trying to reconnect");
            n.b(6, null);
            this.ak = true;
            n.c();
            s.g();
            this.a(aw);
            com.daysofwonder.util.t.a("destruction of Game View");
            aw.b();
            if (this.m != null) {
                this.m.g();
            }
            this.al.a(j);
            return false;
        }
        catch (InterruptedException ex) {
            System.out.println("---> Game interrupted...");
            s.g();
            this.al.e();
            if (this.w != null) {
                this.a(this.w);
            }
            return false;
        }
        if (this.w != null) {
            this.a(this.w);
            return false;
        }
        n.c();
        s.g();
        this.a(aw);
        com.daysofwonder.util.t.a("destruction of Game View");
        aw.b();
        com.daysofwonder.util.t.a("old controller");
        this.al.a(j);
        return true;
    }
    
    private void a(final JComponent component) {
        try {
            SwingUtilities.invokeAndWait(new af(this, component));
        }
        catch (InterruptedException ex) {}
        catch (InvocationTargetException ex2) {}
    }
    
    private void b(final JComponent component) {
        try {
            SwingUtilities.invokeAndWait(new ag(this, component));
        }
        catch (InterruptedException ex) {}
        catch (InvocationTargetException ex2) {}
    }
}
