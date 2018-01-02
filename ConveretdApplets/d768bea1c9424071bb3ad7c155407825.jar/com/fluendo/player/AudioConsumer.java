// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import com.fluendo.utils.Debug;
import javax.sound.sampled.SourceDataLine;
import java.util.Vector;

public class AudioConsumer implements Runnable, DataConsumer, ClockProvider
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
    private SourceDataLine line;
    static /* synthetic */ Class class$javax$sound$sampled$SourceDataLine;
    
    public AudioConsumer(final Clock clock) {
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
        return this.ready && QueueManager.isFilled(this.queueid);
    }
    
    public long getQueuedTime() {
        return this.queuedTime;
    }
    
    public void stop() {
        this.stopping = true;
        QueueManager.unRegisterQueue(this.queueid);
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
        return this.line.getMicrosecondPosition() / 1000L;
    }
    
    public void checkClockAdjust() {
        if (this.sampleCount > this.nextSampleCount) {
            final long n = this.clock.getMediaTime() - (this.line.getFramePosition() * 1000L / this.plugin.rate + this.queuedTime);
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
            this.nextSampleCount = this.sampleCount + this.plugin.rate;
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
            final AudioFormat audioFormat = new AudioFormat(this.plugin.rate, 16, this.plugin.channels, true, true);
            final DataLine.Info info = new DataLine.Info((AudioConsumer.class$javax$sound$sampled$SourceDataLine == null) ? (AudioConsumer.class$javax$sound$sampled$SourceDataLine = class$("javax.sound.sampled.SourceDataLine")) : AudioConsumer.class$javax$sound$sampled$SourceDataLine, audioFormat);
            try {
                (this.line = (SourceDataLine)AudioSystem.getLine(info)).open(audioFormat);
                Debug.log(3, "line info: available: " + this.line.available());
                Debug.log(3, "line info: buffer: " + this.line.getBufferSize());
                Debug.log(3, "line info: framePosition: " + this.line.getFramePosition());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.queuedTime = mediaBuffer2.timestamp;
            try {
                for (int i = 0; i < this.preQueue.size(); ++i) {
                    final MediaBuffer mediaBuffer3 = this.preQueue.elementAt(i);
                    this.line.write(mediaBuffer3.data, mediaBuffer3.offset, mediaBuffer3.length);
                    this.sampleCount += mediaBuffer3.length / (2 * this.plugin.channels);
                    mediaBuffer3.free();
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            this.preQueue.setSize(0);
            this.preQueueing = false;
            this.samplesQueued = 0L;
            if (!this.ready) {
                try {
                    this.ready = true;
                    synchronized (this.clock) {
                        Debug.log(3, "audio preroll wait");
                        this.clock.wait();
                        Debug.log(3, "audio preroll go!");
                        this.line.start();
                    }
                }
                catch (Exception ex3) {
                    if (!this.stopping) {
                        ex3.printStackTrace();
                    }
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
                        this.line.write(decode.data, decode.offset, decode.length);
                        this.sampleCount += decode.length / (2 * this.plugin.channels);
                        this.checkClockAdjust();
                    }
                    catch (Exception ex2) {
                        if (!this.stopping) {
                            ex2.printStackTrace();
                        }
                    }
                    decode.free();
                }
            }
        }
        Debug.log(3, "exit audio thread");
    }
    
    public void setEOS() {
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
