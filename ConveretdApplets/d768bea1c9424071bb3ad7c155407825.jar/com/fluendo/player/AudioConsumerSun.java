// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import com.fluendo.utils.Debug;
import sun.audio.AudioStream;
import java.util.Vector;

public class AudioConsumerSun implements Runnable, DataConsumer, ClockProvider
{
    private int queueid;
    private boolean ready;
    private Clock clock;
    private static final int MAX_BUFFER = 5;
    private boolean stopping;
    private Plugin plugin;
    private long queuedTime;
    private Vector preQueue;
    private boolean preQueueing;
    private long samplesQueued;
    private long sampleCount;
    private long nextSampleCount;
    private AudioBuffer audioBuffer;
    private AudioStream audioStream;
    private static final long DEVICE_BUFFER = 8192L;
    private static final boolean ZEROTRAP = true;
    private static final short BIAS = 132;
    private static final int CLIP = 32635;
    private static final byte[] exp_lut;
    private static final int[] header;
    
    public AudioConsumerSun(final Clock clock) {
        this.stopping = false;
        this.queuedTime = -1L;
        this.preQueue = new Vector();
        this.preQueueing = true;
        this.samplesQueued = 0L;
        this.sampleCount = 0L;
        this.nextSampleCount = 0L;
        this.queueid = QueueManager.registerQueue(5);
        Debug.log(3, "audio on queue " + this.queueid);
        this.clock = clock;
    }
    
    public boolean isReady() {
        return this.ready;
    }
    
    public long getQueuedTime() {
        return this.queuedTime - 1024L;
    }
    
    public void stop() {
        this.stopping = true;
        QueueManager.unRegisterQueue(this.queueid);
        AudioPlayer.player.stop(this.audioStream);
    }
    
    public void run() {
        try {
            this.realRun();
        }
        catch (Throwable t) {
            Cortado.shutdown(t);
        }
    }
    
    public void setPlugin(final Plugin plugin) {
        this.plugin = plugin;
    }
    
    public void consume(final MediaBuffer mediaBuffer) {
        try {
            QueueManager.enqueue(this.queueid, mediaBuffer);
        }
        catch (Exception ex) {
            if (!this.stopping) {
                ex.printStackTrace();
            }
        }
    }
    
    public long getTime() {
        return 0L;
    }
    
    public void checkClockAdjust() {
        if (this.audioBuffer == null) {
            return;
        }
        final long n = this.clock.getMediaTime() - (this.audioBuffer.getFramePosition() * 1000L / 8000L + this.queuedTime);
        final long abs = Math.abs(n);
        final long n2 = 100L;
        if (abs > n2) {
            final long n3 = (long)(Math.log(abs - n2) * 20.0);
            if (n > 0L) {
                this.clock.updateAdjust(-n3);
            }
            else if (n < 0L) {
                this.clock.updateAdjust(n3);
            }
        }
    }
    
