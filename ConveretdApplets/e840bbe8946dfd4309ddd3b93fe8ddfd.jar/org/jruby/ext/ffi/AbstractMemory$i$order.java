// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class AbstractMemory$i$order extends JavaMethodZeroOrOne
{
    public AbstractMemory$i$order(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((AbstractMemory)rubyObject).order(context);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject byte_order) {
        return ((AbstractMemory)rubyObject).order(context, byte_order);
    }
}