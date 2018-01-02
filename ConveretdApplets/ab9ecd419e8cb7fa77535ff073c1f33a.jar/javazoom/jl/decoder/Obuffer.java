// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

public abstract class Obuffer
{
    public static final int OBUFFERSIZE = 2304;
    public static final int MAXCHANNELS = 2;
    
    public abstract void append(final int p0, final short p1);
    
    public void appendSamples(final int n, final float[] array) {
        int i = 0;
        while (i < 32) {
            this.append(n, this.clip(array[i++]));
        }
    }
    
    private final short clip(final float n) {
        return (short)((n > 32767.0f) ? 32767 : ((n < -32768.0f) ? -32768 : ((short)n)));
    }
    
    public abstract void write_buffer(final int p0);
    
    public abstract void close();
    
    public abstract void clear_buffer();
    
    public abstract void set_stop_flag();
}
