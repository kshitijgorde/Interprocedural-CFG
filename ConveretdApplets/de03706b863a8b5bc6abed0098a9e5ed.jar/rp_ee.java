import javax.swing.SwingUtilities;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_ee implements Callable
{
    private /* synthetic */ rp_gd a;
    
    rp_ee(final rp_gd a) {
        this.a = a;
    }
    
    public final Object call() {
        rp_gd.a(this.a, rp_fQ.b);
        return this.a.a();
    }
}
