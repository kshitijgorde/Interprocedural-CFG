// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.jst.SystemClock;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Caps;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Event;
import com.fluendo.jst.WaitStatus;
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
    
    public boolean test() {
        return true;
    }
    
    protected WaitStatus doSync(final long n) {
        return WaitStatus.newOK();
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
            case 3: {
                this.drain();
                break;
            }
        }
        return true;
    }
    
    protected int render(final Buffer buffer) {
        if (buffer.isFlagSet(1)) {
            this.ringBuffer.nextSample = -1L;
        }
        final long n = buffer.timestamp - super.segStart;
        if (n < 0L) {
            return 0;
        }
        this.ringBuffer.commit(buffer.data, (n + super.baseTime) * this.ringBuffer.rate / 1000000L, buffer.offset, buffer.length);
        return 0;
    }
    
    protected boolean setCapsFunc(final Caps caps) {
        this.ringBuffer.release();
        return this.ringBuffer.acquire(caps);
    }
    
    protected int changeState(final int n) {
        switch (n) {
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
        final int changeState = super.changeState(n);
        switch (n) {
            case 33: {
                this.ringBuffer.release();
                break;
            }
        }
        return changeState;
    }
    
    protected void drain() {
        if (this.ringBuffer.rate <= 0) {
            return;
        }
        if (!this.ringBuffer.isAcquired()) {
            return;
        }
        if (this.ringBuffer.getState() != 3) {
            this.ringBuffer.play();
        }
        if (this.ringBuffer.nextSample != -1L) {
            final long n = this.ringBuffer.nextSample * 1000000L / this.ringBuffer.rate;
            final Clock.ClockID singleShotID = this.audioClock.newSingleShotID(n);
            Debug.log(4, this + " waiting until t=" + n / 1000000.0 + "s for playback to finish");
            singleShotID.waitID();
            this.ringBuffer.nextSample = -1L;
        }
    }
    
    protected abstract RingBuffer createRingBuffer();
    
    protected abstract boolean open(final RingBuffer p0);
    
    protected abstract boolean close(final RingBuffer p0);
    
    protected abstract int write(final byte[] p0, final int p1, final int p2);
    
    protected abstract long delay();
    
    protected abstract void reset();
    
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
            int i = 1;
            while (i != 0) {
                synchronized (this) {
                    if (this.state != 2) {
                        while (this.state == 1) {
                            try {
                                this.notifyAll();
                                this.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        if (this.state == 0) {
                            i = 0;
                            break;
                        }
                    }
                }
                final int n = (int)(this.playSeg % this.segTotal);
                final int n2 = n * this.segSize;
                int write;
                for (int j = this.segSize; j > 0; j -= write) {
                    write = AudioSink.this.write(this.buffer, n2, this.segSize);
                    if (write == -1) {
                        break;
                    }
                }
                this.clear(n);
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
            (this.thread = new Thread(this, "cortado-audiosink-ringbuffer")).start();
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
            if (!caps.getMime().equals("audio/raw")) {
                return false;
            }
            this.rate = caps.getFieldInt("rate", 44100);
            this.channels = caps.getFieldInt("channels", 1);
            this.bps = 2 * this.channels;
            final boolean open;
            if (!(open = AudioSink.this.open(this))) {
                return open;
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
            return open;
        }
        
        public synchronized boolean isAcquired() {
            return this.opened;
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
        
        public int commit(final byte[] array, long n, final int n2, int i) {
            if (n == -1L) {
                n = this.nextSample;
            }
            if (n < 0L) {
                return i;
            }
            if (this.nextSample != -1L) {
                if (Math.abs(n - this.nextSample) < this.rate / 10) {
                    n = this.nextSample;
                }
                else {
                    System.out.println("discont: found " + n + " expected " + this.nextSample);
                }
            }
            int n3 = 0;
            this.nextSample = n + i / this.bps;
        Label_0116:
            while (i > 0) {
                int n4 = 0;
                long n5 = -1L;
                final long n6 = n / this.sps;
                final int n7 = (int)(n % this.sps * this.bps);
                do {
                    synchronized (this) {
                        n5 = n6 - this.playSeg;
                    }
                    if (n5 < 0L) {
                        n4 = Math.min(this.segSize, i);
                    }
                    else if (n5 >= this.segTotal) {
                        continue;
                    }
                    if (n5 >= 0L) {
                        final int n8 = (int)(n6 % this.segTotal);
                        n4 = Math.min(this.segSize - n7, i);
                        System.arraycopy(array, n3, this.buffer, n8 * this.segSize + n7, n4);
                    }
                    i -= n4;
                    n3 += n4;
                    n += n4 / this.bps;
                    continue Label_0116;
                } while (this.waitSegment());
                return -1;
            }
            return i;
        }
        
        public long samplesPlayed() {
            final long delay = AudioSink.this.delay();
            final long n = Math.max(0L, this.playSeg - 1L) * this.sps;
            long n2;
            if (n >= delay) {
                n2 = n - delay;
            }
            else {
                n2 = 0L;
            }
            return n2;
        }
        
        public synchronized void clear(final long n) {
            System.arraycopy(this.emptySeg, 0, this.buffer, (int)(n % this.segTotal) * this.segSize, this.segSize);
        }
        
        public synchronized void clearAll() {
            for (int i = 0; i < this.segTotal; ++i) {
                this.clear(i);
            }
        }
        
        public synchronized void setSample(long nextSample) {
            if (nextSample == -1L) {
                nextSample = 0L;
            }
            this.playSeg = nextSample / this.sps;
            this.nextSample = nextSample;
            this.clearAll();
        }
        
        public synchronized void setAutoStart(final boolean autoStart) {
            this.autoStart = autoStart;
        }
        
        public boolean play() {
            synchronized (this) {
                if (this.flushing) {
                    return false;
                }
                this.state = 2;
                AudioSink.this.audioClock.setStarted(true);
                this.notifyAll();
            }
            Debug.log(4, this + " playing");
            return true;
        }
        
        public boolean pause() {
            synchronized (this) {
                Debug.log(4, this + " pausing");
                this.state = 1;
                AudioSink.this.audioClock.setStarted(false);
                this.notifyAll();
                if (this.thread != null) {
                    try {
                        Debug.log(4, this + " waiting for pause");
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
            Debug.log(4, this + " paused");
            return true;
        }
        
        public boolean stop() {
            synchronized (this) {
                Debug.log(4, this + " stopping");
                this.state = 0;
                AudioSink.this.audioClock.setStarted(false);
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
            Debug.log(4, this + " stopped");
            return true;
        }
        
        public synchronized int getState() {
            return this.state;
        }
    }
    
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
        
        public void setStarted(final boolean started) {
            this.started = started;
            if (this.started) {
                this.diff = -1L;
                this.lastTime = -1L;
            }
        }
        
        protected long getInternalTime() {
            long n;
            synchronized (AudioSink.this.ringBuffer) {
                if (AudioSink.this.ringBuffer == null || AudioSink.this.ringBuffer.rate == 0) {
                    return 0L;
                }
                final long lastTime = AudioSink.this.ringBuffer.samplesPlayed() * 1000000L / AudioSink.this.ringBuffer.rate;
                if (this.started) {
                    final long diff = System.currentTimeMillis() * 1000L;
                    if (this.diff == -1L) {
                        this.diff = diff;
                    }
                    if (lastTime != this.lastTime) {
                        this.lastTime = lastTime;
                        this.diff = diff - lastTime;
                    }
                    n = diff - this.diff;
                }
                else {
                    n = lastTime;
                }
            }
            return n;
        }
    }
}
