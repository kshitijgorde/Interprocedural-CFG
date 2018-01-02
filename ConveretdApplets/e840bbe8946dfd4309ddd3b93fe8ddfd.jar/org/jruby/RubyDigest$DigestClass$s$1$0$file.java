// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyDigest$DigestClass$s$1$0$file extends JavaMethodOne
{
    public RubyDigest$DigestClass$s$1$0$file(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext ctx, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject filename) {
        return RubyDigest.DigestClass.file(ctx, recv, filename);
    }
}
