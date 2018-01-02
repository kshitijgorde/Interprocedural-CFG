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
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import wordle.core.e.a;
import wordle.core.c.e;
import java.util.concurrent.Callable;

final class F implements Callable
{
    private /* synthetic */ t a;
    private final /* synthetic */ r b;
    private final /* synthetic */ e c;
    private final /* synthetic */ wordle.core.b.a.e d;
    
    F(final t a, final r b, final e c, final wordle.core.b.a.e d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    private K a() {
        t.a("inside constructDrawables call()");
        this.a.b.a().a("Constructing glyphs");
        try {
            return K.a(this.b, this.c, this.d, 1000.0);
        }
        finally {
            this.a.b.a().c();
            t.a("returning from constructDrawables call()");
        }
    }
    
    public final String toString() {
        return "constructDrawables callable";
    }
}
