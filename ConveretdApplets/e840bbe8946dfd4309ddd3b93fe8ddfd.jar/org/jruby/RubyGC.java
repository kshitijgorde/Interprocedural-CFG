// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.common.IRubyWarnings;
import java.util.Iterator;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "GC" })
public class RubyGC
{
    private static volatile boolean gcDisabled;
    private static volatile boolean stress;
    
    public static RubyModule createGCModule(final Ruby runtime) {
        final RubyModule result = runtime.defineModule("GC");
        runtime.setGC(result);
        result.defineAnnotatedMethods(RubyGC.class);
        return result;
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject start(final ThreadContext context, final IRubyObject recv) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod
    public static IRubyObject garbage_collect(final ThreadContext context, final IRubyObject recv) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject enable(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        emptyImplementationWarning(runtime, "GC.enable");
        final boolean old = RubyGC.gcDisabled;
        RubyGC.gcDisabled = false;
        return runtime.newBoolean(old);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject disable(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        emptyImplementationWarning(runtime, "GC.disable");
        final boolean old = RubyGC.gcDisabled;
        RubyGC.gcDisabled = true;
        return runtime.newBoolean(old);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject stress(final ThreadContext context, final IRubyObject recv) {
        return context.getRuntime().newBoolean(RubyGC.stress);
    }
    
    @JRubyMethod(name = { "stress=" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject stress_set(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        emptyImplementationWarning(runtime, "GC.stress=");
        RubyGC.stress = arg.isTrue();
        return runtime.newBoolean(RubyGC.stress);
    }
    
    @JRubyMethod(module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject count(final ThreadContext context, final IRubyObject recv) {
        try {
            int count = 0;
            for (final GarbageCollectorMXBean bean : ManagementFactory.getGarbageCollectorMXBeans()) {
                count += (int)bean.getCollectionCount();
            }
            return context.runtime.newFixnum(count);
        }
        catch (Throwable t) {
            return RubyFixnum.minus_one(context.runtime);
        }
    }
    
    private static void emptyImplementationWarning(final Ruby runtime, final String name) {
        runtime.getWarnings().warn(IRubyWarnings.ID.EMPTY_IMPLEMENTATION, name + " does nothing on JRuby", name);
    }
    
    static {
        RubyGC.gcDisabled = false;
        RubyGC.stress = false;
    }
}
