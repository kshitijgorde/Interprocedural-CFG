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

final class H implements Runnable
{
    private /* synthetic */ t a;
    private final /* synthetic */ K b;
    
    H(final t a, final K b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        this.a.b.a().a("Restoring...");
        try {
            this.a.b.a(this.b);
            this.a.b.c();
        }
        finally {
            this.a.b.a().c();
        }
        this.a.b.a().c();
    }
}
