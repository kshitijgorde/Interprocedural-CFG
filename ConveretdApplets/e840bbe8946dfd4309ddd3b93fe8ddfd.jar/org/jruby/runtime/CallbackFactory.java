// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.util.SafePropertyAccessor;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.callback.InvocationCallbackFactory;
import org.jruby.runtime.callback.DumpingInvocationCallbackFactory;
import org.jruby.runtime.callback.ReflectionCallbackFactory;
import org.jruby.Ruby;
import org.jruby.runtime.callback.Callback;

public abstract class CallbackFactory
{
    @Deprecated
    public static final Class[] NULL_CLASS_ARRAY;
    private static final boolean reflection;
    private static final boolean dumping;
    
    public abstract Callback getMethod(final String p0);
    
    public abstract Callback getFastMethod(final String p0);
    
    public abstract Callback getMethod(final String p0, final Class p1);
    
    public abstract Callback getFastMethod(final String p0, final Class p1);
    
    public abstract Callback getMethod(final String p0, final Class p1, final Class p2);
    
    public abstract Callback getFastMethod(final String p0, final Class p1, final Class p2);
    
    public abstract Callback getMethod(final String p0, final Class p1, final Class p2, final Class p3);
    
    public abstract Callback getFastMethod(final String p0, final Class p1, final Class p2, final Class p3);
    
    public abstract Callback getSingletonMethod(final String p0);
    
    public abstract Callback getFastSingletonMethod(final String p0);
    
    public abstract Callback getSingletonMethod(final String p0, final Class p1);
    
    public abstract Callback getFastSingletonMethod(final String p0, final Class p1);
    
    public abstract Callback getSingletonMethod(final String p0, final Class p1, final Class p2);
    
    public abstract Callback getFastSingletonMethod(final String p0, final Class p1, final Class p2);
    
    public abstract Callback getSingletonMethod(final String p0, final Class p1, final Class p2, final Class p3);
    
    public abstract Callback getFastSingletonMethod(final String p0, final Class p1, final Class p2, final Class p3);
    
    @Deprecated
    public abstract Callback getBlockMethod(final String p0);
    
    @Deprecated
    public abstract CompiledBlockCallback getBlockCallback(final String p0, final Object p1);
    
    @Deprecated
    public abstract CompiledBlockCallback19 getBlockCallback19(final String p0, final Object p1);
    
    public abstract Callback getOptSingletonMethod(final String p0);
    
    public abstract Callback getFastOptSingletonMethod(final String p0);
    
    public abstract Callback getOptMethod(final String p0);
    
    public abstract Callback getFastOptMethod(final String p0);
    
    public static CallbackFactory createFactory(final Ruby runtime, final Class type) {
        return createFactory(runtime, type, runtime.getJRubyClassLoader());
    }
    
    public static CallbackFactory createFactory(final Ruby runtime, final Class type, final ClassLoader classLoader) {
        if (CallbackFactory.reflection) {
            return new ReflectionCallbackFactory(type);
        }
        if (CallbackFactory.dumping) {
            return new DumpingInvocationCallbackFactory(runtime, type, classLoader);
        }
        return new InvocationCallbackFactory(runtime, type, classLoader);
    }
    
    static {
        NULL_CLASS_ARRAY = new Class[0];
        boolean reflection_ = false;
        boolean dumping_ = false;
        if (Ruby.isSecurityRestricted()) {
            reflection_ = true;
        }
        else {
            reflection_ = RubyInstanceConfig.REFLECTED_HANDLES;
            if (SafePropertyAccessor.getProperty("jruby.dump_invocations") != null) {
                dumping_ = true;
            }
        }
        reflection = reflection_;
        dumping = dumping_;
    }
}
