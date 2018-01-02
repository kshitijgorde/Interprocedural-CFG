// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.util.Vector;

public class f
{
    private Vector b;
    boolean a;
    
    public f() {
        this.b = new Vector(5);
        this.a = true;
        new Thread(new g(this, null)).start();
    }
    
    public void a() {
        this.a = false;
    }
    
    public synchronized void a(final Runnable runnable) {
        this.b.add(runnable);
        this.notify();
    }
    
    private synchronized Runnable b() {
        while (this.b.size() == 0) {
            this.wait();
        }
        return this.b.remove(0);
    }
}
