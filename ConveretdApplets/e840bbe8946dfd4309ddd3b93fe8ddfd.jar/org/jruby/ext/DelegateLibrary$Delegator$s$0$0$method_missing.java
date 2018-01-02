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

public class DelegateLibrary$Delegator$s$0$0$method_missing extends JavaMethodNBlock
{
    public DelegateLibrary$Delegator$s$0$0$method_missing(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject[] args, final Block block) {
        return DelegateLibrary.Delegator.method_missing(context, self, args, block);
    }
}
