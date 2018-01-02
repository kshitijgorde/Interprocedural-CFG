// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class ThreadLibrary$SizedQueue$i$0$0$clear extends JavaMethodZero
{
    public ThreadLibrary$SizedQueue$i$0$0$clear(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((ThreadLibrary.SizedQueue)rubyObject).clear(context);
    }
}
