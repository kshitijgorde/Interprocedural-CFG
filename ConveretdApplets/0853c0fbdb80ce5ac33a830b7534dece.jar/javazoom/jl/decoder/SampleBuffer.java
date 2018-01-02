// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

public class SampleBuffer extends Obuffer
{
    private short[] buffer;
    private int[] bufferp;
    private int channels;
    private int frequency;
    
    public SampleBuffer(final int frequency, final int channels) {
        this.buffer = new short[2304];
        this.bufferp = new int[2];
        this.channels = channels;
        this.frequency = frequency;
        for (int i = 0; i < channels; ++i) {
            this.bufferp[i] = (short)i;
        }
    }
    
    public int getChannelCount() {
        return this.channels;
    }
    
    public int getSampleFrequency() {
        return this.frequency;
    }
    
    public short[] getBuffer() {
        return this.buffer;
    }
    
    public int getBufferLength() {
        return this.bufferp[0];
    }
    
    public void append(final int n, final short n2) {
        this.buffer[this.bufferp[n]] = n2;
        final int[] bufferp = this.bufferp;
        bufferp[n] += this.channels;
    }
    
    public void appendSamples(final int n, final float[] array) {
        int n2 = this.bufferp[n];
        float n3;
        for (int i = 0; i < 32; n3 = array[i++], this.buffer[n2] = (short)((n3 > 32767.0f) ? 32767.0f : ((n3 < -32767.0f) ? -32767.0f : n3)), n2 += this.channels) {}
        this.bufferp[n] = n2;
    }
    
    public void write_buffer(final int n) {
    }
    
    public void close() {
    }
    
    public void clear_buffer() {
        for (int i = 0; i < this.channels; ++i) {
            this.bufferp[i] = (short)i;
        }
    }
    
    public void set_stop_flag() {
    }
}
