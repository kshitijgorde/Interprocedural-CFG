// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

import java.util.Arrays;
import java.security.AccessController;
import java.security.ProtectionDomain;
import java.security.PrivilegedAction;
import java.util.Collections;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.org.objectweb.asm.FieldVisitor;
import java.lang.reflect.UndeclaredThrowableException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.lang.reflect.Field;
import org.jruby.org.objectweb.asm.commons.GeneratorAdapter;
import org.jruby.org.objectweb.asm.ClassWriter;
import org.jruby.org.objectweb.asm.ClassVisitor;
import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;
import java.lang.reflect.InvocationTargetException;
import org.jruby.Ruby;
import java.util.Set;
import java.util.Map;
import org.jruby.org.objectweb.asm.commons.Method;
import org.jruby.org.objectweb.asm.Type;

public class JavaProxyClassFactory
{
    private static final Type JAVA_LANG_CLASS_TYPE;
    private static final Type[] EMPTY_TYPE_ARR;
    private static final Method HELPER_GET_PROXY_CLASS_METHOD;
    private static final Method CLASS_FORNAME_METHOD;
    private static final String INVOCATION_HANDLER_FIELD_NAME = "__handler";
    private static final String PROXY_CLASS_FIELD_NAME = "__proxy_class";
    private static final Class[] EMPTY_CLASS_ARR;
    private static final Type INVOCATION_HANDLER_TYPE;
    private static final Type PROXY_METHOD_TYPE;
    private static final Type PROXY_CLASS_TYPE;
    private static final Method INVOCATION_HANDLER_INVOKE_METHOD;
    private static final Type PROXY_HELPER_TYPE;
    private static final Method PROXY_HELPER_GET_METHOD;
    private static final Type JAVA_PROXY_TYPE;
    private static int counter;
    private static Map proxies;
    private static java.lang.reflect.Method defineClass_method;
    
    private static synchronized int nextId() {
        return JavaProxyClassFactory.counter++;
    }
    
    @Deprecated
    static JavaProxyClass newProxyClass(final ClassLoader loader, final String targetClassName, final Class superClass, final Class[] interfaces, final Set names) throws InvocationTargetException {
        return newProxyClass(JavaProxyClass.runtimeTLS.get(), loader, targetClassName, superClass, interfaces, names);
    }
    
    static JavaProxyClass newProxyClass(final Ruby runtime, ClassLoader loader, String targetClassName, Class superClass, Class[] interfaces, final Set names) throws InvocationTargetException {
        if (loader == null) {
            loader = JavaProxyClassFactory.class.getClassLoader();
        }
        if (superClass == null) {
            superClass = Object.class;
        }
        if (interfaces == null) {
            interfaces = JavaProxyClassFactory.EMPTY_CLASS_ARR;
        }
        final Set key = new HashSet();
        key.add(superClass);
        for (int i = 0; i < interfaces.length; ++i) {
            key.add(interfaces[i]);
        }
        if (names != null) {
            key.addAll(names);
        }
        JavaProxyClass proxyClass = JavaProxyClassFactory.proxies.get(key);
        if (proxyClass == null) {
            if (targetClassName == null) {
                final String pkg = proxyPackageName(superClass);
                final String fullName = superClass.getName();
                final int ix = fullName.lastIndexOf(46);
                String cName = fullName;
                if (ix != -1) {
                    cName = fullName.substring(ix + 1);
                }
                targetClassName = pkg + "." + cName + "$Proxy" + nextId();
            }
            validateArgs(runtime, targetClassName, superClass);
            final Map methods = new HashMap();
            collectMethods(superClass, interfaces, methods, names);
            final Type selfType = Type.getType("L" + toInternalClassName(targetClassName) + ";");
            proxyClass = generate(loader, targetClassName, superClass, interfaces, methods, selfType);
            JavaProxyClassFactory.proxies.put(key, proxyClass);
        }
        return proxyClass;
    }
    
    static JavaProxyClass newProxyClass(final ClassLoader loader, final String targetClassName, final Class superClass, final Class[] interfaces) throws InvocationTargetException {
        return newProxyClass(loader, targetClassName, superClass, interfaces, null);
    }
    
