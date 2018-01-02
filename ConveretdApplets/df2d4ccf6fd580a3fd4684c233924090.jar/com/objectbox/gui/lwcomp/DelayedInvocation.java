// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

public class DelayedInvocation implements Runnable
{
    private static DelayedInvocation instance;
    private long delay;
    private DelayedInvocationCallBack callback;
    private Thread t;
    
    static {
        DelayedInvocation.instance = null;
    }
    
    private DelayedInvocation() {
        this.delay = 1000L;
        this.callback = null;
        this.t = null;
    }
    
    public DelayedInvocationCallBack getCallback() {
        return this.callback;
    }
    
    public long getDelay() {
        return this.delay;
    }
    
    public static DelayedInvocation getInstance() {
        try {
            if (DelayedInvocation.instance == null) {
                DelayedInvocation.instance = new DelayedInvocation();
                (DelayedInvocation.instance.t = new Thread(DelayedInvocation.instance)).start();
            }
            return DelayedInvocation.instance;
        }
        catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.delay);
                if (this.callback == null) {
                    continue;
                }
                this.callback.delayedInvoke();
                this.callback = null;
            }
            catch (Exception ex) {}
        }
    }
    
    public void setCallback(final DelayedInvocationCallBack callback, final long delay) {
        try {
            this.callback = null;
            this.delay = delay;
            this.t.interrupt();
            this.callback = callback;
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public void setDelay(final long delay) {
        this.delay = delay;
    }
}
