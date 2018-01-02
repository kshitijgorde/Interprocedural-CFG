// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import java.io.IOException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.InputStream;
import com.fluendo.utils.Debug;

public class AudioSinkSA extends AudioSink
{
    private static final int BUFFER = 16384;
    private static final int SEGSIZE = 256;
    private static final int DELAY = 8000;
    private double rateDiff;
    private int delay;
    private static final boolean ZEROTRAP = true;
    private static final short BIAS = 132;
    private static final int CLIP = 32635;
    private static final byte[] exp_lut;
    private static final short[] header;
    private int headerPos;
    
    private final int toUlaw(int n) {
        if (n > 32767) {
            n = 32767;
        }
        else if (n < -32768) {
            n = -32768;
        }
        final int n2 = n >> 8 & 0x80;
        if (n2 != 0) {
            n = -n;
        }
        if (n > 32635) {
            n = 32635;
        }
        n += 132;
        final byte b = AudioSinkSA.exp_lut[n >> 7 & 0xFF];
        int n3 = ~(n2 | b << 4 | (n >> b + 3 & 0xF));
        if (n3 == 0) {
            n3 = 2;
        }
        if (n3 < 0) {
            n3 += 256;
        }
        return n3;
    }
    
    protected RingBuffer createRingBuffer() {
        return new RingBufferSA();
    }
    
    protected boolean open(final RingBuffer ringBuffer) {
        this.rateDiff = ringBuffer.rate / 8000.0;
        Debug.log(3, "rateDiff: " + this.rateDiff);
        ringBuffer.segSize = (int)(256.0 * this.rateDiff);
        ringBuffer.segSize *= ringBuffer.bps;
        ringBuffer.segTotal = (int)(16384.0 * this.rateDiff);
        ringBuffer.segTotal = ringBuffer.segTotal * ringBuffer.bps / ringBuffer.segSize;
        ringBuffer.emptySeg = new byte[ringBuffer.segSize];
        ((RingBufferSA)ringBuffer).nextSeg = ringBuffer.segSize;
        this.delay = 8000;
        return true;
    }
    
    protected boolean close(final RingBuffer ringBuffer) {
        return true;
    }
    
    protected int write(final byte[] array, final int n, final int n2) {
        System.out.println("write should not be called");
        return -1;
    }
    
    protected long delay() {
        return (int)(this.delay * this.rateDiff);
    }
    
    protected void reset() {
    }
    
    public String getFactoryName() {
        return "audiosinksa";
    }
    
    static {
        exp_lut = new byte[] { 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
        header = new short[] { 46, 115, 110, 100, 0, 0, 0, 24, 127, 255, 255, 255, 0, 0, 0, 1, 0, 0, 31, 64, 0, 0, 0, 1 };
    }
    
    private class RingBufferSA extends RingBuffer
    {
        private RingReader reader;
        private int devicePos;
        public int nextSeg;
        
        public RingBufferSA() {
            this.reader = new RingReader(this);
            this.devicePos = 0;
        }
        
        protected void startWriteThread() {
        }
        
        public synchronized boolean play() {
            final boolean play = super.play();
            this.reader.play();
            return play;
        }
        
        public synchronized boolean pause() {
            final boolean pause = super.pause();
            this.reader.pause();
            return pause;
        }
        
        public synchronized boolean stop() {
            final boolean stop = super.stop();
            this.reader.stop();
            return stop;
        }
        
        public int read() {
            final int i = (int)(this.devicePos * AudioSinkSA.this.rateDiff) * super.bps;
            if (super.segTotal == 0) {
                return -1;
            }
            while (i >= this.nextSeg) {
                synchronized (this) {
                    this.clear((int)(super.playSeg % super.segTotal));
                    ++super.playSeg;
                    this.notifyAll();
                }
                this.nextSeg += super.segSize;
            }
            int n = 0;
            int n2 = i % super.buffer.length;
            for (int j = 0; j < super.channels; ++j) {
                final byte b = super.buffer[n2];
                int n3 = super.buffer[n2 + 1];
                if (n3 < 0) {
                    n3 += 256;
                }
                n += (b * 256 | n3);
                n2 += 2;
            }
            final int n4 = n / super.channels;
            ++this.devicePos;
            return AudioSinkSA.this.toUlaw(n4);
        }
    }
    
    private class RingReader extends InputStream
    {
        private AudioStream stream;
        private RingBufferSA ringBuffer;
        
        public RingReader(final RingBufferSA ringBuffer) {
            this.ringBuffer = ringBuffer;
            try {
                AudioSinkSA.this.headerPos = 0;
                this.stream = new AudioStream(this);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public synchronized boolean play() {
            AudioPlayer.player.start(this.stream);
            return true;
        }
        
        public synchronized boolean pause() {
            AudioPlayer.player.stop(this.stream);
            return true;
        }
        
        public synchronized boolean stop() {
            AudioPlayer.player.stop(this.stream);
            return true;
        }
        
        public int read() throws IOException {
            int read;
            if (AudioSinkSA.this.headerPos < AudioSinkSA.header.length) {
                read = AudioSinkSA.header[AudioSinkSA.this.headerPos++];
            }
            else {
                read = this.ringBuffer.read();
            }
            return read;
        }
    }
}
