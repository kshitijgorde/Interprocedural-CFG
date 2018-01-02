// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyIO$i$set_encoding extends JavaMethodOneOrTwoOrThree
{
    public RubyIO$i$set_encoding(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject encodingString, final IRubyObject internalEncoding, final IRubyObject options) {
        return ((RubyIO)rubyObject).set_encoding(context, encodingString, internalEncoding, options);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject encodingString) {
        return ((RubyIO)rubyObject).set_encoding(context, encodingString);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject encodingString, final IRubyObject internalEncoding) {
        return ((RubyIO)rubyObject).set_encoding(context, encodingString, internalEncoding);
    }
}
