import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.LayoutManager;
import au.com.rocketdog.project.awc.applet.Main;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class bo extends JPanel
{
    private JTextField a;
    private JLabel b;
    private JTextField c;
    private JLabel d;
    private JTextField e;
    private JLabel f;
    private JLabel g;
    private JTextField h;
    private JTextField i;
    private JLabel j;
    private JLabel k;
    private JTextField l;
    private JLabel m;
    private JTextField n;
    private JLabel o;
    private JTextField p;
    private JLabel q;
    private JTextField r;
    private JButton s;
    
    public void a() {
        Main.c.put("F1", this.a.getText());
        Main.c.put("F2", this.c.getText());
        Main.c.put("F3", this.e.getText());
        Main.c.put("F4", this.h.getText());
        Main.c.put("F5", this.i.getText());
        Main.c.put("F6", this.l.getText());
        Main.c.put("F7", this.n.getText());
        Main.c.put("F8", this.p.getText());
        Main.c.put("F9", this.r.getText());
        Main.f();
    }
    
    public bo() {
        this.a = new JTextField();
        this.b = new JLabel();
        this.c = new JTextField();
        this.d = new JLabel();
        this.e = new JTextField();
        this.f = new JLabel();
        this.g = new JLabel();
        this.h = new JTextField();
        this.i = new JTextField();
        this.j = new JLabel();
        this.k = new JLabel();
        this.l = new JTextField();
        this.m = new JLabel();
        this.n = new JTextField();
        this.o = new JLabel();
        this.p = new JTextField();
        this.q = new JLabel();
        this.r = new JTextField();
        this.s = new JButton();
        try {
            this.b();
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    public void b() throws Exception {
        this.setLayout(null);
        this.b.setFont(new Font("Dialog", 1, 12));
        this.b.setText("F1");
        this.b.setBounds(new Rectangle(40, 21, 31, 21));
        this.a.setBounds(new Rectangle(88, 21, 289, 21));
        this.d.setBounds(new Rectangle(40, 47, 31, 21));
        this.c.setBounds(new Rectangle(88, 47, 289, 21));
        this.d.setText("F2");
        this.d.setFont(new Font("Dialog", 1, 12));
        this.e.setBounds(new Rectangle(88, 73, 289, 21));
        this.f.setFont(new Font("Dialog", 1, 12));
        this.f.setText("F3");
        this.f.setBounds(new Rectangle(40, 73, 31, 21));
        this.g.setBounds(new Rectangle(40, 100, 31, 21));
        this.g.setText("F4");
        this.g.setFont(new Font("Dialog", 1, 12));
        this.h.setBounds(new Rectangle(88, 99, 289, 21));
        this.i.setBounds(new Rectangle(88, 124, 289, 21));
        this.j.setFont(new Font("Dialog", 1, 12));
        this.j.setText("F5");
        this.j.setBounds(new Rectangle(40, 124, 31, 21));
        this.k.setBounds(new Rectangle(40, 150, 31, 21));
        this.k.setText("F6");
        this.k.setFont(new Font("Dialog", 1, 12));
        this.l.setBounds(new Rectangle(88, 150, 289, 21));
        this.m.setFont(new Font("Dialog", 1, 12));
        this.m.setText("F7");
        this.m.setBounds(new Rectangle(40, 176, 31, 21));
        this.n.setBounds(new Rectangle(88, 176, 289, 21));
        this.o.setBounds(new Rectangle(40, 202, 31, 21));
        this.o.setText("F8");
        this.o.setFont(new Font("Dialog", 1, 12));
        this.p.setBounds(new Rectangle(88, 202, 289, 21));
        this.q.setFont(new Font("Dialog", 1, 12));
        this.q.setText("F9");
        this.q.setBounds(new Rectangle(40, 228, 31, 21));
        this.r.setBounds(new Rectangle(88, 228, 289, 21));
        this.s.setText(Main.p.a("settings.save"));
        this.s.setBounds(new Rectangle(172, 286, 84, 24));
        this.s.addActionListener(new bp(this));
        this.add(this.a, null);
        this.add(this.b, null);
        this.add(this.c, null);
        this.add(this.d, null);
        this.add(this.e, null);
        this.add(this.h, null);
        this.add(this.i, null);
        this.add(this.f, null);
        this.add(this.g, null);
        this.add(this.j, null);
        this.add(this.l, null);
        this.add(this.n, null);
        this.add(this.p, null);
        this.add(this.m, null);
        this.add(this.k, null);
        this.add(this.o, null);
        this.add(this.r, null);
        this.add(this.q, null);
        this.add(this.s, null);
        this.a.setText(Main.c.get("F1"));
        this.c.setText(Main.c.get("F2"));
        this.e.setText(Main.c.get("F3"));
        this.h.setText(Main.c.get("F4"));
        this.i.setText(Main.c.get("F5"));
        this.l.setText(Main.c.get("F6"));
        this.n.setText(Main.c.get("F7"));
        this.p.setText(Main.c.get("F8"));
        this.r.setText(Main.c.get("F9"));
    }
}
