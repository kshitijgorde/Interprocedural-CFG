// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.io;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class FileDescriptorIO$s$1$0$wrap extends JavaMethodOne
{
    public FileDescriptorIO$s$1$0$wrap(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject fd) {
        return FileDescriptorIO.wrap(context, recv, fd);
    }
}
