import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class final extends finally
{
    private int mna;
    private int nna;
    private int ona;
    private boolean pna;
    private boolean qna;
    private boolean rna;
    private int sna;
    
    public final() {
        this.sna = 1;
        super.Gma = 1;
        this.pna = false;
        this.qna = false;
        this.rna = false;
        super.Pma.k(true);
    }
    
    public void J(final int nna) {
        this.nna = nna;
        this.pna = true;
    }
    
    public void K(final int ona) {
        this.ona = ona;
        this.qna = true;
    }
    
    public void L(final int mna) {
        this.mna = mna;
        this.rna = true;
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
        this.mna = this.l(graphics);
        this.nna = this.m(graphics);
        this.ona = this.n(graphics);
        if (this.nna + super.Nma < this.ona - super.Mma) {
            if (super.Hma instanceof try) {
                super.Oma.j(super.Ima, super.Jma);
                super.Hma.b(super.Ima, super.Jma);
                super.Hma._(this.nna + super.Nma + Math.abs(this.ona - this.nna) * ((super.Nma == 0) ? super.gna : 0) / 100.0, this.ona - super.Mma - Math.abs(this.ona - this.nna) * ((super.Mma == 0) ? super.fna : 0) / 100.0);
            }
            else {
                final double g = this.g();
                final double h = this.h();
                super.Oma.j(g, h);
                super.Hma.b(g, h);
                super.Hma._(this.nna + super.Nma, this.ona - super.Mma);
            }
            this.b(graphics);
            this.n(graphics);
            b = true;
        }
        this.u();
        return b;
    }
    
    public void u() {
        this.pna = false;
        this.qna = false;
        this.rna = false;
    }
    
    public int M() {
        return this.mna;
    }
    
    public int fa() {
        return this.nna;
    }
    
    public int ga() {
        return this.ona;
    }
    
    public int c(final Graphics graphics) {
        super.Pma.setText("Test[Xy]");
        if (super.Oma instanceof i && Math.abs(super.Jma - super.Ima) <= 2.0) {
            super.Pma.setText(this._(123.456));
        }
        switch (super.Gma) {
            case 0: {
                this.mna = super.bounds.y;
                if (super.Qma) {
                    this.mna += super.Rma;
                }
                if (super.Sma) {
                    this.mna += super.Pma._(graphics);
                    break;
                }
                break;
            }
            case 1: {
                this.mna = super.bounds.y + super.bounds.height;
                if (super.Qma) {
                    this.mna -= super.Rma;
                }
                if (super.Sma) {
                    this.mna -= super.Pma._(graphics);
                    break;
                }
                break;
            }
        }
        this.rna = true;
        return this.mna;
    }
    
    public int l(final Graphics graphics) {
        if (this.rna) {
            return this.mna;
        }
        return this.c(graphics);
    }
    
    private int d(final Graphics graphics) {
        final int n = super.bounds.x + 1;
        this.pna = true;
        return n;
    }
    
    private int m(final Graphics graphics) {
        if (this.pna) {
            return this.nna;
        }
        return this.d(graphics);
    }
    
    private int e(final Graphics graphics) {
        final int n = super.bounds.x + super.bounds.width - 1;
        this.qna = true;
        return n;
    }
    
    private int n(final Graphics graphics) {
        if (this.qna) {
            return this.ona;
        }
        return this.e(graphics);
    }
    
    private void b(final Graphics graphics) {
        graphics.setColor(super.xa);
        graphics.drawLine(this.nna, this.mna, this.ona, this.mna);
    }
    
    private double b(final double[] array) {
        if (array.length < 3) {
            return 0.0;
        }
        double n = 0.0;
        for (int i = 0; i < array.length - 1; ++i) {
            n += Math.abs(super.Hma.b(array[i + 1]) - super.Hma.b(array[i]));
        }
        final double n2 = n / (array.length - 1);
        if (n2 == 0.0) {
            return 0.0;
        }
        double n3 = 0.0;
        for (int j = 0; j < array.length - 1; ++j) {
            n3 += Math.abs(n2 - Math.abs(super.Hma.b(array[j + 1]) - super.Hma.b(array[j]))) / n2;
        }
        return n3 / (array.length - 1);
    }
    
    private int b(final Graphics graphics, final double[] array) {
        int n = 0;
        int n2 = 1;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < array.length; ++i) {
            final double n5 = array[i];
            super.Pma.setText(this._(n5));
            final int n6 = (int)super.Hma.b(n5);
            final int b = super.Pma.b(graphics);
            if (n6 >= this.nna && n6 <= this.ona) {
                if (n2 != 0) {
                    if (n6 - b / 2 >= super.bounds.x && n6 + b / 2 <= super.bounds.x + super.bounds.width) {
                        ++n;
                        n3 = n6;
                        n4 = b;
                    }
                    n2 = 0;
                }
                else if (((n3 < n6 && n3 + n4 / 2.0 + this.sna <= n6 - b / 2.0) || (n3 > n6 && n3 - n4 / 2.0 - this.sna >= n6 + b / 2.0)) && n6 - b / 2 >= super.bounds.x && n6 + b / 2 <= super.bounds.x + super.bounds.width) {
                    ++n;
                    n3 = n6;
                    n4 = b;
                }
            }
        }
        return n;
    }
    
    private void n(final Graphics graphics) {
        super.Pma.setText(this._(super.Ima));
        final int b = super.Pma.b(graphics);
        super.Pma.setText(this._(super.Jma));
        final int max = Math.max(Math.min((int)Math.floor((Math.max(this.ona - super.Mma - (this.nna + super.Nma), 0) - (this.ona - this.nna) * (((super.Mma == 0) ? super.fna : 0) + ((super.Nma == 0) ? super.gna : 0)) / 100.0) / (Math.max(b, super.Pma.b(graphics)) + this.sna / 2.0)), super.Ika), 1);
        if (super.Oma instanceof i) {
            double[] x = null;
            if (super.Hma instanceof try) {
                x = ((try)super.Hma).X();
            }
            super.kna.a(((i)super.Oma).a(x));
        }
        super.Oma.r(max);
        super.Pma.i(1);
        switch (super.Gma) {
            case 0: {
                super.Pma.j(4);
                break;
            }
            case 1: {
                super.Pma.j(3);
                break;
            }
        }
        int n = 0;
        int n2 = 0;
        final Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds == null) {
            return;
        }
        double[] array = new double[super.Oma.ia()];
        for (int i = 0; i < super.Oma.ia(); ++i) {
            array[i] = super.Oma.a(i);
        }
        final double b2 = this.b(array);
        final int b3 = this.b(graphics, array);
        if (super.Hma instanceof try && (((b2 > 0.55 || b3 < 2 || b3 * 2 <= array.length) && (!(super.Oma instanceof i) || Math.abs(super.Jma - super.Ima) < 2.0)) || (super.Oma instanceof i && Math.abs(super.Jma - super.Ima) < 2.0 && b3 < 3))) {
            final double[] x2 = ((try)super.Hma).X();
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
            super.Pma.setText(this._(n9));
            final int n10 = (int)super.Hma.b(n9);
            final int b4 = super.Pma.b(graphics);
            if (n10 >= this.nna && n10 <= this.ona) {
                if (n8 != 0) {
                    if (super.Tma && super.Uma != null && n10 != this.fa() && n10 != this.ga()) {
                        graphics.setColor(super.Uma);
                        graphics.drawLine(n10, super.Vma, n10, super.Wma);
                    }
                    if (n10 - b4 / 2 >= clipBounds.x && n10 + b4 / 2 <= clipBounds.x + clipBounds.width) {
                        this.b(graphics, n10);
                        this.a(graphics, n10);
                        n = n10;
                        n2 = b4;
                    }
                    n8 = 0;
                }
                else if (((n < n10 && n + n2 / 2.0 + this.sna <= n10 - b4 / 2.0) || (n > n10 && n - n2 / 2.0 - this.sna >= n10 + b4 / 2.0)) && n10 - b4 / 2 >= clipBounds.x && n10 + b4 / 2 <= clipBounds.x + clipBounds.width) {
                    if (super.Tma && super.Uma != null && n10 != this.fa() && n10 != this.ga()) {
                        graphics.setColor(super.Uma);
                        graphics.drawLine(n10, super.Vma, n10, super.Wma);
                    }
                    this.a(graphics, n10);
                    this.b(graphics, n10);
                    n = n10;
                    n2 = b4;
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
                graphics.drawLine(n, this.mna, n, this.mna - super.Rma);
                break;
            }
            case 1: {
                graphics.drawLine(n, this.mna, n, this.mna + super.Rma);
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
                super.Pma._(graphics, n, this.mna - super.Rma);
                break;
            }
            case 1: {
                super.Pma._(graphics, n, this.mna + super.Rma);
                break;
            }
        }
    }
}
