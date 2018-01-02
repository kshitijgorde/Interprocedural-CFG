// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

public class w implements Runnable
{
    private int a;
    private Thread b;
    private long c;
    private long d;
    private boolean e;
    private volatile boolean f;
    private long g;
    private int h;
    private long i;
    private long j;
    
    public w() {
        this.e = true;
        this.f = false;
        (this.b = new Thread(this)).setPriority(10);
        this.b.setDaemon(true);
    }
    
    public void a() {
        this.f = true;
        this.b.start();
    }
    
    public void b() {
        this.f = false;
        try {
            this.b.join();
        }
        catch (Exception ex) {}
    }
    
    public void a(final boolean e, final int h) {
        this.e = e;
        this.h = h;
    }
    
    public void a(final int n) {
        this.c = n;
        this.d = this.c;
    }
    
    public void run() {
        this.i = System.currentTimeMillis();
        try {
            while (this.f) {
                if (this.c < 0L) {
                    this.c = 0L;
                }
                Thread.sleep(this.c);
                ++this.a;
                this.c();
                if (this.e && this.a % this.h == 0) {
                    this.j = System.currentTimeMillis();
                    this.g = (this.j - this.i) / this.h - this.d;
                    this.i = this.j;
                    if (this.g > 0L) {
                        --this.c;
                    }
                    if (this.g >= 0L) {
                        continue;
                    }
                    ++this.c;
                }
            }
        }
        catch (Exception ex) {
            System.err.println("Exception in Timer Thread.");
            ex.printStackTrace();
        }
    }
    
    public synchronized void c() {
        this.notifyAll();
    }
    
    public synchronized void d() {
        try {
            this.wait();
        }
        catch (InterruptedException ex) {}
    }
}
