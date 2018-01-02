// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.util.SafePropertyAccessor;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import org.jruby.RubyObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import org.jruby.util.ClassDefiningClassLoader;
import org.jruby.java.codegen.RealClassGenerator;
import org.jruby.util.ClassCache;
import org.jruby.RubyInstanceConfig;
import org.jruby.java.proxies.RubyObjectHolderProxy;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Arity;
import org.jruby.RubyException;
import org.jruby.exceptions.RaiseException;
import java.util.regex.Matcher;
import org.jruby.MetaClass;
import org.jruby.util.IdUtil;
import org.jruby.RubyArray;
import org.jruby.java.dispatch.CallableSelector;
import org.jruby.javasupport.proxy.JavaProxyConstructor;
import java.util.ArrayList;
import java.util.HashMap;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.java.invokers.MethodInvoker;
import java.lang.reflect.Method;
import org.jruby.RubyUnboundMethod;
import org.jruby.java.invokers.InstanceMethodInvoker;
import org.jruby.RubyMethod;
import org.jruby.java.invokers.StaticMethodInvoker;
import org.jruby.util.CodegenUtils;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.Visibility;
import java.lang.reflect.Modifier;
import org.jruby.RubyString;
import java.lang.reflect.Member;
import org.jruby.RubyBasicObject;
import org.jruby.runtime.builtin.IRubyObject;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.java.addons.IOJavaAddons;
import org.jruby.java.addons.StringJavaAddons;
import org.jruby.java.addons.KernelJavaAddons;
import org.jruby.java.addons.ArrayJavaAddons;
import org.jruby.java.proxies.JavaInterfaceTemplate;
import org.jruby.javasupport.proxy.JavaProxyClass;
import org.jruby.java.proxies.MapJavaProxy;
import org.jruby.java.proxies.ArrayJavaProxy;
import org.jruby.java.proxies.InterfaceJavaProxy;
import org.jruby.java.proxies.ConcreteJavaProxy;
import org.jruby.java.proxies.ArrayJavaProxyCreator;
import org.jruby.java.proxies.JavaProxy;
import org.jruby.RubyModule;
import java.io.IOException;
import org.jruby.RubyClassPathVariable;
import org.jruby.Ruby;
import java.util.regex.Pattern;
import java.util.Map;
import org.jruby.util.ClassProvider;
import org.jruby.anno.JRubyModule;
import org.jruby.runtime.load.Library;

