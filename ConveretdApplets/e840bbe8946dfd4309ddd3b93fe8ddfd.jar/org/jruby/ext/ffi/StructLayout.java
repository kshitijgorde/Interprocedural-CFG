// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.util.ByteList;
import org.jruby.RubyInteger;
import org.jruby.runtime.Block;
import org.jruby.RubyString;
import org.jruby.RubyHash;
import java.nio.ByteOrder;
import org.jruby.runtime.Visibility;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyNumeric;
import java.util.Arrays;
import org.jruby.RubyArray;
import org.jruby.runtime.ThreadContext;
import java.util.Iterator;
import java.util.Collections;
import org.jruby.RubySymbol;
import java.util.IdentityHashMap;
import java.util.HashMap;
import java.util.ArrayList;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import java.util.Collection;
import java.util.List;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.Map;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "StructLayout" }, parent = "Object")
public final class StructLayout extends Type
{
    static final Storage nullStorage;
    static final String CLASS_NAME = "StructLayout";
    private final Map<IRubyObject, Member> fieldSymbolMap;
    private final Map<IRubyObject, Member> fieldStringMap;
    private final List<IRubyObject> fieldNames;
    private final List<Field> fields;
    private final Collection<Member> members;
    private final int cacheableFieldCount;
    private final int referenceFieldCount;
    
