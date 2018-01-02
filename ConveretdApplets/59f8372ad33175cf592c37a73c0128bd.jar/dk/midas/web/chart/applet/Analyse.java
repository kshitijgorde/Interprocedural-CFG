// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.FontMetrics;
import java.awt.Color;
import dk.midas.web.chart.applet.a.c;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Insets;

public abstract class Analyse extends av implements q
{
    public static final int cw = 60;
    private static final Insets cB;
    private static final Insets cA;
    private Study cx;
    private String cr;
    protected int cu;
    protected float[][] cz;
    protected int[] cv;
    protected int[][] cy;
    protected float[] cC;
    protected int[] cs;
    protected Font cD;
    protected Font ct;
    private long cq;
    
    public Analyse(final ChartManager chartManager, final Study cx, final DataSource dataSource, final int n) {
        super(chartManager, dataSource, Analyse.cA);
        this.cu = 10;
        this.cz = null;
        this.cq = 0L;
        this.cx = cx;
        this.cz = new float[n][];
        this.cy = new int[n][];
        this.cD = new Font("Arial", 0, 14);
        this.ct = new Font("Arial", 0, 12);
        this.if(Analyse.cB);
        final a a = new a();
        this.addMouseListener(a);
        this.addMouseMotionListener(a);
    }
    
    public abstract void m();
    
    public String g() {
        return this.cr;
    }
    
    public Study f() {
        return this.cx;
    }
    
    public abstract void a(final Object[] p0);
    