@JRubyModule(name = { "Java" })
public class Java implements Library
{
    public static final boolean NEW_STYLE_EXTENSION;
    public static final boolean OBJECT_PROXY_CACHE;
    private static final ClassProvider JAVA_PACKAGE_CLASS_PROVIDER;
    private static final Map<String, Boolean> JAVA_PRIMITIVES;
    private static final Pattern CAMEL_CASE_PACKAGE_SPLITTER;
    
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        createJavaModule(runtime);
        runtime.getLoadService().smartLoad("builtin/javasupport");
        RubyClassPathVariable.createClassPathVariable(runtime);
    }
    
    public static RubyModule createJavaModule(final Ruby runtime) {
        final ThreadContext context = runtime.getCurrentContext();
        final RubyModule javaModule = runtime.defineModule("Java");
        javaModule.defineAnnotatedMethods(Java.class);
        JavaObject.createJavaObjectClass(runtime, javaModule);
        JavaArray.createJavaArrayClass(runtime, javaModule);
        JavaClass.createJavaClassClass(runtime, javaModule);
        JavaMethod.createJavaMethodClass(runtime, javaModule);
        JavaConstructor.createJavaConstructorClass(runtime, javaModule);
        JavaField.createJavaFieldClass(runtime, javaModule);
        JavaProxyMethods.createJavaProxyMethods(context);
        JavaProxy.createJavaProxy(context);
        ArrayJavaProxyCreator.createArrayJavaProxyCreator(context);
        ConcreteJavaProxy.createConcreteJavaProxy(context);
        InterfaceJavaProxy.createInterfaceJavaProxy(context);
        ArrayJavaProxy.createArrayJavaProxy(context);
        MapJavaProxy.createMapJavaProxy(context);
        JavaProxyClass.createJavaProxyModule(runtime);
        JavaInterfaceTemplate.createJavaInterfaceTemplateModule(context);
        final RubyModule javaUtils = runtime.defineModule("JavaUtilities");
        javaUtils.defineAnnotatedMethods(JavaUtilities.class);
        JavaArrayUtilities.createJavaArrayUtilitiesModule(runtime);
        runtime.getArray().defineAnnotatedMethods(ArrayJavaAddons.class);
        runtime.getKernel().defineAnnotatedMethods(KernelJavaAddons.class);
        runtime.getString().defineAnnotatedMethods(StringJavaAddons.class);
        runtime.getIO().defineAnnotatedMethods(IOJavaAddons.class);
        if (runtime.getObject().isConstantDefined("StringIO")) {
            ((RubyClass)runtime.getObject().getConstant("StringIO")).defineAnnotatedMethods(IOJavaAddons.AnyIO.class);
        }
        addNameClassMappings(runtime, runtime.getJavaSupport().getNameClassMap());
        runtime.getJavaSupport().setObjectJavaClass(JavaClass.get(runtime, Object.class));
        runtime.getJavaSupport().setActive(true);
        return javaModule;
    }
    
    private static void addNameClassMappings(final Ruby runtime, final Map<String, JavaClass> nameClassMap) {
        final JavaClass booleanPrimClass = JavaClass.get(runtime, Boolean.TYPE);
        final JavaClass booleanClass = JavaClass.get(runtime, Boolean.class);
        nameClassMap.put("boolean", booleanPrimClass);
        nameClassMap.put("Boolean", booleanClass);
        nameClassMap.put("java.lang.Boolean", booleanClass);
        final JavaClass bytePrimClass = JavaClass.get(runtime, Byte.TYPE);
        final JavaClass byteClass = JavaClass.get(runtime, Byte.class);
        nameClassMap.put("byte", bytePrimClass);
        nameClassMap.put("Byte", byteClass);
        nameClassMap.put("java.lang.Byte", byteClass);
        final JavaClass shortPrimClass = JavaClass.get(runtime, Short.TYPE);
        final JavaClass shortClass = JavaClass.get(runtime, Short.class);
        nameClassMap.put("short", shortPrimClass);
        nameClassMap.put("Short", shortClass);
        nameClassMap.put("java.lang.Short", shortClass);
        final JavaClass charPrimClass = JavaClass.get(runtime, Character.TYPE);
        final JavaClass charClass = JavaClass.get(runtime, Character.class);
        nameClassMap.put("char", charPrimClass);
        nameClassMap.put("Character", charClass);
        nameClassMap.put("Char", charClass);
        nameClassMap.put("java.lang.Character", charClass);
        final JavaClass intPrimClass = JavaClass.get(runtime, Integer.TYPE);
        final JavaClass intClass = JavaClass.get(runtime, Integer.class);
        nameClassMap.put("int", intPrimClass);
        nameClassMap.put("Integer", intClass);
        nameClassMap.put("Int", intClass);
        nameClassMap.put("java.lang.Integer", intClass);
        final JavaClass longPrimClass = JavaClass.get(runtime, Long.TYPE);
        final JavaClass longClass = JavaClass.get(runtime, Long.class);
        nameClassMap.put("long", longPrimClass);
        nameClassMap.put("Long", longClass);
        nameClassMap.put("java.lang.Long", longClass);
        final JavaClass floatPrimClass = JavaClass.get(runtime, Float.TYPE);
        final JavaClass floatClass = JavaClass.get(runtime, Float.class);
        nameClassMap.put("float", floatPrimClass);
        nameClassMap.put("Float", floatClass);
        nameClassMap.put("java.lang.Float", floatClass);
        final JavaClass doublePrimClass = JavaClass.get(runtime, Double.TYPE);
        final JavaClass doubleClass = JavaClass.get(runtime, Double.class);
        nameClassMap.put("double", doublePrimClass);
        nameClassMap.put("Double", doubleClass);
        nameClassMap.put("java.lang.Double", doubleClass);
        final JavaClass bigintClass = JavaClass.get(runtime, BigInteger.class);
        nameClassMap.put("big_int", bigintClass);
        nameClassMap.put("big_integer", bigintClass);
        nameClassMap.put("BigInteger", bigintClass);
        nameClassMap.put("java.math.BigInteger", bigintClass);
        final JavaClass bigdecimalClass = JavaClass.get(runtime, BigDecimal.class);
        nameClassMap.put("big_decimal", bigdecimalClass);
        nameClassMap.put("BigDecimal", bigdecimalClass);
        nameClassMap.put("java.math.BigDecimal", bigdecimalClass);
        final JavaClass objectClass = JavaClass.get(runtime, Object.class);
        nameClassMap.put("object", objectClass);
        nameClassMap.put("Object", objectClass);
        nameClassMap.put("java.lang.Object", objectClass);
        final JavaClass stringClass = JavaClass.get(runtime, String.class);
        nameClassMap.put("string", stringClass);
        nameClassMap.put("String", stringClass);
        nameClassMap.put("java.lang.String", stringClass);
    }
    
    public static IRubyObject create_proxy_class(final IRubyObject recv, final IRubyObject constant, final IRubyObject javaClass, final IRubyObject module) {
        final Ruby runtime = recv.getRuntime();
        if (!(module instanceof RubyModule)) {
            throw runtime.newTypeError(module, runtime.getModule());
        }
        final IRubyObject proxyClass = get_proxy_class(recv, javaClass);
        final RubyModule m = (RubyModule)module;
        final String constName = constant.asJavaString();
        final IRubyObject existing = m.getConstantNoConstMissing(constName);
        if (existing != null && existing != RubyBasicObject.UNDEF && existing != proxyClass) {
            runtime.getWarnings().warn("replacing " + existing + " with " + proxyClass + " in constant '" + constName + " on class/module " + m);
        }
        return ((RubyModule)module).setConstantQuiet(constant.asJavaString(), get_proxy_class(recv, javaClass));
    }
    
    public static IRubyObject get_java_class(final IRubyObject recv, final IRubyObject name) {
        try {
            return JavaClass.for_name(recv, name);
        }
        catch (Exception e) {
            recv.getRuntime().getJavaSupport().handleNativeException(e, null);
            return recv.getRuntime().getNil();
        }
    }
    
    public static IRubyObject getInstance(final Ruby runtime, final Object rawJavaObject) {
        if (rawJavaObject == null) {
            return runtime.getNil();
        }
        if (Java.OBJECT_PROXY_CACHE) {
            return runtime.getJavaSupport().getObjectProxyCache().getOrCreate(rawJavaObject, (RubyClass)getProxyClass(runtime, JavaClass.get(runtime, rawJavaObject.getClass())));
        }
        return allocateProxy(rawJavaObject, (RubyClass)getProxyClass(runtime, JavaClass.get(runtime, rawJavaObject.getClass())));
    }
    
    public static RubyModule getInterfaceModule(final Ruby runtime, final JavaClass javaClass) {
        if (!javaClass.javaClass().isInterface()) {
            throw runtime.newArgumentError(javaClass.toString() + " is not an interface");
        }
        RubyModule interfaceModule;
        if ((interfaceModule = javaClass.getProxyModule()) != null) {
            return interfaceModule;
        }
        javaClass.lockProxy();
        try {
            if ((interfaceModule = javaClass.getProxyModule()) == null) {
                interfaceModule = (RubyModule)runtime.getJavaSupport().getJavaInterfaceTemplate().dup();
                interfaceModule.fastSetInstanceVariable("@java_class", javaClass);
                javaClass.setupInterfaceModule(interfaceModule);
                final Class<?>[] extended = (Class<?>[])javaClass.javaClass().getInterfaces();
                int i = extended.length;
                while (--i >= 0) {
                    final JavaClass extendedClass = JavaClass.get(runtime, extended[i]);
                    final RubyModule extModule = getInterfaceModule(runtime, extendedClass);
                    interfaceModule.includeModule(extModule);
                }
                addToJavaPackageModule(interfaceModule, javaClass);
            }
        }
        finally {
            javaClass.unlockProxy();
        }
        return interfaceModule;
    }
    
    public static IRubyObject get_interface_module(final Ruby runtime, final IRubyObject javaClassObject) {
        JavaClass javaClass;
        if (javaClassObject instanceof RubyString) {
            javaClass = JavaClass.forNameVerbose(runtime, javaClassObject.asJavaString());
        }
        else {
            if (!(javaClassObject instanceof JavaClass)) {
                throw runtime.newArgumentError("expected JavaClass, got " + javaClassObject);
            }
            javaClass = (JavaClass)javaClassObject;
        }
        return getInterfaceModule(runtime, javaClass);
    }
    
    public static RubyClass getProxyClassForObject(final Ruby runtime, final Object object) {
        return (RubyClass)getProxyClass(runtime, JavaClass.get(runtime, object.getClass()));
    }
    
    public static RubyModule getProxyClass(final Ruby runtime, final JavaClass javaClass) {
        final Class<?> c = (Class<?>)javaClass.javaClass();
        RubyClass proxyClass;
        if ((proxyClass = javaClass.getProxyClass()) != null) {
            return proxyClass;
        }
        if (c.isInterface()) {
            return getInterfaceModule(runtime, javaClass);
        }
        javaClass.lockProxy();
        try {
            if ((proxyClass = javaClass.getProxyClass()) == null) {
                if (c.isArray()) {
                    proxyClass = createProxyClass(runtime, runtime.getJavaSupport().getArrayProxyClass(), javaClass, true);
                }
                else if (c.isPrimitive()) {
                    proxyClass = createProxyClass(runtime, runtime.getJavaSupport().getConcreteProxyClass(), javaClass, true);
                }
                else if (c == Object.class) {
                    proxyClass = createProxyClass(runtime, runtime.getJavaSupport().getConcreteProxyClass(), javaClass, true);
                    if (Java.NEW_STYLE_EXTENSION) {
                        proxyClass.getMetaClass().defineAnnotatedMethods(NewStyleExtensionInherited.class);
                    }
                    else {
                        proxyClass.getMetaClass().defineAnnotatedMethods(OldStyleExtensionInherited.class);
                    }
                    addToJavaPackageModule(proxyClass, javaClass);
                }
                else {
                    proxyClass = createProxyClass(runtime, (RubyClass)getProxyClass(runtime, JavaClass.get(runtime, c.getSuperclass())), javaClass, false);
                    final Class<?>[] interfaces = c.getInterfaces();
                    int i = interfaces.length;
                    while (--i >= 0) {
                        final JavaClass ifc = JavaClass.get(runtime, interfaces[i]);
                        proxyClass.includeModule(getInterfaceModule(runtime, ifc));
                    }
                    if (Modifier.isPublic(c.getModifiers())) {
                        addToJavaPackageModule(proxyClass, javaClass);
                    }
                }
                if (Modifier.isFinal(c.getModifiers())) {
                    proxyClass.getMetaClass().addMethod("inherited", new org.jruby.internal.runtime.methods.JavaMethod(proxyClass, Visibility.PUBLIC) {
                        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                            throw context.getRuntime().newTypeError("can not extend final Java class: " + c.getCanonicalName());
                        }
                    });
                }
            }
        }
        finally {
            javaClass.unlockProxy();
        }
        return proxyClass;
    }
    
    public static IRubyObject get_proxy_class(final IRubyObject recv, final IRubyObject java_class_object) {
        final Ruby runtime = recv.getRuntime();
        JavaClass javaClass;
        if (java_class_object instanceof RubyString) {
            javaClass = JavaClass.for_name(recv, java_class_object);
        }
        else {
            if (!(java_class_object instanceof JavaClass)) {
                throw runtime.newTypeError(java_class_object, runtime.getJavaSupport().getJavaClassClass());
            }
            javaClass = (JavaClass)java_class_object;
        }
        return getProxyClass(runtime, javaClass);
    }
    
    private static RubyClass createProxyClass(final Ruby runtime, final RubyClass baseType, final JavaClass javaClass, final boolean invokeInherited) {
        RubyClass proxyClass = javaClass.getProxyClass();
        if (proxyClass != null) {
            return proxyClass;
        }
        RubyClass.checkInheritable(baseType);
        proxyClass = RubyClass.newClass(runtime, baseType);
        proxyClass.makeMetaClass(baseType.getMetaClass());
        try {
            javaClass.javaClass().asSubclass(Map.class);
            proxyClass.setAllocator(runtime.getJavaSupport().getMapJavaProxyClass().getAllocator());
            proxyClass.defineAnnotatedMethods(MapJavaProxy.class);
            proxyClass.includeModule(runtime.getEnumerable());
        }
        catch (ClassCastException e) {
            proxyClass.setAllocator(baseType.getAllocator());
        }
        if (invokeInherited) {
            proxyClass.inherit(baseType);
        }
        proxyClass.callMethod(runtime.getCurrentContext(), "java_class=", javaClass);
        javaClass.setupProxy(proxyClass);
        proxyClass.defineAnnotatedMethods(JavaProxyClassMethods.class);
        return proxyClass;
    }
    
    private static IRubyObject getRubyMethod(final ThreadContext context, final IRubyObject proxyClass, final String name, final Class... argTypesClasses) {
        final Ruby runtime = context.getRuntime();
        if (!(proxyClass instanceof RubyClass)) {
            throw runtime.newTypeError(proxyClass, runtime.getModule());
        }
        final RubyClass rubyClass = (RubyClass)proxyClass;
        final Method jmethod = getMethodFromClass(runtime, proxyClass, name, argTypesClasses);
        final String prettyName = name + CodegenUtils.prettyParams(argTypesClasses);
        if (Modifier.isStatic(jmethod.getModifiers())) {
            final MethodInvoker invoker = new StaticMethodInvoker(rubyClass, jmethod);
            return RubyMethod.newMethod(rubyClass, prettyName, rubyClass, name, invoker, proxyClass);
        }
        final MethodInvoker invoker = new InstanceMethodInvoker(rubyClass, jmethod);
        return RubyUnboundMethod.newUnboundMethod(rubyClass, prettyName, rubyClass, name, invoker);
    }
    
    public static Method getMethodFromClass(final Ruby runtime, final IRubyObject proxyClass, final String name, final Class... argTypes) {
        final Class jclass = (Class)((JavaClass)proxyClass.callMethod(runtime.getCurrentContext(), "java_class")).getValue();
        try {
            return jclass.getMethod(name, (Class[])argTypes);
        }
        catch (NoSuchMethodException nsme) {
            final String prettyName = name + CodegenUtils.prettyParams(argTypes);
            final String errorName = jclass.getName() + "." + prettyName;
            throw runtime.newNameError("Java method not found: " + errorName, name);
        }
    }
    
    private static MethodInvoker getMethodInvokerForMethod(final RubyClass metaClass, final Method method) {
        if (Modifier.isStatic(method.getModifiers())) {
            return new StaticMethodInvoker(metaClass.getMetaClass(), method);
        }
        return new InstanceMethodInvoker(metaClass, method);
    }
    
    public static IRubyObject concrete_proxy_inherited(final IRubyObject recv, final IRubyObject subclass) {
        final Ruby runtime = recv.getRuntime();
        final ThreadContext tc = runtime.getCurrentContext();
        final JavaSupport javaSupport = runtime.getJavaSupport();
        final RubyClass javaProxyClass = javaSupport.getJavaProxyClass().getMetaClass();
        RuntimeHelpers.invokeAs(tc, javaProxyClass, recv, "inherited", subclass, Block.NULL_BLOCK);
        return setupJavaSubclass(tc, subclass, recv.callMethod(tc, "java_class"));
    }
    
    private static IRubyObject setupJavaSubclass(final ThreadContext context, final IRubyObject subclass, final IRubyObject java_class) {
        final Ruby runtime = context.getRuntime();
        if (!(subclass instanceof RubyClass)) {
            throw runtime.newTypeError(subclass, runtime.getClassClass());
        }
        final RubyClass rubySubclass = (RubyClass)subclass;
        rubySubclass.getInstanceVariables().fastSetInstanceVariable("@java_proxy_class", runtime.getNil());
        final RubyClass subclassSingleton = rubySubclass.getSingletonClass();
        subclassSingleton.addReadWriteAttribute(context, "java_proxy_class");
        subclassSingleton.addMethod("java_interfaces", new org.jruby.internal.runtime.methods.JavaMethod.JavaMethodZero(subclassSingleton, Visibility.PUBLIC) {
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
                final IRubyObject javaInterfaces = self.getInstanceVariables().fastGetInstanceVariable("@java_interfaces");
                if (javaInterfaces != null) {
                    return javaInterfaces.dup();
                }
                return context.getRuntime().getNil();
            }
        });
        rubySubclass.addMethod("__jcreate!", new org.jruby.internal.runtime.methods.JavaMethod.JavaMethodN(subclassSingleton, Visibility.PUBLIC) {
            private final Map<Integer, ParameterTypes> methodCache = new HashMap<Integer, ParameterTypes>();
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
                IRubyObject proxyClass = self.getMetaClass().getInstanceVariables().fastGetInstanceVariable("@java_proxy_class");
                if (proxyClass == null || proxyClass.isNil()) {
                    proxyClass = JavaProxyClass.get_with_class(self, self.getMetaClass());
                    self.getMetaClass().getInstanceVariables().fastSetInstanceVariable("@java_proxy_class", proxyClass);
                }
                final JavaProxyClass realProxyClass = (JavaProxyClass)proxyClass;
                final RubyArray constructors = realProxyClass.constructors();
                final ArrayList<JavaProxyConstructor> forArity = new ArrayList<JavaProxyConstructor>();
                for (int i = 0; i < constructors.size(); ++i) {
                    final JavaProxyConstructor constructor = (JavaProxyConstructor)constructors.eltInternal(i);
                    if (constructor.getParameterTypes().length == args.length) {
                        forArity.add(constructor);
                    }
                }
                if (forArity.size() == 0) {
                    throw context.getRuntime().newArgumentError("wrong number of arguments for constructor");
                }
                final JavaProxyConstructor matching = (JavaProxyConstructor)CallableSelector.matchingCallableArityN(this.methodCache, forArity.toArray(new JavaProxyConstructor[forArity.size()]), args, args.length);
                if (matching == null) {
                    throw context.getRuntime().newArgumentError("wrong number of arguments for constructor");
                }
                final Object[] newArgs = new Object[args.length];
                final Class[] parameterTypes = matching.getParameterTypes();
                for (int j = 0; j < args.length; ++j) {
                    newArgs[j] = args[j].toJava(parameterTypes[j]);
                }
                final JavaObject newObject = matching.newInstance(self, newArgs);
                return JavaUtilities.set_java_object(self, self, newObject);
            }
        });
        return runtime.getNil();
    }
    
    private static void addToJavaPackageModule(final RubyModule proxyClass, final JavaClass javaClass) {
        final Ruby runtime = proxyClass.getRuntime();
        final Class<?> clazz = (Class<?>)javaClass.javaClass();
        final String fullName;
        if ((fullName = clazz.getName()) == null) {
            return;
        }
        final int endPackage = fullName.lastIndexOf(46);
        RubyModule parentModule;
        String className;
        if (fullName.indexOf(36) != -1) {
            final IRubyObject declClass = javaClass.declaring_class();
            if (declClass.isNil()) {
                return;
            }
            parentModule = getProxyClass(runtime, (JavaClass)declClass);
            className = clazz.getSimpleName();
        }
        else {
            final String packageString = (endPackage < 0) ? "" : fullName.substring(0, endPackage);
            parentModule = getJavaPackageModule(runtime, packageString);
            className = ((parentModule == null) ? fullName : fullName.substring(endPackage + 1));
        }
        if (parentModule != null && IdUtil.isConstant(className) && parentModule.getConstantAt(className) == null) {
            parentModule.setConstant(className, proxyClass);
        }
    }
    
    private static RubyModule getJavaPackageModule(final Ruby runtime, final String packageString) {
        final int length = packageString.length();
        String packageName;
        if (length == 0) {
            packageName = "Default";
        }
        else {
            final StringBuilder buf = new StringBuilder();
            for (int start = 0, offset = 0; start < length; start = offset + 1) {
                if ((offset = packageString.indexOf(46, start)) == -1) {
                    offset = length;
                }
                buf.append(Character.toUpperCase(packageString.charAt(start))).append(packageString.substring(start + 1, offset));
            }
            packageName = buf.toString();
        }
        final RubyModule javaModule = runtime.getJavaSupport().getJavaModule();
        final IRubyObject packageModule = javaModule.getConstantAt(packageName);
        if (packageModule == null) {
            return createPackageModule(javaModule, packageName, packageString);
        }
        if (packageModule instanceof RubyModule) {
            return (RubyModule)packageModule;
        }
        return null;
    }
    
    private static RubyModule createPackageModule(final RubyModule parent, final String name, final String packageString) {
        final Ruby runtime = parent.getRuntime();
        final RubyModule packageModule = (RubyModule)runtime.getJavaSupport().getPackageModuleTemplate().dup();
        packageModule.fastSetInstanceVariable("@package_name", runtime.newString((packageString.length() > 0) ? (packageString + '.') : packageString));
        packageModule.addClassProvider(Java.JAVA_PACKAGE_CLASS_PROVIDER);
        parent.const_set(runtime.newSymbol(name), packageModule);
        final MetaClass metaClass = (MetaClass)packageModule.getMetaClass();
        metaClass.setAttached(packageModule);
        return packageModule;
    }
    
    private static RubyModule getPackageModule(final Ruby runtime, final String name) {
        final RubyModule javaModule = runtime.getJavaSupport().getJavaModule();
        final IRubyObject value;
        if ((value = javaModule.getConstantAt(name)) instanceof RubyModule) {
            return (RubyModule)value;
        }
        String packageName;
        if ("Default".equals(name)) {
            packageName = "";
        }
        else {
            final Matcher m = Java.CAMEL_CASE_PACKAGE_SPLITTER.matcher(name);
            packageName = m.replaceAll("$1.$2").toLowerCase();
        }
        return createPackageModule(javaModule, name, packageName);
    }
    
    public static IRubyObject get_package_module(final IRubyObject recv, final IRubyObject symObject) {
        return getPackageModule(recv.getRuntime(), symObject.asJavaString());
    }
    
    public static IRubyObject get_package_module_dot_format(final IRubyObject recv, final IRubyObject dottedName) {
        final Ruby runtime = recv.getRuntime();
        final RubyModule module = getJavaPackageModule(runtime, dottedName.asJavaString());
        return (module == null) ? runtime.getNil() : module;
    }
    
    private static RubyModule getProxyOrPackageUnderPackage(final ThreadContext context, final Ruby runtime, final RubyModule parentPackage, final String sym) {
        final IRubyObject packageNameObj = parentPackage.fastGetInstanceVariable("@package_name");
        if (packageNameObj == null) {
            throw runtime.newArgumentError("invalid package module");
        }
        final String packageName = packageNameObj.asJavaString();
        final String name = sym.trim().intern();
        if (name.length() == 0) {
            throw runtime.newArgumentError("empty class or package name");
        }
        final String fullName = packageName + name;
        if (Character.isUpperCase(name.charAt(0))) {
            final RubyModule javaModule = getProxyClass(runtime, JavaClass.forNameVerbose(runtime, fullName));
            memoizePackageOrClass(parentPackage, name, javaModule);
            return javaModule;
        }
        if (Java.JAVA_PRIMITIVES.containsKey(name)) {
            throw runtime.newArgumentError("illegal package name component: " + name);
        }
        try {
            return getProxyClass(runtime, JavaClass.forNameQuiet(runtime, fullName));
        }
        catch (RaiseException re) {
            final RubyException rubyEx = re.getException();
            if (rubyEx.kind_of_p(context, runtime.getStandardError()).isTrue()) {
                RuntimeHelpers.setErrorInfo(runtime, runtime.getNil());
            }
        }
        catch (Exception ex) {}
        final RubyModule packageModule = getJavaPackageModule(runtime, fullName);
        if (packageModule == null) {
            return null;
        }
        memoizePackageOrClass(parentPackage, name, packageModule);
        return packageModule;
    }
    
    public static IRubyObject get_proxy_or_package_under_package(final ThreadContext context, final IRubyObject recv, final IRubyObject parentPackage, final IRubyObject sym) {
        final Ruby runtime = recv.getRuntime();
        if (!(parentPackage instanceof RubyModule)) {
            throw runtime.newTypeError(parentPackage, runtime.getModule());
        }
        final RubyModule result;
        if ((result = getProxyOrPackageUnderPackage(context, runtime, (RubyModule)parentPackage, sym.asJavaString())) != null) {
            return result;
        }
        return runtime.getNil();
    }
    
    private static RubyModule getTopLevelProxyOrPackage(final ThreadContext context, final Ruby runtime, final String sym) {
        final String name = sym.trim().intern();
        if (name.length() == 0) {
            throw runtime.newArgumentError("empty class or package name");
        }
        if (!Character.isLowerCase(name.charAt(0))) {
            RubyModule javaModule = null;
            try {
                javaModule = getProxyClass(runtime, JavaClass.forNameQuiet(runtime, name));
            }
            catch (RaiseException re) {
                final RubyException rubyEx = re.getException();
                if (rubyEx.kind_of_p(context, runtime.getStandardError()).isTrue()) {
                    RuntimeHelpers.setErrorInfo(runtime, runtime.getNil());
                }
            }
            catch (Exception ex) {}
            if (javaModule == null) {
                javaModule = getPackageModule(runtime, name);
            }
            memoizePackageOrClass(runtime.getJavaSupport().getJavaModule(), name, javaModule);
            return javaModule;
        }
        try {
            return getProxyClass(runtime, JavaClass.forNameQuiet(runtime, name));
        }
        catch (RaiseException re2) {
            final RubyException rubyEx2 = re2.getException();
            if (rubyEx2.kind_of_p(context, runtime.getStandardError()).isTrue()) {
                RuntimeHelpers.setErrorInfo(runtime, runtime.getNil());
            }
        }
        catch (Exception ex2) {}
        final RubyModule packageModule = getJavaPackageModule(runtime, name);
        if (packageModule == null) {
            return null;
        }
        final RubyModule javaModule2 = runtime.getJavaSupport().getJavaModule();
        if (javaModule2.getMetaClass().isMethodBound(name, false)) {
            return packageModule;
        }
        memoizePackageOrClass(javaModule2, name, packageModule);
        return packageModule;
    }
    
    private static void memoizePackageOrClass(final RubyModule parentPackage, final String name, final IRubyObject value) {
        final RubyClass singleton = parentPackage.getSingletonClass();
        singleton.addMethod(name, new org.jruby.internal.runtime.methods.JavaMethod(singleton, Visibility.PUBLIC) {
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                if (args.length != 0) {
                    throw context.getRuntime().newArgumentError("Java package `" + parentPackage.callMethod("package_name") + "' does not have a method `" + name + "'");
                }
                return this.call(context, self, clazz, name);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
                return value;
            }
            
            public Arity getArity() {
                return Arity.noArguments();
            }
        });
    }
    
    public static IRubyObject get_top_level_proxy_or_package(final ThreadContext context, final IRubyObject recv, final IRubyObject sym) {
        final Ruby runtime = context.getRuntime();
        final RubyModule result = getTopLevelProxyOrPackage(context, runtime, sym.asJavaString());
        return (result != null) ? result : runtime.getNil();
    }
    
    public static IRubyObject wrap(final Ruby runtime, final IRubyObject java_object) {
        return getInstance(runtime, ((JavaObject)java_object).getValue());
    }
    
    @Deprecated
    @JRubyMethod(frame = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject java_to_ruby(final IRubyObject recv, final IRubyObject object, final Block unusedBlock) {
        try {
            return JavaUtil.java_to_ruby(recv.getRuntime(), object);
        }
        catch (RuntimeException e) {
            recv.getRuntime().getJavaSupport().handleNativeException(e, null);
            return recv.getRuntime().getNil();
        }
    }
    
    @Deprecated
    @JRubyMethod(frame = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject ruby_to_java(final IRubyObject recv, final IRubyObject object, final Block unusedBlock) {
        return JavaUtil.ruby_to_java(recv, object, unusedBlock);
    }
    
    @Deprecated
    @JRubyMethod(frame = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject java_to_primitive(final IRubyObject recv, final IRubyObject object, final Block unusedBlock) {
        return JavaUtil.java_to_primitive(recv, object, unusedBlock);
    }
    
    @JRubyMethod(required = 2, frame = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject new_proxy_instance2(final IRubyObject recv, final IRubyObject wrapper, final IRubyObject ifcs, final Block block) {
        final IRubyObject[] javaClasses = ((RubyArray)ifcs).toJavaArray();
        final Class[] interfaces = new Class[javaClasses.length];
        for (int i = 0; i < javaClasses.length; ++i) {
            if (!(javaClasses[i] instanceof JavaClass) || !((JavaClass)javaClasses[i]).interface_p().isTrue()) {
                throw recv.getRuntime().newArgumentError("Java interface expected. got: " + javaClasses[i]);
            }
            interfaces[i] = ((JavaClass)javaClasses[i]).javaClass();
        }
        return newInterfaceImpl(wrapper, interfaces);
    }
    
    public static IRubyObject newInterfaceImpl(final IRubyObject wrapper, Class[] interfaces) {
        final Ruby runtime = wrapper.getRuntime();
        final Class[] tmp_interfaces = interfaces;
        interfaces = (Class<?>[])new Class[tmp_interfaces.length + 1];
        System.arraycopy(tmp_interfaces, 0, interfaces, 0, tmp_interfaces.length);
        interfaces[tmp_interfaces.length] = RubyObjectHolderProxy.class;
        if (!RubyInstanceConfig.INTERFACES_USE_PROXY) {
            int interfacesHashCode = interfacesHashCode(interfaces);
            if (wrapper.getMetaClass().isSingleton() && wrapper.getMetaClass().getRealClass() == runtime.getProc()) {
                interfacesHashCode = 31 * interfacesHashCode + runtime.getProc().hashCode();
            }
            else {
                interfacesHashCode = 31 * interfacesHashCode + wrapper.getMetaClass().getRealClass().hashCode();
            }
            final String implClassName = "org.jruby.gen.InterfaceImpl" + Math.abs(interfacesHashCode);
            Class proxyImplClass;
            try {
                proxyImplClass = Class.forName(implClassName, true, runtime.getJRubyClassLoader());
            }
            catch (ClassNotFoundException cnfe) {
                final ClassCache.OneShotClassLoader oneShotClassLoader = new ClassCache.OneShotClassLoader(runtime.getJRubyClassLoader());
                proxyImplClass = RealClassGenerator.createOldStyleImplClass(interfaces, wrapper.getMetaClass(), runtime, implClassName, oneShotClassLoader);
            }
            try {
                final Constructor proxyConstructor = proxyImplClass.getConstructor(IRubyObject.class);
                return JavaObject.wrap(runtime, proxyConstructor.newInstance(wrapper));
            }
            catch (NoSuchMethodException nsme) {
                throw runtime.newTypeError("Exception instantiating generated interface impl:\n" + nsme);
            }
            catch (InvocationTargetException ite) {
                throw runtime.newTypeError("Exception instantiating generated interface impl:\n" + ite);
            }
            catch (InstantiationException ie) {
                throw runtime.newTypeError("Exception instantiating generated interface impl:\n" + ie);
            }
            catch (IllegalAccessException iae) {
                throw runtime.newTypeError("Exception instantiating generated interface impl:\n" + iae);
            }
        }
        final Object proxyObject = Proxy.newProxyInstance(runtime.getJRubyClassLoader(), interfaces, new InvocationHandler() {
            private Map parameterTypeCache = new ConcurrentHashMap();
            
            public Object invoke(final Object proxy, final Method method, final Object[] nargs) throws Throwable {
                final String methodName = method.getName();
                final int length = (nargs == null) ? 0 : nargs.length;
                if (methodName == "toString" && length == 0) {
                    return proxy.getClass().getName();
                }
                if (methodName == "hashCode" && length == 0) {
                    return proxy.getClass().hashCode();
                }
                if (methodName == "equals" && length == 1) {
                    Class[] parameterTypes = this.parameterTypeCache.get(method);
                    if (parameterTypes == null) {
                        parameterTypes = method.getParameterTypes();
                        this.parameterTypeCache.put(method, parameterTypes);
                    }
                    if (parameterTypes[0].equals(Object.class)) {
                        return proxy == nargs[0];
                    }
                }
                else if (methodName == "__ruby_object" && length == 0) {
                    return wrapper;
                }
                final IRubyObject[] rubyArgs = JavaUtil.convertJavaArrayToRuby(runtime, nargs);
                try {
                    return RuntimeHelpers.invoke(runtime.getCurrentContext(), wrapper, methodName, rubyArgs).toJava(method.getReturnType());
                }
                catch (RuntimeException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        });
        return JavaObject.wrap(runtime, proxyObject);
    }
    
    public static Class generateRealClass(final RubyClass clazz) {
        final Ruby runtime = clazz.getRuntime();
        final Class[] interfaces = getInterfacesFromRubyClass(clazz);
        int interfacesHashCode = interfacesHashCode(interfaces);
        interfacesHashCode = 31 * interfacesHashCode + clazz.hashCode();
        String implClassName;
        if (clazz.getBaseName() == null) {
            implClassName = "anon_class" + Math.abs(System.identityHashCode(clazz)) + "_" + Math.abs(interfacesHashCode);
        }
        else {
            implClassName = clazz.getName().replaceAll("::", "\\$\\$") + "_" + Math.abs(interfacesHashCode);
        }
        Class proxyImplClass;
        try {
            proxyImplClass = Class.forName(implClassName, true, runtime.getJRubyClassLoader());
        }
        catch (ClassNotFoundException cnfe) {
            Class superClass = clazz.getSuperClass().getRealClass().getReifiedClass();
            if (superClass == null) {
                superClass = RubyObject.class;
            }
            proxyImplClass = RealClassGenerator.createRealImplClass(superClass, interfaces, clazz, runtime, implClassName);
            if (Java.NEW_STYLE_EXTENSION && !RubyBasicObject.class.isAssignableFrom(proxyImplClass) && !clazz.getMethods().containsKey("initialize")) {
                clazz.addMethod("initialize", new org.jruby.internal.runtime.methods.JavaMethod.JavaMethodZero(clazz, Visibility.PUBLIC) {
                    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
                        return context.getRuntime().getNil();
                    }
                });
            }
        }
        clazz.setReifiedClass(proxyImplClass);
        clazz.setRubyClassAllocator(proxyImplClass);
        return proxyImplClass;
    }
    
    public static Constructor getRealClassConstructor(final Ruby runtime, final Class proxyImplClass) {
        try {
            return proxyImplClass.getConstructor(Ruby.class, RubyClass.class);
        }
        catch (NoSuchMethodException nsme) {
            throw runtime.newTypeError("Exception instantiating generated interface impl:\n" + nsme);
        }
    }
    
    public static IRubyObject constructProxy(final Ruby runtime, final Constructor proxyConstructor, final RubyClass clazz) {
        try {
            return proxyConstructor.newInstance(runtime, clazz);
        }
        catch (InvocationTargetException ite) {
            ite.printStackTrace();
            throw runtime.newTypeError("Exception instantiating generated interface impl:\n" + ite);
        }
        catch (InstantiationException ie) {
            throw runtime.newTypeError("Exception instantiating generated interface impl:\n" + ie);
        }
        catch (IllegalAccessException iae) {
            throw runtime.newTypeError("Exception instantiating generated interface impl:\n" + iae);
        }
    }
    
    public static IRubyObject allocateProxy(final Object javaObject, final RubyClass clazz) {
        final IRubyObject proxy = clazz.allocate();
        if (proxy instanceof JavaProxy) {
            ((JavaProxy)proxy).setObject(javaObject);
        }
        else {
            final JavaObject wrappedObject = JavaObject.wrap(clazz.getRuntime(), javaObject);
            proxy.dataWrapStruct(wrappedObject);
        }
        return proxy;
    }
    
    public static IRubyObject wrapJavaObject(final Ruby runtime, final Object object) {
        return allocateProxy(object, getProxyClassForObject(runtime, object));
    }
    
    public static Class[] getInterfacesFromRubyClass(RubyClass klass) {
        final Set<Class> interfaces = new HashSet<Class>();
        while (klass != null) {
            final IRubyObject maybeInterfaces = klass.getInstanceVariables().getInstanceVariable("@java_interfaces");
            if (maybeInterfaces instanceof RubyArray) {
                final RubyArray moreInterfaces = (RubyArray)maybeInterfaces;
                if (!moreInterfaces.isFrozen()) {
                    moreInterfaces.setFrozen(true);
                }
                interfaces.addAll(moreInterfaces);
            }
            klass = klass.getSuperClass();
        }
        return interfaces.toArray(new Class[interfaces.size()]);
    }
    
    private static int interfacesHashCode(final Class[] a) {
        if (a == null) {
            return 0;
        }
        int result = 1;
        for (final Class element : a) {
            result = 31 * result + ((element == null) ? 0 : element.hashCode());
        }
        return result;
    }
    
    static {
        NEW_STYLE_EXTENSION = SafePropertyAccessor.getBoolean("jruby.ji.newStyleExtension", false);
        OBJECT_PROXY_CACHE = SafePropertyAccessor.getBoolean("jruby.ji.objectProxyCache", true);
        JAVA_PACKAGE_CLASS_PROVIDER = new ClassProvider() {
            public RubyClass defineClassUnder(final RubyModule pkg, final String name, final RubyClass superClazz) {
                if (superClazz != null) {
                    return null;
                }
                final IRubyObject packageName;
                if ((packageName = pkg.getInstanceVariables().fastGetInstanceVariable("@package_name")) == null) {
                    return null;
                }
                final Ruby runtime = pkg.getRuntime();
                return (RubyClass)Java.get_proxy_class(runtime.getJavaSupport().getJavaUtilitiesModule(), JavaClass.forNameVerbose(runtime, packageName.asJavaString() + name));
            }
            
            public RubyModule defineModuleUnder(final RubyModule pkg, final String name) {
                final IRubyObject packageName;
                if ((packageName = pkg.getInstanceVariables().fastGetInstanceVariable("@package_name")) == null) {
                    return null;
                }
                final Ruby runtime = pkg.getRuntime();
                return (RubyModule)Java.get_interface_module(runtime, JavaClass.forNameVerbose(runtime, packageName.asJavaString() + name));
            }
        };
        JAVA_PRIMITIVES = new HashMap<String, Boolean>();
        final String[] arr$;
        final String[] primitives = arr$ = new String[] { "boolean", "byte", "char", "short", "int", "long", "float", "double" };
        for (final String primitive : arr$) {
            Java.JAVA_PRIMITIVES.put(primitive, Boolean.TRUE);
        }
        CAMEL_CASE_PACKAGE_SPLITTER = Pattern.compile("([a-z][0-9]*)([A-Z])");
    }
    
    public static class OldStyleExtensionInherited
    {
        @JRubyMethod
        public static IRubyObject inherited(final IRubyObject recv, final IRubyObject arg0) {
            return Java.concrete_proxy_inherited(recv, arg0);
        }
    }
    
    public static class NewStyleExtensionInherited
    {
        @JRubyMethod
        public static IRubyObject inherited(final IRubyObject recv, final IRubyObject arg0) {
            if (!(arg0 instanceof RubyClass)) {
                throw recv.getRuntime().newTypeError(arg0, recv.getRuntime().getClassClass());
            }
            JavaInterfaceTemplate.addRealImplClassNew((RubyClass)arg0);
            return recv.getRuntime().getNil();
        }
    }
    
    public static class JavaProxyClassMethods
    {
        @JRubyMethod(backtrace = true, meta = true)
        public static IRubyObject java_method(final ThreadContext context, final IRubyObject proxyClass, final IRubyObject rubyName) {
            final String name = rubyName.asJavaString();
            return getRubyMethod(context, proxyClass, name, new Class[0]);
        }
        
        @JRubyMethod(backtrace = true, meta = true)
        public static IRubyObject java_method(final ThreadContext context, final IRubyObject proxyClass, final IRubyObject rubyName, final IRubyObject argTypes) {
            final String name = rubyName.asJavaString();
            final RubyArray argTypesAry = argTypes.convertToArray();
            final Class[] argTypesClasses = (Class[])argTypesAry.toArray(new Class[argTypesAry.size()]);
            return getRubyMethod(context, proxyClass, name, argTypesClasses);
        }
        
        @JRubyMethod(backtrace = true, meta = true)
        public static IRubyObject java_send(final ThreadContext context, final IRubyObject recv, final IRubyObject rubyName) {
            final String name = rubyName.asJavaString();
            final Ruby runtime = context.getRuntime();
            final JavaMethod method = new JavaMethod(runtime, Java.getMethodFromClass(runtime, recv, name, new Class[0]));
            return method.invokeStaticDirect();
        }
        
        @JRubyMethod(backtrace = true, meta = true)
        public static IRubyObject java_send(final ThreadContext context, final IRubyObject recv, final IRubyObject rubyName, final IRubyObject argTypes) {
            final String name = rubyName.asJavaString();
            final RubyArray argTypesAry = argTypes.convertToArray();
            final Ruby runtime = context.getRuntime();
            if (argTypesAry.size() != 0) {
                final Class[] argTypesClasses = (Class[])argTypesAry.toArray(new Class[argTypesAry.size()]);
                throw JavaMethod.newArgSizeMismatchError(runtime, argTypesClasses);
            }
            final JavaMethod method = new JavaMethod(runtime, Java.getMethodFromClass(runtime, recv, name, new Class[0]));
            return method.invokeStaticDirect();
        }
        
        @JRubyMethod(backtrace = true, meta = true)
        public static IRubyObject java_send(final ThreadContext context, final IRubyObject recv, final IRubyObject rubyName, final IRubyObject argTypes, final IRubyObject arg0) {
            final String name = rubyName.asJavaString();
            final RubyArray argTypesAry = argTypes.convertToArray();
            final Ruby runtime = context.getRuntime();
            if (argTypesAry.size() != 1) {
                throw JavaMethod.newArgSizeMismatchError(runtime, (Class)argTypesAry.eltInternal(0).toJava(Class.class));
            }
            final Class argTypeClass = (Class)argTypesAry.eltInternal(0).toJava(Class.class);
            final JavaMethod method = new JavaMethod(runtime, Java.getMethodFromClass(runtime, recv, name, argTypeClass));
            return method.invokeStaticDirect(arg0.toJava(argTypeClass));
        }
        
        @JRubyMethod(required = 4, rest = true, backtrace = true, meta = true)
        public static IRubyObject java_send(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
            final Ruby runtime = context.getRuntime();
            final String name = args[0].asJavaString();
            final RubyArray argTypesAry = args[1].convertToArray();
            final int argsLen = args.length - 2;
            if (argTypesAry.size() != argsLen) {
                throw JavaMethod.newArgSizeMismatchError(runtime, (Class[])argTypesAry.toArray(new Class[argTypesAry.size()]));
            }
            final Class[] argTypesClasses = (Class[])argTypesAry.toArray(new Class[argsLen]);
            final Object[] argsAry = new Object[argsLen];
            for (int i = 0; i < argsLen; ++i) {
                argsAry[i] = args[i + 2].toJava(argTypesClasses[i]);
            }
            final JavaMethod method = new JavaMethod(runtime, Java.getMethodFromClass(runtime, recv, name, argTypesClasses));
            return method.invokeStaticDirect(argsAry);
        }
        
        @JRubyMethod(backtrace = true, meta = true, visibility = Visibility.PRIVATE)
        public static IRubyObject java_alias(final ThreadContext context, final IRubyObject proxyClass, final IRubyObject newName, final IRubyObject rubyName) {
            return java_alias(context, proxyClass, newName, rubyName, context.getRuntime().newEmptyArray());
        }
        
        @JRubyMethod(backtrace = true, meta = true, visibility = Visibility.PRIVATE)
        public static IRubyObject java_alias(final ThreadContext context, final IRubyObject proxyClass, final IRubyObject newName, final IRubyObject rubyName, final IRubyObject argTypes) {
            final String name = rubyName.asJavaString();
            final String newNameStr = newName.asJavaString();
            final RubyArray argTypesAry = argTypes.convertToArray();
            final Class[] argTypesClasses = (Class[])argTypesAry.toArray(new Class[argTypesAry.size()]);
            final Ruby runtime = context.getRuntime();
            if (proxyClass instanceof RubyClass) {
                final RubyClass rubyClass = (RubyClass)proxyClass;
                final Method method = Java.getMethodFromClass(runtime, proxyClass, name, argTypesClasses);
                final MethodInvoker invoker = getMethodInvokerForMethod(rubyClass, method);
                if (Modifier.isStatic(method.getModifiers())) {
                    rubyClass.getSingletonClass().addMethod(newNameStr, invoker);
                }
                else {
                    rubyClass.addMethod(newNameStr, invoker);
                }
                return runtime.getNil();
            }
            throw runtime.newTypeError(proxyClass, runtime.getModule());
        }
    }
}
