// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.e;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executor;

public final class a implements Executor
{
    private final LinkedBlockingQueue a;
    private final Thread b;
    
    public a(final String s) {
        this.a = new LinkedBlockingQueue();
        this.b = new Thread(new d(this), s);
    }
    
    public final void a() {
        this.b.start();
    }
    
    public final void execute(final Runnable runnable) {
        if (this.b.isInterrupted()) {
            throw new IllegalStateException("Already interrupted.");
        }
        this.a.add(runnable);
    }
    
    public final void b() {
        this.a.clear();
        this.b.interrupt();
    }
}