    public static RubyClass createStructLayoutClass(final Ruby runtime, final RubyModule module) {
        final RubyClass layoutClass = runtime.defineClassUnder("StructLayout", module.fastGetClass("Type"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR, module);
        layoutClass.defineAnnotatedMethods(StructLayout.class);
        layoutClass.defineAnnotatedConstants(StructLayout.class);
        final RubyClass arrayClass = runtime.defineClassUnder("ArrayProxy", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR, layoutClass);
        arrayClass.includeModule(runtime.getEnumerable());
        arrayClass.defineAnnotatedMethods(ArrayProxy.class);
        final RubyClass charArrayClass = runtime.defineClassUnder("CharArrayProxy", arrayClass, ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR, layoutClass);
        charArrayClass.defineAnnotatedMethods(CharArrayProxy.class);
        final RubyClass fieldClass = runtime.defineClassUnder("Field", runtime.getObject(), FieldAllocator.INSTANCE, layoutClass);
        fieldClass.defineAnnotatedMethods(Field.class);
        final RubyClass numberFieldClass = runtime.defineClassUnder("Number", fieldClass, NumberFieldAllocator.INSTANCE, layoutClass);
        numberFieldClass.defineAnnotatedMethods(NumberField.class);
        final RubyClass enumFieldClass = runtime.defineClassUnder("Enum", fieldClass, EnumFieldAllocator.INSTANCE, layoutClass);
        enumFieldClass.defineAnnotatedMethods(EnumField.class);
        final RubyClass stringFieldClass = runtime.defineClassUnder("String", fieldClass, StringFieldAllocator.INSTANCE, layoutClass);
        stringFieldClass.defineAnnotatedMethods(StringField.class);
        final RubyClass pointerFieldClass = runtime.defineClassUnder("Pointer", fieldClass, PointerFieldAllocator.INSTANCE, layoutClass);
        pointerFieldClass.defineAnnotatedMethods(PointerField.class);
        final RubyClass functionFieldClass = runtime.defineClassUnder("Function", fieldClass, FunctionFieldAllocator.INSTANCE, layoutClass);
        functionFieldClass.defineAnnotatedMethods(FunctionField.class);
        final RubyClass innerStructFieldClass = runtime.defineClassUnder("InnerStruct", fieldClass, InnerStructFieldAllocator.INSTANCE, layoutClass);
        innerStructFieldClass.defineAnnotatedMethods(InnerStructField.class);
        final RubyClass arrayFieldClass = runtime.defineClassUnder("Array", fieldClass, ArrayFieldAllocator.INSTANCE, layoutClass);
        arrayFieldClass.defineAnnotatedMethods(ArrayField.class);
        final RubyClass mappedFieldClass = runtime.defineClassUnder("Mapped", fieldClass, MappedFieldAllocator.INSTANCE, layoutClass);
        mappedFieldClass.defineAnnotatedMethods(MappedField.class);
        return layoutClass;
    }
    
    private StructLayout(final Ruby runtime, final RubyClass klass, final Collection<IRubyObject> fields, final int size, final int alignment) {
        super(runtime, klass, NativeType.STRUCT, size, alignment);
        int cfCount = 0;
        int refCount = 0;
        final List<Field> fieldList = new ArrayList<Field>(fields.size());
        final List<IRubyObject> names = new ArrayList<IRubyObject>(fields.size());
        final List<Member> memberList = new ArrayList<Member>(fields.size());
        final Map<IRubyObject, Member> memberStringMap = new HashMap<IRubyObject, Member>(fields.size());
        final Map<IRubyObject, Member> memberSymbolMap = new IdentityHashMap<IRubyObject, Member>(fields.size() * 2);
        final int index = 0;
        for (final IRubyObject obj : fields) {
            if (!(obj instanceof Field)) {
                throw runtime.newTypeError(obj, runtime.fastGetModule("FFI").fastGetClass("StructLayout").fastGetClass("Field"));
            }
            final Field f = (Field)obj;
            if (!(f.name instanceof RubySymbol)) {
                throw runtime.newTypeError("fields list contains field with invalid name");
            }
            names.add(f.name);
            fieldList.add(f);
            final Member m = new Member(f, index, f.isCacheable() ? cfCount++ : -1, f.isValueReferenceNeeded() ? refCount++ : -1);
            memberSymbolMap.put(f.name, m);
            memberStringMap.put(f.name, m);
            memberStringMap.put(f.name.asString(), m);
            memberList.add(m);
        }
        this.cacheableFieldCount = cfCount;
        this.referenceFieldCount = refCount;
        this.fieldNames = Collections.unmodifiableList((List<? extends IRubyObject>)new ArrayList<IRubyObject>(names));
        this.fields = Collections.unmodifiableList((List<? extends Field>)fieldList);
        this.fieldStringMap = Collections.unmodifiableMap((Map<? extends IRubyObject, ? extends Member>)memberStringMap);
        this.fieldSymbolMap = Collections.unmodifiableMap((Map<? extends IRubyObject, ? extends Member>)memberSymbolMap);
        this.members = (Collection<Member>)Collections.unmodifiableList((List<?>)memberList);
    }
    
    @JRubyMethod(name = { "new" }, meta = true, required = 3, optional = 1)
    public static final IRubyObject newStructLayout(final ThreadContext context, final IRubyObject klass, final IRubyObject[] args) {
        final IRubyObject rbFields = args[0];
        final IRubyObject size = args[1];
        final IRubyObject alignment = args[2];
        if (!(rbFields instanceof RubyArray)) {
            throw context.getRuntime().newTypeError(rbFields, context.getRuntime().getArray());
        }
        final List<IRubyObject> fields = Arrays.asList(((RubyArray)rbFields).toJavaArrayMaybeUnsafe());
        return new StructLayout(context.getRuntime(), (RubyClass)klass, fields, RubyNumeric.num2int(size), RubyNumeric.num2int(alignment));
    }
    
    @JRubyMethod(name = { "get" }, required = 2)
    public IRubyObject get(final ThreadContext context, final IRubyObject ptr, IRubyObject name) {
        return this.getValue(context, name, StructLayout.nullStorage, ptr);
    }
    
    @JRubyMethod(name = { "put" }, required = 3)
    public IRubyObject put(final ThreadContext context, final IRubyObject ptr, IRubyObject name, final IRubyObject value) {
        this.putValue(context, name, StructLayout.nullStorage, ptr, value);
        return value;
    }
    
    @JRubyMethod(name = { "members" })
    public IRubyObject members(final ThreadContext context) {
        final RubyArray mbrs = RubyArray.newArray(context.getRuntime(), this.fieldNames.size());
        for (final IRubyObject name : this.fieldNames) {
            mbrs.append(name);
        }
        return mbrs;
    }
    
    @JRubyMethod(name = { "offsets" })
    public IRubyObject offsets(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyArray offsets = RubyArray.newArray(runtime);
        for (final IRubyObject name : this.fieldNames) {
            final RubyArray offset = RubyArray.newArray(runtime);
            offset.append(name);
            offset.append(runtime.newFixnum(this.getMember(runtime, name).offset));
            offsets.append(offset);
        }
        return offsets;
    }
    
    @JRubyMethod(name = { "[]" })
    public IRubyObject aref(final ThreadContext context, final IRubyObject fieldName) {
        return this.getField(context.getRuntime(), fieldName);
    }
    
    @JRubyMethod
    public IRubyObject fields(final ThreadContext context) {
        return RubyArray.newArray(context.getRuntime(), this.fields);
    }
    
    final IRubyObject getValue(final ThreadContext context, final IRubyObject name, final Storage cache, final IRubyObject ptr) {
        if (!(ptr instanceof AbstractMemory)) {
            throw context.getRuntime().newTypeError(ptr, context.getRuntime().fastGetModule("FFI").fastGetClass("AbstractMemory"));
        }
        return this.getMember(context.getRuntime(), name).get(context, cache, (AbstractMemory)ptr);
    }
    
    final void putValue(final ThreadContext context, final IRubyObject name, final Storage cache, final IRubyObject ptr, final IRubyObject value) {
        if (!(ptr instanceof AbstractMemory)) {
            throw context.getRuntime().newTypeError(ptr, context.getRuntime().fastGetModule("FFI").fastGetClass("AbstractMemory"));
        }
        this.getMember(context.getRuntime(), name).put(context, cache, (AbstractMemory)ptr, value);
    }
    
    final Member getMember(final Ruby runtime, final IRubyObject name) {
        Member f = this.fieldSymbolMap.get(name);
        if (f != null) {
            return f;
        }
        f = this.fieldStringMap.get(name);
        if (f != null) {
            return f;
        }
        throw runtime.newArgumentError("Unknown field: " + name);
    }
    
    final Field getField(final Ruby runtime, final IRubyObject name) {
        return this.getMember(runtime, name).field;
    }
    
    public final int getSize() {
        return this.getNativeSize();
    }
    
    final int getReferenceFieldCount() {
        return this.referenceFieldCount;
    }
    
    final int getReferenceFieldIndex(final Member member) {
        return member.referenceIndex;
    }
    
    final int getCacheableFieldCount() {
        return this.cacheableFieldCount;
    }
    
    final int getCacheableFieldIndex(final Member member) {
        return member.cacheIndex;
    }
    
    public final int getFieldCount() {
        return this.fields.size();
    }
    
    public final Collection<Field> getFields() {
        return this.fields;
    }
    
    public final Collection<Member> getMembers() {
        return this.members;
    }
    
    static {
        nullStorage = new NullStorage();
    }
    
    public static final class Member
    {
        final FieldIO io;
        final Field field;
        final Type type;
        final int offset;
        final int cacheIndex;
        final int referenceIndex;
        final int index;
        
        protected Member(final Field f, final int index, final int cacheIndex, final int referenceIndex) {
            this.field = f;
            this.io = f.io;
            this.type = f.type;
            this.offset = f.offset;
            this.index = index;
            this.cacheIndex = cacheIndex;
            this.referenceIndex = referenceIndex;
        }
        
        final long getOffset(final IRubyObject ptr) {
            return this.offset;
        }
        
        final int getIndex() {
            return this.index;
        }
        
        public boolean equals(final Object obj) {
            return obj instanceof Member && ((Member)obj).offset == this.offset && this.type.equals(((Member)obj).type);
        }
        
        public int hashCode() {
            return 265 + (this.offset ^ this.offset >>> 32) + 37 * this.type.hashCode();
        }
        
        public final void put(final ThreadContext context, final Storage cache, final AbstractMemory ptr, final IRubyObject value) {
            this.io.put(context, cache, this, ptr, value);
        }
        
        public final IRubyObject get(final ThreadContext context, final Storage cache, final AbstractMemory ptr) {
            return this.io.get(context, cache, this, ptr);
        }
        
        public final int offset() {
            return this.offset;
        }
        
        public final Type type() {
            return this.type;
        }
    }
    
    static final class DefaultFieldIO implements FieldIO
    {
        public static final FieldIO INSTANCE;
        
        public IRubyObject get(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr) {
            return m.field.callMethod(context, "get", ptr);
        }
        
        public void put(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr, final IRubyObject value) {
            m.field.callMethod(context, "put", new IRubyObject[] { ptr, value });
        }
        
        public final boolean isCacheable() {
            return false;
        }
        
        public final boolean isValueReferenceNeeded() {
            return false;
        }
        
        static {
            INSTANCE = new DefaultFieldIO();
        }
    }
    
    private static final class FieldAllocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new Field(runtime, klass);
        }
        
