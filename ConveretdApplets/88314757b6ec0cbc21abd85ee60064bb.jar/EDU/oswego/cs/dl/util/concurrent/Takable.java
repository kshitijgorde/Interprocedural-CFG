// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public interface Takable
{
    Object poll(final long p0) throws InterruptedException;
    
    Object take() throws InterruptedException;
}
