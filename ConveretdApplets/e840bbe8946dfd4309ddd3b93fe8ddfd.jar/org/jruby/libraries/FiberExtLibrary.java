// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import org.jruby.CompatVersion;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class FiberExtLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) {
        final RubyClass cFiber = runtime.getClass("Fiber");
        cFiber.defineAnnotatedMethods(FiberExtMeta.class);
    }
    
    public static class FiberExtMeta
    {
        @JRubyMethod(compat = CompatVersion.RUBY1_9, meta = true)
        public static IRubyObject current(final ThreadContext context, final IRubyObject recv) {
            return context.getRuntime().getCurrentContext().getFiber();
        }
    }
}
