// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.io.DataInputStream;
import java.awt.Color;
import java.awt.Image;
import java.util.Vector;

public abstract class aj extends ap implements cu
{
    private be a;
    protected c a;
    an a;
    protected bj a;
    protected bj b;
    protected bj c;
    protected bj d;
    protected bj e;
    protected bj f;
    protected bj g;
    Vector a;
    Vector b;
    av a;
    private av b;
    private el a;
    private String[] a;
    private String[] b;
    private boolean[] a;
    private int b;
    private String b;
    private boolean a;
    private boolean[] b;
    String a;
    private Image a;
    private boolean c;
    private boolean d;
    private int c;
    private int d;
    Color a;
    Color b;
    private l a;
    private String c;
    private ew a;
    private o a;
    private ca a;
    private u a;
    
    public aj() {
        this.a = new Vector();
        this.b = new Vector();
        this.c = false;
        this.d = false;
        this.c = 0;
        this.d = 0;
        this.c = "";
        this.a = ew.a();
    }
    
    public final ek a() {
        return (ek)super.a();
    }
    
    public final o a() {
        return this.a;
    }
    
    public final void a(final l l) {
        this.a.a(l);
    }
    
    public final void b(final l l) {
        this.a.a(l);
    }
    
    public final void c() {
        try {
            this.a = new o();
            ((o)(this.a = ((ek)super.a()).a())).a(this.a, new bz[] { bz.b, bz.a });
        }
        catch (Exception ex) {
            this.a.b("Exception in initAdsSubsystem", ex);
        }
        if (((ek)super.a()).e) {
            if (((ek)super.a()).d() || ((ek)super.a()).c()) {
                this.a(new d(this));
            }
            else {
                this.a(new bt(this));
            }
        }
        else {
            this.a(new j(this));
        }
        super.c();
        this.a = new be(this);
        final int d = this.d();
        this.b = d;
        this.a = new String[d + 1];
        this.b = new String[d + 1];
        this.a = new boolean[d + 1];
        this.b = new boolean[d];
        for (int i = 0; i < d + 1; ++i) {
            this.b[i] = "";
            this.a[i] = "";
        }
        final String parameter;
        if ((parameter = super.a.getParameter("adv_interval")) != null) {
            try {
                final int n = Integer.parseInt(parameter) / 15;
                this.d = n;
                this.c = n;
            }
            catch (NumberFormatException ex2) {}
        }
        (this.a = new c()).a(this.a().a(1716519079));
        this.a.a(this.a().a(1716519056));
        if (this.a().getParameter("isladder") == null) {
            this.a.a(this.a().a(1716519058));
        }
        this.a.a(false);
        this.a = new an(this.a().a(1716519097), null, this.a().f());
        this.a = new bj(this.a().a(1716519073));
        this.c = new bj(this.a().a(1716519076));
        this.b = new bj(this.a().a(1716519080));
        this.d = new bj(((ek)super.a()).e ? this.a().a(1716519063) : this.a().a(1716525261));
        this.e = new bj(this.a().a(1716519082));
        this.g = new bj(this.a().a(1716519070));
        this.f = new bj(this.a().a(1716519095));
        this.d.a(false);
        this.f.a(false);
        this.c.a(false);
        this.b.a(false);
        this.a = this.a().a("yahoo.games.table_side_tabcolor_bg", ap.c.getRGB());
        this.b = this.a().a("yahoo.games.table_side_tabcolor_fg", 16777215);
        this.a();
        final ek ek;
        if ((ek = (ek)super.a()).a() && ek.e && ek.d()) {
            ((ca)(this.a = new l("TAB", "table", 120000L, ek.b(), this))).a(this.a);
        }
    }
    
