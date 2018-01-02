// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class AbstractMemory$i$0$1$to_s extends JavaMethodN
{
    public AbstractMemory$i$0$1$to_s(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length > 1) {
            Arity.checkArgumentCount(context.getRuntime(), array, 0, 1);
        }
        return ((AbstractMemory)rubyObject).to_s(context, array);
    }
}
