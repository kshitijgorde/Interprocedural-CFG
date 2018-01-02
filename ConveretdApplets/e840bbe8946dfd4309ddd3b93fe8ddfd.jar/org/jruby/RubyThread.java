// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.nio.channels.Channel;
import java.io.IOException;
import org.jruby.util.io.SelectorFactory;
import java.nio.channels.SelectableChannel;
import java.util.concurrent.ExecutionException;
import java.util.HashMap;
import org.jruby.common.IRubyWarnings;
import org.jruby.exceptions.ThreadKill;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.FutureThread;
import org.jruby.internal.runtime.RubyRunnable;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ObjectMarshal;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.internal.runtime.NativeThread;
import java.util.WeakHashMap;
import org.jruby.util.io.BlockingIO;
import java.nio.channels.Selector;
import org.jruby.runtime.ThreadContext;
import java.lang.ref.WeakReference;
import org.jruby.internal.runtime.ThreadService;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.Map;
import org.jruby.internal.runtime.ThreadLike;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.ExecutionContext;

@JRubyClass(name = { "Thread" })
public class RubyThread extends RubyObject implements ExecutionContext
{
    private ThreadLike threadImpl;
    private RubyFixnum priority;
    private transient Map<IRubyObject, IRubyObject> threadLocalVariables;
    private final Map<Object, IRubyObject> contextVariables;
    private boolean abortOnException;
    private IRubyObject finalResult;
    private RaiseException exitingException;
    private RubyThreadGroup threadGroup;
    private final ThreadService threadService;
    private IRubyObject errorInfo;
    private volatile WeakReference<ThreadContext> contextRef;
    private static final boolean DEBUG = false;
    private volatile ThreadService.Event mail;
    private volatile Status status;
    private volatile BlockingTask currentBlockingTask;
    private volatile Selector currentSelector;
    private volatile BlockingIO.Condition blockingIO;
    
    protected RubyThread(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
        this.contextVariables = new WeakHashMap<Object, IRubyObject>();
        this.status = Status.RUN;
        this.blockingIO = null;
        this.threadService = runtime.getThreadService();
        this.finalResult = runtime.getNil();
        this.priority = RubyFixnum.newFixnum(runtime, 5L);
        this.errorInfo = runtime.getNil();
    }
    
    public void receiveMail(final ThreadService.Event event) {
        synchronized (this) {
            if (this.status == Status.ABORTING) {
                return;
            }
            this.mail = event;
            switch (event.type) {
                case KILL: {
                    this.status = Status.ABORTING;
                    break;
                }
            }
            this.notify();
        }
        this.interrupt();
    }
    
    public synchronized void checkMail(final ThreadContext context) {
        final ThreadService.Event myEvent = this.mail;
        this.mail = null;
        if (myEvent != null) {
            switch (myEvent.type) {
                case RAISE: {
                    this.receivedAnException(context, myEvent.exception);
                }
                case KILL: {
                    throwThreadKill();
                    break;
                }
            }
        }
    }
    
    public IRubyObject getErrorInfo() {
        return this.errorInfo;
    }
    
    public IRubyObject setErrorInfo(final IRubyObject errorInfo) {
        return this.errorInfo = errorInfo;
    }
    
    public void setContext(final ThreadContext context) {
        this.contextRef = new WeakReference<ThreadContext>(context);
    }
    
    public ThreadContext getContext() {
        return this.contextRef.get();
    }
    
    public Thread getNativeThread() {
        return (this.threadImpl instanceof NativeThread) ? ((NativeThread)this.threadImpl).getThread() : null;
    }
    
    public void dispose() {
        this.threadGroup.remove(this);
    }
    
