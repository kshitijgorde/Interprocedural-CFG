// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "FFI::StructByValue" }, parent = "FFI::Type")
public final class StructByValue extends Type
{
    private final StructLayout structLayout;
    private final RubyClass structClass;
    
    public static RubyClass createStructByValueClass(final Ruby runtime, final RubyModule ffiModule) {
        final RubyClass sbvClass = ffiModule.defineClassUnder("StructByValue", ffiModule.fastGetClass("Type"), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        sbvClass.defineAnnotatedMethods(StructByValue.class);
        sbvClass.defineAnnotatedConstants(StructByValue.class);
        ffiModule.fastGetClass("Type").fastSetConstant("Struct", sbvClass);
        return sbvClass;
    }
    
    @JRubyMethod(name = { "new" }, meta = true)
    public static final IRubyObject newStructByValue(final ThreadContext context, final IRubyObject klass, final IRubyObject structClass) {
        if (!(structClass instanceof RubyClass)) {
            throw context.getRuntime().newTypeError("wrong argument type " + structClass.getMetaClass().getName() + " (expected Class)");
        }
        if (!((RubyClass)structClass).isKindOfModule(context.getRuntime().fastGetModule("FFI").fastGetClass("Struct"))) {
            throw context.getRuntime().newTypeError("wrong argument type " + structClass.getMetaClass().getName() + " (expected subclass of FFI::Struct)");
        }
        return new StructByValue(context.getRuntime(), (RubyClass)klass, (RubyClass)structClass, Struct.getStructLayout(context.getRuntime(), structClass));
    }
    
    private StructByValue(final Ruby runtime, final RubyClass klass, final RubyClass structClass, final StructLayout structLayout) {
        super(runtime, klass, NativeType.STRUCT, structLayout.size, structLayout.alignment);
        this.structClass = structClass;
        this.structLayout = structLayout;
    }
    
    StructByValue(final Ruby runtime, final RubyClass structClass, final StructLayout structLayout) {
        super(runtime, runtime.fastGetModule("FFI").fastGetClass("Type").fastGetClass("Struct"), NativeType.STRUCT, structLayout.size, structLayout.alignment);
        this.structClass = structClass;
        this.structLayout = structLayout;
    }
    
    @JRubyMethod(name = { "to_s" })
    public final IRubyObject to_s(final ThreadContext context) {
        return RubyString.newString(context.getRuntime(), String.format("#<FFI::StructByValue:%s>", this.structClass.getName()));
    }
    
    @JRubyMethod(name = { "layout" })
    public final IRubyObject layout(final ThreadContext context) {
        return this.structLayout;
    }
    
    @JRubyMethod(name = { "struct_class" })
    public final IRubyObject struct_class(final ThreadContext context) {
        return this.structClass;
    }
    
    public final StructLayout getStructLayout() {
        return this.structLayout;
    }
    
    public final RubyClass getStructClass() {
        return this.structClass;
    }
    
    public final Struct newStruct(final Ruby runtime, final byte[] data, final int offset) {
        return new Struct(runtime, this.structClass, this.structLayout, new Buffer(runtime, data, offset, this.structLayout.getSize()));
    }
}
