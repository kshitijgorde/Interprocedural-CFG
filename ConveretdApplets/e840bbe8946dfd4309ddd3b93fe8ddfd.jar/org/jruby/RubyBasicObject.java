// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Set;
import java.util.HashSet;
import org.jruby.common.IRubyWarnings;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.CallType;
import org.jruby.util.IdUtil;
import org.jruby.runtime.component.VariableEntry;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.jruby.runtime.builtin.Variable;
import org.jruby.runtime.ObjectSpace;
import org.jruby.javasupport.JavaUtil;
import org.jruby.util.TypeConverter;
import org.jruby.javasupport.JavaObject;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.InternalVariables;
import org.jruby.runtime.builtin.InstanceVariables;
import org.jruby.runtime.marshal.CoreObjectType;
import java.io.Serializable;
import org.jruby.runtime.builtin.IRubyObject;

public class RubyBasicObject implements Cloneable, IRubyObject, Serializable, Comparable<IRubyObject>, CoreObjectType, InstanceVariables, InternalVariables
{
    private static final boolean DEBUG = false;
    private static final Object[] NULL_OBJECT_ARRAY;
    protected transient RubyClass metaClass;
    protected int flags;
    private volatile Object[] varTable;
    protected static final String ERR_INSECURE_SET_INST_VAR = "Insecure: can't modify instance variable";
    public static final int ALL_F = -1;
    public static final int FALSE_F = 1;
    public static final int NIL_F = 2;
    public static final int FROZEN_F = 4;
    public static final int TAINTED_F = 8;
    public static final int UNTRUSTED_F = 16;
    public static final int FL_USHIFT = 5;
    public static final int USER0_F = 32;
    public static final int USER1_F = 64;
    public static final int USER2_F = 128;
    public static final int USER3_F = 256;
    public static final int USER4_F = 512;
    public static final int USER5_F = 1024;
    public static final int USER6_F = 2048;
    public static final int USER7_F = 4096;
    public static final int USER8_F = 8192;
    public static final int COMPARE_BY_IDENTITY_F = 8192;
    public static final IRubyObject NEVER;
    public static final IRubyObject UNDEF;
    public static final ObjectAllocator BASICOBJECT_ALLOCATOR;
    private static final MethodsCollector methodsCollector;
    private static final MethodsCollector methodsCollector19;
    
    private RubyBasicObject() {
        this.varTable = RubyBasicObject.NULL_OBJECT_ARRAY;
    }
    
    public static RubyClass createBasicObjectClass(final Ruby runtime, final RubyClass objectClass) {
        objectClass.index = 14;
        objectClass.defineAnnotatedMethods(RubyBasicObject.class);
        runtime.setDefaultMethodMissing(objectClass.searchMethod("method_missing"));
        return objectClass;
    }
    
    public IRubyObject initialize() {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19() {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final IRubyObject arg0) {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final IRubyObject arg0, final IRubyObject arg1) {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final IRubyObject[] args) {
        return this.getRuntime().getNil();
    }
    
    public RubyBasicObject(final Ruby runtime, final RubyClass metaClass) {
        this.varTable = RubyBasicObject.NULL_OBJECT_ARRAY;
        assert metaClass != null : "NULL Metaclass!!?!?!";
        this.metaClass = metaClass;
        if (runtime.isObjectSpaceEnabled()) {
            this.addToObjectSpace(runtime);
        }
    }
    
    public RubyBasicObject(final RubyClass metaClass) {
        this.varTable = RubyBasicObject.NULL_OBJECT_ARRAY;
        this.metaClass = metaClass;
    }
    
    protected RubyBasicObject(final Ruby runtime, final RubyClass metaClass, final boolean useObjectSpace, final boolean canBeTainted) {
        this.varTable = RubyBasicObject.NULL_OBJECT_ARRAY;
        this.metaClass = metaClass;
        if (useObjectSpace) {
            this.addToObjectSpace(runtime);
        }
    }
    
    protected RubyBasicObject(final Ruby runtime, final RubyClass metaClass, final boolean useObjectSpace) {
        this.varTable = RubyBasicObject.NULL_OBJECT_ARRAY;
        this.metaClass = metaClass;
        if (useObjectSpace) {
            this.addToObjectSpace(runtime);
        }
    }
    
    private void addToObjectSpace(final Ruby runtime) {
        assert runtime.isObjectSpaceEnabled();
        runtime.getObjectSpace().add(this);
    }
    
    protected void taint(final Ruby runtime) {
        runtime.secure(4);
        if (!this.isTaint()) {
            this.testFrozen();
            this.setTaint(true);
        }
    }
    
    protected final void testFrozen(final String message) {
        if (this.isFrozen()) {
            throw this.getRuntime().newFrozenError(message);
        }
    }
    
    protected final void testFrozen() {
        if (this.isFrozen()) {
            throw this.getRuntime().newFrozenError("object");
        }
    }
    
    public final void setFlag(final int flag, final boolean set) {
        if (set) {
            this.flags |= flag;
        }
        else {
            this.flags &= ~flag;
        }
    }
    
    public final boolean getFlag(final int flag) {
        return (this.flags & flag) != 0x0;
    }
    
    @Deprecated
    public IRubyObject callSuper(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return RuntimeHelpers.invokeSuper(context, this, args, block);
    }
    
    public final IRubyObject checkCallMethod(final ThreadContext context, final String name) {
        return RuntimeHelpers.invokeChecked(context, this, name);
    }
    
    public final IRubyObject callMethod(final ThreadContext context, final String name) {
        return RuntimeHelpers.invoke(context, this, name);
    }
    
    public final IRubyObject callMethod(final ThreadContext context, final String name, final IRubyObject arg) {
        return RuntimeHelpers.invoke(context, this, name, arg);
    }
    
    public final IRubyObject callMethod(final ThreadContext context, final String name, final IRubyObject[] args) {
        return RuntimeHelpers.invoke(context, this, name, args);
    }
    
    public final IRubyObject callMethod(final String name, final IRubyObject... args) {
        return RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, name, args);
    }
    