        static {
            INSTANCE = new FieldAllocator();
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::Field" }, parent = "Object")
    public static class Field extends RubyObject
    {
        private FieldIO io;
        private IRubyObject name;
        private Type type;
        private int offset;
        
        Field(final Ruby runtime, final RubyClass klass) {
            this(runtime, klass, DefaultFieldIO.INSTANCE);
        }
        
        Field(final Ruby runtime, final RubyClass klass, final FieldIO io) {
            this(runtime, klass, (Type)runtime.fastGetModule("FFI").fastGetClass("Type").fastGetConstant("VOID"), -1, io);
        }
        
        Field(final Ruby runtime, final RubyClass klass, final Type type, final int offset, final FieldIO io) {
            super(runtime, klass);
            this.name = runtime.getNil();
            this.type = type;
            this.offset = offset;
            this.io = io;
        }
        
        void init(final IRubyObject name, final IRubyObject type, final IRubyObject offset) {
            this.name = name;
            this.type = this.checkType(type);
            this.offset = RubyNumeric.num2int(offset);
        }
        
        void init(final IRubyObject name, final IRubyObject type, final IRubyObject offset, final FieldIO io) {
            this.init(name, type, offset);
            this.io = io;
        }
        
        void init(final IRubyObject[] args, final FieldIO io) {
            this.init(args[0], args[2], args[1], io);
        }
        
        @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, required = 3, optional = 1)
        public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
            this.init(args[0], args[2], args[1]);
            return this;
        }
        
