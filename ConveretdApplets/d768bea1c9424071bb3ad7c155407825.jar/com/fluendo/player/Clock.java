// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.utils.Debug;

public class Clock
{
    private long adjust;
    private long startTime;
    private long lastMedia;
    private boolean paused;
    private ClockProvider provider;
    
    public Clock() {
        this.paused = true;
        this.provider = new SystemClock();
    }
    
    public synchronized void pause() {
        if (!this.paused) {
            this.paused = true;
            this.lastMedia = this.getMediaTime() - this.adjust;
            this.notifyAll();
        }
    }
    
    public synchronized void setProvider(final ClockProvider provider) {
        this.provider = provider;
    }
    
    public synchronized void play() {
        if (this.paused) {
            final long time = this.provider.getTime();
            if (this.startTime == 0L) {
                this.startTime = time;
            }
            final long n = this.getMediaTime() - this.adjust - this.lastMedia;
            this.paused = false;
            this.startTime += n;
            this.notifyAll();
        }
    }
    
    public synchronized long getElapsedTime() {
        return this.provider.getTime() - this.startTime;
    }
    
    public synchronized long getMediaTime() {
        return this.provider.getTime() - this.startTime + this.adjust;
    }
    
    public synchronized void updateAdjust(final long n) {
        this.adjust += n;
        this.notifyAll();
    }
    
    public synchronized void setAdjust(final long adjust) {
        this.adjust = adjust;
        this.notifyAll();
    }
    
    public long getAdjust() {
        return this.adjust;
    }
    
    public synchronized void checkPlay() throws InterruptedException {
        while (this.paused) {
            Debug.log(4, "checkPlay");
            this.wait();
        }
        Debug.log(4, "checkPlay DONE");
    }
    
    public boolean waitForMediaTime(final long n) throws InterruptedException {
        boolean b = false;
        this.checkPlay();
        long mediaTime;
        long n2;
        while (true) {
            mediaTime = this.getMediaTime();
            n2 = n - mediaTime;
            if (n2 <= 0L) {
                break;
            }
            b = true;
            synchronized (this) {
                Debug.log(4, "waiting now=" + mediaTime + " time=" + n + " interval=" + n2 + "...");
                this.wait(n2);
            }
        }
        Debug.log(4, "shortcut now=" + mediaTime + " time=" + n + " interval=" + n2);
        return b;
    }
}
