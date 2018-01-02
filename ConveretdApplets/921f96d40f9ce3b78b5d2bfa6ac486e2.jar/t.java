import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class t extends db implements dq, Runnable, ActionListener
{
    private static t p;
    private dh d;
    private long a;
    private dl n;
    private Thread v;
    public boolean i;
    public int l;
    public int b;
    public int c;
    public int e;
    public String f;
    
    public static final t p() {
        return t.p;
    }
    
    public t(final y y, final String s) {
        this(y, s, 0L);
    }
    
    public t(final y y, final String f, final long a) {
        super(y, "Notice");
        this.i = false;
        if (t.p != null) {
            t.p.dispose();
        }
        this.setFont(dw.l);
        this.f = f;
        this.a = a;
        (this.n = new dl("Close")).p(this);
        (this.d = new dh(this, y.p(), 150, 1)).setBackground(Color.white);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", this.n);
        this.setLayout(new BorderLayout());
        this.add("Center", this.d);
        this.add("South", panel);
        this.d();
        t.p = this;
    }
    
    public final void p(final String s, final int n, final int c, final int e) {
        this.c = c;
        this.e = e;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.l = (screenSize.width - c) / 2;
        this.b = (screenSize.height - e) / 2;
        this.invalidate();
        this.setTitle("[" + super.p.p().d() + "] " + s);
        this.setSize(c, e);
        this.setResizable(false);
        this.validate();
        this.d.d();
        this.setBounds(this.l, this.b, c, e);
        this.show();
        if (this.a == 0L) {
            this.a = System.currentTimeMillis() + n * 1000;
            this.n.p("Close");
        }
        else {
            this.n.p("Please wait");
        }
        if (this.v == null || !this.v.isAlive()) {
            (this.v = new Thread(this)).start();
        }
    }
    
    public final void d() {
        final String f = this.f;
        final int p = du.p(du.p(f, 0, ' '), 10);
        final int p2 = du.p(du.p(f, 1, ' '), 10);
        final int p3 = du.p(du.p(f, 2, ' '), 10);
        final String d = du.d(f, 2, ' ');
        if (p2 <= 0 || p3 <= 0 || p < 0) {
            System.err.println("Invalid notice: " + d);
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(d, "|");
        if (!stringTokenizer.hasMoreTokens()) {
            System.err.println("Invalid notice: " + d);
            return;
        }
        this.p(stringTokenizer.nextToken(), p, p2, p3);
        while (stringTokenizer.hasMoreTokens()) {
            this.d.i(stringTokenizer.nextToken());
        }
    }
    
    public final void v(final String s) {
    }
    
    public final void run() {
        while (!this.i && System.currentTimeMillis() < this.a) {
            try {
                du.p(1000);
                if (!this.n.p().equals("Close")) {
                    this.n.p("Please wait " + (this.a - System.currentTimeMillis()) / 1000L + "s");
                }
                this.setBounds(this.l, this.b, this.c, this.e);
                if (!this.isShowing()) {
                    this.show();
                }
                this.toFront();
            }
            catch (Exception ex) {}
        }
        this.i = true;
        if (!this.n.p().equals("Close")) {
            this.dispose();
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == this.n) {
                if (this.i) {
                    this.dispose();
                    return;
                }
                this.n.p("Please wait...");
            }
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public final void dispose() {
        this.i = true;
        this.setVisible(false);
        super.dispose();
    }
    
    public final void windowIconified(final WindowEvent windowEvent) {
        if (!this.i) {
            new t(super.p, this.f, this.a);
            this.setVisible(false);
        }
        this.dispose();
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        if (this.i) {
            this.dispose();
            return;
        }
        this.n.p("Please wait...");
    }
    
    static {
        t.p = null;
    }
}
