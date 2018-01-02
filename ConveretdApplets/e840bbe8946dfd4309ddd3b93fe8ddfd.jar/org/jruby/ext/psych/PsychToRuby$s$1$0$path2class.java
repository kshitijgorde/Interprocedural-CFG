// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.psych;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class PsychToRuby$s$1$0$path2class extends JavaMethodOne
{
    public PsychToRuby$s$1$0$path2class(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject path) {
        return PsychToRuby.path2class(context, self, path);
    }
}
