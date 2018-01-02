// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyFileTest$s$2$0$identical_p extends JavaMethodTwo
{
    public RubyFileTest$s$2$0$identical_p(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject filename1, final IRubyObject filename2) {
        return RubyFileTest.identical_p(recv, filename1, filename2);
    }
}
