import javax.swing.SwingUtilities;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_eh extends FutureTask
{
    private /* synthetic */ rp_gd a;
    
    rp_eh(final rp_gd a, final Callable callable) {
        this.a = a;
        super(callable);
    }
    
    protected final void done() {
        rp_gd.a(this.a);
        rp_gd.a(this.a, rp_fQ.c);
    }
}
