// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.callsite.FunctionalCachingCallSite;
import org.jruby.runtime.CallSite;
import org.jruby.internal.runtime.methods.ProfilingDynamicMethod;
import org.jruby.internal.runtime.methods.SynchronizedDynamicMethod;
import java.util.HashMap;
import java.util.Arrays;
import org.jruby.compiler.ASTInspector;
import org.jruby.runtime.builtin.Variable;
import org.jruby.util.IdUtil;
import org.jruby.runtime.marshal.UnmarshalStream;
import java.io.IOException;
import org.jruby.runtime.marshal.MarshalStream;
import java.util.ArrayList;
import org.jruby.runtime.Arity;
import org.jruby.parser.StaticScope;
import org.jruby.internal.runtime.methods.ProcMethod;
import org.jruby.internal.runtime.methods.MethodMethod;
import org.jruby.anno.FrameField;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.CallType;
import org.jruby.internal.runtime.methods.WrapperMethod;
import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.internal.runtime.methods.AliasMethod;
import org.jruby.internal.runtime.methods.DefaultMethod;
import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.ThreadContext;
import org.jruby.internal.runtime.methods.UndefinedMethod;
import org.jruby.internal.runtime.methods.SimpleCallbackMethod;
import org.jruby.anno.JRubyMethod;
import org.jruby.anno.JavaMethodDescriptor;
import java.util.List;
import org.jruby.anno.TypePopulator;
import org.jruby.anno.JRubyConstant;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import org.jruby.runtime.MethodFactory;
import org.jruby.internal.runtime.methods.FullFunctionCallbackMethod;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.callback.Callback;
import java.util.Iterator;
import org.jruby.exceptions.RaiseException;
import java.util.Collection;
import java.util.HashSet;
import org.jruby.util.collections.WeakHashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import org.jruby.util.ClassProvider;
import org.jruby.runtime.callsite.CacheEntry;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.Map;
import org.jruby.runtime.ObjectAllocator;
import java.util.Set;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Module" })
public class RubyModule extends RubyObject
{
    private static final boolean DEBUG = false;
    protected static final String ERR_INSECURE_SET_CONSTANT = "Insecure: can't modify constant";
    protected static final String ERR_FROZEN_CONST_TYPE = "class/module ";
    public static final Set<String> SCOPE_CAPTURING_METHODS;
    public static final ObjectAllocator MODULE_ALLOCATOR;
    protected static final CacheEntryFactory NormalCacheEntryFactory;
    private volatile CacheEntryFactory cacheEntryFactory;
    protected static final String ERR_INSECURE_SET_CLASS_VAR = "Insecure: can't modify class variable";
    protected static final String ERR_FROZEN_CVAR_TYPE = "class/module ";
    public KindOf kindOf;
    public final int id;
    public RubyModule parent;
    protected String classId;
    private volatile Map<String, IRubyObject> constants;
    private volatile Map<String, DynamicMethod> methods;
    private Map<String, CacheEntry> cachedMethods;
    protected int generation;
    protected volatile Set<RubyClass> includingHierarchies;
    private transient volatile Set<ClassProvider> classProviders;
    private String bareName;
    private String fullName;
    protected RubyClass superClass;
    public int index;
    private volatile Map<String, IRubyObject> classVariables;
    
