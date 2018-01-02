// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.a;

import java.util.TimerTask;
import java.util.Timer;

public abstract class a
{
    private volatile boolean a;
    private final b b;
    
    public a() {
        this.a = false;
        this.b = new b(this);
    }
    
    protected abstract boolean a(final long p0);
    
    protected abstract void a();
    
    public final synchronized void a(final Timer timer) {
        this.a();
        timer.scheduleAtFixedRate(this.b, 0L, 16L);
        this.a = true;
    }
    
    public final boolean b() {
        return this.a;
    }
    
    protected final void c() {
        this.b.a();
    }
    
    public final void d() {
        this.b.cancel();
    }
}
