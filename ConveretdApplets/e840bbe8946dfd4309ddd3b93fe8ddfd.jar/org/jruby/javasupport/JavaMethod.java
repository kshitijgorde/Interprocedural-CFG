// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.RubyInstanceConfig;
import java.lang.reflect.AccessibleObject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.proxy.JavaProxyMethod;
import org.jruby.javasupport.proxy.JavaProxyClass;
import org.jruby.javasupport.proxy.InternalJavaProxy;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyBoolean;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyString;
import org.jruby.util.CodegenUtils;
import java.lang.reflect.Modifier;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.compiler.util.HandleFactory;
import java.lang.reflect.Method;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Java::JavaMethod" })
public class JavaMethod extends JavaCallable
{
    private static final boolean USE_HANDLES;
    private static final boolean HANDLE_DEBUG = false;
    private final Method method;
    private final Class boxedReturnType;
    private final boolean isFinal;
    private final HandleFactory.Handle handle;
    private final JavaUtil.JavaConverter returnConverter;
    
    public Object getValue() {
        return this.method;
    }
    
    public static RubyClass createJavaMethodClass(final Ruby runtime, final RubyModule javaModule) {
        final RubyClass result = javaModule.defineClassUnder("JavaMethod", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        JavaAccessibleObject.registerRubyMethods(runtime, result);
        JavaCallable.registerRubyMethods(runtime, result);
        result.defineAnnotatedMethods(JavaMethod.class);
        return result;
    }
    
    public JavaMethod(final Ruby runtime, Method method) {
        super(runtime, runtime.getJavaSupport().getJavaMethodClass(), method.getParameterTypes());
        this.method = method;
        this.isFinal = Modifier.isFinal(method.getModifiers());
        if (method.getReturnType().isPrimitive() && method.getReturnType() != Void.TYPE) {
            this.boxedReturnType = CodegenUtils.getBoxType(method.getReturnType());
        }
        else {
            this.boxedReturnType = method.getReturnType();
        }
        boolean methodIsPublic = Modifier.isPublic(method.getModifiers());
        boolean classIsPublic = Modifier.isPublic(method.getDeclaringClass().getModifiers());
        if (methodIsPublic && !classIsPublic) {
            Method newMethod = method;
        Label_0222:
            for (Class newClass = method.getDeclaringClass(); newClass != null; newClass = newClass.getSuperclass(), newMethod = null) {
                try {
                    newMethod = newClass.getMethod(method.getName(), (Class[])method.getParameterTypes());
                    if (Modifier.isPublic(newMethod.getDeclaringClass().getModifiers())) {
                        break;
                    }
                }
                catch (NoSuchMethodException ex) {}
                final Class[] arr$ = newClass.getInterfaces();
                final int len$ = arr$.length;
                int i$ = 0;
                while (i$ < len$) {
                    final Class ifc = arr$[i$];
                    try {
                        newMethod = ifc.getMethod(method.getName(), (Class[])method.getParameterTypes());
                        break Label_0222;
                    }
                    catch (NoSuchMethodException nsme) {
                        ++i$;
                        continue;
                    }
                    break;
                }
            }
            if (newMethod != null) {
                method = newMethod;
                methodIsPublic = Modifier.isPublic(method.getModifiers());
                classIsPublic = Modifier.isPublic(method.getDeclaringClass().getModifiers());
            }
        }
        HandleFactory.Handle tmpHandle = null;
        try {
            if (JavaMethod.USE_HANDLES && methodIsPublic && classIsPublic && runtime.getJRubyClassLoader().loadClass(method.getDeclaringClass().getCanonicalName()) == method.getDeclaringClass()) {
                tmpHandle = HandleFactory.createHandle(runtime.getJRubyClassLoader(), method);
            }
            else {
                tmpHandle = null;
            }
        }
        catch (ClassNotFoundException cnfe) {
            tmpHandle = null;
        }
        if (tmpHandle == null) {}
        this.handle = tmpHandle;
        if (JavaClass.CAN_SET_ACCESSIBLE) {
            try {
                if (methodIsPublic && !Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
                    this.accessibleObject().setAccessible(true);
                }
            }
            catch (SecurityException se) {
                runtime.getWarnings().warn("failed to setAccessible: " + this.accessibleObject() + ", exception follows: " + se.getMessage());
            }
        }
        this.returnConverter = JavaUtil.getJavaConverter(method.getReturnType());
    }
    
    public static JavaMethod create(final Ruby runtime, final Method method) {
        return new JavaMethod(runtime, method);
    }
    
    public static JavaMethod create(final Ruby runtime, final Class<?> javaClass, final String methodName, final Class<?>[] argumentTypes) {
        try {
            final Method method = javaClass.getMethod(methodName, argumentTypes);
            return create(runtime, method);
        }
        catch (NoSuchMethodException e) {
            throw runtime.newNameError("undefined method '" + methodName + "' for class '" + javaClass.getName() + "'", methodName);
        }
    }
    
    public static JavaMethod createDeclared(final Ruby runtime, final Class<?> javaClass, final String methodName, final Class<?>[] argumentTypes) {
        try {
            return create(runtime, javaClass.getDeclaredMethod(methodName, argumentTypes));
        }
        catch (NoSuchMethodException e) {
            throw runtime.newNameError("undefined method '" + methodName + "' for class '" + javaClass.getName() + "'", methodName);
        }
    }
    
    public static JavaMethod getMatchingDeclaredMethod(final Ruby runtime, final Class<?> javaClass, final String methodName, final Class<?>[] argumentTypes) {
        try {
            return create(runtime, javaClass.getDeclaredMethod(methodName, argumentTypes));
        }
        catch (NoSuchMethodException e) {
            for (final Method method : javaClass.getDeclaredMethods()) {
                Label_0127: {
                    if (method.getName().equals(methodName)) {
                        final Class<?>[] targetTypes = method.getParameterTypes();
                        if (targetTypes.length == 0 && argumentTypes.length == 0) {
                            return create(runtime, method);
                        }
                        for (int i = 0; i < argumentTypes.length; ++i) {
                            if (i >= targetTypes.length) {
                                break Label_0127;
                            }
                            if (!targetTypes[i].isAssignableFrom(argumentTypes[i])) {
                                break Label_0127;
                            }
                        }
                        return create(runtime, method);
                    }
                }
            }
            return null;
        }
    }
    
    public boolean equals(final Object other) {
        return other instanceof JavaMethod && this.method == ((JavaMethod)other).method;
    }
    
    public int hashCode() {
        return this.method.hashCode();
    }
    
    @JRubyMethod
    public RubyString name() {
        return this.getRuntime().newString(this.method.getName());
    }
    
    public int getArity() {
        return this.parameterTypes.length;
    }
    
    @JRubyMethod(name = { "public?" })
    public RubyBoolean public_p() {
        return this.getRuntime().newBoolean(Modifier.isPublic(this.method.getModifiers()));
    }
    
    @JRubyMethod(name = { "final?" })
    public RubyBoolean final_p() {
        return this.getRuntime().newBoolean(Modifier.isFinal(this.method.getModifiers()));
    }
    
    @JRubyMethod(rest = true)
    public IRubyObject invoke(final IRubyObject[] args) {
        this.checkArity(args.length - 1);
        final Object[] arguments = new Object[args.length - 1];
        this.convertArguments(args, arguments, 1);
        final IRubyObject invokee = args[0];
        if (invokee.isNil()) {
            return this.invokeWithExceptionHandling(this.method, null, arguments);
        }
        Object javaInvokee = null;
        if (!this.isStatic()) {
            javaInvokee = JavaUtil.unwrapJavaValue(this.getRuntime(), invokee, "invokee not a java object");
            if (!this.method.getDeclaringClass().isInstance(javaInvokee)) {
                throw this.getRuntime().newTypeError("invokee not instance of method's class (got" + javaInvokee.getClass().getName() + " wanted " + this.method.getDeclaringClass().getName() + ")");
            }
            if (javaInvokee instanceof InternalJavaProxy && !Modifier.isFinal(this.method.getModifiers())) {
                final JavaProxyClass jpc = ((InternalJavaProxy)javaInvokee).___getProxyClass();
                final JavaProxyMethod jpm;
                if ((jpm = jpc.getMethod(this.method.getName(), this.parameterTypes)) != null && jpm.hasSuperImplementation()) {
                    return this.invokeWithExceptionHandling(jpm.getSuperMethod(), javaInvokee, arguments);
                }
            }
        }
        return this.invokeWithExceptionHandling(this.method, javaInvokee, arguments);
    }
    
    @JRubyMethod(rest = true)
    public IRubyObject invoke_static(final IRubyObject[] args) {
        this.checkArity(args.length);
        final Object[] arguments = new Object[args.length];
        System.arraycopy(args, 0, arguments, 0, arguments.length);
        this.convertArguments(args, arguments, 0);
        return this.invokeWithExceptionHandling(this.method, null, arguments);
    }
    
    @JRubyMethod
    public IRubyObject return_type() {
        final Class<?> klass = this.method.getReturnType();
        if (klass.equals(Void.TYPE)) {
            return this.getRuntime().getNil();
        }
        return JavaClass.get(this.getRuntime(), klass);
    }
    
    @JRubyMethod
    public IRubyObject type_parameters() {
        return Java.getInstance(this.getRuntime(), this.method.getTypeParameters());
    }
    
    public IRubyObject invokeDirect(final Object javaInvokee, final Object[] args) {
        this.checkArity(args.length);
        this.checkInstanceof(javaInvokee);
        if (this.mightBeProxy(javaInvokee)) {
            return this.tryProxyInvocation(javaInvokee, args);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, args);
    }
    
    public IRubyObject invokeDirect(final Object javaInvokee) {
        assert this.method.getDeclaringClass().isInstance(javaInvokee);
        this.checkArity(0);
        if (this.mightBeProxy(javaInvokee)) {
            return this.tryProxyInvocation(javaInvokee);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee);
    }
    
    public IRubyObject invokeDirect(final Object javaInvokee, final Object arg0) {
        assert this.method.getDeclaringClass().isInstance(javaInvokee);
        this.checkArity(1);
        if (this.mightBeProxy(javaInvokee)) {
            return this.tryProxyInvocation(javaInvokee, arg0);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, arg0);
    }
    
    public IRubyObject invokeDirect(final Object javaInvokee, final Object arg0, final Object arg1) {
        assert this.method.getDeclaringClass().isInstance(javaInvokee);
        this.checkArity(2);
        if (this.mightBeProxy(javaInvokee)) {
            return this.tryProxyInvocation(javaInvokee, arg0, arg1);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, arg0, arg1);
    }
    
    public IRubyObject invokeDirect(final Object javaInvokee, final Object arg0, final Object arg1, final Object arg2) {
        assert this.method.getDeclaringClass().isInstance(javaInvokee);
        this.checkArity(3);
        if (this.mightBeProxy(javaInvokee)) {
            return this.tryProxyInvocation(javaInvokee, arg0, arg1, arg2);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, arg0, arg1, arg2);
    }
    
    public IRubyObject invokeDirect(final Object javaInvokee, final Object arg0, final Object arg1, final Object arg2, final Object arg3) {
        assert this.method.getDeclaringClass().isInstance(javaInvokee);
        this.checkArity(4);
        if (this.mightBeProxy(javaInvokee)) {
            return this.tryProxyInvocation(javaInvokee, arg0, arg1, arg2, arg3);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, arg0, arg1, arg2, arg3);
    }
    
    public IRubyObject invokeStaticDirect(final Object[] args) {
        this.checkArity(args.length);
        return this.invokeDirectWithExceptionHandling(this.method, null, args);
    }
    
    public IRubyObject invokeStaticDirect() {
        this.checkArity(0);
        return this.invokeDirectWithExceptionHandling(this.method, null);
    }
    
    public IRubyObject invokeStaticDirect(final Object arg0) {
        this.checkArity(1);
        return this.invokeDirectWithExceptionHandling(this.method, null, arg0);
    }
    
    public IRubyObject invokeStaticDirect(final Object arg0, final Object arg1) {
        this.checkArity(2);
        return this.invokeDirectWithExceptionHandling(this.method, null, arg0, arg1);
    }
    
    public IRubyObject invokeStaticDirect(final Object arg0, final Object arg1, final Object arg2) {
        this.checkArity(3);
        return this.invokeDirectWithExceptionHandling(this.method, null, arg0, arg1, arg2);
    }
    
    public IRubyObject invokeStaticDirect(final Object arg0, final Object arg1, final Object arg2, final Object arg3) {
        this.checkArity(4);
        return this.invokeDirectWithExceptionHandling(this.method, null, arg0, arg1, arg2, arg3);
    }
    
    private void checkInstanceof(final Object javaInvokee) throws RaiseException {
        if (!this.method.getDeclaringClass().isInstance(javaInvokee)) {
            throw this.getRuntime().newTypeError("invokee not instance of method's class (got" + javaInvokee.getClass().getName() + " wanted " + this.method.getDeclaringClass().getName() + ")");
        }
    }
    
    private IRubyObject invokeWithExceptionHandling(final Method method, final Object javaInvokee, final Object[] arguments) {
        try {
            final Object result = (this.handle != null) ? this.handle.invoke(javaInvokee, arguments) : method.invoke(javaInvokee, arguments);
            return this.returnConverter.convert(this.getRuntime(), result);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(method, iae, arguments);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(method, iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, method);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, method);
        }
    }
    
