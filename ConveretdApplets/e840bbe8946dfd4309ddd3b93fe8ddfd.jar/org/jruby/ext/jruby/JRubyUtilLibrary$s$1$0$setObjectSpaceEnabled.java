// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JRubyUtilLibrary$s$1$0$setObjectSpaceEnabled extends JavaMethodOne
{
    public JRubyUtilLibrary$s$1$0$setObjectSpaceEnabled(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg) {
        return JRubyUtilLibrary.setObjectSpaceEnabled(recv, arg);
    }
}