        final Type checkType(final IRubyObject type) {
            if (!(type instanceof Type)) {
                throw this.getRuntime().newTypeError(type, this.getRuntime().fastGetModule("FFI").fastGetClass("Type"));
            }
            return (Type)type;
        }
        
        public final int offset() {
            return this.offset;
        }
        
        public final Type ffiType() {
            return this.type;
        }
        
        public boolean equals(final Object obj) {
            return obj instanceof Field && ((Field)obj).offset == this.offset;
        }
        
        public int hashCode() {
            return 265 + (this.offset ^ this.offset >>> 32);
        }
        
        public final boolean isCacheable() {
            return this.io.isCacheable();
        }
        
        public final boolean isValueReferenceNeeded() {
            return this.io.isValueReferenceNeeded();
        }
        
        final FieldIO getFieldIO() {
            return this.io;
        }
        
        static ByteOrder getByteOrderOption(final ThreadContext context, final IRubyObject[] args) {
            ByteOrder order = ByteOrder.nativeOrder();
            if (args.length > 3 && args[3] instanceof RubyHash) {
                final RubyHash options = (RubyHash)args[3];
                final IRubyObject byte_order = options.fastARef(RubySymbol.newSymbol(context.getRuntime(), "byte_order"));
                if (byte_order instanceof RubySymbol || byte_order instanceof RubyString) {
                    final String orderName = byte_order.asJavaString();
                    if ("network".equals(orderName) || "big".equals(orderName)) {
                        order = ByteOrder.BIG_ENDIAN;
                    }
                    else if ("little".equals(orderName)) {
                        order = ByteOrder.LITTLE_ENDIAN;
                    }
                }
            }
            return order;
        }
        
        @JRubyMethod
        public final IRubyObject size(final ThreadContext context) {
            return context.getRuntime().newFixnum(this.type.getNativeSize());
        }
        
        @JRubyMethod
        public final IRubyObject alignment(final ThreadContext context) {
            return context.getRuntime().newFixnum(this.type.getNativeAlignment());
        }
        
        @JRubyMethod
        public final IRubyObject offset(final ThreadContext context) {
            return context.getRuntime().newFixnum(this.offset);
        }
        
        @JRubyMethod(name = { "type", "ffi_type" })
        public final IRubyObject type(final ThreadContext context) {
            return this.type;
        }
        
