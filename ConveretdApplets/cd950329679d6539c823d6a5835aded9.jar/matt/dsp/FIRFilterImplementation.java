// 
// Decompiled by Procyon v0.5.30
// 

package matt.dsp;

public class FIRFilterImplementation
{
    public static final int LP = 1;
    public static final int BP = 2;
    public static final int HP = 3;
    static int taps;
    float[] a;
    int filterType;
    
    public FIRFilterImplementation() {
        this.a = new float[FIRFilterImplementation.taps];
    }
    
    public void setFilterType(final int ft) {
        this.filterType = ft;
    }
    
    public float[] filter(final float[] ip) {
        final int nSamples = ip.length;
        final float[] x = new float[FIRFilterImplementation.taps];
        final float[] op = new float[nSamples];
        switch (this.filterType) {
            case 1: {
                this.a[0] = 0.020934949f;
                this.a[1] = 0.022163745f;
                this.a[2] = 0.023352664f;
                this.a[3] = 0.02449582f;
                this.a[4] = 0.025587523f;
                this.a[5] = 0.026622318f;
                this.a[6] = 0.027594987f;
                this.a[7] = 0.028500626f;
                this.a[8] = 0.02933464f;
                this.a[9] = 0.030092772f;
                this.a[10] = 0.03077116f;
                this.a[11] = 0.03136633f;
                this.a[12] = 0.031875215f;
                this.a[13] = 0.03229521f;
                this.a[14] = 0.03262414f;
                this.a[15] = 0.03286031f;
                this.a[16] = 0.033002503f;
                this.a[17] = 0.033049982f;
                this.a[18] = 0.033002503f;
                this.a[19] = 0.03286031f;
                this.a[20] = 0.03262414f;
                this.a[21] = 0.03229521f;
                this.a[22] = 0.031875215f;
                this.a[23] = 0.03136633f;
                this.a[24] = 0.03077116f;
                this.a[25] = 0.030092772f;
                this.a[26] = 0.02933464f;
                this.a[27] = 0.028500626f;
                this.a[28] = 0.027594987f;
                this.a[29] = 0.026622318f;
                this.a[30] = 0.025587523f;
                this.a[31] = 0.02449582f;
                this.a[32] = 0.023352664f;
                this.a[33] = 0.022163745f;
                this.a[34] = 0.020934949f;
                break;
            }
            case 2: {
                this.a[0] = (this.a[34] = -0.006238957f);
                this.a[1] = (this.a[33] = 0.0f);
                this.a[2] = (this.a[32] = 0.001389077f);
                this.a[3] = (this.a[31] = -0.007818848f);
                this.a[4] = (this.a[30] = -0.0225176f);
                this.a[5] = (this.a[29] = -0.02874291f);
                this.a[6] = (this.a[28] = -0.01432263f);
                this.a[7] = (this.a[27] = 0.01935535f);
                this.a[8] = (this.a[26] = 0.0541791f);
                this.a[9] = (this.a[25] = 0.06508222f);
                this.a[10] = (this.a[24] = 0.03747954f);
                this.a[11] = (this.a[23] = -0.01967865f);
                this.a[12] = (this.a[22] = -0.07569528f);
                this.a[13] = (this.a[21] = -0.09558527f);
                this.a[14] = (this.a[20] = -0.0627654f);
                this.a[15] = (this.a[19] = 0.008218622f);
                this.a[16] = (this.a[18] = 0.07842754f);
                this.a[17] = 0.1074919f;
                break;
            }
            case 3: {
                this.a[0] = (this.a[34] = -3.67104E-4f);
                this.a[1] = (this.a[33] = -0.001433603f);
                this.a[2] = (this.a[32] = -2.698704E-4f);
                this.a[3] = (this.a[31] = 0.003431724f);
                this.a[4] = (this.a[30] = 0.003629797f);
                this.a[5] = (this.a[29] = -0.003950445f);
                this.a[6] = (this.a[28] = -0.01020383f);
                this.a[7] = (this.a[27] = -0.001033758f);
                this.a[8] = (this.a[26] = 0.01707373f);
                this.a[9] = (this.a[25] = 0.01517157f);
                this.a[10] = (this.a[24] = -0.0168595f);
                this.a[11] = (this.a[23] = -0.03861338f);
                this.a[12] = (this.a[22] = -0.001937564f);
                this.a[13] = (this.a[21] = 0.06617677f);
                this.a[14] = (this.a[20] = 0.05988269f);
                this.a[15] = (this.a[19] = -0.0887071f);
                this.a[16] = (this.a[18] = -0.3004827f);
                this.a[17] = 0.5962756f;
                break;
            }
        }
        for (int k = 1; k < FIRFilterImplementation.taps; ++k) {
            x[k] = 0.0f;
        }
        for (int i = 0; i < nSamples; ++i) {
            x[0] = ip[i];
            float y = 0.0f;
            for (int j = 0; j < FIRFilterImplementation.taps; ++j) {
                y += this.a[j] * x[j];
            }
            op[i] = y;
            for (int j = FIRFilterImplementation.taps - 1; j > 0; --j) {
                x[j] = x[j - 1];
            }
        }
        return op;
    }
    
    static {
        FIRFilterImplementation.taps = 35;
    }
}
