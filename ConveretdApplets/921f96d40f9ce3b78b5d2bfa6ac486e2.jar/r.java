import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.util.StringTokenizer;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class r extends q
{
    private int p;
    private int d;
    private int a;
    private int n;
    private boolean p;
    String[] p;
    protected m[] p;
    protected m[] d;
    protected m[] a;
    protected int v;
    protected int[] p;
    protected int[] d;
    protected int i;
    protected int l;
    protected Image p;
    long p;
    int b;
    int c;
    private dl p;
    private dl d;
    private dl a;
    private dl n;
    private dl v;
    private dl i;
    private dl l;
    
    public r(final y y, final int n) {
        super(2, y);
        this.p = 1500;
        this.d = 180;
        this.a = 30;
        this.p = false;
        this.p = new m[2];
        this.d = new m[2];
        this.a = new m[2];
        this.p = System.currentTimeMillis();
        this.c = 1500;
        this.p = y.p().p();
        (this.p = new String[super.d])[0] = (this.p[1] = "0");
        this.n = n;
        this.p = new int[n];
        this.d = new int[n];
        for (int i = 0; i < 2; ++i) {
            this.p[i] = new m();
            this.d[i] = new m();
            this.a[i] = new m();
        }
    }
    
    public final int a() {
        return this.p;
    }
    
    public final int n() {
        return this.d;
    }
    
    public final int v() {
        return this.a;
    }
    
    protected abstract boolean d(final int p0);
    
    protected abstract boolean a(final int p0);
    
    protected abstract void l();
    
    protected abstract void k();
    
    protected abstract void h();
    
    protected abstract void i();
    
    protected abstract void p();
    
    protected abstract void m();
    
    protected abstract void q();
    
    public final void o() {
        this.r();
        super.o();
    }
    
    protected final void r() {
        super.d = false;
        this.l();
        for (int i = 0; i < this.n; ++i) {
            this.d[i] = 0;
        }
        this.i = 0;
        this.b = 0;
        this.p = System.currentTimeMillis();
        for (int j = 0; j < 2; ++j) {
            this.p[j].p(this.p);
            this.d[j].p(this.d);
            this.a[j].p(this.a);
        }
        final boolean b = false;
        this.l = (b ? 1 : 0);
        this.v = (b ? 1 : 0);
        this.p = false;
        this.k();
        this.f();
    }
    
    public final boolean a() {
        final int i = this.i();
        final int d = this.d(i);
        return i != -1 && !super.p.p().equals("Softwares") && !super.p.n().equals("#Masters") && !super.p.n().equals("#CXQBot") && !super.p.n() && super.p.n().startsWith("#") && (d >= 1600 || d < 500);
    }
    
    public final void b() {
        super.b();
        final int i = this.i();
        if (i == -1) {
            return;
        }
        final int d = this.d(i);
        final int n = (this.l - 20 + i) / 2;
        if (n < 0) {
            return;
        }
        if (this.d[n] > 0) {
            return;
        }
        ++this.i;
        final int[] d2 = this.d;
        final int n2 = n;
        ++d2[n2];
        int n3;
        for (n3 = 0; n >= n3 && this.d[n - n3] > 0; ++n3) {}
        if (n < 5 || this.i < 5 || n3 < 4 || this.i - 2 < 2 * (n + 1) / 3) {
            return;
        }
        this.b = this.c * (this.p / 300 + 1) * (n3 - 4 + 1) * (n3 - 4 + 1);
        if (n3 >= 7 && this.i - 2 >= 2 * (n + 1) / 3 && !this.p[i].endsWith("U")) {
            this.d("SETBOT");
        }
        if (!this.a()) {
            this.b = 0;
        }
        if (n == 19) {
            this.d("USERLOG ATTENTION " + (n + 1) + " " + this.i + " " + n3 + " " + this.p + " " + super.p.n() + " " + super.p.p() + " " + this.b + " " + d);
        }
        if (this.b <= 0) {
            this.b = 0;
            return;
        }
        if (this.p[i].p() && this.p[i].d() > 0) {
            this.p[i].d(n3 - 4 + 1);
        }
        if (n3 >= 7 && this.i - 2 >= 2 * (n + 1) / 3) {
            this.d("UNSIT!");
            this.d("OPEN");
            this.d("USERLOG ATTENTION Penalized");
            super.p.l("<4>***MOUSE ERROR: Please do not play in public table. You will be given a private table to play! Xin dung choi tai ba`n cong cong, server se cho ba.n ba`n choi rieng.");
            return;
        }
        super.p.l("<4>***MOUSE ERROR, PLEASE KEEP THE MOUSE IN YOUR GAME WINDOW WHEN PLAYING HERE. <6>In the unlikely case that you consult moves from chess softwares, please stop doing so. Alternatively, please avoid public tables / <4>XIN GIU CHUOT TRONG CUA SO CHOI GAME. <6>Trong truong hop ban du`ng software, xin ngung ngay.  Ban nen tra'nh choi ta.i ba`n cong cong.");
    }
    
    private final void p(final String s) {
        super.p[0] = du.p(s, 0, ' ');
        if (super.p[0].equals("*")) {
            super.p[0] = null;
        }
        this.p[0] = du.p(s, 1, ' ');
        super.p[1] = du.p(s, 2, ' ');
        if (super.p[1].equals("*")) {
            super.p[1] = null;
        }
        this.p[1] = du.p(s, 3, ' ');
        this.f();
    }
    
    final int d(final int n) {
        if (n != 0 && n != 1) {
            return 0;
        }
        String string = "";
        for (int n2 = 0; n2 < this.p[n].length() && Character.isDigit(this.p[n].charAt(n2)); ++n2) {
            string = String.valueOf(string) + this.p[n].charAt(n2);
        }
        return du.p(string, 10);
    }
    
    public final void a() {
        this.h();
        final int i = this.i();
        final int n = this.l % 2;
        if (super.d) {
            if (this.p[n].p() && this.p[n].d() <= 0 && !this.a[n].p()) {
                this.p[n].p();
                this.a[n].d();
            }
            if (n == i) {
                if (this.a[n].p() && this.a[i].d() < 0) {
                    this.a[n].p();
                    this.d[n].p();
                    this.d("TIMEUP");
                    super.p.l("<4>*** Your free time is out");
                    super.d = false;
                    return;
                }
                if (this.d[n].p() && this.d[i].d() < 0) {
                    this.a[n].p();
                    this.d[n].p();
                    this.d("TIMEUP");
                    super.p.l("<4>*** Your move time is out");
                    super.d = false;
                }
            }
        }
    }
    
    public boolean p(final String s, final String s2) {
        if (super.p(s, s2)) {
            return true;
        }
        if (s.equals("TABLENUMBER")) {
            this.a("Table #" + s2);
            return true;
        }
        if (s.equals("MOVE")) {
            this.l(s2);
            this.d(500);
            return true;
        }
        if (s.equals("MOVES")) {
            this.b(s2);
            this.d(500);
            return true;
        }
        if (s.equals("DRAWREQUEST")) {
            super.p.l("<12> " + s2 + " requests to draw game");
            return true;
        }
        if (s.equals("TIMESETTING")) {
            this.n(s2);
            this.h();
            return true;
        }
        if (s.equals("ADDTIME")) {
            this.v(s2);
            this.h();
            return true;
        }
        if (s.equals("TIMEREMAIN")) {
            this.i(s2);
            this.h();
            return true;
        }
        if (s.equals("WIN") || s.equals("DRAW") || s.equals("LOSE")) {
            this.p(s, du.p(s2, 0, ' '), du.p(s2, 1, ' '), du.p(s2, 2, ' '), du.d(s2, 2, ' '));
            return true;
        }
        if (s.equals("WIN")) {
            this.i(s2);
            return true;
        }
        if (s.equals("WIN")) {
            this.i(s2);
            return true;
        }
        if (s.equals("SAVING")) {
            this.p = "true".equals(s2);
            this.v();
            return true;
        }
        if (s.equals("RESETGAME")) {
            this.r();
            this.d(500);
            return true;
        }
        if (s.equals("STATUS")) {
            super.d = s2.equals("true");
            this.n();
            if (super.d) {
                this.g();
            }
            this.v();
            this.d(500);
            return true;
        }
        if (s.equals("PLAYERS")) {
            this.p(s2);
            this.v();
            this.d(500);
            return true;
        }
        if (s.equals("SIT")) {
            final int p2 = du.p(du.p(s2, 0, ' '), 10);
            super.p[p2] = du.p(s2, 1, ' ');
            this.p[p2] = du.p(s2, 2, ' ');
            if (super.p[0] != null && super.p[1] != null) {
                super.p.h();
            }
            this.v();
            this.d(500);
            return true;
        }
        if (s.equals("UNSIT")) {
            final int p3 = du.p(du.p(s2, 0, ' '), 10);
            super.p[p3] = null;
            this.p[p3] = "0";
            this.v();
            this.d(500);
            return true;
        }
        if (s.equals("PART") || s.equals("QPART")) {
            final String p4 = du.p(s2, 1, ' ');
            for (int i = 0; i < 2; ++i) {
                if (super.p[i] != null && super.p[i].equals(p4)) {
                    super.p[i] = null;
                    this.v();
                    this.d(500);
                }
            }
            return true;
        }
        return false;
    }
    
    private final void p(final String s, final String s2, final String s3, final String s4, String s5) {
        String s6 = "";
        if (s5 != null && s5.length() == 0) {
            s5 = null;
        }
        if (s.equals("WIN")) {
            s6 = String.valueOf(s3) + " wins over " + s4;
        }
        else if (s.equals("DRAW")) {
            s6 = String.valueOf(s3) + " draws to " + s4;
        }
        else if (s.equals("LOSE")) {
            s6 = String.valueOf(s3) + " loses to " + s4;
        }
        String s7;
        if (s2.startsWith("#")) {
            s7 = String.valueOf(s2) + ((s5 == null) ? "" : (" (" + s5 + ")"));
        }
        else {
            s7 = "Table #" + s2 + ((s5 == null) ? "" : (" (" + s5 + ")"));
        }
        if (s2.equalsIgnoreCase(super.p.n())) {
            super.p.p().l("<12>***" + s6 + " on " + s7);
            return;
        }
        super.p.p().i("***" + s6 + " on " + s7);
    }
    
    private final void n(final String s) {
        this.p = du.p(du.p(s, 0, ' '), 10);
        this.d = du.p(du.p(s, 1, ' '), 10);
        this.a = du.p(du.p(s, 2, ' '), 10);
        if (this.p > 1800 || this.d > 450 || this.a > 120) {
            super.p.p().l("<4>***WARNING: time setting is longer than usual!");
        }
        if (!super.d) {
            for (int i = 0; i < 2; ++i) {
                this.p[i].p(this.p);
                this.d[i].p(this.d);
                this.a[i].p(this.a);
            }
            this.h();
        }
    }
    
    private final void v(final String s) {
        final String p = du.p(s, 0, ' ');
        final int p2 = du.p(du.p(s, 1, ' '), 10);
        final int p3 = du.p(du.p(s, 2, ' '), 10);
        final int p4 = du.p(du.p(s, 3, ' '), 10);
        final int p5 = du.p(du.p(s, 4, ' '), 10);
        if (p3 < 0 || p4 < 0 || p5 < 0) {
            super.p.l("*** bad time adjustment");
            return;
        }
        this.p[p2].d(p3);
        this.d[p2].d(p4);
        this.a[p2].d(p5);
        this.h();
        super.p.l("***" + p + " adds time to opponent: " + p3 / 60 + " " + p4 + " " + p5);
    }
    
    private final void i(final String s) {
        final int p = du.p(du.p(s, 0, ' '), 10);
        this.p[p].a(du.p(du.p(s, 1, ' '), 10));
        this.d[p].a(du.p(du.p(s, 2, ' '), 10));
        this.a[p].a(du.p(du.p(s, 3, ' '), 10));
        this.h();
    }
    
    private final void l(final String s) {
        final int p = du.p(s, 10);
        if (this.d(p)) {
            this.a(p);
            super.p.h();
            if (this.l == 2) {
                super.d = true;
                this.v();
            }
            ++this.v;
            if (this.i() != -1) {
                this.q();
            }
            else if (this.v == 1) {
                this.i();
            }
            this.n();
            this.d();
            this.g();
            this.f();
            return;
        }
        super.p.l("<4>***Invalid move received from server");
        this.d("REFRESH");
    }
    
    private final void b(final String s) {
        this.l = 0;
        this.l();
        final StringTokenizer stringTokenizer = new StringTokenizer(s.trim());
        while (stringTokenizer.hasMoreElements()) {
            final int p = du.p(stringTokenizer.nextToken(), 10);
            if (!this.d(p)) {
                super.p.l("<4>***Invalid moves received from server");
                break;
            }
            this.a(p);
        }
        this.v = this.l;
        this.q();
        this.n();
        this.d();
        this.g();
        this.f();
    }
    
    final void d() {
        this.d[this.l % 2].p(this.d);
        this.a[this.l % 2].p(this.a);
    }
    
    final void n() {
        for (int i = 0; i < 2; ++i) {
            if (this.d[i].p()) {
                this.d[i].p();
            }
            if (this.p[i].p()) {
                this.p[i].p();
            }
            if (this.a[i].p()) {
                this.a[i].p();
            }
        }
    }
    
    final void g() {
        if (!super.d) {
            return;
        }
        final int n = this.l % 2;
        if (!this.d[n].p()) {
            this.d[n].d();
        }
        if (!this.p[n].p()) {
            if (this.p[n].d() >= 0) {
                this.p[n].d();
                return;
            }
            if (!this.a[n].p()) {
                this.a[n].d();
            }
        }
    }
    
    public final void p(final Panel panel) {
        this.p = new dl("<<");
        this.d = new dl(" <");
        this.a = new dl(" >");
        this.n = new dl(">>");
        this.v = new dl("*******", dl.j);
        this.i = new dl("*******", dl.j);
        this.l = new dl("Options", dl.j);
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        final Panel panel4 = new Panel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.add("West", panel4);
        panel.add("Center", panel3);
        panel.add("East", panel2);
        panel2.setLayout(new FlowLayout(2, 0, 0));
        panel2.add(this.i);
        panel2.add(this.v);
        panel2.add(this.l);
        panel3.setLayout(new FlowLayout(1, 0, 0));
        panel4.setLayout(new FlowLayout(0, 0, 0));
        panel4.add(this.p);
        panel4.add(this.d);
        panel4.add(this.a);
        panel4.add(this.n);
        final dl[] array = { this.p, this.d, this.a, this.n, this.v, this.i, this.l };
        for (int i = 0; i < array.length; ++i) {
            array[i].p(this);
        }
    }
    
    final void v() {
        if (super.d) {
            if (this.i() == -1) {
                if (super.p.n().startsWith("#")) {
                    this.v.p(super.p.a() ? "Fight!" : (super.p.v() ? "TourRegister" : "Join!"));
                }
                else {
                    this.v.p("*******");
                }
                this.i.p("*******");
                return;
            }
            this.v.p("Resign");
            this.i.p("Draw");
        }
        else {
            if (this.i() == -1) {
                this.v.p(super.p.a() ? "Fight!" : (super.p.v() ? "TourRegister" : "Join!"));
                this.i.p("*******");
                return;
            }
            this.v.p("Unsit");
            if (this.i() == 0 || this.l >= 2) {
                this.i.p("Reset");
                return;
            }
            this.i.p("*******");
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        try {
            if (actionCommand == "Wait" || actionCommand.toString().startsWith("*")) {
                super.p.l("<4>***No action available");
                return;
            }
            if (actionCommand == "<<") {
                this.m();
                return;
            }
            if (actionCommand == " <") {
                this.p();
                return;
            }
            if (actionCommand == " >") {
                this.i();
                return;
            }
            if (actionCommand == ">>") {
                this.q();
                return;
            }
            if (actionCommand == "Reset") {
                this.d("RESET");
                return;
            }
            if (actionCommand == "Resign") {
                super.p.l("<12>*** Sending request to resign...");
                this.d("RESIGN");
                return;
            }
            if (actionCommand == "Draw") {
                super.p.l("<12>*** Sending request to draw...");
                this.d("DRAW");
                return;
            }
            if (actionCommand == "Unsit") {
                if (!super.d) {
                    this.d("UNSIT");
                    return;
                }
                this.d("UNSIT!");
            }
            else {
                if (actionCommand == "Options") {
                    this.e();
                    return;
                }
                if (actionCommand == "Sync") {
                    this.d("REFRESH");
                    super.p.l("<2>***Done requesting client to sync with server");
                    return;
                }
                if (actionCommand == "Fight!") {
                    this.d("REQUEST");
                    super.p.l("<2>***Start fighting in " + super.p.p() + "...");
                    return;
                }
                if (actionCommand == "TourRegister") {
                    this.d("TOURREGISTER");
                    super.p.l("<2>***Registering for tournament...");
                    return;
                }
                if (actionCommand != "Join!") {
                    System.err.println("Unknown argument: " + actionCommand);
                    return;
                }
                if (super.p[0] == null) {
                    this.d("REQUEST " + 0);
                    super.p.l("<2>*** Requesting 1st role");
                    return;
                }
                if (super.p[1] == null) {
                    this.d("REQUEST " + 1);
                    super.p.l("<2>*** Requesting 2nd role");
                    return;
                }
                this.d("REQUEST 1");
                super.p.l("<2>*** Requesting a seat");
            }
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
