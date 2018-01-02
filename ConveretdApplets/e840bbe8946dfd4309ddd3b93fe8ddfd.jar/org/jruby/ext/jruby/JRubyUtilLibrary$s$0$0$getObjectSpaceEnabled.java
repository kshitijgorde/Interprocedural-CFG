// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JRubyUtilLibrary$s$0$0$getObjectSpaceEnabled extends JavaMethodZero
{
    public JRubyUtilLibrary$s$0$0$getObjectSpaceEnabled(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s) {
        return JRubyUtilLibrary.getObjectSpaceEnabled(recv);
    }
}