    public void a() {
        (this.a = this.a()).a(Color.black, this.a().a("yahoo.games.table_panel_bg", 13421721), new Color(13421721), new Color(3355392), new Color(16777164), new Color(10066278));
        this.a.a(Color.black, new Color(16777164), new Color(13421721), new Color(3355392), new Color(16777164), new Color(10066278));
        this.a.a();
        for (int i = 0; i < this.a.size(); ++i) {
            if (!((ek)super.a()).e) {
                this.a.b.a((u)this.a.elementAt(i), 10, 2, 2, 1, 1, i, 0, 1, 2, 1, 2);
            }
            else {
                this.a.b.a((u)this.a.elementAt(i), 10, 2, 2, 1, 1, 0, i, 1, 0, 1, 0);
            }
        }
        for (int j = 0; j < this.b.size(); ++j) {
            if (!((ek)super.a()).e) {
                this.a.c.a((u)this.b.elementAt(j), 10, 2, 2, 1, 1, j, 0, 1, 2, 1, 2);
            }
            else {
                this.a.c.a((u)this.b.elementAt(j), 10, 2, 2, 1, 1, 0, j, 1, 0, 1, 0);
            }
        }
        this.b = this.a().a(1716519069) + ((this.d() > 2) ? this.a().a(1716519068) : ".") + (this.b() ? this.a().a(1716519051) : "");
        this.q();
        this.b();
        this.b = this.a;
        if (this.b != null) {
            (this.a = new el()).b(Color.white);
            this.a.a(Color.black);
            this.b.a(this.a, 0, 0);
        }
    }
    
    public final void a(final String s) {
        final aj aj = this;
        final es a = new es(s);
        this = aj;
        if (aj.a == null) {
            this.a = a;
        }
        new x(this.a, super.a, a);
    }
    
    public abstract av a();
    
    protected static Color a() {
        return Color.white;
    }
    
    protected static Color b() {
        return Color.black;
    }
    
    protected final Color a(final int n) {
        if (!super.a.b()) {
            return Color.black;
        }
        if (n % 2 == 0) {
            return Color.red.darker();
        }
        return Color.blue.darker();
    }
    
    public final u a() {
        return this.g;
    }
    
    public final t a() {
        return this.a.a;
    }
    
    public boolean b() {
        return false;
    }
    
    public static int a() {
        if (dm.b() <= 600) {
            return 3;
        }
        return 4;
    }
    
    public final void d() {
        if (this.d) {
            this.a(this.d = false);
        }
        this.r();
    }
    
    protected static void a(final av av, final u u) {
        av.a(u, 1, 1, 1, 3, true);
    }
    
    protected static void b(final av av, final u u) {
        av.a(u, 10, 2, 2, 1, 1, 0, 1);
    }
    
    protected static void c(final av av, final u u) {
        av.a(u, 1, 1, 2, 3, false);
    }
    
    protected static void d(final av av, final u u) {
        av.a(u, 1, 1, 0, 2, true);
    }
    
    protected final void e(final av av, final u u) {
        if (!((ek)super.a()).e) {
            av.a(u, 17, 2, 2, 2, 1, 1, 3);
            return;
        }
        av.a(u, 10, 3, 0, 1, 3, 0, 1);
    }
    
    protected static void f(final av av, final u u) {
        av.a(u, 1, 1, 0, 0, false);
    }
    
    protected static void g(final av av, final u u) {
        av.a(u, 10, 2, 2, 1, 1, 0, 1);
    }
    
    protected static void a(final av av, final av av2) {
        av.a(av2, 2, 2, 1, 1, false);
    }
    
    private void b() {
        this.a(-1, (this.b > 0 && (this.a.a == 0 || super.a.a || this.a)) ? this.a().a(1716519060) : "", false, false);
    }
    
    private void q() {
        if (super.a.c) {
            this.a(-2, "", false, false);
            return;
        }
        if (this.a() >= 0L && this.b() == 0) {
            long n;
            if ((n = this.a() - System.currentTimeMillis()) < 0L) {
                n = 0L;
            }
            this.a(-2, this.a().a(1716524549) + y.d.a(n + 999L), false, false);
            return;
        }
        if (!super.a.d()) {
            this.a(-2, this.b, false, false);
            return;
        }
        for (int i = 0; i < this.d(); ++i) {
            this.a(i, this.b[i] ? this.a().a(1716519090) : this.a().a(1716519072), false, false);
        }
    }
    
