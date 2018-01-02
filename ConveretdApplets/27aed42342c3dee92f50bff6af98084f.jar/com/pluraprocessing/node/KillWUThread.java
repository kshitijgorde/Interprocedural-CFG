// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node;

public class KillWUThread extends Thread
{
    private Plura plura;
    private Thread killThread;
    
    public KillWUThread(final Plura plura) {
        this.killThread = null;
        this.plura = plura;
        this.killThread = new Thread(this);
    }
    
    public void stopThread() {
        this.killThread = null;
    }
    
    @Override
    public void run() {
        while (Thread.currentThread() == this.killThread) {
            if (PluraRuntime.getInstance().isStopped()) {
                this.plura.stopCompute();
                this.killThread = null;
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
}
