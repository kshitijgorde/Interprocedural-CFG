// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyJRuby$JRubyCompiledScript$s$0$0$compiled_script_inspect extends JavaMethodZero
{
    public RubyJRuby$JRubyCompiledScript$s$0$0$compiled_script_inspect(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s) {
        return RubyJRuby.JRubyCompiledScript.compiled_script_inspect(recv);
    }
}
