// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;

final class u implements Runnable
{
    private /* synthetic */ Runnable a;
    private /* synthetic */ t b;
    
    u(final t b, final Runnable a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        this.b.d.decrementAndGet();
        Thread.currentThread();
        if (this.b.b) {
            f.b("Starting " + this.b.c + " task: " + this.a.getClass().getSimpleName());
        }
        try {
            this.a.run();
        }
        catch (Throwable t) {
            f.a(t);
        }
        finally {
            if (this.b.b) {
                f.b("Finished " + this.b.c + " task: " + this.a.getClass().getSimpleName());
            }
        }
        if (this.b.e) {
            synchronized (this.b) {
                try {
                    this.b.wait();
                    t.a(this.b, false);
                }
                catch (InterruptedException ex) {
                    f.b("TaskExecutor " + this.b.c + " interrupted while paused.");
                }
            }
        }
    }
}
