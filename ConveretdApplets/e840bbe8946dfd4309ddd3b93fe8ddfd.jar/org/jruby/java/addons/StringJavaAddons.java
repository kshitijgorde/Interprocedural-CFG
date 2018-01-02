// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.javasupport.Java;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.JavaArrayUtilities;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class StringJavaAddons
{
    @JRubyMethod
    public static IRubyObject to_java_bytes(final ThreadContext context, final IRubyObject self) {
        return JavaArrayUtilities.ruby_string_to_bytes(self, self);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject from_java_bytes(final ThreadContext context, final IRubyObject self, final IRubyObject bytes) {
        return JavaArrayUtilities.bytes_to_ruby_string(bytes, bytes);
    }
    
    @JRubyMethod
    public static IRubyObject to_java_string(final ThreadContext context, final IRubyObject self) {
        return Java.getInstance(context.getRuntime(), self.convertToString().asJavaString());
    }
}
