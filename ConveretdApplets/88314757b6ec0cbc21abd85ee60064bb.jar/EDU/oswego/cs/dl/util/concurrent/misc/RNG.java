// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Rendezvous;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedLong;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedInt;
import java.io.Serializable;

abstract class RNG implements Serializable, Comparable
{
    static final int firstSeed = 4321;
    static final int rmod = Integer.MAX_VALUE;
    static final int rmul = 16807;
    static int lastSeed;
    static final int smod = 32749;
    static final int smul = 3125;
    static final Object constructionLock;
    static final SynchronizedInt computeLoops;
    static final SynchronizedInt syncMode;
    static final SynchronizedInt producerMode;
    static final SynchronizedInt consumerMode;
    static final SynchronizedInt bias;
    static final SynchronizedLong timeout;
    static final SynchronizedInt exchangeParties;
    static final SynchronizedInt sequenceNumber;
    static final SynchronizedInt itersPerBarrier;
    static Rendezvous[] exchangers_;
    final int cloops;
    final int pcBias;
    final int smode;
    final int pmode;
    final int cmode;
    final long waitTime;
    Rendezvous exchanger_;
    static /* synthetic */ Class class$EDU$oswego$cs$dl$util$concurrent$misc$RNG;
    
    static {
        RNG.lastSeed = 4321;
        constructionLock = ((RNG.class$EDU$oswego$cs$dl$util$concurrent$misc$RNG != null) ? RNG.class$EDU$oswego$cs$dl$util$concurrent$misc$RNG : (RNG.class$EDU$oswego$cs$dl$util$concurrent$misc$RNG = class$("EDU.oswego.cs.dl.util.concurrent.misc.RNG")));
        computeLoops = new SynchronizedInt(16, RNG.constructionLock);
        syncMode = new SynchronizedInt(0, RNG.constructionLock);
        producerMode = new SynchronizedInt(0, RNG.constructionLock);
        consumerMode = new SynchronizedInt(0, RNG.constructionLock);
        bias = new SynchronizedInt(0, RNG.constructionLock);
        timeout = new SynchronizedLong(100L, RNG.constructionLock);
        exchangeParties = new SynchronizedInt(1, RNG.constructionLock);
        sequenceNumber = new SynchronizedInt(0, RNG.constructionLock);
        itersPerBarrier = new SynchronizedInt(0, RNG.constructionLock);
    }
    
    RNG() {
        this.cloops = RNG.computeLoops.get();
        this.pcBias = RNG.bias.get();
        this.smode = RNG.syncMode.get();
        this.pmode = RNG.producerMode.get();
        this.cmode = RNG.consumerMode.get();
        this.waitTime = RNG.timeout.get();
        this.exchanger_ = null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public int compareTo(final Object o) {
        final int hashCode = this.hashCode();
        final int hashCode2 = o.hashCode();
        if (hashCode < hashCode2) {
            return -1;
        }
        if (hashCode > hashCode2) {
            return 1;
        }
        return 0;
    }
    
    protected final long compute(long n) {
        for (int n2 = (int)((n & 0x7FFFFFFFL) % (this.cloops * 2)) + 1, i = 0; i < n2; ++i) {
            n = n * 16807L % 2147483647L;
        }
        return (n == 0L) ? 4321L : n;
    }
    
    public void exchange() throws InterruptedException {
        final Runnable runnable = (Runnable)this.getExchanger().rendezvous(new UpdateCommand(this));
        if (runnable != null) {
            runnable.run();
        }
    }
    
    public long get() {
        return this.internalGet();
    }
    
    synchronized Rendezvous getExchanger() {
        if (this.exchanger_ == null) {
            synchronized (RNG.constructionLock) {
                this.exchanger_ = RNG.exchangers_[RNG.sequenceNumber.increment() % RNG.exchangers_.length];
            }
            // monitorexit(RNG.constructionLock)
        }
        return this.exchanger_;
    }
    
    protected abstract long internalGet();
    
    protected abstract void internalUpdate();
    
    public long next() {
        this.internalUpdate();
        return this.internalGet();
    }
    
    static long nextSeed() {
        synchronized (RNG.constructionLock) {
            final long n = RNG.lastSeed;
            RNG.lastSeed = RNG.lastSeed * 3125 % 32749;
            if (RNG.lastSeed == 0) {
                RNG.lastSeed = (int)System.currentTimeMillis();
            }
            // monitorexit(RNG.constructionLock)
            return n;
        }
    }
    
    static void reset(final int n) {
        synchronized (RNG.constructionLock) {
            RNG.sequenceNumber.set(-1);
            int value = RNG.exchangeParties.get();
            if (n < value) {
                value = n;
            }
            if (n % value != 0) {
                throw new Error("need even multiple of parties");
            }
            RNG.exchangers_ = new Rendezvous[n / value];
            for (int i = 0; i < RNG.exchangers_.length; ++i) {
                RNG.exchangers_[i] = new Rendezvous(value);
            }
        }
        // monitorexit(RNG.constructionLock)
    }
    
    protected abstract void set(final long p0);
    
    public void update() {
        this.internalUpdate();
    }
}
