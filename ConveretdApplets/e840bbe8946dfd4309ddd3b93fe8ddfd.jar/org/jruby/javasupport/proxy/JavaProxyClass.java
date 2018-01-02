// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

import java.lang.reflect.Member;
import org.jruby.javasupport.JavaUtil;
import org.jruby.javasupport.JavaObject;
import org.jruby.RubyFixnum;
import java.util.Arrays;
import org.jruby.anno.JRubyClass;
import org.jruby.internal.runtime.methods.DynamicMethod;
import java.util.Map;
import java.util.Iterator;
import org.jruby.RubyString;
import org.jruby.RubyNil;
import org.jruby.anno.JRubyMethod;
import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.JavaClass;
import org.jruby.RubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import java.lang.reflect.Array;
import java.security.PrivilegedActionException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.HashSet;
import org.jruby.RubyArray;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import org.jruby.Ruby;

public class JavaProxyClass extends JavaProxyReflectionObject
{
    static ThreadLocal<Ruby> runtimeTLS;
    private final Class proxyClass;
    private final ArrayList<JavaProxyMethod> methods;
    private final HashMap<String, List<JavaProxyMethod>> methodMap;
    private final RubyArray constructors;
    private static final HashSet<String> EXCLUDE_MODULES;
    private static final HashSet<String> EXCLUDE_METHODS;
    
    JavaProxyClass(final Class proxyClass) {
        super(getThreadLocalRuntime(), getThreadLocalRuntime().fastGetModule("Java").fastGetClass("JavaProxyClass"));
        this.methods = new ArrayList<JavaProxyMethod>();
        this.methodMap = new HashMap<String, List<JavaProxyMethod>>();
        this.proxyClass = proxyClass;
        this.constructors = this.buildRubyArray(this.getConstructors());
    }
    
    public boolean equals(final Object other) {
        return other instanceof JavaProxyClass && this.proxyClass == ((JavaProxyClass)other).proxyClass;
    }
    
    public int hashCode() {
        return this.proxyClass.hashCode();
    }
    
    public Object getValue() {
        return this;
    }
    
    private static Ruby getThreadLocalRuntime() {
        return JavaProxyClass.runtimeTLS.get();
    }
    
    public static JavaProxyClass getProxyClass(final Ruby runtime, final Class superClass, final Class[] interfaces, final Set names) throws InvocationTargetException {
        final Ruby save = JavaProxyClass.runtimeTLS.get();
        JavaProxyClass.runtimeTLS.set(runtime);
        try {
            final ClassLoader loader = runtime.getJRubyClassLoader();
            return JavaProxyClassFactory.newProxyClass(runtime, loader, null, superClass, interfaces, names);
        }
        finally {
            JavaProxyClass.runtimeTLS.set(save);
        }
    }
    
    public static JavaProxyClass getProxyClass(final Ruby runtime, final Class superClass, final Class[] interfaces) throws InvocationTargetException {
        return getProxyClass(runtime, superClass, interfaces, null);
    }
    
