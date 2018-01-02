// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class ThreadedExecutor extends ThreadFactoryUser implements Executor
{
    public synchronized void execute(final Runnable runnable) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        this.getThreadFactory().newThread(runnable).start();
    }
}
