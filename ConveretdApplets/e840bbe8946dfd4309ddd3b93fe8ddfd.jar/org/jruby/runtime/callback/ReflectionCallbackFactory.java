// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callback;

import org.jruby.runtime.CompiledBlockCallback19;
import java.lang.reflect.InvocationTargetException;
import org.jruby.runtime.Block;
import java.lang.reflect.Method;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.CompiledBlockCallback;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.Arity;
import org.jruby.runtime.CallbackFactory;

public class ReflectionCallbackFactory extends CallbackFactory
{
    private final Class type;
    
    public ReflectionCallbackFactory(final Class type) {
        this.type = type;
    }
    
    @Deprecated
    public Callback getMethod(final String method) {
        return new ReflectionCallback(this.type, method, ReflectionCallbackFactory.NULL_CLASS_ARRAY, false, false, Arity.noArguments(), false);
    }
    
    @Deprecated
    public Callback getFastMethod(final String method) {
        return new ReflectionCallback(this.type, method, ReflectionCallbackFactory.NULL_CLASS_ARRAY, false, false, Arity.noArguments(), true);
    }
    
    @Deprecated
    public Callback getFastMethod(final String rubyName, final String method) {
        return new ReflectionCallback(this.type, method, ReflectionCallbackFactory.NULL_CLASS_ARRAY, false, false, Arity.noArguments(), true);
    }
    
