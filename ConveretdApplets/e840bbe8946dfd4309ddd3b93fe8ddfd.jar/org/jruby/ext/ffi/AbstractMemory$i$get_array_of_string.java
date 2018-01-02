// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class AbstractMemory$i$get_array_of_string extends JavaMethodOneOrTwo
{
    public AbstractMemory$i$get_array_of_string(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject rbOffset, final IRubyObject rbCount) {
        return ((AbstractMemory)rubyObject).get_array_of_string(context, rbOffset, rbCount);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject rbOffset) {
        return ((AbstractMemory)rubyObject).get_array_of_string(context, rbOffset);
    }
}
