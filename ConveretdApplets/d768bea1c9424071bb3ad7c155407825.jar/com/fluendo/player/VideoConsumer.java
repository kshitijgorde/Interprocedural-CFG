// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.utils.Debug;
import java.util.Vector;
import java.awt.Component;

public class VideoConsumer implements DataConsumer, Runnable
{
    private ImageTarget target;
    private Component component;
    private int queueid;
    private long framenr;
    private Clock clock;
    private boolean ready;
    private static final int MAX_BUFFER = 100;
    private double framerate;
    private double frameperiod;
    private double aspect;
    private boolean stopping;
    private Plugin plugin;
    private long queuedTime;
    private Vector preQueue;
    private boolean preQueueing;
    private long framesQueued;
    private boolean eos;
    private long baseTime;
    private boolean haveTzOffset;
    
    public VideoConsumer(final Clock clock, final ImageTarget target, final double n) {
        this.aspect = 1.0;
        this.stopping = false;
        this.queuedTime = -1L;
        this.preQueue = new Vector();
        this.preQueueing = true;
        this.framesQueued = 0L;
        this.eos = false;
        this.baseTime = 0L;
        this.target = target;
        this.component = target.getComponent();
        this.queueid = QueueManager.registerQueue(100);
        Debug.log(3, "video on queue " + this.queueid);
        this.clock = clock;
        if (n > 0.0) {
            this.frameperiod = 1000.0 / n;
        }
        else {
            this.frameperiod = -1.0;
        }
    }
    
    public void setPlugin(final Plugin plugin) {
        this.plugin = plugin;
    }
    
    public boolean isReady() {
        return this.ready;
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
    
    public long getQueuedTime() {
        return this.queuedTime;
    }
    
    public void stop() {
        this.stopping = true;
        QueueManager.unRegisterQueue(this.queueid);
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        try {
            this.realRun();
        }
        catch (Throwable t) {
            Cortado.shutdown(t);
        }
    }
    
    private void handleDisplay(final MediaBuffer mediaBuffer) {
        try {
            if (this.frameperiod > 0.0) {
                long timestamp = mediaBuffer.timestamp;
                if (timestamp == -1L) {
                    timestamp = (long)(this.framenr * this.frameperiod);
                    Debug.log(4, "==>init timestamp: " + timestamp);
                }
                Debug.log(4, "set image timestamp: " + timestamp);
                if (this.plugin.haveTzOffset()) {
                    this.target.setImageTime(System.currentTimeMillis() + this.plugin.getTzOffset());
                    this.target.setHaveBasetime();
                }
                else {
                    this.target.setImageTime(this.baseTime + timestamp);
                    if (this.baseTime != 0L) {
                        this.target.setHaveBasetime();
                    }
                }
                this.clock.waitForMediaTime(timestamp);
                Debug.log(4, "[tuning] set image " + timestamp);
                this.target.setImage(mediaBuffer.object, this.framerate, this.aspect);
            }
            else {
                Debug.log(4, "set image: " + (this.framenr + 1L));
                this.target.setImage(mediaBuffer.object, this.framerate, this.aspect);
            }
        }
        catch (Exception ex) {
            if (!this.stopping) {}
        }
        ++this.framenr;
        mediaBuffer.free();
    }
    
    private void handlePrequeue(final MediaBuffer mediaBuffer) {
        Debug.log(4, "video time: " + mediaBuffer.timestamp + " " + mediaBuffer.time_offset + " " + this.queuedTime + " " + this.framesQueued + " " + mediaBuffer.length);
        this.preQueue.addElement(mediaBuffer);
        if (mediaBuffer.timestamp != -1L || mediaBuffer.time_offset != -1L) {
            final MediaBuffer mediaBuffer2 = this.preQueue.elementAt(0);
            if (mediaBuffer.timestamp == -1L) {
                mediaBuffer.timestamp = this.plugin.offsetToTime(mediaBuffer.time_offset);
            }
            Debug.log(4, "prebuffer head " + mediaBuffer2.timestamp);
            mediaBuffer2.timestamp = mediaBuffer.timestamp - (long)(this.framesQueued * 1000L / this.framerate);
            this.framenr = (long)(mediaBuffer2.timestamp / this.frameperiod);
            Debug.log(4, "prebuffer head after correction " + mediaBuffer2.timestamp);
            if (!this.ready) {
                try {
                    this.queuedTime = mediaBuffer2.timestamp;
                    mediaBuffer2.free();
                    synchronized (this.clock) {
                        this.ready = true;
                        Debug.log(3, "video preroll wait");
                        this.clock.wait();
                        Debug.log(3, "video preroll go!");
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                ++this.framenr;
            }
            for (int i = 1; i < this.preQueue.size(); ++i) {
                final MediaBuffer mediaBuffer3 = this.preQueue.elementAt(i);
                if (this.stopping) {
                    return;
                }
                this.handleDisplay(mediaBuffer3);
            }
            this.preQueue.setSize(0);
            this.preQueueing = false;
            this.framesQueued = 0L;
        }
        else {
            Debug.log(4, "video queueing");
            ++this.framesQueued;
        }
    }
    
    private void realRun() {
        Debug.log(3, "entering video thread");
        while (!this.stopping) {
            Debug.log(4, "dequeue image");
            MediaBuffer mediaBuffer = null;
            try {
                synchronized (this) {
                    if (this.eos && QueueManager.isEmpty(this.queueid)) {
                        this.stopping = true;
                        break;
                    }
                    mediaBuffer = (MediaBuffer)QueueManager.dequeue(this.queueid);
                }
            }
            catch (InterruptedException ex) {
                Debug.log(4, "dequeued interrupted !");
                if (this.stopping) {
                    continue;
                }
                ex.printStackTrace();
                continue;
            }
            Debug.log(4, "dequeued image");
            MediaBuffer decode = null;
            if (mediaBuffer != null) {
                decode = this.plugin.decode(mediaBuffer);
            }
            this.baseTime = this.plugin.getImageTime();
            this.target.setImageTime(this.baseTime);
            this.haveTzOffset = this.plugin.haveTzOffset();
            if (decode != null) {
                Debug.log(4, "decoded image");
                if (this.plugin.fps_numerator > 0) {
                    final double framerate = this.plugin.fps_numerator / this.plugin.fps_denominator;
                    if (framerate != this.framerate) {
                        this.framerate = framerate;
                        this.frameperiod = 1000.0 / framerate;
                        Debug.log(3, "frameperiod: " + this.frameperiod);
                    }
                }
                else if (this.preQueueing) {
                    decode.timestamp = 0L;
                }
                this.aspect = this.plugin.aspect_numerator / this.plugin.aspect_denominator;
                if (this.preQueueing) {
                    this.handlePrequeue(decode);
                }
                else {
                    this.handleDisplay(decode);
                }
            }
        }
        Debug.log(3, "exit video thread imageTargetReset");
        this.target.reset();
    }
    
    public void setEOS() {
        Debug.log(3, "reset queueManager");
        synchronized (this) {
            this.eos = true;
        }
    }
}