    private static JavaProxyClass generate(final ClassLoader loader, final String targetClassName, final Class superClass, final Class[] interfaces, final Map methods, final Type selfType) {
        final ClassWriter cw = beginProxyClass(targetClassName, superClass, interfaces);
        final GeneratorAdapter clazzInit = createClassInitializer(selfType, cw);
        generateConstructors(superClass, selfType, cw);
        generateGetProxyClass(selfType, cw);
        generateGetInvocationHandler(selfType, cw);
        generateProxyMethods(superClass, methods, selfType, cw, clazzInit);
        clazzInit.returnValue();
        clazzInit.endMethod();
        cw.visitEnd();
        final byte[] data = cw.toByteArray();
        final Class clazz = invokeDefineClass(loader, selfType.getClassName(), data);
        try {
            final Field proxy_class = clazz.getDeclaredField("__proxy_class");
            proxy_class.setAccessible(true);
            return (JavaProxyClass)proxy_class.get(clazz);
        }
        catch (Exception ex) {
            final InternalError ie = new InternalError();
            ie.initCause(ex);
            throw ie;
        }
    }
    
    private static Class invokeDefineClass(final ClassLoader loader, final String className, final byte[] data) {
        try {
            return (Class)JavaProxyClassFactory.defineClass_method.invoke(loader, className, data, 0, data.length, JavaProxyClassFactory.class.getProtectionDomain());
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
        catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
        catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return null;
        }
    }
    
    private static ClassWriter beginProxyClass(final String targetClassName, final Class superClass, final Class[] interfaces) {
        final ClassWriter cw = new ClassWriter(1);
        final int access = 25;
        final String name = toInternalClassName(targetClassName);
        final String signature = null;
        final String supername = toInternalClassName(superClass);
        final String[] interfaceNames = new String[interfaces.length + 1];
        for (int i = 0; i < interfaces.length; ++i) {
            interfaceNames[i] = toInternalClassName(interfaces[i]);
        }
        interfaceNames[interfaces.length] = toInternalClassName(InternalJavaProxy.class);
        cw.visit(47, access, name, signature, supername, interfaceNames);
        cw.visitField(2, "__handler", JavaProxyClassFactory.INVOCATION_HANDLER_TYPE.getDescriptor(), null, null).visitEnd();
        cw.visitField(10, "__proxy_class", JavaProxyClassFactory.PROXY_CLASS_TYPE.getDescriptor(), null, null).visitEnd();
        return cw;
    }
    
    private static void generateProxyMethods(final Class superClass, final Map methods, final Type selfType, final ClassVisitor cw, final GeneratorAdapter clazzInit) {
        for (final MethodData md : methods.values()) {
            final Type superClassType = Type.getType(superClass);
            generateProxyMethod(selfType, superClassType, cw, clazzInit, md);
        }
    }
    
    private static void generateGetInvocationHandler(final Type selfType, final ClassVisitor cw) {
        final GeneratorAdapter gh = new GeneratorAdapter(1, new Method("___getInvocationHandler", JavaProxyClassFactory.INVOCATION_HANDLER_TYPE, JavaProxyClassFactory.EMPTY_TYPE_ARR), null, JavaProxyClassFactory.EMPTY_TYPE_ARR, cw);
        gh.loadThis();
        gh.getField(selfType, "__handler", JavaProxyClassFactory.INVOCATION_HANDLER_TYPE);
        gh.returnValue();
        gh.endMethod();
    }
    
    private static void generateGetProxyClass(final Type selfType, final ClassVisitor cw) {
        final GeneratorAdapter gpc = new GeneratorAdapter(1, new Method("___getProxyClass", JavaProxyClassFactory.PROXY_CLASS_TYPE, JavaProxyClassFactory.EMPTY_TYPE_ARR), null, JavaProxyClassFactory.EMPTY_TYPE_ARR, cw);
        gpc.getStatic(selfType, "__proxy_class", JavaProxyClassFactory.PROXY_CLASS_TYPE);
        gpc.returnValue();
        gpc.endMethod();
    }
    
