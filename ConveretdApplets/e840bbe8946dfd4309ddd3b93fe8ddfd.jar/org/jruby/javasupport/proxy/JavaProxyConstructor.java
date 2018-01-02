// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

import org.jruby.RubyProc;
import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.JavaObject;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.Arity;
import org.jruby.RubyObject;
import org.jruby.runtime.Block;
import org.jruby.RubyArray;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyFixnum;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import java.lang.reflect.InvocationTargetException;
import org.jruby.anno.JRubyMethod;
import org.jruby.Ruby;
import java.lang.reflect.Constructor;
import org.jruby.javasupport.ParameterTypes;

public class JavaProxyConstructor extends JavaProxyReflectionObject implements ParameterTypes
{
    private final Constructor<?> proxyConstructor;
    private final Class<?>[] apparentParameterTypes;
    private final JavaProxyClass declaringProxyClass;
    
    JavaProxyConstructor(final Ruby runtime, final JavaProxyClass pClass, final Constructor<?> constructor) {
        super(runtime, runtime.getJavaSupport().getJavaModule().fastGetClass("JavaProxyConstructor"));
        this.declaringProxyClass = pClass;
        this.proxyConstructor = constructor;
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        final int len = parameterTypes.length - 1;
        System.arraycopy(parameterTypes, 0, this.apparentParameterTypes = (Class<?>[])new Class[len], 0, len);
    }
    
    public Class<?>[] getParameterTypes() {
        return this.apparentParameterTypes;
    }
    
    public Class<?>[] getExceptionTypes() {
        return this.proxyConstructor.getExceptionTypes();
    }
    
    public boolean isVarArgs() {
        return this.proxyConstructor.isVarArgs();
    }
    
    @JRubyMethod(name = { "declaring_class" })
    public JavaProxyClass getDeclaringClass() {
        return this.declaringProxyClass;
    }
    
