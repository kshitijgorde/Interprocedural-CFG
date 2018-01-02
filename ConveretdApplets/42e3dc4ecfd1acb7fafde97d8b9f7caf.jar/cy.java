import java.text.SimpleDateFormat;
import java.awt.event.KeyEvent;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Date;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.text.DateFormat;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import javax.swing.JTextField;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class cy extends JPanel implements cl, bf, k
{
    private cr a;
    private cn b;
    private JTextField c;
    private cz d;
    private p e;
    private j f;
    private boolean g;
    private cx h;
    private PopupMenu i;
    private MenuItem j;
    private static final DateFormat k;
    public PrintStream l;
    private MenuItem m;
    private MenuItem n;
    private boolean o;
    
    public String i() {
        return this.a.a();
    }
    
    public String b(final int n, final int n2) {
        return this.a.a(n, n2);
    }
    
    public void a(final PrintStream l) {
        this.l = l;
    }
    
    public String g() {
        return this.d.s();
    }
    
    public void a(final boolean o) {
        if (!o) {
            this.j.setLabel("Detach");
            this.j.setName("detach");
        }
        else {
            this.j.setLabel("Attach");
            this.j.setName("attach");
        }
        this.o = o;
    }
    
    public void a(final int n, final int n2) {
        if (this.i == null) {
            this.add(this.i = new PopupMenu());
            final MenuItem menuItem = new MenuItem("Ignore");
            final MenuItem menuItem2 = new MenuItem("Exit");
            final MenuItem menuItem3 = new MenuItem("Help");
            final MenuItem menuItem4 = new MenuItem("Log");
            final MenuItem menuItem5 = new MenuItem("Copy Line");
            final MenuItem menuItem6 = new MenuItem("Copy All");
            menuItem5.setName("cline");
            menuItem6.setName("call");
            this.j.setLabel("Detach");
            this.j.setName("detach");
            this.j.addActionListener(this.h);
            if (p.b()) {
                this.n.setLabel("Disable PM");
                this.n.setName("disable_pm");
            }
            else {
                this.n.setLabel("Enable PM");
                this.n.setName("enable_pm");
            }
            p.a(this.n);
            this.n.addActionListener(this.h);
            menuItem.setName("ignore");
            menuItem2.setName("exit");
            menuItem3.setName("help");
            menuItem4.setName("log");
            menuItem2.addActionListener(this.h);
            menuItem3.addActionListener(this.h);
            menuItem4.addActionListener(this.h);
            menuItem5.addActionListener(this.h);
            menuItem6.addActionListener(this.h);
            menuItem.addActionListener(this.h);
            this.i.add(menuItem2);
            this.i.add(menuItem);
            this.i.add(menuItem3);
            this.i.add(menuItem4);
            this.i.add(this.n);
            this.i.add(this.j);
            this.i.addSeparator();
            this.i.add(menuItem5);
            this.i.add(menuItem6);
        }
        this.h.a(n);
        this.h.b(n2);
        this.i.show(this, n, n2);
    }
    
    public void e() {
        this.c.transferFocus();
    }
    
    public void c() {
        this.d.e(false);
        p.b(this);
        p.b(this.n);
        p.b(this.m);
        if (this.l != null) {
            this.l.flush();
            this.l.close();
        }
        this.f.b(this.getName());
    }
    
    public int d() {
        return 1;
    }
    
    public boolean a() {
        return this.g;
    }
    
    public void b() {
        this.g = false;
    }
    
    private void a(final String s, final int n) {
        if (!this.g) {
            this.g = true;
            this.f.repaint();
        }
        this.a.a(s, n);
        if (this.l != null) {
            this.l.println(cy.k.format(new Date(System.currentTimeMillis())) + " : " + s);
        }
    }
    
    public void b(final String s) {
        this.a(this.d.r() + " : " + s, 0);
    }
    
    public cy(final cz d, final j f) {
        this.j = new MenuItem();
        this.m = new MenuItem();
        this.n = new MenuItem();
        this.setName(d.r());
        this.d = d;
        this.f = f;
        this.c = new JTextField();
        this.e = p.a();
        this.setLayout(new BorderLayout());
        this.b = new cn(ImageRes.g, ImageRes.f, 1);
        final BorderLayout layout = new BorderLayout();
        final JPanel panel = new JPanel();
        final JPanel panel2 = new JPanel();
        layout.setHgap(1);
        layout.setVgap(1);
        panel.setLayout(layout);
        panel.setBackground(dj.w);
        panel2.setLayout(new BorderLayout());
        panel2.add(this.c);
        panel2.add("East", this.b);
        panel2.setBackground(dj.w);
        (this.a = new cr(this)).a(50);
        panel.add(this.a);
        this.add("Center", panel);
        this.add("South", panel2);
        final co co = new co(this);
        this.b.addMouseListener(co);
        this.c.addKeyListener(co);
        this.setBackground(dj.w);
        this.h = new cx(this, f);
    }
    
    public String f() {
        return this.d.r();
    }
    
    public void c(final String s) {
        Main.b(s, "_blank");
    }
    
    public void j() {
        if (!this.c.getText().equals("")) {
            this.a(n.b().z() + " : " + this.c.getText(), 0);
            this.e.j("PRIVMSG " + this.d.r() + " :" + this.c.getText());
            this.c.setText("");
        }
    }
    
    public void a(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.j();
        }
        if (keyEvent.getKeyCode() > 111 && keyEvent.getKeyCode() < 121) {
            final StringBuffer sb = new StringBuffer(this.c.getText());
            switch (keyEvent.getKeyCode()) {
                case 112: {
                    sb.append(Main.c.get("F1").toString());
                    break;
                }
                case 113: {
                    sb.append(Main.c.get("F2").toString());
                    break;
                }
                case 114: {
                    sb.append(Main.c.get("F3").toString());
                    break;
                }
                case 115: {
                    sb.append(Main.c.get("F4").toString());
                    break;
                }
                case 116: {
                    sb.append(Main.c.get("F5").toString());
                    break;
                }
                case 117: {
                    sb.append(Main.c.get("F6").toString());
                    break;
                }
                case 118: {
                    sb.append(Main.c.get("F7").toString());
                    break;
                }
                case 119: {
                    sb.append(Main.c.get("F8").toString());
                    break;
                }
                case 120: {
                    sb.append(Main.c.get("F9").toString());
                    break;
                }
            }
            this.c.setText(sb.toString());
        }
    }
    
    static {
        k = new SimpleDateFormat("dd-MMM-yy 'at' hh:mm:ss a");
    }
}
