// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class aZ
{
    private final aZ a;
    private final int b;
    private final Runnable c;
    private BlockingQueue d;
    private static /* synthetic */ boolean e;
    
    public aZ(final int b) {
        this.c = new aI(this);
        this.d = new LinkedBlockingQueue();
        if (!aZ.e && b <= 0) {
            throw new AssertionError();
        }
        this.a = this;
        this.b = b;
        System.out.println("Creating thread pool...");
        for (int i = 0; i < b; ++i) {
            new e(this, "PoolThread-" + i, null).start();
        }
    }
    
    public final Runnable a() {
        try {
            return this.d.take();
        }
        catch (InterruptedException ex) {
            return null;
        }
    }
    
    public final void a(final Runnable runnable) {
        if (!aZ.e && runnable == null) {
            throw new AssertionError();
        }
        try {
            this.d.put(runnable);
        }
        catch (InterruptedException ex) {}
    }
    
    public final void b() {
        this.d.clear();
    }
    
    public final void c() {
        for (int i = 0; i < this.b; ++i) {
            this.a(this.c);
        }
    }
    
    static {
        aZ.e = !aZ.class.desiredAssertionStatus();
    }
}
