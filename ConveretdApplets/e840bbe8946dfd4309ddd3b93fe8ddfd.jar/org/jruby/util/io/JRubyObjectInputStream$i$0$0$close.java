// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JRubyObjectInputStream$i$0$0$close extends JavaMethodZero
{
    public JRubyObjectInputStream$i$0$0$close(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((JRubyObjectInputStream)rubyObject).close();
    }
}