    private static void generateConstructors(final Class superClass, final Type selfType, final ClassVisitor cw) {
        final Constructor[] cons = superClass.getDeclaredConstructors();
        for (int i = 0; i < cons.length; ++i) {
            final Constructor constructor = cons[i];
            if (!Modifier.isPrivate(constructor.getModifiers())) {
                generateConstructor(selfType, constructor, cw);
            }
        }
    }
    
    private static GeneratorAdapter createClassInitializer(final Type selfType, final ClassVisitor cw) {
        final GeneratorAdapter clazzInit = new GeneratorAdapter(10, new Method("<clinit>", Type.VOID_TYPE, JavaProxyClassFactory.EMPTY_TYPE_ARR), null, JavaProxyClassFactory.EMPTY_TYPE_ARR, cw);
        clazzInit.visitLdcInsn(selfType.getClassName());
        clazzInit.invokeStatic(JavaProxyClassFactory.JAVA_LANG_CLASS_TYPE, JavaProxyClassFactory.CLASS_FORNAME_METHOD);
        clazzInit.invokeStatic(JavaProxyClassFactory.PROXY_HELPER_TYPE, JavaProxyClassFactory.HELPER_GET_PROXY_CLASS_METHOD);
        clazzInit.dup();
        clazzInit.putStatic(selfType, "__proxy_class", JavaProxyClassFactory.PROXY_CLASS_TYPE);
        return clazzInit;
    }
    
    private static void generateProxyMethod(final Type selfType, final Type superType, final ClassVisitor cw, final GeneratorAdapter clazzInit, final MethodData md) {
        if (!md.generateProxyMethod()) {
            return;
        }
        final Method m = md.getMethod();
        final Type[] ex = toType(md.getExceptions());
        final String field_name = "__mth$" + md.getName() + md.scrambledSignature();
        final FieldVisitor fv = cw.visitField(10, field_name, JavaProxyClassFactory.PROXY_METHOD_TYPE.getDescriptor(), null, null);
        fv.visitEnd();
        clazzInit.dup();
        clazzInit.push(m.getName());
        clazzInit.push(m.getDescriptor());
        clazzInit.push(md.isImplemented());
        clazzInit.invokeStatic(JavaProxyClassFactory.PROXY_HELPER_TYPE, JavaProxyClassFactory.PROXY_HELPER_GET_METHOD);
        clazzInit.putStatic(selfType, field_name, JavaProxyClassFactory.PROXY_METHOD_TYPE);
        final Method sm = new Method("__super$" + m.getName(), m.getReturnType(), m.getArgumentTypes());
        final GeneratorAdapter ga = new GeneratorAdapter(1, m, null, ex, cw);
        ga.loadThis();
        ga.getField(selfType, "__handler", JavaProxyClassFactory.INVOCATION_HANDLER_TYPE);
        if (md.isImplemented()) {
            ga.dup();
            final Label ok = ga.newLabel();
            ga.ifNonNull(ok);
            ga.loadThis();
            ga.loadArgs();
            ga.invokeConstructor(superType, m);
            ga.returnValue();
            ga.mark(ok);
        }
        ga.loadThis();
        ga.getStatic(selfType, field_name, JavaProxyClassFactory.PROXY_METHOD_TYPE);
        if (m.getArgumentTypes().length == 0) {
            ga.getStatic(JavaProxyClassFactory.JAVA_PROXY_TYPE, "NO_ARGS", Type.getType(Object[].class));
        }
        else {
            ga.loadArgArray();
        }
        final Label before = ga.mark();
        ga.invokeInterface(JavaProxyClassFactory.INVOCATION_HANDLER_TYPE, JavaProxyClassFactory.INVOCATION_HANDLER_INVOKE_METHOD);
        final Label after = ga.mark();
        ga.unbox(m.getReturnType());
        ga.returnValue();
        final Label rethrow = ga.mark();
        ga.visitInsn(191);
        for (int i = 0; i < ex.length; ++i) {
            ga.visitTryCatchBlock(before, after, rethrow, ex[i].getInternalName());
        }
        ga.visitTryCatchBlock(before, after, rethrow, "java/lang/Error");
        ga.visitTryCatchBlock(before, after, rethrow, "java/lang/RuntimeException");
        final Type thr = Type.getType(Throwable.class);
        final Label handler = ga.mark();
        final Type udt = Type.getType(UndeclaredThrowableException.class);
        final int loc = ga.newLocal(thr);
        ga.storeLocal(loc, thr);
        ga.newInstance(udt);
        ga.dup();
        ga.loadLocal(loc, thr);
        ga.invokeConstructor(udt, Method.getMethod("void <init>(java.lang.Throwable)"));
        ga.throwException();
        ga.visitTryCatchBlock(before, after, handler, "java/lang/Throwable");
        ga.endMethod();
        if (md.isImplemented()) {
            final GeneratorAdapter ga2 = new GeneratorAdapter(1, sm, null, ex, cw);
            ga2.loadThis();
            ga2.loadArgs();
            ga2.invokeConstructor(superType, m);
            ga2.returnValue();
            ga2.endMethod();
        }
    }
    