    private void handlePrequeue(final MediaBuffer mediaBuffer) {
        this.samplesQueued += mediaBuffer.length / (2 * this.plugin.channels);
        this.preQueue.addElement(mediaBuffer);
        if (mediaBuffer.time_offset != -1L || mediaBuffer.time_offset != -1L) {
            final MediaBuffer mediaBuffer2 = this.preQueue.elementAt(0);
            if (mediaBuffer.timestamp == -1L) {
                mediaBuffer.timestamp = this.plugin.offsetToTime(mediaBuffer.time_offset);
            }
            mediaBuffer2.timestamp = mediaBuffer.timestamp - this.samplesQueued * 1000L / this.plugin.rate;
            this.audioBuffer = new AudioBuffer(100000, this.plugin.rate, this.plugin.channels);
            this.queuedTime = mediaBuffer2.timestamp;
            try {
                for (int i = 0; i < this.preQueue.size(); ++i) {
                    final MediaBuffer mediaBuffer3 = this.preQueue.elementAt(i);
                    this.audioBuffer.write(mediaBuffer3.data, mediaBuffer3.offset, mediaBuffer3.length);
                    this.sampleCount += mediaBuffer3.length / (2 * this.plugin.channels);
                    mediaBuffer3.free();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.preQueue.setSize(0);
            this.preQueueing = false;
            this.samplesQueued = 0L;
            if (!this.ready) {
                try {
                    synchronized (this.clock) {
                        this.ready = true;
                        Debug.log(3, "audio preroll wait");
                        this.clock.wait();
                        Debug.log(3, "audio preroll go!");
                        this.audioBuffer.start();
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    public void realRun() {
        Debug.log(3, "entering audio thread");
        while (!this.stopping) {
            MediaBuffer mediaBuffer;
            try {
                mediaBuffer = (MediaBuffer)QueueManager.dequeue(this.queueid);
            }
            catch (InterruptedException ex) {
                if (this.stopping) {
                    continue;
                }
                ex.printStackTrace();
                continue;
            }
            final MediaBuffer decode = this.plugin.decode(mediaBuffer);
            if (decode != null) {
                if (this.preQueueing) {
                    this.handlePrequeue(decode);
                }
                else {
                    try {
                        this.clock.checkPlay();
                        this.audioBuffer.write(decode.data, decode.offset, decode.length);
                        this.sampleCount += decode.length / (2 * this.plugin.channels);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    decode.free();
                }
            }
        }
        Debug.log(3, "exit audio thread");
    }
    
    public void setEOS() {
    }
    
    static {
        exp_lut = new byte[] { 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 };
        header = new int[] { 46, 115, 110, 100, 0, 0, 0, 24, 127, 255, 255, 255, 0, 0, 0, 1, 0, 0, 31, 64, 0, 0, 0, 1 };
    }
    
    class AudioBuffer extends InputStream
    {
        private int readPtr;
        private int writePtr;
        private int rate;
        private int channels;
        private byte[] buffer;
        private boolean started;
        private boolean needHeader;
        private int resampleWrap;
        private long samplesRead;
        private long samplesWritten;
        private long free;
        
        public AudioBuffer(final int n, final int rate, final int channels) {
            this.needHeader = true;
            this.readPtr = 0;
            this.writePtr = 0;
            this.rate = rate;
            this.channels = channels;
            this.buffer = new byte[n];
            for (int i = 0; i < this.buffer.length; ++i) {
                this.buffer[i] = 127;
            }
            this.started = false;
            this.free = n;
            try {
                AudioConsumerSun.this.audioStream = new AudioStream(this);
                AudioPlayer.player.start(AudioConsumerSun.this.audioStream);
                Thread.currentThread();
                Thread.sleep(1000L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public int available() throws IOException {
            return super.available();
        }
        
        public long getFramePosition() {
            return this.samplesRead - 8192L;
        }
        
        public void start() {
            this.started = true;
        }
        
        private final byte toUlaw(int n) {
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
            final byte b = AudioConsumerSun.exp_lut[n >> 7 & 0xFF];
            int n3 = ~(n2 | b << 4 | (n >> b + 3 & 0xF));
            if (n3 == 0) {
                n3 = 2;
            }
            return (byte)n3;
        }
        
        public int read() {
            int n;
            if (this.needHeader) {
                n = AudioConsumerSun.header[this.readPtr];
                ++this.readPtr;
                if (this.readPtr >= AudioConsumerSun.header.length) {
                    this.readPtr = 0;
                    this.needHeader = false;
                }
            }
            else if (!this.started) {
                n = 127;
            }
            else {
                n = this.buffer[this.readPtr];
                if (n < 0) {
                    n += 256;
                }
                this.buffer[this.readPtr++] = 127;
                ++this.free;
                if (this.free >= this.buffer.length) {
                    this.free = this.buffer.length - 1;
                    ++this.writePtr;
                }
                if (this.readPtr >= this.buffer.length) {
                    this.readPtr = 0;
                }
                ++this.samplesRead;
            }
            return n;
        }
        
        public synchronized int read(final byte[] array) throws IOException {
            if (this.started) {
                AudioConsumerSun.this.checkClockAdjust();
            }
            if (AudioConsumerSun.this.stopping) {
                return -1;
            }
            final int read = super.read(array);
            this.notify();
            return read;
        }
        
        public synchronized int read(final byte[] array, final int n, final int n2) throws IOException {
            if (this.started) {
                AudioConsumerSun.this.checkClockAdjust();
            }
            if (AudioConsumerSun.this.stopping) {
                return -1;
            }
            final int read = super.read(array, n, n2);
            this.notify();
            return read;
        }
        
        public synchronized int write(final byte[] array, final int n, final int n2) throws IOException {
            int n3 = 0;
            while (this.free < n2 / (2 * this.channels)) {
                try {
                    this.wait();
                }
                catch (Exception ex) {}
            }
            int i;
            for (i = this.resampleWrap; i < n2; i = 2 * this.channels * (this.rate * n3 / 8000)) {
                int n4 = 0;
                for (int j = 0; j < this.channels; ++j) {
                    final byte b = array[n + i + 2 * j];
                    int n5 = array[n + i + 1 + 2 * j];
                    if (n5 < 0) {
                        n5 += 256;
                    }
                    n4 += (b * 256 | n5);
                }
                this.buffer[this.writePtr] = this.toUlaw(n4 / this.channels);
                if (++this.writePtr >= this.buffer.length) {
                    this.writePtr = 0;
                }
                --this.free;
                ++n3;
            }
            this.resampleWrap = i - n2;
            return n2;
        }
    }
}
