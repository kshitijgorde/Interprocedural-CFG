// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public interface Executor
{
    void execute(final Runnable p0) throws InterruptedException;
}
