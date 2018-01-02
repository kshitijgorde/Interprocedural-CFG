// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Buffer$s$allocateInput extends JavaMethodOneOrTwoOrThree
{
    public Buffer$s$allocateInput(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg) {
        return Buffer.allocateInput(context, recv, arg);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject sizeArg, final IRubyObject countArg, final IRubyObject clearArg) {
        return Buffer.allocateInput(context, recv, sizeArg, countArg, clearArg);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject sizeArg, final IRubyObject arg2) {
        return Buffer.allocateInput(context, recv, sizeArg, arg2);
    }
}
