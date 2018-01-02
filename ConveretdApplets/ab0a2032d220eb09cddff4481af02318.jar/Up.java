import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Up extends Zp
{
    private int lDb;
    private int mDb;
    private int nDb;
    private boolean oDb;
    private boolean pDb;
    private boolean qDb;
    private int rDb;
    
    public Up() {
        this.rDb = 1;
        super.s = 1;
        this.oDb = false;
        this.pDb = false;
        this.qDb = false;
        super.x.c(true);
    }
    
    public void H(final int mDb) {
        this.mDb = mDb;
        this.oDb = true;
    }
    
    public void I(final int nDb) {
        this.nDb = nDb;
        this.pDb = true;
    }
    
    public void J(final int lDb) {
        this.lDb = lDb;
        this.qDb = true;
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
        this.lDb = this.j(graphics);
        this.mDb = this.k(graphics);
        this.nDb = this.l(graphics);
        if (this.mDb + super.Q < this.nDb - super.O) {
            if (super.v instanceof jq) {
                super.w._(super.y, super.z);
                super.v.a(super.y, super.z);
                super.v.b(this.mDb + super.Q + Math.abs(this.nDb - this.mDb) * ((super.Q == 0) ? super.P : 0) / 100.0, this.nDb - super.O - Math.abs(this.nDb - this.mDb) * ((super.O == 0) ? super.N : 0) / 100.0);
            }
            else {
                final double k = this.k();
                final double h = this.H();
                super.w._(k, h);
                super.v.a(k, h);
                super.v.b(this.mDb + super.Q, this.nDb - super.O);
            }
            this._(graphics);
            this.a(graphics);
            b = true;
        }
        this.R();
        return b;
    }
    
    public void R() {
        this.oDb = false;
        this.pDb = false;
        this.qDb = false;
    }
    
    public int D() {
        return this.lDb;
    }
    
    public int j() {
        return this.mDb;
    }
    
    public int u() {
        return this.nDb;
    }
    
    public int m(final Graphics graphics) {
        super.x.setText("Test[Xy]");
        if (super.w instanceof rq && Math.abs(super.z - super.y) <= 2.0) {
            super.x.setText(this.e(123.456));
        }
        switch (super.s) {
            case 0: {
                this.lDb = super.bounds.y;
                if (super.E) {
                    this.lDb += super.C;
                }
                if (super.D) {
                    this.lDb += super.x.a(graphics);
                    break;
                }
                break;
            }
            case 1: {
                this.lDb = super.bounds.y + super.bounds.height;
                if (super.E) {
                    this.lDb -= super.C;
                }
                if (super.D) {
                    this.lDb -= super.x.a(graphics);
                    break;
                }
                break;
            }
        }
        this.qDb = true;
        return this.lDb;
    }
    
    public int j(final Graphics graphics) {
        if (this.qDb) {
            return this.lDb;
        }
        return this.m(graphics);
    }
    
    private int n(final Graphics graphics) {
        final int n = super.bounds.x + 1;
        this.oDb = true;
        return n;
    }
    
    private int k(final Graphics graphics) {
        if (this.oDb) {
            return this.mDb;
        }
        return this.n(graphics);
    }
    
    private int c(final Graphics graphics) {
        final int n = super.bounds.x + super.bounds.width - 1;
        this.pDb = true;
        return n;
    }
    
    private int l(final Graphics graphics) {
        if (this.pDb) {
            return this.nDb;
        }
        return this.c(graphics);
    }
    
    private void _(final Graphics graphics) {
        graphics.setColor(super.Ba);
        graphics.drawLine(this.mDb, this.lDb, this.nDb, this.lDb);
    }
    
    private double b(final double[] array) {
        if (array.length < 3) {
            return 0.0;
        }
        double n = 0.0;
        for (int i = 0; i < array.length - 1; ++i) {
            n += Math.abs(super.v.b(array[i + 1]) - super.v.b(array[i]));
        }
        final double n2 = n / (array.length - 1);
        if (n2 == 0.0) {
            return 0.0;
        }
        double n3 = 0.0;
        for (int j = 0; j < array.length - 1; ++j) {
            n3 += Math.abs(n2 - Math.abs(super.v.b(array[j + 1]) - super.v.b(array[j]))) / n2;
        }
        return n3 / (array.length - 1);
    }
    
    private int _(final Graphics graphics, final double[] array) {
        int n = 0;
        int n2 = 1;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < array.length; ++i) {
            final double n5 = array[i];
            super.x.setText(this.e(n5));
            final int n6 = (int)super.v.b(n5);
            final int _ = super.x._(graphics);
            if (n6 >= this.mDb && n6 <= this.nDb) {
                if (n2 != 0) {
                    if (n6 - _ / 2 >= super.bounds.x && n6 + _ / 2 <= super.bounds.x + super.bounds.width) {
                        ++n;
                        n3 = n6;
                        n4 = _;
                    }
                    n2 = 0;
                }
                else if (((n3 < n6 && n3 + n4 / 2.0 + this.rDb <= n6 - _ / 2.0) || (n3 > n6 && n3 - n4 / 2.0 - this.rDb >= n6 + _ / 2.0)) && n6 - _ / 2 >= super.bounds.x && n6 + _ / 2 <= super.bounds.x + super.bounds.width) {
                    ++n;
                    n3 = n6;
                    n4 = _;
                }
            }
        }
        return n;
    }
    
    private void a(final Graphics graphics) {
        super.x.setText(this.e(super.y));
        final int _ = super.x._(graphics);
        super.x.setText(this.e(super.z));
        final int max = Math.max(Math.min((int)Math.floor((Math.max(this.nDb - super.O - (this.mDb + super.Q), 0) - (this.nDb - this.mDb) * (((super.O == 0) ? super.N : 0) + ((super.Q == 0) ? super.P : 0)) / 100.0) / (Math.max(_, super.x._(graphics)) + this.rDb / 2.0)), super.Ta), 1);
        if (super.w instanceof rq) {
            double[] b = null;
            if (super.v instanceof jq) {
                b = ((jq)super.v).b();
            }
            super.U.b(((rq)super.w)._(b));
        }
        super.w._(max);
        super.x.G(1);
        switch (super.s) {
            case 0: {
                super.x.F(4);
                break;
            }
            case 1: {
                super.x.F(3);
                break;
            }
        }
        int n = 0;
        int n2 = 0;
        final Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds == null) {
            return;
        }
        double[] array = new double[super.w.k()];
        for (int i = 0; i < super.w.k(); ++i) {
            array[i] = super.w._(i);
        }
        final double b2 = this.b(array);
        final int _2 = this._(graphics, array);
        if (super.v instanceof jq && (((b2 > 0.55 || _2 < 2 || _2 * 2 <= array.length) && (!(super.w instanceof rq) || Math.abs(super.z - super.y) < 2.0)) || (super.w instanceof rq && Math.abs(super.z - super.y) < 2.0 && _2 < 3))) {
            final double[] b3 = ((jq)super.v).b();
            if (b3 != null && b3.length > 0) {
                final double[] array2 = new double[Math.min(max, b3.length)];
                int n3 = b3.length / array2.length;
                int n4 = Math.max(Math.round(b3.length / array2.length), 1);
                int n5 = 0;
                if (array2.length == 1) {
                    n3 = b3.length / 2;
                    n4 = b3.length;
                }
                for (int n6 = n3, n7 = 0; n7 < array2.length && n6 < b3.length; n6 += n4, n5 = n7, ++n7) {
                    array2[n7] = b3[n6];
                }
                array = new double[n5 + 1];
                System.arraycopy(array2, 0, array, 0, n5 + 1);
            }
        }
        int n8 = 1;
        for (int j = 0; j < array.length; ++j) {
            final double n9 = array[j];
            super.x.setText(this.e(n9));
            final int n10 = (int)super.v.b(n9);
            final int _3 = super.x._(graphics);
            if (n10 >= this.mDb && n10 <= this.nDb) {
                if (n8 != 0) {
                    if (super.Y && super.V != null && n10 != this.j() && n10 != this.u()) {
                        graphics.setColor(super.V);
                        graphics.drawLine(n10, super.W, n10, super.X);
                    }
                    if (n10 - _3 / 2 >= clipBounds.x && n10 + _3 / 2 <= clipBounds.x + clipBounds.width) {
                        this.a(graphics, n10);
                        this._(graphics, n10);
                        n = n10;
                        n2 = _3;
                    }
                    n8 = 0;
                }
                else if (((n < n10 && n + n2 / 2.0 + this.rDb <= n10 - _3 / 2.0) || (n > n10 && n - n2 / 2.0 - this.rDb >= n10 + _3 / 2.0)) && n10 - _3 / 2 >= clipBounds.x && n10 + _3 / 2 <= clipBounds.x + clipBounds.width) {
                    if (super.Y && super.V != null && n10 != this.j() && n10 != this.u()) {
                        graphics.setColor(super.V);
                        graphics.drawLine(n10, super.W, n10, super.X);
                    }
                    this._(graphics, n10);
                    this.a(graphics, n10);
                    n = n10;
                    n2 = _3;
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
                graphics.drawLine(n, this.lDb, n, this.lDb - super.C);
                break;
            }
            case 1: {
                graphics.drawLine(n, this.lDb, n, this.lDb + super.C);
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
                super.x._(graphics, n, this.lDb - super.C);
                break;
            }
            case 1: {
                super.x._(graphics, n, this.lDb + super.C);
                break;
            }
        }
    }
}
