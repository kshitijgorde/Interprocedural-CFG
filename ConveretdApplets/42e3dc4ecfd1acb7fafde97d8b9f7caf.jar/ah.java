import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;
import java.awt.Component;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.event.MouseListener;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.KeyListener;
import java.awt.Rectangle;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class ah extends JFrame
{
    private JLabel a;
    private JLabel b;
    private JLabel c;
    private JLabel d;
    private JLabel e;
    private JLabel f;
    private JLabel g;
    private JComboBox h;
    private JTextField i;
    private JTextField j;
    private JTextField k;
    private JCheckBox l;
    private JButton m;
    private al n;
    private bl o;
    private p p;
    private JTextField q;
    private JPanel r;
    private BorderLayout s;
    private JTextField t;
    
    public ah() {
        this.a = new JLabel();
        this.b = new JLabel();
        this.c = new JLabel();
        this.d = new JLabel();
        this.e = new JLabel();
        this.f = new JLabel();
        this.g = new JLabel();
        this.h = new JComboBox();
        this.i = new JTextField();
        this.j = new JTextField();
        this.k = new JTextField();
        this.l = new JCheckBox();
        this.n = null;
        this.o = null;
        this.q = new JTextField();
        this.r = new JPanel();
        this.s = new BorderLayout();
        this.t = new JTextField();
        this.getContentPane().setLayout(this.s);
        this.addWindowListener(new ai(this));
        this.a.setBounds(new Rectangle(9, 24, 110, 21));
        this.b.setBounds(new Rectangle(9, 47, 110, 21));
        this.c.setBounds(new Rectangle(9, 93, 110, 21));
        this.d.setBounds(new Rectangle(9, 116, 110, 21));
        this.f.setBounds(new Rectangle(9, 139, 110, 21));
        this.e.setBounds(new Rectangle(9, 162, 110, 21));
        this.i.setBounds(new Rectangle(122, 24, 270, 21));
        this.j.setBounds(new Rectangle(122, 47, 270, 21));
        this.l.setBounds(new Rectangle(122, 93, 269, 21));
        this.h.setBounds(new Rectangle(122, 116, 270, 21));
        this.q.setBounds(new Rectangle(122, 139, 270, 21));
        this.k.setBounds(new Rectangle(122, 162, 270, 21));
        this.k.addKeyListener(new aj(this));
        this.h.addItem(Main.p.a("chat.makeroom.allaccess"));
        this.h.addItem(Main.p.a("chat.makeroom.goldsilveronly"));
        this.h.addItem(Main.p.a("chat.makeroom.goldonly"));
        (this.m = new JButton()).setText(Main.p.a("chat.makeroom.maketip"));
        this.m.setToolTipText(Main.p.a("chat.makeroom.maketip"));
        this.m.setBounds(new Rectangle(200, 250, 120, 27));
        this.m.addMouseListener(new ak(this));
        this.a.setText(Main.p.a("chat.makeroom.name"));
        this.q.setText(Main.p.a("chat.makeroom.unlimited"));
        this.b.setText(Main.p.a("chat.makeroom.topic"));
        this.c.setText(Main.p.a("chat.makeroom.broadcastonly"));
        this.d.setText(Main.p.a("chat.makeroom.restrictto"));
        this.e.setText(Main.p.a("chat.makeroom.privatepassword"));
        this.f.setText(Main.p.a("chat.makeroom.maxusers"));
        this.setBackground(dj.w);
        this.r.setLayout(null);
        this.setIconImage(ImageRes.a4);
        this.setTitle(Main.p.a("chat.makeroom.heading"));
        this.g.setText(Main.p.a("chat.makeroom.greetmessage"));
        this.g.setBounds(new Rectangle(9, 190, 110, 21));
        this.t.setBounds(new Rectangle(122, 190, 270, 22));
        this.getContentPane().add(this.r, "Center");
        this.r.add(this.i, null);
        this.r.add(this.j, null);
        this.r.add(this.l, null);
        this.r.add(this.h, null);
        this.r.add(this.q, null);
        this.r.add(this.k, null);
        this.r.add(this.a, null);
        this.r.add(this.b, null);
        this.r.add(this.c, null);
        this.r.add(this.d, null);
        this.r.add(this.e, null);
        this.r.add(this.f, null);
        this.r.add(this.g, null);
        this.r.add(this.m, null);
        this.r.add(this.t, null);
    }
    
    private String d() {
        String s;
        for (s = a(a(a(a(a(a(a(a(a(a(a(a(a(a(this.i.getText(), "#", "").trim(), "  ", " "), "@", ""), ":", ""), "'", ""), ",", ""), "^", ""), "\"", ""), " ", "_"), "{", ""), "}", ""), "[", ""), "]", ""), "/", ""); s.endsWith("_"); s = s.substring(0, s.length() - 1)) {}
        String s2;
        if (this.l.isSelected()) {
            s2 = "#" + s + "^2^0^1^" + this.h.getSelectedIndex();
        }
        else {
            s2 = "#" + s + "^2^0^0^" + this.h.getSelectedIndex();
        }
        String s3;
        if (this.k.getText().length() > 0) {
            s3 = s2 + "^1";
        }
        else {
            s3 = s2 + "^0";
        }
        return s3;
    }
    
    public void a() {
        final String e = h.e(n.b().z());
        if (e.length() > 0) {
            this.n = new al(this, Main.p.a("chat.makeroom.overwrite") + " (" + e + ")", 400, 100);
            this.n.a.addActionListener(new bk(this));
            this.n.setVisible(true);
        }
        else {
            this.b();
        }
    }
    
    public void b() {
        try {
            this.p = p.a();
            if (this.i.getText().indexOf("fuck") >= 0) {
                (this.o = new bl(this, Main.p.a("chat.makeroom.invalidword") + " fuck")).setVisible(true);
                return;
            }
            if (this.i.getText().indexOf("cunt") >= 0) {
                (this.o = new bl(this, Main.p.a("chat.makeroom.invalidword") + " cunt")).setVisible(true);
                return;
            }
            if (this.i.getText().indexOf("admin") >= 0) {
                (this.o = new bl(this, Main.p.a("chat.makeroom.invalidword") + " admin")).setVisible(true);
                return;
            }
            if (!Character.isLetter(this.i.getText().charAt(0))) {
                (this.o = new bl(this, Main.p.a("chat.makeroom.startletter"))).setVisible(true);
            }
            else if (this.i.getText().indexOf("  ") >= 0) {
                (this.o = new bl(this, Main.p.a("chat.makeroom.doublespace"))).setVisible(true);
            }
            else if (this.i.getText().length() <= 2) {
                (this.o = new bl(this, Main.p.a("chat.makeroom.longerthantwo"))).setVisible(true);
            }
            else {
                this.p.c(this.d(), this.k.getText());
                this.p.j("PRIVMSG ChanServ :REGISTER " + this.d() + " awc");
                this.p.j("PRIVMSG ChanServ :SET " + this.d() + " TOPIC " + this.j.getText());
                if (h.d(this.i.getText()) && h.e(this.i.getText()) != null) {
                    (this.o = new bl(this, Main.p.a("chat.makeroom.alreadyexists"))).setVisible(true);
                }
                else {
                    if (this.t.getText().length() > 0) {
                        this.p.j("PRIVMSG ChanServ :SET " + this.d() + " ENTRYMSG " + this.t.getText());
                    }
                    if (this.k.getText().length() > 0) {
                        this.p.j("PRIVMSG Chanserv :SET " + this.d() + " mlock +k " + this.k.getText());
                    }
                    try {
                        Integer.parseInt(this.q.getText());
                        this.p.j("MODE " + this.d() + " +l " + this.q.getText());
                    }
                    catch (NumberFormatException ex2) {}
                    (this.o = new bl(this, Main.p.a("chat.makeroom.created"))).setVisible(true);
                    this.p.h(this.d());
                    this.dispose();
                }
            }
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    public void c() {
        this.p = p.a();
        final String e = h.e(n.b().z());
        if (e.length() > 0) {
            this.p.j("PRIVMSG ChanServ :DROP " + e + " awc");
        }
    }
    
    public static final String a(final String s, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer("");
        int n = 0;
        while (true) {
            final int index = s.indexOf(s2, n);
            if (index < 0) {
                break;
            }
            sb.append(s.substring(n, index));
            sb.append(s3);
            n = index + 1;
        }
        sb.append(s.substring(n));
        return sb.toString();
    }
    
    public void a(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.a();
        }
    }
}
