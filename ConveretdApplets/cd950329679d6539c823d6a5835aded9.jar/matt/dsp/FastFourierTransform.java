// 
// Decompiled by Procyon v0.5.30
// 

package matt.dsp;

public final class FastFourierTransform
{
    private int n;
    private int nu;
    
    private int bitrev(final int j) {
        int j2 = j;
        int k = 0;
        for (int i = 1; i <= this.nu; ++i) {
            final int j3 = j2 / 2;
            k = 2 * k + j2 - 2 * j3;
            j2 = j3;
        }
        return k;
    }
    
    public final float[] fftMag(final float[] x) {
        return this.fftMag(x, 0, x.length);
    }
    
    public final float[] fftLogMag(final float[] x) {
        return this.fftLogMag(x, 0, x.length);
    }
    
    public final float[] fftMag(final float[] x, final int start, final int length) {
        this.n = length;
        this.nu = (int)(Math.log(this.n) / Math.log(2.0));
        int n2 = this.n / 2;
        int nu1 = this.nu - 1;
        final float[] xre = new float[this.n];
        final float[] xim = new float[this.n];
        final float[] mag = new float[n2];
        for (int i = 0; i < this.n; ++i) {
            xre[i] = x[i + start];
            xim[i] = 0.0f;
        }
        int k = 0;
        for (int l = 1; l <= this.nu; ++l) {
            while (k < this.n) {
                for (int j = 1; j <= n2; ++j) {
                    final float p = this.bitrev(k >> nu1);
                    final float arg = 6.2831855f * p / this.n;
                    final float c = (float)Math.cos(arg);
                    final float s = (float)Math.sin(arg);
                    final float tr = xre[k + n2] * c + xim[k + n2] * s;
                    final float ti = xim[k + n2] * c - xre[k + n2] * s;
                    xre[k + n2] = xre[k] - tr;
                    xim[k + n2] = xim[k] - ti;
                    final float[] array = xre;
                    final int n3 = k;
                    array[n3] += tr;
                    final float[] array2 = xim;
                    final int n4 = k;
                    array2[n4] += ti;
                    ++k;
                }
                k += n2;
            }
            k = 0;
            --nu1;
            n2 /= 2;
        }
        for (k = 0; k < this.n; ++k) {
            final int r = this.bitrev(k);
            if (r > k) {
                final float tr = xre[k];
                final float ti = xim[k];
                xre[k] = xre[r];
                xim[k] = xim[r];
                xre[r] = tr;
                xim[r] = ti;
            }
        }
        mag[0] = (float)Math.sqrt(xre[0] * xre[0] + xim[0] * xim[0]) / this.n;
        for (int j = 1; j < this.n / 2; ++j) {
            mag[j] = 2.0f * (float)Math.sqrt(xre[j] * xre[j] + xim[j] * xim[j]) / this.n;
        }
        return mag;
    }
    
    public final float[] fftLogMag(final float[] x, final int start, final int length) {
        this.n = length;
        this.nu = (int)(Math.log(this.n) / Math.log(2.0));
        int n2 = this.n / 2;
        int nu1 = this.nu - 1;
        final float[] xre = new float[this.n];
        final float[] xim = new float[this.n];
        final float[] mag = new float[n2];
        for (int i = 0; i < this.n; ++i) {
            xre[i] = (float)Math.log(x[i + start]);
            xim[i] = 0.0f;
        }
        int k = 0;
        for (int l = 1; l <= this.nu; ++l) {
            while (k < this.n) {
                for (int j = 1; j <= n2; ++j) {
                    final float p = this.bitrev(k >> nu1);
                    final float arg = 6.2831855f * p / this.n;
                    final float c = (float)Math.cos(arg);
                    final float s = (float)Math.sin(arg);
                    final float tr = xre[k + n2] * c + xim[k + n2] * s;
                    final float ti = xim[k + n2] * c - xre[k + n2] * s;
                    xre[k + n2] = xre[k] - tr;
                    xim[k + n2] = xim[k] - ti;
                    final float[] array = xre;
                    final int n3 = k;
                    array[n3] += tr;
                    final float[] array2 = xim;
                    final int n4 = k;
                    array2[n4] += ti;
                    ++k;
                }
                k += n2;
            }
            k = 0;
            --nu1;
            n2 /= 2;
        }
        for (k = 0; k < this.n; ++k) {
            final int r = this.bitrev(k);
            if (r > k) {
                final float tr = xre[k];
                final float ti = xim[k];
                xre[k] = xre[r];
                xim[k] = xim[r];
                xre[r] = tr;
                xim[r] = ti;
            }
        }
        mag[0] = (float)Math.sqrt(xre[0] * xre[0] + xim[0] * xim[0]) / this.n;
        for (int j = 1; j < this.n / 2; ++j) {
            mag[j] = 2.0f * (float)Math.sqrt(xre[j] * xre[j] + xim[j] * xim[j]) / this.n;
        }
        return mag;
    }
    
    public static int smallestPowerOf2(final int value) {
        int i = 0;
        for (float nearest = 0.0f; value > nearest; nearest = (float)Math.pow(2.0, i)) {
            ++i;
        }
        return i - 1;
    }
    
    public void printFft(final float[] fft, final float sampleRate) {
        final float binWidth = fft.length / sampleRate;
        System.out.println("Frame size: " + fft.length);
        for (int i = 0; i < fft.length; ++i) {
            System.out.println(i * binWidth + "\t" + fft[i]);
        }
    }
    
    public static void main(final String[] args) {
        final int i = 10;
        System.out.println(smallestPowerOf2(i));
    }
}
