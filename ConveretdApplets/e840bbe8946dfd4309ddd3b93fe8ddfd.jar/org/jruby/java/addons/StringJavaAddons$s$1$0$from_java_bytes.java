// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class StringJavaAddons$s$1$0$from_java_bytes extends JavaMethodOne
{
    public StringJavaAddons$s$1$0$from_java_bytes(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject bytes) {
        return StringJavaAddons.from_java_bytes(context, self, bytes);
    }
}
