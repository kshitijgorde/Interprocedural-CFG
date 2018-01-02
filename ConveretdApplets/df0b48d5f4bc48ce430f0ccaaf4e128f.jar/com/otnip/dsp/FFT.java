// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.dsp;

public class FFT
{
    private double[] data;
    private double[] window;
    private int N;
    private Window currentWindow;
    
    public FFT(final int N, final Window window) throws Exception {
        this.data = null;
        this.window = null;
        this.N = 0;
        final double exp = Math.round(Math.log(N) / Math.log(2.0));
        if (N - Math.pow(2.0, exp) != 0.0) {
            throw new Exception("FFT length must be a power of 2 (current length: " + N + ")");
        }
        this.N = N;
        this.setWindow(window);
        this.data = new double[2 * N + 1];
    }
    
    public FFT(final int N) throws Exception {
        this(N, Window.RECTANGULAR);
    }
    
    public void setWindow(final Window type) throws Exception {
        this.currentWindow = type;
        this.window = this.currentWindow.getWindow(this.N);
    }
    
    public double[] prepareData(final double[] source, final int startIndex, final int length) {
        int dataIndex = 1;
        for (int i = 0; i < length; ++i) {
            this.data[dataIndex++] = source[startIndex + i];
            this.data[dataIndex++] = 0.0;
        }
        for (int i = 1 + 2 * length; i < this.data.length; ++i) {
            this.data[i] = 0.0;
        }
        return this.data;
    }
    
    public double[] getPSD(final double[] x, final int startIndex, double[] psd, final boolean useWindow) throws Exception {
        if (psd == null) {
            psd = new double[this.N / 2 + 1];
        }
        if (psd.length > this.N) {
            throw new Exception("PSD placeholder is greater than the FFT length.");
        }
        int dataIndex = 1;
        final int lastIndex = startIndex + this.N;
        if (useWindow) {
            int windowIndex = 0;
            for (int sourceIndex = startIndex; sourceIndex < lastIndex; ++sourceIndex) {
                this.data[dataIndex] = x[sourceIndex] * this.window[windowIndex++];
                ++dataIndex;
                this.data[dataIndex++] = 0.0;
            }
        }
        else {
            for (int sourceIndex2 = startIndex; sourceIndex2 < lastIndex; ++sourceIndex2) {
                this.data[dataIndex++] = x[sourceIndex2];
                this.data[dataIndex++] = 0.0;
            }
        }
        fft(this.data, this.N, 1);
        double scale = 1.0 / (this.N / 2.0);
        scale *= scale;
        final int psdLength = psd.length;
        dataIndex = 1;
        for (int psdIndex = 0; psdIndex < psdLength; ++psdIndex) {
            final double a = this.data[dataIndex++];
            final double b = this.data[dataIndex++];
            psd[psdIndex] = scale * (a * a + b * b);
        }
        return psd;
    }
    
    public double[] getPSDLog(final double[] x, double[] psd, final boolean useWindow) throws Exception {
        if (x.length != this.N) {
            throw new Exception("FFT length must be a power of 2 (current length:  + " + this.N + ")");
        }
        if (psd == null) {
            psd = new double[this.N / 2 + 1];
        }
        if (psd.length > this.N) {
            throw new Exception("PSD is greater than FFT length.");
        }
        int dataIndex = 1;
        if (useWindow) {
            for (int sourceIndex = 0; sourceIndex < this.N; ++sourceIndex) {
                this.data[dataIndex++] = x[sourceIndex] * this.window[sourceIndex];
                this.data[dataIndex++] = 0.0;
            }
        }
        else {
            for (int sourceIndex = 0; sourceIndex < this.N; ++sourceIndex) {
                this.data[dataIndex++] = x[sourceIndex];
                this.data[dataIndex++] = 0.0;
            }
        }
        fft(this.data, this.N, 1);
        final double scale = 1.0 / (this.N / 2.0);
        final int psdLength = psd.length;
        dataIndex = 1;
        for (int psdIndex = 0; psdIndex < psdLength; ++psdIndex) {
            final double a = this.data[dataIndex++];
            final double b = this.data[dataIndex++];
            psd[psdIndex] = Math.log1p(scale * (a * a + b * b));
        }
        return psd;
    }
    
    public int length() {
        return this.N;
    }
    
    public static double[] fft(final double[] data, final int nn, int isign) {
        final double PI2 = 6.283185307179586;
        isign *= -1;
        final int n = nn << 1;
        int j = 1;
        for (int i = 1; i < n; i += 2) {
            if (j > i) {
                double temp = data[j];
                data[j] = data[i];
                data[i] = temp;
                temp = data[j + 1];
                data[j + 1] = data[i + 1];
                data[i + 1] = temp;
            }
            int m;
            for (m = nn; m >= 2 && j > m; j -= m, m >>= 1) {}
            j += m;
        }
        int istep;
        for (int mmax = 2; n > mmax; mmax = istep) {
            istep = mmax << 1;
            final double theta = isign * (6.283185307179586 / mmax);
            double wtemp = Math.sin(0.5 * theta);
            final double wpr = -2.0 * wtemp * wtemp;
            final double wpi = Math.sin(theta);
            double wr = 1.0;
            double wi = 0.0;
            for (int m = 1; m < mmax; m += 2) {
                for (int i = m; i <= n; i += istep) {
                    j = i + mmax;
                    final double tempr = wr * data[j] - wi * data[j + 1];
                    final double tempi = wr * data[j + 1] + wi * data[j];
                    data[j] = data[i] - tempr;
                    data[j + 1] = data[i + 1] - tempi;
                    final int n2 = i;
                    data[n2] += tempr;
                    final int n3 = i + 1;
                    data[n3] += tempi;
                }
                wr += (wtemp = wr) * wpr - wi * wpi;
                wi += wi * wpr + wtemp * wpi;
            }
        }
        return data;
    }
}
