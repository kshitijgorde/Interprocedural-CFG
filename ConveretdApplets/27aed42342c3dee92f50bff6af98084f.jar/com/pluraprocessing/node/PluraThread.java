// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node;

import com.pluraprocessing.common.domain.RequestedWork;

public class PluraThread extends Thread
{
    private Plura plura;
    private Thread pluraThread;
    private RequestedWork workUnitToComplete;
    private State state;
    
    public PluraThread(final Plura plura, final RequestedWork work) {
        this.pluraThread = null;
        this.plura = plura;
        this.workUnitToComplete = work;
        this.state = State.TERMINATED;
        this.pluraThread = new Thread(this);
    }
    
    public void stopPluraThread() {
        this.pluraThread = null;
    }
    
    public void stopPluraCompute() {
        this.plura.stopCompute();
    }
    
    public void startPluraThread() {
        (this.pluraThread = new Thread(this)).start();
    }
    
    @Override
    public void run() {
        this.state = State.RUNNABLE;
        if (Thread.currentThread() == this.pluraThread && this.plura != null) {
            do {
                this.plura.run(this.workUnitToComplete);
            } while (Thread.currentThread() == this.pluraThread && this.plura != null && this.workUnitToComplete == null);
        }
        this.state = State.TERMINATED;
    }
    
    @Override
    public State getState() {
        return this.state;
    }
}
