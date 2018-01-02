// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public interface Channel extends Puttable, Takable
{
    boolean offer(final Object p0, final long p1) throws InterruptedException;
    
    Object peek();
    
    Object poll(final long p0) throws InterruptedException;
    
    void put(final Object p0) throws InterruptedException;
    
    Object take() throws InterruptedException;
}
