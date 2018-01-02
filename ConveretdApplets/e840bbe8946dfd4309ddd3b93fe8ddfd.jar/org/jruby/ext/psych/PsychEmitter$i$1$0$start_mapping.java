// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.psych;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class PsychEmitter$i$1$0$start_mapping extends JavaMethodN
{
    public PsychEmitter$i$1$0$start_mapping(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length != 4) {
            Arity.checkArgumentCount(context.getRuntime(), array, 4, 4);
        }
        return ((PsychEmitter)rubyObject).start_mapping(context, array);
    }
}
