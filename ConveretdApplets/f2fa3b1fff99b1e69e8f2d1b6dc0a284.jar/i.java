import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends n
{
    private DCQuoteTable D;
    private int E;
    
    public i(final DCQuoteTable d) {
        this.E = 0;
        this.D = d;
    }
    
    public void a(int n, final int e, final int n2, final int n3) {
        int n4 = 1;
        this.E = e;
        final int a = f.a();
        super.a.setClip(0, 0, e + 10, this.D.I + a);
        super.a.setColor(this.D.t[1]);
        super.a.fillRect(0, this.D.I, e + 10, a);
        if (this.D.Z) {
            ++n;
        }
        for (int i = 0; i < this.D.P.length; ++i) {
            this.a(i, n);
        }
        final int n5 = 0;
        super.a.setColor(this.D.t[1 - n5]);
        if (this.D.Z) {
            super.a.fillRect(1, 0, this.D.goto, this.D.I);
            ++n4;
            super.a.setColor(this.D.t[2]);
            super.a.drawRect(0, 0, e - 1, this.D.I);
        }
        else {
            super.a.fillRect(0, 0, this.D.goto, this.D.I);
        }
        super.a.setColor(this.D.t[0]);
        if (n2 > 0) {
            super.a.drawLine(n2, 0, n2, this.D.I + a);
        }
        super.a.setPaintMode();
        if (this.D.case.void) {
            int goto1 = this.D.goto;
            for (int j = 0; j < this.D.P.length; ++j) {
                super.a.drawLine(goto1, 0, goto1, this.D.I + a);
                goto1 += this.D.P[j].case();
            }
        }
        super.a.setClip(0, 0, this.D.goto, this.D.I);
        this.a(super.a, this.D.D, n4, this.D.N, this.D.t[n5], this.D.A);
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final Dimension dimension) {
        graphics.drawImage(super.int, super.for.x + n, super.for.y + n2, null);
    }
    
    public int if() {
        if (this.D.getSize().width > 0) {
            this.E = this.D.getSize().width;
        }
        return this.E;
    }
    
    public int for() {
        return this.D.I;
    }
    
    public void a(final Point for1) {
        super.for = for1;
        super.int = this.D.createImage(this.D.F, this.D.I + f.a());
        super.a = super.int.getGraphics();
    }
    
    public void a(final int n, final int n2) {
        final int n3 = 0;
        super.a.setColor(this.D.t[1 - n3]);
        final b b = this.D.P[n];
        int case1 = b.case();
        if (n == this.D.P.length - 1) {
            case1 = this.D.F - n2;
        }
        final String do1 = b.do();
        final int if1 = b.if();
        super.a.fillRect(b.new + n2, 0, case1, this.D.I);
        int stringWidth = this.D.u.stringWidth(do1);
        if (this.D.A.isItalic()) {
            stringWidth += Math.max(this.D.u.getMaxAdvance() / 4, 2);
        }
        if (b.a() != 0) {
            try {
                stringWidth += Math.max(k.g.getWidth(null), k.byte.getWidth(null)) + 2;
            }
            catch (Exception ex) {}
        }
        int n4 = b.new + n2;
        switch (if1) {
            case -1: {
                n4 += 2;
                break;
            }
            case 0: {
                n4 += (b.case() - stringWidth) / 2;
                break;
            }
            case 1: {
                n4 += b.case() - stringWidth - 2;
                break;
            }
        }
        this.a(super.a, do1, n4, this.D.N, this.D.t[n3], this.D.A);
    }
}
