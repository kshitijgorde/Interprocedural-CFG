// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyNumeric;
import org.jruby.RubyFixnum;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "FFI::MemoryPointer" }, parent = "FFI::Pointer")
public final class MemoryPointer extends Pointer
{
    public static RubyClass createMemoryPointerClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("MemoryPointer", module.fastGetClass("Pointer"), MemoryPointerAllocator.INSTANCE);
        result.defineAnnotatedMethods(MemoryPointer.class);
        result.defineAnnotatedConstants(MemoryPointer.class);
        return result;
    }
    
    private MemoryPointer(final Ruby runtime, final IRubyObject klass) {
        super(runtime, (RubyClass)klass);
    }
    
    private MemoryPointer(final Ruby runtime, final IRubyObject klass, final DirectMemoryIO io, final long total, final int typeSize) {
        super(runtime, (RubyClass)klass, io, total, typeSize);
    }
    
    private final IRubyObject init(final ThreadContext context, final IRubyObject rbTypeSize, final int count, final int align, final boolean clear, final Block block) {
        this.typeSize = AbstractMemory.calculateTypeSize(context, rbTypeSize);
        this.size = this.typeSize * count;
        if (this.size < 0L) {
            throw context.getRuntime().newArgumentError(String.format("Negative size (%d objects of %d size)", count, this.typeSize));
        }
        this.setMemoryIO(Factory.getInstance().allocateDirectMemory(context.getRuntime(), (this.size > 0L) ? ((int)this.size) : 1, align, clear));
        if (this.getMemoryIO() == null) {
            final Ruby runtime = context.getRuntime();
            throw new RaiseException(runtime, runtime.getNoMemoryError(), String.format("Failed to allocate %d objects of %d bytes", this.typeSize, count), true);
        }
        if (block.isGiven()) {
            try {
                return block.yield(context, this);
            }
            finally {
                ((AllocatedDirectMemoryIO)this.getMemoryIO()).free();
                this.setMemoryIO(new FreedMemoryIO(context.getRuntime()));
            }
        }
        return this;
    }
    
    static final MemoryPointer allocate(final Ruby runtime, final int typeSize, final int count, final boolean clear) {
        final int total = typeSize * count;
        final AllocatedDirectMemoryIO io = Factory.getInstance().allocateDirectMemory(runtime, (total > 0) ? total : 1, clear);
        if (io == null) {
            throw new RaiseException(runtime, runtime.getNoMemoryError(), String.format("Failed to allocate %d objects of %d bytes", typeSize, count), true);
        }
        return new MemoryPointer(runtime, (IRubyObject)runtime.fastGetModule("FFI").fastGetClass("MemoryPointer"), io, total, typeSize);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public final IRubyObject initialize(final ThreadContext context, final IRubyObject sizeArg, final Block block) {
        return (sizeArg instanceof RubyFixnum) ? this.init(context, RubyFixnum.one(context.getRuntime()), RubyNumeric.fix2int(sizeArg), 1, true, block) : this.init(context, sizeArg, 1, 1, true, block);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public final IRubyObject initialize(final ThreadContext context, final IRubyObject sizeArg, final IRubyObject count, final Block block) {
        return this.init(context, sizeArg, RubyNumeric.fix2int(count), 1, true, block);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public final IRubyObject initialize(final ThreadContext context, final IRubyObject sizeArg, final IRubyObject count, final IRubyObject clear, final Block block) {
        return this.init(context, sizeArg, RubyNumeric.fix2int(count), 1, clear.isTrue(), block);
    }
    
    public final String toString() {
        return String.format("MemoryPointer[address=%#x, size=%d]", this.getAddress(), this.size);
    }
    
    @JRubyMethod(name = { "free" })
    public final IRubyObject free(final ThreadContext context) {
        ((AllocatedDirectMemoryIO)this.getMemoryIO()).free();
        this.setMemoryIO(new FreedMemoryIO(context.getRuntime()));
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "autorelease=" }, required = 1)
    public final IRubyObject autorelease(final ThreadContext context, final IRubyObject release) {
        ((AllocatedDirectMemoryIO)this.getMemoryIO()).setAutoRelease(release.isTrue());
        return context.getRuntime().getNil();
    }
    
    private static final class MemoryPointerAllocator implements ObjectAllocator
    {
        static final ObjectAllocator INSTANCE;
        
        public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
            return new MemoryPointer(runtime, klazz, (MemoryPointer$1)null);
        }
        
        static {
            INSTANCE = new MemoryPointerAllocator();
        }
    }
}
