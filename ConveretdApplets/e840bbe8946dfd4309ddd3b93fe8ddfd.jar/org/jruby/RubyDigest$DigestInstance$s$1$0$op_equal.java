// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyDigest$DigestInstance$s$1$0$op_equal extends JavaMethodOne
{
    public RubyDigest$DigestInstance$s$1$0$op_equal(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext ctx, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject oth) {
        return RubyDigest.DigestInstance.op_equal(ctx, self, oth);
    }
}