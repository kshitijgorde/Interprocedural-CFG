// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JRubyApplet$RubyMethods$s$0$0$on_start extends JavaMethodZeroBlock
{
    public JRubyApplet$RubyMethods$s$0$0$on_start(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final Block block) {
        return JRubyApplet.RubyMethods.on_start(recv, block);
    }
}
