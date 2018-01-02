// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.Hashtable;
import org.jruby.org.objectweb.asm.AnnotationVisitor;
import org.jruby.java.codegen.RealClassGenerator;
import org.jruby.util.JavaNameMangler;
import java.util.HashSet;
import org.jruby.org.objectweb.asm.ClassVisitor;
import org.jruby.org.objectweb.asm.ClassWriter;
import org.jruby.javasupport.Java;
import org.jruby.util.ClassCache;
import org.jruby.java.codegen.Reified;
import org.jruby.runtime.marshal.UnmarshalStream;
import org.jruby.runtime.marshal.MarshalStream;
import org.jruby.util.collections.WeakHashSet;
import java.util.ArrayList;
import java.util.Collection;
import org.jruby.runtime.Visibility;
import java.util.Iterator;
import org.jruby.util.CodegenUtils;
import org.jruby.compiler.impl.SkinnyMethodAdapter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.CallType;
import org.jruby.runtime.ThreadContext;
import java.util.Collections;
import org.jruby.runtime.MethodIndex;
import java.util.HashMap;
import org.jruby.anno.JRubyMethod;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.callsite.CacheEntry;
import java.util.List;
import java.util.Map;
import org.jruby.runtime.CallSite;
import java.util.Set;
import org.jruby.runtime.ObjectMarshal;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Class" }, parent = "Module")
public class RubyClass extends RubyModule
{
    public static final ObjectAllocator CLASS_ALLOCATOR;
    private volatile VariableAccessor objectIdAccessor;
    protected static final ObjectMarshal DEFAULT_OBJECT_MARSHAL;
    private static final boolean DEBUG_REIFY = false;
    protected final Ruby runtime;
    private ObjectAllocator allocator;
    protected ObjectMarshal marshal;
    private Set<RubyClass> subclasses;
    public static final int CS_IDX_INITIALIZE = 0;
    public static final String[] CS_NAMES;
    private final CallSite[] baseCallSites;
    private CallSite[] extraCallSites;
    private Class reifiedClass;
    private static String[] EMPTY_STRING_ARRAY;
    private Map<String, VariableAccessor> variableAccessors;
    private volatile String[] variableNames;
    private volatile boolean hasObjectID;
    private Map<String, List<Map<Class, Map<String, Object>>>> parameterAnnotations;
    private Map<String, Map<Class, Map<String, Object>>> methodAnnotations;
    private Map<String, Class[]> methodSignatures;
    private Map<Class, Map<String, Object>> classAnnotations;
    private MarshalTuple cachedDumpMarshal;
    private CacheEntry cachedLoad;
    
