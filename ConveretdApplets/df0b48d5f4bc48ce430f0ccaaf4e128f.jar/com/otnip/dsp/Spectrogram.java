// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.dsp;

public class Spectrogram
{
    public static double[][] createSpectrogramLog(final double[] x, final int fftLength, final Window window, final int increment) throws Exception {
        final int Nfft = (int)Math.floor((x.length - fftLength) / increment);
        final double[][] B = new double[Nfft][fftLength / 2 + 1];
        final double[] x2 = new double[fftLength];
        final FFT fft = new FFT(fftLength, window);
        for (int i = 0; i < Nfft; ++i) {
            System.arraycopy(x, i * increment, x2, 0, fftLength);
            fft.getPSDLog(x2, B[i], true);
        }
        return B;
    }
    
    public static double[][] createSpectrogram(final double[] x, final int fftLength, final Window window, final int increment) throws Exception {
        final int Nfft = (int)Math.floor((x.length - fftLength) / increment);
        final double[][] B = new double[Nfft][fftLength / 2 + 1];
        final double[] x2 = new double[fftLength];
        final FFT fft = new FFT(fftLength, window);
        for (int i = 0; i < Nfft; ++i) {
            fft.getPSD(x, i * increment, B[i], true);
        }
        return B;
    }
}
