// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.a;

import java.util.Timer;
import java.util.TimerTask;

final class b extends TimerTask
{
    private long a;
    private /* synthetic */ a b;
    
    private b(final a b, final byte b2) {
        this.b = b;
        this.a = System.currentTimeMillis();
    }
    
    final void a() {
        this.a = System.currentTimeMillis();
    }
    
    public final void run() {
        if (!this.b.a(System.currentTimeMillis() - this.a)) {
            wordle.core.a.a.a(this.b, false);
            this.cancel();
        }
    }
}
