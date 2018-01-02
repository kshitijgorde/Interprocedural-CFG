// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyZlib$RubyGzipReader$i$1$0$initialize extends JavaMethodOneBlock
{
    public RubyZlib$RubyGzipReader$i$1$0$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg, final Block unusedBlock) {
        return ((RubyZlib.RubyGzipReader)rubyObject).initialize(arg, unusedBlock);
    }
}
