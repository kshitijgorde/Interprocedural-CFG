// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Arity;
import org.jruby.Ruby;
import org.jruby.runtime.RubyEvent;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.runtime.callback.Callback;

public class SimpleCallbackMethod extends DynamicMethod
{
    private Callback callback;
    
    public SimpleCallbackMethod(final RubyModule implementationClass, final Callback callback, final Visibility visibility) {
        super(implementationClass, visibility, CallConfiguration.FrameNoneScopeNone);
        this.callback = callback;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args, final Block block) {
        assert args != null;
        final Ruby runtime = context.getRuntime();
        if (runtime.hasEventHooks()) {
            runtime.callEventHooks(context, RubyEvent.C_CALL, context.getFile(), context.getLine(), name, this.getImplementationClass());
            try {
                return this.callback.execute(self, args, block);
            }
            finally {
                runtime.callEventHooks(context, RubyEvent.C_RETURN, context.getFile(), context.getLine(), name, this.getImplementationClass());
            }
        }
        return this.callback.execute(self, args, block);
    }
    
    public Callback getCallback() {
        return this.callback;
    }
    
    public Arity getArity() {
        return this.getCallback().getArity();
    }
    
    public DynamicMethod dup() {
        return new SimpleCallbackMethod(this.getImplementationClass(), this.callback, this.getVisibility());
    }
}
