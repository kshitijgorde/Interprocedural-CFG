import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;
import javax.swing.SwingUtilities;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_eW extends PropertyChangeSupport
{
    rp_eW(final rp_gd rp_gd, final Object o) {
        super(o);
    }
    
    public final void firePropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (SwingUtilities.isEventDispatchThread()) {
            super.firePropertyChange(propertyChangeEvent);
            return;
        }
        rp_gd.a.a(false, new rp_aI(this, propertyChangeEvent));
    }
}
