// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyString$i$encode extends JavaMethodZeroOrOneOrTwoOrThree
{
    public RubyString$i$encode(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject enc) {
        return ((RubyString)rubyObject).encode(context, enc);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((RubyString)rubyObject).encode(context);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject toEncoding, final IRubyObject arg) {
        return ((RubyString)rubyObject).encode(context, toEncoding, arg);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject toEncoding, final IRubyObject forcedEncoding, final IRubyObject opts) {
        return ((RubyString)rubyObject).encode(context, toEncoding, forcedEncoding, opts);
    }
}