    public static Object newProxyInstance(final Ruby runtime, final Class superClass, final Class[] interfaces, final Class[] constructorParameters, final Object[] constructorArgs, final JavaProxyInvocationHandler handler) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
        final JavaProxyClass jpc = getProxyClass(runtime, superClass, interfaces);
        final JavaProxyConstructor cons = jpc.getConstructor((constructorParameters == null) ? new Class[0] : constructorParameters);
        return cons.newInstance(constructorArgs, handler);
    }
    
    public Class getSuperclass() {
        return this.proxyClass.getSuperclass();
    }
    
    public Class[] getInterfaces() {
        final Class[] ifaces = this.proxyClass.getInterfaces();
        final Class[] result = new Class[ifaces.length - 1];
        int pos = 0;
        for (int i = 0; i < ifaces.length; ++i) {
            if (ifaces[i] != InternalJavaProxy.class) {
                result[pos++] = ifaces[i];
            }
        }
        return result;
    }
    
    public JavaProxyConstructor[] getConstructors() {
        final Constructor[] cons = this.proxyClass.getConstructors();
        final JavaProxyConstructor[] result = new JavaProxyConstructor[cons.length];
        for (int i = 0; i < cons.length; ++i) {
            result[i] = new JavaProxyConstructor(this.getRuntime(), this, cons[i]);
        }
        return result;
    }
    
    public JavaProxyConstructor getConstructor(final Class[] args) throws SecurityException, NoSuchMethodException {
        final Class[] realArgs = new Class[args.length + 1];
        System.arraycopy(args, 0, realArgs, 0, args.length);
        realArgs[args.length] = JavaProxyInvocationHandler.class;
        final Constructor constructor = this.proxyClass.getConstructor((Class[])realArgs);
        return new JavaProxyConstructor(this.getRuntime(), this, constructor);
    }
    
    public JavaProxyMethod[] getMethods() {
        return this.methods.toArray(new JavaProxyMethod[this.methods.size()]);
    }
    
    public JavaProxyMethod getMethod(final String name, final Class[] parameterTypes) {
        final List<JavaProxyMethod> methods = this.methodMap.get(name);
        if (methods != null) {
            int i = methods.size();
            while (--i >= 0) {
                final ProxyMethodImpl jpm = methods.get(i);
                if (jpm.matches(name, parameterTypes)) {
                    return jpm;
                }
            }
        }
        return null;
    }
    
    Class getProxyClass() {
        return this.proxyClass;
    }
    
    public Class getJavaClass() {
        return this.proxyClass;
    }
    
    JavaProxyMethod initMethod(final String name, final String desc, final boolean hasSuper) {
        final Class proxy = this.proxyClass;
        try {
            final Class[] parms = parse(proxy.getClassLoader(), desc);
            final Method m = proxy.getDeclaredMethod(name, (Class[])parms);
            Method sm = null;
            if (hasSuper) {
                sm = proxy.getDeclaredMethod("__super$" + name, (Class[])parms);
            }
            final JavaProxyMethod jpm = new ProxyMethodImpl(this.getRuntime(), this, m, sm);
            this.methods.add(jpm);
            List<JavaProxyMethod> methodsWithName = this.methodMap.get(name);
            if (methodsWithName == null) {
                methodsWithName = new ArrayList<JavaProxyMethod>(2);
                this.methodMap.put(name, methodsWithName);
            }
            methodsWithName.add(jpm);
            return jpm;
        }
        catch (ClassNotFoundException e) {
            throw new InternalError(e.getMessage());
        }
        catch (SecurityException e2) {
            throw new InternalError(e2.getMessage());
        }
        catch (NoSuchMethodException e3) {
            throw new InternalError(e3.getMessage());
        }
    }
    
    private static Class[] parse(final ClassLoader loader, final String desc) throws ClassNotFoundException {
        final List<Class> al = new ArrayList<Class>();
        int idx = 1;
        while (desc.charAt(idx) != ')') {
            int arr;
            for (arr = 0; desc.charAt(idx) == '['; ++idx, ++arr) {}
            Class type = null;
            switch (desc.charAt(idx)) {
                case 'L': {
                    final int semi = desc.indexOf(59, idx);
                    final String name = desc.substring(idx + 1, semi);
                    idx = semi;
                    try {
                        type = AccessController.doPrivileged((PrivilegedExceptionAction<Class>)new PrivilegedExceptionAction<Class>() {
                            public Class run() throws ClassNotFoundException {
                                return Class.forName(name.replace('/', '.'), false, loader);
                            }
                        });
                        break;
                    }
                    catch (PrivilegedActionException e) {
                        throw (ClassNotFoundException)e.getException();
                    }
                }
                case 'B': {
                    type = Byte.TYPE;
                    break;
                }
                case 'C': {
                    type = Character.TYPE;
                    break;
                }
                case 'Z': {
                    type = Boolean.TYPE;
                    break;
                }
                case 'S': {
                    type = Short.TYPE;
                    break;
                }
                case 'I': {
                    type = Integer.TYPE;
                    break;
                }
                case 'J': {
                    type = Long.TYPE;
                    break;
                }
                case 'F': {
                    type = Float.TYPE;
                    break;
                }
                case 'D': {
                    type = Double.TYPE;
                    break;
                }
                default: {
                    throw new InternalError("cannot parse " + desc + "[" + idx + "]");
                }
            }
            ++idx;
            if (arr != 0) {
                type = Array.newInstance(type, new int[arr]).getClass();
            }
            al.add(type);
        }
        return al.toArray(new Class[al.size()]);
    }
    
    public static RubyClass createJavaProxyClassClass(final Ruby runtime, final RubyModule javaModule) {
        final RubyClass result = javaModule.defineClassUnder("JavaProxyClass", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        JavaProxyReflectionObject.registerRubyMethods(runtime, result);
        result.defineAnnotatedMethods(JavaProxyClass.class);
        return result;
    }
    
    @JRubyMethod(meta = true)
    public static RubyObject get(final IRubyObject recv, final IRubyObject obj) {
        if (!(obj instanceof JavaClass)) {
            throw recv.getRuntime().newTypeError(obj, recv.getRuntime().getJavaSupport().getJavaClassClass());
        }
        final JavaClass type = (JavaClass)obj;
        try {
            return getProxyClass(recv.getRuntime(), (Class)type.getValue(), new Class[0]);
        }
        catch (Error e) {
            final RaiseException ex = recv.getRuntime().newArgumentError("unable to create proxy class for " + type.getValue());
            ex.initCause(e);
            throw ex;
        }
        catch (InvocationTargetException e2) {
            final RaiseException ex = recv.getRuntime().newArgumentError("unable to create proxy class for " + type.getValue());
            ex.initCause(e2);
            throw ex;
        }
    }
    
    @JRubyMethod(meta = true)
    public static RubyObject get_with_class(final IRubyObject recv, final IRubyObject obj) {
        final Ruby runtime = recv.getRuntime();
        if (!(obj instanceof RubyClass)) {
            throw runtime.newTypeError(obj, runtime.getClassClass());
        }
        final RubyClass clazz = (RubyClass)obj;
        JavaClass javaClass = null;
        final Set<String> names = new HashSet<String>();
        final List<Class<?>> interfaceList = new ArrayList<Class<?>>();
        final List<IRubyObject> ancestors = clazz.getAncestorList();
        boolean skipRemainingClasses = false;
        for (final IRubyObject ancestorObject : ancestors) {
            final RubyModule ancestor = (RubyModule)ancestorObject;
            if (ancestor instanceof RubyClass) {
                if (skipRemainingClasses) {
                    continue;
                }
                if (!ancestor.getInstanceVariables().fastHasInstanceVariable("@java_proxy_class")) {
                    skipRemainingClasses = true;
                }
                else {
                    IRubyObject var = ancestor.getInstanceVariables().fastGetInstanceVariable("@java_class");
                    if (var == null) {
                        throw runtime.newTypeError("no java_class defined for proxy (or ancestor): " + ancestor);
                    }
                    if (!(var instanceof JavaClass)) {
                        throw runtime.newTypeError("invalid java_class defined for proxy (or ancestor): " + ancestor + ": " + var);
                    }
                    if (javaClass == null) {
                        javaClass = (JavaClass)var;
                    }
                    else if (javaClass != var) {
                        throw runtime.newTypeError("java_class defined for " + clazz + " (" + javaClass + ") does not match java_class for ancestor " + ancestor + " (" + var + ")");
                    }
                    var = ancestor.getInstanceVariables().fastGetInstanceVariable("@java_interfaces");
                    if (var != null && !(var instanceof RubyNil)) {
                        if (!(var instanceof RubyArray)) {
                            throw runtime.newTypeError("invalid java_interfaces defined for proxy (or ancestor): " + ancestor + ": " + var);
                        }
                        final RubyArray ifcArray = (RubyArray)var;
                        int i;
                        final int size = i = ifcArray.size();
                        while (--i >= 0) {
                            final IRubyObject ifc = ifcArray.eltInternal(i);
                            if (!(ifc instanceof JavaClass)) {
                                throw runtime.newTypeError("invalid java interface defined for proxy (or ancestor): " + ancestor + ": " + ifc);
                            }
                            final Class interfaceClass = ((JavaClass)ifc).javaClass();
                            if (!interfaceClass.isInterface()) {
                                throw runtime.newTypeError("invalid java interface defined for proxy (or ancestor): " + ancestor + ": " + ifc + " (not an interface)");
                            }
                            if (interfaceList.contains(interfaceClass)) {
                                continue;
                            }
                            interfaceList.add(interfaceClass);
                        }
                    }
                    var = ancestor.getInstanceVariables().fastGetInstanceVariable("@__java_ovrd_methods");
                    if (var == null) {
                        final Map<String, DynamicMethod> methods;
                        final Map<String, DynamicMethod> map = methods = ancestor.getMethods();
                        final RubyArray methodNames;
                        synchronized (map) {
                            methodNames = RubyArray.newArrayLight(runtime, methods.size());
                            for (final String methodName : methods.keySet()) {
                                if (!JavaProxyClass.EXCLUDE_METHODS.contains(methodName)) {
                                    names.add(methodName);
                                    methodNames.append(runtime.newString(methodName));
                                }
                            }
                        }
                        ancestor.fastSetInstanceVariable("@__java_ovrd_methods", methodNames);
                    }
                    else {
                        if (!(var instanceof RubyArray)) {
                            throw runtime.newTypeError("invalid @__java_ovrd_methods defined for proxy: " + ancestor + ": " + var);
                        }
                        final RubyArray methodNames2 = (RubyArray)var;
                        int i;
                        final int size = i = methodNames2.size();
                        while (--i >= 0) {
                            final IRubyObject methodName2 = methodNames2.eltInternal(i);
                            if (!(methodName2 instanceof RubyString)) {
                                throw runtime.newTypeError("invalid method name defined for proxy (or ancestor): " + ancestor + ": " + methodName2);
                            }
                            names.add(methodName2.asJavaString());
                        }
                    }
                }
            }
            else {
                if (JavaProxyClass.EXCLUDE_MODULES.contains(ancestor.getName())) {
                    continue;
                }
                final Map<String, DynamicMethod> methods2;
                final Map<String, DynamicMethod> map2 = methods2 = ancestor.getMethods();
                synchronized (map2) {
                    for (final String methodName3 : methods2.keySet()) {
                        if (!JavaProxyClass.EXCLUDE_METHODS.contains(methodName3)) {
                            names.add(methodName3);
                        }
                    }
                }
            }
        }
        if (javaClass == null) {
            throw runtime.newArgumentError("unable to create proxy class: no java_class defined for " + clazz);
        }
        final int interfaceCount = interfaceList.size();
        final Class<?>[] interfaces = (Class<?>[])new Class[interfaceCount];
        int j = interfaceCount;
        while (--j >= 0) {
            interfaces[j] = interfaceList.get(j);
        }
        try {
            return getProxyClass(recv.getRuntime(), javaClass.javaClass(), interfaces, names);
        }
        catch (Error e) {
            final RaiseException ex = recv.getRuntime().newArgumentError("unable to create proxy class for " + javaClass.getValue() + " : " + e.getMessage());
            ex.initCause(e);
            throw ex;
        }
        catch (InvocationTargetException e2) {
            final RaiseException ex = recv.getRuntime().newArgumentError("unable to create proxy class for " + javaClass.getValue() + " : " + e2.getMessage());
            ex.initCause(e2);
            throw ex;
        }
    }
    
    @JRubyMethod
    public RubyObject superclass() {
        return JavaClass.get(this.getRuntime(), this.getSuperclass());
    }
    
    @JRubyMethod
    public RubyArray methods() {
        return this.buildRubyArray(this.getMethods());
    }
    
    @JRubyMethod
    public RubyArray interfaces() {
        return this.buildRubyArray(this.getInterfaces());
    }
    
    @JRubyMethod
    public RubyArray constructors() {
        return this.constructors;
    }
    
    public static void createJavaProxyModule(final Ruby runtime) {
        final RubyModule javaProxyModule = runtime.getJavaSupport().getJavaModule();
        createJavaProxyClassClass(runtime, javaProxyModule);
        ProxyMethodImpl.createJavaProxyMethodClass(runtime, javaProxyModule);
        JavaProxyConstructor.createJavaProxyConstructorClass(runtime, javaProxyModule);
    }
    
    public String nameOnInspection() {
        return "[Proxy:" + this.getSuperclass().getName() + "]";
    }
    
    static {
        JavaProxyClass.runtimeTLS = new ThreadLocal<Ruby>();
        (EXCLUDE_MODULES = new HashSet<String>()).add("Kernel");
        JavaProxyClass.EXCLUDE_MODULES.add("Java");
        JavaProxyClass.EXCLUDE_MODULES.add("JavaProxyMethods");
        JavaProxyClass.EXCLUDE_MODULES.add("Enumerable");
        (EXCLUDE_METHODS = new HashSet<String>()).add("class");
        JavaProxyClass.EXCLUDE_METHODS.add("finalize");
        JavaProxyClass.EXCLUDE_METHODS.add("initialize");
        JavaProxyClass.EXCLUDE_METHODS.add("java_class");
        JavaProxyClass.EXCLUDE_METHODS.add("java_object");
        JavaProxyClass.EXCLUDE_METHODS.add("__jcreate!");
        JavaProxyClass.EXCLUDE_METHODS.add("__jsend!");
    }
    
    @JRubyClass(name = { "JavaProxy::JavaProxyMethod" })
    public static class ProxyMethodImpl extends JavaProxyReflectionObject implements JavaProxyMethod
    {
        private final Method m;
        private Object state;
        private final Method sm;
        private final Class[] parameterTypes;
        private final JavaProxyClass clazz;
        
        public ProxyMethodImpl(final Ruby runtime, final JavaProxyClass clazz, final Method m, final Method sm) {
            super(runtime, runtime.getJavaSupport().getJavaModule().fastGetClass("JavaProxyMethod"));
            this.m = m;
            this.parameterTypes = m.getParameterTypes();
            this.sm = sm;
            this.clazz = clazz;
        }
        
        public boolean equals(final Object other) {
            return other instanceof ProxyMethodImpl && this.m == ((ProxyMethodImpl)other).m;
        }
        
        public int hashCode() {
            return this.m.hashCode();
        }
        
        public Method getMethod() {
            return this.m;
        }
        
        public Method getSuperMethod() {
            return this.sm;
        }
        
        public int getModifiers() {
            return this.m.getModifiers();
        }
        
        public String getName() {
            return this.m.getName();
        }
        
        public Class<?>[] getExceptionTypes() {
            return this.m.getExceptionTypes();
        }
        
        public Class<?>[] getParameterTypes() {
            return (Class<?>[])this.parameterTypes;
        }
        
        public boolean isVarArgs() {
            return this.m.isVarArgs();
        }
        
        public Object getState() {
            return this.state;
        }
        
        public boolean hasSuperImplementation() {
            return this.sm != null;
        }
        
        public Object invoke(final Object proxy, final Object[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            if (!this.hasSuperImplementation()) {
                throw new NoSuchMethodException();
            }
            return this.sm.invoke(proxy, args);
        }
        
        public void setState(final Object state) {
            this.state = state;
        }
        
        public String toString() {
            return this.m.toString();
        }
        
        public Object defaultResult() {
            final Class rt = this.m.getReturnType();
            if (rt == Void.TYPE) {
                return null;
            }
            if (rt == Boolean.TYPE) {
                return Boolean.FALSE;
            }
            if (rt == Byte.TYPE) {
                return 0;
            }
            if (rt == Short.TYPE) {
                return 0;
            }
            if (rt == Integer.TYPE) {
                return 0;
            }
            if (rt == Long.TYPE) {
                return 0L;
            }
            if (rt == Float.TYPE) {
                return new Float(0.0f);
            }
            if (rt == Double.TYPE) {
                return new Double(0.0);
            }
            return null;
        }
        
        public boolean matches(final String name, final Class[] parameterTypes) {
            return this.m.getName().equals(name) && Arrays.equals(this.parameterTypes, parameterTypes);
        }
        
        public Class getReturnType() {
            return this.m.getReturnType();
        }
        
        public static RubyClass createJavaProxyMethodClass(final Ruby runtime, final RubyModule javaProxyModule) {
            final RubyClass result = javaProxyModule.defineClassUnder("JavaProxyMethod", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
            JavaProxyReflectionObject.registerRubyMethods(runtime, result);
            result.defineAnnotatedMethods(ProxyMethodImpl.class);
            return result;
        }
        
        public RubyObject name() {
            return this.getRuntime().newString(this.getName());
        }
        
        @JRubyMethod(name = { "declaring_class" })
        public JavaProxyClass getDeclaringClass() {
            return this.clazz;
        }
        
        @JRubyMethod
        public RubyArray argument_types() {
            return this.buildRubyArray(this.getParameterTypes());
        }
        
        @JRubyMethod(name = { "super?" })
        public IRubyObject super_p() {
            return this.hasSuperImplementation() ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
        }
        
        @JRubyMethod
        public RubyFixnum arity() {
            return this.getRuntime().newFixnum(this.getArity());
        }
        
        protected String nameOnInspection() {
            return this.getDeclaringClass().nameOnInspection() + "/" + this.getName();
        }
        
        @JRubyMethod
        public IRubyObject inspect() {
            final StringBuilder result = new StringBuilder();
            result.append(this.nameOnInspection());
            result.append("(");
            final Class[] parameterTypes = this.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; ++i) {
                result.append(parameterTypes[i].getName());
                if (i < parameterTypes.length - 1) {
                    result.append(',');
                }
            }
            result.append(")>");
            return this.getRuntime().newString(result.toString());
        }
        
        @JRubyMethod(name = { "invoke" }, rest = true)
        public IRubyObject do_invoke(final IRubyObject[] nargs) {
            if (nargs.length != 1 + this.getArity()) {
                throw this.getRuntime().newArgumentError(nargs.length, 1 + this.getArity());
            }
            final IRubyObject invokee = nargs[0];
            if (!(invokee instanceof JavaObject)) {
                throw this.getRuntime().newTypeError("invokee not a java object");
            }
            final Object receiver_value = ((JavaObject)invokee).getValue();
            final Object[] arguments = new Object[nargs.length - 1];
            System.arraycopy(nargs, 1, arguments, 0, arguments.length);
            final Class[] parameterTypes = this.getParameterTypes();
            for (int i = 0; i < arguments.length; ++i) {
                arguments[i] = ((IRubyObject)arguments[i]).toJava(parameterTypes[i]);
            }
            try {
                final Object javaResult = this.sm.invoke(receiver_value, arguments);
                return JavaUtil.convertJavaToRuby(this.getRuntime(), javaResult, this.getReturnType());
            }
            catch (IllegalArgumentException e) {
                throw this.getRuntime().newTypeError("expected " + this.argument_types().inspect());
            }
            catch (IllegalAccessException iae) {
                throw this.getRuntime().newTypeError("illegal access on '" + this.sm.getName() + "': " + iae.getMessage());
            }
            catch (InvocationTargetException ite) {
                if (this.getRuntime().getDebug().isTrue()) {
                    ite.getTargetException().printStackTrace();
                }
                this.getRuntime().getJavaSupport().handleNativeException(ite.getTargetException(), this.sm);
                return this.getRuntime().getNil();
            }
        }
        
        private int getArity() {
            return this.getParameterTypes().length;
        }
    }
}
