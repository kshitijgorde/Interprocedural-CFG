// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$with_object extends JavaMethodOneBlock
{
    public RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$with_object(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject arg, final Block block) {
        return RubyEnumerator.RubyEnumeratorEnumerable.with_object(context, self, arg, block);
    }
}
