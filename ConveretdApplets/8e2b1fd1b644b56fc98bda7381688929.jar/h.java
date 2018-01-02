import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends l
{
    private int Ssa;
    private int Tsa;
    private int Usa;
    private boolean Vsa;
    private boolean Wsa;
    private boolean Xsa;
    private int Ysa;
    
    public h() {
        this.Ysa = 1;
        super.Kra = 1;
        this.Vsa = false;
        this.Wsa = false;
        this.Xsa = false;
        super.t.J(true);
    }
    
    public void S(final int tsa) {
        this.Tsa = tsa;
        this.Vsa = true;
    }
    
    public void T(final int usa) {
        this.Usa = usa;
        this.Wsa = true;
    }
    
    public void U(final int ssa) {
        this.Ssa = ssa;
        this.Xsa = true;
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
        this.Ssa = this.g(graphics);
        this.Tsa = this.h(graphics);
        this.Usa = this.i(graphics);
        if (this.Tsa + super.gsa < this.Usa - super.esa) {
            if (super.Nra instanceof u) {
                super.Ora._(super.Pra, super.Qra);
                super.Nra.a(super.Pra, super.Qra);
                super.Nra.b(this.Tsa + super.gsa + Math.abs(this.Usa - this.Tsa) * ((super.gsa == 0) ? super.fsa : 0) / 100.0, this.Usa - super.esa - Math.abs(this.Usa - this.Tsa) * ((super.esa == 0) ? super.dsa : 0) / 100.0);
            }
            else {
                final double j = this.j();
                final double a = this._a();
                super.Ora._(j, a);
                super.Nra.a(j, a);
                super.Nra.b(this.Tsa + super.gsa, this.Usa - super.esa);
            }
            this.b(graphics);
            this.e(graphics);
            b = true;
        }
        this.Z();
        return b;
    }
    
    public void Z() {
        this.Vsa = false;
        this.Wsa = false;
        this.Xsa = false;
    }
    
    public int ha() {
        return this.Ssa;
    }
    
    public int Z() {
        return this.Tsa;
    }
    
    public int _a() {
        return this.Usa;
    }
    
    public int j(final Graphics graphics) {
        super.t.setText("Test[Xy]");
        if (super.Ora instanceof finally && Math.abs(super.Qra - super.Pra) <= 2.0) {
            super.t.setText(this.i(123.456));
        }
        switch (super.Kra) {
            case 0: {
                this.Ssa = super.bounds.y;
                if (super.Vra) {
                    this.Ssa += super.Tra;
                }
                if (super.Ura) {
                    this.Ssa += super.t._(graphics);
                    break;
                }
                break;
            }
            case 1: {
                this.Ssa = super.bounds.y + super.bounds.height;
                if (super.Vra) {
                    this.Ssa -= super.Tra;
                }
                if (super.Ura) {
                    this.Ssa -= super.t._(graphics);
                    break;
                }
                break;
            }
        }
        this.Xsa = true;
        return this.Ssa;
    }
    
    public int g(final Graphics graphics) {
        if (this.Xsa) {
            return this.Ssa;
        }
        return this.j(graphics);
    }
    
    private int k(final Graphics graphics) {
        final int n = super.bounds.x + 1;
        this.Vsa = true;
        return n;
    }
    
    private int h(final Graphics graphics) {
        if (this.Vsa) {
            return this.Tsa;
        }
        return this.k(graphics);
    }
    
    private int l(final Graphics graphics) {
        final int n = super.bounds.x + super.bounds.width - 1;
        this.Wsa = true;
        return n;
    }
    
    private int i(final Graphics graphics) {
        if (this.Wsa) {
            return this.Usa;
        }
        return this.l(graphics);
    }
    
    private void b(final Graphics graphics) {
        graphics.setColor(super.T);
        graphics.drawLine(this.Tsa, this.Ssa, this.Usa, this.Ssa);
    }
    
    private double a(final double[] array) {
        if (array.length < 3) {
            return 0.0;
        }
        double n = 0.0;
        for (int i = 0; i < array.length - 1; ++i) {
            n += Math.abs(super.Nra.b(array[i + 1]) - super.Nra.b(array[i]));
        }
        final double n2 = n / (array.length - 1);
        if (n2 == 0.0) {
            return 0.0;
        }
        double n3 = 0.0;
        for (int j = 0; j < array.length - 1; ++j) {
            n3 += Math.abs(n2 - Math.abs(super.Nra.b(array[j + 1]) - super.Nra.b(array[j]))) / n2;
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
            super.t.setText(this.i(n5));
            final int n6 = (int)super.Nra.b(n5);
            final int b = super.t.b(graphics);
            if (n6 >= this.Tsa && n6 <= this.Usa) {
                if (n2 != 0) {
                    if (n6 - b / 2 >= super.bounds.x && n6 + b / 2 <= super.bounds.x + super.bounds.width) {
                        ++n;
                        n3 = n6;
                        n4 = b;
                    }
                    n2 = 0;
                }
                else if (((n3 < n6 && n3 + n4 / 2.0 + this.Ysa <= n6 - b / 2.0) || (n3 > n6 && n3 - n4 / 2.0 - this.Ysa >= n6 + b / 2.0)) && n6 - b / 2 >= super.bounds.x && n6 + b / 2 <= super.bounds.x + super.bounds.width) {
                    ++n;
                    n3 = n6;
                    n4 = b;
                }
            }
        }
        return n;
    }
    
    private void e(final Graphics graphics) {
        super.t.setText(this.i(super.Pra));
        final int b = super.t.b(graphics);
        super.t.setText(this.i(super.Qra));
        final int max = Math.max(Math.min((int)Math.floor((Math.max(this.Usa - super.esa - (this.Tsa + super.gsa), 0) - (this.Usa - this.Tsa) * (((super.esa == 0) ? super.dsa : 0) + ((super.gsa == 0) ? super.fsa : 0)) / 100.0) / (Math.max(b, super.t.b(graphics)) + this.Ysa / 2.0)), super.Bqa), 1);
        if (super.Ora instanceof finally) {
            double[] x = null;
            if (super.Nra instanceof u) {
                x = ((u)super.Nra).X();
            }
            super.ksa._(((finally)super.Ora)._(x));
        }
        super.Ora.D(max);
        super.t.K(1);
        switch (super.Kra) {
            case 0: {
                super.t.L(4);
                break;
            }
            case 1: {
                super.t.L(3);
                break;
            }
        }
        int n = 0;
        int n2 = 0;
        final Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds == null) {
            return;
        }
        double[] array = new double[super.Ora.R()];
        for (int i = 0; i < super.Ora.R(); ++i) {
            array[i] = super.Ora._(i);
        }
        final double a = this.a(array);
        final int _ = this._(graphics, array);
        if (super.Nra instanceof u && (((a > 0.55 || _ < 2 || _ * 2 <= array.length) && (!(super.Ora instanceof finally) || Math.abs(super.Qra - super.Pra) < 2.0)) || (super.Ora instanceof finally && Math.abs(super.Qra - super.Pra) < 2.0 && _ < 3))) {
            final double[] x2 = ((u)super.Nra).X();
            if (x2 != null && x2.length > 0) {
                final double[] array2 = new double[Math.min(max, x2.length)];
                int n3 = x2.length / array2.length;
                int n4 = Math.max(Math.round(x2.length / array2.length), 1);
                int n5 = 0;
                if (array2.length == 1) {
                    n3 = x2.length / 2;
                    n4 = x2.length;
                }
                for (int n6 = n3, n7 = 0; n7 < array2.length && n6 < x2.length; n6 += n4, n5 = n7, ++n7) {
                    array2[n7] = x2[n6];
                }
                array = new double[n5 + 1];
                System.arraycopy(array2, 0, array, 0, n5 + 1);
            }
        }
        int n8 = 1;
        for (int j = 0; j < array.length; ++j) {
            final double n9 = array[j];
            super.t.setText(this.i(n9));
            final int n10 = (int)super.Nra.b(n9);
            final int b2 = super.t.b(graphics);
            if (n10 >= this.Tsa && n10 <= this.Usa) {
                if (n8 != 0) {
                    if (super.osa && super.lsa != null && n10 != this.Z() && n10 != this._a()) {
                        graphics.setColor(super.lsa);
                        graphics.drawLine(n10, super.msa, n10, super.nsa);
                    }
                    if (n10 - b2 / 2 >= clipBounds.x && n10 + b2 / 2 <= clipBounds.x + clipBounds.width) {
                        this.a(graphics, n10);
                        this._(graphics, n10);
                        n = n10;
                        n2 = b2;
                    }
                    n8 = 0;
                }
                else if (((n < n10 && n + n2 / 2.0 + this.Ysa <= n10 - b2 / 2.0) || (n > n10 && n - n2 / 2.0 - this.Ysa >= n10 + b2 / 2.0)) && n10 - b2 / 2 >= clipBounds.x && n10 + b2 / 2 <= clipBounds.x + clipBounds.width) {
                    if (super.osa && super.lsa != null && n10 != this.Z() && n10 != this._a()) {
                        graphics.setColor(super.lsa);
                        graphics.drawLine(n10, super.msa, n10, super.nsa);
                    }
                    this._(graphics, n10);
                    this.a(graphics, n10);
                    n = n10;
                    n2 = b2;
                }
            }
        }
    }
    
    private void _(final Graphics graphics, final int n) {
        if (!super.Vra) {
            return;
        }
        graphics.setColor(super.T);
        switch (super.Kra) {
            case 0: {
                graphics.drawLine(n, this.Ssa, n, this.Ssa - super.Tra);
                break;
            }
            case 1: {
                graphics.drawLine(n, this.Ssa, n, this.Ssa + super.Tra);
                break;
            }
        }
    }
    
    private void a(final Graphics graphics, final int n) {
        if (!super.Ura) {
            return;
        }
        switch (super.Kra) {
            case 0: {
                super.t.a(graphics, n, this.Ssa - super.Tra);
                break;
            }
            case 1: {
                super.t.a(graphics, n, this.Ssa + super.Tra);
                break;
            }
        }
    }
}
