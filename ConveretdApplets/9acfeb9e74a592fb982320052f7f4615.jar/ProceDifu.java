import java.util.Date;
import java.awt.Rectangle;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

class ProceDifu
{
    double[][] cx;
    double[][] cy;
    int[][] icx;
    int[][] icy;
    boolean[] fuera_lim;
    double[] dist;
    double desv_tip;
    static final int MAX_NUM_PART = 400;
    int num_partic;
    int contador;
    int[] anchoalto;
    int[] origen;
    double dOx;
    double dOy;
    Random rangen;
    Rectangle rgraf;
    double dang;
    double dimp;
    double dincx;
    double dincy;
    boolean bcurva;
    
    public ProceDifu(final int num_partic) {
        this.cx = new double[2][400];
        this.cy = new double[2][400];
        this.icx = new int[6][400];
        this.icy = new int[6][400];
        this.fuera_lim = new boolean[400];
        this.dist = new double[400];
        this.num_partic = 50;
        this.anchoalto = new int[2];
        this.origen = new int[2];
        this.rangen = new Random();
        this.rgraf = new Rectangle(0, 0, 0, 0);
        this.bcurva = false;
        if (num_partic <= 400) {
            this.num_partic = num_partic;
        }
    }
    
    public void setrandom() {
        this.rangen.setSeed(new Date().getTime());
    }
    
    public void setorigen(final int n, final int n2) {
        this.anchoalto[0] = n;
        this.anchoalto[1] = n2;
        this.origen[0] = n / 2;
        this.origen[1] = n2 / 2;
        this.dOx = new Integer(this.origen[0]);
        this.dOy = new Integer(this.origen[1]);
        for (int i = 0; i < this.num_partic; ++i) {
            this.fuera_lim[i] = false;
            for (int j = 0; j < 2; ++j) {
                this.cx[j][i] = this.origen[0];
                this.cy[j][i] = this.origen[1];
            }
        }
        for (int k = 0; k < this.num_partic; ++k) {
            this.dist[k] = 0.0;
        }
        this.desv_tip = 0.0;
        this.contador = 0;
    }
    
    public void getnewpos() {
        ++this.contador;
        for (int i = 0; i < this.num_partic; ++i) {
            this.dang = this.rangen.nextDouble() * 6.283185;
            this.dimp = this.rangen.nextGaussian() * 4.0 + 16.0;
            this.dincx = this.dimp * Math.cos(this.dang);
            this.dincy = this.dimp * Math.sin(this.dang);
            this.cx[1][i] = this.cx[0][i] + this.dincx;
            this.cy[1][i] = this.cy[0][i] + this.dincy;
            this.icx[0][i] = (int)(Object)new Double(this.cx[0][i]);
            this.icy[0][i] = (int)(Object)new Double(this.cy[0][i]);
            for (int j = 1; j < 6; ++j) {
                this.icx[j][i] = this.icx[0][i] + (int)(Object)new Double(j * this.dincx / 5.0);
                this.icy[j][i] = this.icy[0][i] + (int)(Object)new Double(j * this.dincy / 5.0);
            }
            if (this.cx[1][i] < 0.0 | this.cx[1][i] > this.anchoalto[0]) {
                this.fuera_lim[i] = true;
            }
            else {
                this.fuera_lim[i] = false;
            }
            if (this.cy[1][i] < 0.0 | this.cy[1][i] > this.anchoalto[1]) {
                this.fuera_lim[i] = true;
            }
            else {
                this.fuera_lim[i] = false;
            }
            this.cx[0][i] = this.cx[1][i];
            this.cy[0][i] = this.cy[1][i];
            this.cal_desvtip();
        }
    }
    
    private void cal_desvtip() {
        double n = 0.0;
        for (int i = 0; i < this.num_partic; ++i) {
            final double n2 = (this.cx[0][i] - this.dOx) * (this.cx[0][i] - this.dOx) + (this.cy[0][i] - this.dOy) * (this.cy[0][i] - this.dOy);
            n += n2;
            this.dist[i] = Math.sqrt(n2);
        }
        this.desv_tip = Math.sqrt(n / this.num_partic);
    }
    
    public int[] recuento(final int n, final int n2) {
        final int[] array = new int[2];
        for (int i = 0; i < this.num_partic; ++i) {
            final int intValue = (int)(Object)new Double(this.cx[0][i] - this.dOx);
            if (intValue >= n & intValue < n2) {
                final int[] array2 = array;
                final int n3 = 0;
                ++array2[n3];
            }
            if (intValue <= -n & intValue > -n2) {
                final int[] array3 = array;
                final int n4 = 1;
                ++array3[n4];
            }
        }
        array[0] = array[0] * 800 / this.num_partic;
        array[1] = array[1] * 800 / this.num_partic;
        return array;
    }
}
