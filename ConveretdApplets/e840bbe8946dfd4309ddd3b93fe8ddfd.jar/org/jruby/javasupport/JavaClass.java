// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.java.invokers.InstanceMethodInvoker;
import org.jruby.java.invokers.SingletonMethodInvoker;
import org.jruby.java.invokers.StaticMethodInvoker;
import org.jruby.java.invokers.ConstructorInvoker;
import org.jruby.java.invokers.InstanceFieldSetter;
import org.jruby.java.invokers.InstanceFieldGetter;
import org.jruby.java.invokers.StaticFieldSetter;
import org.jruby.java.invokers.StaticFieldGetter;
import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import java.util.Collections;
import org.jruby.util.SafePropertyAccessor;
import java.security.Permission;
import java.security.AccessController;
import java.lang.reflect.ReflectPermission;
import org.jruby.RubyInstanceConfig;
import java.util.Collection;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.Arrays;
import org.jruby.exceptions.RaiseException;
import org.jruby.java.addons.ArrayJavaAddons;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyFixnum;
import org.jruby.java.proxies.ArrayJavaProxy;
import org.jruby.RubyInteger;
import java.lang.reflect.Array;
import org.jruby.java.proxies.ConcreteJavaProxy;
import java.io.InputStream;
import org.jruby.util.ByteList;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import org.jruby.RubyString;
import org.jruby.RubyBoolean;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyMethod;
import org.jruby.common.IRubyWarnings;
import java.lang.reflect.Constructor;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.runtime.Visibility;
import org.jruby.util.IdUtil;
import java.util.Iterator;
import org.jruby.Ruby;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.jruby.runtime.callback.Callback;
import java.util.concurrent.locks.ReentrantLock;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.ArrayList;
import org.jruby.RubyArray;
import java.util.List;
import org.jruby.RubyModule;
import java.util.Map;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Java::JavaClass" }, parent = "Java::JavaObject")
public class JavaClass extends JavaObject
{
    public static final String METHOD_MANGLE = "__method";
    public static final boolean DEBUG_SCALA = false;
    public static final boolean CAN_SET_ACCESSIBLE;
    private static final Map<String, AssignedName> RESERVED_NAMES;
    private static final Map<String, AssignedName> STATIC_RESERVED_NAMES;
    private static final Map<String, AssignedName> INSTANCE_RESERVED_NAMES;
    private final RubyModule JAVA_UTILITIES;
    private Map<String, AssignedName> staticAssignedNames;
    private Map<String, AssignedName> instanceAssignedNames;
    private Map<String, NamedInstaller> staticInstallers;
    private Map<String, NamedInstaller> instanceInstallers;
    private ConstructorInvokerInstaller constructorInstaller;
    private List<ConstantField> constantFields;
    private volatile RubyArray constructors;
    private volatile ArrayList<IRubyObject> proxyExtenders;
    private volatile RubyModule proxyModule;
    private volatile RubyClass proxyClass;
    private RubyModule unfinishedProxyModule;
    private RubyClass unfinishedProxyClass;
    private final ReentrantLock proxyLock;
    private final Initializer initializer;
    private static final Initializer DUMMY_INITIALIZER;
    private static final Map<String, String> SCALA_OPERATORS;
    private static Map<String, Class> PRIMITIVE_TO_CLASS;
    private static final Callback __jsend_method;
    