    private IRubyObject invokeDirectSuperWithExceptionHandling(final Method method, final Object javaInvokee, final Object... arguments) {
        try {
            final Object result = method.invoke(javaInvokee, arguments);
            return this.convertReturn(result);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(method, iae, arguments);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(method, iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, method);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, method);
        }
    }
    
    private IRubyObject invokeDirectWithExceptionHandling(final Method method, final Object javaInvokee, final Object[] arguments) {
        try {
            final Object result = (this.handle != null) ? this.handle.invoke(javaInvokee, arguments) : method.invoke(javaInvokee, arguments);
            return this.convertReturn(result);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(method, iae, arguments);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(method, iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, method);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, method);
        }
    }
    
    private IRubyObject invokeDirectWithExceptionHandling(final Method method, final Object javaInvokee) {
        try {
            final Object result = (this.handle != null) ? this.handle.invoke(javaInvokee) : method.invoke(javaInvokee, new Object[0]);
            return this.convertReturn(result);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(method, iae, new Object[0]);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(method, iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, method);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, method);
        }
    }
    
    private IRubyObject invokeDirectWithExceptionHandling(final Method method, final Object javaInvokee, final Object arg0) {
        try {
            final Object result = (this.handle != null) ? this.handle.invoke(javaInvokee, arg0) : method.invoke(javaInvokee, arg0);
            return this.convertReturn(result);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(method, iae, arg0);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(method, iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, method);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, method);
        }
    }
    
    private IRubyObject invokeDirectWithExceptionHandling(final Method method, final Object javaInvokee, final Object arg0, final Object arg1) {
        try {
            final Object result = (this.handle != null) ? this.handle.invoke(javaInvokee, arg0, arg1) : method.invoke(javaInvokee, arg0, arg1);
            return this.convertReturn(result);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(method, iae, arg0, arg1);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(method, iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, method);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, method);
        }
    }
    
    private IRubyObject invokeDirectWithExceptionHandling(final Method method, final Object javaInvokee, final Object arg0, final Object arg1, final Object arg2) {
        try {
            final Object result = (this.handle != null) ? this.handle.invoke(javaInvokee, arg0, arg1, arg2) : method.invoke(javaInvokee, arg0, arg1, arg2);
            return this.convertReturn(result);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(method, iae, arg0, arg1, arg2);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(method, iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, method);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, method);
        }
    }
    
    private IRubyObject invokeDirectWithExceptionHandling(final Method method, final Object javaInvokee, final Object arg0, final Object arg1, final Object arg2, final Object arg3) {
        try {
            final Object result = (this.handle != null) ? this.handle.invoke(javaInvokee, arg0, arg1, arg2, arg3) : method.invoke(javaInvokee, arg0, arg1, arg2, arg3);
            return this.convertReturn(result);
        }
        catch (IllegalArgumentException iae) {
            return this.handlelIllegalArgumentEx(method, iae, arg0, arg1, arg2, arg3);
        }
        catch (IllegalAccessException iae2) {
            return this.handleIllegalAccessEx(method, iae2);
        }
        catch (InvocationTargetException ite) {
            return this.handleInvocationTargetEx(ite, method);
        }
        catch (Throwable t) {
            return this.handleThrowable(t, method);
        }
    }
    
    private IRubyObject convertReturn(final Object result) {
        if (result != null && result.getClass() != this.boxedReturnType) {
            return JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), result);
        }
        return JavaUtil.convertJavaToUsableRubyObjectWithConverter(this.getRuntime(), result, this.returnConverter);
    }
    
    private IRubyObject handleIllegalAccessEx(final Method method, final IllegalAccessException iae) throws RaiseException {
        throw this.getRuntime().newTypeError("illegal access on '" + method.getName() + "': " + iae.getMessage());
    }
    
    private IRubyObject handlelIllegalArgumentEx(final Method method, final IllegalArgumentException iae, final Object... arguments) throws RaiseException {
        throw this.getRuntime().newTypeError("for method " + method.getDeclaringClass().getSimpleName() + "." + method.getName() + " expected " + this.argument_types().inspect() + "; got: " + JavaCallable.dumpArgTypes(arguments) + "; error: " + iae.getMessage());
    }
    
    private void convertArguments(final IRubyObject[] argsIn, final Object[] argsOut, final int from) {
        final Class<?>[] types = this.parameterTypes;
        int i = argsOut.length;
        while (--i >= 0) {
            argsOut[i] = argsIn[i + from].toJava(types[i]);
        }
    }
    
    public Class<?>[] getParameterTypes() {
        return this.parameterTypes;
    }
    
    public Class<?>[] getExceptionTypes() {
        return this.method.getExceptionTypes();
    }
    
    public Type[] getGenericParameterTypes() {
        return this.method.getGenericParameterTypes();
    }
    
    public Type[] getGenericExceptionTypes() {
        return this.method.getGenericExceptionTypes();
    }
    
    public Annotation[][] getParameterAnnotations() {
        return this.method.getParameterAnnotations();
    }
    
    public boolean isVarArgs() {
        return this.method.isVarArgs();
    }
    
    protected String nameOnInspection() {
        return "#<" + this.getType().toString() + "/" + this.method.getName() + "(";
    }
    
    @JRubyMethod(name = { "static?" })
    public RubyBoolean static_p() {
        return this.getRuntime().newBoolean(this.isStatic());
    }
    
    public RubyBoolean bridge_p() {
        return this.getRuntime().newBoolean(this.method.isBridge());
    }
    
    private boolean isStatic() {
        return Modifier.isStatic(this.method.getModifiers());
    }
    
    public int getModifiers() {
        return this.method.getModifiers();
    }
    
    public String toGenericString() {
        return this.method.toGenericString();
    }
    
    protected AccessibleObject accessibleObject() {
        return this.method;
    }
    
    private boolean mightBeProxy(final Object javaInvokee) {
        return javaInvokee instanceof InternalJavaProxy && !this.isFinal;
    }
    
    private IRubyObject tryProxyInvocation(final Object javaInvokee, final Object... args) {
        final JavaProxyClass jpc = ((InternalJavaProxy)javaInvokee).___getProxyClass();
        final JavaProxyMethod jpm;
        if ((jpm = jpc.getMethod(this.method.getName(), this.parameterTypes)) != null && jpm.hasSuperImplementation()) {
            return this.invokeDirectSuperWithExceptionHandling(jpm.getSuperMethod(), javaInvokee, args);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, args);
    }
    
    private IRubyObject tryProxyInvocation(final Object javaInvokee) {
        final JavaProxyClass jpc = ((InternalJavaProxy)javaInvokee).___getProxyClass();
        final JavaProxyMethod jpm;
        if ((jpm = jpc.getMethod(this.method.getName(), this.parameterTypes)) != null && jpm.hasSuperImplementation()) {
            return this.invokeDirectSuperWithExceptionHandling(jpm.getSuperMethod(), javaInvokee, new Object[0]);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee);
    }
    
    private IRubyObject tryProxyInvocation(final Object javaInvokee, final Object arg0) {
        final JavaProxyClass jpc = ((InternalJavaProxy)javaInvokee).___getProxyClass();
        final JavaProxyMethod jpm;
        if ((jpm = jpc.getMethod(this.method.getName(), this.parameterTypes)) != null && jpm.hasSuperImplementation()) {
            return this.invokeDirectSuperWithExceptionHandling(jpm.getSuperMethod(), javaInvokee, arg0);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, arg0);
    }
    
    private IRubyObject tryProxyInvocation(final Object javaInvokee, final Object arg0, final Object arg1) {
        final JavaProxyClass jpc = ((InternalJavaProxy)javaInvokee).___getProxyClass();
        final JavaProxyMethod jpm;
        if ((jpm = jpc.getMethod(this.method.getName(), this.parameterTypes)) != null && jpm.hasSuperImplementation()) {
            return this.invokeDirectSuperWithExceptionHandling(jpm.getSuperMethod(), javaInvokee, arg0, arg1);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, arg0, arg1);
    }
    
    private IRubyObject tryProxyInvocation(final Object javaInvokee, final Object arg0, final Object arg1, final Object arg2) {
        final JavaProxyClass jpc = ((InternalJavaProxy)javaInvokee).___getProxyClass();
        final JavaProxyMethod jpm;
        if ((jpm = jpc.getMethod(this.method.getName(), this.parameterTypes)) != null && jpm.hasSuperImplementation()) {
            return this.invokeDirectSuperWithExceptionHandling(jpm.getSuperMethod(), javaInvokee, arg0, arg1, arg2);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, arg0, arg1, arg2);
    }
    
    private IRubyObject tryProxyInvocation(final Object javaInvokee, final Object arg0, final Object arg1, final Object arg2, final Object arg3) {
        final JavaProxyClass jpc = ((InternalJavaProxy)javaInvokee).___getProxyClass();
        final JavaProxyMethod jpm;
        if ((jpm = jpc.getMethod(this.method.getName(), this.parameterTypes)) != null && jpm.hasSuperImplementation()) {
            return this.invokeDirectSuperWithExceptionHandling(jpm.getSuperMethod(), javaInvokee, arg0, arg1, arg2, arg3);
        }
        return this.invokeDirectWithExceptionHandling(this.method, javaInvokee, arg0, arg1, arg2, arg3);
    }
    
    public static RaiseException newMethodNotFoundError(final Ruby runtime, final Class target, final String prettyName, final String simpleName) {
        return runtime.newNameError("java method not found: " + target.getName() + "." + prettyName, simpleName);
    }
    
    public static RaiseException newArgSizeMismatchError(final Ruby runtime, final Class... argTypes) {
        return runtime.newArgumentError("argument count mismatch for method signature " + CodegenUtils.prettyParams(argTypes));
    }
    
    static {
        USE_HANDLES = RubyInstanceConfig.USE_GENERATED_HANDLES;
    }
}
