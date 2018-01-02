// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.util.PhantomReferenceReaper;
import org.jruby.runtime.ObjectAllocator;
import java.util.concurrent.ConcurrentHashMap;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import java.util.concurrent.ConcurrentMap;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "FFI::AutoPointer" }, parent = "FFI::Pointer")
public final class AutoPointer extends Pointer
{
    static final String AUTOPTR_CLASS_NAME = "AutoPointer";
    private static final ConcurrentMap<Reaper, Boolean> referenceSet;
    private Pointer pointer;
    private transient volatile Reaper reaper;
    
    public static RubyClass createAutoPointerClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("AutoPointer", module.getClass("Pointer"), AutoPointerAllocator.INSTANCE);
        result.defineAnnotatedMethods(AutoPointer.class);
        result.defineAnnotatedConstants(AutoPointer.class);
        return result;
    }
    
    private AutoPointer(final Ruby runtime, final RubyClass klazz) {
        super(runtime, klazz, new NullMemoryIO(runtime));
    }
    
    private static final void checkPointer(final Ruby runtime, final IRubyObject ptr) {
        if (!(ptr instanceof Pointer)) {
            throw runtime.newTypeError(ptr, runtime.fastGetModule("FFI").fastGetClass("Pointer"));
        }
        if (ptr instanceof MemoryPointer || ptr instanceof AutoPointer) {
            throw runtime.newTypeError("Cannot use AutoPointer with MemoryPointer or AutoPointer instances");
        }
    }
    
    @JRubyMethod(name = { "from_native" }, meta = true)
    public static IRubyObject from_native(final ThreadContext context, final IRubyObject recv, final IRubyObject value, final IRubyObject ctx) {
        return ((RubyClass)recv).newInstance(context, new IRubyObject[] { value }, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public final IRubyObject initialize(final ThreadContext context, final IRubyObject pointerArg) {
        final Ruby runtime = context.getRuntime();
        checkPointer(runtime, pointerArg);
        if (!this.getMetaClass().respondsTo("release")) {
            throw runtime.newRuntimeError("No release method defined");
        }
        this.setMemoryIO(((Pointer)pointerArg).getMemoryIO());
        this.pointer = (Pointer)pointerArg;
        AutoPointer.referenceSet.put(this.reaper = new Reaper(this, this.pointer, (IRubyObject)this.getMetaClass(), "release"), Boolean.TRUE);
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE)
    public final IRubyObject initialize(final ThreadContext context, final IRubyObject pointerArg, final IRubyObject releaser) {
        checkPointer(context.getRuntime(), pointerArg);
        this.setMemoryIO(((Pointer)pointerArg).getMemoryIO());
        this.pointer = (Pointer)pointerArg;
        AutoPointer.referenceSet.put(this.reaper = new Reaper(this, this.pointer, releaser, "call"), Boolean.TRUE);
        return this;
    }
    
    @JRubyMethod(name = { "free" })
    public final IRubyObject free(final ThreadContext context) {
        final Reaper r = this.reaper;
        if (r == null) {
            throw context.getRuntime().newRuntimeError("pointer already freed");
        }
        r.release(context);
        this.reaper = null;
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "autorelease=" })
    public final IRubyObject autorelease(final ThreadContext context, final IRubyObject autorelease) {
        final Reaper r = this.reaper;
        if (r == null) {
            throw context.getRuntime().newRuntimeError("pointer already freed");
        }
        r.autorelease(autorelease.isTrue());
        return context.getRuntime().getNil();
    }
    
    static {
        referenceSet = new ConcurrentHashMap<Reaper, Boolean>();
    }
    
    private static final class AutoPointerAllocator implements ObjectAllocator
    {
        static final ObjectAllocator INSTANCE;
        
        public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
            return new AutoPointer(runtime, klazz, (AutoPointer$1)null);
        }
        
        static {
            INSTANCE = new AutoPointerAllocator();
        }
    }
    
    private static final class Reaper extends PhantomReferenceReaper<AutoPointer> implements Runnable
    {
        private final Pointer pointer;
        private final IRubyObject proc;
        private final String methodName;
        
        private Reaper(final AutoPointer pointer, final Pointer ptr, final IRubyObject proc, final String methodName) {
            super(pointer);
            this.pointer = ptr;
            this.proc = proc;
            this.methodName = methodName;
        }
        
        public final void release(final ThreadContext context) {
            AutoPointer.referenceSet.remove(this);
            this.proc.callMethod(context, this.methodName, this.pointer);
        }
        
        public final void autorelease(final boolean autorelease) {
            if (!autorelease) {
                AutoPointer.referenceSet.remove(this);
            }
            else {
                AutoPointer.referenceSet.putIfAbsent(this, Boolean.TRUE);
            }
        }
        
        public void run() {
            this.release(this.pointer.getRuntime().getCurrentContext());
        }
    }
}
