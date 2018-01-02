// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JRubyObjectInputStream$i$1$0$initialize extends JavaMethodOne
{
    public JRubyObjectInputStream$i$1$0$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject wrappedStream) {
        return ((JRubyObjectInputStream)rubyObject).initialize(wrappedStream);
    }
}
