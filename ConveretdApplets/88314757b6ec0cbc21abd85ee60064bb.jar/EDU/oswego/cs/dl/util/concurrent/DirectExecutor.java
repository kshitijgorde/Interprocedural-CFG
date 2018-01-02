// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class DirectExecutor implements Executor
{
    public void execute(final Runnable runnable) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        runnable.run();
    }
}
