// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class StructLayout$ArrayProxy$i$1$0$get extends JavaMethodOne
{
    public StructLayout$ArrayProxy$i$1$0$get(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject index) {
        return ((StructLayout.ArrayProxy)rubyObject).get(context, index);
    }
}
