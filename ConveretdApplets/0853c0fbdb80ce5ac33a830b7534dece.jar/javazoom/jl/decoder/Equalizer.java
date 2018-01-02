// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

public final class Equalizer
{
    public static final float BAND_NOT_PRESENT = Float.NEGATIVE_INFINITY;
    public static final Equalizer PASS_THRU_EQ;
    private static final int BANDS = 32;
    private final float[] settings;
    
    public Equalizer() {
        this.settings = new float[32];
    }
    
    public Equalizer(final float[] from) {
        this.settings = new float[32];
        this.setFrom(from);
    }
    
    public Equalizer(final EQFunction from) {
        this.settings = new float[32];
        this.setFrom(from);
    }
    
    public void setFrom(final float[] array) {
        this.reset();
        for (int n = (array.length > 32) ? 32 : array.length, i = 0; i < n; ++i) {
            this.settings[i] = this.limit(array[i]);
        }
    }
    
    public void setFrom(final EQFunction eqFunction) {
        this.reset();
        for (int n = 32, i = 0; i < n; ++i) {
            this.settings[i] = this.limit(eqFunction.getBand(i));
        }
    }
    
    public void setFrom(final Equalizer equalizer) {
        if (equalizer != this) {
            this.setFrom(equalizer.settings);
        }
    }
    
    public void reset() {
        for (int i = 0; i < 32; ++i) {
            this.settings[i] = 0.0f;
        }
    }
    
    public int getBandCount() {
        return this.settings.length;
    }
    
    public float setBand(final int n, final float n2) {
        float n3 = 0.0f;
        if (n >= 0 && n < 32) {
            n3 = this.settings[n];
            this.settings[n] = this.limit(n2);
        }
        return n3;
    }
    
    public float getBand(final int n) {
        float n2 = 0.0f;
        if (n >= 0 && n < 32) {
            n2 = this.settings[n];
        }
        return n2;
    }
    
    private float limit(final float n) {
        if (n == Float.NEGATIVE_INFINITY) {
            return n;
        }
        if (n > 1.0f) {
            return 1.0f;
        }
        if (n < -1.0f) {
            return -1.0f;
        }
        return n;
    }
    
    float[] getBandFactors() {
        final float[] array = new float[32];
        for (int i = 0; i < 32; ++i) {
            array[i] = this.getBandFactor(this.settings[i]);
        }
        return array;
    }
    
    float getBandFactor(final float n) {
        if (n == Float.NEGATIVE_INFINITY) {
            return 0.0f;
        }
        return (float)Math.pow(2.0, n);
    }
    
    static {
        PASS_THRU_EQ = new Equalizer();
    }
    
    public abstract static class EQFunction
    {
        public float getBand(final int n) {
            return 0.0f;
        }
    }
}
