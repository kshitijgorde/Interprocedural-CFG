// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock;

class Timer implements Runnable
{
    public static final long DEFAULT_TICKING_FREQUENCY = 1000L;
    private long tickingFrequency;
    private TimerSupport timerSupport;
    private Thread timerThread;
    private static final boolean DEBUG = true;
    
    private TimerSupport getTimerSupport() {
        if (this.timerSupport == null) {
            this.timerSupport = new TimerSupport(this);
        }
        return this.timerSupport;
    }
    
    synchronized long getTickingFrequency() {
        return this.tickingFrequency;
    }
    
    synchronized void setTickingFrequency(final long tickingFrequency) {
        if (tickingFrequency <= 0L) {
            throw new IllegalArgumentException();
        }
        this.tickingFrequency = tickingFrequency;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.getTickingFrequency());
            }
            catch (InterruptedException ex) {}
            this.getTimerSupport().fireTimeChangedEvent();
        }
    }
    
    private Thread getTimerThread() {
        if (this.timerThread == null) {
            this.timerThread = new Thread(this);
        }
        return this.timerThread;
    }
    
    public synchronized void startTicking() {
        try {
            this.getTimerThread().start();
        }
        catch (IllegalThreadStateException ex) {
            debug(ex.toString());
        }
    }
    
    public synchronized void stopTicking() throws SecurityException {
        this.getTimerThread().stop();
    }
    
    public void addTimerListener(final TimerListener timerListener) {
        this.getTimerSupport().addTimerListener(timerListener);
    }
    
    public void removeTimerListener(final TimerListener timerListener) {
        this.getTimerSupport().removeTimerListener(timerListener);
    }
    
    private static final void debug(final String s) {
        System.out.println("Timer::" + s);
    }
    
    Timer() {
        this.tickingFrequency = 1000L;
    }
}