    private static Class[] generateConstructor(final Type selfType, final Constructor constructor, final ClassVisitor cw) {
        final Class[] superConstructorParameterTypes = constructor.getParameterTypes();
        final Class[] newConstructorParameterTypes = new Class[superConstructorParameterTypes.length + 1];
        System.arraycopy(superConstructorParameterTypes, 0, newConstructorParameterTypes, 0, superConstructorParameterTypes.length);
        newConstructorParameterTypes[superConstructorParameterTypes.length] = JavaProxyInvocationHandler.class;
        final int access = 1;
        final String name1 = "<init>";
        final String signature = null;
        final Class[] superConstructorExceptions = constructor.getExceptionTypes();
        final Method super_m = new Method(name1, Type.VOID_TYPE, toType(superConstructorParameterTypes));
        final Method m = new Method(name1, Type.VOID_TYPE, toType(newConstructorParameterTypes));
        final GeneratorAdapter ga = new GeneratorAdapter(access, m, signature, toType(superConstructorExceptions), cw);
        ga.loadThis();
        ga.loadArgs(0, superConstructorParameterTypes.length);
        ga.invokeConstructor(Type.getType(constructor.getDeclaringClass()), super_m);
        ga.loadThis();
        ga.loadArg(superConstructorParameterTypes.length);
        ga.putField(selfType, "__handler", JavaProxyClassFactory.INVOCATION_HANDLER_TYPE);
        ga.returnValue();
        ga.endMethod();
        return newConstructorParameterTypes;
    }
    
    private static String toInternalClassName(final Class clazz) {
        return toInternalClassName(clazz.getName());
    }
    
    private static String toInternalClassName(final String name) {
        return name.replace('.', '/');
    }
    
    private static Type[] toType(final Class[] parameterTypes) {
        final Type[] result = new Type[parameterTypes.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = Type.getType(parameterTypes[i]);
        }
        return result;
    }
    
    private static void collectMethods(final Class superClass, final Class[] interfaces, final Map methods, final Set names) {
        final HashSet allClasses = new HashSet();
        addClass(allClasses, methods, superClass, names);
        addInterfaces(allClasses, methods, interfaces, names);
    }
    
    private static void addInterfaces(final Set allClasses, final Map methods, final Class[] interfaces, final Set names) {
        for (int i = 0; i < interfaces.length; ++i) {
            addInterface(allClasses, methods, interfaces[i], names);
        }
    }
    
    private static void addInterface(final Set allClasses, final Map methods, final Class interfaze, final Set names) {
        if (allClasses.add(interfaze)) {
            addMethods(methods, interfaze, names);
            addInterfaces(allClasses, methods, interfaze.getInterfaces(), names);
        }
    }
    
