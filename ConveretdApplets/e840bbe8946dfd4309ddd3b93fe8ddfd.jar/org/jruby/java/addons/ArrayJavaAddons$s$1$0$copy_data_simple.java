// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class ArrayJavaAddons$s$1$0$copy_data_simple extends JavaMethodOne
{
    public ArrayJavaAddons$s$1$0$copy_data_simple(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject from, final RubyModule rubyModule, final String s, final IRubyObject to) {
        return ArrayJavaAddons.copy_data_simple(context, from, to);
    }
}
