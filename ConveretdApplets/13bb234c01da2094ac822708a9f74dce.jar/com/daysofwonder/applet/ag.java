// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

public class ag
{
    private boolean a;
    
    public ag() {
        this.a = true;
    }
    
    public synchronized void a() {
        Thread.interrupted();
        this.a = true;
        while (this.a) {
            this.wait();
        }
    }
    
    public synchronized void b() {
        this.a = false;
        this.notify();
    }
}
