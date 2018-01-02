// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import java.lang.reflect.AccessibleObject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import java.lang.reflect.Constructor;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Java::JavaConstructor" })
public class JavaConstructor extends JavaCallable
{
    private final Constructor<?> constructor;
    private final JavaUtil.JavaConverter objectConverter;
    
    public Object getValue() {
        return this.constructor;
    }
    
    public static RubyClass createJavaConstructorClass(final Ruby runtime, final RubyModule javaModule) {
        final RubyClass result = javaModule.defineClassUnder("JavaConstructor", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        JavaAccessibleObject.registerRubyMethods(runtime, result);
        JavaCallable.registerRubyMethods(runtime, result);
        result.defineAnnotatedMethods(JavaConstructor.class);
        return result;
    }
    
    public JavaConstructor(final Ruby runtime, final Constructor<?> constructor) {
        super(runtime, runtime.getJavaSupport().getJavaConstructorClass(), constructor.getParameterTypes());
        this.constructor = constructor;
        this.objectConverter = JavaUtil.getJavaConverter(constructor.getDeclaringClass());
    }
    
    public static JavaConstructor create(final Ruby runtime, final Constructor<?> constructor) {
        return new JavaConstructor(runtime, constructor);
    }
    
    public static JavaConstructor getMatchingConstructor(final Ruby runtime, final Class<?> javaClass, final Class<?>[] argumentTypes) {
        try {
            return create(runtime, javaClass.getConstructor(argumentTypes));
        }
        catch (NoSuchMethodException e) {
            for (final Constructor<?> ctor : javaClass.getConstructors()) {
                final Class<?>[] targetTypes = ctor.getParameterTypes();
                Label_0144: {
                    if (targetTypes.length == argumentTypes.length) {
                        if (targetTypes.length == 0 && argumentTypes.length == 0) {
                            return create(runtime, ctor);
                        }
                        boolean found = true;
                        for (int i = 0; i < argumentTypes.length; ++i) {
                            if (i >= targetTypes.length) {
                                found = false;
                            }
                            if (!targetTypes[i].isAssignableFrom(argumentTypes[i])) {
                                found = false;
                                break Label_0144;
                            }
                            found = true;
                        }
                        if (found) {
                            return create(runtime, ctor);
                        }
                    }
                }
            }
            return null;
        }
    }
    
    public boolean equals(final Object other) {
        return other instanceof JavaConstructor && this.constructor == ((JavaConstructor)other).constructor;
    }
    
    public int hashCode() {
        return this.constructor.hashCode();
    }
    
    public int getArity() {
        return this.parameterTypes.length;
    }
    
    protected String nameOnInspection() {
        return this.getType().toString();
    }
    
    public Class<?>[] getParameterTypes() {
        return this.parameterTypes;
    }
    
    public Class<?>[] getExceptionTypes() {
        return this.constructor.getExceptionTypes();
    }
    
    public Type[] getGenericParameterTypes() {
        return this.constructor.getGenericParameterTypes();
    }
    
    public Type[] getGenericExceptionTypes() {
        return this.constructor.getGenericExceptionTypes();
    }
    
    public Annotation[][] getParameterAnnotations() {
        return this.constructor.getParameterAnnotations();
    }
    
    public boolean isVarArgs() {
        return this.constructor.isVarArgs();
    }
    
    public int getModifiers() {
        return this.constructor.getModifiers();
    }
    
    public String toGenericString() {
        return this.constructor.toGenericString();
    }
    
    protected AccessibleObject accessibleObject() {
        return this.constructor;
    }
    
    @JRubyMethod
    public IRubyObject type_parameters() {
        return Java.getInstance(this.getRuntime(), this.constructor.getTypeParameters());
    }
    
    @JRubyMethod
    public IRubyObject return_type() {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(rest = true)
    public IRubyObject new_instance(final IRubyObject[] args) {
        final int length = args.length;
        final Class<?>[] types = this.parameterTypes;
        if (length != types.length) {
            throw this.getRuntime().newArgumentError(length, types.length);
        }
        final Object[] constructorArguments = new Object[length];
        int i = length;
        while (--i >= 0) {
            constructorArguments[i] = args[i].toJava(types[i]);
        }
        try {
            final Object result = this.constructor.newInstance(constructorArguments);
            return JavaObject.wrap(this.getRuntime(), result);
        }
        catch (IllegalArgumentException iae) {
            throw this.getRuntime().newTypeError("expected " + this.argument_types().inspect() + ", got [" + constructorArguments[0].getClass().getName() + ", ...]");
        }
        catch (IllegalAccessException iae2) {
            throw this.getRuntime().newTypeError("illegal access");
        }
        catch (InvocationTargetException ite) {
            this.getRuntime().getJavaSupport().handleNativeException(ite.getTargetException(), this.constructor);
            assert false;
            return null;
        }
        catch (InstantiationException ie) {
            throw this.getRuntime().newTypeError("can't make instance of " + this.constructor.getDeclaringClass().getName());
        }
    }
    
    public IRubyObject new_instance(final Object[] arguments) {
        this.checkArity(arguments.length);
        try {
            final Object result = this.constructor.newInstance(arguments);
            return JavaObject.wrap(this.getRuntime(), result);
        }
        catch (IllegalArgumentException iae) {
            throw this.getRuntime().newTypeError("expected " + this.argument_types().inspect() + ", got [" + arguments[0].getClass().getName() + ", ...]");
        }
        catch (IllegalAccessException iae2) {
            throw this.getRuntime().newTypeError("illegal access");
        }
        catch (InvocationTargetException ite) {
            this.getRuntime().getJavaSupport().handleNativeException(ite.getTargetException(), this.constructor);
            assert false;
            return null;
        }
        catch (InstantiationException ie) {
            throw this.getRuntime().newTypeError("can't make instance of " + this.constructor.getDeclaringClass().getName());
        }
    }
    
    public Object newInstanceDirect(final Object... arguments) {
        this.checkArity(arguments.length);
        try {
            return this.constructor.newInstance(arguments);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(iae, arguments);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, this.constructor);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, this.constructor);
        }
    }
    
