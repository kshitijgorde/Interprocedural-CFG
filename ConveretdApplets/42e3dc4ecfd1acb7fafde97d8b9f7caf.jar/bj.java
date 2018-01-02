import java.awt.MenuItem;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.PopupMenu;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class bj extends av
{
    private static al a;
    private static al b;
    private static al c;
    private boolean d;
    private int e;
    private String f;
    private boolean g;
    private boolean h;
    private String i;
    private int j;
    private int k;
    private String l;
    private cz m;
    private int n;
    private String o;
    private int p;
    private final Dimension q;
    private ch r;
    private PopupMenu s;
    private n t;
    
    public void c(final int n, final int n2) {
        final p a = p.a();
        if (n.b().o()) {
            a.d(this.s());
            return;
        }
        if (this.b() && 0 == n.b().j()) {
            Main.g();
            return;
        }
        if (this.v() == 2 && this.t.h() < 2) {
            bj.b.setVisible(true);
            return;
        }
        if (this.v() == 1 && this.t.h() < 1) {
            bj.a.setVisible(true);
            return;
        }
        if (!i.b().a(this.r())) {
            if (this.a()) {
                final ce ce = new ce(Main.h(), Main.p.a("dialog.enterpass"));
                ce.setSize(200, 125);
                final Dimension screenSize = ce.getToolkit().getScreenSize();
                ce.setLocation(screenSize.width / 2 - ce.getSize().width / 2, screenSize.height / 2 - ce.getSize().height / 2);
                ce.a.addActionListener(new cg(this, ce));
                ce.setVisible(true);
            }
            else {
                a.d(this.s());
            }
        }
    }
    
    public Dimension g() {
        return this.q;
    }
    
    public boolean a() {
        return this.g;
    }
    
    private void h(final int n) {
        if (n == 0) {
            this.g = false;
        }
        else {
            this.g = true;
        }
    }
    
    private void i(final int n) {
        if (n == 0) {
            this.h = false;
        }
        else {
            this.h = true;
        }
    }
    
    public boolean b() {
        return this.h;
    }
    
    public String c() {
        return this.f;
    }
    
    public cz d() {
        return this.m;
    }
    
    public void a(final cz m) {
        this.m = m;
    }
    
    public String e() {
        return this.l;
    }
    
    public void c(final String l) {
        this.l = l;
    }
    
    public void d(final String f) {
        this.f = f;
    }
    
    public int f() {
        return this.n;
    }
    
    public void a(final int n) {
        this.n = n;
    }
    
    public int t() {
        return this.p;
    }
    
    public void d(final int p) {
        this.p = p;
    }
    
    public String toString() {
        return this.o;
    }
    
    public int u() {
        return this.e;
    }
    
    public void e(final int e) {
        this.e = e;
    }
    
    public int v() {
        return this.k;
    }
    
    public void f(final int k) {
        this.k = k;
    }
    
    public int w() {
        return this.j;
    }
    
    public void g(final int j) {
        this.j = j;
    }
    
    public void b(final Graphics graphics, final Component component, final int n, final int n2) {
        graphics.fillRect(n, n2, this.g().width, this.g().height);
        graphics.setColor(dj.x);
        if (this.d) {
            graphics.setFont(dj.ak);
        }
        else {
            graphics.setFont(dj.aj);
        }
        graphics.drawImage(ImageRes.a5, n + 4, n2 + 4, component);
        this.i = this.r();
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        for (int i = fontMetrics.stringWidth(this.i) + 19; i > 132; i = fontMetrics.stringWidth(this.i) + 19) {
            this.i = this.i.substring(0, this.i.length() - 4) + "...";
        }
        graphics.drawString(this.i, 19, n2 + 14);
        if (this.f() > 0) {
            final String string = "(" + this.f() + ")";
            graphics.drawString(string, this.g().width - (Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont()).stringWidth(string) + 4), n2 + 14);
        }
        graphics.setColor(dj.b);
    }
    
    public bj(final String o) {
        this.d = false;
        this.g = false;
        this.h = false;
        this.j = 0;
        this.k = 0;
        this.l = "";
        this.n = 0;
        this.p = 0;
        this.q = new Dimension(178, 18);
        try {
            this.o = o;
            final StringTokenizer stringTokenizer = new StringTokenizer(o, " ");
            this.b(stringTokenizer.nextToken());
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    if (stringTokenizer.hasMoreTokens()) {
                        this.a(Integer.parseInt(stringTokenizer.nextToken()));
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        this.d(Integer.parseInt(stringTokenizer.nextToken()));
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        this.c(stringTokenizer.nextToken());
                    }
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(o, ":");
                    stringTokenizer2.nextToken();
                    stringTokenizer2.nextToken();
                    if (stringTokenizer2.hasMoreTokens()) {
                        this.d(stringTokenizer2.nextToken());
                    }
                }
                catch (Exception ex) {
                    b.a(o, 3);
                    b.a(ex, 3);
                }
            }
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.s(), "^");
            if (stringTokenizer3.hasMoreTokens()) {
                this.a(ah.a(stringTokenizer3.nextToken().substring(1), "_", " "));
            }
            else {
                this.a("error");
            }
            if (stringTokenizer3.hasMoreTokens()) {
                this.g(Integer.parseInt(stringTokenizer3.nextToken()));
            }
            if (stringTokenizer3.hasMoreTokens()) {
                this.e(Integer.parseInt(stringTokenizer3.nextToken()));
            }
            if (stringTokenizer3.hasMoreTokens()) {
                this.i(Integer.parseInt(stringTokenizer3.nextToken()));
            }
            if (stringTokenizer3.hasMoreTokens()) {
                this.f(Integer.parseInt(stringTokenizer3.nextToken()));
            }
            if (stringTokenizer3.hasMoreTokens()) {
                this.h(Integer.parseInt(stringTokenizer3.nextToken()));
            }
            if (stringTokenizer3.hasMoreTokens()) {
                this.b(Integer.parseInt(stringTokenizer3.nextToken()));
            }
        }
        catch (Exception ex2) {
            b.a(this.s(), 3);
            b.a(ex2, 3);
        }
        this.t = n.b();
        this.b(dj.b);
    }
    
    public void a(final boolean d) {
        this.d = d;
        this.p();
    }
    
    public PopupMenu a(final int n, final int n2, final Component component) {
        if (this.s != null) {
            this.s.removeAll();
        }
        if (this.s == null) {
            this.s = new PopupMenu();
            this.r = new ch(this);
        }
        MenuItem menuItem;
        if (!Main.e(this.s())) {
            menuItem = new MenuItem(Main.p.a("cams.popupmenu.addfavourites") + " " + this.r());
            menuItem.setName("addfav");
        }
        else {
            menuItem = new MenuItem(Main.p.a("cams.popupmenu.removefavourites") + " " + this.r());
            menuItem.setName("remfav");
        }
        menuItem.addActionListener(this.r);
        this.s.add(menuItem);
        return this.s;
    }
    
    public String b(final int n, final int n2, final Component component) {
        String s = "";
        if (this.b()) {
            s = Main.p.a("chat.rooms.status.broadcaster") + " ";
        }
        switch (this.v()) {
            case 0: {
                if (this.a()) {
                    s = s + Main.p.a("chat.rooms.status.private") + " ";
                    break;
                }
                break;
            }
            case 1: {
                if (this.a()) {
                    s = s + Main.p.a("chat.rooms.status.silver.private") + " ";
                    break;
                }
                s = s + Main.p.a("chat.rooms.status.silver") + " ";
                break;
            }
            case 2: {
                if (this.a()) {
                    s = s + Main.p.a("chat.rooms.status.gold.private") + " ";
                    break;
                }
                s = s + Main.p.a("chat.rooms.status.gold") + " ";
                break;
            }
        }
        String s2 = "<html><body bgcolor=\"#CDDBE8\" text=\"#1F5285\"><font face=\"verdana\" size=\"2\"> " + this.r() + " <br> Owner:" + this.e();
        if (s.length() > 0) {
            s2 = s2 + "<br> " + s;
        }
        if (this.c() != null) {
            s2 = s2 + "<br> " + this.c();
        }
        return s2 + "</font></body></html>";
    }
    
    static {
        bj.a = new al(Main.h(), Main.p.a("dialog.goldsilverroom"), 400, 100);
        bj.b = new al(Main.h(), Main.p.a("dialog.goldroom"), 400, 100);
        bj.c = new al(Main.h(), Main.p.a("dialog.bcroom"), 400, 100);
        bj.a.a.addActionListener(new am());
        bj.b.a.addActionListener(new am());
        bj.c.a.addActionListener(new di());
    }
}