    public final IRubyObject callMethod(final String name) {
        return RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, name);
    }
    
    public final IRubyObject callMethod(final ThreadContext context, final String name, final IRubyObject[] args, final Block block) {
        return RuntimeHelpers.invoke(context, this, name, args, block);
    }
    
    @Deprecated
    public final IRubyObject callMethod(final ThreadContext context, final int methodIndex, final String name) {
        return RuntimeHelpers.invoke(context, this, name);
    }
    
    @Deprecated
    public final IRubyObject callMethod(final ThreadContext context, final int methodIndex, final String name, final IRubyObject arg) {
        return RuntimeHelpers.invoke(context, this, name, arg, Block.NULL_BLOCK);
    }
    
    public final boolean isNil() {
        return (this.flags & 0x2) != 0x0;
    }
    
    public final boolean isTrue() {
        return (this.flags & 0x1) == 0x0;
    }
    
    public final boolean isFalse() {
        return (this.flags & 0x1) != 0x0;
    }
    
    public boolean isTaint() {
        return (this.flags & 0x8) != 0x0;
    }
    
    public void setTaint(final boolean taint) {
        if (this.isImmediate()) {
            return;
        }
        if (taint) {
            this.flags |= 0x8;
        }
        else {
            this.flags &= 0xFFFFFFF7;
        }
    }
    
    public IRubyObject infectBy(final IRubyObject obj) {
        if (obj.isTaint()) {
            this.setTaint(true);
        }
        if (obj.isUntrusted()) {
            this.setUntrusted(true);
        }
        return this;
    }
    
    final RubyBasicObject infectBy(final RubyBasicObject obj) {
        this.flags |= (obj.flags & 0x18);
        return this;
    }
    
    final RubyBasicObject infectBy(final int tuFlags) {
        this.flags |= (tuFlags & 0x18);
        return this;
    }
    
    public boolean isFrozen() {
        return (this.flags & 0x4) != 0x0;
    }
    
    public void setFrozen(final boolean frozen) {
        if (frozen) {
            this.flags |= 0x4;
        }
        else {
            this.flags &= 0xFFFFFFFB;
        }
    }
    
    public boolean isUntrusted() {
        return (this.flags & 0x10) != 0x0;
    }
    
    public void setUntrusted(final boolean untrusted) {
        if (untrusted) {
            this.flags |= 0x10;
        }
        else {
            this.flags &= 0xFFFFFFEF;
        }
    }
    
    public boolean isImmediate() {
        return false;
    }
    
    public final RubyClass getMetaClass() {
        return this.metaClass;
    }
    
    public RubyClass getSingletonClass() {
        RubyClass klass;
        if (this.getMetaClass().isSingleton() && ((MetaClass)this.getMetaClass()).getAttached() == this) {
            klass = this.getMetaClass();
        }
        else {
            klass = this.makeMetaClass(this.getMetaClass());
        }
        klass.setTaint(this.isTaint());
        if (this.isFrozen()) {
            klass.setFrozen(true);
        }
        return klass;
    }
    
    public RubyClass makeMetaClass(final RubyClass superClass) {
        final MetaClass klass = new MetaClass(this.getRuntime(), superClass, this);
        this.setMetaClass(klass);
        klass.setMetaClass(superClass.getRealClass().getMetaClass());
        superClass.addSubclass(klass);
        return klass;
    }
    
    public void setMetaClass(final RubyClass metaClass) {
        this.metaClass = metaClass;
    }
    
    public RubyClass getType() {
        return this.getMetaClass().getRealClass();
    }
    
    public final boolean respondsTo(final String name) {
        final DynamicMethod method = this.getMetaClass().searchMethod("respond_to?");
        if (method == this.getRuntime().getRespondToMethod()) {
            return this.getMetaClass().isMethodBound(name, false);
        }
        if (!method.isUndefined()) {
            return method.call(this.getRuntime().getCurrentContext(), this, this.metaClass, "respond_to?", this.getRuntime().newSymbol(name)).isTrue();
        }
        return this.callMethod(this.getRuntime().getCurrentContext(), "respond_to?", this.getRuntime().newSymbol(name)).isTrue();
    }
    
    public final boolean respondsToMissing(final String name) {
        return this.respondsToMissing(name, true);
    }
    
    public final boolean respondsToMissing(final String name, final boolean priv) {
        final DynamicMethod method = this.getMetaClass().searchMethod("respond_to_missing?");
        return !method.isUndefined() && method.call(this.getRuntime().getCurrentContext(), this, this.metaClass, "respond_to_missing?", this.getRuntime().newSymbol(name), this.getRuntime().newBoolean(priv)).isTrue();
    }
    
    public final Ruby getRuntime() {
        return this.getMetaClass().getClassRuntime();
    }
    
    public Class getJavaClass() {
        final Object obj = this.dataGetStruct();
        if (obj instanceof JavaObject) {
            return ((JavaObject)obj).getValue().getClass();
        }
        return this.getClass();
    }
    
    public String asJavaString() {
        final IRubyObject asString = this.checkStringType();
        if (!asString.isNil()) {
            return ((RubyString)asString).asJavaString();
        }
        throw this.getRuntime().newTypeError(this.inspect().toString() + " is not a string");
    }
    
    public RubyString asString() {
        final IRubyObject str = RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, "to_s");
        if (!(str instanceof RubyString)) {
            return (RubyString)this.anyToString();
        }
        if (this.isTaint()) {
            str.setTaint(true);
        }
        return (RubyString)str;
    }
    
    public RubyArray convertToArray() {
        return (RubyArray)TypeConverter.convertToType(this, this.getRuntime().getArray(), "to_ary");
    }
    
    public RubyHash convertToHash() {
        return (RubyHash)TypeConverter.convertToType(this, this.getRuntime().getHash(), "to_hash");
    }
    
    public RubyFloat convertToFloat() {
        return (RubyFloat)TypeConverter.convertToType(this, this.getRuntime().getFloat(), "to_f");
    }
    
    public RubyInteger convertToInteger() {
        return this.convertToInteger("to_int");
    }
    
    @Deprecated
    public RubyInteger convertToInteger(final int methodIndex, final String convertMethod) {
        return this.convertToInteger(convertMethod);
    }
    
    public RubyInteger convertToInteger(final String convertMethod) {
        final IRubyObject val = TypeConverter.convertToType(this, this.getRuntime().getInteger(), convertMethod, true);
        if (!(val instanceof RubyInteger)) {
            throw this.getRuntime().newTypeError(this.getMetaClass().getName() + "#" + convertMethod + " should return Integer");
        }
        return (RubyInteger)val;
    }
    
    public RubyString convertToString() {
        return (RubyString)TypeConverter.convertToType(this, this.getRuntime().getString(), "to_str");
    }
    
    public IRubyObject anyToString() {
        final String cname = this.getMetaClass().getRealClass().getName();
        final RubyString str = this.getRuntime().newString("#<" + cname + ":0x" + Integer.toHexString(System.identityHashCode(this)) + ">");
        str.setTaint(this.isTaint());
        return str;
    }
    
    public IRubyObject checkStringType() {
        IRubyObject str = TypeConverter.convertToTypeWithCheck(this, this.getRuntime().getString(), "to_str");
        if (!str.isNil() && !(str instanceof RubyString)) {
            str = RubyString.newEmptyString(this.getRuntime());
        }
        return str;
    }
    
    public IRubyObject checkStringType19() {
        IRubyObject str = TypeConverter.convertToTypeWithCheck19(this, this.getRuntime().getString(), "to_str");
        if (!str.isNil() && !(str instanceof RubyString)) {
            str = RubyString.newEmptyString(this.getRuntime());
        }
        return str;
    }
    
    public IRubyObject checkArrayType() {
        return TypeConverter.convertToTypeWithCheck(this, this.getRuntime().getArray(), "to_ary");
    }
    
    IRubyObject checkIntegerType(final Ruby runtime, final IRubyObject obj, final String method) {
        if (obj instanceof RubyFixnum) {
            return obj;
        }
        final IRubyObject conv = TypeConverter.convertToType(obj, this.getRuntime().getInteger(), method, false);
        return (conv instanceof RubyInteger) ? conv : obj.getRuntime().getNil();
    }
    
    public Object toJava(final Class target) {
        if (target == Void.TYPE) {
            return null;
        }
        if (this.dataGetStruct() instanceof JavaObject) {
            final JavaObject innerWrapper = (JavaObject)this.dataGetStruct();
            if (target.isAssignableFrom(innerWrapper.getValue().getClass())) {
                this.getRuntime().getJavaSupport().getObjectProxyCache().put(innerWrapper.getValue(), this);
                return innerWrapper.getValue();
            }
        }
        else if (JavaUtil.isDuckTypeConvertable(this.getClass(), target)) {
            if (!this.respondsTo("java_object")) {
                return JavaUtil.convertProcToInterface(this.getRuntime().getCurrentContext(), this, target);
            }
        }
        else if (target.isAssignableFrom(this.getClass())) {
            return this;
        }
        throw this.getRuntime().newTypeError("cannot convert instance of " + this.getClass() + " to " + target);
    }
    
    public IRubyObject dup() {
        if (this.isImmediate()) {
            throw this.getRuntime().newTypeError("can't dup " + this.getMetaClass().getName());
        }
        final IRubyObject dup = this.getMetaClass().getRealClass().allocate();
        if (this.isTaint()) {
            dup.setTaint(true);
        }
        if (this.isUntrusted()) {
            dup.setUntrusted(true);
        }
        initCopy(dup, this);
        return dup;
    }
    
    private static void initCopy(final IRubyObject clone, final IRubyObject original) {
        assert !clone.isFrozen() : "frozen object (" + clone.getMetaClass().getName() + ") allocated";
        original.copySpecialInstanceVariables(clone);
        if (original.hasVariables()) {
            clone.syncVariables(original);
        }
        if (original instanceof RubyModule) {
            final RubyModule cloneMod = (RubyModule)clone;
            cloneMod.syncConstants((RubyModule)original);
            cloneMod.syncClassVariables((RubyModule)original);
        }
        clone.callMethod(clone.getRuntime().getCurrentContext(), "initialize_copy", original);
    }
    
    public void copySpecialInstanceVariables(final IRubyObject clone) {
    }
    
    static RubyString inspect(final ThreadContext context, final IRubyObject object) {
        return RubyString.objAsString(context, object.callMethod(context, "inspect"));
    }
    
    public IRubyObject rbClone() {
        if (this.isImmediate()) {
            throw this.getRuntime().newTypeError("can't clone " + this.getMetaClass().getName());
        }
        final RubyBasicObject clone = (RubyBasicObject)this.getMetaClass().getRealClass().allocate();
        clone.setMetaClass(this.getSingletonClassClone());
        if (this.isTaint()) {
            clone.setTaint(true);
        }
        initCopy(clone, this);
        if (this.isFrozen()) {
            clone.setFrozen(true);
        }
        if (this.isUntrusted()) {
            clone.setUntrusted(true);
        }
        return clone;
    }
    
    protected RubyClass getSingletonClassClone() {
        final RubyClass klass = this.getMetaClass();
        if (!klass.isSingleton()) {
            return klass;
        }
        final MetaClass clone = new MetaClass(this.getRuntime(), klass.getSuperClass(), ((MetaClass)klass).getAttached());
        clone.flags = this.flags;
        if (this instanceof RubyClass) {
            clone.setMetaClass(clone);
        }
        else {
            clone.setMetaClass(klass.getSingletonClassClone());
        }
        if (klass.hasVariables()) {
            clone.syncVariables(klass);
        }
        clone.syncConstants(klass);
        klass.cloneMethods(clone);
        ((MetaClass)clone.getMetaClass()).setAttached(clone);
        return clone;
    }
    
    public boolean isModule() {
        return false;
    }
    
    public boolean isClass() {
        return false;
    }
    
    public synchronized void dataWrapStruct(final Object obj) {
        if (obj == null) {
            this.removeInternalVariable("__wrap_struct__");
        }
        else {
            this.fastSetInternalVariable("__wrap_struct__", obj);
        }
    }
    
    public synchronized Object dataGetStruct() {
        return this.fastGetInternalVariable("__wrap_struct__");
    }
    
    public synchronized Object dataGetStructChecked() {
        TypeConverter.checkData(this);
        return this.fastGetInternalVariable("__wrap_struct__");
    }
    
    public IRubyObject id() {
        return this.getRuntime().newFixnum(this.getObjectId());
    }
    
    protected synchronized long getObjectId() {
        synchronized (this) {
            final RubyClass.VariableAccessor objectIdAccessor = this.getMetaClass().getRealClass().getObjectIdAccessorForWrite();
            final Long id = (Long)objectIdAccessor.get(this);
            if (id == null) {
                return this.initObjectId(objectIdAccessor);
            }
            return id;
        }
    }
    
    protected synchronized long initObjectId(final RubyClass.VariableAccessor objectIdAccessor) {
        final Ruby runtime = this.getRuntime();
        long id;
        if (runtime.isObjectSpaceEnabled()) {
            id = runtime.getObjectSpace().createAndRegisterObjectId(this);
        }
        else {
            id = ObjectSpace.calculateObjectId(this);
        }
        this.setObjectId(objectIdAccessor.getIndex(), id);
        return id;
    }
    
    public IRubyObject inspect() {
        final Ruby runtime = this.getRuntime();
        if (!this.isImmediate() && !(this instanceof RubyModule) && this.hasVariables()) {
            return this.hashyInspect();
        }
        if (this.isNil()) {
            return RubyNil.inspect(this);
        }
        return RuntimeHelpers.invoke(runtime.getCurrentContext(), this, "to_s");
    }
    
    public IRubyObject hashyInspect() {
        final Ruby runtime = this.getRuntime();
        final StringBuilder part = new StringBuilder();
        final String cname = this.getMetaClass().getRealClass().getName();
        part.append("#<").append(cname).append(":0x");
        part.append(Integer.toHexString(this.inspectHashCode()));
        if (runtime.isInspecting(this)) {
            part.append(" ...>");
            return runtime.newString(part.toString());
        }
        try {
            runtime.registerInspecting(this);
            return runtime.newString(this.inspectObj(part).toString());
        }
        finally {
            runtime.unregisterInspecting(this);
        }
    }
    
    protected int inspectHashCode() {
        return System.identityHashCode(this);
    }
    
    private StringBuilder inspectObj(final StringBuilder part) {
        final ThreadContext context = this.getRuntime().getCurrentContext();
        String sep = "";
        for (final Variable<IRubyObject> ivar : this.getInstanceVariableList()) {
            part.append(sep).append(" ").append(ivar.getName()).append("=");
            part.append(ivar.getValue().callMethod(context, "inspect"));
            sep = ",";
        }
        part.append(">");
        return part;
    }
    
    @JRubyMethod(name = { "!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_not(final ThreadContext context) {
        return context.getRuntime().newBoolean(!this.isTrue());
    }
    
    @JRubyMethod(name = { "!=" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_not_equal(final ThreadContext context, final IRubyObject other) {
        return context.getRuntime().newBoolean(!RuntimeHelpers.invokedynamic(context, this, 1, other).isTrue());
    }
    
    public int compareTo(final IRubyObject other) {
        return (int)RuntimeHelpers.invokedynamic(this.getRuntime().getCurrentContext(), this, 4, other).convertToInteger().getLongValue();
    }
    
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject obj) {
        return this.op_equal_19(context, obj);
    }
    
    @JRubyMethod(name = { "==" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_equal_19(final ThreadContext context, final IRubyObject obj) {
        return (this == obj) ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
    }
    
    public IRubyObject op_eqq(final ThreadContext context, final IRubyObject other) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "equal?" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject equal_p19(final ThreadContext context, final IRubyObject other) {
        return this.op_equal_19(context, other);
    }
    
    protected static boolean equalInternal(final ThreadContext context, final IRubyObject that, final IRubyObject other) {
        return that == other || RuntimeHelpers.invokedynamic(context, that, 1, other).isTrue();
    }
    
    public boolean eql(final IRubyObject other) {
        return RuntimeHelpers.invokedynamic(this.getRuntime().getCurrentContext(), this, 2, other).isTrue();
    }
    
    public void addFinalizer(final IRubyObject f) {
        Finalizer finalizer = (Finalizer)this.fastGetInternalVariable("__finalizer__");
        if (finalizer == null) {
            final long id = this.getObjectId();
            final RubyFixnum fixnumId = (RubyFixnum)this.id();
            this.getRuntime().getObjectSpace().registerObjectId(id, this);
            finalizer = new Finalizer(fixnumId);
            this.fastSetInternalVariable("__finalizer__", finalizer);
            this.getRuntime().addFinalizer(finalizer);
        }
        finalizer.addFinalizer(f);
    }
    
    public void removeFinalizers() {
        final Finalizer finalizer = (Finalizer)this.fastGetInternalVariable("__finalizer__");
        if (finalizer != null) {
            finalizer.removeFinalizers();
            this.removeInternalVariable("__finalizer__");
            this.getRuntime().removeFinalizer(finalizer);
        }
    }
    
    private Object[] getVariableTableForRead() {
        return this.varTable;
    }
    
    private synchronized Object[] getVariableTableForWrite(final int index) {
        if (this.varTable == RubyBasicObject.NULL_OBJECT_ARRAY) {
            this.varTable = new Object[this.getMetaClass().getRealClass().getVariableTableSizeWithObjectId()];
        }
        else if (this.varTable.length <= index) {
            final Object[] newTable = new Object[this.getMetaClass().getRealClass().getVariableTableSizeWithObjectId()];
            System.arraycopy(this.varTable, 0, newTable, 0, this.varTable.length);
            this.varTable = newTable;
        }
        return this.varTable;
    }
    
    public Object getVariable(final int index) {
        if (index < 0) {
            return null;
        }
        final Object[] ivarTable = this.getVariableTableForRead();
        if (ivarTable.length > index) {
            return ivarTable[index];
        }
        return null;
    }
    
    public void setVariable(final int index, final Object value) {
        this.ensureInstanceVariablesSettable();
        if (index < 0) {
            return;
        }
        final Object[] ivarTable = this.getVariableTableForWrite(index);
        ivarTable[index] = value;
    }
    
    private void setObjectId(final int index, final long value) {
        if (index < 0) {
            return;
        }
        final Object[] ivarTable = this.getVariableTableForWrite(index);
        ivarTable[index] = value;
    }
    
    public boolean hasVariables() {
        return this.getMetaClass().getRealClass().getVariableTableSize() > 0 && this.varTable.length > 0;
    }
    
    @Deprecated
    public int getVariableCount() {
        return Math.min(this.varTable.length, this.getMetaClass().getRealClass().getVariableTableSize());
    }
    
    public List<Variable<Object>> getVariableList() {
        final Map<String, RubyClass.VariableAccessor> ivarAccessors = this.getMetaClass().getRealClass().getVariableAccessorsForRead();
        final ArrayList<Variable<Object>> list = new ArrayList<Variable<Object>>();
        for (final Map.Entry<String, RubyClass.VariableAccessor> entry : ivarAccessors.entrySet()) {
            final Object value = entry.getValue().get(this);
            if (value == null) {
                continue;
            }
            list.add(new VariableEntry<Object>(entry.getKey(), value));
        }
        return list;
    }
    
    public List<String> getVariableNameList() {
        final Map<String, RubyClass.VariableAccessor> ivarAccessors = this.getMetaClass().getRealClass().getVariableAccessorsForRead();
        final ArrayList<String> list = new ArrayList<String>();
        for (final Map.Entry<String, RubyClass.VariableAccessor> entry : ivarAccessors.entrySet()) {
            final Object value = entry.getValue().get(this);
            if (value == null) {
                continue;
            }
            list.add(entry.getKey());
        }
        return list;
    }
    
    protected boolean variableTableContains(final String name) {
        return this.getMetaClass().getRealClass().getVariableAccessorForRead(name).get(this) != null;
    }
    
    protected boolean variableTableFastContains(final String internedName) {
        return this.variableTableContains(internedName);
    }
    
    protected Object variableTableFetch(final String name) {
        return this.getMetaClass().getRealClass().getVariableAccessorForRead(name).get(this);
    }
    
    protected Object variableTableFastFetch(final String internedName) {
        return this.variableTableFetch(internedName);
    }
    
    protected Object variableTableStore(final String name, final Object value) {
        this.getMetaClass().getRealClass().getVariableAccessorForWrite(name).set(this, value);
        return value;
    }
    
    protected Object variableTableFastStore(final String internedName, final Object value) {
        return this.variableTableStore(internedName, value);
    }
    
    protected Object variableTableRemove(final String name) {
        synchronized (this) {
            final Object value = this.getMetaClass().getRealClass().getVariableAccessorForRead(name).get(this);
            this.getMetaClass().getRealClass().getVariableAccessorForWrite(name).set(this, null);
            return value;
        }
    }
    
    protected void variableTableSync(final List<Variable<Object>> vars) {
        synchronized (this) {
            for (final Variable<Object> var : vars) {
                this.variableTableStore(var.getName(), var.getValue());
            }
        }
    }
    
    public InternalVariables getInternalVariables() {
        return this;
    }
    
    public boolean hasInternalVariable(final String name) {
        assert !IdUtil.isRubyVariable(name);
        return this.variableTableContains(name);
    }
    
    public boolean fastHasInternalVariable(final String internedName) {
        assert !IdUtil.isRubyVariable(internedName);
        return this.variableTableFastContains(internedName);
    }
    
    public Object getInternalVariable(final String name) {
        assert !IdUtil.isRubyVariable(name);
        return this.variableTableFetch(name);
    }
    
    public Object fastGetInternalVariable(final String internedName) {
        assert !IdUtil.isRubyVariable(internedName);
        return this.variableTableFastFetch(internedName);
    }
    
    public void setInternalVariable(final String name, final Object value) {
        assert !IdUtil.isRubyVariable(name);
        this.variableTableStore(name, value);
    }
    
    public void fastSetInternalVariable(final String internedName, final Object value) {
        assert !IdUtil.isRubyVariable(internedName);
        this.variableTableFastStore(internedName, value);
    }
    
    public Object removeInternalVariable(final String name) {
        assert !IdUtil.isRubyVariable(name);
        return this.variableTableRemove(name);
    }
    
    @Deprecated
    public void syncVariables(final List<Variable<Object>> variables) {
        this.variableTableSync(variables);
    }
    
    public void syncVariables(final IRubyObject other) {
        final RubyClass realClass = this.metaClass.getRealClass();
        final RubyClass otherRealClass = other.getMetaClass().getRealClass();
        final boolean sameTable = otherRealClass == realClass;
        for (final Map.Entry<String, RubyClass.VariableAccessor> entry : otherRealClass.getVariableAccessorsForRead().entrySet()) {
            final RubyClass.VariableAccessor accessor = entry.getValue();
            final Object value = accessor.get(other);
            if (value != null) {
                if (sameTable) {
                    accessor.set(this, value);
                }
                else {
                    realClass.getVariableAccessorForWrite(accessor.getName()).set(this, value);
                }
            }
        }
    }
    
    public InstanceVariables getInstanceVariables() {
        return this;
    }
    
    public boolean hasInstanceVariable(final String name) {
        assert IdUtil.isInstanceVariable(name);
        return this.variableTableContains(name);
    }
    
    public boolean fastHasInstanceVariable(final String internedName) {
        assert IdUtil.isInstanceVariable(internedName);
        return this.variableTableFastContains(internedName);
    }
    
    public IRubyObject getInstanceVariable(final String name) {
        assert IdUtil.isInstanceVariable(name);
        return (IRubyObject)this.variableTableFetch(name);
    }
    
    public IRubyObject fastGetInstanceVariable(final String internedName) {
        assert IdUtil.isInstanceVariable(internedName);
        return (IRubyObject)this.variableTableFastFetch(internedName);
    }
    
    public IRubyObject setInstanceVariable(final String name, final IRubyObject value) {
        assert IdUtil.isInstanceVariable(name) && value != null;
        this.ensureInstanceVariablesSettable();
        return (IRubyObject)this.variableTableStore(name, value);
    }
    
    public IRubyObject fastSetInstanceVariable(final String internedName, final IRubyObject value) {
        assert IdUtil.isInstanceVariable(internedName) && value != null;
        this.ensureInstanceVariablesSettable();
        return (IRubyObject)this.variableTableFastStore(internedName, value);
    }
    
    public IRubyObject removeInstanceVariable(final String name) {
        assert IdUtil.isInstanceVariable(name);
        this.ensureInstanceVariablesSettable();
        return (IRubyObject)this.variableTableRemove(name);
    }
    
    public List<Variable<IRubyObject>> getInstanceVariableList() {
        final Map<String, RubyClass.VariableAccessor> ivarAccessors = this.getMetaClass().getVariableAccessorsForRead();
        final ArrayList<Variable<IRubyObject>> list = new ArrayList<Variable<IRubyObject>>();
        for (final Map.Entry<String, RubyClass.VariableAccessor> entry : ivarAccessors.entrySet()) {
            final Object value = entry.getValue().get(this);
            if (value != null && value instanceof IRubyObject) {
                if (!IdUtil.isInstanceVariable(entry.getKey())) {
                    continue;
                }
                list.add(new VariableEntry<IRubyObject>(entry.getKey(), (IRubyObject)value));
            }
        }
        return list;
    }
    
    public List<String> getInstanceVariableNameList() {
        final Map<String, RubyClass.VariableAccessor> ivarAccessors = this.getMetaClass().getRealClass().getVariableAccessorsForRead();
        final ArrayList<String> list = new ArrayList<String>();
        for (final Map.Entry<String, RubyClass.VariableAccessor> entry : ivarAccessors.entrySet()) {
            final Object value = entry.getValue().get(this);
            if (value != null && value instanceof IRubyObject) {
                if (!IdUtil.isInstanceVariable(entry.getKey())) {
                    continue;
                }
                list.add(entry.getKey());
            }
        }
        return list;
    }
    
    public void copyInstanceVariablesInto(final InstanceVariables other) {
        for (final Variable<IRubyObject> var : this.getInstanceVariableList()) {
            synchronized (this) {
                other.setInstanceVariable(var.getName(), var.getValue());
            }
        }
    }
    
    protected final void ensureInstanceVariablesSettable() {
        if (!this.isFrozen() && (this.getRuntime().getSafeLevel() < 4 || this.isTaint())) {
            return;
        }
        if (this.getRuntime().getSafeLevel() >= 4 && !this.isTaint()) {
            throw this.getRuntime().newSecurityError("Insecure: can't modify instance variable");
        }
        if (!this.isFrozen()) {
            return;
        }
        if (this instanceof RubyModule) {
            throw this.getRuntime().newFrozenError("class/module ");
        }
        throw this.getRuntime().newFrozenError("");
    }
    
    public int getNativeTypeIndex() {
        return 38;
    }
    
    public boolean isBuiltin(final String methodName) {
        final DynamicMethod method = this.getMetaClass().searchMethodInner(methodName);
        return method != null && method.isBuiltin();
    }
    
    @JRubyMethod(name = { "singleton_method_added" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject singleton_method_added19(final ThreadContext context, final IRubyObject recv, final IRubyObject symbolId, final Block block) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "singleton_method_removed" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject singleton_method_removed19(final ThreadContext context, final IRubyObject recv, final IRubyObject symbolId, final Block block) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "singleton_method_undefined" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject singleton_method_undefined19(final ThreadContext context, final IRubyObject recv, final IRubyObject symbolId, final Block block) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "method_missing" }, rest = true, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject method_missing19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Visibility lastVis = context.getLastVisibility();
        final CallType lastCallType = context.getLastCallType();
        if (args.length == 0 || !(args[0] instanceof RubySymbol)) {
            throw context.getRuntime().newArgumentError("no id given");
        }
        return RubyKernel.methodMissingDirect(context, recv, (RubySymbol)args[0], lastVis, lastCallType, args, block);
    }
    
    @JRubyMethod(name = { "__send__" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject send19(final ThreadContext context, final Block block) {
        throw context.getRuntime().newArgumentError(0, 1);
    }
    
    @JRubyMethod(name = { "__send__" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject send19(final ThreadContext context, final IRubyObject arg0, final Block block) {
        final String name = arg0.asJavaString();
        return this.getMetaClass().finvoke(context, this, name, block);
    }
    
    @JRubyMethod(name = { "__send__" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject send19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final String name = arg0.asJavaString();
        return this.getMetaClass().finvoke(context, this, name, arg1, block);
    }
    
    @JRubyMethod(name = { "__send__" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject send19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final String name = arg0.asJavaString();
        return this.getMetaClass().finvoke(context, this, name, arg1, arg2, block);
    }
    
    @JRubyMethod(name = { "__send__" }, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject send19(final ThreadContext context, final IRubyObject[] args, final Block block) {
        final String name = args[0].asJavaString();
        final int newArgsLength = args.length - 1;
        IRubyObject[] newArgs;
        if (newArgsLength == 0) {
            newArgs = IRubyObject.NULL_ARRAY;
        }
        else {
            newArgs = new IRubyObject[newArgsLength];
            System.arraycopy(args, 1, newArgs, 0, newArgs.length);
        }
        return this.getMetaClass().finvoke(context, this, name, newArgs, block);
    }
    
    @JRubyMethod(name = { "instance_eval" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject instance_eval19(final ThreadContext context, final Block block) {
        return this.specificEval(context, this.getInstanceEvalClass(), block);
    }
    
    @JRubyMethod(name = { "instance_eval" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject instance_eval19(final ThreadContext context, final IRubyObject arg0, final Block block) {
        return this.specificEval(context, this.getInstanceEvalClass(), arg0, block);
    }
    
    @JRubyMethod(name = { "instance_eval" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject instance_eval19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.specificEval(context, this.getInstanceEvalClass(), arg0, arg1, block);
    }
    
    @JRubyMethod(name = { "instance_eval" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject instance_eval19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.specificEval(context, this.getInstanceEvalClass(), arg0, arg1, arg2, block);
    }
    
    @JRubyMethod(name = { "instance_exec" }, optional = 3, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject instance_exec19(final ThreadContext context, final IRubyObject[] args, final Block block) {
        if (!block.isGiven()) {
            throw context.getRuntime().newLocalJumpErrorNoBlock();
        }
        RubyModule klazz;
        if (this.isImmediate()) {
            klazz = context.getRuntime().getDummy();
        }
        else {
            klazz = this.getSingletonClass();
        }
        return this.yieldUnder(context, klazz, args, block);
    }
    
    protected IRubyObject yieldUnder(final ThreadContext context, final RubyModule under, final IRubyObject[] args, final Block block) {
        context.preExecuteUnder(under, block);
        final Visibility savedVisibility = block.getBinding().getVisibility();
        block.getBinding().setVisibility(Visibility.PUBLIC);
        try {
            if (args.length == 1) {
                final IRubyObject valueInYield = args[0];
                return this.setupBlock(block).yieldNonArray(context, valueInYield, this, context.getRubyClass());
            }
            final IRubyObject valueInYield = RubyArray.newArrayNoCopy(context.getRuntime(), args);
            return block.yieldArray(context, valueInYield, this, context.getRubyClass());
        }
        catch (JumpException.BreakJump bj) {
            return (IRubyObject)bj.getValue();
        }
        finally {
            block.getBinding().setVisibility(savedVisibility);
            context.postExecuteUnder();
        }
    }
    
    private Block setupBlock(Block block) {
        block = block.cloneBlock();
        block.getBinding().setSelf(this);
        block.getBinding().getFrame().setSelf(this);
        return block;
    }
    
    protected IRubyObject yieldUnder(final ThreadContext context, final RubyModule under, final Block block) {
        context.preExecuteUnder(under, block);
        final Visibility savedVisibility = block.getBinding().getVisibility();
        block.getBinding().setVisibility(Visibility.PUBLIC);
        try {
            return this.setupBlock(block).yieldNonArray(context, this, this, context.getRubyClass());
        }
        catch (JumpException.BreakJump bj) {
            return (IRubyObject)bj.getValue();
        }
        finally {
            block.getBinding().setVisibility(savedVisibility);
            context.postExecuteUnder();
        }
    }
    
    public IRubyObject specificEval(final ThreadContext context, final RubyModule mod, final Block block) {
        if (block.isGiven()) {
            return this.yieldUnder(context, mod, block);
        }
        throw context.getRuntime().newArgumentError("block not supplied");
    }
    
    public IRubyObject specificEval(final ThreadContext context, final RubyModule mod, final IRubyObject arg, final Block block) {
        if (block.isGiven()) {
            throw context.getRuntime().newArgumentError(1, 0);
        }
        RubyString evalStr;
        if (arg instanceof RubyString) {
            evalStr = (RubyString)arg;
        }
        else {
            evalStr = arg.convertToString();
        }
        final String file = "(eval)";
        final int line = 0;
        return this.evalUnder(context, mod, evalStr, file, line);
    }
    
    public IRubyObject specificEval(final ThreadContext context, final RubyModule mod, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        if (block.isGiven()) {
            throw context.getRuntime().newArgumentError(2, 0);
        }
        RubyString evalStr;
        if (arg0 instanceof RubyString) {
            evalStr = (RubyString)arg0;
        }
        else {
            evalStr = arg0.convertToString();
        }
        final String file = arg1.convertToString().asJavaString();
        final int line = 0;
        return this.evalUnder(context, mod, evalStr, file, line);
    }
    
    public IRubyObject specificEval(final ThreadContext context, final RubyModule mod, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        if (block.isGiven()) {
            throw context.getRuntime().newArgumentError(2, 0);
        }
        RubyString evalStr;
        if (arg0 instanceof RubyString) {
            evalStr = (RubyString)arg0;
        }
        else {
            evalStr = arg0.convertToString();
        }
        final String file = arg1.convertToString().asJavaString();
        final int line = (int)(arg2.convertToInteger().getLongValue() - 1L);
        return this.evalUnder(context, mod, evalStr, file, line);
    }
    
    protected RubyModule getInstanceEvalClass() {
        if (this.isImmediate()) {
            return this.getRuntime().getDummy();
        }
        return this.getSingletonClass();
    }
    
    public IRubyObject evalUnder(final ThreadContext context, final RubyModule under, final RubyString src, final String file, final int line) {
        final Visibility savedVisibility = context.getCurrentVisibility();
        context.setCurrentVisibility(Visibility.PUBLIC);
        context.preExecuteUnder(under, Block.NULL_BLOCK);
        try {
            return ASTInterpreter.evalSimple(context, this, src, file, line);
        }
        finally {
            context.postExecuteUnder();
            context.setCurrentVisibility(savedVisibility);
        }
    }
    
    public IRubyObject equal_p(final ThreadContext context, final IRubyObject obj) {
        return (this == obj) ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
    }
    
    public IRubyObject eql_p(final IRubyObject obj) {
        return (this == obj) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (this == other || RuntimeHelpers.invokedynamic(context, this, 1, other).isTrue()) {
            return RubyFixnum.zero(runtime);
        }
        return runtime.getNil();
    }
    
    public IRubyObject initialize_copy(final IRubyObject original) {
        if (this == original) {
            return this;
        }
        this.checkFrozen();
        if (this.getMetaClass().getRealClass() != original.getMetaClass().getRealClass()) {
            throw this.getRuntime().newTypeError("initialize_copy should take same class object");
        }
        return this;
    }
    
    protected void checkFrozen() {
        this.testFrozen();
    }
    
    public RubyBoolean respond_to_p(final IRubyObject mname) {
        final String name = mname.asJavaString();
        return this.getRuntime().newBoolean(this.getMetaClass().isMethodBound(name, true));
    }
    
    public IRubyObject respond_to_p19(final IRubyObject mname) {
        final String name = mname.asJavaString();
        IRubyObject respond = this.getRuntime().newBoolean(this.getMetaClass().isMethodBound(name, true, true));
        if (!respond.isTrue()) {
            respond = RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, "respond_to_missing?", mname, this.getRuntime().getFalse());
            respond = this.getRuntime().newBoolean(respond.isTrue());
        }
        return respond;
    }
    
    public RubyBoolean respond_to_p(final IRubyObject mname, final IRubyObject includePrivate) {
        final String name = mname.asJavaString();
        return this.getRuntime().newBoolean(this.getMetaClass().isMethodBound(name, !includePrivate.isTrue()));
    }
    
    public IRubyObject respond_to_p19(final IRubyObject mname, final IRubyObject includePrivate) {
        final String name = mname.asJavaString();
        IRubyObject respond = this.getRuntime().newBoolean(this.getMetaClass().isMethodBound(name, !includePrivate.isTrue()));
        if (!respond.isTrue()) {
            respond = RuntimeHelpers.invoke(this.getRuntime().getCurrentContext(), this, "respond_to_missing?", mname, includePrivate);
            respond = this.getRuntime().newBoolean(respond.isTrue());
        }
        return respond;
    }
    
    public IRubyObject id_deprecated() {
        this.getRuntime().getWarnings().warn(IRubyWarnings.ID.DEPRECATED_METHOD, "Object#id will be deprecated; use Object#object_id", "Object#id", "Object#object_id");
        return this.id();
    }
    
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum(super.hashCode());
    }
    
    public RubyClass type() {
        return this.getMetaClass().getRealClass();
    }
    
    public RubyClass type_deprecated() {
        this.getRuntime().getWarnings().warn(IRubyWarnings.ID.DEPRECATED_METHOD, "Object#type is deprecated; use Object#class", "Object#type", "Object#class");
        return this.type();
    }
    
    public IRubyObject display(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject port = (args.length == 0) ? context.getRuntime().getGlobalVariables().get("$>") : args[0];
        port.callMethod(context, "write", this);
        return context.getRuntime().getNil();
    }
    
    public RubyBoolean tainted_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.isTaint());
    }
    
    public IRubyObject taint(final ThreadContext context) {
        this.taint(context.getRuntime());
        return this;
    }
    
    public IRubyObject untaint(final ThreadContext context) {
        context.getRuntime().secure(3);
        if (this.isTaint()) {
            this.testFrozen();
            this.setTaint(false);
        }
        return this;
    }
    
    public IRubyObject freeze(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if ((this.flags & 0x4) == 0x0 && (runtime.is1_9() || !this.isImmediate())) {
            if (runtime.getSafeLevel() >= 4 && !this.isTaint()) {
                throw runtime.newSecurityError("Insecure: can't freeze object");
            }
            this.flags |= 0x4;
        }
        return this;
    }
    
    public RubyBoolean frozen_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.isFrozen());
    }
    
    public RubyBoolean untrusted_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.isUntrusted());
    }
    
    public IRubyObject untrust(final ThreadContext context) {
        if (!this.isUntrusted() && !this.isImmediate()) {
            this.checkFrozen();
            this.flags |= 0x10;
        }
        return this;
    }
    
    public IRubyObject trust(final ThreadContext context) {
        if (this.isUntrusted() && !this.isImmediate()) {
            this.checkFrozen();
            this.flags &= 0xFFFFFFEF;
        }
        return this;
    }
    
    public RubyBoolean instance_of_p(final ThreadContext context, final IRubyObject type) {
        if (this.type() == type) {
            return context.getRuntime().getTrue();
        }
        if (!(type instanceof RubyModule)) {
            throw context.getRuntime().newTypeError("class or module required");
        }
        return context.getRuntime().getFalse();
    }
    
    public RubyBoolean kind_of_p(final ThreadContext context, final IRubyObject type) {
        if (!(type instanceof RubyModule)) {
            throw context.getRuntime().newTypeError("class or module required");
        }
        return context.getRuntime().newBoolean(((RubyModule)type).isInstance(this));
    }
    
    public IRubyObject methods(final ThreadContext context, final IRubyObject[] args) {
        return this.methods(context, args, false);
    }
    
    public IRubyObject methods19(final ThreadContext context, final IRubyObject[] args) {
        return this.methods(context, args, true);
    }
    
    public IRubyObject methods(final ThreadContext context, final IRubyObject[] args, final boolean useSymbols) {
        final boolean all = args.length != 1 || args[0].isTrue();
        final Ruby runtime = this.getRuntime();
        final RubyArray methods = runtime.newArray();
        final Set<String> seen = new HashSet<String>();
        if (this.getMetaClass().isSingleton()) {
            this.getMetaClass().populateInstanceMethodNames(seen, methods, Visibility.PRIVATE, true, useSymbols, false);
            if (all) {
                this.getMetaClass().getSuperClass().populateInstanceMethodNames(seen, methods, Visibility.PRIVATE, true, useSymbols, true);
            }
        }
        else if (all) {
            this.getMetaClass().populateInstanceMethodNames(seen, methods, Visibility.PRIVATE, true, useSymbols, true);
        }
        return methods;
    }
    
    public IRubyObject public_methods(final ThreadContext context, final IRubyObject[] args) {
        return this.getMetaClass().public_instance_methods(this.trueIfNoArgument(context, args));
    }
    
    public IRubyObject public_methods19(final ThreadContext context, final IRubyObject[] args) {
        return this.getMetaClass().public_instance_methods19(this.trueIfNoArgument(context, args));
    }
    
    public IRubyObject protected_methods(final ThreadContext context, final IRubyObject[] args) {
        return this.getMetaClass().protected_instance_methods(this.trueIfNoArgument(context, args));
    }
    
    public IRubyObject protected_methods19(final ThreadContext context, final IRubyObject[] args) {
        return this.getMetaClass().protected_instance_methods19(this.trueIfNoArgument(context, args));
    }
    
    public IRubyObject private_methods(final ThreadContext context, final IRubyObject[] args) {
        return this.getMetaClass().private_instance_methods(this.trueIfNoArgument(context, args));
    }
    
    public IRubyObject private_methods19(final ThreadContext context, final IRubyObject[] args) {
        return this.getMetaClass().private_instance_methods19(this.trueIfNoArgument(context, args));
    }
    
    private IRubyObject[] trueIfNoArgument(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject[] array;
        if (args.length == 0) {
            array = new IRubyObject[] { context.getRuntime().getTrue() };
        }
        return array;
    }
    
    public RubyArray singleton_methods(final ThreadContext context, final IRubyObject[] args) {
        return this.singletonMethods(context, args, RubyBasicObject.methodsCollector);
    }
    
    public RubyArray singleton_methods19(final ThreadContext context, final IRubyObject[] args) {
        return this.singletonMethods(context, args, RubyBasicObject.methodsCollector19);
    }
    
    private RubyArray singletonMethods(final ThreadContext context, final IRubyObject[] args, final MethodsCollector collect) {
        boolean all = true;
        if (args.length == 1) {
            all = args[0].isTrue();
        }
        RubyArray singletonMethods;
        if (this.getMetaClass().isSingleton()) {
            final IRubyObject[] methodsArgs = { context.getRuntime().getFalse() };
            singletonMethods = collect.instanceMethods(this.getMetaClass(), methodsArgs);
            if (all) {
                for (RubyClass superClass = this.getMetaClass().getSuperClass(); superClass.isSingleton() || superClass.isIncluded(); superClass = superClass.getSuperClass()) {
                    singletonMethods.concat(collect.instanceMethods(superClass, methodsArgs));
                }
            }
        }
        else {
            singletonMethods = context.getRuntime().newEmptyArray();
        }
        return singletonMethods;
    }
    
    public IRubyObject method(final IRubyObject symbol) {
        return this.getMetaClass().newMethod(this, symbol.asJavaString(), true, null);
    }
    
    public IRubyObject method19(final IRubyObject symbol) {
        return this.getMetaClass().newMethod(this, symbol.asJavaString(), true, null, true);
    }
    
    public IRubyObject to_s() {
        return this.anyToString();
    }
    
    public RubyArray to_a() {
        this.getRuntime().getWarnings().warn(IRubyWarnings.ID.DEPRECATED_METHOD, "default 'to_a' will be obsolete", "to_a");
        return this.getRuntime().newArray(this);
    }
    
    public IRubyObject instance_eval(final ThreadContext context, final Block block) {
        return this.specificEval(context, this.getInstanceEvalClass(), block);
    }
    
    public IRubyObject instance_eval(final ThreadContext context, final IRubyObject arg0, final Block block) {
        return this.specificEval(context, this.getInstanceEvalClass(), arg0, block);
    }
    
    public IRubyObject instance_eval(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.specificEval(context, this.getInstanceEvalClass(), arg0, arg1, block);
    }
    
    public IRubyObject instance_eval(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.specificEval(context, this.getInstanceEvalClass(), arg0, arg1, arg2, block);
    }
    
    public IRubyObject instance_exec(final ThreadContext context, final IRubyObject[] args, final Block block) {
        if (!block.isGiven()) {
            throw context.getRuntime().newArgumentError("block not supplied");
        }
        RubyModule klazz;
        if (this.isImmediate()) {
            klazz = context.getRuntime().getDummy();
        }
        else {
            klazz = this.getSingletonClass();
        }
        return this.yieldUnder(context, klazz, args, block);
    }
    
    public IRubyObject extend(final IRubyObject[] args) {
        final Ruby runtime = this.getRuntime();
        for (int i = 0; i < args.length; ++i) {
            if (!args[i].isModule()) {
                throw runtime.newTypeError(args[i], runtime.getModule());
            }
        }
        final ThreadContext context = runtime.getCurrentContext();
        for (int j = args.length - 1; j >= 0; --j) {
            args[j].callMethod(context, "extend_object", this);
            args[j].callMethod(context, "extended", this);
        }
        return this;
    }
    
    public IRubyObject send(final ThreadContext context, final Block block) {
        throw context.getRuntime().newArgumentError(0, 1);
    }
    
    public IRubyObject send(final ThreadContext context, final IRubyObject arg0, final Block block) {
        final String name = arg0.asJavaString();
        return this.getMetaClass().finvoke(context, this, name, block);
    }
    
    public IRubyObject send(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final String name = arg0.asJavaString();
        return this.getMetaClass().finvoke(context, this, name, arg1, block);
    }
    
    public IRubyObject send(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final String name = arg0.asJavaString();
        return this.getMetaClass().finvoke(context, this, name, arg1, arg2, block);
    }
    
    public IRubyObject send(final ThreadContext context, final IRubyObject[] args, final Block block) {
        final String name = args[0].asJavaString();
        final int newArgsLength = args.length - 1;
        IRubyObject[] newArgs;
        if (newArgsLength == 0) {
            newArgs = IRubyObject.NULL_ARRAY;
        }
        else {
            newArgs = new IRubyObject[newArgsLength];
            System.arraycopy(args, 1, newArgs, 0, newArgs.length);
        }
        return this.getMetaClass().finvoke(context, this, name, newArgs, block);
    }
    
    public IRubyObject nil_p(final ThreadContext context) {
        return context.getRuntime().getFalse();
    }
    
    public IRubyObject op_match(final ThreadContext context, final IRubyObject arg) {
        return context.getRuntime().getFalse();
    }
    
    public IRubyObject op_match19(final ThreadContext context, final IRubyObject arg) {
        return context.getRuntime().getNil();
    }
    
    public IRubyObject op_not_match(final ThreadContext context, final IRubyObject arg) {
        return context.getRuntime().newBoolean(!this.callMethod(context, "=~", arg).isTrue());
    }
    
    public IRubyObject instance_variable_defined_p(final ThreadContext context, final IRubyObject name) {
        if (this.variableTableContains(this.validateInstanceVariable(name.asJavaString()))) {
            return context.getRuntime().getTrue();
        }
        return context.getRuntime().getFalse();
    }
    
    public IRubyObject instance_variable_get(final ThreadContext context, final IRubyObject name) {
        final Object value;
        if ((value = this.variableTableFetch(this.validateInstanceVariable(name.asJavaString()))) != null) {
            return (IRubyObject)value;
        }
        return context.getRuntime().getNil();
    }
    
    public IRubyObject instance_variable_set(final IRubyObject name, final IRubyObject value) {
        this.ensureInstanceVariablesSettable();
        return (IRubyObject)this.variableTableStore(this.validateInstanceVariable(name.asJavaString()), value);
    }
    
    public IRubyObject remove_instance_variable(final ThreadContext context, final IRubyObject name, final Block block) {
        this.ensureInstanceVariablesSettable();
        final IRubyObject value;
        if ((value = (IRubyObject)this.variableTableRemove(this.validateInstanceVariable(name.asJavaString()))) != null) {
            return value;
        }
        throw context.getRuntime().newNameError("instance variable " + name.asJavaString() + " not defined", name.asJavaString());
    }
    
    public RubyArray instance_variables(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final List<String> nameList = this.getInstanceVariableNameList();
        final RubyArray array = runtime.newArray(nameList.size());
        for (final String name : nameList) {
            array.append(runtime.newString(name));
        }
        return array;
    }
    
    public RubyArray instance_variables19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final List<String> nameList = this.getInstanceVariableNameList();
        final RubyArray array = runtime.newArray(nameList.size());
        for (final String name : nameList) {
            array.append(runtime.newSymbol(name));
        }
        return array;
    }
    
    protected String validateInstanceVariable(final String name) {
        if (IdUtil.isValidInstanceVariableName(name)) {
            return name;
        }
        throw this.getRuntime().newNameError("`" + name + "' is not allowable as an instance variable name", name);
    }
    
    static {
        NULL_OBJECT_ARRAY = new Object[0];
        NEVER = new RubyBasicObject();
        UNDEF = new RubyBasicObject();
        BASICOBJECT_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyBasicObject(runtime, klass);
            }
        };
        methodsCollector = new MethodsCollector() {
            public RubyArray instanceMethods(final RubyClass rubyClass, final IRubyObject[] args) {
                return rubyClass.instance_methods(args);
            }
        };
        methodsCollector19 = new MethodsCollector() {
            public RubyArray instanceMethods(final RubyClass rubyClass, final IRubyObject[] args) {
                return rubyClass.instance_methods19(args);
            }
        };
    }
    
    public static class Finalizer implements Finalizable
    {
        private RubyFixnum id;
        private IRubyObject firstFinalizer;
        private List<IRubyObject> finalizers;
        private AtomicBoolean finalized;
        
        public Finalizer(final RubyFixnum id) {
            this.id = id;
            this.finalized = new AtomicBoolean(false);
        }
        
        public void addFinalizer(final IRubyObject finalizer) {
            if (this.firstFinalizer == null) {
                this.firstFinalizer = finalizer;
            }
            else {
                if (this.finalizers == null) {
                    this.finalizers = new ArrayList<IRubyObject>(4);
                }
                this.finalizers.add(finalizer);
            }
        }
        
        public void removeFinalizers() {
            this.firstFinalizer = null;
            this.finalizers = null;
        }
        
        public void finalize() {
            if (this.finalized.compareAndSet(false, true)) {
                if (this.firstFinalizer != null) {
                    this.callFinalizer(this.firstFinalizer);
                }
                if (this.finalizers != null) {
                    for (int i = 0; i < this.finalizers.size(); ++i) {
                        this.callFinalizer(this.finalizers.get(i));
                    }
                }
            }
        }
        
        private void callFinalizer(final IRubyObject finalizer) {
            RuntimeHelpers.invoke(finalizer.getRuntime().getCurrentContext(), finalizer, "call", this.id);
        }
    }
    
    private abstract static class MethodsCollector
    {
        public abstract RubyArray instanceMethods(final RubyClass p0, final IRubyObject[] p1);
    }
}
