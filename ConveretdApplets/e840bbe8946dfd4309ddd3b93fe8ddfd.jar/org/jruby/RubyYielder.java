// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Enumerator::Yielder" })
public class RubyYielder extends RubyObject
{
    private RubyProc proc;
    private static ObjectAllocator YIELDER_ALLOCATOR;
    
    public static RubyClass createYielderClass(final Ruby runtime) {
        final RubyClass yielderc = runtime.defineClassUnder("Yielder", runtime.getObject(), RubyYielder.YIELDER_ALLOCATOR, runtime.getEnumerator());
        runtime.setYielder(yielderc);
        yielderc.index = 25;
        yielderc.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyYielder;
            }
        };
        yielderc.defineAnnotatedMethods(RubyYielder.class);
        return yielderc;
    }
    
    public RubyYielder(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
    }
    
    public RubyYielder(final Ruby runtime) {
        super(runtime, runtime.getYielder());
    }
    
    private void checkInit() {
        if (this.proc == null) {
            throw this.getRuntime().newArgumentError("uninitializer yielder");
        }
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            throw runtime.newLocalJumpErrorNoBlock();
        }
        this.proc = runtime.newProc(Block.Type.PROC, block);
        return this;
    }
    
    @JRubyMethod(name = { "yield", "<<" }, rest = true)
    public IRubyObject yield(final ThreadContext context, final IRubyObject[] args) {
        this.checkInit();
        this.proc.call(context, args);
        return this;
    }
    
    static {
        RubyYielder.YIELDER_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyYielder(runtime, klass);
            }
        };
    }
}
