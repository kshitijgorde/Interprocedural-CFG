// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.e;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executor;

final class d implements Runnable
{
    private /* synthetic */ a a;
    
    d(final a a) {
        this.a = a;
    }
    
    public final void run() {
        try {
            while (true) {
                this.a.a.take().run();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
