// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.java.proxies.JavaProxy;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyModule;
import org.jruby.RubySymbol;
import org.jruby.RubyString;
import org.jruby.javasupport.JavaClass;
import org.jruby.javasupport.Java;
import org.jruby.RubyArray;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.Ruby;
import org.jruby.util.unsafe.UnsafeFactory;
import org.jruby.RubyKernel;
import org.jruby.java.proxies.ConcreteJavaProxy;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class KernelJavaAddons
{
    @JRubyMethod(name = { "raise" }, optional = 3, frame = true, module = true, visibility = Visibility.PRIVATE, omit = true)
    public static IRubyObject rbRaise(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        ConcreteJavaProxy exception = null;
        if (args.length == 0 && runtime.getGlobalVariables().get("$!") instanceof ConcreteJavaProxy) {
            exception = (ConcreteJavaProxy)runtime.getGlobalVariables().get("$!");
        }
        else if (args.length == 1 && args[0] instanceof ConcreteJavaProxy) {
            exception = (ConcreteJavaProxy)args[0];
        }
        if (exception == null) {
            return RubyKernel.raise(context, recv, args, block);
        }
        final Object maybeThrowable = exception.getObject();
        if (maybeThrowable instanceof Throwable) {
            UnsafeFactory.getUnsafe().throwException((Throwable)maybeThrowable);
            return recv;
        }
        throw runtime.newTypeError("can't raise a non-Throwable Java object");
    }
    
    @JRubyMethod(backtrace = true)
    public static IRubyObject to_java(final ThreadContext context, final IRubyObject fromObject) {
        if (fromObject instanceof RubyArray) {
            return context.getRuntime().getJavaSupport().getObjectJavaClass().javaArrayFromRubyArray(context, fromObject);
        }
        return Java.getInstance(context.getRuntime(), fromObject.toJava(Object.class));
    }
    
    @JRubyMethod(backtrace = true)
    public static IRubyObject to_java(final ThreadContext context, final IRubyObject fromObject, final IRubyObject type) {
        if (type.isNil()) {
            return to_java(context, fromObject);
        }
        final Ruby runtime = context.getRuntime();
        final JavaClass targetType = getTargetType(context, runtime, type);
        if (fromObject instanceof RubyArray) {
            return targetType.javaArrayFromRubyArray(context, fromObject);
        }
        return Java.getInstance(runtime, fromObject.toJava(targetType.javaClass()));
    }
    
    @JRubyMethod(rest = true)
    public static IRubyObject java_signature(final IRubyObject recv, final IRubyObject[] args) {
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(rest = true)
    public static IRubyObject java_name(final IRubyObject recv, final IRubyObject[] args) {
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(rest = true)
    public static IRubyObject java_implements(final IRubyObject recv, final IRubyObject[] args) {
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(rest = true)
    public static IRubyObject java_annotation(final IRubyObject recv, final IRubyObject[] args) {
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(rest = true)
    public static IRubyObject java_require(final IRubyObject recv, final IRubyObject[] args) {
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(rest = true)
    public static IRubyObject java_package(final IRubyObject recv, final IRubyObject[] args) {
        return recv.getRuntime().getNil();
    }
    
    private static JavaClass getTargetType(final ThreadContext context, final Ruby runtime, final IRubyObject type) {
        JavaClass targetType;
        if (type instanceof RubyString || type instanceof RubySymbol) {
            targetType = runtime.getJavaSupport().getNameClassMap().get(type.asJavaString());
            if (targetType == null) {
                targetType = JavaClass.forNameVerbose(runtime, type.asJavaString());
            }
        }
        else if (type instanceof RubyModule && type.respondsTo("java_class")) {
            targetType = (JavaClass)RuntimeHelpers.invoke(context, type, "java_class");
        }
        else {
            if (!(type instanceof JavaProxy)) {
                throw runtime.newTypeError("unable to convert to type: " + type);
            }
            if (!(((JavaProxy)type).getObject() instanceof Class)) {
                throw runtime.newTypeError("not a valid target type: " + type);
            }
            targetType = JavaClass.get(runtime, (Class<?>)((JavaProxy)type).getObject());
        }
        return targetType;
    }
}
