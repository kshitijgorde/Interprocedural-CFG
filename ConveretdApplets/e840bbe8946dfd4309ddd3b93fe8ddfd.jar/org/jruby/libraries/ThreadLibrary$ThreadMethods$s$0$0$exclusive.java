// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class ThreadLibrary$ThreadMethods$s$0$0$exclusive extends JavaMethodZeroBlock
{
    public ThreadLibrary$ThreadMethods$s$0$0$exclusive(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject receiver, final RubyModule rubyModule, final String s, final Block block) {
        return ThreadLibrary.ThreadMethods.exclusive(context, receiver, block);
    }
}
