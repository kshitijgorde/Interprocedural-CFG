// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBigDecimal$BigDecimalKernelMethods$s$0$0$newBigDecimal extends JavaMethodN
{
    public RubyBigDecimal$BigDecimalKernelMethods$s$0$0$newBigDecimal(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] args) {
        return RubyBigDecimal.BigDecimalKernelMethods.newBigDecimal(recv, args);
    }
}
