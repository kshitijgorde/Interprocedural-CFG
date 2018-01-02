import java.awt.Event;
import java.awt.Toolkit;
import java.awt.Rectangle;
import ABLwidgets.new_font;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Component;
import java.awt.Button;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemnp extends Frame
{
    abljema a;
    abljemtc b;
    Panel c;
    GridBagLayout d;
    GridBagConstraints e;
    Label f;
    Label g;
    Label h;
    Label i;
    abljemgt j;
    abljemgt k;
    Button l;
    Button m;
    Component n;
    byte[] o;
    byte[] p;
    String q;
    String r;
    abljemd s;
    GridBagLayout t;
    GridBagConstraints u;
    Label v;
    Label w;
    Button x;
    Font y;
    int z;
    int aa;
    int ab;
    int ac;
    String ad;
    boolean ae;
    
    public abljemnp(final abljema a, final String ad, String concat, String concat2) {
        super(String.valueOf(ad) + " - Identification");
        this.ab = 40;
        this.ac = 50;
        this.ad = "ID";
        this.ad = ad;
        this.a = a;
        this.o = new byte[this.ab];
        this.p = new byte[this.ac];
        final String s = "     ";
        if (concat.length() > 0 && concat.charAt(0) != ' ') {
            concat = s.concat(concat);
        }
        if (concat2.length() > 0 && concat2.charAt(0) != ' ') {
            concat2 = s.concat(concat2);
        }
        this.f = new Label(concat);
        this.j = new abljemgt("", this.ab, this.a);
        this.g = new Label(concat2);
        (this.k = new abljemgt("", this.ac, this.a)).b('*');
        this.l = new Button("    OK    ");
        this.m = new Button("Cancel");
        (this.h = new Label("Insecure Connection")).setAlignment(1);
        (this.i = new Label("  ")).setAlignment(1);
        this.b = new abljemtc();
        if (this.a.ce) {
            this.pack();
            (this.s = new abljemd(this.a, this, "Error", true)).pack();
            this.setBackground(this.s.getBackground());
        }
        this.d = new GridBagLayout();
        this.e = new GridBagConstraints();
        this.e.insets = new Insets(0, 0, 1, 0);
        this.c = new Panel();
        if (this.a.ce) {
            this.c.setBackground(this.s.getBackground());
        }
        this.c.setLayout(this.d);
        this.setLayout(new BorderLayout());
        this.a.a(this.c, this.n, this.d, this.e, 1, 7, -1, 1, 'C');
        this.a.a(this.c, this.f, this.d, this.e, 2, 1, 1, 1, 'E');
        this.a.a(this.c, this.j, this.d, this.e, 2, 2, 5, 1, 'W');
        this.a.a(this.c, this.g, this.d, this.e, 3, 1, 1, 1, 'E');
        this.a.a(this.c, this.k, this.d, this.e, 3, 2, 5, 1, 'W');
        this.a.a(this.c, this.n, this.d, this.e, 4, 1, -1, 1, 'C');
        this.a.a(this.c, this.l, this.d, this.e, 5, 1, 7, 1, 'C');
        this.a.a(this.c, this.h, this.d, this.e, 6, 1, 7, 1, 'C');
        this.add("East", this.c);
        this.setResizable(false);
        this.pack();
        this.ae = false;
        this.h.setText("                                       ");
        if (!this.a.ce) {
            this.s = new abljemd(this.a, this, "Error", true);
        }
        this.t = new GridBagLayout();
        this.u = new GridBagConstraints();
        this.w = new Label("   " + this.getTitle() + " Error   ");
        this.v = new Label("Replaced by error text, but doesn't resize so make this long");
        this.x = new Button("    OK    ");
        this.s.setLayout(this.t);
        this.a.a(this.s, this.w, this.t, this.u, 1, 1, 1, 1, 'C');
        this.z = 2;
        this.aa = 1;
        this.a.a(this.s, this.n, this.t, this.u, 3, 1, 1, 1, 'C');
        this.a.a(this.s, this.x, this.t, this.u, 4, 1, 1, 1, 'C');
        this.a.a(this.s, this.n, this.t, this.u, 5, 1, 1, 1, 'C');
        this.y = this.w.getFont();
        if (this.y == null) {
            abljem.d("Font error 1");
        }
        else {
            this.w.setFont(new_font.a(this.y.getName(), 1, this.y.getSize()));
        }
        this.s.setResizable(false);
        this.s.pack();
    }
    
    private void e() {
        final Rectangle bounds = this.h.getBounds();
        this.f.hide();
        this.j.hide();
        this.g.hide();
        this.k.hide();
        this.l.hide();
        this.a.a(this.c, this.i, this.d, this.e, 3, 1, 7, 1, 'C');
        this.c.remove(this.g);
        this.c.remove(this.k);
        this.d.layoutContainer(this.c);
        final Rectangle bounds2 = this.i.getBounds();
        this.i.reshape(2, bounds2.y, this.i.getParent().getSize().width - 4, bounds2.height);
        this.h.move(bounds.x, bounds.y);
    }
    
    void a(final String text) {
        if (this.f.isVisible()) {
            this.e();
        }
        this.i.setText(text);
        this.i.repaint();
    }
    
    void a() {
        this.h.setText("Secure connection");
        this.h.show();
    }
    
    void b() {
        this.j.disable();
        this.k.disable();
        this.l.disable();
        this.m.disable();
    }
    
    void c() {
        if (this.a.c8) {
            return;
        }
        this.j.enable();
        this.k.enable();
        this.l.enable();
        this.m.enable();
    }
    
    void d() {
        final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        if (!this.ae) {
            if (this.a.cs != null) {
                try {
                    this.a.cv.waitForID(this.a.cx);
                }
                catch (InterruptedException ex) {}
                this.b.c(this.a.cs);
                this.b.show();
                this.add("Center", this.b);
            }
            this.pack();
            this.ae = true;
        }
        this.move((width - this.bounds().width) / 2, (height - this.bounds().height) / 3);
        if (this.a.c8) {
            this.setTitle(String.valueOf(this.ad) + " - Auto Logon");
            if (this.a.gw != null) {
                this.j.c(this.a.gw);
            }
            this.b();
        }
        this.show();
        this.j.requestFocus();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.l) {
            this.a(event);
        }
        else if (event.target == this.m) {
            this.f();
        }
        else {
            if (event.target != this.x) {
                return false;
            }
            this.g();
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 9) {
            final boolean b = event.modifiers == 1;
            if (event.target == this) {
                if (b) {
                    this.l.requestFocus();
                }
                else {
                    this.j.requestFocus();
                }
            }
            if (event.target == this.j) {
                if (b) {
                    this.l.requestFocus();
                }
                else {
                    this.k.requestFocus();
                }
            }
            if (event.target == this.k) {
                if (b) {
                    this.j.requestFocus();
                }
                else {
                    this.l.requestFocus();
                }
            }
            if (event.target == this.l) {
                if (b) {
                    this.k.requestFocus();
                }
                else {
                    this.j.requestFocus();
                }
            }
            if (event.target == this.s) {
                this.x.requestFocus();
            }
            return true;
        }
        if (n == 10 && event.modifiers == 0) {
            if (event.target == this.m) {
                this.f();
                return true;
            }
            if (event.target == this.l) {
                this.a(event);
                return true;
            }
            if (event.target == this.x || event.target == this.s) {
                this.g();
                return true;
            }
            this.a(event);
            return true;
        }
        else {
            if (n == 27 && event.modifiers == 0 && (event.target == this.x || event.target == this.s || (event.target == this && event.x == 0 && event.y == 0 && this.a.cl))) {
                this.g();
                return true;
            }
            return super.keyDown(event, n);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return super.mouseDown(event, n, n2);
    }
    
    private void a(final Event event) {
        this.q = this.j.av;
        this.q = this.q.trim();
        this.r = this.k.av;
        this.r = this.r.trim();
        if (!this.a.gt) {
            this.r = this.r.toUpperCase();
        }
        int n = this.q.length();
        if (n > this.ab) {
            n = this.ab;
        }
        int n2 = this.r.length();
        if (n2 > this.ac) {
            n2 = this.ac;
        }
        if (n < 1) {
            this.b("No name entered");
            return;
        }
        if (n2 < 1) {
            this.b("No passphrase entered");
            return;
        }
        if (event != null && event.target instanceof abljemgt) {
            ((abljemgt)event.target).lostFocus(null, null);
        }
        for (int i = 0; i < this.o.length; ++i) {
            this.o[i] = 32;
        }
        for (int j = 0; j < this.p.length; ++j) {
            this.p[j] = 32;
        }
        this.q.getBytes(0, n, this.o, 0);
        this.r.getBytes(0, n2, this.p, 0);
        this.a.g8.b();
        this.a.bm.a = true;
    }
    
    private void f() {
        this.hide();
        this.dispose();
        this.a.a2 = null;
        this.a.az = false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            if (event.target == this) {
                this.f();
            }
            else if (event.target == this.s) {
                this.g();
            }
        }
        return super.handleEvent(event);
    }
    
    private void g() {
        this.s.hide();
        this.c();
        this.enable();
        this.l.requestFocus();
    }
    
    public void b(final String text) {
        this.s.move(this.bounds().x + this.bounds().width / 5, this.bounds().y + this.bounds().height * 7 / 12);
        this.v.setText(text);
        this.v.setAlignment(1);
        this.s.remove(this.v);
        this.a.a(this.s, this.v, this.t, this.u, this.z, this.aa, 1, 1, 'C');
        this.s.pack();
        this.s.show();
    }
}
