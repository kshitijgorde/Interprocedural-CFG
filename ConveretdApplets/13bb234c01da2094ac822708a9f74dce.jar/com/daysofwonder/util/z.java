// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

class z extends Thread
{
    private f b;
    final /* synthetic */ a a;
    
    public z(final a a) {
        this.a = a;
        this.b = new f(1, f.b);
        this.setDaemon(true);
    }
    
    public void run() {
        while (!this.isInterrupted()) {
            try {
                ((B)this.b.a()).a(this.a, null);
                yield();
                continue;
            }
            catch (InterruptedException ex) {
                t.a("TBExecuteTrhread interruptedException: " + this.isInterrupted());
            }
            break;
        }
        t.a("TBExecuteTrhread out");
    }
    
    public void a(final B b) {
        try {
            this.b.a(b);
        }
        catch (s s) {}
    }
}
