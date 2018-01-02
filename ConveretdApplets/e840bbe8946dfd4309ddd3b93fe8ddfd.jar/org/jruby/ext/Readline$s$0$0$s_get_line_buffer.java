// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Readline$s$0$0$s_get_line_buffer extends JavaMethodZero
{
    public Readline$s$0$0$s_get_line_buffer(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s) {
        return Readline.s_get_line_buffer(context, recv);
    }
}
