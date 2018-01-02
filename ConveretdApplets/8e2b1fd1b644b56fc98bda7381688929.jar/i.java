import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends l
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private int Msa;
    private int Nsa;
    private int Osa;
    private boolean Psa;
    private boolean Qsa;
    private boolean Rsa;
    
    public i(final int n) {
        if (n == 0) {
            super.Kra = 0;
        }
        else {
            super.Kra = 1;
        }
        this.Psa = false;
        this.Qsa = false;
        this.Rsa = false;
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
        if (super.Nra instanceof abstract) {
            if (n <= 1.0E-6) {
                n = 1.0E-6;
            }
            if (n2 <= 1.0E-6) {
                n = 2.0E-6;
            }
        }
        super.Pra = Math.min(n, n2);
        super.Qra = Math.max(n, n2);
        if (super.Sra && super.Wra == 0) {
            this.Y();
        }
    }
    
    public void P(final int nsa) {
        this.Nsa = nsa;
        this.Psa = true;
    }
    
    public void Q(final int osa) {
        this.Osa = osa;
        this.Qsa = true;
    }
    
    public void R(final int msa) {
        this.Msa = msa;
        this.Rsa = true;
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
        this.Msa = this.a(graphics);
        this.Nsa = this.n(graphics);
        this.Osa = this.c(graphics);
        if (this.Nsa + super.esa < this.Osa - super.gsa) {
            final double j = this.j();
            final double a = this._a();
            super.Ora._(j, a);
            super.Nra.a(j, a);
            super.Nra.b(this.Osa - super.gsa, this.Nsa + super.esa);
            this.b(graphics);
            this.e(graphics);
            b = true;
        }
        this.Z();
        return b;
    }
    
    public void Z() {
        this.Psa = false;
        this.Qsa = false;
        this.Rsa = false;
    }
    
    public int ea() {
        return this.Msa;
    }
    
    public int fa() {
        return this.Nsa;
    }
    
    public int ga() {
        return this.Osa;
    }
    
    public int d(final Graphics graphics) {
        super.t.setText(this.i(super.Pra));
        final int b = super.t.b(graphics);
        super.t.setText(this.i(super.Qra));
        final int max = Math.max(b, super.t.b(graphics));
        switch (super.Kra) {
            case 0: {
                this.Msa = super.bounds.x;
                if (super.Vra) {
                    this.Msa += super.Tra;
                }
                if (super.Ura) {
                    this.Msa += max;
                }
                this.Msa += 4;
                break;
            }
            case 1: {
                this.Msa = super.bounds.x + super.bounds.width;
                if (super.Vra) {
                    this.Msa -= super.Tra;
                }
                if (super.Ura) {
                    this.Msa -= max;
                }
                this.Msa -= 4;
                break;
            }
        }
        this.Rsa = true;
        return this.Msa;
    }
    
    public int a(final Graphics graphics) {
        if (this.Rsa) {
            return this.Msa;
        }
        return this.d(graphics);
    }
    
    private int e(final Graphics graphics) {
        final int y = super.bounds.y;
        this.Psa = true;
        return y;
    }
    
    private int n(final Graphics graphics) {
        if (this.Psa) {
            return this.Nsa;
        }
        return this.e(graphics);
    }
    
    private int f(final Graphics graphics) {
        final int n = super.bounds.y + super.bounds.height - 1;
        this.Qsa = true;
        return n;
    }
    
    private int c(final Graphics graphics) {
        if (this.Qsa) {
            return this.Osa;
        }
        return this.f(graphics);
    }
    
    private void b(final Graphics graphics) {
        graphics.setColor(super.T);
        graphics.drawLine(this.Msa, this.Nsa, this.Msa, this.Osa);
    }
    
    private void e(final Graphics graphics) {
        super.t.setText(this.i(super.Pra));
        int _ = super.t._(graphics);
        int n = 0;
        if (!super.t.i()) {
            --n;
            if (!super.t.M()) {
                n -= 2;
            }
        }
        if (_ + n > 0) {
            _ += n;
        }
        super.Ora.D(Math.min(Math.max(Math.round(Math.max(1 + (this.Osa - super.gsa) - (this.Nsa + super.esa), 0) / _), 1), super.Bqa));
        super.t.L(1);
        switch (super.Kra) {
            case 0: {
                super.t.K(2);
                break;
            }
            case 1: {
                super.t.K(0);
                break;
            }
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        for (int i = 0; i < super.Ora.R(); ++i) {
            final double _2 = super.Ora._(i);
            super.t.setText(this.i(_2));
            final int n5 = (int)super.Nra.b(_2);
            final int _3 = super.t._(graphics);
            if (n5 + _3 / 2 + n <= this.Osa && n5 - _3 / 2 - n > this.Nsa) {
                if (n4 != 0) {
                    this._(graphics, n5);
                    this.a(graphics, n5);
                    n2 = n5;
                    n3 = _3;
                    n4 = 0;
                    if (super.osa && super.lsa != null && n5 != this.fa() && n5 != this.ga()) {
                        graphics.setColor(super.lsa);
                        graphics.drawLine(super.msa, n5, super.nsa, n5);
                    }
                }
                else if ((n2 < n5 && n2 + n3 / 2.0 + n < n5 - _3 / 2.0) || (n2 > n5 && n2 - n3 / 2.0 - n > n5 + _3 / 2.0)) {
                    this._(graphics, n5);
                    this.a(graphics, n5);
                    n2 = n5;
                    n3 = _3;
                    if (super.osa && super.lsa != null && n5 != this.fa() && n5 != this.ga()) {
                        graphics.setColor(super.lsa);
                        graphics.drawLine(super.msa, n5, super.nsa, n5);
                    }
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
                graphics.drawLine(this.Msa - super.Tra, n, this.Msa, n);
                break;
            }
            case 1: {
                graphics.drawLine(this.Msa, n, this.Msa + super.Tra, n);
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
                super.t.a(graphics, this.Msa - super.Tra, n);
                break;
            }
            case 1: {
                super.t.a(graphics, this.Msa + super.Tra + 1, n);
                break;
            }
        }
    }
}
