// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.activity;

import prefuse.util.collections.CopyOnWriteArrayList;

public abstract class Activity
{
    public static final long INFINITY = -1L;
    public static final long DEFAULT_STEP_TIME = 15L;
    private boolean m_enabled;
    private Pacer m_pacer;
    private long m_startTime;
    private long m_duration;
    private long m_stepTime;
    private long m_nextTime;
    private boolean m_isRunning;
    private boolean m_isScheduled;
    private CopyOnWriteArrayList m_listeners;
    
    public Activity(final long n) {
        this(n, 15L);
    }
    
    public Activity(final long n, final long n2) {
        this(n, n2, System.currentTimeMillis());
    }
    
    public Activity(final long duration, final long stepTime, final long n) {
        this.m_enabled = true;
        this.m_startTime = -1L;
        this.m_duration = -1L;
        this.m_stepTime = -1L;
        this.m_nextTime = -1L;
        this.m_isRunning = false;
        this.m_isScheduled = false;
        this.m_startTime = n;
        this.m_nextTime = n;
        this.m_duration = duration;
        this.m_stepTime = stepTime;
    }
    
    public void run() {
        ActivityManager.scheduleNow(this);
    }
    
    public void runAt(final long n) {
        ActivityManager.scheduleAt(this, n);
    }
    
    public void runAfter(final Activity activity) {
        ActivityManager.scheduleAfter(activity, this);
    }
    
    public void alwaysRunAfter(final Activity activity) {
        ActivityManager.alwaysScheduleAfter(activity, this);
    }
    
    protected abstract void run(final long p0);
    
    long runActivity(final long n) {
        if (n < this.m_startTime) {
            return this.m_startTime - n;
        }
        final long n2 = n - this.m_startTime;
        if (this.m_duration == 0L || n >= this.getStopTime()) {
            if (!this.setRunning(true)) {
                this.fireActivityStarted();
            }
            if (this.m_enabled) {
                this.run(n2);
                this.fireActivityStepped();
            }
            this.setRunning(false);
            this.fireActivityFinished();
            return -1L;
        }
        if (n >= this.m_nextTime) {
            if (!this.setRunning(true)) {
                this.fireActivityStarted();
            }
            if (this.m_enabled) {
                this.run(n2);
                this.fireActivityStepped();
            }
            this.m_nextTime = n + this.m_stepTime;
        }
        return this.m_nextTime - n;
    }
    
    public void cancel() {
        boolean b = false;
        synchronized (this) {
            if (this.isScheduled()) {
                ActivityManager.removeActivity(this);
                b = true;
            }
            this.setRunning(false);
        }
        if (b) {
            this.fireActivityCancelled();
        }
    }
    
    public synchronized boolean isScheduled() {
        return this.m_isScheduled;
    }
    
    void setScheduled(final boolean isScheduled) {
        final boolean b;
        synchronized (this) {
            b = (isScheduled && !this.m_isScheduled);
            this.m_isScheduled = isScheduled;
        }
        if (b) {
            this.fireActivityScheduled();
        }
    }
    
    synchronized boolean setRunning(final boolean isRunning) {
        final boolean isRunning2 = this.m_isRunning;
        this.m_isRunning = isRunning;
        return isRunning2;
    }
    
    public synchronized boolean isRunning() {
        return this.m_isRunning;
    }
    
    public void addActivityListener(final ActivityListener activityListener) {
        if (this.m_listeners == null) {
            this.m_listeners = new CopyOnWriteArrayList();
        }
        if (!this.m_listeners.contains(activityListener)) {
            this.m_listeners.add(activityListener);
        }
    }
    
    public void removeActivityListener(final ActivityListener activityListener) {
        if (this.m_listeners == null) {
            return;
        }
        this.m_listeners.remove(activityListener);
        if (this.m_listeners.size() == 0) {
            this.m_listeners = null;
        }
    }
    
    protected void fireActivityScheduled() {
        if (this.m_listeners == null) {
            return;
        }
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ActivityListener)array[i]).activityScheduled(this);
        }
    }
    
    protected void fireActivityStarted() {
        if (this.m_listeners == null) {
            return;
        }
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ActivityListener)array[i]).activityStarted(this);
        }
    }
    
    protected void fireActivityStepped() {
        if (this.m_listeners == null) {
            return;
        }
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ActivityListener)array[i]).activityStepped(this);
        }
    }
    
    protected void fireActivityFinished() {
        if (this.m_listeners == null) {
            return;
        }
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ActivityListener)array[i]).activityFinished(this);
        }
    }
    
    protected void fireActivityCancelled() {
        if (this.m_listeners == null) {
            return;
        }
        final Object[] array = this.m_listeners.getArray();
        for (int i = 0; i < array.length; ++i) {
            ((ActivityListener)array[i]).activityCancelled(this);
        }
    }
    
    public double getPace(final long n) {
        final long duration = this.getDuration();
        final double min = Math.min(1.0, Math.max(0.0, (duration == 0L) ? 0.0 : (n / duration)));
        return (this.m_pacer != null) ? this.m_pacer.pace(min) : min;
    }
    
    public synchronized Pacer getPacingFunction() {
        return this.m_pacer;
    }
    
    public synchronized void setPacingFunction(final Pacer pacer) {
        this.m_pacer = pacer;
    }
    
    public long getStopTime() {
        if (this.m_duration == -1L) {
            return Long.MAX_VALUE;
        }
        return this.m_startTime + this.m_duration;
    }
    
    public long getNextTime() {
        return this.m_nextTime;
    }
    
    public long getDuration() {
        return this.m_duration;
    }
    
    public void setDuration(final long duration) {
        this.m_duration = duration;
    }
    
    public long getStartTime() {
        return this.m_startTime;
    }
    
    public void setStartTime(final long startTime) {
        this.m_startTime = startTime;
    }
    
    public long getStepTime() {
        return this.m_stepTime;
    }
    
    public void setStepTime(final long stepTime) {
        this.m_stepTime = stepTime;
    }
    
    public boolean isEnabled() {
        return this.m_enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.m_enabled = enabled;
    }
}
