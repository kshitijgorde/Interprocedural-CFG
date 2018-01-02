// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Buffer$i$initialize extends JavaMethodOneOrTwoOrThree
{
    public Buffer$i$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject sizeArg) {
        return ((Buffer)rubyObject).initialize(context, sizeArg);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject sizeArg, final IRubyObject arg2) {
        return ((Buffer)rubyObject).initialize(context, sizeArg, arg2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject sizeArg, final IRubyObject countArg, final IRubyObject clearArg) {
        return ((Buffer)rubyObject).initialize(context, sizeArg, countArg, clearArg);
    }
}
