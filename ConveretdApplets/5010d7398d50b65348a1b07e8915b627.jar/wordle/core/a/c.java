// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.a;

import java.util.TimerTask;
import java.util.Timer;

public final class c extends Timer
{
    private TimerTask a;
    
    public c(final String s) {
        super(s);
        this.a = null;
    }
    
    public final synchronized void scheduleAtFixedRate(final TimerTask a, final long n, final long n2) {
        if (this.a != null) {
            this.a.cancel();
        }
        super.scheduleAtFixedRate(this.a = a, n, n2);
    }
}
