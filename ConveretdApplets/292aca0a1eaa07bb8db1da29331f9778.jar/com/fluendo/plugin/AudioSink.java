// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.utils.Debug;
import com.fluendo.jst.SystemClock;
import com.fluendo.jst.Caps;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Event;
import com.fluendo.jst.Clock;
import com.fluendo.jst.ClockProvider;
import com.fluendo.jst.Sink;

public abstract class AudioSink extends Sink implements ClockProvider
{
    protected RingBuffer ringBuffer;
    private AudioClock audioClock;
    
    public AudioSink() {
        this.ringBuffer = null;
        this.audioClock = new AudioClock();
    }
    
    public Clock provideClock() {
        return this.audioClock;
    }
    
    protected int doSync(final long time) {
        return 0;
    }
    
    protected boolean doEvent(final Event event) {
        switch (event.getType()) {
            case 1: {
                this.ringBuffer.setFlushing(true);
                break;
            }
            case 2: {
                this.ringBuffer.setFlushing(false);
            }
        }
        return true;
    }
    
    protected int render(final Buffer buf) {
        if (buf.isFlagSet(1)) {
            this.ringBuffer.nextSample = -1L;
        }
        long time = buf.timestamp - this.segStart;
        if (time < 0L) {
            return 0;
        }
        time += this.baseTime;
        final long sample = time * this.ringBuffer.rate / 1000000L;
        this.ringBuffer.commit(buf.data, sample, buf.offset, buf.length);
        return 0;
    }
    
    protected boolean setCapsFunc(final Caps caps) {
        this.ringBuffer.release();
        final boolean res = this.ringBuffer.acquire(caps);
        return res;
    }
    
    protected int changeState(final int transition) {
        switch (transition) {
            case 18: {
                (this.ringBuffer = this.createRingBuffer()).setFlushing(false);
                break;
            }
            case 35: {
                this.ringBuffer.setAutoStart(true);
                break;
            }
            case 50: {
                this.reset();
                this.ringBuffer.setAutoStart(false);
                this.ringBuffer.pause();
                break;
            }
            case 33: {
                this.ringBuffer.setFlushing(true);
                break;
            }
        }
        final int result = super.changeState(transition);
        switch (transition) {
            case 33: {
                this.ringBuffer.release();
                break;
            }
        }
        return result;
    }
    
    protected abstract RingBuffer createRingBuffer();
    
    protected abstract boolean open(final RingBuffer p0);
    
    protected abstract boolean close(final RingBuffer p0);
    
    protected abstract int write(final byte[] p0, final int p1, final int p2);
    
    protected abstract long delay();
    
    protected abstract void reset();
    
    private class AudioClock extends SystemClock
    {
        private long lastTime;
        private long diff;
        private boolean started;
        
        private AudioClock() {
            this.lastTime = -1L;
            this.diff = -1L;
            this.started = false;
        }
        
        public synchronized void setStarted(final boolean s) {
            this.started = s;
            if (this.started) {
                this.diff = -1L;
                this.lastTime = -1L;
            }
        }
        
        protected synchronized long getInternalTime() {
            if (AudioSink.this.ringBuffer == null || AudioSink.this.ringBuffer.rate == 0) {
                return 0L;
            }
            final long samples = AudioSink.this.ringBuffer.samplesPlayed();
            final long timePos = samples * 1000000L / AudioSink.this.ringBuffer.rate;
            long result;
            if (this.started) {
                final long now = System.currentTimeMillis() * 1000L;
                if (this.diff == -1L) {
                    this.diff = now;
                }
                if (timePos != this.lastTime) {
                    this.lastTime = timePos;
                    this.diff = now - timePos;
                }
                result = now - this.diff;
            }
            else {
                result = timePos;
            }
            return result;
        }
    }
    
    protected class RingBuffer implements Runnable
    {
        protected byte[] buffer;
        private int state;
        private Thread thread;
        private long nextSample;
        private boolean flushing;
        private boolean autoStart;
        private boolean opened;
        private static final int STOP = 0;
        private static final int PAUSE = 1;
        private static final int PLAY = 2;
        public int bps;
        public int sps;
        public byte[] emptySeg;
        public long playSeg;
        public int segTotal;
        public int segSize;
        public int rate;
        public int channels;
        
        public void run() {
            boolean running = true;
            while (running) {
                synchronized (this) {
                    if (this.state != 2) {
                        while (this.state == 1) {
                            try {
                                this.notifyAll();
                                this.wait();
                            }
                            catch (InterruptedException ie) {}
                        }
                        if (this.state == 0) {
                            running = false;
                            break;
                        }
                    }
                }
                final int segNum = (int)(this.playSeg % this.segTotal);
                final int index = segNum * this.segSize;
                int ret;
                for (int toWrite = this.segSize; toWrite > 0; toWrite -= ret) {
                    ret = AudioSink.this.write(this.buffer, index, this.segSize);
                    if (ret == -1) {
                        break;
                    }
                }
                this.clear(segNum);
                synchronized (this) {
                    ++this.playSeg;
                    this.notifyAll();
                }
            }
        }
        
        public synchronized void setFlushing(final boolean flushing) {
            this.flushing = flushing;
            this.clearAll();
            if (flushing) {
                this.pause();
            }
        }
        
