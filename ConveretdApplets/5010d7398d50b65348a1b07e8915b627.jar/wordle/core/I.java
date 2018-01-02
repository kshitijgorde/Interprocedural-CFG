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

final class I implements Runnable
{
    private /* synthetic */ t a;
    private final /* synthetic */ Runnable b;
    
    I(final t a, final Runnable b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        try {
            t.a("Running task.");
            this.b.run();
            t.a("Task has finished running.");
        }
        finally {
            t.a("Releasing baton...");
            this.a.d.release();
            t.a("...released.");
        }
        t.a("Releasing baton...");
        this.a.d.release();
        t.a("...released.");
    }
}
