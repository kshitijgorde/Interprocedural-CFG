// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import wordle.core.b.c;
import wordle.core.fitness.SpiralPositionSetter;
import wordle.core.fitness.PlacementStrategy;
import java.awt.geom.Dimension2D;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import wordle.core.e.a;
import java.awt.geom.Rectangle2D;
import wordle.core.c.e;

final class G implements Runnable
{
    private /* synthetic */ t a;
    private final /* synthetic */ K b;
    private final /* synthetic */ e c;
    
    G(final t a, final K b, final e c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final void run() {
        this.a.b.a(this.b);
        if (!this.b.c()) {
            this.a.b.c();
            return;
        }
        final D a = this.b.a(this.a.b.getWidth() / this.a.b.getHeight());
        this.b.a(new Rectangle2D.Double(0.0, 0.0, a.a, a.b));
        try {
            this.a.b.a().b();
            try {
                t.a(this.a, this.c, a);
            }
            finally {
                this.a.b.a().c();
            }
            this.a.b.a().c();
        }
        finally {
            this.b.b();
        }
        this.b.b();
    }
}
