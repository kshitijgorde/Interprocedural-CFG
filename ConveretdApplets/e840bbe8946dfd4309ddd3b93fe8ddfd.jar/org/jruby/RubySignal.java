// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.internal.runtime.ThreadService;
import org.jruby.runtime.backtrace.BacktraceData;
import org.jruby.runtime.backtrace.TraceType;
import org.jruby.runtime.BlockCallback;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.NoFunctionalitySignalFacade;
import org.jruby.util.SignalFacade;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Signal" })
public class RubySignal
{
    private static final SignalFacade SIGNALS;
    public static final String[] NAMES;
    
    private static final SignalFacade getSignalFacade() {
        try {
            final Class realFacadeClass = Class.forName("org.jruby.util.SunSignalFacade");
            return realFacadeClass.newInstance();
        }
        catch (Throwable e) {
            return new NoFunctionalitySignalFacade();
        }
    }
    
    public static void createSignal(final Ruby runtime) {
        final RubyModule mSignal = runtime.defineModule("Signal");
        mSignal.defineAnnotatedMethods(RubySignal.class);
    }
    
    @JRubyMethod(required = 1, optional = 1, module = true)
    public static IRubyObject trap(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = recv.getRuntime();
        runtime.getLoadService().require("jsignal_internal");
        return RuntimeHelpers.invoke(context, runtime.getKernel(), "__jtrap", args, block);
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject list(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        final RubyHash names = RubyHash.newHash(runtime);
        for (int i = 0; i < RubySignal.NAMES.length; ++i) {
            names.op_aset(context, runtime.newString(RubySignal.NAMES[i]), runtime.newFixnum(i));
        }
        names.op_aset(context, runtime.newString("IOT"), runtime.newFixnum(6));
        names.op_aset(context, runtime.newString("CLD"), runtime.newFixnum(20));
        return names;
    }
    
    @JRubyMethod(name = { "__jtrap_kernel" }, required = 2, module = true)
    public static IRubyObject __jtrap_kernel(final IRubyObject recv, final IRubyObject block, final IRubyObject sig) {
        return RubySignal.SIGNALS.trap(recv, block, sig);
    }
    
    private static void registerThreadDumpSignalHandler(final Ruby runtime) {
        final String threadDumpSignal = runtime.getInstanceConfig().getThreadDumpSignal();
        if (threadDumpSignal != null && threadDumpSignal.length() > 0) {
            RubySignal.SIGNALS.trap(runtime, new BlockCallback() {
                public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Block block) {
                    System.err.println("Ruby Thread Dump");
                    final ThreadService threadService = runtime.getThreadService();
                    final RubyThread[] arr$;
                    final RubyThread[] thrs = arr$ = threadService.getActiveRubyThreads();
                    for (final RubyThread th : arr$) {
                        System.err.println("\n" + th);
                        final RubyException exc = new RubyException(runtime, runtime.getRuntimeError(), "");
                        final ThreadContext tc = threadService.getThreadContextForThread(th);
                        if (tc != null) {
                            exc.setBacktraceData(new BacktraceData(th.javaBacktrace(), tc.createBacktrace2(0, false), false, false, TraceType.Gather.NORMAL));
                            exc.printBacktrace(System.err);
                        }
                        else {
                            System.err.println("    [no longer alive]");
                        }
                    }
                    return runtime.getNil();
                }
            }, threadDumpSignal);
        }
    }
    
    static {
        SIGNALS = getSignalFacade();
        NAMES = new String[] { "EXIT", "HUP", "INT", "QUIT", "ILL", "TRAP", "ABRT", "EMT", "FPE", "KILL", "BUS", "SEGV", "SYS", "PIPE", "ALRM", "TERM", "URG", "STOP", "TSTP", "CONT", "CHLD", "TTIN", "TTOU", "IO", "XCPU", "XFSZ", "VTALRM", "PROF", "WINCH", "INFO", "USR1", "USR2" };
    }
}
