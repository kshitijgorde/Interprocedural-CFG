// 
// Decompiled by Procyon v0.5.30
// 

public class LineSObject
{
    private static final int DEFAULT_NINTEGRATION = 100;
    private static final int DEFAULT_NFREQ = 100;
    private static final double DEFAULT_ENERGY = 1.0;
    private static final double MAX_SHORT_AMPL = 6000.0;
    private static final double MAX_BYTE_AMPL = 120.0;
    private double samplingRate;
    private double f0;
    private int np;
    private int nf;
    private int nNumInt;
    private double impulsEnergy;
    private double materialConstant;
    private double overallDecayConstant;
    private boolean ampBufIsLoaded;
    private double max_short_ampl;
    private double[][] ampBuf;
    
    private void constructorHelper(final double overallDecayConstant, final double materialConstant, final double f0, final int np) {
        this.f0 = f0;
        this.np = np;
        this.materialConstant = materialConstant;
        this.overallDecayConstant = overallDecayConstant;
        this.nf = this.maxFreq();
        this.ampBuf = new double[this.nf + 1][this.np];
        if (this.ampBuf == null) {
            System.out.println("Couldnt allocate ampBuf");
        }
        for (int i = 1; i < this.nf; ++i) {
            for (int j = 0; j < this.np; ++j) {
                final double centerOfSound = this.centerOfSound(j);
                try {
                    this.ampBuf[i][j] = this.ampl(i, centerOfSound);
                }
                catch (Exception ex) {}
            }
        }
        this.ampBufIsLoaded = true;
    }
    
    public LineSObject(final double n, final double n2, final double n3, final int n4) {
        this.samplingRate = 8012.0;
        this.nf = 100;
        this.nNumInt = 100;
        this.impulsEnergy = 1.0;
        this.ampBufIsLoaded = false;
        this.max_short_ampl = 6000.0;
        this.constructorHelper(n, n2, n3, n4);
    }
    
    public LineSObject(final double n, final double n2, final double n3, final int n4, final double impulsEnergy) {
        this.samplingRate = 8012.0;
        this.nf = 100;
        this.nNumInt = 100;
        this.impulsEnergy = 1.0;
        this.ampBufIsLoaded = false;
        this.max_short_ampl = 6000.0;
        this.impulsEnergy = impulsEnergy;
        this.constructorHelper(n, n2, n3, n4);
    }
    
    public LineSObject(final double n, final double n2, final double n3, final int n4, final double impulsEnergy, final double max_short_ampl) {
        this.samplingRate = 8012.0;
        this.nf = 100;
        this.nNumInt = 100;
        this.impulsEnergy = 1.0;
        this.ampBufIsLoaded = false;
        this.max_short_ampl = 6000.0;
        this.impulsEnergy = impulsEnergy;
        this.max_short_ampl = max_short_ampl;
        this.constructorHelper(n, n2, n3, n4);
    }
    
    public int soundMapOf(double n) {
        if (n >= 0.5) {
            n = 1.0 - n;
        }
        int n2 = (int)(n * 2.0 * this.np);
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= this.np) {
            n2 = this.np - 1;
        }
        return n2;
    }
    
    public double centerOfSound(final int n) {
        return (n + 0.5) / (2 * this.np);
    }
    
    public double freq(final int n) {
        return n * this.f0;
    }
    
    public int maxFreq() {
        int n = (int)(0.5 * this.samplingRate / this.f0);
        if (n < 1) {
            n = 1;
        }
        return n;
    }
    
    public double decayRate(final int n) {
        return this.overallDecayConstant + this.materialConstant * this.freq(n);
    }
    
    public double psi(final int n, final double n2) {
        return Math.sin(3.141592653589793 * n * n2);
    }
    
    public double ampl(final int n, final double n2) {
        double abs = 1.0;
        double n3 = 0.0;
        try {
            if (!this.ampBufIsLoaded) {
                try {
                    n3 = 0.0;
                    for (int i = 1; i <= this.nf; ++i) {
                        n3 += this.psi(i, n2) * this.psi(i, n2);
                    }
                    if (n3 <= 0.0) {
                        return 0.0;
                    }
                }
                catch (Exception ex) {
                    System.out.println("ampl threw exception at point 1" + ex);
                }
                try {
                    abs = this.impulsEnergy / this.freq(n);
                    abs *= this.psi(n, n2) / Math.sqrt(n3);
                    abs = Math.abs(abs);
                }
                catch (Exception ex2) {
                    System.out.println("ampl threw exception at point 2" + ex2);
                }
            }
            else {
                try {
                    abs = this.ampBuf[n][this.soundMapOf(n2)];
                }
                catch (Exception ex3) {
                    System.out.println("ampl threw exception at ampBuf access" + ex3);
                }
            }
        }
        catch (Exception ex4) {
            System.out.println("ampl threw exception" + ex4);
        }
        return abs;
    }
    
    public byte[] writeSound(final double n, final double[] array, final double n2, final double n3, final double n4) {
        final int n5 = (int)(n3 * n);
        final int n6 = (int)(n4 * n);
        final int n7 = n5 + n6;
        final double[] array2 = new double[n5];
        double abs = 0.0;
        final double[] array3 = new double[this.nf + 1];
        final double[] array4 = new double[this.nf + 1];
        final double[] array5 = new double[this.nf + 1];
        final double[] array6 = new double[this.nf + 1];
        final double[] array7 = new double[this.nf + 1];
        for (int i = 1; i <= this.nf; ++i) {
            array3[i] = (array4[i] = 0.0);
            final double n8 = Math.cos(6.283185307179586 * this.freq(i) / n) * Math.exp(-this.decayRate(i) / n);
            final double n9 = Math.sin(6.283185307179586 * this.freq(i) / n) * Math.exp(-this.decayRate(i) / n);
            final double n10 = n9 * n9;
            final double sqrt = Math.sqrt(1.0 - n10);
            array5[i] = n8 + sqrt;
            array6[i] = n8 - sqrt;
            array7[i] = n10 * this.ampl(i, n2);
        }
        double n11 = 0.0;
        for (int j = 0; j < n6; ++j) {
            n11 = 0.0;
            for (int k = 1; k <= this.nf; ++k) {
                final double n12 = array3[k];
                array3[k] = array6[k] * n12 - array4[k] + array7[k] * array[j];
                array4[k] = n12 + array5[k] * array4[k];
                n11 += array4[k];
            }
        }
        for (int n13 = 0, l = n6; l < n7; ++l, ++n13) {
            array2[n13] = n11;
            if (Math.abs(n11) > abs) {
                abs = Math.abs(n11);
            }
            n11 = 0.0;
            for (int n14 = 1; n14 <= this.nf; ++n14) {
                final double n15 = array3[n14];
                array3[n14] = array6[n14] * n15 - array4[n14] + array7[n14] * array[l];
                array4[n14] = n15 + array5[n14] * array4[n14];
                n11 += array4[n14];
            }
        }
        final byte[] array8 = new byte[n5];
        if (abs <= 0.0) {
            abs = 1.0;
        }
        for (int n16 = n6, n17 = 0; n16 < n7; ++n16, ++n17) {
            array8[n17] = Mulaw.linear2ulaw((short)(array2[n17] * this.max_short_ampl / abs));
        }
        return array8;
    }
}
