import java.util.StringTokenizer;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Component;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import javax.swing.JTextField;

// 
// Decompiled by Procyon v0.5.30
// 

public class v extends w
{
    private JTextField a;
    private String b;
    private x c;
    private al d;
    private static v e;
    private boolean f;
    public aa g;
    
    public static synchronized v a() {
        if (v.e == null) {
            v.e = new v();
        }
        return v.e;
    }
    
    private v() {
        super("Find in Chat", ImageRes.a1);
        this.a = new JTextField("Enter Member's Name");
        this.b = "";
        this.c = new x("Find");
        this.f = false;
        this.g = new aa(new y());
        this.add(this.a);
        this.add(this.c);
        this.add(this.g);
        this.a.setBounds(1, 17, 196, 21);
        this.a.addKeyListener(new ae(this));
        this.a.addMouseListener(new af(this));
        this.c.setBounds(1, 40, 195, 17);
        this.g.setBounds(1, 59, 195, 135);
        this.c.addMouseListener(new ag(this));
    }
    
    public void a(final String text) {
        this.a.setText(text);
        this.c();
    }
    
    public void b() {
        if (this.a.getText().equals("Enter Member's Name") || this.a.getText().equals("Searching") || this.a.getText().equals(Main.p.a("chat.findusers.results")) || this.a.getText().equals(Main.p.a("chat.findusers.notonline")) || this.a.getText().equals(Main.p.a("chat.findusers.notinchat"))) {
            this.a.setText("");
            this.g.c();
        }
    }
    
    public void c() {
        h.f().i();
        this.a(true);
        if (n.b().u()) {
            this.b = this.a.getText();
            this.g.c();
            p.a().j("whois " + this.b);
            this.a.setText("Searching");
            this.f = false;
        }
        else {
            (this.d = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100)).setVisible(true);
        }
    }
    
    public void a(final int n, String s) {
        try {
            s = s.substring(this.b.length() + 2, s.length());
            s = s.trim();
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
        switch (n) {
            case 319: {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
                while (stringTokenizer.hasMoreElements()) {
                    String s2 = stringTokenizer.nextToken();
                    if (s2.startsWith("@")) {
                        s2 = s2.substring(1);
                    }
                    final bj bj = new bj(s2);
                    if (h.f().a(bj.s())) {
                        this.g.a(bj);
                        this.f = true;
                    }
                }
                this.a.setText("Search Completed");
                break;
            }
            case 401: {
                if (!this.f) {
                    this.a.setText(Main.p.a("chat.findusers.notonline"));
                    this.f = true;
                    break;
                }
                break;
            }
            case 318: {
                if (!this.f) {
                    this.a.setText(Main.p.a("chat.findusers.notinchat"));
                    break;
                }
                break;
            }
        }
        this.g.a();
    }
}
