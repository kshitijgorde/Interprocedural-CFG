// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

class TimerQueue implements Runnable
{
    private static final Object sharedInstanceKey;
    private static final Object expiredTimersKey;
    Timer firstTimer;
    boolean running;
    private static final Object classLock;
    
    static {
        sharedInstanceKey = new StringBuffer("TimerQueue.sharedInstanceKey");
        expiredTimersKey = new StringBuffer("TimerQueue.expiredTimersKey");
        classLock = new Object();
    }
    
    public TimerQueue() {
        this.start();
    }
    
    synchronized void addTimer(final Timer timer, final long expirationTime) {
        if (timer.running) {
            return;
        }
        Timer timer2 = null;
        Timer nextTimer;
        for (nextTimer = this.firstTimer; nextTimer != null && nextTimer.expirationTime <= expirationTime; nextTimer = nextTimer.nextTimer) {
            timer2 = nextTimer;
        }
        if (timer2 == null) {
            this.firstTimer = timer;
        }
        else {
            timer2.nextTimer = timer;
        }
        timer.expirationTime = expirationTime;
        timer.nextTimer = nextTimer;
        timer.running = true;
        this.notify();
    }
    
    synchronized boolean containsTimer(final Timer timer) {
        return timer.running;
    }
    
    synchronized long postExpiredTimers() {
        long n;
        do {
            final Timer firstTimer = this.firstTimer;
            if (firstTimer == null) {
                return 0L;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            n = firstTimer.expirationTime - currentTimeMillis;
            if (n <= 0L) {
                try {
                    firstTimer.post();
                }
                catch (SecurityException ex) {}
                this.removeTimer(firstTimer);
                if (firstTimer.isRepeats()) {
                    this.addTimer(firstTimer, currentTimeMillis + firstTimer.getDelay());
                }
            }
            try {
                this.wait(1L);
            }
            catch (InterruptedException ex2) {}
        } while (n <= 0L);
        return n;
    }
    
    synchronized void removeTimer(final Timer timer) {
        if (!timer.running) {
            return;
        }
        Timer timer2 = null;
        Timer timer3 = this.firstTimer;
        boolean b = false;
        while (timer3 != null) {
            if (timer3 == timer) {
                b = true;
                break;
            }
            timer2 = timer3;
            timer3 = timer3.nextTimer;
        }
        if (!b) {
            return;
        }
        if (timer2 == null) {
            this.firstTimer = timer.nextTimer;
        }
        else {
            timer2.nextTimer = timer.nextTimer;
        }
        timer.expirationTime = 0L;
        timer.nextTimer = null;
        timer.running = false;
    }
    
    public synchronized void run() {
        try {
            while (this.running) {
                final long postExpiredTimers = this.postExpiredTimers();
                try {
                    this.wait(postExpiredTimers);
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (ThreadDeath threadDeath) {
            this.running = false;
            for (Timer timer = this.firstTimer; timer != null; timer = timer.nextTimer) {
                timer.eventQueued = false;
            }
            SystemEventQueueUtilities.restartTimerQueueThread();
            throw threadDeath;
        }
    }
    
    public static TimerQueue sharedInstance() {
        synchronized (TimerQueue.classLock) {
            TimerQueue timerQueue = (TimerQueue)SwingUtilities.appContextGet(TimerQueue.sharedInstanceKey);
            if (timerQueue == null) {
                timerQueue = new TimerQueue();
                SwingUtilities.appContextPut(TimerQueue.sharedInstanceKey, timerQueue);
            }
            // monitorexit(TimerQueue.classLock)
            return timerQueue;
        }
    }
    
    synchronized void start() {
        if (this.running) {
            throw new RuntimeException("Can't start a TimerQueue that is already running");
        }
        final Thread thread = new Thread(this, "TimerQueue");
        try {
            thread.setDaemon(true);
        }
        catch (SecurityException ex) {}
        thread.start();
        this.running = true;
    }
    
    synchronized void stop() {
        this.running = false;
        this.notify();
    }
    
    public synchronized String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("TimerQueue (");
        Timer timer = this.firstTimer;
        while (timer != null) {
            sb.append(timer.toString());
            timer = timer.nextTimer;
            if (timer != null) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
