// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.runtime.Visibility;
import org.jruby.runtime.Arity;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import org.jruby.threading.DaemonThreadFactory;
import org.jruby.RubyKernel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.jruby.anno.JRubyMethod;
import java.util.concurrent.Future;
import org.jruby.RubyThread;
import org.jruby.exceptions.RaiseException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import java.io.IOException;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.RubyFixnum;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyRegexp;
import org.jruby.util.RegexpOptions;
import org.jruby.Ruby;
import java.util.concurrent.ScheduledExecutorService;
import org.jruby.runtime.load.Library;

public class Timeout implements Library
{
    private static ScheduledExecutorService timeoutExecutor;
    
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        final RubyModule timeout = runtime.defineModule("Timeout");
        final RubyClass superclass = runtime.is1_9() ? runtime.getRuntimeError() : runtime.getInterrupt();
        final RubyClass timeoutError = runtime.defineClassUnder("Error", superclass, superclass.getAllocator(), timeout);
        runtime.defineClassUnder("ExitException", runtime.getException(), runtime.getException().getAllocator(), timeout);
        final RubyClass anonEx = runtime.defineClassUnder("AnonymousException", runtime.getException(), runtime.getException().getAllocator(), timeout);
        anonEx.setBaseName(null);
        timeout.defineConstant("THIS_FILE", RubyRegexp.newRegexp(runtime, "timeout\\.rb", new RegexpOptions()));
        timeout.defineConstant("CALLER_OFFSET", RubyFixnum.newFixnum(runtime, 0L));
        timeout.defineAnnotatedMethods(Timeout.class);
        runtime.getObject().defineConstant("TimeoutError", timeoutError);
        runtime.getObject().defineAnnotatedMethods(TimeoutToplevel.class);
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject timeout(final ThreadContext context, final IRubyObject timeout, final IRubyObject seconds, final Block block) {
        if (seconds.isNil() || RuntimeHelpers.invoke(context, seconds, "zero?").isTrue()) {
            return block.yieldSpecific(context);
        }
        final Ruby runtime = context.getRuntime();
        if (runtime.getThreadService().getCritical()) {
            return raiseBecauseCritical(context);
        }
        final RubyThread currentThread = context.getThread();
        final AtomicBoolean latch = new AtomicBoolean(false);
        final Runnable timeoutRunnable = prepareRunnable(currentThread, runtime, latch);
        Future timeoutFuture = null;
        try {
            try {
                timeoutFuture = Timeout.timeoutExecutor.schedule(timeoutRunnable, (long)(seconds.convertToFloat().getDoubleValue() * 1000000.0), TimeUnit.MICROSECONDS);
                return block.yield(context, seconds);
            }
            finally {
                killTimeoutThread(context, timeoutFuture, latch);
            }
        }
        catch (RaiseException re) {
            if (re.getException().getMetaClass() == runtime.getClassFromPath("Timeout::AnonymousException")) {
                return raiseTimeoutError(context, re);
            }
            throw re;
        }
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject timeout(final ThreadContext context, final IRubyObject timeout, final IRubyObject seconds, final IRubyObject exceptionType, final Block block) {
        if (seconds.isNil() || RuntimeHelpers.invoke(context, seconds, "zero?").isTrue()) {
            return block.yieldSpecific(context);
        }
        final Ruby runtime = context.getRuntime();
        if (runtime.getThreadService().getCritical()) {
            return raiseBecauseCritical(context);
        }
        final IRubyObject exception = exceptionType.isNil() ? runtime.getClassFromPath("Timeout::AnonymousException") : exceptionType;
        final RubyThread currentThread = context.getThread();
        final AtomicBoolean latch = new AtomicBoolean(false);
        final Runnable timeoutRunnable = prepareRunnableWithException(currentThread, exception, runtime, latch);
        Future timeoutFuture = null;
        try {
            try {
                timeoutFuture = Timeout.timeoutExecutor.schedule(timeoutRunnable, (long)(seconds.convertToFloat().getDoubleValue() * 1000000.0), TimeUnit.MICROSECONDS);
                return block.yield(context, seconds);
            }
            finally {
                killTimeoutThread(context, timeoutFuture, latch);
            }
        }
        catch (RaiseException re) {
            if (re.getException().getMetaClass() == exception && exceptionType.isNil()) {
                return raiseTimeoutError(context, re);
            }
            throw re;
        }
    }
    
    private static Runnable prepareRunnable(final RubyThread currentThread, final Ruby runtime, final AtomicBoolean latch) {
        final Runnable timeoutRunnable = new Runnable() {
            public void run() {
                if (latch.compareAndSet(false, true)) {
                    raiseInThread(runtime, currentThread, runtime.getClassFromPath("Timeout::AnonymousException"));
                }
            }
        };
        return timeoutRunnable;
    }
    
    private static Runnable prepareRunnableWithException(final RubyThread currentThread, final IRubyObject exception, final Ruby runtime, final AtomicBoolean latch) {
        final Runnable timeoutRunnable = new Runnable() {
            public void run() {
                if (latch.compareAndSet(false, true)) {
                    raiseInThread(runtime, currentThread, exception);
                }
            }
        };
        return timeoutRunnable;
    }
    
    private static void killTimeoutThread(final ThreadContext context, final Future timeoutFuture, final AtomicBoolean latch) {
        if (latch.compareAndSet(false, true)) {
            timeoutFuture.cancel(false);
            if (Timeout.timeoutExecutor instanceof ScheduledThreadPoolExecutor && timeoutFuture instanceof Runnable) {
                ((ScheduledThreadPoolExecutor)Timeout.timeoutExecutor).remove((Runnable)timeoutFuture);
            }
        }
        else {
            try {
                timeoutFuture.get();
            }
            catch (ExecutionException ex) {}
            catch (InterruptedException ex2) {}
            context.pollThreadEvents();
        }
    }
    
    private static void raiseInThread(final Ruby runtime, final RubyThread currentThread, final IRubyObject exception) {
        if (currentThread.alive_p().isTrue()) {
            currentThread.internalRaise(new IRubyObject[] { exception, runtime.newString("execution expired") });
        }
    }
    
    private static IRubyObject raiseBecauseCritical(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        return RubyKernel.raise(context, runtime.getKernel(), new IRubyObject[] { runtime.getThreadError(), runtime.newString("timeout within critical section") }, Block.NULL_BLOCK);
    }
    
    private static IRubyObject raiseTimeoutError(final ThreadContext context, final RaiseException re) {
        final Ruby runtime = context.getRuntime();
        return RubyKernel.raise(context, runtime.getKernel(), new IRubyObject[] { runtime.getClassFromPath("Timeout::Error"), re.getException().callMethod(context, "message"), re.getException().callMethod(context, "backtrace") }, Block.NULL_BLOCK);
    }
    
    static {
        Timeout.timeoutExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors(), new DaemonThreadFactory());
    }
    
    public static class TimeoutToplevel
    {
        @JRubyMethod(required = 1, optional = 1, visibility = Visibility.PRIVATE)
        public static IRubyObject timeout(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
            final RubyModule timeout = context.getRuntime().getModule("Timeout");
            switch (args.length) {
                case 1: {
                    return Timeout.timeout(context, timeout, args[0], block);
                }
                case 2: {
                    return Timeout.timeout(context, timeout, args[0], args[1], block);
                }
                default: {
                    Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 2);
                    return context.getRuntime().getNil();
                }
            }
        }
    }
}
