// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;

public abstract class ChartBody implements aq
{
    private DataSource fk;
    protected int fs;
    protected int[] fp;
    float fe;
    float fc;
    float e7;
    float fa;
    int e9;
    int fb;
    int fm;
    int fg;
    int fi;
    int fr;
    int ff;
    int fq;
    boolean fn;
    boolean fl;
    boolean fj;
    Rectangle e6;
    Rectangle fw;
    bi fd;
    boolean e8;
    private boolean fv;
    String fu;
    be fh;
    Color ft;
    static final double fo;
    
    protected ChartBody(final String fu, final be fh, final DataSource dataSource) {
        this.fn = true;
        this.fl = false;
        this.fj = false;
        this.e6 = new Rectangle(0, 0, 0, 0);
        this.fw = new Rectangle(0, 0, 0, 0);
        this.fv = true;
        this.fu = "";
        this.ft = Color.black;
        this.fu = fu;
        this.fh = fh;
        this.new(dataSource);
    }
    
    public void dataSourceChanged(final a2 a2) {
        this.try(true);
        if (!this.fh.try()) {
            this.fh.a(true);
            this.fh.repaint();
        }
    }
    
    public void if(final boolean b, final boolean b2) {
    }
    
    public void a(final int e9, final int fb, final int fm, final int fg, final int fi, final int fr) {
        this.e9 = e9;
        this.fm = fm;
        this.fb = fb;
        this.fg = fg;
        this.fi = fi;
        this.fr = fr;
    }
    
    public void a(final Rectangle e6, final Rectangle fw) {
        this.e6 = e6;
        this.fw = fw;
    }
    
    public void do(final int ff, final int fq) {
        final boolean b = ff != this.ff;
        final boolean b2 = fq != this.fq;
        this.ff = ff;
        this.fq = fq;
        if (this.fh.parent.eE && b) {
            int n;
            if (this.al().C().do()) {
                n = this.t(ff);
            }
            else {
                n = this.r(ff);
            }
            this.al().for(n - this.fh.parent.O());
        }
        this.try(this.ak() || b || b2);
    }
    
    private final int t(final int n) {
        return n;
    }
    
    protected int r(final int n) {
        return n / 3;
    }
    
    public void new(final boolean e8) {
        this.e8 = e8;
    }
    
    public void try(final DataSource dataSource) {
        this.new(dataSource);
    }
    
    public abstract bk ag();
    
    protected static int if(final int n, final int n2, final int n3, final int n4) {
        final int abs = Math.abs(n - n3);
        final int abs2 = Math.abs(n2 - n4);
        return abs * abs + abs2 * abs2;
    }
    
    public int q(final int n) {
        return 0;
    }
    
    public int a(final int n, final Point point, final int n2) {
        return 0;
    }
    
    public int ad() {
        if (this.al() != null) {
            return this.al().l() - this.al().u();
        }
        return 0;
    }
    
    public float if(final int n, final int n2) {
        return 0.0f;
    }
    
    public float p(final int n) {
        return 0.0f;
    }
    
    public float s(final int n) {
        return 0.0f;
    }
    
    public int u(final int n) {
        return 0;
    }
    
    public void for(final Graphics graphics, final int n) {
    }
    
    void if(final bi fd) {
        this.fd = fd;
    }
    
    public void a(final Color ft) {
        this.ft = ft;
    }
    
    public void if(final Graphics graphics, final int n, final int n2, final int n3, final Color color) {
        final int n4 = 10;
        final Color color2 = graphics.getColor();
        graphics.setColor(color);
        for (int i = n; i < n2; i += n4) {
            graphics.drawLine(i, n3, i + n4 / 2, n3);
        }
        graphics.setColor(color2);
    }
    
    public float ai() {
        return 1.0f;
    }
    
    public float ab() {
        return 0.0f;
    }
    
    public float ah() {
        return 0.0f;
    }
    
    public float am() {
        return this.ff / (this.al().y().new() + (this.al().y().a() ? this.fh.parent.O() : 0));
    }
    
    public void try(final boolean fv) {
        this.fv = fv;
    }
    
    public boolean ak() {
        return this.fv;
    }
    
    public Vector ac() {
        return this.al().b6;
    }
    
    public void new(final DataSource fk) {
        if (this.fk != null) {
            this.fk.a(this);
        }
        this.fk = fk;
        if (this.fk != null) {
            this.fk.if(this);
        }
    }
    
    public DataSource al() {
        return this.fk;
    }
    
    public void a(final Graphics graphics, final boolean b) {
        if (!this.ak() && !this.fh.try()) {
            return;
        }
        this.aj();
        if (b && this.fh.C()) {
            this.f(graphics);
        }
        this.c(graphics);
        if (b) {
            this.d(graphics);
            this.e(graphics);
            this.fh.a(false);
        }
        this.try(false);
    }
    
    protected abstract void aj();
    
    protected abstract void c(final Graphics p0);
    
    public void d(final Graphics graphics) {
        final int af = this.af();
        if (af != Integer.MIN_VALUE) {
            this.if(graphics, this.fp[0], this.ff, af, this.fd.g1);
        }
    }
    
    protected void f(final Graphics graphics) {
        graphics.setColor(this.fd.gN.darker());
        final int ae = this.ae();
        final int round = Math.round(ae * this.fe);
        for (int i = this.fs - 1; i >= 0; i -= ae) {
            graphics.drawLine(this.fp[i], 0, this.fp[i], this.fq);
        }
        for (int j = this.fp[0] - round; j >= 0; j -= round) {
            graphics.drawLine(j, 0, j, this.fq);
        }
        for (int k = this.fp[this.fs - 1] + round; k < this.ff; k += round) {
            graphics.drawLine(k, 0, k, this.fq);
        }
        for (int l = this.fq - round; l >= this.fr; l -= round) {
            graphics.drawLine(0, l, this.ff, l);
        }
    }
    
    protected int ae() {
        return 15;
    }
    
    protected abstract int af();
    
    protected int if(final int[] array) {
        for (int i = this.fs - 1; i >= 0; --i) {
            if (array[i] != Integer.MIN_VALUE) {
                return array[i];
            }
        }
        return Integer.MIN_VALUE;
    }
    
    protected void e(final Graphics graphics) {
        final Font font = graphics.getFont();
        graphics.setFont(new Font("Arial", 0, 16));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (this.al().y().do()) {
            graphics.drawString("Zoom", 5, this.fq - fontMetrics.getHeight());
        }
        graphics.setFont(font);
    }
    
    static {
        fo = Math.log(10.0);
    }
}
