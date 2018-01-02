// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;

public final class t
{
    private ExecutorService a;
    private boolean b;
    private String c;
    private AtomicInteger d;
    private boolean e;
    
    public t(final String c) {
        this.b = true;
        this.d = new AtomicInteger(0);
        this.c = c;
        (this.a = Executors.newFixedThreadPool(1)).execute(new v(this));
    }
    
    public final void a(final boolean b) {
        this.b = false;
    }
    
    public final void a(final Runnable runnable) {
        this.d.incrementAndGet();
        this.a.execute(new u(this, runnable));
    }
    
    public final void a() {
        f.b("TaskExecutor " + this.c + " shutting down.");
        this.a.shutdownNow();
    }
}
