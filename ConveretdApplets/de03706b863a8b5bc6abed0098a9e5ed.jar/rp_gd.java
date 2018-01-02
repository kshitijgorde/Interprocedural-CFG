import javax.swing.SwingUtilities;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_gd implements Runnable, Future
{
    private volatile rp_fQ a;
    private final FutureTask a;
    private final PropertyChangeSupport a;
    private static final rp_cx a;
    private static ExecutorService a;
    
    public static void b() {
        rp_gd.a = null;
    }
    
    public rp_gd() {
        this.a = new rp_eh(this, new rp_ee(this));
        this.a = rp_fQ.a;
        this.a = new rp_eW(this, this);
    }
    
    protected abstract Object a();
    
    public final void run() {
        this.a.run();
    }
    
    protected void a() {
    }
    
    public final void c() {
        a().execute(this);
    }
    
    public final boolean cancel(final boolean b) {
        return this.a.cancel(b);
    }
    
    public final boolean isCancelled() {
        return this.a.isCancelled();
    }
    
    public final boolean isDone() {
        return this.a.isDone();
    }
    
    public final Object get() {
        return this.a.get();
    }
    
    public final Object get(final long n, final TimeUnit timeUnit) {
        return this.a.get(n, timeUnit);
    }
    
    private static synchronized ExecutorService a() {
        if (rp_gd.a == null) {
            rp_gd.a = new rp_eb(0, 10, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new rp_ed());
        }
        return rp_gd.a;
    }
    
    static {
        a = new rp_bn();
        rp_gd.a = null;
    }
}
