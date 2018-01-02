// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class ArrayJavaAddons$s$dimensions extends JavaMethodZeroOrOneOrTwo
{
    public ArrayJavaAddons$s$dimensions(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject maybeArray, final RubyModule rubyModule, final String s, final IRubyObject dims) {
        return ArrayJavaAddons.dimensions(context, maybeArray, dims);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject maybeArray, final RubyModule rubyModule, final String s, final IRubyObject dims, final IRubyObject index) {
        return ArrayJavaAddons.dimensions(context, maybeArray, dims, index);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject maybeArray, final RubyModule rubyModule, final String s) {
        return ArrayJavaAddons.dimensions(context, maybeArray);
    }
}
