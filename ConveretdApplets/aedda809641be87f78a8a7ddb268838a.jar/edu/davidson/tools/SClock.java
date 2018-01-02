// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.tools;

import java.util.Enumeration;
import java.util.Vector;

public final class SClock implements Runnable, SDataSource
{
    Vector clockListeners;
    private String[] varStrings;
    private double[][] ds;
    private Thread thread;
    private Object runLock;
    private boolean shouldRun;
    private boolean running;
    private double time;
    int delay;
    double maxTime;
    double minTime;
    double dt;
    boolean oneShot;
    boolean cycle;
    SApplet owner;
    
    public SClock() {
        this.clockListeners = new Vector();
        this.varStrings = new String[] { "t" };
        this.ds = new double[1][1];
        this.thread = null;
        this.runLock = new Object();
        this.shouldRun = true;
        this.running = false;
        this.time = 0.0;
        this.delay = 100;
        this.maxTime = 100.0;
        this.minTime = 0.0;
        this.dt = 0.1;
        this.oneShot = false;
        this.cycle = false;
        this.owner = null;
        this.shouldRun = true;
        this.running = false;
        (this.thread = new Thread(this)).start();
        try {
            SApplet.addDataSource(this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public SClock(final SApplet owner) {
        this();
        this.owner = owner;
    }
    
    public synchronized void addClockListener(final SStepable sStepable) {
        if (this.thread == null) {
            synchronized (this.runLock) {
                this.shouldRun = true;
                this.running = false;
                (this.thread = new Thread(this)).start();
            }
            // monitorexit(this.runLock)
        }
        if (this.clockListeners.contains(sStepable)) {
            return;
        }
        this.clockListeners.addElement(sStepable);
    }
    
    public void removeClockListener(final SStepable sStepable) {
        synchronized (this.runLock) {
            if (!this.clockListeners.contains(sStepable)) {
                // monitorexit(this.runLock)
                return;
            }
            this.clockListeners.removeElement(sStepable);
        }
        // monitorexit(this.runLock)
    }
    
    public void removeAllClockListeners() {
        synchronized (this.runLock) {
            this.clockListeners.removeAllElements();
        }
        // monitorexit(this.runLock)
    }
    
    public void doStep() {
        if (this.running) {
            this.stopClock();
            return;
        }
        boolean b = false;
        if (this.isRunning() || (this.oneShot && this.dt > 0 && this.time + 0.49 * this.dt >= this.maxTime) || (this.oneShot && this.dt < 0 && this.time + 0.49 * this.dt <= this.minTime) || (this.cycle && this.dt < 0 && this.time + this.dt < this.minTime)) {
            return;
        }
        if (this.cycle && this.time >= this.maxTime && this.dt > 0) {
            this.time = this.minTime;
            b = true;
        }
        if (this.cycle && this.time < this.minTime && this.dt < 0) {
            this.time = this.maxTime;
            b = true;
        }
        final Enumeration<SStepable> elements = (Enumeration<SStepable>)this.clockListeners.elements();
        while (elements.hasMoreElements()) {
            final SStepable sStepable = elements.nextElement();
            if (this.shouldRun) {
                sStepable.step(this.dt, this.time);
            }
        }
        if (this.owner != null && b) {
            this.owner.cyclingClock();
        }
        if (this.owner != null && this.shouldRun) {
            this.owner.updateDataConnection(this.hashCode());
        }
        this.time += this.dt;
    }
    
    private void runningStep() {
        boolean b = false;
        if (this.cycle && this.time + 0.49 * this.dt >= this.maxTime && this.dt > 0) {
            this.time = this.minTime;
            b = true;
        }
        final Enumeration<SStepable> elements = (Enumeration<SStepable>)this.clockListeners.elements();
        while (elements.hasMoreElements()) {
            final SStepable sStepable = elements.nextElement();
            if (this.shouldRun) {
                sStepable.step(this.dt, this.time);
            }
        }
        if (this.owner != null && this.shouldRun) {
            this.owner.updateDataConnection(this.hashCode());
        }
        this.time += this.dt;
        if (this.owner != null && SApplet.runningID != null && this.owner != SApplet.runningID) {
            this.running = false;
            this.owner.pausingClock();
        }
        if ((this.cycle && this.dt < 0 && this.time + 0.49 * this.dt < this.minTime) || (this.oneShot && this.dt < 0 && this.time + 0.49 * this.dt <= this.minTime) || (this.oneShot && this.dt > 0 && this.time + 0.49 * this.dt >= this.maxTime)) {
            this.running = false;
            this.owner.stoppingClock();
        }
        if (this.owner != null && b) {
            this.owner.cyclingClock();
        }
    }
    
    public final double getTime() {
        return this.time;
    }
    
    public final double getMinTime() {
        return this.minTime;
    }
    
    public final double getMaxTime() {
        return this.maxTime;
    }
    
    public void setTime(final double time) {
        final boolean running = this.running;
        this.running = false;
        synchronized (this.runLock) {
            this.time = time;
        }
        // monitorexit(this.runLock)
        if (running) {
            this.startClock();
        }
        else {
            this.owner.updateDataConnection(this.hashCode());
        }
    }
    
    public final double getDt() {
        return this.dt;
    }
    
    public void setDt(final double dt) {
        final boolean running = this.running;
        this.running = false;
        synchronized (this.runLock) {
            this.dt = dt;
        }
        // monitorexit(this.runLock)
        if (running) {
            this.startClock();
        }
    }
    
    public final double getTimeStep() {
        return this.getDt();
    }
    
    public void setTimeStep(final double dt) {
        this.setDt(dt);
    }
    
    public void setContinuous() {
        this.cycle = false;
        this.oneShot = false;
    }
    
    public void setCycle(final double n, final double n2) {
        final boolean running = this.running;
        final double time = this.time;
        this.running = false;
        synchronized (this.runLock) {
            this.minTime = Math.min(n, n2);
            this.maxTime = Math.max(n, n2);
            this.time = Math.max(this.time, this.minTime);
            this.time = Math.min(this.time, this.maxTime);
            this.oneShot = false;
            this.cycle = true;
        }
        // monitorexit(this.runLock)
        if (running) {
            this.startClock();
        }
        else if (time != this.time) {
            this.owner.updateDataConnection(this.hashCode());
        }
    }
    
    public void setOneShot(final double n, final double n2) {
        final boolean running = this.running;
        final double time = this.time;
        this.running = false;
        synchronized (this.runLock) {
            this.minTime = Math.min(n, n2);
            this.maxTime = Math.max(n, n2);
            this.time = Math.max(this.time, this.minTime);
            this.time = Math.min(this.time, this.maxTime);
            this.oneShot = true;
            this.cycle = false;
        }
        // monitorexit(this.runLock)
        if (running) {
            this.startClock();
        }
        else if (time != this.time) {
            this.owner.updateDataConnection(this.hashCode());
        }
    }
    
    public synchronized void startClock() {
        if (this.owner != null) {
            SApplet.runningID = this.owner;
        }
        if (this.running && this.thread != null) {
            return;
        }
        if (this.oneShot && this.dt > 0 && this.time + 0.49 * this.dt >= this.maxTime) {
            this.running = false;
            this.owner.stoppingClock();
            return;
        }
        if ((this.oneShot || this.cycle) && this.dt < 0 && this.time + 0.49 * this.dt <= this.minTime) {
            this.running = false;
            this.owner.stoppingClock();
            return;
        }
        if (this.thread == null) {
            this.shouldRun = true;
            (this.thread = new Thread(this)).start();
        }
        else {
            synchronized (this.runLock) {
                this.running = true;
                this.runLock.notifyAll();
            }
            // monitorexit(this.runLock)
        }
    }
    
    public synchronized void panicStopClock() {
        this.shouldRun = false;
        if (this.thread == null) {
            return;
        }
        this.startClock();
        try {
            this.thread.join(1000L);
        }
        catch (Exception ex) {}
        this.thread = null;
    }
    
    public void stopClock() {
        if (!this.running) {
            return;
        }
        this.running = false;
        final Object runLock = this.runLock;
    }
    // monitorenter(runLock)
    // monitorexit(runLock)
    
    public double getFPS() {
        return 1000.0 / this.delay;
    }
    
    public void setFPS(final double n) {
        this.delay = Math.max(0, (int)(1000 / n));
    }
    
    public boolean isCycle() {
        return this.cycle;
    }
    
    public boolean isOneShot() {
        return this.oneShot;
    }
    
    public boolean isContinous() {
        return !this.oneShot && !this.cycle;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void run() {
        while (this.shouldRun) {
            synchronized (this.runLock) {
                while (!this.running) {
                    try {
                        this.runLock.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                if (this.shouldRun) {
                    this.runningStep();
                }
            }
            // monitorexit(this.runLock)
            if (this.shouldRun) {
                try {
                    Thread.sleep(this.delay);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public double[][] getVariables() {
        this.ds[0][0] = this.time;
        return this.ds;
    }
    
    public String[] getVarStrings() {
        return this.varStrings;
    }
    
    public int getID() {
        return this.hashCode();
    }
    
    public void setOwner(final SApplet owner) {
        this.owner = owner;
    }
    
    public SApplet getOwner() {
        return this.owner;
    }
}
