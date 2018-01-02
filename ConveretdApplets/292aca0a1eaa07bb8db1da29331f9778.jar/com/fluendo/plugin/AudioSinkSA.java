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
    
    private final int toUlaw(int sample) {
        if (sample > 32767) {
            sample = 32767;
        }
        else if (sample < -32768) {
            sample = -32768;
        }
        final int sign = sample >> 8 & 0x80;
        if (sign != 0) {
            sample = -sample;
        }
        if (sample > 32635) {
            sample = 32635;
        }
        sample += 132;
        final int exponent = AudioSinkSA.exp_lut[sample >> 7 & 0xFF];
        final int mantissa = sample >> exponent + 3 & 0xF;
        int ulawbyte = ~(sign | exponent << 4 | mantissa);
        if (ulawbyte == 0) {
            ulawbyte = 2;
        }
        if (ulawbyte < 0) {
            ulawbyte += 256;
        }
        return ulawbyte;
    }
    
    protected RingBuffer createRingBuffer() {
        return new RingBufferSA();
    }
    
    protected boolean open(final RingBuffer ring) {
        this.rateDiff = ring.rate / 8000.0;
        Debug.log(3, "rateDiff: " + this.rateDiff);
        ring.segSize = (int)(256.0 * this.rateDiff);
        ring.segSize *= ring.bps;
        ring.segTotal = (int)(16384.0 * this.rateDiff);
        ring.segTotal = ring.segTotal * ring.bps / ring.segSize;
        ring.emptySeg = new byte[ring.segSize];
        ((RingBufferSA)ring).nextSeg = ring.segSize;
        this.delay = 8000;
        return true;
    }
    
    protected boolean close(final RingBuffer ring) {
        return true;
    }
    
    protected int write(final byte[] data, final int offset, final int length) {
        System.out.println("write should not be called");
        return -1;
    }
    
    protected long delay() {
        final long ret = (int)(this.delay * this.rateDiff);
        return ret;
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
    
    private class RingReader extends InputStream
    {
        private AudioStream stream;
        private RingBufferSA ringBuffer;
        
        public RingReader(final RingBufferSA rb) {
            this.ringBuffer = rb;
            try {
                AudioSinkSA.this.headerPos = 0;
                this.stream = new AudioStream(this);
            }
            catch (Exception e) {
                e.printStackTrace();
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
            int result;
            if (AudioSinkSA.this.headerPos < AudioSinkSA.header.length) {
                result = AudioSinkSA.header[AudioSinkSA.this.headerPos++];
            }
            else {
                result = this.ringBuffer.read();
            }
            return result;
        }
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
            final boolean res = super.play();
            this.reader.play();
            return res;
        }
        
        public synchronized boolean pause() {
            final boolean res = super.pause();
            this.reader.pause();
            return res;
        }
        
        public synchronized boolean stop() {
            final boolean res = super.stop();
            this.reader.stop();
            return res;
        }
        
        public int read() {
            final int ringPos = (int)(this.devicePos * AudioSinkSA.this.rateDiff) * this.bps;
            if (this.segTotal == 0) {
                return -1;
            }
            while (ringPos >= this.nextSeg) {
                synchronized (this) {
                    this.clear((int)(this.playSeg % this.segTotal));
                    ++this.playSeg;
                    this.notifyAll();
                }
                this.nextSeg += this.segSize;
            }
            int sample = 0;
            int ptr = ringPos % this.buffer.length;
            for (int j = 0; j < this.channels; ++j) {
                final int b1 = this.buffer[ptr];
                int b2 = this.buffer[ptr + 1];
                if (b2 < 0) {
                    b2 += 256;
                }
                sample += (b1 * 256 | b2);
                ptr += 2;
            }
            sample /= this.channels;
            ++this.devicePos;
            return AudioSinkSA.this.toUlaw(sample);
        }
    }
}
