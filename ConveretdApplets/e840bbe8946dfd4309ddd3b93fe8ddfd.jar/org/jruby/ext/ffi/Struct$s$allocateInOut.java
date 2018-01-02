// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Struct$s$allocateInOut extends JavaMethodZeroOrOne
{
    public Struct$s$allocateInOut(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject klass, final RubyModule rubyModule, final String s) {
        return Struct.allocateInOut(context, klass);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject klass, final RubyModule rubyModule, final String s, final IRubyObject clearArg) {
        return Struct.allocateInOut(context, klass, clearArg);
    }
}
