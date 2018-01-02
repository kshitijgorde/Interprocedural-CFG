import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_ed implements ThreadFactory
{
    private AtomicInteger a;
    
    rp_ed() {
        this.a = new AtomicInteger(1);
    }
    
    public final Thread newThread(final Runnable runnable) {
        final StringBuilder sb;
        (sb = new StringBuilder("SwingWorker-pool-")).append(System.identityHashCode(this));
        sb.append("-thread-");
        sb.append(this.a.getAndIncrement());
        final Thread thread;
        if ((thread = new Thread(runnable, sb.toString())).isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        return thread;
    }
}
