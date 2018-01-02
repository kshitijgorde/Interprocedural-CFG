// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyUnboundMethod$i$0$0$to_proc extends JavaMethodZeroBlock
{
    public RubyUnboundMethod$i$0$0$to_proc(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final Block unusedBlock) {
        return ((RubyUnboundMethod)rubyObject).to_proc(context, unusedBlock);
    }
}
