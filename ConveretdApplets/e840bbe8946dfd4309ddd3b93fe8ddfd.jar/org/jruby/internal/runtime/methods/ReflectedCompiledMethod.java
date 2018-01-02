// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.Ruby;
import java.lang.reflect.InvocationTargetException;
import org.jruby.exceptions.JumpException;
import java.lang.reflect.Member;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.RubyEvent;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Arity;
import org.jruby.RubyModule;
import java.lang.reflect.Method;

public class ReflectedCompiledMethod extends CompiledMethod
{
    private final Method method;
    
    public ReflectedCompiledMethod(final RubyModule implementationClass, final Arity arity, final Visibility visibility, final StaticScope staticScope, final Object scriptObject, final Method method, final CallConfiguration callConfig, final ISourcePosition position, final String parameterDesc) {
        this.init(implementationClass, arity, visibility, staticScope, scriptObject, callConfig, position, parameterDesc);
        this.method = method;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        this.callConfig.pre(context, self, this.getImplementationClass(), name, block, this.staticScope);
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            final boolean isTrace = runtime.hasEventHooks();
            try {
                if (isTrace) {
                    runtime.callEventHooks(context, RubyEvent.CALL, this.position.getFile(), this.position.getStartLine(), name, this.getImplementationClass());
                }
                return (IRubyObject)this.method.invoke(null, this.$scriptObject, context, self, args, block);
            }
            finally {
                if (isTrace) {
                    runtime.callEventHooks(context, RubyEvent.RETURN, context.getFile(), context.getLine(), name, this.getImplementationClass());
                }
            }
        }
        catch (IllegalArgumentException e) {
            throw RaiseException.createNativeRaiseException(runtime, e, this.method);
        }
        catch (IllegalAccessException e2) {
            throw RaiseException.createNativeRaiseException(runtime, e2, this.method);
        }
        catch (InvocationTargetException e3) {
            final Throwable cause = e3.getCause();
            if (cause instanceof JumpException.ReturnJump) {
                return this.handleReturn(context, (JumpException.ReturnJump)cause, callNumber);
            }
            if (cause instanceof JumpException.RedoJump) {
                return this.handleRedo(runtime);
            }
            if (cause instanceof RuntimeException) {
                throw (RuntimeException)cause;
            }
            if (cause instanceof Error) {
                throw (Error)cause;
            }
            throw RaiseException.createNativeRaiseException(runtime, cause, this.method);
        }
        finally {
            this.callConfig.post(context);
        }
    }
}
