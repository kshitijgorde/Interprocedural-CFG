// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyIO$i$initialize19 extends JavaMethodOneOrTwoOrThreeBlock
{
    public RubyIO$i$initialize19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject fileNumber, final IRubyObject modeValue, final IRubyObject options, final Block unusedBlock) {
        return ((RubyIO)rubyObject).initialize19(context, fileNumber, modeValue, options, unusedBlock);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject fileNumber, final IRubyObject second, final Block unusedBlock) {
        return ((RubyIO)rubyObject).initialize19(context, fileNumber, second, unusedBlock);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject fileNumber, final Block unusedBlock) {
        return ((RubyIO)rubyObject).initialize19(context, fileNumber, unusedBlock);
    }
}