    public static RubyClass createModuleClass(final Ruby runtime, final RubyClass moduleClass) {
        moduleClass.index = 12;
        moduleClass.setReifiedClass(RubyModule.class);
        moduleClass.kindOf = new KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyModule;
            }
        };
        moduleClass.defineAnnotatedMethods(RubyModule.class);
        moduleClass.defineAnnotatedMethods(ModuleKernelMethods.class);
        return moduleClass;
    }
    
    public int getNativeTypeIndex() {
        return 12;
    }
    
    public boolean isModule() {
        return true;
    }
    
    public boolean isClass() {
        return false;
    }
    
    public boolean isSingleton() {
        return false;
    }
    
    public boolean isInstance(final IRubyObject object) {
        return this.kindOf.isKindOf(object, this);
    }
    
    public Map<String, IRubyObject> getConstantMap() {
        return this.constants;
    }
    
    public synchronized Map<String, IRubyObject> getConstantMapForWrite() {
        return (this.constants == Collections.EMPTY_MAP) ? (this.constants = new ConcurrentHashMap<String, IRubyObject>(4, 0.9f, 1)) : this.constants;
    }
    
    public void addIncludingHierarchy(final IncludedModuleWrapper hierarchy) {
        synchronized (this.getRuntime().getHierarchyLock()) {
            Set<RubyClass> oldIncludingHierarchies = this.includingHierarchies;
            if (oldIncludingHierarchies == Collections.EMPTY_SET) {
                oldIncludingHierarchies = (this.includingHierarchies = new WeakHashSet<RubyClass>(4));
            }
            oldIncludingHierarchies.add(hierarchy);
        }
    }
    
    protected RubyModule(final Ruby runtime, final RubyClass metaClass, final boolean objectSpace) {
        super(runtime, metaClass, objectSpace);
        this.kindOf = KindOf.DEFAULT_KIND_OF;
        this.constants = (Map<String, IRubyObject>)Collections.EMPTY_MAP;
        this.methods = (Map<String, DynamicMethod>)Collections.EMPTY_MAP;
        this.cachedMethods = (Map<String, CacheEntry>)Collections.EMPTY_MAP;
        this.includingHierarchies = (Set<RubyClass>)Collections.EMPTY_SET;
        this.classProviders = (Set<ClassProvider>)Collections.EMPTY_SET;
        this.classVariables = (Map<String, IRubyObject>)Collections.EMPTY_MAP;
        this.id = runtime.allocModuleId();
        runtime.addModule(this);
        this.setFlag(4096, !this.isClass());
        this.generation = runtime.getNextModuleGeneration();
        if (runtime.getInstanceConfig().isProfiling()) {
            this.cacheEntryFactory = new ProfilingCacheEntryFactory(RubyModule.NormalCacheEntryFactory);
        }
        else {
            this.cacheEntryFactory = RubyModule.NormalCacheEntryFactory;
        }
    }
    
    protected RubyModule(final Ruby runtime, final RubyClass metaClass) {
        this(runtime, metaClass, runtime.isObjectSpaceEnabled());
    }
    
    protected RubyModule(final Ruby runtime) {
        this(runtime, runtime.getModule());
    }
    
    public boolean needsImplementer() {
        return this.getFlag(4096);
    }
    
    public static RubyModule newModule(final Ruby runtime) {
        return new RubyModule(runtime);
    }
    
    public static RubyModule newModule(final Ruby runtime, final String name, final RubyModule parent, final boolean setParent) {
        final RubyModule module = newModule(runtime);
        module.setBaseName(name);
        if (setParent) {
            module.setParent(parent);
        }
        parent.setConstant(name, module);
        return module;
    }
    
    public synchronized void addClassProvider(final ClassProvider provider) {
        if (!this.classProviders.contains(provider)) {
            final Set<ClassProvider> cp = new HashSet<ClassProvider>(this.classProviders);
            cp.add(provider);
            this.classProviders = cp;
        }
    }
    
    public synchronized void removeClassProvider(final ClassProvider provider) {
        final Set<ClassProvider> cp = new HashSet<ClassProvider>(this.classProviders);
        cp.remove(provider);
        this.classProviders = cp;
    }
    
    private void checkForCyclicInclude(final RubyModule m) throws RaiseException {
        if (this.getNonIncludedClass() == m.getNonIncludedClass()) {
            throw this.getRuntime().newArgumentError("cyclic include detected");
        }
    }
    
    private RubyClass searchProvidersForClass(final String name, final RubyClass superClazz) {
        for (final ClassProvider classProvider : this.classProviders) {
            final RubyClass clazz;
            if ((clazz = classProvider.defineClassUnder(this, name, superClazz)) != null) {
                return clazz;
            }
        }
        return null;
    }
    
    private RubyModule searchProvidersForModule(final String name) {
        for (final ClassProvider classProvider : this.classProviders) {
            final RubyModule module;
            if ((module = classProvider.defineModuleUnder(this, name)) != null) {
                return module;
            }
        }
        return null;
    }
    
    public RubyClass getSuperClass() {
        return this.superClass;
    }
    
    protected void setSuperClass(final RubyClass superClass) {
        this.superClass = superClass;
        if (superClass != null && superClass.isSynchronized()) {
            this.becomeSynchronized();
        }
    }
    
    public RubyModule getParent() {
        return this.parent;
    }
    
    public void setParent(final RubyModule parent) {
        this.parent = parent;
    }
    
    public Map<String, DynamicMethod> getMethods() {
        return this.methods;
    }
    
    public synchronized Map<String, DynamicMethod> getMethodsForWrite() {
        final Map<String, DynamicMethod> myMethods = this.methods;
        return (myMethods == Collections.EMPTY_MAP) ? (this.methods = new ConcurrentHashMap<String, DynamicMethod>(0, 0.9f, 1)) : myMethods;
    }
    
    private void putMethod(final String name, final DynamicMethod method) {
        this.getMethodsForWrite().put(name, method);
        this.getRuntime().addProfiledMethod(name, method);
    }
    
    public boolean isIncluded() {
        return false;
    }
    
    public RubyModule getNonIncludedClass() {
        return this;
    }
    
    public String getBaseName() {
        return this.classId;
    }
    
    public void setBaseName(final String name) {
        this.classId = name;
    }
    
    public String getName() {
        if (this.fullName == null) {
            this.calculateName();
        }
        return this.fullName;
    }
    
    private void calculateName() {
        this.fullName = this.calculateFullName();
    }
    
    private String calculateFullName() {
        if (this.getBaseName() == null) {
            if (this.bareName == null) {
                if (this.isClass()) {
                    this.bareName = "#<Class:0x1" + String.format("%08x", System.identityHashCode(this)) + ">";
                }
                else {
                    this.bareName = "#<Module:0x1" + String.format("%08x", System.identityHashCode(this)) + ">";
                }
            }
            return this.bareName;
        }
        String result = this.getBaseName();
        final RubyClass objectClass = this.getRuntime().getObject();
        for (RubyModule p = this.getParent(); p != null && p != objectClass; p = p.getParent()) {
            String pName = p.getBaseName();
            if (pName == null) {
                pName = p.getName();
            }
            result = pName + "::" + result;
        }
        return result;
    }
    
    @Deprecated
    public IncludedModuleWrapper newIncludeClass(final RubyClass superClazz) {
        final IncludedModuleWrapper includedModule = new IncludedModuleWrapper(this.getRuntime(), superClazz, this);
        if (this.getSuperClass() != null) {
            includedModule.includeModule(this.getSuperClass());
        }
        return includedModule;
    }
    
    public RubyClass getClass(final String name) {
        final IRubyObject module;
        if ((module = this.getConstantAt(name)) instanceof RubyClass) {
            return (RubyClass)module;
        }
        return null;
    }
    
    public RubyClass fastGetClass(final String internedName) {
        final IRubyObject module;
        if ((module = this.fastGetConstantAt(internedName)) instanceof RubyClass) {
            return (RubyClass)module;
        }
        return null;
    }
    
    public synchronized void includeModule(final IRubyObject arg) {
        assert arg != null;
        this.testFrozen("module");
        if (!this.isTaint()) {
            this.getRuntime().secure(4);
        }
        if (!(arg instanceof RubyModule)) {
            throw this.getRuntime().newTypeError("Wrong argument type " + arg.getMetaClass().getName() + " (expected Module).");
        }
        final RubyModule module = (RubyModule)arg;
        this.checkForCyclicInclude(module);
        this.infectBy(module);
        this.doIncludeModule(module);
        this.invalidateConstantCache();
        this.invalidateCoreClasses();
        this.invalidateCacheDescendants();
    }
    
    public void defineMethod(final String name, final Callback method) {
        final Visibility visibility = name.equals("initialize") ? Visibility.PRIVATE : Visibility.PUBLIC;
        this.addMethod(name, new FullFunctionCallbackMethod(this, method, visibility));
    }
    
    public void defineAnnotatedMethod(final Class clazz, final String name) {
        boolean foundMethod = false;
        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(name) && this.defineAnnotatedMethod(method, MethodFactory.createFactory(this.getRuntime().getJRubyClassLoader()))) {
                foundMethod = true;
            }
        }
        if (!foundMethod) {
            throw new RuntimeException("No JRubyMethod present for method " + name + "on class " + clazz.getName());
        }
    }
    
    public void defineAnnotatedConstants(final Class clazz) {
        final Field[] arr$;
        final Field[] declaredFields = arr$ = clazz.getDeclaredFields();
        for (final Field field : arr$) {
            if (Modifier.isStatic(field.getModifiers())) {
                this.defineAnnotatedConstant(field);
            }
        }
    }
    
    public boolean defineAnnotatedConstant(final Field field) {
        final JRubyConstant jrubyConstant = field.getAnnotation(JRubyConstant.class);
        if (jrubyConstant == null) {
            return false;
        }
        String[] names = jrubyConstant.value();
        if (names.length == 0) {
            names = new String[] { field.getName() };
        }
        final Class tp = field.getType();
        IRubyObject realVal;
        try {
            if (tp == Integer.class || tp == Integer.TYPE || tp == Short.class || tp == Short.TYPE || tp == Byte.class || tp == Byte.TYPE) {
                realVal = RubyNumeric.int2fix(this.getRuntime(), field.getInt(null));
            }
            else if (tp == Boolean.class || tp == Boolean.TYPE) {
                realVal = (field.getBoolean(null) ? this.getRuntime().getTrue() : this.getRuntime().getFalse());
            }
            else {
                realVal = this.getRuntime().getNil();
            }
        }
        catch (Exception e) {
            realVal = this.getRuntime().getNil();
        }
        for (final String name : names) {
            this.fastSetConstant(name, realVal);
        }
        return true;
    }
    
    public void defineAnnotatedMethods(final Class clazz) {
        this.defineAnnotatedMethodsIndividually(clazz);
    }
    
    public void defineAnnotatedMethodsIndividually(final Class clazz) {
        TypePopulator populator;
        if (RubyInstanceConfig.FULL_TRACE_ENABLED || RubyInstanceConfig.REFLECTED_HANDLES) {
            populator = TypePopulator.DEFAULT;
        }
        else {
            try {
                final String qualifiedName = "org.jruby.gen." + clazz.getCanonicalName().replace('.', '$');
                final Class populatorClass = Class.forName(qualifiedName + "$Populator");
                populator = populatorClass.newInstance();
            }
            catch (Throwable t) {
                populator = TypePopulator.DEFAULT;
            }
        }
        populator.populate(this, clazz);
    }
    
    public boolean defineAnnotatedMethod(final String name, final List<JavaMethodDescriptor> methods, final MethodFactory methodFactory) {
        final JavaMethodDescriptor desc = methods.get(0);
        if (methods.size() == 1) {
            return this.defineAnnotatedMethod(desc, methodFactory);
        }
        final DynamicMethod dynamicMethod = methodFactory.getAnnotatedMethod(this, methods);
        define(this, desc, dynamicMethod);
        return true;
    }
    
    public boolean defineAnnotatedMethod(final Method method, final MethodFactory methodFactory) {
        final JRubyMethod jrubyMethod = method.getAnnotation(JRubyMethod.class);
        if (jrubyMethod == null) {
            return false;
        }
        if (jrubyMethod.compat() == CompatVersion.BOTH || this.getRuntime().getInstanceConfig().getCompatVersion() == jrubyMethod.compat()) {
            final JavaMethodDescriptor desc = new JavaMethodDescriptor(method);
            final DynamicMethod dynamicMethod = methodFactory.getAnnotatedMethod(this, desc);
            define(this, desc, dynamicMethod);
            return true;
        }
        return false;
    }
    
    public boolean defineAnnotatedMethod(final JavaMethodDescriptor desc, final MethodFactory methodFactory) {
        final JRubyMethod jrubyMethod = desc.anno;
        if (jrubyMethod == null) {
            return false;
        }
        if (jrubyMethod.compat() == CompatVersion.BOTH || this.getRuntime().getInstanceConfig().getCompatVersion() == jrubyMethod.compat()) {
            final DynamicMethod dynamicMethod = methodFactory.getAnnotatedMethod(this, desc);
            define(this, desc, dynamicMethod);
            return true;
        }
        return false;
    }
    
    public void defineFastMethod(final String name, final Callback method) {
        final Visibility visibility = name.equals("initialize") ? Visibility.PRIVATE : Visibility.PUBLIC;
        this.addMethod(name, new SimpleCallbackMethod(this, method, visibility));
    }
    
    public void defineFastMethod(final String name, final Callback method, final Visibility visibility) {
        this.addMethod(name, new SimpleCallbackMethod(this, method, visibility));
    }
    
    public void definePrivateMethod(final String name, final Callback method) {
        this.addMethod(name, new FullFunctionCallbackMethod(this, method, Visibility.PRIVATE));
    }
    
    public void defineFastPrivateMethod(final String name, final Callback method) {
        this.addMethod(name, new SimpleCallbackMethod(this, method, Visibility.PRIVATE));
    }
    
    public void defineFastProtectedMethod(final String name, final Callback method) {
        this.addMethod(name, new SimpleCallbackMethod(this, method, Visibility.PROTECTED));
    }
    
    public void undefineMethod(final String name) {
        this.addMethod(name, UndefinedMethod.getInstance());
    }
    
    public void undef(final ThreadContext context, final String name) {
        final Ruby runtime = context.getRuntime();
        if (this == runtime.getObject()) {
            runtime.secure(4);
        }
        if (runtime.getSafeLevel() >= 4 && !this.isTaint()) {
            throw new SecurityException("Insecure: can't undef");
        }
        this.testFrozen("module");
        if (name.equals("__id__") || name.equals("__send__")) {
            runtime.getWarnings().warn(IRubyWarnings.ID.UNDEFINING_BAD, "undefining `" + name + "' may cause serious problem", new Object[0]);
        }
        final DynamicMethod method = this.searchMethod(name);
        if (method.isUndefined()) {
            String s0 = " class";
            RubyModule c = this;
            if (c.isSingleton()) {
                final IRubyObject obj = ((MetaClass)c).getAttached();
                if (obj != null && obj instanceof RubyModule) {
                    c = (RubyModule)obj;
                    s0 = "";
                }
            }
            else if (c.isModule()) {
                s0 = " module";
            }
            throw runtime.newNameError("Undefined method " + name + " for" + s0 + " '" + c.getName() + "'", name);
        }
        this.addMethod(name, UndefinedMethod.getInstance());
        if (this.isSingleton()) {
            final IRubyObject singleton = ((MetaClass)this).getAttached();
            singleton.callMethod(context, "singleton_method_undefined", runtime.newSymbol(name));
        }
        else {
            this.callMethod(context, "method_undefined", runtime.newSymbol(name));
        }
    }
    
    @JRubyMethod(name = { "include?" }, required = 1)
    public IRubyObject include_p(final ThreadContext context, final IRubyObject arg) {
        if (!arg.isModule()) {
            throw context.getRuntime().newTypeError(arg, context.getRuntime().getModule());
        }
        final RubyModule moduleToCompare = (RubyModule)arg;
        for (RubyModule p = this.getSuperClass(); p != null; p = p.getSuperClass()) {
            if (p.isSame(moduleToCompare)) {
                return context.getRuntime().getTrue();
            }
        }
        return context.getRuntime().getFalse();
    }
    
    public void addMethod(final String name, final DynamicMethod method) {
        final Ruby runtime = this.getRuntime();
        if (this == runtime.getObject()) {
            runtime.secure(4);
        }
        if (runtime.getSafeLevel() >= 4 && !this.isTaint()) {
            throw runtime.newSecurityError("Insecure: can't define method");
        }
        this.testFrozen("class/module");
        this.addMethodInternal(name, method);
    }
    
    public void addMethodInternal(final String name, final DynamicMethod method) {
        synchronized (this.getMethodsForWrite()) {
            this.addMethodAtBootTimeOnly(name, method);
            this.invalidateCoreClasses();
            this.invalidateCacheDescendants();
        }
    }
    
    public void addMethodAtBootTimeOnly(final String name, final DynamicMethod method) {
        this.getMethodsForWrite().put(name, method);
        this.getRuntime().addProfiledMethod(name, method);
    }
    
    public void removeMethod(final ThreadContext context, final String name) {
        final Ruby runtime = context.getRuntime();
        if (this == runtime.getObject()) {
            runtime.secure(4);
        }
        if (runtime.getSafeLevel() >= 4 && !this.isTaint()) {
            throw runtime.newSecurityError("Insecure: can't remove method");
        }
        this.testFrozen("class/module");
        synchronized (this.getMethodsForWrite()) {
            final DynamicMethod method = this.getMethodsForWrite().remove(name);
            if (method == null) {
                throw runtime.newNameError("method '" + name + "' not defined in " + this.getName(), name);
            }
            this.invalidateCoreClasses();
            this.invalidateCacheDescendants();
        }
        if (this.isSingleton()) {
            final IRubyObject singleton = ((MetaClass)this).getAttached();
            singleton.callMethod(context, "singleton_method_removed", runtime.newSymbol(name));
        }
        else {
            this.callMethod(context, "method_removed", runtime.newSymbol(name));
        }
    }
    
    public DynamicMethod searchMethod(final String name) {
        return this.searchWithCache(name).method;
    }
    
    public CacheEntry searchWithCache(final String name) {
        final CacheEntry entry = this.cacheHit(name);
        if (entry != null) {
            return entry;
        }
        final int token = this.getCacheToken();
        DynamicMethod method = this.searchMethodInner(name);
        if (method instanceof DefaultMethod) {
            method = ((DefaultMethod)method).getMethodForCaching();
        }
        return (method != null) ? this.addToCache(name, method, token) : this.addToCache(name, UndefinedMethod.getInstance(), token);
    }
    
    public final int getCacheToken() {
        return this.generation;
    }
    
    private final Map<String, CacheEntry> getCachedMethods() {
        return this.cachedMethods;
    }
    
    private final Map<String, CacheEntry> getCachedMethodsForWrite() {
        final Map<String, CacheEntry> myCachedMethods = this.cachedMethods;
        return (myCachedMethods == Collections.EMPTY_MAP) ? (this.cachedMethods = new ConcurrentHashMap<String, CacheEntry>(0, 0.75f, 1)) : myCachedMethods;
    }
    
    private CacheEntry cacheHit(final String name) {
        final CacheEntry cacheEntry = this.getCachedMethods().get(name);
        if (cacheEntry != null && cacheEntry.token == this.getCacheToken()) {
            return cacheEntry;
        }
        return null;
    }
    
    public void becomeSynchronized() {
        this.cacheEntryFactory = new SynchronizedCacheEntryFactory(this.cacheEntryFactory);
    }
    
    public boolean isSynchronized() {
        return this.cacheEntryFactory.hasCacheEntryFactory(SynchronizedCacheEntryFactory.class);
    }
    
    private CacheEntry addToCache(final String name, final DynamicMethod method, final int token) {
        final CacheEntry entry = this.cacheEntryFactory.newCacheEntry(method, token);
        this.getCachedMethodsForWrite().put(name, entry);
        return entry;
    }
    
    protected DynamicMethod searchMethodInner(final String name) {
        final DynamicMethod method = this.getMethods().get(name);
        if (method != null) {
            return method;
        }
        return (this.superClass == null) ? null : this.superClass.searchMethodInner(name);
    }
    
    public void invalidateCacheDescendants() {
        this.invalidateCacheDescendantsInner();
        synchronized (this.getRuntime().getHierarchyLock()) {
            for (final RubyClass includingHierarchy : this.includingHierarchies) {
                includingHierarchy.invalidateCacheDescendants();
            }
        }
    }
    
    protected void invalidateCoreClasses() {
        if (!this.getRuntime().isBooting()) {
            if (this == this.getRuntime().getFixnum()) {
                this.getRuntime().setFixnumReopened(true);
            }
            else if (this == this.getRuntime().getFloat()) {
                this.getRuntime().setFloatReopened(true);
            }
        }
    }
    
    protected void invalidateCacheDescendantsInner() {
        this.generation = this.getRuntime().getNextModuleGeneration();
    }
    
    protected void invalidateConstantCache() {
        this.getRuntime().incrementConstantGeneration();
    }
    
    public DynamicMethod retrieveMethod(final String name) {
        return this.getMethods().get(name);
    }
    
    public RubyModule findImplementer(final RubyModule clazz) {
        for (RubyModule module = this; module != null; module = module.getSuperClass()) {
            if (module.isSame(clazz)) {
                return module;
            }
        }
        return null;
    }
    
    public void addModuleFunction(final String name, final DynamicMethod method) {
        this.addMethod(name, method);
        this.getSingletonClass().addMethod(name, method);
    }
    
    public void defineModuleFunction(final String name, final Callback method) {
        this.definePrivateMethod(name, method);
        this.getSingletonClass().defineMethod(name, method);
    }
    
    public void definePublicModuleFunction(final String name, final Callback method) {
        this.defineMethod(name, method);
        this.getSingletonClass().defineMethod(name, method);
    }
    
    public void defineFastModuleFunction(final String name, final Callback method) {
        this.defineFastPrivateMethod(name, method);
        this.getSingletonClass().defineFastMethod(name, method);
    }
    
    public void defineFastPublicModuleFunction(final String name, final Callback method) {
        this.defineFastMethod(name, method);
        this.getSingletonClass().defineFastMethod(name, method);
    }
    
    public synchronized void defineAlias(final String name, final String oldName) {
        this.testFrozen("module");
        if (oldName.equals(name)) {
            return;
        }
        final Ruby runtime = this.getRuntime();
        if (this == runtime.getObject()) {
            runtime.secure(4);
        }
        if (RubyModule.SCOPE_CAPTURING_METHODS.contains(oldName)) {
            runtime.getWarnings().warn("`" + oldName + "' should not be aliased");
        }
        DynamicMethod method = this.searchMethod(oldName);
        if (method.isUndefined()) {
            if (this.isModule()) {
                method = runtime.getObject().searchMethod(oldName);
            }
            if (method.isUndefined()) {
                throw runtime.newNameError("undefined method `" + oldName + "' for " + (this.isModule() ? "module" : "class") + " `" + this.getName() + "'", oldName);
            }
        }
        this.invalidateCoreClasses();
        this.invalidateCacheDescendants();
        this.putMethod(name, new AliasMethod(this, method, oldName));
    }
    
    public synchronized void defineAliases(final List<String> aliases, final String oldName) {
        this.testFrozen("module");
        final Ruby runtime = this.getRuntime();
        if (this == runtime.getObject()) {
            runtime.secure(4);
        }
        DynamicMethod method = this.searchMethod(oldName);
        if (method.isUndefined()) {
            if (this.isModule()) {
                method = runtime.getObject().searchMethod(oldName);
            }
            if (method.isUndefined()) {
                throw runtime.newNameError("undefined method `" + oldName + "' for " + (this.isModule() ? "module" : "class") + " `" + this.getName() + "'", oldName);
            }
        }
        for (final String name : aliases) {
            if (oldName.equals(name)) {
                continue;
            }
            this.putMethod(name, new AliasMethod(this, method, oldName));
        }
        this.invalidateCoreClasses();
        this.invalidateCacheDescendants();
    }
    
    public RubyClass defineOrGetClassUnder(final String name, RubyClass superClazz) {
        final Ruby runtime = this.getRuntime();
        final IRubyObject classObj = this.getConstantAtSpecial(name);
        RubyClass clazz;
        if (classObj != null) {
            if (!(classObj instanceof RubyClass)) {
                throw runtime.newTypeError(name + " is not a class");
            }
            clazz = (RubyClass)classObj;
            if (superClazz != null) {
                RubyClass tmp;
                for (tmp = clazz.getSuperClass(); tmp != null && tmp.isIncluded(); tmp = tmp.getSuperClass()) {}
                if (tmp != null) {
                    tmp = tmp.getRealClass();
                }
                if (tmp != superClazz) {
                    throw runtime.newTypeError("superclass mismatch for class " + name);
                }
            }
            if (runtime.getSafeLevel() >= 4) {
                throw runtime.newTypeError("extending class prohibited");
            }
        }
        else if (this.classProviders == null || (clazz = this.searchProvidersForClass(name, superClazz)) == null) {
            if (superClazz == null) {
                superClazz = runtime.getObject();
            }
            if (superClazz == runtime.getObject() && RubyInstanceConfig.REIFY_RUBY_CLASSES) {
                clazz = RubyClass.newClass(runtime, superClazz, name, RubyModule.REIFYING_OBJECT_ALLOCATOR, this, true);
            }
            else {
                clazz = RubyClass.newClass(runtime, superClazz, name, superClazz.getAllocator(), this, true);
            }
        }
        return clazz;
    }
    
    public RubyModule defineOrGetModuleUnder(final String name) {
        final Ruby runtime = this.getRuntime();
        final IRubyObject moduleObj = this.getConstantAtSpecial(name);
        RubyModule module;
        if (moduleObj != null) {
            if (!moduleObj.isModule()) {
                throw runtime.newTypeError(name + " is not a module");
            }
            if (runtime.getSafeLevel() >= 4) {
                throw runtime.newSecurityError("extending module prohibited");
            }
            module = (RubyModule)moduleObj;
        }
        else if (this.classProviders == null || (module = this.searchProvidersForModule(name)) == null) {
            module = newModule(runtime, name, this, true);
        }
        return module;
    }
    
    public RubyClass defineClassUnder(final String name, final RubyClass superClass, final ObjectAllocator allocator) {
        return this.getRuntime().defineClassUnder(name, superClass, allocator, this);
    }
    
    public RubyModule defineModuleUnder(final String name) {
        return this.getRuntime().defineModuleUnder(name, this);
    }
    
    private void addAccessor(final ThreadContext context, String internedName, Visibility visibility, final boolean readable, final boolean writeable) {
        assert internedName == internedName.intern() : internedName + " is not interned";
        final Ruby runtime = context.getRuntime();
        if (visibility != Visibility.PRIVATE) {
            if (visibility == Visibility.MODULE_FUNCTION) {
                visibility = Visibility.PRIVATE;
            }
        }
        final String variableName = ("@" + internedName).intern();
        if (readable) {
            this.addMethod(internedName, new JavaMethod.JavaMethodZero(this, visibility, CallConfiguration.FrameNoneScopeNone) {
                private RubyClass.VariableAccessor accessor = RubyClass.VariableAccessor.DUMMY_ACCESSOR;
                
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
                    final IRubyObject variable = (IRubyObject)this.verifyAccessor(self.getMetaClass().getRealClass()).get(self);
                    return (variable == null) ? runtime.getNil() : variable;
                }
                
                private RubyClass.VariableAccessor verifyAccessor(final RubyClass cls) {
                    RubyClass.VariableAccessor localAccessor = this.accessor;
                    if (localAccessor.getClassId() != cls.id) {
                        localAccessor = cls.getVariableAccessorForRead(variableName);
                        this.accessor = localAccessor;
                    }
                    return localAccessor;
                }
            });
            this.callMethod(context, "method_added", runtime.fastNewSymbol(internedName));
        }
        if (writeable) {
            internedName = (internedName + "=").intern();
            this.addMethod(internedName, new JavaMethod.JavaMethodOne(this, visibility, CallConfiguration.FrameNoneScopeNone) {
                private RubyClass.VariableAccessor accessor = RubyClass.VariableAccessor.DUMMY_ACCESSOR;
                
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg1) {
                    this.verifyAccessor(self.getMetaClass().getRealClass()).set(self, arg1);
                    return arg1;
                }
                
                private RubyClass.VariableAccessor verifyAccessor(final RubyClass cls) {
                    RubyClass.VariableAccessor localAccessor = this.accessor;
                    if (localAccessor.getClassId() != cls.id) {
                        localAccessor = cls.getVariableAccessorForWrite(variableName);
                        this.accessor = localAccessor;
                    }
                    return localAccessor;
                }
            });
            this.callMethod(context, "method_added", runtime.fastNewSymbol(internedName));
        }
    }
    
    public void setMethodVisibility(final IRubyObject[] methods, final Visibility visibility) {
        if (this.getRuntime().getSafeLevel() >= 4 && !this.isTaint()) {
            throw this.getRuntime().newSecurityError("Insecure: can't change method visibility");
        }
        for (int i = 0; i < methods.length; ++i) {
            this.exportMethod(methods[i].asJavaString(), visibility);
        }
    }
    
    public void exportMethod(final String name, final Visibility visibility) {
        final Ruby runtime = this.getRuntime();
        if (this == runtime.getObject()) {
            this.getRuntime().secure(4);
        }
        final DynamicMethod method = this.deepMethodSearch(name, runtime);
        if (method.getVisibility() != visibility) {
            if (this == method.getImplementationClass()) {
                method.setVisibility(visibility);
            }
            else {
                this.addMethod(name, new WrapperMethod(this, method, visibility));
            }
            this.invalidateCoreClasses();
            this.invalidateCacheDescendants();
        }
    }
    
    private DynamicMethod deepMethodSearch(final String name, final Ruby runtime) {
        DynamicMethod method = this.searchMethod(name);
        if (method.isUndefined() && this.isModule()) {
            method = runtime.getObject().searchMethod(name);
        }
        if (method.isUndefined()) {
            throw runtime.newNameError("undefined method '" + name + "' for " + (this.isModule() ? "module" : "class") + " '" + this.getName() + "'", name);
        }
        return method;
    }
    
    public boolean isMethodBound(final String name, final boolean checkVisibility) {
        final DynamicMethod method = this.searchMethod(name);
        return !method.isUndefined() && (!checkVisibility || method.getVisibility() != Visibility.PRIVATE);
    }
    
    public boolean isMethodBound(final String name, final boolean checkVisibility, final boolean checkRespondTo) {
        if (!checkRespondTo) {
            return this.isMethodBound(name, checkVisibility);
        }
        final DynamicMethod method = this.searchMethod(name);
        return !method.isUndefined() && !method.isNotImplemented() && (!checkVisibility || method.getVisibility() != Visibility.PRIVATE);
    }
    
    public void checkMethodBound(final ThreadContext context, final IRubyObject[] args, final Visibility visibility) {
        if (args.length == 0) {
            throw context.getRuntime().newArgumentError("no method name given");
        }
        final String name = args[0].asJavaString();
        final DynamicMethod method = this.searchMethod(name);
        if (!method.isUndefined() && method.getVisibility() != visibility) {
            final Ruby runtime = context.getRuntime();
            final RubyNameError.RubyNameErrorMessage message = new RubyNameError.RubyNameErrorMessage(runtime, this, runtime.newString(name), method.getVisibility(), CallType.NORMAL);
            throw runtime.newNoMethodError(message.to_str(context).asJavaString(), name, RubyModule.NEVER);
        }
    }
    
    public IRubyObject newMethod(final IRubyObject receiver, final String methodName, final boolean bound, final Visibility visibility) {
        return this.newMethod(receiver, methodName, bound, visibility, false, true);
    }
    
    public IRubyObject newMethod(final IRubyObject receiver, final String methodName, final boolean bound, final Visibility visibility, final boolean respondToMissing) {
        return this.newMethod(receiver, methodName, bound, visibility, respondToMissing, true);
    }
    
    public IRubyObject newMethod(final IRubyObject receiver, final String methodName, final boolean bound, final Visibility visibility, final boolean respondToMissing, final boolean priv) {
        DynamicMethod method = this.searchMethod(methodName);
        if (method.isUndefined() || (visibility != null && method.getVisibility() != visibility)) {
            if (!respondToMissing) {
                throw this.getRuntime().newNameError("undefined method `" + methodName + "' for class `" + this.getName() + "'", methodName);
            }
            if (!receiver.respondsToMissing(methodName, priv)) {
                throw this.getRuntime().newNameError("undefined method `" + methodName + "' for class `" + this.getName() + "'", methodName);
            }
            method = new RespondToMissingMethod(this, Visibility.PUBLIC, methodName);
        }
        RubyModule implementationModule;
        RubyModule originModule;
        for (implementationModule = method.getImplementationClass(), originModule = this; originModule != implementationModule && originModule.isSingleton(); originModule = ((MetaClass)originModule).getRealClass()) {}
        RubyMethod newMethod;
        if (bound) {
            newMethod = RubyMethod.newMethod(implementationModule, methodName, originModule, methodName, method, receiver);
        }
        else {
            newMethod = RubyUnboundMethod.newUnboundMethod(implementationModule, methodName, originModule, methodName, method);
        }
        newMethod.infectBy(this);
        return newMethod;
    }
    
    @JRubyMethod(name = { "define_method" }, visibility = Visibility.PRIVATE, reads = { FrameField.VISIBILITY })
    public IRubyObject define_method(final ThreadContext context, final IRubyObject arg0, final Block block) {
        final Ruby runtime = context.getRuntime();
        final String name = arg0.asJavaString().intern();
        DynamicMethod newMethod = null;
        final Visibility visibility = Visibility.PUBLIC;
        final RubyProc proc = runtime.newProc(Block.Type.LAMBDA, block);
        proc.getBlock().type = Block.Type.LAMBDA;
        newMethod = this.createProcMethod(name, visibility, proc);
        RuntimeHelpers.addInstanceMethod(this, name, newMethod, visibility, context, runtime);
        return proc;
    }
    
    @JRubyMethod(name = { "define_method" }, visibility = Visibility.PRIVATE, reads = { FrameField.VISIBILITY })
    public IRubyObject define_method(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final Ruby runtime = context.getRuntime();
        final String name = arg0.asJavaString().intern();
        DynamicMethod newMethod = null;
        final Visibility visibility = Visibility.PUBLIC;
        IRubyObject body;
        if (runtime.getProc().isInstance(arg1)) {
            final RubyProc proc = (RubyProc)(body = arg1);
            newMethod = this.createProcMethod(name, visibility, proc);
        }
        else {
            if (!runtime.getMethod().isInstance(arg1)) {
                throw runtime.newTypeError("wrong argument type " + arg1.getType().getName() + " (expected Proc/Method)");
            }
            final RubyMethod method = (RubyMethod)(body = arg1);
            newMethod = new MethodMethod(this, method.unbind(), visibility);
        }
        RuntimeHelpers.addInstanceMethod(this, name, newMethod, visibility, context, runtime);
        return body;
    }
    
    @Deprecated
    public IRubyObject define_method(final ThreadContext context, final IRubyObject[] args, final Block block) {
        switch (args.length) {
            case 1: {
                return this.define_method(context, args[0], block);
            }
            case 2: {
                return this.define_method(context, args[0], args[1], block);
            }
            default: {
                throw context.getRuntime().newArgumentError("wrong number of arguments (" + args.length + " for 2)");
            }
        }
    }
    
    private DynamicMethod createProcMethod(final String name, final Visibility visibility, final RubyProc proc) {
        final Block block = proc.getBlock();
        block.getBinding().getFrame().setKlazz(this);
        block.getBinding().getFrame().setName(name);
        block.getBinding().setMethod(name);
        final StaticScope scope = block.getBody().getStaticScope();
        scope.makeArgumentScope();
        final Arity arity = block.arity();
        scope.setRequiredArgs(arity.required());
        if (!arity.isFixed()) {
            scope.setRestArg(arity.required());
        }
        return new ProcMethod(this, proc, visibility);
    }
    
    @Deprecated
    public IRubyObject executeUnder(final ThreadContext context, final Callback method, final IRubyObject[] args, final Block block) {
        context.preExecuteUnder(this, block);
        try {
            return method.execute(this, args, block);
        }
        finally {
            context.postExecuteUnder();
        }
    }
    
    @JRubyMethod(name = { "name" })
    public IRubyObject name() {
        final Ruby runtime = this.getRuntime();
        if (this.getBaseName() == null) {
            return RubyString.newEmptyString(runtime);
        }
        return runtime.newString(this.getName());
    }
    
    @JRubyMethod(name = { "name" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject name19() {
        final Ruby runtime = this.getRuntime();
        if (this.getBaseName() == null) {
            return runtime.getNil();
        }
        return runtime.newString(this.getName());
    }
    
    protected IRubyObject cloneMethods(final RubyModule clone) {
        final RubyModule realType = this.getNonIncludedClass();
        for (final Map.Entry<String, DynamicMethod> entry : this.getMethods().entrySet()) {
            final DynamicMethod method = entry.getValue();
            if (method.getImplementationClass() == realType || method.isUndefined()) {
                final DynamicMethod clonedMethod = method.dup();
                clonedMethod.setImplementationClass(clone);
                clone.putMethod(entry.getKey(), clonedMethod);
            }
        }
        return clone;
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1)
    public IRubyObject initialize_copy(final IRubyObject original) {
        super.initialize_copy(original);
        final RubyModule originalModule = (RubyModule)original;
        if (!this.getMetaClass().isSingleton()) {
            this.setMetaClass(originalModule.getSingletonClassClone());
        }
        this.setSuperClass(originalModule.getSuperClass());
        if (originalModule.hasVariables()) {
            this.syncVariables(originalModule);
        }
        this.syncConstants(originalModule);
        originalModule.cloneMethods(this);
        return this;
    }
    
    public void syncConstants(final RubyModule other) {
        if (other.getConstantMap() != Collections.EMPTY_MAP) {
            this.getConstantMapForWrite().putAll(other.getConstantMap());
        }
    }
    
    public void syncClassVariables(final RubyModule other) {
        if (other.getClassVariablesForRead() != Collections.EMPTY_MAP) {
            this.getClassVariables().putAll(other.getClassVariablesForRead());
        }
    }
    
    @JRubyMethod(name = { "included_modules" })
    public RubyArray included_modules(final ThreadContext context) {
        final RubyArray ary = context.getRuntime().newArray();
        for (RubyModule p = this.getSuperClass(); p != null; p = p.getSuperClass()) {
            if (p.isIncluded()) {
                ary.append(p.getNonIncludedClass());
            }
        }
        return ary;
    }
    
    @JRubyMethod(name = { "ancestors" })
    public RubyArray ancestors(final ThreadContext context) {
        return context.getRuntime().newArray(this.getAncestorList());
    }
    
    @Deprecated
    public RubyArray ancestors() {
        return this.getRuntime().newArray(this.getAncestorList());
    }
    
    public List<IRubyObject> getAncestorList() {
        final ArrayList<IRubyObject> list = new ArrayList<IRubyObject>();
        for (RubyModule module = this; module != null; module = module.getSuperClass()) {
            if (!module.isSingleton()) {
                list.add(module.getNonIncludedClass());
            }
        }
        return list;
    }
    
    public boolean hasModuleInHierarchy(final RubyModule type) {
        for (RubyModule module = this; module != null; module = module.getSuperClass()) {
            if (module.getNonIncludedClass() == type) {
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        return this.id;
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum(this.id);
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s() {
        if (this.isSingleton()) {
            final IRubyObject attached = ((MetaClass)this).getAttached();
            final StringBuilder buffer = new StringBuilder("#<Class:");
            if (attached != null) {
                if (attached instanceof RubyClass || attached instanceof RubyModule) {
                    buffer.append(attached.inspect());
                }
                else {
                    buffer.append(attached.anyToString());
                }
            }
            buffer.append(">");
            return this.getRuntime().newString(buffer.toString());
        }
        return this.getRuntime().newString(this.getName());
    }
    
    @JRubyMethod(name = { "===" }, required = 1)
    public RubyBoolean op_eqq(final ThreadContext context, final IRubyObject obj) {
        return context.getRuntime().newBoolean(this.isInstance(obj));
    }
    
    public boolean equals(final Object other) {
        return this == other;
    }
    
    @JRubyMethod(name = { "==" }, required = 1)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        return super.op_equal(context, other);
    }
    
    @JRubyMethod(name = { "freeze" })
    public final IRubyObject freeze(final ThreadContext context) {
        this.to_s();
        return super.freeze(context);
    }
    
    @JRubyMethod(name = { "<=" }, required = 1)
    public IRubyObject op_le(final IRubyObject obj) {
        if (!(obj instanceof RubyModule)) {
            throw this.getRuntime().newTypeError("compared with non class/module");
        }
        if (this.isKindOfModule((RubyModule)obj)) {
            return this.getRuntime().getTrue();
        }
        if (((RubyModule)obj).isKindOfModule(this)) {
            return this.getRuntime().getFalse();
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "<" }, required = 1)
    public IRubyObject op_lt(final IRubyObject obj) {
        return (obj == this) ? this.getRuntime().getFalse() : this.op_le(obj);
    }
    
    @JRubyMethod(name = { ">=" }, required = 1)
    public IRubyObject op_ge(final IRubyObject obj) {
        if (!(obj instanceof RubyModule)) {
            throw this.getRuntime().newTypeError("compared with non class/module");
        }
        return ((RubyModule)obj).op_le(this);
    }
    
    @JRubyMethod(name = { ">" }, required = 1)
    public IRubyObject op_gt(final IRubyObject obj) {
        return (this == obj) ? this.getRuntime().getFalse() : this.op_ge(obj);
    }
    
    @JRubyMethod(name = { "<=>" }, required = 1)
    public IRubyObject op_cmp(final IRubyObject obj) {
        if (this == obj) {
            return this.getRuntime().newFixnum(0);
        }
        if (!(obj instanceof RubyModule)) {
            return this.getRuntime().getNil();
        }
        final RubyModule module = (RubyModule)obj;
        if (module.isKindOfModule(this)) {
            return this.getRuntime().newFixnum(1);
        }
        if (this.isKindOfModule(module)) {
            return this.getRuntime().newFixnum(-1);
        }
        return this.getRuntime().getNil();
    }
    
    public boolean isKindOfModule(final RubyModule type) {
        for (RubyModule module = this; module != null; module = module.getSuperClass()) {
            if (module.isSame(type)) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean isSame(final RubyModule module) {
        return this == module;
    }
    
    @JRubyMethod(name = { "initialize" }, frame = true, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final Block block) {
        if (block.isGiven()) {
            block.getBinding().setVisibility(Visibility.PUBLIC);
            block.yieldNonArray(this.getRuntime().getCurrentContext(), this, this, this);
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "initialize" }, frame = true, compat = CompatVersion.RUBY1_9, visibility = Visibility.PRIVATE)
    public IRubyObject initialize19(final ThreadContext context, final Block block) {
        if (block.isGiven()) {
            block.getBinding().setVisibility(Visibility.PUBLIC);
            this.module_exec(context, block);
        }
        return this.getRuntime().getNil();
    }
    
    public void addReadWriteAttribute(final ThreadContext context, final String name) {
        this.addAccessor(context, name.intern(), Visibility.PUBLIC, true, true);
    }
    
    public void addReadAttribute(final ThreadContext context, final String name) {
        this.addAccessor(context, name.intern(), Visibility.PUBLIC, true, false);
    }
    
    public void addWriteAttribute(final ThreadContext context, final String name) {
        this.addAccessor(context, name.intern(), Visibility.PUBLIC, false, true);
    }
    
    @JRubyMethod(name = { "attr" }, required = 1, optional = 1, visibility = Visibility.PRIVATE, reads = { FrameField.VISIBILITY }, compat = CompatVersion.RUBY1_8)
    public IRubyObject attr(final ThreadContext context, final IRubyObject[] args) {
        final boolean writeable = args.length > 1 && args[1].isTrue();
        final Visibility visibility = context.getCurrentVisibility();
        this.addAccessor(context, args[0].asJavaString().intern(), visibility, true, writeable);
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "attr" }, rest = true, visibility = Visibility.PRIVATE, reads = { FrameField.VISIBILITY }, compat = CompatVersion.RUBY1_9)
    public IRubyObject attr19(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (args.length == 2 && (args[1] == runtime.getTrue() || args[1] == runtime.getFalse())) {
            runtime.getWarnings().warn(IRubyWarnings.ID.OBSOLETE_ARGUMENT, "optional boolean argument is obsoleted", new Object[0]);
            this.addAccessor(context, args[0].asJavaString().intern(), context.getCurrentVisibility(), args[0].isTrue(), true);
            return runtime.getNil();
        }
        return this.attr_reader(context, args);
    }
    
    @Deprecated
    public IRubyObject attr_reader(final IRubyObject[] args) {
        return this.attr_reader(this.getRuntime().getCurrentContext(), args);
    }
    
    @JRubyMethod(name = { "attr_reader" }, rest = true, visibility = Visibility.PRIVATE, reads = { FrameField.VISIBILITY })
    public IRubyObject attr_reader(final ThreadContext context, final IRubyObject[] args) {
        final Visibility visibility = context.getCurrentVisibility();
        for (int i = 0; i < args.length; ++i) {
            this.addAccessor(context, args[i].asJavaString().intern(), visibility, true, false);
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "attr_writer" }, rest = true, visibility = Visibility.PRIVATE, reads = { FrameField.VISIBILITY })
    public IRubyObject attr_writer(final ThreadContext context, final IRubyObject[] args) {
        final Visibility visibility = context.getCurrentVisibility();
        for (int i = 0; i < args.length; ++i) {
            this.addAccessor(context, args[i].asJavaString().intern(), visibility, false, true);
        }
        return context.getRuntime().getNil();
    }
    
    @Deprecated
    public IRubyObject attr_accessor(final IRubyObject[] args) {
        return this.attr_accessor(this.getRuntime().getCurrentContext(), args);
    }
    
    @JRubyMethod(name = { "attr_accessor" }, rest = true, visibility = Visibility.PRIVATE, reads = { FrameField.VISIBILITY })
    public IRubyObject attr_accessor(final ThreadContext context, final IRubyObject[] args) {
        final Visibility visibility = context.getCurrentVisibility();
        for (int i = 0; i < args.length; ++i) {
            this.addAccessor(context, args[i].asJavaString().intern(), visibility, true, true);
        }
        return context.getRuntime().getNil();
    }
    
    private RubyArray instance_methods(final IRubyObject[] args, final Visibility visibility, final boolean not, final boolean useSymbols) {
        final boolean includeSuper = args.length <= 0 || args[0].isTrue();
        final Ruby runtime = this.getRuntime();
        final RubyArray ary = runtime.newArray();
        final Set<String> seen = new HashSet<String>();
        this.populateInstanceMethodNames(seen, ary, visibility, not, useSymbols, includeSuper);
        return ary;
    }
    
    public void populateInstanceMethodNames(final Set<String> seen, final RubyArray ary, final Visibility visibility, final boolean not, final boolean useSymbols, final boolean includeSuper) {
        final Ruby runtime = this.getRuntime();
        for (RubyModule type = this; type != null; type = type.getSuperClass()) {
            final RubyModule realType = type.getNonIncludedClass();
            for (final Map.Entry entry : type.getMethods().entrySet()) {
                final String methodName = entry.getKey();
                if (!seen.contains(methodName)) {
                    seen.add(methodName);
                    final DynamicMethod method = entry.getValue();
                    if (method.getImplementationClass() != realType || ((not || method.getVisibility() != visibility) && (!not || method.getVisibility() == visibility)) || method.isUndefined()) {
                        continue;
                    }
                    ary.append(useSymbols ? runtime.newSymbol(methodName) : runtime.newString(methodName));
                }
            }
            if (!includeSuper) {
                break;
            }
        }
    }
    
    @JRubyMethod(name = { "instance_methods" }, optional = 1, compat = CompatVersion.RUBY1_8)
    public RubyArray instance_methods(final IRubyObject[] args) {
        return this.instance_methods(args, Visibility.PRIVATE, true, false);
    }
    
    @JRubyMethod(name = { "instance_methods" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public RubyArray instance_methods19(final IRubyObject[] args) {
        return this.instance_methods(args, Visibility.PRIVATE, true, true);
    }
    
    @JRubyMethod(name = { "public_instance_methods" }, optional = 1, compat = CompatVersion.RUBY1_8)
    public RubyArray public_instance_methods(final IRubyObject[] args) {
        return this.instance_methods(args, Visibility.PUBLIC, false, false);
    }
    
    @JRubyMethod(name = { "public_instance_methods" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public RubyArray public_instance_methods19(final IRubyObject[] args) {
        return this.instance_methods(args, Visibility.PUBLIC, false, true);
    }
    
    @JRubyMethod(name = { "instance_method" }, required = 1)
    public IRubyObject instance_method(final IRubyObject symbol) {
        return this.newMethod(null, symbol.asJavaString(), false, null);
    }
    
    @JRubyMethod(name = { "protected_instance_methods" }, optional = 1, compat = CompatVersion.RUBY1_8)
    public RubyArray protected_instance_methods(final IRubyObject[] args) {
        return this.instance_methods(args, Visibility.PROTECTED, false, false);
    }
    
    @JRubyMethod(name = { "protected_instance_methods" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public RubyArray protected_instance_methods19(final IRubyObject[] args) {
        return this.instance_methods(args, Visibility.PROTECTED, false, true);
    }
    
    @JRubyMethod(name = { "private_instance_methods" }, optional = 1, compat = CompatVersion.RUBY1_8)
    public RubyArray private_instance_methods(final IRubyObject[] args) {
        return this.instance_methods(args, Visibility.PRIVATE, false, false);
    }
    
    @JRubyMethod(name = { "private_instance_methods" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public RubyArray private_instance_methods19(final IRubyObject[] args) {
        return this.instance_methods(args, Visibility.PRIVATE, false, true);
    }
    
    @JRubyMethod(name = { "append_features" }, required = 1, visibility = Visibility.PRIVATE)
    public RubyModule append_features(final IRubyObject module) {
        if (!(module instanceof RubyModule)) {
            throw this.getRuntime().newTypeError(module, this.getRuntime().getClassClass());
        }
        ((RubyModule)module).includeModule(this);
        return this;
    }
    
    @JRubyMethod(name = { "extend_object" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject extend_object(final IRubyObject obj) {
        obj.getSingletonClass().includeModule(this);
        return obj;
    }
    
    @JRubyMethod(name = { "include" }, rest = true, visibility = Visibility.PRIVATE)
    public RubyModule include(final IRubyObject[] modules) {
        final ThreadContext context = this.getRuntime().getCurrentContext();
        int i = modules.length;
        while (--i >= 0) {
            final IRubyObject obj = modules[i];
            if (!obj.isModule()) {
                throw context.getRuntime().newTypeError(obj, context.getRuntime().getModule());
            }
        }
        for (i = modules.length - 1; i >= 0; --i) {
            modules[i].callMethod(context, "append_features", this);
            modules[i].callMethod(context, "included", this);
        }
        return this;
    }
    
    @JRubyMethod(name = { "included" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject included(final ThreadContext context, final IRubyObject other) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "extended" }, required = 1, frame = true, visibility = Visibility.PRIVATE)
    public IRubyObject extended(final ThreadContext context, final IRubyObject other, final Block block) {
        return context.getRuntime().getNil();
    }
    
    private void setVisibility(final ThreadContext context, final IRubyObject[] args, final Visibility visibility) {
        if (context.getRuntime().getSafeLevel() >= 4 && !this.isTaint()) {
            throw context.getRuntime().newSecurityError("Insecure: can't change method visibility");
        }
        if (args.length == 0) {
            context.setCurrentVisibility(visibility);
        }
        else {
            this.setMethodVisibility(args, visibility);
        }
    }
    
    @JRubyMethod(name = { "public" }, rest = true, visibility = Visibility.PRIVATE, writes = { FrameField.VISIBILITY })
    public RubyModule rbPublic(final ThreadContext context, final IRubyObject[] args) {
        this.setVisibility(context, args, Visibility.PUBLIC);
        return this;
    }
    
    @JRubyMethod(name = { "protected" }, rest = true, visibility = Visibility.PRIVATE, writes = { FrameField.VISIBILITY })
    public RubyModule rbProtected(final ThreadContext context, final IRubyObject[] args) {
        this.setVisibility(context, args, Visibility.PROTECTED);
        return this;
    }
    
    @JRubyMethod(name = { "private" }, rest = true, visibility = Visibility.PRIVATE, writes = { FrameField.VISIBILITY })
    public RubyModule rbPrivate(final ThreadContext context, final IRubyObject[] args) {
        this.setVisibility(context, args, Visibility.PRIVATE);
        return this;
    }
    
    @JRubyMethod(name = { "module_function" }, rest = true, visibility = Visibility.PRIVATE, writes = { FrameField.VISIBILITY })
    public RubyModule module_function(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (runtime.getSafeLevel() >= 4 && !this.isTaint()) {
            throw runtime.newSecurityError("Insecure: can't change method visibility");
        }
        if (args.length == 0) {
            context.setCurrentVisibility(Visibility.MODULE_FUNCTION);
        }
        else {
            this.setMethodVisibility(args, Visibility.PRIVATE);
            for (int i = 0; i < args.length; ++i) {
                final String name = args[i].asJavaString().intern();
                final DynamicMethod method = this.deepMethodSearch(name, runtime);
                this.getSingletonClass().addMethod(name, new WrapperMethod(this.getSingletonClass(), method, Visibility.PUBLIC));
                this.callMethod(context, "singleton_method_added", context.getRuntime().fastNewSymbol(name));
            }
        }
        return this;
    }
    
    @JRubyMethod(name = { "method_added" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject method_added(final ThreadContext context, final IRubyObject nothing) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "method_removed" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject method_removed(final ThreadContext context, final IRubyObject nothing) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "method_undefined" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject method_undefined(final ThreadContext context, final IRubyObject nothing) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "method_defined?" }, required = 1)
    public RubyBoolean method_defined_p(final ThreadContext context, final IRubyObject symbol) {
        return this.isMethodBound(symbol.asJavaString(), true) ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "public_method_defined?" }, required = 1)
    public IRubyObject public_method_defined(final ThreadContext context, final IRubyObject symbol) {
        final DynamicMethod method = this.searchMethod(symbol.asJavaString());
        return context.getRuntime().newBoolean(!method.isUndefined() && method.getVisibility() == Visibility.PUBLIC);
    }
    
    @JRubyMethod(name = { "protected_method_defined?" }, required = 1)
    public IRubyObject protected_method_defined(final ThreadContext context, final IRubyObject symbol) {
        final DynamicMethod method = this.searchMethod(symbol.asJavaString());
        return context.getRuntime().newBoolean(!method.isUndefined() && method.getVisibility() == Visibility.PROTECTED);
    }
    
    @JRubyMethod(name = { "private_method_defined?" }, required = 1)
    public IRubyObject private_method_defined(final ThreadContext context, final IRubyObject symbol) {
        final DynamicMethod method = this.searchMethod(symbol.asJavaString());
        return context.getRuntime().newBoolean(!method.isUndefined() && method.getVisibility() == Visibility.PRIVATE);
    }
    
    @JRubyMethod(name = { "public_class_method" }, rest = true)
    public RubyModule public_class_method(final IRubyObject[] args) {
        this.getMetaClass().setMethodVisibility(args, Visibility.PUBLIC);
        return this;
    }
    
    @JRubyMethod(name = { "private_class_method" }, rest = true)
    public RubyModule private_class_method(final IRubyObject[] args) {
        this.getMetaClass().setMethodVisibility(args, Visibility.PRIVATE);
        return this;
    }
    
    @JRubyMethod(name = { "alias_method" }, required = 2, visibility = Visibility.PRIVATE)
    public RubyModule alias_method(final ThreadContext context, final IRubyObject newId, final IRubyObject oldId) {
        final String newName = newId.asJavaString();
        this.defineAlias(newName, oldId.asJavaString());
        final RubySymbol newSym = (RubySymbol)((newId instanceof RubySymbol) ? newId : context.getRuntime().newSymbol(newName));
        if (this.isSingleton()) {
            ((MetaClass)this).getAttached().callMethod(context, "singleton_method_added", newSym);
        }
        else {
            this.callMethod(context, "method_added", newSym);
        }
        return this;
    }
    
    @JRubyMethod(name = { "undef_method" }, required = 1, rest = true, visibility = Visibility.PRIVATE)
    public RubyModule undef_method(final ThreadContext context, final IRubyObject[] args) {
        for (int i = 0; i < args.length; ++i) {
            this.undef(context, args[i].asJavaString());
        }
        return this;
    }
    
    @JRubyMethod(name = { "module_eval", "class_eval" }, frame = true)
    public IRubyObject module_eval(final ThreadContext context, final Block block) {
        return this.specificEval(context, this, block);
    }
    
    @JRubyMethod(name = { "module_eval", "class_eval" }, frame = true)
    public IRubyObject module_eval(final ThreadContext context, final IRubyObject arg0, final Block block) {
        return this.specificEval(context, this, arg0, block);
    }
    
    @JRubyMethod(name = { "module_eval", "class_eval" }, frame = true)
    public IRubyObject module_eval(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.specificEval(context, this, arg0, arg1, block);
    }
    
    @JRubyMethod(name = { "module_eval", "class_eval" }, frame = true)
    public IRubyObject module_eval(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.specificEval(context, this, arg0, arg1, arg2, block);
    }
    
    @Deprecated
    public IRubyObject module_eval(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return this.specificEval(context, this, args, block);
    }
    
    @JRubyMethod(name = { "module_exec", "class_exec" }, frame = true)
    public IRubyObject module_exec(final ThreadContext context, final Block block) {
        if (block.isGiven()) {
            return this.yieldUnder(context, this, block);
        }
        throw context.getRuntime().newLocalJumpErrorNoBlock();
    }
    
    @JRubyMethod(name = { "module_exec", "class_exec" }, rest = true, frame = true)
    public IRubyObject module_exec(final ThreadContext context, final IRubyObject[] args, final Block block) {
        if (block.isGiven()) {
            return this.yieldUnder(context, this, args, block);
        }
        throw context.getRuntime().newLocalJumpErrorNoBlock();
    }
    
    @JRubyMethod(name = { "remove_method" }, required = 1, rest = true, visibility = Visibility.PRIVATE)
    public RubyModule remove_method(final ThreadContext context, final IRubyObject[] args) {
        for (int i = 0; i < args.length; ++i) {
            this.removeMethod(context, args[i].asJavaString());
        }
        return this;
    }
    
    public static void marshalTo(final RubyModule module, final MarshalStream output) throws IOException {
        output.registerLinkTarget(module);
        output.writeString(MarshalStream.getPathFromClass(module));
    }
    
    public static RubyModule unmarshalFrom(final UnmarshalStream input) throws IOException {
        final String name = RubyString.byteListToString(input.unmarshalString());
        final RubyModule result = UnmarshalStream.getModuleFromPath(input.getRuntime(), name);
        input.registerLinkTarget(result);
        return result;
    }
    
    @JRubyMethod(name = { "nesting" }, frame = true, meta = true)
    public static RubyArray nesting(final ThreadContext context, final IRubyObject recv, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyModule object = runtime.getObject();
        final StaticScope scope = context.getCurrentScope().getStaticScope();
        final RubyArray result = runtime.newArray();
        for (StaticScope current = scope; current.getModule() != object; current = current.getPreviousCRefScope()) {
            result.append(current.getModule());
        }
        return result;
    }
    
    private void doIncludeModule(final RubyModule baseModule) {
        final List<RubyModule> modulesToInclude = this.gatherModules(baseModule);
        RubyModule currentInclusionPoint = this;
    Label_0016:
        for (final RubyModule nextModule : modulesToInclude) {
            this.checkForCyclicInclude(nextModule);
            boolean superclassSeen = false;
            RubyClass nextClass = this.getSuperClass();
            while (nextClass != null) {
                if (this.doesTheClassWrapTheModule(nextClass, nextModule)) {
                    if (!superclassSeen) {
                        currentInclusionPoint = nextClass;
                        continue Label_0016;
                    }
                    continue Label_0016;
                }
                else {
                    superclassSeen = true;
                    nextClass = nextClass.getSuperClass();
                }
            }
            currentInclusionPoint = this.proceedWithInclude(currentInclusionPoint, nextModule);
        }
    }
    
    private boolean doesTheClassWrapTheModule(final RubyClass theClass, final RubyModule theModule) {
        return theClass.isIncluded() && theClass.getNonIncludedClass() == theModule.getNonIncludedClass();
    }
    
    private List<RubyModule> gatherModules(RubyModule baseModule) {
        final List<RubyModule> modulesToInclude = new ArrayList<RubyModule>();
        while (baseModule != null) {
            modulesToInclude.add(baseModule);
            baseModule = baseModule.getSuperClass();
        }
        return modulesToInclude;
    }
    
    private RubyModule proceedWithInclude(RubyModule insertAbove, final RubyModule moduleToInclude) {
        final RubyClass wrapper = new IncludedModuleWrapper(this.getRuntime(), insertAbove.getSuperClass(), moduleToInclude.getNonIncludedClass());
        if (insertAbove instanceof RubyClass) {
            final RubyClass insertAboveClass = (RubyClass)insertAbove;
            if (insertAboveClass.getSuperClass() != null) {
                insertAboveClass.getSuperClass().replaceSubclass(insertAboveClass, wrapper);
            }
            wrapper.addSubclass(insertAboveClass);
        }
        insertAbove.setSuperClass(wrapper);
        insertAbove = insertAbove.getSuperClass();
        return insertAbove;
    }
    
    @JRubyMethod(name = { "class_variable_defined?" }, required = 1)
    public IRubyObject class_variable_defined_p(final ThreadContext context, final IRubyObject var) {
        final String internedName = this.validateClassVariable(var.asJavaString().intern());
        RubyModule module = this;
        while (!module.fastHasClassVariable(internedName)) {
            if ((module = module.getSuperClass()) == null) {
                return context.getRuntime().getFalse();
            }
        }
        return context.getRuntime().getTrue();
    }
    
    @JRubyMethod(name = { "class_variable_get" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject class_variable_get(final IRubyObject var) {
        return this.fastGetClassVar(this.validateClassVariable(var.asJavaString()).intern());
    }
    
    @JRubyMethod(name = { "class_variable_set" }, required = 2, visibility = Visibility.PRIVATE)
    public IRubyObject class_variable_set(final IRubyObject var, final IRubyObject value) {
        return this.fastSetClassVar(this.validateClassVariable(var.asJavaString()).intern(), value);
    }
    
    @JRubyMethod(name = { "remove_class_variable" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject remove_class_variable(final ThreadContext context, IRubyObject name) {
        return this.removeClassVariable(name.asJavaString());
    }
    
    @JRubyMethod(name = { "class_variables" }, compat = CompatVersion.RUBY1_8)
    public RubyArray class_variables(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyArray ary = runtime.newArray();
        final Collection<String> names = this.classVariablesCommon();
        ary.addAll(names);
        return ary;
    }
    
    @JRubyMethod(name = { "class_variables" }, compat = CompatVersion.RUBY1_9)
    public RubyArray class_variables19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyArray ary = runtime.newArray();
        final Collection<String> names = this.classVariablesCommon();
        for (final String name : names) {
            ary.add(runtime.newSymbol(name));
        }
        return ary;
    }
    
    private Collection<String> classVariablesCommon() {
        final Set<String> names = new HashSet<String>();
        for (RubyModule p = this; p != null; p = p.getSuperClass()) {
            names.addAll(p.getClassVariableNameList());
        }
        return names;
    }
    
    @JRubyMethod(name = { "const_defined?" }, required = 1, compat = CompatVersion.RUBY1_8)
    public RubyBoolean const_defined_p(final ThreadContext context, final IRubyObject symbol) {
        return context.getRuntime().newBoolean(this.fastIsConstantDefined(this.validateConstant(symbol.asJavaString()).intern()));
    }
    
    @JRubyMethod(name = { "const_defined?" }, required = 1, optional = 1, compat = CompatVersion.RUBY1_9)
    public RubyBoolean const_defined_p19(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject symbol = args[0];
        final boolean inherit = args.length == 1 || (!args[1].isNil() && args[1].isTrue());
        return context.getRuntime().newBoolean(this.fastIsConstantDefined19(this.validateConstant(symbol.asJavaString()).intern(), inherit));
    }
    
    @JRubyMethod(name = { "const_get" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject const_get(final IRubyObject symbol) {
        return this.getConstant(this.validateConstant(symbol.asJavaString()));
    }
    
    @JRubyMethod(name = { "const_get" }, required = 1, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject const_get(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject symbol = args[0];
        final boolean inherit = args.length == 1 || (!args[1].isNil() && args[1].isTrue());
        return this.getConstant(this.validateConstant(symbol.asJavaString()), inherit);
    }
    
    @JRubyMethod(name = { "const_set" }, required = 2)
    public IRubyObject const_set(final IRubyObject symbol, final IRubyObject value) {
        final IRubyObject constant = this.fastSetConstant(this.validateConstant(symbol.asJavaString()).intern(), value);
        if (constant instanceof RubyModule) {
            ((RubyModule)constant).calculateName();
        }
        return constant;
    }
    
    @JRubyMethod(name = { "remove_const" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject remove_const(final ThreadContext context, final IRubyObject rubyName) {
        final String name = this.validateConstant(rubyName.asJavaString());
        final IRubyObject value;
        if ((value = this.deleteConstant(name)) != null) {
            this.invalidateConstantCache();
            if (value != RubyModule.UNDEF) {
                return value;
            }
            context.getRuntime().getLoadService().removeAutoLoadFor(this.getName() + "::" + name);
            return context.getRuntime().getNil();
        }
        else {
            if (this.hasConstantInHierarchy(name)) {
                throw this.cannotRemoveError(name);
            }
            throw context.getRuntime().newNameError("constant " + name + " not defined for " + this.getName(), name);
        }
    }
    
    private boolean hasConstantInHierarchy(final String name) {
        for (RubyModule p = this; p != null; p = p.getSuperClass()) {
            if (p.hasConstant(name)) {
                return true;
            }
        }
        return false;
    }
    
    @JRubyMethod(name = { "const_missing" }, required = 1, frame = true)
    public IRubyObject const_missing(final ThreadContext context, final IRubyObject rubyName, final Block block) {
        final Ruby runtime = context.getRuntime();
        String name;
        if (this != runtime.getObject()) {
            name = this.getName() + "::" + rubyName.asJavaString();
        }
        else {
            name = rubyName.asJavaString();
        }
        throw runtime.newNameError("uninitialized constant " + name, name);
    }
    
    @JRubyMethod(name = { "constants" }, compat = CompatVersion.RUBY1_8)
    public RubyArray constants(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyArray array = runtime.newArray();
        final Collection<String> constantNames = this.constantsCommon(runtime, true, true);
        array.addAll(constantNames);
        return array;
    }
    
    @JRubyMethod(name = { "constants" }, compat = CompatVersion.RUBY1_9)
    public RubyArray constants19(final ThreadContext context) {
        return this.constantsCommon19(context, true, true);
    }
    
    @JRubyMethod(name = { "constants" }, compat = CompatVersion.RUBY1_9)
    public RubyArray constants19(final ThreadContext context, final IRubyObject allConstants) {
        return this.constantsCommon19(context, false, allConstants.isTrue());
    }
    
    public RubyArray constantsCommon19(final ThreadContext context, final boolean replaceModule, final boolean allConstants) {
        final Ruby runtime = context.getRuntime();
        final RubyArray array = runtime.newArray();
        final Collection<String> constantNames = this.constantsCommon(runtime, replaceModule, allConstants);
        for (final String name : constantNames) {
            array.add(runtime.newSymbol(name));
        }
        return array;
    }
    
    public Collection<String> constantsCommon(final Ruby runtime, final boolean replaceModule, final boolean allConstants) {
        final RubyModule objectClass = runtime.getObject();
        Collection<String> constantNames = new HashSet<String>();
        if (allConstants) {
            if ((replaceModule && runtime.getModule() == this) || objectClass == this) {
                constantNames = objectClass.getConstantNames();
            }
            else {
                final Set<String> names = new HashSet<String>();
                for (RubyModule module = this; module != null && module != objectClass; module = module.getSuperClass()) {
                    names.addAll(module.getConstantNames());
                }
                constantNames = names;
            }
        }
        else if ((replaceModule && runtime.getModule() == this) || objectClass == this) {
            constantNames = objectClass.getConstantNames();
        }
        else {
            constantNames = this.getConstantNames();
        }
        return constantNames;
    }
    
    public IRubyObject setClassVar(final String name, final IRubyObject value) {
        RubyModule module = this;
        while (!module.hasClassVariable(name)) {
            if ((module = module.getSuperClass()) == null) {
                return this.storeClassVariable(name, value);
            }
        }
        return module.storeClassVariable(name, value);
    }
    
    public IRubyObject fastSetClassVar(final String internedName, final IRubyObject value) {
        assert internedName == internedName.intern() : internedName + " is not interned";
        RubyModule module = this;
        while (!module.fastHasClassVariable(internedName)) {
            if ((module = module.getSuperClass()) == null) {
                return this.fastStoreClassVariable(internedName, value);
            }
        }
        return module.fastStoreClassVariable(internedName, value);
    }
    
    public IRubyObject getClassVar(final String name) {
        assert IdUtil.isClassVariable(name);
        RubyModule module = this;
        Object value;
        while ((value = module.fetchClassVariable(name)) == null) {
            if ((module = module.getSuperClass()) == null) {
                throw this.getRuntime().newNameError("uninitialized class variable " + name + " in " + this.getName(), name);
            }
        }
        return (IRubyObject)value;
    }
    
    public IRubyObject fastGetClassVar(final String internedName) {
        assert internedName == internedName.intern() : internedName + " is not interned";
        assert IdUtil.isClassVariable(internedName);
        RubyModule module = this;
        IRubyObject value;
        while ((value = module.fetchClassVariable(internedName)) == null) {
            if ((module = module.getSuperClass()) == null) {
                throw this.getRuntime().newNameError("uninitialized class variable " + internedName + " in " + this.getName(), internedName);
            }
        }
        return value;
    }
    
    public boolean isClassVarDefined(final String name) {
        RubyModule module = this;
        while (!module.hasClassVariable(name)) {
            if ((module = module.getSuperClass()) == null) {
                return false;
            }
        }
        return true;
    }
    
    public boolean fastIsClassVarDefined(final String internedName) {
        assert internedName == internedName.intern() : internedName + " is not interned";
        RubyModule module = this;
        while (!module.fastHasClassVariable(internedName)) {
            if ((module = module.getSuperClass()) == null) {
                return false;
            }
        }
        return true;
    }
    
    @Deprecated
    public IRubyObject removeCvar(final IRubyObject name) {
        return this.removeClassVariable(name.asJavaString());
    }
    
    public IRubyObject removeClassVariable(final String name) {
        final String javaName = this.validateClassVariable(name);
        final IRubyObject value;
        if ((value = this.deleteClassVariable(javaName)) != null) {
            return value;
        }
        if (this.fastIsClassVarDefined(javaName)) {
            throw this.cannotRemoveError(javaName);
        }
        throw this.getRuntime().newNameError("class variable " + javaName + " not defined for " + this.getName(), javaName);
    }
    
    public IRubyObject getConstantAtSpecial(final String name) {
        IRubyObject value;
        if (this == this.getRuntime().getObject()) {
            value = this.getConstantNoConstMissing(name);
        }
        else {
            value = this.fetchConstant(name);
        }
        return (value == RubyModule.UNDEF) ? this.resolveUndefConstant(this.getRuntime(), name) : value;
    }
    
    public IRubyObject getConstantAt(final String name) {
        final IRubyObject value = this.fetchConstant(name);
        return (value == RubyModule.UNDEF) ? this.resolveUndefConstant(this.getRuntime(), name) : value;
    }
    
    public IRubyObject fastGetConstantAt(final String internedName) {
        assert internedName == internedName.intern() : internedName + " is not interned";
        final IRubyObject value = this.fastFetchConstant(internedName);
        return (value == RubyModule.UNDEF) ? this.resolveUndefConstant(this.getRuntime(), internedName) : value;
    }
    
    public IRubyObject getConstant(final String name) {
        return this.getConstant(name, true);
    }
    
    public IRubyObject getConstant(final String name, final boolean inherit) {
        return this.fastGetConstant(name.intern(), inherit);
    }
    
    public IRubyObject fastGetConstant(final String internedName) {
        return this.fastGetConstant(internedName, true);
    }
    
    public IRubyObject fastGetConstant(final String internedName, final boolean inherit) {
        final IRubyObject value = this.getConstantNoConstMissing(internedName, inherit);
        final Ruby runtime = this.getRuntime();
        return (value == null) ? this.callMethod(runtime.getCurrentContext(), "const_missing", runtime.fastNewSymbol(internedName)) : value;
    }
    
    public IRubyObject getConstantNoConstMissing(final String name) {
        return this.getConstantNoConstMissing(name, true);
    }
    
    public IRubyObject getConstantNoConstMissing(final String name, final boolean inherit) {
        assert IdUtil.isConstant(name);
        IRubyObject constant = this.iterateConstantNoConstMissing(name, this, inherit);
        if (constant == null && !this.isClass()) {
            constant = this.iterateConstantNoConstMissing(name, this.getRuntime().getObject(), inherit);
        }
        return constant;
    }
    
    private IRubyObject iterateConstantNoConstMissing(final String name, final RubyModule init, final boolean inherit) {
        for (RubyModule p = init; p != null; p = p.getSuperClass()) {
            final IRubyObject value = p.getConstantInner(name);
            if (value != null) {
                return (value == RubyModule.UNDEF) ? null : value;
            }
            if (!inherit) {
                break;
            }
        }
        return null;
    }
    
    protected IRubyObject getConstantInner(final String name) {
        IRubyObject value;
        for (value = this.constantTableFetch(name); value == RubyModule.UNDEF; value = this.constantTableFetch(name)) {
            if (this.resolveUndefConstant(this.getRuntime(), name) == null) {
                return RubyModule.UNDEF;
            }
        }
        return value;
    }
    
    public IRubyObject getConstantFrom(final String name) {
        return this.fastGetConstantFrom(name.intern());
    }
    
    public IRubyObject fastGetConstantFrom(final String internedName) {
        final IRubyObject value = this.fastGetConstantFromNoConstMissing(internedName);
        return (value != null) ? value : this.fastGetConstantFromConstMissing(internedName);
    }
    
    public IRubyObject fastGetConstantFromNoConstMissing(final String internedName) {
        assert internedName == internedName.intern() : internedName + " is not interned";
        assert IdUtil.isConstant(internedName);
        final Ruby runtime = this.getRuntime();
        final RubyClass objectClass = runtime.getObject();
        RubyModule p = this;
        while (p != null) {
            final IRubyObject value;
            if ((value = p.constantTableFastFetch(internedName)) != null) {
                if (value != RubyModule.UNDEF) {
                    if (p == objectClass && this != objectClass) {
                        final String badCName = this.getName() + "::" + internedName;
                        runtime.getWarnings().warn(IRubyWarnings.ID.CONSTANT_BAD_REFERENCE, "toplevel constant " + internedName + " referenced by " + badCName, badCName);
                    }
                    return value;
                }
                if (p.resolveUndefConstant(runtime, internedName) == null) {
                    break;
                }
                continue;
            }
            else {
                p = p.getSuperClass();
            }
        }
        return null;
    }
    
    public IRubyObject fastGetConstantFromConstMissing(final String internedName) {
        assert internedName == internedName.intern() : internedName + " is not interned";
        assert IdUtil.isConstant(internedName);
        return this.callMethod(this.getRuntime().getCurrentContext(), "const_missing", this.getRuntime().fastNewSymbol(internedName));
    }
    
    public IRubyObject resolveUndefConstant(final Ruby runtime, final String name) {
        if (!runtime.is1_9()) {
            this.deleteConstant(name);
        }
        return runtime.getLoadService().autoload(this.getName() + "::" + name);
    }
    
    public IRubyObject setConstantQuiet(final String name, final IRubyObject value) {
        return this.setConstantCommon(name, value, false);
    }
    
    public IRubyObject setConstant(final String name, final IRubyObject value) {
        return this.setConstantCommon(name, value, true);
    }
    
    private IRubyObject setConstantCommon(final String name, final IRubyObject value, final boolean warn) {
        final IRubyObject oldValue = this.fetchConstant(name);
        if (oldValue != null) {
            final Ruby runtime = this.getRuntime();
            if (oldValue == RubyModule.UNDEF) {
                runtime.getLoadService().removeAutoLoadFor(this.getName() + "::" + name);
            }
            else if (warn) {
                runtime.getWarnings().warn(IRubyWarnings.ID.CONSTANT_ALREADY_INITIALIZED, "already initialized constant " + name, name);
            }
        }
        this.storeConstant(name, value);
        this.invalidateConstantCache();
        if (value instanceof RubyModule) {
            final RubyModule module = (RubyModule)value;
            if (module.getBaseName() == null) {
                module.setBaseName(name);
                module.setParent(this);
            }
        }
        return value;
    }
    
    public IRubyObject fastSetConstant(final String internedName, final IRubyObject value) {
        return this.setConstant(internedName, value);
    }
    
    public void defineConstant(final String name, final IRubyObject value) {
        assert value != null;
        if (this == this.getRuntime().getClassClass()) {
            this.getRuntime().secure(4);
        }
        if (!IdUtil.isValidConstantName(name)) {
            throw this.getRuntime().newNameError("bad constant name " + name, name);
        }
        this.setConstant(name, value);
    }
    
    public boolean isConstantDefined(final String name) {
        assert IdUtil.isConstant(name);
        final boolean isObject = this == this.getRuntime().getObject();
        RubyModule module = this;
        Object value;
        while ((value = module.constantTableFetch(name)) == null) {
            if (!isObject || (module = module.getSuperClass()) == null) {
                return false;
            }
        }
        return value != RubyModule.UNDEF || this.getRuntime().getLoadService().autoloadFor(module.getName() + "::" + name) != null;
    }
    
    public boolean fastIsConstantDefined(final String internedName) {
        assert internedName == internedName.intern() : internedName + " is not interned";
        assert IdUtil.isConstant(internedName);
        final boolean isObject = this == this.getRuntime().getObject();
        RubyModule module = this;
        Object value;
        while ((value = module.constantTableFastFetch(internedName)) == null) {
            if (!isObject || (module = module.getSuperClass()) == null) {
                return false;
            }
        }
        return value != RubyModule.UNDEF || this.getRuntime().getLoadService().autoloadFor(module.getName() + "::" + internedName) != null;
    }
    
    public boolean fastIsConstantDefined19(final String internedName) {
        return this.fastIsConstantDefined19(internedName, true);
    }
    
    public boolean fastIsConstantDefined19(final String internedName, final boolean inherit) {
        assert internedName == internedName.intern() : internedName + " is not interned";
        assert IdUtil.isConstant(internedName);
        for (RubyModule module = this; module != null; module = module.getSuperClass()) {
            final Object value;
            if ((value = module.constantTableFastFetch(internedName)) != null) {
                return value != RubyModule.UNDEF || this.getRuntime().getLoadService().autoloadFor(module.getName() + "::" + internedName) != null;
            }
            if (!inherit) {
                break;
            }
        }
        return false;
    }
    
    private RaiseException cannotRemoveError(final String id) {
        return this.getRuntime().newNameError("cannot remove " + id + " for " + this.getName(), id);
    }
    
    public boolean hasInternalModuleVariable(final String name) {
        for (RubyModule module = this; module != null; module = module.getSuperClass()) {
            if (module.hasInternalVariable(name)) {
                return true;
            }
        }
        return false;
    }
    
    public IRubyObject searchInternalModuleVariable(final String name) {
        for (RubyModule module = this; module != null; module = module.getSuperClass()) {
            final IRubyObject value = (IRubyObject)module.getInternalVariable(name);
            if (value != null) {
                return value;
            }
        }
        return null;
    }
    
    public void setInternalModuleVariable(final String name, final IRubyObject value) {
        for (RubyModule module = this; module != null; module = module.getSuperClass()) {
            if (module.hasInternalVariable(name)) {
                module.setInternalVariable(name, value);
                return;
            }
        }
        this.setInternalVariable(name, value);
    }
    
    protected Map<String, IRubyObject> getClassVariables() {
        if (this.classVariables == Collections.EMPTY_MAP) {
            synchronized (this) {
                if (this.classVariables == Collections.EMPTY_MAP) {
                    this.classVariables = new ConcurrentHashMap<String, IRubyObject>(4, 0.75f, 2);
                }
            }
        }
        return this.classVariables;
    }
    
    protected Map<String, IRubyObject> getClassVariablesForRead() {
        return this.classVariables;
    }
    
    public boolean hasClassVariable(final String name) {
        assert IdUtil.isClassVariable(name);
        return this.getClassVariablesForRead().containsKey(name);
    }
    
    public boolean fastHasClassVariable(final String internedName) {
        return this.hasClassVariable(internedName);
    }
    
    public IRubyObject fetchClassVariable(final String name) {
        assert IdUtil.isClassVariable(name);
        return this.getClassVariablesForRead().get(name);
    }
    
    public IRubyObject fastFetchClassVariable(final String internedName) {
        return this.fetchClassVariable(internedName);
    }
    
    public IRubyObject storeClassVariable(final String name, final IRubyObject value) {
        assert IdUtil.isClassVariable(name) && value != null;
        this.ensureClassVariablesSettable();
        this.getClassVariables().put(name, value);
        return value;
    }
    
    public IRubyObject fastStoreClassVariable(final String internedName, final IRubyObject value) {
        return this.storeClassVariable(internedName, value);
    }
    
    public IRubyObject deleteClassVariable(final String name) {
        assert IdUtil.isClassVariable(name);
        this.ensureClassVariablesSettable();
        return this.getClassVariablesForRead().remove(name);
    }
    
    public List<String> getClassVariableNameList() {
        return new ArrayList<String>(this.getClassVariablesForRead().keySet());
    }
    
    protected final String validateClassVariable(final String name) {
        if (IdUtil.isValidClassVariableName(name)) {
            return name;
        }
        throw this.getRuntime().newNameError("`" + name + "' is not allowed as a class variable name", name);
    }
    
    protected final void ensureClassVariablesSettable() {
        final Ruby runtime = this.getRuntime();
        if (!this.isFrozen() && (runtime.getSafeLevel() < 4 || this.isTaint())) {
            return;
        }
        if (runtime.getSafeLevel() >= 4 && !this.isTaint()) {
            throw runtime.newSecurityError("Insecure: can't modify constant");
        }
        if (!this.isFrozen()) {
            return;
        }
        if (this instanceof RubyModule) {
            throw runtime.newFrozenError("class/module ");
        }
        throw runtime.newFrozenError("");
    }
    
    public boolean hasConstant(final String name) {
        assert IdUtil.isConstant(name);
        return this.constantTableContains(name);
    }
    
    public boolean fastHasConstant(final String internedName) {
        assert IdUtil.isConstant(internedName);
        return this.constantTableFastContains(internedName);
    }
    
    public IRubyObject fetchConstant(final String name) {
        assert IdUtil.isConstant(name);
        return this.constantTableFetch(name);
    }
    
    public IRubyObject fastFetchConstant(final String internedName) {
        assert IdUtil.isConstant(internedName);
        return this.constantTableFastFetch(internedName);
    }
    
    public IRubyObject storeConstant(final String name, final IRubyObject value) {
        assert IdUtil.isConstant(name) && value != null;
        this.ensureConstantsSettable();
        return this.constantTableStore(name, value);
    }
    
    public IRubyObject fastStoreConstant(final String internedName, final IRubyObject value) {
        assert IdUtil.isConstant(internedName) && value != null;
        this.ensureConstantsSettable();
        return this.constantTableFastStore(internedName, value);
    }
    
    public IRubyObject deleteConstant(final String name) {
        assert IdUtil.isConstant(name);
        this.ensureConstantsSettable();
        return this.constantTableRemove(name);
    }
    
    @Deprecated
    public List<Variable<IRubyObject>> getStoredConstantList() {
        return null;
    }
    
    @Deprecated
    public List<String> getStoredConstantNameList() {
        return new ArrayList<String>(this.getConstantMap().keySet());
    }
    
    public Collection<String> getConstantNames() {
        return this.getConstantMap().keySet();
    }
    
    protected final String validateConstant(final String name) {
        if (IdUtil.isValidConstantName(name)) {
            return name;
        }
        throw this.getRuntime().newNameError("wrong constant name " + name, name);
    }
    
    protected final void ensureConstantsSettable() {
        final boolean isSecure = this.getRuntime().getSafeLevel() >= 4 && !this.isTaint();
        if (isSecure) {
            throw this.getRuntime().newSecurityError("Insecure: can't modify constant");
        }
        if (this.isFrozen()) {
            throw this.getRuntime().newFrozenError("class/module ");
        }
    }
    
    protected boolean constantTableContains(final String name) {
        return this.getConstantMap().containsKey(name);
    }
    
    protected boolean constantTableFastContains(final String internedName) {
        return this.getConstantMap().containsKey(internedName);
    }
    
    protected IRubyObject constantTableFetch(final String name) {
        return this.getConstantMap().get(name);
    }
    
    protected IRubyObject constantTableFastFetch(final String internedName) {
        return this.getConstantMap().get(internedName);
    }
    
    protected IRubyObject constantTableStore(final String name, final IRubyObject value) {
        this.getConstantMapForWrite().put(name, value);
        return value;
    }
    
    protected IRubyObject constantTableFastStore(final String internedName, final IRubyObject value) {
        this.getConstantMapForWrite().put(internedName, value);
        return value;
    }
    
    protected IRubyObject constantTableRemove(final String name) {
        return this.getConstantMapForWrite().remove(name);
    }
    
    private static void define(final RubyModule module, final JavaMethodDescriptor desc, final DynamicMethod dynamicMethod) {
        final JRubyMethod jrubyMethod = desc.anno;
        if (jrubyMethod.frame()) {
            for (final String name : jrubyMethod.name()) {
                ASTInspector.FRAME_AWARE_METHODS.add(name);
            }
        }
        if (jrubyMethod.compat() == CompatVersion.BOTH || module.getRuntime().getInstanceConfig().getCompatVersion() == jrubyMethod.compat()) {
            if (jrubyMethod.meta()) {
                final RubyModule singletonClass = module.getSingletonClass();
                dynamicMethod.setImplementationClass(singletonClass);
                String baseName;
                if (jrubyMethod.name().length == 0) {
                    baseName = desc.name;
                    singletonClass.addMethod(baseName, dynamicMethod);
                }
                else {
                    baseName = jrubyMethod.name()[0];
                    for (final String name2 : jrubyMethod.name()) {
                        singletonClass.addMethod(name2, dynamicMethod);
                    }
                }
                if (jrubyMethod.alias().length > 0) {
                    for (final String alias : jrubyMethod.alias()) {
                        singletonClass.defineAlias(alias, baseName);
                    }
                }
            }
            else {
                String baseName;
                if (jrubyMethod.name().length == 0) {
                    baseName = desc.name;
                    module.addMethod(baseName, dynamicMethod);
                }
                else {
                    baseName = jrubyMethod.name()[0];
                    for (final String name2 : jrubyMethod.name()) {
                        module.addMethod(name2, dynamicMethod);
                    }
                }
                if (jrubyMethod.alias().length > 0) {
                    for (final String alias : jrubyMethod.alias()) {
                        module.defineAlias(alias, baseName);
                    }
                }
                if (jrubyMethod.module()) {
                    final RubyModule singletonClass = module.getSingletonClass();
                    final DynamicMethod moduleMethod = dynamicMethod.dup();
                    moduleMethod.setVisibility(Visibility.PUBLIC);
                    if (jrubyMethod.name().length == 0) {
                        baseName = desc.name;
                        singletonClass.addMethod(desc.name, moduleMethod);
                    }
                    else {
                        baseName = jrubyMethod.name()[0];
                        for (final String name3 : jrubyMethod.name()) {
                            singletonClass.addMethod(name3, moduleMethod);
                        }
                    }
                    if (jrubyMethod.alias().length > 0) {
                        for (final String alias2 : jrubyMethod.alias()) {
                            singletonClass.defineAlias(alias2, baseName);
                        }
                    }
                }
            }
        }
    }
    
    static {
        SCOPE_CAPTURING_METHODS = new HashSet<String>(Arrays.asList("eval", "module_eval", "class_eval", "instance_eval", "module_exec", "class_exec", "instance_exec", "binding", "local_variables"));
        MODULE_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyModule(runtime, klass);
            }
        };
        NormalCacheEntryFactory = new CacheEntryFactory() {
            public CacheEntry newCacheEntry(final DynamicMethod method, final int token) {
                return new CacheEntry(method, token);
            }
        };
    }
    
    public static class ModuleKernelMethods
    {
        @JRubyMethod
        public static IRubyObject autoload(final IRubyObject recv, final IRubyObject arg0, final IRubyObject arg1) {
            return RubyKernel.autoload(recv, arg0, arg1);
        }
        
        @JRubyMethod(name = { "autoload?" })
        public static IRubyObject autoload_p(final ThreadContext context, final IRubyObject recv, final IRubyObject arg0) {
            return RubyKernel.autoload_p(context, recv, arg0);
        }
    }
    
    public static class KindOf
    {
        public static final KindOf DEFAULT_KIND_OF;
        
        public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
            return obj.getMetaClass().hasModuleInHierarchy(type);
        }
        
        static {
            DEFAULT_KIND_OF = new KindOf();
        }
    }
    
    public static class MethodClumper
    {
        Map<String, List<JavaMethodDescriptor>> annotatedMethods;
        Map<String, List<JavaMethodDescriptor>> staticAnnotatedMethods;
        Map<String, List<JavaMethodDescriptor>> annotatedMethods1_8;
        Map<String, List<JavaMethodDescriptor>> staticAnnotatedMethods1_8;
        Map<String, List<JavaMethodDescriptor>> annotatedMethods1_9;
        Map<String, List<JavaMethodDescriptor>> staticAnnotatedMethods1_9;
        Map<String, List<JavaMethodDescriptor>> allAnnotatedMethods;
        
        public MethodClumper() {
            this.annotatedMethods = new HashMap<String, List<JavaMethodDescriptor>>();
            this.staticAnnotatedMethods = new HashMap<String, List<JavaMethodDescriptor>>();
            this.annotatedMethods1_8 = new HashMap<String, List<JavaMethodDescriptor>>();
            this.staticAnnotatedMethods1_8 = new HashMap<String, List<JavaMethodDescriptor>>();
            this.annotatedMethods1_9 = new HashMap<String, List<JavaMethodDescriptor>>();
            this.staticAnnotatedMethods1_9 = new HashMap<String, List<JavaMethodDescriptor>>();
            this.allAnnotatedMethods = new HashMap<String, List<JavaMethodDescriptor>>();
        }
        
        public void clump(final Class cls) {
            final Method[] arr$;
            final Method[] declaredMethods = arr$ = cls.getDeclaredMethods();
            for (final Method method : arr$) {
                final JRubyMethod anno = method.getAnnotation(JRubyMethod.class);
                if (anno != null) {
                    final JavaMethodDescriptor desc = new JavaMethodDescriptor(method);
                    final String name = (anno.name().length == 0) ? method.getName() : anno.name()[0];
                    Map<String, List<JavaMethodDescriptor>> methodsHash = null;
                    if (desc.isStatic) {
                        if (anno.compat() == CompatVersion.RUBY1_8) {
                            methodsHash = this.staticAnnotatedMethods1_8;
                        }
                        else if (anno.compat() == CompatVersion.RUBY1_9) {
                            methodsHash = this.staticAnnotatedMethods1_9;
                        }
                        else {
                            methodsHash = this.staticAnnotatedMethods;
                        }
                    }
                    else if (anno.compat() == CompatVersion.RUBY1_8) {
                        methodsHash = this.annotatedMethods1_8;
                    }
                    else if (anno.compat() == CompatVersion.RUBY1_9) {
                        methodsHash = this.annotatedMethods1_9;
                    }
                    else {
                        methodsHash = this.annotatedMethods;
                    }
                    List<JavaMethodDescriptor> methodDescs = methodsHash.get(name);
                    if (methodDescs == null) {
                        methodDescs = new ArrayList<JavaMethodDescriptor>();
                        methodsHash.put(name, methodDescs);
                    }
                    methodDescs.add(desc);
                    methodDescs = this.allAnnotatedMethods.get(name);
                    if (methodDescs == null) {
                        methodDescs = new ArrayList<JavaMethodDescriptor>();
                        this.allAnnotatedMethods.put(name, methodDescs);
                    }
                    methodDescs.add(desc);
                }
            }
        }
        
        public Map<String, List<JavaMethodDescriptor>> getAllAnnotatedMethods() {
            return this.allAnnotatedMethods;
        }
        
        public Map<String, List<JavaMethodDescriptor>> getAnnotatedMethods() {
            return this.annotatedMethods;
        }
        
        public Map<String, List<JavaMethodDescriptor>> getAnnotatedMethods1_8() {
            return this.annotatedMethods1_8;
        }
        
        public Map<String, List<JavaMethodDescriptor>> getAnnotatedMethods1_9() {
            return this.annotatedMethods1_9;
        }
        
        public Map<String, List<JavaMethodDescriptor>> getStaticAnnotatedMethods() {
            return this.staticAnnotatedMethods;
        }
        
        public Map<String, List<JavaMethodDescriptor>> getStaticAnnotatedMethods1_8() {
            return this.staticAnnotatedMethods1_8;
        }
        
        public Map<String, List<JavaMethodDescriptor>> getStaticAnnotatedMethods1_9() {
            return this.staticAnnotatedMethods1_9;
        }
    }
    
    protected abstract static class CacheEntryFactory
    {
        public abstract CacheEntry newCacheEntry(final DynamicMethod p0, final int p1);
        
        public boolean hasCacheEntryFactory(final Class cacheEntryFactoryClass) {
            CacheEntryFactory current;
            for (current = this; current instanceof WrapperCacheEntryFactory; current = ((WrapperCacheEntryFactory)current).getPrevious()) {
                if (cacheEntryFactoryClass.isAssignableFrom(current.getClass())) {
                    return true;
                }
            }
            return cacheEntryFactoryClass.isAssignableFrom(current.getClass());
        }
    }
    
    protected abstract static class WrapperCacheEntryFactory extends CacheEntryFactory
    {
        protected final CacheEntryFactory previous;
        
        public WrapperCacheEntryFactory(final CacheEntryFactory previous) {
            this.previous = previous;
        }
        
        public CacheEntryFactory getPrevious() {
            return this.previous;
        }
    }
    
    protected static class SynchronizedCacheEntryFactory extends WrapperCacheEntryFactory
    {
        public SynchronizedCacheEntryFactory(final CacheEntryFactory previous) {
            super(previous);
        }
        
        public CacheEntry newCacheEntry(final DynamicMethod method, final int token) {
            if (method.isUndefined()) {
                return new CacheEntry(method, token);
            }
            final CacheEntry delegated = this.previous.newCacheEntry(method, token);
            return new CacheEntry(new SynchronizedDynamicMethod(delegated.method), delegated.token);
        }
    }
    
    protected static class ProfilingCacheEntryFactory extends WrapperCacheEntryFactory
    {
        public ProfilingCacheEntryFactory(final CacheEntryFactory previous) {
            super(previous);
        }
        
        public CacheEntry newCacheEntry(final DynamicMethod method, final int token) {
            if (method.isUndefined()) {
                return new CacheEntry(method, token);
            }
            final CacheEntry delegated = this.previous.newCacheEntry(method, token);
            return new CacheEntry(new ProfilingDynamicMethod(delegated.method), delegated.token);
        }
    }
    
    public static class RespondToMissingMethod extends JavaMethodNBlock
    {
        final CallSite site;
        
        public RespondToMissingMethod(final RubyModule implClass, final Visibility vis, final String methodName) {
            super(implClass, vis);
            this.site = new FunctionalCachingCallSite(methodName);
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            return this.site.call(context, self, self, args, block);
        }
        
        public boolean equals(final Object other) {
            if (!(other instanceof RespondToMissingMethod)) {
                return false;
            }
            final RespondToMissingMethod rtmm = (RespondToMissingMethod)other;
            return this.site.methodName.equals(rtmm.site.methodName) && this.getImplementationClass() == rtmm.getImplementationClass();
        }
    }
}
