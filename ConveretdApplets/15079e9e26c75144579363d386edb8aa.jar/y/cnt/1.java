// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

import java.io.IOException;

class 1 extends Thread
{
    boolean b;
    boolean c;
    12 d;
    
    1(final 12 d) {
        this.d = d;
        this.start();
    }
    
    public void run() {
        while (true) {
            synchronized (this) {
                if (this.b) {
                    // monitorexit(this)
                    break;
                }
                try {
                    this.wait(900000L);
                }
                catch (InterruptedException ex) {}
            }
            synchronized (this.d.e) {
                this.d.d.x(80);
                try {
                    this.d.Ca();
                }
                catch (IOException ex2) {}
                // monitorexit(this.d.e)
                continue;
            }
            break;
        }
        synchronized (this) {
            this.c = true;
            this.notify();
        }
    }
    
    synchronized void a() {
        this.b = true;
        this.notify();
        while (!this.c) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
}
