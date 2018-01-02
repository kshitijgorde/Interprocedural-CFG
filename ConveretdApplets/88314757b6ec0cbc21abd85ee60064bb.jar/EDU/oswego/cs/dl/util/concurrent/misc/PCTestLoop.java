// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.BrokenBarrierException;
import EDU.oswego.cs.dl.util.concurrent.CyclicBarrier;
import EDU.oswego.cs.dl.util.concurrent.Channel;

class PCTestLoop extends TestLoop
{
    final Channel primaryChannel;
    final Channel sharedChannel;
    
    public PCTestLoop(final RNG rng, final RNG rng2, final Fraction fraction, final int n, final CyclicBarrier cyclicBarrier, final Channel sharedChannel, final Channel primaryChannel) {
        super(rng, rng2, fraction, n, cyclicBarrier);
        this.sharedChannel = sharedChannel;
        this.primaryChannel = primaryChannel;
    }
    
    public Runnable testLoop(final boolean b) {
        return new Runnable() {
            private final /* synthetic */ boolean val$isProducer = val$isProducer;
            
            public void run() {
                Thread.currentThread().setPriority(5 - 1);
                final int value = RNG.itersPerBarrier.get();
                try {
                    try {
                        int n = (int)(PCTestLoop.this.iters * PCTestLoop.this.pshared.asDouble());
                        int n2 = PCTestLoop.this.iters - n;
                        int firstidx = PCTestLoop.this.firstidx;
                        PCTestLoop.this.barrier.barrier();
                        final ChanRNG chanRNG = (ChanRNG)PCTestLoop.this.primary;
                        for (int i = PCTestLoop.this.iters; i > 0; --i) {
                            ++firstidx;
                            if (i % value == 0) {
                                PCTestLoop.this.primary.exchange();
                            }
                            else {
                                Channel channel;
                                if (n > 0 && PCTestLoop.this.useShared[firstidx % PCTestLoop.this.useShared.length]) {
                                    --n;
                                    channel = PCTestLoop.this.sharedChannel;
                                }
                                else {
                                    --n2;
                                    channel = PCTestLoop.this.primaryChannel;
                                }
                                long n3;
                                if (this.val$isProducer) {
                                    n3 = chanRNG.producerNext(channel);
                                }
                                else {
                                    n3 = chanRNG.consumerNext(channel);
                                }
                                if (n3 % 2L == 0L && Thread.currentThread().isInterrupted()) {
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
                        PCTestLoop.this.barrier.barrier();
                    }
                    catch (InterruptedException ex3) {
                        Thread.currentThread().interrupt();
                    }
                    catch (BrokenBarrierException ex4) {}
                    finally {
                        Thread.currentThread();
                    }
                }
                finally {
                    try {
                        PCTestLoop.this.barrier.barrier();
                    }
                    catch (InterruptedException ex5) {
                        Thread.currentThread().interrupt();
                    }
                    catch (BrokenBarrierException ex6) {}
                }
            }
        };
    }
}
