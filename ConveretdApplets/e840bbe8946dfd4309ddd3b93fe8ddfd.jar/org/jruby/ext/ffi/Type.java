// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.RubyNumeric;
import org.jruby.RubyString;
import org.jruby.RubySymbol;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import java.util.Iterator;
import java.util.Map;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyObject;

@JRubyClass(name = { "FFI::Type" }, parent = "Object")
public abstract class Type extends RubyObject
{
    protected final NativeType nativeType;
    protected final int size;
    protected final int alignment;
    
    public static RubyClass createTypeClass(final Ruby runtime, final RubyModule ffiModule) {
        final RubyClass typeClass = ffiModule.defineClassUnder("Type", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        typeClass.defineAnnotatedMethods(Type.class);
        typeClass.defineAnnotatedConstants(Type.class);
        final RubyClass builtinClass = typeClass.defineClassUnder("Builtin", typeClass, ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        builtinClass.defineAnnotatedMethods(Builtin.class);
        final RubyModule nativeType = ffiModule.defineModuleUnder("NativeType");
        defineBuiltinType(runtime, builtinClass, NativeType.CHAR, "char", "schar", "int8", "sint8");
        defineBuiltinType(runtime, builtinClass, NativeType.UCHAR, "uchar", "uint8");
        defineBuiltinType(runtime, builtinClass, NativeType.SHORT, "short", "sshort", "int16", "sint16");
        defineBuiltinType(runtime, builtinClass, NativeType.USHORT, "ushort", "uint16");
        defineBuiltinType(runtime, builtinClass, NativeType.INT, "int", "sint", "int32", "sint32");
        defineBuiltinType(runtime, builtinClass, NativeType.UINT, "uint", "uint32");
        defineBuiltinType(runtime, builtinClass, NativeType.LONG_LONG, "long_long", "slong_long", "int64", "sint64");
        defineBuiltinType(runtime, builtinClass, NativeType.ULONG_LONG, "ulong_long", "uint64");
        defineBuiltinType(runtime, builtinClass, NativeType.LONG, "long", "slong");
        defineBuiltinType(runtime, builtinClass, NativeType.ULONG, "ulong");
        defineBuiltinType(runtime, builtinClass, NativeType.FLOAT, "float", "float32");
        defineBuiltinType(runtime, builtinClass, NativeType.DOUBLE, "double", "float64");
        for (final NativeType t : NativeType.values()) {
            if (!builtinClass.hasConstant(t.name())) {
                try {
                    final Type b = new Builtin(runtime, builtinClass, t, t.name().toLowerCase());
                    builtinClass.defineConstant(t.name().toUpperCase(), b);
                }
                catch (UnsupportedOperationException ex) {}
            }
        }
        for (final Map.Entry<String, IRubyObject> c : builtinClass.getConstantMap().entrySet()) {
            if (c.getValue() instanceof Builtin) {
                typeClass.defineConstant(c.getKey(), c.getValue());
                nativeType.defineConstant(c.getKey(), c.getValue());
                ffiModule.defineConstant("TYPE_" + c.getKey(), c.getValue());
            }
        }
        final RubyClass arrayTypeClass = typeClass.defineClassUnder("Array", typeClass, ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        arrayTypeClass.defineAnnotatedMethods(Array.class);
        return typeClass;
    }
    
    private static final void defineBuiltinType(final Ruby runtime, final RubyClass builtinClass, final NativeType nativeType, final String... names) {
        try {
            if (names.length > 0) {
                for (final String n : names) {
                    builtinClass.setConstant(n.toUpperCase(), new Builtin(runtime, builtinClass, nativeType, n.toLowerCase()));
                }
            }
            else {
                builtinClass.setConstant(nativeType.name(), new Builtin(runtime, builtinClass, nativeType, nativeType.name().toLowerCase()));
            }
        }
        catch (UnsupportedOperationException ex) {}
    }
    
    public static final RubyClass getTypeClass(final Ruby runtime) {
        return runtime.fastGetModule("FFI").fastGetClass("Type");
    }
    
    protected Type(final Ruby runtime, final RubyClass klass, final NativeType type, final int size, final int alignment) {
        super(runtime, klass);
        this.nativeType = type;
        this.size = size;
        this.alignment = alignment;
    }
    
    protected Type(final Ruby runtime, final RubyClass klass, final NativeType type) {
        super(runtime, klass);
        this.nativeType = type;
        this.size = getNativeSize(type);
        this.alignment = getNativeAlignment(type);
    }
    
    public final NativeType getNativeType() {
        return this.nativeType;
    }
    
    public final int getNativeSize() {
        return this.size;
    }
    
    public final int getNativeAlignment() {
        return this.alignment;
    }
    
    @JRubyMethod(name = { "size" })
    public IRubyObject size(final ThreadContext context) {
        return context.getRuntime().newFixnum(this.getNativeSize());
    }
    
    @JRubyMethod(name = { "alignment" })
    public IRubyObject alignment(final ThreadContext context) {
        return context.getRuntime().newFixnum(this.getNativeAlignment());
    }
    
    private static final boolean isPrimitive(final NativeType type) {
        switch (type) {
            case VOID:
            case BOOL:
            case CHAR:
            case UCHAR:
            case SHORT:
            case USHORT:
            case INT:
            case UINT:
            case LONG_LONG:
            case ULONG_LONG:
            case LONG:
            case ULONG:
            case FLOAT:
            case DOUBLE:
            case BUFFER_IN:
            case BUFFER_INOUT:
            case BUFFER_OUT:
            case POINTER:
            case STRING:
            case RBXSTRING: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private static final int getNativeAlignment(final NativeType type) {
        return isPrimitive(type) ? Factory.getInstance().alignmentOf(type) : 1;
    }
    
    private static final int getNativeSize(final NativeType type) {
        return isPrimitive(type) ? Factory.getInstance().sizeOf(type) : 0;
    }
    
    @JRubyClass(name = { "FFI::Type::Builtin" }, parent = "FFI::Type")
    public static final class Builtin extends Type
    {
        private final RubySymbol sym;
        
        private Builtin(final Ruby runtime, final RubyClass klass, final NativeType nativeType, final String symName) {
            super(runtime, klass, nativeType, getNativeSize(nativeType), getNativeAlignment(nativeType));
            this.sym = runtime.newSymbol(symName);
        }
        
        @JRubyMethod(name = { "to_s" })
        public final IRubyObject to_s(final ThreadContext context) {
            return RubyString.newString(context.getRuntime(), String.format("#<FFI::Type::Builtin:%s size=%d alignment=%d>", this.nativeType.name(), this.size, this.alignment));
        }
        
        public final String toString() {
            return this.nativeType.name();
        }
        
        public boolean equals(final Object obj) {
            return obj instanceof Builtin && ((Builtin)obj).nativeType.equals(this.nativeType);
        }
        
        public int hashCode() {
            int hash = 5;
            hash = 23 * hash + this.nativeType.hashCode();
            return hash;
        }
        
        @JRubyMethod
        public final IRubyObject to_sym(final ThreadContext context) {
            return this.sym;
        }
        
        @JRubyMethod(name = { "==" }, required = 1)
        public IRubyObject op_equal(final ThreadContext context, final IRubyObject obj) {
            return context.getRuntime().newBoolean(this.equals(obj));
        }
        
        @JRubyMethod(name = { "equal?" }, required = 1)
        public IRubyObject equal_p(final ThreadContext context, final IRubyObject obj) {
            return context.getRuntime().newBoolean(this.equals(obj));
        }
        
        @JRubyMethod(name = { "eql?" }, required = 1)
        public IRubyObject eql_p(final ThreadContext context, final IRubyObject obj) {
            return context.getRuntime().newBoolean(this.equals(obj));
        }
    }
    
    @JRubyClass(name = { "FFI::Type::Array" }, parent = "FFI::Type")
    public static final class Array extends Type
    {
        private final Type componentType;
        private final int length;
        
        public Array(final Ruby runtime, final RubyClass klass, final Type componentType, final int length) {
            super(runtime, klass, NativeType.ARRAY, componentType.getNativeSize() * length, componentType.getNativeAlignment());
            this.componentType = componentType;
            this.length = length;
        }
        
        public Array(final Ruby runtime, final Type componentType, final int length) {
            this(runtime, Type.getTypeClass(runtime).fastGetClass("Array"), componentType, length);
        }
        
        public final Type getComponentType() {
            return this.componentType;
        }
        
        public final int length() {
            return this.length;
        }
        
        @JRubyMethod(name = { "new" }, required = 2, meta = true)
        public static final IRubyObject newInstance(final ThreadContext context, final IRubyObject klass, final IRubyObject componentType, final IRubyObject length) {
            if (!(componentType instanceof Type)) {
                throw context.getRuntime().newTypeError(componentType, Type.getTypeClass(context.getRuntime()));
            }
            return new Array(context.getRuntime(), (RubyClass)klass, (Type)componentType, RubyNumeric.fix2int(length));
        }
        
        @JRubyMethod
        public final IRubyObject length(final ThreadContext context) {
            return context.getRuntime().newFixnum(this.length);
        }
        
        @JRubyMethod
        public final IRubyObject elem_type(final ThreadContext context) {
            return this.componentType;
        }
    }
}
