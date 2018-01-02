import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Seiche2Plot
{
    public int length;
    public int blength;
    public int clength;
    public int amplitude;
    public int totalh;
    public int totalh1;
    public int totalh2;
    public int[] x;
    public int[] y;
    public float H;
    public float d1;
    public float d2;
    public float T;
    public float L1;
    public float L2;
    public float mode;
    public float ratio;
    public float of;
    public double k;
    public double C1;
    public double C2;
    public double scale;
    public double fguess;
    int[] depth;
    int[] uc;
    int[] ut;
    int[] vt;
    int[] vc;
    int[] um;
    int[] vm;
    int[] up;
    int[] vp;
    float dz;
    double dt;
    int dep;
    public float famplitude1;
    public float famplitude2;
    public float famplitudex;
    public float xtmp;
    int ncount;
    String seicheperiod;
    String ratioout;
    String error;
    public double temp;
    public int jstep;
    float[] umag;
    float[] vmag;
    int offset;
    int Depth;
    int x1;
    int x2;
    int x3;
    int x4;
    int x5;
    int x6;
    int x7;
    int x8;
    
    public Seiche2Plot(final int length, final int totalh) {
        this.Block$();
        this.length = length;
        this.totalh = totalh;
        this.x = new int[this.length + 4];
        this.y = new int[this.length + 4];
        this.uc = new int[this.ncount];
        this.ut = new int[this.ncount];
        this.vc = new int[this.ncount];
        this.vt = new int[this.ncount];
        this.um = new int[this.ncount];
        this.vm = new int[this.ncount];
        this.vp = new int[this.ncount];
        this.up = new int[this.ncount];
        this.umag = new float[this.ncount];
        this.vmag = new float[this.ncount];
        this.depth = new int[this.ncount];
        for (int i = 0; i < this.length + 3; ++i) {
            this.x[i] = 0;
            this.y[i] = i;
        }
    }
    
    public void initialize(final float l1, final float l2, final float d1, final float d2, final float mode, final float of) {
        this.L1 = l1;
        this.L2 = l2;
        this.scale = this.length / (this.L1 + this.L2);
        this.blength = (int)(this.L1 * this.scale);
        this.clength = (int)(this.L2 * this.scale);
        System.out.println("L1, L2:" + this.L1 + ", " + this.L2);
        System.out.println("blength, length: " + this.blength + ", " + this.length + " clength: " + this.clength);
        this.mode = mode;
        this.of = of;
        this.d1 = d1;
        this.d2 = d2;
        if (this.d1 > this.d2) {
            this.totalh1 = this.totalh;
            this.totalh2 = (int)(this.totalh * this.d2 / this.d1);
        }
        else {
            this.totalh1 = (int)(this.totalh * this.d1 / this.d2);
            this.totalh2 = this.totalh;
        }
        System.out.println("totalh,totalh1,totalh2: " + this.totalh + ", " + this.totalh1 + ", " + this.totalh2);
        this.C1 = Math.sqrt(9.81 * this.d1);
        this.C2 = Math.sqrt(9.81 * this.d2);
        System.out.println("is fguess right? :" + this.fguess);
        if (this.of == 0.0) {
            this.fguess = (this.mode - 1.0f) * 3.0 * this.C1 / this.L1;
            if (this.fguess < 1.0E-4) {
                this.fguess = 3.141592653589793 * (this.C1 + this.C2) / (2.0f * (this.L1 + this.L2));
            }
            System.out.println("Initial guess: " + this.fguess + ", d1, d2: " + this.d1 + ", " + this.d2);
        }
        double n = this.d2 * this.C1 * Math.tan(this.fguess * this.L2 / this.C2) + this.d1 * this.C2 * Math.tan(this.fguess * this.L1 / this.C1);
        double n2 = this.L2 * this.d2 * this.C1 / (this.C2 * Math.cos(this.fguess * this.L2 / this.C2) * Math.cos(this.fguess * this.L2 / this.C2)) + this.L1 * this.d1 * this.C2 / (this.C1 * Math.cos(this.fguess * this.L1 / this.C1) * Math.cos(this.fguess * this.L1 / this.C1));
        System.out.println("zf,zfp: " + n + ", " + n2);
        double n3 = this.fguess - n / n2;
        System.out.println("guess: " + n3);
        double fguess = n3;
        if (Math.abs(n) > 1.0E-4) {
            for (int i = 0; i < 10; ++i) {
                final double n4 = this.d2 * this.C1 * Math.tan(n3 * this.L2 / this.C2) + this.d1 * this.C2 * Math.tan(n3 * this.L1 / this.C1);
                if (Math.abs(n4) > 1.0E-6) {
                    final double n5 = this.L2 * this.d2 * this.C1 / (this.C2 * Math.cos(n3 * this.L2 / this.C2) * Math.cos(n3 * this.L2 / this.C2)) + this.L1 * this.d1 * this.C2 / (this.C1 * Math.cos(n3 * this.L1 / this.C1) * Math.cos(n3 * this.L1 / this.C1));
                    final double n6 = n3 - n4 / n5;
                    final double n7 = n6 - n3;
                    final double n8 = (2.0 * n5 + n2 - 3.0 * (n4 - n) / n7) / n7;
                    final double n9 = n4 / n5;
                    System.out.println("nguess,zd,za,zu: " + n6 + ", " + n7 + ", " + n8 + ", " + n9);
                    fguess = n6 - n9 * (1.0 + n9 * n8 / n5);
                    System.out.println("lguess, f: " + fguess + ", " + n4);
                    n3 = n6;
                    final double n10 = fguess;
                    n = n4;
                    n2 = n5;
                    System.out.println("f=" + n4 + ", sigma=" + n10);
                }
            }
        }
        System.out.println("arg1= " + fguess * this.L1 / this.C1 + ", arg2= " + fguess * this.L2 / this.C2);
        this.T = (float)(6.2831854 / fguess);
        System.out.println("T= " + this.T);
        if (Math.abs(n) > 1.0) {
            this.error = "SOLUTION NOT FOUND";
            this.T = 0.0f;
        }
        else if (Math.abs(fguess) < 1.0E-6) {
            this.error = "SOLUTION NOT FOUND";
            this.T = 0.0f;
        }
        else {
            this.error = " ";
        }
        this.dt = this.T / 20.0;
        this.fguess = fguess;
        this.temp = (int)(this.T * 100.0f) / 100.0;
        this.seicheperiod = "T = " + this.temp + " s";
        System.out.println("T (mode " + this.mode + ") = " + this.T + " s");
        this.H = 1.0f;
        this.famplitudex = (float)(this.H / 2.0f * Math.cos(n3 * this.L1 / this.C1) / Math.cos(n3 * this.L2 / this.C2));
        this.ratio = (float)(2.0 * this.famplitudex);
        this.ratio = (float)((int)(Math.abs(this.ratio) * 100.0f) / 100.0);
        this.ratioout = "Ratio: " + this.ratio;
        double n11 = (this.totalh - this.offset) / (this.d1 + this.H / 2.0f);
        final double n12 = (this.totalh - this.offset) / (this.d2 + Math.abs(this.famplitudex));
        if (n12 < n11) {
            n11 = n12;
        }
        this.famplitude1 = (float)(this.H / 2.0f * n11);
        this.famplitude2 = (float)(this.famplitudex * n11);
        final double n13 = this.H / 2.0 * Math.cos(n3 * this.L1 / this.C1);
        final double n14 = this.famplitudex * Math.cos(n3 * this.L2 / this.C2);
        final double n15 = n3 * this.L1 / this.C1;
        final double n16 = -n3 * this.L2 / this.C2;
        this.amplitude = (int)Math.abs(this.famplitude2);
        if (Math.abs(this.famplitude1) > Math.abs(this.famplitude2)) {
            this.amplitude = (int)Math.abs(this.famplitude1);
        }
        System.out.println("amplitude: " + this.amplitude);
        this.dz = (this.totalh - this.amplitude - this.offset) / (this.ncount - 1);
    }
    
    public void do_wave() {
        ++this.jstep;
        final double n = 6.283185307179586 / this.T;
        for (int i = 0; i < this.length; ++i) {
            final int n2 = i - this.blength;
            final double n3 = n * this.jstep * this.dt;
            if (n2 < 0) {
                this.x[i] = this.offset + this.amplitude - (int)(this.famplitude1 * Math.cos(6.283185307179586 * (n2 + this.blength) / (this.T * this.C1 * this.scale)) * Math.cos(n3));
            }
            else {
                this.x[i] = this.offset + this.amplitude - (int)(this.famplitude2 * Math.cos(6.283185307179586 * (n2 - this.clength) / (this.T * this.C2 * this.scale)) * Math.cos(n3));
            }
        }
    }
    
    public void Draw(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawLine(0, this.amplitude + this.offset, this.length, this.amplitude + this.offset);
        graphics.drawLine(0, 0, 0, this.totalh);
        final int n = this.length - 1;
        final int n2 = this.totalh - 1;
        graphics.drawLine(0, n2, n, n2);
        graphics.setColor(Color.black);
        graphics.drawLine(n, 0, n, n);
        graphics.setColor(Color.blue);
        this.x[this.length] = this.totalh2;
        this.x[this.length + 1] = this.totalh2;
        this.x[this.length + 2] = this.totalh1;
        this.x[this.length + 3] = this.totalh1;
        this.y[this.length] = this.length - 1;
        this.y[this.length + 1] = this.blength + 1;
        this.y[this.length + 2] = this.blength;
        this.y[this.length + 3] = 0;
        graphics.fillPolygon(this.y, this.x, this.length + 4);
        final int n3 = 70;
        final int n4 = this.amplitude + 80;
        graphics.setColor(Color.yellow);
        graphics.drawString(this.seicheperiod, n3, n4);
        final int n5 = n4 - 20;
        graphics.drawString(this.ratioout, n3, n5);
        graphics.drawString(this.error, n3, n5 - 20);
    }
    
    public void update(final Graphics graphics) {
        this.Draw(graphics);
    }
    
    private void Block$() {
        this.fguess = 1.0;
        this.ncount = 8;
        this.seicheperiod = " ";
        this.ratioout = " ";
        this.error = " ";
        this.offset = 5;
    }
}
