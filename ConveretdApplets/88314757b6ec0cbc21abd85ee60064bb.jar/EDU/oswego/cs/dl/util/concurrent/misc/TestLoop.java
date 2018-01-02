// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.BrokenBarrierException;
import EDU.oswego.cs.dl.util.concurrent.CyclicBarrier;

class TestLoop
{
    final RNG shared;
    final RNG primary;
    final int iters;
    final Fraction pshared;
    final CyclicBarrier barrier;
    final boolean[] useShared;
    final int firstidx;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$PrioritySemRNG;
    
    public TestLoop(final RNG shared, final RNG primary, final Fraction pshared, final int iters, final CyclicBarrier barrier) {
        this.shared = shared;
        this.primary = primary;
        this.pshared = pshared;
        this.iters = iters;
        this.barrier = barrier;
        this.firstidx = (int)this.primary.get();
        final int n = (int)this.pshared.numerator();
        final int n2 = (int)this.pshared.denominator();
        if (n == 0 || this.primary == this.shared) {
            (this.useShared = new boolean[1])[0] = false;
        }
        else if (n >= n2) {
            (this.useShared = new boolean[1])[0] = true;
        }
        else {
            int n3 = 1024 / n2;
            if (n3 < 1) {
                n3 = 1;
            }
            this.useShared = new boolean[n2 * n3];
            for (int i = 0; i < n * n3; ++i) {
                this.useShared[i] = true;
            }
            for (int j = n * n3; j < n2 * n3; ++j) {
                this.useShared[j] = false;
            }
            for (int k = 1; k < this.useShared.length; ++k) {
                final int n4 = (int)(this.shared.next() & 0x7FFFFFFFL) % (k + 1);
                final boolean b = this.useShared[k];
                this.useShared[k] = this.useShared[n4];
                this.useShared[n4] = b;
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public Runnable testLoop() {
        return new Runnable() {
            public void run() {
                final int value = RNG.itersPerBarrier.get();
                try {
                    try {
                        int n = -1;
                        if (TestLoop.this.primary.getClass().equals((TestLoop.class$EDU$oswego$cs$dl$util$concurrent$misc$PrioritySemRNG != null) ? TestLoop.class$EDU$oswego$cs$dl$util$concurrent$misc$PrioritySemRNG : (TestLoop.class$EDU$oswego$cs$dl$util$concurrent$misc$PrioritySemRNG = TestLoop.class$("EDU.oswego.cs.dl.util.concurrent.misc.PrioritySemRNG")))) {
                            n = 2 - (int)(TestLoop.this.primary.get() % 5L);
                        }
                        Thread.currentThread().setPriority(5 + n);
                        int n2 = (int)(TestLoop.this.iters * TestLoop.this.pshared.asDouble());
                        int n3 = TestLoop.this.iters - n2;
                        int firstidx = TestLoop.this.firstidx;
                        TestLoop.this.barrier.barrier();
                        for (int i = TestLoop.this.iters; i > 0; --i) {
                            ++firstidx;
                            if (i % value == 0) {
                                TestLoop.this.primary.exchange();
                            }
                            else {
                                RNG rng;
                                if (n2 > 0 && TestLoop.this.useShared[firstidx % TestLoop.this.useShared.length]) {
                                    --n2;
                                    rng = TestLoop.this.shared;
                                }
                                else {
                                    --n3;
                                    rng = TestLoop.this.primary;
                                }
                                if (rng.next() % 2L == 0L && Thread.currentThread().isInterrupted()) {
                                    break;
                                }
                            }
                        }
                    }
                    catch (BrokenBarrierException ex) {}
                    catch (InterruptedException ex2) {
                        Thread.currentThread().interrupt();
                    }
                    try {
                        TestLoop.this.barrier.barrier();
                    }
                    catch (BrokenBarrierException ex3) {}
                    catch (InterruptedException ex4) {
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        Thread.currentThread();
                    }
                }
                finally {
                    try {
                        TestLoop.this.barrier.barrier();
                    }
                    catch (BrokenBarrierException ex5) {}
                    catch (InterruptedException ex6) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
    }
}
