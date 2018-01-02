// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class DynamicLibrary$s$2$0$open extends JavaMethodTwo
{
    public DynamicLibrary$s$2$0$open(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject libraryName, final IRubyObject libraryFlags) {
        return DynamicLibrary.open(context, recv, libraryName, libraryFlags);
    }
}
