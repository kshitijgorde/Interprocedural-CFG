// 
// Decompiled by Procyon v0.5.30
// 

public class wave2d
{
    private int Tx;
    private int Ty;
    private double Min;
    private double Max;
    private double[][] Table;
    private int W;
    private int H;
    private double Maxi;
    private double Mini;
    static int LINEAR;
    static int ELLIPTIC;
    private int vX;
    private int vY;
    private int[] tX;
    private int[] tY;
    private int CurrentX;
    private int CurrentY;
    private int PeriodX;
    private int PeriodY;
    
    wave2d(final int[] array, final int w, final int h, final double min, final double max, final int vx, final int vy, final int n) {
        final double n2 = 3.141592653589793;
        this.Tx = array[0];
        this.Ty = array[1];
        this.W = w;
        this.H = h;
        this.Min = min;
        this.Max = max;
        this.Table = new double[this.Ty + this.H - 1][this.Tx + this.W - 1];
        for (int i = 0; i < array.length; i += 2) {
            final double n3 = 2.0 * n2 / array[i];
            final double n4 = 2.0 * n2 / array[i + 1];
            for (int j = 0; j < this.Ty + this.H - 1; ++j) {
                for (int k = 0; k < this.Tx + this.W - 1; ++k) {
                    this.Table[j][k] += Math.cos(n4 * j) * Math.sin(n3 * k);
                }
            }
        }
        this.Maxi = this.Table[0][0];
        this.Mini = this.Table[0][0];
        for (int l = 0; l < this.Ty + this.H - 1; ++l) {
            for (int n5 = 0; n5 < this.Tx + this.W - 1; ++n5) {
                if (this.Table[l][n5] > this.Maxi) {
                    this.Maxi = this.Table[l][n5];
                }
                if (this.Table[l][n5] < this.Mini) {
                    this.Mini = this.Table[l][n5];
                }
            }
        }
        for (int n6 = 0; n6 < this.Ty + this.H - 1; ++n6) {
            for (int n7 = 0; n7 < this.Tx + this.W - 1; ++n7) {
                this.Table[n6][n7] = (this.Min + (-this.Mini + this.Table[n6][n7]) / (this.Maxi - this.Mini)) * (this.Max - this.Min);
            }
        }
        this.CurrentX = 0;
        this.CurrentY = 0;
        this.vX = vx;
        this.vY = vy;
        if (n == wave2d.LINEAR) {
            this.PeriodX = this.Tx;
            this.PeriodY = this.Ty;
            this.tX = new int[this.Tx];
            this.tY = new int[this.Ty];
            for (int n8 = 0; n8 < this.Tx; ++n8) {
                this.tX[n8] = n8;
            }
            for (int n9 = 0; n9 < this.Ty; ++n9) {
                this.tY[n9] = n9;
            }
        }
        if (n == wave2d.ELLIPTIC) {
            final int n10 = 360;
            this.PeriodX = n10;
            this.PeriodY = n10;
            this.tX = new int[n10];
            this.tY = new int[n10];
            final int round = Math.round((this.Tx - 3) / 2);
            final int round2 = Math.round((this.Ty - 3) / 2);
            final int n11 = 1 + round;
            final int n12 = 1 + round2;
            for (int n13 = 0; n13 < n10; ++n13) {
                this.tX[n13] = (int)Math.round(round * Math.cos(n13 * n2 / 180.0) + n11);
                this.tY[n13] = (int)Math.round(round2 * Math.sin(n13 * n2 / 180.0) + n12);
            }
        }
    }
    
    public double[][] getTable() {
        return this.Table;
    }
    
    public int getoX() {
        return this.tX[this.CurrentX];
    }
    
    public int getoY() {
        return this.tY[this.CurrentY];
    }
    
    public double getMaxi() {
        return this.Maxi;
    }
    
    public double getMini() {
        return this.Mini;
    }
    
    public void Propag() {
        if (this.CurrentX < this.PeriodX - this.vX) {
            this.CurrentX += this.vX;
        }
        else {
            this.CurrentX = 0;
        }
        if (this.CurrentY < this.PeriodY - this.vY) {
            this.CurrentY += this.vY;
            return;
        }
        this.CurrentY = 0;
    }
    
    static {
        wave2d.ELLIPTIC = 1;
    }
}