    private void r() {
        this.c.a(System.currentTimeMillis() >= this.a() && super.a.d() && this.e() >= 0 && super.a.a <= 0);
    }
    
    public final av b() {
        return this.a;
    }
    
    protected boolean a() {
        return true;
    }
    
    private void s() {
        for (int i = 0; i < this.d(); ++i) {
            this.g_(i);
            if (super.a[i] != null && super.a.b()) {
                super.a[i].a.a(this.a(this.b(i)));
            }
        }
    }
    
    private static boolean b(final String s) {
        return s.length() > 0 && s.charAt(0) == '~';
    }
    
    public final void g_(final int n) {
        super.g_(n);
        final boolean b = super.a[n] == null;
        super.a[this.a(n)].b.c((!b && (!b(super.a[n].a) || this.e() != -1)) ? 1 : 0);
        if (!b && b(super.a[n].a)) {
            super.a[this.a(n)].a.a(((ek)super.a()).b);
        }
    }
    
    public final void e() {
        for (int i = 0; i < this.d(); ++i) {
            this.l(i);
        }
    }
    
    private void l(final int n) {
        super.a[this.a(n)].a.a((this.a.a == 0 || super.a.a || this.a) && super.a == -1 && (super.a[n] == null || b(super.a[n].a)) && this.a());
    }
    
    public void a(final byte b, final DataInputStream dataInputStream) {
        switch (b) {
            case 99: {
                final eu a = this.a(dataInputStream);
                final String utf = dataInputStream.readUTF();
                if (!this.a(a.a) && !this.a().c()) {
                    this.e(a.b + ": " + utf);
                    return;
                }
                break;
            }
            case 91: {
                this.a().a(dataInputStream.readByte());
            }
            case 120: {
                final boolean b2 = dataInputStream.readByte() != 0;
                final String utf2 = dataInputStream.readUTF();
                this.a().a();
                if (utf2.length() > 0) {
                    this.a(this.a().a(1716519093) + (b2 ? this.a().a(1716519094) : this.a().a(1716519062)), Color.blue);
                }
                if (utf2.equals(this.a())) {
                    this.a(false);
                    return;
                }
                break;
            }
            case 54: {
                this.a().c();
            }
            case 57: {
                final byte byte1;
                if ((byte1 = dataInputStream.readByte()) == -1) {
                    for (int i = 0; i < this.d(); ++i) {
                        this.b[i] = false;
                    }
                }
                else {
                    this.b[byte1] = true;
                }
                this.q();
            }
            default: {
                super.a(b, dataInputStream);
                break;
            }
        }
    }
    
    private void t() {
        boolean b = true;
        for (int i = 0; i < super.c.size(); ++i) {
            if (!((db)super.c.elementAt(i)).a()) {
                b = false;
            }
        }
        if (b) {
            this.a('D');
        }
    }
    
    public final void f() {
        for (int i = 0; i < super.c.size(); ++i) {
            ((db)super.c.elementAt(i)).c();
        }
        ((ek)super.a()).a(super.a.e);
    }
    
    public final void g() {
        this.a(false);
    }
    
    public final void a(final boolean b) {
        boolean b2 = true;
        this.a.a("CompetitiveClientTable.doExit called, forceExit=" + b);
        for (int i = 0; i < super.c.size(); ++i) {
            if (!((db)super.c.elementAt(i)).a(b)) {
                this.a.a("module.doExit returned false. forceExit=" + b);
                b2 = false;
            }
        }
        if (super.a.a > 0) {
            this.d = true;
            b2 = false;
        }
        if (b2 || b) {
            this.a.a("calling CompetitiveClientTable.superDoExit()");
            this.f();
        }
    }
    
    public final void h() {
        this.a('{', (byte)this.e());
    }
    
    public final void d(final int n) {
        this.a('{', (byte)n);
    }
    