    public static RubyClass createThreadClass(final Ruby runtime) {
        final RubyClass threadClass = runtime.defineClass("Thread", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setThread(threadClass);
        threadClass.index = 29;
        threadClass.setReifiedClass(RubyThread.class);
        threadClass.defineAnnotatedMethods(RubyThread.class);
        final RubyThread rubyThread = new RubyThread(runtime, threadClass);
        rubyThread.threadImpl = new NativeThread(rubyThread, Thread.currentThread());
        runtime.getThreadService().setMainThread(Thread.currentThread(), rubyThread);
        runtime.getDefaultThreadGroup().addDirectly(rubyThread);
        threadClass.setMarshal(ObjectMarshal.NOT_MARSHALABLE_MARSHAL);
        return threadClass;
    }
    
    @JRubyMethod(name = { "new", "fork" }, rest = true, meta = true)
    public static IRubyObject newInstance(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return startThread(recv, args, true, block);
    }
    
    @JRubyMethod(rest = true, meta = true)
    public static RubyThread start(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return startThread(recv, args, false, block);
    }
    
    public static RubyThread adopt(final IRubyObject recv, final Thread t) {
        return adoptThread(recv, t, Block.NULL_BLOCK);
    }
    
    private static RubyThread adoptThread(final IRubyObject recv, final Thread t, final Block block) {
        final Ruby runtime = recv.getRuntime();
        final RubyThread rubyThread = new RubyThread(runtime, (RubyClass)recv);
        rubyThread.threadImpl = new NativeThread(rubyThread, t);
        final ThreadContext context = runtime.getThreadService().registerNewThread(rubyThread);
        runtime.getThreadService().associateThread(t, rubyThread);
        context.preAdoptThread();
        runtime.getDefaultThreadGroup().addDirectly(rubyThread);
        return rubyThread;
    }
    
    @JRubyMethod(rest = true, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args, final Block block) {
        final Ruby runtime = this.getRuntime();
        if (!block.isGiven()) {
            throw runtime.newThreadError("must be called with a block");
        }
        try {
            final RubyRunnable runnable = new RubyRunnable(this, args, context.getFrames(0), block);
            if (RubyInstanceConfig.POOLING_ENABLED) {
                final FutureThread futureThread = new FutureThread(this, runnable);
                this.threadImpl = futureThread;
                this.addToCorrectThreadGroup(context);
                this.threadImpl.start();
                runtime.getThreadService().associateThread(futureThread.getFuture(), this);
            }
            else {
                final Thread thread = new Thread(runnable);
                thread.setDaemon(true);
                thread.setName("Ruby" + thread.getName() + ": " + context.getFile() + ":" + (context.getLine() + 1));
                this.threadImpl = new NativeThread(this, thread);
                this.addToCorrectThreadGroup(context);
                this.threadImpl.start();
                runtime.getThreadService().associateThread(thread, this);
            }
            Thread.yield();
            return this;
        }
        catch (OutOfMemoryError oome) {
            if (oome.getMessage().equals("unable to create new native thread")) {
                throw runtime.newThreadError(oome.getMessage());
            }
            throw oome;
        }
        catch (SecurityException ex) {
            throw runtime.newThreadError(ex.getMessage());
        }
    }
    
    private static RubyThread startThread(final IRubyObject recv, final IRubyObject[] args, final boolean callInit, final Block block) {
        final RubyThread rubyThread = new RubyThread(recv.getRuntime(), (RubyClass)recv);
        if (callInit) {
            rubyThread.callInit(args, block);
        }
        else {
            rubyThread.initialize(recv.getRuntime().getCurrentContext(), args, block);
        }
        return rubyThread;
    }
    
    public synchronized void cleanTerminate(final IRubyObject result) {
        this.finalResult = result;
    }
    
    public synchronized void beDead() {
        this.status = Status.DEAD;
    }
    
    public void pollThreadEvents() {
        this.pollThreadEvents(this.getRuntime().getCurrentContext());
    }
    
    public void pollThreadEvents(final ThreadContext context) {
        if (this.mail != null) {
            this.checkMail(context);
        }
    }
    
    private static void throwThreadKill() {
        throw new ThreadKill();
    }
    
    @JRubyMethod(name = { "abort_on_exception" }, meta = true)
    public static RubyBoolean abort_on_exception_x(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        return runtime.isGlobalAbortOnExceptionEnabled() ? runtime.getTrue() : runtime.getFalse();
    }
    
    @JRubyMethod(name = { "abort_on_exception=" }, required = 1, meta = true)
    public static IRubyObject abort_on_exception_set_x(final IRubyObject recv, final IRubyObject value) {
        recv.getRuntime().setGlobalAbortOnExceptionEnabled(value.isTrue());
        return value;
    }
    
    @JRubyMethod(name = { "current" }, meta = true)
    public static RubyThread current(final IRubyObject recv) {
        return recv.getRuntime().getCurrentContext().getThread();
    }
    
    @JRubyMethod(name = { "main" }, meta = true)
    public static RubyThread main(final IRubyObject recv) {
        return recv.getRuntime().getThreadService().getMainThread();
    }
    
    @JRubyMethod(name = { "pass" }, meta = true)
    public static IRubyObject pass(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        final ThreadService ts = runtime.getThreadService();
        final boolean critical = ts.getCritical();
        ts.setCritical(false);
        Thread.yield();
        ts.setCritical(critical);
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "list" }, meta = true)
    public static RubyArray list(final IRubyObject recv) {
        final RubyThread[] activeThreads = recv.getRuntime().getThreadService().getActiveRubyThreads();
        return recv.getRuntime().newArrayNoCopy((IRubyObject[])activeThreads);
    }
    
