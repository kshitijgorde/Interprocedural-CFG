// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class ArrayJavaProxy$i$op_aref extends JavaMethodOneOrN
{
    public ArrayJavaProxy$i$op_aref(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 1) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 1);
        }
        return ((ArrayJavaProxy)rubyObject).op_aref(context, array);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg) {
        return ((ArrayJavaProxy)rubyObject).op_aref(context, arg);
    }
}
