// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.rubinius;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyTuple$s$1$0$rbNew extends JavaMethodOne
{
    public RubyTuple$s$1$0$rbNew(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject tupleCls, final RubyModule rubyModule, final String s, final IRubyObject cnt) {
        return RubyTuple.rbNew(context, tupleCls, cnt);
    }
}