    public int e() {
        int n = 0;
        if (this.cz != null) {
            for (int i = 0; i < this.cz.length; ++i) {
                if (this.cz[i] != null) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    public float[] new(final int n) {
        if (this.cz != null && n >= 0 && n < this.cz.length) {
            return this.cz[n];
        }
        return null;
    }
    
    public boolean char(final int n) {
        return true;
    }
    
    public synchronized float void() {
        this.null().o().do();
        try {
            if (this.cz != null && this.cz[0] != null) {
                return this.cz[0][Math.min(this.cz[0].length - 1, this.null().D() - 1)];
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        finally {
            this.null().o().a();
        }
        return Float.MIN_VALUE;
    }
    
    protected void int(final String s) {
        if (s != "") {
            this.cr = this.f().int() + " " + s;
        }
        else {
            this.cr = this.f().int();
        }
    }
    
    protected void long(final int cu) {
        this.cu = cu;
        this.int("" + cu);
    }
    
    protected void u() {
        final int d = this.null().D();
        if (c.a(this.cz[0]) < d) {
            for (int i = 0; i < this.cz.length; ++i) {
                this.cz[i] = new float[d];
            }
        }
    }
    
    protected void d() {
        if (!this.try()) {
            return;
        }
        this.m();
        this.v();
        this.t();
        this.k();
        this.s();
        super.bD.do();
        this.a(false);
    }
    
    protected void v() {
        final int new1 = this.null().y().new();
        if (c.a(this.cv) < new1) {
            this.cv = new int[new1];
            for (int i = 0; i < this.cy.length; ++i) {
                this.cy[i] = new int[new1];
            }
        }
        super.bJ = this.null(new1);
        super.bC = this.l();
        super.bF = this.q();
        super.bI = this.o();
        final int u = this.null().u();
        for (int j = 0; j < new1; ++j) {
            this.cv[j] = this.a(super.bJ, j + u);
            for (int k = 0; k < this.cy.length; ++k) {
                this.cy[k][j] = this.a(super.bI, j + u, k);
            }
        }
        final int a = c.a(this.cC);
        if (this.cs == null) {
            this.cs = new int[a];
        }
        for (int l = 0; l < a; ++l) {
            this.cs[l] = this.a(this.cC[l], super.bI);
        }
    }
    
    protected float null(final int n) {
        return this.b() / (n + (this.null().y().a() ? super.parent.O() : 0));
    }
    
    protected float l() {
        return this.n();
    }
    
    protected float n() {
        float n = this.c(0);
        for (int i = 1; i < this.cz.length; ++i) {
            n = Math.min(n, this.c(i));
        }
        return n;
    }
    
    protected float c(final int n) {
        final int max = Math.max(this.goto(n), this.null().u());
        final int l = this.null().l();
        if (max >= this.cz[n].length) {
            return 0.0f;
        }
        float min = this.cz[n][max];
        for (int i = max + 1; i <= l; ++i) {
            min = Math.min(min, this.cz[n][i]);
        }
        return min;
    }
    
    protected float q() {
        return this.p();
    }
    
    protected float p() {
        float n = this.b(0);
        for (int i = 1; i < this.cz.length; ++i) {
            n = Math.max(n, this.b(i));
        }
        return n;
    }
    
    protected float b(final int n) {
        final int max = Math.max(this.goto(n), this.null().u());
        final int l = this.null().l();
        if (max >= this.cz[n].length) {
            return 1.0f;
        }
        float max2 = this.cz[n][max];
        for (int i = max + 1; i <= l; ++i) {
            max2 = Math.max(max2, this.cz[n][i]);
        }
        return max2;
    }
    
    protected float o() {
        return (this.char() - this.byte().bottom - this.byte().top) / (super.bF - super.bC);
    }
    
    protected int a(final float n, final int n2) {
        return Math.round((n2 - this.null().u()) * n);
    }
    
    protected int a(final float n, final int n2, final int n3) {
        float n4 = this.cz[n3][n2];
        if (n2 < this.goto(n3)) {
            n4 = 0.0f;
        }
        return Math.round(this.char() - this.byte().bottom - (n4 - super.bC) * n);
    }
    
    protected int a(final float n, final float n2) {
        return Math.round(this.char() - this.byte().bottom - (n - super.bC) * n2);
    }
    
    protected void t() {
        int n = 0;
        boolean b = true;
        for (float n2 = this.char() / super.bK.gw, n3 = 0.0f; n3 < this.char(); n3 += n2) {
            if (b) {
                this.else().setColor(super.bK.gG);
            }
            else {
                this.else().setColor(super.bK.gE);
            }
            b = !b;
            final int round = Math.round(n3 + n2);
            this.else().fillRect(0, n, this.b(), round - n + 1);
            n = round;
        }
    }
    
    protected void k() {
        for (int i = 0; i < this.e(); ++i) {
            this.else().setColor(this.void(i));
            final int else1 = this.else(i);
            final int r = this.r();
            if (this.cv != null) {
                if (else1 < this.cv.length) {
                    int n = this.cv[else1];
                    int n2 = this.cy[i][else1];
                    for (int j = else1 + 1; j <= r; ++j) {
                        try {
                            final int n3 = this.cv[j];
                            final int n4 = this.cy[i][j];
                            this.else().drawLine(n, n2, n3, n4);
                            n = n3;
                            n2 = n4;
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {}
                    }
                    this.a(this.else(), this.cv[0], this.b(), n2, super.bK.g1);
                }
            }
        }
        for (int k = 0; k < c.a(this.cC); ++k) {
            if (this.cC[k] != Float.MIN_VALUE) {
                this.a(this.else(), this.cv[0], this.b(), this.cs[k], super.bK.gS);
            }
        }
    }
    
    protected int else(final int n) {
        return Math.max(0, this.goto(n) - this.null().u());
    }
    
    protected int r() {
        return this.null().y().new() - 1;
    }
    
    public int goto(final int n) {
        return 0;
    }
    
    protected Color void(final int n) {
        return super.bK.g3;
    }
    
    protected void s() {
        final Font font = this.else().getFont();
        this.else().setColor(super.bK.g3);
        this.else().setFont(this.cD);
        final FontMetrics fontMetrics = this.else().getFontMetrics();
        final int n = 5;
        final int n2 = 5;
        final int stringWidth = fontMetrics.stringWidth(this.g());
        final int height = fontMetrics.getHeight();
        this.else().drawString(this.g(), n, height);
        if (this.e() > 1) {
            this.else().setFont(this.ct);
            final FontMetrics fontMetrics2 = this.else().getFontMetrics();
            int n3 = n + (stringWidth + n2);
            final int n4 = (height + fontMetrics2.getHeight()) / 2;
            for (int i = 0; i < this.cy.length; ++i) {
                final String d = this.d(i);
                if (!c.a(d)) {
                    this.else().setColor(this.void(i));
                    this.else().drawString(d, n3, n4);
                    n3 += fontMetrics2.stringWidth(d) + n2;
                }
            }
        }
        this.else().setFont(font);
    }
    
    protected String d(final int n) {
        return null;
    }
    
    static {
        cB = new Insets(6, 0, 8, 0);
        cA = new Insets(1, 15, 1, be.ea);
    }
    
    class a extends MouseAdapter implements MouseMotionListener
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (System.currentTimeMillis() - Analyse.this.cq > 20L) {
                Analyse.this.bR.setLocation(mouseEvent.getX(), mouseEvent.getY());
                Analyse.this.cq = System.currentTimeMillis();
                Analyse.this.parent.a((av)mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY(), false);
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            Analyse.this.bL = true;
            Analyse.this.parent.a((av)mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY(), false);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            Analyse.this.bL = false;
            Analyse.this.parent.a((av)mouseEvent.getSource(), -1, -1, false);
        }
    }
}