    private void handleScalaSingletons(final Class<?> javaClass, final InitializerState state) {
        try {
            final Class<?> companionClass = javaClass.getClassLoader().loadClass(javaClass.getName() + "$");
            final Field field = companionClass.getField("MODULE$");
            final Object singleton = field.get(null);
            if (singleton != null) {
                final Method[] sMethods = getMethods(companionClass);
                int j = sMethods.length;
                while (j-- >= 0) {
                    final Method method = sMethods[j];
                    String name = method.getName();
                    if (name.indexOf("$") >= 0) {
                        name = fixScalaNames(name);
                    }
                    if (!Modifier.isStatic(method.getModifiers())) {
                        final AssignedName assignedName = state.staticNames.get(name);
                        if (JavaClass.INSTANCE_RESERVED_NAMES.containsKey(method.getName())) {
                            this.installSingletonMethods(state.staticCallbacks, javaClass, singleton, method, name + "__method");
                        }
                        else {
                            if (assignedName == null) {
                                state.staticNames.put(name, new AssignedName(name, Priority.METHOD));
                            }
                            else {
                                if (Priority.METHOD.lessImportantThan(assignedName)) {
                                    continue;
                                }
                                if (!Priority.METHOD.asImportantAs(assignedName)) {
                                    state.staticCallbacks.remove(name);
                                    state.staticCallbacks.remove(name + '=');
                                    state.staticNames.put(name, new AssignedName(name, Priority.METHOD));
                                }
                            }
                            this.installSingletonMethods(state.staticCallbacks, javaClass, singleton, method, name);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public RubyModule getProxyModule() {
        final RubyModule proxy;
        if ((proxy = this.proxyModule) != null) {
            return proxy;
        }
        if (this.proxyLock.isHeldByCurrentThread()) {
            return this.unfinishedProxyModule;
        }
        return null;
    }
    
    public RubyClass getProxyClass() {
        final RubyClass proxy;
        if ((proxy = this.proxyClass) != null) {
            return proxy;
        }
        if (this.proxyLock.isHeldByCurrentThread()) {
            return this.unfinishedProxyClass;
        }
        return null;
    }
    
    public void lockProxy() {
        this.proxyLock.lock();
    }
    
    public void unlockProxy() {
        this.proxyLock.unlock();
    }
    
    private Map<String, AssignedName> getStaticAssignedNames() {
        return this.staticAssignedNames;
    }
    
    private Map<String, AssignedName> getInstanceAssignedNames() {
        return this.instanceAssignedNames;
    }
    
    private JavaClass(final Ruby runtime, final Class<?> javaClass) {
        super(runtime, runtime.getJavaSupport().getJavaClassClass(), javaClass);
        this.JAVA_UTILITIES = this.getRuntime().getJavaSupport().getJavaUtilitiesModule();
        this.proxyLock = new ReentrantLock();
        if (javaClass.isInterface()) {
            this.initializer = new InterfaceInitializer(javaClass);
        }
        else if (!javaClass.isArray() && !javaClass.isPrimitive()) {
            this.initializer = new ClassInitializer(javaClass);
        }
        else {
            this.initializer = JavaClass.DUMMY_INITIALIZER;
        }
    }
    
    public boolean equals(final Object other) {
        return other instanceof JavaClass && this.getValue() == ((JavaClass)other).getValue();
    }
    
    public int hashCode() {
        return this.javaClass().hashCode();
    }
    
    public void setupProxy(final RubyClass proxy) {
        this.initializer.initialize();
        assert this.proxyLock.isHeldByCurrentThread();
        proxy.defineFastMethod("__jsend!", JavaClass.__jsend_method);
        final Class<?> javaClass = (Class<?>)this.javaClass();
        if (javaClass.isInterface()) {
            this.setupInterfaceProxy(proxy);
            return;
        }
        proxy.setReifiedClass(javaClass);
        assert this.proxyClass == null;
        this.unfinishedProxyClass = proxy;
        if (javaClass.isArray() || javaClass.isPrimitive()) {
            this.proxyClass = proxy;
            this.proxyModule = proxy;
            return;
        }
        this.installClassFields(proxy);
        this.installClassMethods(proxy);
        this.installClassConstructors(proxy);
        this.installClassClasses(javaClass, proxy);
        this.proxyClass = proxy;
        this.proxyModule = proxy;
        this.applyProxyExtenders();
    }
    
    private static void assignAliases(final MethodInstaller installer, final Map<String, AssignedName> assignedNames) {
        final String name = installer.name;
        final String rubyCasedName = JavaUtil.getRubyCasedName(name);
        addUnassignedAlias(rubyCasedName, assignedNames, installer);
        final String javaPropertyName = JavaUtil.getJavaPropertyName(name);
        String rubyPropertyName = null;
        for (final Method method : installer.methods) {
            final Class<?>[] argTypes = method.getParameterTypes();
            final Class<?> resultType = method.getReturnType();
            final int argCount = argTypes.length;
            if (rubyCasedName.equals("apply")) {
                addUnassignedAlias("[]", assignedNames, installer);
            }
            if (rubyCasedName.equals("update") && argCount == 2) {
                addUnassignedAlias("[]=", assignedNames, installer);
            }
            if (javaPropertyName != null) {
                if (rubyCasedName.startsWith("get_")) {
                    rubyPropertyName = rubyCasedName.substring(4);
                    if (argCount == 0 || (argCount == 1 && argTypes[0] == Integer.TYPE)) {
                        addUnassignedAlias(javaPropertyName, assignedNames, installer);
                        addUnassignedAlias(rubyPropertyName, assignedNames, installer);
                    }
                }
                else if (rubyCasedName.startsWith("set_")) {
                    rubyPropertyName = rubyCasedName.substring(4);
                    if (argCount == 1 && resultType == Void.TYPE) {
                        addUnassignedAlias(javaPropertyName + '=', assignedNames, installer);
                        addUnassignedAlias(rubyPropertyName + '=', assignedNames, installer);
                    }
                }
                else if (rubyCasedName.startsWith("is_")) {
                    rubyPropertyName = rubyCasedName.substring(3);
                    if (resultType == Boolean.TYPE) {
                        addUnassignedAlias(javaPropertyName, assignedNames, installer);
                        addUnassignedAlias(rubyPropertyName, assignedNames, installer);
                    }
                }
            }
            if (resultType == Boolean.TYPE) {
                addUnassignedAlias(rubyCasedName + '?', assignedNames, installer);
                if (rubyPropertyName == null) {
                    continue;
                }
                addUnassignedAlias(rubyPropertyName + '?', assignedNames, installer);
            }
        }
    }
    
    private static void addUnassignedAlias(final String name, final Map<String, AssignedName> assignedNames, final MethodInstaller installer) {
        if (name == null) {
            return;
        }
        final AssignedName assignedName = assignedNames.get(name);
        if (Priority.ALIAS.moreImportantThan(assignedName)) {
            installer.addAlias(name);
            assignedNames.put(name, new AssignedName(name, Priority.ALIAS));
        }
        else if (Priority.ALIAS.asImportantAs(assignedName)) {
            installer.addAlias(name);
        }
    }
    
    private void installClassClasses(final Class<?> javaClass, final RubyModule proxy) {
        final Class<?>[] classes = getDeclaredClasses(javaClass);
        int i = classes.length;
        while (--i >= 0) {
            if (javaClass == classes[i].getDeclaringClass()) {
                final Class<?> clazz = classes[i];
                if (!Modifier.isPublic(clazz.getModifiers())) {
                    continue;
                }
                final String simpleName = getSimpleName(clazz);
                if (simpleName.length() == 0) {
                    continue;
                }
                final IRubyObject innerProxy = Java.get_proxy_class(this.JAVA_UTILITIES, get(this.getRuntime(), clazz));
                if (IdUtil.isConstant(simpleName)) {
                    if (proxy.getConstantAt(simpleName) != null) {
                        continue;
                    }
                    proxy.const_set(this.getRuntime().newString(simpleName), innerProxy);
                }
                else {
                    if (proxy.respondsTo(simpleName)) {
                        continue;
                    }
                    proxy.getSingletonClass().addMethod(simpleName, new JavaMethod.JavaMethodZero(proxy.getSingletonClass(), Visibility.PUBLIC) {
                        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
                            return innerProxy;
                        }
                    });
                }
            }
        }
    }
    
    private synchronized void installClassConstructors(final RubyClass proxy) {
        if (this.constructorInstaller != null) {
            this.constructorInstaller.install(proxy);
            this.constructorInstaller = null;
        }
    }
    
    private synchronized void installClassFields(final RubyClass proxy) {
        for (final ConstantField field : this.constantFields) {
            field.install(proxy);
        }
        this.constantFields = null;
    }
    
    private synchronized void installClassMethods(final RubyClass proxy) {
        for (final NamedInstaller installer : this.staticInstallers.values()) {
            installer.install(proxy);
        }
        this.staticInstallers = null;
        for (final NamedInstaller installer : this.instanceInstallers.values()) {
            installer.install(proxy);
        }
        this.instanceInstallers = null;
    }
    
    private void setupClassConstructors(final Class<?> javaClass) {
        final Constructor[] clsConstructors = getConstructors(javaClass);
        this.constructorInstaller = new ConstructorInvokerInstaller("__jcreate!");
        int i = clsConstructors.length;
        while (--i >= 0) {
            final Constructor ctor = clsConstructors[i];
            this.constructorInstaller.addConstructor(ctor, javaClass);
        }
    }
    
    private void addField(final Map<String, NamedInstaller> callbacks, final Map<String, AssignedName> names, final Field field, final boolean isFinal, final boolean isStatic) {
        final String name = field.getName();
        if (Priority.FIELD.lessImportantThan(names.get(name))) {
            return;
        }
        names.put(name, new AssignedName(name, Priority.FIELD));
        callbacks.put(name, isStatic ? new StaticFieldGetterInstaller(name, field) : new InstanceFieldGetterInstaller(name, field));
        if (!isFinal) {
            final String setName = name + '=';
            callbacks.put(setName, isStatic ? new StaticFieldSetterInstaller(setName, field) : new InstanceFieldSetterInstaller(setName, field));
        }
    }
    
    private void setupClassFields(final Class<?> javaClass, final InitializerState state) {
        final Field[] fields = getFields(javaClass);
        int i = fields.length;
        while (--i >= 0) {
            final Field field = fields[i];
            if (javaClass != field.getDeclaringClass()) {
                continue;
            }
            if (ConstantField.isConstant(field)) {
                state.constantFields.add(new ConstantField(field));
            }
            else {
                final int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    this.addField(state.staticCallbacks, state.staticNames, field, Modifier.isFinal(modifiers), true);
                }
                else {
                    this.addField(state.instanceCallbacks, state.instanceNames, field, Modifier.isFinal(modifiers), false);
                }
            }
        }
    }
    
    private static String fixScalaNames(final String name) {
        for (final Map.Entry<String, String> entry : JavaClass.SCALA_OPERATORS.entrySet()) {
            name.replaceAll(entry.getKey(), entry.getValue());
        }
        return name;
    }
    
    private void setupClassMethods(final Class<?> javaClass, final InitializerState state) {
        final Method[] methods = getMethods(javaClass);
        int i = methods.length;
        while (--i >= 0) {
            final Method method = methods[i];
            String name = method.getName();
            if (name.startsWith("$")) {
                name = fixScalaNames(name);
            }
            if (Modifier.isStatic(method.getModifiers())) {
                final AssignedName assignedName = state.staticNames.get(name);
                if (JavaClass.STATIC_RESERVED_NAMES.containsKey(method.getName())) {
                    this.installStaticMethods(state.staticCallbacks, javaClass, method, name + "__method");
                }
                else {
                    if (assignedName == null) {
                        state.staticNames.put(name, new AssignedName(name, Priority.METHOD));
                    }
                    else {
                        if (Priority.METHOD.lessImportantThan(assignedName)) {
                            continue;
                        }
                        if (!Priority.METHOD.asImportantAs(assignedName)) {
                            state.staticCallbacks.remove(name);
                            state.staticCallbacks.remove(name + '=');
                            state.staticNames.put(name, new AssignedName(name, Priority.METHOD));
                        }
                    }
                    this.installStaticMethods(state.staticCallbacks, javaClass, method, name);
                }
            }
            else {
                final AssignedName assignedName = state.instanceNames.get(name);
                if (JavaClass.INSTANCE_RESERVED_NAMES.containsKey(method.getName())) {
                    this.installInstanceMethods(state.instanceCallbacks, javaClass, method, name + "__method");
                }
                else {
                    if (assignedName == null) {
                        state.instanceNames.put(name, new AssignedName(name, Priority.METHOD));
                    }
                    else {
                        if (Priority.METHOD.lessImportantThan(assignedName)) {
                            continue;
                        }
                        if (!Priority.METHOD.asImportantAs(assignedName)) {
                            state.instanceCallbacks.remove(name);
                            state.instanceCallbacks.remove(name + '=');
                            state.instanceNames.put(name, new AssignedName(name, Priority.METHOD));
                        }
                    }
                    this.installInstanceMethods(state.instanceCallbacks, javaClass, method, name);
                }
            }
        }
        this.handleScalaSingletons(javaClass, state);
        for (final Map.Entry<String, NamedInstaller> entry : state.staticCallbacks.entrySet()) {
            if (entry.getKey().endsWith("__method")) {
                continue;
            }
            if (entry.getValue().type != 2 || !entry.getValue().hasLocalMethod()) {
                continue;
            }
            assignAliases(entry.getValue(), state.staticNames);
        }
        for (final Map.Entry<String, NamedInstaller> entry : state.instanceCallbacks.entrySet()) {
            if (entry.getKey().endsWith("__method")) {
                continue;
            }
            if (entry.getValue().type != 4 || !entry.getValue().hasLocalMethod()) {
                continue;
            }
            assignAliases(entry.getValue(), state.instanceNames);
        }
    }
    
    private void installInstanceMethods(final Map<String, NamedInstaller> methodCallbacks, final Class<?> javaClass, final Method method, final String name) {
        MethodInstaller invoker = methodCallbacks.get(name);
        if (invoker == null) {
            invoker = new InstanceMethodInvokerInstaller(name);
            methodCallbacks.put(name, invoker);
        }
        invoker.addMethod(method, javaClass);
    }
    
    private void installStaticMethods(final Map<String, NamedInstaller> methodCallbacks, final Class<?> javaClass, final Method method, final String name) {
        MethodInstaller invoker = methodCallbacks.get(name);
        if (invoker == null) {
            invoker = new StaticMethodInvokerInstaller(name);
            methodCallbacks.put(name, invoker);
        }
        invoker.addMethod(method, javaClass);
    }
    
    private void installSingletonMethods(final Map<String, NamedInstaller> methodCallbacks, final Class<?> javaClass, final Object singleton, final Method method, final String name) {
        MethodInstaller invoker = methodCallbacks.get(name);
        if (invoker == null) {
            invoker = new SingletonMethodInvokerInstaller(name, singleton);
            methodCallbacks.put(name, invoker);
        }
        invoker.addMethod(method, javaClass);
    }
    
    private void setupInterfaceProxy(final RubyClass proxy) {
        this.initializer.initialize();
        assert this.javaClass().isInterface();
        assert this.proxyLock.isHeldByCurrentThread();
        assert this.proxyClass == null;
        this.proxyClass = proxy;
    }
    
    public void setupInterfaceModule(final RubyModule module) {
        this.initializer.initialize();
        assert this.javaClass().isInterface();
        assert this.proxyLock.isHeldByCurrentThread();
        assert this.proxyModule == null;
        this.unfinishedProxyModule = module;
        final Class<?> javaClass = (Class<?>)this.javaClass();
        for (final ConstantField field : this.constantFields) {
            field.install(module);
        }
        for (final NamedInstaller installer : this.staticInstallers.values()) {
            installer.install(module);
        }
        this.installClassClasses(javaClass, module);
        this.proxyModule = module;
        this.applyProxyExtenders();
    }
    
    public void addProxyExtender(final IRubyObject extender) {
        this.lockProxy();
        try {
            if (!extender.respondsTo("extend_proxy")) {
                throw this.getRuntime().newTypeError("proxy extender must have an extend_proxy method");
            }
            if (this.proxyModule == null) {
                if (this.proxyExtenders == null) {
                    this.proxyExtenders = new ArrayList<IRubyObject>();
                }
                this.proxyExtenders.add(extender);
            }
            else {
                this.getRuntime().getWarnings().warn(IRubyWarnings.ID.PROXY_EXTENDED_LATE, " proxy extender added after proxy class created for " + this, new Object[0]);
                this.extendProxy(extender);
            }
        }
        finally {
            this.unlockProxy();
        }
    }
    
    private void applyProxyExtenders() {
        final ArrayList<IRubyObject> extenders;
        if ((extenders = this.proxyExtenders) != null) {
            for (final IRubyObject extender : extenders) {
                this.extendProxy(extender);
            }
            this.proxyExtenders = null;
        }
    }
    
    private void extendProxy(final IRubyObject extender) {
        extender.callMethod(this.getRuntime().getCurrentContext(), "extend_proxy", this.proxyModule);
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject extend_proxy(final IRubyObject extender) {
        this.addProxyExtender(extender);
        return this.getRuntime().getNil();
    }
    
    public static JavaClass get(final Ruby runtime, final Class<?> klass) {
        JavaClass javaClass = runtime.getJavaSupport().getJavaClassFromCache(klass);
        if (javaClass == null) {
            javaClass = createJavaClass(runtime, klass);
        }
        return javaClass;
    }
    
    public static RubyArray getRubyArray(final Ruby runtime, final Class<?>[] classes) {
        final IRubyObject[] javaClasses = new IRubyObject[classes.length];
        int i = classes.length;
        while (--i >= 0) {
            javaClasses[i] = get(runtime, classes[i]);
        }
        return runtime.newArrayNoCopy(javaClasses);
    }
    
    private static synchronized JavaClass createJavaClass(final Ruby runtime, final Class<?> klass) {
        JavaClass javaClass = runtime.getJavaSupport().getJavaClassFromCache(klass);
        if (javaClass == null) {
            javaClass = new JavaClass(runtime, klass);
            runtime.getJavaSupport().putJavaClassIntoCache(javaClass);
        }
        return javaClass;
    }
    
    public static RubyClass createJavaClassClass(final Ruby runtime, final RubyModule javaModule) {
        final RubyClass result = javaModule.defineClassUnder("JavaClass", javaModule.fastGetClass("JavaObject"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        result.includeModule(runtime.fastGetModule("Comparable"));
        result.defineAnnotatedMethods(JavaClass.class);
        result.getMetaClass().undefineMethod("new");
        result.getMetaClass().undefineMethod("allocate");
        return result;
    }
    
    public static synchronized JavaClass forNameVerbose(final Ruby runtime, final String className) {
        Class<?> klass = null;
        if (className.indexOf(".") == -1 && Character.isLowerCase(className.charAt(0))) {
            klass = JavaClass.PRIMITIVE_TO_CLASS.get(className);
        }
        if (klass == null) {
            klass = (Class<?>)runtime.getJavaSupport().loadJavaClassVerbose(className);
        }
        return get(runtime, klass);
    }
    
    public static synchronized JavaClass forNameQuiet(final Ruby runtime, final String className) {
        final Class klass = runtime.getJavaSupport().loadJavaClassQuiet(className);
        return get(runtime, klass);
    }
    
    @JRubyMethod(name = { "for_name" }, required = 1, meta = true)
    public static JavaClass for_name(final IRubyObject recv, IRubyObject name) {
        return forNameVerbose(recv.getRuntime(), name.asJavaString());
    }
    
    @JRubyMethod
    public RubyModule ruby_class() {
        return Java.getProxyClass(this.getRuntime(), this);
    }
    
    @JRubyMethod(name = { "public?" })
    public RubyBoolean public_p() {
        return this.getRuntime().newBoolean(Modifier.isPublic(this.javaClass().getModifiers()));
    }
    
    @JRubyMethod(name = { "protected?" })
    public RubyBoolean protected_p() {
        return this.getRuntime().newBoolean(Modifier.isProtected(this.javaClass().getModifiers()));
    }
    
    @JRubyMethod(name = { "private?" })
    public RubyBoolean private_p() {
        return this.getRuntime().newBoolean(Modifier.isPrivate(this.javaClass().getModifiers()));
    }
    
    public Class javaClass() {
        return (Class)this.getValue();
    }
    
    @JRubyMethod(name = { "final?" })
    public RubyBoolean final_p() {
        return this.getRuntime().newBoolean(Modifier.isFinal(this.javaClass().getModifiers()));
    }
    
    @JRubyMethod(name = { "interface?" })
    public RubyBoolean interface_p() {
        return this.getRuntime().newBoolean(this.javaClass().isInterface());
    }
    
    @JRubyMethod(name = { "array?" })
    public RubyBoolean array_p() {
        return this.getRuntime().newBoolean(this.javaClass().isArray());
    }
    
    @JRubyMethod(name = { "enum?" })
    public RubyBoolean enum_p() {
        return this.getRuntime().newBoolean(this.javaClass().isEnum());
    }
    
    @JRubyMethod(name = { "annotation?" })
    public RubyBoolean annotation_p() {
        return this.getRuntime().newBoolean(this.javaClass().isAnnotation());
    }
    
    @JRubyMethod(name = { "anonymous_class?" })
    public RubyBoolean anonymous_class_p() {
        return this.getRuntime().newBoolean(this.javaClass().isAnonymousClass());
    }
    
    @JRubyMethod(name = { "local_class?" })
    public RubyBoolean local_class_p() {
        return this.getRuntime().newBoolean(this.javaClass().isLocalClass());
    }
    
    @JRubyMethod(name = { "member_class?" })
    public RubyBoolean member_class_p() {
        return this.getRuntime().newBoolean(this.javaClass().isMemberClass());
    }
    
    @JRubyMethod(name = { "synthetic?" })
    public IRubyObject synthetic_p() {
        return this.getRuntime().newBoolean(this.javaClass().isSynthetic());
    }
    
    @JRubyMethod(name = { "name", "to_s" })
    public RubyString name() {
        return this.getRuntime().newString(this.javaClass().getName());
    }
    
    @JRubyMethod
    public RubyString inspect() {
        return this.getRuntime().newString("class " + this.javaClass().getName());
    }
    
    @JRubyMethod
    public IRubyObject canonical_name() {
        final String canonicalName = this.javaClass().getCanonicalName();
        if (canonicalName != null) {
            return this.getRuntime().newString(canonicalName);
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "package" })
    public IRubyObject get_package() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getPackage());
    }
    
    @JRubyMethod
    public IRubyObject class_loader() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getClassLoader());
    }
    
    @JRubyMethod
    public IRubyObject protection_domain() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getProtectionDomain());
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject resource(final IRubyObject name) {
        return Java.getInstance(this.getRuntime(), this.javaClass().getResource(name.asJavaString()));
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject resource_as_stream(final IRubyObject name) {
        return Java.getInstance(this.getRuntime(), this.javaClass().getResourceAsStream(name.asJavaString()));
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject resource_as_string(final IRubyObject name) {
        final InputStream in = this.javaClass().getResourceAsStream(name.asJavaString());
        if (in == null) {
            return this.getRuntime().getNil();
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            final byte[] buf = new byte[4096];
            int len;
            while ((len = in.read(buf)) >= 0) {
                out.write(buf, 0, len);
            }
        }
        catch (IOException e) {
            throw this.getRuntime().newIOErrorFromException(e);
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ex) {}
        }
        return this.getRuntime().newString(new ByteList(out.toByteArray(), false));
    }
    
    @JRubyMethod(required = 1)
    public IRubyObject annotation(final IRubyObject annoClass) {
        if (!(annoClass instanceof JavaClass)) {
            throw this.getRuntime().newTypeError(annoClass, this.getRuntime().getJavaSupport().getJavaClassClass());
        }
        return Java.getInstance(this.getRuntime(), this.javaClass().getAnnotation(((JavaClass)annoClass).javaClass()));
    }
    
    @JRubyMethod
    public IRubyObject annotations() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getAnnotations());
    }
    
    @JRubyMethod(name = { "annotations?" })
    public RubyBoolean annotations_p() {
        return this.getRuntime().newBoolean(this.javaClass().getAnnotations().length > 0);
    }
    
    @JRubyMethod
    public IRubyObject declared_annotations() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getDeclaredAnnotations());
    }
    
    @JRubyMethod(name = { "declared_annotations?" })
    public RubyBoolean declared_annotations_p() {
        return this.getRuntime().newBoolean(this.javaClass().getDeclaredAnnotations().length > 0);
    }
    
    @JRubyMethod(name = { "annotation_present?" }, required = 1)
    public IRubyObject annotation_present_p(final IRubyObject annoClass) {
        if (!(annoClass instanceof JavaClass)) {
            throw this.getRuntime().newTypeError(annoClass, this.getRuntime().getJavaSupport().getJavaClassClass());
        }
        return this.getRuntime().newBoolean(this.javaClass().isAnnotationPresent(((JavaClass)annoClass).javaClass()));
    }
    
    @JRubyMethod
    public IRubyObject modifiers() {
        return this.getRuntime().newFixnum(this.javaClass().getModifiers());
    }
    
    @JRubyMethod
    public IRubyObject declaring_class() {
        final Class<?> clazz = (Class<?>)this.javaClass().getDeclaringClass();
        if (clazz != null) {
            return get(this.getRuntime(), clazz);
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod
    public IRubyObject enclosing_class() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getEnclosingClass());
    }
    
    @JRubyMethod
    public IRubyObject enclosing_constructor() {
        final Constructor<?> ctor = (Constructor<?>)this.javaClass().getEnclosingConstructor();
        if (ctor != null) {
            return new JavaConstructor(this.getRuntime(), ctor);
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod
    public IRubyObject enclosing_method() {
        final Method meth = this.javaClass().getEnclosingMethod();
        if (meth != null) {
            return new org.jruby.javasupport.JavaMethod(this.getRuntime(), meth);
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod
    public IRubyObject enum_constants() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getEnumConstants());
    }
    
    @JRubyMethod
    public IRubyObject generic_interfaces() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getGenericInterfaces());
    }
    
    @JRubyMethod
    public IRubyObject generic_superclass() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getGenericSuperclass());
    }
    
    @JRubyMethod
    public IRubyObject type_parameters() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getTypeParameters());
    }
    
    @JRubyMethod
    public IRubyObject signers() {
        return Java.getInstance(this.getRuntime(), this.javaClass().getSigners());
    }
    
    private static String getSimpleName(final Class<?> clazz) {
        if (clazz.isArray()) {
            return getSimpleName(clazz.getComponentType()) + "[]";
        }
        final String className = clazz.getName();
        final int len = className.length();
        int i = className.lastIndexOf(36);
        if (i != -1) {
            while (++i < len && Character.isDigit(className.charAt(i))) {}
            return className.substring(i);
        }
        return className.substring(className.lastIndexOf(46) + 1);
    }
    
    @JRubyMethod
    public RubyString simple_name() {
        return this.getRuntime().newString(getSimpleName(this.javaClass()));
    }
    
    @JRubyMethod
    public IRubyObject superclass() {
        final Class<?> superclass = (Class<?>)this.javaClass().getSuperclass();
        if (superclass == null) {
            return this.getRuntime().getNil();
        }
        return get(this.getRuntime(), superclass);
    }
    
    @JRubyMethod(name = { "<=>" }, required = 1)
    public IRubyObject op_cmp(final IRubyObject other) {
        final Class me = this.javaClass();
        Class them = null;
        if (other instanceof JavaClass) {
            final JavaClass otherClass = (JavaClass)other;
            them = otherClass.javaClass();
        }
        else if (other instanceof ConcreteJavaProxy) {
            final ConcreteJavaProxy proxy = (ConcreteJavaProxy)other;
            if (proxy.getObject() instanceof Class) {
                them = (Class)proxy.getObject();
            }
        }
        if (them != null) {
            if (this.javaClass() == them) {
                return this.getRuntime().newFixnum(0);
            }
            if (them.isAssignableFrom(me)) {
                return this.getRuntime().newFixnum(-1);
            }
            if (me.isAssignableFrom(them)) {
                return this.getRuntime().newFixnum(1);
            }
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod
    public RubyArray java_instance_methods() {
        return this.java_methods(this.javaClass().getMethods(), false);
    }
    
    @JRubyMethod
    public RubyArray declared_instance_methods() {
        return this.java_methods(this.javaClass().getDeclaredMethods(), false);
    }
    
    private RubyArray java_methods(final Method[] methods, final boolean isStatic) {
        final RubyArray result = this.getRuntime().newArray(methods.length);
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            if (isStatic == Modifier.isStatic(method.getModifiers())) {
                result.append(org.jruby.javasupport.JavaMethod.create(this.getRuntime(), method));
            }
        }
        return result;
    }
    
    @JRubyMethod
    public RubyArray java_class_methods() {
        return this.java_methods(this.javaClass().getMethods(), true);
    }
    
    @JRubyMethod
    public RubyArray declared_class_methods() {
        return this.java_methods(this.javaClass().getDeclaredMethods(), true);
    }
    
    @JRubyMethod(required = 1, rest = true)
    public org.jruby.javasupport.JavaMethod java_method(final IRubyObject[] args) {
        final String methodName = args[0].asJavaString();
        try {
            final Class<?>[] argumentTypes = this.buildArgumentTypes(args);
            return org.jruby.javasupport.JavaMethod.create(this.getRuntime(), this.javaClass(), methodName, argumentTypes);
        }
        catch (ClassNotFoundException cnfe) {
            throw this.getRuntime().newNameError("undefined method '" + methodName + "' for class '" + this.javaClass().getName() + "'", methodName);
        }
    }
    
    @JRubyMethod(required = 1, rest = true)
    public org.jruby.javasupport.JavaMethod declared_method(final IRubyObject[] args) {
        final String methodName = args[0].asJavaString();
        try {
            final Class<?>[] argumentTypes = this.buildArgumentTypes(args);
            return org.jruby.javasupport.JavaMethod.createDeclared(this.getRuntime(), this.javaClass(), methodName, argumentTypes);
        }
        catch (ClassNotFoundException cnfe) {
            throw this.getRuntime().newNameError("undefined method '" + methodName + "' for class '" + this.javaClass().getName() + "'", methodName);
        }
    }
    
    @JRubyMethod(required = 1, rest = true)
    public JavaCallable declared_method_smart(final IRubyObject[] args) {
        final String methodName = args[0].asJavaString();
        try {
            final Class<?>[] argumentTypes = this.buildArgumentTypes(args);
            final JavaCallable callable = getMatchingCallable(this.getRuntime(), this.javaClass(), methodName, argumentTypes);
            if (callable != null) {
                return callable;
            }
        }
        catch (ClassNotFoundException ex) {}
        throw this.getRuntime().newNameError("undefined method '" + methodName + "' for class '" + this.javaClass().getName() + "'", methodName);
    }
    
    public static JavaCallable getMatchingCallable(final Ruby runtime, final Class<?> javaClass, final String methodName, final Class<?>[] argumentTypes) {
        if ("<init>".equals(methodName)) {
            return JavaConstructor.getMatchingConstructor(runtime, javaClass, argumentTypes);
        }
        return org.jruby.javasupport.JavaMethod.getMatchingDeclaredMethod(runtime, javaClass, methodName, argumentTypes);
    }
    
    private Class<?>[] buildArgumentTypes(final IRubyObject[] args) throws ClassNotFoundException {
        if (args.length < 1) {
            throw this.getRuntime().newArgumentError(args.length, 1);
        }
        final Class<?>[] argumentTypes = (Class<?>[])new Class[args.length - 1];
        for (int i = 1; i < args.length; ++i) {
            JavaClass type;
            if (args[i] instanceof JavaClass) {
                type = (JavaClass)args[i];
            }
            else if (args[i].respondsTo("java_class")) {
                type = (JavaClass)args[i].callMethod(this.getRuntime().getCurrentContext(), "java_class");
            }
            else {
                type = for_name(this, args[i]);
            }
            argumentTypes[i - 1] = (Class<?>)type.javaClass();
        }
        return argumentTypes;
    }
    
    @JRubyMethod
    public RubyArray constructors() {
        final RubyArray ctors;
        if ((ctors = this.constructors) != null) {
            return ctors;
        }
        return this.constructors = this.buildConstructors(this.javaClass().getConstructors());
    }
    
    @JRubyMethod
    public RubyArray classes() {
        return getRubyArray(this.getRuntime(), this.javaClass().getClasses());
    }
    
    @JRubyMethod
    public RubyArray declared_classes() {
        final Ruby runtime = this.getRuntime();
        final RubyArray result = runtime.newArray();
        final Class<?> javaClass = (Class<?>)this.javaClass();
        try {
            final Class<?>[] classes = javaClass.getDeclaredClasses();
            for (int i = 0; i < classes.length; ++i) {
                if (Modifier.isPublic(classes[i].getModifiers())) {
                    result.append(get(runtime, classes[i]));
                }
            }
        }
        catch (SecurityException e) {
            try {
                final Class<?>[] classes2 = javaClass.getClasses();
                for (int j = 0; j < classes2.length; ++j) {
                    if (javaClass == classes2[j].getDeclaringClass()) {
                        result.append(get(runtime, classes2[j]));
                    }
                }
            }
            catch (SecurityException ex) {}
        }
        return result;
    }
    
    @JRubyMethod
    public RubyArray declared_constructors() {
        return this.buildConstructors(this.javaClass().getDeclaredConstructors());
    }
    
    private RubyArray buildConstructors(final Constructor<?>[] constructors) {
        final RubyArray result = this.getRuntime().newArray(constructors.length);
        for (int i = 0; i < constructors.length; ++i) {
            result.append(new JavaConstructor(this.getRuntime(), constructors[i]));
        }
        return result;
    }
    
    @JRubyMethod(rest = true)
    public JavaConstructor constructor(final IRubyObject[] args) {
        try {
            final Class<?>[] parameterTypes = this.buildClassArgs(args);
            final Constructor<?> constructor = this.javaClass().getConstructor(parameterTypes);
            return new JavaConstructor(this.getRuntime(), constructor);
        }
        catch (NoSuchMethodException nsme) {
            throw this.getRuntime().newNameError("no matching java constructor", null);
        }
    }
    
    @JRubyMethod(rest = true)
    public JavaConstructor declared_constructor(final IRubyObject[] args) {
        try {
            final Class<?>[] parameterTypes = this.buildClassArgs(args);
            final Constructor<?> constructor = this.javaClass().getDeclaredConstructor(parameterTypes);
            return new JavaConstructor(this.getRuntime(), constructor);
        }
        catch (NoSuchMethodException nsme) {
            throw this.getRuntime().newNameError("no matching java constructor", null);
        }
    }
    
    private Class<?>[] buildClassArgs(final IRubyObject[] args) {
        final Class<?>[] parameterTypes = (Class<?>[])new Class[args.length];
        for (int i = 0; i < args.length; ++i) {
            JavaClass type;
            if (args[i] instanceof JavaClass) {
                type = (JavaClass)args[i];
            }
            else if (args[i].respondsTo("java_class")) {
                type = (JavaClass)args[i].callMethod(this.getRuntime().getCurrentContext(), "java_class");
            }
            else {
                type = for_name(this, args[i]);
            }
            parameterTypes[i] = (Class<?>)type.javaClass();
        }
        return parameterTypes;
    }
    
    @JRubyMethod
    public JavaClass array_class() {
        return get(this.getRuntime(), Array.newInstance(this.javaClass(), 0).getClass());
    }
    
    @JRubyMethod(required = 1)
    public JavaObject new_array(final IRubyObject lengthArgument) {
        if (lengthArgument instanceof RubyInteger) {
            final int length = (int)((RubyInteger)lengthArgument).getLongValue();
            return new JavaArray(this.getRuntime(), Array.newInstance(this.javaClass(), length));
        }
        if (!(lengthArgument instanceof RubyArray)) {
            throw this.getRuntime().newArgumentError("invalid length or dimensions specifier for java array - must be Integer or Array of Integer");
        }
        final List list = ((RubyArray)lengthArgument).getList();
        final int length2 = list.size();
        if (length2 == 0) {
            throw this.getRuntime().newArgumentError("empty dimensions specifier for java array");
        }
        final int[] dimensions = new int[length2];
        int i = length2;
        while (--i >= 0) {
            final IRubyObject dimensionLength = list.get(i);
            if (!(dimensionLength instanceof RubyInteger)) {
                throw this.getRuntime().newTypeError(dimensionLength, this.getRuntime().getInteger());
            }
            dimensions[i] = (int)((RubyInteger)dimensionLength).getLongValue();
        }
        return new JavaArray(this.getRuntime(), Array.newInstance(this.javaClass(), dimensions));
    }
    
    public IRubyObject emptyJavaArray(final ThreadContext context) {
        final JavaArray javaArray = new JavaArray(this.getRuntime(), Array.newInstance(this.javaClass(), 0));
        final RubyClass newProxyClass = (RubyClass)Java.get_proxy_class(javaArray, this.array_class());
        final ArrayJavaProxy proxy = new ArrayJavaProxy(context.getRuntime(), newProxyClass);
        proxy.dataWrapStruct(javaArray);
        return proxy;
    }
    
    public IRubyObject javaArraySubarray(final ThreadContext context, final JavaArray fromArray, final int index, int size) {
        final int actualLength = Array.getLength(fromArray.getValue());
        if (index >= actualLength) {
            return context.getRuntime().getNil();
        }
        if (index + size > actualLength) {
            size = actualLength - index;
        }
        final Object newArray = Array.newInstance(this.javaClass(), size);
        final JavaArray javaArray = new JavaArray(this.getRuntime(), newArray);
        System.arraycopy(fromArray.getValue(), index, newArray, 0, size);
        final RubyClass newProxyClass = (RubyClass)Java.get_proxy_class(javaArray, this.array_class());
        final ArrayJavaProxy proxy = new ArrayJavaProxy(context.getRuntime(), newProxyClass);
        proxy.dataWrapStruct(javaArray);
        return proxy;
    }
    
    public IRubyObject concatArrays(final ThreadContext context, final JavaArray original, final JavaArray additional) {
        final int oldLength = (int)original.length().getLongValue();
        final int addLength = (int)additional.length().getLongValue();
        final Object newArray = Array.newInstance(this.javaClass(), oldLength + addLength);
        final JavaArray javaArray = new JavaArray(this.getRuntime(), newArray);
        System.arraycopy(original.getValue(), 0, newArray, 0, oldLength);
        System.arraycopy(additional.getValue(), 0, newArray, oldLength, addLength);
        final RubyClass newProxyClass = (RubyClass)Java.get_proxy_class(javaArray, this.array_class());
        final ArrayJavaProxy proxy = new ArrayJavaProxy(context.getRuntime(), newProxyClass);
        proxy.dataWrapStruct(javaArray);
        return proxy;
    }
    
    public IRubyObject concatArrays(final ThreadContext context, final JavaArray original, final IRubyObject additional) {
        final int oldLength = (int)original.length().getLongValue();
        final int addLength = (int)((RubyFixnum)RuntimeHelpers.invoke(context, additional, "length")).getLongValue();
        final Object newArray = Array.newInstance(this.javaClass(), oldLength + addLength);
        final JavaArray javaArray = new JavaArray(this.getRuntime(), newArray);
        System.arraycopy(original.getValue(), 0, newArray, 0, oldLength);
        final RubyClass newProxyClass = (RubyClass)Java.get_proxy_class(javaArray, this.array_class());
        final ArrayJavaProxy proxy = new ArrayJavaProxy(context.getRuntime(), newProxyClass);
        proxy.dataWrapStruct(javaArray);
        final Ruby runtime = context.getRuntime();
        for (int i = 0; i < addLength; ++i) {
            RuntimeHelpers.invoke(context, proxy, "[]=", runtime.newFixnum(oldLength + i), RuntimeHelpers.invoke(context, additional, "[]", runtime.newFixnum(i)));
        }
        return proxy;
    }
    
    public IRubyObject javaArrayFromRubyArray(final ThreadContext context, final IRubyObject fromArray) {
        final Ruby runtime = context.getRuntime();
        if (!(fromArray instanceof RubyArray)) {
            throw runtime.newTypeError(fromArray, runtime.getArray());
        }
        final RubyArray rubyArray = (RubyArray)fromArray;
        final JavaArray javaArray = new JavaArray(this.getRuntime(), Array.newInstance(this.javaClass(), rubyArray.size()));
        if (this.javaClass().isArray()) {
            for (int i = 0; i < rubyArray.size(); ++i) {
                final JavaClass componentType = this.component_type();
                final IRubyObject wrappedComponentArray = componentType.javaArrayFromRubyArray(context, rubyArray.eltInternal(i));
                javaArray.setWithExceptionHandling(i, JavaUtil.unwrapJavaObject(wrappedComponentArray));
            }
        }
        else {
            ArrayJavaAddons.copyDataToJavaArray(context, rubyArray, javaArray);
        }
        final RubyClass newProxyClass = (RubyClass)Java.get_proxy_class(javaArray, this.array_class());
        final ArrayJavaProxy proxy = new ArrayJavaProxy(runtime, newProxyClass);
        proxy.dataWrapStruct(javaArray);
        return proxy;
    }
    
    @JRubyMethod
    public RubyArray fields() {
        return this.buildFieldResults(this.javaClass().getFields());
    }
    
    @JRubyMethod
    public RubyArray declared_fields() {
        return this.buildFieldResults(this.javaClass().getDeclaredFields());
    }
    
    private RubyArray buildFieldResults(final Field[] fields) {
        final RubyArray result = this.getRuntime().newArray(fields.length);
        for (int i = 0; i < fields.length; ++i) {
            result.append(new JavaField(this.getRuntime(), fields[i]));
        }
        return result;
    }
    
    @JRubyMethod(required = 1)
    public JavaField field(final ThreadContext context, final IRubyObject name) {
        final Class<?> javaClass = (Class<?>)this.javaClass();
        final Ruby runtime = context.getRuntime();
        final String stringName = name.asJavaString();
        try {
            return new JavaField(runtime, javaClass.getField(stringName));
        }
        catch (NoSuchFieldException nsfe) {
            final String newName = JavaUtil.getJavaCasedName(stringName);
            if (newName != null) {
                try {
                    return new JavaField(runtime, javaClass.getField(newName));
                }
                catch (NoSuchFieldException ex) {}
            }
            throw undefinedFieldError(runtime, javaClass.getName(), stringName);
        }
    }
    
    @JRubyMethod(required = 1)
    public JavaField declared_field(final ThreadContext context, final IRubyObject name) {
        final Class<?> javaClass = (Class<?>)this.javaClass();
        final Ruby runtime = context.getRuntime();
        final String stringName = name.asJavaString();
        try {
            return new JavaField(runtime, javaClass.getDeclaredField(stringName));
        }
        catch (NoSuchFieldException nsfe) {
            final String newName = JavaUtil.getJavaCasedName(stringName);
            if (newName != null) {
                try {
                    return new JavaField(runtime, javaClass.getDeclaredField(newName));
                }
                catch (NoSuchFieldException ex) {}
            }
            throw undefinedFieldError(runtime, javaClass.getName(), stringName);
        }
    }
    
    public static RaiseException undefinedFieldError(final Ruby runtime, final String javaClassName, final String name) {
        return runtime.newNameError("undefined field '" + name + "' for class '" + javaClassName + "'", name);
    }
    
    @JRubyMethod
    public RubyArray interfaces() {
        return getRubyArray(this.getRuntime(), this.javaClass().getInterfaces());
    }
    
    @JRubyMethod(name = { "primitive?" })
    public RubyBoolean primitive_p() {
        return this.getRuntime().newBoolean(this.isPrimitive());
    }
    
    @JRubyMethod(name = { "assignable_from?" }, required = 1)
    public RubyBoolean assignable_from_p(final IRubyObject other) {
        if (!(other instanceof JavaClass)) {
            throw this.getRuntime().newTypeError("assignable_from requires JavaClass (" + other.getType() + " given)");
        }
        final Class<?> otherClass = (Class<?>)((JavaClass)other).javaClass();
        return assignable(this.javaClass(), otherClass) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    public static boolean assignable(Class<?> thisClass, Class<?> otherClass) {
        if ((!thisClass.isPrimitive() && otherClass == Void.TYPE) || thisClass.isAssignableFrom(otherClass)) {
            return true;
        }
        otherClass = JavaUtil.primitiveToWrapper(otherClass);
        thisClass = JavaUtil.primitiveToWrapper(thisClass);
        if (thisClass.isAssignableFrom(otherClass)) {
            return true;
        }
        if (Number.class.isAssignableFrom(thisClass)) {
            if (Number.class.isAssignableFrom(otherClass)) {
                return true;
            }
            if (otherClass.equals(Character.class)) {
                return true;
            }
        }
        return thisClass.equals(Character.class) && Number.class.isAssignableFrom(otherClass);
    }
    
    private boolean isPrimitive() {
        return this.javaClass().isPrimitive();
    }
    
    @JRubyMethod
    public JavaClass component_type() {
        if (!this.javaClass().isArray()) {
            throw this.getRuntime().newTypeError("not a java array-class");
        }
        return get(this.getRuntime(), this.javaClass().getComponentType());
    }
    
    private static Constructor[] getConstructors(final Class<?> javaClass) {
        try {
            return javaClass.getConstructors();
        }
        catch (SecurityException e) {
            return new Constructor[0];
        }
    }
    
    private static Class<?>[] getDeclaredClasses(final Class<?> javaClass) {
        try {
            return javaClass.getDeclaredClasses();
        }
        catch (SecurityException e) {
            return (Class<?>[])new Class[0];
        }
        catch (NoClassDefFoundError cnfe) {
            return (Class<?>[])new Class[0];
        }
    }
    
    private static Class<?>[] getClasses(final Class<?> javaClass) {
        try {
            return javaClass.getClasses();
        }
        catch (SecurityException e) {
            return (Class<?>[])new Class[0];
        }
    }
    
    public static Field[] getDeclaredFields(final Class<?> javaClass) {
        try {
            return javaClass.getDeclaredFields();
        }
        catch (SecurityException e) {
            return getFields(javaClass);
        }
    }
    
    public static Field[] getFields(final Class<?> javaClass) {
        try {
            return javaClass.getFields();
        }
        catch (SecurityException e) {
            return new Field[0];
        }
    }
    
    private static boolean methodsAreEquivalent(final Method child, final Method parent) {
        return parent.getDeclaringClass().isAssignableFrom(child.getDeclaringClass()) && child.getReturnType() == parent.getReturnType() && child.isVarArgs() == parent.isVarArgs() && Modifier.isPublic(child.getModifiers()) == Modifier.isPublic(parent.getModifiers()) && Modifier.isProtected(child.getModifiers()) == Modifier.isProtected(parent.getModifiers()) && Modifier.isStatic(child.getModifiers()) == Modifier.isStatic(parent.getModifiers()) && Arrays.equals(child.getParameterTypes(), parent.getParameterTypes());
    }
    
    private static int addNewMethods(final HashMap<String, List<Method>> nameMethods, final Method[] methods, final boolean includeStatic, final boolean removeDuplicate) {
        int added = 0;
        for (final Method m : methods) {
            Label_0176: {
                if (includeStatic || !Modifier.isStatic(m.getModifiers())) {
                    List<Method> childMethods = nameMethods.get(m.getName());
                    if (childMethods == null) {
                        childMethods = new ArrayList<Method>(1);
                        childMethods.add(m);
                        nameMethods.put(m.getName(), childMethods);
                        ++added;
                    }
                    else {
                        final ListIterator<Method> iter = childMethods.listIterator();
                        while (iter.hasNext()) {
                            final Method m2 = iter.next();
                            if (methodsAreEquivalent(m2, m)) {
                                if (removeDuplicate) {
                                    iter.set(m);
                                }
                                break Label_0176;
                            }
                        }
                        childMethods.add(m);
                        ++added;
                    }
                }
            }
        }
        return added;
    }
    
    public static Method[] getMethods(final Class<?> javaClass) {
        final HashMap<String, List<Method>> nameMethods = new HashMap<String, List<Method>>(30);
        int total = 0;
        for (Class c = javaClass; c != null; c = c.getSuperclass()) {
            Label_0061: {
                if (!Modifier.isPublic(c.getModifiers())) {
                    if (!JavaClass.CAN_SET_ACCESSIBLE) {
                        break Label_0061;
                    }
                }
                try {
                    total += addNewMethods(nameMethods, c.getDeclaredMethods(), c == javaClass, true);
                }
                catch (SecurityException ex) {}
            }
            for (final Class i : c.getInterfaces()) {
                try {
                    total += addNewMethods(nameMethods, i.getMethods(), false, false);
                }
                catch (SecurityException ex2) {}
            }
        }
        final ArrayList<Method> finalList = new ArrayList<Method>(total);
        for (final Map.Entry<String, List<Method>> entry : nameMethods.entrySet()) {
            finalList.addAll(entry.getValue());
        }
        return finalList.toArray(new Method[finalList.size()]);
    }
    
    static {
        boolean canSetAccessible = false;
        if (RubyInstanceConfig.CAN_SET_ACCESSIBLE) {
            try {
                AccessController.checkPermission(new ReflectPermission("suppressAccessChecks"));
                canSetAccessible = true;
            }
            catch (Throwable t) {
                if (SafePropertyAccessor.getBoolean("jruby.ji.logCanSetAccessible")) {
                    t.printStackTrace();
                }
                canSetAccessible = false;
            }
        }
        CAN_SET_ACCESSIBLE = canSetAccessible;
        (RESERVED_NAMES = new HashMap<String, AssignedName>()).put("__id__", new AssignedName("__id__", Priority.RESERVED));
        JavaClass.RESERVED_NAMES.put("__send__", new AssignedName("__send__", Priority.RESERVED));
        JavaClass.RESERVED_NAMES.put("instance_of?", new AssignedName("instance_of?", Priority.RESERVED));
        (STATIC_RESERVED_NAMES = new HashMap<String, AssignedName>(JavaClass.RESERVED_NAMES)).put("new", new AssignedName("new", Priority.RESERVED));
        (INSTANCE_RESERVED_NAMES = new HashMap<String, AssignedName>(JavaClass.RESERVED_NAMES)).put("class", new AssignedName("class", Priority.RESERVED));
        JavaClass.INSTANCE_RESERVED_NAMES.put("initialize", new AssignedName("initialize", Priority.RESERVED));
        DUMMY_INITIALIZER = new Initializer() {
            public synchronized void initialize() {
            }
        };
        final Map<String, String> tmp = new HashMap<String, String>();
        tmp.put("$plus", "+");
        tmp.put("$minus", "-");
        tmp.put("$colon", ":");
        tmp.put("$div", "/");
        tmp.put("$eq", "=");
        tmp.put("$less", "<");
        tmp.put("$greater", ">");
        tmp.put("$bslash", "\\");
        tmp.put("$hash", "#");
        tmp.put("$times", "*");
        tmp.put("$bang", "!");
        tmp.put("$at", "@");
        tmp.put("$percent", "%");
        tmp.put("$up", "^");
        tmp.put("$amp", "&");
        tmp.put("$tilde", "~");
        tmp.put("$qmark", "?");
        tmp.put("$bar", "|");
        SCALA_OPERATORS = Collections.unmodifiableMap((Map<? extends String, ? extends String>)tmp);
        (JavaClass.PRIMITIVE_TO_CLASS = new HashMap<String, Class>()).put("byte", Byte.TYPE);
        JavaClass.PRIMITIVE_TO_CLASS.put("boolean", Boolean.TYPE);
        JavaClass.PRIMITIVE_TO_CLASS.put("short", Short.TYPE);
        JavaClass.PRIMITIVE_TO_CLASS.put("char", Character.TYPE);
        JavaClass.PRIMITIVE_TO_CLASS.put("int", Integer.TYPE);
        JavaClass.PRIMITIVE_TO_CLASS.put("long", Long.TYPE);
        JavaClass.PRIMITIVE_TO_CLASS.put("float", Float.TYPE);
        JavaClass.PRIMITIVE_TO_CLASS.put("double", Double.TYPE);
        __jsend_method = new Callback() {
            public IRubyObject execute(final IRubyObject self, final IRubyObject[] args, final Block block) {
                final String name = args[0].asJavaString();
                final DynamicMethod method = self.getMetaClass().searchMethod(name);
                final int v = method.getArity().getValue();
                final IRubyObject[] newArgs = new IRubyObject[args.length - 1];
                System.arraycopy(args, 1, newArgs, 0, newArgs.length);
                if (v < 0 || v == newArgs.length) {
                    return RuntimeHelpers.invoke(self.getRuntime().getCurrentContext(), self, name, newArgs, block);
                }
                final RubyClass superClass = self.getMetaClass().getSuperClass();
                return RuntimeHelpers.invokeAs(self.getRuntime().getCurrentContext(), superClass, self, name, newArgs, block);
            }
            
            public Arity getArity() {
                return Arity.optional();
            }
        };
    }
    
    private enum Priority
    {
        RESERVED(0), 
        METHOD(1), 
        FIELD(2), 
        PROTECTED_METHOD(3), 
        WEAKLY_RESERVED(4), 
        ALIAS(5), 
        PROTECTED_FIELD(6);
        
        private int value;
        
        private Priority(final int value) {
            this.value = value;
        }
        
        public boolean asImportantAs(final AssignedName other) {
            return other != null && other.type.value == this.value;
        }
        
        public boolean lessImportantThan(final AssignedName other) {
            return other != null && other.type.value < this.value;
        }
        
        public boolean moreImportantThan(final AssignedName other) {
            return other == null || other.type.value > this.value;
        }
    }
    
    private static class AssignedName
    {
        String name;
        Priority type;
        
        AssignedName() {
        }
        
        AssignedName(final String name, final Priority type) {
            this.name = name;
            this.type = type;
        }
    }
    
    private abstract static class NamedInstaller
    {
        static final int STATIC_FIELD = 1;
        static final int STATIC_METHOD = 2;
        static final int INSTANCE_FIELD = 3;
        static final int INSTANCE_METHOD = 4;
        static final int CONSTRUCTOR = 5;
        String name;
        int type;
        Visibility visibility;
        
        NamedInstaller() {
            this.visibility = Visibility.PUBLIC;
        }
        
        NamedInstaller(final String name, final int type) {
            this.visibility = Visibility.PUBLIC;
            this.name = name;
            this.type = type;
        }
        
        abstract void install(final RubyModule p0);
        
        boolean hasLocalMethod() {
            return true;
        }
        
        boolean isPublic() {
            return this.visibility == Visibility.PUBLIC;
        }
        
        boolean isProtected() {
            return this.visibility == Visibility.PROTECTED;
        }
    }
    
    private abstract static class FieldInstaller extends NamedInstaller
    {
        Field field;
        
        FieldInstaller() {
        }
        
        FieldInstaller(final String name, final int type, final Field field) {
            super(name, type);
            this.field = field;
        }
    }
    
    private static class StaticFieldGetterInstaller extends FieldInstaller
    {
        StaticFieldGetterInstaller() {
        }
        
        StaticFieldGetterInstaller(final String name, final Field field) {
            super(name, 1, field);
        }
        
        void install(final RubyModule proxy) {
            if (Modifier.isPublic(this.field.getModifiers())) {
                proxy.getSingletonClass().addMethod(this.name, new StaticFieldGetter(this.name, proxy, this.field));
            }
        }
    }
    
    private static class StaticFieldSetterInstaller extends FieldInstaller
    {
        StaticFieldSetterInstaller() {
        }
        
        StaticFieldSetterInstaller(final String name, final Field field) {
            super(name, 1, field);
        }
        
        void install(final RubyModule proxy) {
            if (Modifier.isPublic(this.field.getModifiers())) {
                proxy.getSingletonClass().addMethod(this.name, new StaticFieldSetter(this.name, proxy, this.field));
            }
        }
    }
    
    private static class InstanceFieldGetterInstaller extends FieldInstaller
    {
        InstanceFieldGetterInstaller() {
        }
        
        InstanceFieldGetterInstaller(final String name, final Field field) {
            super(name, 3, field);
        }
        
        void install(final RubyModule proxy) {
            if (Modifier.isPublic(this.field.getModifiers())) {
                proxy.addMethod(this.name, new InstanceFieldGetter(this.name, proxy, this.field));
            }
        }
    }
    
    private static class InstanceFieldSetterInstaller extends FieldInstaller
    {
        InstanceFieldSetterInstaller() {
        }
        
        InstanceFieldSetterInstaller(final String name, final Field field) {
            super(name, 3, field);
        }
        
        void install(final RubyModule proxy) {
            if (Modifier.isPublic(this.field.getModifiers())) {
                proxy.addMethod(this.name, new InstanceFieldSetter(this.name, proxy, this.field));
            }
        }
    }
    
    private abstract static class MethodInstaller extends NamedInstaller
    {
        private boolean haveLocalMethod;
        protected List<Method> methods;
        protected List<String> aliases;
        
        MethodInstaller() {
        }
        
        MethodInstaller(final String name, final int type) {
            super(name, type);
        }
        
        void addMethod(final Method method, final Class<?> javaClass) {
            if (this.methods == null) {
                this.methods = new ArrayList<Method>(4);
            }
            this.methods.add(method);
            this.haveLocalMethod |= (javaClass == method.getDeclaringClass() || method.getDeclaringClass().isInterface());
        }
        
        void addAlias(final String alias) {
            if (this.aliases == null) {
                this.aliases = new ArrayList<String>(4);
            }
            if (!this.aliases.contains(alias)) {
                this.aliases.add(alias);
            }
        }
        
        boolean hasLocalMethod() {
            return this.haveLocalMethod;
        }
    }
    
    private static class ConstructorInvokerInstaller extends MethodInstaller
    {
        private boolean haveLocalConstructor;
        protected List<Constructor> constructors;
        
        ConstructorInvokerInstaller(final String name) {
            super(name, 2);
        }
        
        void addConstructor(final Constructor ctor, final Class<?> javaClass) {
            if (this.constructors == null) {
                this.constructors = new ArrayList<Constructor>(4);
            }
            if (!Ruby.isSecurityRestricted()) {
                try {
                    ctor.setAccessible(true);
                }
                catch (SecurityException ex) {}
            }
            this.constructors.add(ctor);
            this.haveLocalConstructor |= (javaClass == ctor.getDeclaringClass());
        }
        
        void install(final RubyModule proxy) {
            if (this.haveLocalConstructor) {
                final DynamicMethod method = new ConstructorInvoker(proxy, this.constructors);
                proxy.addMethod(this.name, method);
            }
            else {
                proxy.addMethod(this.name, new JavaMethod(proxy, Visibility.PUBLIC) {
                    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                        throw context.getRuntime().newTypeError("no public constructors for " + clazz);
                    }
                });
            }
        }
    }
    
    private static class StaticMethodInvokerInstaller extends MethodInstaller
    {
        StaticMethodInvokerInstaller(final String name) {
            super(name, 2);
        }
        
        void install(final RubyModule proxy) {
            if (this.hasLocalMethod()) {
                final RubyClass singleton = proxy.getSingletonClass();
                final DynamicMethod method = new StaticMethodInvoker(singleton, this.methods);
                singleton.addMethod(this.name, method);
                if (this.aliases != null && this.isPublic()) {
                    singleton.defineAliases(this.aliases, this.name);
                    this.aliases = null;
                }
            }
        }
    }
    
    private static class SingletonMethodInvokerInstaller extends StaticMethodInvokerInstaller
    {
        private Object singleton;
        
        SingletonMethodInvokerInstaller(final String name, final Object singleton) {
            super(name);
            this.singleton = singleton;
        }
        
        void install(final RubyModule proxy) {
            if (this.hasLocalMethod()) {
                final RubyClass rubySingleton = proxy.getSingletonClass();
                final DynamicMethod method = new SingletonMethodInvoker(this.singleton, rubySingleton, this.methods);
                rubySingleton.addMethod(this.name, method);
                if (this.aliases != null && this.isPublic()) {
                    rubySingleton.defineAliases(this.aliases, this.name);
                    this.aliases = null;
                }
            }
        }
    }
    
    private static class InstanceMethodInvokerInstaller extends MethodInstaller
    {
        InstanceMethodInvokerInstaller(final String name) {
            super(name, 4);
        }
        
        void install(final RubyModule proxy) {
            if (this.hasLocalMethod()) {
                final DynamicMethod method = new InstanceMethodInvoker(proxy, this.methods);
                proxy.addMethod(this.name, method);
                if (this.aliases != null && this.isPublic()) {
                    proxy.defineAliases(this.aliases, this.name);
                    this.aliases = null;
                }
            }
        }
    }
    
    private static class ConstantField
    {
        static final int CONSTANT = 25;
        final Field field;
        
        ConstantField(final Field field) {
            this.field = field;
        }
        
        void install(final RubyModule proxy) {
            if (proxy.getConstantAt(this.field.getName()) == null) {
                try {
                    proxy.setConstant(this.field.getName(), JavaUtil.convertJavaToUsableRubyObject(proxy.getRuntime(), this.field.get(null)));
                }
                catch (IllegalAccessException ex) {}
            }
        }
        
        static boolean isConstant(final Field field) {
            return (field.getModifiers() & 0x19) == 0x19 && Character.isUpperCase(field.getName().charAt(0));
        }
    }
    
    private class InterfaceInitializer implements Initializer
    {
        private volatile boolean hasRun;
        private final Class javaClass;
        
        public InterfaceInitializer(final Class<?> javaClass) {
            this.hasRun = false;
            this.javaClass = javaClass;
        }
        
        public synchronized void initialize() {
            if (this.hasRun) {
                return;
            }
            this.hasRun = true;
            final Map<String, AssignedName> staticNames = new HashMap<String, AssignedName>(JavaClass.STATIC_RESERVED_NAMES);
            final List<ConstantField> constants = new ArrayList<ConstantField>();
            final Map<String, NamedInstaller> staticCallbacks = new HashMap<String, NamedInstaller>();
            final Field[] fields = JavaClass.getDeclaredFields(this.javaClass);
            int i = fields.length;
            while (--i >= 0) {
                final Field field = fields[i];
                if (this.javaClass != field.getDeclaringClass()) {
                    continue;
                }
                if (ConstantField.isConstant(field)) {
                    constants.add(new ConstantField(field));
                }
                final int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    continue;
                }
                JavaClass.this.addField(staticCallbacks, staticNames, field, Modifier.isFinal(modifiers), true);
            }
            for (final Map.Entry<String, NamedInstaller> entry : staticCallbacks.entrySet()) {
                if (entry.getValue().type == 2 && entry.getValue().hasLocalMethod()) {
                    assignAliases(entry.getValue(), staticNames);
                }
            }
            JavaClass.this.staticAssignedNames = staticNames;
            JavaClass.this.staticInstallers = staticCallbacks;
            JavaClass.this.constantFields = constants;
        }
    }
    
    private class ClassInitializer implements Initializer
    {
        private volatile boolean hasRun;
        private final Class javaClass;
        
        public ClassInitializer(final Class<?> javaClass) {
            this.hasRun = false;
            this.javaClass = javaClass;
        }
        
        public synchronized void initialize() {
            if (this.hasRun) {
                return;
            }
            this.hasRun = true;
            final Class<?> superclass = (Class<?>)this.javaClass.getSuperclass();
            final InitializerState state = new InitializerState(JavaClass.this.getRuntime(), superclass);
            JavaClass.this.setupClassFields(this.javaClass, state);
            JavaClass.this.setupClassMethods(this.javaClass, state);
            JavaClass.this.setupClassConstructors(this.javaClass);
            JavaClass.this.staticAssignedNames = (Map<String, AssignedName>)Collections.unmodifiableMap((Map<?, ?>)state.staticNames);
            JavaClass.this.instanceAssignedNames = (Map<String, AssignedName>)Collections.unmodifiableMap((Map<?, ?>)state.instanceNames);
            JavaClass.this.staticInstallers = (Map<String, NamedInstaller>)Collections.unmodifiableMap((Map<?, ?>)state.staticCallbacks);
            JavaClass.this.instanceInstallers = (Map<String, NamedInstaller>)Collections.unmodifiableMap((Map<?, ?>)state.instanceCallbacks);
            JavaClass.this.constantFields = (List<ConstantField>)Collections.unmodifiableList((List<?>)state.constantFields);
        }
    }
    
    private static class InitializerState
    {
        public final Map<String, AssignedName> staticNames;
        public final Map<String, AssignedName> instanceNames;
        public final Map<String, NamedInstaller> staticCallbacks;
        public final Map<String, NamedInstaller> instanceCallbacks;
        public final List<ConstantField> constantFields;
        
        public InitializerState(final Ruby runtime, final Class superclass) {
            this.staticCallbacks = new HashMap<String, NamedInstaller>();
            this.instanceCallbacks = new HashMap<String, NamedInstaller>();
            this.constantFields = new ArrayList<ConstantField>();
            if (superclass == null) {
                this.staticNames = new HashMap<String, AssignedName>();
                this.instanceNames = new HashMap<String, AssignedName>();
            }
            else {
                final JavaClass superJavaClass = JavaClass.get(runtime, superclass);
                this.staticNames = new HashMap<String, AssignedName>(superJavaClass.getStaticAssignedNames());
                this.instanceNames = new HashMap<String, AssignedName>(superJavaClass.getInstanceAssignedNames());
            }
            this.staticNames.putAll(JavaClass.STATIC_RESERVED_NAMES);
            this.instanceNames.putAll(JavaClass.INSTANCE_RESERVED_NAMES);
        }
    }
    
    private interface Initializer
    {
        void initialize();
    }
}