        @JRubyMethod
        public final IRubyObject name(final ThreadContext context) {
            return this.name;
        }
    }
    
    private static final class NumberFieldAllocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new NumberField(runtime, klass);
        }
        
        static {
            INSTANCE = new NumberFieldAllocator();
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::Number" }, parent = "FFI::StructLayout::Field")
    public static final class NumberField extends Field
    {
        public NumberField(final Ruby runtime, final RubyClass klass) {
            super(runtime, klass);
        }
        
        public final IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
            this.init(args, new NumberFieldIO(this.checkType(args[2]), Field.getByteOrderOption(context, args)));
            return this;
        }
    }
    
    private static final class EnumFieldAllocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new EnumField(runtime, klass);
        }
        
        static {
            INSTANCE = new EnumFieldAllocator();
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::Enum" }, parent = "FFI::StructLayout::Field")
    public static final class EnumField extends Field
    {
        public EnumField(final Ruby runtime, final RubyClass klass) {
            super(runtime, klass);
        }
        
        public final IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
            this.init(args, new EnumFieldIO(Field.getByteOrderOption(context, args)));
            return this;
        }
    }
    
    private static final class StringFieldAllocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new StringField(runtime, klass);
        }
        
        static {
            INSTANCE = new StringFieldAllocator();
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::String" }, parent = "FFI::StructLayout::Field")
    static final class StringField extends Field
    {
        public StringField(final Ruby runtime, final RubyClass klass) {
            super(runtime, klass, StringFieldIO.INSTANCE);
        }
    }
    
    private static final class PointerFieldAllocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new PointerField(runtime, klass);
        }
        
        static {
            INSTANCE = new PointerFieldAllocator();
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::Pointer" }, parent = "FFI::StructLayout::Field")
    public static final class PointerField extends Field
    {
        public PointerField(final Ruby runtime, final RubyClass klass) {
            super(runtime, klass, PointerFieldIO.INSTANCE);
        }
    }
    
    private static final class FunctionFieldAllocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new FunctionField(runtime, klass);
        }
        
        static {
            INSTANCE = new FunctionFieldAllocator();
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::Function" }, parent = "FFI::StructLayout::Field")
    public static final class FunctionField extends Field
    {
        public FunctionField(final Ruby runtime, final RubyClass klass) {
            super(runtime, klass, FunctionFieldIO.INSTANCE);
        }
        
        @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, required = 3, optional = 1)
        public final IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
            final IRubyObject type = args[2];
            if (!(type instanceof CallbackInfo)) {
                throw context.getRuntime().newTypeError(type, context.getRuntime().fastGetModule("FFI").fastGetClass("Type").fastGetClass("Function"));
            }
            this.init(args, FunctionFieldIO.INSTANCE);
            return this;
        }
    }
    
    private static final class InnerStructFieldAllocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new InnerStructField(runtime, klass);
        }
        
        static {
            INSTANCE = new InnerStructFieldAllocator();
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::InnerStruct" }, parent = "FFI::StructLayout::Field")
    public static final class InnerStructField extends Field
    {
        public InnerStructField(final Ruby runtime, final RubyClass klass) {
            super(runtime, klass, DefaultFieldIO.INSTANCE);
        }
        
        @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, required = 3, optional = 1)
        public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
            final IRubyObject type = args[2];
            if (!(type instanceof StructByValue)) {
                throw context.getRuntime().newTypeError(type, context.getRuntime().fastGetModule("FFI").fastGetClass("Type").fastGetClass("Struct"));
            }
            this.init(args, new InnerStructFieldIO((StructByValue)type));
            return this;
        }
    }
    
    private static final class ArrayFieldAllocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new ArrayField(runtime, klass);
        }
        
        static {
            INSTANCE = new ArrayFieldAllocator();
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::Array" }, parent = "FFI::StructLayout::Field")
    public static final class ArrayField extends Field
    {
        public ArrayField(final Ruby runtime, final RubyClass klass) {
            super(runtime, klass, DefaultFieldIO.INSTANCE);
        }
        
        @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, required = 3, optional = 1)
        public final IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
            final IRubyObject type = args[2];
            if (!(type instanceof Array)) {
                throw context.getRuntime().newTypeError(type, context.getRuntime().fastGetModule("FFI").fastGetClass("Type").fastGetClass("Array"));
            }
            this.init(args, new ArrayFieldIO((Array)type));
            return this;
        }
    }
    
    private static final class MappedFieldAllocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new MappedField(runtime, klass);
        }
        
        static {
            INSTANCE = new MappedFieldAllocator();
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::Mapped" }, parent = "FFI::StructLayout::Field")
    public static final class MappedField extends Field
    {
        public MappedField(final Ruby runtime, final RubyClass klass) {
            super(runtime, klass, DefaultFieldIO.INSTANCE);
        }
        
        @JRubyMethod(required = 4)
        public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
            if (!(args[2] instanceof MappedType)) {
                throw context.getRuntime().newTypeError(args[2], context.getRuntime().fastGetModule("FFI").fastGetClass("Type").fastGetClass("Mapped"));
            }
            if (!(args[3] instanceof Field)) {
                throw context.getRuntime().newTypeError(args[3], context.getRuntime().fastGetModule("FFI").fastGetClass("StructLayout").fastGetClass("Field"));
            }
            this.init(args[0], args[2], args[1], new MappedFieldIO((MappedType)args[2], ((Field)args[3]).getFieldIO()));
            return this;
        }
    }
    
    static class NullStorage implements Storage
    {
        public IRubyObject getCachedValue(final Member member) {
            return null;
        }
        
        public void putCachedValue(final Member member, final IRubyObject value) {
        }
        
        public void putReference(final Member member, final Object value) {
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::ArrayProxy" }, parent = "Object")
    public static class ArrayProxy extends RubyObject
    {
        protected final AbstractMemory ptr;
        final MemoryOp aio;
        protected final Array arrayType;
        
        ArrayProxy(final Ruby runtime, final IRubyObject ptr, final long offset, final Array type, final MemoryOp aio) {
            this(runtime, runtime.fastGetModule("FFI").fastGetClass("StructLayout").fastGetClass("ArrayProxy"), ptr, offset, type, aio);
        }
        
        ArrayProxy(final Ruby runtime, final RubyClass klass, final IRubyObject ptr, final long offset, final Array type, final MemoryOp aio) {
            super(runtime, klass);
            this.ptr = ((AbstractMemory)ptr).slice(runtime, offset, type.getNativeSize());
            this.arrayType = type;
            this.aio = aio;
        }
        
        private final long getOffset(final IRubyObject index) {
            return this.getOffset(Util.int32Value(index));
        }
        
        private final long getOffset(final int index) {
            if (index < 0 || index >= this.arrayType.length()) {
                throw this.getRuntime().newIndexError("index " + index + " out of bounds");
            }
            return index * this.arrayType.getComponentType().getNativeSize();
        }
        
        private IRubyObject get(final ThreadContext context, final int index) {
            return this.aio.get(context, this.ptr, this.getOffset(index));
        }
        
        @JRubyMethod(name = { "[]" })
        public IRubyObject get(final ThreadContext context, final IRubyObject index) {
            return this.aio.get(context, this.ptr, this.getOffset(index));
        }
        
        @JRubyMethod(name = { "[]=" })
        public IRubyObject put(final ThreadContext context, final IRubyObject index, final IRubyObject value) {
            this.aio.put(context, this.ptr, this.getOffset(index), value);
            return value;
        }
        
        @JRubyMethod(name = { "to_a", "to_ary" })
        public IRubyObject get(final ThreadContext context) {
            final IRubyObject[] elems = new IRubyObject[this.arrayType.length()];
            for (int i = 0; i < elems.length; ++i) {
                elems[i] = this.get(context, i);
            }
            return RubyArray.newArrayNoCopy(context.getRuntime(), elems);
        }
        
        @JRubyMethod(name = { "to_ptr" })
        public IRubyObject to_ptr(final ThreadContext context) {
            return this.ptr;
        }
        
        @JRubyMethod(name = { "size" })
        public IRubyObject size(final ThreadContext context) {
            return context.getRuntime().newFixnum(this.arrayType.getNativeSize());
        }
        
        @JRubyMethod(name = { "each" })
        public IRubyObject each(final ThreadContext context, final Block block) {
            if (!block.isGiven()) {
                throw context.getRuntime().newLocalJumpErrorNoBlock();
            }
            for (int i = 0; i < this.arrayType.length(); ++i) {
                block.yield(context, this.get(context, i));
            }
            return this;
        }
    }
    
    @JRubyClass(name = { "FFI::StructLayout::CharArrayProxy" }, parent = "FFI::StructLayout::ArrayProxy")
    public static final class CharArrayProxy extends ArrayProxy
    {
        CharArrayProxy(final Ruby runtime, final IRubyObject ptr, final long offset, final Array type, final MemoryOp aio) {
            super(runtime, runtime.fastGetModule("FFI").fastGetClass("StructLayout").fastGetClass("CharArrayProxy"), ptr, offset, type, aio);
        }
        
        @JRubyMethod(name = { "to_s" })
        public IRubyObject to_s(final ThreadContext context) {
            return MemoryUtil.getTaintedString(context.getRuntime(), this.ptr.getMemoryIO(), 0L, this.arrayType.length());
        }
    }
    
    static final class NumberFieldIO implements FieldIO
    {
        private final MemoryOp op;
        
        NumberFieldIO(final Type type, final ByteOrder order) {
            this.op = MemoryOp.getMemoryOp(type, order);
        }
        
        NumberFieldIO(final MemoryOp op) {
            this.op = op;
        }
        
        public void put(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr, final IRubyObject value) {
            this.op.put(context, ptr, m.offset, value);
        }
        
        public IRubyObject get(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr) {
            return this.op.get(context, ptr, m.offset);
        }
        
        public final boolean isCacheable() {
            return false;
        }
        
        public final boolean isValueReferenceNeeded() {
            return false;
        }
    }
    
    static final class EnumFieldIO implements FieldIO
    {
        private final MemoryOp op;
        
        public EnumFieldIO(final ByteOrder order) {
            this.op = MemoryOp.getMemoryOp(NativeType.INT, order);
        }
        
        public void put(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr, final IRubyObject value) {
            this.op.put(context, ptr, m.offset, m.type.callMethod(context, "find", value));
        }
        
        public IRubyObject get(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr) {
            return m.type.callMethod(context, "find", this.op.get(context, ptr, m.offset));
        }
        
        public final boolean isCacheable() {
            return false;
        }
        
        public final boolean isValueReferenceNeeded() {
            return false;
        }
    }
    
    static final class PointerFieldIO implements FieldIO
    {
        public static final FieldIO INSTANCE;
        
        public void put(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr, final IRubyObject value) {
            if (value instanceof Pointer) {
                ptr.getMemoryIO().putMemoryIO(m.offset, ((Pointer)value).getMemoryIO());
            }
            else if (value instanceof Struct) {
                final MemoryIO mem = ((Struct)value).getMemoryIO();
                if (!(mem instanceof DirectMemoryIO)) {
                    throw context.getRuntime().newArgumentError("Struct memory not backed by a native pointer");
                }
                ptr.getMemoryIO().putMemoryIO(m.offset, mem);
            }
            else if (value instanceof RubyInteger) {
                ptr.getMemoryIO().putAddress(m.offset, Util.int64Value(ptr));
            }
            else if (value.respondsTo("to_ptr")) {
                final IRubyObject addr = value.callMethod(context, "to_ptr");
                if (!(addr instanceof Pointer)) {
                    throw context.getRuntime().newArgumentError("Invalid pointer value");
                }
                ptr.getMemoryIO().putMemoryIO(m.offset, ((Pointer)addr).getMemoryIO());
            }
            else {
                if (!value.isNil()) {
                    throw context.getRuntime().newArgumentError("Invalid pointer value");
                }
                ptr.getMemoryIO().putAddress(m.offset, 0L);
            }
            cache.putReference(m, value);
        }
        
        public IRubyObject get(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr) {
            final DirectMemoryIO memory = ptr.getMemoryIO().getMemoryIO(m.getOffset(ptr));
            final IRubyObject old = cache.getCachedValue(m);
            if (old instanceof Pointer) {
                final MemoryIO oldMemory = ((Pointer)old).getMemoryIO();
                if (memory.equals(oldMemory)) {
                    return old;
                }
            }
            final Pointer retval = new Pointer(context.getRuntime(), memory);
            cache.putCachedValue(m, retval);
            return retval;
        }
        
        public final boolean isCacheable() {
            return true;
        }
        
        public final boolean isValueReferenceNeeded() {
            return true;
        }
        
        static {
            INSTANCE = new PointerFieldIO();
        }
    }
    
    static final class StringFieldIO implements FieldIO
    {
        public static final FieldIO INSTANCE;
        
        public IRubyObject get(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr) {
            final MemoryIO io = ptr.getMemoryIO().getMemoryIO(m.getOffset(ptr));
            if (io == null || io.isNull()) {
                return context.getRuntime().getNil();
            }
            return RubyString.newStringNoCopy(context.getRuntime(), io.getZeroTerminatedByteArray(0L));
        }
        
        public void put(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr, final IRubyObject value) {
            final ByteList bl = value.convertToString().getByteList();
            final MemoryPointer mem = MemoryPointer.allocate(context.getRuntime(), 1, bl.length() + 1, false);
            cache.putReference(m, mem);
            final MemoryIO io = mem.getMemoryIO();
            io.put(0L, bl.getUnsafeBytes(), bl.begin(), bl.length());
            io.putByte(bl.length(), (byte)0);
            ptr.getMemoryIO().putMemoryIO(m.getOffset(ptr), io);
        }
        
        public final boolean isCacheable() {
            return false;
        }
        
        public final boolean isValueReferenceNeeded() {
            return true;
        }
        
        static {
            INSTANCE = new StringFieldIO();
        }
    }
    
    static final class FunctionFieldIO implements FieldIO
    {
        public static final FieldIO INSTANCE;
        
        public void put(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr, final IRubyObject value) {
            if (value.isNil()) {
                ptr.getMemoryIO().putAddress(m.getOffset(ptr), 0L);
                cache.putReference(m, value);
            }
            else {
                final Pointer cb = Factory.getInstance().getCallbackManager().getCallback(context.getRuntime(), (CallbackInfo)m.type, value);
                ptr.getMemoryIO().putMemoryIO(m.getOffset(ptr), cb.getMemoryIO());
                cache.putReference(m, cb);
            }
        }
        
        public IRubyObject get(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr) {
            final long address = ((Pointer)ptr).getMemoryIO().getAddress(m.getOffset(ptr));
            AbstractInvoker fptr = (AbstractInvoker)cache.getCachedValue(m);
            if (fptr != null && fptr.getAddress() == address) {
                return fptr;
            }
            fptr = Factory.getInstance().newFunction(context.getRuntime(), ((Pointer)ptr).getPointer(context.getRuntime(), m.getOffset(ptr)), (CallbackInfo)m.type);
            cache.putCachedValue(m, fptr);
            return fptr;
        }
        
        public final boolean isCacheable() {
            return true;
        }
        
        public final boolean isValueReferenceNeeded() {
            return true;
        }
        
        static {
            INSTANCE = new FunctionFieldIO();
        }
    }
    
    static final class InnerStructFieldIO implements FieldIO
    {
        private final StructByValue sbv;
        
        public InnerStructFieldIO(final StructByValue sbv) {
            this.sbv = sbv;
        }
        
        public void put(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr, final IRubyObject value) {
            throw context.getRuntime().newNotImplementedError("Cannot set Struct fields");
        }
        
        public IRubyObject get(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr) {
            IRubyObject s = cache.getCachedValue(m);
            if (s == null) {
                s = this.sbv.getStructClass().newInstance(context, new IRubyObject[] { ptr.slice(context.getRuntime(), m.getOffset(ptr)) }, Block.NULL_BLOCK);
                cache.putCachedValue(m, s);
            }
            return s;
        }
        
        public final boolean isCacheable() {
            return true;
        }
        
        public final boolean isValueReferenceNeeded() {
            return false;
        }
    }
    
    static final class ArrayFieldIO implements FieldIO
    {
        private final Array arrayType;
        private final MemoryOp op;
        
        public ArrayFieldIO(final Array arrayType) {
            this.arrayType = arrayType;
            this.op = MemoryOp.getMemoryOp(arrayType.getComponentType());
            if (this.op == null) {
                throw arrayType.getRuntime().newNotImplementedError("unsupported array field type: " + arrayType.getComponentType());
            }
        }
        
        public void put(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr, final IRubyObject value) {
            if (this.isCharArray() && value instanceof RubyString) {
                final ByteList bl = value.convertToString().getByteList();
                ptr.getMemoryIO().putZeroTerminatedByteArray(m.offset, bl.getUnsafeBytes(), bl.begin(), Math.min(bl.length(), this.arrayType.length() - 1));
                return;
            }
            throw context.getRuntime().newNotImplementedError("cannot set array field");
        }
        
        public IRubyObject get(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr) {
            IRubyObject s = cache.getCachedValue(m);
            if (s == null) {
                s = (this.isCharArray() ? new CharArrayProxy(context.getRuntime(), ptr, m.offset, this.arrayType, this.op) : new ArrayProxy(context.getRuntime(), ptr, m.offset, this.arrayType, this.op));
                cache.putCachedValue(m, s);
            }
            return s;
        }
        
        private final boolean isCharArray() {
            return this.arrayType.getComponentType().nativeType == NativeType.CHAR || this.arrayType.getComponentType().nativeType == NativeType.UCHAR;
        }
        
        public final boolean isCacheable() {
            return true;
        }
        
        public final boolean isValueReferenceNeeded() {
            return false;
        }
    }
    
    static final class MappedFieldIO implements FieldIO
    {
        private final FieldIO nativeFieldIO;
        private final MappedType mappedType;
        
        public MappedFieldIO(final MappedType mappedType, final FieldIO nativeFieldIO) {
            this.nativeFieldIO = nativeFieldIO;
            this.mappedType = mappedType;
        }
        
        public final boolean isCacheable() {
            return false;
        }
        
        public final boolean isValueReferenceNeeded() {
            return this.nativeFieldIO.isValueReferenceNeeded() || this.mappedType.isReferenceRequired();
        }
        
        public final IRubyObject get(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr) {
            return this.mappedType.fromNative(context, this.nativeFieldIO.get(context, StructLayout.nullStorage, m, ptr));
        }
        
        public void put(final ThreadContext context, final Storage cache, final Member m, final AbstractMemory ptr, final IRubyObject value) {
            final IRubyObject nativeValue = this.mappedType.toNative(context, value);
            this.nativeFieldIO.put(context, cache, m, ptr, nativeValue);
            if (this.isValueReferenceNeeded()) {
                cache.putReference(m, new Object[] { value, nativeValue });
            }
        }
    }
    
    interface FieldIO
    {
        void put(final ThreadContext p0, final Storage p1, final Member p2, final AbstractMemory p3, final IRubyObject p4);
        
        IRubyObject get(final ThreadContext p0, final Storage p1, final Member p2, final AbstractMemory p3);
        
        boolean isCacheable();
        
        boolean isValueReferenceNeeded();
    }
    
    public interface Storage
    {
        IRubyObject getCachedValue(final Member p0);
        
        void putCachedValue(final Member p0, final IRubyObject p1);
        
        void putReference(final Member p0, final Object p1);
    }
}
