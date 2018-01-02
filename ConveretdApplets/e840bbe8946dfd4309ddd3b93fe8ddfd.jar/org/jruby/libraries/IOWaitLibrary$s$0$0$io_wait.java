// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class IOWaitLibrary$s$0$0$io_wait extends JavaMethodZero
{
    public IOWaitLibrary$s$0$0$io_wait(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject obj, final RubyModule rubyModule, final String s) {
        return IOWaitLibrary.io_wait(context, obj);
    }
}
