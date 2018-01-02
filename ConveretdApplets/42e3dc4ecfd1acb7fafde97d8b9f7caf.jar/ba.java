import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.net.URL;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class ba extends av implements bb, bc, bd, Runnable
{
    private Image a;
    private String b;
    private boolean c;
    private int d;
    private int e;
    private String f;
    private int g;
    private boolean h;
    private boolean i;
    private boolean j;
    private b6 k;
    private PopupMenu l;
    private final Dimension m;
    private b5 n;
    private Image o;
    private StringBuffer p;
    private static final Toolkit q;
    
    public void run() {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL("http://" + Main.b + "/awc/servlet/dispatch?CMD=cmd.applet.viewuser&id=" + this.q()).openStream())));
            this.p = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.p.append(line);
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            b.a(ex, 3);
            this.p = null;
        }
    }
    
    private String y() {
        if (this.p == null) {
            (this.p = new StringBuffer()).append("<html><body bgcolor=\"#CDDBE8\" text=\"#1F5285\"><font face=\"verdana\" size=\"2\"> ");
            this.p.append(this.r());
            this.p.append("<br> Country:");
            this.p.append(this.t());
            this.p.append("</font></body></html>");
        }
        return this.p.toString();
    }
    
    public void a(final b5 b5) {
        if (b5 != null && b5.b() == 0) {
            this.a(ba.q.createImage(b5.a(), 0, b5.f()));
        }
        else {
            this.a();
        }
    }
    
    public void a() {
        this.a(ImageRes.ak);
    }
    
    public boolean b() {
        return this.o != null;
    }
    
    public b5 c() {
        if (this.n == null) {
            this.n = new b5();
        }
        return this.n;
    }
    
    public void a(final Image o) {
        this.m.height = 140;
        if (o.getWidth(null) == 160 && o.getHeight(null) == 120) {
            this.o = o;
        }
        else {
            this.o = o.getScaledInstance(160, 120, 2);
        }
    }
    
    public void d() {
        this.o.flush();
        this.o = null;
        this.m.height = 18;
    }
    
    public static String a(final int n) {
        switch (n) {
            case 0: {
                return Main.p.a("gender.unknown");
            }
            case 1: {
                return Main.p.a("gender.male");
            }
            case 2: {
                return Main.p.a("gender.female");
            }
            case 6: {
                return Main.p.a("gender.group");
            }
            default: {
                return Main.p.a("gender.other");
            }
        }
    }
    
    public void a(final boolean j) {
        this.j = j;
    }
    
    public void c(final boolean i) {
        this.i = i;
    }
    
    public boolean e() {
        return this.i;
    }
    
    public void d(final boolean h) {
        this.h = h;
    }
    
    public boolean f() {
        return this.h;
    }
    
    public String t() {
        return this.b;
    }
    
    public void c(final String b) {
        if (b != null) {
            this.b = b;
        }
    }
    
    public int u() {
        return this.e;
    }
    
    public void d(final int e) {
        this.e = e;
    }
    
    public int v() {
        return this.g;
    }
    
    public void e(final int g) {
        this.g = g;
    }
    
    private void g(final int n) {
        if (this.u() > 0) {
            if (n == 0) {
                this.h = false;
            }
            else {
                this.h = true;
            }
        }
    }
    
    public boolean equals(final Object o) {
        return o.toString().equals(this.toString());
    }
    
    public int w() {
        return this.d;
    }
    
    public void f(final int d) {
        this.d = d;
        switch (this.w()) {
            case 4: {
                this.d = 6;
                break;
            }
            case 5: {
                this.d = 6;
                break;
            }
            case 3: {
                this.d = 6;
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
    
    private void z() {
        this.b((this.r() + "_" + this.j()).toLowerCase());
        this.b(dj.b);
        if (p.b(this.r())) {
            this.c = true;
        }
    }
    
    public ba(final String f) {
        this.c = false;
        this.h = false;
        this.i = false;
        this.m = new Dimension(178, 18);
        final StringTokenizer stringTokenizer = new StringTokenizer(f, ",");
        this.c(Integer.parseInt(stringTokenizer.nextToken()));
        if (stringTokenizer.hasMoreTokens()) {
            this.a(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.c(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.d(Integer.parseInt(stringTokenizer.nextToken()));
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.f(Integer.parseInt(stringTokenizer.nextToken()));
        }
        if (stringTokenizer.hasMoreTokens()) {
            stringTokenizer.nextToken();
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.g(Integer.parseInt(stringTokenizer.nextToken()));
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.e(Integer.parseInt(stringTokenizer.nextToken()));
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.b(Integer.parseInt(stringTokenizer.nextToken()));
        }
        this.f = f;
        this.z();
    }
    
    public String toString() {
        return this.f;
    }
    
    public void b(final Graphics graphics, final Component component, final int n, final int n2) {
        graphics.fillRect(n, n2, this.g().width, this.g().height);
        if (this.e()) {
            graphics.setFont(dj.ak);
        }
        else {
            graphics.setFont(dj.aj);
        }
        int n3 = 0;
        if (this.o != null) {
            n3 = 122;
            graphics.drawImage(this.o, n + 9, n2 + 1, component);
        }
        graphics.drawImage(this.a, n + 2, n2 + 2 + n3, component);
        if (this.f()) {
            final Color color = graphics.getColor();
            graphics.drawLine(n + 3, n2 + 4 + n3, n + 13, n2 + 14 + n3);
            graphics.setColor(Color.red);
            graphics.drawLine(n + 4, n2 + 4 + n3, n + 14, n2 + 14 + n3);
            graphics.drawLine(n + 5, n2 + 4 + n3, n + 15, n2 + 14 + n3);
            graphics.setColor(color);
            graphics.drawLine(n + 6, n2 + 4 + n3, n + 16, n2 + 14 + n3);
        }
        graphics.setColor(dj.x);
        if (this.e == 3) {
            graphics.setColor(dj.ad);
        }
        else if (this.c) {
            graphics.setColor(dj.ae);
        }
        if (this.r().length() > 34) {
            graphics.drawString(this.r().substring(0, 31) + "...", n + 20, n2 + 14 + n3);
        }
        else {
            graphics.drawString(this.r(), n + 20, n2 + 14 + n3);
        }
    }
    
    public PopupMenu a(final int n, final int n2, final Component component) {
        final n b = n.b();
        if (this.l != null) {
            this.l.removeAll();
        }
        if (this.l == null) {
            this.l = new PopupMenu();
            this.k = new b6(this);
        }
        final MenuItem menuItem = new MenuItem(Main.p.a("cams.popupmenu.viewprofile") + " " + this.r());
        final MenuItem menuItem2 = new MenuItem(Main.p.a("cams.popupmenu.viewcam") + " " + this.r());
        final MenuItem menuItem3 = new MenuItem(Main.p.a("cams.popupmenu.addtobuddies") + " " + this.r());
        menuItem.setName("pro");
        menuItem2.setName("view");
        menuItem3.setName("bud");
        menuItem.addActionListener(this.k);
        menuItem2.addActionListener(this.k);
        menuItem3.addActionListener(this.k);
        this.l.add(menuItem);
        this.l.add(menuItem2);
        this.l.add(menuItem3);
        if (b.s() == this.j()) {
            final MenuItem menuItem4 = new MenuItem(Main.p.a("cams.popupmenu.findinchat") + " " + this.r());
            menuItem4.setName("chat");
            menuItem4.addActionListener(this.k);
            this.l.add(menuItem4);
        }
        MenuItem menuItem5;
        if (!Main.d(this.s())) {
            menuItem5 = new MenuItem(Main.p.a("cams.popupmenu.addfavourites") + " " + this.r());
            menuItem5.setName("addfav");
        }
        else {
            menuItem5 = new MenuItem(Main.p.a("cams.popupmenu.removefavourites") + " " + this.r());
            menuItem5.setName("remfav");
        }
        menuItem5.addActionListener(this.k);
        this.l.add(menuItem5);
        if (b.o() && b.s() == this.j()) {
            this.l.addSeparator();
            final MenuItem menuItem6 = new MenuItem(Main.p.a("cams.popupmenu.kick") + " " + this.r());
            final MenuItem menuItem7 = new MenuItem(Main.p.a("cams.popupmenu.ban") + " " + this.r());
            final MenuItem menuItem8 = new MenuItem(Main.p.a("cams.popupmenu.changegender") + " " + this.r());
            final MenuItem menuItem9 = new MenuItem("History " + this.r());
            final MenuItem menuItem10 = new MenuItem(Main.p.a("cams.popupmenu.message") + " " + this.r());
            menuItem6.setName("kick");
            menuItem7.setName("ban");
            menuItem8.setName("cg");
            menuItem9.setName("history");
            menuItem10.setName("message");
            menuItem6.addActionListener(this.k);
            menuItem7.addActionListener(this.k);
            menuItem8.addActionListener(this.k);
            menuItem9.addActionListener(this.k);
            menuItem10.addActionListener(this.k);
            this.l.add(menuItem6);
            this.l.add(menuItem7);
            this.l.add(menuItem8);
            this.l.add(menuItem9);
            this.l.add(menuItem10);
        }
        if (b.o()) {
            final MenuItem menuItem11 = new MenuItem("View in debug mode");
            menuItem11.addActionListener(this.k);
            menuItem11.setName("debug");
            this.l.add(menuItem11);
        }
        return this.l;
    }
    
    public void c(final int n, final int n2) {
        this.x();
    }
    
    public void x() {
        l.b().b(this.q(), this.r(), this.h, this.u(), this.j());
    }
    
    public Dimension g() {
        return this.m;
    }
    
    public String b(final int n, final int n2, final Component component) {
        return this.y();
    }
    
    static {
        q = Toolkit.getDefaultToolkit();
    }
}
