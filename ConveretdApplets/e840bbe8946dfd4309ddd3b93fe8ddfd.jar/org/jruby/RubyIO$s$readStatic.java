// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyIO$s$readStatic extends JavaMethodOneOrTwoOrThree
{
    public RubyIO$s$readStatic(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject path, final IRubyObject length) {
        return RubyIO.readStatic(context, recv, path, length);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject path) {
        return RubyIO.readStatic(context, recv, path);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject path, final IRubyObject length, final IRubyObject offset) {
        return RubyIO.readStatic(context, recv, path, length, offset);
    }
}
