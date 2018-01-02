// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.RubyException;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.Visibility;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Block;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import java.lang.ref.WeakReference;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "WeakRef" }, parent = "Delegator")
public class WeakRef extends RubyObject
{
    private WeakReference<IRubyObject> ref;
    public static final ObjectAllocator WEAKREF_ALLOCATOR;
    
    public WeakRef(final Ruby runtime, final RubyClass klazz) {
        super(runtime, klazz);
    }
    
    @JRubyMethod(name = { "__getobj__" })
    public IRubyObject getobj() {
        final IRubyObject obj = this.ref.get();
        if (obj == null) {
            throw this.newRefError("Illegal Reference - probably recycled");
        }
        return obj;
    }
    
    @JRubyMethod(name = { "__setobj__" })
    public IRubyObject setobj(final IRubyObject obj) {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "new" }, required = 1, meta = true)
    public static IRubyObject newInstance(final IRubyObject clazz, final IRubyObject arg) {
        final WeakRef weakRef = (WeakRef)((RubyClass)clazz).allocate();
        weakRef.callInit(new IRubyObject[] { arg }, Block.NULL_BLOCK);
        return weakRef;
    }
    
    @JRubyMethod(frame = true, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject obj) {
        this.ref = new WeakReference<IRubyObject>(obj);
        return RuntimeHelpers.invokeSuper(context, this, obj, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(name = { "weakref_alive?" })
    public IRubyObject weakref_alive_p() {
        return (this.ref.get() != null) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    private RaiseException newRefError(final String message) {
        final Ruby runtime = this.getRuntime();
        final ThreadContext context = runtime.getCurrentContext();
        final RubyException exception = (RubyException)runtime.getClass("RefError").newInstance(context, new IRubyObject[] { runtime.newString(message) }, Block.NULL_BLOCK);
        final RaiseException re = new RaiseException(exception);
        return re;
    }
    
    static {
        WEAKREF_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new WeakRef(runtime, klazz);
            }
        };
    }
    
    @JRubyClass(name = { "RefError" }, parent = "StandardError")
    public static class RefError
    {
    }
}