        protected void startWriteThread() {
            (this.thread = new Thread(this)).start();
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        
        public synchronized boolean acquire(final Caps caps) {
            if (this.thread != null) {
                return false;
            }
            if (this.opened) {
                return false;
            }
            final String mime = caps.getMime();
            if (!mime.equals("audio/raw")) {
                return false;
            }
            this.rate = caps.getFieldInt("rate", 44100);
            this.channels = caps.getFieldInt("channels", 1);
            this.bps = 2 * this.channels;
            final boolean res;
            if (!(res = AudioSink.this.open(this))) {
                return res;
            }
            this.opened = true;
            Debug.log(3, "audio: segSize: " + this.segSize);
            Debug.log(3, "audio: segTotal: " + this.segTotal);
            ++this.segTotal;
            this.buffer = new byte[this.segSize * this.segTotal];
            this.sps = this.segSize / this.bps;
            this.state = 1;
            this.nextSample = 0L;
            this.playSeg = 0L;
            this.startWriteThread();
            return res;
        }
        
        public boolean release() {
            this.stop();
            synchronized (this) {
                if (this.opened && !AudioSink.this.close(this)) {
                    return false;
                }
                this.opened = false;
            }
            return true;
        }
        
        private synchronized boolean waitSegment() {
            if (this.flushing) {
                return false;
            }
            if (this.state != 2 && this.autoStart) {
                this.play();
            }
            try {
                if (this.state != 2) {
                    return false;
                }
                this.wait();
                if (this.flushing) {
                    return false;
                }
                if (this.state != 2) {
                    return false;
                }
            }
            catch (InterruptedException ex) {}
            return true;
        }
        
        public int commit(final byte[] data, long sample, final int offset, int len) {
            if (sample == -1L) {
                sample = this.nextSample;
            }
            if (sample < 0L) {
                return len;
            }
            if (this.nextSample != -1L) {
                if (Math.abs(sample - this.nextSample) < this.rate / 10) {
                    sample = this.nextSample;
                }
                else {
                    System.out.println("discont: found " + sample + " expected " + this.nextSample);
                }
            }
            int idx = 0;
            this.nextSample = sample + len / this.bps;
        Label_0116:
            while (len > 0) {
                int writeLen = 0;
                long diff = -1L;
                final long writeSeg = sample / this.sps;
                final int writeOff = (int)(sample % this.sps * this.bps);
                do {
                    synchronized (this) {
                        diff = writeSeg - this.playSeg;
                    }
                    if (diff < 0L) {
                        writeLen = Math.min(this.segSize, len);
                    }
                    else if (diff >= this.segTotal) {
                        continue;
                    }
                    if (diff >= 0L) {
                        final int writeSegRel = (int)(writeSeg % this.segTotal);
                        writeLen = Math.min(this.segSize - writeOff, len);
                        System.arraycopy(data, idx, this.buffer, writeSegRel * this.segSize + writeOff, writeLen);
                    }
                    len -= writeLen;
                    idx += writeLen;
                    sample += writeLen / this.bps;
                    continue Label_0116;
                } while (this.waitSegment());
                return -1;
            }
            return len;
        }
        
        public synchronized long samplesPlayed() {
            final long delay = AudioSink.this.delay();
            final long seg = Math.max(0L, this.playSeg - 1L);
            long samples = seg * this.sps;
            if (samples >= delay) {
                samples -= delay;
            }
            else {
                samples = 0L;
            }
            return samples;
        }
        
        public synchronized void clear(final long segNum) {
            final int index = (int)(segNum % this.segTotal) * this.segSize;
            System.arraycopy(this.emptySeg, 0, this.buffer, index, this.segSize);
        }
        
        public synchronized void clearAll() {
            for (int i = 0; i < this.segTotal; ++i) {
                this.clear(i);
            }
        }
        
        public synchronized void setSample(long sample) {
            if (sample == -1L) {
                sample = 0L;
            }
            this.playSeg = sample / this.sps;
            this.nextSample = sample;
            this.clearAll();
            synchronized (AudioSink.this.audioClock) {
                AudioSink.this.audioClock.notifyAll();
            }
        }
        
        public synchronized void setAutoStart(final boolean start) {
            this.autoStart = start;
        }
        
        public boolean play() {
            synchronized (this) {
                if (this.flushing) {
                    return false;
                }
                this.state = 2;
                this.notifyAll();
                AudioSink.this.audioClock.setStarted(true);
                Debug.log(4, this + " playing");
            }
            return true;
        }
        
        public boolean pause() {
            synchronized (this) {
                this.state = 1;
                Debug.log(4, this + " pausing");
                this.notifyAll();
                if (this.thread != null) {
                    try {
                        Debug.log(4, this + " waiting for pause");
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
            AudioSink.this.audioClock.setStarted(false);
            Debug.log(4, this + " paused");
            return true;
        }
        
        public boolean stop() {
            synchronized (this) {
                this.state = 0;
                Debug.log(4, this + " stopping");
                this.notifyAll();
            }
            if (this.thread != null) {
                try {
                    Debug.log(4, this + " joining thread");
                    this.thread.join();
                    this.thread = null;
                }
                catch (InterruptedException ex) {}
            }
            AudioSink.this.audioClock.setStarted(false);
            Debug.log(4, this + " stopped");
            return true;
        }
        
        public synchronized int getState() {
            return this.state;
        }
    }
}
