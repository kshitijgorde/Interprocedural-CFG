// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Random;

public class FJTaskRunner extends Thread
{
    protected final FJTaskRunnerGroup group;
    protected static final int INITIAL_CAPACITY = 4096;
    protected static final int MAX_CAPACITY = 1073741824;
    protected VolatileTaskRef[] deq;
    protected volatile int top;
    protected volatile int base;
    protected final Object barrier;
    protected boolean active;
    protected final Random victimRNG;
    protected int scanPriority;
    protected int runPriority;
    static final boolean COLLECT_STATS = true;
    protected int runs;
    protected int scans;
    protected int steals;
    
    protected FJTaskRunner(final FJTaskRunnerGroup group) {
        this.deq = VolatileTaskRef.newArray(4096);
        this.top = 0;
        this.base = 0;
        this.barrier = new Object();
        this.active = false;
        this.scanPriority = 2;
        this.runs = 0;
        this.scans = 0;
        this.steals = 0;
        this.group = group;
        this.victimRNG = new Random(System.identityHashCode(this));
        this.runPriority = this.getPriority();
        this.setDaemon(true);
    }
    
    protected void checkOverflow() {
        final int top = this.top;
        int base = this.base;
        if (top - base < this.deq.length - 1) {
            final int base2 = base & this.deq.length - 1;
            int top2 = this.top & this.deq.length - 1;
            if (top2 < base2) {
                top2 += this.deq.length;
            }
            this.top = top2;
            this.base = base2;
            for (int i = base2; i != top2; i = (i - 1 & this.deq.length - 1)) {
                if (this.deq[i].ref == null) {
                    break;
                }
                this.deq[i].ref = null;
            }
        }
        else {
            final int top3 = top - base;
            final int length = this.deq.length;
            final int n = length * 2;
            if (n >= 1073741824) {
                throw new Error("FJTask queue maximum capacity exceeded");
            }
            final VolatileTaskRef[] deq = new VolatileTaskRef[n];
            for (int j = 0; j < length; ++j) {
                deq[j] = this.deq[base++ & length - 1];
            }
            for (int k = length; k < n; ++k) {
                deq[k] = new VolatileTaskRef();
            }
            this.deq = deq;
            this.base = 0;
            this.top = top3;
        }
    }
    
    protected final void coInvoke(final FJTask fjTask, final FJTask fjTask2) {
        final int top = this.top;
        if (top < (this.base & this.deq.length - 1) + this.deq.length) {
            this.deq[top & this.deq.length - 1].put(fjTask);
            this.top = top + 1;
            if (!fjTask2.isDone()) {
                ++this.runs;
                fjTask2.run();
                fjTask2.setDone();
            }
            while (!fjTask.isDone()) {
                final FJTask pop = this.pop();
                if (pop != null) {
                    if (pop.isDone()) {
                        continue;
                    }
                    ++this.runs;
                    pop.run();
                    pop.setDone();
                    if (pop == fjTask) {
                        return;
                    }
                    continue;
                }
                else {
                    this.scan(fjTask);
                }
            }
        }
        else {
            this.slowCoInvoke(fjTask, fjTask2);
        }
    }
    
    protected final void coInvoke(final FJTask[] array) {
        final int n = array.length - 1;
        int top = this.top;
        if (n >= 0 && top + n < (this.base & this.deq.length - 1) + this.deq.length) {
            for (int i = 0; i < n; ++i) {
                this.deq[top++ & this.deq.length - 1].put(array[i]);
                this.top = top;
            }
            final FJTask fjTask = array[n];
            if (!fjTask.isDone()) {
                ++this.runs;
                fjTask.run();
                fjTask.setDone();
            }
            for (final FJTask fjTask2 : array) {
                while (!fjTask2.isDone()) {
                    final FJTask pop = this.pop();
                    if (pop != null) {
                        if (pop.isDone()) {
                            continue;
                        }
                        ++this.runs;
                        pop.run();
                        pop.setDone();
                    }
                    else {
                        this.scan(fjTask2);
                    }
                }
            }
        }
        else {
            this.slowCoInvoke(array);
        }
    }
    
    protected final synchronized FJTask confirmPop(final int n) {
        if (this.base <= n) {
            return this.deq[n & this.deq.length - 1].take();
        }
        final boolean b = false;
        this.base = (b ? 1 : 0);
        this.top = (b ? 1 : 0);
        return null;
    }
    
    protected FJTask confirmTake(final int base) {
        synchronized (this.barrier) {
            if (base < this.top) {
                // monitorexit(this.barrier)
                return this.deq[base & this.deq.length - 1].get();
            }
            this.base = base;
            // monitorexit(this.barrier)
            return null;
        }
    }
    
    protected int deqSize() {
        return this.deq.length;
    }
    
    protected final FJTaskRunnerGroup getGroup() {
        return this.group;
    }
    
    protected final FJTask pop() {
        final int top = this.top - 1;
        this.top = top;
        final int n = top;
        if (this.base + 1 < n) {
            return this.deq[n & this.deq.length - 1].take();
        }
        return this.confirmPop(n);
    }
    
    protected final void push(final FJTask fjTask) {
        final int top = this.top;
        if (top < (this.base & this.deq.length - 1) + this.deq.length) {
            this.deq[top & this.deq.length - 1].put(fjTask);
            this.top = top + 1;
        }
        else {
            this.slowPush(fjTask);
        }
    }
    
