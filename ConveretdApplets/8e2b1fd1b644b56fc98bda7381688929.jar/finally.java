// 
// Decompiled by Procyon v0.5.30
// 

public class finally implements do
{
    private static final int uqa = 0;
    private static final int vqa = 1;
    private static final int wqa = 2;
    private static final int xqa = 3;
    private static final int yqa = 4;
    private double[] zqa;
    private int Aqa;
    private int Bqa;
    private double Tpa;
    private double Cqa;
    private boolean Dqa;
    private else Eqa;
    
    public finally() {
        this.Eqa = new else();
        this.Dqa = false;
        this.Bqa = 1;
    }
    
    public finally(final double n, final double n2, final int bqa) {
        this.Eqa = new else();
        if (n2 >= n) {
            this.Tpa = n;
            this.Cqa = n2;
        }
        else {
            this.Tpa = n2;
            this.Cqa = n;
        }
        this.Bqa = bqa;
        if (this.Bqa <= 0) {
            this.Bqa = 1;
        }
        this.Dqa = false;
    }
    
    public void _(final double n, final double n2) {
        if (n2 >= n) {
            this.Tpa = n;
            this.Cqa = n2;
        }
        else {
            this.Tpa = n2;
            this.Cqa = n;
        }
        this.Dqa = false;
    }
    
    public void D(final int bqa) {
        this.Bqa = bqa;
        if (this.Bqa <= 0) {
            this.Bqa = 1;
        }
        this.Dqa = false;
    }
    
    public int R() {
        if (!this.Dqa) {
            this.O();
        }
        return this.Aqa;
    }
    
    public double _(final int n) {
        if (!this.Dqa) {
            this.O();
        }
        if (n < 0 || n >= this.Aqa) {
            return 0.0;
        }
        return this.zqa[n];
    }
    
    public int[] _(final double[] array) {
        return this.Eqa.a(array);
    }
    
    private void O() {
        if (this.Cqa == this.Tpa || this.Bqa < 2) {
            if (this.zqa == null || this.zqa.length < this.Bqa) {
                this.zqa = new double[this.Bqa];
            }
            this.zqa[0] = this.Tpa + (this.Cqa - this.Tpa) / 2.0;
            if (this.Cqa - this.Tpa > 2.0) {
                this.zqa[0] = Math.round(this.zqa[0]);
            }
            this.Aqa = 1;
            this.Dqa = true;
            return;
        }
        this.Aqa = this.Bqa;
        if (this.zqa == null || this.zqa.length < this.Bqa) {
            this.zqa = new double[this.Bqa];
        }
        final double n = this.Eqa.a(this.Tpa, this.Cqa);
        this.Eqa._(this.Tpa);
        if ((int)n <= this.Aqa) {
            this.Aqa = (int)n + 1;
            if (this.Aqa > this.Bqa) {
                this.Aqa = this.Bqa;
            }
            for (int i = 0; i < this.Aqa; ++i) {
                this.zqa[i] = this.Eqa.l();
                if (this.zqa[i] > this.Cqa) {
                    break;
                }
                this.Aqa = i + 1;
                this.Eqa.E(1);
            }
            this.Dqa = true;
            return;
        }
        final double ceil = Math.ceil(n / this.Bqa);
        int n2;
        int n3;
        if (ceil >= this.Eqa.c() || this.Cqa - this.Tpa > 2.0) {
            n2 = (int)Math.ceil(ceil / this.Eqa.c());
            n3 = 3;
        }
        else if (ceil >= 3600000.0) {
            n2 = (int)Math.ceil(ceil / 3600000.0);
            n3 = 2;
        }
        else if (ceil >= 60000.0) {
            n2 = (int)Math.ceil(ceil / 60000.0);
            n3 = 1;
        }
        else if (ceil >= 1000.0) {
            n2 = (int)Math.ceil(ceil / 1000.0);
            n3 = 0;
        }
        else {
            n2 = (int)Math.ceil(ceil);
            n3 = 4;
        }
        this.Eqa._(this.Tpa);
        this.Eqa.E(1);
        switch (n3) {
            case 3: {
                this.Eqa.P();
                break;
            }
            case 2: {
                this.Eqa.J();
                break;
            }
            case 1: {
                if (n2 >= 5) {
                    this.Eqa.L();
                    while (n2 % 5 > 0) {
                        ++n2;
                    }
                    break;
                }
                this.Eqa.K();
                break;
            }
            case 0: {
                this.Eqa.M();
                if (n2 >= 5) {
                    this.Eqa.N();
                    while (n2 % 5 > 0) {
                        ++n2;
                    }
                    break;
                }
                break;
            }
        }
        this.Aqa = 0;
        for (int j = 0; j < this.Bqa; ++j) {
            this.zqa[j] = this.Eqa.l();
            if (this.zqa[j] > this.Cqa) {
                break;
            }
            this.Aqa = j + 1;
            switch (n3) {
                case 3: {
                    this.Eqa.F(n2);
                    break;
                }
                case 2: {
                    this.Eqa.B(n2);
                    break;
                }
                case 1: {
                    this.Eqa.A(n2);
                    break;
                }
                case 0: {
                    this.Eqa.z(n2);
                    break;
                }
                case 4: {
                    this.Eqa.E(n2);
                    break;
                }
            }
        }
        this.Dqa = true;
    }
}