    private void addToCorrectThreadGroup(final ThreadContext context) {
        final IRubyObject group = context.getThread().group();
        if (!group.isNil()) {
            ((RubyThreadGroup)group).addDirectly(this);
        }
        else {
            context.getRuntime().getDefaultThreadGroup().addDirectly(this);
        }
    }
    
    private IRubyObject getSymbolKey(final IRubyObject originalKey) {
        if (originalKey instanceof RubySymbol) {
            return originalKey;
        }
        if (originalKey instanceof RubyString) {
            return this.getRuntime().newSymbol(originalKey.asJavaString());
        }
        if (originalKey instanceof RubyFixnum) {
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.FIXNUMS_NOT_SYMBOLS, "Do not use Fixnums as Symbols", new Object[0]);
            throw this.getRuntime().newArgumentError(originalKey + " is not a symbol");
        }
        throw this.getRuntime().newTypeError(originalKey + " is not a symbol");
    }
    
    private synchronized Map<IRubyObject, IRubyObject> getThreadLocals() {
        if (this.threadLocalVariables == null) {
            this.threadLocalVariables = new HashMap<IRubyObject, IRubyObject>();
        }
        return this.threadLocalVariables;
    }
    
    public final Map<Object, IRubyObject> getContextVariables() {
        return this.contextVariables;
    }
    
    public boolean isAlive() {
        return this.threadImpl.isAlive() && this.status != Status.ABORTING;
    }
    
    @JRubyMethod(name = { "[]" }, required = 1)
    public IRubyObject op_aref(final IRubyObject key) {
        final IRubyObject value;
        if ((value = this.getThreadLocals().get(this.getSymbolKey(key))) != null) {
            return value;
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "[]=" }, required = 2)
    public IRubyObject op_aset(IRubyObject key, final IRubyObject value) {
        key = this.getSymbolKey(key);
        this.getThreadLocals().put(key, value);
        return value;
    }
    
    @JRubyMethod(name = { "abort_on_exception" })
    public RubyBoolean abort_on_exception() {
        return this.abortOnException ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "abort_on_exception=" }, required = 1)
    public IRubyObject abort_on_exception_set(final IRubyObject val) {
        this.abortOnException = val.isTrue();
        return val;
    }
    
    @JRubyMethod(name = { "alive?" })
    public RubyBoolean alive_p() {
        return this.isAlive() ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "join" }, optional = 1, backtrace = true)
    public IRubyObject join(final IRubyObject[] args) {
        long timeoutMillis = Long.MAX_VALUE;
        if (args.length > 0) {
            if (args.length > 1) {
                throw this.getRuntime().newArgumentError(args.length, 1);
            }
            timeoutMillis = (long)(1000.0 * args[0].convertToFloat().getValue());
            if (timeoutMillis <= 0L) {
                if (this.threadImpl.isAlive()) {
                    return this.getRuntime().getNil();
                }
                return this;
            }
        }
        if (this.isCurrent()) {
            throw this.getRuntime().newThreadError("thread " + this.identityString() + " tried to join itself");
        }
        try {
            if (this.threadService.getCritical()) {
                synchronized (this) {
                    this.notify();
                }
            }
            final RubyThread currentThread = this.getRuntime().getCurrentContext().getThread();
            final long timeToWait = Math.min(timeoutMillis, 200L);
            final long start = System.currentTimeMillis();
            do {
                currentThread.pollThreadEvents();
                this.threadImpl.join(timeToWait);
                if (!this.threadImpl.isAlive()) {
                    break;
                }
            } while (System.currentTimeMillis() - start <= timeoutMillis);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
            assert false : ie;
        }
        catch (ExecutionException ie2) {
            ie2.printStackTrace();
            assert false : ie2;
        }
        if (this.exitingException != null) {
            throw this.exitingException;
        }
        if (this.threadImpl.isAlive()) {
            return this.getRuntime().getNil();
        }
        return this;
    }
    
    @JRubyMethod
    public IRubyObject value() {
        this.join(new IRubyObject[0]);
        synchronized (this) {
            return this.finalResult;
        }
    }
    
    @JRubyMethod
    public IRubyObject group() {
        if (this.threadGroup == null) {
            return this.getRuntime().getNil();
        }
        return this.threadGroup;
    }
    
    void setThreadGroup(final RubyThreadGroup rubyThreadGroup) {
        this.threadGroup = rubyThreadGroup;
    }
    
    @JRubyMethod(name = { "inspect" })
    public synchronized IRubyObject inspect() {
        final StringBuilder part = new StringBuilder();
        final String cname = this.getMetaClass().getRealClass().getName();
        part.append("#<").append(cname).append(":");
        part.append(this.identityString());
        part.append(' ');
        part.append(this.status.toString().toLowerCase());
        part.append('>');
        return this.getRuntime().newString(part.toString());
    }
    
    @JRubyMethod(name = { "key?" }, required = 1)
    public RubyBoolean key_p(IRubyObject key) {
        key = this.getSymbolKey(key);
        return this.getRuntime().newBoolean(this.getThreadLocals().containsKey(key));
    }
    
    @JRubyMethod(name = { "keys" })
    public RubyArray keys() {
        final IRubyObject[] keys = new IRubyObject[this.getThreadLocals().size()];
        return RubyArray.newArrayNoCopy(this.getRuntime(), this.getThreadLocals().keySet().toArray(keys));
    }
    
    @JRubyMethod(name = { "critical=" }, required = 1, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject critical_set(final IRubyObject receiver, final IRubyObject value) {
        receiver.getRuntime().getThreadService().setCritical(value.isTrue());
        return value;
    }
    
    @JRubyMethod(name = { "critical" }, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject critical(final IRubyObject receiver) {
        return receiver.getRuntime().newBoolean(receiver.getRuntime().getThreadService().getCritical());
    }
    
    @JRubyMethod(name = { "stop" }, meta = true)
    public static IRubyObject stop(final ThreadContext context, final IRubyObject receiver) {
        final RubyThread rubyThread = context.getThread();
        synchronized (rubyThread) {
            rubyThread.checkMail(context);
            try {
                receiver.getRuntime().getThreadService().setCritical(false);
                rubyThread.status = Status.SLEEP;
                rubyThread.wait();
            }
            catch (InterruptedException ie) {
                rubyThread.checkMail(context);
                rubyThread.status = Status.RUN;
            }
        }
        return receiver.getRuntime().getNil();
    }
    
    @JRubyMethod(required = 1, meta = true)
    public static IRubyObject kill(final IRubyObject receiver, final IRubyObject rubyThread, final Block block) {
        if (!(rubyThread instanceof RubyThread)) {
            throw receiver.getRuntime().newTypeError(rubyThread, receiver.getRuntime().getThread());
        }
        return ((RubyThread)rubyThread).kill();
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject s_exit(final IRubyObject receiver, final Block block) {
        final RubyThread rubyThread = receiver.getRuntime().getThreadService().getCurrentContext().getThread();
        synchronized (rubyThread) {
            rubyThread.status = Status.ABORTING;
            rubyThread.mail = null;
            receiver.getRuntime().getThreadService().setCritical(false);
            throw new ThreadKill();
        }
    }
    
    @JRubyMethod(name = { "stop?" })
    public RubyBoolean stop_p() {
        return this.getRuntime().newBoolean(this.status == Status.SLEEP || this.status == Status.DEAD);
    }
    
    @JRubyMethod(name = { "wakeup" })
    public synchronized RubyThread wakeup() {
        if (!this.threadImpl.isAlive() && this.status == Status.DEAD) {
            throw this.getRuntime().newThreadError("killed thread");
        }
        this.status = Status.RUN;
        this.notifyAll();
        return this;
    }
    
    @JRubyMethod(name = { "priority" })
    public RubyFixnum priority() {
        return this.priority;
    }
    
    @JRubyMethod(name = { "priority=" }, required = 1)
    public IRubyObject priority_set(final IRubyObject priority) {
        int iPriority = RubyNumeric.fix2int(priority);
        if (iPriority < 1) {
            iPriority = 1;
        }
        else if (iPriority > 10) {
            iPriority = 10;
        }
        this.priority = RubyFixnum.newFixnum(this.getRuntime(), iPriority);
        if (this.threadImpl.isAlive()) {
            this.threadImpl.setPriority(iPriority);
        }
        return this.priority;
    }
    
    @JRubyMethod(optional = 3)
    public IRubyObject raise(final IRubyObject[] args, final Block block) {
        final Ruby runtime = this.getRuntime();
        final ThreadContext context = runtime.getCurrentContext();
        if (this == context.getThread()) {
            return RubyKernel.raise(context, runtime.getKernel(), args, block);
        }
        final RubyThread currentThread = this.getRuntime().getCurrentContext().getThread();
        final IRubyObject exception = this.prepareRaiseException(runtime, args, block);
        runtime.getThreadService().deliverEvent(new ThreadService.Event(currentThread, this, ThreadService.Event.Type.RAISE, exception));
        return this;
    }
    
    public void internalRaise(final IRubyObject[] args) {
        final Ruby runtime = this.getRuntime();
        final IRubyObject exception = this.prepareRaiseException(runtime, args, Block.NULL_BLOCK);
        this.receiveMail(new ThreadService.Event(this, this, ThreadService.Event.Type.RAISE, exception));
    }
    
    private IRubyObject prepareRaiseException(final Ruby runtime, final IRubyObject[] args, final Block block) {
        if (args.length == 0) {
            final IRubyObject lastException = this.errorInfo;
            if (lastException.isNil()) {
                return new RaiseException(runtime, runtime.getRuntimeError(), "", false).getException();
            }
            return lastException;
        }
        else {
            final ThreadContext context = this.getRuntime().getCurrentContext();
            IRubyObject exception;
            if (args.length == 1) {
                if (args[0] instanceof RubyString) {
                    return runtime.getRuntimeError().newInstance(context, args, block);
                }
                if (!args[0].respondsTo("exception")) {
                    return runtime.newTypeError("exception class/object expected").getException();
                }
                exception = args[0].callMethod(context, "exception");
            }
            else {
                if (!args[0].respondsTo("exception")) {
                    return runtime.newTypeError("exception class/object expected").getException();
                }
                exception = args[0].callMethod(context, "exception", args[1]);
            }
            if (!runtime.getException().isInstance(exception)) {
                return runtime.newTypeError("exception object expected").getException();
            }
            if (args.length == 3) {
                ((RubyException)exception).set_backtrace(args[2]);
            }
            return exception;
        }
    }
    
    @JRubyMethod(name = { "run" })
    public synchronized IRubyObject run() {
        return this.wakeup();
    }
    
    public synchronized boolean sleep(final long millis) throws InterruptedException {
        assert this == this.getRuntime().getCurrentContext().getThread();
        boolean result = true;
        synchronized (this) {
            this.pollThreadEvents();
            try {
                this.status = Status.SLEEP;
                if (millis == -1L) {
                    this.wait();
                }
                else {
                    this.wait(millis);
                }
            }
            finally {
                result = (this.status != Status.RUN);
                this.pollThreadEvents();
                this.status = Status.RUN;
            }
        }
        return result;
    }
    
    @JRubyMethod(name = { "status" })
    public synchronized IRubyObject status() {
        if (this.threadImpl.isAlive()) {
            return this.getRuntime().newString(this.status.toString().toLowerCase());
        }
        if (this.exitingException != null) {
            return this.getRuntime().getNil();
        }
        return this.getRuntime().getFalse();
    }
    
    public void executeBlockingTask(final BlockingTask task) throws InterruptedException {
        this.enterSleep();
        try {
            this.currentBlockingTask = task;
            this.pollThreadEvents();
            task.run();
        }
        finally {
            this.exitSleep();
            this.currentBlockingTask = null;
            this.pollThreadEvents();
        }
    }
    
    public void enterSleep() {
        this.status = Status.SLEEP;
    }
    
    public void exitSleep() {
        this.status = Status.RUN;
    }
    
    @JRubyMethod(name = { "kill", "exit", "terminate" })
    public IRubyObject kill() {
        final RubyThread currentThread = this.getRuntime().getCurrentContext().getThread();
        if (currentThread == this) {
            throwThreadKill();
        }
        currentThread.pollThreadEvents();
        this.getRuntime().getThreadService().deliverEvent(new ThreadService.Event(currentThread, this, ThreadService.Event.Type.KILL));
        return this;
    }
    
    @JRubyMethod(name = { "kill!", "exit!", "terminate!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject kill_bang() {
        throw this.getRuntime().newNotImplementedError("Thread#kill!, exit!, and terminate! are not safe and not supported");
    }
    
    @JRubyMethod(name = { "safe_level" })
    public IRubyObject safe_level() {
        throw this.getRuntime().newNotImplementedError("Thread-specific SAFE levels are not supported");
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject backtrace(final ThreadContext context) {
        return this.getContext().createCallerBacktrace(context.getRuntime(), 0);
    }
    
    public StackTraceElement[] javaBacktrace() {
        if (this.threadImpl instanceof NativeThread) {
            return ((NativeThread)this.threadImpl).getThread().getStackTrace();
        }
        return new StackTraceElement[0];
    }
    
    private boolean isCurrent() {
        return this.threadImpl.isCurrent();
    }
    
    public void exceptionRaised(final RaiseException exception) {
        assert this.isCurrent();
        final RubyException rubyException = exception.getException();
        final Ruby runtime = rubyException.getRuntime();
        if (runtime.getSystemExit().isInstance(rubyException)) {
            this.threadService.getMainThread().raise(new IRubyObject[] { rubyException }, Block.NULL_BLOCK);
        }
        else {
            if (this.abortOnException(runtime)) {
                runtime.printError(rubyException);
                final RubyException systemExit = RubySystemExit.newInstance(runtime, 1);
                systemExit.message = rubyException.message;
                systemExit.set_backtrace(rubyException.backtrace());
                this.threadService.getMainThread().raise(new IRubyObject[] { systemExit }, Block.NULL_BLOCK);
                return;
            }
            if (runtime.getDebug().isTrue()) {
                runtime.printError(exception.getException());
            }
        }
        this.exitingException = exception;
    }
    
    private boolean abortOnException(final Ruby runtime) {
        return runtime.isGlobalAbortOnExceptionEnabled() || this.abortOnException;
    }
    
    public static RubyThread mainThread(final IRubyObject receiver) {
        return receiver.getRuntime().getThreadService().getMainThread();
    }
    
    @Deprecated
    public boolean selectForAccept(final RubyIO io) {
        return this.select(io, 16);
    }
    
    private synchronized Selector getSelector(final SelectableChannel channel) throws IOException {
        return SelectorFactory.openWithRetryFrom(this.getRuntime(), channel.provider());
    }
    
    public boolean select(final RubyIO io, final int ops) {
        return this.select(io.getChannel(), io, ops);
    }
    
    public boolean select(final RubyIO io, final int ops, final long timeout) {
        return this.select(io.getChannel(), io, ops, timeout);
    }
    
    public boolean select(final Channel channel, final RubyIO io, final int ops) {
        return this.select(channel, io, ops, -1L);
    }
    
    public boolean select(final Channel channel, final RubyIO io, final int ops, final long timeout) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* channel */
        //     1: instanceof      Ljava/nio/channels/SelectableChannel;
        //     4: ifeq            346
        //     7: aload_1         /* channel */
        //     8: checkcast       Ljava/nio/channels/SelectableChannel;
        //    11: astore          selectable
        //    13: aload           selectable
        //    15: invokevirtual   java/nio/channels/SelectableChannel.blockingLock:()Ljava/lang/Object;
        //    18: dup            
        //    19: astore          7
        //    21: monitorenter   
        //    22: aload           selectable
        //    24: invokevirtual   java/nio/channels/SelectableChannel.isBlocking:()Z
        //    27: istore          oldBlocking
        //    29: aconst_null    
        //    30: astore          key
        //    32: aload           selectable
        //    34: iconst_0       
        //    35: invokevirtual   java/nio/channels/SelectableChannel.configureBlocking:(Z)Ljava/nio/channels/SelectableChannel;
        //    38: pop            
        //    39: aload_2         /* io */
        //    40: ifnull          48
        //    43: aload_2         /* io */
        //    44: aload_0         /* this */
        //    45: invokevirtual   org/jruby/RubyIO.addBlockingThread:(Lorg/jruby/RubyThread;)V
        //    48: aload_0         /* this */
        //    49: aload_0         /* this */
        //    50: invokevirtual   org/jruby/RubyThread.getRuntime:()Lorg/jruby/Ruby;
        //    53: invokevirtual   org/jruby/Ruby.getSelectorPool:()Lorg/jruby/util/io/SelectorPool;
        //    56: invokevirtual   org/jruby/util/io/SelectorPool.get:()Ljava/nio/channels/Selector;
        //    59: putfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //    62: aload           selectable
        //    64: aload_0         /* this */
        //    65: getfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //    68: iload_3         /* ops */
        //    69: invokevirtual   java/nio/channels/SelectableChannel.register:(Ljava/nio/channels/Selector;I)Ljava/nio/channels/SelectionKey;
        //    72: astore          key
        //    74: aload_0         /* this */
        //    75: invokevirtual   org/jruby/RubyThread.beforeBlockingCall:()V
        //    78: lload           timeout
        //    80: lconst_0       
        //    81: lcmp           
        //    82: ifge            97
        //    85: aload_0         /* this */
        //    86: getfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //    89: invokevirtual   java/nio/channels/Selector.select:()I
        //    92: istore          result
        //    94: goto            127
        //    97: lload           timeout
        //    99: lconst_0       
        //   100: lcmp           
        //   101: ifne            116
        //   104: aload_0         /* this */
        //   105: getfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //   108: invokevirtual   java/nio/channels/Selector.selectNow:()I
        //   111: istore          result
        //   113: goto            127
        //   116: aload_0         /* this */
        //   117: getfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //   120: lload           timeout
        //   122: invokevirtual   java/nio/channels/Selector.select:(J)I
        //   125: istore          result
        //   127: aload_0         /* this */
        //   128: invokevirtual   org/jruby/RubyThread.pollThreadEvents:()V
        //   131: iload           result
        //   133: iconst_1       
        //   134: if_icmpne       175
        //   137: aload_0         /* this */
        //   138: getfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //   141: invokevirtual   java/nio/channels/Selector.selectedKeys:()Ljava/util/Set;
        //   144: astore          keySet
        //   146: aload           keySet
        //   148: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   153: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   158: aload           key
        //   160: if_acmpne       175
        //   163: iconst_1       
        //   164: istore          12
        //   166: jsr             226
        //   169: aload           7
        //   171: monitorexit    
        //   172: iload           12
        //   174: ireturn        
        //   175: iconst_0       
        //   176: istore          11
        //   178: jsr             226
        //   181: aload           7
        //   183: monitorexit    
        //   184: iload           11
        //   186: ireturn        
        //   187: astore          ioe
        //   189: aload_0         /* this */
        //   190: invokevirtual   org/jruby/RubyThread.getRuntime:()Lorg/jruby/Ruby;
        //   193: new             Ljava/lang/StringBuilder;
        //   196: dup            
        //   197: invokespecial   java/lang/StringBuilder.<init>:()V
        //   200: ldc_w           "Error with selector: "
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: aload           ioe
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   211: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   214: invokevirtual   org/jruby/Ruby.newRuntimeError:(Ljava/lang/String;)Lorg/jruby/exceptions/RaiseException;
        //   217: athrow         
        //   218: astore          13
        //   220: jsr             226
        //   223: aload           13
        //   225: athrow         
        //   226: astore          14
        //   228: aload           key
        //   230: ifnull          238
        //   233: aload           key
        //   235: invokevirtual   java/nio/channels/SelectionKey.cancel:()V
        //   238: aload_0         /* this */
        //   239: getfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //   242: ifnull          253
        //   245: aload_0         /* this */
        //   246: getfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //   249: invokevirtual   java/nio/channels/Selector.selectNow:()I
        //   252: pop            
        //   253: goto            258
        //   256: astore          e
        //   258: aload_0         /* this */
        //   259: getfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //   262: ifnull          279
        //   265: aload_0         /* this */
        //   266: invokevirtual   org/jruby/RubyThread.getRuntime:()Lorg/jruby/Ruby;
        //   269: invokevirtual   org/jruby/Ruby.getSelectorPool:()Lorg/jruby/util/io/SelectorPool;
        //   272: aload_0         /* this */
        //   273: getfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //   276: invokevirtual   org/jruby/util/io/SelectorPool.put:(Ljava/nio/channels/Selector;)V
        //   279: jsr             301
        //   282: goto            310
        //   285: astore          e
        //   287: jsr             301
        //   290: goto            310
        //   293: astore          16
        //   295: jsr             301
        //   298: aload           16
        //   300: athrow         
        //   301: astore          17
        //   303: aload_0         /* this */
        //   304: aconst_null    
        //   305: putfield        org/jruby/RubyThread.currentSelector:Ljava/nio/channels/Selector;
        //   308: ret             17
        //   310: aload_2         /* io */
        //   311: ifnull          319
        //   314: aload_2         /* io */
        //   315: aload_0         /* this */
        //   316: invokevirtual   org/jruby/RubyIO.removeBlockingThread:(Lorg/jruby/RubyThread;)V
        //   319: aload           selectable
        //   321: iload           oldBlocking
        //   323: invokevirtual   java/nio/channels/SelectableChannel.configureBlocking:(Z)Ljava/nio/channels/SelectableChannel;
        //   326: pop            
        //   327: goto            332
        //   330: astore          e
        //   332: aload_0         /* this */
        //   333: invokevirtual   org/jruby/RubyThread.afterBlockingCall:()V
        //   336: ret             14
        //   338: astore          18
        //   340: aload           7
        //   342: monitorexit    
        //   343: aload           18
        //   345: athrow         
        //   346: iconst_1       
        //   347: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  -------------------------------------
        //  94     3       10    result       I
        //  113    3       10    result       I
        //  146    29      11    keySet       Ljava/util/Set;
        //  127    60      10    result       I
        //  189    29      10    ioe          Ljava/io/IOException;
        //  258    0       15    e            Ljava/lang/Exception;
        //  287    0       15    e            Ljava/lang/Exception;
        //  332    0       15    e            Ljava/lang/Exception;
        //  29     309     8     oldBlocking  Z
        //  32     306     9     key          Ljava/nio/channels/SelectionKey;
        //  13     333     6     selectable   Ljava/nio/channels/SelectableChannel;
        //  0      348     0     this         Lorg/jruby/RubyThread;
        //  0      348     1     channel      Ljava/nio/channels/Channel;
        //  0      348     2     io           Lorg/jruby/RubyIO;
        //  0      348     3     ops          I
        //  0      348     4     timeout      J
        //    LocalVariableTypeTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  -------------------------------------------------
        //  146    29      11    keySet  Ljava/util/Set<Ljava/nio/channels/SelectionKey;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  32     169    187    218    Ljava/io/IOException;
        //  175    181    187    218    Ljava/io/IOException;
        //  32     169    218    226    Any
        //  175    181    218    226    Any
        //  187    223    218    226    Any
        //  228    253    256    258    Ljava/lang/Exception;
        //  258    279    285    293    Ljava/lang/Exception;
        //  258    282    293    301    Any
        //  285    290    293    301    Any
        //  293    298    293    301    Any
        //  319    327    330    332    Ljava/lang/Exception;
        //  22     172    338    346    Any
        //  175    184    338    346    Any
        //  187    343    338    346    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0277 (coming from #0274).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2181)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void interrupt() {
        final Selector activeSelector = this.currentSelector;
        if (activeSelector != null) {
            activeSelector.wakeup();
        }
        final BlockingIO.Condition iowait = this.blockingIO;
        if (iowait != null) {
            iowait.cancel();
        }
        final BlockingTask task = this.currentBlockingTask;
        if (task != null) {
            task.wakeup();
        }
    }
    
    public boolean waitForIO(final ThreadContext context, final RubyIO io, final int ops) {
        final Channel channel = io.getChannel();
        if (!(channel instanceof SelectableChannel)) {
            return true;
        }
        try {
            io.addBlockingThread(this);
            this.blockingIO = BlockingIO.newCondition(channel, ops);
            final boolean ready = this.blockingIO.await();
            this.pollThreadEvents();
            return ready;
        }
        catch (IOException ioe) {
            throw context.getRuntime().newRuntimeError("Error with selector: " + ioe);
        }
        catch (InterruptedException ex) {
            throw context.getRuntime().newRuntimeError("Interrupted");
        }
        finally {
            this.blockingIO = null;
            io.removeBlockingThread(this);
        }
    }
    
    public void beforeBlockingCall() {
        this.pollThreadEvents();
        this.enterSleep();
    }
    
    public void afterBlockingCall() {
        this.exitSleep();
        this.pollThreadEvents();
    }
    
    private void receivedAnException(final ThreadContext context, final IRubyObject exception) {
        final RubyModule kernelModule = this.getRuntime().getKernel();
        kernelModule.callMethod(context, "raise", exception);
    }
    
    public boolean wait_timeout(final IRubyObject o, final Double timeout) throws InterruptedException {
        if (timeout != null) {
            final long delay_ns = (long)(timeout * 1.0E9);
            final long start_ns = System.nanoTime();
            if (delay_ns > 0L) {
                final long delay_ms = delay_ns / 1000000L;
                final int delay_ns_remainder = (int)(delay_ns % 1000000L);
                this.executeBlockingTask(new SleepTask(o, delay_ms, delay_ns_remainder));
            }
            final long end_ns = System.nanoTime();
            return end_ns - start_ns <= delay_ns;
        }
        this.executeBlockingTask(new SleepTask(o, 0L, 0));
        return true;
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final RubyThread other = (RubyThread)obj;
        return this.threadImpl == other.threadImpl || (this.threadImpl != null && this.threadImpl.equals(other.threadImpl));
    }
    
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + ((this.threadImpl != null) ? this.threadImpl.hashCode() : 0);
        return hash;
    }
    
    public String toString() {
        return this.threadImpl.toString();
    }
    
    private String identityString() {
        return "0x" + Integer.toHexString(System.identityHashCode(this));
    }
    
    public enum Status
    {
        RUN, 
        SLEEP, 
        ABORTING, 
        DEAD;
    }
    
    public static final class SleepTask implements BlockingTask
    {
        private final Object object;
        private final long millis;
        private final int nanos;
        
        public SleepTask(final Object object, final long millis, final int nanos) {
            this.object = object;
            this.millis = millis;
            this.nanos = nanos;
        }
        
        public void run() throws InterruptedException {
            synchronized (this.object) {
                this.object.wait(this.millis, this.nanos);
            }
        }
        
        public void wakeup() {
            synchronized (this.object) {
                this.object.notify();
            }
        }
    }
    
    public interface BlockingTask
    {
        void run() throws InterruptedException;
        
        void wakeup();
    }
}