    private static void addMethods(final Map methods, final Class classOrInterface, final Set names) {
        final java.lang.reflect.Method[] mths = classOrInterface.getDeclaredMethods();
        for (int i = 0; i < mths.length; ++i) {
            if (names == null || names.contains(mths[i].getName())) {
                addMethod(methods, mths[i]);
            }
        }
    }
    
    private static void addMethod(final Map methods, final java.lang.reflect.Method method) {
        final int acc = method.getModifiers();
        if (Modifier.isStatic(acc) || Modifier.isPrivate(acc)) {
            return;
        }
        final MethodKey mk = new MethodKey(method);
        MethodData md = methods.get(mk);
        if (md == null) {
            md = new MethodData(method);
            methods.put(mk, md);
        }
        md.add(method);
    }
    
    private static void addClass(final Set allClasses, final Map methods, final Class clazz, final Set names) {
        if (allClasses.add(clazz)) {
            addMethods(methods, clazz, names);
            final Class superClass = clazz.getSuperclass();
            if (superClass != null) {
                addClass(allClasses, methods, superClass, names);
            }
            addInterfaces(allClasses, methods, clazz.getInterfaces(), names);
        }
    }
    
    private static void validateArgs(final Ruby runtime, final String targetClassName, final Class superClass) {
        if (Modifier.isFinal(superClass.getModifiers())) {
            throw runtime.newTypeError("cannot extend final class " + superClass.getName());
        }
        final String targetPackage = packageName(targetClassName);
        final String pkg = targetPackage.replace('.', '/');
        if (pkg.startsWith("java")) {
            throw runtime.newTypeError("cannot add classes to package " + pkg);
        }
        final Package p = Package.getPackage(pkg);
        if (p != null && p.isSealed()) {
            throw runtime.newTypeError("package " + p + " is sealed");
        }
    }
    
    private static String packageName(final String clazzName) {
        final int idx = clazzName.lastIndexOf(46);
        if (idx == -1) {
            return "";
        }
        return clazzName.substring(0, idx);
    }
    
    private static String proxyPackageName(final Class clazz) {
        final String clazzName = clazz.getName();
        final int idx = clazzName.lastIndexOf(46);
        if (idx == -1) {
            return "org.jruby.proxy";
        }
        return "org.jruby.proxy." + clazzName.substring(0, idx);
    }
    
