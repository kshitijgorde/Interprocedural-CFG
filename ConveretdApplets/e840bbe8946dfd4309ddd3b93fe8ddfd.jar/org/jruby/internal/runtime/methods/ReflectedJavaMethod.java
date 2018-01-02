// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.Ruby;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyString;
import org.jruby.runtime.RubyEvent;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Arity;
import java.lang.reflect.Modifier;
import org.jruby.runtime.Block;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyModule;
import java.lang.reflect.Method;

public class ReflectedJavaMethod extends JavaMethod
{
    private final Method method;
    private final boolean needsBlock;
    private final boolean isStatic;
    private final int required;
    private final int optional;
    private final boolean rest;
    private final int max;
    private final boolean argsAsIs;
    private final boolean needsThreadContext;
    
    public ReflectedJavaMethod(final RubyModule implementationClass, final Method method, final JRubyMethod annotation) {
        super(implementationClass, annotation.visibility());
        this.method = method;
        final Class<?>[] params = method.getParameterTypes();
        this.needsBlock = (params.length > 0 && params[params.length - 1] == Block.class);
        this.isStatic = Modifier.isStatic(method.getModifiers());
        final Arity arity = Arity.fromAnnotation(annotation, params, this.isStatic);
        this.setArity(arity);
        this.required = ((arity.getValue() >= 0) ? arity.getValue() : (Math.abs(arity.getValue()) - 1));
        this.optional = annotation.optional();
        this.rest = annotation.rest();
        this.needsThreadContext = (params.length > 0 && params[0] == ThreadContext.class);
        this.argsAsIs = (!this.isStatic && this.optional == 0 && !this.rest && !this.needsBlock && !this.needsThreadContext);
        if (this.rest) {
            this.max = -1;
        }
        else {
            this.max = this.required + this.optional;
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        Arity.checkArgumentCount(runtime, args, this.required, this.max);
        this.callConfig.pre(context, self, this.getImplementationClass(), name, block, null);
        try {
            if (!this.isStatic && !this.method.getDeclaringClass().isAssignableFrom(self.getClass())) {
                throw new ClassCastException(self.getClass().getName() + " cannot be converted to " + this.method.getDeclaringClass().getName());
            }
            if (this.argsAsIs) {
                final boolean isTrace = runtime.hasEventHooks();
                try {
                    if (isTrace) {
                        runtime.callEventHooks(context, RubyEvent.C_CALL, context.getFile(), context.getLine(), name, this.getImplementationClass());
                    }
                    return (IRubyObject)this.method.invoke(self, (Object[])args);
                }
                finally {
                    if (isTrace) {
                        runtime.callEventHooks(context, RubyEvent.C_RETURN, context.getFile(), context.getLine(), name, this.getImplementationClass());
                    }
                }
            }
            final int argsLength = this.calcArgsLength();
            final Object[] params = new Object[argsLength];
            int offset = 0;
            if (this.needsThreadContext) {
                params[offset++] = context;
            }
            if (this.isStatic) {
                params[offset++] = self;
            }
            if (this.required < 4 && this.optional == 0 && !this.rest) {
                for (int i = 0; i < args.length; ++i) {
                    if (this.method.getParameterTypes()[offset] == RubyString.class) {
                        params[offset++] = args[i].convertToString();
                    }
                    else {
                        params[offset++] = args[i];
                    }
                }
            }
            else {
                params[offset++] = args;
            }
            if (this.needsBlock) {
                params[offset++] = block;
            }
            final boolean isTrace2 = runtime.hasEventHooks();
            try {
                if (isTrace2) {
                    runtime.callEventHooks(context, RubyEvent.C_CALL, context.getFile(), context.getLine(), name, this.getImplementationClass());
                }
                IRubyObject result;
                if (this.isStatic) {
                    result = (IRubyObject)this.method.invoke(null, params);
                }
                else {
                    result = (IRubyObject)this.method.invoke(self, params);
                }
                return (result == null) ? runtime.getNil() : result;
            }
            finally {
                if (isTrace2) {
                    runtime.callEventHooks(context, RubyEvent.C_RETURN, context.getFile(), context.getLine(), name, this.getImplementationClass());
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
    
    private int calcArgsLength() {
        int argsLength = 0;
        if (this.needsThreadContext) {
            ++argsLength;
        }
        if (this.isStatic) {
            ++argsLength;
        }
        if (this.required < 4 && this.optional == 0 && !this.rest) {
            argsLength += this.required;
        }
        else {
            ++argsLength;
        }
        if (this.needsBlock) {
            ++argsLength;
        }
        return argsLength;
    }
}