    public static void createClassClass(final Ruby runtime, final RubyClass classClass) {
        classClass.index = 13;
        classClass.setReifiedClass(RubyClass.class);
        classClass.kindOf = new KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyClass;
            }
        };
        classClass.undefineMethod("module_function");
        classClass.undefineMethod("append_features");
        classClass.undefineMethod("extend_object");
        classClass.defineAnnotatedMethods(RubyClass.class);
    }
    
    public ObjectAllocator getAllocator() {
        return this.allocator;
    }
    
    public void setAllocator(final ObjectAllocator allocator) {
        this.allocator = allocator;
    }
    
    public void setClassAllocator(final Class cls) {
        this.allocator = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                try {
                    final RubyBasicObject object = cls.newInstance();
                    object.setMetaClass(klazz);
                    return object;
                }
                catch (InstantiationException ie) {
                    throw runtime.newTypeError("could not allocate " + cls + " with default constructor:\n" + ie);
                }
                catch (IllegalAccessException iae) {
                    throw runtime.newSecurityError("could not allocate " + cls + " due to inaccessible default constructor:\n" + iae);
                }
            }
        };
        this.reifiedClass = cls;
    }
    
    public void setRubyClassAllocator(final Class cls) {
        try {
            final Constructor constructor = cls.getConstructor(Ruby.class, RubyClass.class);
            this.allocator = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                    try {
                        return constructor.newInstance(runtime, klazz);
                    }
                    catch (InvocationTargetException ite) {
                        throw runtime.newTypeError("could not allocate " + cls + " with (Ruby, RubyClass) constructor:\n" + ite);
                    }
                    catch (InstantiationException ie) {
                        throw runtime.newTypeError("could not allocate " + cls + " with (Ruby, RubyClass) constructor:\n" + ie);
                    }
                    catch (IllegalAccessException iae) {
                        throw runtime.newSecurityError("could not allocate " + cls + " due to inaccessible (Ruby, RubyClass) constructor:\n" + iae);
                    }
                }
            };
            this.reifiedClass = cls;
        }
        catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }
    
    public void setRubyStaticAllocator(final Class cls) {
        try {
            final Method method = cls.getDeclaredMethod("__allocate__", Ruby.class, RubyClass.class);
            this.allocator = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                    try {
                        return (IRubyObject)method.invoke(null, runtime, klazz);
                    }
                    catch (InvocationTargetException ite) {
                        throw runtime.newTypeError("could not allocate " + cls + " with (Ruby, RubyClass) constructor:\n" + ite);
                    }
                    catch (IllegalAccessException iae) {
                        throw runtime.newSecurityError("could not allocate " + cls + " due to inaccessible (Ruby, RubyClass) constructor:\n" + iae);
                    }
                }
            };
            this.reifiedClass = cls;
        }
        catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }
    
    @JRubyMethod(name = { "allocate" })
    public IRubyObject allocate() {
        if (this.superClass == null && (!this.runtime.is1_9() || this != this.runtime.getBasicObject())) {
            throw this.runtime.newTypeError("can't instantiate uninitialized class");
        }
        final IRubyObject obj = this.allocator.allocate(this.runtime, this);
        if (obj.getMetaClass().getRealClass() != this.getRealClass()) {
            throw this.runtime.newTypeError("wrong instance allocation");
        }
        return obj;
    }
    
    public CallSite[] getBaseCallSites() {
        return this.baseCallSites;
    }
    
    public CallSite[] getExtraCallSites() {
        return this.extraCallSites;
    }
    
    public Map<String, VariableAccessor> getVariableAccessorsForRead() {
        return this.variableAccessors;
    }
    
    private final synchronized VariableAccessor allocateVariableAccessor(final String name) {
        final String[] myVariableNames = this.variableNames;
        final int newIndex = myVariableNames.length;
        final String[] newVariableNames = new String[newIndex + 1];
        final VariableAccessor newVariableAccessor = new VariableAccessor(name, newIndex, this.id);
        System.arraycopy(myVariableNames, 0, newVariableNames, 0, newIndex);
        newVariableNames[newIndex] = name;
        this.variableNames = newVariableNames;
        return newVariableAccessor;
    }
    
    public VariableAccessor getVariableAccessorForWrite(final String name) {
        VariableAccessor ivarAccessor = this.variableAccessors.get(name);
        if (ivarAccessor == null) {
            synchronized (this) {
                final Map<String, VariableAccessor> myVariableAccessors = this.variableAccessors;
                ivarAccessor = myVariableAccessors.get(name);
                if (ivarAccessor == null) {
                    ivarAccessor = this.allocateVariableAccessor(name);
                    final Map<String, VariableAccessor> newVariableAccessors = new HashMap<String, VariableAccessor>(myVariableAccessors.size() + 1);
                    newVariableAccessors.putAll(myVariableAccessors);
                    newVariableAccessors.put(name, ivarAccessor);
                    this.variableAccessors = newVariableAccessors;
                }
            }
        }
        return ivarAccessor;
    }
    
    public VariableAccessor getVariableAccessorForRead(final String name) {
        VariableAccessor accessor = this.getVariableAccessorsForRead().get(name);
        if (accessor == null) {
            accessor = VariableAccessor.DUMMY_ACCESSOR;
        }
        return accessor;
    }
    
    public synchronized VariableAccessor getObjectIdAccessorForWrite() {
        if (this.objectIdAccessor == VariableAccessor.DUMMY_ACCESSOR) {
            this.objectIdAccessor = this.allocateVariableAccessor("object_id");
        }
        return this.objectIdAccessor;
    }
    
    public VariableAccessor getObjectIdAccessorForRead() {
        return this.objectIdAccessor;
    }
    
    public int getVariableTableSize() {
        return this.variableAccessors.size();
    }
    
    public int getVariableTableSizeWithObjectId() {
        return this.variableAccessors.size() + ((this.objectIdAccessor != VariableAccessor.DUMMY_ACCESSOR) ? 1 : 0);
    }
    
    public Map<String, VariableAccessor> getVariableTableCopy() {
        return new HashMap<String, VariableAccessor>(this.getVariableAccessorsForRead());
    }
    
    public String[] getVariableNames() {
        final String[] original = this.variableNames;
        final String[] copy = new String[original.length];
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
    }
    
    public int getNativeTypeIndex() {
        return 13;
    }
    
    public boolean isModule() {
        return false;
    }
    
    public boolean isClass() {
        return true;
    }
    
    public boolean isSingleton() {
        return false;
    }
    
    public static RubyClass createBootstrapClass(final Ruby runtime, final String name, final RubyClass superClass, final ObjectAllocator allocator) {
        RubyClass obj;
        if (superClass == null) {
            obj = new RubyClass(runtime);
            obj.marshal = RubyClass.DEFAULT_OBJECT_MARSHAL;
        }
        else {
            obj = new RubyClass(runtime, superClass);
        }
        obj.setAllocator(allocator);
        obj.setBaseName(name);
        return obj;
    }
    
    protected RubyClass(final Ruby runtime, final RubyClass superClass, final boolean objectSpace) {
        super(runtime, runtime.getClassClass(), objectSpace);
        this.objectIdAccessor = VariableAccessor.DUMMY_ACCESSOR;
        this.baseCallSites = new CallSite[RubyClass.CS_NAMES.length];
        for (int i = 0; i < RubyClass.CS_NAMES.length; ++i) {
            this.baseCallSites[i] = MethodIndex.getFunctionalCallSite(RubyClass.CS_NAMES[i]);
        }
        this.variableAccessors = (Map<String, VariableAccessor>)Collections.EMPTY_MAP;
        this.variableNames = RubyClass.EMPTY_STRING_ARRAY;
        this.hasObjectID = false;
        this.cachedDumpMarshal = MarshalTuple.NULL_TUPLE;
        this.cachedLoad = CacheEntry.NULL_CACHE;
        this.runtime = runtime;
        this.setSuperClass(superClass);
    }
    
    protected RubyClass(final Ruby runtime) {
        super(runtime, runtime.getClassClass());
        this.objectIdAccessor = VariableAccessor.DUMMY_ACCESSOR;
        this.baseCallSites = new CallSite[RubyClass.CS_NAMES.length];
        for (int i = 0; i < RubyClass.CS_NAMES.length; ++i) {
            this.baseCallSites[i] = MethodIndex.getFunctionalCallSite(RubyClass.CS_NAMES[i]);
        }
        this.variableAccessors = (Map<String, VariableAccessor>)Collections.EMPTY_MAP;
        this.variableNames = RubyClass.EMPTY_STRING_ARRAY;
        this.hasObjectID = false;
        this.cachedDumpMarshal = MarshalTuple.NULL_TUPLE;
        this.cachedLoad = CacheEntry.NULL_CACHE;
        this.runtime = runtime;
        this.index = 13;
    }
    
    protected RubyClass(final Ruby runtime, final RubyClass superClazz) {
        this(runtime);
        this.setSuperClass(superClazz);
        this.marshal = superClazz.marshal;
        superClazz.addSubclass(this);
        this.allocator = superClazz.allocator;
        this.infectBy(this.superClass);
    }
    
    protected RubyClass(final Ruby runtime, final RubyClass superClazz, final CallSite[] extraCallSites) {
        this(runtime);
        this.setSuperClass(superClazz);
        this.marshal = superClazz.marshal;
        superClazz.addSubclass(this);
        this.extraCallSites = extraCallSites;
        this.infectBy(this.superClass);
    }
    
    public static RubyClass newClass(final Ruby runtime, final RubyClass superClass) {
        if (superClass == runtime.getClassClass()) {
            throw runtime.newTypeError("can't make subclass of Class");
        }
        if (superClass.isSingleton()) {
            throw runtime.newTypeError("can't make subclass of virtual class");
        }
        return new RubyClass(runtime, superClass);
    }
    
    public static RubyClass newClass(final Ruby runtime, final RubyClass superClass, final CallSite[] extraCallSites) {
        if (superClass == runtime.getClassClass()) {
            throw runtime.newTypeError("can't make subclass of Class");
        }
        if (superClass.isSingleton()) {
            throw runtime.newTypeError("can't make subclass of virtual class");
        }
        return new RubyClass(runtime, superClass, extraCallSites);
    }
    
    public static RubyClass newClass(final Ruby runtime, final RubyClass superClass, final String name, final ObjectAllocator allocator, final RubyModule parent, final boolean setParent) {
        final RubyClass clazz = newClass(runtime, superClass);
        clazz.setBaseName(name);
        clazz.setAllocator(allocator);
        clazz.makeMetaClass(superClass.getMetaClass());
        if (setParent) {
            clazz.setParent(parent);
        }
        parent.setConstant(name, clazz);
        clazz.inherit(superClass);
        return clazz;
    }
    
    public static RubyClass newClass(final Ruby runtime, final RubyClass superClass, final String name, final ObjectAllocator allocator, final RubyModule parent, final boolean setParent, final CallSite[] extraCallSites) {
        final RubyClass clazz = newClass(runtime, superClass, extraCallSites);
        clazz.setBaseName(name);
        clazz.setAllocator(allocator);
        clazz.makeMetaClass(superClass.getMetaClass());
        if (setParent) {
            clazz.setParent(parent);
        }
        parent.setConstant(name, clazz);
        clazz.inherit(superClass);
        return clazz;
    }
    
    public RubyClass makeMetaClass(final RubyClass superClass) {
        if (this.isSingleton()) {
            final MetaClass klass = new MetaClass(this.runtime, superClass, this);
            this.setMetaClass(klass);
            klass.setMetaClass(klass);
            klass.setSuperClass(this.getSuperClass().getRealClass().getMetaClass());
            return klass;
        }
        return super.makeMetaClass(superClass);
    }
    
    @Deprecated
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final int methodIndex, final String name, final IRubyObject[] args, final CallType callType, final Block block) {
        return this.invoke(context, self, name, args, callType, block);
    }
    
    public boolean notVisibleAndNotMethodMissing(final DynamicMethod method, final String name, final IRubyObject caller, final CallType callType) {
        return !method.isCallableFrom(caller, callType) && !name.equals("method_missing");
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final CallType callType, final Block block) {
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, block);
        }
        return method.call(context, self, this, name, block);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name, final Block block) {
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, block);
        }
        return method.call(context, self, this, name, block);
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject[] args, final CallType callType, final Block block) {
        assert args != null;
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, args, block);
        }
        return method.call(context, self, this, name, args, block);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject[] args, final Block block) {
        assert args != null;
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, args, block);
        }
        return method.call(context, self, this, name, args, block);
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg, final CallType callType, final Block block) {
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, arg, block);
        }
        return method.call(context, self, this, name, arg, block);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg, final Block block) {
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, arg, block);
        }
        return method.call(context, self, this, name, arg, block);
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final CallType callType, final Block block) {
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, arg0, arg1, block);
        }
        return method.call(context, self, this, name, arg0, arg1, block);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, arg0, arg1, block);
        }
        return method.call(context, self, this, name, arg0, arg1, block);
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final CallType callType, final Block block) {
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, arg0, arg1, arg2, block);
        }
        return method.call(context, self, this, name, arg0, arg1, arg2, block);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, arg0, arg1, arg2, block);
        }
        return method.call(context, self, this, name, arg0, arg1, arg2, block);
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final CallType callType) {
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name) {
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name);
    }
    
    public IRubyObject finvokeChecked(final ThreadContext context, final IRubyObject self, final String name) {
        final DynamicMethod method = this.searchMethod(name);
        if (method.isUndefined()) {
            final DynamicMethod methodMissing = this.searchMethod("method_missing");
            if (methodMissing.isUndefined() || methodMissing == context.getRuntime().getDefaultMethodMissing()) {
                return null;
            }
            try {
                return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, Block.NULL_BLOCK);
            }
            catch (RaiseException e) {
                if (!context.getRuntime().getNoMethodError().isInstance(e.getException())) {
                    throw e;
                }
                if (self.respondsTo(name)) {
                    throw e;
                }
                context.setErrorInfo(context.nil);
                return null;
            }
        }
        return method.call(context, self, this, name);
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject[] args, final CallType callType) {
        assert args != null;
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, args, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name, args);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject[] args) {
        assert args != null;
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, args, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name, args);
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg, final CallType callType) {
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, arg, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name, arg);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg) {
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, arg, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name, arg);
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final CallType callType) {
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, arg0, arg1, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name, arg0, arg1);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, arg0, arg1, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name, arg0, arg1);
    }
    
    public IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final CallType callType) {
        final DynamicMethod method = this.searchMethod(name);
        final IRubyObject caller = context.getFrameSelf();
        if (this.shouldCallMethodMissing(method, name, caller, callType)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, callType, arg0, arg1, arg2, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name, arg0, arg1, arg2);
    }
    
    public IRubyObject finvoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        final DynamicMethod method = this.searchMethod(name);
        if (this.shouldCallMethodMissing(method)) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, CallType.FUNCTIONAL, arg0, arg1, arg2, Block.NULL_BLOCK);
        }
        return method.call(context, self, this, name, arg0, arg1, arg2);
    }
    
    private void dumpReifiedClass(String dumpDir, final String javaPath, final byte[] classBytes) {
        if (dumpDir != null) {
            if (dumpDir.equals("")) {
                dumpDir = ".";
            }
            FileOutputStream classStream = null;
            try {
                final File classFile = new File(dumpDir, javaPath + ".class");
                classFile.getParentFile().mkdirs();
                classStream = new FileOutputStream(classFile);
                classStream.write(classBytes);
            }
            catch (IOException io) {
                this.getRuntime().getWarnings().warn("unable to dump class file: " + io.getMessage());
            }
            finally {
                if (classStream != null) {
                    try {
                        classStream.close();
                    }
                    catch (IOException ex) {}
                }
            }
        }
    }
    
    private void generateMethodAnnotations(final Map<Class, Map<String, Object>> methodAnnos, final SkinnyMethodAdapter m, final List<Map<Class, Map<String, Object>>> parameterAnnos) {
        if (methodAnnos != null && methodAnnos.size() != 0) {
            for (final Map.Entry<Class, Map<String, Object>> entry : methodAnnos.entrySet()) {
                m.visitAnnotationWithFields(CodegenUtils.ci(entry.getKey()), true, entry.getValue());
            }
        }
        if (parameterAnnos != null && parameterAnnos.size() != 0) {
            for (int i = 0; i < parameterAnnos.size(); ++i) {
                final Map<Class, Map<String, Object>> annos = parameterAnnos.get(i);
                if (annos != null && annos.size() != 0) {
                    for (final Map.Entry<Class, Map<String, Object>> entry2 : annos.entrySet()) {
                        m.visitParameterAnnotationWithFields(i, CodegenUtils.ci(entry2.getKey()), true, entry2.getValue());
                    }
                }
            }
        }
    }
    
    private boolean shouldCallMethodMissing(final DynamicMethod method) {
        return method.isUndefined();
    }
    
    private boolean shouldCallMethodMissing(final DynamicMethod method, final String name, final IRubyObject caller, final CallType callType) {
        return method.isUndefined() || this.notVisibleAndNotMethodMissing(method, name, caller, callType);
    }
    
    public IRubyObject invokeInherited(final ThreadContext context, final IRubyObject self, final IRubyObject subclass) {
        final DynamicMethod method = this.getMetaClass().searchMethod("inherited");
        if (method.isUndefined()) {
            return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), "inherited", CallType.FUNCTIONAL, Block.NULL_BLOCK);
        }
        return method.call(context, self, this.getMetaClass(), "inherited", subclass, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(name = { "new" }, omit = true)
    public IRubyObject newInstance(final ThreadContext context, final Block block) {
        final IRubyObject obj = this.allocate();
        this.baseCallSites[0].call(context, obj, obj, block);
        return obj;
    }
    
    @JRubyMethod(name = { "new" }, omit = true)
    public IRubyObject newInstance(final ThreadContext context, final IRubyObject arg0, final Block block) {
        final IRubyObject obj = this.allocate();
        this.baseCallSites[0].call(context, obj, obj, arg0, block);
        return obj;
    }
    
    @JRubyMethod(name = { "new" }, omit = true)
    public IRubyObject newInstance(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final IRubyObject obj = this.allocate();
        this.baseCallSites[0].call(context, obj, obj, arg0, arg1, block);
        return obj;
    }
    
    @JRubyMethod(name = { "new" }, omit = true)
    public IRubyObject newInstance(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final IRubyObject obj = this.allocate();
        this.baseCallSites[0].call(context, obj, obj, arg0, arg1, arg2, block);
        return obj;
    }
    
    @JRubyMethod(name = { "new" }, rest = true, omit = true)
    public IRubyObject newInstance(final ThreadContext context, final IRubyObject[] args, final Block block) {
        final IRubyObject obj = this.allocate();
        this.baseCallSites[0].call(context, obj, obj, args, block);
        return obj;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final Block block) {
        this.checkNotInitialized();
        return this.initializeCommon(context, this.runtime.getObject(), block, false);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject superObject, final Block block) {
        this.checkNotInitialized();
        checkInheritable(superObject);
        return this.initializeCommon(context, (RubyClass)superObject, block, false);
    }
    
    @JRubyMethod(name = { "initialize" }, compat = CompatVersion.RUBY1_9, visibility = Visibility.PRIVATE)
    public IRubyObject initialize19(final ThreadContext context, final Block block) {
        this.checkNotInitialized();
        return this.initializeCommon(context, this.runtime.getObject(), block, true);
    }
    
    @JRubyMethod(name = { "initialize" }, compat = CompatVersion.RUBY1_9, visibility = Visibility.PRIVATE)
    public IRubyObject initialize19(final ThreadContext context, final IRubyObject superObject, final Block block) {
        this.checkNotInitialized();
        checkInheritable(superObject);
        return this.initializeCommon(context, (RubyClass)superObject, block, true);
    }
    
    private IRubyObject initializeCommon(final ThreadContext context, final RubyClass superClazz, final Block block, final boolean ruby1_9) {
        this.setSuperClass(superClazz);
        this.allocator = superClazz.allocator;
        this.makeMetaClass(superClazz.getMetaClass());
        this.marshal = superClazz.marshal;
        superClazz.addSubclass(this);
        if (ruby1_9) {
            this.inherit(superClazz);
            super.initialize19(context, block);
        }
        else {
            super.initialize(block);
            this.inherit(superClazz);
        }
        return this;
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize_copy(final IRubyObject original) {
        this.checkNotInitialized();
        if (original instanceof MetaClass) {
            throw this.runtime.newTypeError("can't copy singleton class");
        }
        super.initialize_copy(original);
        this.allocator = ((RubyClass)original).allocator;
        return this;
    }
    
    protected void setModuleSuperClass(final RubyClass superClass) {
        if (this.superClass != null) {
            this.superClass.removeSubclass(this);
        }
        superClass.addSubclass(this);
        this.setSuperClass(superClass);
    }
    
    public Collection<RubyClass> subclasses(final boolean includeDescendants) {
        final Set<RubyClass> mySubclasses = this.subclasses;
        if (mySubclasses != null) {
            final Collection<RubyClass> mine = new ArrayList<RubyClass>(mySubclasses);
            if (includeDescendants) {
                for (final RubyClass i : mySubclasses) {
                    mine.addAll(i.subclasses(includeDescendants));
                }
            }
            return mine;
        }
        return (Collection<RubyClass>)Collections.EMPTY_LIST;
    }
    
    public synchronized void addSubclass(final RubyClass subclass) {
        synchronized (this.runtime.getHierarchyLock()) {
            Set<RubyClass> oldSubclasses = this.subclasses;
            if (oldSubclasses == null) {
                oldSubclasses = (this.subclasses = new WeakHashSet<RubyClass>(4));
            }
            oldSubclasses.add(subclass);
        }
    }
    
    public synchronized void removeSubclass(final RubyClass subclass) {
        synchronized (this.runtime.getHierarchyLock()) {
            final Set<RubyClass> oldSubclasses = this.subclasses;
            if (oldSubclasses == null) {
                return;
            }
            oldSubclasses.remove(subclass);
        }
    }
    
    public synchronized void replaceSubclass(final RubyClass subclass, final RubyClass newSubclass) {
        synchronized (this.runtime.getHierarchyLock()) {
            final Set<RubyClass> oldSubclasses = this.subclasses;
            if (oldSubclasses == null) {
                return;
            }
            oldSubclasses.remove(subclass);
            oldSubclasses.add(newSubclass);
        }
    }
    
    public void becomeSynchronized() {
        synchronized (this.getRuntime().getHierarchyLock()) {
            super.becomeSynchronized();
            final Set<RubyClass> mySubclasses = this.subclasses;
            if (mySubclasses != null) {
                for (final RubyClass subclass : mySubclasses) {
                    subclass.becomeSynchronized();
                }
            }
        }
    }
    
    public void invalidateCacheDescendants() {
        super.invalidateCacheDescendants();
        synchronized (this.runtime.getHierarchyLock()) {
            final Set<RubyClass> mySubclasses = this.subclasses;
            if (mySubclasses != null) {
                for (final RubyClass subclass : mySubclasses) {
                    subclass.invalidateCacheDescendants();
                }
            }
        }
    }
    
    public Ruby getClassRuntime() {
        return this.runtime;
    }
    
    public RubyClass getRealClass() {
        return this;
    }
    
    @JRubyMethod(name = { "inherited" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject inherited(final ThreadContext context, final IRubyObject arg) {
        return this.runtime.getNil();
    }
    
    public void inherit(RubyClass superClazz) {
        if (superClazz == null) {
            superClazz = this.runtime.getObject();
        }
        if (this.getRuntime().getNil() != null) {
            superClazz.invokeInherited(this.runtime.getCurrentContext(), superClazz, this);
        }
    }
    
    @JRubyMethod(name = { "superclass" })
    public IRubyObject superclass(final ThreadContext context) {
        RubyClass superClazz = this.superClass;
        if (superClazz != null) {
            while (superClazz != null && superClazz.isIncluded()) {
                superClazz = superClazz.superClass;
            }
            return (superClazz != null) ? superClazz : this.runtime.getNil();
        }
        if (this.metaClass == this.runtime.getBasicObject().getMetaClass()) {
            return this.runtime.getNil();
        }
        throw this.runtime.newTypeError("uninitialized class");
    }
    
    private void checkNotInitialized() {
        if (this.superClass != null || (this.runtime.is1_9() && this == this.runtime.getBasicObject())) {
            throw this.runtime.newTypeError("already initialized class");
        }
    }
    
    public static void checkInheritable(final IRubyObject superClass) {
        if (!(superClass instanceof RubyClass)) {
            throw superClass.getRuntime().newTypeError("superclass must be a Class (" + superClass.getMetaClass() + " given)");
        }
        if (((RubyClass)superClass).isSingleton()) {
            throw superClass.getRuntime().newTypeError("can't make subclass of virtual class");
        }
    }
    
    public final ObjectMarshal getMarshal() {
        return this.marshal;
    }
    
    public final void setMarshal(final ObjectMarshal marshal) {
        this.marshal = marshal;
    }
    
    public final void marshal(final Object obj, final MarshalStream marshalStream) throws IOException {
        this.getMarshal().marshalTo(this.runtime, obj, this, marshalStream);
    }
    
    public final Object unmarshal(final UnmarshalStream unmarshalStream) throws IOException {
        return this.getMarshal().unmarshalFrom(this.runtime, this, unmarshalStream);
    }
    
    public static void marshalTo(final RubyClass clazz, final MarshalStream output) throws IOException {
        output.registerLinkTarget(clazz);
        output.writeString(MarshalStream.getPathFromClass(clazz));
    }
    
    public static RubyClass unmarshalFrom(final UnmarshalStream input) throws IOException {
        final String name = RubyString.byteListToString(input.unmarshalString());
        final RubyClass result = UnmarshalStream.getClassFromPath(input.getRuntime(), name);
        input.registerLinkTarget(result);
        return result;
    }
    
    public boolean isReifiable() {
        RubyClass realSuper = null;
        if (this.reifiedClass != null) {
            return false;
        }
        if (this.superClass == null || (realSuper = this.superClass.getRealClass()) == null) {
            return false;
        }
        final Class reifiedSuper = realSuper.reifiedClass;
        if (reifiedSuper != null) {
            return reifiedSuper == RubyObject.class || reifiedSuper == RubyBasicObject.class || Reified.class.isAssignableFrom(reifiedSuper);
        }
        return realSuper.isReifiable();
    }
    
    public void reifyWithAncestors() {
        if (this.isReifiable()) {
            final RubyClass realSuper = this.getSuperClass().getRealClass();
            if (realSuper.reifiedClass == null) {
                realSuper.reifyWithAncestors();
            }
            this.reify();
        }
    }
    
    public void reifyWithAncestors(final String classDumpDir) {
        if (this.isReifiable()) {
            final RubyClass realSuper = this.getSuperClass().getRealClass();
            if (realSuper.reifiedClass == null) {
                realSuper.reifyWithAncestors(classDumpDir);
            }
            this.reify(classDumpDir);
        }
    }
    
    public synchronized void reify() {
        this.reify(null);
    }
    
    public synchronized void reify(final String classDumpDir) {
        Class reifiedParent = RubyObject.class;
        String name;
        if (this.getBaseName() == null) {
            name = "AnonymousRubyClass#" + this.id;
        }
        else {
            name = this.getName();
        }
        final String javaName = "rubyobj." + name.replaceAll("::", ".");
        final String javaPath = "rubyobj/" + name.replaceAll("::", "/");
        final Class parentReified = this.superClass.getRealClass().getReifiedClass();
        if (parentReified == null) {
            throw this.getClassRuntime().newTypeError("class " + this.getName() + " parent class is not yet reified");
        }
        ClassCache.OneShotClassLoader parentCL;
        if (parentReified.getClassLoader() instanceof ClassCache.OneShotClassLoader) {
            parentCL = (ClassCache.OneShotClassLoader)this.superClass.getRealClass().getReifiedClass().getClassLoader();
        }
        else {
            parentCL = new ClassCache.OneShotClassLoader(this.runtime.getJRubyClassLoader());
        }
        if (this.superClass.reifiedClass != null) {
            reifiedParent = this.superClass.reifiedClass;
        }
        final Class[] interfaces = Java.getInterfacesFromRubyClass(this);
        final String[] interfaceNames = new String[interfaces.length + 1];
        interfaceNames[0] = CodegenUtils.p(Reified.class);
        for (int i = 0; i < interfaces.length; ++i) {
            interfaceNames[i + 1] = CodegenUtils.p(interfaces[i]);
        }
        final ClassWriter cw = new ClassWriter(3);
        cw.visit(RubyInstanceConfig.JAVA_VERSION, 33, javaPath, null, CodegenUtils.p(reifiedParent), interfaceNames);
        if (this.classAnnotations != null && this.classAnnotations.size() != 0) {
            for (final Map.Entry<Class, Map<String, Object>> entry : this.classAnnotations.entrySet()) {
                final Class annoType = entry.getKey();
                final Map<String, Object> fields = entry.getValue();
                final AnnotationVisitor av = cw.visitAnnotation(CodegenUtils.ci(annoType), true);
                CodegenUtils.visitAnnotationFields(av, fields);
                av.visitEnd();
            }
        }
        cw.visitField(10, "ruby", CodegenUtils.ci(Ruby.class), null, null);
        cw.visitField(10, "rubyClass", CodegenUtils.ci(RubyClass.class), null, null);
        SkinnyMethodAdapter m = new SkinnyMethodAdapter(cw, 9, "clinit", CodegenUtils.sig(Void.TYPE, Ruby.class, RubyClass.class), null, null);
        m.start();
        m.aload(0);
        m.putstatic(javaPath, "ruby", CodegenUtils.ci(Ruby.class));
        m.aload(1);
        m.putstatic(javaPath, "rubyClass", CodegenUtils.ci(RubyClass.class));
        m.voidreturn();
        m.end();
        m = new SkinnyMethodAdapter(cw, 1, "<init>", CodegenUtils.sig(Void.TYPE, Ruby.class, RubyClass.class), null, null);
        m.aload(0);
        m.aload(1);
        m.aload(2);
        m.invokespecial(CodegenUtils.p(reifiedParent), "<init>", CodegenUtils.sig(Void.TYPE, Ruby.class, RubyClass.class));
        m.voidreturn();
        m.end();
        m = new SkinnyMethodAdapter(cw, 1, "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]), null, null);
        m.aload(0);
        m.getstatic(javaPath, "ruby", CodegenUtils.ci(Ruby.class));
        m.getstatic(javaPath, "rubyClass", CodegenUtils.ci(RubyClass.class));
        m.invokespecial(CodegenUtils.p(reifiedParent), "<init>", CodegenUtils.sig(Void.TYPE, Ruby.class, RubyClass.class));
        m.voidreturn();
        m.end();
        final Set<String> instanceMethods = new HashSet<String>();
        for (final Map.Entry<String, DynamicMethod> methodEntry : this.getMethods().entrySet()) {
            final String methodName = methodEntry.getKey();
            final String javaMethodName = JavaNameMangler.mangleStringForCleanJavaIdentifier(methodName);
            final Map<Class, Map<String, Object>> methodAnnos = this.getMethodAnnotations().get(methodName);
            final List<Map<Class, Map<String, Object>>> parameterAnnos = this.getParameterAnnotations().get(methodName);
            final Class[] methodSignature = this.getMethodSignatures().get(methodName);
            String signature = null;
            if (methodSignature == null) {
                switch (methodEntry.getValue().getArity().getValue()) {
                    case 0: {
                        signature = CodegenUtils.sig(IRubyObject.class, new Class[0]);
                        m = new SkinnyMethodAdapter(cw, 129, javaMethodName, signature, null, null);
                        this.generateMethodAnnotations(methodAnnos, m, parameterAnnos);
                        m.aload(0);
                        m.ldc(methodName);
                        m.invokevirtual(javaPath, "callMethod", CodegenUtils.sig(IRubyObject.class, String.class));
                        break;
                    }
                    default: {
                        signature = CodegenUtils.sig(IRubyObject.class, IRubyObject[].class);
                        m = new SkinnyMethodAdapter(cw, 129, javaMethodName, signature, null, null);
                        this.generateMethodAnnotations(methodAnnos, m, parameterAnnos);
                        m.aload(0);
                        m.ldc(methodName);
                        m.aload(1);
                        m.invokevirtual(javaPath, "callMethod", CodegenUtils.sig(IRubyObject.class, String.class, IRubyObject[].class));
                        break;
                    }
                }
                m.areturn();
            }
            else {
                final Class[] params = new Class[methodSignature.length - 1];
                System.arraycopy(methodSignature, 1, params, 0, params.length);
                int baseIndex = 1;
                for (final Class paramType : params) {
                    if (paramType == Double.TYPE || paramType == Long.TYPE) {
                        baseIndex += 2;
                    }
                    else {
                        ++baseIndex;
                    }
                }
                final int rubyIndex = baseIndex;
                signature = CodegenUtils.sig(methodSignature[0], params);
                m = new SkinnyMethodAdapter(cw, 129, javaMethodName, signature, null, null);
                this.generateMethodAnnotations(methodAnnos, m, parameterAnnos);
                m.getstatic(javaPath, "ruby", CodegenUtils.ci(Ruby.class));
                m.astore(rubyIndex);
                m.aload(0);
                m.ldc(methodName);
                RealClassGenerator.coerceArgumentsToRuby(m, params, rubyIndex);
                m.invokevirtual(javaPath, "callMethod", CodegenUtils.sig(IRubyObject.class, String.class, IRubyObject[].class));
                RealClassGenerator.coerceResultAndReturn(m, methodSignature[0]);
            }
            instanceMethods.add(javaMethodName + signature);
            m.end();
        }
        for (final Map.Entry<String, DynamicMethod> methodEntry : this.getMetaClass().getMethods().entrySet()) {
            final String methodName = methodEntry.getKey();
            final String javaMethodName = JavaNameMangler.mangleStringForCleanJavaIdentifier(methodName);
            final Map<Class, Map<String, Object>> methodAnnos = this.getMetaClass().getMethodAnnotations().get(methodName);
            final List<Map<Class, Map<String, Object>>> parameterAnnos = this.getMetaClass().getParameterAnnotations().get(methodName);
            final Class[] methodSignature = this.getMetaClass().getMethodSignatures().get(methodName);
            if (methodSignature == null) {
                switch (methodEntry.getValue().getArity().getValue()) {
                    case 0: {
                        final String signature = CodegenUtils.sig(IRubyObject.class, new Class[0]);
                        if (instanceMethods.contains(javaMethodName + signature)) {
                            continue;
                        }
                        m = new SkinnyMethodAdapter(cw, 137, javaMethodName, signature, null, null);
                        this.generateMethodAnnotations(methodAnnos, m, parameterAnnos);
                        m.getstatic(javaPath, "rubyClass", CodegenUtils.ci(RubyClass.class));
                        m.ldc(methodName);
                        m.invokevirtual("org/jruby/RubyClass", "callMethod", CodegenUtils.sig(IRubyObject.class, String.class));
                        break;
                    }
                    default: {
                        final String signature = CodegenUtils.sig(IRubyObject.class, IRubyObject[].class);
                        if (instanceMethods.contains(javaMethodName + signature)) {
                            continue;
                        }
                        m = new SkinnyMethodAdapter(cw, 137, javaMethodName, signature, null, null);
                        this.generateMethodAnnotations(methodAnnos, m, parameterAnnos);
                        m.getstatic(javaPath, "rubyClass", CodegenUtils.ci(RubyClass.class));
                        m.ldc(methodName);
                        m.aload(0);
                        m.invokevirtual("org/jruby/RubyClass", "callMethod", CodegenUtils.sig(IRubyObject.class, String.class, IRubyObject[].class));
                        break;
                    }
                }
                m.areturn();
            }
            else {
                final Class[] params = new Class[methodSignature.length - 1];
                System.arraycopy(methodSignature, 1, params, 0, params.length);
                int baseIndex = 0;
                for (final Class paramType : params) {
                    if (paramType == Double.TYPE || paramType == Long.TYPE) {
                        baseIndex += 2;
                    }
                    else {
                        ++baseIndex;
                    }
                }
                final int rubyIndex = baseIndex;
                final String signature = CodegenUtils.sig(methodSignature[0], params);
                if (instanceMethods.contains(javaMethodName + signature)) {
                    continue;
                }
                m = new SkinnyMethodAdapter(cw, 137, javaMethodName, signature, null, null);
                this.generateMethodAnnotations(methodAnnos, m, parameterAnnos);
                m.getstatic(javaPath, "ruby", CodegenUtils.ci(Ruby.class));
                m.astore(rubyIndex);
                m.getstatic(javaPath, "rubyClass", CodegenUtils.ci(RubyClass.class));
                m.ldc(methodName);
                RealClassGenerator.coerceArgumentsToRuby(m, params, rubyIndex);
                m.invokevirtual("org/jruby/RubyClass", "callMethod", CodegenUtils.sig(IRubyObject.class, String.class, IRubyObject[].class));
                RealClassGenerator.coerceResultAndReturn(m, methodSignature[0]);
            }
            m.end();
        }
        cw.visitEnd();
        final byte[] classBytes = cw.toByteArray();
        this.dumpReifiedClass(classDumpDir, javaPath, classBytes);
        final Class result = parentCL.defineClass(javaName, classBytes);
        try {
            final Method clinit = result.getDeclaredMethod("clinit", Ruby.class, RubyClass.class);
            clinit.invoke(null, this.runtime, this);
        }
        catch (Exception e) {
            if (RubyInstanceConfig.REIFY_LOG_ERRORS) {
                System.err.println("failed to reify class " + this.getName() + " due to:\n");
                e.printStackTrace(System.err);
            }
        }
        this.setClassAllocator(result);
        this.reifiedClass = result;
    }
    
    public void setReifiedClass(final Class newReifiedClass) {
        this.reifiedClass = newReifiedClass;
    }
    
    public Class getReifiedClass() {
        return this.reifiedClass;
    }
    
    public Map<String, List<Map<Class, Map<String, Object>>>> getParameterAnnotations() {
        if (this.parameterAnnotations == null) {
            return (Map<String, List<Map<Class, Map<String, Object>>>>)Collections.EMPTY_MAP;
        }
        return this.parameterAnnotations;
    }
    
    public synchronized void addParameterAnnotation(final String method, final int i, final Class annoClass, final Map<String, Object> value) {
        if (this.parameterAnnotations == null) {
            this.parameterAnnotations = new Hashtable<String, List<Map<Class, Map<String, Object>>>>();
        }
        List<Map<Class, Map<String, Object>>> paramList = this.parameterAnnotations.get(method);
        if (paramList == null) {
            paramList = new ArrayList<Map<Class, Map<String, Object>>>(i + 1);
            this.parameterAnnotations.put(method, paramList);
        }
        if (paramList.size() < i + 1) {
            for (int j = paramList.size(); j < i + 1; ++j) {
                paramList.add(null);
            }
        }
        if (annoClass != null && value != null) {
            Map<Class, Map<String, Object>> annos = paramList.get(i);
            if (annos == null) {
                annos = new HashMap<Class, Map<String, Object>>();
                paramList.set(i, annos);
            }
            annos.put(annoClass, value);
        }
        else {
            paramList.set(i, null);
        }
    }
    
    public Map<String, Map<Class, Map<String, Object>>> getMethodAnnotations() {
        if (this.methodAnnotations == null) {
            return (Map<String, Map<Class, Map<String, Object>>>)Collections.EMPTY_MAP;
        }
        return this.methodAnnotations;
    }
    
    public synchronized void addMethodAnnotation(final String methodName, final Class annotation, final Map fields) {
        if (this.methodAnnotations == null) {
            this.methodAnnotations = new Hashtable<String, Map<Class, Map<String, Object>>>();
        }
        Map<Class, Map<String, Object>> annos = this.methodAnnotations.get(methodName);
        if (annos == null) {
            annos = new Hashtable<Class, Map<String, Object>>();
            this.methodAnnotations.put(methodName, annos);
        }
        annos.put(annotation, fields);
    }
    
    public Map<String, Class[]> getMethodSignatures() {
        if (this.methodSignatures == null) {
            return (Map<String, Class[]>)Collections.EMPTY_MAP;
        }
        return this.methodSignatures;
    }
    
    public synchronized void addMethodSignature(final String methodName, final Class[] types) {
        if (this.methodSignatures == null) {
            this.methodSignatures = new Hashtable<String, Class[]>();
        }
        this.methodSignatures.put(methodName, types);
    }
    
    public Map<Class, Map<String, Object>> getClassAnnotations() {
        if (this.classAnnotations == null) {
            return (Map<Class, Map<String, Object>>)Collections.EMPTY_MAP;
        }
        return this.classAnnotations;
    }
    
    public synchronized void addClassAnnotation(final Class annotation, final Map fields) {
        if (this.classAnnotations == null) {
            this.classAnnotations = new Hashtable<Class, Map<String, Object>>();
        }
        this.classAnnotations.put(annotation, fields);
    }
    
    public Object toJava(final Class klass) {
        Class returnClass = null;
        if (klass == Class.class) {
            if (this.respondsTo("java_class")) {
                return this.callMethod("java_class").toJava(klass);
            }
            for (RubyClass current = this; current != null; current = current.getSuperClass()) {
                returnClass = current.getReifiedClass();
                if (returnClass != null) {
                    return returnClass;
                }
            }
        }
        if (klass.isAssignableFrom(RubyClass.class)) {
            return this;
        }
        return super.toJava(klass);
    }
    
    public void smartDump(final MarshalStream stream, final IRubyObject target) throws IOException {
        MarshalTuple tuple;
        if ((tuple = this.cachedDumpMarshal).generation != this.generation) {
            DynamicMethod method = this.searchMethod("respond_to?");
            if (method != this.runtime.getRespondToMethod() && !method.isUndefined()) {
                final MarshalTuple cachedDumpMarshal = new MarshalTuple(null, MarshalType.DEFAULT_SLOW, this.generation);
                this.cachedDumpMarshal = cachedDumpMarshal;
                tuple = cachedDumpMarshal;
            }
            else if (!(method = this.searchMethod("marshal_dump")).isUndefined()) {
                final MarshalTuple cachedDumpMarshal2 = new MarshalTuple(method, MarshalType.NEW_USER, this.generation);
                this.cachedDumpMarshal = cachedDumpMarshal2;
                tuple = cachedDumpMarshal2;
            }
            else if (!(method = this.searchMethod("_dump")).isUndefined()) {
                final MarshalTuple cachedDumpMarshal3 = new MarshalTuple(method, MarshalType.OLD_USER, this.generation);
                this.cachedDumpMarshal = cachedDumpMarshal3;
                tuple = cachedDumpMarshal3;
            }
            else {
                final MarshalTuple cachedDumpMarshal4 = new MarshalTuple(null, MarshalType.DEFAULT, this.generation);
                this.cachedDumpMarshal = cachedDumpMarshal4;
                tuple = cachedDumpMarshal4;
            }
        }
        tuple.dump(stream, target);
    }
    
    public IRubyObject smartLoadNewUser(final IRubyObject target, final IRubyObject data) {
        final ThreadContext context = this.runtime.getCurrentContext();
        CacheEntry cache;
        if ((cache = this.cachedLoad).token == this.generation) {
            cache.method.call(context, target, this, "marshal_load", data);
            return target;
        }
        final DynamicMethod method = this.searchMethod("respond_to?");
        if (method != this.runtime.getRespondToMethod() && !method.isUndefined()) {
            if (method.call(context, target, this, "respond_to?", this.runtime.newSymbol("marshal_load")).isTrue()) {
                target.callMethod(context, "marshal_load", data);
                return target;
            }
            throw this.runtime.newTypeError("class " + this.getName() + " needs to have method `marshal_load'");
        }
        else {
            if (!(cache = this.searchWithCache("marshal_load")).method.isUndefined()) {
                this.cachedLoad = cache;
                cache.method.call(context, target, this, "marshal_load", data);
                return target;
            }
            target.callMethod(context, "marshal_load", data);
            return target;
        }
    }
    
    public IRubyObject smartLoadOldUser(final IRubyObject data) {
        final ThreadContext context = this.runtime.getCurrentContext();
        CacheEntry cache;
        if ((cache = this.getSingletonClass().cachedLoad).token == this.getSingletonClass().generation) {
            return cache.method.call(context, this, this.getSingletonClass(), "_load", data);
        }
        final DynamicMethod method = this.getSingletonClass().searchMethod("respond_to?");
        if (method != this.runtime.getRespondToMethod() && !method.isUndefined()) {
            if (method.call(context, this, this.getSingletonClass(), "respond_to?", this.runtime.newSymbol("_load")).isTrue()) {
                return this.callMethod(context, "_load", data);
            }
            throw this.runtime.newTypeError("class " + this.getName() + " needs to have method `_load'");
        }
        else {
            if (!(cache = this.getSingletonClass().searchWithCache("_load")).method.isUndefined()) {
                this.getSingletonClass().cachedLoad = cache;
                return cache.method.call(context, this, this.getSingletonClass(), "_load", data);
            }
            throw this.runtime.newTypeError("class " + this.getName() + " needs to have method `_load'");
        }
    }
    
    public boolean hasObjectID() {
        return this.hasObjectID;
    }
    
    static {
        CLASS_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyClass clazz = new RubyClass(runtime);
                clazz.allocator = ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR;
                return clazz;
            }
        };
        DEFAULT_OBJECT_MARSHAL = new ObjectMarshal() {
            public void marshalTo(final Ruby runtime, final Object obj, final RubyClass type, final MarshalStream marshalStream) throws IOException {
                final IRubyObject object = (IRubyObject)obj;
                marshalStream.registerLinkTarget(object);
                marshalStream.dumpVariables(object.getVariableList());
            }
            
            public Object unmarshalFrom(final Ruby runtime, final RubyClass type, final UnmarshalStream unmarshalStream) throws IOException {
                final IRubyObject result = type.allocate();
                unmarshalStream.registerLinkTarget(result);
                unmarshalStream.defaultVariablesUnmarshal(result);
                return result;
            }
        };
        CS_NAMES = new String[] { "initialize" };
        RubyClass.EMPTY_STRING_ARRAY = new String[0];
    }
    
    public static class VariableAccessor
    {
        private String name;
        private int index;
        private final int classId;
        public static final VariableAccessor DUMMY_ACCESSOR;
        
        public VariableAccessor(final String name, final int index, final int classId) {
            this.index = index;
            this.classId = classId;
            this.name = name;
        }
        
        public int getClassId() {
            return this.classId;
        }
        
        public int getIndex() {
            return this.index;
        }
        
        public String getName() {
            return this.name;
        }
        
        public Object get(final Object object) {
            return ((IRubyObject)object).getVariable(this.index);
        }
        
        public void set(final Object object, final Object value) {
            ((IRubyObject)object).setVariable(this.index, value);
        }
        
        static {
            DUMMY_ACCESSOR = new VariableAccessor(null, -1, -1);
        }
    }
    
    private enum MarshalType
    {
        DEFAULT, 
        NEW_USER, 
        OLD_USER, 
        DEFAULT_SLOW, 
        NEW_USER_SLOW, 
        USER_SLOW;
    }
    
    private static class MarshalTuple
    {
        public static final MarshalTuple NULL_TUPLE;
        public final DynamicMethod method;
        public final MarshalType type;
        public final int generation;
        
        public MarshalTuple(final DynamicMethod method, final MarshalType type, final int generation) {
            this.method = method;
            this.type = type;
            this.generation = generation;
        }
        
        public void dump(final MarshalStream stream, final IRubyObject object) throws IOException {
            switch (this.type) {
                case DEFAULT: {
                    stream.writeDirectly(object);
                }
                case NEW_USER: {
                    stream.userNewMarshal(object, this.method);
                }
                case OLD_USER: {
                    stream.userMarshal(object, this.method);
                }
                case DEFAULT_SLOW: {
                    if (object.respondsTo("marshal_dump")) {
                        stream.userNewMarshal(object);
                    }
                    else if (object.respondsTo("_dump")) {
                        stream.userMarshal(object);
                    }
                    else {
                        stream.writeDirectly(object);
                    }
                }
                default: {}
            }
        }
        
        static {
            NULL_TUPLE = new MarshalTuple(null, null, 0);
        }
    }
}