    protected final synchronized void put(final FJTask fjTask) {
        int n;
        while (true) {
            n = this.base - 1;
            if (this.top < n + this.deq.length) {
                break;
            }
            this.checkOverflow();
        }
        final int base = n & this.deq.length - 1;
        this.deq[base].put(fjTask);
        if (n != (this.base = base)) {
            int top = this.top & this.deq.length - 1;
            if (top < base) {
                top += this.deq.length;
            }
            this.top = top;
        }
    }
    
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final FJTask pop = this.pop();
                if (pop != null) {
                    if (pop.isDone()) {
                        continue;
                    }
                    ++this.runs;
                    pop.run();
                    pop.setDone();
                }
                else {
                    this.scanWhileIdling();
                }
            }
        }
        finally {
            this.group.setInactive(this);
        }
    }
    
    protected void scan(final FJTask fjTask) {
        FJTask fjTask2 = null;
        int n = 0;
        final FJTaskRunner[] array = this.group.getArray();
        int nextInt = this.victimRNG.nextInt(array.length);
        for (int i = 0; i < array.length; ++i) {
            final FJTaskRunner fjTaskRunner = array[nextInt];
            if (++nextInt >= array.length) {
                nextInt = 0;
            }
            if (fjTaskRunner != null && fjTaskRunner != this) {
                if (fjTask != null && fjTask.isDone()) {
                    break;
                }
                ++this.scans;
                fjTask2 = fjTaskRunner.take();
                if (fjTask2 != null) {
                    ++this.steals;
                    break;
                }
                if (this.isInterrupted()) {
                    break;
                }
                if (n == 0) {
                    n = 1;
                    this.setPriority(this.scanPriority);
                }
                else {
                    Thread.yield();
                }
            }
        }
        if (fjTask2 == null) {
            ++this.scans;
            fjTask2 = this.group.pollEntryQueue();
            if (fjTask2 != null) {
                ++this.steals;
            }
        }
        if (n != 0) {
            this.setPriority(this.runPriority);
        }
        if (fjTask2 != null && !fjTask2.isDone()) {
            ++this.runs;
            fjTask2.run();
            fjTask2.setDone();
        }
    }
    
    protected void scanWhileIdling() {
        FJTask fjTask = null;
        int n = 0;
        long n2 = 0L;
        final FJTaskRunner[] array = this.group.getArray();
        int nextInt = this.victimRNG.nextInt(array.length);
        do {
            for (int i = 0; i < array.length; ++i) {
                final FJTaskRunner fjTaskRunner = array[nextInt];
                if (++nextInt >= array.length) {
                    nextInt = 0;
                }
                if (fjTaskRunner != null && fjTaskRunner != this) {
                    ++this.scans;
                    fjTask = fjTaskRunner.take();
                    if (fjTask != null) {
                        ++this.steals;
                        if (n != 0) {
                            this.setPriority(this.runPriority);
                        }
                        this.group.setActive(this);
                        break;
                    }
                }
            }
            if (fjTask == null) {
                if (this.isInterrupted()) {
                    return;
                }
                ++this.scans;
                fjTask = this.group.pollEntryQueue();
                if (fjTask != null) {
                    ++this.steals;
                    if (n != 0) {
                        this.setPriority(this.runPriority);
                    }
                    this.group.setActive(this);
                }
                else {
                    ++n2;
                    if (n2 >= 15L) {
                        this.group.checkActive(this, n2);
                        if (this.isInterrupted()) {
                            return;
                        }
                        continue;
                    }
                    else if (n == 0) {
                        n = 1;
                        this.setPriority(this.scanPriority);
                    }
                    else {
                        Thread.yield();
                    }
                }
            }
        } while (fjTask == null);
        if (!fjTask.isDone()) {
            ++this.runs;
            fjTask.run();
            fjTask.setDone();
        }
    }
    
    protected void setRunPriority(final int runPriority) {
        this.runPriority = runPriority;
    }
    
    protected void setScanPriority(final int scanPriority) {
        this.scanPriority = scanPriority;
    }
    
    protected void slowCoInvoke(final FJTask fjTask, final FJTask fjTask2) {
        this.push(fjTask);
        FJTask.invoke(fjTask2);
        this.taskJoin(fjTask);
    }
    
    protected void slowCoInvoke(final FJTask[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.push(array[i]);
        }
        for (int j = 0; j < array.length; ++j) {
            this.taskJoin(array[j]);
        }
    }
    
    protected synchronized void slowPush(final FJTask fjTask) {
        this.checkOverflow();
        this.push(fjTask);
    }
    
    protected final synchronized FJTask take() {
        final int base = this.base++;
        if (base < this.top) {
            return this.confirmTake(base);
        }
        this.base = base;
        return null;
    }
    
    protected final void taskJoin(final FJTask fjTask) {
        while (!fjTask.isDone()) {
            final FJTask pop = this.pop();
            if (pop != null) {
                if (pop.isDone()) {
                    continue;
                }
                ++this.runs;
                pop.run();
                pop.setDone();
                if (pop == fjTask) {
                    return;
                }
                continue;
            }
            else {
                this.scan(fjTask);
            }
        }
    }
    
    protected final void taskYield() {
        final FJTask pop = this.pop();
        if (pop != null) {
            if (!pop.isDone()) {
                ++this.runs;
                pop.run();
                pop.setDone();
            }
        }
        else {
            this.scan(null);
        }
    }
    
    protected static final class VolatileTaskRef
    {
        protected volatile FJTask ref;
        
        protected final FJTask get() {
            return this.ref;
        }
        
        protected static VolatileTaskRef[] newArray(final int n) {
            final VolatileTaskRef[] array = new VolatileTaskRef[n];
            for (int i = 0; i < n; ++i) {
                array[i] = new VolatileTaskRef();
            }
            return array;
        }
        
        protected final void put(final FJTask ref) {
            this.ref = ref;
        }
        
        protected final FJTask take() {
            final FJTask ref = this.ref;
            this.ref = null;
            return ref;
        }
    }
}
