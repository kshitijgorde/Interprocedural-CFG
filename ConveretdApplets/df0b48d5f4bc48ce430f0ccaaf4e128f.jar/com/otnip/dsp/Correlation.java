// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.dsp;

public class Correlation
{
    private int N;
    private int M;
    private int fftLength;
    private double[] x1;
    
    public Correlation(final int N, final int M) throws Exception {
        this.N = N;
        this.M = M;
        final int total = N + M;
        final double minPower = Math.ceil(Math.log(total) / Math.log(2.0));
        final double realTotal = Math.pow(2.0, minPower);
        this.fftLength = (int)realTotal;
        this.x1 = new double[2 * this.fftLength + 1];
    }
    
    public Correlation(final int N) throws Exception {
        this(N, N - 1);
    }
    
    public double[] corr(final double[] x, final double[] y, double[] r) throws Exception {
        final int n = this.fftLength;
        final int nn4;
        final int nn3 = 1 + (nn4 = 2 + n + n);
        int dataIndex = 1;
        int sourceIndex = 0;
        while (dataIndex <= this.x1.length - 1) {
            if (sourceIndex < x.length) {
                this.x1[dataIndex++] = x[sourceIndex];
            }
            else {
                this.x1[dataIndex++] = 0.0;
            }
            if (sourceIndex < y.length) {
                this.x1[dataIndex++] = y[sourceIndex++];
            }
            else {
                this.x1[dataIndex++] = 0.0;
                ++sourceIndex;
            }
        }
        FFT.fft(this.x1, this.fftLength, 1);
        this.x1[1] *= this.x1[2];
        this.x1[2] = 0.0;
        for (int j = 3; j <= n + 1; j += 2) {
            final double rep = 0.5 * (this.x1[j] + this.x1[nn4 - j]);
            final double rem = 0.5 * (this.x1[j] - this.x1[nn4 - j]);
            final double aip = 0.5 * (this.x1[j + 1] + this.x1[nn3 - j]);
            final double aim = 0.5 * (this.x1[j + 1] - this.x1[nn3 - j]);
            this.x1[j] = rep * aip - aim * rem;
            this.x1[j + 1] = rep * rem + aim * aip;
            this.x1[nn4 - j] = rep * aip - aim * rem;
            this.x1[nn3 - j] = -rep * rem - aim * aip;
        }
        FFT.fft(this.x1, this.fftLength, -1);
        if (r == null) {
            r = new double[2 * this.M + 1];
        }
        dataIndex = 1;
        r[this.M] = this.x1[dataIndex] / this.fftLength;
        int posSourceIndex = 3;
        int negSourceIndex = this.x1.length - 2;
        int posDestIndex = this.M + 1;
        int negDestIndex = this.M - 1;
        for (int i = 0; i < this.M; ++i) {
            r[posDestIndex++] = this.x1[posSourceIndex] / this.fftLength;
            r[negDestIndex--] = this.x1[negSourceIndex] / this.fftLength;
            posSourceIndex += 2;
            negSourceIndex -= 2;
        }
        return r;
    }
}
