// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class TimeoutException extends InterruptedException
{
    public final long duration;
    
    public TimeoutException(final long duration) {
        this.duration = duration;
    }
    
    public TimeoutException(final long duration, final String s) {
        super(s);
        this.duration = duration;
    }
}