    public Object newInstance(final Object[] args, final JavaProxyInvocationHandler handler) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (args.length != this.apparentParameterTypes.length) {
            throw new IllegalArgumentException("wrong number of parameters");
        }
        final Object[] realArgs = new Object[args.length + 1];
        System.arraycopy(args, 0, realArgs, 0, args.length);
        realArgs[args.length] = handler;
        return this.proxyConstructor.newInstance(realArgs);
    }
    
    public static RubyClass createJavaProxyConstructorClass(final Ruby runtime, final RubyModule javaProxyModule) {
        final RubyClass result = javaProxyModule.defineClassUnder("JavaProxyConstructor", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        JavaProxyReflectionObject.registerRubyMethods(runtime, result);
        result.defineAnnotatedMethods(JavaProxyConstructor.class);
        return result;
    }
    
    @JRubyMethod
    public RubyFixnum arity() {
        return this.getRuntime().newFixnum(this.getParameterTypes().length);
    }
    
    public boolean equals(final Object other) {
        return other instanceof JavaProxyConstructor && this.proxyConstructor == ((JavaProxyConstructor)other).proxyConstructor;
    }
    
    public int hashCode() {
        return this.proxyConstructor.hashCode();
    }
    
    protected String nameOnInspection() {
        return this.getDeclaringClass().nameOnInspection();
    }
    
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
    
    @JRubyMethod
    public RubyArray argument_types() {
        return this.buildRubyArray(this.getParameterTypes());
    }
    
    @JRubyMethod(rest = true)
    public RubyObject new_instance2(final IRubyObject[] args, final Block unusedBlock) {
        Arity.checkArgumentCount(this.getRuntime(), args, 2, 2);
        final IRubyObject self = args[0];
        final Ruby runtime = self.getRuntime();
        final RubyArray constructor_args = (RubyArray)args[1];
        final Class<?>[] parameterTypes = this.getParameterTypes();
        final int count = (int)constructor_args.length().getLongValue();
        final Object[] converted = new Object[count];
        for (int i = 0; i < count; ++i) {
            final IRubyObject ith = constructor_args.aref(this.getRuntime().newFixnum(i));
            converted[i] = ith.toJava(parameterTypes[i]);
        }
        final JavaProxyInvocationHandler handler = new JavaProxyInvocationHandler() {
            public IRubyObject getOrig() {
                return self;
            }
            
            public Object invoke(final Object proxy, final JavaProxyMethod m, final Object[] nargs) throws Throwable {
                final String name = m.getName();
                final DynamicMethod method = self.getMetaClass().searchMethod(name);
                final int v = method.getArity().getValue();
                final IRubyObject[] newArgs = new IRubyObject[nargs.length];
                int i = nargs.length;
                while (--i >= 0) {
                    newArgs[i] = JavaUtil.convertJavaToUsableRubyObject(runtime, nargs[i]);
                }
                if (v < 0 || v == newArgs.length) {
                    return method.call(runtime.getCurrentContext(), self, self.getMetaClass(), name, newArgs).toJava(m.getReturnType());
                }
                if (m.hasSuperImplementation()) {
                    final RubyClass superClass = self.getMetaClass().getSuperClass();
                    return RuntimeHelpers.invokeAs(runtime.getCurrentContext(), superClass, self, name, newArgs, Block.NULL_BLOCK).toJava(m.getReturnType());
                }
                throw runtime.newArgumentError(newArgs.length, v);
            }
        };
        try {
            return JavaObject.wrap(this.getRuntime(), this.newInstance(converted, handler));
        }
        catch (Exception e) {
            final RaiseException ex = this.getRuntime().newArgumentError("Constructor invocation failed: " + e.getMessage());
            ex.initCause(e);
            throw ex;
        }
    }
    
    public JavaObject newInstance(final IRubyObject self, final Object[] args) {
        final Ruby runtime = self.getRuntime();
        final JavaProxyInvocationHandler handler = new JavaProxyInvocationHandler() {
            public IRubyObject getOrig() {
                return self;
            }
            
            public Object invoke(final Object proxy, final JavaProxyMethod m, final Object[] nargs) throws Throwable {
                final String name = m.getName();
                final DynamicMethod method = self.getMetaClass().searchMethod(name);
                final int v = method.getArity().getValue();
                final IRubyObject[] newArgs = new IRubyObject[nargs.length];
                int i = nargs.length;
                while (--i >= 0) {
                    newArgs[i] = JavaUtil.convertJavaToUsableRubyObject(runtime, nargs[i]);
                }
                IRubyObject result = null;
                if (v < 0 || v == newArgs.length) {
                    result = method.call(runtime.getCurrentContext(), self, self.getMetaClass(), name, newArgs);
                }
                else {
                    if (!m.hasSuperImplementation()) {
                        throw runtime.newArgumentError(newArgs.length, v);
                    }
                    final RubyClass superClass = self.getMetaClass().getSuperClass();
                    result = RuntimeHelpers.invokeAs(runtime.getCurrentContext(), superClass, self, name, newArgs, Block.NULL_BLOCK);
                }
                if (m.getReturnType() == Void.TYPE) {
                    return null;
                }
                return result.toJava(m.getReturnType());
            }
        };
        try {
            return JavaObject.wrap(this.getRuntime(), this.newInstance(args, handler));
        }
        catch (Throwable t) {
            while (t.getCause() != null) {
                t = t.getCause();
            }
            final RaiseException ex = this.getRuntime().newArgumentError("Constructor invocation failed: " + t.getMessage());
            ex.initCause(t);
            throw ex;
        }
    }
    
    @JRubyMethod(required = 1, optional = 1)
    public RubyObject new_instance(final IRubyObject[] args, final Block block) {
        int size = Arity.checkArgumentCount(this.getRuntime(), args, 1, 2) - 1;
        RubyProc proc;
        if (args[size] instanceof RubyProc) {
            proc = (RubyProc)args[size];
        }
        else {
            proc = this.getRuntime().newProc(Block.Type.PROC, block);
            ++size;
        }
        final RubyArray constructor_args = (RubyArray)args[0];
        final Class<?>[] parameterTypes = this.getParameterTypes();
        final int count = (int)constructor_args.length().getLongValue();
        final Object[] converted = new Object[count];
        for (int i = 0; i < count; ++i) {
            final IRubyObject ith = constructor_args.aref(this.getRuntime().newFixnum(i));
            converted[i] = ith.toJava(parameterTypes[i]);
        }
        final JavaProxyInvocationHandler handler = new JavaProxyInvocationHandler() {
            final /* synthetic */ IRubyObject val$recv;
            
            public IRubyObject getOrig() {
                return null;
            }
            
            public Object invoke(final Object proxy, final JavaProxyMethod method, final Object[] nargs) throws Throwable {
                final int length = (nargs == null) ? 0 : nargs.length;
                final IRubyObject[] rubyArgs = new IRubyObject[length + 2];
                rubyArgs[0] = JavaObject.wrap(this.val$recv.getRuntime(), proxy);
                rubyArgs[1] = method;
                for (int i = 0; i < length; ++i) {
                    rubyArgs[i + 2] = JavaUtil.convertJavaToRuby(JavaProxyConstructor.this.getRuntime(), nargs[i]);
                }
                final IRubyObject call_result = proc.call(JavaProxyConstructor.this.getRuntime().getCurrentContext(), rubyArgs);
                final Object converted_result = call_result.toJava(method.getReturnType());
                return converted_result;
            }
        };
        Object result;
        try {
            result = this.newInstance(converted, handler);
        }
        catch (Exception e) {
            final RaiseException ex = this.getRuntime().newArgumentError("Constructor invocation failed: " + e.getMessage());
            ex.initCause(e);
            throw ex;
        }
        return JavaObject.wrap(this.getRuntime(), result);
    }
}
