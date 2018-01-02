// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Slot;
import EDU.oswego.cs.dl.util.concurrent.BoundedChannel;

public class FIFOSlot implements BoundedChannel
{
    private final Slot slot_;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$FIFOSemaphore;
    
    public FIFOSlot() {
        try {
            this.slot_ = new Slot((FIFOSlot.class$EDU$oswego$cs$dl$util$concurrent$FIFOSemaphore != null) ? FIFOSlot.class$EDU$oswego$cs$dl$util$concurrent$FIFOSemaphore : (FIFOSlot.class$EDU$oswego$cs$dl$util$concurrent$FIFOSemaphore = class$("EDU.oswego.cs.dl.util.concurrent.FIFOSemaphore")));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new Error("Cannot make Slot?");
        }
    }
    
    public int capacity() {
        return 1;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public boolean offer(final Object o, final long n) throws InterruptedException {
        return this.slot_.offer(o, n);
    }
    
    public Object peek() {
        return this.slot_.peek();
    }
    
    public Object poll(final long n) throws InterruptedException {
        return this.slot_.poll(n);
    }
    
    public void put(final Object o) throws InterruptedException {
        this.slot_.put(o);
    }
    
    public Object take() throws InterruptedException {
        return this.slot_.take();
    }
}
