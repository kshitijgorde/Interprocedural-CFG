// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JavaObject$i$3$0$afill extends JavaMethodThree
{
    public JavaObject$i$3$0$afill(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject beginIndex, final IRubyObject endIndex, final IRubyObject someValue) {
        return ((JavaObject)rubyObject).afill(beginIndex, endIndex, someValue);
    }
}
