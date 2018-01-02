// 
// Decompiled by Procyon v0.5.30
// 

package screen;

import java.awt.event.MouseEvent;
import java.applet.AudioClip;
import java.util.StringTokenizer;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Container;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Point;
import java.net.URL;
import java.util.Vector;
import java.applet.Applet;

public class GameClient extends Applet implements y, Runnable
{
    public String p;
    private boolean d;
    private int a;
    private Thread n;
    private Thread v;
    private int i;
    private int l;
    private Vector b;
    private i c;
    private dv e;
    private k f;
    private a g;
    private boolean h;
    private String j;
    private dp k;
    private de m;
    private dd o;
    private dc q;
    private dn r;
    private w s;
    private da t;
    private z u;
    private s w;
    private u x;
    private boolean y;
    private String z;
    private String dp;
    private String dd;
    private String da;
    private int dn;
    public int dv;
    public int di;
    private int dl;
    public static long db;
    public static boolean dc;
    
    public final Applet p() {
        return this;
    }
    
    public final void p(final URL url) {
        if (this.getAppletContext() != null) {
            this.getAppletContext().showDocument(url, "_blank");
        }
    }
    
    public final String a() {
        return this.j;
    }
    
    public final i p() {
        return this.c;
    }
    
    public final String n() {
        return this.dd;
    }
    
    public final String p() {
        return this.dp;
    }
    
    public final boolean a() {
        return this.dp.indexOf("Arena") != -1;
    }
    
    public final boolean n() {
        return this.dp.indexOf("Tour") != -1 || this.dp.equals("Exhibits");
    }
    
    public final boolean v() {
        return this.n() && this.dd.equals("#Common");
    }
    
    public boolean i() {
        return true;
    }
    
    public final dv p() {
        return this.e;
    }
    
    public final k p() {
        return this.f;
    }
    
    public final do p() {
        return this.q;
    }
    
    public final dc p() {
        return this.q;
    }
    
    public final a p() {
        return this.g;
    }
    
    public final void requestFocus() {
        this.y = true;
    }
    
    public final void v(final String s) {
        this.q.p(s);
    }
    
    public final boolean d(final String s) {
        if ((s.toUpperCase().startsWith("FOLLOW") || s.toUpperCase().startsWith("ENTER") || s.toUpperCase().startsWith("JOIN")) && this.f.i() != -1) {
            this.q.l("***<4>Please leave your current game first");
            return false;
        }
        this.c.p(s);
        return true;
    }
    
    public final dp p() {
        return this.k;
    }
    
    public boolean p() {
        return false;
    }
    
    public final boolean d() {
        final k p = this.p();
        return p.i() == -1 || (!p.p() && p.p() < 2);
    }
    
    public final String p(final String s) {
        try {
            return System.getProperty(s);
        }
        catch (Exception ex) {
            return "*";
        }
    }
    
