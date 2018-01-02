// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class TimedCallable extends ThreadFactoryUser implements Callable
{
    private final Callable function;
    private final long millis;
    
    public TimedCallable(final Callable function, final long millis) {
        this.function = function;
        this.millis = millis;
    }
    
    public Object call() throws Exception {
        final FutureResult futureResult = new FutureResult();
        final Thread thread = this.getThreadFactory().newThread(futureResult.setter(this.function));
        thread.start();
        try {
            return futureResult.timedGet(this.millis);
        }
        catch (InterruptedException ex) {
            thread.interrupt();
            throw ex;
        }
    }
}
