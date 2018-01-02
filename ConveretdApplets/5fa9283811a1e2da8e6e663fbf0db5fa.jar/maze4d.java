import java.awt.image.ImageObserver;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class maze4d extends Applet implements Runnable
{
    public c p;
    public Graphics d;
    public double a;
    public double n;
    public double v;
    public double i;
    public double l;
    public double b;
    public p c;
    public dp e;
    public z f;
    public double g;
    public boolean h;
    public r j;
    public k k;
    public boolean m;
    public boolean o;
    public t q;
    public boolean r;
    public Image s;
    public int t;
    public int u;
    public boolean w;
    public int x;
    public int y;
    public double z;
    public double dp;
    public double dd;
    public int da;
    public boolean dn;
    public MediaTracker dv;
    public StringTokenizer di;
    public Thread dl;
    public boolean db;
    public int dc;
    public int de;
    public double df;
    public double dg;
    public double dh;
    public l[] dj;
    public int dk;
    public boolean dm;
    public boolean do;
    public boolean dq;
    
    public final void g() {
        (this.q = new t(this.p)).p(0.0, 0.0, this.p.a - new z(this.q.p[this.p.a].p).a);
        this.p.p = this.q;
        this.p.p = 0.0;
        this.p.d = 0.0;
        this.p.o();
        this.dg = 0.0;
        this.de = 0;
        this.dh = 0.0;
        this.p.v();
        this.dk = 0;
    }
    
    public final void init() {
        this.p = new c(0.0, 0.0, 0.01);
        this.c();
        this.g = this.p.l;
        this.j = new r();
        this.k = new k(this.p);
        this.g();
        final Rectangle bounds = this.bounds();
        this.t = bounds.width;
        this.u = bounds.height;
        this.p.p(this.t, this.u);
        this.s = this.createImage(this.t, this.u);
        this.setBackground(Color.white);
        this.requestFocus();
        this.dv = new MediaTracker(this);
        this.di = new StringTokenizer(this.getParameter("imageVect"), "+");
        this.k();
        for (int i = 0; i < 3; ++i) {
            this.dj[i] = new l();
        }
    }
    
    public final boolean p() {
        if (this.dk > 1) {
            return this.dm;
        }
        return this.dm = false;
    }
    
    public final void j() {
        this.dg = this.dh;
        this.dh = this.de * -0.19634954084936207;
        this.df = this.dh - this.dg;
        this.x = 6;
    }
    
    public final void n() {
        this.k.d(this.p.d, -this.dh);
        this.de = 0;
        this.df = 0.0;
        this.dg = 0.0;
        this.dh = 0.0;
    }
    
    public final void l() {
        --this.de;
        this.j();
    }
    
    public final void a() {
        ++this.de;
        this.j();
    }
    
    public final void m() {
        if (this.de < 8) {
            this.a();
            this.repaint();
        }
        else {
            this.m = true;
        }
    }
    
    public final void v() {
        if (this.de > -8) {
            this.l();
            this.repaint();
        }
        else {
            this.m = true;
        }
    }
    
    public final void i() {
        if (this.de == 0) {
            return;
        }
        this.q.a(this.f, this.dh);
        this.k.d(this.p.d, this.dh);
        this.p.i();
    }
    
    public final void k() {
        int n;
        for (n = 0; this.di.hasMoreTokens() && n < 30; ++n) {
            this.p.p[n] = this.di.nextToken();
            this.p.p[n] = this.getImage(this.getDocumentBase(), this.p.p[n]);
            this.dv.addImage(this.p.p[n], n);
        }
        this.p.n = n;
    }
    
    public final void p(final p p) {
        this.v = p.p();
        this.i = p.d();
        this.a = this.v / this.p.b;
        this.n = this.i / this.p.b;
    }
    
    public final void p(final double n, final double n2) {
        if (this.p.a) {
            this.p.p += n;
            this.p.d += n2;
        }
        else {
            this.p.p -= n;
            this.p.d -= n2;
        }
        this.p.v();
    }
    
    public final void d(final double n, final double n2) {
        this.z = n - this.a;
        this.dp = n2 - this.n;
        this.p(this.z, this.dp);
        this.x = 1;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.c.p(n, n2);
        this.d(this.c);
        return true;
    }
    
    public final void d(final p p) {
        this.p(p);
        if (this.p.v) {
            this.n();
            this.p.q();
            this.repaint();
        }
        else if (this.n()) {
            this.repaint();
        }
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        this.c.p(n, n2);
        this.a(this.c);
        return true;
    }
    
    public final void a(final p p) {
        if (this.m && !this.d()) {
            this.m = false;
            this.o = true;
            final double a = this.a;
            final double n = this.n;
            this.p(p);
            this.d(a, n);
            this.e();
            this.repaint();
        }
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        this.c.p(n, n2);
        this.n(this.c);
        return true;
    }
    
    public final void n(final p p) {
        this.p(p);
        this.o = false;
        if (this.r) {
            this.r = false;
            this.p.p(this.v - this.l, this.i - this.b);
            this.repaint();
        }
    }
    
    public final void d(final double n) {
        this.q.a(this.f, n);
        if (this.p.a) {
            this.k.d(this.p.d, n);
            this.p.i();
        }
        ++this.dk;
    }
    
    public final void n(final double n) {
        this.q.d(this.f, n);
        if (this.p.a) {
            this.k.a(this.p.d, n);
            this.p.i();
        }
        ++this.dk;
    }
    
    public final void p(final double n) {
        this.q.p(this.f, n);
        if (this.p.a) {
            this.k.p(this.p.d, n);
            this.p.i();
        }
        ++this.dk;
    }
    
    public final void a(final double dd) {
        this.dd = dd;
        this.q.d(this.dd);
        this.x = 2;
        ++this.dk;
    }
    
    public final void p(final String s) {
        final AppletContext appletContext = this.getAppletContext();
        try {
            appletContext.showDocument(new URL(s));
        }
        catch (MalformedURLException ex) {
            this.showStatus("URL not found");
        }
    }
    
    public final void o() {
        final String parameter = this.getParameter("sponsors");
        if (parameter != null) {
            this.p(parameter);
        }
    }
    
    public final void d(final int n) {
        this.p.p[n] = new Color(255, 255, 255);
    }
    
    public final void p(final int n) {
        if (this.getParameter("colorME" + n) == null) {
            this.d(n);
            return;
        }
        final String s = new String(this.getParameter("colorME" + n));
        if (s == null) {
            this.d(n);
            return;
        }
        if (s.equalsIgnoreCase("red")) {
            this.p.p[n] = new Color(255, 0, 0);
        }
        else if (s.equalsIgnoreCase("green")) {
            this.p.p[n] = new Color(0, 255, 0);
        }
        else if (s.equalsIgnoreCase("blue")) {
            this.p.p[n] = new Color(0, 0, 255);
        }
        else if (s.equalsIgnoreCase("lightGrey")) {
            this.d(n);
        }
        else if (s.equalsIgnoreCase("yellow")) {
            this.p.p[n] = new Color(255, 255, 0);
        }
        else if (s.equalsIgnoreCase("lightBlue")) {
            this.p.p[n] = new Color(0, 255, 255);
        }
        else if (s.equalsIgnoreCase("purple")) {
            this.p.p[n] = new Color(255, 0, 255);
        }
        else {
            this.d(n);
        }
    }
    
    public final void c() {
        final String parameter = this.getParameter("publicPW");
        final String parameter2 = this.getParameter("nameLogo");
        final String parameter3 = this.getParameter("widthLogo");
        final String parameter4 = this.getParameter("heightLogo");
        final String parameter5 = this.getParameter("startViewWithLogo");
        final String parameter6 = this.getParameter("backGroundLogo");
        final String parameter7 = this.getParameter("displayImages");
        this.getParameter("startWithEasyMaze");
        final String parameter8 = this.getParameter("exitNb1");
        this.p.l = "helparrow.gif";
        if (parameter != null) {
            this.p.v = parameter;
        }
        if (parameter2 != null) {
            this.p.i = parameter2;
        }
        if (parameter3 != null) {
            this.p.h = Integer.parseInt(parameter3) * this.p.g / 100.0;
        }
        if (this.p.h > this.p.g) {
            this.p.h = this.p.g;
        }
        else if (this.p.h < 0.2) {
            this.p.h = 0.2;
        }
        if (parameter4 != null) {
            this.p.j = Integer.parseInt(parameter4) * this.p.g / 100.0;
        }
        if (this.p.j > this.p.g) {
            this.p.j = this.p.g;
        }
        else if (this.p.j < 0.2) {
            this.p.j = 0.2;
        }
        int n = 0;
        if (parameter5 != null) {
            n = Integer.parseInt(parameter5);
        }
        this.p.h = (n == 1);
        if (parameter6 != null) {
            n = Integer.parseInt(parameter6);
        }
        this.p.g = (n == 1);
        if (parameter7 != null) {
            n = Integer.parseInt(parameter7);
        }
        this.p.j = (n == 1);
        this.p.k = true;
        if (parameter8 != null) {
            n = Integer.parseInt(parameter8);
        }
        this.p.m = (n == 1);
        for (int i = 0; i < 26; ++i) {
            this.p(i);
        }
    }
    
    public final boolean d(final int n, final int n2, final int n3, final int n4) {
        return this.v >= n && this.i >= n2 && this.v <= n3 && this.i <= n4;
    }
    
    public final boolean p(int n, int n2, int n3, int n4) {
        final int n5 = (int)this.p.n.p;
        final int n6 = (int)this.p.n.d;
        n += n5;
        n3 += n5;
        n2 += n6;
        n4 += n6;
        return this.d(n, n2, n3, n4);
    }
    
    public final boolean d() {
        return this.p(0, 0, 148, 258);
    }
    
    public final void b() {
        final z z = new z();
        if (this.p.b) {
            z.n(this.p.p.p);
            this.p.e();
            this.p.p.p = this.p.p;
        }
        else {
            z.n(this.p.d.p);
            this.p.h();
        }
        this.q.p(-z.p, -z.d, 700.5 - z.a);
    }
    
    public final void h() {
        this.q = null;
        if (this.p.b) {
            this.p.e();
        }
        this.p.c = false;
        this.g();
        this.q.d();
    }
    
    public final void p() {
        this.p.k = true;
        this.h();
    }
    
    public final void e() {
        if (this.p.a) {
            this.f.p(-this.p.p, this.p.d, this.p.a);
        }
        else {
            this.f.n(this.p.v);
        }
    }
    
    public final boolean n() {
        if (this.o) {
            return false;
        }
        if (this.m && (this.d() || this.d(0, 0, 10, 10))) {
            this.m = false;
            final double abs = Math.abs(this.g);
            this.e();
            if (this.p(20, 16, 128, 28)) {
                this.p(this.p.p(this.p.b));
            }
            if (!this.p.k) {
                if (this.p(62, 35, 87, 54)) {
                    this.d(-4.0 * abs);
                }
                if (this.p(62, 54, 87, 65)) {
                    this.d(-abs);
                }
                if (this.p(62, 77, 87, 87)) {
                    this.d(abs);
                }
                if (this.p(62, 87, 87, 108)) {
                    this.d(4.0 * abs);
                }
            }
            if (this.p(2, 56, 28, 81)) {
                this.n(4.0 * abs);
            }
            if (this.p(30, 56, 55, 81)) {
                this.n(abs);
            }
            if (this.p(93, 56, 117, 81)) {
                this.n(-abs);
            }
            if (this.p(118, 56, 145, 81)) {
                this.n(-4.0 * abs);
            }
            if (!this.p.k) {
                if (this.p(7, 84, 26, 104)) {
                    this.p(-1.5707963267948966);
                }
                if (this.p(34, 84, 48, 104)) {
                    this.p(-0.25 * abs);
                }
                if (this.p(99, 84, 113, 104)) {
                    this.p(0.25 * abs);
                }
                if (this.p(118, 84, 144, 104)) {
                    this.p(1.5707963267948966);
                }
            }
            if (this.p(62, 115, 86, 134)) {
                this.a(20.0);
            }
            if (this.p(62, 134, 86, 149)) {
                this.a(2.0);
            }
            if (this.p(62, 153, 86, 168)) {
                this.a(-2.0);
            }
            if (this.p(62, 168, 86, 189)) {
                this.a(-20.0);
            }
            if (this.p.k && this.p(64, 82, 86, 102)) {
                this.p.p = "- Gaze: up=\"w\"; down=\"s\"¬- Regard: haut=\"w\"; bas=\"s\"";
            }
            if (this.dq && this.p(123, 33, 145, 54)) {
                this.p.p = "-Goal: find your way out, a surprise is waiting for you!¬-Central arrows have a smaller effect than external ones.¬-Each button has a function (and you can drag the mouse on the 3D image).¬-If you lose the controlPanel, click the top left corner.¬-** INSTALL ** this game on your Web Site (free, own parameters, logo),¬ click on the creation link 'fast4d.com' below.¬¬-But: trouver la sortie, une surprise vous y attend!¬-Les fl\u00e8ches centrales ont une plus petite influence que les fl\u00e8ches externes.¬-Chaque bouton \u00e0 une fonction (et vous pouvez tirer la souris sur l'image 3D).¬-Si vous perdez le tableau de contr\u00f4le, cliquez le coin tout en haut \u00e0 gauche.";
                this.p.j();
                this.p.n = true;
                this.p.f = false;
                this.dq = false;
            }
            else {
                this.dq = true;
            }
            if (this.p(7, 113, 44, 137) || (this.p.k && this.p(7, 103, 44, 137))) {
                if (this.p.a) {
                    if (this.p.k) {
                        this.p.p = "-Already in \"walking\" mode!¬-D\u00e9j\u00e0 en mode \"pi\u00e9ton\"!";
                    }
                    else {
                        this.p.p = "-Already in flight mode!¬-D\u00e9j\u00e0 en mode vol!";
                    }
                }
                else {
                    this.p.a = true;
                }
            }
            if (this.p(123, 115, 147, 139) || (this.p.k && this.p(123, 105, 147, 139))) {
                if (this.p.b) {
                    if (this.p.a) {
                        this.p.a = false;
                    }
                    else {
                        this.p.p = "-Already in object mode!¬-D\u00e9j\u00e0 en mode objet!";
                    }
                }
                else {
                    this.p.p = "-Object mode: valid only outside!¬-Mode objet: seulement valide¬ \u00e0 l'ext\u00e9rieur!";
                }
            }
            if (this.w && this.p(22, 147, 28, 155)) {
                this.b();
            }
            this.w = false;
            if (this.p(83, 0, 89, 9)) {
                this.w = true;
            }
            if (this.p(7, 38, 19, 50) || this.d(0, 0, 10, 10)) {
                this.p.d(this.p.a.p, this.p.a.d);
            }
            if (this.p(16, 204, 34, 220)) {
                this.p.d = !this.p.d;
            }
            if (this.p(61, 205, 86, 226)) {
                this.p.j = !this.p.j;
                final c p = this.p;
                ++p.f;
                if (this.p.j) {
                    this.p.p = "-Images ON = slower!¬-Images ON = plus lent!";
                }
                else {
                    this.p.p = "-Images OFF = faster!¬-Images OFF = plus rapide!";
                }
            }
            if (this.p.b) {
                if (this.p(118, 204, 130, 214)) {
                    this.p.n();
                }
                if (this.p(118, 215, 130, 224)) {
                    this.p.k();
                }
            }
            else if (this.p(111, 204, 137, 224)) {
                this.p.p = "-Scissors: valid only outside!¬-Ciseaux: valable seulement¬ \u00e0 l'ext\u00e9rieur!";
            }
            if (this.p(37, 242, 114, 258)) {
                this.o();
            }
            if (this.p.q && this.p(7, 171, 45, 189)) {
                this.h();
            }
            if (this.p.q && this.p(102, 171, 141, 189)) {
                if (this.p.k) {
                    final i i = new i(false);
                    final String s = "          ";
                    this.p.p = s + "The full version¬can be bought at fast4d.com¬" + s + s + "###¬" + s + "La version totale¬peut-\u00eatre achet\u00e9e \u00e0 fast4d.com";
                }
                else {
                    this.p();
                }
            }
            if (this.p(127, 233, 144, 247)) {
                this.r = true;
                this.l = this.v;
                this.b = this.i;
            }
            final c p2 = this.p;
            ++p2.g;
            return true;
        }
        this.dq = true;
        return false;
    }
    
    public final boolean keyDown(final Event event, final int n) {
        this.e.p(n);
        this.p(this.e);
        return true;
    }
    
    public final void p(final dp dp) {
        if (this.m) {
            this.m = false;
            final int p = dp.p();
            double abs = Math.abs(this.g);
            double n = 2.17;
            if (this.h) {
                abs *= 4.0;
                n *= 10.0;
            }
            if (this.p.v) {
                this.p.q();
            }
            this.e();
            switch (p) {
                case 90: {
                    if (this.p.k) {
                        this.m = true;
                    }
                    else {
                        this.p(this.g);
                        this.repaint();
                    }
                    break;
                }
                case 32: {
                    this.g = -this.g;
                    this.h = !this.h;
                    this.m = true;
                    break;
                }
                case 1006: {
                    this.n(abs);
                    this.repaint();
                    break;
                }
                case 1007: {
                    this.n(-abs);
                    this.repaint();
                    break;
                }
                case 1004: {
                    if (this.p.k) {
                        this.a(n);
                    }
                    else {
                        this.d(-abs);
                    }
                    this.repaint();
                    break;
                }
                case 1005: {
                    if (this.p.k) {
                        this.a(-n);
                    }
                    else {
                        this.d(abs);
                    }
                    this.repaint();
                    break;
                }
                case 81: {
                    this.a(n);
                    this.repaint();
                    break;
                }
                case 65: {
                    this.a(-n);
                    this.repaint();
                    break;
                }
                case 87: {
                    this.m();
                    break;
                }
                case 83: {
                    this.v();
                    break;
                }
                case 69: {
                    this.q.p(100.0);
                    this.repaint();
                    break;
                }
                case 67: {
                    this.p.s = false;
                    this.repaint();
                    break;
                }
                case 68: {
                    this.q.p(-100.0);
                    this.repaint();
                    break;
                }
                default: {
                    this.m = true;
                    break;
                }
            }
        }
    }
    
    public final void p(final Graphics graphics, final l l) {
        int n = (int)l.p;
        int n2 = (int)l.d;
        if (this.p.k) {
            n += 64;
            n2 += 80;
        }
        else {
            n += 10;
            n2 += 235;
        }
        this.p.p.p();
        graphics.drawOval(n, n2, 22, 22);
        graphics.fillOval(n + 7, n2 + 6, 10, 10);
        this.dj[0].a(n, n2);
        this.dj[1].a(n + 22, n2 + 11);
        this.dj[2].a(n, n2 + 22);
        for (int i = 0; i < 3; ++i) {
            this.dj[i].p(n + 11, n2 + 11, -this.dh);
        }
        for (int j = 0; j < 2; ++j) {
            graphics.drawLine((int)this.dj[j].p, (int)this.dj[j].d, (int)this.dj[j + 1].p, (int)this.dj[j + 1].d);
        }
        if (this.de == 0) {
            graphics.setColor(Color.blue);
            graphics.drawLine(n - 5, n2 + 11, n - 13, n2 + 11);
            graphics.drawLine(n + 27, n2 + 11, n + 35, n2 + 11);
        }
    }
    
    public final void p(final Graphics graphics) {
        final m p = this.p.p;
        final int n = (int)(p.d.p - p.p.p);
        final int n2 = (int)(p.d.d - p.p.d);
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.t, this.u);
        final f d = this.p.d;
        final z a = this.p.a;
        Math.sqrt(d.p * d.p + d.d * d.d + d.a * d.a);
    }
    
    public final boolean a() {
        this.p.i();
        this.q.v();
        return this.q.d();
    }
    
    public final synchronized void d() {
        int n = 0;
        if (this.p.o && !this.p.p()) {
            return;
        }
        if (this.p()) {
            return;
        }
        this.q.c();
        this.i();
        final boolean d;
        if (!(d = this.q.d())) {
            this.n();
            switch (this.x) {
                case 1: {
                    this.q.l();
                    this.p(-this.z, -this.dp);
                    n = (this.a() ? 1 : 0);
                    this.p.p = "-Bumping on a surface!!!!¬-Collision avec un plan!!!!";
                    break;
                }
                case 2: {
                    this.q.f();
                    n = (this.a() ? 1 : 0);
                    this.p.p = "-Bumping on a surface!!!!¬ Try center buttons!¬-Collision!!!!-> boutons centraux!";
                    break;
                }
                case 6: {
                    this.q.l();
                    n = (this.a() ? 1 : 0);
                    System.out.println("Err: out through head rotation!");
                    break;
                }
                default: {
                    System.out.println("Err: can't find last move!");
                    break;
                }
            }
        }
        this.q.e();
        if (d) {
            this.q.b();
            this.q.l();
            if (this.de != 0) {
                this.k.d(this.p.d, -this.dh);
            }
        }
        else if (n != 0) {}
        this.q.v();
        this.p.i();
        this.p.f();
        final int n2 = (int)this.p.p.p;
        final int n3 = (int)this.p.p.d;
        final int n4 = (int)(this.p.d.p - this.p.p.p);
        final int n5 = (int)(this.p.d.d - this.p.p.d);
        this.p.p.setColor(Color.black);
        this.p.p.drawRect(n2 - 1, n3 - 1, n4 + 1, n5 + 1);
        if (this.p.s) {
            this.p.p = "Browser ok for this site,¬but could need updating to see¬a broader Internet content¬and to play FASTER!:¬go to www.fast4d.com (browsers)¬Clear this message with 'C'";
        }
        if (this.p.p.equals("")) {
            final String s = "bouton */x";
            final String string = "¬¬# Number of moves = " + this.dk;
            final String string2 = "¬project. Dist.= " + this.p.a;
            if (this.p.f < 3) {
                if (this.p.j) {
                    this.p.p = "# No image with */x button¬# Pas d'image-->" + s + string + string2;
                }
                else {
                    this.p.p = "# Add images with */x button¬# + images avec le " + s + string + string2;
                }
            }
            else {
                this.p.p = string + string2;
            }
        }
        if (this.y != 1 && this.x == 1) {
            ++this.dk;
        }
        this.y = this.x;
    }
    
    public final void start() {
        this.dl = new Thread(this);
        this.db = false;
        this.dl.start();
    }
    
    public final void stop() {
        this.db = true;
    }
    
    public final void run() {
        this.dl.setPriority(1);
        do {
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        } while (!this.db);
    }
    
    public final void f() {
        this.d.drawString("Loading a few images!", 50, 200);
        for (int i = 0; i < this.dc; ++i) {
            this.d.drawString("X", 50 + i * 15, 240);
        }
        ++this.dc;
    }
    
    public final void paint(Graphics graphics) {
        this.d = graphics;
        graphics = this.s.getGraphics();
        this.p.d(graphics);
        this.p.p = new o(graphics, this.p.d.p + 5.0, this.p.d.p + 105.0);
        if (this.dv.checkAll(true) && this.do) {
            this.p(this.p.p);
            this.p.a(graphics);
            if (this.p.p(this.s)) {
                this.d();
            }
            else {
                this.p.d();
            }
            if (!this.p.p.equals("")) {
                this.p.p.a(this.p.p);
                this.p.p = "";
            }
            if (this.p.n) {
                this.p.v = true;
                this.p.n = false;
            }
            this.p(graphics, this.p.n);
            this.d.drawImage(this.s, 0, 0, null);
            this.m = true;
        }
        else {
            if (this.dv.checkAll(true)) {
                this.stop();
                this.p.c();
                this.q.d();
                this.do = true;
                if (this.db) {
                    this.repaint();
                }
            }
            this.f();
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public maze4d() {
        this.d = null;
        this.a = 0.0;
        this.n = 0.0;
        this.v = 0.0;
        this.i = 0.0;
        this.l = 0.0;
        this.b = 0.0;
        this.c = new p();
        this.e = new dp();
        this.f = new z();
        this.h = true;
        this.m = true;
        this.o = false;
        this.r = false;
        this.s = null;
        this.w = false;
        this.x = 0;
        this.y = 0;
        this.z = 0.0;
        this.dp = 0.0;
        this.dd = 0.0;
        this.da = 1;
        this.dn = false;
        this.db = false;
        this.dc = 5;
        this.de = 0;
        this.df = 0.0;
        this.dg = 0.0;
        this.dh = 0.0;
        this.dj = new l[3];
        this.do = false;
        this.dq = true;
    }
}
