// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.ObjectAllocator;
import java.nio.ByteOrder;
import org.jruby.RubyString;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyNumeric;
import org.jruby.RubyFixnum;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "FFI::Buffer" }, parent = "FFI::AbstractMemory")
public final class Buffer extends AbstractMemory
{
    public static final int IN = 1;
    public static final int OUT = 2;
    private int inout;
    
    public static RubyClass createBufferClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("Buffer", module.getClass("AbstractMemory"), BufferAllocator.INSTANCE);
        result.defineAnnotatedMethods(Buffer.class);
        result.defineAnnotatedConstants(Buffer.class);
        return result;
    }
    
    public Buffer(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass, new ArrayMemoryIO(runtime, 0), 0L, 0);
        this.inout = 3;
    }
    
    public Buffer(final Ruby runtime, final int size) {
        this(runtime, size, 3);
    }
    
    public Buffer(final Ruby runtime, final int size, final int flags) {
        this(runtime, runtime.fastGetModule("FFI").fastGetClass("Buffer"), new ArrayMemoryIO(runtime, size), size, 1, flags);
    }
    
    public Buffer(final Ruby runtime, final byte[] data, final int offset, final int size) {
        this(runtime, runtime.fastGetModule("FFI").fastGetClass("Buffer"), new ArrayMemoryIO(runtime, data, offset, size), size, 1, 3);
    }
    
    private Buffer(final Ruby runtime, final IRubyObject klass, final MemoryIO io, final long size, final int typeSize, final int inout) {
        super(runtime, (RubyClass)klass, io, size, typeSize);
        this.inout = inout;
    }
    
    private static final int getCount(final IRubyObject countArg) {
        return (countArg instanceof RubyFixnum) ? RubyNumeric.fix2int(countArg) : 1;
    }
    
    private static Buffer allocate(final ThreadContext context, final IRubyObject recv, final IRubyObject sizeArg, final int count, final int flags) {
        final int typeSize = AbstractMemory.calculateTypeSize(context, sizeArg);
        final int total = typeSize * count;
        return new Buffer(context.getRuntime(), recv, new ArrayMemoryIO(context.getRuntime(), total), total, typeSize, flags);
    }
    
    private IRubyObject init(final ThreadContext context, final IRubyObject rbTypeSize, final int count, final int flags) {
        this.typeSize = AbstractMemory.calculateTypeSize(context, rbTypeSize);
        this.size = this.typeSize * count;
        this.inout = flags;
        this.setMemoryIO(new ArrayMemoryIO(context.getRuntime(), (int)this.size));
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject sizeArg) {
        return (sizeArg instanceof RubyFixnum) ? this.init(context, RubyFixnum.one(context.getRuntime()), RubyNumeric.fix2int(sizeArg), 3) : this.init(context, sizeArg, 1, 3);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject sizeArg, final IRubyObject arg2) {
        return this.init(context, sizeArg, getCount(arg2), 3);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject sizeArg, final IRubyObject countArg, final IRubyObject clearArg) {
        return this.init(context, sizeArg, RubyNumeric.fix2int(countArg), 3);
    }
    
    @JRubyMethod(required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize_copy(final ThreadContext context, final IRubyObject other) {
        if (this == other) {
            return this;
        }
        final Buffer orig = (Buffer)other;
        this.typeSize = orig.typeSize;
        this.size = orig.size;
        this.inout = orig.inout;
        this.setMemoryIO(orig.getMemoryIO().dup());
        return this;
    }
    
    @JRubyMethod(name = { "alloc_inout", "__alloc_inout" }, meta = true)
    public static Buffer allocateInOut(final ThreadContext context, final IRubyObject recv, final IRubyObject sizeArg) {
        return allocate(context, recv, sizeArg, 1, 3);
    }
    
    @JRubyMethod(name = { "alloc_inout", "__alloc_inout" }, meta = true)
    public static Buffer allocateInOut(final ThreadContext context, final IRubyObject recv, final IRubyObject sizeArg, final IRubyObject arg2) {
        return allocate(context, recv, sizeArg, getCount(arg2), 3);
    }
    
    @JRubyMethod(name = { "alloc_inout", "__alloc_inout" }, meta = true)
    public static Buffer allocateInOut(final ThreadContext context, final IRubyObject recv, final IRubyObject sizeArg, final IRubyObject countArg, final IRubyObject clearArg) {
        return allocate(context, recv, sizeArg, RubyNumeric.fix2int(countArg), 3);
    }
    
    @JRubyMethod(name = { "new_in", "alloc_in", "__alloc_in" }, meta = true)
    public static Buffer allocateInput(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        return allocate(context, recv, arg, 1, 1);
    }
    
    @JRubyMethod(name = { "new_in", "alloc_in", "__alloc_in" }, meta = true)
    public static Buffer allocateInput(final ThreadContext context, final IRubyObject recv, final IRubyObject sizeArg, final IRubyObject arg2) {
        return allocate(context, recv, sizeArg, getCount(arg2), 1);
    }
    
    @JRubyMethod(name = { "new_in", "alloc_in", "__alloc_in" }, meta = true)
    public static Buffer allocateInput(final ThreadContext context, final IRubyObject recv, final IRubyObject sizeArg, final IRubyObject countArg, final IRubyObject clearArg) {
        return allocate(context, recv, sizeArg, RubyNumeric.fix2int(countArg), 1);
    }
    
    @JRubyMethod(name = { "new_out", "alloc_out", "__alloc_out" }, meta = true)
    public static Buffer allocateOutput(final ThreadContext context, final IRubyObject recv, final IRubyObject sizeArg) {
        return allocate(context, recv, sizeArg, 1, 2);
    }
    
    @JRubyMethod(name = { "new_out", "alloc_out", "__alloc_out" }, meta = true)
    public static Buffer allocateOutput(final ThreadContext context, final IRubyObject recv, final IRubyObject sizeArg, final IRubyObject arg2) {
        return allocate(context, recv, sizeArg, getCount(arg2), 2);
    }
    
    @JRubyMethod(name = { "new_out", "alloc_out", "__alloc_out" }, meta = true)
    public static Buffer allocateOutput(final ThreadContext context, final IRubyObject recv, final IRubyObject sizeArg, final IRubyObject countArg, final IRubyObject clearArg) {
        return allocate(context, recv, sizeArg, RubyNumeric.fix2int(countArg), 2);
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect(final ThreadContext context) {
        return RubyString.newString(context.getRuntime(), String.format("#<Buffer size=%d>", this.size));
    }
    
    public final AbstractMemory order(final Ruby runtime, final ByteOrder order) {
        return new Buffer(runtime, this.getMetaClass(), order.equals(this.getMemoryIO().order()) ? this.getMemoryIO() : new SwappedMemoryIO(runtime, this.getMemoryIO()), this.size, this.typeSize, this.inout);
    }
    
    ArrayMemoryIO getArrayMemoryIO() {
        return (ArrayMemoryIO)this.getMemoryIO();
    }
    
    protected AbstractMemory slice(final Ruby runtime, final long offset) {
        return new Buffer(runtime, this.getMetaClass(), this.io.slice(offset), this.size - offset, this.typeSize, this.inout);
    }
    
    protected AbstractMemory slice(final Ruby runtime, final long offset, final long size) {
        return new Buffer(runtime, this.getMetaClass(), this.io.slice(offset, size), size, this.typeSize, this.inout);
    }
    
    protected Pointer getPointer(final Ruby runtime, final long offset) {
        return new Pointer(runtime, this.getMemoryIO().getMemoryIO(offset));
    }
    
    public int getInOutFlags() {
        return this.inout;
    }
    
    private static final class BufferAllocator implements ObjectAllocator
    {
        static final ObjectAllocator INSTANCE;
        
        public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
            return new Buffer(runtime, klazz);
        }
        
        static {
            INSTANCE = new BufferAllocator();
        }
    }
}