    public final void e(final int n) {
        this.a('T', (byte)n);
    }
    
    final void a(final eu eu) {
        this.a.a.a(eu.b, eu);
        this.a("*** " + eu.b + this.a().a(1716519071), Color.blue);
        if (eu.a.equals(this.a().c)) {
            this.c = true;
        }
        else if (this.c) {
            this.k();
        }
        for (int i = 0; i < super.c.size(); ++i) {
            ((db)super.c.elementAt(i)).a(eu);
        }
        this.c(eu);
        this.a(eu, 0, eu.a);
    }
    
    final void b(final eu eu) {
        this.a.a.a(this.a.a.a(eu.b, 0));
        this.a("*** " + eu.b + this.a().a(1716519074), Color.blue);
        if (!eu.a.equals(this.a().c)) {
            this.k();
        }
    }
    
    public void i() {
        for (int i = 0; i < super.c.size(); ++i) {
            ((db)super.c.elementAt(i)).d();
        }
        if (this.a != null) {
            try {
                this.a.flush();
            }
            catch (SecurityException ex) {}
        }
        final dm a;
        if ((a = (this = this).a()).a() && a.e && a.d()) {
            this.a.a(this.a);
            this.a = null;
        }
    }
    
    final void h_(final int a) {
        if (a < this.a.a.size()) {
            final c a2;
            (a2 = this.a).a.a((String)a2.a.elementAt(a));
            a2.a = a;
        }
        if (this.a) {
            if (a == 0) {
                this.a(this.a().a(1716519067), Color.blue);
            }
            else if (a == 1) {
                this.a(this.a().a(1716519084), Color.blue);
            }
            else if (a == 2) {
                this.a(this.a().a(1716519075), Color.blue);
            }
        }
        this.b();
        this.e();
    }
    
    final void j() {
        this.b();
        this.e();
    }
    
    final void c(final eu eu) {
        if (((ek)super.a()).j || ((ek)super.a()).i) {
            this.a.a.a(eu.c, this.a.a.a(eu.b, 0), 1);
        }
    }
    
    protected final void a(final eu eu, final int n, final int n2) {
        this.a().a(n, n2, this.a.a, this.a.a.a(eu.b, 0));
    }
    
    public final void a(final String s, final Color color) {
        this.a.b.a(s, color);
    }
    
    public final void k() {
        if (this.a.a) {
            ((ek)super.a()).g();
        }
    }
    
    private void f(final String c) {
        if (this.a != null && !super.a.c) {
            this.a.a(c);
        }
        this.a.a.a(c);
        if (!this.c.equals(c)) {
            if (!this.a().c()) {
                this.a.b.a(c);
            }
            this.c = c;
        }
    }
    
    private void u() {
        if (super.a == -1) {
            this.f((this.a[super.a.length].equals("") || this.a[super.a.length]) ? this.b[super.a.length] : this.a[super.a.length]);
            return;
        }
        this.f((this.a[super.a].equals("") || this.a[super.a.length]) ? this.b[super.a] : this.a[super.a]);
    }
    
    public final void a(final int n, final String s) {
        this.a(n, s, true, false);
    }
    
    private void a(final int n, final String s, final boolean b, final boolean b2) {
        final String[] array = b ? this.b : this.a;
        if (n == -1 || n == -3) {
            array[super.a.length] = s;
            if (b2) {
                this.a[super.a.length] = !s.equals("");
            }
        }
        if (n == -2 || n == -3) {
            for (int i = 0; i < super.a.length; ++i) {
                this.a(i, s, b, b2);
            }
        }
        if (n >= 0) {
            array[n] = s;
            if (b2) {
                this.a[n] = !s.equals("");
            }
        }
        this.u();
    }
    
    public final int a(final int n) {
        if (super.a >= 0) {
            return (n - super.a + this.d()) % this.d();
        }
        return n;
    }
    
    public final int b(final int n) {
        if (super.a >= 0) {
            return (n + super.a) % this.d();
        }
        return n;
    }
    
