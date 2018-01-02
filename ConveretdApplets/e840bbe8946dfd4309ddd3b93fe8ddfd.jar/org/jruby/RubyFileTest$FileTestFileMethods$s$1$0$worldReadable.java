// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyFileTest$FileTestFileMethods$s$1$0$worldReadable extends JavaMethodOne
{
    public RubyFileTest$FileTestFileMethods$s$1$0$worldReadable(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject filename) {
        return RubyFileTest.FileTestFileMethods.worldReadable(context, recv, filename);
    }
}
