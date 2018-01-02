// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class DefaultChannelCapacity
{
    public static final int INITIAL_DEFAULT_CAPACITY = 1024;
    private static final SynchronizedInt defaultCapacity_;
    
    static {
        defaultCapacity_ = new SynchronizedInt(1024);
    }
    
    public static int get() {
        return DefaultChannelCapacity.defaultCapacity_.get();
    }
    
    public static void set(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        DefaultChannelCapacity.defaultCapacity_.set(n);
    }
}
