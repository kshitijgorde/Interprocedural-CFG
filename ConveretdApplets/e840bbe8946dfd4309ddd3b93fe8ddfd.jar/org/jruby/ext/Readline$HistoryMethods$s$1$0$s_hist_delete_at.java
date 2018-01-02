// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Readline$HistoryMethods$s$1$0$s_hist_delete_at extends JavaMethodOne
{
    public Readline$HistoryMethods$s$1$0$s_hist_delete_at(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject index) {
        return Readline.HistoryMethods.s_hist_delete_at(recv, index);
    }
}