    static {
        JAVA_LANG_CLASS_TYPE = Type.getType(Class.class);
        EMPTY_TYPE_ARR = new Type[0];
        HELPER_GET_PROXY_CLASS_METHOD = Method.getMethod(JavaProxyClass.class.getName() + " initProxyClass(java.lang.Class)");
        CLASS_FORNAME_METHOD = Method.getMethod("java.lang.Class forName(java.lang.String)");
        EMPTY_CLASS_ARR = new Class[0];
        INVOCATION_HANDLER_TYPE = Type.getType(JavaProxyInvocationHandler.class);
        PROXY_METHOD_TYPE = Type.getType(JavaProxyMethod.class);
        PROXY_CLASS_TYPE = Type.getType(JavaProxyClass.class);
        INVOCATION_HANDLER_INVOKE_METHOD = Method.getMethod("java.lang.Object invoke(java.lang.Object, " + JavaProxyClassFactory.PROXY_METHOD_TYPE.getClassName() + ", java.lang.Object[])");
        PROXY_HELPER_TYPE = Type.getType(InternalJavaProxyHelper.class);
        PROXY_HELPER_GET_METHOD = Method.getMethod(JavaProxyClassFactory.PROXY_METHOD_TYPE.getClassName() + " initProxyMethod(" + JavaProxyClass.class.getName() + ",java.lang.String,java.lang.String,boolean)");
        JAVA_PROXY_TYPE = Type.getType(InternalJavaProxy.class);
        JavaProxyClassFactory.proxies = Collections.synchronizedMap(new HashMap<Object, Object>());
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            public Object run() {
                try {
                    JavaProxyClassFactory.defineClass_method = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                JavaProxyClassFactory.defineClass_method.setAccessible(true);
                return null;
            }
        });
    }
    
    static class MethodData
    {
        Set methods;
        final java.lang.reflect.Method mostSpecificMethod;
        final Class[] mostSpecificParameterTypes;
        boolean hasPublicDecl;
        
        MethodData(final java.lang.reflect.Method method) {
            this.methods = new HashSet();
            this.hasPublicDecl = false;
            this.mostSpecificMethod = method;
            this.mostSpecificParameterTypes = this.mostSpecificMethod.getParameterTypes();
            this.hasPublicDecl = (method.getDeclaringClass().isInterface() || Modifier.isPublic(method.getModifiers()));
        }
        
        public String scrambledSignature() {
            final StringBuilder sb = new StringBuilder();
            final Class[] parms = this.getParameterTypes();
            for (int i = 0; i < parms.length; ++i) {
                sb.append('$');
                String name = parms[i].getName();
                name = name.replace('[', '1');
                name = name.replace('.', '_');
                name = name.replace(';', '2');
                sb.append(name);
            }
            return sb.toString();
        }
        
        public Class getDeclaringClass() {
            return this.mostSpecificMethod.getDeclaringClass();
        }
        
        public Method getMethod() {
            return new Method(this.getName(), Type.getType(this.getReturnType()), this.getType(this.getParameterTypes()));
        }
        
        private Type[] getType(final Class[] parameterTypes) {
            final Type[] result = new Type[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; ++i) {
                result[i] = Type.getType(parameterTypes[i]);
            }
            return result;
        }
        
        private String getName() {
            return this.mostSpecificMethod.getName();
        }
        
        private Class[] getParameterTypes() {
            return this.mostSpecificParameterTypes;
        }
        
        public Class[] getExceptions() {
            final Set all = new HashSet();
            for (final java.lang.reflect.Method m : this.methods) {
                final Class[] ex = m.getExceptionTypes();
                for (int i = 0; i < ex.length; ++i) {
                    final Class exx = ex[i];
                    if (!all.contains(exx)) {
                        boolean add = true;
                        final Iterator it2 = all.iterator();
                        while (it2.hasNext()) {
                            final Class de = it2.next();
                            if (de.isAssignableFrom(exx)) {
                                add = false;
                                break;
                            }
                            if (!exx.isAssignableFrom(de)) {
                                continue;
                            }
                            it2.remove();
                            add = true;
                        }
                        if (add) {
                            all.add(exx);
                        }
                    }
                }
            }
            return all.toArray(new Class[all.size()]);
        }
        
        public boolean generateProxyMethod() {
            return !this.isFinal() && !this.isPrivate();
        }
        
        public void add(final java.lang.reflect.Method method) {
            this.methods.add(method);
            this.hasPublicDecl |= Modifier.isPublic(method.getModifiers());
        }
        
        Class getReturnType() {
            return this.mostSpecificMethod.getReturnType();
        }
        
        boolean isFinal() {
            if (this.mostSpecificMethod.getDeclaringClass().isInterface()) {
                return false;
            }
            final int mod = this.mostSpecificMethod.getModifiers();
            return Modifier.isFinal(mod);
        }
        
        boolean isPrivate() {
            if (this.mostSpecificMethod.getDeclaringClass().isInterface()) {
                return false;
            }
            final int mod = this.mostSpecificMethod.getModifiers();
            return Modifier.isPrivate(mod);
        }
        
        boolean isImplemented() {
            if (this.mostSpecificMethod.getDeclaringClass().isInterface()) {
                return false;
            }
            final int mod = this.mostSpecificMethod.getModifiers();
            return !Modifier.isAbstract(mod);
        }
    }
    
    static class MethodKey
    {
        private String name;
        private Class[] arguments;
        
        MethodKey(final java.lang.reflect.Method m) {
            this.name = m.getName();
            this.arguments = m.getParameterTypes();
        }
        
        public boolean equals(final Object obj) {
            if (obj instanceof MethodKey) {
                final MethodKey key = (MethodKey)obj;
                return this.name.equals(key.name) && Arrays.equals(this.arguments, key.arguments);
            }
            return false;
        }
        
        public int hashCode() {
            return this.name.hashCode();
        }
    }
}
