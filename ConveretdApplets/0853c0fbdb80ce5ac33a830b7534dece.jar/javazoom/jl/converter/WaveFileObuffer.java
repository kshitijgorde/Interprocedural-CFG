// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.converter;

import javazoom.jl.decoder.Obuffer;

public class WaveFileObuffer extends Obuffer
{
    private short[] buffer;
    private short[] bufferp;
    private int channels;
    private WaveFile outWave;
    short[] myBuffer;
    
    public WaveFileObuffer(final int channels, final int n, final String s) {
        this.myBuffer = new short[2];
        if (s == null) {
            throw new NullPointerException("FileName");
        }
        this.buffer = new short[2304];
        this.bufferp = new short[2];
        this.channels = channels;
        for (int i = 0; i < channels; ++i) {
            this.bufferp[i] = (short)i;
        }
        (this.outWave = new WaveFile()).OpenForWrite(s, n, (short)16, (short)this.channels);
    }
    
    public void append(final int n, final short n2) {
        this.buffer[this.bufferp[n]] = n2;
        final short[] bufferp = this.bufferp;
        bufferp[n] += (short)this.channels;
    }
    
    public void write_buffer(final int n) {
        this.outWave.WriteData(this.buffer, this.bufferp[0]);
        for (int i = 0; i < this.channels; ++i) {
            this.bufferp[i] = (short)i;
        }
    }
    
    public void close() {
        this.outWave.Close();
    }
    
    public void clear_buffer() {
    }
    
    public void set_stop_flag() {
    }
}