    public final String a(final int n) {
        if (super.a[n] == null) {
            return "????";
        }
        return super.a[n].b;
    }
    
    public final void a(final u u) {
        this.a.addElement(u);
    }
    
    public final void b(final u u) {
        this.b.addElement(u);
    }
    
    public void a(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.a) {
                this.i((byte)this.a.a);
                return;
            }
            if (event.target == this.a) {
                this.a(this.a().c() ? "http://help.yahoo.com/help/us/yahooligans/games/" : ("http://" + this.a().a + "/games/helphub.html?page=" + this.a().d), "_blank");
                return;
            }
            if (event.target == this.c) {
                this.p();
                return;
            }
            if (event.target == this.d) {
                this.t();
                return;
            }
            if (event.target == this.f) {
                ((ek)(this = this).a()).a.a(super.a.e);
                return;
            }
            if (event.target == this.e) {
                ((ek)super.a()).a(super.a);
                return;
            }
            if (event.target == this.b) {
                this.a('A');
                return;
            }
        }
        for (int i = 0; i < super.c.size(); ++i) {
            super.c.elementAt(i);
        }
    }
    
    final void a(final String s, final boolean b) {
        final ax a = this.a.a.a(this.a(s).b, 0);
        if (b) {
            this.a.a.a(new bh(((ek)super.a()).d), a, 0, 0);
            return;
        }
        this.a.a.a(a, 0, 0);
    }
    
    protected final void b(final String s) {
        super.b(s);
        for (int i = 0; i < super.c.size(); ++i) {
            ((db)super.c.elementAt(i)).a(s);
        }
        this.a = s.equals(this.a());
        if (this.a) {
            this.a(this.a().a(1716519087), Color.blue);
        }
        this.a.a(this.a);
        this.f.a(this.a);
        this.b();
        this.e();
    }
    
    public void a(final int n) {
        this.b.a(true);
        this.d.a(true);
        this.e();
        this.s();
        this.o();
    }
    
    public void b(final int n, final String s) {
        super.b(n, s);
        this.l(n);
        this.g_(n);
        --this.b;
        this.q();
        if (this.b == 0) {
            this.b();
            this.b.a(false);
        }
        this.u();
        this.r();
    }
    
    public void b(final int n) {
        this.b.a(false);
        this.e();
        this.s();
        this.u();
        this.d.a(false);
        this.o();
    }
    
    public void g(final int n) {
        super.g(n);
        if (super.a != -1) {
            this.b.a(true);
        }
        for (int i = 0; i < super.c.size(); ++i) {
            ((db)super.c.elementAt(i)).b(n);
        }
        this.l(n);
        this.g_(n);
        ++this.b;
        this.b();
        this.r();
        this.q();
    }
    
    public void l() {
        super.l();
        if (this.a() > 0L && !super.a.c) {
            this.q();
        }
        if (this.c > 0) {
            --this.c;
            if (this.d == 0) {
                this.c = this.d;
                ((ek)super.a()).h();
            }
        }
    }
    
    public void m() {
        super.m();
        if (this.b != null) {
            this.b.a(this.a);
        }
        this.q();
        if (!((ek)super.a()).e) {
            this.a().a("gamesGameOn()");
        }
    }
    
    public final void n() {
        this.q();
        this.r();
    }
    
    public final void a(final cw cw) {
        super.a(cw);
        if (this.b != null) {
            this.b.a(this.a, 0, 0);
        }
        this.q();
        if (((ek)super.a()).e) {
            ((ek)super.a()).h();
        }
        if (!((ek)super.a()).e) {
            this.a().a("gamesGameOff()");
        }
    }
    
    public final boolean a(final bg bg) {
        if (bg.a == this.a) {
            final Image a = bg.a;
            if (null != a) {
                this.a.a.a(a);
                if (this.a != null) {
                    try {
                        this.a.flush();
                    }
                    catch (SecurityException ex) {}
                }
                this.a = a;
                this.a = bg.b;
            }
        }
        return true;
    }
    
    static {
        new Color(10944512);
    }
}
