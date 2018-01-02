// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

final class e extends Thread
{
    private /* synthetic */ aZ a;
    
    private e(final aZ a, final String s) {
        this.a = a;
        super(s);
        this.setDaemon(false);
    }
    
    public final void run() {
        while (true) {
            System.out.println("Thread " + this.getName() + " is IDLE");
            final Runnable a = this.a.a.a();
            System.out.println("Thread " + this.getName() + " is ACTIVE");
            if (a == this.a.c) {
                break;
            }
            a.run();
        }
    }
}
