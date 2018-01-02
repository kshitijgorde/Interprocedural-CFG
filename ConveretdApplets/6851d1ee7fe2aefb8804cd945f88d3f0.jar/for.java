import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class for extends finally
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private int Ama;
    private int Bma;
    private int Cma;
    private boolean Dma;
    private boolean Ema;
    private boolean Fma;
    
    public for(final int n) {
        if (n == 0) {
            super.Gma = 0;
        }
        else {
            super.Gma = 1;
        }
        this.Dma = false;
        this.Ema = false;
        this.Fma = false;
    }
    
    public void j(double n, double n2) {
        if (Math.abs(n2 - n) < 1.0E-6) {
            if (n == 0.0 || n == -0.0) {
                n = 0.0;
                n2 = 1.0;
            }
            else {
                if (n > 0.1) {
                    n -= 0.1;
                }
                else {
                    n -= 0.01;
                }
                n2 += 0.1;
            }
        }
        if (super.Hma instanceof d) {
            if (n <= 1.0E-6) {
                n = 1.0E-6;
            }
            if (n2 <= 1.0E-6) {
                n = 2.0E-6;
            }
        }
        super.Ima = Math.min(n, n2);
        super.Jma = Math.max(n, n2);
        if (super.Kma && super.Lma == 0) {
            this.t();
        }
    }
    
    public void y(final int bma) {
        this.Bma = bma;
        this.Dma = true;
    }
    
    public void z(final int cma) {
        this.Cma = cma;
        this.Ema = true;
    }
    
    public void A(final int ama) {
        this.Ama = ama;
        this.Fma = true;
    }
    
    public boolean a(final Graphics graphics) {
        boolean b = false;
        super.bounds = graphics.getClipBounds();
        if (super.bounds == null) {
            super.bounds = new Rectangle(0, 0, 1, 1);
        }
        final Rectangle bounds = super.bounds;
        --bounds.height;
        final Rectangle bounds2 = super.bounds;
        --bounds2.width;
        this.Ama = this.a(graphics);
        this.Bma = this.g(graphics);
        this.Cma = this.h(graphics);
        if (this.Bma + super.Mma < this.Cma - super.Nma) {
            final double g = this.g();
            final double h = this.h();
            super.Oma.j(g, h);
            super.Hma.b(g, h);
            super.Hma._(this.Cma - super.Nma, this.Bma + super.Mma);
            this.b(graphics);
            this.n(graphics);
            b = true;
        }
        this.u();
        return b;
    }
    
    public void u() {
        this.Dma = false;
        this.Ema = false;
        this.Fma = false;
    }
    
    public int D() {
        return this.Ama;
    }
    
    public int E() {
        return this.Bma;
    }
    
    public int F() {
        return this.Cma;
    }
    
    public int i(final Graphics graphics) {
        super.Pma.setText(this._(super.Ima));
        final int b = super.Pma.b(graphics);
        super.Pma.setText(this._(super.Jma));
        final int max = Math.max(b, super.Pma.b(graphics));
        switch (super.Gma) {
            case 0: {
                this.Ama = super.bounds.x;
                if (super.Qma) {
                    this.Ama += super.Rma;
                }
                if (super.Sma) {
                    this.Ama += max;
                }
                this.Ama += 4;
                break;
            }
            case 1: {
                this.Ama = super.bounds.x + super.bounds.width;
                if (super.Qma) {
                    this.Ama -= super.Rma;
                }
                if (super.Sma) {
                    this.Ama -= max;
                }
                this.Ama -= 4;
                break;
            }
        }
        this.Fma = true;
        return this.Ama;
    }
    
    public int a(final Graphics graphics) {
        if (this.Fma) {
            return this.Ama;
        }
        return this.i(graphics);
    }
    
    private int j(final Graphics graphics) {
        final int y = super.bounds.y;
        this.Dma = true;
        return y;
    }
    
    private int g(final Graphics graphics) {
        if (this.Dma) {
            return this.Bma;
        }
        return this.j(graphics);
    }
    
    private int k(final Graphics graphics) {
        final int n = super.bounds.y + super.bounds.height - 1;
        this.Ema = true;
        return n;
    }
    
    private int h(final Graphics graphics) {
        if (this.Ema) {
            return this.Cma;
        }
        return this.k(graphics);
    }
    
    private void b(final Graphics graphics) {
        graphics.setColor(super.xa);
        graphics.drawLine(this.Ama, this.Bma, this.Ama, this.Cma);
    }
    
    private void n(final Graphics graphics) {
        super.Pma.setText(this._(super.Ima));
        int _ = super.Pma._(graphics);
        int n = 0;
        if (!super.Pma.g()) {
            --n;
            if (!super.Pma.P()) {
                n -= 2;
            }
        }
        if (_ + n > 0) {
            _ += n;
        }
        super.Oma.r(Math.min(Math.max(Math.round(Math.max(1 + (this.Cma - super.Nma) - (this.Bma + super.Mma), 0) / _), 1), super.Ika));
        super.Pma.j(1);
        switch (super.Gma) {
            case 0: {
                super.Pma.i(2);
                break;
            }
            case 1: {
                super.Pma.i(0);
                break;
            }
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        for (int i = 0; i < super.Oma.ia(); ++i) {
            final double a = super.Oma.a(i);
            super.Pma.setText(this._(a));
            final int n5 = (int)super.Hma.b(a);
            final int _2 = super.Pma._(graphics);
            if (n5 + _2 / 2 + n <= this.Cma && n5 - _2 / 2 - n > this.Bma) {
                if (n4 != 0) {
                    this.a(graphics, n5);
                    this.b(graphics, n5);
                    n2 = n5;
                    n3 = _2;
                    n4 = 0;
                    if (super.Tma && super.Uma != null && n5 != this.E() && n5 != this.F()) {
                        graphics.setColor(super.Uma);
                        graphics.drawLine(super.Vma, n5, super.Wma, n5);
                    }
                }
                else if ((n2 < n5 && n2 + n3 / 2.0 + n < n5 - _2 / 2.0) || (n2 > n5 && n2 - n3 / 2.0 - n > n5 + _2 / 2.0)) {
                    this.a(graphics, n5);
                    this.b(graphics, n5);
                    n2 = n5;
                    n3 = _2;
                    if (super.Tma && super.Uma != null && n5 != this.E() && n5 != this.F()) {
                        graphics.setColor(super.Uma);
                        graphics.drawLine(super.Vma, n5, super.Wma, n5);
                    }
                }
            }
        }
    }
    
    private void a(final Graphics graphics, final int n) {
        if (!super.Qma) {
            return;
        }
        graphics.setColor(super.xa);
        switch (super.Gma) {
            case 0: {
                graphics.drawLine(this.Ama - super.Rma, n, this.Ama, n);
                break;
            }
            case 1: {
                graphics.drawLine(this.Ama, n, this.Ama + super.Rma, n);
                break;
            }
        }
    }
    
    private void b(final Graphics graphics, final int n) {
        if (!super.Sma) {
            return;
        }
        switch (super.Gma) {
            case 0: {
                super.Pma._(graphics, this.Ama - super.Rma, n);
                break;
            }
            case 1: {
                super.Pma._(graphics, this.Ama + super.Rma + 1, n);
                break;
            }
        }
    }
}
