import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Vp extends Zp
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private int fDb;
    private int gDb;
    private int hDb;
    private boolean iDb;
    private boolean jDb;
    private boolean kDb;
    
    public Vp(final int n) {
        if (n == 0) {
            super.s = 0;
        }
        else {
            super.s = 1;
        }
        this.iDb = false;
        this.jDb = false;
        this.kDb = false;
    }
    
    public void _(double n, double n2) {
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
        if (super.v instanceof lq) {
            if (n <= 1.0E-6) {
                n = 1.0E-6;
            }
            if (n2 <= 1.0E-6) {
                n = 2.0E-6;
            }
        }
        super.y = Math.min(n, n2);
        super.z = Math.max(n, n2);
        if (super.B && super.F == 0) {
            this.Q();
        }
    }
    
    public void C(final int gDb) {
        this.gDb = gDb;
        this.iDb = true;
    }
    
    public void D(final int hDb) {
        this.hDb = hDb;
        this.jDb = true;
    }
    
    public void E(final int fDb) {
        this.fDb = fDb;
        this.kDb = true;
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
        this.fDb = this.b(graphics);
        this.gDb = this.e(graphics);
        this.hDb = this.f(graphics);
        if (this.gDb + super.O < this.hDb - super.Q) {
            final double k = this.k();
            final double h = this.H();
            super.w._(k, h);
            super.v.a(k, h);
            super.v.b(this.hDb - super.Q, this.gDb + super.O);
            this._(graphics);
            this.a(graphics);
            b = true;
        }
        this.R();
        return b;
    }
    
    public void R() {
        this.iDb = false;
        this.jDb = false;
        this.kDb = false;
    }
    
    public int A() {
        return this.fDb;
    }
    
    public int B() {
        return this.gDb;
    }
    
    public int C() {
        return this.hDb;
    }
    
    public int g(final Graphics graphics) {
        super.x.setText(this.e(super.y));
        final int _ = super.x._(graphics);
        super.x.setText(this.e(super.z));
        final int max = Math.max(_, super.x._(graphics));
        switch (super.s) {
            case 0: {
                this.fDb = super.bounds.x;
                if (super.E) {
                    this.fDb += super.C;
                }
                if (super.D) {
                    this.fDb += max;
                }
                this.fDb += 4;
                break;
            }
            case 1: {
                this.fDb = super.bounds.x + super.bounds.width;
                if (super.E) {
                    this.fDb -= super.C;
                }
                if (super.D) {
                    this.fDb -= max;
                }
                this.fDb -= 4;
                break;
            }
        }
        this.kDb = true;
        return this.fDb;
    }
    
    public int b(final Graphics graphics) {
        if (this.kDb) {
            return this.fDb;
        }
        return this.g(graphics);
    }
    
    private int h(final Graphics graphics) {
        final int y = super.bounds.y;
        this.iDb = true;
        return y;
    }
    
    private int e(final Graphics graphics) {
        if (this.iDb) {
            return this.gDb;
        }
        return this.h(graphics);
    }
    
    private int i(final Graphics graphics) {
        final int n = super.bounds.y + super.bounds.height - 1;
        this.jDb = true;
        return n;
    }
    
    private int f(final Graphics graphics) {
        if (this.jDb) {
            return this.hDb;
        }
        return this.i(graphics);
    }
    
    private void _(final Graphics graphics) {
        graphics.setColor(super.Ba);
        graphics.drawLine(this.fDb, this.gDb, this.fDb, this.hDb);
    }
    
    private void a(final Graphics graphics) {
        super.x.setText(this.e(super.y));
        int a = super.x.a(graphics);
        int n = 0;
        if (!super.x.a()) {
            --n;
            if (!super.x.b()) {
                n -= 2;
            }
        }
        if (a + n > 0) {
            a += n;
        }
        super.w._(Math.min(Math.max(Math.round(Math.max(1 + (this.hDb - super.Q) - (this.gDb + super.O), 0) / a), 1), super.Ta));
        super.x.F(1);
        switch (super.s) {
            case 0: {
                super.x.G(2);
                break;
            }
            case 1: {
                super.x.G(0);
                break;
            }
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        for (int i = 0; i < super.w.k(); ++i) {
            final double _ = super.w._(i);
            super.x.setText(this.e(_));
            final int n5 = (int)super.v.b(_);
            final int a2 = super.x.a(graphics);
            if (n5 + a2 / 2 + n <= this.hDb && n5 - a2 / 2 - n > this.gDb) {
                if (n4 != 0) {
                    this._(graphics, n5);
                    this.a(graphics, n5);
                    n2 = n5;
                    n3 = a2;
                    n4 = 0;
                    if (super.Y && super.V != null && n5 != this.B() && n5 != this.C()) {
                        graphics.setColor(super.V);
                        graphics.drawLine(super.W, n5, super.X, n5);
                    }
                }
                else if ((n2 < n5 && n2 + n3 / 2.0 + n < n5 - a2 / 2.0) || (n2 > n5 && n2 - n3 / 2.0 - n > n5 + a2 / 2.0)) {
                    this._(graphics, n5);
                    this.a(graphics, n5);
                    n2 = n5;
                    n3 = a2;
                    if (super.Y && super.V != null && n5 != this.B() && n5 != this.C()) {
                        graphics.setColor(super.V);
                        graphics.drawLine(super.W, n5, super.X, n5);
                    }
                }
            }
        }
    }
    
    private void _(final Graphics graphics, final int n) {
        if (!super.E) {
            return;
        }
        graphics.setColor(super.Ba);
        switch (super.s) {
            case 0: {
                graphics.drawLine(this.fDb - super.C, n, this.fDb, n);
                break;
            }
            case 1: {
                graphics.drawLine(this.fDb, n, this.fDb + super.C, n);
                break;
            }
        }
    }
    
    private void a(final Graphics graphics, final int n) {
        if (!super.D) {
            return;
        }
        switch (super.s) {
            case 0: {
                super.x._(graphics, this.fDb - super.C, n);
                break;
            }
            case 1: {
                super.x._(graphics, this.fDb + super.C + 1, n);
                break;
            }
        }
    }
}
