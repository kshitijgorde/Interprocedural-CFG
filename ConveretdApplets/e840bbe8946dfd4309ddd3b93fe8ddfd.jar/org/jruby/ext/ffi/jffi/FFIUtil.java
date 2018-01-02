// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.RubyModule;
import org.jruby.RubyHash;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyString;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import com.kenai.jffi.Array;
import java.util.Iterator;
import java.util.Collection;
import com.kenai.jffi.Struct;
import org.jruby.ext.ffi.MappedType;
import org.jruby.ext.ffi.StructByValue;
import org.jruby.ext.ffi.StructLayout;
import org.jruby.ext.ffi.CallbackInfo;
import java.util.EnumMap;
import com.kenai.jffi.Type;
import org.jruby.ext.ffi.NativeType;
import java.util.Map;
import com.kenai.jffi.MemoryIO;

public final class FFIUtil
{
    private static final MemoryIO IO;
    private static final Map<NativeType, Type> typeMap;
    
    private static final Map<NativeType, Type> buildTypeMap() {
        final Map<NativeType, Type> m = new EnumMap<NativeType, Type>(NativeType.class);
        m.put(NativeType.VOID, Type.VOID);
        m.put(NativeType.BOOL, Type.UINT8);
        m.put(NativeType.CHAR, Type.SCHAR);
        m.put(NativeType.SHORT, Type.SSHORT);
        m.put(NativeType.INT, Type.SINT);
        m.put(NativeType.LONG, Type.SLONG);
        m.put(NativeType.LONG_LONG, Type.SLONG_LONG);
        m.put(NativeType.UCHAR, Type.UCHAR);
        m.put(NativeType.USHORT, Type.USHORT);
        m.put(NativeType.UINT, Type.UINT);
        m.put(NativeType.ULONG, Type.ULONG);
        m.put(NativeType.ULONG_LONG, Type.ULONG_LONG);
        m.put(NativeType.FLOAT, Type.FLOAT);
        m.put(NativeType.DOUBLE, Type.DOUBLE);
        m.put(NativeType.POINTER, Type.POINTER);
        m.put(NativeType.BUFFER_IN, Type.POINTER);
        m.put(NativeType.BUFFER_OUT, Type.POINTER);
        m.put(NativeType.BUFFER_INOUT, Type.POINTER);
        m.put(NativeType.STRING, Type.POINTER);
        return m;
    }
    
    static final Type getFFIType(final org.jruby.ext.ffi.Type type) {
        if (type instanceof org.jruby.ext.ffi.Type.Builtin || type instanceof CallbackInfo) {
            return getFFIType(type.getNativeType());
        }
        if (type instanceof StructLayout) {
            return newStruct((StructLayout)type);
        }
        if (type instanceof StructByValue) {
            return newStruct(((StructByValue)type).getStructLayout());
        }
        if (type instanceof org.jruby.ext.ffi.Type.Array) {
            return newArray((org.jruby.ext.ffi.Type.Array)type);
        }
        if (type instanceof MappedType) {
            return getFFIType(((MappedType)type).getRealType());
        }
        return null;
    }
    
    static final Type getFFIType(final NativeType type) {
        return FFIUtil.typeMap.get(type);
    }
    
    static final Struct newStruct(final StructLayout layout) {
        final Collection<StructLayout.Member> structMembers = layout.getMembers();
        final Type[] fields = new Type[structMembers.size()];
        int i = 0;
        for (final StructLayout.Member m : structMembers) {
            final Type fieldType = getFFIType(m.type());
            if (fieldType == null) {
                throw layout.getRuntime().newTypeError("unsupported Struct field type " + m);
            }
            fields[i++] = fieldType;
        }
        return new Struct(fields);
    }
    
    static final Array newArray(final org.jruby.ext.ffi.Type.Array arrayType) {
        final Type componentType = getFFIType(arrayType.getComponentType());
        if (componentType == null) {
            throw arrayType.getRuntime().newTypeError("unsupported array element type " + arrayType.getComponentType());
        }
        return new Array(componentType, arrayType.length());
    }
    
    static final IRubyObject getString(final Ruby runtime, final long address) {
        if (address == 0L) {
            return runtime.getNil();
        }
        final byte[] bytes = getZeroTerminatedByteArray(address);
        if (bytes.length == 0) {
            return RubyString.newEmptyString(runtime);
        }
        final RubyString s = RubyString.newStringNoCopy(runtime, bytes);
        s.setTaint(true);
        return s;
    }
    
    static final byte[] getZeroTerminatedByteArray(final long address) {
        return FFIUtil.IO.getZeroTerminatedByteArray(address);
    }
    
    static final byte[] getZeroTerminatedByteArray(final long address, final int maxlen) {
        return FFIUtil.IO.getZeroTerminatedByteArray(address, maxlen);
    }
    
    static final void putZeroTerminatedByteArray(final long address, final byte[] bytes, final int off, final int len) {
        FFIUtil.IO.putByteArray(address, bytes, off, len);
        FFIUtil.IO.putByte(address + len, (byte)0);
    }
    
    static final org.jruby.ext.ffi.Type resolveType(final ThreadContext context, final IRubyObject obj) {
        if (obj instanceof org.jruby.ext.ffi.Type) {
            return (org.jruby.ext.ffi.Type)obj;
        }
        final RubyModule ffi = context.getRuntime().fastGetModule("FFI");
        final IRubyObject typeDefs = ffi.fastFetchConstant("TypeDefs");
        if (!(typeDefs instanceof RubyHash)) {
            throw context.getRuntime().newRuntimeError("invalid or corrupted FFI::TypeDefs");
        }
        IRubyObject type = ((RubyHash)typeDefs).fastARef(obj);
        if (type == null || type.isNil()) {
            type = ffi.callMethod(context, "find_type", obj);
        }
        if (!(type instanceof org.jruby.ext.ffi.Type)) {
            throw context.getRuntime().newTypeError("Could not resolve type: " + obj);
        }
        return (org.jruby.ext.ffi.Type)type;
    }
    
    static {
        IO = MemoryIO.getInstance();
        typeMap = buildTypeMap();
    }
}
