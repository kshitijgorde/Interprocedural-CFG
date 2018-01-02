// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JavaInterfaceTemplate$s$0$0$op_aref extends JavaMethodN
{
    public JavaInterfaceTemplate$s$0$0$op_aref(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject[] args) {
        return JavaInterfaceTemplate.op_aref(context, self, args);
    }
}
