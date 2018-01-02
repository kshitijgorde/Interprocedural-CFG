// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class AutoPointer$i$initialize extends JavaMethodOneOrTwo
{
    public AutoPointer$i$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject pointerArg, final IRubyObject releaser) {
        return ((AutoPointer)rubyObject).initialize(context, pointerArg, releaser);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject pointerArg) {
        return ((AutoPointer)rubyObject).initialize(context, pointerArg);
    }
}
