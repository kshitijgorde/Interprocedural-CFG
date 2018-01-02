import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.util.Hashtable;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class bn extends JPanel
{
    public static final Hashtable a;
    public String b;
    public String c;
    public String d;
    public int e;
    private JLabel f;
    private BorderLayout g;
    
    public bn() {
        this.b = "";
        this.c = "";
        this.d = Main.p.a("settings.chat.defaultexampletext");
        this.e = 10;
        this.f = new JLabel();
        this.g = new BorderLayout();
        this.f.setAlignmentX(0.0f);
        this.f.setAlignmentY(0.0f);
        this.f.setText(this.d);
        this.f.setOpaque(true);
        this.setLayout(this.g);
        this.f.setForeground(dj.x);
        this.add(this.f, "Center");
    }
    
    public void a(final String s) {
        this.setBackground(bn.a.get(s));
        this.f.setBackground(bn.a.get(s));
    }
    
    public void b(final String s) {
        final Font font = new Font(s, 0, this.e);
        this.setFont(font);
        this.f.setFont(font);
    }
    
    public void a(final int e) {
        this.e = e;
        final Font font = new Font(this.getFont().getName(), 0, e);
        this.setFont(font);
        this.f.setFont(font);
    }
    
    public void c(final String b) {
        this.b = b;
        this.f.setForeground(bn.a.get(b));
    }
    
    static {
        (a = new Hashtable()).put(Main.p.a("settings.chat.color.black"), Color.black);
        bn.a.put(Main.p.a("settings.chat.color.blue"), Color.blue);
        bn.a.put(Main.p.a("settings.chat.color.cyan"), Color.cyan);
        bn.a.put(Main.p.a("settings.chat.color.darkgray"), Color.darkGray);
        bn.a.put(Main.p.a("settings.chat.color.gray"), Color.gray);
        bn.a.put(Main.p.a("settings.chat.color.green"), Color.green);
        bn.a.put(Main.p.a("settings.chat.color.lightgray"), Color.lightGray);
        bn.a.put(Main.p.a("settings.chat.color.magenta"), Color.magenta);
        bn.a.put(Main.p.a("settings.chat.color.orange"), Color.orange);
        bn.a.put(Main.p.a("settings.chat.color.pink"), Color.pink);
        bn.a.put(Main.p.a("settings.chat.color.red"), Color.red);
        bn.a.put(Main.p.a("settings.chat.color.white"), Color.white);
        bn.a.put(Main.p.a("settings.chat.color.yellow"), Color.yellow);
    }
}
