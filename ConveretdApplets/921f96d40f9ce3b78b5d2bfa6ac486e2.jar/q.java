import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class q extends Thread implements k
{
    private static final int[] p;
    private static final int[] d;
    boolean p;
    boolean d;
    boolean a;
    int p;
    String p;
    String[] p;
    int d;
    int a;
    int n;
    y p;
    do p;
    Color p;
    Font p;
    Font d;
    FontMetrics p;
    FontMetrics d;
    int v;
    static int i;
    static int l;
    static int b;
    
    public final boolean n() {
        return !this.a;
    }
    
    public q(final int d, final y p2) {
        this.p = false;
        this.a = true;
        this.p = Color.red;
        this.p = new String[d];
        this.d = d;
        this.p = p2;
        this.p = p2.p();
        final int a = p2.a();
        this.p = ((a < 0 || a > 2) ? 1 : a);
        this.a = q.d[a];
        this.n = q.p[a];
        this.p = dw.v;
        this.d = dw.i;
        this.p = Toolkit.getDefaultToolkit().getFontMetrics(this.p);
        this.d = Toolkit.getDefaultToolkit().getFontMetrics(this.d);
        this.v = this.p.getHeight();
        this.start();
    }
    
    public int d() {
        return 2;
    }
    
    final void e() {
        this.p.p().setVisible(true);
    }
    
    public void p(final Graphics graphics, final Color color, final int n, final int n2) {
        graphics.setColor(color);
        graphics.fill3DRect(0, 0, n, n2, true);
    }
    
    final void d(final String s) {
        this.p.p().d(s);
    }
    
    public void o() {
        for (int i = 0; i < this.d; ++i) {
            this.p[i] = null;
        }
        this.d(1000);
    }
    
    public final int i() {
        if (this.p.p() == null) {
            return -1;
        }
        return this.p(this.p.p().d());
    }
    
    public final int p(final String s) {
        for (int i = 0; i < this.d; ++i) {
            if (s.equalsIgnoreCase(this.p[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public final boolean p() {
        return this.d;
    }
    
    public final int p() {
        int n = 0;
        for (int i = 0; i < this.d; ++i) {
            if (this.p[i] != null) {
                ++n;
            }
        }
        return n;
    }
    
    public final String p() {
        return this.p;
    }
    
    public final void a(final String p) {
        this.p = p;
        this.d(200);
    }
    
    public boolean a(final int n, final int n2) {
        return true;
    }
    
    public boolean v(final int n, final int n2) {
        return true;
    }
    
    public boolean i(final int n, final int n2) {
        return true;
    }
    
    public boolean p(final int n, final int n2) {
        return true;
    }
    
    public final boolean d(final int n, final int n2) {
        this.e();
        return true;
    }
    
    public final void destroy() {
        this.p = true;
    }
    
    public final void run() {
        while (!this.p && this.p.isValid()) {
            try {
                du.p(300);
                this.a();
            }
            catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    final void f() {
        this.p.p().repaint();
    }
    
    final void d(final int n) {
        this.p.p().repaint(n);
    }
    
    public boolean p(final String s, final String s2) {
        if (s.equals("RATING")) {
            final boolean equals = "true".equals(s2);
            if (equals != this.a) {
                this.a = equals;
                this.p.l("<4>***Game is now " + (this.a ? "rated" : "unrated"));
                this.p.p().p(!this.a);
                this.v();
            }
            else if (!equals) {
                this.p.l("<4>***This game is non-rating.");
            }
            return true;
        }
        return false;
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final String string = itemEvent.getItem().toString();
        if (string.toString().startsWith("$")) {
            if (this.i() != 0) {
                this.p.l("<4>***You can only change bet when in seat #1" + ((this.p[0] != null) ? (", ask " + this.p[0]) : ""));
                return;
            }
            this.d("BET " + string.toString().substring(1));
        }
    }
    
    void v() {
    }
    
    public void b() {
    }
    
    public boolean a() {
        return false;
    }
    
    public final void p(final Graphics graphics, final String s, int n, final int n2, final Color color, final Color color2) {
        final FontMetrics fontMetrics = this.p.p().getFontMetrics(graphics.getFont());
        n -= fontMetrics.stringWidth(s);
        if (color2 != null) {
            graphics.setColor(color2);
            graphics.fill3DRect(n - 4, n2 - fontMetrics.getAscent() - 2, fontMetrics.stringWidth(s) + 8, fontMetrics.getHeight() + 4, true);
        }
        graphics.setColor(color);
        graphics.drawString(s, n, n2);
    }
    
    public abstract String d();
    
    public abstract int p(final int p0);
    
    public abstract void p(final Graphics p0);
    
    public abstract void p(final Panel p0);
    
    public abstract void a();
    
    public abstract void actionPerformed(final ActionEvent p0);
    
    static {
        p = new int[] { 85, 97, 120 };
        d = new int[] { 64, 73, 90 };
        q.i = 1;
        q.l = 2;
        q.b = 3;
    }
}
