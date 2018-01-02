// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Iterator;

public class ObservableSync implements Sync
{
    protected final CopyOnWriteArraySet observers_;
    protected Object arg_;
    
    public ObservableSync(final Object arg_) {
        this.observers_ = new CopyOnWriteArraySet();
        this.arg_ = arg_;
    }
    
    public void acquire() {
        final Object notificationArgument = this.getNotificationArgument();
        final Iterator iterator = this.observers_.iterator();
        while (iterator.hasNext()) {
            iterator.next().onAcquire(notificationArgument);
        }
    }
    
    public void attach(final SyncObserver syncObserver) {
        this.observers_.add(syncObserver);
    }
    
    public boolean attempt(final long n) {
        this.acquire();
        return true;
    }
    
    public void detach(final SyncObserver syncObserver) {
        this.observers_.remove(syncObserver);
    }
    
    public synchronized Object getNotificationArgument() {
        return this.arg_;
    }
    
    public Iterator observers() {
        return this.observers_.iterator();
    }
    
    public void release() {
        final Object notificationArgument = this.getNotificationArgument();
        final Iterator iterator = this.observers_.iterator();
        while (iterator.hasNext()) {
            iterator.next().onRelease(notificationArgument);
        }
    }
    
    public synchronized Object setNotificationArgument(final Object arg_) {
        final Object arg_2 = this.arg_;
        this.arg_ = arg_;
        return arg_2;
    }
    
    public interface SyncObserver
    {
        void onAcquire(final Object p0);
        
        void onRelease(final Object p0);
    }
}
