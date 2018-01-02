// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class MapJavaProxy$i$1$0$default_value_set extends JavaMethodOne
{
    public MapJavaProxy$i$1$0$default_value_set(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject defaultValue) {
        return ((MapJavaProxy)rubyObject).default_value_set(defaultValue);
    }
}
