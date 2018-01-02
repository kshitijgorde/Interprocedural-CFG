// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import org.jruby.runtime.Arity;
import org.jruby.RubyNumeric;
import org.jruby.exceptions.RaiseException;
import java.util.LinkedList;
import org.jruby.RubyBoolean;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyThread;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;
import org.jruby.CompatVersion;
import org.jruby.anno.JRubyMethod;
import org.jruby.internal.runtime.ThreadService;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import java.io.IOException;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class ThreadLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        runtime.getThread().defineAnnotatedMethods(ThreadMethods.class);
        Mutex.setup(runtime);
        ConditionVariable.setup(runtime);
        Queue.setup(runtime);
        SizedQueue.setup(runtime);
    }
    
    public static class ThreadMethods
    {
        @JRubyMethod(name = { "exclusive" }, meta = true, compat = CompatVersion.RUBY1_8)
        public static IRubyObject exclusive(final ThreadContext context, final IRubyObject receiver, final Block block) {
            final ThreadService service = context.getRuntime().getThreadService();
            final boolean old = service.getCritical();
            try {
                service.setCritical(true);
                return block.yield(receiver.getRuntime().getCurrentContext(), null);
            }
            finally {
                service.setCritical(old);
            }
        }
    }
    
    @JRubyClass(name = { "Mutex" })
    public static class Mutex extends RubyObject
    {
        private RubyThread owner;
        
        @JRubyMethod(name = { "new" }, rest = true, meta = true)
        public static Mutex newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
            final Mutex result = new Mutex(context.getRuntime(), (RubyClass)recv);
            result.callInit(args, block);
            return result;
        }
        
        public Mutex(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
            this.owner = null;
        }
        
        public static void setup(final Ruby runtime) {
            final RubyClass cMutex = runtime.defineClass("Mutex", runtime.getObject(), new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new Mutex(runtime, klass);
                }
            });
            cMutex.setReifiedClass(Mutex.class);
            cMutex.defineAnnotatedMethods(Mutex.class);
        }
        
        @JRubyMethod(name = { "locked?" })
        public synchronized RubyBoolean locked_p(final ThreadContext context) {
            return context.getRuntime().newBoolean(this.isLocked());
        }
        
        private boolean isLocked() {
            return this.owner != null && this.owner.isAlive();
        }
        
        @JRubyMethod
        public RubyBoolean try_lock(final ThreadContext context) {
            synchronized (this) {
                if (this.isLocked()) {
                    return context.getRuntime().getFalse();
                }
                this.lock(context);
            }
            return context.getRuntime().getTrue();
        }
        
        @JRubyMethod
        public IRubyObject lock(final ThreadContext context) {
            try {
                context.getThread().enterSleep();
                synchronized (this) {
                    try {
                        if (this.owner == context.getThread()) {
                            throw context.getRuntime().newThreadError("Mutex relocking by same thread");
                        }
                        while (this.isLocked()) {
                            this.wait();
                        }
                        this.owner = context.getThread();
                    }
                    catch (InterruptedException ex) {
                        if (!this.isLocked()) {
                            this.notify();
                        }
                        throw context.getRuntime().newConcurrencyError(ex.getLocalizedMessage());
                    }
                }
            }
            finally {
                context.getThread().exitSleep();
            }
            return this;
        }
        
        @JRubyMethod(name = { "unlock" }, compat = CompatVersion.RUBY1_8)
        public synchronized IRubyObject unlock(final ThreadContext context) {
            final Ruby runtime = context.getRuntime();
            if (!this.isLocked()) {
                throw runtime.newThreadError("Mutex is not locked");
            }
            if (this.owner != context.getThread()) {
                throw runtime.newThreadError("Mutex is not owned by calling thread");
            }
            this.owner = null;
            this.notify();
            return this;
        }
        
        @JRubyMethod(name = { "unlock" }, compat = CompatVersion.RUBY1_9)
        public synchronized IRubyObject unlock19(final ThreadContext context) {
            final Ruby runtime = context.getRuntime();
            if (!this.isLocked()) {
                throw runtime.newThreadError("Attempt to unlock a mutex which is not locked");
            }
            if (this.owner != context.getThread()) {
                throw runtime.newThreadError("Attempt to unlock a mutex which is locked by another thread");
            }
            this.owner = null;
            this.notify();
            return this;
        }
        
        @JRubyMethod(compat = CompatVersion.RUBY1_9)
        public IRubyObject sleep(final ThreadContext context) {
            final long beg = System.currentTimeMillis();
            try {
                this.unlock(context);
                context.getThread().sleep(-1L);
            }
            catch (InterruptedException ex) {}
            finally {
                this.lock(context);
            }
            return context.runtime.newFixnum((System.currentTimeMillis() - beg) / 1000L);
        }
        
        @JRubyMethod(compat = CompatVersion.RUBY1_9)
        public IRubyObject sleep(final ThreadContext context, final IRubyObject timeout) {
            final long beg = System.currentTimeMillis();
            try {
                this.unlock(context);
                context.getThread().sleep((long)(timeout.convertToFloat().getDoubleValue() * 1000.0));
            }
            catch (InterruptedException ex) {}
            finally {
                this.lock(context);
            }
            return context.runtime.newFixnum((System.currentTimeMillis() - beg) / 1000L);
        }
        
        @JRubyMethod
        public IRubyObject synchronize(final ThreadContext context, final Block block) {
            try {
                this.lock(context);
                return block.yield(context, null);
            }
            finally {
                this.unlock(context);
            }
        }
    }
    
    @JRubyClass(name = { "ConditionVariable" })
    public static class ConditionVariable extends RubyObject
    {
        @JRubyMethod(name = { "new" }, rest = true, meta = true)
        public static ConditionVariable newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
            final ConditionVariable result = new ConditionVariable(context.getRuntime(), (RubyClass)recv);
            result.callInit(args, block);
            return result;
        }
        
        public ConditionVariable(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
        }
        
        public static void setup(final Ruby runtime) {
            final RubyClass cConditionVariable = runtime.defineClass("ConditionVariable", runtime.getObject(), new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new ConditionVariable(runtime, klass);
                }
            });
            cConditionVariable.setReifiedClass(ConditionVariable.class);
            cConditionVariable.defineAnnotatedMethods(ConditionVariable.class);
        }
        
        @JRubyMethod(name = { "wait" }, required = 1, optional = 1)
        public IRubyObject wait_ruby(final ThreadContext context, final IRubyObject[] args) {
            final Ruby runtime = context.getRuntime();
            if (args.length < 1) {
                throw runtime.newArgumentError(args.length, 1);
            }
            if (args.length > 2) {
                throw runtime.newArgumentError(args.length, 2);
            }
            if (!(args[0] instanceof Mutex)) {
                throw context.getRuntime().newTypeError(args[0], runtime.fastGetClass("Mutex"));
            }
            final Mutex mutex = (Mutex)args[0];
            Double timeout = null;
            if (args.length > 1 && !args[1].isNil()) {
                timeout = args[1].convertToFloat().getDoubleValue();
                if (timeout < 0.0) {
                    throw runtime.newArgumentError("time interval must be positive");
                }
            }
            if (Thread.interrupted()) {
                throw runtime.newConcurrencyError("thread interrupted");
            }
            boolean success = false;
            try {
                synchronized (this) {
                    mutex.unlock(context);
                    try {
                        success = context.getThread().wait_timeout(this, timeout);
                    }
                    catch (InterruptedException ie) {
                        throw runtime.newConcurrencyError(ie.getLocalizedMessage());
                    }
                    finally {
                        if (!success) {
                            this.notify();
                        }
                    }
                }
            }
            finally {
                mutex.lock(context);
            }
            if (timeout != null) {
                return runtime.newBoolean(success);
            }
            return this;
        }
        
        @JRubyMethod
        public synchronized IRubyObject broadcast(final ThreadContext context) {
            this.notifyAll();
            return this;
        }
        
        @JRubyMethod
        public synchronized IRubyObject signal(final ThreadContext context) {
            this.notify();
            return this;
        }
    }
    
    @JRubyClass(name = { "Queue" })
    public static class Queue extends RubyObject
    {
        private LinkedList entries;
        protected volatile int numWaiting;
        
        @JRubyMethod(name = { "new" }, rest = true, meta = true)
        public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
            final Queue result = new Queue(context.getRuntime(), (RubyClass)recv);
            result.callInit(args, block);
            return result;
        }
        
        public Queue(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
            this.numWaiting = 0;
            this.entries = new LinkedList();
        }
        
        public static void setup(final Ruby runtime) {
            final RubyClass cQueue = runtime.defineClass("Queue", runtime.getObject(), new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new Queue(runtime, klass);
                }
            });
            cQueue.setReifiedClass(Queue.class);
            cQueue.defineAnnotatedMethods(Queue.class);
        }
        
        @JRubyMethod(name = { "shutdown!" })
        public synchronized IRubyObject shutdown(final ThreadContext context) {
            this.entries = null;
            this.notifyAll();
            return context.getRuntime().getNil();
        }
        
        public synchronized void checkShutdown(final ThreadContext context) {
            if (this.entries == null) {
                throw new RaiseException(context.getRuntime(), context.getRuntime().getThreadError(), "queue shut down", false);
            }
        }
        
        @JRubyMethod
        public synchronized IRubyObject clear(final ThreadContext context) {
            this.checkShutdown(context);
            this.entries.clear();
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "empty?" })
        public synchronized RubyBoolean empty_p(final ThreadContext context) {
            this.checkShutdown(context);
            return context.getRuntime().newBoolean(this.entries.size() == 0);
        }
        
        @JRubyMethod(name = { "length", "size" })
        public synchronized RubyNumeric length(final ThreadContext context) {
            this.checkShutdown(context);
            return RubyNumeric.int2fix(context.getRuntime(), this.entries.size());
        }
        
        protected synchronized long java_length() {
            return this.entries.size();
        }
        
        @JRubyMethod
        public RubyNumeric num_waiting(final ThreadContext context) {
            return context.getRuntime().newFixnum(this.numWaiting);
        }
        
        @JRubyMethod(name = { "pop", "deq", "shift" }, optional = 1)
        public synchronized IRubyObject pop(final ThreadContext context, final IRubyObject[] args) {
            this.checkShutdown(context);
            boolean should_block = true;
            if (Arity.checkArgumentCount(context.getRuntime(), args, 0, 1) == 1) {
                should_block = !args[0].isTrue();
            }
            if (!should_block && this.entries.size() == 0) {
                throw new RaiseException(context.getRuntime(), context.getRuntime().getThreadError(), "queue empty", false);
            }
            ++this.numWaiting;
            try {
                while (this.java_length() == 0L) {
                    try {
                        context.getThread().wait_timeout(this, null);
                    }
                    catch (InterruptedException ex) {}
                    this.checkShutdown(context);
                }
            }
            finally {
                --this.numWaiting;
            }
            return this.entries.removeFirst();
        }
        
        @JRubyMethod(name = { "push", "<<", "enq" })
        public synchronized IRubyObject push(final ThreadContext context, final IRubyObject value) {
            this.checkShutdown(context);
            this.entries.addLast(value);
            this.notify();
            return context.getRuntime().getNil();
        }
    }
    
    @JRubyClass(name = { "SizedQueue" }, parent = "Queue")
    public static class SizedQueue extends Queue
    {
        private int capacity;
        
        @JRubyMethod(name = { "new" }, rest = true, meta = true)
        public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
            final SizedQueue result = new SizedQueue(context.getRuntime(), (RubyClass)recv);
            result.callInit(args, block);
            return result;
        }
        
        public SizedQueue(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
            this.capacity = 1;
        }
        
        public static void setup(final Ruby runtime) {
            final RubyClass cSizedQueue = runtime.defineClass("SizedQueue", runtime.fastGetClass("Queue"), new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new SizedQueue(runtime, klass);
                }
            });
            cSizedQueue.setReifiedClass(SizedQueue.class);
            cSizedQueue.defineAnnotatedMethods(SizedQueue.class);
        }
        
        @JRubyMethod
        public synchronized IRubyObject clear(final ThreadContext context) {
            super.clear(context);
            this.notifyAll();
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod
        public synchronized RubyNumeric max(final ThreadContext context) {
            return RubyNumeric.int2fix(context.getRuntime(), this.capacity);
        }
        
        @JRubyMethod(name = { "max=", "initialize" })
        public synchronized IRubyObject max_set(final ThreadContext context, final IRubyObject arg) {
            final int new_capacity = RubyNumeric.fix2int(arg);
            if (new_capacity <= 0) {
                context.getRuntime().newArgumentError("queue size must be positive");
            }
            int difference;
            if (new_capacity > this.capacity) {
                difference = new_capacity - this.capacity;
            }
            else {
                difference = 0;
            }
            this.capacity = new_capacity;
            if (difference > 0) {
                this.notifyAll();
            }
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "pop", "deq", "shift" }, optional = 1)
        public synchronized IRubyObject pop(final ThreadContext context, final IRubyObject[] args) {
            final IRubyObject result = super.pop(context, args);
            this.notifyAll();
            return result;
        }
        
        @JRubyMethod(name = { "push", "<<" })
        public synchronized IRubyObject push(final ThreadContext context, final IRubyObject value) {
            this.checkShutdown(context);
            if (this.java_length() >= this.capacity) {
                ++this.numWaiting;
                try {
                    while (this.java_length() >= this.capacity) {
                        try {
                            context.getThread().wait_timeout(this, null);
                        }
                        catch (InterruptedException ex) {}
                        this.checkShutdown(context);
                    }
                }
                finally {
                    --this.numWaiting;
                }
            }
            super.push(context, value);
            this.notifyAll();
            return context.getRuntime().getNil();
        }
    }
}
