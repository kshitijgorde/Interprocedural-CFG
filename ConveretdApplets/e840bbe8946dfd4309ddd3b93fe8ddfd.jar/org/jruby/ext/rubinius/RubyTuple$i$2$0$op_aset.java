// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.rubinius;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyTuple$i$2$0$op_aset extends JavaMethodTwo
{
    public RubyTuple$i$2$0$op_aset(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject idx, final IRubyObject val) {
        return ((RubyTuple)rubyObject).op_aset(context, idx, val);
    }
}
