// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import org.jruby.RubyString;
import org.jruby.RubyBoolean;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyArray;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyFixnum;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import org.jruby.RubyClass;
import org.jruby.Ruby;

public abstract class JavaCallable extends JavaAccessibleObject implements ParameterTypes
{
    protected final Class<?>[] parameterTypes;
    
    public JavaCallable(final Ruby runtime, final RubyClass rubyClass, final Class<?>[] parameterTypes) {
        super(runtime, rubyClass);
        this.parameterTypes = parameterTypes;
    }
    
    public static void registerRubyMethods(final Ruby runtime, final RubyClass result) {
        result.defineAnnotatedMethods(JavaCallable.class);
    }
    
    public abstract int getArity();
    
    public abstract int getModifiers();
    
    public abstract Class<?>[] getParameterTypes();
    
    public abstract Class<?>[] getExceptionTypes();
    
    public abstract Type[] getGenericExceptionTypes();
    
    public abstract Type[] getGenericParameterTypes();
    
    public abstract Annotation[][] getParameterAnnotations();
    
    public abstract boolean isVarArgs();
    
    public abstract String toGenericString();
    
    protected abstract String nameOnInspection();
    
    @JRubyMethod
    public final RubyFixnum arity() {
        return this.getRuntime().newFixnum(this.getArity());
    }
    
    @JRubyMethod
    public final RubyArray argument_types() {
        return JavaClass.getRubyArray(this.getRuntime(), this.getParameterTypes());
    }
    
    @JRubyMethod
    public IRubyObject parameter_types() {
        return JavaClass.getRubyArray(this.getRuntime(), this.getParameterTypes());
    }
    
    @JRubyMethod
    public IRubyObject exception_types() {
        return JavaClass.getRubyArray(this.getRuntime(), this.getExceptionTypes());
    }
    
    @JRubyMethod
    public IRubyObject generic_parameter_types() {
        return Java.getInstance(this.getRuntime(), this.getGenericParameterTypes());
    }
    
    @JRubyMethod
    public IRubyObject generic_exception_types() {
        return Java.getInstance(this.getRuntime(), this.getGenericExceptionTypes());
    }
    
    @JRubyMethod
    public IRubyObject parameter_annotations() {
        return Java.getInstance(this.getRuntime(), this.getParameterAnnotations());
    }
    
    @JRubyMethod(name = { "varargs?" })
    public RubyBoolean varargs_p() {
        return this.getRuntime().newBoolean(this.isVarArgs());
    }
    
    @JRubyMethod
    public RubyString to_generic_string() {
        return this.getRuntime().newString(this.toGenericString());
    }
    
    @JRubyMethod
    public IRubyObject inspect() {
        final StringBuilder result = new StringBuilder();
        result.append(this.nameOnInspection());
        final Class<?>[] parameterTypes = this.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; ++i) {
            result.append(parameterTypes[i].getName());
            if (i < parameterTypes.length - 1) {
                result.append(',');
            }
        }
        result.append(")>");
        return this.getRuntime().newString(result.toString());
    }
    
    @JRubyMethod(name = { "public?" })
    public RubyBoolean public_p() {
        return RubyBoolean.newBoolean(this.getRuntime(), Modifier.isPublic(this.getModifiers()));
    }
    
    protected void checkArity(final int length) {
        if (length != this.getArity()) {
            throw this.getRuntime().newArgumentError(length, this.getArity());
        }
    }
    
    protected static String dumpArgTypes(final Object[] arguments) {
        final StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < arguments.length; ++i) {
            if (i > 0) {
                str.append(",");
            }
            if (arguments[i] == null) {
                str.append("null");
            }
            else {
                str.append(arguments[i].getClass().getName());
            }
        }
        str.append("]");
        return str.toString();
    }
    
    protected IRubyObject handleThrowable(final Throwable t, final Member target) {
        this.getRuntime().getJavaSupport().handleNativeException(t, target);
        return this.getRuntime().getNil();
    }
    
    protected IRubyObject handleInvocationTargetEx(final InvocationTargetException ite, final Member target) {
        this.getRuntime().getJavaSupport().handleNativeException(ite.getTargetException(), target);
        return this.getRuntime().getNil();
    }
}
