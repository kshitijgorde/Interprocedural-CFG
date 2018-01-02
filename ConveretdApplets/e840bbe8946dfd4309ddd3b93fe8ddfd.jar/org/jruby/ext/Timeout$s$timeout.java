// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Timeout$s$timeout extends JavaMethodOneOrTwoBlock
{
    public Timeout$s$timeout(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject timeout, final RubyModule rubyModule, final String s, final IRubyObject seconds, final IRubyObject exceptionType, final Block block) {
        return Timeout.timeout(context, timeout, seconds, exceptionType, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject timeout, final RubyModule rubyModule, final String s, final IRubyObject seconds, final Block block) {
        return Timeout.timeout(context, timeout, seconds, block);
    }
}
