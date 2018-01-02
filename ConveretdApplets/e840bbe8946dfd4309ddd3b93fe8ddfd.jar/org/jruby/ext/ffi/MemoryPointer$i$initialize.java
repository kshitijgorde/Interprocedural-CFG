// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class MemoryPointer$i$initialize extends JavaMethodOneOrTwoOrThreeBlock
{
    public MemoryPointer$i$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject sizeArg, final IRubyObject count, final IRubyObject clear, final Block block) {
        return ((MemoryPointer)rubyObject).initialize(context, sizeArg, count, clear, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject sizeArg, final IRubyObject count, final Block block) {
        return ((MemoryPointer)rubyObject).initialize(context, sizeArg, count, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject sizeArg, final Block block) {
        return ((MemoryPointer)rubyObject).initialize(context, sizeArg, block);
    }
}
