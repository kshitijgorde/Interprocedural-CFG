import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class volatile extends Panel
{
    static final int R = 0;
    static final int S = 1;
    Image T;
    Graphics U;
    Dimension size;
    Font V;
    var W;
    transient X;
    String Y;
    String Z;
    String _a;
    String aa;
    String ba;
    String ca;
    String[] da;
    int ea;
    int fa;
    int ga;
    int ha;
    int ia;
    int ja;
    int ka;
    int la;
    Rectangle ma;
    Rectangle na;
    Rectangle oa;
    Rectangle pa;
    Rectangle qa;
    Rectangle ra;
    Rectangle bounds;
    synchronized sa;
    private static String o = "\u37fa\u37c9\u37d2\u37da\u37d7";
    private static String p = "\u37d5\u37de\u37c3\u37cf";
    private static String q = "\u37d9\u37da\u37d8\u37d0";
    private static String C = "\u37d8\u37d7\u37de\u37da\u37c9";
    private static String D = "\u37c8\u37d4\u37d7\u37cd\u37de";
    private static String E = "\u37d3\u37de\u37d7\u37cb";
    private static String F = "\u37de\u37da\u37c8\u37c2\u379b\u3785\u3785";
    private static String ta = "\u37d6\u37de\u37df\u37d2\u37ce\u37d6\u379b\u3785\u3785";
    private static String ua = "\u37d3\u37da\u37c9\u37df\u379b\u3785\u3785";
    private static String wa = "\u37cf\u37da\u37d5\u37dc\u37c9\u37da\u37d6\u379b\u3781\u379b";
    private static String xa = "\u379b\u3781\u379b";
    
    public volatile(final var w, final transient x, final synchronized sa) {
        this.V = new Font(volatile.o, 1, 12);
        this.Y = volatile.p;
        this.Z = volatile.q;
        this._a = volatile.C;
        this.aa = volatile.D;
        this.ba = volatile.E;
        this.da = new String[] { volatile.F, volatile.ta, volatile.ua };
        this.ea = -1;
        this.W = w;
        this.X = x;
        this.sa = sa;
        this.ca = this.da[0];
        this._(0);
        this.enableEvents(16L);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this._(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    private void _(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.ra.contains(n, n2)) {
            this.X.va = !this.X.va;
            if (this.X.va) {
                this.X.c();
            }
            this.X.repaint();
            this._(this.X.va ? 1 : 0);
        }
        if (!this.X.va) {
            if (this.ma.contains(n, n2)) {
                this.X.d();
                this.X.repaint();
                this._(0);
                return;
            }
            if (this.na.contains(n, n2)) {
                this.X.e();
                this.X.repaint();
                this._(0);
                return;
            }
            if (this.oa.contains(n, n2)) {
                this.X.c();
                this.X.repaint();
                return;
            }
            if (this.pa.contains(n, n2)) {
                this.X.f();
                ++this.la;
                if (this.la > 2) {
                    this.la = 0;
                }
                this.ca = this.da[this.la];
                this._(0);
                this.X.repaint();
                this.repaint();
                return;
            }
            if (this.qa.contains(n, n2)) {
                this.X.g();
                this.X.repaint();
            }
        }
    }
    
    private void _(final int n) {
        final StringBuffer sb = new StringBuffer(volatile.wa);
        this.W.b(null);
        switch (n) {
            case 0: {
                sb.append(this.ca.substring(0, this.ca.length() - 3)).append(volatile.xa);
                sb.append(this.X.b() + 1);
                this.W.setTitle(sb.toString());
                break;
            }
            case 1: {
                sb.append(this.ba);
                this.W.setTitle(sb.toString());
                break;
            }
        }
        this.W.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.W.repaint();
        this.U.setColor(this.getBackground());
        this.U.fillRect(0, 0, this.size.width, this.size.height);
        this.U.setColor(this.getForeground());
        switch._(this.na, this.U);
        switch._(this.ma, this.U);
        switch._(this.oa, this.U);
        switch._(this.pa, this.U);
        switch._(this.qa, this.U);
        switch._(this.ra, this.U);
        if (this.ea == -1) {
            this.ea = switch.a(this.Z, false, this.ma, this.U);
            this.fa = switch.a(this.Z, true, this.na, this.U);
            this.ga = switch.a(this.Y, true, this.ma, this.U);
            this.ha = switch.a(this._a, true, this.oa, this.U);
            this.ja = switch.a(this.aa, true, this.qa, this.U);
            this.ka = switch.a(this.ba, true, this.ra, this.U);
        }
        this.ia = switch.a(this.ca, true, this.pa, this.U);
        this.U.setColor(this.getForeground());
        this.U.setFont(this.V);
        this.U.drawString(this.Z, this.fa, this.ea);
        this.U.drawString(this.Y, this.ga, this.ea);
        this.U.drawString(this._a, this.ha, this.ea);
        this.U.drawString(this.ca, this.ia, this.ea);
        this.U.drawString(this.aa, this.ja, this.ea);
        this.U.drawString(this.ba, this.ka, this.ea);
        graphics.drawImage(this.T, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.T == null) {
            this.size = this.getSize();
            this.T = this.createImage(this.size.width, this.size.height);
            this.U = this.T.getGraphics();
            this.bounds = this.getBounds();
            final int width = this.X.ya.width;
            final int x = this.X.ya.x;
            final int n = 50;
            final int n2 = this.bounds.height - 10;
            final int n3 = (this.bounds.height - n2) / 2;
            final int n4 = (width / 2 - n) / 2;
            this.na = new Rectangle(x + n4, n3, n, n2);
            this.ma = new Rectangle(x + width / 2 + n4, n3, n, n2);
            this.oa = new Rectangle(x - 80, n3, n, n2);
            this.qa = new Rectangle(x - 160, n3, n, n2);
            this.ra = new Rectangle(x - 240, n3, n, n2);
            this.pa = new Rectangle(30, n3, 90, n2);
        }
        this.update(graphics);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF37BB);
        }
        return new String(array);
    }
    
    static {
        volatile.o = _(volatile.o);
        volatile.p = _(volatile.p);
        volatile.q = _(volatile.q);
        volatile.C = _(volatile.C);
        volatile.D = _(volatile.D);
        volatile.E = _(volatile.E);
        volatile.F = _(volatile.F);
        volatile.ta = _(volatile.ta);
        volatile.ua = _(volatile.ua);
        volatile.wa = _(volatile.wa);
        volatile.xa = _(volatile.xa);
    }
}
