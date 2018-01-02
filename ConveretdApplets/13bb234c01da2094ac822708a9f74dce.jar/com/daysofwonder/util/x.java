// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

final class x extends Thread
{
    private volatile boolean b;
    private volatile boolean c;
    final /* synthetic */ a a;
    
    public x(final a a) {
        this.a = a;
        this.b = true;
        this.c = false;
        this.setPriority(this.getThreadGroup().getMaxPriority());
    }
    
    public void a() {
        this.b = false;
    }
    
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Thread.sleep(this.a.e);
            }
            catch (InterruptedException ex) {
                break;
            }
            synchronized (this) {
                if (this.isInterrupted()) {
                    break;
                }
                this.c = true;
                if (this.b) {
                    this.a.d();
                }
                if (this.a.d == com.daysofwonder.util.a.a) {
                    this.a.a(this);
                    break;
                }
                if (this.a.d != com.daysofwonder.util.a.c) {
                    continue;
                }
                this.a.c();
            }
        }
    }
}