    public final String d() {
        final String[] array = { "java.version", "java.vendor", "os.name", "os.arch", "os.version" };
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(this.p(array[i])).append("|");
        }
        return sb.toString();
    }
    
    public final int d() {
        return this.i;
    }
    
    public final void v(final int i) {
        this.i = i;
    }
    
    public final int n() {
        return this.l;
    }
    
    public final void p(final int l) {
        this.l = l;
    }
    
    public final void p(final String s) {
        this.q.v(s);
    }
    
    public final void d(final String s) {
        this.q.d(s);
    }
    
    public final void n() {
        this.q.d();
    }
    
    public final void d(final String name, final Point point) {
        this.u.setVisible(false);
        this.t.setName(name);
        this.t.p(point.x, point.y, true);
    }
    
    public final void p() {
        this.t.setVisible(false);
    }
    
    public final void p(final String name, final Point point) {
        this.t.setVisible(false);
        this.u.setName(name);
        this.u.p(point.x, point.y, true);
    }
    
    public final void v() {
        this.u.setVisible(false);
    }
    
    public final s p() {
        return this.w;
    }
    
    public final boolean p(final String s) {
        for (int i = 0; i < this.b.size(); ++i) {
            if (((String)this.b.elementAt(i)).equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public final void a(final String s) {
        this.q.c(s);
    }
    
    public final void n(final String s) {
        this.q.e(s);
    }
    
    public GameClient() {
        this.p = "Welcome to Vinagames";
        this.d = true;
        this.b = new Vector();
        this.j = "";
        this.y = true;
        this.dp = "";
        this.dd = "";
        this.da = "";
        this.dn = 1;
        this.dv = -1;
        this.di = -1;
        this.g = new p();
    }
    
    public final boolean isValid() {
        return this.d;
    }
    
    public final int a() {
        return this.dn;
    }
    
    public void init() {
        this.z = this.getParameter("room");
        this.dn = du.p(this.getParameter("reso"));
        if (this.dn == -1) {
            this.dn = 1;
        }
        this.h = ("vinagames.com".equals(this.getParameter("domain")) || "clubxiangqi.com".equals(this.getParameter("domain")));
        this.da = this.getParameter("stamp");
        String parameter = this.getParameter("language");
        if (!"cn".equals(parameter) && !"vn".equals(parameter)) {
            parameter = "en";
        }
        dl.d = parameter;
        this.q = new dc(this);
        this.k = new dp(this);
        this.m = new de(this, 500, 340);
        this.o = new dd(this, 350, 400);
        this.w = new s(this);
        this.x = new u(this);
        this.e = new dv(this);
        this.r = new dn(this);
        this.s = new w(this);
        this.t = new da(this);
        this.u = new z(this);
        this.setLayout(new BorderLayout(0, 0));
        this.p(this.p((String)null));
        this.c();
        if (this.h && "true".equals(this.getParameter("tunnel"))) {
            this.c = new l(this);
        }
        else {
            this.c = new f(this);
        }
        this.c.a();
        this.start();
    }
    
    public final void e() {
        if (this.c == null) {
            return;
        }
        this.c.p();
        if (!this.h) {
            this.q.l("***<4>Connection error, quitting...");
            return;
        }
        if (this.c instanceof f) {
            this.q.l("***<4>Attempts to work around...");
            (this.c = new l(this)).a();
            return;
        }
        if (this.c instanceof l) {
            this.q.l("***<4>More attempts to work around...");
            (this.c = new e(this)).a();
        }
    }
    
    public final int v() {
        if (this.f.d().equals("Chat")) {
            return 0;
        }
        return 1;
    }
    
    private final void j() {
        final int v = this.v();
        if (v == this.dv) {
            return;
        }
        this.dv = v;
        this.removeAll();
        this.s.p(v);
        if (v == 0) {
            this.add("East", this.s);
            this.add("Center", this.q);
        }
        else {
            final Panel panel = new Panel();
            this.add("West", this.r);
            this.add("Center", panel);
            panel.setLayout(new BorderLayout(0, 0));
            panel.add("North", this.s);
            panel.add("Center", this.q);
        }
        final Vector p = du.p(this);
        for (int i = 0; i < p.size(); ++i) {
            p.elementAt(i).invalidate();
        }
        this.validate();
        for (int j = 0; j < p.size(); ++j) {
            final Container element = p.elementAt(j);
            if (element instanceof Container) {
                element.doLayout();
            }
        }
    }
    
    public final int p() {
        return this.di;
    }
    
    private final void a(final int di) {
        if (di == this.di) {
            return;
        }
        this.di = di;
        this.i();
    }
    
    public final void i() {
        this.s.p();
        this.q.p();
        this.r.p();
        this.e.p();
        this.t.l();
        this.u.l();
        this.r.p();
        final db[] array = { this.o, this.x, this.w, this.k, this.m };
        for (int i = 0; i < array.length; ++i) {
            array[i].l();
        }
    }
    
    public final void b() {
        final Vector p = du.p(this);
        for (int i = 0; i < p.size(); ++i) {
            final Component component = p.elementAt(i);
            component.removeMouseListener(this);
            component.removeMouseMotionListener(this);
            component.addMouseListener(this);
            component.addMouseMotionListener(this);
        }
    }
    
    public final void destroy() {
        try {
            this.c.p("QUIT");
        }
        catch (Exception ex) {}
        du.p(500);
        this.d = false;
        try {
            this.q.n();
        }
        catch (Exception ex2) {}
        try {
            this.m.dispose();
        }
        catch (Exception ex3) {}
        try {
            this.k.dispose();
        }
        catch (Exception ex4) {}
        try {
            this.o.dispose();
        }
        catch (Exception ex5) {}
        try {
            this.t.dispose();
        }
        catch (Exception ex6) {}
        try {
            this.w.dispose();
        }
        catch (Exception ex7) {}
        try {
            t.p().dispose();
        }
        catch (Exception ex8) {}
        super.destroy();
    }
    
    public final void d() {
        if (this.y) {
            this.c.d("PONG!");
            this.y = false;
            return;
        }
        this.c.d("PONG");
    }
    
    private final boolean p(final String s, final String s2) {
        if (this.q.p(s, s2) || this.s.p(s, s2) || this.f.p(s, s2)) {
            return true;
        }
        if (s.equals("ROOMINIT")) {
            this.k.d(s2);
            this.p().repaint();
        }
        else if (s.equals("ROOMLIMITS")) {
            this.k.p(s2);
            this.p().repaint();
        }
        else if (s.equals("ROOMINFO")) {
            this.k.d(s2);
            this.p().repaint();
            this.k.show();
        }
        else if (s.equals("TABLELIST")) {
            this.m.d();
            this.m.setTitle(s2);
        }
        else if (s.equals("ENDTABLELIST")) {
            this.m.show();
        }
        else if (s.equals("TABLEENTRY")) {
            this.m.p(this.d(s2));
        }
        else if (s.equals("USERLIST")) {
            this.o.d();
            this.o.setTitle(s2);
        }
        else if (s.equals("ENDUSERLIST")) {
            this.o.show();
        }
        else if (s.equals("USERENTRY")) {
            this.o.p(s2);
        }
        else if (s.equals("INVITED")) {
            this.x.p(s2, true);
        }
        else if (s.equals("INVITING")) {
            this.x.p(s2, false);
        }
        else if (s.equals("ACCEPT")) {
            this.x.p(s2);
        }
        else if (s.equals("UNINVITE")) {
            this.q.l("***" + s2 + " withdraws invitation");
            this.x.p(s2);
        }
        else if (s.equals("DENY")) {
            this.q.l("***invitation to " + s2 + " fails");
            this.x.p(s2);
        }
        else if (s.equals("LOGOUT")) {
            this.c.p();
            this.q.l("*** Connection to server closed. " + s2);
            this.q.l("<4>*** GAME OVER ***");
            this.f.a("Thank you for playing");
        }
        else if (s.equals("LOGIN")) {
            this.c.a(du.p(s2, 0));
            this.q.l("<2>***Welcome " + s2 + "***");
            this.c.d("USERLOG VERSION " + System.getProperty("java.version") + " " + this.dn + " " + this.getParameter("cabbase"));
            this.c.d("USERLOG SYSINFO " + this.d());
            if (this.p()) {
                this.c.d("USERLOG LOADINGERROR " + this.d());
            }
            if (this.i >= 0 && this.i < 99 && this.l > 0) {
                this.c.d("LOTTERY " + this.i + " " + this.l);
            }
        }
        else if (s.equals("OWNERLIST")) {
            if (s2 == null || s2.trim().length() == 0) {
                this.q.l("<1>***No owner");
            }
            else {
                this.q.l("<1>***OWNERLIST: " + s2);
            }
        }
        else if (s.equals("BLOCKLIST")) {
            if (s2 == null || s2.trim().length() == 0) {
                this.q.l("<1>***Empty block list");
            }
            else {
                this.q.l("<1>***BLOCKLIST: " + s2);
            }
        }
        else if (s.equals("ISTATUS")) {
            this.f.a(s2);
        }
        else if (s.equals("VIPS") || s.equals("VIP")) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            while (stringTokenizer.hasMoreElements()) {
                final String s3 = (String)stringTokenizer.nextElement();
                if (!this.p(s3)) {
                    this.b.addElement(s3);
                }
            }
            if (s.equals("VIP")) {
                this.s.d(s2);
            }
        }
        else if (!s.equals("LEAVE") && !s.equals("ENTER")) {
            if (s.equals("TABLEMSG")) {
                if (s2 != null && !s2.trim().equals("")) {
                    this.q.b(s2);
                }
                else {
                    this.q.b(null);
                }
            }
            else if (s.equals("LOCATION")) {
                this.i(s2);
            }
            else if (s.equals("NOTICE")) {
                new t(this, s2);
            }
            else if (s.equals("LOTTERY")) {
                this.p(du.p(du.p(s2, 0)), du.p(du.p(s2, 1)));
            }
            else if (s.equals("LWIN")) {
                this.d(du.p(du.p(s2, 0)));
            }
            else if (s.equals("LTRUST")) {
                this.n(du.p(du.p(s2, 0)));
            }
            else if (s.equals("LRESULT")) {
                this.p(du.p(du.p(s2, 0)), du.p(du.p(s2, 1)), du.p(du.p(s2, 2)));
            }
            else if (s.equals("LWINNER")) {
                this.p(du.p(s2, 0), du.p(du.p(s2, 1)));
            }
            else {
                this.q.l("<2>***" + s + " " + s2);
            }
        }
        return true;
    }
    
    private final String d(final String s) {
        final StringBuffer sb = new StringBuffer();
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (this.p(nextToken)) {
                sb.append("<12>").append(nextToken).append("<1> ");
            }
            else {
                sb.append(nextToken).append(" ");
            }
        }
        return sb.toString().trim();
    }
    
    private final void p(final int i, final int l) {
        this.i = i;
        this.l = l;
        if (l != 0) {
            this.q.l("<2>*** Your lottery #" + i + ", price $" + l);
        }
        else {
            this.q.l("<2>*** You have no lottery ticket");
        }
        this.e.repaint();
    }
    
    private final void d(final int dl) {
        this.q.l("<13>$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        this.q.l("<13>$$<12> Lottery Winning Number: " + dl);
        this.dl = dl;
    }
    
    private final void p(final int n, final int n2, final int n3) {
        if (n2 <= 0) {
            this.q.l("<13>$$<12> Your number was " + n + " 'sad");
            this.q.l("<13>$$<12> Your balance: $" + n3);
            this.q.l("<13>$$<12> You lost $" + -n2);
            return;
        }
        if (this.dl == n && (n2 == 100 * this.l || n2 == 10 * this.l)) {
            this.q.l("<13>$$<12> Your balance: $" + n3);
            this.q.l("<13>$$<12> 'r You win $" + n2 + " 'r");
            return;
        }
        if (this.dl == n && n2 == 15 * this.l) {
            this.q.l("<13>$$<12> Your balance: $" + n3);
            this.q.l("<13>$$<12> 'r You win with server bonus $" + n2 + " 'r");
            this.q.l("<13>$$<12> (for low score accounts only)");
            return;
        }
        this.q.l("<13>$$<12> Your balance: $" + n3);
        this.q.l("<13>$$<12> 'h You win bonus $" + n2 + " 'h");
        this.q.l("<13>$$<12> (for low score accounts only)");
    }
    
    private final void p(final String s, final int n) {
        this.q.l("<13>$$<12> 'e " + s + " wins $" + n);
    }
    
    private final void n(final int n) {
        this.q.l("<13>$$<12> Lottery trust: $" + n);
        this.q.l("<13>$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }
    
    public k p(final String s) {
        return null;
    }
    
    public final void p(final k f) {
        if (this.f == f) {
            return;
        }
        if (this.f != null) {
            this.f.destroy();
        }
        this.f = f;
        this.e.p(this.f.p(this.dn));
        this.a(this.f.d());
        this.j();
        this.r.d();
        this.b();
    }
    
    private final void i(final String s) {
        final String p = du.p(s, 1);
        this.dp = du.p(s, 0);
        this.dd = du.p(s, 2);
        if (!p.equalsIgnoreCase(this.f.d())) {
            final k p2 = this.p(p);
            if (p2 == null) {
                this.c.p();
                this.q.l("<4>***The version you are using do not support game " + p + ", client quits");
                return;
            }
            this.p(p2);
        }
        this.f.a(String.valueOf(this.dp) + "::" + this.dd);
        this.e.repaint();
    }
    
    public final void start() {
        if (this.n == null) {
            (this.n = new Thread(this)).start();
        }
        if (this.v == null) {
            (this.v = new Thread(this)).start();
        }
    }
    
    public final void run() {
        if (Thread.currentThread() == this.n) {
            this.a();
        }
        if (Thread.currentThread() == this.v) {
            this.k();
        }
    }
    
    public final void h() {
        ++this.a;
    }
    
    public final void k() {
        final AudioClip p = df.p((Applet)this, "whoosh.au");
        while (this.d && this.c.p()) {
            try {
                du.p(200);
                if (this.a > 0) {
                    if (this.w.d()) {
                        p.play();
                    }
                    --this.a;
                    du.p(800);
                }
                this.l();
            }
            catch (Exception ex) {
                du.p(ex);
            }
        }
        this.v = null;
    }
    
    public final void p(final long n) {
        if (n < 5L) {
            this.showStatus("Client network is good");
            return;
        }
        if (n < 10L) {
            this.showStatus("Client network is fair");
            return;
        }
        if (n < 20L) {
            this.showStatus("CLIENT NETWORK IS POOR!");
            return;
        }
        this.showStatus("CLIENT NETWORK IS BAD, lag >" + n / 5L * 5L + "s!!!");
    }
    
    public final void a() {
        while (this.d && this.c.p()) {
            try {
                final String p = this.c.p();
                if (p == null) {
                    du.p(1000);
                }
                else {
                    final String upperCase = du.p(p, 0).toUpperCase();
                    this.p(upperCase, p.substring(upperCase.length()).trim());
                }
            }
            catch (Exception ex) {
                du.p(ex);
            }
        }
        this.d = false;
        this.f.o();
        this.s.d();
        this.n = null;
    }
    
    public final void l() {
        if (!GameClient.dc && this.f != null && this.f instanceof r && this.f.i() != -1 && System.currentTimeMillis() - GameClient.db > 500L) {
            if (this.f.a()) {
                this.showStatus("Mouse is away, please keep it inside your game window in this game!");
            }
            else {
                this.showStatus("Mouse away");
            }
            this.f.b();
        }
    }
    
    private final void c() {
        long n = Math.abs(du.p(this.da));
        final StringBuffer sb = new StringBuffer();
        final String lowerCase = this.p.toLowerCase();
        for (int i = 0; i < 16; ++i) {
            n = Math.abs(n / 17L * 11L ^ 0x498F2589L);
            final char char1 = lowerCase.charAt((int)(n % this.p.length()));
            if (char1 != ' ') {
                sb.append(char1);
            }
        }
        this.j = sb.toString();
    }
    
    public final void g() {
        if (GameClient.dc) {
            return;
        }
        this.l();
        GameClient.dc = true;
        if (System.currentTimeMillis() - GameClient.db > 500L) {
            this.showStatus("");
        }
    }
    
    public final void f() {
        GameClient.dc = false;
        GameClient.db = System.currentTimeMillis();
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.g();
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.f();
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.g();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.g();
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        this.g();
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent().contains(mouseEvent.getX(), mouseEvent.getY())) {
            this.g();
            return;
        }
        this.f();
    }
    
    static {
        GameClient.db = System.currentTimeMillis();
    }
}
