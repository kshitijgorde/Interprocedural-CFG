// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.dsp;

public enum Window
{
    HAMMING("Hamming", "Symmetric Hamming window"), 
    HANNING("Hanning", "Symetric Hanning window without zeros on the end-points"), 
    RECTANGULAR("Rectangular", "Window with all 1s"), 
    BARTLETT_TRIANGULAR("Bartlett(Triangular)", "Symmetric Triangular Window"), 
    GAUSSIAN("Gaussian", "Gaussian Window With 2.5 Default STD");
    
    private final String name;
    private final String description;
    
    private Window(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
    
    public String toString() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public double[] getWindow(final int N) throws Exception {
        final double[] window = new double[N];
        final int thisN = N - 1;
        switch (this) {
            case RECTANGULAR: {
                for (int i = 0; i < N; ++i) {
                    window[i] = 1.0;
                }
                break;
            }
            case HAMMING: {
                for (int i = 0; i < N; ++i) {
                    window[i] = 0.54 - 0.46 * Math.cos(6.283185307179586 * i / thisN);
                }
                break;
            }
            case HANNING: {
                for (int i = 0; i < N; ++i) {
                    window[i] = 0.5 * (1.0 - Math.cos(6.283185307179586 * (i + 1) / (N + 1)));
                }
                break;
            }
            case BARTLETT_TRIANGULAR: {
                for (int i = 0; i < N / 2; ++i) {
                    window[i] = 2.0 * i / thisN;
                }
                for (int i = N / 2; i < N; ++i) {
                    window[i] = 2.0 - 2.0 * i / thisN;
                }
                break;
            }
            case GAUSSIAN: {
                final double a = 2.5;
                for (int j = 0; j < N; ++j) {
                    final double x = j - (N - 1.0) / 2.0;
                    double arg = a * x / ((N - 1.0) / 2.0);
                    arg *= -0.5 * arg;
                    window[j] = Math.exp(arg);
                }
                break;
            }
            default: {
                throw new UnsupportedOperationException("Operation not implemented yet");
            }
        }
        return window;
    }
}
