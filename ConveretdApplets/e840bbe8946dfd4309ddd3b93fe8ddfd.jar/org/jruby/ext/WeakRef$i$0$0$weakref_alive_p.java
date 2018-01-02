// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class WeakRef$i$0$0$weakref_alive_p extends JavaMethodZero
{
    public WeakRef$i$0$0$weakref_alive_p(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((WeakRef)rubyObject).weakref_alive_p();
    }
}
