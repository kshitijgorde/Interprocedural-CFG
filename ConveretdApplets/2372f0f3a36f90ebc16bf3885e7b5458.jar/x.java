// 
// Decompiled by Procyon v0.5.30
// 

public class x
{
    public double[] a;
    public double[] b;
    public int c;
    public int d;
    public double[][] e;
    
    public x() {
        this.a = new double[11];
        this.b = new double[11];
        this.c = 0;
        this.d = 0;
        this.e = new double[][] { { -0.4661689823, 4.7612287371, -22.1451597269, 61.770365433, -114.4388222872, 147.1613294861, -133.0523992281, 83.5375902486, -34.8695212244, 8.7415537814, 2.721412602E8 }, { -0.4357796975, 4.4349929412, -20.5999277807, 57.5091300746, -106.8710418505, 138.1604906793, -125.8674267456, 79.8188124132, -33.735205566, 8.5859469729, 1.196396016E8 }, { -0.3185277424, 3.1484803697, -14.3913614622, 40.0481984622, -75.1401418562, 99.3527282442, -93.8138950999, 62.5251148906, -28.1880702431, 7.7772848985, 5402592.635 }, { -0.190484035, 1.7056965267, -7.3089332512, 19.6800092635, -36.8322196902, 50.0752031765, -50.1609180738, 36.6681478943, -18.8176151067, 6.1743249453, 150846.6549 }, { -0.1022197056, 0.7231663314, -2.6532039099, 6.5076128639, -11.7185711236, 16.1474802298, -17.2906808532, 14.2998451497, -8.9084781445, 3.845784368, 6860.291519 }, { -0.0370330093, 0.0858424932, -0.2286394113, 0.3571672827, -0.7364650631, 0.4321646247, -1.6847166912, -0.3119265502, -2.2294825387, -0.635966548, 170.9785483 } };
    }
    
    public boolean a(final int[] array, final int n, final int n2, final int n3, final int n4) {
        return this.b(array, n, n2, n3, n4);
    }
    
    private boolean a(final int n, final int n2) {
        if (n == 0) {
            return false;
        }
        final double n3 = n2 / n;
        if (n3 > 0.9) {
            this.c = 0;
        }
        else if (n3 > 0.72) {
            this.c = 5;
        }
        else if (n3 > 0.49) {
            this.c = 4;
        }
        else if (n3 > 0.36) {
            this.c = 3;
        }
        else if (n3 > 0.24) {
            this.c = 2;
        }
        else if (n3 > 0.18) {
            this.c = 1;
        }
        else if (n3 > 0.16) {
            this.c = 0;
        }
        else {
            this.c = 0;
        }
        return true;
    }
    
    private boolean b(final int[] array, final int n, final int n2, final int n3, final int n4) {
        if (n3 != 1) {
            return false;
        }
        if (!this.a(n4, 8000)) {
            return false;
        }
        if (this.c != 0) {
            for (int i = 0; i < n; ++i) {
                this.a[0] = this.a[1];
                this.a[1] = this.a[2];
                this.a[2] = this.a[3];
                this.a[3] = this.a[4];
                this.a[4] = this.a[5];
                this.a[5] = this.a[6];
                this.a[6] = this.a[7];
                this.a[7] = this.a[8];
                this.a[8] = this.a[9];
                this.a[9] = this.a[10];
                this.a[10] = array[i] / this.e[this.c][10];
                this.b[0] = this.b[1];
                this.b[1] = this.b[2];
                this.b[2] = this.b[3];
                this.b[3] = this.b[4];
                this.b[4] = this.b[5];
                this.b[5] = this.b[6];
                this.b[6] = this.b[7];
                this.b[7] = this.b[8];
                this.b[8] = this.b[9];
                this.b[9] = this.b[10];
                this.b[10] = this.a[0] + this.a[10] + 10.0 * (this.a[1] + this.a[9]) + 45.0 * (this.a[2] + this.a[8]) + 120.0 * (this.a[3] + this.a[7]) + 210.0 * (this.a[4] + this.a[6]) + 252.0 * this.a[5] + this.e[this.c][0] * this.b[0] + this.e[this.c][1] * this.b[1] + this.e[this.c][2] * this.b[2] + this.e[this.c][3] * this.b[3] + this.e[this.c][4] * this.b[4] + this.e[this.c][5] * this.b[5] + this.e[this.c][6] * this.b[6] + this.e[this.c][7] * this.b[7] + this.e[this.c][8] * this.b[8] + this.e[this.c][9] * this.b[9];
                int n5 = (int)this.b[10];
                if (n5 > 32767) {
                    n5 = 32767;
                }
                else if (n5 < -32768) {
                    n5 = -32768;
                }
                array[i] = n5;
            }
            return true;
        }
        return false;
    }
}
