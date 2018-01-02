// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class ThreadFactoryUser
{
    protected ThreadFactory threadFactory_;
    
    public ThreadFactoryUser() {
        this.threadFactory_ = new DefaultThreadFactory();
    }
    
    public synchronized ThreadFactory getThreadFactory() {
        return this.threadFactory_;
    }
    
    public synchronized ThreadFactory setThreadFactory(final ThreadFactory threadFactory_) {
        final ThreadFactory threadFactory_2 = this.threadFactory_;
        this.threadFactory_ = threadFactory_;
        return threadFactory_2;
    }
    
    protected static class DefaultThreadFactory implements ThreadFactory
    {
        public Thread newThread(final Runnable runnable) {
            return new Thread(runnable);
        }
    }
}