    @Deprecated
    public Callback getMethod(final String method, final Class arg1) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1 }, false, false, Arity.singleArgument(), false);
    }
    
    @Deprecated
    public Callback getFastMethod(final String method, final Class arg1) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1 }, false, false, Arity.singleArgument(), true);
    }
    
    @Deprecated
    public Callback getFastMethod(final String rubyName, final String method, final Class arg1) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1 }, false, false, Arity.singleArgument(), true);
    }
    
    @Deprecated
    public Callback getMethod(final String method, final Class arg1, final Class arg2) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2 }, false, false, Arity.fixed(2), false);
    }
    
    @Deprecated
    public Callback getFastMethod(final String method, final Class arg1, final Class arg2) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2 }, false, false, Arity.fixed(2), true);
    }
    
    @Deprecated
    public Callback getFastMethod(final String rubyName, final String method, final Class arg1, final Class arg2) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2 }, false, false, Arity.fixed(2), true);
    }
    
    @Deprecated
    public Callback getMethod(final String method, final Class arg1, final Class arg2, final Class arg3) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2, arg3 }, false, false, Arity.fixed(3), false);
    }
    
    @Deprecated
    public Callback getFastMethod(final String method, final Class arg1, final Class arg2, final Class arg3) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2, arg3 }, false, false, Arity.fixed(3), true);
    }
    
    @Deprecated
    public Callback getFastMethod(final String rubyName, final String method, final Class arg1, final Class arg2, final Class arg3) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2, arg3 }, false, false, Arity.fixed(3), true);
    }
    
    @Deprecated
    public Callback getSingletonMethod(final String method) {
        return new ReflectionCallback(this.type, method, ReflectionCallbackFactory.NULL_CLASS_ARRAY, false, true, Arity.noArguments(), false);
    }
    
    @Deprecated
    public Callback getFastSingletonMethod(final String method) {
        return new ReflectionCallback(this.type, method, ReflectionCallbackFactory.NULL_CLASS_ARRAY, false, true, Arity.noArguments(), true);
    }
    
    @Deprecated
    public Callback getSingletonMethod(final String method, final Class arg1) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1 }, false, true, Arity.singleArgument(), false);
    }
    
    @Deprecated
    public Callback getFastSingletonMethod(final String method, final Class arg1) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1 }, false, true, Arity.singleArgument(), true);
    }
    
    @Deprecated
    public Callback getSingletonMethod(final String method, final Class arg1, final Class arg2) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2 }, false, true, Arity.fixed(2), false);
    }
    
    @Deprecated
    public Callback getFastSingletonMethod(final String method, final Class arg1, final Class arg2) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2 }, false, true, Arity.fixed(2), true);
    }
    
    @Deprecated
    public Callback getSingletonMethod(final String method, final Class arg1, final Class arg2, final Class arg3) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2, arg3 }, false, true, Arity.fixed(3), false);
    }
    
    @Deprecated
    public Callback getFastSingletonMethod(final String method, final Class arg1, final Class arg2, final Class arg3) {
        return new ReflectionCallback(this.type, method, new Class[] { arg1, arg2, arg3 }, false, true, Arity.fixed(3), true);
    }
    
    @Deprecated
    public Callback getBlockMethod(final String method) {
        return new ReflectionCallback(this.type, method, new Class[] { IRubyObject.class, IRubyObject.class }, false, true, Arity.fixed(2), false);
    }
    
    @Deprecated
    public CompiledBlockCallback getBlockCallback(final String method, final Object scriptObject) {
        try {
            final Method blockMethod = scriptObject.getClass().getMethod(method, ThreadContext.class, IRubyObject.class, IRubyObject.class);
            return new CompiledBlockCallback() {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final IRubyObject args, final Block block) {
                    try {
                        return (IRubyObject)blockMethod.invoke(scriptObject, context, self, args, block);
                    }
                    catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw new RuntimeException(ex2);
                    }
                    catch (InvocationTargetException ex3) {
                        final Throwable cause = ex3.getCause();
                        if (cause instanceof RuntimeException) {
                            throw (RuntimeException)cause;
                        }
                        if (cause instanceof Error) {
                            throw (Error)cause;
                        }
                        throw new RuntimeException(ex3);
                    }
                }
                
                public String getFile() {
                    return "(deprecated)";
                }
                
                public int getLine() {
                    return -1;
                }
            };
        }
        catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }
    
    @Deprecated
    public CompiledBlockCallback19 getBlockCallback19(final String method, final Object scriptObject) {
        try {
            final Method blockMethod = scriptObject.getClass().getMethod(method, ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class);
            return new CompiledBlockCallback19() {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
                    try {
                        return (IRubyObject)blockMethod.invoke(scriptObject, context, self, args, block);
                    }
                    catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw new RuntimeException(ex2);
                    }
                    catch (InvocationTargetException ex3) {
                        final Throwable cause = ex3.getCause();
                        if (cause instanceof RuntimeException) {
                            throw (RuntimeException)cause;
                        }
                        if (cause instanceof Error) {
                            throw (Error)cause;
                        }
                        throw new RuntimeException(ex3);
                    }
                }
                
                public String getFile() {
                    return "(deprecated)";
                }
                
                public int getLine() {
                    return -1;
                }
            };
        }
        catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }
    
    @Deprecated
    public Callback getOptSingletonMethod(final String method) {
        return new ReflectionCallback(this.type, method, new Class[] { IRubyObject[].class }, true, true, Arity.optional(), false);
    }
    
    @Deprecated
    public Callback getFastOptSingletonMethod(final String method) {
        return new ReflectionCallback(this.type, method, new Class[] { IRubyObject[].class }, true, true, Arity.optional(), true);
    }
    
    @Deprecated
    public Callback getOptMethod(final String method) {
        return new ReflectionCallback(this.type, method, new Class[] { IRubyObject[].class }, true, false, Arity.optional(), false);
    }
    
    @Deprecated
    public Callback getFastOptMethod(final String method) {
        return new ReflectionCallback(this.type, method, new Class[] { IRubyObject[].class }, true, false, Arity.optional(), true);
    }
    
    @Deprecated
    public Callback getFastOptMethod(final String rubyName, final String method) {
        return new ReflectionCallback(this.type, method, new Class[] { IRubyObject[].class }, true, false, Arity.optional(), true);
    }
}
