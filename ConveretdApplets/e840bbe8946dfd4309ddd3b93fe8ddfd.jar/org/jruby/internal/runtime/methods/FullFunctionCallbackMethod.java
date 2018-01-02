// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Arity;
import org.jruby.Ruby;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.RubyEvent;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.runtime.callback.Callback;

public class FullFunctionCallbackMethod extends DynamicMethod
{
    private Callback callback;
    
    public FullFunctionCallbackMethod(final RubyModule implementationClass, final Callback callback, final Visibility visibility) {
        super(implementationClass, visibility, CallConfiguration.FrameFullScopeNone);
        this.callback = callback;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        try {
            this.callConfig.pre(context, self, clazz, name, block, null);
            assert args != null;
            final Ruby runtime = context.getRuntime();
            final boolean isTrace = runtime.hasEventHooks();
            if (isTrace) {
                runtime.callEventHooks(context, RubyEvent.C_CALL, context.getFile(), context.getLine(), name, this.getImplementationClass());
            }
            try {
                return this.callback.execute(self, args, block);
            }
            catch (JumpException.ReturnJump rj) {
                if (rj.getTarget() == context.getFrameJumpTarget()) {
                    return (IRubyObject)rj.getValue();
                }
                throw rj;
            }
            finally {
                if (isTrace) {
                    runtime.callEventHooks(context, RubyEvent.C_RETURN, context.getFile(), context.getLine(), name, this.getImplementationClass());
                }
            }
        }
        finally {
            this.callConfig.post(context);
        }
    }
    
    public Callback getCallback() {
        return this.callback;
    }
    
    public Arity getArity() {
        return this.getCallback().getArity();
    }
    
    public DynamicMethod dup() {
        return new FullFunctionCallbackMethod(this.getImplementationClass(), this.callback, this.getVisibility());
    }
}
