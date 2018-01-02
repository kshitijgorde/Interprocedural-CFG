import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.awt.MenuComponent;
import java.util.StringTokenizer;
import java.awt.event.ActionListener;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.MouseListener;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import java.io.PrintStream;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.util.Vector;
import java.text.DateFormat;
import java.util.Hashtable;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ck extends JPanel implements cj, k, cl
{
    private static final Hashtable a;
    private n b;
    private static final DateFormat c;
    private final Vector d;
    private t e;
    public aa f;
    private boolean g;
    private MenuItem h;
    private MenuItem i;
    private p j;
    private cn k;
    private cx l;
    private PopupMenu m;
    private PrintStream n;
    private bj o;
    private final JTextField p;
    private cr q;
    public cn r;
    private j s;
    private boolean t;
    
    public String i() {
        return this.q.a();
    }
    
    public String b(final int n, final int n2) {
        return this.q.a(n, n2);
    }
    
    public void a(final av av, final Color color) {
        final av c = this.f.c(av.s());
        if (c != null) {
            c.b(color);
            c.p();
        }
    }
    
    public void a(final av av) {
        final av c = this.f.c(av.s());
        if (c != null) {
            c.b(dj.b);
            c.p();
        }
    }
    
    public ck(final bj o, final j s) {
        this.b = n.b();
        this.d = new Vector(0);
        this.g = false;
        this.h = new MenuItem();
        this.i = new MenuItem();
        this.j = null;
        this.p = new JTextField(45);
        this.t = false;
        this.s = s;
        this.o = o;
        (this.f = new aa(new cm())).setName(o.r());
        this.f.a(132, 200);
        this.f.setBackground(dj.b);
        final BorderLayout layout = new BorderLayout();
        final BorderLayout borderLayout = new BorderLayout();
        this.e = t.a();
        this.setLayout(layout);
        this.k = new cn(ImageRes.g, ImageRes.f, 1);
        final co co = new co(this);
        this.k.addMouseListener(co);
        (this.r = new cn(ImageRes.aj, ImageRes.aj, 1)).addMouseListener(new cp(this));
        layout.setHgap(1);
        layout.setVgap(1);
        this.setBackground(dj.w);
        final Container container = new Container();
        final FlowLayout layout2 = new FlowLayout(0);
        container.setBackground(dj.w);
        container.setLayout(layout2);
        container.add(this.p);
        container.add(this.k);
        container.add(this.r);
        this.add(container, "South");
        (this.q = new cr(this)).a(150);
        this.q.setBackground(ck.a.get(this.e.a("chat.font.backgroundcolor")));
        this.q.setFont(new Font(this.e.a("chat.font.name"), 0, Integer.parseInt(this.e.a("chat.font.size"))));
        this.q.setForeground(ck.a.get(this.e.a("chat.font.color")));
        this.add(this.f, "East");
        this.add(this.q, "Center");
        this.p.addKeyListener(co);
        borderLayout.setVgap(1);
        borderLayout.setHgap(1);
        this.p.setBackground(dj.ac);
        this.setName(o.r());
        this.j = p.a();
        p.a(this);
        o.a(true);
        o.p();
        this.l = new cx(this, s);
    }
    
    public void b() {
        this.t = false;
    }
    
    public void c() {
        try {
            this.r.mousePressed(null);
            this.o.a(false);
            this.o.p();
            p.b(this);
            this.j.h(this.g());
            this.j.c();
            p.b(this.i);
            final av[] b = this.f.b();
            for (int i = 0; i < b.length; ++i) {
                l.c((bb)b[i]);
            }
            h.c(this.g()).a(false);
            if (this.n != null) {
                this.n.flush();
                this.n.close();
            }
        }
        catch (Exception ex) {
            b.a(ex, 2);
        }
        this.s.b(this.getName());
    }
    
    public void e() {
        this.p.requestFocus();
    }
    
    public String g() {
        return this.o.s();
    }
    
    public int d() {
        return 0;
    }
    
    public void c(final String s) {
        Main.b(s, "_blank");
    }
    
    public void a(final String s) {
        this.d.removeElement(s);
        this.d.addElement(s);
    }
    
    public void f() {
        final bl bl = new bl(Main.h(), Main.p.a("chat.message.youarebanned"));
        bl.a.addActionListener(new c7(this));
        bl.setVisible(true);
    }
    
    public void a(final String s, final String s2, final String s3, final boolean b) {
        cz cz = (cz)this.f.c((s + "_" + this.b.s()).toLowerCase());
        if (cz == null) {
            cz = new cz(this.g(), s);
        }
        if (s3.indexOf("+") >= 0) {
            cz.f(true);
        }
        if (s3.indexOf("*") >= 0) {
            cz.a(3);
        }
        else if (s3.indexOf("@") >= 0) {
            if (this.o.w() < 2) {
                cz.a(3);
            }
            else if (this.g().equalsIgnoreCase(h.e(cz.r()))) {
                cz.a(2);
            }
            else {
                cz.a(1);
            }
        }
        if (Main.b(s)) {
            cz.a(-1);
        }
        if (p.b(cz.r())) {
            cz.a(3);
        }
        if (!b) {
            this.a(s + " " + Main.p.a("chat.message.hasjoined"), 0);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, "^");
        try {
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final String nextToken = stringTokenizer.nextToken();
            final int int2 = Integer.parseInt(nextToken.substring(0, 1));
            final int int3 = Integer.parseInt(nextToken.substring(1, 2));
            cz.c(int1);
            cz.d(int2);
            final ba a = l.b().a(cz.s());
            if (a != null) {
                cz.f(a.w());
                cz.d(a.f());
                cz.c(a.e());
                cz.a(true);
            }
            else {
                cz.f(int3);
            }
            if (cz.r().equalsIgnoreCase(this.b.z())) {
                n.a.put(this.g(), new Integer(cz.f()));
                this.o.a(cz);
                if (this.m != null) {
                    this.remove(this.m);
                    this.i.removeActionListener(this.l);
                    this.h.removeActionListener(this.l);
                    this.m = null;
                }
            }
            if (!this.f.a(cz)) {
                this.f.a();
            }
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    public void a(final String s, final String s2, final String s3) {
        if (s2.equalsIgnoreCase(this.b.z())) {
            final bl bl = new bl(Main.h(), Main.p.a("chat.message.kicked1") + " " + s + Main.p.a("chat.message.kicked2") + " " + s3, Main.p.a("chat.message.kicked3") + ".");
            bl.a.addActionListener(new c7(this));
            bl.setVisible(true);
        }
        else {
            this.a(s2 + " " + Main.p.a("chat.message.kickedfor") + " " + s3, 0);
            this.f.b(s2 + "_" + this.b.s());
        }
    }
    
    public void a(final String s, final String s2) {
        if (!Main.b(s)) {
            final cz cz = (cz)this.f.c(s + "_" + this.b.s());
            if (cz == null) {
                this.a(s + " : " + s2, 0);
            }
            else {
                this.a(s + " : " + s2, cz.f());
            }
        }
    }
    
    public void b(final String s, String substring) {
        final String substring2 = substring.substring(0, 2);
        substring = substring.substring(3, substring.length());
        final cz cz = (cz)this.f.c((substring + "_" + this.b.s()).toLowerCase());
        if (cz == null) {
            return;
        }
        if (substring2.equals("-o")) {
            cz.a(0);
        }
        if (substring2.equals("+o")) {
            if (this.g().equalsIgnoreCase(h.e(substring))) {
                cz.a(2);
            }
            else if (p.b(cz.r())) {
                cz.a(3);
            }
            else {
                cz.a(1);
            }
        }
        if (substring2.equals("+v")) {
            cz.f(true);
        }
        if (substring2.equals("-v")) {
            cz.f(false);
        }
        if (substring.equalsIgnoreCase(this.b.z())) {
            this.o.a(cz);
            n.a.put(this.g(), new Integer(cz.f()));
            if (this.m != null) {
                this.remove(this.m);
                this.m = null;
            }
        }
    }
    
    public void a(String substring, final String s, final boolean b) {
        if (substring.startsWith("@")) {
            substring = substring.substring(1);
        }
        this.f.b((substring + "_" + this.b.s()).toLowerCase());
        if (!b) {
            this.a(substring + " " + Main.p.a("chat.message.hasleft") + " " + s, 0);
        }
    }
    
    public void a(final boolean g) {
        if (!(this.g = g)) {
            this.h.setLabel("Detach");
            this.h.setName("detach");
        }
        else {
            this.h.setLabel("Attach");
            this.h.setName("attach");
        }
    }
    
    public boolean a() {
        return this.t;
    }
    
    public void a(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 127 && this.p.getSelectedText().length() == 0 && this.p.getText().length() > 0) {
            final int caretPosition = this.p.getCaretPosition();
            this.p.setText(this.p.getText().substring(0, this.p.getCaretPosition() - 1) + this.p.getText().substring(this.p.getCaretPosition(), this.p.getText().length()));
            this.p.setCaretPosition(caretPosition - 1);
            return;
        }
        if (keyEvent.getKeyCode() == 10) {
            this.j();
        }
        else if (keyEvent.getKeyCode() == 17 || keyEvent.getKeyCode() == 9) {
            int n = 1 + this.p.getText().lastIndexOf(" ");
            if (n < 0) {
                n = 0;
            }
            String s = this.p.getText().substring(n, this.p.getText().length());
            if (s.length() > 0) {
                final av[] b = this.f.b();
                for (int i = 0; i < b.length; ++i) {
                    s = s.toLowerCase();
                    final av av = b[i];
                    if (av.r().toLowerCase().startsWith(s)) {
                        s = av.r();
                        break;
                    }
                }
                if (s != null) {
                    final StringBuffer sb = new StringBuffer(this.p.getText().substring(0, n));
                    sb.append(s);
                    this.p.setText(sb.toString());
                    this.p.setCaretPosition(this.p.getText().length());
                }
            }
        }
        else if (keyEvent.getKeyCode() > 111 && keyEvent.getKeyCode() < 121) {
            final StringBuffer sb2 = new StringBuffer(this.p.getText());
            switch (keyEvent.getKeyCode()) {
                case 112: {
                    sb2.append(Main.c.get("F1"));
                    break;
                }
                case 113: {
                    sb2.append(Main.c.get("F2"));
                    break;
                }
                case 114: {
                    sb2.append(Main.c.get("F3"));
                    break;
                }
                case 115: {
                    sb2.append(Main.c.get("F4"));
                    break;
                }
                case 116: {
                    sb2.append(Main.c.get("F5"));
                    break;
                }
                case 117: {
                    sb2.append(Main.c.get("F6"));
                    break;
                }
                case 118: {
                    sb2.append(Main.c.get("F7"));
                    break;
                }
                case 119: {
                    sb2.append(Main.c.get("F8"));
                    break;
                }
                case 120: {
                    sb2.append(Main.c.get("F9"));
                    break;
                }
            }
            if (!sb2.toString().equalsIgnoreCase("null")) {
                this.p.setText(sb2.toString());
                this.p.setCaretPosition(this.p.getText().length());
            }
        }
    }
    
    public void j() {
        if (!this.p.getText().equals("")) {
            final cz cz = (cz)this.f.c(this.b.z() + "_" + this.b.s());
            if (cz != null) {
                this.a(this.b.z() + " : " + this.p.getText(), cz.f());
            }
            else {
                this.a(this.b.z() + " : " + this.p.getText(), 0);
            }
            this.j.f(this.g(), this.p.getText());
            this.p.setText("");
        }
    }
    
    public void a(final PrintStream n) {
        this.n = n;
    }
    
    public void h() {
        new c8(Main.h(), this.g(), (Vector)this.d.clone(), this.getName()).setVisible(true);
    }
    
    public void a(final int n, final int n2) {
        if (this.m == null) {
            this.add(this.m = new PopupMenu());
            final MenuItem menuItem = new MenuItem("Exit");
            final MenuItem menuItem2 = new MenuItem("Help");
            final MenuItem menuItem3 = new MenuItem("Log");
            final MenuItem menuItem4 = new MenuItem("Copy Line");
            final MenuItem menuItem5 = new MenuItem("Copy All");
            menuItem.setName("exit");
            menuItem2.setName("help");
            menuItem3.setName("log");
            menuItem4.setName("cline");
            menuItem5.setName("call");
            if (!this.g) {
                this.h.setLabel("Detach");
                this.h.setName("detach");
            }
            else {
                this.h.setLabel("Attach");
                this.h.setName("attach");
            }
            this.h.addActionListener(this.l);
            if (p.b()) {
                this.i.setLabel("Disable PM");
                this.i.setName("disable_pm");
            }
            else {
                this.i.setLabel("Enable PM");
                this.i.setName("enable_pm");
            }
            p.a(this.i);
            this.i.addActionListener(this.l);
            menuItem.addActionListener(this.l);
            menuItem2.addActionListener(this.l);
            menuItem3.addActionListener(this.l);
            menuItem4.addActionListener(this.l);
            menuItem5.addActionListener(this.l);
            this.m.add(menuItem);
            this.m.add(menuItem2);
            this.m.add(menuItem3);
            this.m.add(this.i);
            this.m.add(this.h);
            if (this.o.d() != null) {
                if (h.e(this.b.z()).equalsIgnoreCase(this.g()) || this.o.d().f() >= 3) {
                    this.m.addSeparator();
                    final MenuItem menuItem6 = new MenuItem("Change Topic");
                    menuItem6.setName("topic");
                    menuItem6.addActionListener(this.l);
                    this.m.add(menuItem6);
                    final MenuItem menuItem7 = new MenuItem("Close Room");
                    menuItem7.setName("close");
                    menuItem7.addActionListener(this.l);
                    this.m.add(menuItem7);
                }
                if (this.o.d().f() >= 1) {
                    final MenuItem menuItem8 = new MenuItem("List Banned Users");
                    menuItem8.setName("ban");
                    menuItem8.addActionListener(this.l);
                    this.m.add(menuItem8);
                }
                if (this.o.d().f() >= 3) {
                    final MenuItem menuItem9 = new MenuItem("Message Ops");
                    final MenuItem menuItem10 = new MenuItem("Summon User");
                    menuItem9.setName("messops");
                    menuItem9.addActionListener(this.l);
                    menuItem10.setName("summon");
                    menuItem10.addActionListener(this.l);
                    this.m.add(menuItem9);
                    this.m.add(menuItem10);
                }
            }
            this.m.addSeparator();
            this.m.add(menuItem4);
            this.m.add(menuItem5);
        }
        this.l.a(n);
        this.l.b(n2);
        this.m.show(this, n, n2);
    }
    
    private void a(final String s, final int n) {
        if (!this.t) {
            this.t = true;
            this.s.repaint();
        }
        this.q.a(s, n);
        if (this.n != null) {
            this.n.println(ck.c.format(new Date(System.currentTimeMillis())) + " : " + s);
        }
    }
    
    static {
        a = new Hashtable();
        c = new SimpleDateFormat("dd-MMM-yy 'at' hh:mm:ss a");
        ck.a.put(Main.p.a("settings.chat.color.black"), Color.black);
        ck.a.put(Main.p.a("settings.chat.color.blue"), Color.blue);
        ck.a.put(Main.p.a("settings.chat.color.cyan"), Color.cyan);
        ck.a.put(Main.p.a("settings.chat.color.darkgray"), Color.darkGray);
        ck.a.put(Main.p.a("settings.chat.color.gray"), Color.gray);
        ck.a.put(Main.p.a("settings.chat.color.green"), Color.green);
        ck.a.put(Main.p.a("settings.chat.color.lightgray"), Color.lightGray);
        ck.a.put(Main.p.a("settings.chat.color.magenta"), Color.magenta);
        ck.a.put(Main.p.a("settings.chat.color.orange"), Color.orange);
        ck.a.put(Main.p.a("settings.chat.color.pink"), Color.pink);
        ck.a.put(Main.p.a("settings.chat.color.red"), Color.red);
        ck.a.put(Main.p.a("settings.chat.color.white"), Color.white);
        ck.a.put(Main.p.a("settings.chat.color.yellow"), Color.yellow);
    }
}
