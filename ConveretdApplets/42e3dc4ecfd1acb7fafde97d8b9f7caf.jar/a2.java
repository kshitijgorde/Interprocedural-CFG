import java.awt.event.ItemEvent;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.Rectangle;
import au.com.rocketdog.project.awc.applet.Main;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class a2 extends JPanel
{
    private JCheckBox a;
    private JCheckBox b;
    private JCheckBox c;
    private JCheckBox d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    
    public a2(final boolean e, final boolean f, final boolean g, final boolean h) {
        this.a = new JCheckBox();
        this.b = new JCheckBox();
        this.c = new JCheckBox();
        this.d = new JCheckBox();
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = false;
        try {
            this.b.setText(Main.p.a("gender.female"));
            this.b.setSelected(f);
            this.b.setBounds(new Rectangle(7, 28, 150, 21));
            this.b.addItemListener(new a3(this, 2));
            this.a.setText(Main.p.a("gender.male"));
            this.a.setBounds(new Rectangle(7, 49, 150, 21));
            this.a.setSelected(e);
            this.a.addItemListener(new a3(this, 1));
            this.c.setText(Main.p.a("gender.group"));
            this.c.setSelected(g);
            this.c.setBounds(new Rectangle(7, 70, 150, 21));
            this.c.addItemListener(new a3(this, 6));
            this.d.setText(Main.p.a("gender.other"));
            this.d.setSelected(h);
            this.d.setBounds(new Rectangle(7, 91, 150, 21));
            this.d.addItemListener(new a3(this, 0));
            this.add(this.b, null);
            this.add(this.a, null);
            this.add(this.c, null);
            this.add(this.d, null);
            this.setLayout(null);
            final l b = l.b();
            b.d = e;
            b.h = h;
            b.f = f;
            b.g = g;
            this.e = e;
            this.f = f;
            this.g = g;
            this.h = h;
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    public void a(final ItemEvent itemEvent, final int n) {
        this.i = true;
        final l b = l.b();
        if (itemEvent.getStateChange() == 1) {
            switch (n) {
                case 1: {
                    b.d = true;
                    this.e = true;
                    break;
                }
                case 2: {
                    b.f = true;
                    this.f = true;
                    break;
                }
                case 6: {
                    b.g = true;
                    this.g = true;
                    break;
                }
                case 0: {
                    this.h = true;
                    b.h = true;
                    break;
                }
            }
        }
        else {
            switch (n) {
                case 1: {
                    b.d = false;
                    this.e = false;
                    break;
                }
                case 2: {
                    b.f = false;
                    this.f = false;
                    break;
                }
                case 6: {
                    b.g = false;
                    this.g = false;
                    break;
                }
                case 0: {
                    b.h = false;
                    this.h = false;
                    break;
                }
            }
        }
        b.e();
    }
}
