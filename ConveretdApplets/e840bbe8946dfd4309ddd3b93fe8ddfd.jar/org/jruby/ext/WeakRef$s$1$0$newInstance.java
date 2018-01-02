// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class WeakRef$s$1$0$newInstance extends JavaMethodOne
{
    public WeakRef$s$1$0$newInstance(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject clazz, final RubyModule rubyModule, final String s, final IRubyObject arg) {
        return WeakRef.newInstance(clazz, arg);
    }
}
