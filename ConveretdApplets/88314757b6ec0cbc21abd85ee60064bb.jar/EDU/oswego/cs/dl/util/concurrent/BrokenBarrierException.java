// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class BrokenBarrierException extends RuntimeException
{
    public final int index;
    
    public BrokenBarrierException(final int index) {
        this.index = index;
    }
    
    public BrokenBarrierException(final int index, final String s) {
        super(s);
        this.index = index;
    }
}
