// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.psych;

import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.RubyObject;
import org.jruby.RubyModule;
import org.jruby.Ruby;

public class PsychToRuby
{
    public static void initPsychToRuby(final Ruby runtime, final RubyModule psych) {
        final RubyModule visitors = runtime.defineModuleUnder("Visitors", psych);
        final RubyClass visitor = runtime.defineClassUnder("Visitor", runtime.getObject(), runtime.getObject().getAllocator(), visitors);
        final RubyClass psychToRuby = runtime.defineClassUnder("ToRuby", visitor, RubyObject.OBJECT_ALLOCATOR, visitors);
        psychToRuby.defineAnnotatedMethods(PsychToRuby.class);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public static IRubyObject build_exception(final ThreadContext context, final IRubyObject self, final IRubyObject klass, final IRubyObject message) {
        if (klass instanceof RubyClass) {
            final IRubyObject exception = ((RubyClass)klass).allocate();
            exception.getInternalVariables().setInternalVariable("mesg", message);
            return exception;
        }
        throw context.runtime.newTypeError(klass, context.runtime.getClassClass());
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public static IRubyObject path2class(final ThreadContext context, final IRubyObject self, final IRubyObject path) {
        return context.runtime.getClassFromPath(path.asJavaString());
    }
}
