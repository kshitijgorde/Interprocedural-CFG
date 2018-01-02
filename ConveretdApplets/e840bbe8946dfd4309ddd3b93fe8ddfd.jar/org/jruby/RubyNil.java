// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "NilClass" })
public class RubyNil extends RubyObject
{
    public static final ObjectAllocator NIL_ALLOCATOR;
    
    public RubyNil(final Ruby runtime) {
        super(runtime, runtime.getNilClass(), false, false);
        this.flags |= 0x3;
    }
    
    public static RubyClass createNilClass(final Ruby runtime) {
        final RubyClass nilClass = runtime.defineClass("NilClass", runtime.getObject(), RubyNil.NIL_ALLOCATOR);
        runtime.setNilClass(nilClass);
        nilClass.index = 5;
        nilClass.setReifiedClass(RubyNil.class);
        nilClass.defineAnnotatedMethods(RubyNil.class);
        nilClass.getMetaClass().undefineMethod("new");
        return nilClass;
    }
    
    public int getNativeTypeIndex() {
        return 5;
    }
    
    public boolean isImmediate() {
        return true;
    }
    
    public RubyClass getSingletonClass() {
        return this.metaClass;
    }
    
    public Class<?> getJavaClass() {
        return Void.TYPE;
    }
    
    @JRubyMethod(name = { "to_i" })
    public static RubyFixnum to_i(final IRubyObject recv) {
        return RubyFixnum.zero(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "to_f" })
    public static RubyFloat to_f(final IRubyObject recv) {
        return RubyFloat.newFloat(recv.getRuntime(), 0.0);
    }
    
    @JRubyMethod(name = { "to_s" })
    public static RubyString to_s(final IRubyObject recv) {
        return RubyString.newEmptyString(recv.getRuntime());
    }
    
    @JRubyMethod(name = { "to_a" })
    public static RubyArray to_a(final IRubyObject recv) {
        return recv.getRuntime().newEmptyArray();
    }
    
    @JRubyMethod(name = { "inspect" })
    public static RubyString inspect(final IRubyObject recv) {
        return recv.getRuntime().newString("nil");
    }
    
    @JRubyMethod(name = { "type" })
    public static RubyClass type(final IRubyObject recv) {
        return recv.getRuntime().getNilClass();
    }
    
    @JRubyMethod(name = { "&" }, required = 1)
    public static RubyBoolean op_and(final IRubyObject recv, final IRubyObject obj) {
        return recv.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "|" }, required = 1)
    public static RubyBoolean op_or(final IRubyObject recv, final IRubyObject obj) {
        return recv.getRuntime().newBoolean(obj.isTrue());
    }
    
    @JRubyMethod(name = { "^" }, required = 1)
    public static RubyBoolean op_xor(final IRubyObject recv, final IRubyObject obj) {
        return recv.getRuntime().newBoolean(obj.isTrue());
    }
    
    @JRubyMethod(name = { "nil?" })
    public IRubyObject nil_p() {
        return this.getRuntime().getTrue();
    }
    
    public RubyFixnum id() {
        return this.getRuntime().newFixnum(4);
    }
    
    public IRubyObject taint(final ThreadContext context) {
        return this;
    }
    
    @JRubyMethod(name = { "to_c" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject to_c(final ThreadContext context, final IRubyObject recv) {
        return RubyComplex.newComplexCanonicalize(context, RubyFixnum.zero(context.getRuntime()));
    }
    
    @JRubyMethod(name = { "to_r" }, compat = CompatVersion.RUBY1_9)
    public static IRubyObject to_r(final ThreadContext context, final IRubyObject recv) {
        return RubyRational.newRationalCanonicalize(context, RubyFixnum.zero(context.getRuntime()));
    }
    
    @JRubyMethod(name = { "rationalize" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject rationalize(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        return to_r(context, recv);
    }
    
    public Object toJava(final Class target) {
        if (target.isPrimitive()) {
            if (target == Boolean.TYPE) {
                return Boolean.FALSE;
            }
            if (target == Byte.TYPE) {
                return 0;
            }
            if (target == Short.TYPE) {
                return 0;
            }
            if (target == Character.TYPE) {
                return '\0';
            }
            if (target == Integer.TYPE) {
                return 0;
            }
            if (target == Long.TYPE) {
                return 0L;
            }
            if (target == Float.TYPE) {
                return 0.0f;
            }
            if (target == Double.TYPE) {
                return 0.0;
            }
        }
        return null;
    }
    
    static {
        NIL_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return runtime.getNil();
            }
        };
    }
}
