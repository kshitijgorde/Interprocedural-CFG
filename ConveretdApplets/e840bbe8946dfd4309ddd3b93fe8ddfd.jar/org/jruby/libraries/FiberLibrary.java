// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import org.jruby.CompatVersion;
import java.util.WeakHashMap;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyLocalJumpError;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyModule;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyThread;
import org.jruby.runtime.Block;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.ExecutionContext;
import org.jruby.RubyObject;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import org.jruby.threading.DaemonThreadFactory;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.Ruby;
import java.util.concurrent.Executor;
import org.jruby.runtime.load.Library;

public class FiberLibrary implements Library
{
    private Executor executor;
    
    public void load(final Ruby runtime, final boolean wrap) {
        final RubyClass cFiber = runtime.defineClass("Fiber", runtime.getObject(), new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new Fiber(runtime, klazz);
            }
        });
        cFiber.defineAnnotatedMethods(Fiber.class);
        cFiber.defineAnnotatedMethods(FiberMeta.class);
        if (runtime.getExecutor() != null) {
            this.executor = runtime.getExecutor();
        }
        else {
            this.executor = Executors.newCachedThreadPool(new DaemonThreadFactory());
        }
    }
    
    public enum FiberState
    {
        NOT_STARTED, 
        STARTED, 
        YIELDED, 
        RUNNING, 
        FINISHED;
    }
    
    @JRubyClass(name = { "Fiber" })
    public class Fiber extends RubyObject implements ExecutionContext
    {
        private final SynchronousQueue<IRubyObject> yield;
        private final SynchronousQueue<IRubyObject> resume;
        private final Map<Object, IRubyObject> contextVariables;
        private Block block;
        private IRubyObject result;
        private RubyThread parent;
        private Runnable runnable;
        private FiberState state;
        
        @JRubyMethod(rest = true, visibility = Visibility.PRIVATE)
        public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args, final Block block) {
            final Ruby runtime = context.getRuntime();
            if (block == null || !block.isGiven()) {
                throw runtime.newArgumentError("tried to create Proc object without a block");
            }
            this.block = block;
            this.parent = context.getThread();
            this.result = runtime.getNil();
            this.runnable = new Runnable() {
                public void run() {
                    final ThreadContext context = runtime.getCurrentContext();
                    context.setFiber(Fiber.this);
                    try {
                        Fiber.this.state = FiberState.STARTED;
                        Fiber.this.result = Fiber.this.resume.take();
                        Fiber.this.state = FiberState.RUNNING;
                        Fiber.this.result = Fiber.this.block.yieldArray(context, Fiber.this.result, null, null);
                        Fiber.this.state = FiberState.FINISHED;
                        Fiber.this.yield.put(Fiber.this.result);
                    }
                    catch (JumpException.RetryJump rtry) {
                        Fiber.this.parent.raise(new IRubyObject[] { runtime.newSyntaxError("Invalid retry").getException() }, Block.NULL_BLOCK);
                        Fiber.this.parent.getNativeThread().interrupt();
                    }
                    catch (JumpException.BreakJump brk) {
                        Fiber.this.parent.raise(new IRubyObject[] { runtime.newLocalJumpError(RubyLocalJumpError.Reason.BREAK, runtime.getNil(), "break from proc-closure").getException() }, Block.NULL_BLOCK);
                        Fiber.this.parent.getNativeThread().interrupt();
                    }
                    catch (JumpException.ReturnJump ret) {
                        Fiber.this.parent.raise(new IRubyObject[] { runtime.newLocalJumpError(RubyLocalJumpError.Reason.RETURN, runtime.getNil(), "unexpected return").getException() }, Block.NULL_BLOCK);
                        Fiber.this.parent.getNativeThread().interrupt();
                    }
                    catch (RaiseException re) {
                        Fiber.this.parent.raise(new IRubyObject[] { re.getException() }, Block.NULL_BLOCK);
                        Fiber.this.parent.getNativeThread().interrupt();
                    }
                    catch (InterruptedException ie) {
                        context.pollThreadEvents();
                        throw context.getRuntime().newConcurrencyError(ie.getLocalizedMessage());
                    }
                    finally {
                        Fiber.this.state = FiberState.FINISHED;
                    }
                }
            };
            return this;
        }
        
        public Fiber(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
            this.yield = new SynchronousQueue<IRubyObject>();
            this.resume = new SynchronousQueue<IRubyObject>();
            this.contextVariables = new WeakHashMap<Object, IRubyObject>();
            this.state = FiberState.NOT_STARTED;
        }
        
        @JRubyMethod(rest = true, compat = CompatVersion.RUBY1_9)
        public IRubyObject resume(final ThreadContext context, final IRubyObject[] args) {
            IRubyObject result;
            if (args.length == 0) {
                result = context.getRuntime().getNil();
            }
            else if (args.length == 1) {
                result = args[0];
            }
            else {
                result = context.getRuntime().newArrayNoCopyLight(args);
            }
            try {
                switch (this.state) {
                    case NOT_STARTED: {
                        FiberLibrary.this.executor.execute(this.runnable);
                    }
                    case YIELDED: {
                        this.resume.put(result);
                        result = this.yield.take();
                        context.pollThreadEvents();
                        return result;
                    }
                    case RUNNING: {
                        throw context.getRuntime().newFiberError("double resume");
                    }
                    case FINISHED: {
                        throw context.getRuntime().newFiberError("dead fiber called");
                    }
                    default: {
                        throw context.getRuntime().newFiberError("fiber in an unknown state");
                    }
                }
            }
            catch (OutOfMemoryError oome) {
                if (oome.getMessage().equals("unable to create new native thread")) {
                    throw context.runtime.newThreadError("too many threads, can't create a new Fiber");
                }
                throw oome;
            }
            catch (InterruptedException ie) {
                context.pollThreadEvents();
                throw context.getRuntime().newConcurrencyError(ie.getLocalizedMessage());
            }
        }
        
        @JRubyMethod(name = { "transfer" }, rest = true, compat = CompatVersion.RUBY1_9)
        public IRubyObject transfer(final ThreadContext context, final IRubyObject[] args) {
            return this.resume(context, args);
        }
        
        @JRubyMethod(name = { "alive?" }, compat = CompatVersion.RUBY1_9)
        public IRubyObject alive_p(final ThreadContext context) {
            return context.getRuntime().newBoolean(this.state != FiberState.FINISHED);
        }
        
        public Map<Object, IRubyObject> getContextVariables() {
            return this.contextVariables;
        }
    }
    
    public static class FiberMeta
    {
        @JRubyMethod(compat = CompatVersion.RUBY1_9, rest = true, meta = true)
        public static IRubyObject yield(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
            final Fiber fiber = context.getFiber();
            if (fiber == null) {
                throw context.getRuntime().newFiberError("can't yield from root fiber");
            }
            if (args.length == 1) {
                fiber.result = args[0];
            }
            else if (args.length > 0) {
                fiber.result = context.getRuntime().newArrayNoCopyLight(args);
            }
            try {
                fiber.state = FiberState.YIELDED;
                fiber.yield.put(fiber.result);
                fiber.result = fiber.resume.take();
                context.pollThreadEvents();
                fiber.state = FiberState.RUNNING;
            }
            catch (InterruptedException ie) {
                context.pollThreadEvents();
                throw context.getRuntime().newConcurrencyError(ie.getLocalizedMessage());
            }
            return fiber.result;
        }
    }
}
