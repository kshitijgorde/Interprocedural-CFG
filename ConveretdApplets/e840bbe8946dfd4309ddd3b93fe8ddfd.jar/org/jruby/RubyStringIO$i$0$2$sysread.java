// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyStringIO$i$0$2$sysread extends JavaMethodN
{
    public RubyStringIO$i$0$2$sysread(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length > 2) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 0, 2);
        }
        return ((RubyStringIO)rubyObject).sysread(array);
    }
}
