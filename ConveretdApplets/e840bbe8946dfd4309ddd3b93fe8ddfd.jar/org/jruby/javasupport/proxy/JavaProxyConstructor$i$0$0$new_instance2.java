// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JavaProxyConstructor$i$0$0$new_instance2 extends JavaMethodNBlock
{
    public JavaProxyConstructor$i$0$0$new_instance2(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] args, final Block unusedBlock) {
        return ((JavaProxyConstructor)rubyObject).new_instance2(args, unusedBlock);
    }
}
