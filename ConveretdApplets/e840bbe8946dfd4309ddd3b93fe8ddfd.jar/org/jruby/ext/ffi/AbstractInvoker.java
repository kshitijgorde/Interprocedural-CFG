// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import java.util.Collections;
import java.util.WeakHashMap;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.runtime.Arity;
import org.jruby.internal.runtime.methods.DynamicMethod;
import java.util.Map;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "FFI::AbstractInvoker" }, parent = "Object")
public abstract class AbstractInvoker extends Pointer
{
    static final String CLASS_NAME = "AbstractInvoker";
    private static final Map<DynamicMethod, AbstractInvoker> refmap;
    protected final Arity arity;
    
    public static RubyClass createAbstractInvokerClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = module.defineClassUnder("AbstractInvoker", module.fastGetClass("Pointer"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        result.defineAnnotatedMethods(AbstractInvoker.class);
        result.defineAnnotatedConstants(AbstractInvoker.class);
        return result;
    }
    
    protected AbstractInvoker(final Ruby runtime, final RubyClass klass, final int arity, final DirectMemoryIO io) {
        super(runtime, klass, io, 0L);
        this.arity = Arity.fixed(arity);
    }
    
    @JRubyMethod(name = { "attach" })
    public IRubyObject attach(final ThreadContext context, final IRubyObject obj, final IRubyObject methodName) {
        final DynamicMethod m = this.createDynamicMethod(obj.getSingletonClass());
        obj.getSingletonClass().addMethod(methodName.asJavaString(), m);
        if (obj instanceof RubyModule) {
            ((RubyModule)obj).addMethod(methodName.asJavaString(), m);
        }
        AbstractInvoker.refmap.put(m, this);
        return this;
    }
    
    protected abstract DynamicMethod createDynamicMethod(final RubyModule p0);
    
    public final Arity getArity() {
        return this.arity;
    }
    
    static {
        refmap = Collections.synchronizedMap(new WeakHashMap<DynamicMethod, AbstractInvoker>());
    }
}
