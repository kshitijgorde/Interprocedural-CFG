// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.runtime.Block;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "FFI::StructByReference" }, parent = "Object")
public final class StructByReference extends RubyObject
{
    private final StructLayout structLayout;
    private final RubyClass structClass;
    
    public static RubyClass createStructByReferenceClass(final Ruby runtime, final RubyModule ffiModule) {
        final RubyClass sbrClass = ffiModule.defineClassUnder("StructByReference", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        sbrClass.defineAnnotatedMethods(StructByReference.class);
        sbrClass.defineAnnotatedConstants(StructByReference.class);
        sbrClass.includeModule(ffiModule.fastGetConstant("DataConverter"));
        return sbrClass;
    }
    
    @JRubyMethod(name = { "new" }, meta = true)
    public static final IRubyObject newStructByReference(final ThreadContext context, final IRubyObject klass, final IRubyObject structClass) {
        if (!(structClass instanceof RubyClass)) {
            throw context.getRuntime().newTypeError("wrong argument type " + structClass.getMetaClass().getName() + " (expected Class)");
        }
        if (!((RubyClass)structClass).isKindOfModule(context.getRuntime().fastGetModule("FFI").fastGetClass("Struct"))) {
            throw context.getRuntime().newTypeError("wrong argument type " + structClass.getMetaClass().getName() + " (expected subclass of FFI::Struct)");
        }
        return new StructByReference(context.getRuntime(), (RubyClass)klass, (RubyClass)structClass, Struct.getStructLayout(context.getRuntime(), structClass));
    }
    
    private StructByReference(final Ruby runtime, final RubyClass klass, final RubyClass structClass, final StructLayout layout) {
        super(runtime, klass);
        this.structClass = structClass;
        this.structLayout = layout;
    }
    
    @JRubyMethod(name = { "to_s" })
    public final IRubyObject to_s(final ThreadContext context) {
        return RubyString.newString(context.getRuntime(), String.format("#<FFI::StructByReference:%s>", this.structClass.getName()));
    }
    
    @JRubyMethod(name = { "layout" })
    public final IRubyObject layout(final ThreadContext context) {
        return this.structLayout;
    }
    
    @JRubyMethod(name = { "struct_class" })
    public final IRubyObject struct_class(final ThreadContext context) {
        return this.structClass;
    }
    
    @JRubyMethod(name = { "native_type" })
    public IRubyObject native_type(final ThreadContext context) {
        return context.getRuntime().fastGetModule("FFI").fastGetClass("Type").fastGetConstant("POINTER");
    }
    
    @JRubyMethod(name = { "to_native" })
    public IRubyObject to_native(final ThreadContext context, final IRubyObject value, final IRubyObject ctx) {
        if (value instanceof Struct) {
            return ((Struct)value).getMemory();
        }
        if (value.isNil()) {
            return Pointer.getNull(context.getRuntime());
        }
        throw context.getRuntime().newTypeError(value, context.getRuntime().fastGetModule("FFI").fastGetClass("Struct"));
    }
    
    @JRubyMethod(name = { "from_native" })
    public IRubyObject from_native(final ThreadContext context, final IRubyObject value, final IRubyObject ctx) {
        if (value instanceof AbstractMemory) {
            return this.getStructClass().newInstance(context, new IRubyObject[] { (AbstractMemory)value }, Block.NULL_BLOCK);
        }
        if (value.isNil()) {
            return this.getStructClass().newInstance(context, new IRubyObject[] { Pointer.getNull(context.getRuntime()) }, Block.NULL_BLOCK);
        }
        throw context.getRuntime().newTypeError(value, context.getRuntime().fastGetModule("FFI").fastGetClass("Pointer"));
    }
    
    public final StructLayout getStructLayout() {
        return this.structLayout;
    }
    
    public final RubyClass getStructClass() {
        return this.structClass;
    }
}
