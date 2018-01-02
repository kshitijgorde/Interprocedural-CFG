// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.ObjectAllocator;
import java.nio.ByteOrder;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "FFI::Struct" }, parent = "Object")
public class Struct extends RubyObject implements StructLayout.Storage
{
    private final StructLayout layout;
    private final Object[] referenceCache;
    private AbstractMemory memory;
    private IRubyObject[] valueCache;
    
    public static RubyClass createStructClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = runtime.defineClassUnder("Struct", runtime.getObject(), Allocator.INSTANCE, module);
        result.defineAnnotatedMethods(Struct.class);
        result.defineAnnotatedConstants(Struct.class);
        return result;
    }
    
    Struct(final Ruby runtime) {
        this(runtime, runtime.fastGetModule("FFI").fastGetClass("Struct"));
    }
    
    Struct(final Ruby runtime, final RubyClass klass) {
        this(runtime, klass, getStructLayout(runtime, klass), null);
    }
    
    Struct(final Ruby runtime, final RubyClass klass, final StructLayout layout, final IRubyObject memory) {
        super(runtime, klass);
        this.layout = layout;
        if (memory != null && !(memory instanceof AbstractMemory)) {
            throw runtime.newTypeError("wrong argument type " + memory.getMetaClass().getName() + " (expected Pointer or Buffer)");
        }
        this.memory = (AbstractMemory)memory;
        this.referenceCache = new IRubyObject[layout.getReferenceFieldCount()];
    }
    
    static final boolean isStruct(final Ruby runtime, final RubyClass klass) {
        return klass.isKindOfModule(runtime.fastGetModule("FFI").getClass("Struct"));
    }
    
    static final int getStructSize(final Ruby runtime, final IRubyObject structClass) {
        return getStructLayout(runtime, structClass).getSize();
    }
    
    static final StructLayout getStructLayout(final Ruby runtime, final IRubyObject structClass) {
        if (!(structClass instanceof RubyClass)) {
            throw runtime.newTypeError("wrong argument type " + structClass.getMetaClass().getName() + " (expected subclass of Struct");
        }
        try {
            final StructLayout layout = (StructLayout)((RubyClass)structClass).fastGetInstanceVariable("@layout");
            if (layout == null) {
                throw runtime.newRuntimeError("No struct layout set for " + ((RubyClass)structClass).getName());
            }
            return layout;
        }
        catch (RaiseException ex) {
            throw runtime.newRuntimeError("No layout set for struct " + ((RubyClass)structClass).getName());
        }
        catch (ClassCastException ex2) {
            throw runtime.newRuntimeError("Invalid layout set for struct " + ((RubyClass)structClass).getName());
        }
    }
    
    static final Struct newStruct(final Ruby runtime, final RubyClass klass, final IRubyObject ptr) {
        return new Struct(runtime, klass, getStructLayout(runtime, klass), ptr);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context) {
        this.memory = MemoryPointer.allocate(context.getRuntime(), this.layout.getSize(), 1, true);
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject ptr) {
        if (!(ptr instanceof AbstractMemory)) {
            throw context.getRuntime().newTypeError("wrong argument type " + ptr.getMetaClass().getName() + " (expected Pointer or Buffer)");
        }
        if (((AbstractMemory)ptr).getSize() < this.layout.getSize()) {
            throw context.getRuntime().newArgumentError("memory object has insufficient space for " + this.getMetaClass().getName());
        }
        this.memory = (AbstractMemory)ptr;
        return this;
    }
    
    @JRubyMethod(name = { "initialize_copy" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize_copy(final ThreadContext context, final IRubyObject other) {
        if (other == this) {
            return this;
        }
        if (!(other instanceof Struct)) {
            throw context.getRuntime().newTypeError("not an instance of Struct");
        }
        final Struct orig = (Struct)other;
        this.memory = (AbstractMemory)orig.getMemory().slice(context.getRuntime(), 0L, this.layout.getSize()).dup();
        System.arraycopy(orig.referenceCache, 0, this.referenceCache, 0, this.referenceCache.length);
        return this;
    }
    
    private static final Struct allocateStruct(final ThreadContext context, final IRubyObject klass, final int flags) {
        final Ruby runtime = context.getRuntime();
        final StructLayout layout = getStructLayout(runtime, klass);
        return new Struct(runtime, (RubyClass)klass, layout, new Buffer(runtime, layout.getSize(), flags));
    }
    
    @JRubyMethod(name = { "new_in", "alloc_in" }, meta = true)
    public static IRubyObject allocateIn(final ThreadContext context, final IRubyObject klass) {
        return allocateStruct(context, klass, 1);
    }
    
    @JRubyMethod(name = { "new_in", "alloc_in" }, meta = true)
    public static IRubyObject allocateIn(final ThreadContext context, final IRubyObject klass, final IRubyObject clearArg) {
        return allocateStruct(context, klass, 1);
    }
    
    @JRubyMethod(name = { "new_out", "alloc_out" }, meta = true)
    public static IRubyObject allocateOut(final ThreadContext context, final IRubyObject klass) {
        return allocateStruct(context, klass, 2);
    }
    
    @JRubyMethod(name = { "new_out", "alloc_out" }, meta = true)
    public static IRubyObject allocateOut(final ThreadContext context, final IRubyObject klass, final IRubyObject clearArg) {
        return allocateStruct(context, klass, 2);
    }
    
    @JRubyMethod(name = { "new_inout", "alloc_inout" }, meta = true)
    public static IRubyObject allocateInOut(final ThreadContext context, final IRubyObject klass) {
        return allocateStruct(context, klass, 3);
    }
    
    @JRubyMethod(name = { "new_inout", "alloc_inout" }, meta = true)
    public static IRubyObject allocateInOut(final ThreadContext context, final IRubyObject klass, final IRubyObject clearArg) {
        return allocateStruct(context, klass, 3);
    }
    
    @JRubyMethod(name = { "[]" })
    public IRubyObject getFieldValue(final ThreadContext context, final IRubyObject fieldName) {
        return this.layout.getValue(context, fieldName, this, this.getMemory());
    }
    
    @JRubyMethod(name = { "[]=" })
    public IRubyObject setFieldValue(final ThreadContext context, final IRubyObject fieldName, final IRubyObject fieldValue) {
        this.layout.putValue(context, fieldName, this, this.getMemory(), fieldValue);
        return fieldValue;
    }
    
    @JRubyMethod(name = { "cspec", "layout" })
    public IRubyObject getLayout(final ThreadContext context) {
        return this.layout;
    }
    
    @JRubyMethod(name = { "pointer" })
    public IRubyObject pointer(final ThreadContext context) {
        return this.getMemory();
    }
    
    @JRubyMethod(name = { "members" })
    public IRubyObject members(final ThreadContext context) {
        return this.layout.members(context);
    }
    
    @JRubyMethod(name = { "null?" })
    public IRubyObject null_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.getMemory().getMemoryIO().isNull());
    }
    
    @JRubyMethod(name = { "order" }, required = 0)
    public final IRubyObject order(final ThreadContext context) {
        return context.getRuntime().newSymbol(this.getMemoryIO().order().equals(ByteOrder.LITTLE_ENDIAN) ? "little" : "big");
    }
    
    @JRubyMethod(name = { "order" }, required = 1)
    public final IRubyObject order(final ThreadContext context, final IRubyObject byte_order) {
        final ByteOrder order = Util.parseByteOrder(context.getRuntime(), byte_order);
        return new Struct(context.getRuntime(), this.getMetaClass(), this.layout, this.getMemory().order(context.getRuntime(), order));
    }
    
    public final AbstractMemory getMemory() {
        return (this.memory != null) ? this.memory : (this.memory = MemoryPointer.allocate(this.getRuntime(), this.layout.getSize(), 1, true));
    }
    
    final MemoryIO getMemoryIO() {
        return this.getMemory().getMemoryIO();
    }
    
    public final IRubyObject getCachedValue(final StructLayout.Member member) {
        return (this.valueCache != null) ? this.valueCache[this.layout.getCacheableFieldIndex(member)] : null;
    }
    
    public final void putCachedValue(final StructLayout.Member member, final IRubyObject value) {
        if (this.valueCache == null) {
            this.valueCache = new IRubyObject[this.layout.getCacheableFieldCount()];
        }
        this.valueCache[this.layout.getCacheableFieldIndex(member)] = value;
    }
    
    public void putReference(final StructLayout.Member member, final IRubyObject value) {
        this.referenceCache[this.layout.getReferenceFieldIndex(member)] = value;
    }
    
    public void putReference(final StructLayout.Member member, final Object value) {
        this.referenceCache[this.layout.getReferenceFieldIndex(member)] = value;
    }
    
    private static final class Allocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new Struct(runtime, klass);
        }
        
        static {
            INSTANCE = new Allocator();
        }
    }
}
