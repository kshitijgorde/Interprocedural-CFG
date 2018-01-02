// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyString$i$2$0$tr_s_bang19 extends JavaMethodTwo
{
    public RubyString$i$2$0$tr_s_bang19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject src, final IRubyObject repl) {
        return ((RubyString)rubyObject).tr_s_bang19(context, src, repl);
    }
}
