import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import au.com.rocketdog.project.awc.applet.Main;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.PopupMenu;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class cz extends av implements bb, bd
{
    private Image a;
    private int b;
    private Image c;
    private boolean d;
    private int e;
    private boolean f;
    private int g;
    private boolean h;
    private boolean i;
    private String j;
    private static p k;
    private PopupMenu l;
    private b5 m;
    private al n;
    private static final Toolkit o;
    public static final String p;
    public static final String q;
    public static final String r;
    public static final String s;
    public static final String t;
    public static final String u;
    private boolean v;
    private int w;
    private boolean x;
    private final Dimension y;
    
    public void a(final b5 b5) {
        if (b5 != null) {
            this.a(cz.o.createImage(b5.a(), 0, b5.f()));
        }
        else {
            this.a(ImageRes.a2);
        }
    }
    
    public b5 c() {
        return this.m;
    }
    
    public void a(final boolean x) {
        this.x = x;
        l.b().a(this);
    }
    
    public void m() {
        super.m();
        l.c(this);
        if (this.c != null) {
            this.c.flush();
            this.c = null;
        }
    }
    
    public void a() {
        if (this.v()) {
            l.b().b(this.q(), this.r(), this.h, this.u(), this.j());
        }
    }
    
    public void e(final boolean v) {
        this.v = v;
    }
    
    public synchronized void a(final Image image) {
        this.y.height = 108;
        this.c = image.getScaledInstance(this.y.width, this.y.height - 20, 2);
        this.p();
    }
    
    public void b() {
        this.c = null;
        this.y.height = 18;
        this.p();
    }
    
    public boolean d() {
        return this.v;
    }
    
    public void d(final boolean h) {
        this.h = h;
    }
    
    public boolean e() {
        return this.h;
    }
    
    public void e(final int w) {
        this.w = w;
    }
    
    public void c(final boolean i) {
        this.i = i;
    }
    
    public int f() {
        return this.b;
    }
    
    public String t() {
        switch (this.b) {
            case 0: {
                return cz.t;
            }
            case 1: {
                return cz.r;
            }
            case 2: {
                return cz.s;
            }
            case 3: {
                return cz.q;
            }
            case 4: {
                return cz.p;
            }
            default: {
                return cz.t;
            }
        }
    }
    
    public void a(final int b) {
        if (this.u() == 3) {
            this.b = 4;
        }
        else {
            this.b = b;
        }
        switch (this.b) {
            case 0: {
                this.a(dj.ah);
                break;
            }
            case 1: {
                this.a(dj.af);
                Main.h(this.r());
                break;
            }
            case 2: {
                this.a(dj.ag);
                Main.h(this.r());
                break;
            }
            case 3: {
                this.a(dj.ae);
                Main.h(this.r());
                break;
            }
            case 4: {
                this.a(dj.ad);
                Main.h(this.r());
                break;
            }
            case -1: {
                this.a(dj.ai);
                break;
            }
            default: {
                this.a(dj.ah);
                break;
            }
        }
        this.p();
    }
    
    public int u() {
        return this.g;
    }
    
    public void d(final int g) {
        this.g = g;
        if (this.u() == 3) {
            this.a(4);
        }
        this.f(this.e);
    }
    
    public int w() {
        return this.e;
    }
    
    public void f(final int e) {
        this.e = e;
        switch (this.w()) {
            case 4: {
                this.e = 6;
                break;
            }
            case 5: {
                this.e = 6;
                break;
            }
            case 3: {
                this.e = 6;
                break;
            }
        }
        Label_0527: {
            switch (this.w()) {
                case 1: {
                    switch (this.u()) {
                        case 0: {
                            this.a = ImageRes.j;
                            break Label_0527;
                        }
                        case 1: {
                            this.a = ImageRes.v;
                            break Label_0527;
                        }
                        case 2: {
                            this.a = ImageRes.r;
                            break Label_0527;
                        }
                        case 3: {
                            this.a = ImageRes.z;
                            break Label_0527;
                        }
                        default: {
                            this.a = ImageRes.j;
                            break Label_0527;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (this.u()) {
                        case 0: {
                            this.a = ImageRes.k;
                            break Label_0527;
                        }
                        case 1: {
                            this.a = ImageRes.w;
                            break Label_0527;
                        }
                        case 2: {
                            this.a = ImageRes.s;
                            break Label_0527;
                        }
                        case 3: {
                            this.a = ImageRes.aa;
                            break Label_0527;
                        }
                        default: {
                            this.a = ImageRes.k;
                            break Label_0527;
                        }
                    }
                    break;
                }
                case 6: {
                    switch (this.u()) {
                        case 0: {
                            this.a = ImageRes.m;
                            break Label_0527;
                        }
                        case 1: {
                            this.a = ImageRes.y;
                            break Label_0527;
                        }
                        case 2: {
                            this.a = ImageRes.u;
                            break Label_0527;
                        }
                        case 3: {
                            this.a = ImageRes.ac;
                            break Label_0527;
                        }
                        default: {
                            this.a = ImageRes.m;
                            break Label_0527;
                        }
                    }
                    break;
                }
                case 0: {
                    switch (this.u()) {
                        case 0: {
                            this.a = ImageRes.l;
                            break Label_0527;
                        }
                        case 1: {
                            this.a = ImageRes.x;
                            break Label_0527;
                        }
                        case 2: {
                            this.a = ImageRes.t;
                            break Label_0527;
                        }
                        case 3: {
                            this.a = ImageRes.ab;
                            break Label_0527;
                        }
                        default: {
                            this.a = ImageRes.l;
                            break Label_0527;
                        }
                    }
                    break;
                }
                default: {
                    switch (this.u()) {
                        case 0: {
                            this.a = ImageRes.l;
                            break Label_0527;
                        }
                        case 1: {
                            this.a = ImageRes.x;
                            break Label_0527;
                        }
                        case 2: {
                            this.a = ImageRes.t;
                            break Label_0527;
                        }
                        case 3: {
                            this.a = ImageRes.ab;
                            break Label_0527;
                        }
                        default: {
                            this.a = ImageRes.l;
                            break Label_0527;
                        }
                    }
                    break;
                }
            }
        }
        if (p.b(this.r()) && this.u() != 3) {
            switch (this.w()) {
                case 0: {
                    this.a = ImageRes.p;
                    break;
                }
                case 1: {
                    this.a = ImageRes.n;
                    break;
                }
                case 2: {
                    this.a = ImageRes.o;
                    break;
                }
                case 6: {
                    this.a = ImageRes.q;
                    break;
                }
                default: {
                    this.a = ImageRes.p;
                    break;
                }
            }
        }
    }
    
    public boolean v() {
        return this.x;
    }
    
    public boolean x() {
        return this.d;
    }
    
    public void f(final boolean d) {
        this.d = d;
    }
    
    public cz(final String s, final int n, final int n2, final int n3, final int n4, final String j) {
        this.d = false;
        this.f = false;
        this.h = false;
        this.i = false;
        this.m = new b5();
        this.x = false;
        this.y = new Dimension(122, 18);
        this.a(s);
        this.a(n);
        this.d(n4);
        this.c(n2);
        this.f(n3);
        this.j = j;
        this.aa();
    }
    
    public boolean n() {
        return false;
    }
    
    public void b(final boolean f) {
        this.f = f;
        if (f) {
            this.b();
        }
    }
    
    public boolean y() {
        return this.f;
    }
    
    public void a(final String s) {
        super.a(s);
        this.b((this.r() + "_" + n.b().s()).toLowerCase());
    }
    
    public cz(final String j, final String s) {
        this.d = false;
        this.f = false;
        this.h = false;
        this.i = false;
        this.m = new b5();
        this.x = false;
        this.y = new Dimension(122, 18);
        this.j = j;
        this.a(s);
        this.aa();
    }
    
    private void aa() {
        l.b(this);
        this.b(n.b().s());
        if (p.b(this)) {
            this.b(p.a(this.r()));
        }
        else {
            this.b(dj.b);
        }
        this.a(dj.ah);
    }
    
    public String z() {
        return this.j;
    }
    
    public void b(final Graphics graphics, final Component component, final int n, final int n2) {
        graphics.setColor(this.i());
        graphics.fillRect(n + 21, n2, this.g().width - 21, this.g().height);
        if (this.i) {
            graphics.setFont(dj.ak);
        }
        else {
            graphics.setFont(dj.aj);
        }
        graphics.setColor(this.h());
        int n3 = 4;
        if (this.c != null) {
            n3 = 93;
            graphics.drawImage(this.c, n + 0, n2 + 0, component);
        }
        if (this.v() && !this.f) {
            if (this.e()) {
                graphics.drawImage(ImageRes.i, n + 0, n2 + n3, component);
            }
            else {
                graphics.drawImage(ImageRes.h, n + 0, n2 + n3, component);
            }
        }
        graphics.drawImage(this.a, n + 11, n2 + n3, component);
        if (this.r().length() > 20) {
            graphics.drawString(this.r().substring(0, 19) + "...", n + 29, n2 + n3 + 9);
        }
        else {
            graphics.drawString(this.r(), n + 29, n2 + n3 + 9);
        }
    }
    
    public Dimension g() {
        return this.y;
    }
    
    public PopupMenu a(final int n, final int n2, final Component component) {
        if (this.l == null) {
            this.l = new PopupMenu();
        }
        this.l.removeAll();
        final de de = new de(this, this.j, component);
        if (n.b().h() >= 2) {
            MenuItem menuItem;
            if (p.b(this)) {
                menuItem = new MenuItem("UnHighlight " + this.r());
                menuItem.setName("unhi");
            }
            else {
                menuItem = new MenuItem("Highlight " + this.r());
                menuItem.setName("hi");
            }
            menuItem.addActionListener(de);
            this.l.add(menuItem);
        }
        if (n.b().h() > 0) {
            final MenuItem menuItem2 = new MenuItem(Main.p.a("chat.popupmenu.pm") + " " + this.r());
            menuItem2.setName("hi");
            menuItem2.setName("pm");
            menuItem2.addActionListener(de);
            this.l.add(menuItem2);
        }
        if (this.f() == -1) {
            final MenuItem menuItem3 = new MenuItem(Main.p.a("chat.popupmenu.unignore") + " " + this.r());
            menuItem3.setName("unignore");
            menuItem3.addActionListener(de);
            this.l.add(menuItem3);
        }
        else if (this.f() < 1) {
            final MenuItem menuItem4 = new MenuItem(Main.p.a("chat.popupmenu.ignore") + " " + this.r());
            menuItem4.setName("ignore");
            menuItem4.addActionListener(de);
            this.l.add(menuItem4);
        }
        final MenuItem menuItem5 = new MenuItem(Main.p.a("chat.popupmenu.viewprofile") + " " + this.r());
        final MenuItem menuItem6 = new MenuItem(Main.p.a("chat.popupmenu.addbuddies") + " " + this.r());
        final MenuItem menuItem7 = new MenuItem(Main.p.a("chat.popupmenu.viewcam") + " " + this.r());
        menuItem5.setName("pro");
        menuItem6.setName("bud");
        menuItem7.setName("view");
        menuItem5.addActionListener(de);
        menuItem6.addActionListener(de);
        menuItem7.addActionListener(de);
        this.l.add(menuItem5);
        this.l.add(menuItem7);
        if (n.a.get(this.j) > this.f() && n.a.get(this.j) >= 1) {
            this.l.addSeparator();
            final MenuItem menuItem8 = new MenuItem(Main.p.a("cams.popupmenu.ban") + " " + this.r());
            final MenuItem menuItem9 = new MenuItem(Main.p.a("cams.popupmenu.kick") + " " + this.r());
            MenuItem menuItem10;
            if (this.x()) {
                menuItem10 = new MenuItem(Main.p.a("chat.popupmenu.unmute") + " " + this.r());
                menuItem10.setName("unmute");
            }
            else {
                menuItem10 = new MenuItem(Main.p.a("chat.popupmenu.mute") + " " + this.r());
                menuItem10.setName("mute");
            }
            menuItem8.setName("ban");
            menuItem9.setName("kick");
            menuItem8.addActionListener(de);
            menuItem9.addActionListener(de);
            menuItem10.addActionListener(de);
            this.l.add(menuItem8);
            this.l.add(menuItem9);
            this.l.add(menuItem10);
            if (this.u() > 0 && (n.a.get(this.j) >= 2 || n.b().o())) {
                MenuItem menuItem11;
                if (this.f() < 1) {
                    menuItem11 = new MenuItem(Main.p.a("chat.popupmenu.makeop") + " " + this.r());
                    final MenuItem menuItem12 = new MenuItem(Main.p.a("chat.popupmenu.maketempop") + " " + this.r());
                    menuItem12.setName("tempopp");
                    menuItem12.addActionListener(de);
                    this.l.add(menuItem12);
                }
                else {
                    menuItem11 = new MenuItem(Main.p.a("chat.popupmenu.removeop") + " " + this.r());
                }
                menuItem11.setName("opp");
                menuItem11.addActionListener(de);
                this.l.add(menuItem11);
            }
        }
        if (this.v()) {
            menuItem7.setEnabled(true);
        }
        else {
            menuItem7.setEnabled(false);
        }
        return this.l;
    }
    
    public void c(final int n, final int n2) {
        if (n < 12 && this.v()) {
            this.a();
        }
        else if (!this.d()) {
            try {
                if (n.b().h() > 0) {
                    (cz.k = p.a()).a(this);
                }
                else {
                    this.n = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100);
                    this.n.a.addActionListener(new am());
                    this.n.setVisible(true);
                }
            }
            catch (Exception ex) {
                b.a(ex, 3);
            }
        }
    }
    
    public String b(final int n, final int n2, final Component component) {
        return this.t();
    }
    
    static {
        cz.k = null;
        o = Toolkit.getDefaultToolkit();
        p = Main.p.a("user.awcstaff");
        q = Main.p.a("user.awcop");
        r = Main.p.a("user.roomop");
        s = Main.p.a("user.roomown");
        t = Main.p.a("user.participant");
        u = Main.p.a("user.ignored");
    }
}
