// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Arity;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "FFI::Type::Mapped" }, parent = "FFI::Type")
public final class MappedType extends Type
{
    private final Type realType;
    private final IRubyObject converter;
    private final DynamicMethod toNativeMethod;
    private final DynamicMethod fromNativeMethod;
    
    public static RubyClass createConverterTypeClass(final Ruby runtime, final RubyModule ffiModule) {
        final RubyClass convClass = ffiModule.fastGetClass("Type").defineClassUnder("Mapped", ffiModule.fastGetClass("Type"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        convClass.defineAnnotatedMethods(MappedType.class);
        convClass.defineAnnotatedConstants(MappedType.class);
        return convClass;
    }
    
    private MappedType(final Ruby runtime, final RubyClass klass, final Type nativeType, final IRubyObject converter, final DynamicMethod toNativeMethod, final DynamicMethod fromNativeMethod) {
        super(runtime, klass, NativeType.MAPPED, nativeType.getNativeSize(), nativeType.getNativeAlignment());
        this.realType = nativeType;
        this.converter = converter;
        this.toNativeMethod = toNativeMethod;
        this.fromNativeMethod = fromNativeMethod;
    }
    
    @JRubyMethod(name = { "new" }, meta = true)
    public static final IRubyObject newMappedType(final ThreadContext context, final IRubyObject klass, final IRubyObject converter) {
        if (!converter.respondsTo("native_type")) {
            throw context.getRuntime().newNoMethodError("converter needs a native_type method", "native_type", converter.getMetaClass());
        }
        final DynamicMethod toNativeMethod = converter.getMetaClass().searchMethod("to_native");
        if (toNativeMethod.isUndefined()) {
            throw context.getRuntime().newNoMethodError("converter needs a to_native method", "to_native", converter.getMetaClass());
        }
        if (!Arity.TWO_ARGUMENTS.equals(toNativeMethod.getArity())) {
            throw context.getRuntime().newArgumentError("to_native should accept two arguments");
        }
        final DynamicMethod fromNativeMethod = converter.getMetaClass().searchMethod("from_native");
        if (fromNativeMethod.isUndefined()) {
            throw context.getRuntime().newNoMethodError("converter needs a from_native method", "from_native", converter.getMetaClass());
        }
        if (!Arity.TWO_ARGUMENTS.equals(fromNativeMethod.getArity())) {
            throw context.getRuntime().newArgumentError("from_native should accept two arguments");
        }
        Type nativeType;
        try {
            nativeType = (Type)converter.callMethod(context, "native_type");
        }
        catch (ClassCastException ex) {
            throw context.getRuntime().newTypeError("native_type did not return instance of FFI::Type");
        }
        return new MappedType(context.getRuntime(), (RubyClass)klass, nativeType, converter, toNativeMethod, fromNativeMethod);
    }
    
    public final Type getRealType() {
        return this.realType;
    }
    
    public final boolean isReferenceRequired() {
        return false;
    }
    
    public final boolean isPostInvokeRequired() {
        return false;
    }
    
    @JRubyMethod
    public final IRubyObject native_type(final ThreadContext context) {
        return this.realType;
    }
    
    @JRubyMethod
    public final IRubyObject from_native(final ThreadContext context, final IRubyObject value, final IRubyObject ctx) {
        return this.fromNative(context, value);
    }
    
    @JRubyMethod
    public final IRubyObject to_native(final ThreadContext context, final IRubyObject value, final IRubyObject ctx) {
        return this.toNative(context, value);
    }
    
    public final IRubyObject fromNative(final ThreadContext context, final IRubyObject value) {
        return this.fromNativeMethod.call(context, this.converter, this.converter.getMetaClass(), "from_native", value, context.getRuntime().getNil());
    }
    
    public final IRubyObject toNative(final ThreadContext context, final IRubyObject value) {
        return this.toNativeMethod.call(context, this.converter, this.converter.getMetaClass(), "to_native", value, context.getRuntime().getNil());
    }
}
