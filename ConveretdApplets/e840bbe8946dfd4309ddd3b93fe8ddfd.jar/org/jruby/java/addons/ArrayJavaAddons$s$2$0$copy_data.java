// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class ArrayJavaAddons$s$2$0$copy_data extends JavaMethodTwo
{
    public ArrayJavaAddons$s$2$0$copy_data(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyArray, final RubyModule rubyModule, final String s, final IRubyObject javaArray, final IRubyObject fillValue) {
        return ArrayJavaAddons.copy_data(context, rubyArray, javaArray, fillValue);
    }
}
