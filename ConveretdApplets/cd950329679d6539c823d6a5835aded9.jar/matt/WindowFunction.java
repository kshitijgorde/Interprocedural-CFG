// 
// Decompiled by Procyon v0.5.30
// 

package matt;

public class WindowFunction
{
    public static final int RECTANGULAR = 0;
    public static final int BARTLETT = 1;
    public static final int HANNING = 2;
    public static final int HAMMING = 3;
    public static final int BLACKMAN = 4;
    public static final int KAISER = 5;
    int windowType;
    
    public WindowFunction() {
        this.windowType = 0;
    }
    
    public void setWindowType(final int wt) {
        this.windowType = wt;
    }
    
    public void setWindowType(final String w) {
        if (w.equals("Rectangular")) {
            this.windowType = 0;
        }
        if (w.equals("Bartlett")) {
            this.windowType = 1;
        }
        if (w.equals("Hanning")) {
            this.windowType = 2;
        }
        if (w.equals("Hamming")) {
            this.windowType = 3;
        }
        if (w.equals("Blackman")) {
            this.windowType = 4;
        }
    }
    
    public int getWindowType() {
        return this.windowType;
    }
    
    public float[] generate(final int nSamples) {
        final int m = nSamples / 2;
        final float pi = 3.1415927f;
        final float[] w = new float[nSamples];
        switch (this.windowType) {
            case 1: {
                for (int n = 0; n < nSamples; ++n) {
                    w[n] = 1.0f - Math.abs(n - m) / m;
                }
                break;
            }
            case 2: {
                final float r = pi / (m + 1);
                for (int n = -m; n < m; ++n) {
                    w[m + n] = 0.5f + 0.5f * (float)Math.cos(n * r);
                }
                break;
            }
            case 3: {
                final float r = pi / m;
                for (int n = -m; n < m; ++n) {
                    w[m + n] = 0.54f + 0.46f * (float)Math.cos(n * r);
                }
                break;
            }
            case 4: {
                final float r = pi / m;
                for (int n = -m; n < m; ++n) {
                    w[m + n] = 0.42f + 0.5f * (float)Math.cos(n * r) + 0.08f * (float)Math.cos(2 * n * r);
                }
                break;
            }
            default: {
                for (int n = 0; n < nSamples; ++n) {
                    w[n] = 1.0f;
                }
                break;
            }
        }
        return w;
    }
}
