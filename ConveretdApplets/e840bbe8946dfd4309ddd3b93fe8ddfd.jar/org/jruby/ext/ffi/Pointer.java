// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyString;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyNumeric;
import org.jruby.runtime.ThreadContext;
import java.nio.ByteOrder;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "FFI::Pointer" }, parent = "AbstractMemory")
public class Pointer extends AbstractMemory
{
    public static RubyClass createPointerClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("Pointer", module.getClass("AbstractMemory"), PointerAllocator.INSTANCE);
        result.defineAnnotatedMethods(Pointer.class);
        result.defineAnnotatedConstants(Pointer.class);
        module.defineClassUnder("NullPointerError", runtime.getRuntimeError(), runtime.getRuntimeError().getAllocator());
        result.fastSetConstant("NULL", new Pointer(runtime, result, new NullMemoryIO(runtime)));
        return result;
    }
    
    public static final Pointer getNull(final Ruby runtime) {
        return (Pointer)runtime.fastGetModule("FFI").fastGetClass("Pointer").fastGetConstant("NULL");
    }
    
    Pointer(final Ruby runtime, final RubyClass klazz) {
        super(runtime, klazz, new NullMemoryIO(runtime), 0L);
    }
    
    public Pointer(final Ruby runtime, final DirectMemoryIO io) {
        this(runtime, getPointerClass(runtime), io);
    }
    
    public Pointer(final Ruby runtime, final DirectMemoryIO io, final long size, final int typeSize) {
        this(runtime, getPointerClass(runtime), io, size, typeSize);
    }
    
    protected Pointer(final Ruby runtime, final RubyClass klass, final DirectMemoryIO io) {
        super(runtime, klass, io, Long.MAX_VALUE);
    }
    
    protected Pointer(final Ruby runtime, final RubyClass klass, final DirectMemoryIO io, final long size) {
        super(runtime, klass, io, size);
    }
    
    protected Pointer(final Ruby runtime, final RubyClass klass, final DirectMemoryIO io, final long size, final int typeSize) {
        super(runtime, klass, io, size, typeSize);
    }
    
    public static final RubyClass getPointerClass(final Ruby runtime) {
        return runtime.fastGetModule("FFI").fastGetClass("Pointer");
    }
    
    public final AbstractMemory order(final Ruby runtime, final ByteOrder order) {
        return new Pointer(runtime, order.equals(this.getMemoryIO().order()) ? ((DirectMemoryIO)this.getMemoryIO()) : new SwappedMemoryIO(runtime, this.getMemoryIO()), this.size, this.typeSize);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject address) {
        this.io = ((address instanceof Pointer) ? ((Pointer)address).getMemoryIO() : Factory.getInstance().wrapDirectMemory(context.getRuntime(), RubyNumeric.num2long(address)));
        this.size = Long.MAX_VALUE;
        this.typeSize = 1;
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject type, final IRubyObject address) {
        this.io = ((address instanceof Pointer) ? ((Pointer)address).getMemoryIO() : Factory.getInstance().wrapDirectMemory(context.getRuntime(), RubyNumeric.num2long(address)));
        this.size = Long.MAX_VALUE;
        this.typeSize = AbstractMemory.calculateTypeSize(context, type);
        return this;
    }
    
    @JRubyMethod(required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize_copy(final ThreadContext context, final IRubyObject other) {
        if (this == other) {
            return this;
        }
        final Pointer orig = (Pointer)other;
        this.typeSize = orig.typeSize;
        this.size = orig.size;
        this.setMemoryIO(orig.getMemoryIO().dup());
        return this;
    }
    
    @JRubyMethod(name = { "null?" })
    public IRubyObject null_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.getMemoryIO().isNull());
    }
    
    @JRubyMethod(name = { "to_s", "inspect" }, optional = 1)
    public IRubyObject to_s(final ThreadContext context, final IRubyObject[] args) {
        final String s = (this.size != Long.MAX_VALUE) ? String.format("#<%s address=0x%x size=%s>", this.getMetaClass().getName(), this.getAddress(), this.size) : String.format("#<%s address=0x%x>", this.getMetaClass().getName(), this.getAddress());
        return RubyString.newString(context.getRuntime(), s);
    }
    
    @JRubyMethod(name = { "address", "to_i" })
    public IRubyObject address(final ThreadContext context) {
        return context.getRuntime().newFixnum(this.getAddress());
    }
    
    public final long getAddress() {
        return ((DirectMemoryIO)this.getMemoryIO()).getAddress();
    }
    
    protected AbstractMemory slice(final Ruby runtime, final long offset) {
        return new Pointer(runtime, getPointerClass(runtime), (DirectMemoryIO)this.getMemoryIO().slice(offset), (this.size == Long.MAX_VALUE) ? Long.MAX_VALUE : (this.size - offset), this.typeSize);
    }
    
    protected AbstractMemory slice(final Ruby runtime, final long offset, final long size) {
        return new Pointer(runtime, getPointerClass(runtime), (DirectMemoryIO)this.getMemoryIO().slice(offset, size), size, this.typeSize);
    }
    
    protected Pointer getPointer(final Ruby runtime, final long offset) {
        return new Pointer(runtime, getPointerClass(runtime), this.getMemoryIO().getMemoryIO(offset), Long.MAX_VALUE);
    }
    
    private static final class PointerAllocator implements ObjectAllocator
    {
        static final ObjectAllocator INSTANCE;
        
        public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
            return new Pointer(runtime, klazz);
        }
        
        static {
            INSTANCE = new PointerAllocator();
        }
    }
}
