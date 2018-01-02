import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.Frame;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class c8 extends JDialog
{
    private JList a;
    private JButton b;
    private JTextField c;
    private JButton d;
    private Vector e;
    private p f;
    private String g;
    
    public c8(final Frame frame, final String g, final Vector e, final String s) {
        super(frame, s + " " + Main.p.a("chat.ban"));
        this.b = new JButton();
        this.c = new JTextField();
        this.d = new JButton();
        this.g = g;
        this.e = e;
        this.a = new JList(e);
        try {
            this.f = p.a();
            this.setSize(420, 300);
            this.getContentPane().setLayout(null);
            this.setResizable(false);
            this.addWindowListener(new c9(this));
            final JScrollPane scrollPane = new JScrollPane(this.a);
            scrollPane.setBounds(new Rectangle(15, 28, 180, 192));
            this.b.setText(Main.p.a("chat.addtobanlist"));
            this.b.setBounds(new Rectangle(200, 53, 180, 21));
            this.b.addActionListener(new da(this));
            this.c.setBounds(new Rectangle(200, 29, 180, 21));
            this.d.setBounds(new Rectangle(15, 224, 180, 21));
            this.d.addActionListener(new db(this));
            this.d.setText(Main.p.a("chat.remfrombanlist"));
            this.getContentPane().add(scrollPane, null);
            this.getContentPane().add(this.c, null);
            this.getContentPane().add(this.b, null);
            this.getContentPane().add(this.d, null);
        }
        catch (Exception ex) {
            b.a(ex, 3);
            this.dispose();
        }
    }
    
    public void a() {
        this.a.removeAll();
        this.e.removeAllElements();
        this.dispose();
    }
    
    public void b() {
        this.f.b(this.g, this.c.getText());
        this.e.addElement(this.c.getText());
        this.c.setText("");
    }
    
    public void c() {
        this.f.j(this.g, this.a.getSelectedValue().toString());
        this.a.remove(this.a.getSelectedIndex());
    }
}