    public Object newInstanceDirect() {
        this.checkArity(0);
        try {
            return this.constructor.newInstance(new Object[0]);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(iae, new Object[0]);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, this.constructor);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, this.constructor);
        }
    }
    
    public Object newInstanceDirect(final Object arg0) {
        this.checkArity(1);
        try {
            return this.constructor.newInstance(arg0);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(iae, arg0);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, this.constructor);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, this.constructor);
        }
    }
    
    public Object newInstanceDirect(final Object arg0, final Object arg1) {
        this.checkArity(2);
        try {
            return this.constructor.newInstance(arg0, arg1);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(iae, arg0, arg1);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, this.constructor);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, this.constructor);
        }
    }
    
    public Object newInstanceDirect(final Object arg0, final Object arg1, final Object arg2) {
        this.checkArity(3);
        try {
            return this.constructor.newInstance(arg0, arg1, arg2);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(iae, arg0, arg1, arg2);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, this.constructor);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, this.constructor);
        }
    }
    
    public Object newInstanceDirect(final Object arg0, final Object arg1, final Object arg2, final Object arg3) {
        this.checkArity(4);
        try {
            return this.constructor.newInstance(arg0, arg1, arg2, arg3);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(iae, arg0, arg1, arg2, arg3);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, this.constructor);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, this.constructor);
        }
    }
    
    private IRubyObject handleIllegalAccessEx(final IllegalAccessException iae) {
        throw this.getRuntime().newTypeError("illegal access on constructor for type " + this.constructor.getDeclaringClass().getSimpleName() + ": " + iae.getMessage());
    }
    
    private IRubyObject handlelIllegalArgumentEx(final IllegalArgumentException iae, final Object... arguments) {
        throw this.getRuntime().newTypeError("for constructor of type " + this.constructor.getDeclaringClass().getSimpleName() + " expected " + this.argument_types().inspect() + "; got: " + JavaCallable.dumpArgTypes(arguments) + "; error: " + iae.getMessage());
    }
}
