import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.KeyListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import au.com.rocketdog.project.awc.applet.Main;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Dimension;
import java.awt.PopupMenu;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class be extends Dialog implements bf, Runnable, bd, MouseListener, bc
{
    private n a;
    private int b;
    public bg c;
    private JPanel d;
    private b5 e;
    public static long f;
    private String g;
    public boolean h;
    private PopupMenu i;
    private String j;
    private int k;
    private static boolean l;
    private boolean m;
    public boolean n;
    public boolean o;
    private Thread p;
    private String q;
    private static final Dimension r;
    private static final Dimension s;
    private static final Dimension t;
    private static final Dimension u;
    private static final Dimension v;
    private static final Dimension w;
    private static final Dimension x;
    private int y;
    private t z;
    private l aa;
    private p ab;
    public int ac;
    public int ad;
    private int ae;
    private int af;
    public int ag;
    public boolean ah;
    private boolean ai;
    private boolean aj;
    private boolean ak;
    private boolean al;
    private int am;
    private int an;
    private static int ao;
    private int ap;
    
    private static synchronized int h() {
        final n b = n.b();
        if (!b.p()) {
            return 1;
        }
        ++be.ao;
        if (b.h() == 1 && be.ao == 3) {
            be.ao = 1;
        }
        if (b.h() == 2 || b.h() == 3) {
            if (be.ao == 7) {
                be.ao = 1;
            }
        }
        else if (be.ao == 3) {
            be.ao = 1;
        }
        return be.ao;
    }
    
    public b5 c() {
        return this.e;
    }
    
    public void a(final String s) {
        this.c.a(4, this.c.getSize().height - 10, dj.x, dj.al, s);
    }
    
    public int w() {
        return this.e.d;
    }
    
    public String r() {
        return this.getName();
    }
    
    public long a() {
        return be.f + this.an;
    }
    
    public String toString() {
        if (this.g == null) {
            this.g = (this.getName() + "_" + this.j()).toLowerCase();
        }
        return this.g;
    }
    
    public void b(final String s) {
        if (s.startsWith("+")) {
            this.m = true;
            this.show();
        }
        if (s.startsWith("-") && !this.a.o()) {
            this.m = false;
            this.ah = false;
            this.p.interrupt();
            this.p = null;
            this.c.a(ImageRes.b);
            this.c.a(4, this.c.getSize().height - 10, dj.x, dj.al, Main.p.a("cam.reqdenied"));
            this.al = true;
        }
        if (s.startsWith("!")) {
            this.m = false;
            this.ah = false;
            this.p.interrupt();
            this.p = null;
            this.al = true;
        }
    }
    
    public void c(final String s) {
        try {
            if (this.a.h() != 3) {
                if (this.ab == null) {
                    this.c.a(ImageRes.b);
                    this.a(Main.p.a("cam.connectcomms"));
                    this.ab = p.a();
                }
                if (this.j() == this.a.s()) {
                    this.ab.j("PRIVMSG ^" + this.getName() + " :" + s);
                }
            }
            this.c.c();
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    public int q() {
        return this.b;
    }
    
    public void a(final int b) {
        this.b = b;
    }
    
    public int j() {
        return this.am;
    }
    
    public be(final int n, final String s, final boolean b, final int n2, final int n3) {
        super(Main.h());
        this.e = new b5();
        this.ae = -1;
        this.af = -1;
        this.a(n, s, b, n2, n3);
    }
    
    public be(final int n, final String s, final boolean b, final int n2, final int n3, final boolean ai) {
        super(Main.h());
        this.e = new b5();
        this.ae = -1;
        this.af = -1;
        this.ai = ai;
        this.a(n, s, b, n2, n3);
    }
    
    private void a(final int n, final String s, final boolean h, final int k, final int am) {
        this.m = false;
        this.n = false;
        this.o = false;
        this.y = 2;
        this.aa = l.b();
        this.ae = -1;
        this.af = -1;
        this.ag = 0;
        this.ah = false;
        this.aj = true;
        this.ak = false;
        this.al = true;
        this.k = k;
        this.am = am;
        this.h = h;
        this.setTitle(s);
        this.setName(s);
        this.a(n);
        this.setResizable(false);
        this.setBackground(Color.white);
        this.z = t.a();
        this.a = n.b();
        if (this.a.p()) {
            be.f = n.c;
        }
        else {
            be.f = n.d;
        }
        this.j = this.z.a("cam.capturepath");
        if (this.j.endsWith(System.getProperty("file.separator"))) {
            this.j = this.j + this.getName() + System.getProperty("file.separator") + System.currentTimeMillis();
        }
        else {
            this.j = this.j + System.getProperty("file.separator") + this.getName() + System.getProperty("file.separator") + System.currentTimeMillis();
        }
        if (this.z.a("cam.closewindow").startsWith("t")) {
            be.l = true;
        }
        else {
            be.l = false;
        }
        final BorderLayout layout = new BorderLayout();
        layout.setHgap(1);
        this.setLayout(layout);
        (this.c = new bg(ImageRes.b, Integer.parseInt(this.z.a("cam.frame.size").substring(0, 3)), Integer.parseInt(this.z.a("cam.frame.size").substring(4, this.z.a("cam.frame.size").length())))).addMouseListener(this);
        this.add(this.c, "Center");
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setSize(Integer.parseInt(this.z.a("cam.frame.size").substring(0, 3)), Integer.parseInt(this.z.a("cam.frame.size").substring(4, this.z.a("cam.frame.size").length())));
        this.y = b(Integer.parseInt(this.z.a("cam.frame.size").substring(0, 3)));
        this.ac = screenSize.width / 2 - this.getSize().width / 2;
        this.ad = screenSize.height / 2 - this.getSize().height / 2;
        this.setLocation(this.ac, this.ad);
        this.addKeyListener(new b7(this));
        this.addWindowListener(new b8(this));
        this.addMouseListener(this);
        if (this.a.h() < 1) {
            this.aj = true;
            this.add(this.d = new b9("http://" + Main.b + "/awc/servlet/dispatch?CMD=cmd.xsl&XSL=xsl.billing.promo", "frameContent", ImageRes.a6), "South");
        }
        this.pack();
    }
    
    public void show() {
        super.show();
        synchronized (this) {
            (this.p = new Thread(this)).start();
        }
    }
    
    public void run() {
        if (this.ab == null || !this.ab.g()) {
            this.ab = p.a();
        }
        int n = 0;
        this.aa.a(this.b, this.j(), true);
        this.c.a(ImageRes.b);
        this.a(Main.p.a("cam.connectcomms"));
        p.a(this);
        this.a(Main.p.a("cam.auth"));
        if (this.a.h() != 3) {
            if (this.k > 0) {
                if (this.h) {
                    if (this.j() != this.a.s()) {
                        this.a(Main.p.a("cam.reqdenied"));
                        return;
                    }
                    if (this.k > 1) {
                        n = l.a(this.q(), true);
                        if (n == 0) {
                            this.ah = true;
                        }
                    }
                }
                else if (this.j() == this.a.s() && !this.a.o()) {
                    if (this.k > 1) {
                        n = l.a(this.q(), false);
                    }
                }
                else {
                    n = 0;
                }
                if (n == 2) {
                    this.a(Main.p.a("cam.reqdenied"));
                    return;
                }
            }
            this.m = true;
        }
        else {
            this.m = true;
            this.ah = true;
        }
        if (this.m) {
            this.a(Main.p.a("cam.connectcams"));
            if (this.q == null) {
                this.ak = true;
            }
            else {
                this.a(this.q);
            }
        }
        final m a = m.a(this.a, h(), this.ai);
        try {
            while (this.p != null && !this.p.isInterrupted() && this.m) {
                this.an = 0;
                final long currentTimeMillis = System.currentTimeMillis();
                this.e = a.a(this);
                long n2 = System.currentTimeMillis() - currentTimeMillis;
                if (this.e.b() == 0) {
                    this.a(this.e);
                }
                else if (this.e.b() == 1) {
                    n2 = 0L;
                }
                else if (this.e.b() == 2) {
                    this.e();
                }
                else if (this.e.b() == 3) {}
                if (n2 < this.a()) {
                    if (this.ai) {
                        System.out.println("waiting " + (this.a() - n2) + " " + this.getName() + " " + this.aa.a() + " " + a.getName());
                    }
                    Thread.sleep(this.a() - n2);
                }
                if (this.ai) {
                    System.out.println("waited " + n2 + " max sleep " + this.a() + " " + this.getName() + " " + this.aa.a() + " " + a.getName());
                }
            }
        }
        catch (InterruptedException ex) {
            if (this.ai) {
                ex.printStackTrace();
            }
        }
    }
    
    public int b() {
        return this.ap;
    }
    
    public boolean d() {
        return this.h;
    }
    
    public void a(final b5 e) {
        if (this.m) {
            this.e = e;
            this.ap = e.e;
            if (this.ak) {
                this.c.c();
                this.ak = false;
            }
            try {
                if (this.af != e.d() || this.ae != e.e()) {
                    this.af = e.d();
                    this.ae = e.e();
                    e.c();
                    this.h = e.l;
                    this.aa.a(this.getName(), this.b, e.d, e.l, e.e, true, true, this.j());
                    if ((this.al && !e.l) || (this.al && this.ah && this.a.h() != 3)) {
                        this.c("+" + this.a.x() + this.a.h() + this.a.j());
                    }
                    this.al = false;
                    if (this.a.h() != 3) {
                        if (e.l && !this.ah) {
                            this.f();
                            if (this.j() == this.a.s()) {
                                this.m = false;
                                this.i();
                            }
                        }
                        this.ag = e.f;
                    }
                }
                if (this.m) {
                    this.c.a(e.a(), 0, e.f());
                }
                if ((this.ag == 1 || this.a.h() == 3) && this.n) {
                    this.a(e.a(), e.f());
                }
                if ((this.ag == 1 || this.a.h() == 3) && this.o) {
                    this.a(e.a(), e.f());
                    this.o = false;
                }
            }
            catch (Exception ex) {
                b.a(ex, 3);
            }
        }
    }
    
    private void i() {
        final al al = new al(Main.h(), this.getName() + " " + Main.p.a("cams.window.goneprivate"), 100, 200);
        al.setLocation(this.getLocation().x + this.getSize().width / 2, this.getLocation().y + this.getSize().height / 2);
        al.a.addActionListener(new cb(this, 1));
        al.b.addActionListener(new cb(this, 0));
        al.setVisible(true);
    }
    
    public void e() {
        if (be.l) {
            this.g();
        }
        else {
            this.c.a(ImageRes.b);
            this.a(Main.p.a("cam.offline"));
            this.ak = true;
            this.af = -1;
            this.ae = -1;
            if (this.p != null) {
                this.an = 30000;
                if (this.ai) {
                    System.out.println("Waiting as " + this.getName() + " not in list " + this.a());
                }
            }
        }
    }
    
    public void f() {
        this.m = false;
        this.c.a(ImageRes.b);
        this.a(Main.p.a("cam.private"));
        this.p.interrupt();
        this.p = null;
    }
    
    public void g() {
        if (this.getSize().height < 100) {
            ++this.y;
            this.a(false);
        }
        else {
            try {
                this.m = false;
                this.aa.c(this.toString());
                this.aa.a(this.q(), this.j(), false);
                this.af = -1;
                this.ae = -1;
                if (this.k > 0) {
                    this.c("-");
                }
                this.m = false;
                if (this.p != null) {
                    this.p.interrupt();
                    this.p = null;
                }
                p.b(this);
            }
            catch (RuntimeException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        }
    }
    
    public void a(final boolean b) {
        if (b) {
            ++this.y;
            if (this.y >= 8) {
                this.y = 7;
            }
        }
        else {
            --this.y;
            if (this.y <= 0) {
                this.y = 1;
            }
        }
        switch (this.y) {
            case 1: {
                if (this.aj) {
                    this.setSize(be.r.width, be.r.height + 20);
                }
                else {
                    this.setSize(be.r);
                }
                this.c.setSize(be.r);
                break;
            }
            case 2: {
                if (this.aj) {
                    this.setSize(be.s.width, be.s.height + 20);
                }
                else {
                    this.setSize(be.s);
                }
                this.c.setSize(be.s);
                break;
            }
            case 3: {
                if (this.aj) {
                    this.setSize(be.t.width, be.t.height + 20);
                }
                else {
                    this.setSize(be.t);
                }
                this.c.setSize(be.t);
                break;
            }
            case 4: {
                if (this.aj) {
                    this.setSize(be.u.width, be.u.height + 20);
                }
                else {
                    this.setSize(be.u);
                }
                this.c.setSize(be.u);
                break;
            }
            case 5: {
                if (this.aj) {
                    this.setSize(be.v.width, be.v.height + 20);
                }
                else {
                    this.setSize(be.v);
                }
                this.c.setSize(be.v);
                break;
            }
            case 6: {
                if (this.aj) {
                    this.setSize(be.w.width, be.w.height + 20);
                }
                else {
                    this.setSize(be.w);
                }
                this.c.setSize(be.w);
                break;
            }
            case 7: {
                if (this.aj) {
                    this.setSize(be.x.width, be.x.height + 20);
                }
                else {
                    this.setSize(be.x);
                }
                this.c.setSize(be.x);
                break;
            }
            default: {
                this.y = 1;
                if (this.aj) {
                    this.setSize(be.r.width, be.r.height + 20);
                }
                else {
                    this.setSize(be.r);
                }
                this.c.setSize(be.r);
                break;
            }
        }
        this.a(this.c.a());
        this.pack();
    }
    
    private static int b(final int n) {
        switch (n) {
            case 160: {
                return 1;
            }
            case 240: {
                return 2;
            }
            case 320: {
                return 3;
            }
            case 400: {
                return 4;
            }
            case 480: {
                return 5;
            }
            case 560: {
                return 6;
            }
            case 640: {
                return 7;
            }
            default: {
                return 1;
            }
        }
    }
    
    private static byte[] b(final byte[] array, final int n) {
        final byte[] array2 = new byte[n - 10];
        System.arraycopy(array, 10, array2, 0, array2.length);
        for (int i = array2.length - 1, n2 = 0; i > n2; --i, ++n2) {
            final byte b = array2[i];
            array2[i] = array2[n2];
            array2[n2] = b;
        }
        return array2;
    }
    
    public void a(final byte[] array, final int n) {
        try {
            final String string = this.j + System.getProperty("file.separator") + System.currentTimeMillis();
            final File file = new File(this.j);
            if (!file.exists()) {
                file.mkdirs();
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(string + ".awc");
            fileOutputStream.write(b(array, n), 0, n - 10);
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (IOException ex) {
            b.a(ex, 3);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (0x4 == (mouseEvent.getModifiers() & 0x4)) {
            if (this.i == null) {
                this.add(this.i = new PopupMenu());
            }
            this.i.removeAll();
            final MenuItem menuItem = new MenuItem(Main.p.a("cams.popupmenu.viewprofile"));
            final MenuItem menuItem2 = new MenuItem(Main.p.a("cams.popupmenu.resizeup"));
            final MenuItem menuItem3 = new MenuItem(Main.p.a("cams.popupmenu.resizedown"));
            final MenuItem menuItem4 = new MenuItem(Main.p.a("cams.popupmenu.minimize"));
            final MenuItem menuItem5 = new MenuItem(Main.p.a("cams.popupmenu.snapshot"));
            MenuItem menuItem6;
            if (this.n) {
                menuItem6 = new MenuItem(Main.p.a("cams.popupmenu.captureoff"));
            }
            else {
                menuItem6 = new MenuItem(Main.p.a("cams.popupmenu.captureon"));
            }
            final MenuItem menuItem7 = new MenuItem(Main.p.a("cams.popupmenu.addtobuddies"));
            final MenuItem menuItem8 = new MenuItem(Main.p.a("cams.popupmenu.findinchat"));
            menuItem.setName("pro");
            menuItem2.setName("+");
            menuItem3.setName("-");
            menuItem5.setName("snap");
            menuItem6.setName("cap");
            menuItem7.setName("bud");
            menuItem4.setName("min");
            menuItem8.setName("chat");
            final cc cc = new cc(this);
            menuItem.addActionListener(cc);
            menuItem2.addActionListener(cc);
            menuItem3.addActionListener(cc);
            menuItem5.addActionListener(cc);
            menuItem6.addActionListener(cc);
            menuItem7.addActionListener(cc);
            menuItem8.addActionListener(cc);
            this.i.add(menuItem);
            this.i.add(menuItem2);
            this.i.add(menuItem3);
            this.i.add(menuItem5);
            this.i.add(menuItem6);
            this.i.add(menuItem7);
            this.i.add(menuItem8);
            MenuItem menuItem9;
            if (!Main.d((this.r() + "_" + this.j()).toLowerCase())) {
                menuItem9 = new MenuItem("Add to My Favourites");
                menuItem9.setName("addfav");
            }
            else {
                menuItem9 = new MenuItem("Remove from My Favourites");
                menuItem9.setName("remfav");
            }
            menuItem9.addActionListener(cc);
            this.i.add(menuItem9);
            if (this.a.o()) {
                this.i.addSeparator();
                final MenuItem menuItem10 = new MenuItem(Main.p.a("cams.popupmenu.kick"));
                final MenuItem menuItem11 = new MenuItem(Main.p.a("cams.popupmenu.ban"));
                final MenuItem menuItem12 = new MenuItem(Main.p.a("cams.popupmenu.changegender"));
                final MenuItem menuItem13 = new MenuItem("History");
                final MenuItem menuItem14 = new MenuItem(Main.p.a("cams.popupmenu.message"));
                menuItem10.setName("kick");
                menuItem11.setName("ban");
                menuItem12.setName("cg");
                menuItem13.setName("history");
                menuItem14.setName("message");
                menuItem10.addActionListener(cc);
                menuItem11.addActionListener(cc);
                menuItem12.addActionListener(cc);
                menuItem13.addActionListener(cc);
                menuItem14.addActionListener(cc);
                this.i.add(menuItem10);
                this.i.add(menuItem11);
                this.i.add(menuItem12);
                this.i.add(menuItem13);
                this.i.add(menuItem14);
            }
            this.i.show(this, mouseEvent.getX() + 8, mouseEvent.getY() + 8);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    static {
        be.f = 1000L;
        be.l = false;
        r = new Dimension(160, 120);
        s = new Dimension(240, 180);
        t = new Dimension(320, 240);
        u = new Dimension(400, 300);
        v = new Dimension(480, 360);
        w = new Dimension(560, 420);
        x = new Dimension(640, 480);
        be.ao = 0;
    }
}
