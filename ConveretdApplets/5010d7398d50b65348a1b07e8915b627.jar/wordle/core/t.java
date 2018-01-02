// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import wordle.core.b.c;
import wordle.core.fitness.SpiralPositionSetter;
import java.awt.geom.Rectangle2D;
import wordle.core.fitness.PlacementStrategy;
import java.awt.geom.Dimension2D;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import wordle.core.c.e;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import wordle.core.e.a;

public final class t
{
    private static boolean a;
    private final n b;
    private final a c;
    private final Semaphore d;
    
    static {
        t.a = false;
    }
    
    public t(final n b) {
        this.c = new a("LayoutController executor");
        this.d = new Semaphore(1);
        this.b = b;
    }
    
    public final void a() {
        this.c.a();
    }
    
    public final void b() {
        this.c.b();
    }
    
    private synchronized void a(final Runnable runnable) {
        new StringBuilder("Invoke: ").append(runnable).toString();
        final long id = Thread.currentThread().getId();
        try {
            this.d.acquire();
        }
        catch (InterruptedException ex) {
            return;
        }
        try {
            this.c.execute(new I(this, runnable));
        }
        catch (RejectedExecutionException ex2) {
            System.err.println(String.valueOf(id) + " suffered rejected execute; releasing baton.");
            this.d.release();
        }
    }
    
    public final synchronized void a(final K k) {
        this.a(new H(this, k));
    }
    
    public final synchronized void a(final K k, final e e) {
        this.a(new G(this, k, e));
    }
    
    private synchronized Object a(Callable futureTask) {
        new StringBuilder("enter blockingCall ").append(futureTask).toString();
        futureTask = new FutureTask<Object>((Callable<?>)futureTask);
        new StringBuilder("invoking future ").append(futureTask).append("...").toString();
        this.a(futureTask);
        new StringBuilder("invoked ").append(futureTask).append(".").toString();
        try {
            new StringBuilder("Waiting on future ").append(futureTask).append("...").toString();
            return futureTask.get();
        }
        catch (InterruptedException ex) {
            throw new RuntimeException("Unexpected interruption: " + ex);
        }
        catch (ExecutionException ex2) {
            throw new RuntimeException(ex2);
        }
        finally {
            new StringBuilder("...got result of ").append(futureTask).append(".").toString();
        }
    }
    
    public final K a(final r r, final e e, final wordle.core.b.a.e e2) {
        try {
            return (K)this.a(new F(this, r, e, e2));
        }
        finally {}
    }
    
    private static PlacementStrategy a(final Class clazz, final Dimension2D dimension2D) {
        try {
            return clazz.getConstructor(Dimension2D.class).newInstance(dimension2D);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
